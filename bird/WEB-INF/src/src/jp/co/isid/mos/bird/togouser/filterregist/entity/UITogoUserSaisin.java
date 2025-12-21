package jp.co.isid.mos.bird.togouser.filterregist.entity;

public class UITogoUserSaisin {
    
    public static final String TABLE = "VIR51USER";
    
    public static final String userId_COLUMN = "USER_ID";
    
    public static final String hatsureiDt_COLUMN = "HATSUREI_DT";
    
    public static final String userNameKj_COLUMN = "USER_NAME_KJ";
    
    public static final String salaryCd_COLUMN = "SALARY_CD";
    
    public static final String jinjiCd_COLUMN = "JINJI_CD";
    
    public static final String bumonCdKakutei8_COLUMN = "BUMON_CD_KAKUTEI8";
    
    public static final String bumonName_COLUMN = "BUMON_NAME";
    
    public static final String kbnSpare1_COLUMN = "KBN_SPARE1";
    
    /**
     * 社員番号
     */
    private String userId;
    
    /**
     * 発令日
     */
    private String hatsureiDt;
    
    /**
     * 氏名
     */
    private String userNameKj;
    
    /**
     * 給与所属コード
     */
    private String salaryCd;
    
    /**
     * 人事所属コード
     */
    private String jinjiCd;
    
    /**
     * 部門コード（８桁）
     */
    private String bumonCdKakutei8;
    
    /**
     * 部門名称
     */
    private String bumonName;
    
    /**
     * 画面登録フラグ
     */
    private String kbnSpare1;
    
    /**
     * 社員番号を取得します。
     * @return 社員番号
     */
    public String getUserId() {
        return userId;
    }
    /**
     * 社員番号を設定します。
     * @param userId 社員番号
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * 発令日を取得します。
     * @return 発令日
     */
    public String getHatsureiDt() {
        return hatsureiDt;
    }
    /**
     * 発令日を設定します。
     * @param hatsureiDt 発令日
     */
    public void setHatsureiDt(String hatsureiDt) {
        this.hatsureiDt = hatsureiDt;
    }
    
    /**
     * 氏名を取得します。
     * @return 氏名
     */
    public String getUserNameKj() {
        return userNameKj;
    }
    /**
     * 氏名を設定します。
     * @param userNameKj 氏名
     */
    public void setUserNameKj(String userNameKj) {
        this.userNameKj = userNameKj;
    }
    
    /**
     * 給与所属コードを取得します。
     * @return 給与所属コード
     */
    public String getSalaryCd() {
        return salaryCd;
    }
    /**
     * 給与所属コードを設定します。
     * @param salaryCd 給与所属コード
     */
    public void setSalaryCd(String salaryCd) {
        this.salaryCd = salaryCd;
    }
    
    /**
     * 人事所属コードを取得します。
     * @return 人事所属コード
     */
    public String getJinjiCd() {
        return jinjiCd;
    }
    /**
     * 人事所属コードを設定します。
     * @param jinjiCd 人事所属コード
     */
    public void setJinjiCd(String jinjiCd) {
        this.jinjiCd = jinjiCd;
    }
    
    /**
     * 部門コード（８桁）を取得します。
     * @return 部門コード（８桁）
     */
    public String getBumonCdKakutei8() {
        return bumonCdKakutei8;
    }
    /**
     * 部門コード（８桁）を設定します。
     * @param bumonCdKakutei8 部門コード（８桁）
     */
    public void setBumonCdKakutei8(String bumonCdKakutei8) {
        this.bumonCdKakutei8 = bumonCdKakutei8;
    }
    
    /**
     * 部門名称を取得します。
     * @return 部門名称
     */
    public String getBumonName() {
        return bumonName;
    }
    /**
     * 部門名称を設定します。
     * @param bumonName 部門名称
     */
    public void setBumonName(String bumonName) {
        this.bumonName = bumonName;
    }
    
    /**
     * 画面登録フラグを取得します。
     * @return 画面登録フラグ
     */
    public String getKbnSpare1() {
        return kbnSpare1;
    }
    /**
     * 画面登録フラグを設定します。
     * @param kbnSpare1 画面登録フラグ
     */
    public void setKbnSpare1(String kbnSpare1) {
        this.kbnSpare1 = kbnSpare1;
    }
    
}
