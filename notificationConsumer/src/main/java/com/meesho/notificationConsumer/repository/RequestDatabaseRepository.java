package com.meesho.notificationConsumer.repository;

import com.meesho.notificationConsumer.models.RequestDatabase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestDatabaseRepository extends JpaRepository<RequestDatabase, String> {
}
