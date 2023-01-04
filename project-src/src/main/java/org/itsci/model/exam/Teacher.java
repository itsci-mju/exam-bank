package org.itsci.model.exam;

import org.itsci.model.Member;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Teacher extends Member {
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "teacher_subject",
            joinColumns= { @JoinColumn(name = "teacher_id")},
            inverseJoinColumns= { @JoinColumn(name = "subject_id")})
    private Set<Subject> teachs;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "teacher_own_exam",
            joinColumns= { @JoinColumn(name = "teacher_id")},
            inverseJoinColumns= { @JoinColumn(name = "exam_id")})
    private Set<Exam> exams;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "teacher_own_quiz",
            joinColumns= { @JoinColumn(name = "teacher_id")},
            inverseJoinColumns= { @JoinColumn(name = "quiz_id")})
    private Set<Quiz> quizzes;

    public Set<Subject> getTeachs() {
        return teachs;
    }

    public void setTeachs(Set<Subject> teachs) {
        this.teachs = teachs;
    }

    public Set<Exam> getExams() {
        return exams;
    }

    public void setExams(Set<Exam> exams) {
        this.exams = exams;
    }

    public Set<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(Set<Quiz> quizzes) {
        this.quizzes = quizzes;
    }
}
