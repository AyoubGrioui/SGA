package com.sga.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DonneursMorals")
@PrimaryKeyJoinColumn(name = "idDonneur")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DonneurMoral extends Donneur {
	private static final long serialVersionUID = 8626659034469556559L;
	@NotBlank(message = "Merci d'entrer le nom du donneur.")
	private String nom;
}
