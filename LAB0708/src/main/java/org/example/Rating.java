package org.example;


import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer ocena;
    @ManyToOne
    private CarShowroom carShowroom;
    private LocalDate data;
    @Column(nullable = false)
    private String opis;

}
