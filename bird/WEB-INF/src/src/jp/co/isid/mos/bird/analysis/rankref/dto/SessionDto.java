/**
 * 
 */
package jp.co.isid.mos.bird.analysis.rankref.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * 売上ランク画面
 * SessionDto
 * 
 * 作成日:2008/10/20
 * @author xkinu
 *
 */
public class SessionDto {
    /**
     * ログインユーザー情報
     */
    private BirdUserInfo birdUserInfo;
    /**
     * BIRD日付情報
     */
    private BirdDateInfo birdDateInfo;
    
    /**
     * 会社リスト
     */
    private List listCompany = new ArrayList();
    /**
     * 期間指定リスト
     */
    private List listKikanSitei = new ArrayList();
    /**
     * 最大ウィンドウID
     */
    private int maxWindowId = 0;
    /**
     * 最大データ保持件数
     */
    private int maxSize=5;
    /**
     * 会社コード
     */ 
    private Map companyCd= new HashMap();
    /**
     * 店舗種別
     */ 
    private Map tenpoShubetu= new HashMap();
    /**
     * 前年データ種別
     */ 
    private Map zennenDataShubetu= new HashMap();
    /**
     * 対象期間
     */ 
    private Map taishoKikan= new HashMap();
    /**
     * 期間指定
     */ 
    private Map kikanSitei = new HashMap();
    /**
     * 期別期報区分
     */ 
    private Map kibetuKbn = new HashMap();
    /**
     * ランク
     */
    private Map rankType = new HashMap();
    /**
     * ランク(対象)
     */
    private Map rankTarget = new HashMap();

    /**
     * 検索結果
     */
    private Map listSearchData = new LinkedHashMap();

    /**
	 * @return birdDateInfo を戻します。
	 */
	public BirdDateInfo getBirdDateInfo() {
		return birdDateInfo;
	}

