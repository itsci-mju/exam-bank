package org.itsci.exam.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class MultipleChoice extends Question {
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "correct_answer_id")
    private Choice correctAnswer;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy = "multipleChoice")
    private Set<Choice> choices;

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
