package com.sga.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "Donneurs")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public abstract class Donneur implements Serializable
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDonneur;
    
    @NotBlank(message = "Merci d'entrer l'email du donneur")
    @Email(message = "Veuillez saisir une adresse email valide")
    private String email;
    
    @NotBlank(message = "Merci d'entrer le numero de telephone du donneur.")
    @Pattern(regexp="^\\d+$",message="le numero de telephone doit contenir uniquement des chiffres")
    @Size(min = 10, max = 10 , message="le numero de telephone doit contenir 10 chiffres.")
    private String telephone;
    
    @NotBlank(message = "Merci d'entrer l'adresse du donneur.")
    private String adresse;
    
    @NotBlank(message = "Merci d'entrer le mot de passe du donneur")
    private String motDePasse;

    @OneToMany(targetEntity = Don.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "idDonneur")
    private List<Don> donList=new ArrayList<>();
}
