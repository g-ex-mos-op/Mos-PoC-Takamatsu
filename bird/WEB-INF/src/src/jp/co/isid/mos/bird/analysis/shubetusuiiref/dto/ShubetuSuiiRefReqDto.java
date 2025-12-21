package jp.co.isid.mos.bird.analysis.shubetusuiiref.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;

/**
 * 種別売上推移表リクエストDTO
 * @author xnkusama
 *
 */
public class ShubetuSuiiRefReqDto implements CsvOutputDto {
    
    /** 画面制御 */
    private int windowId;
    
    /**ユーザー情報*/
    private String userTypeCd;

    /** 検索条件 */
    private String companyCd;
    private String tenpoShu;
    private String taishoJoken;
    private String hyojiTaishoSibu;
    private String hyojiTaishoMise;
    private String kikan;
    private String zenDataShu;
    private boolean newWindowFlg;
    private String appDate;
    
    /** 検索結果 */
    private String taishoKikan;
    private String taishoKikanCsv;
    private String hyojiTaishoDisp;
    private String taishoTenpoCnt;
    private String tenpoShuName;
    private String zenDataShuName;
    private boolean reSearchFlg;
    private List listNipoData;
    private List listGepoData;
    
    public String getCompanyCd() {
        return companyCd;
    }
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    public String getHyojiTaishoSibu() {
        return hyojiTaishoSibu;
    }
    public void setHyojiTaishoSibu(String hyojiTaisho) {
        this.hyojiTaishoSibu = hyojiTaisho;
    }
    public String getHyojiTaishoDisp() {
        return hyojiTaishoDisp;
    }
    public void setHyojiTaishoDisp(String hyojiTaishoDisp) {
        this.hyojiTaishoDisp = hyojiTaishoDisp;
    }
    public String getKikan() {
        return kikan;
    }
    public void setKikan(String kikan) {
        this.kikan = kikan;
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
    public String getTenpoShu() {
        return tenpoShu;
    }
    public void setTenpoShu(String tenpoShu) {
        this.tenpoShu = tenpoShu;
    }
    public String getZenDataShu() {
        return zenDataShu;
    }
    public void setZenDataShu(String zenDataShu) {
        this.zenDataShu = zenDataShu;
    }
    public int getWindowId() {
        return windowId;
    }
    public void setWindowId(int windowId) {
        this.windowId = windowId;
    }
    public String getHyojiTaishoMise() {
        return hyojiTaishoMise;
    }
    public void setHyojiTaishoMise(String hyojiTaishoMise) {
        this.hyojiTaishoMise = hyojiTaishoMise;
    }
    public boolean isNewWindowFlg() {
        return newWindowFlg;
    }
    public void setNewWindowFlg(boolean newWindowFlg) {
        this.newWindowFlg = newWindowFlg;
    }
    public List getListGepoData() {
        return listGepoData;
    }
    public void setListGepoData(List listGepoData) {
        this.listGepoData = listGepoData;
    }
    public List getListNipoData() {
        return listNipoData;
    }
    public void setListNipoData(List listNipoData) {
        this.listNipoData = listNipoData;
    }
    public String getUserTypeCd() {
        return userTypeCd;
    }
    public void setUserTypeCd(String userTypeCd) {
        this.userTypeCd = userTypeCd;
    }
    public String getTenpoShuName() {
        return tenpoShuName;
    }
    public void setTenpoShuName(String tenpoShuName) {
        this.tenpoShuName = tenpoShuName;
    }
    public String getZenDataShuName() {
        return zenDataShuName;
    }
    public void setZenDataShuName(String zenDataShuName) {
        this.zenDataShuName = zenDataShuName;
    }
    public boolean isReSearchFlg() {
        return reSearchFlg;
    }
    public void setReSearchFlg(boolean reSearchFlg) {
        this.reSearchFlg = reSearchFlg;
    }
    public String getAppDate() {
        return appDate;
    }
    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }
    public String getTaishoKikanCsv() {
        return taishoKikanCsv;
    }
    public void setTaishoKikanCsv(String taishoKikanCsv) {
        this.taishoKikanCsv = taishoKikanCsv;
    }
}
