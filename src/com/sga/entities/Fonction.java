package com.sga.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table( name = "Fonctions" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fonction implements Serializable {
    private static final long serialVersionUID = 5539331936719097418L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long              idFonction;
    private String            role;
}
