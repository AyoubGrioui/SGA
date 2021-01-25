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
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

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
	@NotBlank(message = "Merci d'entrer le nom de l'adherent..")
	@Min(value = 2, message = "le nom doit contenir au moins 2 caracteres.")
	private String nom;
	@NotBlank(message = "Merci d'entrer le prenom de l'adherent.")
	@Min(value = 2, message = "le prenom doit contenir au moins 2 caracteres.")
	private String prenom;
	@NotBlank(message = "Merci d'entrer le CIN de l'adherent.")
	@Size(min = 5, max = 8, message = "le CIN doit contenir 8 caracteres.")
	private String cin;
	@NotBlank(message = "Merci d'entrer la date de naissance de l'adherent.")
	@Past(message = "la date de naissance est incorrecte.")
	private LocalDate dateNaissance;
	@NotBlank(message = "Merci d'entrer le lieu de naissance de l'adherent..")
	private String lieuNaissance;
	@NotBlank(message = "Merci d'entrer la date d'adhesion de l'adherent..")
	@PastOrPresent(message = "la date d'adhesion est incorrecte.")
	private LocalDate dateAdhesion;
	@NotBlank(message = "Merci d'entrer la profession de l'adherent.")
	private String profession;
	@NotBlank(message = "Merci d'entrer la photo de l'adherent.")
	private String photo;
	@NotBlank(message = "Merci d'entrer le sexe de l'adherent.")
	private String sexe;
	@NotNull(message = "Merci d'entrer le mot de passe de l'adherent.")
	private String motDePasse;
	@NotBlank(message = "Merci d'entrer le numero de telephone de l'adherent.")
	@Size(min = 10, max = 10, message = "le numero de telephone doit contenir 10 chiffres.")
	private String telephone;
	@NotBlank(message = "Merci d'entrer l'adresse de l'adherent.")
	private String adresse;
	@NotBlank(message = "Merci d'entrer l'email de l'adherent")
	@Email(message = "Veuillez saisir une adresse email valide")
	private String email;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idLigneFonction")
	private LigneFonction ligneFonction;
	@ManyToOne
	@JoinColumn(name = "idStructure")
	private Structure structure;

}
