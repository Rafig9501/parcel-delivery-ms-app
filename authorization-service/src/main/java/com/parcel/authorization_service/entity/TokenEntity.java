package com.parcel.authorization_service.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "TOKEN_", schema = "user_ms")
public class TokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_")
    private Long id;

    @Column(name = "TOKEN_")
    private String token;

    @Column(name = "EXPIRY_DATE_")
    private Instant expiryDate;

    @Column(name = "USER_ID_")
    private UUID userId;
}
