package com.soulrebel.ml.entity;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;


import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Dna", schema = "public")
public class Dna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "DNA")
    private String dna;

    @Column(name = "ISMUTANT")
    @Type(type = "true_false")
    @NotNull
    private Boolean isMutant;


}
