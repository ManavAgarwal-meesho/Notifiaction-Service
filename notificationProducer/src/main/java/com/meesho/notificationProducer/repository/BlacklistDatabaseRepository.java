package com.meesho.notificationProducer.repository;

import com.meesho.notificationProducer.models.BlacklistDatabase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistDatabaseRepository extends JpaRepository<BlacklistDatabase, String> {
}