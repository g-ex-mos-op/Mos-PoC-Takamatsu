package jp.co.isid.mos.bird.bizreport.zendougeturegist.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jp.co.isid.mos.bird.bizreport.zendougeturegist.common.ZenDougetuRegistCommon;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

public class CtlZennenDouyouInfo {
    
    public static final String TABLE = "BR63YSZS";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String eigyoDt_COLUMN = "EIGYO_DT";
    
    public static final String zennenDt_COLUMN = "ZENNEN_DT";
    
    public static final String uriageZen_COLUMN = "URIAGE_ZEN";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    
    
    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * 当年日付
     */
    private String eigyoDt;
    
    /**
     * 前年対象日付
     */
    private String zennenDt;
    
    /**
     * 前年売上高
     */
    private BigDecimal  uriageZen;
    
    /**
     * 修正ユーザー
     */
    private String lastUser;
    
    /**
     * 修正プログラム
     */
    private String lastPgm;
    
    /**
     * 修正時タイムスタンプ
     */
    private Timestamp lastTmsp;
    
    /**
     * 祝日FLG(当年日付用)
     */
    private boolean isShukuJitu;
    /**
     * データフラグ
     */
    private String emptyData;
    
    /**
     * 当年日付(YYYY/MM/DD(曜日))
     */
    private String eigyoDtYoubi;
    
    /**
     * 前年対象日付(YYYY/MM/DD(曜日))
     */
    private String zennenDtYoubi;
    
    /**
     * 祝日名称(前年曜日)
     */
    private String zenShukuNm;
    /**
     * 祝日名称(当年曜日)
     */
    private String shukuNm;
    
    /**
     * 会社コードを取得します。
     * @return 会社コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 会社コードを設定します。
     * @param companyCd 会社コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * 当年日付を取得します。
     * @return 当年日付
     */
    public String getEigyoDt() {
        return eigyoDt;
    }
    /**
     * 当年日付を設定します。
     * @param eigyoDt 当年日付
     */
    public void setEigyoDt(String eigyoDt) {
        this.eigyoDt = eigyoDt;
    }
    
    /**
     * 前年対象日付を取得します。
     * @return 前年対象日付
     */
    public String getZennenDt() {
        return zennenDt;
    }
    /**
     * 前年対象日付を設定します。
     * @param zennenDt 前年対象日付
     */
    public void setZennenDt(String zennenDt) {
        this.zennenDt = zennenDt;
    }
    
    /**
     * 前年売上高を取得します。
     * @return 前年売上高
     */
    public BigDecimal getUriageZen() {
        return uriageZen;
    }
    /**
     * 前年売上高を設定します。
     * @param uriageZen 前年売上高
     */
    public void setUriageZen(BigDecimal uriageZen) {
        this.uriageZen = uriageZen;
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
     * 修正時タイムスタンプを取得します。
     * @return 修正時タイムスタンプ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 修正時タイムスタンプを設定します。
     * @param lastTmsp 修正時タイムスタンプ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    /**
     * 祝日フラグ(当年日付)を取得します
     * @return isShukuJitu 祝日フラグ(当年日付)
     */
    public boolean isShukuJitu() {
        return isShukuJitu;
    }
    /**
     * 祝日フラグ(当年日付)を設定します。
     * @param isShukuJitu
     */
    public void setShukuJitu(boolean isShukuJitu) {
        this.isShukuJitu = isShukuJitu;
    }
    /**
     * 当年日付(YYYY/MM/DD(曜日))を取得します。
     * @return 当年日付(YYYY/MM/DD(曜日))
     */
    public String getEigyoDtYoubi() {
        if(!ZenDougetuRegistCommon.isNull(getEigyoDt())){
            DateFormatter formatter = new DateFormatter();
            String dayWeekNm = "";
            String ymdKanji = formatter.format(getEigyoDt(), DateFormatter.PATTERN_SLASH, DateFormatter.DATE_TYPE_YMD);
            //曜日
            dayWeekNm = "("+formatter.format(getEigyoDt(),DateFormatter.PATTERN_DAY_OF_WEEK,DateFormatter.DATE_TYPE_YMD)+")";
            eigyoDtYoubi = ymdKanji + dayWeekNm;
        }else{
            eigyoDtYoubi = "";
        }
        return eigyoDtYoubi;
    }
    /**
     * 前年対象日付(YYYY/MM/DD(曜日))を取得します。
     * @return 前年対象日付(YYYY/MM/DD(曜日))
     */
    public String getZennenDtYoubi() {
        if(!ZenDougetuRegistCommon.isNull(getZennenDt())){
            DateFormatter formatter = new DateFormatter();
            String dayWeekNm = "";
            String ymdKanji = formatter.format(getZennenDt(), DateFormatter.PATTERN_SLASH, DateFormatter.DATE_TYPE_YMD);
            dayWeekNm = "("+formatter.format(getZennenDt(),DateFormatter.PATTERN_DAY_OF_WEEK,DateFormatter.DATE_TYPE_YMD)+")";
            zennenDtYoubi = ymdKanji + dayWeekNm;
        }else{
            zennenDtYoubi = "";
        }
        return zennenDtYoubi;
    }
    /**
     * 祝日名称(前年日付)を取得します。
     * @return 祝日名称(前年日付)
     */
    public String getZenShukuNm() {
        return zenShukuNm;
    }
    /**
     * 祝日名称(前年日付)を設定します
     * @param 祝日名称(前年日付)
     */
    public void setZenShukuNm(String zenShukuNm) {
        this.zenShukuNm = zenShukuNm;
    }
    /**
     * 祝日名称(当年日付)を取得します。
     * @return 祝日名称(当年日付)
     */
    public String getShukuNm() {
        return shukuNm;
    }
    /**
     * 祝日名称(当年日付)を設定します
     * @param 祝日名称(当年日付)
     */
    public void setShukuNm(String shukuNm) {
        this.shukuNm = shukuNm;
    }
    /**
     * 空データチェックを取得
     * @return 空データチェック
     */
    public String getEmptyData() {
        return emptyData;
    }
    /**
     * 空データチェックを設定
     * @param 空データチェック
     */
    public void setEmptyData(String emptyData) {
        this.emptyData = emptyData;
    }

}
