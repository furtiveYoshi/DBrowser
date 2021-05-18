package com.ataccama.dbrowser.util;

import com.ataccama.dbrowser.dto.AttributeDto;
import com.ataccama.dbrowser.dto.ObjectDto;
import com.ataccama.dbrowser.entity.Connection;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcUtils {
    private static final String SQL_COLUMN_STATISTIC = "SELECT min(%s) as 'minValue', max(%s) as 'maxValue', avg(%s) as 'avgValue', " +
            " (SELECT  tt.%s " +
            " FROM ( " +
            " SELECT t.%s, @rownum:=@rownum+1 as `row_number`, @total_rows:=@rownum " +
            "   FROM  %s t, (SELECT @rownum:=0) r " +
            "   WHERE t.%s is NOT NULL " +
            "   ORDER BY t.%s " +
            " ) as tt " +
            " WHERE tt.row_number = FLOOR((@total_rows+1)/2)) as 'medianValue' from %s";

    private static final String SQL_TABLE_STATISTIC = "SELECT count(*) as recordsCount, " +
            " (select count(*) " +
            " FROM information_schema.columns " +
            " WHERE table_schema = ? AND table_name = ?) AS attributesCount FROM %s ";

    public static JdbcTemplate createJdbcTemplate(Connection connection) {
        final String driverClassName = "com.mysql.jdbc.Driver";
        final String jdbcUrl = "jdbc:mysql://" + connection.getHostName() + ":" + connection.getPort() + "/" + connection.getDatabaseName();
        final String username = connection.getUsername();
        final String password = connection.getPassword();
        final DataSource dataSource = DataSourceBuilder.create().driverClassName(driverClassName).url(jdbcUrl).username(username).password(password).build();
        return new JdbcTemplate(dataSource);
    }

    public static ObjectDto buildObject(ResultSet rs, String objectName) throws SQLException {
        List<AttributeDto> attributes = new ArrayList<>();
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            attributes.add(new AttributeDto(rs.getMetaData().getColumnName(i), rs.getString(i)));
        }
        return ObjectDto.builder().name(objectName).attributes(attributes).build();
    }

    public static String buildSqlForColumnStatistic(String table, String column){
        return String.format(SQL_COLUMN_STATISTIC, column, column, column, column, column, table, column, column, table);
    }

    public static String buildSqlForTableStatistic(String table){
        return String.format(SQL_TABLE_STATISTIC, table);
    }
}
