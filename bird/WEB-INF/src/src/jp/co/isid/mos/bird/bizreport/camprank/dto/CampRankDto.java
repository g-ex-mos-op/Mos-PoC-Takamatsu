package jp.co.isid.mos.bird.bizreport.camprank.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.camp.entity.MstCampDate;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * キャンペーンベスト１００画面セッション保持DTO
 * @author xnkusama
 *
 */
public class CampRankDto {
    /**
     * ウィインドウID
     */
    private int windowId;

    /**
     * 最大データ保持件数
     */
    private int maxSize;
    
    /** 
     * キャンペーン一覧リスト
     */
    private List listCamp;
    
    /**
     * キャンペーンプルダウンリスト
     */
    private List listCampPulldown;
    
    /**
     * 順位プルダウンリスト
     */
    private List listRankPulldown;
    
    /**
     * 対象日プルダウンリスト
     */
    private List listTargetDtPulldown;
    
    /**
     * 検索条件：会社コード
     */
    private Map condCompanyCd = new HashMap();
    
    /**
     * 検索条件：キャンペーン識別番号
     */
    private Map condCampId = new HashMap();
    
    /**
     * 検索条件：順位項目
     */
    private Map condRank = new HashMap();
    
    /**
     * 検索条件：対象日
     */
    private Map condTargetDt = new HashMap();
    
    /**
     * 検索結果
     */
    private Map listCampRankData = new LinkedHashMap();
    
    /**
     * 検索モード
     */
    private Map condMode = new HashMap();

    /**
     * 対象日プルダウン選択Index
     */
    private Map targetDtSelectedIndex = new HashMap();
    
    /**
     * 検索結果キャンペーンマスタ
     */
    private Map targetMstCampDate = new HashMap();
    
    /**
     * 最大ウインドウID
     */
    private int maxWindowId;
    
    /**
     * BirdUserInfo
     */
    private BirdUserInfo birdUserInfo;
    
    /**
     * BirdDateInfo
     */
    private BirdDateInfo birdDateInfo;
    
    /**
     * 対象店舗数
     */
    private Map taishoTenpoCount = new HashMap();
    
    /**
     * リモート制限フラグ
     */
    private boolean remoteLimitFlg = false;
    

    public String getCondCampId(int windowId) {
        return (String) condCampId.get(new Integer(windowId));
    }

    public void setCondCampId(String condCampId, int windowId) {
        this.condCampId.put(new Integer(windowId), condCampId);
    }

    public String getCondRank(int windowId) {
        return (String) condRank.get(new Integer(windowId));
    }

    public void setCondRank(String condRank, int windowId) {
        this.condRank.put(new Integer(windowId), condRank);
    }

    public String getCondTargetDt(int windowId) {
        return (String) condTargetDt.get(new Integer(windowId));
    }

    public void setCondTargetDt(String condTargetDt, int windowId) {
        this.condTargetDt.put(new Integer(windowId), condTargetDt);
    }

    public List getListCamp() {
        return listCamp;
    }

    public void setListCamp(List listCamp) {
        this.listCamp = listCamp;
    }

    public List getListCampPulldown() {
        return listCampPulldown;
    }
    
    public int getListCampPulldownSize() {
        return listCampPulldown == null ? 0 : listCampPulldown.size();
    }

    public void setListCampPulldown(List listCampPulldown) {
        this.listCampPulldown = listCampPulldown;
    }

    public List getListCampRankData(int windowId) {
        return (List) listCampRankData.get(new Integer(windowId));
    }

    public void setListCampRankData(List listCampRankData, int windowId) {
        // 現在ウインドウIDのデータを保持しているか判定
        if (this.listCampRankData.containsKey(new Integer(windowId))) {
            // 順番を入れ替える為、前回データを削除しておく
            this.listCampRankData.remove(new Integer(windowId));
        // 最大数を超える
        } else if (this.listCampRankData.size() >= getMaxSize()) {
            // 最古データを削除
            this.listCampRankData.remove(this.listCampRankData.keySet().toArray()[0]);
        }
        // リスト設定
        this.listCampRankData.put(new Integer(windowId), listCampRankData);
    }

    public List getListRankPulldown() {
        return listRankPulldown;
    }

    public void setListRankPulldown(List listRankPulldown) {
        this.listRankPulldown = listRankPulldown;
    }

    public List getListTargetDtPulldown() {
        return listTargetDtPulldown;
    }

    public void setListTargetDtPulldown(List listTargetDtPulldown) {
        this.listTargetDtPulldown = listTargetDtPulldown;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public String getCondMode(int windowId) {
        return (String) condMode.get(new Integer(windowId));
    }

    public void setCondMode(String condMode, int windowId) {
        this.condMode.put(new Integer(windowId), condMode);
    }

    public MstCampDate getTargetMstCampDate(int windowId) {
        return (MstCampDate) targetMstCampDate.get(new Integer(windowId));
    }

    public void setTargetMstCampDate(MstCampDate targetMstCampDate, int windowId) {
        this.targetMstCampDate.put(new Integer(windowId), targetMstCampDate);
    }

    public int getWindowId() {
        return windowId;
    }

    public void setWindowId(int windowId) {
        this.windowId = windowId;
    }

    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }

    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }

    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

    public String getCondCompanyCd(int windowId) {
        return (String) condCompanyCd.get(new Integer(windowId));
    }

    public void setCondCompanyCd(String condCompanyCd, int windowId) {
        this.condCompanyCd.put(new Integer(windowId), condCompanyCd);
    }
    /**
     * ウインドウID生成
     * @return 採番されたウインドウID
     */
    private int createWindowId() {
        int newWindowId = getMaxWindowId() + 1;
        setMaxWindowId(newWindowId);
        return newWindowId;
    }
    /**
     * ウインドウID更新
     */
    public int updateWindowid() {
        int newWindowId = createWindowId();
        setWindowId(newWindowId);
        return newWindowId;
    }

    public int getMaxWindowId() {
        return maxWindowId;
    }

    public void setMaxWindowId(int maxWindowId) {
        this.maxWindowId = maxWindowId;
    }

    public BigDecimal getTaishoTenpoCount(int windowId) {
        return (BigDecimal) taishoTenpoCount.get(new Integer(windowId));
    }

    public void setTaishoTenpoCount(BigDecimal taishoTenpoCount, int windowId) {
        this.taishoTenpoCount.put(new Integer(windowId), taishoTenpoCount);
    }

    public boolean isRemoteLimitFlg() {
        return remoteLimitFlg;
    }

    public void setRemoteLimitFlg(boolean remoteLimitFlg) {
        this.remoteLimitFlg = remoteLimitFlg;
    }

    public int getTargetDtSelectedIndex(int windowId) {
        return ((Integer) targetDtSelectedIndex.get(new Integer(windowId))).intValue();
    }

    public void setTargetDtSelectedIndex(int targetDtSelectedIndex, int windowId) {
        this.targetDtSelectedIndex.put(new Integer(windowId), new Integer(targetDtSelectedIndex));
    }

}