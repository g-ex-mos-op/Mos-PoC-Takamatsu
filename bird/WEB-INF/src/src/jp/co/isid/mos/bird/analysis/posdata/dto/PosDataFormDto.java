package jp.co.isid.mos.bird.analysis.posdata.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;

/**
 * 集信データ提供 画面DTO
 *
 * @author narita
 */
public class PosDataFormDto implements DownloadDto {
	   
    /**
     * ログインユーザ情報
     */
    private BirdUserInfo birdUserInfo;
    
    /**
     * オーナーコード
     */
    private Map onerId = new HashMap();
    
    /**
     * 集信データ日
     */
    private Map posDataDt = new HashMap();
    
    /**
     * 検索集信データリスト
     */
    private List posDataList;
    
    /**
     * 集信データ日リスト
     */
    private List posDataDtList;

    /**
     * オーナーコード（検索内容）
     */
    private String searchOnerId;
    
    /**
     * 集信データ日（検索内容）
     */
    private String searchPosDataDt;
        
    /**
     * 集信データダウンロード成功フラグ
     */
    private boolean downloadFlag;
    
    /**
     * オーナーコード入力チェックフラグ
     */
    private boolean onerIdFlg;
       
    /**
     * 集信データファイル名
     */
    private String fileName;
    
    /**
     * ＰＤＦファイル名
     */
    private String pdfFileName;  
    
    
//	ウインドウ管理
	/**
	 * ウインドウID
	 */
	private int windowId;

	/**
	 * MAXウインドウID
	 */
	private int maxWindowId;
	
	
    
	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}

	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}

	public String getOnerId() {
		return (String) onerId.get(new Integer(getWindowId()));
		//return onerId;
	}

	public void setOnerId(String onerId) {
		this.onerId.put(new Integer(getWindowId()), onerId);
		//this.onerId = onerId;
	}

	public String getPosDataDt() {
		return (String) posDataDt.get(new Integer(getWindowId()));
		//return posDataDt;
	}

	public void setPosDataDt(String posDataDt) {
		this.posDataDt.put(new Integer(getWindowId()), posDataDt);
		//this.posDataDt = posDataDt;
	}

	public List getPosDataDtList() {
		return posDataDtList;
	}

	public void setPosDataDtList(List posDataDtList) {
		this.posDataDtList = posDataDtList;
	}

	public String getSearchOnerId() {
		return searchOnerId;
	}

	public void setSearchOnerId(String searchOnerId) {
		this.searchOnerId = searchOnerId;
	}

	public String getSearchPosDataDt() {
		return searchPosDataDt;
	}

	public void setSearchPosDataDt(String searchPosDataDt) {
		this.searchPosDataDt = searchPosDataDt;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPdfFileName() {
		return pdfFileName;
	}

	public void setPdfFileName(String pdfFileName) {
		this.pdfFileName = pdfFileName;
	}

	public boolean isDownloadFlag() {
		return downloadFlag;
	}

	public void setDownloadFlag(boolean downloadFlag) {
		this.downloadFlag = downloadFlag;
	}

	public List getPosDataList() {
		return posDataList;
	}

	public void setPosDataList(List posDataList) {
		this.posDataList = posDataList;
	}

	public boolean isOnerIdFlg() {
		return onerIdFlg;
	}

	public void setOnerIdFlg(boolean onerIdFlg) {
		this.onerIdFlg = onerIdFlg;
	}

	public int getMaxWindowId() {
		return maxWindowId;
	}

	public void setMaxWindowId(int maxWindowId) {
		this.maxWindowId = maxWindowId;
	}

	public int getWindowId() {
		return windowId;
	}

	public void setWindowId(int windowId) {
		this.windowId = windowId;
	}
	
	/**
	 * ウィンドウIDを生成する
	 */ 
	public void updateWindowId() {
		this.setWindowId(createWindowId());
	}
	/**
	 * MAXウィンドウIDを生成する
     * @return int  MAXウィンドウID
	 */ 
	public int createWindowId() {
		int newWindowId = getMaxWindowId() + 1;
		setMaxWindowId(newWindowId);
		return newWindowId;
	}
}
