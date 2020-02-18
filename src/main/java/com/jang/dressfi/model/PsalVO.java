package com.jang.dressfi.model;

public class PsalVO {

	private int prno;
	private String pr_sendid;
	private String pr_targetid;
	private String pr_title;
	private String pr_context;
	private String pr_startdate;
	private String pr_enddate;
	private String pr_fileloc;
	private String pr_regdate;
	private int pr_area;
	private int pr_target;
	private int p_ano1;
	private int p_ano2;
	private int p_ano3;
	
	public String getPr_targetid() {
		return pr_targetid;
	}
	public void setPr_targetid(String pr_targetid) {
		this.pr_targetid = pr_targetid;
	}
	public int getPr_area() {
		return pr_area;
	}
	public void setPr_area(int pr_area) {
		this.pr_area = pr_area;
	}
	public int getprno() {
		return prno;
	}
	public void setprno(int prno) {
		this.prno = prno;
	}
	public String getPr_sendid() {
		return pr_sendid;
	}
	public void setPr_sendid(String pr_sendid) {
		this.pr_sendid = pr_sendid;
	}

	public String getPr_title() {
		return pr_title;
	}
	public void setPr_title(String pr_title) {
		this.pr_title = pr_title;
	}
	public String getPr_context() {
		return pr_context;
	}
	public void setPr_context(String pr_context) {
		this.pr_context = pr_context;
	}
	public String getPr_startdate() {
		return pr_startdate;
	}
	public void setPr_startdate(String pr_startdate) {
		this.pr_startdate = pr_startdate;
	}
	public String getPr_enddate() {
		return pr_enddate;
	}
	public void setPr_enddate(String pr_enddate) {
		this.pr_enddate = pr_enddate;
	}
	public String getPr_fileloc() {
		return pr_fileloc;
	}
	public void setPr_fileloc(String pr_fileloc) {
		this.pr_fileloc = pr_fileloc;
	}
	public String getPr_regdate() {
		return pr_regdate;
	}
	public void setPr_regdate(String pr_regdate) {
		this.pr_regdate = pr_regdate;
	}
	public int getPr_target() {
		return pr_target;
	}
	public void setPr_target(int pr_target) {
		this.pr_target = pr_target;
	}
	public int getP_ano1() {
		return p_ano1;
	}
	public void setP_ano1(int p_ano1) {
		this.p_ano1 = p_ano1;
	}
	public int getP_ano2() {
		return p_ano2;
	}
	public void setP_ano2(int p_ano2) {
		this.p_ano2 = p_ano2;
	}
	public int getP_ano3() {
		return p_ano3;
	}
	public void setP_ano3(int p_ano3) {
		this.p_ano3 = p_ano3;
	}
	@Override
	public String toString() {
		return "PsalVO [prno=" + prno + ", pr_sendid=" + pr_sendid + ", pr_targetid=" + pr_targetid + ", pr_title="
				+ pr_title + ", pr_context=" + pr_context + ", pr_startdate=" + pr_startdate + ", pr_enddate="
				+ pr_enddate + ", pr_fileloc=" + pr_fileloc + ", pr_regdate=" + pr_regdate + ", pr_target=" + pr_target
				+ ", p_ano1=" + p_ano1 + ", p_ano2=" + p_ano2 + ", p_ano3=" + p_ano3 + "]";
	}
	
}