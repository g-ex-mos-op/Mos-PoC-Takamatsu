package jp.co.isid.mos.bird.storemanage.claimtotal.dto;

import java.util.List;

import jp.co.isid.mos.bird.common.code.TaishoJoken;

public class ClaimTotalRequestDto {
    /**
     * ウィンドウID
     */
    private int windowId;

    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * 対象年月
     */
    private String taishoNengetu;
    
    /**
     * 対象条件
     */
    private String taishoJoken;
    
    /**
     * 表示対象
     */
    private String hyojiTaisho;
    
    /** 
     * 表示対象名称
     */
    private String hyojiTaishoName;
    
    /**
     * 検索結果
     */
    private List listSearchData;
    
    /**
     * 結果テーブルヘッダリスト
     */
    private List listTableHeader;
    
    /**
     * 対象期間From
     */
    private String taishoKikanFrom;
    
    /**
     * 対象期間To
     */
    private String taishoKikanTo;
    
    /**
     * 検索済みフラグ 
     */
    private boolean searchedFlg;
    
    /**
     * タイプコード（お客様の声画面リンク用）
     */
    private String linkRowType;
    
    /**
     * 対象年月（お客様の声画面リンク用）
     */
    private String linkTaishoNengetu;
    
    /**
     * 中分類（お客様の声画面リンク用）
     */
    private String linkBnrM;

    public int getWindowId() {
        return windowId;
    }

    public void setWindowId(int windowId) {
        this.windowId = windowId;
    }

    public String getCompanyCd() {
        return companyCd;
    }

    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }

    public String getHyojiTaisho() {
        return hyojiTaisho;
    }

    public void setHyojiTaisho(String hyojiTaisho) {
        this.hyojiTaisho = hyojiTaisho;
    }

    public String getTaishoJoken() {
        return taishoJoken;
    }

    public void setTaishoJoken(String taishoJoken) {
        this.taishoJoken = taishoJoken;
    }

    public String getTaishoNengetu() {
        return taishoNengetu;
    }

    public void setTaishoNengetu(String taishoNengetu) {
        this.taishoNengetu = taishoNengetu;
    }

    public List getListSearchData() {
        return listSearchData;
    }

    public void setListSearchData(List listSearchData) {
        this.listSearchData = listSearchData;
    }

    public String getHyojiTaishoNameDisp() {
        String name = "";
        if (TaishoJoken.CODE_ONER.equals(getTaishoJoken())
                || TaishoJoken.CODE_MISE.equals(getTaishoJoken())) 
        {
            name = getHyojiTaisho() + " " + this.hyojiTaishoName;
        }
        else {
            name = this.hyojiTaishoName;
        }
        return name;
    }
    
    public String getHyojiTaishoName() {
        return this.hyojiTaishoName;
    }

    public void setHyojiTaishoName(String hyojiTaishoName) {
        this.hyojiTaishoName = hyojiTaishoName;
    }

    public boolean isSearchedFlg() {
        return searchedFlg;
    }

    public void setSearchedFlg(boolean searchedFlg) {
        this.searchedFlg = searchedFlg;
    }

    public String getTaishoKikanFrom() {
        return taishoKikanFrom;
    }

    public void setTaishoKikanFrom(String taishoKikanFrom) {
        this.taishoKikanFrom = taishoKikanFrom;
    }

    public String getTaishoKikanTo() {
        return taishoKikanTo;
    }

    public void setTaishoKikanTo(String taishoKikanTo) {
        this.taishoKikanTo = taishoKikanTo;
    }

    public List getListTableHeader() {
        return listTableHeader;
    }

    public void setListTableHeader(List listTableHeader) {
        this.listTableHeader = listTableHeader;
    }

    public String getLinkRowType() {
        return linkRowType;
    }

    public void setLinkRowType(String linkRowType) {
        this.linkRowType = linkRowType;
    }

    public String getLinkTaishoNengetu() {
        return linkTaishoNengetu;
    }

    public void setLinkTaishoNengetu(String linkTaishoNengetu) {
        this.linkTaishoNengetu = linkTaishoNengetu;
    }

    public String getLinkBnrM() {
        return linkBnrM;
    }

    public void setLinkBnrM(String linkBnrM) {
        this.linkBnrM = linkBnrM;
    }

}