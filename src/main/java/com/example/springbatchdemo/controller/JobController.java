package com.example.springbatchdemo.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class JobController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    JobOperator jobOperator;

    @Resource(name="fileItemWriterDemoJob")
    private Job batchJob;

    @GetMapping("/runJob")
    public void runJob(@RequestParam("job1param") String job1param) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder().addString("job1param",job1param).toJobParameters();
        //JobExecution run = jobLauncher.run(batchJob, new JobParametersBuilder().addLong("time",System.currentTimeMillis()).toJobParameters());
        JobExecution run = jobLauncher.run(batchJob, jobParameters);
        run.getId();
    }

}