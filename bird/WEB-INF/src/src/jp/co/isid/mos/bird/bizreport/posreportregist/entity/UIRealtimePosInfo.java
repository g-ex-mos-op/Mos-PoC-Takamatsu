package jp.co.isid.mos.bird.bizreport.posreportregist.entity;

import java.sql.Timestamp;

public class UIRealtimePosInfo {
    
    public static final String TABLE = "BR56RTSH";
    
    public static final String haisSijiDt_COLUMN = "HAIS_SIJI_DT";
    
    public static final String haisSijiSeq_COLUMN = "HAIS_SIJI_SEQ";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String shuDtSta_COLUMN = "SHU_DT_STA";
    
    public static final String shuDtEnd_COLUMN = "SHU_DT_END";
    
    public static final String mstDt_COLUMN = "MST_DT";
    
    public static final String haisSijiName_COLUMN = "HAIS_SIJI_NAME";
    
    public static final String shuTime1_COLUMN = "SHU_TIME1";
    
    public static final String shuTime2_COLUMN = "SHU_TIME2";
    
    public static final String shuTime3_COLUMN = "SHU_TIME3";
    
    public static final String shuTime4_COLUMN = "SHU_TIME4";
    
    public static final String shuTime5_COLUMN = "SHU_TIME5";
    
    public static final String sakujoFlg_COLUMN = "SAKUJO_FLG";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
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
     * 集信開始日
     */
    private String shuDtSta;
    
    /**
     * 集信終了日
     */
    private String shuDtEnd;
    
    /**
     * マスタ更新日
     */
    private String mstDt;
    
    /**
     * 配信指示名称
     */
    private String haisSijiName;
    
    /**
     * 集信時刻1
     */
    private String shuTime1;
    
    /**
     * 集信時刻2
     */
    private String shuTime2;
    
    /**
     * 集信時刻3
     */
    private String shuTime3;
    
    /**
     * 集信時刻4
     */
    private String shuTime4;
    
    /**
     * 集信時刻5
     */
    private String shuTime5;
    
    /**
     * 削除フラグ
     */
    private String sakujoFlg;
    
    /**
     * 登録ユーザＩＤ
     */
    private String firstUser;
    
    /**
     * 登録プログラムＩＤ
     */
    private String firstPgm;
    
    /**
     * 登録タイムスタンプ
     */
    private Timestamp firstTmsp;
    
    /**
     * 修正ユーザＩＤ
     */
    private String lastUser;
    
    /**
     * 修正プログラムＩＤ
     */
    private String lastPgm;
    
    /**
     * 修正タイムスタンプ
     */
    private Timestamp lastTmsp;
    
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
     * 配信指示名称を取得します。
     * @return 配信指示名称
     */
    public String getHaisSijiName() {
        return haisSijiName;
    }
    /**
     * 配信指示名称を設定します。
     * @param haisSijiName 配信指示名称
     */
    public void setHaisSijiName(String haisSijiName) {
        this.haisSijiName = haisSijiName;
    }
    
    /**
     * 集信時刻1を取得します。
     * @return 集信時刻1
     */
    public String getShuTime1() {
        return shuTime1;
    }
    /**
     * 集信時刻1を設定します。
     * @param shuTime1 集信時刻1
     */
    public void setShuTime1(String shuTime1) {
        this.shuTime1 = shuTime1;
    }
    
    /**
     * 集信時刻2を取得します。
     * @return 集信時刻2
     */
    public String getShuTime2() {
        return shuTime2;
    }
    /**
     * 集信時刻2を設定します。
     * @param shuTime2 集信時刻2
     */
    public void setShuTime2(String shuTime2) {
        this.shuTime2 = shuTime2;
    }
    
    /**
     * 集信時刻3を取得します。
     * @return 集信時刻3
     */
    public String getShuTime3() {
        return shuTime3;
    }
    /**
     * 集信時刻3を設定します。
     * @param shuTime3 集信時刻3
     */
    public void setShuTime3(String shuTime3) {
        this.shuTime3 = shuTime3;
    }
    
    /**
     * 集信時刻4を取得します。
     * @return 集信時刻4
     */
    public String getShuTime4() {
        return shuTime4;
    }
    /**
     * 集信時刻4を設定します。
     * @param shuTime4 集信時刻4
     */
    public void setShuTime4(String shuTime4) {
        this.shuTime4 = shuTime4;
    }
    
    /**
     * 集信時刻5を取得します。
     * @return 集信時刻5
     */
    public String getShuTime5() {
        return shuTime5;
    }
    /**
     * 集信時刻5を設定します。
     * @param shuTime5 集信時刻5
     */
    public void setShuTime5(String shuTime5) {
        this.shuTime5 = shuTime5;
    }
    
    /**
     * 削除フラグを取得します。
     * @return 削除フラグ
     */
    public String getSakujoFlg() {
        return sakujoFlg;
    }
    /**
     * 削除フラグを設定します。
     * @param sakujoFlg 削除フラグ
     */
    public void setSakujoFlg(String sakujoFlg) {
        this.sakujoFlg = sakujoFlg;
    }
    
    /**
     * 登録ユーザＩＤを取得します。
     * @return 登録ユーザＩＤ
     */
    public String getFirstUser() {
        return firstUser;
    }
    /**
     * 登録ユーザＩＤを設定します。
     * @param firstUser 登録ユーザＩＤ
     */
    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
    }
    
    /**
     * 登録プログラムＩＤを取得します。
     * @return 登録プログラムＩＤ
     */
    public String getFirstPgm() {
        return firstPgm;
    }
    /**
     * 登録プログラムＩＤを設定します。
     * @param firstPgm 登録プログラムＩＤ
     */
    public void setFirstPgm(String firstPgm) {
        this.firstPgm = firstPgm;
    }
    
    /**
     * 登録タイムスタンプを取得します。
     * @return 登録タイムスタンプ
     */
    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * 登録タイムスタンプを設定します。
     * @param firstTmsp 登録タイムスタンプ
     */
    public void setFirstTmsp(Timestamp firstTmsp) {
        this.firstTmsp = firstTmsp;
    }
    
    /**
     * 修正ユーザＩＤを取得します。
     * @return 修正ユーザＩＤ
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * 修正ユーザＩＤを設定します。
     * @param lastUser 修正ユーザＩＤ
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 修正プログラムＩＤを取得します。
     * @return 修正プログラムＩＤ
     */
    public String getLastPgm() {
        return lastPgm;
    }
    /**
     * 修正プログラムＩＤを設定します。
     * @param lastPgm 修正プログラムＩＤ
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    
    /**
     * 修正タイムスタンプを取得します。
     * @return 修正タイムスタンプ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 修正タイムスタンプを設定します。
     * @param lastTmsp 修正タイムスタンプ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    
}
