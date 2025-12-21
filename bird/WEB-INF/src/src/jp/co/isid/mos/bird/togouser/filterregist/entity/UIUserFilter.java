package jp.co.isid.mos.bird.togouser.filterregist.entity;

import java.sql.Timestamp;

public class UIUserFilter {
    
    public static final String TABLE = "IR03UFLT";
    
    public static final String userId_COLUMN = "USER_ID";
    
    public static final String groupCd_COLUMN = "GROUP_CD";
    
    public static final String tekiyouDt_COLUMN = "TEKIYOU_DT";
    
    public static final String renkeiFlg_COLUMN = "RENKEI_FLG";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    /**
     * 社員番号
     */
    private String userId;
    
    /**
     * グループコード
     */
    private String groupCd;
    
    /**
     * フィルタ適用日
     */
    private String tekiyouDt;
    
    /**
     * 連携フラグ
     */
    private String renkeiFlg;
    
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
     * グループコードを取得します。
     * @return グループコード
     */
    public String getGroupCd() {
        return groupCd;
    }
    /**
     * グループコードを設定します。
     * @param groupCd グループコード
     */
    public void setGroupCd(String groupCd) {
        this.groupCd = groupCd;
    }
    
    /**
     * フィルタ適用日を取得します。
     * @return フィルタ適用日
     */
    public String getTekiyouDt() {
        return tekiyouDt;
    }
    /**
     * フィルタ適用日を設定します。
     * @param tekiyouDt フィルタ適用日
     */
    public void setTekiyouDt(String tekiyouDt) {
        this.tekiyouDt = tekiyouDt;
    }
    
    /**
     * 連携フラグを取得します。
     * @return 連携フラグ
     */
    public String getRenkeiFlg() {
        return renkeiFlg;
    }
    /**
     * 連携フラグを設定します。
     * @param renkeiFlg 連携フラグ
     */
    public void setRenkeiFlg(String renkeiFlg) {
        this.renkeiFlg = renkeiFlg;
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
