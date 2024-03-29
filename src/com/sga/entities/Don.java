package com.sga.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table( name = "Dons" )
@Inheritance( strategy = InheritanceType.JOINED )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Don implements Serializable {
    private static final long serialVersionUID = 6127062843934892302L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long              idDon;
    private LocalDate         dateDon;
    private Double            montant;

    @ManyToOne
    @JoinColumn( name = "idDonneur" )
    private Donneur           donneur;
}
