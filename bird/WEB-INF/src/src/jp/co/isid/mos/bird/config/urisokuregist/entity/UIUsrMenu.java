/**
 * 
 */
package jp.co.isid.mos.bird.config.urisokuregist.entity;


/**
 * 売上速報商品グループエンティティ
 * 
 * 作成日:2008/08/06
 * @author xkinu
 *
 */
public class UIUsrMenu extends TrnUsrShohinGroup {
    public static final String menuNameKj_COLUMN = "MENU_NAME_KJ";

    /**
     * メニュー名称
     */
    private String menuNameKj;
    
	/**
	 * @return menuNameKj を戻します。
	 */
	public String getMenuNameKj() {
		return menuNameKj;
	}

	/**
	 * @param menuNameKj を クラス変数menuNameKjへ設定します。
	 */
	public void setMenuNameKj(String menuNameKj) {
		this.menuNameKj = menuNameKj;
	}

}
