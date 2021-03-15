package org.rimmar.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table ( name = "hobby")
@Getter
@Setter
@NoArgsConstructor
public class Hobby {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY) //this for integration tests, coz we create tables with Liquibase
  // @GeneratedValue // this for unit tests, coz hibernate create tables
    private Long id;

    @Column(nullable = false)
    private String name;

    public Hobby (String name) {
        this.name = name;
    }

}
