package org.itsci.dao.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.itsci.model.exam.Exam;
import org.itsci.model.exam.ExamSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
@Repository
public class ExamSectionDaoImpl implements ExamSectionDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ExamSection> getExamSections() {
        Class<ExamSection> clazz = ExamSection.class;
        Query<ExamSection> query;
        CriteriaQuery<ExamSection> criteria;
        Root<ExamSection> root;
        List<ExamSection> results;

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
    public void saveExamSection(ExamSection examSection) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(examSection);
    }

    @Override
    public ExamSection getExamSection(Long id) {
        Session session = sessionFactory.getCurrentSession();
        ExamSection result = session.get(ExamSection.class, id);
        return result;
    }

    @Override
    public void deleteExamSection(Long id) {
        Session session = sessionFactory.getCurrentSession();
        ExamSection obj = session.get(ExamSection.class, id);
        session.delete(obj);
        session.flush() ;
    }
}
