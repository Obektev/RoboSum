package com.obektevco.robosum.obektev_utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RobotInfo {
    private String name;
    private String weight;
    private Integer place = -1;

    private Map<String, Integer> battleScore = new HashMap<>();
    private Integer looses = 0;
    private Integer wins = 0;

    List<String> winsOnOtherRobots = new ArrayList<>();
    public Integer getLooses() {
        return looses;
    }

    public void setLooses(Integer looses) {
        this.looses = looses;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getDraws() {
        return draws;
    }

    public void setDraws(Integer draws) {
        this.draws = draws;
    }

    private Integer draws = 0;

    public RobotInfo(String name, String weight) {
        this.name = name;
        this.weight = weight;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public List<String> getWinsOnOtherRobots() {
        return winsOnOtherRobots;
    }

    public void addWinOnOtherRobot(String defeatedRobot) {
        winsOnOtherRobots.add(defeatedRobot);
    }
    public void setWinsOnOtherRobots(List<String> winsOnOtherRobots) {
        this.winsOnOtherRobots = winsOnOtherRobots;
    }

    public Map<String, Integer> getBattleScore() {
        return battleScore;
    }

    public void setBattleScore(Map<String, Integer> battleScore) {
        this.battleScore = battleScore;
    }
}