package Os2.backend.model;

public class User {

	public String username;
	public String email;
	public String password;
	
	public User() {
	}
	
	public User(String username, String email, String password) {
		this.setUsername(username);
		this.setEmail(email);
		this.setPassword(password);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
