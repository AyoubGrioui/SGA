package com.sga.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DonsCheque")
@PrimaryKeyJoinColumn(name = "idDon")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DonCheque extends Don {
	private static final long serialVersionUID = 4912258162784610425L;
	@NotBlank(message = "Merci d'entrer le numero de compte du donneur .")
	private String numeroCompteBanque;
	@NotBlank(message = "Merci d'entrer la date du cheque.")
	@PastOrPresent(message = "la date du cheque est incorrecte.")
	@Pattern(regexp="^(0[1-9]|1[0-9]|2[0-9]|3[0-1])([\\/])(0[1-9]|1[0-2])([\\/])([1-2][0-9][0-9][0-9])",message="la date n'est pas valide")
	private LocalDate dateCheque;
	@NotBlank(message = "Merci d'entrer la date de depot.")
	@Future(message = "La date de depot est incorrecte.")
	@Pattern(regexp="^(0[1-9]|1[0-9]|2[0-9]|3[0-1])([\\/])(0[1-9]|1[0-2])([\\/])([1-2][0-9][0-9][0-9])",message="la date n'est pas valide")
	private LocalDate dateDepot;
	@NotBlank(message = "Merci d'entrer le nom du banque.")
	private String nomBanque;
}
