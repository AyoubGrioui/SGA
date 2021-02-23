package com.sga.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table( name = "Donneurs" )
@Inheritance( strategy = InheritanceType.JOINED )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Donneur implements Serializable {
    private static final long serialVersionUID = 3897142428147558951L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long              idDonneur;
    private String            email;
    private String            telephone;
    private String            adresse;
    private String            motDePasse;

    @OneToMany( targetEntity = Don.class, mappedBy = "donneur", cascade = CascadeType.ALL )
    // @JoinColumn(name = "idDonneur")
    private List<Don>         donList          = new ArrayList<>();

    @ManyToOne( targetEntity = Structure.class )
    @JoinColumn( name = "idStructure" )
    private Structure         structure;

}
