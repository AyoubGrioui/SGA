package com.sga.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity @Table(name = "Dons")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public abstract class Don implements Serializable
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDon;
    private LocalDateTime dateDon;
    private Double montant;
}
