/**
 * 
 */
package jp.co.isid.mos.bird.commonform.menusearch.entity;

/**
 * メニュー分類
 * 
 * 作成日:2008/08/26
 * @author xkinu
 *
 */
public class CodMenuBunrui {
    public static final String TABLE = "PC11MBUN";
    
    public static final String menuBunrui_COLUMN = "MENU_BUNRUI";
    
    public static final String mbunruiKj_COLUMN = "MBUNRUI_NAME_KJ";

    public static final String mbunruiKna_COLUMN = "MBUNRUI_NAME_KNA";
    /**
     * メニュー分類
     */
    private String menuBunrui;
    
    /**
     * メニュー分類
     */
    private String mbunruiNameKj;
    
    /**
     * メニュー分類
     */
    private String mbunruiNameKna;

	/**
	 * @return menuBunrui を戻します。
	 */
	public String getMenuBunrui() {
		return menuBunrui;
	}

	/**
	 * @param menuBunrui を クラス変数menuBunruiへ設定します。
	 */
	public void setMenuBunrui(String menuBunrui) {
		this.menuBunrui = menuBunrui;
	}

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

}
