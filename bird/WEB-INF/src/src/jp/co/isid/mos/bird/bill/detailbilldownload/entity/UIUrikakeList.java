package jp.co.isid.mos.bird.bill.detailbilldownload.entity;

public class UIUrikakeList {
    
    public static final String TABLE = "BM33ONUR";
    
    public static final String urikakeCd_COLUMN = "URIKAKE_CD";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";


    /**
     * 売掛先コード
     */
    private String urikakeCd;
    
    /**
     * オーナー名称
     */
    private String onerNameKj;
    
    /**
     * 売掛先コードを取得します。
     * @return 売掛先コード
     */
    public String getUrikakeCd() {
        return urikakeCd;
    }
    /**
     * 売掛先コードを設定します。
     * @param urikakeCd 売掛先コード
     */
    public void setUrikakeCd(String urikakeCd) {
        this.urikakeCd = urikakeCd;
    }
    
    /**
     * オーナー名称を取得します。
     * @return オーナー名称
     */
    public String getOnerNameKj() {
        return onerNameKj;
    }
    /**
     * オーナー名称を設定します。
     * @param onerNameKj オーナー名称
     */
    public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj = onerNameKj;
    }
    
}
