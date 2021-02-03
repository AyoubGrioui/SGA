package com.sga.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

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
	private String numeroCompteBanque;
}
