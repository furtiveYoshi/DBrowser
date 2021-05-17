package com.ataccama.dbrowser.service;

import com.ataccama.dbrowser.dto.ObjectDto;
import com.ataccama.dbrowser.entity.Connection;
import com.ataccama.dbrowser.filter.Filter;
import com.ataccama.dbrowser.repository.CommonRepository;
import com.ataccama.dbrowser.repository.ConnectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrowserDataService {

    private final ConnectionRepository connectionRepository;
    private final CommonRepository commonRepository;

    public List<ObjectDto> getSchemas(Long connectionId, String schemaName) {
        Connection connection = connectionRepository.getOne(connectionId);
        JdbcTemplate jdbcTemplate = createJdbcTemplate(connection);
        List<ObjectDto> result = commonRepository.getList(jdbcTemplate,
                "schema",
                "information_schema.schemata",
                new Filter("schema_name", schemaName));
        return result;
    }

    public List<ObjectDto> getTables(Long connectionId, String schemaName) {
        Connection connection = connectionRepository.getOne(connectionId);
        JdbcTemplate jdbcTemplate = createJdbcTemplate(connection);
        List<ObjectDto> result = commonRepository.getList(jdbcTemplate,
                "table",
                "information_schema.tables",
                new Filter("table_schema", schemaName));
        return result;
    }

    public List<ObjectDto> getColumns(Long connectionId, String tableName) {
        Connection connection = connectionRepository.getOne(connectionId);
        JdbcTemplate jdbcTemplate = createJdbcTemplate(connection);
        List<ObjectDto> result = commonRepository.getList(jdbcTemplate,
                "column",
                "information_schema.columns",
                new Filter("table_name", tableName));
        return result;
    }

    public List<ObjectDto> getTablePreview(Long connectionId, String tableName) {
        Connection connection = connectionRepository.getOne(connectionId);
        JdbcTemplate jdbcTemplate = createJdbcTemplate(connection);
        List<ObjectDto> result = commonRepository.getList(jdbcTemplate, "table_data", tableName, null);
        return result;
    }

    private JdbcTemplate createJdbcTemplate(Connection connection) {
        final String driverClassName = "com.mysql.jdbc.Driver";
        final String jdbcUrl = "jdbc:mysql://" + connection.getHostName() + ":" + connection.getPort() + "/" + connection.getDatabaseName();
        final String username = connection.getUsername();
        final String password = connection.getPassword();
        final DataSource dataSource = DataSourceBuilder.create().driverClassName(driverClassName).url(jdbcUrl).username(username).password(password).build();
        return new JdbcTemplate(dataSource);
    }
}
