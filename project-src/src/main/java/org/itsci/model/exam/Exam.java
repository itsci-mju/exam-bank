package org.itsci.model.exam;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.SortedSet;

@Entity
@Table(name = "exams")
public class Exam {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    @Column(length = 50, nullable = false, unique = true)
    private String code;
    @Column(length = 50, nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name="subject_id")
    private Subject subject;
    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="exam")
    @OrderBy("commandNo")
    private SortedSet<ExamSection> sections;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public SortedSet<ExamSection> getSections() {
        return sections;
    }

    public void setSections(SortedSet<ExamSection> sections) {
        this.sections = sections;
    }
}
