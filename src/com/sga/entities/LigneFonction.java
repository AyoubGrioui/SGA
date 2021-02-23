package com.sga.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table( name = "LigneFonctions" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LigneFonction implements Serializable {
    private static final long serialVersionUID = -2509167196362627083L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long              idLigneFonction;
    private LocalDate         dateDebut;
    private LocalDate         dateFin;
    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn( name = "idFonction" )
    private Fonction          fonction;
}
