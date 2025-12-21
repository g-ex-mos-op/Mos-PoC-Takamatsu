/*
 * 作成日: 2006/02/10
 * 更新日：2006/09/12　miyagi～ログインユーザー自体をプルダウンに追加
 *
 */
package jp.co.isid.mos.bird.bizadmin.accountedit.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizadmin.accountedit.action.GetUserAction;
import jp.co.isid.mos.bird.bizadmin.accountedit.dto.AccountEditRequestDto;
import jp.co.isid.mos.bird.bizadmin.accountedit.dto.AccounteditDto;
import jp.co.isid.mos.bird.bizadmin.accountedit.logic.GetRevisionDataLogic;
import jp.co.isid.mos.bird.bizadmin.common.code.BizAdminConst;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUser;
import jp.co.isid.mos.bird.bizadmin.common.logic.GetBirdUserSelfLogic;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.commonform.usersearch.dto.UserSearchDto;
import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.BirdUserListDto;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.GamenRoleDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.CtlGamenRole;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.logic.GetGamenRoleLogic;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;


/**
 * 初期画面アクション
 * 
 * @author 慮
 * 更新日:2009/12/14 xkinu ロック解除機能追加
 */
public class GetUserActionImpl implements GetUserAction, CommonAction {
	
    public static final String initialize_ACTION_ID = "BBA001A01"; 
    public static final String userSearch_ACTION_ID = "BBA001A02"; 
    public static final String edit_ACTION_ID = "BBA001A03"; 
    
	private GamenRoleDto gamenRoleDto;
	private AccounteditDto accounteditDto;
	/** DTO【リクエスト用】*/
	private AccountEditRequestDto accounteditReqDto;
    
	private BirdUserInfo birdUserInfo;
	private BirdUserListDto birdUserListDto;
	private UserSearchDto userSearchDto;
    /* ownerSearchDto */

    private CommonCodeDto commonCodeDto;
    private PullDownMenuDto pullDownMenuDto;
    private GetRevisionDataLogic accounteditRevisionLogic;
    private GetGamenRoleLogic gamenRoleLogic;
    private GetBirdUserSelfLogic bizadminGetBirdUserSelfLogic;
    /* セッションキー */
    private MakeSessionKey sessionKey = new MakeSessionKey();
    
