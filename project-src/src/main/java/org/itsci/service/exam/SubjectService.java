package org.itsci.service.exam;

import org.itsci.model.exam.Chapter;
import org.itsci.model.exam.Subject;

import java.util.List;

public interface SubjectService {

    List<Subject> getSubjects();

    void saveSubject(Subject subject);

    void saveChaper(Chapter chapter);

    Subject getSubject(Long id);

    void deleteSubject(Long id);

    void addChaper(Long id, Chapter obj);

    Chapter getChapter(Long id);
}
