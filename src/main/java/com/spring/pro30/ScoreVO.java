package com.spring.pro30;

import org.springframework.stereotype.Component;

@Component
public class ScoreVO {
    public String id;
    public int score;
    public ScoreVO() {}
    public ScoreVO(String id, int score) {
        this.id = id;
        this.score = score;
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
