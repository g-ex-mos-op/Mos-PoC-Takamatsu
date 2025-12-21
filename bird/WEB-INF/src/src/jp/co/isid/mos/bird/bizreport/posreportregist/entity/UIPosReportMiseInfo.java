package jp.co.isid.mos.bird.bizreport.posreportregist.entity;

import jp.co.isid.mos.bird.bizreport.posreportregist.common.PosReportRegistConstants;

public class UIPosReportMiseInfo {
    
    public static final String TABLE = "BR56RTSH";
    
    public static final String haisSijiDt_COLUMN = "HAIS_SIJI_DT";
    
    public static final String haisSijiSeq_COLUMN = "HAIS_SIJI_SEQ";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String shuDtSta_COLUMN = "SHU_DT_STA";
    
    public static final String shuDtEnd_COLUMN = "SHU_DT_END";
    
    public static final String haisRsltSt_COLUMN = "HAIS_RSLT_ST";
    
    
    /**
     * 配信指示日
     */
    private String haisSijiDt;
    
    /**
     * 配信指示SEQ
     */
    private String haisSijiSeq;
    
    /**
     * 企業コード
     */
    private String companyCd;
    
    /**
     * 店舗コード
     */
    private String miseCd;
    
    /**
     * 店舗名称
     */
    private String miseNameKj;
    
    /**
     * クローズ日
     */
    private String closeDt;
    
    /**
     * 集信開始日
     */
    private String shuDtSta;
    
    /**
     * 集信終了日
     */
    private String shuDtEnd;
    
    /**
     * 配信結果ステータス
     */
    private String haisRsltSt;
    
    /**
     * 新規追加フラグ
     */
    private boolean insertFlg;
    
    /**
     * 削除フラグ
     */
    private boolean delFlg;
    
    /**
     * 重複フラグ
     */
    private boolean dblFlg;
    
    /**
     * 処理ステータス
     */
    private String  procState;
    
    /**
     * SEQ No.
     */
    private String seqNo;
    /**
     * SEQ No(確認画面用)
     */
    private String seqNoConfirm;
    
    
    
    /**
     * 配信指示日を取得します。
     * @return 配信指示日
     */
    public String getHaisSijiDt() {
        return haisSijiDt;
    }
    /**
     * 配信指示日を設定します。
     * @param haisSijiDt 配信指示日
     */
    public void setHaisSijiDt(String haisSijiDt) {
        this.haisSijiDt = haisSijiDt;
    }
    
    /**
     * 配信指示SEQを取得します。
     * @return 配信指示SEQ
     */
    public String getHaisSijiSeq() {
        return haisSijiSeq;
    }
    /**
     * 配信指示SEQを設定します。
     * @param haisSijiSeq 配信指示SEQ
     */
    public void setHaisSijiSeq(String haisSijiSeq) {
        this.haisSijiSeq = haisSijiSeq;
    }
    
    /**
     * 企業コードを取得します。
     * @return 企業コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 企業コードを設定します。
     * @param companyCd 企業コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * 店舗コードを取得します。
     * @return 店舗コード
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * 店舗コードを設定します。
     * @param miseCd 店舗コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    
    /**
     * 店舗名称を取得します。
     * @return 店舗名称
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 店舗名称を設定します。
     * @param miseNameKj 店舗名称
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * クローズ日を取得します。
     * @return クローズ日
     */
    public String getCloseDt() {
        return closeDt;
    }
    /**
     * クローズ日を設定します。
     * @param closeDt クローズ日
     */
    public void setCloseDt(String closeDt) {
        this.closeDt = closeDt;
    }
    
    /**
     * 集信開始日を取得します。
     * @return 集信開始日
     */
    public String getShuDtSta() {
        return shuDtSta;
    }
    /**
     * 集信開始日を設定します。
     * @param shuDtSta 集信開始日
     */
    public void setShuDtSta(String shuDtSta) {
        this.shuDtSta = shuDtSta;
    }
    
    /**
     * 集信終了日を取得します。
     * @return 集信終了日
     */
    public String getShuDtEnd() {
        return shuDtEnd;
    }
    /**
     * 集信終了日を設定します。
     * @param shuDtEnd 集信終了日
     */
    public void setShuDtEnd(String shuDtEnd) {
        this.shuDtEnd = shuDtEnd;
    }
    
