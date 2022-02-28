package Os2.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="payment")
@Getter
@Setter
public class Payment {
	@Id
	@Column(name="id", unique=true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int id;
	@Column(name="amount")
	public double amount;
	@Column(name="currency")
	public String currency;
	@Column(name="date")
	public Date date;
	@Column(name="transaction_id")
	public String transaction_id;
	@Column(name="payment_ref")
	public String payment_ref;
	@Column(name="pay_token")
	public String pay_token;
	@Column(name="package_id")
	public long package_id;
	@Column(name="subscriber_id")
	public long subscriber_id;
	
}
