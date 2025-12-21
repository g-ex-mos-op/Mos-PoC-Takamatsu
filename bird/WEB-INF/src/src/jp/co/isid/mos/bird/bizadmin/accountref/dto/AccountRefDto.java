/*
 * 作成日: 2006/03/03
 */
package jp.co.isid.mos.bird.bizadmin.accountref.dto;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizadmin.common.entity.UIUser;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserCompany;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserMise;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserOner;

/**
 * @author 慮
 */
public class AccountRefDto {
	
	private UIUser userInfo;
	private UIUserCompany userCompany;
	private List listUserGyotai;
	private List listUserOner;
	private UIUserOner userOner;
	private UIUserMise userMise;

	public void clear(){
		setUserInfo(null);
		setUserCompany(null);
		setListUserGyotai(null);
		setListUserOner(null);
		setUserMise(null);
	}
	/**
	 * @return クラス変数userInfo を戻します。
	 */
	public UIUser getUserInfo() {
		return userInfo;
	}
	/**
	 * @param userInfo を クラス変数userInfoへ設定します。
	 */
	public void setUserInfo(UIUser userInfo) {
		this.userInfo = userInfo;
	}
	/**
	 * @return クラス変数listUserOner を戻します。
	 */
	public List getListUserOner() {
		return listUserOner;
	}
	/**
	 * @param listUserOner を クラス変数listUserOnerへ設定します。
	 */
	public void setListUserOner(List listUserOner) {
		this.listUserOner = listUserOner;
	}
	/**
	 * 画面表示用オーナー情報
	 * (オーナー情報時のみ使用)
	 * 
	 * TRタグ１段につき３個まで表示可能
	 * @return
	 */
	public List getUserOnerScreenList() {
		List userOnerScreenList = new ArrayList(0);
		if(getListUserOner() != null
				&& getListUserOner().size()>0) {
			//TRタグの段数を算出します。
			int trCnt = getListUserOner().size()/3+(getListUserOner().size()%3>0?1:0);
			for (int r=0; r<trCnt; r++) {
				//段数分のリストを格納します。
				userOnerScreenList.add(new ArrayList(0));
			}
			for (int i=0; i<getListUserOner().size(); i++) {
				//現行行[ユーザー対象オーナー]の格納対象Entity[段]のインデックスを算出します。
				int rowIndex = i/3;
				//格納対象List[[段]]から格納対象Entity[段]を取得します。
				List listRow = (List)userOnerScreenList.get(rowIndex);
				//格納対象Entity[段]へ現行行[ユーザー対象オーナー]を格納します。
				listRow.add(getListUserOner().get(i));
			}
		}
		return userOnerScreenList;
	}
	/**
	 * @return クラス変数userCompany を戻します。
	 */
	public UIUserCompany getUserCompany() {
		return userCompany;
	}
	/**
	 * @param userCompany を クラス変数userCompanyへ設定します。
	 */
	public void setUserCompany(UIUserCompany userCompany) {
		this.userCompany = userCompany;
	}
	/**
	 * @return クラス変数userMise を戻します。
	 */
	public UIUserMise getUserMise() {
		return userMise;
	}
	/**
	 * @param userMise を クラス変数userMiseへ設定します。
	 */
	public void setUserMise(UIUserMise userMise) {
		this.userMise = userMise;
	}
	/**
	 * @return クラス変数listUserGyotai を戻します。
	 */
	public List getListUserGyotai() {
		return listUserGyotai;
	}
	/**
	 * @param listUserGyotai を クラス変数listUserGyotaiへ設定します。
	 */
	public void setListUserGyotai(List listUserGyotai) {
		this.listUserGyotai = listUserGyotai;
	}
	/**
	 * @return クラス変数userOner を戻します。
	 */
	public UIUserOner getUserOner() {
		return userOner;
	}
	/**
	 * @param userOner を クラス変数userOnerへ設定します。
	 */
	public void setUserOner(UIUserOner userOner) {
		this.userOner = userOner;
	}
	/** ご利用停止文言 */
	public String getLabelLimitKbn(){
		return "1".equals(getUserInfo().getLimitKbn())?"あり":"なし";
	}
	/** ご利用停止文言 */
	public String getLabelStopFlg(){
		return "1".equals(getUserInfo().getStopFlg())?"ON":"OFF";
	}
	/*
	 *	ご請求フラグ  請求フラグ（1：請求対象 、2：請求対象外） 	
	 */
	public String getLabelSekyuFlg(){
		return "1".equals(getUserInfo().getSekyuFlg())?"する":"しない";
	}
}
