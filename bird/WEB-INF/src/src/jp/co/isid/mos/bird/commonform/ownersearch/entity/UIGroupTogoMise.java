/*
 * 作成日: 2005/12/22
 */
package jp.co.isid.mos.bird.commonform.ownersearch.entity;

/**
 * オーナ検索 店情報
 * 
 * @author itamoto
 */
public class UIGroupTogoMise {

    public static final String TABLE = "BM01TENM";
    public static final String kaisyaCd_COLUMN = "KAISYA_CD";
    public static final String kaisyaName_COLUMN = "KAISYA_NAME";
    public static final String miseCd_COLUMN = "MISE_CD";
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    public static final String onerCd_COLUMN = "ONER_CD";

    /**
     * 会社コード
     */
    private String kaisyaCd;

    /**
     * 会社名
     */
    private String kaisyaName;

    /**
     * 店コード
     */
    private String miseCd;

    /**
     * 店名称
     */
    private String miseNameKj;

    /**
     * オーナコード
     */
    private String onerCd;

    /**
     * 会社コードを取得します。
     * @return 会社コード
     */
    public String getKaisyaCd() {
        return kaisyaCd;
    }

    /**
     * 会社コードを設定します。
     * @param kaisyaCd 会社コード
     */
    public void setKaisyaCd(String kaisyaCd) {
        this.kaisyaCd = kaisyaCd;
    }

    /**
     * 会社名を取得します。
     * @return 会社名
     */
    public String getKaisyaName() {
        return kaisyaName;
    }

    /**
     * 会社名を設定します。
     * @param kaisyaName 会社名
     */
    public void setKaisyaName(String kaisyaName) {
        this.kaisyaName = kaisyaName;
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
     * 店名称を取得します。
     * @return 店名称
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }

    /**
     * 店名称を設定します。
     * @param miseNameKj 店名称
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }

    /**
     * オーナコードを取得します。
     * @return オーナコード
     */
    public String getOnerCd() {
        return onerCd;
    }

    /**
     * オーナコードを設定します。
     * @param onerCd オーナコード
     */
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }
}
