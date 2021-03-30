package com.bin23.nbatoday.entity;

public class TeamBean {
    private String teamName;
    private String teamFans;
    private int teamPic;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamFans() {
        return teamFans;
    }

    public void setTeamFans(String teamFans) {
        this.teamFans = teamFans;
    }

    public int getTeamPic() {
        return teamPic;
    }

    public void setTeamPic(int teamPic) {
        this.teamPic = teamPic;
    }

    public TeamBean() {
    }

    public TeamBean(String teamName, String teamFans, int teamPic) {
        this.teamName = teamName;
        this.teamFans = teamFans;
        this.teamPic = teamPic;
    }
}
