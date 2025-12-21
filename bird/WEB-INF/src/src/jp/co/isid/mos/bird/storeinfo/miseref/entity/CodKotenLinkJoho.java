package jp.co.isid.mos.bird.storeinfo.miseref.entity;

import jp.co.isid.mos.bird.common.entity.BirdLinkJohoEntity;

public class CodKotenLinkJoho implements BirdLinkJohoEntity {

    public static final String TABLE = "BR34KLNK";

    public static final String kLinkId_COLUMN = "K_LINK_ID";

    public static final String menuName_COLUMN = "MENU_NAME";

    public static final String subMenuName_COLUMN = "SUB_MENU_NAME";

    public static final String siteKbn_COLUMN = "SITE_KBN";

    public static final String url_COLUMN = "URL";

    public static final String param_COLUMN = "PARAM";

    public static final String readme_COLUMN = "README";

    public static final String sort_COLUMN = "SORT";

    /**
     * 個店リンクID
     */
    private String kLinkId;

    /**
     * メニュー名
     */
    private String menuName;

    /**
     * サブメニュー名
     */
    private String subMenuName;

    /**
     * サイト区分
     */
    private String siteKbn;

    /**
     * URL
     */
    private String url;

    /**
     * パラメータ
     */
    private String param;

    /**
     * 説明
     */
    private String readme;

    /**
     * 順序
     */
    private String sort;

    /**
     * リンクURL
     */
    private String linkUrl;

    /**
     * 個店リンクIDを取得します。
     * @return 個店リンクID
     */
    public String getKLinkId() {
        return kLinkId;
    }
    /**
     * 個店リンクIDを設定します。
     * @param kLinkId 個店リンクID
     */
    public void setKLinkId(String kLinkId) {
        this.kLinkId = kLinkId;
    }

    /**
     * メニュー名を取得します。
     * @return メニュー名
     */
    public String getMenuName() {
        return menuName;
    }
    /**
     * メニュー名を設定します。
     * @param menuName メニュー名
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * サブメニュー名を取得します。
     * @return サブメニュー名
     */
    public String getSubMenuName() {
        return subMenuName;
    }
    /**
     * サブメニュー名を設定します。
     * @param subMenuName サブメニュー名
     */
    public void setSubMenuName(String subMenuName) {
        this.subMenuName = subMenuName;
    }

    /**
     * サイト区分を取得します。
     * @return サイト区分
     */
    public String getSiteKbn() {
        return siteKbn;
    }
    /**
     * サイト区分を設定します。
     * @param siteKbn サイト区分
     */
    public void setSiteKbn(String siteKbn) {
        this.siteKbn = siteKbn;
    }

    /**
     * URLを取得します。
     * @return URL
     */
    public String getUrl() {
        return url;
    }
    /**
     * URLを設定します。
     * @param url URL
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * パラメータを取得します。
     * @return パラメータ
     */
    public String getParam() {
        return param;
    }
    /**
     * パラメータを設定します。
     * @param param パラメータ
     */
    public void setParam(String param) {
        this.param = param;
    }

    /**
     * 説明を取得します。
     * @return 説明
     */
    public String getReadme() {
        return readme;
    }
    /**
     * 説明を設定します。
     * @param readme 説明
     */
    public void setReadme(String readme) {
        this.readme = readme;
    }

    /**
     * 順序を取得します。
     * @return 順序
     */
    public String getSort() {
        return sort;
    }
    /**
     * 順序を設定します。
     * @param sort 順序
     */
    public void setSort(String sort) {
        this.sort = sort;
    }

	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String url) {
		this.linkUrl = url;
	}
}