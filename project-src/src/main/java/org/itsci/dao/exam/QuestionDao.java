package org.itsci.dao.exam;

import org.itsci.model.exam.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> getQuestions();

    void saveQuestion(Question question);

    Question getQuestion(Long id);

    void deleteQuestion(Long id);
}
