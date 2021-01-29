package com.sga.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DonsVersement")
@PrimaryKeyJoinColumn(name = "idDon")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DonVersement extends Don {
	private static final long serialVersionUID = -3438451077900734023L;
	@NotBlank(message = "Merci d'entrer le numero de compte.")
	@Pattern(regexp="^\\d+$",message="le numero de compte doit contenir uniquement des chiffres")
	private String numeroCompteBanque;
}
