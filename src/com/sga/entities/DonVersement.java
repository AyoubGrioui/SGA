package com.sga.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity @Table(name = "DonsVersement")
@PrimaryKeyJoinColumn(name = "idDon")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DonVersement extends Don
{
    private String numeroCompteBanque;
}
