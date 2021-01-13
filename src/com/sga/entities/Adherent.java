package com.sga.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Table(name = "Adherents")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Adherent {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdherent;
    private String nom;
    private String prenom;
    private String cin;
    private LocalDateTime dateNaissance;
    private String lieuNaissance;
    private String dateAdhesion;
    private String profession;
    private String photo;
    private String sexe;
    private String motDePasse;
    private String telephone;
    private String adresse;
    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idLigneFonction")
    private LigneFonction ligneFonction;




}
