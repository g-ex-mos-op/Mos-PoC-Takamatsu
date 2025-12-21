/*
 * 作成日: 2006/2/15
 */
package jp.co.isid.mos.bird.sysadmin.userroleregist.action.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.commonform.usersearch.dto.UserSearchDto;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.BirdUserListDto;
import jp.co.isid.mos.bird.framework.logic.GetBirdUserLogic;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.sysadmin.userroleregist.action.GetBirdUserAction;
import jp.co.isid.mos.bird.sysadmin.userroleregist.dto.RoleUserInfoDto;
import jp.co.isid.mos.bird.sysadmin.userroleregist.dto.SeteiKubunDto;
import jp.co.isid.mos.bird.sysadmin.userroleregist.dto.UserRoleRegistDto;
import jp.co.isid.mos.bird.sysadmin.userroleregist.entity.UIBirdUser;
import jp.co.isid.mos.bird.sysadmin.userroleregist.logic.SearchRoleLogic;




/**
 * ユーザ一覧アクションクラス
 * @author lee
 */
public class GetBirdUserActionImpl implements GetBirdUserAction {

    public static String  initialize_ACTION_ID = "BSA011A02";
    public static String  viewUserRole_ACTION_ID = "BSA011A03";
    
    private List userList;
	
    private BirdUserListDto birdUserListDto;
   
    private BirdUserInfo birdUserInfo;
    
	private RoleUserInfoDto roleUserInfoDto;
    
	private UserRoleRegistDto userRoleRegistDto;
    
	private UserSearchDto userSearchDto;
	
	private SeteiKubunDto seteiKubunDto;
    private GetBirdUserLogic getBirdUserLogic;
    private SearchRoleLogic userRoleRegistSearchRoleLogic;
	
	public String initialize() {
        
    	userList = getGetBirdUserLogic().execute(getBirdUserInfo().getMstUser(),getBirdUserInfo().getUserOner(),getBirdUserInfo().getUserMise());

    	getBirdUserListDto().setUserList(userList);
    	
    	return null;
    }

    public String viewUserRole() {
       	/* ユーザーID前ゼロ付加 */
        CodeFormatter formatter = new CodeFormatter(8);
        formatter.setFormatPattern("00000000");
        getSeteiKubunDto().setUserId(formatter.format(getSeteiKubunDto().getUserId(), true));
        
    	Map roleMap = new HashMap();
    	roleMap = getUserRoleRegistSearchRoleLogic().execute((getSeteiKubunDto().getUserId()==null || "".equals(getSeteiKubunDto().getUserId().trim())) ? getUserRoleRegistDto().getUserId() : getSeteiKubunDto().getUserId());


        
    	UIBirdUser uIBirdUser = (UIBirdUser)((List)roleMap.get("rollInfo1st")).get(0);        
    	roleUserInfoDto.setBumonCd(uIBirdUser.getBumonCd());
    	roleUserInfoDto.setUserId(uIBirdUser.getUserId());
    	roleUserInfoDto.setUserNameKana(uIBirdUser.getUserNameKana());
    	roleUserInfoDto.setUserNameKj(uIBirdUser.getUserNameKj());
    	roleUserInfoDto.setUsertypeCd(uIBirdUser.getUsertypeCd());
    	roleUserInfoDto.setUserRoleList((List)roleMap.get("rollInfo2nd"));
    	
    	return "BSA011V03";
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
	
	/**
	 * @return roleUserInfoDto を戻します。
	 */
	public RoleUserInfoDto getRoleUserInfoDto() {
		return roleUserInfoDto;
	}
	/**
	 * @param roleUserInfoDto roleUserInfoDto を設定。
	 */
	public void setRoleUserInfoDto(RoleUserInfoDto roleUserInfoDto) {
		this.roleUserInfoDto = roleUserInfoDto;
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

    public SearchRoleLogic getUserRoleRegistSearchRoleLogic() {
        return userRoleRegistSearchRoleLogic;
    }

    public void setUserRoleRegistSearchRoleLogic(
            SearchRoleLogic userRoleRegistSearchRoleLogic) {
        this.userRoleRegistSearchRoleLogic = userRoleRegistSearchRoleLogic;
    }

    public GetBirdUserLogic getGetBirdUserLogic() {
        return getBirdUserLogic;
    }

    public void setGetBirdUserLogic(GetBirdUserLogic getBirdUserLogic) {
        this.getBirdUserLogic = getBirdUserLogic;
    }

    public SeteiKubunDto getSeteiKubunDto() {
        return seteiKubunDto;
    }

    public void setSeteiKubunDto(SeteiKubunDto seteiKubunDto) {
        this.seteiKubunDto = seteiKubunDto;
    }
}

