package org.itsci.dao.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.itsci.model.exam.ExamSection;
import org.itsci.model.exam.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class QuestionDaoImpl implements QuestionDao {

    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Question> getQuestions() {
        Class<Question> clazz = Question.class;
        Query<Question> query;
        CriteriaQuery<Question> criteria;
        Root<Question> root;
        List<Question> results;

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
    public void saveQuestion(Question question) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(question);
    }

    @Override
    public Question getQuestion(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Question result = session.get(Question.class, id);
        return result;
    }

    @Override
    public void deleteQuestion(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Question obj = session.get(Question.class, id);
        session.delete(obj);
        session.flush() ;
    }
}
