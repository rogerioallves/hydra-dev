package com.hydra.dev.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    private Long id;

    @Column(length = 50)
    private String name;
}
