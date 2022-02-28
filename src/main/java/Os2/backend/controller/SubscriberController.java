package Os2.backend.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import Os2.backend.service.SubscriberRepository;
import Os2.backend.model.*;
import Os2.backend.model.Package;

@RestController
public class SubscriberController {
	private PayUnitDetails payunitDetails = new PayUnitDetails();
	
	@Autowired
	private SubscriberRepository subscriberRepo;

	

	
	@PostMapping("/subscribers")
	public Subscriber create(Subscriber subscriber) {
		return this.subscriberRepo.save(subscriber);
	}
	
	@GetMapping("/subscribers")
	public List<Subscriber> getAll(){
		return this.subscriberRepo.findAll();
	}
	
	@GetMapping("/subscribers/sort/{order}")
	public List<Subscriber> getAllSorted(Sort order){
		return this.subscriberRepo.findAll(order);
	}
	
	
	@GetMapping("/usbscribers/{id}")
	public Optional<Subscriber> getById(@PathVariable("id") int subscriberId){
		return this.subscriberRepo.findById(subscriberId);
	}
	
	@PutMapping("/subscriber/{id}")
	public Subscriber update(@PathVariable("id") int subscriberId, @RequestBody Subscriber update) {
		update.id = subscriberId;
		return this.subscriberRepo.save(update);
	}
	
	@DeleteMapping("/subscribers/{id}")
	public void delete(@PathVariable("id") int subscriberId) {
		this.subscriberRepo.deleteById(subscriberId);
	}
	
	@PostMapping("/subscribe")
	public HttpEntity<SubscriptionRequestModel> subscribe(@RequestBody SubscriptionRequestModel subscription) {//subscription is an object containing the package details and the subscriber details; s_package and subscriber
//		if subscription is not null, perform payUnit transaction and register subscriber and transaction details
		if(!(subscription.equals(null))) {
//			make payment
			
			PayUnitDetails.PaymentRequestBody req = this.payunitDetails.new PaymentRequestBody();
			req.transaction_id = ((Math.random()*1000)*(new Date()).hashCode())+"";
			req.amount = subscription.s_package.price+"";
			req.currency = "XAF";
			req.phone_number = subscription.subscriber.contact;
			req.gateway = "mtnmomo";
			req.paymentType = "button";
			
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(this.payunitDetails.content_type);
			headers.setBasicAuth(this.payunitDetails.authorization);
			headers.set("x-api-key", this.payunitDetails.x_api_key);
			headers.set("mode", this.payunitDetails.mode);
			
			
			HttpEntity<PayUnitDetails.PaymentRequestBody> request = new HttpEntity<PayUnitDetails.PaymentRequestBody>(req, headers);
					
			
			RestTemplate restTemplate = new RestTemplate();
			PayUnitDetails.PaymentResponse payment_response = restTemplate.postForObject(this.payunitDetails.init_url, request, PayUnitDetails.PaymentResponse.class);
			
//			if payment is successful, register subscriber and payment
			if(payment_response.statusCode == 200) {
//				register subscriber and get the subscriber_id
				Subscriber subscriber = subscription.subscriber;
				subscriber.package_id = subscription.s_package.id;
				
				SubscriberController subscriber_controller = new SubscriberController();
				Subscriber resp = subscriber_controller.create(subscriber);
				
				
				Payment payment = new Payment();
				payment.amount = subscription.s_package.price;
				payment.currency = "XAF";
				payment.date = new Date();
				payment.subscriber_id = resp.id;
				payment.package_id = subscription.s_package.id;
				payment.transaction_id = payment_response.data.transaction_id;
				payment.payment_ref = payment_response.data.payment_ref;
				payment.pay_token = payment_response.data.pay_token;
				
				PaymentController pay_controller = new PaymentController();
				Payment transaction = pay_controller.savePayment(payment);
				if(!transaction.equals(null)) {
					SubscriptionRequestModel response = new SubscriptionRequestModel();
					response.s_package = subscription.s_package;
					response.subscriber = resp;
					
					return new HttpEntity<SubscriptionRequestModel>(response);
				}
				
				
			}
			
		}else {
//			flag subscription status to failed and return null
		}
		return null;
	}
	
	public class SubscriptionRequestModel{
		public Package s_package;
		public Subscriber subscriber;
	}
	
	public class InitRequest{
		String transaction_id;
		String total_amount;
		String currency;
		String return_url;
		String name;
	}
	
	public class InitResponse{
		String t_id;
		String t_sum;
		String t_url;
		String transaction_id;
		String transaction_url;//url for payment execution
	}

}
