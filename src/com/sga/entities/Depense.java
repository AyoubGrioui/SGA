package com.sga.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity @Table(name = "Depenses")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Depense implements Serializable
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDepense;
    private Double montant;
    private LocalDateTime dateDepense;
    private String typeDepense;

}
