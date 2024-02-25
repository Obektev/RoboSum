package com.obektevco.robosum.tournamet_utils;

public class BattleInfo {
    public String getFirstWinner() {
        return firstWinner;
    }

    public void setFirstWinner(String firstWinner) {
        this.firstWinner = firstWinner;
    }

    public String getSecondWinner() {
        return secondWinner;
    }

    public void setSecondWinner(String secondWinner) {
        this.secondWinner = secondWinner;
    }

    public String getThirdWinner() {
        return thirdWinner;
    }

    public void setThirdWinner(String thirdWinner) {
        this.thirdWinner = thirdWinner;
    }

    public String getBattleWinner() {
        return battleWinner;
    }

    public void setBattleWinner(String battleWinner) {
        this.battleWinner = battleWinner;
    }

    private String firstWinner;
    private String secondWinner;
    private String thirdWinner;
    private String battleWinner;
}