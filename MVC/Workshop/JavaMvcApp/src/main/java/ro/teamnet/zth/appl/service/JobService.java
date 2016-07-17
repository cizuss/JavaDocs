package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.domain.Job;

import java.util.List;

/**
 * Created by cizuss94 on 7/16/2016.
 */
public interface JobService {
    List<Job> findAllJobs();
    Job findOneJob(String id);
    void deleteOneJob(String jobId);
    void saveOneJob(String jobId, String jobTitle, int minSalary, int maxSalary);
}
