package com.jang.dressfi.model;

import oracle.sql.DATE;

public class KnowHowVO {

    private int hno;
    private int h_category;
    private String h_mdiv;
    private int h_mnum;
    private String h_name;
    private String h_title;
    private String h_text;
    private String h_viewcnt;
    private String h_likecnt;
    private String h_replycnt;
    private String h_fileloc;
    private DATE h_regdate;

    public int getHno() {
        return hno;
    }

    public void setHno(int hno) {
        this.hno = hno;
    }

    public int getH_category() {
        return h_category;
    }

    public void setH_category(int h_category) {
        this.h_category = h_category;
    }

    public String getH_mdiv() {
        return h_mdiv;
    }

    public void setH_mdiv(String h_mdiv) {
        this.h_mdiv = h_mdiv;
    }

    public int getH_mnum() {
        return h_mnum;
    }

    public void setH_mnum(int h_mnum) {
        this.h_mnum = h_mnum;
    }

    public String getH_name() {
        return h_name;
    }

    public void setH_name(String h_name) {
        this.h_name = h_name;
    }

    public String getH_title() {
        return h_title;
    }

    public void setH_title(String h_title) {
        this.h_title = h_title;
    }

    public String getH_text() {
        return h_text;
    }

    public void setH_text(String h_text) {
        this.h_text = h_text;
    }

    public String getH_viewcnt() {
        return h_viewcnt;
    }

    public void setH_viewcnt(String h_viewcnt) {
        this.h_viewcnt = h_viewcnt;
    }

    public String getH_likecnt() {
        return h_likecnt;
    }

    public void setH_likecnt(String h_likecnt) {
        this.h_likecnt = h_likecnt;
    }

    public String getH_replycnt() {
        return h_replycnt;
    }

    public void setH_replycnt(String h_replycnt) {
        this.h_replycnt = h_replycnt;
    }

    public String getH_fileloc() {
        return h_fileloc;
    }

    public void setH_fileloc(String h_fileloc) {
        this.h_fileloc = h_fileloc;
    }

    public DATE getH_regdate() {
        return h_regdate;
    }

    public void setH_regdate(DATE h_regdate) {
        this.h_regdate = h_regdate;
    }


}
