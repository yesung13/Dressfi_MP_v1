package com.jang.dressfi.model;

import javax.validation.constraints.NotEmpty;

public class UserVO {

	private String mno;

	@NotEmpty(message = "id를 입력하세요!")
	private String userId;
	@NotEmpty(message = "패스워드를 입력하세요!")
	private String passwd;
	private String name;
	private String phone;
	private String homepage;
	private String introduce;
	private String kakaourl;
	private String proimg;
	private String joindate;
	//	private char del_yn='n';

	public String getMno() {
		return mno;
	}

	public void setMno(String mno) {
		this.mno = mno;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getKakaourl() {
		return kakaourl;
	}

	public void setKakaourl(String kakaourl) {
		this.kakaourl = kakaourl;
	}

	public String getProimg() {
		return proimg;
	}

	public void setProimg(String proimg) {
		this.proimg = proimg;
	}

	public String getJoindate() {
		return joindate;
	}

	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}

	@Override
	public String toString() {
		return "UserVO [mno=" + mno + ", userId=" + userId + ", passwd=" + passwd + ", name=" + name + ", phone=" + phone + ", homepage=" + homepage + ", introduce=" + introduce + ", kakaourl=" + kakaourl + ", proimg=" + proimg
				+ ", joindate=" + joindate + "]";
	}

	//	public char getDel_yn() {
	//		return del_yn;
	//	}
	//	public void setDel_yn(char del_yn) {
	//		this.del_yn = del_yn;
	//	}

}
