package org.itsci.service.exam;

import org.itsci.model.exam.*;

import java.util.List;

public interface ExamService {

    List<Exam> getExams();

    void saveExam(Exam exam);

    Exam getExam(Long id);

    void deleteExam(Long id);

    List<Subject> getSubjects();

    void addExamSection(long examId, ExamSection section);

    ExamSection getExamSection(long id);

    void saveSection(ExamSection section);

    Chapter getChapter(Long id);

    Question getQuestion(long id);

    void saveQuestion(Question question);
}
