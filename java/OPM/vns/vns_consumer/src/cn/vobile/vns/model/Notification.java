package cn.vobile.vns.model;

import java.io.Serializable;
import java.util.List;

/**
 * A notification model specifies notification format
 * 
 * @author wang_lin
 * 
 */
@SuppressWarnings("serial")
public class Notification implements Serializable {

	private String type;
	private String sender;

	private List<String> recipients;
	private String subject;
	private String content;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public List<String> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
