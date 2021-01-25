package com.sga.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
   // @NotBlank(message = "Merci d'entrer l'ID du donneur.")
    private Long idDonneur;
    
    @NotBlank(message = "Merci d'entrer l'email du donneur")
    @Email(message = "Veuillez saisir une adresse email valide")
    private String email;
    
    @NotBlank(message = "Merci d'entrer le numero de telephone du donneur.")
    @Size(min = 10, max = 10 , message="le numero de telephone doit contenir 10 chiffres.")
    // verifier que le numero de telephone est valide "ecrire une fonction dans notre package 'services' et l'implementer"
    private String telephone;
    
    @NotBlank(message = "Merci d'entrer l'adresse du donneur.")
    private String adresse;
    
  //  @NotBlank(message = "Merci d'entrer le mot de passe du donneur")
    // verifier si le systeme genere le mot de passe ou bien c'est saisi par le secreteur
    private String motDePasse;

    @OneToMany(targetEntity = Don.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "idDonneur")
    private List<Don> donList=new ArrayList<>();
}
