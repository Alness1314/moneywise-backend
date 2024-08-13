package com.alness.moneywise.users.entity;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "user_detail")
public class DetailEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false, columnDefinition = "character varying(192)")
    private String names;

    @Column(nullable = false, columnDefinition = "character varying(64)")
    private String lastname;

    @Column(columnDefinition = "character varying(64)")
    private String maternalSurname;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
