package jp.co.isid.mos.bird.bizsupport.moschickenmastregist.entity;

import java.sql.Timestamp;

public class MstHacchuBnr {
    
    public static final String TABLE = "PM09HCBR";
    
    public static final String hacchuBnrCd_COLUMN = "HACCHU_BNR_CD";
    
    public static final String mstDt_COLUMN = "MST_DT";
    
    public static final String hacBnrNameKj_COLUMN = "HAC_BNR_NAME_KJ";
    
    public static final String shuseiFlg_COLUMN = "SHUSEI_FLG";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    /**
     * 発注分類コード
     */
    private String hacchuBnrCd;
    
    /**
     * マスタ更新日
     */
    private String mstDt;
    
    /**
     * 発注分類名称
     */
    private String hacBnrNameKj;
    
    /**
     * 修正
     */
    private String shuseiFlg;
    
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
     * 発注分類コードを取得します。
     * @return 発注分類コード
     */
    public String getHacchuBnrCd() {
        return hacchuBnrCd;
    }
    /**
     * 発注分類コードを設定します。
     * @param hacchuBnrCd 発注分類コード
     */
    public void setHacchuBnrCd(String hacchuBnrCd) {
        this.hacchuBnrCd = hacchuBnrCd;
    }
    
    /**
     * マスタ更新日を取得します。
     * @return マスタ更新日
     */
    public String getMstDt() {
        return mstDt;
    }
    /**
     * マスタ更新日を設定します。
     * @param mstDt マスタ更新日
     */
    public void setMstDt(String mstDt) {
        this.mstDt = mstDt;
    }
    
    /**
     * 発注分類名称を取得します。
     * @return 発注分類名称
     */
    public String getHacBnrNameKj() {
        return hacBnrNameKj;
    }
    /**
     * 発注分類名称を設定します。
     * @param hacBnrNameKj 発注分類名称
     */
    public void setHacBnrNameKj(String hacBnrNameKj) {
        this.hacBnrNameKj = hacBnrNameKj;
    }
    
    /**
     * 修正を取得します。
     * @return 修正
     */
    public String getShuseiFlg() {
        return shuseiFlg;
    }
    /**
     * 修正を設定します。
     * @param shuseiFlg 修正
     */
    public void setShuseiFlg(String shuseiFlg) {
        this.shuseiFlg = shuseiFlg;
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
    
}
