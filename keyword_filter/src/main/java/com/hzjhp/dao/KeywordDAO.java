package com.hzjhp.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class KeywordDAO {

	private static Logger logger = Logger.getLogger(KeywordDAO.class);
	private static final String FINDPOSTKEYWORD = "select id, comment from post where id > ? and comment != \"\"";
	private static final String SHIELDPOST = "update post set shielded = 1 where id = ?";
	private static final String FINDREPLYKEYWORD = "select id, content from reply where id > ? and content != \"\"";
	private static final String SHIELDREPLY = "update reply set shielded = 1 where id = ?";
	private static final String FINDGradeKEYWORD = "select id, comment from grade where id > ? and comment != \"\"";
	private static final String SHIELDGRADE = "update grade set shielded = 1 where id = ?";
	private static final String FINDPOSTMAXID = "select max(id) from post";
	private static final String FINDREPLYMAXID = "select max(id) from reply";
	private static final String FINDGRADEMAXID = "select max(id) from grade";
	@Resource
	private JdbcTemplate jdbcTemplate;

	public List<Map<String, Object>> findPostKeyword(long id) {
		return jdbcTemplate.queryForList(FINDPOSTKEYWORD, new Object[] { id });
	}

	public void shieldPost(final List<Long> ids) {
		jdbcTemplate.batchUpdate(SHIELDPOST,
				new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement arg0, int arg1)
							throws SQLException {
						arg0.setLong(1, ids.get(arg1));
					}

					@Override
					public int getBatchSize() {
						return ids.size();
					}
				});
	}

	public List<Map<String, Object>> findReplyKeyword(long id) {
		return jdbcTemplate.queryForList(FINDREPLYKEYWORD, new Object[] { id });
	}

	public void shieldReply(final List<Long> ids) {
		jdbcTemplate.batchUpdate(SHIELDREPLY,
				new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement arg0, int arg1)
							throws SQLException {
						arg0.setLong(1, ids.get(arg1));
					}

					@Override
					public int getBatchSize() {
						return ids.size();
					}
				});

	}

	public List<Map<String, Object>> findGradeKeyword(long id) {
		return jdbcTemplate.queryForList(FINDGradeKEYWORD, new Object[] { id });
	}

	public void shieldGrade(final List<Long> ids) {
		jdbcTemplate.batchUpdate(SHIELDGRADE,
				new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement arg0, int arg1)
							throws SQLException {
						arg0.setLong(1, ids.get(arg1));
					}

					@Override
					public int getBatchSize() {
						return ids.size();
					}
				});

	}

	public long findPostMaxId() {
		return jdbcTemplate.queryForObject(FINDPOSTMAXID, Long.class);
	}

	public long findReplyMaxId() {
		return jdbcTemplate.queryForObject(FINDREPLYMAXID, Long.class);
	}

	public long findGradeMaxId() {
		return jdbcTemplate.queryForObject(FINDGRADEMAXID, Long.class);
	}
}