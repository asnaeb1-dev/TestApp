package com.abhigyan.user.testapp;

public class Question {

    private String title;
    private int voteCount;
    private String acceptedAnswerLink;
    private long score;
    private long viewVount;
    private long answerCount;
    private String ownerName;
    private String ownerImage;
    private String dateModified;
    private String creationDate;
    private boolean isAnswered;
    private int questionID;

    public int getQuestionID() {
        return questionID;
    }

    public Question(boolean isAnswered, String title, int voteCount, String acceptedAnswerLink, long score, long viewVount, long answerCount, String ownerName, String ownerImage, String dateModified, String creationDate, int quesID) {
        this.isAnswered = isAnswered;
        this.title = title;
        this.voteCount = voteCount;
        this.acceptedAnswerLink = acceptedAnswerLink;
        this.score = score;
        this.viewVount = viewVount;
        this.answerCount = answerCount;
        this.ownerName = ownerName;
        this.ownerImage = ownerImage;
        this.dateModified = dateModified;
        this.creationDate = creationDate;
        this.questionID = quesID;
    }


    public boolean getIsAnswered()
    {
        return isAnswered;
    }

    public String getTitle() {
        return title;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public String getAcceptedAnswerLink() {
        return acceptedAnswerLink;
    }

    public long getScore() {
        return score;
    }

    public long getViewVount() {
        return viewVount;
    }

    public long getAnswerCount() {
        return answerCount;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getOwnerImage() {
        return ownerImage;
    }

    public String getDateModified() {
        return dateModified;
    }

    public String getCreationDate() {
        return creationDate;
    }
}
