package org.itsci.exam.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ExamSection implements Comparable<ExamSection> {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    @Column(length = 50, nullable = false, unique = true)
    private int commandNo;
    @Column(length = 50, nullable = false, unique = true)
    private String commandText;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "exam_section_question",
            joinColumns= { @JoinColumn(name = "exam_section_id")},
            inverseJoinColumns= { @JoinColumn(name = "question_id")})
    private Set<Question> questions;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCommandText() {
        return commandText;
    }

    public void setCommandText(String commandText) {
        this.commandText = commandText;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public int getCommandNo() {
        return commandNo;
    }

    public void setCommandNo(int commandNo) {
        this.commandNo = commandNo;
    }

    @Override
    public int compareTo(ExamSection o) {
        return this.commandNo - o.commandNo;
    }
}
