package org.itsci.service;

import org.itsci.dao.ChapterDao;
import org.itsci.dao.SubjectDao;
import org.itsci.exam.model.Chapter;
import org.itsci.exam.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectDao subjectDao;

    @Autowired
    private ChapterDao chapterDao;

    @Override
    @Transactional
    public List<Subject> getSubjects() {
        return subjectDao.getSubjects();
    }

    @Override
    @Transactional
    public void saveSubject(Subject subject) {
        subjectDao.saveSubject(subject);
    }

    @Override
    @Transactional
    public void saveChaper(Chapter chapter) {
        chapterDao.saveChapter(chapter);
    }

    @Override
    @Transactional
    public Subject getSubject(Long id) {
        return subjectDao.getSubject(id);
    }

    @Override
    @Transactional
    public void deleteSubject(Long id) {
        subjectDao.deleteSubject(id);
    }

    @Override
    @Transactional
    public void addChaper(Long id, Chapter obj) {
        Subject subject = subjectDao.getSubject(id);
        subject.getChapters().add(obj);
        subjectDao.saveSubject(subject);
    }

    @Override
    @Transactional
    public Chapter getChapter(Long id) {
        return chapterDao.getChapter(id);
    }
}
