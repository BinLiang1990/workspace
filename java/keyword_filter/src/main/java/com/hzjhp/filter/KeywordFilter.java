package com.hzjhp.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.hzjhp.dao.KeywordDAO;

@Component
public class KeywordFilter {

	@Autowired
	private KeywordDAO keywordDAO;
	private static Logger logger = Logger.getLogger(KeywordFilter.class);
	private Map<String, Object> keywordMap;

	public Set<String> getKeywordSet() {
		Set<String> keywordSet = new HashSet<String>();
		Resource resource = new ClassPathResource("keyword.txt");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					resource.getInputStream()));
			String keyword = reader.readLine();
			while (keyword != null) {
				keywordSet.add(keyword.trim());
				keyword = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return keywordSet;
	}

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		Set<String> keywordSet = getKeywordSet();
		keywordMap = new HashMap<String, Object>(keywordSet.size());
		Iterator<String> iterator = keywordSet.iterator();
		while (iterator.hasNext()) {
			String keyword = iterator.next();
			Map<String, Object> currentMap = keywordMap;
			for (int i = 0; i < keyword.length(); ++i) {
				char word = keyword.charAt(i);
				Map<String, Object> wordMap = (Map<String, Object>) currentMap
						.get(String.valueOf(word));
				if (wordMap != null) {
					currentMap = wordMap;
				} else {
					Map<String, Object> newMap = new HashMap<String, Object>();
					newMap.put("isEnd", false);
					currentMap.put(String.valueOf(word), newMap);
					currentMap = newMap;
				}
				if (i == keyword.length() - 1) {
					currentMap.put("isEnd", true);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public boolean checkKeyword(String content) {
		Map<String, Object> currentMap = keywordMap;
		for (int i = 0; i < content.length(); ++i) {
			char word = content.charAt(i);
			currentMap = (Map<String, Object>) currentMap.get(String
					.valueOf(word));
			if (currentMap != null) {
				if ((boolean) currentMap.get("isEnd")) {
					return true;
				}
			} else {
				currentMap = (Map<String, Object>) keywordMap.get(String
						.valueOf(word));
				currentMap = currentMap == null ? keywordMap : currentMap;
			}
		}
		return false;
	}

	public void filterPost(long id) {
		List<Long> ids = new ArrayList<Long>();
		List<Map<String, Object>> list = keywordDAO.findPostKeyword(id);
		Iterator<Map<String, Object>> iterator = list.iterator();
		while (iterator.hasNext()) {
			Map<String, Object> map = iterator.next();
			long postId = (Long) map.get("id");
			String postComment = (String) map.get("comment");
			if (checkKeyword(postComment)) {
				ids.add(postId);
				logger.info("Post " + postId + ":" + postComment);
			}
		}
		keywordDAO.shieldPost(ids);
		System.out.println();
	}

	public void filterReply(long id) {
		List<Long> ids = new ArrayList<Long>();
		List<Map<String, Object>> list = keywordDAO.findReplyKeyword(id);
		Iterator<Map<String, Object>> iterator = list.iterator();
		while (iterator.hasNext()) {
			Map<String, Object> map = iterator.next();
			long replyId = (Long) map.get("id");
			String replyContent = (String) map.get("content");
			if (checkKeyword(replyContent)) {
				ids.add(replyId);
				logger.info("Reply " + replyId + ":" + replyContent);
			}
		}
		keywordDAO.shieldReply(ids);
		System.out.println();
	}

	public void filterGrade(long id) {
		List<Long> ids = new ArrayList<Long>();
		List<Map<String, Object>> list = keywordDAO.findGradeKeyword(id);
		Iterator<Map<String, Object>> iterator = list.iterator();
		while (iterator.hasNext()) {
			Map<String, Object> map = iterator.next();
			long gradeId = (Long) map.get("id");
			String gradeComment = (String) map.get("comment");
			if (checkKeyword(gradeComment)) {
				ids.add(gradeId);
				logger.info("Grade " + gradeId + ":" + gradeComment);
			}
		}
		keywordDAO.shieldGrade(ids);
		System.out.println();
	}

	public long getPostMaxId() {
		return keywordDAO.findPostMaxId();
	}

	public long getReplyMaxId() {
		return keywordDAO.findReplyMaxId();
	}

	public long getGradeMaxId() {
		return keywordDAO.findGradeMaxId();
	}
}
