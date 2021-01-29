package com.sga.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Depenses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Depense implements Serializable {
	private static final long serialVersionUID = -4930903130532553319L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDepense;
	@NotBlank(message = "Merci d'entrer l'identifiant de l'adherent.")
	private Long idAdherent;
	@NotBlank(message = "Merci d'entrer le montant de la depense.")
	private Double montant;
	@NotBlank(message = "Merci d'entrer la date de depense.")
	@PastOrPresent(message = "la date de depense est incorrecte.")
	@Pattern(regexp="^(0[1-9]|1[0-9]|2[0-9]|3[0-1])([\\/])(0[1-9]|1[0-2])([\\/])([1-2][0-9][0-9][0-9])",message="la date n'est pas valide")
	private LocalDate dateDepense;
	@NotNull(message = "Merci d'entrer le type de depense.")
	private String typeDepense;
	@ManyToOne
	@JoinColumn(name = "idStructure")
	private Structure structure;

}
