package com.abhigyan.user.testapp;

public class Answers {
    String answerBody;
    String answerTitles;

    public Answers(String answerBody, String answerTitles) {
        this.answerBody = answerBody;
        this.answerTitles = answerTitles;
    }

    public String getAnswerBody() {
        return answerBody;
    }

    public String getAnswerTitles()
    {
        return answerTitles;
    }
}
