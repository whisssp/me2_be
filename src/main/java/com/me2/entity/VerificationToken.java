package com.me2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.me2.global.enums.ActionStatus;
import com.me2.global.enums.VerificationTokenType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "verification_token")
public class VerificationToken extends AbstractAuditEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "verification_token_id_gen")
    @SequenceGenerator(name = "verification_token_id_gen", sequenceName = "verification_token_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private Long userId;

    @Size(max = 20)
    @Column(name = "code", length = 20)
    private String code;

    @Size(max = 20)
    @Column(name = "type", length = 20)
    private VerificationTokenType type;

    @Column(name = "expired_date")
    private Instant expiredDate;

    @Column(name = "resend")
    private Short resend;

    @Column(name = "type_error")
    private Short typeError;

    @Size(max = 50)
    @Column(name = "status", length = 50)
    private ActionStatus status;

}