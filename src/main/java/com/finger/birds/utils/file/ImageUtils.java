package com.finger.birds.utils.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.log4j.Logger;

import com.finger.birds.utils.CommConstant;
import com.finger.birds.utils.exception.business.BusinessException;

public class ImageUtils {

	private static Logger log = Logger.getLogger(ImageUtils.class);

	/**
	 * 获取单个图片缩略图
	 * 
	 * @param fileName
	 * @return
	 */
	public static String createSmallerName(String fileName) {
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		;
		String name = fileName
				.substring(0, fileName.length() - suffix.length());
		return name + CommConstant.SMALLER + suffix;
	}

	public static String createDynamicSmallerName(String fileName, int width,
			int height) {
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		String name = fileName
				.substring(0, fileName.length() - suffix.length());
		return name + "_" + width + "_" + height + suffix;
	}

	/**
	 * 获取图片集合中所有图片的缩略图
	 * 
	 * @param imgUrl
	 * @return
	 */
	public static List<String> createSmallerName(List<String> imgUrl) {
		List<String> list = new ArrayList<String>();
		for (String img : imgUrl) {
			if (img == null || "".equals(img))
				continue;
			list.add(createSmallerName(img));
		}
		return list;
	}

	public static SmallImageSize getImgSize(String filename) throws IOException {
		Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName(checkIsImg(filename));
		ImageReader reader = (ImageReader) readers.next();
		ImageInputStream iis = null;
		try {
			File f = new File(filename);
			iis = ImageIO.createImageInputStream(f);
			reader.setInput(iis, true);
			SmallImageSize size = new SmallImageSize(reader.getWidth(0),
					reader.getHeight(0));
			return size;
		} catch (Exception e) {
			// TODO
			log.error("system err 读取图片尺寸失败==============" + filename);
		} finally {
			if (iis != null) {// XXX
				iis.close();
			}
		}
		return null;
	}

	public static SmallImageSize getImgSize(File f, String filename) throws IOException {
		Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName(checkIsImg(filename));
		ImageReader reader = (ImageReader) readers.next();
		ImageInputStream iis = null;
		try {
			iis = ImageIO.createImageInputStream(f);
			reader.setInput(iis, true);
			SmallImageSize size = new SmallImageSize(reader.getWidth(0),
					reader.getHeight(0));
			return size;
		} catch (IOException e) {
			log.error("system err 读取图片尺寸失败==============" + filename);
			e.printStackTrace();
		} finally {
			if (iis != null) {// XXX
				iis.close();
			}
		}
		return null;
	}

	public static String checkIsImg(String fileName) {
		fileName = fileName.toLowerCase();
		String regex = ".*(.jpg|.png|.gif|.jpeg|.bmp)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(fileName);
		if (!matcher.matches()) {
			throw new BusinessException("文件类型不匹配");
		}
		return fileName.substring(fileName.lastIndexOf(".")).replace(".", "");
	}

	/**
	 * 压缩方式A
	 * 
	 * @param size
	 * @param f
	 * @param fileName
	 * @throws IOException
	 */
	// public static void compressA(int width, int height, File f, String
	// filename) throws IOException{
	// SmallImageSize size = getImgSize(f, filename);
	// if(size != null && size.getWidth() > 0 && size.getHeight() > 0){
	// double i = (double)size.getWidth()/width;
	// double j = (double)size.getHeight()/height;
	// }
	// }
}
