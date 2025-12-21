package jp.co.isid.mos.bird.bizsupport.moschickenreservation.entity;

import java.sql.Timestamp;


public class TrnMosChikenDet {
    
    public static final String TABLE = "BT71CRSD";
    
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";

    public static final String ckanriNo_COLUMN = "CKANRI_NO";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String menuCd_COLUMN = "MENU_CD";

    public static final String seqNo_COLUMN = "SEQ_NO";

    public static final String reserveAmt_COLUMN = "RESERVE_AMT";
        
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    public static final String menuNameKj_COLUMN = "MENU_NAME_KJ";

    public static final String menuGroup_COLUMN = "MENU_GROUP";

    public static final String sunGroup_COLUMN = "SUM_GROUP";
    
    public static final String reserveDt_COLUMN = "RESERVE_DT";
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
     * シーケンス番号
     */
    private int seqNo;

    /**
     * 予約数
     */
    private int reserveAmt;
    /**
     * 編集用予約数
     */
    private String strReserveAmt;
    
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
    private Timestamp firstTmsp;
    
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
    private Timestamp lastTmsp;
    
    /**
     * メニュー名称
     */
    private String menuNameKj;
    /**
     * 商品グループ
     */
    private String menuGroup;
    /**
     * 集計グループ
     */
    private String sumGroup;
    
    /**
     * お渡し日
     */
    private String reserveDt;
    
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
     * @param companCd 企業コード
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
     * シーケンス番号を取得します
     * @return seqNo
     */
    public int getSeqNo() {
        return seqNo;
    }
    /**
     * シーケンス番号を設定します
     * @param シーケンス番号 seqNo
     */
    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }

    /**
     * 予約数を取得します。
     * @return 予約数
     */
    public int getReserveAmt() {
        return reserveAmt;
    }
    /**
     * 予約数を設定します。
     * @param reserverAmt 予約数
     */
    public void setReserveAmt(int reserveAmt) {
        this.reserveAmt = reserveAmt;
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
    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟを設定します。
     * @param firstTmsp 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public void setFirstTmsp(Timestamp firstTmsp) {
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
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟを設定します。
     * @param lastTmsp 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
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
     * 予約数を取得します。
     * @param strReserveAmt 予約数
     */
    public String getStrReserveAmt() {
        return strReserveAmt;
    }
    /**
     * 予約数を設定します。
     * @param strReserveAmt メニュー名称
     */
    public void setStrReserveAmt(String strReserveAmt) {
        this.strReserveAmt = strReserveAmt;
    }
    /**
     * メニューグループを取得します
     * @return menuGroup　メニューグループ
     */
    public String getMenuGroup() {
        return menuGroup;
    }
    /**
     * メニューグループを設定します。
     * @param menuGroup
     */
    public void setMenuGroup(String menuGroup) {
        this.menuGroup = menuGroup;
    }
    /**
     * 集計グループを取得します
     * @return sumGroup 集計グループ
     */
    public String getSumGroup() {
        return sumGroup;
    }
    /**
     * 集計グループを設定します
     * @param sumGroup集計グループ
     */
    public void setSumGroup(String sumGroup) {
        this.sumGroup = sumGroup;
    }
    
    /**
     * お渡し日を取得します
     * @return reserveDt お渡し日
     */
    public String getReserveDt() {
        return reserveDt;
    }
    /**
     * お渡し日を設定します
     * @param reserveDtお渡し日
     */
    public void setReserveDt(String reserveDt) {
        this.reserveDt = reserveDt;
    }
    
}
