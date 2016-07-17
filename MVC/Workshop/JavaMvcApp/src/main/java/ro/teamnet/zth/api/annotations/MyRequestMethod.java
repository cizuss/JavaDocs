package ro.teamnet.zth.api.annotations;

<<<<<<< HEAD
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
=======
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by user on 7/14/2016.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
>>>>>>> 855c1a6880e16f18104918fdd2e8cbca3602e0f4
public @interface MyRequestMethod {
    String methodType() default "GET";
    String urlPath();
}
