package org.itsci.service.exam;

import org.itsci.model.exam.Exam;
import org.itsci.model.exam.Subject;

import java.util.List;

public interface ExamService {

    List<Exam> getExams();

    void saveExam(Exam exam);

    Exam getExam(Long id);

    void deleteExam(Long id);

    List<Subject> getSubjects();
}
