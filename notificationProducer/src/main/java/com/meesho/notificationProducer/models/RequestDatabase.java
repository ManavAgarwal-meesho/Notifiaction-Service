package com.meesho.notificationProducer.models;

import com.meesho.notificationProducer.constants.Constants;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = Constants.SQL_TABLE)
public class RequestDatabase {

    @Id
    @Column(name = "request_id", unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String requestId;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "status")
    private String status;

    @Column(name = "status_comment")
    private String statusComment;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

}

