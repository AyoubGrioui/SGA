package com.sga.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "Donneurs")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public abstract class Donneur implements Serializable
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDonneur;
    private String email;
    private String telephone;
    private String adresse;
    private String motDePasse;

    @OneToMany(targetEntity = Don.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "idDonneur")
    private List<Don> donList=new ArrayList<>();

    @OneToOne(targetEntity = Structure.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "idStructure")
    private  Structure structure;

}
