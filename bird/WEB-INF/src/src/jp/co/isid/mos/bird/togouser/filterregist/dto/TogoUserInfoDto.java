package jp.co.isid.mos.bird.togouser.filterregist.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;

public class TogoUserInfoDto {
    
    /**
     * 画面表示用検索結果
     */
    private List userlist;
    
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
     * 日付取得処理
     */    
    private BirdDateInfo birdDateInfo;
    
    /**
     * 日付取得処理
     */    
    private String strLoginUser;;    
    
    private String Message = "登録します。よろしいですか。";
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
    public List getUserlist() {
        return userlist;
    }
    
    public void setUserlist(List userlist) {
        this.userlist = userlist;
    }
    /**
     * 日付取得処理を取得します。
     * @param birdDateInfo 日付取得処理
     */
    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }
    /**
     * 日付取得処理をを設定します。
     * @param birdDateInfo 日付取得処理
     */
    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }
    /**
     * ログインユーザーをを設定します。
     * @param strLoginUser ログインユーザー
     */
    public String getStrLoginUser() {
        return strLoginUser;
    }
    /**
     * ログインユーザーをを設定します。
     * @param strLoginUser ログインユーザー
     */
    public void setStrLoginUser(String strLoginUser) {
        this.strLoginUser = strLoginUser;
    }
    
    //初期化処理
    public void initialize() {
        this.userlist = null;
        this.userNameKj = "";
        this.bumonName = "";
        this.userId = "";
    }
    public String getMessage() {
        return Message;
    }
    public void setMessage(String message) {
        Message = message;
    }
    
        
}
