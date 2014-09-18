package cn.vobile.vns.sdk.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mail extends Notification {

	public Mail() {
		this.type = "mail";
	}

	/**
	 * @brief Check the mail style of sender and recipients
	 * @return true or false
	 * @author wang_lin
	 */
	@Override
	public boolean checkStyle() {
		Pattern mailPattern = Pattern.compile("[\\w.]+@[a-z0-9]+\\.[a-z]{2,4}");
		Matcher mailMatcher = mailPattern.matcher(getSender());
		if (!mailMatcher.matches()) {
			return false;
		}
		for (String string : getRecipients()) {
			mailMatcher = mailPattern.matcher(string);
			if (!mailMatcher.matches()) {
				return false;
			}
		}
		return true;
	}

}
