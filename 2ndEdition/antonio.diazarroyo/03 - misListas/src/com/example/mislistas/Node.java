package com.example.mislistas;

public class Node {
	
	private String title;
	private String desc;
	private Integer imgId;

	public Node(String title, String desc, Integer imgId) {
		super();
		this.title = title;
		this.desc = desc;
		this.imgId = imgId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getImgId() {
		return imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

}
