package jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.entity;

import java.math.BigDecimal;

public class TrnReserveInfo {
    
    public static final String TABLE = "BT70CRSV";
    
    public static final String ckanriNo_COLUMN = "CKANRI_NO";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String seqNo_COLUMN = "SEQ_NO";
    
    public static final String reserveDt_COLUMN = "RESERVE_DT";
    
    public static final String accountDt_COLUMN = "ACCOUNT_DT";
    
    public static final String reserveHh_COLUMN = "RESERVE_HH";
    
    public static final String reserveMm_COLUMN = "RESERVE_MM";
    
    public static final String remark_COLUMN = "REMARK";
    
    public static final String paymentFlg_COLUMN = "PAYMENT_FLG";
    /** プレミアムお渡し済フラグ */
    public static final String premiumFlg_COLUMN = "PREMIUM_FLG";
    
    public static final String memo_COLUMN = "MEMO";
    
    public static final String cancelFlg_COLUMN = "CANCEL_FLG";
    
    public static final String cancelDt_COLUMN = "CANCEL_DT";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    /** 代金 */
    public static final String totalMoney_COLUMN = "TOTAL_MONEY";
    
    /**
     * 管理番号
     */
    private String ckanriNo;
    
    /**
     * 企業コード
     */
    private String companyCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * シーケンスNO
     */
    private String seqNo;
    
    /**
     * お渡し日
     */
    private String reserveDt;
    
    /**
     * ご予約日
     */
    private String accountDt;
    
    /**
     * お渡し予定時刻（時）
     */
    private String reserveHh;
    
    /**
     * お渡し予定時刻（分）
     */
    private String reserveMm;
    
    /**
     * 備考
     */
    private String remark;
    
    /**
     * 代済フラグ
     */
    private String paymentFlg;
    
    /**
     * メモ
     */
    private String memo;
    
    /**
     * キャンセルフラグ
     */
    private String cancelFlg;
    
    /**
     * キャンセル日
     */
    private String cancelDt;
    
    /**
     * 登録ユーザー
     */
    private String firstUser;
    
    /**
     * 登録プログラム
     */
    private String firstPgm;
    
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private String firstTmsp;
    
    /**
     * 修正ユーザー
     */
    private String lastUser;
    
    /**
     * 修正プログラム
     */
    private String lastPgm;
    
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private String lastTmsp;
    /**
     * プレミアムお渡し済フラグ
     */
    private String premiumFlg;
    
