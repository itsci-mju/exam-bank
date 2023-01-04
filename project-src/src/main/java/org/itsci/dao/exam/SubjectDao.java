package org.itsci.dao.exam;

import org.itsci.model.exam.Subject;

import java.util.List;

public interface SubjectDao {

    List<Subject> getSubjects();

    void saveSubject(Subject subject);

    Subject getSubject(Long id);

    void deleteSubject(Long id);
}
