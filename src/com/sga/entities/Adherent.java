package com.sga.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@Table(name = "Adherents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Adherent implements Serializable {
	private static final long serialVersionUID = 3467409103890331034L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
