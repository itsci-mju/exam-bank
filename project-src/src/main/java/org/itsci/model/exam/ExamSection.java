package org.itsci.model.exam;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.SortedSet;

@Entity
@Table(name = "exam_sections")
public class ExamSection {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    @Column(length = 50, nullable = false, unique = true)
    private int commandNo;
    @Column(length = 50, nullable = false, unique = true)
    private String commandText;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="exam_id")
    private Exam exam;
    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="examSection")
    @OrderBy("chapter")
    private SortedSet<Question> questions;

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

    public SortedSet<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(SortedSet<Question> questions) {
        this.questions = questions;
    }

    public int getCommandNo() {
        return commandNo;
    }

    public void setCommandNo(int commandNo) {
        this.commandNo = commandNo;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
