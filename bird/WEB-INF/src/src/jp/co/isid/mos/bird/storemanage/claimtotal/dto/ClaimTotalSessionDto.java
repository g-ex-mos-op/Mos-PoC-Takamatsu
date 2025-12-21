package jp.co.isid.mos.bird.storemanage.claimtotal.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

public class ClaimTotalSessionDto {

    /**
     * ユーザータイプコード
     */
    private String userTypeCd;
    
    /**
     * 対象年月プルダウン
     */
    private List listTaishoNengetu = new ArrayList();
    
    /**
     * 対象条件
     */
    private List listTaishoJoken = new ArrayList();
    
    /**
     * 事業本部プルダウン
     */
    private List listJigyohonbu = new ArrayList();

    /**
     * エリアプルダウン
     */
    private List listSlArea = new ArrayList();

    /**
     * 支部プルダウン
     */
    private List listSibu = new ArrayList();

    /**
     * エリア大プルダウン
     */
    private List listAreaDai = new ArrayList();
    
    /**
     * 結果テーブルヘッダリスト
     */
    private Map mapListTableHeader = new HashMap();
    
    /**
     * 検索条件：会社コード
     */
    private Map companyCd = new HashMap();
    /**
     * 検索条件：対象年月
     */
    private Map taishoNengetu = new HashMap();
    /**
     * 検索条件：対象条件
     */
    private Map taishoJoken = new HashMap();
    /**
     * 検索条件：表示対象
     */
    private Map hyojiTaisho = new HashMap();
    /**
     * 検索条件：表示対象名
     */
    private Map hyojiTaishoName = new HashMap();
    /**
     * 検索条件：対象期間From
     */
    private Map taishoKikanFrom = new HashMap();
    /**
     * 検索条件：対象期間To
     */
    private Map taishoKikanTo = new HashMap();

    /**
     * 検索結果
     */
    private Map listSearchData = new LinkedHashMap();
    
    /**
     * 最大ウィンドウID
     */
    private int maxWindowId = 0;
    /**
     * 最大データ保持件数
     */
    private int maxSize=5;
    /**
     * BirdUserInfo
     */
    private BirdUserInfo birdUserInfo;
    
    /**
     * BirdDateInfo
     */
    private BirdDateInfo birdDateInfo;
    
    /**
     * 検索フォーム呼び出し時のリクエストDTO保持
     */
    private Map mapSearchFormDto = new HashMap();

    /**
     * 検索済みフラグ
     */
    private Map searchedFlg = new HashMap();
    
    /**
     * @return listSearchData を戻します。
     */
    public List getListSearchData(int windowId) {
        return (List)listSearchData.get(new Integer(windowId));
    }
    
    /**
     * 検索実行判断処理
     * 
     * @param windowId
     * @return
     */
    public boolean isMustSearch(int windowId) {
        if(isSearchedFlg(windowId) && getTaishoNengetu(windowId) != null) {
            return !isExistSearchedData(windowId);
        }
        return false;
    }

    /**
     * 指定RequestDTOへ検索済み条件値格納します。
     */
    public void getSearchedDataDto(int windowId, ClaimTotalRequestDto searchedDataDto) {
        searchedDataDto.setWindowId(windowId);
        searchedDataDto.setCompanyCd(getCompanyCd(windowId));
        searchedDataDto.setTaishoNengetu(getTaishoNengetu(windowId));
        searchedDataDto.setTaishoJoken(getTaishoJoken(windowId));
        searchedDataDto.setHyojiTaisho(getHyojiTaisho(windowId));
        searchedDataDto.setListSearchData(getListSearchData(windowId));
        searchedDataDto.setListTableHeader(getListTableHeader(windowId));
        searchedDataDto.setHyojiTaishoName(getHyojiTaishoName(windowId));
        searchedDataDto.setSearchedFlg(isSearchedFlg(windowId));
        searchedDataDto.setTaishoKikanFrom(getTaishoKiaknFrom(windowId));
        searchedDataDto.setTaishoKikanTo(getTaishoKikanTo(windowId));
    }
    /**
     * 指定RequestDTOへ検索条件をセット
     */
    public void setSearchedDataDto(ClaimTotalRequestDto searchedDataDto) {
        int windowId = searchedDataDto.getWindowId();
        setCompanyCd(windowId, searchedDataDto.getCompanyCd());
        setTaishoNengetu(windowId, searchedDataDto.getTaishoNengetu());
        setTaishoJoken(windowId, searchedDataDto.getTaishoJoken());
        setHyojiTaisho(windowId, searchedDataDto.getHyojiTaisho());
        setListSearchData(windowId, searchedDataDto.getListSearchData());
        setListTableHeader(windowId, searchedDataDto.getListTableHeader());
        setHyojiTaishoName(windowId, searchedDataDto.getHyojiTaishoName());
        setSearchedFlg(windowId, searchedDataDto.isSearchedFlg());
        setTaishoKikanFrom(windowId, searchedDataDto.getTaishoKikanFrom());
        setTaishoKikanTo(windowId, searchedDataDto.getTaishoKikanTo());
    }
    
