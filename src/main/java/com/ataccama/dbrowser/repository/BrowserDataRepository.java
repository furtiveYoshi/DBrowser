package com.ataccama.dbrowser.repository;

import com.ataccama.dbrowser.dto.ObjectDto;
import com.ataccama.dbrowser.filter.Filter;
import com.ataccama.dbrowser.util.JdbcUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@org.springframework.stereotype.Repository
public class BrowserDataRepository {

    private List<ObjectDto> execute(JdbcTemplate jdbcTemplate, String sql, String objectName) {
        return jdbcTemplate
                .query(sql, (rs, rowNum) -> JdbcUtils.buildObject(rs, objectName));
    }

    private List<ObjectDto> execute(JdbcTemplate jdbcTemplate, String sql, String filterValue, String objectName) {
        return jdbcTemplate
                .query(sql, (rs, rowNum) -> JdbcUtils.buildObject(rs, objectName), filterValue);
    }

    public List<ObjectDto> getList(JdbcTemplate jdbcTemplate, String objectName, String tableName, Filter filter) {
        String sql = buildSqlRequest(tableName, filter);
        return filter!=null ? execute(jdbcTemplate, sql, filter.getFieldValue(), objectName) : execute(jdbcTemplate, sql, objectName);
    }

    private String buildSqlRequest(String tableName, Filter filter) {
        StringBuilder sb = new StringBuilder("SELECT * FROM ").append(tableName);
        if (filter != null) {
            sb.append(" WHERE ").append(filter.getFieldName()).append(" = ?");
        }
        return sb.toString();
    }
}
