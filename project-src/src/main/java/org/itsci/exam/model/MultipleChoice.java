package org.itsci.exam.model;

import org.itsci.exam.model.Choice;
import org.itsci.exam.model.Question;

import javax.persistence.*;
import java.util.Set;

@Entity
public class MultipleChoice extends Question {
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name="correct_answer_id")
    private Choice correctAnswer;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn (name = "question_id")
    private Set<Choice> choices;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name="answer_id")

    public Choice getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Choice correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Set<Choice> getChoices() {
        return choices;
    }

    public void setChoices(Set<Choice> choices) {
        this.choices = choices;
    }
}
