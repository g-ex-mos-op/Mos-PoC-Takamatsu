/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.ordertimerequired.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * セッションDTO
 * 
 * 作成日:2009/10/15
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
     * [条件項目]店舗リスト
     */
    private List listMise = new ArrayList(0);
    /**
     * 期間プルダウンリスト
     * 
     */ 
    private List listKikan = new ArrayList(0);
    /**
     * 最大ウィンドウID
     */
    private int maxWindowId = 0;
    /**
     * 最大データ保持件数
     */
    private int maxSize=5;

    /**
     * 共通画面遷移時のDTO保持オブジェクト
     */
    private Map holdReqDto = new HashMap();
    /**
	 * @return holdReqDto を戻します。
	 */
	public RequestDto getHoldReqDto(int windowId) {
		return (RequestDto)holdReqDto.get(new Integer(windowId));
	}

	/**
	 * @param holdReqDto を クラス変数holdReqDtoへ設定します。
	 */
	public void setHoldReqDto(int windowId, RequestDto holdReqDto) {
		this.holdReqDto.put(new Integer(windowId), holdReqDto);
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
	/**
	 * ログインユーザータイプコード取得処理
	 * 
	 * @return
	 */
	public String getUserTypeCd() {
		return getBirdUserInfo().getMstUser().getUserTypeCd();
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
	 * @return listKikan を戻します。
	 */
	public List getListKikan() {
		return listKikan;
	}

	/**
	 * @param listKikan を クラス変数listKikanへ設定します。
	 */
	public void setListKikan(List listKikan) {
		this.listKikan = listKikan;
	}

	/**
	 * @return listMise を戻します。
	 */
	public List getListMise() {
		return listMise;
	}

	/**
	 * @param listMise を クラス変数listMiseへ設定します。
	 */
	public void setListMise(List listMise) {
		this.listMise = listMise;
	}

}
