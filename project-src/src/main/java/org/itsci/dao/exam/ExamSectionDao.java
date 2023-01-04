package org.itsci.dao.exam;

import org.itsci.model.exam.ExamSection;

import java.util.List;

public interface ExamSectionDao {

    List<ExamSection> getExamSections();

    void saveExamSection(ExamSection examSection);

    ExamSection getExamSection(Long id);

    void deleteExamSection(Long id);
}
