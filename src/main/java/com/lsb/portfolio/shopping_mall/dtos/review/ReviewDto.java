package com.lsb.portfolio.shopping_mall.dtos.review;

import java.sql.Timestamp;
import java.util.Date;

public class ReviewDto {
    private final String nickname;
    private final int memberIndex;
    private final String content;
    private final int ratingOptions;
    private final Timestamp reviewDate;

    public ReviewDto(String nickname, int memberIndex, String content, int ratingOptions, Timestamp reviewDate) {
        this.nickname = nickname;
        this.memberIndex = memberIndex;
        this.content = content;
        this.ratingOptions = ratingOptions;
        this.reviewDate = reviewDate;
    }

    public int getMemberIndex() {
        return memberIndex;
    }

    public String getContent() {
        return content;
    }

    public int getRatingOptions() {
        return ratingOptions;
    }

    public Timestamp getReviewDate() {
        return reviewDate;
    }

    public String getNickname() {
        return nickname;
    }
}
