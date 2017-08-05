package com.finger.birds.utils.file;

public class SmallImageSize {
	private int width;
	private int height;

	public SmallImageSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public boolean isSmaller(SmallImageSize size){
		if(this.getWidth() < size.getWidth() && this.getHeight() < size.getHeight()){
			return true;
		}
		return false;
	}
}
