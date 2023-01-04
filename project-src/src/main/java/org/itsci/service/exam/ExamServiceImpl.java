package org.itsci.service.exam;

import org.itsci.dao.exam.ExamDao;
import org.itsci.dao.exam.SubjectDao;
import org.itsci.model.exam.Exam;
import org.itsci.model.exam.Subject;
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
}
