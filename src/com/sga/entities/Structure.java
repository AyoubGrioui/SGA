package com.sga.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Structures")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Structure implements Serializable {
    private static final long serialVersionUID = -2605779498178246039L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStructure;
    @Setter
    @NotBlank(message = "Merci d'entrer le nom de la structure.")
    @Min(value = 2, message="le nom doit contenir au moins 2 caracteres.")
    private String nom;
    @PastOrPresent(message= "la date de creation est incorrecte.")
    private LocalDate dateCreation;
    @NotBlank(message = "Merci d'entrer l'email.")
    @Email(message = "Veuillez saisir un email valide.")
    private String email;
    @NotBlank(message = "Merci de donner l'adresse.")
    private String adresse;
    @URL(message = "Merci de donner un url valide.")
    private String siteWeb;
    @NotBlank(message = "Merci d'entrer l'objectif")
    private String objectif;

    @OneToMany(targetEntity = Adherent.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "idStructure")
    private List<Adherent> adherentList = new ArrayList<>();

    @OneToMany(targetEntity = Donneur.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "idStructure")
    private List<Donneur> donneurList = new ArrayList<>();

    @OneToMany(targetEntity = Depense.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "idStructure")
    private List<Depense> depenseList = new ArrayList<>();
}
