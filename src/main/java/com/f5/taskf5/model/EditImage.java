package com.f5.taskf5.model;

public class EditImage {
    private String title;
    private String url;

    
	public EditImage(String title, String url) {
		super();
		this.title = title;
		this.url = url;
	}

	public EditImage() {
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}