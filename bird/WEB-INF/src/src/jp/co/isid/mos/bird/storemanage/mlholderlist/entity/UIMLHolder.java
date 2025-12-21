package jp.co.isid.mos.bird.storemanage.mlholderlist.entity;
/**
 * 
 * 作成日:2009/09/03
 * @author xkinu
 * 更新日:2009/09/03 xkinu 一括ダウンロードCSV項目へ"スタッフID"の追加対応
 */
public class UIMLHolder {
    
    public static final String TABLE = "BT26UPJK";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String staffId_COLUMN = "STAFF_ID";
    
    public static final String staffLNameKj_COLUMN = "STAFF_L_NAME_KJ";
    
    public static final String staffFNameKj_COLUMN = "STAFF_F_NAME_KJ";
    
    public static final String licenseKbn_COLUMN = "LICENSE_KBN";
    
    public static final String licenseNo_COLUMN = "LICENSE_NO";
    
    public static final String licenseDt_COLUMN = "LICENSE_DT";
    
    public static final String licenseValidDt_COLUMN = "LICENSE_VALID_DT";
    
    public static final String renew1Status_COLUMN = "RENEW1_STATUS";
    
    public static final String renew2Status_COLUMN = "RENEW2_STATUS";
    
    public static final String renew3Status_COLUMN = "RENEW3_STATUS";
    
    public static final String situationKbn_COLUMN = "SITUATION_KBN";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String sibuNameKj_COLUMN = "SIBU_NAME_KJ";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    /** 更新研修状況：未修了 0 */
    public static final String STATUS_MISYURYO = "0";
    /** 更新研修状況：修了 1 */
    public static final String STATUS_SYURYO = "1";
    /** 更新研修状況：予定 2 */
    public static final String STATUS_YOTEI = "2";
    /** 活動状況：活動中 0 */
    public static final String SITUATION_ON = "0";
    /** 活動状況：休職中 1 */
    public static final String SITUATION_SLEEP = "1";
    /** 活動状況：退職 2 */
    public static final String SITUATION_OFF = "2";
    
    /**
     * 店名称（漢字）
     */
    private String miseNameKj;
    
    /**
     * スタッフID
     */
    private String staffId;
    
    /**
     * スタッフ氏　（漢字）
     */
    private String staffLNameKj;
    
    /**
     * スタッフ名　（漢字）
     */
    private String staffFNameKj;
    
    /**
     * ライセンス種別
     */
    private String licenseKbn;
    
    /**
     * ライセンス認定番号
     */
    private String licenseNo;
    
    /**
     * ライセンス取得年月
     */
    private String licenseDt;
    
    /**
     * ライセンス有効期限日
     */
    private String licenseValidDt;
    
    /**
     * 更新研修1状況
     */
    private String renew1Status;
    
    /**
     * 更新研修2状況
     */
    private String renew2Status;
    
    /**
     * 更新研修3状況
     */
    private String renew3Status;
    
    /**
     * 活動状況区分
     */
    private String situationKbn;
    
    /**
     * 支部取込コード
     */
    private String sibuCd;
    
    /**
     * 支部取込名称
     */
    private String sibuNameKj;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * オーナー名称（漢字）
     */
    private String onerNameKj;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    
    /**
     * 店名称（漢字）を取得します。
     * @return 店名称（漢字）
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 店名称（漢字）を設定します。
     * @param miseNameKj 店名称（漢字）
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * スタッフ氏　（漢字）を取得します。
     * @return スタッフ氏　（漢字）
     */
    public String getStaffLNameKj() {
        return staffLNameKj;
    }
    /**
     * スタッフ氏　（漢字）を設定します。
     * @param staffLNameKj スタッフ氏　（漢字）
     */
    public void setStaffLNameKj(String staffLNameKj) {
        this.staffLNameKj = staffLNameKj;
    }
    
    /**
     * スタッフ名　（漢字）を取得します。
     * @return スタッフ名　（漢字）
     */
    public String getStaffFNameKj() {
        return staffFNameKj;
    }
    /**
     * スタッフ名　（漢字）を設定します。
     * @param staffFNameKj スタッフ名　（漢字）
     */
    public void setStaffFNameKj(String staffFNameKj) {
        this.staffFNameKj = staffFNameKj;
    }
    
    /**
     * ライセンス種別を取得します。
     * @return ライセンス種別
     */
    public String getLicenseKbn() {
        return licenseKbn;
    }
    /**
     * ライセンス種別を設定します。
     * @param licenseKbn ライセンス種別
     */
    public void setLicenseKbn(String licenseKbn) {
        this.licenseKbn = licenseKbn;
    }
    
