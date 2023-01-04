package org.itsci.dao.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.itsci.model.exam.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ChapterDaoImpl implements ChapterDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Chapter> getChapters() {
        Class<Chapter> clazz = Chapter.class;
        Query<Chapter> query;
        CriteriaQuery<Chapter> criteria;
        Root<Chapter> root;
        List<Chapter> results;

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
    public void saveChapter(Chapter obj) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(obj);
    }

    @Override
    public Chapter getChapter(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Chapter result = session.get(Chapter.class, id);
        return result;
    }

    @Override
    public void deleteChapter(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Chapter obj = session.get(Chapter.class, id);
        session.delete(obj);
        session.flush() ;
    }
}
