package com.sga.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DonneursPhysiques")
@PrimaryKeyJoinColumn(name = "idDonneur")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DonneurPhysique extends Donneur {
	private static final long serialVersionUID = -1824530304311273895L;
	@NotBlank(message = "Merci d'entrer le nom du donneur.")
	@Min(value = 2, message = "le nom doit contenir au moins 2 caracteres.")
	private String nom;
	@NotBlank(message = "Merci d'entrer le nom du donneur.")
	@Min(value = 2, message = "le prenom doit contenir au moins 2 caracteres.")
	private String prenom;
	@NotBlank(message = "Merci d'entrer le CIN du donneur.")
	@Size(min = 8, max = 8, message = "le CIN doit contenir 8 caracteres.")
	private String cin;
}
