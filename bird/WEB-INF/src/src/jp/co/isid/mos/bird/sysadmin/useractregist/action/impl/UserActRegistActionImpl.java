/*
 * 作成日: 2006/02/27
 */
package jp.co.isid.mos.bird.sysadmin.useractregist.action.impl;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;
import jp.co.isid.mos.bird.sysadmin.useractregist.action.UserActRegistAction;
import jp.co.isid.mos.bird.sysadmin.useractregist.dto.UserActConditionDto;
import jp.co.isid.mos.bird.sysadmin.useractregist.dto.UserActRegistDto;

/**
 * ユーザー別アクセス権限設定（編集画面）アクションインターフェース
 * 
 * @author xkhata
 *
 */
public class UserActRegistActionImpl implements UserActRegistAction{

	public static String initialize_ACTION_ID = "BSA012A04";
	public static String confirm_ACTION_ID = "BSA012A05";
	public static String back_ACTION_ID = "BSA012A06";
	
	/* メニュー後の初期処理 */
	private PullDownMenuDto pullDownMenuDto;
	/** DTO【Request】 */
    private UserActConditionDto userActConditionDto;
	/** DTO【ユーザー別アクセス権限設定登録情報保持】 */
    private UserActRegistDto userActRegistDto;
    /* セッションキー */
    private MakeSessionKey sessionKey = new MakeSessionKey();
    /**
     * 初期化
     */   
	public String initialize() {
        //１．セッションキー作成・保持行います。
        createSessionKey();
		return null;
	}
	/**
	 * 取消処理
	 */
	public String back() {
		getPullDownMenuDto().setClearFlg( true );
		return "BSA012V01";
	}
    /* (非 Javadoc)
     * @see jp.co.isid.mos.bird.sysadmin.useractregist.action.UserActRegistAction#comfirm()
     */
    public String confirm() {
        //セッションキーチェック
        if (!sessionKey.isValidSessionKey(getUserActRegistDto().getSessionKey(), getUserActConditionDto().getSessionKey())) {
            return CommonUtil.operationErr_VIEW_ID;
        }
    	// TODO 自動生成されたメソッド・スタブ
    	return "BSA012V03";
    }
    
	
	/**
	 * メニュー後の初期処理判定
	 * 
	 * @return
	 */
	public PullDownMenuDto getPullDownMenuDto() {
		return pullDownMenuDto;
	}

	public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
		this.pullDownMenuDto = pullDownMenuDto;
	}    
    /**
     * セッションキー作成・保持
     */
    private void createSessionKey() {
        String key = sessionKey._makeSessionKey();
        //DTO【Request用】*/
        getUserActConditionDto().setSessionKey(key);
        //DTO【Session用】*/
        getUserActRegistDto().setSessionKey(key);
    }
	/**
	 * @return クラス変数userActRegistDto を戻します。
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
