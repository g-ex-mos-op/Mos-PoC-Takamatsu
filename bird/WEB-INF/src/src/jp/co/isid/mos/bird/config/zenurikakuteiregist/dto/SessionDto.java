/**
 * 
 */
package jp.co.isid.mos.bird.config.zenurikakuteiregist.dto;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

/**
 * 機能設定]【売上速報前月売上確定登録】
 * Session保持データDTO
 * 
 * 作成日:2008/08/15
 * @author xkinu
 *
 */
public class SessionDto {
    /* セッションキー作成クラス生成 */
    private MakeSessionKey mkSession = new MakeSessionKey();
    /**
     * ログインユーザー情報
     */
    private BirdUserInfo birdUserInfo;
    /**
     * BIRD日付情報
     */
    private BirdDateInfo birdDateInfo;
    /**
     * 最大ウィンドウID
     */
    private int maxWindowId = 0;
    /* 現行セッションKey */
    private String nowSessionKey;
    /* 登録対象データ */
    private List listRegistData = new ArrayList(0);
    
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
	 * 登録対象データ取得処理
	 * 
	 * @return listRegistData を戻します。
	 */
	public List getListRegistData() {
		return listRegistData;
	}
	/**
	 * 登録対象データ設定処理
	 * 
	 * @param listRegistData listRegistDataへ設定します。
	 */
	public void setListRegistData(List listRegistData) {
		this.listRegistData = listRegistData;
	}
	/**
	 * @return maxWindowId を戻します。
	 */
	public int getMaxWindowId() {
		return maxWindowId;
	}
	/**
	 * @param maxWindowId maxWindowIdへ設定します。
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
	 * @param nowSessionKey nowSessionKeyへ設定します。
	 */
	public void setNowSessionKey(String nowSessionKey) {
		this.nowSessionKey = nowSessionKey;
	}
    /**
     * 操作エラー判断処理
     * 
     * 現行のウィンドウが無効の場合は false
     *                   有効の場合は true
     * @param RequestDto
     * @return
     */
    public boolean isValidSessionKey(RequestDto reqDto){
        return mkSession.isValidSessionKey( 
                     getNowSessionKey(),  reqDto.getSesstionKey() );
    }

	/**
	 * @return birdDateInfo を戻します。
	 */
	public BirdDateInfo getBirdDateInfo() {
		return birdDateInfo;
	}

	/**
	 * @param birdDateInfo birdDateInfoへ設定します。
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
	 * @param birdUserInfo birdUserInfoへ設定します。
	 */
	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}
	/**
	 * 新規セッションKey生成処理
	 * 
	 * １．クラス変数【セッションキー作成クラス】で新規セッションKeyを生成する。
	 * ２．処理７で生成した新規セッションKeyを
	 *         DTO【自画面Session】.現行セッションKeyへ設定する。
	 * ３．処理７で生成した新規セッションKeyを
	 *         処理３で設定したWindowIDをKeyに
	 *         DTO【自画面Session】.セッションKey保持Mapへ設定する。
	 * @param windowId
	 */
	public String makeSessionKey() {
		//１．クラス変数【セッションキー作成クラス】で新規セッションKeyを生成する。
		String key = mkSession._makeSessionKey();
		//２．処理７で生成した新規セッションKeyを
		//        DTO【自画面Session】.現行セッションKeyへ設定する。
		setNowSessionKey(key);
		return key;
	}
}
