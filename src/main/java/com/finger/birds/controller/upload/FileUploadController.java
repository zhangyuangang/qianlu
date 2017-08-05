package com.finger.birds.controller.upload;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.finger.birds.param.upload.FileUploadTypeEnum;
import com.finger.birds.param.upload.UploadParam;
import com.finger.birds.ucontroller.BaseController;
import com.finger.birds.ucontroller.ajax.AjaxUtils;
import com.finger.birds.ucontroller.exception.param.ParamException;
import com.finger.birds.utils.CommConstant;
import com.finger.birds.utils.date.DateUtils;
import com.finger.birds.utils.exception.business.BusinessException;
import com.finger.birds.utils.rslt.type.Result;

@Controller
@RequestMapping("/")
public class FileUploadController extends BaseController<FileUploadController>{
	
	@RequestMapping("upload.html")
	public void appUpload(@Valid UploadParam param, BindingResult bindingResult, @RequestParam CommonsMultipartFile file, HttpServletResponse response) throws IllegalStateException, IOException {
		String fileName = "";
		if (file != null && !file.isEmpty()) {
			fileName = this.getFileNameByUpType(param.getType(),file.getOriginalFilename(), super.getAdminUserId());
			File f = new File(fileName);
			file.transferTo(f);
			FileUploadTypeEnum typeEnum = FileUploadTypeEnum.选择.getEnumByType(param.getType());
			
			//Smaller
			fileName = typeEnum.compress(f, fileName);
		} else {
			throw new BusinessException("文件不能为空");
		}
		Result<String> result = new Result<String>();
		result.setData(fileName.replace(CommConstant.RESOURCE_URL, ""));
		AjaxUtils.writeJSONP(response, result);
	}
	
	@RequestMapping("uploadImg.html")
	public void appUploadImg(@Valid UploadParam param, BindingResult bindingResult, @RequestParam CommonsMultipartFile file, HttpServletResponse response) throws IllegalStateException, IOException {
		String fileName = "";
		if (file != null && !file.isEmpty()) {
			fileName = this.getFileNameByUpType(param.getType(),file.getOriginalFilename(), super.getUserId());
			File f = new File(fileName);
			file.transferTo(f);
			FileUploadTypeEnum typeEnum = FileUploadTypeEnum.选择.getEnumByType(param.getType());
			
			//Smaller
			fileName = typeEnum.compress(f, fileName);
		} else {
			throw new BusinessException("文件不能为空");
		}
		Result<String> result = new Result<String>();
		result.setData(fileName.replace(CommConstant.RESOURCE_URL, ""));
		AjaxUtils.writeJSONP(response, result);
	}

	private String getFileNameByUpType(Integer type, String fileName, Long userId) {
		FileUploadTypeEnum typeEnum = FileUploadTypeEnum.选择.getEnumByType(type);
		String path = CommConstant.RESOURCE_URL + "/" + DateUtils.getTodayDayWithYear() + typeEnum.getUrl();//TODO
		this.isExist(path);
		String suffix = checkFileIsRight(fileName, typeEnum);
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			return path  + "/" + type + CommConstant.STR_CONNECTOR + new Date().getTime()+ CommConstant.STR_CONNECTOR + userId + suffix;
		}
		String name = path  + "/" + type+ CommConstant.STR_CONNECTOR + new Date().getTime()+ CommConstant.STR_CONNECTOR + userId + suffix;
		return name;
	}
	
	private void isExist(String path) {
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}
	
	private String checkFileIsRight(String fileName, FileUploadTypeEnum fileType) {
		fileName = fileName.toLowerCase();
		Pattern pattern = Pattern.compile(fileType.getReg());
		Matcher matcher = pattern.matcher(fileName);
		if (!matcher.matches()) {
//			if(fileType.getReg().equals(PatternEnum.音频文件.getVal())){
//				return ".mp3";
//			}
//			return ".png";
			throw new ParamException("文件类型错误");
		}
		return fileName.substring(fileName.lastIndexOf("."));
	}
}
