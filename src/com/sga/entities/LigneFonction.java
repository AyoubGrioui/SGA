package com.sga.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "LigneFonctions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LigneFonction implements Serializable {
	private static final long serialVersionUID = -2509167196362627083L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLigneFonction;
	@NotEmpty(message = "Merci d'entrer la date de debut.")
	@PastOrPresent(message = "la date de debut est incorrecte.")
	@Pattern(regexp="^(0[1-9]|1[0-9]|2[0-9]|3[0-1])([\\/])(0[1-9]|1[0-2])([\\/])([1-2][0-9][0-9][0-9])",message="la date n'est pas valide")
	private LocalDate dateDebut;
	@NotEmpty(message = "Merci d'entrer la date de fin.")
	@Pattern(regexp="^(0[1-9]|1[0-9]|2[0-9]|3[0-1])([\\/])(0[1-9]|1[0-2])([\\/])([1-2][0-9][0-9][0-9])",message="la date n'est pas valide")
	private LocalDate dateFin;
}
