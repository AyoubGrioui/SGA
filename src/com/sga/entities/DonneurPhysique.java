package com.sga.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table( name = "DonneursPhysiques" )
@PrimaryKeyJoinColumn( name = "idDonneur" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DonneurPhysique extends Donneur {
    private static final long serialVersionUID = -1824530304311273895L;
    private String            nom;
    private String            prenom;
    private String            cin;
}
