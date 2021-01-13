package com.sga.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity @Table(name = "DonneursMorals")
@PrimaryKeyJoinColumn(name = "idDonneur")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DonneurMorale extends Donneur
{
    private String nom;
}
