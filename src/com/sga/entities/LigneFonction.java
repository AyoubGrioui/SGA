package com.sga.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity @Table(name = "LigneFonctions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class LigneFonction implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLigneFonction;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
}
