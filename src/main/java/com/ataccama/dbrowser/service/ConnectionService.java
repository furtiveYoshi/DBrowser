package com.ataccama.dbrowser.service;

import com.ataccama.dbrowser.repository.ConnectionRepository;
import com.ataccama.dbrowser.dto.ConnectionDto;
import com.ataccama.dbrowser.entity.Connection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import static com.ataccama.dbrowser.mapper.ConnectionMapper.*;

@Service
@RequiredArgsConstructor
public class ConnectionService {
    private final ConnectionRepository connectionRepository;

    public ConnectionDto create(ConnectionDto connectionDto){
        Connection connection = dtoToEntityMapper().apply(connectionDto);
        connection = connectionRepository.save(connection);
        return entityToDtoMapper().apply(connection);
    }

    public ConnectionDto update(ConnectionDto connectionDto){
        if (connectionDto.getId() == null){
            throw new IllegalArgumentException("Please specify id of connection to update it");
        }
        Connection connection = dtoToEntityMapper().apply(connectionDto);
        connection = connectionRepository.save(connection);
        return entityToDtoMapper().apply(connection);
    }

    public void delete(long id){
        connectionRepository.deleteById(id);
    }

    public ConnectionDto getConnectionById(long id){
        Connection connection = connectionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Connection does not exist."));
        return entityToDtoMapper().apply(connection);
    }
}
