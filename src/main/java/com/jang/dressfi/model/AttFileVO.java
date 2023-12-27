package com.jang.dressfi.model;

public class AttFileVO {

    private int fno;
    private int pno;
    private String ofilename;
    private String sfilename;
    private long filesize;

    public int getFno() {
        return fno;
    }

    public void setFno(int fno) {
        this.fno = fno;
    }

    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }

    public String getOfilename() {
        return ofilename;
    }

    public void setOfilename(String ofilename) {
        this.ofilename = ofilename;
    }

    public String getSfilename() {
        return sfilename;
    }

    public void setSfilename(String sfilename) {
        this.sfilename = sfilename;
    }

    public long getFilesize() {
        return filesize;
    }

    public void setFilesize(long filesize) {
        this.filesize = filesize;
    }


}
