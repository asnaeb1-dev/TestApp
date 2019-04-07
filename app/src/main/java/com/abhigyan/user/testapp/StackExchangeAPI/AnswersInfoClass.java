package com.abhigyan.user.testapp.StackExchangeAPI;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AnswersInfoClass {

    @SerializedName("items")
    @Expose
    private List<Item> items = null;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public class Answer {

        @SerializedName("comment_count")
        @Expose
        private Integer commentCount;
        @SerializedName("answer_id")
        @Expose
        private Integer answerId;
        @SerializedName("link")
        @Expose
        private String link;
        @SerializedName("body")
        @Expose
        private String body;

        public Integer getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(Integer commentCount) {
            this.commentCount = commentCount;
        }

        public Integer getAnswerId() {
            return answerId;
        }

        public void setAnswerId(Integer answerId) {
            this.answerId = answerId;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }

    public class Item {

        @SerializedName("answers")
        @Expose
        private List<Answer> answers = null;
        @SerializedName("comment_count")
        @Expose
        private Integer commentCount;
        @SerializedName("last_activity_date")
        @Expose
        private Integer lastActivityDate;
        @SerializedName("question_id")
        @Expose
        private Integer questionId;
        @SerializedName("link")
        @Expose
        private String link;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("body")
        @Expose
        private String body;

        public List<Answer> getAnswers() {
            return answers;
        }

        public void setAnswers(List<Answer> answers) {
            this.answers = answers;
        }

        public Integer getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(Integer commentCount) {
            this.commentCount = commentCount;
        }

        public Integer getLastActivityDate() {
            return lastActivityDate;
        }

        public void setLastActivityDate(Integer lastActivityDate) {
            this.lastActivityDate = lastActivityDate;
        }

        public Integer getQuestionId() {
            return questionId;
        }

        public void setQuestionId(Integer questionId) {
            this.questionId = questionId;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }
}
