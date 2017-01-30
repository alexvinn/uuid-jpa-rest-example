package com.example;

import static javax.persistence.AccessType.FIELD;

import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Access(FIELD)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Table(name = "LATEST_MOUSE", indexes= {
        @Index(columnList="ID", unique=true)
})
public class LatestMouse {

    @Id
    @Getter
    @Column(columnDefinition="BINARY(16) NOT NULL", nullable = false, insertable = true, updatable = false)
    private UUID name;

    @OneToOne(optional=false)
    @Getter
    private Mouse mouse;

}