    private BigDecimal totalMoney;
    /**
     * 管理番号を取得します。
     * @return 管理番号
     */
    public String getCkanriNo() {
        return ckanriNo;
    }
    /**
     * 管理番号を設定します。
     * @param ckanriNo 管理番号
     */
    public void setCkanriNo(String ckanriNo) {
        this.ckanriNo = ckanriNo;
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
     * シーケンスNOを取得します。
     * @return シーケンスNO
     */
    public String getSeqNo() {
        return seqNo;
    }
    /**
     * シーケンスNOを設定します。
     * @param seqNo シーケンスNO
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }
    
    /**
     * お渡し日を取得します。
     * @return お渡し日
     */
    public String getReserveDt() {
        return reserveDt;
    }
    /**
     * お渡し日を設定します。
     * @param reserveDt お渡し日
     */
    public void setReserveDt(String reserveDt) {
        this.reserveDt = reserveDt;
    }
    
    /**
     * ご予約日を取得します。
     * @return ご予約日
     */
    public String getAccountDt() {
        return accountDt;
    }
    /**
     * ご予約日を設定します。
     * @param accountDt ご予約日
     */
    public void setAccountDt(String accountDt) {
        this.accountDt = accountDt;
    }
    
    /**
     * お渡し予定時刻（時）を取得します。
     * @return お渡し予定時刻（時）
     */
    public String getReserveHh() {
        return reserveHh;
    }
    /**
     * お渡し予定時刻（時）を設定します。
     * @param reserveHh お渡し予定時刻（時）
     */
    public void setReserveHh(String reserveHh) {
        this.reserveHh = reserveHh;
    }
    
    /**
     * お渡し予定時刻（分）を取得します。
     * @return お渡し予定時刻（分）
     */
    public String getReserveMm() {
        return reserveMm;
    }
    /**
     * お渡し予定時刻（分）を設定します。
     * @param reserveMm お渡し予定時刻（分）
     */
    public void setReserveMm(String reserveMm) {
        this.reserveMm = reserveMm;
    }
    
    /**
     * 備考を取得します。
     * @return 備考
     */
    public String getRemark() {
        return remark;
    }
    /**
     * 備考を設定します。
     * @param remark 備考
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    /**
     * 代済フラグを取得します。
     * @return 代済フラグ
     */
    public String getPaymentFlg() {
        return paymentFlg;
    }
    /**
     * 代済フラグを設定します。
     * @param paymentFlg 代済フラグ
     */
    public void setPaymentFlg(String paymentFlg) {
        this.paymentFlg = paymentFlg;
    }
    
    /**
     * メモを取得します。
     * @return メモ
     */
    public String getMemo() {
        return memo;
    }
    /**
     * メモを設定します。
     * @param memo メモ
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }
    
    /**
     * キャンセルフラグを取得します。
     * @return キャンセルフラグ
     */
    public String getCancelFlg() {
        return cancelFlg;
    }
    /**
     * キャンセルフラグを設定します。
     * @param cancelFlg キャンセルフラグ
     */
    public void setCancelFlg(String cancelFlg) {
        this.cancelFlg = cancelFlg;
    }
    
    /**
     * キャンセル日を取得します。
     * @return キャンセル日
     */
    public String getCancelDt() {
        return cancelDt;
    }
    /**
     * キャンセル日を設定します。
     * @param cancelDt キャンセル日
     */
    public void setCancelDt(String cancelDt) {
        this.cancelDt = cancelDt;
    }
    
    /**
     * 登録ユーザーを取得します。
     * @return 登録ユーザー
     */
    public String getFirstUser() {
        return firstUser;
    }
    /**
     * 登録ユーザーを設定します。
     * @param firstUser 登録ユーザー
     */
    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
    }
    
    /**
     * 登録プログラムを取得します。
     * @return 登録プログラム
     */
    public String getFirstPgm() {
        return firstPgm;
    }
    /**
     * 登録プログラムを設定します。
     * @param firstPgm 登録プログラム
     */
    public void setFirstPgm(String firstPgm) {
        this.firstPgm = firstPgm;
    }
    
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟを取得します。
     * @return 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public String getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟを設定します。
     * @param firstTmsp 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public void setFirstTmsp(String firstTmsp) {
        this.firstTmsp = firstTmsp;
    }
    
    /**
     * 修正ユーザーを取得します。
     * @return 修正ユーザー
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * 修正ユーザーを設定します。
     * @param lastUser 修正ユーザー
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 修正プログラムを取得します。
     * @return 修正プログラム
     */
    public String getLastPgm() {
        return lastPgm;
    }
    /**
     * 修正プログラムを設定します。
     * @param lastPgm 修正プログラム
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟを取得します。
     * @return 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public String getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟを設定します。
     * @param lastTmsp 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public void setLastTmsp(String lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
	/**
	 * プレミアムお渡し済フラグを取得する。
	 * @return premiumFlg を戻します。
	 */
	public String getPremiumFlg() {
		return premiumFlg;
	}
	/**
	 * プレミアムお渡し済フラグを設定する。
	 * @param premiumFlg 設定する premiumFlg。
	 */
	public void setPremiumFlg(String premiumFlg) {
		this.premiumFlg = premiumFlg;
	}
	/**
	 * @return totalMoney を戻します。
	 */
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	/**
	 * @param totalMoney 設定する totalMoney。
	 */
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
    
}
