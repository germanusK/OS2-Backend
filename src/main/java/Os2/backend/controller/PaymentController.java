package Os2.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Os2.backend.model.Payment;
import Os2.backend.service.PaymentRepository;

@RestController
public class PaymentController {
	
	@Autowired
	private PaymentRepository paymentRepository;

	@PostMapping("/payments")
	public Payment savePayment(@RequestBody Payment payment) {
		return this.paymentRepository.save(payment);
	}
	
	@GetMapping("/payments")
	public List<Payment> getAll(){
		return this.paymentRepository.findAll();
	}
	
	@GetMapping("/payments/sort/{order}")
	public List<Payment> getAllSorted(Sort order){
		return this.paymentRepository.findAll(order);
	}

	@GetMapping("/payments/{id}")
	public Optional<Payment> getById(int paymentId) {
		return this.paymentRepository.findById(paymentId);
	}
	
	@PutMapping("/payments/{id}")
	public Payment update(@PathVariable("id") int paymentId, @RequestBody Payment payment) {
		payment.id = paymentId;
		return this.paymentRepository.save(payment);
	}
	
	@DeleteMapping("/payments/{id}")
	public void delete(@PathVariable("id") int paymentId) {
		this.paymentRepository.deleteById(paymentId);
	}
}
