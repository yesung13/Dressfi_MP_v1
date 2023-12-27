package com.jang.dressfi.model;

public class FollowVO {

    private int fno;
    private String following;
    private String follower;
    private String f_date;
    private String flimg;
    private String fwimg;


    public int getFno() {
        return fno;
    }

    public void setFno(int fno) {
        this.fno = fno;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getF_date() {
        return f_date;
    }

    public void setF_date(String f_date) {
        this.f_date = f_date;
    }

    public String getFlimg() {
        return flimg;
    }

    public void setFlimg(String flimg) {
        this.flimg = flimg;
    }

    public String getFwimg() {
        return fwimg;
    }

    public void setFwimg(String fwimg) {
        this.fwimg = fwimg;
    }

    @Override
    public String toString() {
        return "FollowVO [fno=" + fno + ", following=" + following + ", follower=" + follower + ", f_date=" + f_date
                + ", flimg=" + flimg + ", fwimg=" + fwimg + "]";
    }

}
