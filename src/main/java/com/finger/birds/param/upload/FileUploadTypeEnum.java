package com.finger.birds.param.upload;

import java.io.File;
import java.io.IOException;

import com.finger.birds.utils.file.ImageUtils;
import com.finger.birds.utils.file.SmallImageSize;
import com.finger.birds.utils.pattern.PatternEnum;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public enum FileUploadTypeEnum {
	选择(-1, null, ""),
	头像(1, "/h", PatternEnum.图片文件.getVal()){

		@Override
		public SmallImageSize getSmallerSize(){
			return new SmallImageSize(200, 200);
		}
		
		@Override
		public String createSmallerName(String fileName){
			return fileName;
		}
		
		@Override
		public String compress(File f, String fileName) throws IOException{//TODO 头像压缩逻辑，
			SmallImageSize s = this.getSmallerSize();
			SmallImageSize size = ImageUtils.getImgSize(f, fileName);
			int width = s.getWidth();
			int height = s.getHeight();
			if (size != null && size.getWidth() > 0 && size.getHeight() > 0) {
				if(size.isSmaller(s)){//如果两边都小于200,则都不做处理
					return fileName;
				}
				double i = (double) size.getWidth() / width;
				double j = (double) size.getHeight() / height;
				//不保留原图，覆盖原图,从中间剪切
				if(i > j){
					double w = size.getWidth()/j;
					int iw = (int)w;
					Thumbnails.of(f).size(iw, width).toFile(fileName);
					Thumbnails.of(f).sourceRegion(Positions.CENTER, width, height).size(width, height).keepAspectRatio(false).toFile(fileName);
				} else if(i < j){
					double h = size.getHeight()/i;
					int ih = (int)h;
					Thumbnails.of(f).size(width, ih).toFile(fileName);
					Thumbnails.of(f).sourceRegion(Positions.CENTER, width, height).size(width, height).keepAspectRatio(false).toFile(fileName);
				} else {
					Thumbnails.of(f).size(width, height).toFile(fileName);
					Thumbnails.of(f).sourceRegion(Positions.CENTER, width, height).size(width, height).keepAspectRatio(false).toFile(fileName);
				}
			}
			return fileName;
		}
		
	},
	项目发布(2, "/p", PatternEnum.图片文件.getVal()){
		public SmallImageSize getSmallerSize(){
			return new SmallImageSize(200, 200);
		}
	},
	项目提炼(3, "/p", PatternEnum.图片文件.getVal()){
		public SmallImageSize getSmallerSize(){
			return new SmallImageSize(200, 200);
		}
	}
	;
	
	private Integer type;
	private String url;
	private String reg;
	
	private FileUploadTypeEnum(Integer type, String url, String reg){
		this.type = type;
		this.url = url;
		this.reg = reg;
	}
	
	public Integer getType() {
		return type;
	}

	public String getUrl() {
		return url;
	}

	public String getReg() {
		return reg;
	}

	public FileUploadTypeEnum getEnumByType(Integer type){
		FileUploadTypeEnum[] fes = FileUploadTypeEnum.values();
		for(int i = 0; i < fes.length; i++){
			if(fes[i].getType().equals(type)){
				return fes[i];
			}
		}
		return null;
	}
	
	public SmallImageSize getSmallerSize(){
		return null;
	}
	
	public String compress(File f, String fileName) throws IOException{
		SmallImageSize size = this.getSmallerSize();
		if(size == null){
			return fileName;
		}
		Thumbnails.of(f).size(size.getWidth(), size.getHeight()).toFile(this.createSmallerName(fileName));
		return this.createSmallerName(fileName);
	}
	
	public String createSmallerName(String fileName){
		return ImageUtils.createSmallerName(fileName);
	}
	
}
