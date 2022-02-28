package Os2.backend.service;

import org.springframework.data.jpa.repository.JpaRepository;

import Os2.backend.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
