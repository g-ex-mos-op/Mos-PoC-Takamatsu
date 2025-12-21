package jp.co.isid.mos.bird.common.entity;

/**
 * ユーザー作成可否フラグ、オーナーコードの取得
 *
 * 作成日:2018/04/27
 * @author caiweimei
 *
 */
public class UIChannelParam {

    public static final String TABLE = "BR84MCUJ";

    public static final String regDate_COLUMN = "USER_SAKUSEI_FLG";

    public static final String seq_COLUMN = "ONER_CD";

    /**
     * ユーザー作成可否フラグ
     */
    private String userSakuseiFlg;

    /**
     * オーナーコード
     */
    private String onerCd;

    /**
     * ユーザー作成可否フラグを取得します。
     * @return ユーザー作成可否フラグ
     */
    public String getUserSakuseiFlg() {
        return userSakuseiFlg;
    }
    /**
     * ユーザー作成可否フラグを設定します。
     * @param userSakuseiFlg ユーザー作成可否フラグ
     */
    public void setUserSakuseiFlg(String userSakuseiFlg) {
        this.userSakuseiFlg = userSakuseiFlg;
    }

    /**
     * オーナーコードを取得します。
     * @return オーナーコード
     */
    public String getOnerCd() {
        return onerCd;
    }
    /**
     * オーナーコードを設定します。
     * @param onerCd オーナーコード
     */
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }
}
