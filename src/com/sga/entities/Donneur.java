package com.sga.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Donneurs")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Donneur implements Serializable {
    private static final long serialVersionUID = -6098183657336289215L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDonneur;
    private String email;
    private String telephone;
    private String adresse;
    private String motDePasse;
    @OneToMany(targetEntity = Don.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "idDonneur")
    private List<Don> donList = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "idStructure")
    private Structure structure;
}
