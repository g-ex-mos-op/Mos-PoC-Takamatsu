package jp.co.isid.mos.bird.entry.nationalentry.entity;

public class UINatiEntryOnerInfo {
    
    public static final String TABLE = "BM11ONER";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    public static final String onerTel_COLUMN = "ONER_TEL";
    
    /**
     * オーナー名称（漢字）
     */
    private String onerNameKj;
    
    /**
     * オーナー電話番号
     */
    private String onerTel;
    
    /**
     * オーナー名称（漢字）を取得します。
     * @return オーナー名称（漢字）
     */
    public String getOnerNameKj() {
        return onerNameKj;
    }
    /**
     * オーナー名称（漢字）を設定します。
     * @param onerNameKj オーナー名称（漢字）
     */
    public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj = onerNameKj;
    }
    
    /**
     * オーナー電話番号を取得します。
     * @return オーナー電話番号
     */
    public String getOnerTel() {
        return onerTel;
    }
    /**
     * オーナー電話番号を設定します。
     * @param onerTel オーナー電話番号
     */
    public void setOnerTel(String onerTel) {
        this.onerTel = onerTel;
    }
    
}
