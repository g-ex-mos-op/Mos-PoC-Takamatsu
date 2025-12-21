package jp.co.isid.mos.bird.analysis.sibuaverage.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * 支部平均比較セッションDTO
 * @author xnkusama
 *
 */
public class SibuAverageDto {

    private BirdUserInfo birdUserInfo;
    private BirdDateInfo birdDateInfo;
    
    /**条件情報*/
    private List listTaishoJoken = new ArrayList(0);
    private List listHyojiTaisho = null;
    private List listKikanSitei = new ArrayList(0);
    //プルダウン情報：集計区分
    private Map listShukeiKbn = new HashMap(0);
    
    /**検索情報*/
    //DTO【Request情報】保持Map
    private Map mapRequestDto = new HashMap();
    //DTO【検索結果情報】保持Map
    private Map mapSearchedDataDto = new LinkedHashMap();
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
        return getBirdUserInfo().getUserID();
    }
    public String getUserTypeCd() {
        return getBirdUserInfo().getMstUser().getUserTypeCd();
    }
    public List getListKikanSitei() {
        return listKikanSitei;
    }
    public void setListKikanSitei(List listKikan) {
        this.listKikanSitei = listKikan;
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
    /**
     * 
     * @param windowId
     * @return
     */
    public SibuAverageReqDto getRequestDto(int windowId) {
        return (SibuAverageReqDto) mapRequestDto.get(new Integer(windowId));
    }
    /**
     * 
     * @param windowId
     * @param requestDto
     */
    public void setRequestDto(SibuAverageReqDto requestDto) {
    	mapRequestDto.put(new Integer(requestDto.getWindowId()), requestDto);
    }
    /**
     * 
     * @param windowId
     */
    public void removeRequestDto(int windowId) {
    	mapRequestDto.remove(new Integer(windowId));
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
    public SibuAverageReqDto getSearchedDataDto(int windowId) {
        return (SibuAverageReqDto)mapSearchedDataDto.get(new Integer(windowId));
    }

    /**
     * 検索結果設定処理
     * @param searchedDataDto
     */
    public void setSearchedDataDto(SibuAverageReqDto searchedDataDto) {
        // 現在ウインドウIDのデータを保持している場合
        if (this.mapSearchedDataDto.containsKey(new Integer(searchedDataDto.getWindowId()))) {
            // 順番を入れ替える為、前回データを削除する
            this.mapSearchedDataDto.remove(new Integer(searchedDataDto.getWindowId()));
        // 最大数を超えた場合
        } else if (this.mapSearchedDataDto.size() >= getMaxSize()) {
        	//上限件以上有る場合　
        	int removeIndex = this.mapSearchedDataDto.size()-getMaxSize();
        	Integer windowIdKey = (Integer)this.mapSearchedDataDto.keySet().toArray()[removeIndex];
            //直近から５件まえの検索結果データを削除
        	SibuAverageReqDto dataClearDto = (SibuAverageReqDto)this.mapSearchedDataDto.get(windowIdKey);
        	dataClearDto.setListData(null);
        }
        this.mapSearchedDataDto.put(new Integer(searchedDataDto.getWindowId()), searchedDataDto);
    }
    
    /**
     * 検索結果削除処理
     * @param windowId
     */
    public void removeSearchDataDto(int windowId) {
        this.mapSearchedDataDto.remove(new Integer(windowId));
    }
    
    public int getMaxSize() {
        return maxSize;
    }
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
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
	/**
	 * 集計区分プルダウンリスト取得処理
	 * 
	 * @param onerCd
	 * @return
	 */
	public List getListShukeiKbn(final String onerCd) {
		return (List)this.listShukeiKbn.get(onerCd);
	}
	/**
	 * 集計区分プルダウンリスト取得処理
	 * 
	 * @param onerCd
	 * @param listShukeiKbn
	 */
	public void setListShukeiKbn(String onerCd, List listShukeiKbn) {
		this.listShukeiKbn.put(onerCd, listShukeiKbn);
	}
	/**
	 * @return クラス変数listHyojiTaisho を戻します。
	 */
	public List getListHyojiTaisho() {
		return listHyojiTaisho;
	}
	/**
	 * @param listHyojiTaisho を クラス変数listHyojiTaishoへ設定します。
	 */
	public void setListHyojiTaisho(List listHyojiTaisho) {
		this.listHyojiTaisho = listHyojiTaisho;
	}
	/**
	 * @return クラス変数listTaishoJoken を戻します。
	 */
	public List getListTaishoJoken() {
		return listTaishoJoken;
	}
	/**
	 * @param listTaishoJoken を クラス変数listTaishoJokenへ設定します。
	 */
	public void setListTaishoJoken(List listTaishoJoken) {
		this.listTaishoJoken = listTaishoJoken;
	}
}