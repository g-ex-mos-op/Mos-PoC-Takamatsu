package jp.co.isid.mos.bird.bizsupport.spot.entity;


/**
 * スポット未受注店情報エンティティ
 * @author xsong
 *
 */
public class TenpoResult {
    
    public static final String TABLE = "BT80CMJU";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String miseCode_COLUMN = "MISE_CD";
    
    public static final String shuErrFlg_COLUMN = "SHU_ERR_FLG";
    
    /**
     * 店名称
     */
    private String miseNameKj;
    
    /**
     * 店コード
     */
    private String  miseCode;
    
    /**
     * 集信エラーフラグ
     */
    private String shuErrFlg;
    
    /**
     * 店名称を取得します。
     * @return 店名称
     */
    public String getMiseNameKj() {
        
    	return (miseNameKj == null) ? "": miseNameKj;
    }
    /**
     * 店名称を設定します。
     * @param miseNameKj 店名称
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * 店コードを取得します。
     * @return 店コード
     */
    public String  getMiseCode() {
        return (miseCode == null)? "": miseCode;
    }
    /**
     * 店コードを設定します。
     * @param miseCode 店コード
     */
    public void setMiseCode(String  miseCode) {
        this.miseCode = miseCode;
    }
    
    /**
     * 集信エラーフラグを取得します。
     * @return 集信エラーフラグ
     */
    public String getShuErrFlg() {
    	     
       	return (shuErrFlg.equals("1")) ? "*" : ""; 
    }
    /**
     * 集信エラーフラグを設定します。
     * @param shuErrFlg 集信エラーフラグ
     */
    public void setShuErrFlg(String shuErrFlg) {
        this.shuErrFlg = shuErrFlg;
    }
    
}
