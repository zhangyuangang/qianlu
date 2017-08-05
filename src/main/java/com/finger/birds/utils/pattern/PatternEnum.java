package com.finger.birds.utils.pattern;

public enum PatternEnum {
	正则_邮箱("^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$"),
	正则_电话("^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$"),
	图片文件(".*(.jpg|.png|.gif|.jpeg|.bmp)$"),
	音频文件(".*(.mp3|.wmv|.amr)$")
	;
	private PatternEnum(String val){
		this.val=val;
	}
	
	private String val;

	public String getVal() {
		return val;
	}
	
}
