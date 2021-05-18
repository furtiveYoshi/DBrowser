package com.ataccama.dbrowser.service;

import com.ataccama.dbrowser.dto.ObjectDto;
import com.ataccama.dbrowser.entity.Connection;
import com.ataccama.dbrowser.repository.ConnectionRepository;
import com.ataccama.dbrowser.repository.StatisticRepository;
import com.ataccama.dbrowser.util.JdbcUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class StatisticService {

    private final ConnectionRepository connectionRepository;
    private final StatisticRepository statisticRepository;

    public ObjectDto getColumnStatistic(Long connectionId, String tableName, String columnName){
        Connection connection = connectionRepository.findById(connectionId).orElseThrow(() -> new EntityNotFoundException("Connection does not exist."));
        JdbcTemplate jdbcTemplate = JdbcUtils.createJdbcTemplate(connection);

        return statisticRepository.getColumnStatistic(jdbcTemplate, tableName, columnName);
    }

    public ObjectDto getTableStatistic(Long connectionId, String schemaName, String tableName){
        Connection connection = connectionRepository.findById(connectionId).orElseThrow(() -> new EntityNotFoundException("Connection does not exist."));
        JdbcTemplate jdbcTemplate = JdbcUtils.createJdbcTemplate(connection);

       return statisticRepository.getTableStatistic(jdbcTemplate, schemaName, tableName);
    }
}
