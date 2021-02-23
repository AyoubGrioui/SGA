package com.sga.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table( name = "Adherents" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Adherent implements Serializable {

    private static final long serialVersionUID = 3467409103890331034L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long              idAdherent;
    private String            nom;
    private String            prenom;
    private String            cin;
    private LocalDate         dateNaissance;
    private String            lieuNaissance;
    private LocalDate         dateAdhesion;
    private String            profession;
    private String            sexe;
    private String            motDePasse;
    private String            telephone;
    private String            adresse;
    private String            email;

    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn( name = "idLigneFonction" )
    private LigneFonction     ligneFonction;

    @ManyToOne
    @JoinColumn( name = "idStructure" )
    private Structure         structure;

}