    /**
     * 検索結果設定処理
     * 
     * @param windowId
     * @param listSearchData
     */
    public void setListSearchData(int windowId, List listSearchData) {
        // 現在ウインドウIDのデータを保持している場合
        if (this.listSearchData.containsKey(new Integer(windowId))) {
            // 順番を入れ替える為、前回データを削除する
            this.listSearchData.remove(new Integer(windowId));
        // 最大数を超えた場合
        } else if (this.listSearchData.size() >= getMaxSize()) {
            // 最古データを削除
            this.listSearchData.remove(this.listSearchData.keySet().toArray()[0]);
        }           
        this.listSearchData.put(new Integer(windowId), listSearchData);
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
     * 検索結果削除処理
     * 
     * @param windowId
     */
    public void removeListSearchData(int windowId) {
        // 現在ウインドウIDのデータを保持している場合
        if (isExistSearchedData(windowId)) {
            // 順番を入れ替える為、前回データを削除する
            this.listSearchData.remove(new Integer(windowId));
        }
    }
    /**
     * 検索データ存在判断処理
     * 
     * @param windowId
     * @return
     */
    public boolean isExistSearchedData(int windowId) {
        return listSearchData.containsKey(new Integer(windowId));
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

    public List getListAreaDai() {
        return listAreaDai;
    }

    public void setListAreaDai(List listAreaDai) {
        this.listAreaDai = listAreaDai;
    }

    public List getListJigyohonbu() {
        return listJigyohonbu;
    }

    public void setListJigyohonbu(List listJigyohonbu) {
        this.listJigyohonbu = listJigyohonbu;
    }

    public List getListSibu() {
        return listSibu;
    }

    public void setListSibu(List listSibu) {
        this.listSibu = listSibu;
    }

    public List getListSlArea() {
        return listSlArea;
    }

    public void setListSlArea(List listSlArea) {
        this.listSlArea = listSlArea;
    }

    public List getListTaishoJoken() {
        return listTaishoJoken;
    }

    public void setListTaishoJoken(List listTaishoJoken) {
        this.listTaishoJoken = listTaishoJoken;
    }

    public List getListTaishoNengetu() {
        return listTaishoNengetu;
    }

    public void setListTaishoNengetu(List listTaishoNengetu) {
        this.listTaishoNengetu = listTaishoNengetu;
    }

    public String getUserTypeCd() {
        return userTypeCd;
    }

    public void setUserTypeCd(String userTypeCd) {
        this.userTypeCd = userTypeCd;
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

    public ClaimTotalRequestDto getSearchFormDto(int windowId) {
        ClaimTotalRequestDto retDto = (ClaimTotalRequestDto) mapSearchFormDto.get(new Integer(windowId));
        mapSearchFormDto.remove(new Integer(windowId));
        return retDto;
    }

    public void setSearchFormDto(ClaimTotalRequestDto dto) {
        this.mapSearchFormDto.put(new Integer(dto.getWindowId()), dto);
    }

    public String getHyojiTaisho(int windowId) {
        return (String) hyojiTaisho.get(new Integer(windowId));
    }

    public void setHyojiTaisho(int windowId, String hyojiTaisho) {
        this.hyojiTaisho.put(new Integer(windowId), hyojiTaisho);
    }

    public String getTaishoJoken(int windowId) {
        return (String) taishoJoken.get(new Integer(windowId));
    }

    public void setTaishoJoken(int windowId, String taishoJoken) {
        this.taishoJoken.put(new Integer(windowId), taishoJoken);
    }

    public String getTaishoNengetu(int windowId) {
        return (String) taishoNengetu.get(new Integer(windowId));
    }

    public void setTaishoNengetu(int windowId, String taishoNengetu) {
        this.taishoNengetu.put(new Integer(windowId), taishoNengetu);
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getMaxWindowId() {
        return maxWindowId;
    }

    public void setMaxWindowId(int maxWindowId) {
        this.maxWindowId = maxWindowId;
    }

    public String getCompanyCd(int windowId) {
        return (String) companyCd.get(new Integer(windowId));
    }

    public void setCompanyCd(int windowId, String companyCd) {
        this.companyCd.put(new Integer(windowId), companyCd);
    }

    public List getListTableHeader(int windowId) {
        return (List) mapListTableHeader.get(new Integer(windowId));
    }

    public void setListTableHeader(int windowId, List listTableHeader) {
        this.mapListTableHeader.put(new Integer(windowId), listTableHeader);
    }

    public String getHyojiTaishoName(int windowId) {
        return (String) hyojiTaishoName.get(new Integer(windowId));
    }

    public void setHyojiTaishoName(int windowId, String hyojiTaishoName) {
        this.hyojiTaishoName.put(new Integer(windowId), hyojiTaishoName);
    }
    
    public String getTaishoKiaknFrom(int windowId) {
        return (String) taishoKikanFrom.get(new Integer(windowId));
    }

    public void setTaishoKikanFrom(int windowId, String taishoKikanFrom) {
        this.taishoKikanFrom.put(new Integer(windowId), taishoKikanFrom);
    }

    public String getTaishoKikanTo(int windowId) {
        return (String) taishoKikanTo.get(new Integer(windowId));
    }

    public void setTaishoKikanTo(int windowId, String taishoKikanTo) {
        this.taishoKikanTo.put(new Integer(windowId), taishoKikanTo);
    }
}