package com.ataccama.dbrowser.service;

import com.ataccama.dbrowser.dto.ObjectDto;
import com.ataccama.dbrowser.entity.Connection;
import com.ataccama.dbrowser.filter.Filter;
import com.ataccama.dbrowser.repository.BrowserDataRepository;
import com.ataccama.dbrowser.repository.ConnectionRepository;
import com.ataccama.dbrowser.util.JdbcUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrowserDataService {

    private final ConnectionRepository connectionRepository;
    private final BrowserDataRepository browserDataRepository;

    public List<ObjectDto> getSchemas(Long connectionId, String schemaName) {
        Connection connection = connectionRepository.findById(connectionId).orElseThrow(() -> new EntityNotFoundException("Connection does not exist."));
        JdbcTemplate jdbcTemplate = JdbcUtils.createJdbcTemplate(connection);
        return browserDataRepository.getList(jdbcTemplate,
                "schema",
                "information_schema.schemata",
                new Filter("schema_name", schemaName));
    }

    public List<ObjectDto> getTables(Long connectionId, String schemaName) {
        Connection connection = connectionRepository.findById(connectionId).orElseThrow(() -> new EntityNotFoundException("Connection does not exist."));
        JdbcTemplate jdbcTemplate = JdbcUtils.createJdbcTemplate(connection);
        return browserDataRepository.getList(jdbcTemplate,
                "table",
                "information_schema.tables",
                new Filter("table_schema", schemaName));
    }

    public List<ObjectDto> getColumns(Long connectionId, String tableName) {
        Connection connection = connectionRepository.findById(connectionId).orElseThrow(() -> new EntityNotFoundException("Connection does not exist."));
        JdbcTemplate jdbcTemplate = JdbcUtils.createJdbcTemplate(connection);
        return browserDataRepository.getList(jdbcTemplate,
                "column",
                "information_schema.columns",
                new Filter("table_name", tableName));
    }

    public List<ObjectDto> getTablePreview(Long connectionId, String tableName) {
        Connection connection = connectionRepository.findById(connectionId).orElseThrow(() -> new EntityNotFoundException("Connection does not exist."));
        JdbcTemplate jdbcTemplate = JdbcUtils.createJdbcTemplate(connection);
        return browserDataRepository.getList(jdbcTemplate, "table_data", tableName, null);
    }
}
