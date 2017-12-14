package com.myorg.service;

import com.myorg.model.Qna;
import com.myorg.model.MoreReading;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class QnaManager {
    private static List<Qna> qnaList;

    static {
        MoreReading moreReadingSet1 = new MoreReading();
        moreReadingSet1.setPublisherId((long)1);
        moreReadingSet1.setPublisherName("SPD");

        Qna qnaSet1 = new Qna();
        qnaSet1.setQnaId((long)1);
        qnaSet1.setMoreReading(moreReadingSet1);
        qnaSet1.setQuestion("q1");
        qnaSet1.setAnswer("a1");

        Qna qnaSet2 = new Qna();
        qnaSet2.setQnaId((long)2);
        qnaSet2.setMoreReading(moreReadingSet1);
        qnaSet2.setQuestion("q2");
        qnaSet2.setAnswer("a2");

        Qna qnaSet3 = new Qna();
        qnaSet3.setQnaId((long)3);
        qnaSet3.setMoreReading(moreReadingSet1);
        qnaSet3.setQuestion("q3");
        qnaSet3.setAnswer("a3");

        qnaList = new LinkedList<Qna>();
        qnaList.add(qnaSet1);
        qnaList.add(qnaSet2);
        qnaList.add(qnaSet3);
    }
        
    public List<Qna> getQnaList() {
        return qnaList;
    }
}