    /**
     * 配信結果ステータスを取得します。
     * @return 配信結果ステータス
     */
    public String getHaisRsltSt() {
        return haisRsltSt;
    }
    /**
     * 配信結果ステータスを設定します。
     * @param haisRsltSt 配信結果ステータス
     */
    public void setHaisRsltSt(String haisRsltSt) {
        this.haisRsltSt = haisRsltSt;
    }
    /**
     * 配信結果ステータス名称を取得します。
     * @return 配信結果ステータス名称
     */
    public String getHaisRsltStName () {
    	if(PosReportRegistConstants.HAIS_RSLT_ST_SEIJO.equals(haisRsltSt)) {
    		return PosReportRegistConstants.HAIS_RSLT_ST_STR_SEIJO; //正常
    	}
    	if(PosReportRegistConstants.HAIS_RSLT_ST_NOFILE.equals(haisRsltSt)) {
    		return PosReportRegistConstants.HAIS_RSLT_ST_STR_NOFILE; //ファイルなし
    	}
    	if(PosReportRegistConstants.HAIS_RSLT_ST_TUSHINFURYO.equals(haisRsltSt)) {
    		return PosReportRegistConstants.HAIS_RSLT_ST_STR_TUSHINFURYO; //通信不良
    	}
    	if(PosReportRegistConstants.HAIS_RSLT_ST_MIJISO.equals(haisRsltSt)) {
    		return PosReportRegistConstants.HAIS_RSLT_ST_STR_MIJISSI; //未実装
    	}
    	return "";
    }
    public void setHaisRsltStName(String name) {
    }
    /**
     * 新規追加フラグを取得します。
     * @return 新規追加フラグ
     */
    public boolean isInsertFlg() {
        return insertFlg;
    }
    /**
     * 新規追加フラグを設定します。
     * @param insertFlg 新規追加フラグ
     */
    public void setInsertFlg(boolean insertFlg) {
        this.insertFlg = insertFlg;
    }
    
    /**
     * 削除フラグを取得します。
     * @return 削除フラグ
     */
    public boolean getDelFlg() {
        return delFlg;
    }
    /**
     * 削除フラグを設定します。
     * @param delFlg 削除フラグ
     */
    public void setDelFlg(boolean delFlg) {
        this.delFlg = delFlg;
    }
    
    /**
     * 重複フラグを取得します。
     * @return 重複フラグ
     */
    public boolean getDblFlg() {
        return dblFlg;
    }
    /**
     * 重複フラグを設定します。
     * @param dblFlg 重複フラグ
     */
    public void setDblFlg(boolean dblFlg) {
        this.dblFlg = dblFlg;
    }
    
    /**
     * 処理ステータスを取得します。
     * @return 処理ステータス
     */
    public String  getProcState() {
        return procState;
    }
    /**
     * 処理ステータスを設定します。
     * @param procState 処理ステータス
     */
    public void setProcState(String  procState) {
        this.procState = procState;
    }
    public String getProcStateName() {
    	if(delFlg || PosReportRegistConstants.PRO_STATE_DEL.equals(getProcState())) {
    		return PosReportRegistConstants.PRO_STATE_STR_DEL;
    	}
    	else if (PosReportRegistConstants.PRO_STATE_UPD.equals(getProcState())) {
    		return PosReportRegistConstants.PRO_STATE_STR_UPD;		
    	}
    	else if (PosReportRegistConstants.PRO_STATE_INS.equals(getProcState())) {
    		return PosReportRegistConstants.PRO_STATE_STR_INS;		
    	}
    	return "";
    }
    public void setProcStateName(String name) {
    }

    /**
     * SEQ Noを設定します。
     * @return SEQ No
     */
    public String getSeqNo() {
        return seqNo;
    }
    /**
     * SEQ Noを設定します。
     * @param SEQ No
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }
	public String getSeqNoConfirm() {
		return seqNoConfirm;
	}
	public void setSeqNoConfirm(String seqNoConfirm) {
		this.seqNoConfirm = seqNoConfirm;
	}
    
}
