package com.ataccama.dbrowser.mapper;

import com.ataccama.dbrowser.dto.ConnectionDto;
import com.ataccama.dbrowser.entity.Connection;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConnectionMapper {
    private static final ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true);
    }

    public static Function<ConnectionDto, Connection> dtoToEntityMapper() {
        return connectionDto -> modelMapper.map(connectionDto, Connection.class);
    }

    public static Function<Connection, ConnectionDto> entityToDtoMapper() {
        return connection -> modelMapper.map(connection, ConnectionDto.class);
    }
}
