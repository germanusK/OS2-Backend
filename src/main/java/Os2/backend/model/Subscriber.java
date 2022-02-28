package Os2.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="subscriber")
@Getter
@Setter
public class Subscriber {
	
	@Id
	@Column(unique=true, name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int id;
	@Column(name="name")
	public String name;
	@Column(name="email")
	public String email;
	@Column(name="contact")
	public String contact;
	@Column(name="username")
	public String username;
	@Column(name="password")
	public String password;
	@Column(name="package_id")
	public long package_id;
	
}
