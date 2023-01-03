package org.itsci.exam.model;

import org.itsci.model.User;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Teacher extends User {
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "teacher_subject",
            joinColumns= { @JoinColumn(name = "teacher_id")},
            inverseJoinColumns= { @JoinColumn(name = "subject_id")})
    private Set<Subject> teachs;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "teacher_own_exam",
            joinColumns= { @JoinColumn(name = "teacher_id")},
            inverseJoinColumns= { @JoinColumn(name = "exam_id")})
//    private Set<Exam> exams;
//    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
//    @JoinTable(name = "teacher_own_quiz",
//            joinColumns= { @JoinColumn(name = "teacher_id")},
//            inverseJoinColumns= { @JoinColumn(name = "quiz_id")})
//    private Set<Quiz> quizs;

    public Set<Subject> getTeachs() {
        return teachs;
    }

    public void setTeachs(Set<Subject> teachs) {
        this.teachs = teachs;
    }

//    public Set<Exam> getExams() {
//        return exams;
//    }
//
//    public void setExams(Set<Exam> exams) {
//        this.exams = exams;
//    }
//
//    public Set<Quiz> getQuizs() {
//        return quizs;
//    }
//
//    public void setQuizs(Set<Quiz> quizs) {
//        this.quizs = quizs;
//    }
}
