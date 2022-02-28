package Os2.backend.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import Os2.backend.model.Package;
import Os2.backend.model.Subscriber;
import Os2.backend.controller.*;

@EnableScheduling
public class RecurrentService {

	private String interval;
	Package _package;
	Subscriber subscriber;
	
	
	
	public  RecurrentService(SubscriberController.SubscriptionRequestModel subscription) {
		this.interval = subscription.s_package.duration;
	}
	
	@Scheduled(cron = "@hourly")
	
	public void scheduleSubscription(SubscriberController.SubscriptionRequestModel subscription) {
		
	}
}
