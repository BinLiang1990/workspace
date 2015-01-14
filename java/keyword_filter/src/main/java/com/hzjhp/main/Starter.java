package com.hzjhp.main;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hzjhp.filter.KeywordFilter;
import com.hzjhp.util.Command;

public class Starter {
	private static Logger logger = Logger.getLogger(Starter.class);

	public static void main(String[] args) throws IOException {

		long initPostId = 0;
		long initReplyId = 0;
		long initGradeId = 0;
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		KeywordFilter filter = context.getBean(KeywordFilter.class);
		Command command = context.getBean(Command.class);
		while (true) {
			try {
				filter.filterPost(initPostId);
				filter.filterReply(initReplyId);
				filter.filterGrade(initGradeId);
				initPostId = filter.getPostMaxId();
				initReplyId = filter.getReplyMaxId();
				initGradeId = filter.getGradeMaxId();
				Thread.sleep(command.getInterval() * 60 * 1000);
			} catch (InterruptedException e) {
				logger.error(e.getMessage());
			}
		}

	}
}
