package org.itsci.dao;

import org.itsci.exam.model.Exam;

import java.util.List;

public interface ExamDao {

    List<Exam> getExams();

    void saveExam(Exam exam);

    Exam getExam(Long id);

    void deleteExam(Long id);
}
