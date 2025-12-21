/**
 * 
 */
package jp.co.isid.mos.bird.config.urisokuregist.dto;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.config.urisokuregist.util.UrisokuRegistUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

/**
 * 機能設定]【売上速報設定】
 * Session保持データDTO
 * 
 * 作成日:2008/08/06
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
    /* プルダウン用対象レポート年月リスト */
    private List listTaishoYm = new ArrayList(0);
    /* 対象レポート年月 */
    private String taishoYm;
    /** メイン選択タブ */
    private String selectedTabMain;
    /** サブ選択タブ */
    private String selectedTabSub;
    /* 登録対象データ */
    private List listRegistData = new ArrayList(0);
    /** メニュー選択対象メニューインデックス */
    private int selectedSearchMenuIndex;
    private ApplicationException checkException;
	/**
	 * @return checkException を戻します。
	 */
	public ApplicationException getCheckException() {
		return checkException;
	}

	/**
	 * @param checkException を クラス変数checkExceptionへ設定します。
	 */
	public void setCheckException(ApplicationException checkException) {
		this.checkException = checkException;
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
     * 
     * @param RequestDto
     * @return
     */
    public boolean isValidSessionKey(RequestDto reqDto){
        return mkSession.isValidSessionKey( 
                     getNowSessionKey(),  reqDto.getSesstionKey() );
    }

	/**
	 * @return listTaishoYm を戻します。
	 */
	public List getListTaishoYm() {
		return listTaishoYm;
	}

	/**
	 * @param listTaishoYm listTaishoYmへ設定します。
	 */
	public void setListTaishoYm(List listTaishoYm) {
		this.listTaishoYm = listTaishoYm;
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
	 * 前月店舗数編集情報取得処理
	 * 
	 * @return
	 */
	public List getListRegistDataMiseCnt() {
		for(int i=0; i<getListRegistData().size(); i++) {
			UILists uiRegistData = (UILists)getListRegistData().get(i);
			if(UrisokuRegistUtil.VIEW_ID_EDIT_MISECNT.equals(uiRegistData.getKey())) {
				return uiRegistData.getListData();
			}
		}
		return null;
	}
	/**
	 * TVCM放映日編集情報取得処理
	 * 
	 * @return
	 */
	public List getListRegistDataTvcm() {
		for(int i=0; i<getListRegistData().size(); i++) {
			UILists uiRegistData = (UILists)getListRegistData().get(i);
			if(UrisokuRegistUtil.VIEW_ID_EDIT_TVCM.equals(uiRegistData.getKey())) {
				return uiRegistData.getListData();
			}
		}
		return null;
	}
	/**
	 * 商品メニュー編集情報取得処理
	 * 
	 * @return
	 */
	public List getListRegistDataMenu() {
		for(int i=0; i<getListRegistData().size(); i++) {
			UILists uiRegistData = (UILists)getListRegistData().get(i);
			if(UrisokuRegistUtil.VIEW_ID_EDIT_MENU.equals(uiRegistData.getKey())) {
				return uiRegistData.getListData();
			}
		}
		return null;
	}
	/**
	 * キャンペーン編集情報取得処理
	 * 
	 * @return
	 */
	public List getListRegistDataCamp() {
		for(int i=0; i<getListRegistData().size(); i++) {
			UILists uiRegistData = (UILists)getListRegistData().get(i);
			if(UrisokuRegistUtil.VIEW_ID_EDIT_CAMP.equals(uiRegistData.getKey())) {
				return uiRegistData.getListData();
			}
		}
		return null;
	}
	/**
	 * キャンペーン編集情報存在判断処理
	 * 
	 * @return
	 */
	public boolean isExistListRegistDataCamp() {
		if(getListRegistDataCamp() != null && getListRegistDataCamp().size()>0) {
			return true;
		}
		return false;
	}

	/**
	 * @return selectedSearchMenuIndex を戻します。
	 */
	public int getSelectedSearchMenuIndex() {
		return selectedSearchMenuIndex;
	}

	/**
	 * @param selectedSearchMenuIndex を クラス変数selectedSearchMenuIndexへ設定します。
	 */
	public void setSelectedSearchMenuIndex(int selectedSearchMenuIndex) {
		this.selectedSearchMenuIndex = selectedSearchMenuIndex;
	}

	/**
	 * @return selectedTabMain を戻します。
	 */
	public String getSelectedTabMain() {
		return selectedTabMain;
	}

	/**
	 * @param selectedTabMain を クラス変数selectedTabMainへ設定します。
	 */
	public void setSelectedTabMain(String selectedTabMain) {
		this.selectedTabMain = selectedTabMain;
	}

	/**
	 * @return selectedTabSub を戻します。
	 */
	public String getSelectedTabSub() {
		return selectedTabSub;
	}

	/**
	 * @param selectedTabSub を クラス変数selectedTabSubへ設定します。
	 */
	public void setSelectedTabSub(String selectedTabSub) {
		this.selectedTabSub = selectedTabSub;
	}

	/**
	 * @return taishoYm を戻します。
	 */
	public String getTaishoYm() {
		return taishoYm;
	}

	/**
	 * @param taishoYm を クラス変数taishoYmへ設定します。
	 */
	public void setTaishoYm(String taishoYm) {
		this.taishoYm = taishoYm;
	}
	/**
	 * 新規セッションKey生成処理
	 * 
	 * １．クラス変数【セッションキー作成クラス】で新規セッションKeyを生成する。
	 * ２．処理７で生成した新規セッションKeyを
	 *         DTO【自画面Session】.現行セッションKeyへ設定する。
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
