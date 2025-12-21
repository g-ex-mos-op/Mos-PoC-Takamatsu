package jp.co.isid.mos.bird.bizsupport.moschickenmastregist.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.GenericMessageException;

/**
 * モスチキン管理マスタ登録
 * メニュー追加DTO
 * 
 * @author xkinu
 *
 */
public class MosChickenShanaiMenuDto {
    /* 処理対象：管理番号 */
    private String ckanriNo;
    /* 処理対象：メニューグループコード */
    private String menuGroupCd;
    /* 処理対象：メニューグループ名称  */
    private String menuGroupName;
    /* 検索条件：メニューコードFROM */
    private String menuCdFrom;
    /* 検索条件：メニューコードTO */
    private String menuCdTo;
    /* 検索条件：メニュー名称 */
    private String menuName;
    /* 検索結果：社内メニューマスタリスト */
    private List listMenuMst;
    /* 重複時例外 */
    private GenericMessageException duplicateEx;
    /**
     * クリア処理
     */
    public void clear(){
        setCkanriNo(null);
        setListMenuMst(null);
        setMenuCdFrom(null);
        setMenuCdTo(null);
        setMenuGroupCd(null);
        setMenuGroupName(null);
        setMenuName(null);
        setListMenuMst(null);
        setDuplicateEx(null);
    }
    /**
     * @return ckanriNo を戻します。
     */
    public String getCkanriNo() {
        return ckanriNo;
    }
    /**
     * @param ckanriNo 設定する ckanriNo。
     */
    public void setCkanriNo(String ckanriNo) {
        this.ckanriNo = ckanriNo;
    }
    /**
     * @return listMenuMst を戻します。
     */
    public List getListMenuMst() {
        return listMenuMst;
    }
    /**
     * @param listMenuMst 設定する listMenuMst。
     */
    public void setListMenuMst(List listMenuMst) {
        this.listMenuMst = listMenuMst;
    }
    /**
     * @return menuCdFrom を戻します。
     */
    public String getMenuCdFrom() {
        return menuCdFrom;
    }
    /**
     * @param menuCdFrom 設定する menuCdFrom。
     */
    public void setMenuCdFrom(String menuCdFrom) {
        this.menuCdFrom = menuCdFrom;
    }
    /**
     * @return menuCdTo を戻します。
     */
    public String getMenuCdTo() {
        return menuCdTo;
    }
    /**
     * @param menuCdTo 設定する menuCdTo。
     */
    public void setMenuCdTo(String menuCdTo) {
        this.menuCdTo = menuCdTo;
    }
    /**
     * @return menuGroupCd を戻します。
     */
    public String getMenuGroupCd() {
        return menuGroupCd;
    }
    /**
     * @param menuGroupCd 設定する menuGroupCd。
     */
    public void setMenuGroupCd(String menuGroupCd) {
        this.menuGroupCd = menuGroupCd;
    }
    /**
     * @return menuGroupName を戻します。
     */
    public String getMenuGroupName() {
        return menuGroupName;
    }
    /**
     * @param menuGroupName 設定する menuGroupName。
     */
    public void setMenuGroupName(String menuGroupName) {
        this.menuGroupName = menuGroupName;
    }
    /**
     * @return menuName を戻します。
     */
    public String getMenuName() {
        return menuName;
    }
    /**
     * @param menuName 設定する menuName。
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    /**
     * @return duplicateEx を戻します。
     */
    public GenericMessageException getDuplicateEx() {
        return duplicateEx;
    }
    /**
     * @param duplicateEx 設定する duplicateEx。
     */
    public void setDuplicateEx(GenericMessageException duplicateEx) {
        this.duplicateEx = duplicateEx;
    }
    

}
