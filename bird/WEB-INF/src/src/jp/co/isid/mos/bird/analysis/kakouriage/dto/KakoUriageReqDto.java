package jp.co.isid.mos.bird.analysis.kakouriage.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;

/**
 * 過去売上リクエストDTO
 * @author xnkusama
 *
 */
public class KakoUriageReqDto implements CsvOutputDto {
    
    /** 画面制御 */
    private int windowId;
    
    /**ユーザー情報*/
    private String userTypeCd;

    /** 検索条件 */
    private String companyCd;
    private String taishoJoken;
    private String taishoKikan;
    private String hyojiTaisho;
    private boolean newWindowFlg;
    private String tabMode;
    
    /** 検索結果 */
    private String taishoKikanDisp;
    private String hyojiTaishoDisp;
    private String taishoTenpoCnt;
    private boolean reSearchFlg;
    private List listHeader;
    private List listData;
    
    public String getCompanyCd() {
        return companyCd;
    }
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    public String getHyojiTaishoDisp() {
        return hyojiTaishoDisp;
    }
    public void setHyojiTaishoDisp(String hyojiTaishoDisp) {
        this.hyojiTaishoDisp = hyojiTaishoDisp;
    }
    public String getTaishoKikanDisp() {
        return taishoKikanDisp;
    }
    public void setTaishoKikanDisp(String kikan) {
        this.taishoKikanDisp = kikan;
    }
    public String getTaishoJoken() {
        return taishoJoken;
    }
    public void setTaishoJoken(String taishoJoken) {
        this.taishoJoken = taishoJoken;
    }
    public String getTaishoKikan() {
        return taishoKikan;
    }
    public void setTaishoKikan(String taishoKikan) {
        this.taishoKikan = taishoKikan;
    }
    public String getTaishoTenpoCnt() {
        return taishoTenpoCnt;
    }
    public void setTaishoTenpoCnt(String taishoTenpoCnt) {
        this.taishoTenpoCnt = taishoTenpoCnt;
    }
    public int getWindowId() {
        return windowId;
    }
    public void setWindowId(int windowId) {
        this.windowId = windowId;
    }
    public String getHyojiTaisho() {
        return hyojiTaisho;
    }
    public void setHyojiTaisho(String hyojiTaishoMise) {
        this.hyojiTaisho = hyojiTaishoMise;
    }
    public boolean isNewWindowFlg() {
        return newWindowFlg;
    }
    public void setNewWindowFlg(boolean newWindowFlg) {
        this.newWindowFlg = newWindowFlg;
    }
    public List getListData() {
        return listData;
    }
    public void setListData(List listNipoData) {
        this.listData = listNipoData;
    }
    public String getUserTypeCd() {
        return userTypeCd;
    }
    public void setUserTypeCd(String userTypeCd) {
        this.userTypeCd = userTypeCd;
    }
    public List getListHeader() {
        return listHeader;
    }
    public void setListHeader(List listHeader) {
        this.listHeader = listHeader;
    }
    public String getTabMode() {
        return tabMode;
    }
    public void setTabMode(String tabMode) {
        this.tabMode = tabMode;
    }
    public boolean isReSearchFlg() {
        return reSearchFlg;
    }
    public void setReSearchFlg(boolean reSearchFlg) {
        this.reSearchFlg = reSearchFlg;
    }
}
