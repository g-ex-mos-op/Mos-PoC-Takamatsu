/**
 *
 */
package jp.co.isid.mos.bird.commonform.miseonersearch.dto;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * [共通]店・オーナー検索画面
 * Session用DTO
 *
 * 作成日:2008/11/20
 * @author xkinu
 *
 */
public class SessionDto {
    /* 現行セッションKey */
    private String nowSessionKey;
    /* セッションKey保持Map */
    private Map sessionKey = new HashMap();
    /**
     * ログインユーザー情報
     */
    private BirdUserInfo birdUserInfo;
    /**
     * BIRD日付情報
     */
    private BirdDateInfo birdDateInfo;
//    /**
//     * 自画面SessionDTO
//     */
//    private SessionDto miseOnerSearchSesDto;
    /**
     * 最大ウィンドウID
     */
    private int maxWindowId = 0;
    /**
     * 最大データ保持件数
     */
    private int maxSize=5;
    /** リクエスト元(呼び出し元)VIEW_ID */
    private Map requesterViewId = new HashMap();
    /** リクエスト元(呼び出し元)ウィンドウID */
    private Map requesterWindowId = new HashMap();

    /**
     * [条件項目]開始メニューコード
     */
    private Map searchWord = new HashMap();
    /**
     * 検索結果
     */
    private Map listSearchData = new LinkedHashMap();


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

	/**
	 * @return menuCd を戻します。
	 */
	public String getSearchWord(int windowId) {
		return (String)searchWord.get(new Integer(windowId));
	}

	/**
	 * @param menuCd を クラス変数menuCdへ設定します。
	 */
	public void setSearchWord(int windowId, String menuCd) {
		this.searchWord.put(new Integer(windowId), menuCd);
	}

	/**
	 * リクエスト元(呼び出し元)VIEW_ID取得処理
	 *
	 * @return requesterViewId を戻します。
	 */
	public String getRequesterViewId(int windowId) {
		return (String)requesterViewId.get(new Integer(windowId));
	}

	/**
	 * リクエスト元(呼び出し元)VIEW_ID設定処理
	 *
	 * @param requesterViewId を クラス変数requesterViewIdへ設定します。
	 */
	public void setRequesterViewId(int windowId, String requesterViewId) {
		this.requesterViewId.put(new Integer(windowId), requesterViewId);
	}

	/**
	 * リクエスト元(呼び出し元)ウィンドウID取得処理
	 *
	 * @return requesterWindowId を戻します。
	 */
	public int getRequesterWindowId(int windowId) {
		return ((Integer)requesterWindowId.get(new Integer(windowId))).intValue();
	}

	/**
	 * リクエスト元(呼び出し元)ウィンドウID設定処理
	 *
	 * @param requesterWindowId を クラス変数requesterWindowIdへ設定します。
	 */
	public void setRequesterWindowId(int windowId, int requesterWindowId) {
		this.requesterWindowId.put(new Integer(windowId), new Integer(requesterWindowId));
	}
	/**
	 * @return maxSize を戻します。
	 */
	public int getMaxSize() {
		return maxSize;
	}

	/**
	 * @param maxSize 設定する maxSize。
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
	 * @param maxWindowId 設定する maxWindowId。
	 */
	public void setMaxWindowId(int maxWindowId) {
		this.maxWindowId = maxWindowId;
	}

	/**
	 * @return nowSessionKey を戻します。
	 */
	public String getNowSessionKey() {
		return nowSessionKey;
	}

	/**
	 * @param nowSessionKey 設定する nowSessionKey。
	 */
	public void setNowSessionKey(String nowSessionKey) {
		this.nowSessionKey = nowSessionKey;
	}

	/**
	 * セッションKey保持MapからセッションKeyの取得
	 * @return sessionKey を戻します。
	 */
	public String getSessionKey(int windowId) {
		return (String)sessionKey.get(new Integer(windowId));
	}
    /**
     * セッションKey保持MapへセッションKeyの設定
     * @param sessionKey sessionKey を設定。
     */
    public void setSessionKey(int windowId, String sessionKey) {
        this.sessionKey.put(new Integer(windowId), sessionKey);
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
	 * 検索済み検索条件値保持処理
	 *
	 * @param requestDto
	 */
	public void setSearchedData(RequestDto requestDto) {
		int windowId = requestDto.getWindowId();
		setSearchWord(windowId, requestDto.getSearchWord());
		setListSearchData(windowId, requestDto.getListSearchData());
	}

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

//	/**
//	 * @return miseOnerSearchSesDto を戻します。
//	 */
//	public SessionDto getMiseOnerSearchSesDto() {
//		return miseOnerSearchSesDto;
//	}
//
//	/**
//	 * @param miseOnerSearchSesDto を クラス変数miseOnerSearchSesDtoへ設定します。
//	 */
//	public void setMiseOnerSearchSesDto(SessionDto miseOnerSearchSesDto) {
//		this.miseOnerSearchSesDto = miseOnerSearchSesDto;
//	}

}
