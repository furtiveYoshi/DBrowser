package com.ataccama.dbrowser.repository;

import com.ataccama.dbrowser.dto.ObjectDto;
import com.ataccama.dbrowser.util.JdbcUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StatisticRepository {
    public ObjectDto getColumnStatistic(JdbcTemplate jdbcTemplate, String tableName, String columnName){
        return jdbcTemplate.queryForObject(JdbcUtils.buildSqlForColumnStatistic(tableName, columnName),
                (rs, colNum) -> JdbcUtils.buildObject(rs, "column_statistic"));
    }

    public ObjectDto getTableStatistic(JdbcTemplate jdbcTemplate, String schemaName, String tableName){
        return jdbcTemplate.queryForObject(JdbcUtils.buildSqlForTableStatistic(tableName),
                (rs, colNum) -> JdbcUtils.buildObject(rs, "table_statistic"), schemaName, tableName);
    }
}
