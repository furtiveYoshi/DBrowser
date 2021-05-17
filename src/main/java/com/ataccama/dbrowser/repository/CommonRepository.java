package com.ataccama.dbrowser.repository;

import com.ataccama.dbrowser.dto.AttributeDto;
import com.ataccama.dbrowser.dto.ObjectDto;
import com.ataccama.dbrowser.filter.Filter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository
public class CommonRepository{

    private List<ObjectDto> execute(JdbcTemplate jdbcTemplate, String sql, String objectName) {
        return jdbcTemplate
                .query(sql, (rs, rowNum) -> buildObject(rs, objectName));
    }

    private List<ObjectDto> execute(JdbcTemplate jdbcTemplate, String sql, String filterValue, String objectName) {
        return jdbcTemplate
                .query(sql, (rs, rowNum) -> buildObject(rs, objectName), filterValue);
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

    private ObjectDto buildObject(ResultSet rs, String objectName) throws SQLException {
        List<AttributeDto> attributes = new ArrayList<>();
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            attributes.add(new AttributeDto(rs.getMetaData().getColumnName(i), rs.getString(i)));
        }
        return ObjectDto.builder().name(objectName).attributes(attributes).build();
    }
}
