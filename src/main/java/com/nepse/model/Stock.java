package com.nepse.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Stock {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sn")
    private Integer sn;

    @Column(name = "companyName")
    private String companyName;

    @Column(name = "numberOfTransaction")
    private Integer numberOfTransaction;

    @Column(name = "maxPrice")
    private Double maxPrice;

    @Column(name = "minPrice")
    private Double minPrice;

    @Column(name = "closingPrice")
    private Double closingPrice;

    @Column(name = "tradedShares")
    private Double tradedShares;

    @Column(name = "totalAmount")
    private Double totalAmount;

    @Column(name = "previousClosingPrice")
    private Double previousClosingPrice;

    @Column(name = "differenceAmount")
    private Double differenceAmount;

    @Column(name = "createdBy")
    private String createdBy;

    @Column(name = "createdOn")
    private LocalDateTime createdOn;

    @Column(name = "updatedBy")
    private String updatedBy;

    @Column(name = "updatedOn")
    private LocalDateTime updatedOn;


    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now();
        createdBy = "app-user-create";
    }

    @PreUpdate
    public void preUpdate() {
        updatedOn = LocalDateTime.now();
        updatedBy = "app-user-update";
    }
}
