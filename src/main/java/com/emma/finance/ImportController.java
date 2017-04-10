package com.emma.finance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/import")
public class ImportController {
	
	private static final Logger log = LoggerFactory.getLogger(ImportController.class);
	
    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @RequestMapping(value = "/run", method = RequestMethod.POST)
    public @ResponseBody String runImport() {
    	log.info("Controller: Invoke Import Batch");
    	
    	JobExecution jobExecution = null;
        try {
        	
        	JobParameters jobParameters = new JobParametersBuilder().addLong("time",System.currentTimeMillis()).toJobParameters();
        	
            jobExecution = jobLauncher.run(job, jobParameters);
        } catch (Exception e) {
        	log.info(e.getMessage());
        }
    	
        return "jobExecution's info: Id = " + jobExecution.getId() + " ,status = " + jobExecution.getExitStatus();
    }
}