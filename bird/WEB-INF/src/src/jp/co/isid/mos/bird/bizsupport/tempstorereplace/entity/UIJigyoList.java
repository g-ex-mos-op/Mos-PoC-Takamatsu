package jp.co.isid.mos.bird.bizsupport.tempstorereplace.entity;

public class UIJigyoList {
    
    public static final String TABLE = "";
    
    public static final String jigyouCd_COLUMN = "JIGYOU_CD";
    
    public static final String jigyouName_COLUMN = "JIGYOU_NAME";
    
    /**
     * 事業コード
     */
    private String jigyouCd;
    
    /**
     * 事業名称
     */
    private String jigyouName;
    
    /**
     * 事業コードを取得します。
     * @return 事業コード
     */
    public String getJigyouCd() {
        return jigyouCd;
    }
    /**
     * 事業コードを設定します。
     * @param jigyouCd 事業コード
     */
    public void setJigyouCd(String jigyouCd) {
        this.jigyouCd = jigyouCd;
    }
    
    /**
     * 事業名称を取得します。
     * @return 事業名称
     */
    public String getJigyouName() {
        return jigyouName;
    }
    /**
     * 事業名称を設定します。
     * @param jigyouName 事業名称
     */
    public void setJigyouName(String jigyouName) {
        this.jigyouName = jigyouName;
    }
    
}
