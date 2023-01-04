package org.itsci.dao.exam;

import org.itsci.model.exam.Exam;

import java.util.List;

public interface ExamDao {

    List<Exam> getExams();

    void saveExam(Exam exam);

    Exam getExam(Long id);

    void deleteExam(Long id);
}
