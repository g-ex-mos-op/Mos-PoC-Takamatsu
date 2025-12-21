/*
 * 作成日: 2006/05/11
 *
 */
package jp.co.isid.mos.bird.bizsupport.plinfoview.dto;


/**
 * P/L照会検索条件DTO
 * 
 * @author xyuchida
 */
public class PlInfoViewConditionDto {

    /* 検索条件 */
    //対象年月
    private String condTargetYM;
    //対象店舗
    private String condTaishoTenpo;
    //対象店舗（オーナー用）
    private String condTaishoTenpoOner;
    //事業本部
    private String condJigyouCd;
    //営業エリア
    private String condSlareaCd;
    //支部
    private String condSibuCd;
    //ブロック
    private String condBlockCd;
    //店コード
    private String condMiseCd;
    //店コード（オーナー用）
    private String condMiseCdOner;
    //オーナー
    private String condOnerCd;
    //店舗種別
    private String condTenpoShu;
    //検索タイプ
    private String condSearchType;
    
    /* CSV関連 */
    // CSV出力モード
    private String csvMode;

    public String getCondBlockCd() {
        return condBlockCd;
    }
    public void setCondBlockCd(String condBlockCd) {
        this.condBlockCd = condBlockCd;
    }
    public String getCondJigyouCd() {
        return condJigyouCd;
    }
    public void setCondJigyouCd(String condJigyouCd) {
        this.condJigyouCd = condJigyouCd;
    }
    public String getCondMiseCd() {
        return condMiseCd;
    }
    public void setCondMiseCd(String condMiseCd) {
        this.condMiseCd = condMiseCd;
    }
    public String getCondMiseCdOner() {
        return condMiseCdOner;
    }
    public void setCondMiseCdOner(String condMiseCdOner) {
        this.condMiseCdOner = condMiseCdOner;
    }
    public String getCondOnerCd() {
        return condOnerCd;
    }
    public void setCondOnerCd(String condOnerCd) {
        this.condOnerCd = condOnerCd;
    }
    public String getCondSearchType() {
        return condSearchType;
    }
    public void setCondSearchType(String condSearchType) {
        this.condSearchType = condSearchType;
    }
    public String getCondSibuCd() {
        return condSibuCd;
    }
    public void setCondSibuCd(String condSibuCd) {
        this.condSibuCd = condSibuCd;
    }
    public String getCondSlareaCd() {
        return condSlareaCd;
    }
    public void setCondSlareaCd(String condSlareaCd) {
        this.condSlareaCd = condSlareaCd;
    }
    public String getCondTaishoTenpo() {
        return condTaishoTenpo;
    }
    public void setCondTaishoTenpo(String condTaishoTenpo) {
        this.condTaishoTenpo = condTaishoTenpo;
    }
    public String getCondTaishoTenpoOner() {
        return condTaishoTenpoOner;
    }
    public void setCondTaishoTenpoOner(String condTaishoTenpoOner) {
        this.condTaishoTenpoOner = condTaishoTenpoOner;
    }
    public String getCondTargetYM() {
        return condTargetYM;
    }
    public void setCondTargetYM(String condTargetYM) {
        this.condTargetYM = condTargetYM;
    }
    public String getCondTenpoShu() {
        return condTenpoShu;
    }
    public void setCondTenpoShu(String condTenpoShu) {
        this.condTenpoShu = condTenpoShu;
    }
    public String getCsvMode() {
        return csvMode;
    }
    public void setCsvMode(String csvMode) {
        this.csvMode = csvMode;
    }

}
