package jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.entity;

public class MstMosChikenFromTo {
    
    public static final String TABLE = "BM39CRPD";
    
    public static final String ckanriNo_COLUMN = "CKANRI_NO";
    
    public static final String title_COLUMN = "TITLE";
    
    public static final String targetFrom_COLUMN = "TARGET_FROM";
    
    public static final String targetTo_COLUMN = "TARGET_TO";
    
    public static final String defoultFrom_COLUMN = "DEFOULT_FROM";
    
    public static final String defoultTo_COLUMN = "DEFOULT_TO";
    
    public static final String yobiFrom_COLUMN = "YOBI_FROM";
    
    public static final String yobiTo_COLUMN = "YOBI_TO";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    /**
     * 管理番号
     */
    private String ckanriNo;
    
    /**
     * タイトル
     */
    private String title;
    
    /**
     * 対象期間FROM  
     */
    private String targetFrom;
    
    /**
     * 対象期間TO
     */
    private String targetTo;
    
    /**
     * デフォルト表示期間FROM  
     */
    private String defoultFrom;
    
    /**
     * デフォルト表示期間To
     */
    private String defoultTo;
    
    /**
     * 予備FROM  
     */
    private String yobiFrom;
    
    /**
     * 予備TO  
     */
    private String yobiTo;
    
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
     * タイトルを取得します。
     * @return タイトル
     */
    public String getTitle() {
        return title;
    }
    /**
     * タイトルを設定します。
     * @param title タイトル
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * 対象期間FROM  を取得します。
     * @return 対象期間FROM  
     */
    public String getTargetFrom() {
        return targetFrom;
    }
    /**
     * 対象期間FROM  を設定します。
     * @param targetFrom 対象期間FROM  
     */
    public void setTargetFrom(String targetFrom) {
        this.targetFrom = targetFrom;
    }
    
    /**
     * 対象期間TOを取得します。
     * @return 対象期間TO
     */
    public String getTargetTo() {
        return targetTo;
    }
    /**
     * 対象期間TOを設定します。
     * @param targetTo 対象期間TO
     */
    public void setTargetTo(String targetTo) {
        this.targetTo = targetTo;
    }
    
    /**
     * デフォルト表示期間FROM  を取得します。
     * @return デフォルト表示期間FROM  
     */
    public String getDefoultFrom() {
        return defoultFrom;
    }
    /**
     * デフォルト表示期間FROM  を設定します。
     * @param defoultFrom デフォルト表示期間FROM  
     */
    public void setDefoultFrom(String defoultFrom) {
        this.defoultFrom = defoultFrom;
    }
    
    /**
     * デフォルト表示期間Toを取得します。
     * @return デフォルト表示期間To
     */
    public String getDefoultTo() {
        return defoultTo;
    }
    /**
     * デフォルト表示期間Toを設定します。
     * @param defoultTo デフォルト表示期間To
     */
    public void setDefoultTo(String defoultTo) {
        this.defoultTo = defoultTo;
    }
    
    /**
     * 予備FROM  を取得します。
     * @return 予備FROM  
     */
    public String getYobiFrom() {
        return yobiFrom;
    }
    /**
     * 予備FROM  を設定します。
     * @param yobiFrom 予備FROM  
     */
    public void setYobiFrom(String yobiFrom) {
        this.yobiFrom = yobiFrom;
    }
    
    /**
     * 予備TO  を取得します。
     * @return 予備TO  
     */
    public String getYobiTo() {
        return yobiTo;
    }
    /**
     * 予備TO  を設定します。
     * @param yobiTo 予備TO  
     */
    public void setYobiTo(String yobiTo) {
        this.yobiTo = yobiTo;
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
    
}
