package com.ataccama.dbrowser.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionDto {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String hostName;
    @NotNull
    private Integer port;
    @NotNull
    private String databaseName;
    @NotNull
    private String username;
    @NotNull
    private String password;
}
