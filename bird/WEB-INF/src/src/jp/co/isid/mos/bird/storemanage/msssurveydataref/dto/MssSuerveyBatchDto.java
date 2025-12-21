/*
 * 作成日: 2006/04/19
 */
package jp.co.isid.mos.bird.storemanage.msssurveydataref.dto;

import jp.co.isid.mos.bird.framework.dto.BatchExecuteDto;
import jp.co.isid.mos.bird.storemanage.msssurveydataref.entity.UIMssBatch;

/**
 * @author lee
 */
public class MssSuerveyBatchDto implements BatchExecuteDto {
    
	/* VIEW_ID */
    public final String VIEW_ID				= "BSM012";
    public final String upload_VIEW_ID		= "BSM012V02";
//    public final String condition_VIEW_ID		= "BSM007V01";
    public final String result_VIEW_ID			= "BSM004V01";
    public final String onerSearch_VIEW_ID		= "BCO006V01";
    public final String miseSearch_VIEW_ID		= "BCO008V01";
    /* ユーザーID */
    private String userId;
    
    /* 会社コード */
    private String companyCd;
    
    private String successMsg;

    private String errMsg;

    private String errTitle;
    /* 選択Flage 0:ファイル転送　1:支部洗え替え */
    private int selectFlg; 
        
    /*処理区分 0:取り込み処理 1:支部洗え替え*/
    private String shoriKbn; 
    
    /*対象年度*/
    private String nendo;

    /*対象回数*/
    private String kai;
    
    /*エラーFlg*/
    private String shoriFlg;

    /*バッチ起動中FLG*/
    private String batchDur;

    
    private UIMssBatch UIMssBatchReg;

    private UIMssBatch UIMssBatchSibu;

    /**/
    private String[] executeJobName;
    
    /**/
    private String pageKey;
	public int getSelectFlg() {
		return selectFlg;
	}
	/**
	 * @param selectFlg selectFlg を設定。 
	 */
	public void setSelectFlg(int selectFlg) {
		this.selectFlg = selectFlg;
	}
	/**
	 * @return companyCd を戻します。
	 */
	public String getCompanyCd() {
		return companyCd;
	}
	/**
	 * @param companyCd companyCd を設定。
	 */
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}
	/**
	 * @return condition_VIEW_ID を戻します。
	 */
	public String getUpload_VIEW_ID() {
		return upload_VIEW_ID;
	}
    /**
     * shoriKbnを戻す
     * @return shoriKbn
     */
    public String getShoriKbn() {
        return shoriKbn;
    }
    /**
     * shoriKbnをセット
     * @param shoriKbn
     */
    public void setShoriKbn(String shoriKbn) {
        this.shoriKbn = shoriKbn;
    }
    /**
     * nendoを戻す
     * @return nendo
     */
    public String getNendo() {
        return nendo;
    }
    /**
     * nendoをセット
     * @param nendo
     */
    public void setNendo(String nendo) {
        this.nendo = nendo;
    }
    /**
     * kaiを戻す
     * @return kai
     */
    public String getKai() {
        return kai;
    }
    /**
     * kaiをセット
     * @param kai
     */
    public void setKai(String kai) {
        this.kai = kai;
    }
     public String[] getExecuteJobName() {
        return executeJobName;
    }
    public void setExecuteJobName(String[] executeJobName) {
        this.executeJobName = executeJobName;
        
    }
    public String getPageKey() {
        return pageKey;
    }
    public void setPageKey(String pageKey) {
        this.pageKey = pageKey;
        
    }
    public String getShoriFlg() {
        return shoriFlg;
    }
    public void setShoriFlg(String shoriFlg) {
        this.shoriFlg = shoriFlg;
    }
    public String getSuccessMsg() {
        return successMsg;
    }
    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
    }
	public void clear(){
        setShoriFlg("0");
        setSuccessMsg(null);
    }
    public UIMssBatch getUIMssBatchReg() {
        return UIMssBatchReg;
    }
    public void setUIMssBatchReg(UIMssBatch UIMssBatchReg) {
        this.UIMssBatchReg = UIMssBatchReg;
    }
    public UIMssBatch getUIMssBatchSibu() {
        return UIMssBatchSibu;
    }
    public void setUIMssBatchSibu(UIMssBatch UIMssBatchSibu) {
        this.UIMssBatchSibu = UIMssBatchSibu;
    }
    public String getErrMsg() {
        return errMsg;
    }
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
    public String getErrTitle() {
        return errTitle;
    }
    public void setErrTitle(String errTitle) {
        this.errTitle = errTitle;
    }
    public String getBatchDur() {
        return batchDur;
    }
    public void setBatchDur(String batchDur) {
        this.batchDur = batchDur;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
