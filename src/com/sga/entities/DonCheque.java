package com.sga.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

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
	private String numeroCompteBanque;
	private LocalDate dateCheque;
	private LocalDate dateDepot;
	private String nomBanque;
}
