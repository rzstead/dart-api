package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Log {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private String requestType;
	private String entityChanged;
	private String description;
	private Date timeChanged;
	
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getEntityChanged() {
		return entityChanged;
	}
	public void setEntityChanged(String entityChanged) {
		this.entityChanged = entityChanged;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getTimeChanged() {
		return timeChanged;
	}
	public void setTimeChanged(Date timeChanged) {
		this.timeChanged = timeChanged;
	}
	
	public void logInteraction(String requestType, String entityChanged, String description) {
		setRequestType(requestType);
		setEntityChanged(entityChanged);
		setDescription(description);
		setTimeChanged(new Date());
	}
}
