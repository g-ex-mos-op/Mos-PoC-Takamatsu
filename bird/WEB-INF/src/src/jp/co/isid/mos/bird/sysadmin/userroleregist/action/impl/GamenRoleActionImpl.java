/*
 * 作成日: 2006/2/13
 */
package jp.co.isid.mos.bird.sysadmin.userroleregist.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.commonform.usersearch.dto.UserSearchDto;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.BirdUserListDto;
import jp.co.isid.mos.bird.framework.dto.GamenRoleDto;
import jp.co.isid.mos.bird.framework.entity.CtlGamenRole;
import jp.co.isid.mos.bird.framework.logic.GetBirdUserLogic;
import jp.co.isid.mos.bird.framework.logic.GetGamenRoleLogic;
import jp.co.isid.mos.bird.sysadmin.userroleregist.action.GamenRoleAction;
import jp.co.isid.mos.bird.sysadmin.userroleregist.dto.SeteiKubunDto;
import jp.co.isid.mos.bird.sysadmin.userroleregist.dto.UserRoleRegistDto;


/**
 * 画面ロールアクションクラス
 * @author lee
 */
public class GamenRoleActionImpl implements GamenRoleAction {

    public static String initialize_ACTION_ID        = "BSA011A01";
    
    private List userRole;
    
    private List userList;
	
	private GamenRoleDto gamenRoleDto;
   
    private UserSearchDto userSearchDto;
    
    private BirdUserInfo birdUserInfo;
    
    private SeteiKubunDto seteiKubunDto;
    
    private BirdUserListDto birdUserListDto;
    
	private UserRoleRegistDto userRoleRegistDto;
    private GetGamenRoleLogic userRoleRegistGetGamenRoleLogic;
    private GetBirdUserLogic userRoleRegistGetBirdUserLogic;
    
    public String initialize() {
    	
//    	S2Container container = SingletonS2ContainerFactory.getContainer();
//  	 2006/03/09 e-mosリンク対応 xkhata
    	//setBirdUserInfo( (BirdUserInfo) container.getComponent(BirdUserInfo.class) );
//    	BirdUserInfo birdUserInfo = (BirdUserInfo) container.getComponent(BirdUserInfo.class);
//end
    	userRoleRegistDto.setUserId("");
    	if(getUserSearchDto().isActionFlg()){
    		seteiKubunDto.setUserId(getUserSearchDto().getUserId());
    	}else{
    		seteiKubunDto.setUserId("");
    	}
		gamenRoleDto.setGamenId("BSA011");
    	//gamenRoleDto.setUserId(getBirdUserInfo().getUserID());
    	gamenRoleDto.setUserId(birdUserInfo.getUserID());
    	
    	gamenRoleDto.setBunruiCd("01");

//    	GetGamenRoleLogic getGamenRoleLogic = (GetGamenRoleLogic) container.getComponent(GetGamenRoleLogicImpl.class);
    	userRole = getUserRoleRegistGetGamenRoleLogic().excute(gamenRoleDto);

    	CtlGamenRole ctlGamenRole = (CtlGamenRole) userRole.get(0);
    	seteiKubunDto.setSeteiKubun(ctlGamenRole.getSeteiKbn());
    	
    	if(seteiKubunDto.getSeteiKubun().equals("02")) {
//    		GetBirdUserLogic getBirdUserLogic = (GetBirdUserLogic) container.getComponent(GetBirdUserLogicImpl.class);
        	//userList = getBirdUserLogic.execute(getBirdUserInfo().getMstUser(),getBirdUserInfo().getUserOner(),getBirdUserInfo().getUserMise());
        	userList = getUserRoleRegistGetBirdUserLogic().execute(getBirdUserInfo().getMstUser(), getBirdUserInfo().getUserOner(), getBirdUserInfo().getUserMise());

        	getBirdUserListDto().setUserList(userList);
        }
    	return null;
    }

    public String selectUser() {
    	getUserSearchDto().setNavigationCase("BSA011V01");
    	getUserSearchDto().setInitFlag(true); 
    	getUserSearchDto().setUserId(seteiKubunDto.getUserId());
    	return "BCO003V01";
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
	 * @return userSearchDto を戻します。
	 */
	public UserSearchDto getUserSearchDto() {
		return userSearchDto;
	}
	/**
	 * @param userSearchDto を設定。
	 */
	public void setUserSearchDto(UserSearchDto userSearchDto) {
		this.userSearchDto = userSearchDto;
	}
	
	/**
	 * @return seteiKubunDto を戻します。
	 */
	public SeteiKubunDto getSeteiKubunDto() {
		return seteiKubunDto;
	}
	/**
	 * @param seteiKubunDto を設定。
	 */
	public void setSeteiKubunDto(SeteiKubunDto seteiKubunDto) {
		this.seteiKubunDto= seteiKubunDto;
	}
	
	/**
	 * @return birdUserListDto を戻します。
	 */
	public BirdUserListDto getBirdUserListDto() {
		return birdUserListDto;
	}
	/**
	 * @param birdUserListDto を設定。
	 */
	public void setBirdUserListDto(BirdUserListDto birdUserListDto) {
		this.birdUserListDto= birdUserListDto;
	}
	
	/**
	 * @return userRoleRegistDto を戻します。
	 */
	public UserRoleRegistDto getUserRoleRegistDto() {
		return userRoleRegistDto;
	}
	/**
	 * @param userRoleRegistDto userRoleRegistDto を設定。
	 */
	public void setUserRoleRegistDto(UserRoleRegistDto userRoleRegistDto) {
		this.userRoleRegistDto = userRoleRegistDto;
	}

    public GetGamenRoleLogic getUserRoleRegistGetGamenRoleLogic() {
        return userRoleRegistGetGamenRoleLogic;
    }

    public void setUserRoleRegistGetGamenRoleLogic(
            GetGamenRoleLogic userRoleRegistGetGamenRoleLogic) {
        this.userRoleRegistGetGamenRoleLogic = userRoleRegistGetGamenRoleLogic;
    }

    public GetBirdUserLogic getUserRoleRegistGetBirdUserLogic() {
        return userRoleRegistGetBirdUserLogic;
    }

    public void setUserRoleRegistGetBirdUserLogic(
            GetBirdUserLogic userRoleRegistGetBirdUserLogic) {
        this.userRoleRegistGetBirdUserLogic = userRoleRegistGetBirdUserLogic;
    }
}