	/**
	 * 初期化処理
	 */
	public String initialize(){
        //1)．DTO【編集用】の初期化を行います。
        //   （ユーザーID以外の全ての変数の値をクリア（null又はfalseに設定）します）
		accounteditDto.clear();
		//１．メニューから又は利用者情報照会／変更画面ら遷移された場合
        if (getPullDownMenuDto().isClearFlg() 
        		|| (getCommonCodeDto() != null && getCommonCodeDto().getUseCommonDto())) {
            //2)．メニューから遷移された場合、下記の処理を行います。
            if (getPullDownMenuDto().isClearFlg()) {
                getPullDownMenuDto().setClearFlg(false);
    	        /*画面タイトル設定 2009/02/20*/
    	        //６．画面タイトルをログインユーザータイプ別に、
    	        //    DTO【編集用】.画面タイトルへ各文字列を設定します。
    	        //＜本部ユーザーの場合＞：”アカウント情報変更”
    	        if (UserType.isHonbu(getBirdUserInfo().getMstUser().getUserTypeCd())) {
    	            accounteditDto.setGamenTitle("アカウント情報変更");
    	        }
    	        //＜オーナー・店舗ユーザーの場合＞：”利用者情報照会／変更”
    	        else {
    	            accounteditDto.setGamenTitle("利用者情報照会／変更");
    	        }
                accounteditDto.setReturnViewId("");
            }
	    	List userRole = null;
			/*3) 【汎用画面別ロール制御の取得】*/
			gamenRoleDto.setGamenId("BBA001");
	    	gamenRoleDto.setUserId(birdUserInfo.getUserID());
	    	gamenRoleDto.setBunruiCd("01"); 
	    	try{
	    		userRole = getGamenRoleLogic().excute(gamenRoleDto);
	    	}catch(NotExistException e){
	        	/*
	        	 * 画面ロール制御の取得に失敗した場合は、nullをリターンする
	        	 * 自分（ログインユーザ）の情報のみを表示対象とする
	        	 */
	    		accounteditReqDto.setUserId(birdUserInfo.getUserID());
	    		accounteditDto.setSeteiKbn("ATHER");
	    		return null;
	    	}
	    	//4)．クラス変数[汎用画面別ロール制御情報]へ
	    	//    処理８－１で取得したList[[汎用画面別ロール制御]]のインデックス0番目の値を設定します。
	    	//5)．設定区分へクラス変数.[汎用画面別ロール制御].設定区分を設定します。
			accounteditDto.setSeteiKbn(((CtlGamenRole) userRole.get(0)).getSeteiKbn());
			
	        //6)．利用者情報照会／変更から遷移された場合
	        if ((getCommonCodeDto() != null && getCommonCodeDto().getUseCommonDto())) {
	            Object obj = getCommonCodeDto().getParam(BizAdminConst.ACCOUNT_EDIT_USER_ID_PARAM_KEY);
	            String navigationCase = getCommonCodeDto().getNavigationCase();
	            getCommonCodeDto().clear();
	            if (obj != null) {
	            	accounteditReqDto.setUserId(obj.toString());
	                accounteditDto.setReturnViewId(navigationCase);
	                String viewId = edit();
	                return viewId;
	            }
	        }    
	        /*7)．ログインユーザーがユーザー管理者の場合 */
	        if(accounteditDto.isUserAdminUser()) {
	        	//LOGIC【管理対象ユーザー一覧取得】を実行し、List[[管理対象ユーザー情報]]を取得します。
	        	List userList = getBizadminGetBirdUserSelfLogic().execute(
	        			birdUserInfo.getMstUser(), birdUserInfo.getUserOner(), birdUserInfo.getUserMise());
	        	//DTO【BIRD管理対象ユーザー】.List[[管理対象ユーザー情報]]へList[[管理対象ユーザー情報]]を設定します。
	        	getBirdUserListDto().setUserList(userList);
	    	}
        }
        //２．DTO【ユーザー選択】.アクションフラグがtrueの場合、下記の処理を行います。
        else if(getUserSearchDto().isActionFlg()){
			if(UserSearchDto.RETURNKIND_SELECT==getUserSearchDto().getReturnKind()) {
				//４－１．DTO【編集用】.ユーザーIDへDTO【ユーザー選択】.ユーザーIDを設定します。
				accounteditReqDto.setUserId(getUserSearchDto().getUserId());
			}
            //４－２．DTO【ユーザー選択】.クリア処理を行います。
            getUserSearchDto().clear();
		}
    	return null;

	}
	/**
     * 変更ボタン処理
     */
	public String edit(){
        //ウィンドウＩＤを作成します。
        accounteditReqDto.setWindowId(accounteditDto.createWindowId());
        if(accounteditDto.isSystemAdminUser()== false && accounteditDto.isUserAdminUser()==false) {
        	accounteditReqDto.setUserId(birdUserInfo.getUserID());
        }
		/* LOGIC【アカウント情報取得】 */
		getAccounteditRevisionLogic().execute(accounteditReqDto.getUserId(), accounteditDto);
		UIUser uIUser = accounteditDto.getUIUser();
        /*ログインユーザがオーナーユーザー以外の時は、契約タイプをクリア*/
        if (!UserType.isOner(birdUserInfo.getMstUser().getUserTypeCd())) {
            accounteditDto.setKeiyakuType("");
        }
		String typecode = uIUser.getUsertypeCd();

		//ログインユーザーがシステム管理者の場合
		if(accounteditDto.isSystemAdminUser()){
			if(UserType.isHonbu(typecode)){				
                accounteditDto.setPageId("BBA001V02");/* 本部編集画面 */
            }
			else if(UserType.isOner(typecode)){	/* オーナー編集画面 */
				accounteditDto.setPageId("BBA001V04");
			}
			else{								/* 店舗編集画面 */
				accounteditDto.setPageId("BBA001V06");
			}
		} 
        //ログインユーザーがユーザ管理者の場合              
        else {
			if(UserType.isHonbu(typecode)){				/* 本部編集画面 */
				accounteditDto.setPageId("BBA001V03");
			}else if(UserType.isOner(typecode)){	    /* オーナー編集画面 */
				accounteditDto.setPageId("BBA001V05");
			}else{								    /* 店舗編集画面 */
				accounteditDto.setPageId("BBA001V07");
			}
		}
		//セッションキーを作成します。
		createSessionKey();
		return accounteditDto.getPageId();
	}
	/*ユーザ選択処理*/
	public String userSearch(){
        
		getUserSearchDto().clear();
		getUserSearchDto().setInitFlag(true);
		getUserSearchDto().setNeedReturnKind(true);
		getUserSearchDto().setNavigationCase("BBA001V01");
		
		return "BCO003V01";
	}
    /**
     * セッションキー作成・保持
     */
    private void createSessionKey() {
        String key = sessionKey._makeSessionKey();
        getAccounteditReqDto().setSessionKey(key);
        getAccounteditDto().setSessionKeyNow(key);
        getAccounteditDto().setSessionKey(getAccounteditReqDto().getWindowId(), key);
    }

