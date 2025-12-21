package jp.co.isid.mos.bird.bizsupport.moschickenmastregist.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.GenericMessageException;

/**
 * モスチキン管理マスタ登録
 * 食包材選択DTO
 * 
 * @author xkinu
 *
 */
public class MosChickenShokuhouzaiDto {
    /* 処理対象：管理番号 */
    private String ckanriNo;
    /* 処理対象：メニューグループコード */
    private String menuGroupCd;
    /* 処理対象：メニューグループ名称  */
    private String menuGroupName;
    /* 検索条件：メニューコード */
    private String menuCd;
    /* 検索条件：コードFROM */
    private String cdFrom;
    /* 検索条件：コードTO */
    private String cdTo;
    /* 検索条件：名称 */
    private String name;
    /* 検索条件：発注分類コード */
    private String hacchuBnrCd;
    /* 検索条件：発注分類リスト */
    private List listHacchuBnr;
    /* 検索結果：食包材マスタリスト */
    private List listShokuhouzaiMst;
    /* 重複時例外 */
    private GenericMessageException duplicateEx;
    /* 選択対象食包材コード */
    private String targetShokuCd;
    /* 選択対象食包材名称 */
    private String targetShokuName;
    /**
     * クリア処理
     */
    public void clear(){
        setCkanriNo(null);
        setMenuCd(null);
        setCdFrom(null);
        setCdTo(null);
        setHacchuBnrCd(null);
        setMenuGroupCd(null);
        setMenuGroupName(null);
        setName(null);
        setListHacchuBnr(null);
        setListShokuhouzaiMst(null);
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
     * @return cdFrom を戻します。
     */
    public String getCdFrom() {
        return cdFrom;
    }
    /**
     * @param cdFrom 設定する cdFrom。
     */
    public void setCdFrom(String cdFrom) {
        this.cdFrom = cdFrom;
    }
    /**
     * @return cdTo を戻します。
     */
    public String getCdTo() {
        return cdTo;
    }
    /**
     * @param cdTo 設定する cdTo。
     */
    public void setCdTo(String cdTo) {
        this.cdTo = cdTo;
    }
    /**
     * @return listShokuhouzaiMst を戻します。
     */
    public List getListShokuhouzaiMst() {
        return listShokuhouzaiMst;
    }
    /**
     * @param listShokuhouzaiMst 設定する listShokuhouzaiMst。
     */
    public void setListShokuhouzaiMst(List listShokuhouzaiMst) {
        this.listShokuhouzaiMst = listShokuhouzaiMst;
    }
    /**
     * @return menuCd を戻します。
     */
    public String getMenuCd() {
        return menuCd;
    }
    /**
     * @param menuCd 設定する menuCd。
     */
    public void setMenuCd(String menuCd) {
        this.menuCd = menuCd;
    }
    /**
     * @return name を戻します。
     */
    public String getName() {
        return name;
    }
    /**
     * @param name 設定する name。
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return hacchuBnrCd を戻します。
     */
    public String getHacchuBnrCd() {
        return hacchuBnrCd;
    }
    /**
     * @param hacchuBnrCd 設定する hacchuBnrCd。
     */
    public void setHacchuBnrCd(String hacchuBnrCd) {
        this.hacchuBnrCd = hacchuBnrCd;
    }
    /**
     * @return listHacchuBnr を戻します。
     */
    public List getListHacchuBnr() {
        return listHacchuBnr;
    }
    /**
     * @param listHacchuBnr 設定する listHacchuBnr。
     */
    public void setListHacchuBnr(List listHacchuBnr) {
        this.listHacchuBnr = listHacchuBnr;
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
    /**
     * @return targetShokuCd を戻します。
     */
    public String getTargetShokuCd() {
        return targetShokuCd;
    }
    /**
     * @param targetShokuCd 設定する targetShokuCd。
     */
    public void setTargetShokuCd(String targetShokuCd) {
        this.targetShokuCd = targetShokuCd;
    }
    /**
     * @return targetShokuName を戻します。
     */
    public String getTargetShokuName() {
        return targetShokuName;
    }
    /**
     * @param targetShokuName 設定する targetShokuName。
     */
    public void setTargetShokuName(String targetShokuName) {
        this.targetShokuName = targetShokuName;
    }
    

}
