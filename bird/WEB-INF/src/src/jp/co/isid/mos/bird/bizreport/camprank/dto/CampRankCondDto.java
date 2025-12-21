package jp.co.isid.mos.bird.bizreport.camprank.dto;

import java.math.BigDecimal;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.camprank.common.CampRankConst;
import jp.co.isid.mos.bird.bizreport.common.camp.entity.MstCampDate;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;

/**
 * キャンペーンベスト１００リクエスト用DTO
 * @author xnkusama
 *
 */
public class CampRankCondDto implements CsvOutputDto {

    /**
     * 検索条件：キャンペーン識別番号
     */
    private String condCampId;
    
    /**
     * 検索条件：会社コード
     */
    private String condCompanyCd;
    
    /**
     * 検索条件：順位
     */
    private String condRank;
    
    /**
     * 検索条件：対象日
     */
    private String condTargetDt;
    
    /**
     * ウィンドウID
     */
    private int windowId;
    
    /**
     * 検索条件：検索モード
     */
    private String condMode;
    
    /**
     * 検索結果
     */
    private List listResult;
    
    /**
     * 対象日プルダウン選択Index
     */
    private int targetDtSelectedIndex;
    
    /**
     * 別ウィンドウフラグ
     */
    private boolean flgTargetWindow;
    
    /**
     * 検索対象キャンペーン期間マスタ
     */
    private MstCampDate targetMstCampDate;
    
    /**
     * BirdUserInfo
     */
    private BirdUserInfo birdUserInfo;
    
    /**
     * 対象店舗数
     * @return 
     */
    private BigDecimal taishoTenpoCount;

    
    /**
     * 順位名称 取得
     * @return
     */
    public String getCondRankName() {
        String name = "";
        if (CampRankConst.COND_RANK_KINGAKUKOSEI.equals(getCondRank())) {
            name = CampRankConst.COND_RANK_KINGAKUKOSEI_NAME;
        }
        else if (CampRankConst.COND_RANK_KOSU.equals(getCondRank())) {
            name = CampRankConst.COND_RANK_KOSU_NAME;
        }
        else if (CampRankConst.COND_RANK_ZENNENHI.equals(getCondRank())) {
            name = CampRankConst.COND_RANK_ZENNENHI_NAME;
        }
        return name;
    }
    public String getCondCampId() {
        return condCampId;
    }

    public void setCondCampId(String condCampId) {
        this.condCampId = condCampId;
    }

    public String getCondMode() {
        return condMode;
    }

    public void setCondMode(String condMode) {
        this.condMode = condMode;
    }

    public String getCondRank() {
        return condRank;
    }

    public void setCondRank(String condRank) {
        this.condRank = condRank;
    }

    public String getCondTargetDt() {
        return condTargetDt;
    }

    public void setCondTargetDt(String condTargetDt) {
        this.condTargetDt = condTargetDt;
    }

    public List getListResult() {
        return listResult;
    }

    public void setListResult(List listResult) {
        this.listResult = listResult;
    }

    public int getWindowId() {
        return windowId;
    }

    public void setWindowId(int windowId) {
        this.windowId = windowId;
    }

    public String getCondCompanyCd() {
        return condCompanyCd;
    }

    public void setCondCompanyCd(String condCompanyCd) {
        this.condCompanyCd = condCompanyCd;
    }

    public BigDecimal getTaishoTenpoCount() {
        return taishoTenpoCount;
    }

    public void setTaishoTenpoCount(BigDecimal taishoTenpoCount) {
        this.taishoTenpoCount = taishoTenpoCount;
    }

    public int getTargetDtSelectedIndex() {
        return targetDtSelectedIndex;
    }

    public void setTargetDtSelectedIndex(int targetDtSelectedIndex) {
        this.targetDtSelectedIndex = targetDtSelectedIndex;
    }

    public boolean isFlgTargetWindow() {
        return flgTargetWindow;
    }

    public void setFlgTargetWindow(boolean flgTargetWindow) {
        this.flgTargetWindow = flgTargetWindow;
    }

    public MstCampDate getTargetMstCampDate() {
        return targetMstCampDate;
    }

    public void setTargetMstCampDate(MstCampDate targetMstCampDate) {
        this.targetMstCampDate = targetMstCampDate;
    }
    
    public String getCampFrom() {
        if (getTargetMstCampDate() != null) {
            return getTargetMstCampDate().getCampFrom();
        }
        return "";
    }

    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }
}