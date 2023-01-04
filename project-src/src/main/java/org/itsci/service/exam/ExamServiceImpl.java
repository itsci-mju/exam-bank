package org.itsci.service.exam;

import org.itsci.dao.exam.*;
import org.itsci.model.exam.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamDao examDao;

    @Autowired
    private SubjectDao subjectDao;

    @Autowired
    private ExamSectionDao examSectionDao;

    @Autowired
    private ChapterDao chapterDao;

    @Autowired
    private QuestionDao questionDao;

    @Override
    @Transactional
    public List<Exam> getExams() {
        return examDao.getExams();
    }

    @Override
    @Transactional
    public void saveExam(Exam exam) {
        Subject subject = subjectDao.getSubject(exam.getSubject().getId());
        exam.setSubject(subject);
        examDao.saveExam(exam);
    }

    @Override
    @Transactional
    public Exam getExam(Long id) {
        return examDao.getExam(id);
    }

    @Override
    @Transactional
    public void deleteExam(Long id) {
        examDao.deleteExam(id);
    }

    @Override
    @Transactional
    public List<Subject> getSubjects() {
        return subjectDao.getSubjects();
    }

    @Override
    @Transactional
    public void addExamSection(long examId, ExamSection section) {
        Exam exam = examDao.getExam(examId);
        section.setExam(exam);
        exam.getSections().add(section);
        examDao.saveExam(exam);
    }

    @Override
    @Transactional
    public ExamSection getExamSection(long id) {
        return examSectionDao.getExamSection(id);
    }

    @Override
    @Transactional
    public void saveSection(ExamSection section) {
        examSectionDao.saveExamSection(section);
    }

    @Override
    @Transactional
    public Chapter getChapter(Long id) {
        return chapterDao.getChapter(id);
    }

    @Override
    @Transactional
    public Question getQuestion(long id) {
        return questionDao.getQuestion(id);
    }

    @Override
    @Transactional
    public void saveQuestion(Question question) {
        questionDao.saveQuestion(question);
    }
}
