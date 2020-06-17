package com.dsmbamboo.api.utils;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeCalculator {

    public static String toRecentKorean(LocalDateTime createdAt) {
        long seconds = Duration.between(createdAt, LocalDateTime.now()).toSeconds();

        String result = "약 ";
        if (seconds < 60) result += seconds + "초";
        else if ((seconds /= 60) < 60) result += seconds + "분";
        else if ((seconds /= 60) < 24) result += seconds + "시간";
        else if ((seconds /= 24) < 30) result += seconds + "일";
        else if ((seconds /= 30) < 12) result += seconds + "달";
        else result += seconds + "년";

        return result + " 전";
    }

}
