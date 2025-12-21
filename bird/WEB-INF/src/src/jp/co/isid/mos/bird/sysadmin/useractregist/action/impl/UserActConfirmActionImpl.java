/**
 * 
 */
package jp.co.isid.mos.bird.sysadmin.useractregist.action.impl;

import java.util.HashMap;
import java.util.Map;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;
import jp.co.isid.mos.bird.sysadmin.useractregist.action.UserActConfirmAction;
import jp.co.isid.mos.bird.sysadmin.useractregist.dto.UserActConditionDto;
import jp.co.isid.mos.bird.sysadmin.useractregist.dto.UserActRegistDto;
import jp.co.isid.mos.bird.sysadmin.useractregist.logic.UpdateUserActLogic;

/**
 * ユーザー別アクセス権限設定（確認画面）アクション
 * 
 * 2009/12に確認画面が追加されました。その前は確認画面は有りませんでした。
 * 
 * 作成日:2009/12/16
 * @author xkinu
 *
 */
public class UserActConfirmActionImpl implements UserActConfirmAction {
	/** アクションID:戻る */
	public static String initialize_ACTION_ID = "BSA012A21";
	/** アクションID:戻る */
	public static String back_ACTION_ID = "BSA012A22";
	/** アクションID:登録 */
	public static String regist_ACTION_ID = "BSA012A23";
	/** BIRDログインユーザー情報 */
	private BirdUserInfo birdUserInfo;
	/** DTO【メニュー情報】 */
	private PullDownMenuDto pullDownMenuDto;
	/** DTO【Request】 */
    private UserActConditionDto userActConditionDto;
	/** DTO【ユーザー別アクセス権限設定登録情報保持】 */
    private UserActRegistDto userActRegistDto;
    /** LOGIC【ユーザー別アクセス権限登録】*/
    private UpdateUserActLogic userActUpdateLogic;
    /* セッションキー */
    private MakeSessionKey sessionKey = new MakeSessionKey();

    /* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.sysadmin.useractregist.action.UserActConfirmAction#initialize()
	 */
	public String initialize() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.sysadmin.useractregist.action.UserActConfirmAction#back()
	 */
	public String back() {
		// TODO 自動生成されたメソッド・スタブ
		return "BSA012V02";
	}
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.sysadmin.useractregist.action.UserActConfirmAction#regist()
	 */
	public String regist() {
        //セッションキーチェック
        if (!sessionKey.isValidSessionKey(
        		  getUserActRegistDto().getSessionKey()
        		, getUserActConditionDto().getSessionKey())) 
        {
            return CommonUtil.operationErr_VIEW_ID;
        }
		Map listMap = new HashMap();
		
		listMap.put("userId", getBirdUserInfo().getUserID() );
		listMap.put("menuInfo", getUserActRegistDto().getRoleList() );
		listMap.put("actUserId", getUserActRegistDto().getUserId() );
		
		getUserActUpdateLogic().execute(listMap);
		
		getPullDownMenuDto().setClearFlg( true );
		return "BSA012V01";
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
	 * @return userActRegistDto を戻します。
	 */
	public UserActRegistDto getUserActRegistDto() {
		return userActRegistDto;
	}
	/**
	 * @param userActRegistDto を クラス変数userActRegistDtoへ設定します。
	 */
	public void setUserActRegistDto(UserActRegistDto userActRegistDto) {
		this.userActRegistDto = userActRegistDto;
	}
	/**
	 * @return pullDownMenuDto を戻します。
	 */
	public PullDownMenuDto getPullDownMenuDto() {
		return pullDownMenuDto;
	}
	/**
	 * @param pullDownMenuDto を クラス変数pullDownMenuDtoへ設定します。
	 */
	public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
		this.pullDownMenuDto = pullDownMenuDto;
	}
	/**
	 * LOGIC【ユーザー別アクセス権限登録】取得処理
	 * 
	 * @return userActUpdateActLogic を戻します。
	 */
	public UpdateUserActLogic getUserActUpdateLogic() {
		return userActUpdateLogic;
	}
	/**
	 * LOGIC【ユーザー別アクセス権限登録】設定処理
	 * 
	 * @param logic を クラス変数userActUpdateActLogicへ設定します。
	 */
	public void setUserActUpdateActLogic(UpdateUserActLogic logic) {
		this.userActUpdateLogic = logic;
	}
	/**
	 * @return クラス変数userActConditionDto を戻します。
	 */
	public UserActConditionDto getUserActConditionDto() {
		return userActConditionDto;
	}
	/**
	 * @param userActConditionDto を クラス変数userActConditionDtoへ設定します。
	 */
	public void setUserActConditionDto(UserActConditionDto userActConditionDto) {
		this.userActConditionDto = userActConditionDto;
	}
}
