package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity;

/**
 * 対象店舗情報エンティティクラス
 * 
 * @author xjung
 */
public class MstMiseInfo {
    /** テーブル名称 */    
    public static final String TABLE = "BM01TENM";
    /** カラム名称：店コード */    
    public static final String miseCd_COLUMN = "MISE_CD";
    /** カラム名称：店名称 */    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称
     */
    private String miseNameKj;
    
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
}