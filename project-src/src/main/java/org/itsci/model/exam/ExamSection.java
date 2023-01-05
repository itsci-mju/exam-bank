package org.itsci.model.exam;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;

@Entity
@Table(name = "exam_sections")
public class ExamSection implements Comparable<ExamSection> {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    @Column(length = 50, nullable = false)
    private int commandNo;
    @Column(length = 50, nullable = false)
    private String commandText;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="exam_id")
    private Exam exam;
    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn (name = "exam_section_id")
    @OrderBy("chapter")
    private List<Question> questions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommandText() {
        return commandText;
    }

    public void setCommandText(String commandText) {
        this.commandText = commandText;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamSection that = (ExamSection) o;
        return id == that.id && commandNo == that.commandNo && commandText.equals(that.commandText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, commandNo, commandText);
    }

    @Override
    public int compareTo(ExamSection o) {
        String str = commandNo + commandText;
        String str2 = o.getCommandNo() + o.getCommandText();
        return str.compareTo(str2);
    }
}
