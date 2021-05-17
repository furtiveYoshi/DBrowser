package com.ataccama.dbrowser.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Connection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column(name="host_name")
    private String hostName;
    @Column
    private int port;
    @Column(name = "database_name")
    private String databaseName;
    @Column
    private String username;
    @Column
    private String password;
    @Version
    private Long version;
}
