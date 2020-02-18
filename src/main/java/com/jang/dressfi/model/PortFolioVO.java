package com.jang.dressfi.model;

import oracle.sql.DATE;

public class PortFolioVO {

	private int ino;
	private int i_mnum;
	private String i_mdiv;
	private String i_title;
	private String i_text;
	private String i_viewcnt;
	private String i_likecnt;
	private String i_replycnt;
	private String i_fileloc;
	private DATE i_regdate;
	
	public int getIno() {
		return ino;
	}
	public void setIno(int ino) {
		this.ino = ino;
	}
	public int getI_mnum() {
		return i_mnum;
	}
	public void setI_mnum(int i_mnum) {
		this.i_mnum = i_mnum;
	}
	public String getI_mdiv() {
		return i_mdiv;
	}
	public void setI_mdiv(String i_mdiv) {
		this.i_mdiv = i_mdiv;
	}
	public String getI_title() {
		return i_title;
	}
	public void setI_title(String i_title) {
		this.i_title = i_title;
	}
	public String getI_text() {
		return i_text;
	}
	public void setI_text(String i_text) {
		this.i_text = i_text;
	}
	public String getI_viewcnt() {
		return i_viewcnt;
	}
	public void setI_viewcnt(String i_viewcnt) {
		this.i_viewcnt = i_viewcnt;
	}
	public String getI_likecnt() {
		return i_likecnt;
	}
	public void setI_likecnt(String i_likecnt) {
		this.i_likecnt = i_likecnt;
	}
	public String getI_replycnt() {
		return i_replycnt;
	}
	public void setI_replycnt(String i_replycnt) {
		this.i_replycnt = i_replycnt;
	}
	public String getI_fileloc() {
		return i_fileloc;
	}
	public void setI_fileloc(String i_fileloc) {
		this.i_fileloc = i_fileloc;
	}
	public DATE getI_regdate() {
		return i_regdate;
	}
	public void setI_regdate(DATE i_regdate) {
		this.i_regdate = i_regdate;
	}
	
}
