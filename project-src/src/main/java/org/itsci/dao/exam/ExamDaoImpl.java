package org.itsci.dao.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.itsci.model.exam.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ExamDaoImpl implements ExamDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Exam> getExams() {
        Class<Exam> clazz = Exam.class;
        Query<Exam> query;
        CriteriaQuery<Exam> criteria;
        Root<Exam> root;
        List<Exam> results;

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        criteria = builder.createQuery(clazz);
        root = criteria.from(clazz);
        criteria.select(root);

        query = session.createQuery(criteria);
        results = query.getResultList();
        return results;
    }

    @Override
    public void saveExam(Exam exam) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(exam);
    }

    @Override
    public Exam getExam(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Exam result = session.get(Exam.class, id);
        return result;
    }

    @Override
    public void deleteExam(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Exam obj = session.get(Exam.class, id);
        session.delete(obj);
        session.flush() ;
    }
}
