/*
 * 作成日: 2006/2/16
 */
package jp.co.isid.mos.bird.sysadmin.userregist.action.impl;

import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.sysadmin.userregist.action.UserRegistSelectUserTypeAction;
import jp.co.isid.mos.bird.sysadmin.userregist.dto.UserRegistDto;
import jp.co.isid.mos.bird.sysadmin.userregist.logic.SettingDataLogic;
import jp.co.isid.mos.bird.sysadmin.userroleregist.dto.SeteiKubunDto;

/**
 * 初期条件画面アクションクラス
 * @author itamoto
 */
public class UserRegistSelectUserTypeActionImpl implements UserRegistSelectUserTypeAction {

    //private static Logger logger_ = Logger.getLogger(UserRegistSelectUserTypeActionImpl.class);

    /* アクションID */
    public static String initialize_ACTION_ID = "BSA006A01";
    public static String regist_ACTION_ID     = "BSA006A02";

    /* UserRegistDto */
    private UserRegistDto userRegistDto;
    /* pullDownMenuDto */
    private PullDownMenuDto pullDownMenuDto;
    /* ログイン情報 */
    private BirdUserInfo birdUserInfo;
    /* システム日付情報 */
    private BirdDateInfo birdDateInfo;
    
// 2006/03/17 xkhata ユーザロール設定 → ユーザ登録 → ユーザロール設定時のバグ対応
    /* ユーザロール設定Dto */
    private SeteiKubunDto seteiKubunDto;
//
    private SettingDataLogic userRegistSettingDataLogic;
// end
	/**
     * 初期処理
     * @return 画面遷移情報
     */
	public String initialize() {
        if (pullDownMenuDto.isClearFlg()) {
            // ユーザ登録情報初期化
            userRegistDto = new UserRegistDto();
            pullDownMenuDto.setClearFlg(false);
// 2006/03/17 xkhata ユーザロール設定 → ユーザ登録 → ユーザロール設定時のバグ対応            
            getSeteiKubunDto().setUserId( null );
// end
        }
        return null;
    }

    /**
     * 新規登録ボタン処理
     */
    public String regist() {
		// ユーザ登録情報初期化
    	userRegistDto.clear();
        userRegistDto.setBirdDateInfo(birdDateInfo);
        userRegistDto.setBirdUserInfo(birdUserInfo);
        
        getUserRegistSettingDataLogic().execute(userRegistDto);
        userRegistDto.setUserLevel(2);

		// ユーザタイプセット
        String userType = userRegistDto.getUserType();
		return UserType.isOner(userType) ? userRegistDto.editOwner_VIEW_ID
				: UserType.isTenpo(userType) ? userRegistDto.editStore_VIEW_ID
                        : UserType.isHonbu(userType) ? userRegistDto.editHonbu_VIEW_ID
						: null;
	}
	/**
	 * @return クラス変数userRegistSettingDataLogic を戻します。
	 */
	public SettingDataLogic getUserRegistSettingDataLogic() {
		return userRegistSettingDataLogic;
	}
	/**
	 * @param userRegistSettingDataLogic を クラス変数userRegistSettingDataLogicへ設定します。
	 */
	public void setUserRegistSettingDataLogic(
			SettingDataLogic userRegistSettingDataLogic) {
		this.userRegistSettingDataLogic = userRegistSettingDataLogic;
	}
	/**
	 * ユーザ登録Dtoの設定
	 * @return userRegistDto を戻します。
	 */
	public UserRegistDto getUserRegistDto() {
		return userRegistDto;
	}
	/**
	 * ユーザ登録Dtoの設定
	 * @param userRegistDto userRegistDto を設定。
	 */
	public void setUserRegistDto(UserRegistDto userRegistDto) {
		this.userRegistDto = userRegistDto;
	}

	/**
     * 初期処理判定Dtoの設定
     * @return pullDownMenuDto を戻します。
     */
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }
    /**
     * 初期処理判定Dtoの設定
     * @param pullDownMenuDto pullDownMenuDto を設定。
     */
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }
    
	/**
	 * システム日付情報の設定
	 * @return birdDateInfo を戻します。
	 */
	public BirdDateInfo getBirdDateInfo() {
		return birdDateInfo;
	}
	/**
	 * システム日付情報の設定
	 * @param birdDateInfo birdDateInfo を設定。
	 */
	public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
		this.birdDateInfo = birdDateInfo;
	}
	/**
	 * ログイン情報の設定
	 * @return birdUserInfo を戻します。
	 */
	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}
	/**
	 * ログイン情報の設定
	 * @param birdUserInfo birdUserInfo を設定。
	 */
	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}

// 2006/03/17 xkhata ユーザロール設定 → ユーザ登録 → ユーザロール設定時のバグ対応
    /**
     * ユーザロール設定Dto 取得処理
     * @return
     */
    public SeteiKubunDto getSeteiKubunDto() {
    	return this.seteiKubunDto;
    }
    
    /**
     * ユーザロール設定Dto 設定処理
     * @param seteiKubunDto
     */
    public void setSeteiKubunDto(SeteiKubunDto seteiKubunDto) {
    	this.seteiKubunDto = seteiKubunDto;
    }
}

