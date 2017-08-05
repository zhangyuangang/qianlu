package com.finger.birds.utils.rslt.type;

import java.util.Collection;

import com.finger.birds.utils.param.Param;


/**
 * API返回值类型
 * 
 * @author danny
 *
 * @param <T>
 */
public class APIResult<T> extends Result<T> {

	private Param param;
	private int dataLen;
	
	public Param getParam() {
		return param;
	}

	public void setParam(Param param) {
		this.param = param;
	}

	public int getDataLen() {
		return dataLen;
	}

	public void setDataLen(int dataLen) {
		this.dataLen = dataLen;
	}
	
	@Override
	public void setData(T data) {
		super.data = data;
		this.initDataLen(data) ;
	}
	
	@SuppressWarnings("rawtypes")
	private void initDataLen(T data){
		if(data instanceof Collection){
			this.dataLen=((Collection) data).size();
		}else{
			if(data==null){
				this.dataLen=0;
			}else{
				this.dataLen=1;
			}
		}
	}
	
	
}
