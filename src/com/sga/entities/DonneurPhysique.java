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
	private String nom;
	private String prenom;
	@NotBlank(message = "Merci d'entrer le CIN du donneur.")
	@Size(min = 5, max = 8, message = "le CIN n'est pas valide.")
	private String cin;
}
