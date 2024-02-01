package com.meesho.notificationProducer.models;

import com.meesho.notificationProducer.constants.Constants;
import jakarta.persistence.*;

@Entity
@Table(name = Constants.BLACKLIST_DB)
public class BlacklistDatabase {
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
}
