package org.itsci.model.exam;

import javax.persistence.Entity;

@Entity
public class QuestionAndAnswer extends Question {
    private String answerOfQuestion;

    public String getAnswerOfQuestion() {
        return answerOfQuestion;
    }

    public void setAnswerOfQuestion(String answerOfQuestion) {
        this.answerOfQuestion = answerOfQuestion;
    }
}
