package com.sise.design_book.pojo;

public class Student {
	private String stuno;
	private String stupwd;
	private String name;
	private String pclass;
	private String photo;
	public String getStuno() {
		return stuno;
	}
	public void setStuno(String stuno) {
		this.stuno = stuno;
	}
	public String getStupwd() {
		return stupwd;
	}
	public void setStupwd(String stupwd) {
		this.stupwd = stupwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPclass() {
		return pclass;
	}
	public void setPclass(String pclass) {
		this.pclass = pclass;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/*@Override
	public String toString() {
		return "Student [stuno=" + stuno + ", stupwd=" + stupwd + ", name=" + name + ", pclass=" + pclass + "]";
	}*/

	@Override
	public String toString() {
		return "Student{" + "stuno='" + stuno + '\'' + ", stupwd='" + stupwd + '\'' +
				", name='" + name + '\'' +
				", pclass='" + pclass + '\'' +
				", photo='" + photo + '\'' +
				'}';
	}
}
