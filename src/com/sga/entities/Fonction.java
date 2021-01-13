package com.sga.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity @Table(name = "Fonctions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Fonction implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFonction;
    private String role;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idLigneFonction")
    private LigneFonction ligneFonction;
}
