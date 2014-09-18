package cn.vobile.vns.sdk.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Message extends Notification {

	public Message() {
		this.type = "message";
	}

	/**
	 * @brief Check the mail style of sender and recipients
	 * @return true or false
	 * @author wang_lin
	 */
	@Override
	public boolean checkStyle() {
		Pattern messagePattern = Pattern.compile("1\\d{10}");
		Matcher messageMatcher = null;
		for (String string : getRecipients()) {
			messageMatcher = messagePattern.matcher(string);
			if (!messageMatcher.matches()) {
				return false;
			}
		}
		return true;
	}

}
