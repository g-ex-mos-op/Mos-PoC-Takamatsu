/*
 * 作成日: 2006/2/16
 */
package jp.co.isid.mos.bird.sysadmin.userregist.action.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.sysadmin.userregist.action.UserRegistEditStoreAction;
import jp.co.isid.mos.bird.sysadmin.userregist.dto.UserRegistDto;
import jp.co.isid.mos.bird.sysadmin.userregist.logic.CheckUserLogic;

/**
 * 登録画面（店舗）アクションクラス
 * @author itamoto
 */
public class UserRegistEditStoreActionImpl implements UserRegistEditStoreAction {

//    private static Logger logger_ = Logger.getLogger(UserRegistEditOwnerActionImpl.class);

    /* アクションID */
    public static String initialize_ACTION_ID        = "BSA006A09";
    public static String regist_ACTION_ID            = "BSA006A10";
    public static String cancel_ACTION_ID            = "BSA006A11";
    public static String searchMise_ACTION_ID   = "BSA006A12";
    
    /* userRegistDto */
    private UserRegistDto userRegistDto;
    /* miseSearchDto */
    private MiseSearchDto miseSearchDto;

    /* 登録内容チェックロジック */
    private CheckUserLogic userRegistCheckUserLogic;

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
	 * 店選択Dtoの設定
	 * @return miseSearchDto を戻します。
	 */
	public MiseSearchDto getMiseSearchDto() {
		return miseSearchDto;
	}
	/**
	 * 店選択Dtoの設定
	 * @param miseSearchDto miseSearchDto を設定。
	 */
	public void setMiseSearchDto(MiseSearchDto miseSearchDto) {
		this.miseSearchDto = miseSearchDto;
	}

	/**
	 * 登録内容チェックロジックの設定
	 * @return userRegistCheckUserLogic を戻します。
	 */
	public CheckUserLogic getUserRegistCheckUserLogic() {
		return userRegistCheckUserLogic;
	}
	/**
	 * 登録内容チェックロジックの設定
	 * @param userRegistCheckUserLogic userRegistCheckUserLogic を設定。
	 */
	public void setUserRegistCheckUserLogic(CheckUserLogic checkUserLogic) {
		this.userRegistCheckUserLogic = checkUserLogic;
	}

	/**
     * 初期処理
     * @return 画面遷移情報
     */
	public String initialize() {
		// 店情報取得
		if(getMiseSearchDto().getReturnKind() != MiseSearchDto.RETURNKIND_INIT) {
			//1．店舗を選択後遷移してきた場合。
			if (MiseSearchDto.RETURNKIND_SELECT == getMiseSearchDto().getReturnKind()) {
				userRegistDto.getUserMise().setMiseCd(getMiseSearchDto().getMiseCd());
			}
            //DTO【店舗選択】.クリア処理を実行します。
            getMiseSearchDto().clear();
		}
		return null;
    }

	/**
     * 登録ボタン処理
     */
    public String regist(){
    	userRegistCheckUserLogic.execute(userRegistDto);
        return userRegistDto.confirmStore_VIEW_ID;
    }

    /**
     * 取消処理
     */
    public String cancel(){
        return userRegistDto.select_VIEW_ID;
    }

	/* 店コード選択処理 */
	public String searchMise(){
		getMiseSearchDto().setInitialFlag(true);
		getMiseSearchDto().setNeedReturnKind(true);
		getMiseSearchDto().setNavigationCase(userRegistDto.editStore_VIEW_ID);
        // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(userRegistDto.getUserCompany().getRCompanyCd());
        getMiseSearchDto().setRCompanyCdList(listCompany);
		return "BCO008V01";
	}
}

