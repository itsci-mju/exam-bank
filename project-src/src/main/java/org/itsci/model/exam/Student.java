package org.itsci.model.exam;

import org.itsci.model.User;

import javax.persistence.*;

@Entity
public class Student extends User {
    private String studentCode;
}
