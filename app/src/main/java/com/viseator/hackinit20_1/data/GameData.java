package com.viseator.hackinit20_1.data;

import java.util.List;

/**
 * Created by viseator on 7/8/17.
 *
 * @author Wu Di
 *         Email: viseator@gmail.com
 */

public class GameData {
    private static GameData INSTANCE = null;
    private GameDataEntityDao mGameDataEntityDao;

    public static GameData getInstance() {
        return INSTANCE;
    }

    public static GameData getInstance(GameDataEntityDao gameDataEntityDao) {
        INSTANCE = new GameData(gameDataEntityDao);
        return INSTANCE;
    }

    private GameData(GameDataEntityDao gameDataEntityDao) {
        mGameDataEntityDao = gameDataEntityDao;
    }


    public int count() {
        return (int) mGameDataEntityDao.count();
    }

    public void addGameData(String name, long time, boolean isOpen) {
        GameDataEntity gameDataEntity = new GameDataEntity();
        gameDataEntity.setTime(time);
        gameDataEntity.setName(name);
        gameDataEntity.setIsOpen(isOpen);
        mGameDataEntityDao.insert(gameDataEntity);
    }

    public List<GameDataEntity> getDataList() {
        return mGameDataEntityDao.queryBuilder().list();
    }
}
