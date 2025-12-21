package jp.co.isid.mos.bird.analysis.shubetusuiiref.dto;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * 種別売上推移表DTO
 * @author xnkusama
 *
 */
public class ShubetuSuiiRefDto {

    private BirdUserInfo birdUserInfo;
    private BirdDateInfo birdDateInfo;
    
    /**ユーザー情報*/
    private String userId;
    private String userTypeCd;
    
    /**条件情報*/
    private List listTenpoShu;
    private List listTenpoShuKoten;
    private List listTaishoJoken;
    private List listHyojiTaisho;
    private List listKikan;
    private List listZenDataShu;
    
    /**検索情報*/
    //検索済み検索条件
    private Map mapSearchedInfo = new HashMap();
    //検索フォーム検索条件
    private Map mapSearchFormInfo = new HashMap();
    //検索結果
    private Map mapSearchedData = new LinkedHashMap();
    // 検索済みフラグ
    private Map searchedFlg = new HashMap();
    // 最大ウィンドウID
    private int maxWindowId = 0;
    // 最大データ保持件数
    private int maxSize = 5;
    // 再検索フラグ
    private Map mapResearchFlg = new HashMap();

    
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
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserTypeCd() {
        return userTypeCd;
    }
    public void setUserTypeCd(String userTypeCd) {
        this.userTypeCd = userTypeCd;
    }
    public List getListHyojiTaisho() {
        return listHyojiTaisho;
    }
    public void setListHyojiTaisho(List listHyojiTaisho) {
        this.listHyojiTaisho = listHyojiTaisho;
    }
    public List getListKikan() {
        return listKikan;
    }
    public void setListKikan(List listKikan) {
        this.listKikan = listKikan;
    }
    public List getListTaishoJoken() {
        return listTaishoJoken;
    }
    public void setListTaishoJoken(List listTaishoJoken) {
        this.listTaishoJoken = listTaishoJoken;
    }
    public List getListTenpoShu() {
        return listTenpoShu;
    }
    public void setListTenpoShu(List listTenpoShu) {
        this.listTenpoShu = listTenpoShu;
    }
    public List getListZenDataShu() {
        return listZenDataShu;
    }
    public void setListZenDataShu(List listZenDataShu) {
        this.listZenDataShu = listZenDataShu;
    }
    
    /**
     * ウィンドウIDを生成します。
     */
    public int createWindowId() {
        int newWindowId = getMaxWindowId() + 1;
        setMaxWindowId(newWindowId);
        return newWindowId;
    }
    
    /**
     * ウィンドウIDを更新します。
     */
    public int updateWindowid() {
       return createWindowId();
    }
    public int getMaxWindowId() {
        return maxWindowId;
    }
    public void setMaxWindowId(int maxWindowId) {
        this.maxWindowId = maxWindowId;
    }
    public ShubetuSuiiRefReqDto getSearchedInfo(int windowId) {
        return (ShubetuSuiiRefReqDto) mapSearchedInfo.get(new Integer(windowId));
    }
    public void setSearchedInfo(int windowId, ShubetuSuiiRefReqDto dto) {
        mapSearchedInfo.put(new Integer(windowId), dto);
    }
    public ShubetuSuiiRefReqDto getSearchFormInfo(int windowId) {
        return (ShubetuSuiiRefReqDto) mapSearchFormInfo.get(new Integer(windowId));
    }
    public void setSearchFormInfo(int windowId, ShubetuSuiiRefReqDto dto) {
        mapSearchFormInfo.put(new Integer(windowId), dto);
    }
    /**
     * 検索済みフラグ
     * @return
     */
    public boolean isSearchedFlg(int windowId) {
        if (searchedFlg.containsKey(new Integer(windowId))) {
            if (Boolean.TRUE.equals((Boolean) searchedFlg.get(new Integer(windowId)))) {
                return true;
            }
        }
        return false;
    }
    /**
     * 検索済みフラグ設定処理
     * @param flg  true:検索済み
     */
    public void setSearchedFlg(int windowId, boolean flg) {
        searchedFlg.put(new Integer(windowId), new Boolean(flg));
    }

    /**
     * @return listSearchData を戻します。
     */
    public List getListSearchData(int windowId) {
        return (List)mapSearchedData.get(new Integer(windowId));
    }

    /**
     * 検索結果設定処理
     * 
     * @param windowId
     * @param listSearchData
     */
    public void setListSearchData(int windowId, List listSearchData) {
        // 現在ウインドウIDのデータを保持している場合
        if (this.mapSearchedData.containsKey(new Integer(windowId))) {
            // 順番を入れ替える為、前回データを削除する
            this.mapSearchedData.remove(new Integer(windowId));
        // 最大数を超えた場合
        } else if (this.mapSearchedData.size() >= getMaxSize()) {
            // 最古データを削除
            this.mapSearchedData.remove(this.mapSearchedData.keySet().toArray()[0]);
        }           
        this.mapSearchedData.put(new Integer(windowId), listSearchData);
    }
    
    /**
     * 検索結果削除処理
     * @param windowId
     */
    public void removeSearchData(int windowId) {
        this.mapSearchedData.remove(new Integer(windowId));
    }
    
    public int getMaxSize() {
        return maxSize;
    }
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
    public List getListTenpoShuKoten() {
        return listTenpoShuKoten;
    }
    public void setListTenpoShuKoten(List listTenpoShuKoten) {
        this.listTenpoShuKoten = listTenpoShuKoten;
    }
    public boolean isResearchFlg(int windowId) {
        if (mapResearchFlg.containsKey(new Integer(windowId))) {
            if (Boolean.TRUE.equals((Boolean) mapResearchFlg.get(new Integer(windowId)))) {
                return true;
            }
        }
        return false;
    }
    public void setResearchFlg(int windowId, boolean flg) {
        mapResearchFlg.put(new Integer(windowId), new Boolean(flg));
    }
}
