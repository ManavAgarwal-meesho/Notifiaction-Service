package com.meesho.notificationConsumer.repository;

import com.meesho.notificationConsumer.models.RequestDatabase;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestDatabaseRepository extends JpaRepository<RequestDatabase, String> {

    @Modifying
    @Transactional
    @Query("UPDATE RequestDatabase r SET r.status = :status, r.statusComment = :statusComment WHERE r.requestId = :requestId")
    void updateRequest(@Param("requestId") String request_id,
                       @Param("statusComment") String status_comment,
                       @Param("status") String request_status );

}
