package com.rcube.generalknowldgequiz;

public class BookmarksModal {
    String uuid;
    String question;
    String answer;


    public BookmarksModal(String uuid, String question, String answer) {
        this.uuid = uuid;
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
