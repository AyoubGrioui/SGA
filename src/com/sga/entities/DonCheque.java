package com.sga.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity @Table(name = "DonsCheque")
@PrimaryKeyJoinColumn(name = "idDon")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DonCheque extends Don
{
    private String numeroCompteBanque;
    private LocalDateTime dateCheque;
    private LocalDateTime dateDepot;
    private String nomBanque;
}
