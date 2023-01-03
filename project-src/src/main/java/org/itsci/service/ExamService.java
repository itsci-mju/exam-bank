package org.itsci.service;

import org.itsci.exam.model.Exam;
import org.itsci.exam.model.Subject;

import java.util.List;
import java.util.SortedSet;

public interface ExamService {

    List<Exam> getExams();

    void saveExam(Exam exam);

    Exam getExam(Long id);

    void deleteExam(Long id);

    List<Subject> getSubjects();
}
