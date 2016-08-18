package com.ImaginationUnlimited.GifKeyboard.gifkeycommon.network.entity;

public class AuthorizeInfo {
	private String time;
	private String imageUrl;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	@Override
	public String toString() {
		return "AuthorizeInfo [time=" + time + ", imageUrl=" + imageUrl + "]";
	}
	

}
