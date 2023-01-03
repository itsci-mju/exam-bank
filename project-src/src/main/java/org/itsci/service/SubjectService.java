package org.itsci.service;

import org.itsci.exam.model.Chapter;
import org.itsci.exam.model.Subject;

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
