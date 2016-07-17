package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestParameter;
import ro.teamnet.zth.appl.domain.Job;
import ro.teamnet.zth.appl.service.JobServiceImpl;

import java.util.List;

/**
 * Created by cizuss94 on 7/15/2016.
=======

/**
 * Created by user on 7/14/2016.
>>>>>>> 855c1a6880e16f18104918fdd2e8cbca3602e0f4
 */
@MyController(urlPath = "/jobs")
public class JobController {
    @MyRequestMethod(urlPath = "/all")
    public List<Job> getAllJobs() {
        return new JobServiceImpl().findAllJobs();
    }
    @MyRequestMethod(urlPath = "/one")
    public Job getOneJob(@MyRequestParameter(name = "id") String jobId) {
        return new JobServiceImpl().findOneJob(jobId);
    }
    @MyRequestMethod(urlPath = "/one", methodType = "DELETE")
    public void deleteOneJob(@MyRequestParameter(name = "id") String jobId) {
        new JobServiceImpl().deleteOneJob(jobId);
    }
    @MyRequestMethod(urlPath = "/one", methodType = "POST")
    public void saveOneJob(@MyRequestParameter(name = "id") String jobId,
                            @MyRequestParameter(name = "title") String jobTitle,
                           @MyRequestParameter(name = "minsalary") int minSalary,
                           @MyRequestParameter(name = "maxsalary") int maxSalary)
    {
        new JobServiceImpl().saveOneJob(jobId,jobTitle, minSalary, maxSalary);
    }
}
