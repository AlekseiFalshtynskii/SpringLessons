package ru.spring.quiz;

public class Question {
    private final String question;
    private final String answer;

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getAnswer() {
        return this.answer;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Question other = (Question) obj;
        return this.question.equals(other.getQuestion())
                && this.answer.equals(other.getAnswer());
    }

    @Override
    public int hashCode() {
        return this.question.hashCode() + this.answer.hashCode();
    }
}
