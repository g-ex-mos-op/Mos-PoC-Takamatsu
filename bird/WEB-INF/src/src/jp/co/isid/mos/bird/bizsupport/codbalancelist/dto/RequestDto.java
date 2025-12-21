package jp.co.isid.mos.bird.bizsupport.codbalancelist.dto;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;

/**
 * リクエスト用DTO
 * @author xkinu
 *
 */
public class RequestDto implements CsvOutputDto {
	private boolean downloadFlg = false;
	private List listDatas = new ArrayList();
	private String msg = "";
	private Exception exceptionObj;
	/**
	 * @return downloadFlg を戻します。
	 */
	public boolean isDownloadFlg() {
		return downloadFlg;
	}
	/**
	 * @param downloadFlg 設定する downloadFlg。
	 */
	public void setDownloadFlg(boolean downloadFlg) {
		this.downloadFlg = downloadFlg;
	}
	/**
	 * @return listDatas を戻します。
	 */
	public List getListDatas() {
		return listDatas;
	}
	/**
	 * @param listDatas 設定する listDatas。
	 */
	public void setListDatas(List listDatas) {
		this.listDatas = listDatas;
	}
	/**
	 * @return msg を戻します。
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg 設定する msg。
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/**
	 * @return exceptionObj を戻します。
	 */
	public Exception getExceptionObj() {
		return exceptionObj;
	}
	/**
	 * @param exceptionObj 設定する exceptionObj。
	 */
	public void setExceptionObj(Exception exceptionObj) {
		this.exceptionObj = exceptionObj;
	}
}
