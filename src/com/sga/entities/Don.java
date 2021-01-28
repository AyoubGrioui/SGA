package com.sga.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Table(name = "Dons")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Don implements Serializable {
	private static final long serialVersionUID = 6127062843934892302L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDon;
	@NotBlank(message = "Merci d'entrer la date du don.")
	@PastOrPresent(message = "la date du don est incorrecte.")
	@Pattern(regexp="^(0[1-9]|1[0-9]|2[0-9]|3[0-1])([\\/])(0[1-9]|1[0-2])([\\/])([1-2][0-9][0-9][0-9])$",message="la date n'est pas valide")
	private LocalDate dateDon;
	@NotBlank(message = "Merci d'entrer le montant du don.")
	private Double montant;

	@NotNull(message = "Merci de saisir les information srequises")
	@ManyToOne
	@JoinColumn(name = "idDonneur")
	private Donneur donneur;
}
