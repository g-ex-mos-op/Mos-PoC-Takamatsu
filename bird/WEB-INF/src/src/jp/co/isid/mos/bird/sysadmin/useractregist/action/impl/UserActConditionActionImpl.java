/*
 * 作成日: 2006/02/22
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.sysadmin.useractregist.action.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.commonform.usersearch.dto.UserSearchDto;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.GamenRoleDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.dto.UserListDto;
import jp.co.isid.mos.bird.framework.entity.CtlGamenRole;
import jp.co.isid.mos.bird.framework.entity.UIBirdUser;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.logic.GetGamenRoleLogic;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.sysadmin.useractregist.action.UserActConditionAction;
import jp.co.isid.mos.bird.sysadmin.useractregist.dto.UserActConditionDto;
import jp.co.isid.mos.bird.sysadmin.useractregist.dto.UserActRegistDto;
import jp.co.isid.mos.bird.sysadmin.useractregist.logic.GetBirdUserLogic;
import jp.co.isid.mos.bird.sysadmin.useractregist.logic.GetUserInfoLogic;
import jp.co.isid.mos.bird.sysadmin.useractregist.logic.SearchMenuLogic;

/**
 * @author xkhata
 */
public class UserActConditionActionImpl implements UserActConditionAction{

    /* 画面ID */
    private final String GAMEN_ID = "BSA012";
	/* アクションID */
	public static String initialize_ACTION_ID   = "BSA012A01";
	public static String select_ACTION_ID       = "BSA012A02";
	public static String selectUserId_ACTION_ID = "BSA012A03";
	
	/* 汎用画面別ロール制御のLogic */
	private GetGamenRoleLogic getGamenRoleLogic;
	
	/* BIRDユーザの取得 */
	private GetBirdUserLogic useractregistGetBirdUserLogic;
	
	/* ユーザ情報取得 */
	private GetUserInfoLogic getUserInfoLogic;
	
	/* 機能の取得 */
	private SearchMenuLogic searchMenuLogic;
	
	/* メニュー後の初期処理 */
	private PullDownMenuDto pullDownMenuDto;
	
    /* ログイン情報 */
    private BirdUserInfo birdUserInfo;
	/** DTO【Request】 */
    private UserActConditionDto userActConditionDto;
	/** DTO【ユーザー別アクセス権限設定登録情報保持】 */
    private UserActRegistDto userActRegistDto;    
    /* ユーザ検索Dto */
    private UserSearchDto userSearchDto;
    
	/**
	 * 初期化
	 */
	public String initialize() {

		if(getPullDownMenuDto().isClearFlg()) {
			getPullDownMenuDto().setClearFlg(false);
			if ( getUserActConditionDto() != null ) {
				getUserActConditionDto().clear();
			}
			if ( getUserActRegistDto() != null ) {
				getUserActRegistDto().clear();
			}
		}

		String bunruiCd = "01";
		//String userId = getBirdUserInfo().getUserID();
		String userId = birdUserInfo.getUserID();
		
		if ( !getUserActConditionDto().getInitFlg()) {
			GamenRoleDto dto = new GamenRoleDto();
			dto.setUserId(userId);
			dto.setGamenId(GAMEN_ID);
			dto.setBunruiCd(bunruiCd);
			
			List gamenRoleList = getGetGamenRoleLogic().excute(dto);
			
			CtlGamenRole entity = (CtlGamenRole)gamenRoleList.get(0);
			
			String sKbn = entity.getSeteiKbn();
			getUserActConditionDto().setSeteiKbn(sKbn);
			//ログインユーザーがシステム管理者の場合
			if( getUserActConditionDto().isSystemAdminUser() ) {
				
			}
			//ログインユーザーがユーザー管理者の場合
			else if ( getUserActConditionDto().isUserAdminUser() ) {
			
				List resultList = getUseractregistGetBirdUserLogic().execute(
						birdUserInfo.getMstUser(), birdUserInfo.getUserOner(),getBirdUserInfo().getUserMise() );
				
				getUserActConditionDto().setAllUserList( resultList );
				
				List pullDownList = new ArrayList();
				
				for ( int i = 0; i < resultList.size(); i++ ) {
					UserListDto userDto = (UserListDto)resultList.get(i);
					pullDownList.add(new SelectItem(userDto.getUserId(),userDto.getUserId() + " " + userDto.getUserNameKj()));
				}
				getUserActConditionDto().setUserList(pullDownList);
				
			}
			//DTO【条件情報保持】.初期化フラグをtrueに設定します。
			getUserActConditionDto().setInitFlg(true);
		} else {
			if ( getUserSearchDto().isActionFlg() ) {
				if(getUserSearchDto().getReturnKind() == UserSearchDto.RETURNKIND_SELECT) {
					getUserActConditionDto().setSelectUserId(getUserSearchDto().getUserId());
					getUserActConditionDto().setSelectUserName(getUserSearchDto().getUserName());
				}
				getUserSearchDto().clear();
			}
		}
		return null;
	}

