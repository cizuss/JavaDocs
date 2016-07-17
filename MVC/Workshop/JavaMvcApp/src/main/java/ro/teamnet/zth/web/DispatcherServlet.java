package ro.teamnet.zth.web;

import org.codehaus.jackson.map.ObjectMapper;
import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestParameter;
import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.appl.controller.DepartmentController;
import ro.teamnet.zth.appl.controller.EmployeeController;

import ro.teamnet.zth.fmk.AnnotationScanUtils;
import ro.teamnet.zth.fmk.MethodAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.stream.Collectors;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 7/14/2016.
 */
public class DispatcherServlet extends HttpServlet {
    public HashMap<String, MethodAttributes> allowedMethods;
    public void init() throws ServletException {
        allowedMethods = new HashMap<String, MethodAttributes>();
        try {
            List<Class> controllers = (List<Class>) AnnotationScanUtils.getClasses("ro.teamnet.zth.appl.controller");
            for (Class controller : controllers) {
                if (controller.isAnnotationPresent(MyController.class)) {
                    MyController annotation = (MyController) controller.getAnnotation(MyController.class);
                    String urlPath = annotation.urlPath();
                    Method[] methods = controller.getMethods();
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(MyRequestMethod.class)) {
                            MyRequestMethod methodAnnotation = method.getAnnotation(MyRequestMethod.class);
                            String methodUrlPath = methodAnnotation.urlPath();
                            String methodType = methodAnnotation.methodType();
                            MethodAttributes newMethodAttributes = new MethodAttributes();
                            newMethodAttributes.setControllerClass(controller.getName());
                            newMethodAttributes.setMethodName(method.getName());
                            newMethodAttributes.setMethodType(methodType);

                            // set the parameters
                            newMethodAttributes.setParameterTypes(method.getParameterTypes());
                            String finalPath = urlPath + methodUrlPath + methodAnnotation.methodType();
                            allowedMethods.put(finalPath, newMethodAttributes);
                        }
                    }
                }
            }
            } catch(ClassNotFoundException e){
                e.printStackTrace();
            } catch(IOException e){
                e.printStackTrace();
            }
        }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply("GET", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply("POST", req, resp);
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        dispatchReply("DELETE", req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply("PUT", req, resp);
    }

    private void dispatchReply(String requestType, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Object objectToReply = null;
        try {
            objectToReply = dispatch(requestType, req, resp);
        } catch (Exception e) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("C:\\test\\exceptions.txt")));
            bw.write("Exception with reqtype = " + requestType + " and exception type = " + e.getClass());
            bw.write("\r\n" + e.getStackTrace().toString());
            e.printStackTrace(resp.getWriter());
            bw.close();
        }
        reply(objectToReply, req, resp);
    }

    private Object dispatch(String requestType, HttpServletRequest req, HttpServletResponse resp) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, IOException {
        String urlPath = req.getPathInfo();
        MethodAttributes methodAttributes = allowedMethods.get(urlPath + requestType);
        Class controllerClass = Class.forName(methodAttributes.getControllerClass());
        Method methodToExecute = controllerClass.getMethod(methodAttributes.getMethodName(), methodAttributes.getParameterTypes());
        Parameter[] methodParameters = methodToExecute.getParameters();
        List<Object> methodArguments = new ArrayList<Object>();
        // daca request e de tip POST iau parametrii din request body, nu din url
        if (requestType.equalsIgnoreCase("post")) {
            String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            Map<String, String> paramMap = HttpPostParser.getParameterMap(requestBody);
            for (Parameter parameter : methodParameters) {
                if (parameter.isAnnotationPresent(MyRequestParameter.class)) {
                    MyRequestParameter annotation = parameter.getAnnotation(MyRequestParameter.class);
                    String name = annotation.name();
                    String parameterValueFromReqBody = paramMap.get(name);
                    Object argument = new ObjectMapper().readValue(parameterValueFromReqBody, parameter.getType());
                    methodArguments.add(argument);
                }
            }
            Object objectToReply = controllerClass.newInstance();
            resp.getWriter().write(new ObjectMapper().writeValueAsString(objectToReply));
            return methodToExecute.invoke(objectToReply, methodArguments.toArray());
        }
        // altfel, daca request e de tip get sau delete
        for (Parameter parameter : methodParameters) {
            if (parameter.isAnnotationPresent(MyRequestParameter.class)) {
                MyRequestParameter annotation = parameter.getAnnotation(MyRequestParameter.class);
                String name = annotation.name();
                String parameterValueFromUrl = req.getParameter(name);
                Object argument = new ObjectMapper().readValue(parameterValueFromUrl, parameter.getType());
                methodArguments.add(argument);
            }
        }
        Object objectToReply = controllerClass.newInstance();
        return methodToExecute.invoke(objectToReply, methodArguments.toArray());
    }

    private void reply(Object objectToReply, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        resp.getWriter().write(mapper.writeValueAsString(objectToReply));
    }
}