    /**
     * ライセンス認定番号を取得します。
     * @return ライセンス認定番号
     */
    public String getLicenseNo() {
        return licenseNo;
    }
    /**
     * ライセンス認定番号を設定します。
     * @param licenseNo ライセンス認定番号
     */
    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }
    
    /**
     * ライセンス取得年月を取得します。
     * @return ライセンス取得年月
     */
    public String getLicenseDt() {
        return licenseDt;
    }
    /**
     * ライセンス取得年月を設定します。
     * @param licenseDt ライセンス取得年月
     */
    public void setLicenseDt(String licenseDt) {
        this.licenseDt = licenseDt;
    }
    
    /**
     * ライセンス有効期限日を取得します。
     * @return ライセンス有効期限日
     */
    public String getLicenseValidDt() {
        return licenseValidDt;
    }
    /**
     * ライセンス有効期限日を設定します。
     * @param licenseValidDt ライセンス有効期限日
     */
    public void setLicenseValidDt(String licenseValidDt) {
        this.licenseValidDt = licenseValidDt;
    }
    
    /**
     * 更新研修1状況を取得します。
     * @return 更新研修1状況
     */
    public String getRenew1Status() {
        return renew1Status;
    }
    /**
     * 更新研修1状況を設定します。
     * @param renew1Status 更新研修1状況
     */
    public void setRenew1Status(String renew1Status) {
        this.renew1Status = renew1Status;
    }
    
    /**
     * 更新研修2状況を取得します。
     * @return 更新研修2状況
     */
    public String getRenew2Status() {
        return renew2Status;
    }
    /**
     * 更新研修2状況を設定します。
     * @param renew2Status 更新研修2状況
     */
    public void setRenew2Status(String renew2Status) {
        this.renew2Status = renew2Status;
    }
    
    /**
     * 更新研修3状況を取得します。
     * @return 更新研修3状況
     */
    public String getRenew3Status() {
        return renew3Status;
    }
    /**
     * 更新研修3状況を設定します。
     * @param renew3Status 更新研修3状況
     */
    public void setRenew3Status(String renew3Status) {
        this.renew3Status = renew3Status;
    }
    
    /**
     * 活動状況区分を取得します。
     * @return 活動状況区分
     */
    public String getSituationKbn() {
        return situationKbn;
    }
    /**
     * 活動状況区分を設定します。
     * @param situationKbn 活動状況区分
     */
    public void setSituationKbn(String situationKbn) {
        this.situationKbn = situationKbn;
    }
    
    /**
     * 支部取込コードを取得します。
     * @return 支部取込コード
     */
    public String getSibuCd() {
        return sibuCd;
    }
    /**
     * 支部取込コードを設定します。
     * @param sibuCd 支部取込コード
     */
    public void setSibuCd(String sibuCd) {
        this.sibuCd = sibuCd;
    }
    
    /**
     * 支部取込名称を取得します。
     * @return 支部取込名称
     */
    public String getSibuNameKj() {
        return sibuNameKj;
    }
    /**
     * 支部取込名称を設定します。
     * @param sibuNameKj 支部取込名称
     */
    public void setSibuNameKj(String sibuNameKj) {
        this.sibuNameKj = sibuNameKj;
    }
    
    /**
     * オーナーコードを取得します。
     * @return オーナーコード
     */
    public String getOnerCd() {
        return onerCd;
    }
    /**
     * オーナーコードを設定します。
     * @param onerCd オーナーコード
     */
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }
    
    /**
     * オーナー名称（漢字）を取得します。
     * @return オーナー名称（漢字）
     */
    public String getOnerNameKj() {
        return onerNameKj;
    }
    /**
     * オーナー名称（漢字）を設定します。
     * @param onerNameKj オーナー名称（漢字）
     */
    public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj = onerNameKj;
    }
    
    /**
     * 店コードを取得します。
     * @return 店コード
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * 店コードを設定します。
     * @param miseCd 店コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    /**
     * 更新研修1状況を取得します。
     * @return 更新研修1状況
     */
    public String getRenew1StatusMoji() {
        return getStatusMoji(renew1Status);
    }
    /**
     * 更新研修2状況を取得します。
     * @return 更新研修2状況
     */
    public String getRenew2StatusMoji() {
        return getStatusMoji(renew2Status);
    }
    /**
     * 更新研修3状況を取得します。
     * @return 更新研修3状況
     */
    public String getRenew3StatusMoji() {
        return getStatusMoji(renew3Status);
    }
//    /**
//     * 更新研修4状況を取得します。
//     * @return 更新研修4状況
//     */
//    public String getRenew4StatusMoji() {
//        return getStatusMoji(renew4Status);
//    }
//    /**
//     * 更新研修5状況を取得します。
//     * @return 更新研修5状況
//     */
//    public String getRenew5StatusMoji() {
//        return getStatusMoji(renew5Status);
//    }
    /**
     * 更新研修状況の名称を取得します。
     * @return 更新研修状況名称
     */
    public String getStatusMoji(String status) {
    	if(STATUS_MISYURYO.equals(status)) {
    		return "未修了";
    	}
    	else if(STATUS_SYURYO.equals(status)) {
    		return "修了";
    	}
    	else if(STATUS_YOTEI.equals(status)) {
    		return "予定";
    	}
    	return "";
    }
    /**
     * 活動状況区分を取得します。
     * @return 活動状況区分
     */
    public String getSituationKbnMoji() {
    	if(SITUATION_ON.equals(situationKbn)) {
    		return "活動中";
    	}
    	else if(SITUATION_SLEEP.equals(situationKbn)) {
    		return "休職中";
    	}
    	else if(SITUATION_OFF.equals(situationKbn)) {
    		return "退職";
    	}
    	return "";
    }
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
    
}