	/**
	 * ユーザの選択
	 */
	public String selectUserId() {
		getUserSearchDto().clear();
		getUserSearchDto().setInitFlag(true);
		getUserSearchDto().setNeedReturnKind(true);
		getUserSearchDto().setNavigationCase("BSA012V01");
		return "BCO003V01";
	}

	/**
	 * 機能の取得
	 */
	public String select() {
		String userId = getUserActConditionDto().getSelectUserId();
		if(!CommonUtil.isNull(userId)) {
	        //入力されたユーザーIDが空で無い場合は、その入力されたユーザーIDへ前ゼロ付加します。
	        CodeFormatter formatter = new CodeFormatter(8);
	        formatter.setFormatPattern("00000000");
	        userId = formatter.format(userId, true);
		}
		if ( getBirdUserInfo().getUserID().equals(userId) ) {
			throw new InvalidInputException("ユーザーID");
		}
		if( getUserActConditionDto().isSystemAdminUser() ) {
			List userList = getGetUserInfoLogic().execute( userId );
			UIBirdUser userEntity = (UIBirdUser)userList.get(0);
			getUserActRegistDto().setUserId( userId );
			getUserActRegistDto().setUserName( userEntity.getUserNameKj() );
		} else {
			for( Iterator i = getUserActConditionDto().getAllUserList().iterator(); i.hasNext(); ) {
				UserListDto userEntity = (UserListDto) i.next();
				if ( userId.equals(userEntity.getUserId()) ) {
					getUserActRegistDto().setUserId(userId );
					getUserActRegistDto().setUserName( userEntity.getUserNameKj() );
					break;
				}
			}
		}
		//LOGIC【登録対象メニュー検索】を実行し、
		//戻り値List[[登録対象メニュー]]をDTO【編集対象情報】.List[[登録対象メニュー]]へ設定します。
		getUserActRegistDto().setRoleList( getSearchMenuLogic().execute( userId ) );
        getUserActConditionDto().setSelectUserId(userId);
		//編集画面VIEWIDをリターンします。
		return "BSA012V02";
	}

	/**
	 * 汎用画面別ロール制御のロジック設定
	 */
	public void setGetGamenRoleLogic(GetGamenRoleLogic getGamenRoleLogic) {
		this.getGamenRoleLogic = getGamenRoleLogic;
	}

	/**
	 * 汎用画面別ロール制御のロジック取得
	 */
	public GetGamenRoleLogic getGetGamenRoleLogic() {
		return this.getGamenRoleLogic;
	}
	
	/**
	 * ユーザ情報の取得
	 * @param getUserInfoLogic
	 */
	public void setGetUserInfoLogic(GetUserInfoLogic getUserInfoLogic) {
		this.getUserInfoLogic = getUserInfoLogic;
	}
	public GetUserInfoLogic getGetUserInfoLogic() {
		return this.getUserInfoLogic;
	}
	
	/**
	 * 機能の取得
	 * @param searchMenuLogic
	 */
	public void setSearchMenuLogic(SearchMenuLogic searchMenuLogic) {
		this.searchMenuLogic = searchMenuLogic;
	}
	public SearchMenuLogic getSearchMenuLogic() {
		return this.searchMenuLogic;
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
     * BIRDログイン情報の設定
     * @return birdUserInfo を戻します。
     */
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }
    /**
     * BIRDログイン情報の設定
     * @param birdUserInfo birdUserInfo を設定。
     */
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }
    
    
    /**
     * Dto情報
     * @return
     */
    public UserActConditionDto getUserActConditionDto() {
    	return this.userActConditionDto;
    }
    public void setUserActConditionDto(UserActConditionDto userActConditionDto) {
    	this.userActConditionDto = userActConditionDto;
    }
    
    public UserActRegistDto getUserActRegistDto() {
    	return this.userActRegistDto;
    }
    public void setUserActRegistDto(UserActRegistDto userActRegistDto) {
    	this.userActRegistDto = userActRegistDto;
    }
    
    /**
     * ユーザ検索Dto
     * @return
     */
    public UserSearchDto getUserSearchDto() {
    	return this.userSearchDto;
    }
    
    public void setUserSearchDto(UserSearchDto userSearchDto) {
    	this.userSearchDto = userSearchDto;
    }
    
    public GetBirdUserLogic getUseractregistGetBirdUserLogic() {
        return useractregistGetBirdUserLogic;
    }

    public void setUseractregistGetBirdUserLogic(
            GetBirdUserLogic useractregistGetBirdUserLogic) {
        this.useractregistGetBirdUserLogic = useractregistGetBirdUserLogic;
    }
}