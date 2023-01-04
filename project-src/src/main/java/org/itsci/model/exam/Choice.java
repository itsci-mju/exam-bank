package org.itsci.model.exam;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "choices")
public class Choice {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="multiple_choice_id")
    private MultipleChoice multipleChoice;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipleChoice getMultipleChoice() {
        return multipleChoice;
    }

    public void setMultipleChoice(MultipleChoice multipleChoice) {
        this.multipleChoice = multipleChoice;
    }
}
