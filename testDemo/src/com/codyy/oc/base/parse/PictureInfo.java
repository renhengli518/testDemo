package com.codyy.oc.base.parse;

/**
 * @author xierongbing
 * @date 2015年8月21日 上午9:18:56 
 * @description 
 * 用于保存图片信息
 */

public class PictureInfo {
	private String fileName;
	
	private byte[] data;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}
