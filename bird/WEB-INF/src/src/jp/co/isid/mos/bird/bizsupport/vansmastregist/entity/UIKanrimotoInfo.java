/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.vansmastregist.entity;

import java.util.List;

/**
 * 管理元情報
 * @author narita
 */
public class UIKanrimotoInfo {
    
    /**
     * 代表商品情報リスト（UIVansShohinInfo）
     */
    private List shohinList;
    
    /**
     * 管理元コード
     */
    private String kanriMotoCd;
    
    /**
     * 管理元名
     */
    private String kanriMotoName;

	public String getKanriMotoName() {
		return kanriMotoName;
	}

	public void setKanriMotoName(String kanriMotoName) {
		this.kanriMotoName = kanriMotoName;
	}

	public List getShohinList() {
		return shohinList;
	}

	public void setShohinList(List shohinList) {
		this.shohinList = shohinList;
	}

	public String getKanriMotoCd() {
		return kanriMotoCd;
	}

	public void setKanriMotoCd(String kanriMotoCd) {
		this.kanriMotoCd = kanriMotoCd;
	}
    
}
