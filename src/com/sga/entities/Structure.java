package com.sga.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "Structures")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Structure implements Serializable
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStructure;
    private String nom;
    private LocalDateTime dateCreation;
    private String email;
    private String adresse;
    private String siteWeb;
    private String objectif;

    @OneToMany(targetEntity = Adherent.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "idStructure")
    private List<Adherent> adherentList=new ArrayList<>();

    @OneToMany(targetEntity = Donneur.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "idStructure")
    private List<Donneur> donneurList=new ArrayList<>();

    @OneToMany(targetEntity = Depense.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "idStructure")
    private List<Depense> depenseList=new ArrayList<>();
}
