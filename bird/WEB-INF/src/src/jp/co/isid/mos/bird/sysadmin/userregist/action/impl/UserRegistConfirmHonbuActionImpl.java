/*
 * 作成日: 2006/2/16
 */
package jp.co.isid.mos.bird.sysadmin.userregist.action.impl;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.sysadmin.userregist.action.UserRegistConfirmOwnerAction;
import jp.co.isid.mos.bird.sysadmin.userregist.dto.UserRegistDto;
import jp.co.isid.mos.bird.sysadmin.userregist.logic.UpdateUserLogic;
import jp.co.isid.mos.bird.sysadmin.userroleregist.action.GetBirdUserAction;
import jp.co.isid.mos.bird.sysadmin.userroleregist.dto.UserRoleRegistDto;

/**
 * 確認画面（オーナー）アクションクラス
 * @author itamoto
 */
public class UserRegistConfirmHonbuActionImpl implements UserRegistConfirmOwnerAction {

//    private static Logger logger_ = Logger.getLogger(UserRegistConfirmHonbuActionImpl.class);

    /* アクションID */
    public static String initialize_ACTION_ID        = "BSA006A13";
    public static String regist_ACTION_ID            = "BSA006A14";
    public static String setupUserRoll_ACTION_ID     = "BSA006A15";
    public static String cancel_ACTION_ID            = "BSA006A16";
    
    /* userRegistDto */
    private UserRegistDto userRegistDto;
    /* userRoleRegistDto */
    private UserRoleRegistDto userRoleRegistDto;
    
    /* ユーザ情報の更新Logic */
    private UpdateUserLogic updateUserLogic;
    /* ログイン情報 */
    private BirdUserInfo birdUserInfo;
    /* システム日付情報 */
    private BirdDateInfo birdDateInfo;
    
    /* ユーザロール設定起動Action */
    private GetBirdUserAction getBirdUserAction;
    
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
	 * ユーザロール設定Dtoの設定
	 * @return userRoleRegistDto を戻します。
	 */
	public UserRoleRegistDto getUserRoleRegistDto() {
		return userRoleRegistDto;
	}
	/**
	 * ユーザロール設定Dtoの設定
	 * @param userRoleRegistDto userRoleRegistDto を設定。
	 */
	public void setUserRoleRegistDto(UserRoleRegistDto userRoleRegistDto) {
		this.userRoleRegistDto = userRoleRegistDto;
	}

	/**
	 * ユーザ情報の更新ロジックの設定
	 * @return updateUserLogic を戻します。
	 */
	public UpdateUserLogic getUpdateUserLogic() {
		return updateUserLogic;
	}
	/**
	 * ユーザ情報の更新ロジックの設定
	 * @param updateUserLogic updateUserLogic を設定。
	 */
	public void setUpdateUserLogic(UpdateUserLogic updateUserLogic) {
		this.updateUserLogic = updateUserLogic;
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

	/**
	 * 日付情報の設定
	 * @return birdDateInfo を戻します。
	 */
	public BirdDateInfo getBirdDateInfo() {
		return birdDateInfo;
	}
	/**
	 * 日付情報の設定
	 * @param birdDateInfo birdDateInfo を設定。
	 */
	public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
		this.birdDateInfo = birdDateInfo;
	}

	/**
	 * ユーザロールActionの設定
	 * @return getBirdUserAction を戻します。
	 */
	public GetBirdUserAction getGetBirdUserAction() {
		return getBirdUserAction;
	}
	/**
	 * ユーザロールActionの設定
	 * @param getBirdUserAction getBirdUserAction を設定。
	 */
	public void setGetBirdUserAction(GetBirdUserAction getBirdUserAction) {
		this.getBirdUserAction = getBirdUserAction;
	}

	/**
     * 初期処理
     * @return 画面遷移情報
     */
	public String initialize() {
        return null;
    }

    /**
     * 新規登録処理
     */
    public String regist(){
    	// 登録
        userRegistDto.setBirdDateInfo(birdDateInfo);
        userRegistDto.setBirdUserInfo(birdUserInfo);
    	updateUserLogic.execute(userRegistDto);
    	// 情報クリア
    	userRegistDto.clear();
		return userRegistDto.select_VIEW_ID;
    }

    /**
     * ユーザを登録してユーザロール設定画面へ（オーナー）
     */
    public String setupUserRoll(){
    	// 登録
        userRegistDto.setBirdDateInfo(birdDateInfo);
        userRegistDto.setBirdUserInfo(birdUserInfo);
    	updateUserLogic.execute(userRegistDto);

    	// ユーザロールDtoにユーザIDを設定
    	userRoleRegistDto.setUserId(userRegistDto.getUIUser().getUserId());
    	// 登録情報クリア
    	userRegistDto.clear();
    	return getBirdUserAction.viewUserRole();
    }

    /**
	 * 取消処理
	 */
    public String cancel() {
        return userRegistDto.editHonbu_VIEW_ID;
    }
}

