/*
 * 作成日: 2006/03/02
 * 更新日：2006/09/13　miyagi　ユーザー管理者のとき、自分もプルダウンに表示させる
 * 更新日：2006/09/25　miyagi　一般ユーザーのとき、自分のアカウント情報を照会できるようにする
 *
 */
package jp.co.isid.mos.bird.bizadmin.accountref.action.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizadmin.accountref.action.AccountRefAction;
import jp.co.isid.mos.bird.bizadmin.accountref.dto.AccountRefDto;
import jp.co.isid.mos.bird.bizadmin.accountref.dto.RequestDto;
import jp.co.isid.mos.bird.bizadmin.accountref.logic.GetAccountLogic;
import jp.co.isid.mos.bird.bizadmin.common.logic.GetBirdUserSelfLogic;
import jp.co.isid.mos.bird.commonform.usersearch.dto.UserSearchDto;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.BirdUserListDto;
import jp.co.isid.mos.bird.framework.dto.GamenRoleDto;
import jp.co.isid.mos.bird.framework.entity.CtlGamenRole;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.logic.GetGamenRoleLogic;
/**
 * アカウント情報照会アクション
 * 
 * @author 慮
 *
 */
public class AccountRefActionImpl implements AccountRefAction {

    public static final String initialize_ACTION_ID = "BBA002A01";
    public static final String view_ACTION_ID = "BBA002A02";
    public static final String back_ACTION_ID = "BBA002A03";

	private BirdUserInfo birdUserInfo;

	private GetAccountLogic accountRefGetAccountLogic;	
    private GetGamenRoleLogic gamenRoleLogic;
    private GetBirdUserSelfLogic getBirdUserSelfLogic;

	private GamenRoleDto gamenRoleDto;
	private BirdUserListDto birdUserListDto;
	private UserSearchDto userSearchDto;
	private RequestDto accountRefSesDto;	
	private AccountRefDto accountRefDto;

	/**
	 * 初期化処理
	 */
	public String initialize() {
		/* 初期化 */
		accountRefSesDto.clear();
		if(getUserSearchDto().getReturnKind() != UserSearchDto.RETURNKIND_INIT){
			if(UserSearchDto.RETURNKIND_SELECT == getUserSearchDto().getReturnKind()) {
				accountRefSesDto.setUserId(getUserSearchDto().getUserId());
			}
			getUserSearchDto().clear();
		}
	 	gamenRoleDto.setGamenId("BBA002");
    	gamenRoleDto.setUserId(birdUserInfo.getUserID());
    	gamenRoleDto.setBunruiCd("01");

    	/* ロジック【汎用画面別ロール制御の取得】
    	 * 画面ロール制御の取得に失敗した場合は、nullをリターンする
    	 * 自分（ログインユーザ）の情報のみを表示対象とする
    	 */
    	List userRole = new ArrayList();
    	try{
    		userRole = getGamenRoleLogic().excute(gamenRoleDto);
    	}catch(NotExistException e){
    		accountRefSesDto.setUserId(getBirdUserInfo().getUserID());
    		return null;
    	}
    	
    	CtlGamenRole ctlGamenRole = (CtlGamenRole) userRole.get(0);
    	/*[[汎用画面別ロール制御]].設定区分*/
    	String seteiKubun = ctlGamenRole.getSeteiKbn();
    	/*Dto 設定*/
    	accountRefSesDto.setSeteiKbn(seteiKubun);
    	
    	if("01".equals(seteiKubun)){
    	}
    	/*[[汎用画面別ロール制御]].設定区分が’02’の場合*/		
    	else if("02".equals(seteiKubun)){            
        	/*ロジック【BIRDユーザの取得】*/
            List userList = getGetBirdUserSelfLogic().execute(birdUserInfo.getMstUser(),birdUserInfo.getUserOner(),birdUserInfo.getUserMise());
        	getBirdUserListDto().setUserList(userList);
    	}
    	else {
    		accountRefSesDto.setUserId(getBirdUserInfo().getUserID());
    	}
    	
 		return null;
	}   
	/**
	 * 照会
	 */
	public String reference(){
        accountRefDto.clear();
		/*ロジック【ユーザー情報の取得】*/
		return accountRefGetAccountLogic.execute(accountRefDto, accountRefSesDto.getUserId());
	}
	
