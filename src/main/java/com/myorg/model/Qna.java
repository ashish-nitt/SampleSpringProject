package com.myorg.model;

public class Qna {
    private Long qnaId;
    private MoreReading moreReading;
    private String question;
    private String answer;

    public Long getQnaId() {
        return qnaId;
    }
    public void setQnaId(Long qnaId) {
        this.qnaId = qnaId;
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

    public MoreReading getMoreReading() {
        return moreReading;
    }
    public void setMoreReading(MoreReading moreReading) {
        this.moreReading = moreReading;
    }
}
