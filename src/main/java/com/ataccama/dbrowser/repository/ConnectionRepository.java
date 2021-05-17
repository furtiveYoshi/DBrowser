package com.ataccama.dbrowser.repository;

import com.ataccama.dbrowser.entity.Connection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectionRepository extends JpaRepository<Connection, Long> {
}
