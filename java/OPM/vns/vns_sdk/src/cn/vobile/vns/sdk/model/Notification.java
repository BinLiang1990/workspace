package cn.vobile.vns.sdk.model;

import java.util.List;

/**
 * The class defines the content of notification
 * 
 * @author wang_lin
 * 
 */
public abstract class Notification {

	protected String type;
	protected String sender;
	protected List<String> recipients;
	protected String subject;
	protected String content;

	public abstract boolean checkStyle();

	public String getType() {
		return type;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject.replaceAll("\"", "\\\\\"");
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content.replaceAll("\"", "\\\\\"");
	}

	public List<String> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
	}

}
