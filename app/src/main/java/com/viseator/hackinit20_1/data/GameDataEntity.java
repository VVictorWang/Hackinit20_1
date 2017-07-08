package com.viseator.hackinit20_1.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by viseator on 7/8/17.
 *
 * @author Wu Di
 * Email: viseator@gmail.com
 */

@Entity
public class GameDataEntity {
    long time;
    String name;
    boolean isOpen;
    @Generated(hash = 1267141990)
    public GameDataEntity(long time, String name, boolean isOpen) {
        this.time = time;
        this.name = name;
        this.isOpen = isOpen;
    }
    @Generated(hash = 588054022)
    public GameDataEntity() {
    }
    public long getTime() {
        return this.time;
    }
    public void setTime(long time) {
        this.time = time;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean getIsOpen() {
        return this.isOpen;
    }
    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

}