	/**
	 * @param birdDateInfo を クラス変数birdDateInfoへ設定します。
	 */
	public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
		this.birdDateInfo = birdDateInfo;
	}

	/**
	 * @return birdUserInfo を戻します。
	 */
	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}

	/**
	 * @param birdUserInfo を クラス変数birdUserInfoへ設定します。
	 */
	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}
	/**
	 * ログインユーザータイプコード取得処理
	 * 
	 * @return
	 */
	public String getUserTypeCd() {
		return getBirdUserInfo().getMstUser().getUserTypeCd();
	}
	/**
	 * @return listCompany を戻します。
	 */
	public List getListCompany() {
		return listCompany;
	}

	/**
	 * @param listCompany を クラス変数listCompanyへ設定します。
	 */
	public void setListCompany(List listCompany) {
		this.listCompany = listCompany;
	}

	/**
	 * @return maxSize を戻します。
	 */
	public int getMaxSize() {
		return maxSize;
	}

	/**
	 * @param maxSize を クラス変数maxSizeへ設定します。
	 */
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	/**
	 * @return maxWindowId を戻します。
	 */
	public int getMaxWindowId() {
		return maxWindowId;
	}

	/**
	 * @param maxWindowId を クラス変数maxWindowIdへ設定します。
	 */
	public void setMaxWindowId(int maxWindowId) {
		this.maxWindowId = maxWindowId;
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
	 * 会社コード取得処理
	 * @param windowId
	 * @return companyCd を戻します。
	 */
	public String getCompanyCd(int windowId) {
		return (String)companyCd.get(new Integer(windowId));
	}

	/**
	 * 会社コード設定処理
	 * 
	 * @param windowId
	 * @param companyCd 設定する companyCd。
	 */
	public void setCompanyCd(int windowId, String companyCd) {
		this.companyCd.put(new Integer(windowId), companyCd);
	}
	
	/**
	 * 店舗種別取得処理
	 * @param windowId
	 * @return companyCd を戻します。
	 */
	public String getTenpoShubetu(int windowId) {
		return (String)tenpoShubetu.get(new Integer(windowId));
	}

	/**
	 * 店舗種別設定処理
	 * 
	 * @param windowId
	 * @param companyCd 設定する companyCd。
	 */
	public void setTenpoShubetu(int windowId, String tenpoShubetu) {
		this.tenpoShubetu.put(new Integer(windowId), tenpoShubetu);
	}

	/**
	 * @return listSearchData を戻します。
	 */
	public List getListSearchData(int windowId) {
		return (List)listSearchData.get(new Integer(windowId));
	}

	/**
	 * 検索結果設定処理
	 * 
	 * @param windowId
	 * @param listSearchData
	 */
	public void setListSearchData(int windowId, List listSearchData) {
        // 現在ウインドウIDのデータを保持している場合
        if (isExistSearchedData(windowId)) {
            // 順番を入れ替える為、前回データを削除する
        	removeListSearchData(windowId);
        // 最大数を超えた場合
        } else if (this.listSearchData.size() >= getMaxSize()) {
            // 最古データを削除
            this.listSearchData.remove(this.listSearchData.keySet().toArray()[0]);
        }       	
    	this.listSearchData.put(new Integer(windowId), listSearchData);
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
     * 検索実行判断処理
     * 
     * @param windowId
     * @return
     */
    public boolean isMustSearch(int windowId) {
    	if(isSearched(windowId)) {
    		return !isExistSearchedData(windowId);
    	}
    	return false;
    }
    /**
     * 検索済み判断処理
     * 
     * @param windowId
     * @return
     */
    public boolean isSearched(int windowId) {
    	return (companyCd.containsKey(new Integer(windowId)) && !CommonUtil.isNull(getCompanyCd(windowId)));
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
	 * ランク取得処理
	 * @param windowId
	 * @return ベスト１００ or ワースト１００
	 */
	public String getRankType(int windowId) {
		return (String)rankType.get(new Integer(windowId));
	}

	/**
	 * ランク設定処理
	 * @param windowId
	 * @param rank
	 */
	public void setRankType(int windowId, String rank) {
		this.rankType.put(new Integer(windowId), rank);
	}
	/**
	 * ランク(対象)取得処理
	 * @param windowId
	 * @return　売上：客数：前年比(売上)：前年比(客数)
	 */
	public String getRankTarget(int windowId) {
		return (String)rankTarget.get(new Integer(windowId));
	}

	/**
	 * ランク(対象)設定処理
	 * @param windowId
	 * @param rank
	 */
	public void setRankTarget(int windowId, String rank) {
		this.rankTarget.put(new Integer(windowId), rank);
	}
	/**
	 * 対象期間取得処理
	 * @return taishoKikan を戻します。
	 */
	public String getTaishoKikan(int windowId) {
		return (String)taishoKikan.get(new Integer(windowId));
	}

	/**
	 * 対象期間設定処理
	 * @param taishoKikan を クラス変数taishoKikanへ設定します。
	 */
	public void setTaishoKikan(int windowId, String taishoKikan) {
		this.taishoKikan.put(new Integer(windowId), taishoKikan);
	}
	/**
	 * 期間指定取得処理
	 * 
	 * @return kikanSitei を戻します。
	 */
	public String getKikanSitei(int windowId) {
		return (String)kikanSitei.get(new Integer(windowId));
	}

	/**
	 * 期間指定設定処理
	 * 
	 * @param kikanSitei を クラス変数kikanSiteiへ設定します。
	 */
	public void setKikanSitei(int windowId, String kikanSitei) {
		this.kikanSitei.put(new Integer(windowId), kikanSitei);
	}
	/**
	 * 期別期報区分取得処理
	 * 
	 * @return kikanSitei を戻します。
	 */
	public String getKibetuKbn(int windowId) {
		return (String)kibetuKbn.get(new Integer(windowId));
	}

	/**
	 * 期別期報区分設定処理
	 * 
	 * @param kibetuKbn を クラス変数kibetuKbnへ設定します。
	 */
	public void setKibetuKbn(int windowId, String kibetuKbn) {
		this.kibetuKbn.put(new Integer(windowId), kibetuKbn);
	}

	/**
	 * @return listKikanSitei を戻します。
	 */
	public List getListKikanSitei() {
		return listKikanSitei;
	}

	/**
	 * @param listKikanSitei を クラス変数listKikanSiteiへ設定します。
	 */
	public void setListKikanSitei(List listKikanSitei) {
		this.listKikanSitei = listKikanSitei;
	}

	/**
	 * 前年データ種別取得処理
	 * 
	 * @return zennenDataShubetu を戻します。
	 */
	public String getZennenDataShubetu(int windowId) {
		return (String)zennenDataShubetu.get(new Integer(windowId));
	}

	/**
	 * 前年データ種別設定処理
	 * 
	 * @param zennenDataShubetu を クラス変数zennenDataShubetuへ設定します。
	 */
	public void setZennenDataShubetu(int windowId, String zennenDataShubetu) {
		this.zennenDataShubetu.put(new Integer(windowId), zennenDataShubetu);
	}
}
