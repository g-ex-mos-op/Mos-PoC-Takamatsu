package jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.entity;

public class TrnMosChikenDet {
    
    public static final String TABLE = "BT71CRSD";
    
    public static final String ckanriNo_COLUMN = "CKANRI_NO";
    
    public static final String companCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String menuCd_COLUMN = "MENU_CD";
    
    public static final String reserveAmt_COLUMN = "RESERVE_AMT";
    
    public static final String seqNo_COLUMN = "SEQ_NO";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    public static final String menuNameKJ_COLUMN = "MENU_NAME_KJ";
    
    public static final String totalMoney_COLUMN = "TOTAL_MONEY";
    /** マスタ登録メニュー判断フラグ */
    public static final String masterFlg_COLUMN = "MASTER_FLG";
    
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
     * メニューコード
     */
    private String menuCd;
    
    /**
     * 予約数
     */
    private String reserveAmt;
    
    /**
     * シーケンス番号
     */
    private int seqNo;
    
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
     * メニュー名称
     */
    private String menuNameKj;
    /**
     * 合計金額
     */
    private String totalMoney;
    /**
     * マスタ登録メニュー判断フラグ
     */
    private String masterFlg;
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
    public void setCompanCd(String companyCd) {
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
     * メニューコードを取得します。
     * @return メニューコード
     */
    public String getMenuCd() {
        return menuCd;
    }
    /**
     * メニューコードを設定します。
     * @param menuCd メニューコード
     */
    public void setMenuCd(String menuCd) {
        this.menuCd = menuCd;
    }
    
    /**
     * 予約数を取得します。
     * @return 予約数
     */
    public String getReserverAmt() {
        return reserveAmt;
    }
    /**
     * 予約数を設定します。
     * @param reserveAmt 予約数
     */
    public void setReserveAmt(String reserveAmt) {
        this.reserveAmt = reserveAmt;
    }
    
    /**
     * シーケンス番号を取得します。
     * @return シーケンス番号
     */
    public int getSeqNo() {
        return seqNo;
    }
    /**
     * シーケンス番号を設定します。
     * @param seqNo シーケンス番号
     */
    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
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
     * メニュー名称を取得します。
     * @return メニュー名称
     */
    public String getMenuNameKj() {
        return menuNameKj;
    }
    /**
     * メニュー名称を設定します。
     * @param menuNameKj メニュー名称
     */
    public void setMenuNameKj(String menuNameKj) {
        this.menuNameKj = menuNameKj;
    }
    /**
     * 合計金額を取得します
     * @return totalMoney合計金額
     */
    public String getTotalMoney() {
        return totalMoney;
    }
    /**
     * 合計金額を設定します
     * @param totalMoney 合計金額
     */
    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }
	/**
	 * @return masterFlg を戻します。
	 */
	public String getMasterFlg() {
		return masterFlg;
	}
	/**
	 * @param masterFlg 設定する masterFlg。
	 */
	public void setMasterFlg(String masterFlg) {
		this.masterFlg = masterFlg;
	}
    
}
