/*
 * 作成日: 2006/2/16
 */
package jp.co.isid.mos.bird.sysadmin.userregist.action.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.sysadmin.userregist.action.UserRegistConfirmStoreAction;
import jp.co.isid.mos.bird.sysadmin.userregist.dto.UserRegistDto;
import jp.co.isid.mos.bird.sysadmin.userregist.logic.GetUserRoleLogic;
import jp.co.isid.mos.bird.sysadmin.userregist.logic.UpdateUserLogic;
import jp.co.isid.mos.bird.sysadmin.userregist.logic.impl.GetUserRoleLogicImpl;
import jp.co.isid.mos.bird.sysadmin.userroleregist.action.GetBirdUserAction;
import jp.co.isid.mos.bird.sysadmin.userroleregist.dto.UserRoleRegistDto;

/**
 * 確認画面（店舗）アクションクラス
 * @author itamoto
 */
public class UserRegistConfirmStoreActionImpl implements UserRegistConfirmStoreAction {

//    private static Logger logger_ = Logger.getLogger(UserRegistConfirmStoreActionImpl.class);

    /* アクションID */
    public static String initialize_ACTION_ID        = "BSA006A17";
    public static String regist_ACTION_ID            = "BSA006A18";
    public static String setupUserRoll_ACTION_ID     = "BSA006A19";
    public static String cancel_ACTION_ID            = "BSA006A20";
    
    /* userRegistDto */
    private UserRegistDto userRegistDto;
    /* userRoleRegistDto */
    private UserRoleRegistDto userRoleRegistDto;

    /* ユーザ情報の更新Logic */
    private UpdateUserLogic userRegistLogic;
    /* ログイン情報 */
    private BirdUserInfo birdUserInfo;
    /* システム日付情報 */
    private BirdDateInfo birdDateInfo;
    
    /* ユーザロール設定起動Action */
    private GetBirdUserAction getBirdUserAction;
    
    /** 2007/02/01 add start xamaruyama */
    private GetUserRoleLogic userRegistGetUserRoleLogic;
    
    /* 契約タイプが店舗の場合 */
    private String keiyakuTenpo;
    
    private String miseCd;
    /** 2007/02/01 add end xamaruyama */

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
	 * @return userRegistLogic を戻します。
	 */
	public UpdateUserLogic getUserRegistLogic() {
		return userRegistLogic;
	}
	/**
	 * ユーザ情報の更新ロジックの設定
	 * @param userRegistLogic userRegistLogic を設定。
	 */
	public void setUserRegistLogic(UpdateUserLogic updateUserLogic) {
		this.userRegistLogic = updateUserLogic;
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
        
/*************************** 2007/02/02 add start xamaruyama ***************************/
        Map roleParam = new HashMap();
        List arrCompanyCd = new ArrayList();
        
        // １．選択された企業コードの設定
        if (!CommonUtil.isNull(getUserRegistDto().getUserMise().getCompanyCd())) {
            arrCompanyCd.add(getUserRegistDto().getUserMise().getCompanyCd());
        }
        // ２．選択された企業コード、システム日付をMapに格納
        roleParam.put(GetUserRoleLogicImpl.PK_ARR_COMPANY_CD, arrCompanyCd);
        roleParam.put(GetUserRoleLogicImpl.PK_SYSDATE, birdDateInfo.getSysDate());
        // 契約タイプが店舗のため、keiyakuTenpo("03")をkeiyakuChoiceに代入。
        // (パラメータkeiyakuChoiceはdiconに定義)
        roleParam.put(GetUserRoleLogicImpl.PK_KEIYAKU_CHOICE, keiyakuTenpo);
        // 店舗コード取得
        roleParam.put(GetUserRoleLogicImpl.PK_MISE_CD, getUserRegistDto().getUserMise().getMiseCd());
        
        // ３．ロールコードを取得するロジック（GetUserRoleLogic）に入り、結果をDTOにセットする（List型）。
        getUserRegistDto().setRoleCode(getUserRegistGetUserRoleLogic().execute(roleParam));
        
/*************************** 2007/02/02 add end xamaruyama ***************************/
        
    	userRegistLogic.execute(userRegistDto);
        
    	// 情報クリア
    	userRegistDto.clear();
		return userRegistDto.select_VIEW_ID;
    }

    /**
     * ユーザを登録してユーザロール設定画面へ
     */
    public String setupUserRoll(){
    	// 登録
        userRegistDto.setBirdDateInfo(birdDateInfo);
        userRegistDto.setBirdUserInfo(birdUserInfo);
    	userRegistLogic.execute(userRegistDto);

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
        return userRegistDto.editStore_VIEW_ID;
    }

    /** 2007/02/01 add start xamaruyama */
    
    public GetUserRoleLogic getUserRegistGetUserRoleLogic() {
        return userRegistGetUserRoleLogic;
    }
    public void setUserRegistGetUserRoleLogic(GetUserRoleLogic getUserRoleLogic) {
        this.userRegistGetUserRoleLogic = getUserRoleLogic;
    }
    public String getKeiyakuTenpo() {
        return keiyakuTenpo;
    }
    public void setKeiyakuTenpo(String keiyakuTenpo) {
        this.keiyakuTenpo = keiyakuTenpo;
    }
    public String getMiseCd() {
        return miseCd;
    }
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }

    /** 2007/02/01 add end xamaruyama */

}