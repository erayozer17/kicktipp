package com.erayozer.kicktipp.worker;

import org.jobrunr.jobs.annotations.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RecurrentWorkerService {

    public static final long EXECUTION_TIME = 5000L;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final AtomicInteger count = new AtomicInteger();

    @Job(name = "The sample job with variable %0", retries = 2)//this name appears on dashboard port 8000
    public void executeSampleJob(String variable) {

        logger.info("The sample job has begun. The variable you passed is {}", variable);
        try {
            Thread.sleep(EXECUTION_TIME);
        } catch (InterruptedException e) {
            logger.error("Error while executing sample job", e);
        } finally {
            count.incrementAndGet();
            logger.info("Sample job has finished...");
        }
    }

    public int getNumberOfInvocations() {
        return count.get();
    }
}
