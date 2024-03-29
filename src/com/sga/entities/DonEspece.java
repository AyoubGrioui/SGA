package com.sga.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table( name = "DonsEspece" )
@PrimaryKeyJoinColumn( name = "idDon" )
@Getter
@Setter
@NoArgsConstructor
public class DonEspece extends Don {
    private static final long serialVersionUID = 2831079144558236402L;
}
