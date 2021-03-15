package org.rimmar.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //this for integration tests, coz we create tables with Liquibase
  // @GeneratedValue // this for unit tests, coz hibernate create tables
    private Long id;



    @Column(nullable = false)
    private String name;

   @OneToMany ( cascade = CascadeType.ALL, orphanRemoval = true)
   private List<Hobby> hobbyList;

   public Person (String name, List<Hobby> hobbyList ){
       this.name = name;
       this.hobbyList = hobbyList;
   }

}
