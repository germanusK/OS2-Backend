package Os2.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity //indicates that a class is a database model
@Table(name = "_package") //sets the name of the table that the model will be mapped to
@Getter //Generate getters for all the fields in the model
@Setter //Generate setters for all the fields in the model
public class Package {
	
	@Id
	@Column(name="id", unique=true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	@Column(unique=true, name="label")
	public String label;
	@Column(name="duration")
	public String duration;//duration_monthly, duration_weekly, duration_daily, duration_hourly, duration_per_minute
	@Column(name="price")
	public double price;
	@Column(name="description")
	public String description;
	
	public class Duration{
		public String DURATION_YEARLY = "@yearly";
		public String DURATION_MONTHLY = "@monthly";
		public String DURATION_WEEKLY = "@weekly";
		public String DURATION_DAILY = "@daily";
		public String DURATION_HOURLY = "@hourly";
		public String DURATION_PER_MINUTE = "0 * * * * *";
		@SuppressWarnings("deprecation")
		public String DURATION_CUSTOM(int time_in_seconds) {
			StringBuilder builder = new StringBuilder();
			Date customInterval = new Date(time_in_seconds);
			int customArray[] = {
			                     customInterval.getSeconds(), 
			                     customInterval.getMinutes(), 
			                     customInterval.getHours(), 
			                     customInterval.getDay(), 
			                     customInterval.getMonth(), 
			                     customInterval.getYear()
								};
			
			builder = build(customArray, 0, 5, builder).reverse();
			return builder.toString();
		}
		
//		recursive method to build cron interval pattern string builder
		private StringBuilder build(int[] gmtArray, int flag, int index, StringBuilder _builder) {//zero based index; from 0 to 5
			if(index == 0) {
				if(flag == 0) {
					if(gmtArray[index] == 0)
						_builder.append(" *");
					else
						_builder.append(" "+gmtArray[index]);
				}
				return _builder;
			}
			else {
				if(flag == 0) {
					if(gmtArray[index] == 0)
						_builder.append(" *");
					else {
						_builder.append(" "+gmtArray[index]);
						flag = 1;
					}
				}
				else {
					if(gmtArray[index] == 0)
						_builder.append(" 0");
					else {
						_builder.append(" "+gmtArray[index]);
					}
				}
				return build(gmtArray, flag, index-1, _builder);
			}
		}
	}
}
