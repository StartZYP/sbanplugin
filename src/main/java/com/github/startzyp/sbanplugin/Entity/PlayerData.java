package com.github.startzyp.sbanplugin.Entity;

public class PlayerData {
    private String playername;
    private String RebootCode;


    @Override
    public String toString() {
        return "PlayerData{" +
                "playername='" + playername + '\'' +
                ", RebootCode='" + RebootCode + '\'' +
                '}';
    }

    public String getPlayername() {
        return playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername;
    }

    public String getRebootCode() {
        return RebootCode;
    }

    public void setRebootCode(String rebootCode) {
        RebootCode = rebootCode;
    }

    public PlayerData(String playername, String rebootCode) {
        this.playername = playername;
        RebootCode = rebootCode;
    }
}