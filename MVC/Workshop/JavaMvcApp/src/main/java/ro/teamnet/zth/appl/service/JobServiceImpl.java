package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.dao.JobDao;
import ro.teamnet.zth.appl.domain.Job;

import java.util.List;

/**
 * Created by cizuss94 on 7/16/2016.
 */
public class JobServiceImpl implements JobService {
    @Override
    public List<Job> findAllJobs() {
        return new JobDao().getAllJobs();
    }

    @Override
    public Job findOneJob(String id) {
        return new JobDao().getJobById(id);
    }

    @Override
    public void deleteOneJob(String jobId) {
        new JobDao().deleteJob(new JobDao().getJobById(jobId));
    }

    @Override
    public void saveOneJob(String jobId, String jobTitle, int minSalary, int maxSalary) {
        Job newJob = new Job();
        newJob.setId(jobId);
        newJob.setJobTitle(jobTitle);
        newJob.setMinSalary(minSalary);
        newJob.setMaxSalary(maxSalary);
        new JobDao().insertJob(newJob);
    }
}
