package org.itsci.exam.model;

import org.itsci.model.User;

import javax.persistence.*;

@Entity
public class Student extends User {
    private String studentCode;
}
