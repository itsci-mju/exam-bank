package org.itsci.dao;

import org.itsci.exam.model.Subject;

import java.util.List;

public interface SubjectDao {

    List<Subject> getSubjects();

    void saveSubject(Subject subject);

    Subject getSubject(Long id);

    void deleteSubject(Long id);
}
