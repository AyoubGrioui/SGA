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
	private Long idAdherent;
	private Double montant;
	private LocalDate dateDepense;
	private String typeDepense;

	@ManyToOne
	@JoinColumn(name = "idStructure")
	private Structure structure;

}