	/**
	 * ユーザー選択画面遷移
	 * 
	 * @return
	 */
	public String callUserSearchForm(){
		getUserSearchDto().clear();
		getUserSearchDto().setInitFlag(true);
		getUserSearchDto().setNeedReturnKind(true);
		getUserSearchDto().setNavigationCase("BBA002V01");
		return "BCO003V01";

	}
	/**
	 * 戻る
	 */
	public String back(){
		return "BBA002V01";
	}
	/**
	 * @return クラス変数accountRefDto を戻します。
	 */
	public AccountRefDto getAccountRefDto() {
		return accountRefDto;
	}
	/**
	 * @param accountRefDto を クラス変数accountRefDtoへ設定します。
	 */
	public void setAccountRefDto(AccountRefDto accountRefDto) {
		this.accountRefDto = accountRefDto;
	}
	/**
	 * @return クラス変数accountRefReqDto を戻します。
	 */
	public RequestDto getAccountRefSesDto() {
		return accountRefSesDto;
	}
	/**
	 * @param accountRefReqDto を クラス変数accountRefReqDtoへ設定します。
	 */
	public void setAccountRefSesDto(RequestDto accountRefReqDto) {
		this.accountRefSesDto = accountRefReqDto;
	}
	/**
	 * @return クラス変数birdUserInfo を戻します。
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
	 * @return クラス変数birdUserListDto を戻します。
	 */
	public BirdUserListDto getBirdUserListDto() {
		return birdUserListDto;
	}
	/**
	 * @param birdUserListDto を クラス変数birdUserListDtoへ設定します。
	 */
	public void setBirdUserListDto(BirdUserListDto birdUserListDto) {
		this.birdUserListDto = birdUserListDto;
	}
	/**
	 * @return クラス変数gamenRoleDto を戻します。
	 */
	public GamenRoleDto getGamenRoleDto() {
		return gamenRoleDto;
	}
	/**
	 * @param gamenRoleDto を クラス変数gamenRoleDtoへ設定します。
	 */
	public void setGamenRoleDto(GamenRoleDto gamenRoleDto) {
		this.gamenRoleDto = gamenRoleDto;
	}
	/**
	 * @return クラス変数gamenRoleLogic を戻します。
	 */
	public GetGamenRoleLogic getGamenRoleLogic() {
		return gamenRoleLogic;
	}
	/**
	 * @param gamenRoleLogic を クラス変数gamenRoleLogicへ設定します。
	 */
	public void setGamenRoleLogic(GetGamenRoleLogic gamenRoleLogic) {
		this.gamenRoleLogic = gamenRoleLogic;
	}
	/**
	 * @return クラス変数getBirdUserSelfLogic を戻します。
	 */
	public GetBirdUserSelfLogic getGetBirdUserSelfLogic() {
		return getBirdUserSelfLogic;
	}
	/**
	 * @param getBirdUserSelfLogic を クラス変数getBirdUserSelfLogicへ設定します。
	 */
	public void setGetBirdUserSelfLogic(GetBirdUserSelfLogic getBirdUserSelfLogic) {
		this.getBirdUserSelfLogic = getBirdUserSelfLogic;
	}
	/**
	 * @return クラス変数userSearchDto を戻します。
	 */
	public UserSearchDto getUserSearchDto() {
		return userSearchDto;
	}
	/**
	 * @param userSearchDto を クラス変数userSearchDtoへ設定します。
	 */
	public void setUserSearchDto(UserSearchDto userSearchDto) {
		this.userSearchDto = userSearchDto;
	}
	/**
	 * @return クラス変数accountRefGetAccountLogic を戻します。
	 */
	public GetAccountLogic getAccountRefGetAccountLogic() {
		return accountRefGetAccountLogic;
	}
	/**
	 * @param accountRefGetAccountLogic を クラス変数accountRefGetAccountLogicへ設定します。
	 */
	public void setAccountRefGetAccountLogic(
			GetAccountLogic accountRefGetAccountLogic) {
		this.accountRefGetAccountLogic = accountRefGetAccountLogic;
	}
}