	/**
	 * @return birdUserInfo を戻します。
	 */
	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}
	/**
	 * @param birdUserInfo birdUserInfo を設定。
	 */
	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}
		
	/**
	 * @return accounteditrevisionDto を戻します。
	 */
	public AccounteditDto getAccounteditDto() {
		return accounteditDto;
	}
	/**
	 * @param accounteditrevisionDto accounteditrevisionDto を設定。
	 */
	public void setAccounteditDto(	AccounteditDto accounteditDto) {
		this.accounteditDto = accounteditDto;
	}
	/**
	 * @return gamenRoleDto を戻します。
	 */
	public GamenRoleDto getGamenRoleDto() {
		return gamenRoleDto;
	}
	/**
	 * @param gamenRoleDto gamenRoleDto を設定。
	 */
	public void setGamenRoleDto(GamenRoleDto gamenRoleDto) {
		this.gamenRoleDto = gamenRoleDto;
	}
	/**
	 * @return birdUserListDto を戻します。
	 */
	public BirdUserListDto getBirdUserListDto() {
		return birdUserListDto;
	}
	/**
	 * @param birdUserListDto birdUserListDto を設定。
	 */
	public void setBirdUserListDto(BirdUserListDto birdUserListDto) {
		this.birdUserListDto = birdUserListDto;
	}
	/**
	 * @return userSearchDto を戻します。
	 */
	public UserSearchDto getUserSearchDto() {
		return userSearchDto;
	}
	/**
	 * @param userSearchDto userSearchDto を設定。
	 */
	public void setUserSearchDto(UserSearchDto userSearchDto) {
		this.userSearchDto = userSearchDto;
	}
    public GetRevisionDataLogic getAccounteditRevisionLogic() {
        return accounteditRevisionLogic;
    }
    public void setAccounteditRevisionLogic(GetRevisionDataLogic accountrevisionLogic) {
        this.accounteditRevisionLogic = accountrevisionLogic;
    }
    public GetGamenRoleLogic getGamenRoleLogic() {
        return gamenRoleLogic;
    }
    public void setGamenRoleLogic(GetGamenRoleLogic gamenRoleLogic) {
        this.gamenRoleLogic = gamenRoleLogic;
    }
    public GetBirdUserSelfLogic getBizadminGetBirdUserSelfLogic() {
        return bizadminGetBirdUserSelfLogic;
    }
    public void setBizadminGetBirdUserSelfLogic(GetBirdUserSelfLogic getBirdUserSelfLogic) {
        this.bizadminGetBirdUserSelfLogic = getBirdUserSelfLogic;
    }
    public CommonCodeDto getCommonCodeDto() {
        return commonCodeDto;
    }
    public void setCommonCodeDto(CommonCodeDto commonCodeDto) {
        this.commonCodeDto = commonCodeDto;
    }
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }
	/**
	 * @return クラス変数accountEditRequestDto を戻します。
	 */
	public AccountEditRequestDto getAccounteditReqDto() {
		return accounteditReqDto;
	}
	/**
	 * @param accountEditRequestDto を クラス変数accountEditRequestDtoへ設定します。
	 */
	public void setAccounteditReqDto(AccountEditRequestDto accountEditRequestDto) {
		this.accounteditReqDto = accountEditRequestDto;
	}
}
    