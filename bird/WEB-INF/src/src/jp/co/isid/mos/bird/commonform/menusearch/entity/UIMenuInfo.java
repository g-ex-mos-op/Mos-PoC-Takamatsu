/**
 * 
 */
package jp.co.isid.mos.bird.commonform.menusearch.entity;

import jp.co.isid.mos.bird.common.entity.MstShanaiMenu;

/**
 * メニュー情報
 * 
 * 作成日:2008/08/26
 * @author xkinu
 *
 */
public class UIMenuInfo extends MstShanaiMenu {
	
    public static final String mbunruiNameKj_COLUMN = "MBUNRUI_NAME_KJ";

    public static final String mbunruiNameKna_COLUMN = "MBUNRUI_NAME_KNA";

    public static final String menuHanbaiNameKj_COLUMN = "MHANBAI_NAME_KJ";
    
    public static final String menuHanbaiNameKna_COLUMN = "MHANBAI_NAME_KNA";
    
    public static final String menuShokuzaiNameKj_COLUMN = "MSHOKUZAI_NAME_KJ";

    public static final String menuShokuzaiNameKna_COLUMN = "MSHOKUZAI_NAME_KNA";
    /**
     * メニュー分類
     */
    private String mbunruiNameKj;
    
    /**
     * メニュー分類
     */
    private String mbunruiNameKna;
    
    /**
     * メニュー販売形態
     */
    private String mhanbaiNameKj;
    
    /**
     * メニュー販売形態
     */
    private String mhanbaiNameKna;
    
    /**
     * メニュー食材区分
     */
    private String mshokuzaiNameKj;

    /**
     * メニュー食材区分
     */
    private String mshokuzaiNameKna;

	/**
	 * @return mbunruiNameKj を戻します。
	 */
	public String getMbunruiNameKj() {
		return mbunruiNameKj;
	}

	/**
	 * @param mbunruiNameKj を クラス変数mbunruiNameKjへ設定します。
	 */
	public void setMbunruiNameKj(String mbunruiNameKj) {
		this.mbunruiNameKj = mbunruiNameKj;
	}

	/**
	 * @return mbunruiNameKna を戻します。
	 */
	public String getMbunruiNameKna() {
		return mbunruiNameKna;
	}

	/**
	 * @param mbunruiNameKna を クラス変数mbunruiNameKnaへ設定します。
	 */
	public void setMbunruiNameKna(String mbunruiNameKna) {
		this.mbunruiNameKna = mbunruiNameKna;
	}

	/**
	 * @return mhanbaiNameKj を戻します。
	 */
	public String getMhanbaiNameKj() {
		return mhanbaiNameKj;
	}

	/**
	 * @param mhanbaiNameKj を クラス変数mhanbaiNameKjへ設定します。
	 */
	public void setMhanbaiNameKj(String mhanbaiNameKj) {
		this.mhanbaiNameKj = mhanbaiNameKj;
	}

	/**
	 * @return mhanbaiNameKna を戻します。
	 */
	public String getMhanbaiNameKna() {
		return mhanbaiNameKna;
	}

	/**
	 * @param mhanbaiNameKna を クラス変数mhanbaiNameKnaへ設定します。
	 */
	public void setMhanbaiNameKna(String mhanbaiNameKna) {
		this.mhanbaiNameKna = mhanbaiNameKna;
	}

	/**
	 * @return mshokuzaiNameKj を戻します。
	 */
	public String getMshokuzaiNameKj() {
		return mshokuzaiNameKj;
	}

	/**
	 * @param mshokuzaiNameKj を クラス変数mshokuzaiNameKjへ設定します。
	 */
	public void setMshokuzaiNameKj(String mshokuzaiNameKj) {
		this.mshokuzaiNameKj = mshokuzaiNameKj;
	}

	/**
	 * @return mshokuzaiNameKna を戻します。
	 */
	public String getMshokuzaiNameKna() {
		return mshokuzaiNameKna;
	}

	/**
	 * @param mshokuzaiNameKna を クラス変数mshokuzaiNameKnaへ設定します。
	 */
	public void setMshokuzaiNameKna(String mshokuzaiNameKna) {
		this.mshokuzaiNameKna = mshokuzaiNameKna;
	}

}
