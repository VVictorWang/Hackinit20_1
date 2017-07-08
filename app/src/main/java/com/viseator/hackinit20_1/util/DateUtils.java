package com.viseator.hackinit20_1.util;

import com.viseator.hackinit20_1.data.GameInfo;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by victor on 7/9/17.
 * email: chengyiwang@hustunique.com
 * blog: www.victorwang.science                                            #
 */

public class DateUtils {
    public static List<GameInfo> sortGameInfo(List<GameInfo> gameInfos) {
        List<GameInfo> result = new ArrayList<>();
        GameInfo[] gameInfos1 = new GameInfo[gameInfos.size()];
        gameInfos1 = gameInfos.toArray(gameInfos1);
        for (int i = 0; i < gameInfos.size(); i++) {
            for (int j = i + 1; j < gameInfos.size(); j++) {
                if (gameInfos1[i].getTime() > gameInfos1[j].getTime()) {
                    GameInfo temp = gameInfos1[i];
                    gameInfos1[i] = gameInfos1[j];
                    gameInfos1[j] = temp;
                }
            }
        }
        for (int i = 0; i < gameInfos1.length; i++) {
            result.add(gameInfos1[i]);
        }
        return result;
    }

    public static String getWeekbyTime(long time) {
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("EEEE");
        String week = format.format(date);
        return week;
    }
    public static String getHourbyTime(long time) {
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("hh:mm");
        String hour = format.format(date);
        return hour;
    }
}
