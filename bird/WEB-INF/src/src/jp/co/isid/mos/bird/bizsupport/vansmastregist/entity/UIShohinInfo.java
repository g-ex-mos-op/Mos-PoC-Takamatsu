/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.vansmastregist.entity;

/**
 * 注文商品情報
 * @author narita
 */
public class UIShohinInfo {
    
    public static final String TABLE = "TM22SCHU";
    
    public static final String shoCdDai_COLUMN = "SHO_CD_DAI";
    
    public static final String shoNameKj_COLUMN = "SHO_NAME_KJ";
    
    /**
     * 注文商品コード
     */
    private String shoCdDai;
    
    /**
     * 注文商品名
     */
    private String shoNameKj;

	public String getShoCdDai() {
		return shoCdDai;
	}

	public void setShoCdDai(String shoCdDai) {
		this.shoCdDai = shoCdDai;
	}

	public String getShoNameKj() {
		return shoNameKj;
	}

	public void setShoNameKj(String shoNameKj) {
		this.shoNameKj = shoNameKj;
	}   
}
