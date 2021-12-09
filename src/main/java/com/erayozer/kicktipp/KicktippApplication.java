package com.erayozer.kicktipp;

import com.erayozer.kicktipp.worker.RecurrentWorkerService;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.cron.Cron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class KicktippApplication {

	@Autowired
	private JobScheduler jobScheduler;

	public static void main(String[] args) {
		SpringApplication.run(KicktippApplication.class, args);
	}

	@PostConstruct
	public void scheduleRecurrent(){
		jobScheduler.<RecurrentWorkerService>scheduleRecurrently("score-retriever", Cron.every5minutes(), x -> x.executeSampleJob("yeni job basladi"));
		//jobScheduler.delete("score-retriever"); // this stops the job above (pay attention to the id)
		// https://www.api-football.com/documentation-v3#operation/get-fixtures
	}
}
