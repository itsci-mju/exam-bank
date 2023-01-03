package org.itsci.exam.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

//@Entity
//@Table(name = "quizs")
public class Quiz {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    private int term;
    private int year;
    private Date date;
//    @OneToOne
//    private Exam exam;

}
