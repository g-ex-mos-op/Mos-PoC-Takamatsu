/*
 * 作成日：2006/2/15
 * 更新日：2006/07/03 
 *         INSERT 重複主キー登録対応　INAZAWA
 */
package jp.co.isid.mos.bird.sysadmin.userroleregist.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.commonform.rolesearch.dto.RoleSearchDto;
import jp.co.isid.mos.bird.commonform.rolesearch.entity.UIRole;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.sysadmin.userroleregist.action.SearchRoleAction;
import jp.co.isid.mos.bird.sysadmin.userroleregist.dto.RoleUserInfoDto;
import jp.co.isid.mos.bird.sysadmin.userroleregist.logic.UpdateUserRoleLogic;

//import org.seasar.framework.container.S2Container;
//import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * ロール検索を行う
 * @author lee
 */
public class SearchRoleActionImpl implements SearchRoleAction {

    public static String  initialize_ACTION_ID = "BSA011A04";
    public static String  updateUserRole_ACTION_ID = "BSA011A05";
    public static String  selectUserRole_ACTION_ID = "BSA011A06";
    public static String  backUserRole_ACTION_ID ="BSA011A07";
    
	private RoleUserInfoDto roleUserInfoDto;
	
	private BirdUserInfo birdUserInfo;
	
	private RoleSearchDto roleSearchDto;
    private UpdateUserRoleLogic userRoleRegistUpdateUserRoleLogic;
	
	/**
	 * @return roleSearchDto を戻します。
	 */
	public RoleSearchDto getRoleSearchDto() {
		return roleSearchDto;
	}
	/**
	 * @param roleSearchDto roleSearchDto を設定。
	 */
	public void setRoleSearchDto(RoleSearchDto roleSearchDto) {
		this.roleSearchDto = roleSearchDto;
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
	
	
	public String initialize() {

		if (getRoleSearchDto().isActionFlg()) {
			List list = getRoleSearchDto().getResultRoleList();
         //ADD START 2006/07/03 重複登録バグ対応　INAZAWA
			List kizonList = getRoleUserInfoDto().getUserRoleList();
         //ADD END 2006/07/03 重複登録バグ対応　INAZAWA
			for (int i = 0; i < list.size(); i++) {
                UIRole uIRole = (UIRole) list.get(i);
                //ADD START 2006/07/03 重複登録バグ対応　INAZAWA
                boolean choufukuCheck = true;
                for (int j = 0; j < kizonList.size(); j++) {
                jp.co.isid.mos.bird.sysadmin.userroleregist.entity.UIRole kizonUIRole = (jp.co.isid.mos.bird.sysadmin.userroleregist.entity.UIRole) kizonList.get(j);
                    if(uIRole.getRoleCd().equals(kizonUIRole.getRoleCd())){
                        choufukuCheck = false;
                        break;
                    }
                }
                if(choufukuCheck){
          //ADD END 2006/07/03 重複登録バグ対応　INAZAWA
                    jp.co.isid.mos.bird.sysadmin.userroleregist.entity.UIRole uiRoleRegist
                    = new jp.co.isid.mos.bird.sysadmin.userroleregist.entity.UIRole();
                    uiRoleRegist.setRoleCd(uIRole.getRoleCd());
                    uiRoleRegist.setBunruiCd(uIRole.getBunruiCd());
                    uiRoleRegist.setBunruiName(uIRole.getBunruiName());
                    uiRoleRegist.setBunruiSortSeq(uIRole.getBunruiSortSeq());
                    uiRoleRegist.setDescription(uIRole.getDescription());
                    uiRoleRegist.setRoleName(uIRole.getRoleName());
                    uiRoleRegist.setRoleSortSeq(uIRole.getRoleSortSeq());
                    uiRoleRegist.setCheckedRole(true);
                    uiRoleRegist.setBatFlg("0");
    
                    getRoleUserInfoDto().getUserRoleList().add(uiRoleRegist);
           //ADD START 2006/07/03 重複登録バグ対応　INAZAWA
                }

			}
            //ADD END 2006/07/03 重複登録バグ対応　INAZAWA

			getRoleSearchDto().setActionFlg(false);
		}

		return null;
    }
    
	public String updateUserRole() {
        
//    	S2Container container = SingletonS2ContainerFactory.getContainer();
//    	UpdateUserRoleLogic updateUserRoleLogic = (UpdateUserRoleLogic) container.getComponent(UpdateUserRoleLogicImpl.class);
    	getUserRoleRegistUpdateUserRoleLogic().execute(roleUserInfoDto.getUserId(),getBirdUserInfo().getUserID(),roleUserInfoDto.getUserRoleList());
    	
    	return "BSA011V01";
    }

	public String selectUserRole() {
		getRoleSearchDto().setNavigationCase("BSA011V03");
		return "BCO004V01";
	}
	
	public String backUserRole() {
		
		return "BSA011V01";
	}
    public UpdateUserRoleLogic getUserRoleRegistUpdateUserRoleLogic() {
        return userRoleRegistUpdateUserRoleLogic;
    }
    public void setUserRoleRegistUpdateUserRoleLogic(
            UpdateUserRoleLogic userRoleRegistUpdateUserRoleLogic) {
        this.userRoleRegistUpdateUserRoleLogic = userRoleRegistUpdateUserRoleLogic;
    }
}

