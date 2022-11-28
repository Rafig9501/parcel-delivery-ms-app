package com.parcel.customer_service.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "CUSTOMER_", schema = "customer_ms",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "USERNAME_"),
                @UniqueConstraint(columnNames = "PHONE_")})
public class CustomerEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {@org.hibernate.annotations.Parameter(
                    name = "uuid_gen_strategy_class",
                    value = "org.hibernate.id.uuid.CustomVersionOneStrategy")}
    )
    @Column(name = "ID_", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "NAME_")
    private String name;

    @Column(name = "SURNAME_")
    private String surname;

    @Column(name = "USERNAME_")
    private String username;

    @Column(name = "PASSWORD_")
    private String password;

    @Column(name = "ROLE_")
    private String role;

    @Column(name = "CREATION_DATE_")
    private LocalDateTime dateTime;

    @Column(name = "PHONE_")
    private String phone;

    @Column(name = "IS_NON_EXPIRED_")
    private Boolean isNonExpired;

    @Column(name = "IS_ENABLED_")
    private Boolean isEnabled;
}
