package com.viseator.hackinit20_1.util;


import com.viseator.hackinit20_1.data.DataBean;
import com.viseator.hackinit20_1.data.GameData;
import com.viseator.hackinit20_1.data.GameDataEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static android.R.attr.format;

/**
 * Created by victor on 7/9/17.
 * email: chengyiwang@hustunique.com
 * blog: www.victorwang.science                                            #
 */

public class DateUtils {
    public static List<GameDataEntity> sortGameInfo(List<GameDataEntity> gameInfos) {
        List<GameDataEntity> result = new ArrayList<>();
        GameDataEntity[] gameInfos1 = new GameDataEntity[gameInfos.size()];
        gameInfos1 = gameInfos.toArray(gameInfos1);
        for (int i = 0; i < gameInfos.size(); i++) {
            for (int j = i + 1; j < gameInfos.size(); j++) {
                if (gameInfos1[i].getTime() > gameInfos1[j].getTime()) {
                    GameDataEntity temp = gameInfos1[i];
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

    public static String formatDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd");
        return format.format(date);
    }

    public static int sumEveryDay(List<GameDataEntity> gameDataEntities) {
        int time = 0;

        HashMap<String, TempData> infos = new HashMap<>();
        for (GameDataEntity entity : gameDataEntities) {
            if (infos.get(entity.getName()) == null) {
                TempData tempData = new TempData();
                tempData.setTime(entity.getTime());
                tempData.setIsopen(entity.getIsOpen());
                infos.put(entity.getName(), tempData);
            } else {
                TempData tempData = infos.get(entity.getName());
                if (tempData.isopen() != entity.getIsOpen()) {
                    time += Math.abs(entity.getTime() - tempData.getTime());
                }
            }
        }
        return time;

    }

    public static String timetoMinute(long time) {
        time /= 1000;
        int mint = (int) (time % 3600) / 60;
        String mintStr = String.valueOf(mint);
        mintStr = "0" + mintStr + "分钟";
        return mintStr;
    }

    static class TempData {
        private boolean isopen;
        private long time;

        public boolean isopen() {
            return isopen;
        }

        public void setIsopen(boolean isopen) {
            this.isopen = isopen;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }
    }
}
