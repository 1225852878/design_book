package com.sise.design_book.pojo;

public class Book {
	private String id;
	private String name;
	private String author;
	private String press;
	private String address;
	private String ebooks;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEbooks() {
		return ebooks;
	}
	public void setEbooks(String ebooks) {
		this.ebooks = ebooks;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + ", press=" + press + ", address=" + address
				+ ", ebooks=" + ebooks + "]";
	}
	

}
