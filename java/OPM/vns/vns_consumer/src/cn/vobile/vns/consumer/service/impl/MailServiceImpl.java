package cn.vobile.vns.consumer.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import cn.vobile.vns.consumer.service.NotificationService;
import cn.vobile.vns.model.Notification;

/**
 * A service can send mail
 * 
 * @author wang_lin
 * 
 */
@Service
public class MailServiceImpl implements NotificationService {

	@Resource
	private JavaMailSender sender;
	private static Logger logger = Logger.getLogger(MailServiceImpl.class);

	@Override
	public void sendNotification(Notification noti) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(noti.getSender());
		msg.setTo(noti.getRecipients().toArray(
				new String[noti.getRecipients().size()]));
		msg.setSubject(noti.getSubject());
		msg.setText(noti.getContent());
		try {
			sender.send(msg);
		} catch (MailException e) {
			logger.error(e.getMessage());
		}

	}
}
