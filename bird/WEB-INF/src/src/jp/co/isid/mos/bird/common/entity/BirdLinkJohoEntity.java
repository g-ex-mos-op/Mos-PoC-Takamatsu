/*
 * 作成日: 2006/03/07
 */
package jp.co.isid.mos.bird.common.entity;

/**
 * リンク情報作成用Entityインターフェイス
 * @author xnkusama
 */
public interface BirdLinkJohoEntity {
	public String getMenuName();
	public String getSubMenuName();
	public String getSiteKbn();
	public String getUrl();
	public String getParam();
	public String getLinkUrl();
	public void setMenuName(String menuName);
	public void setSubMenuName(String subMenuName);
	public void setLinkUrl(String url);
}
