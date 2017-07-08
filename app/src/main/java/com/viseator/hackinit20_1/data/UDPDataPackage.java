package com.viseator.hackinit20_1.data;

import android.content.Context;

import com.viseator.hackinit20_1.util.network.GetNetworkInfo;

import java.io.Serializable;

/**
 * Created by viseator on 2016/12/21.
 * Wudi
 * viseator@gmail.com
 */

public class UDPDataPackage implements Serializable {
    final long serialVersionUID = 66666666L;
    private String ipAddress;
    private String macAddress;
    private String title;
    private String id;

    public UDPDataPackage(Context context) {
        this.ipAddress = GetNetworkInfo.getIp(context);
        this.macAddress = GetNetworkInfo.getMac();
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
