package Os2.backend.model;

import java.util.Base64;
import org.springframework.http.MediaType;

public class PayUnitDetails {
	public String base_url = "https://app.payunit.net/api";
	public String init_url = this.base_url+"/gateway/initialize";
	public String payment_url = this.base_url+"/gateway/makepayment";
	
	public class PaymentRequestBody {
		public String gateway;
		public String amount;
		public String transaction_id;
		public String phone_number;
		public String currency;
		public String paymentType;
	}
	
	public class PaymentResponseBody{
		public String transaction_id;
		public String payment_ref;
		public String pay_token;
	}
	
	public class PaymentResponse{
		public String status;
		public int statusCode;
		public String message;
		public PaymentResponseBody data;
	}
	
	
	public String api_key = "7bef9f3f4cbf96bf2ecf3c7e596fa729059c8a9d";
	public String api_user = "payunit_sand_hTRBIXZu6";
	public String api_password = "6bce09b2-0314-489e-a665-e0e16b2807c0";
	
	
	public MediaType content_type = MediaType.APPLICATION_JSON;
	public String authorization = Base64.getEncoder().encodeToString((api_user+":"+api_password).getBytes()); 
	public String x_api_key = api_key;
	public String mode = "test";

	
}
