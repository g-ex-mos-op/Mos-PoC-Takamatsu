/*
 * 作成日: 2006/02/21
 *
 */
package jp.co.isid.mos.bird.sysadmin.userroleregist.logic.impl;

import java.sql.Timestamp;
import java.util.List;

import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.sysadmin.userroleregist.dao.CtlUserRoleDao;
import jp.co.isid.mos.bird.sysadmin.userroleregist.entity.CtlUserRole;
import jp.co.isid.mos.bird.sysadmin.userroleregist.entity.UIRole;
import jp.co.isid.mos.bird.sysadmin.userroleregist.logic.UpdateUserRoleLogic;

/**
 * @author xylee
 *
 */
public class UpdateUserRoleLogicImpl implements UpdateUserRoleLogic {

    /* ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BSA011L02";
	
	private CtlUserRoleDao ctlUserRoleDao;
	
	/**
     * コントロールユーザロールDAOを取得します。
     * 
     * @return コントロールユーザロールDAO
     */
	public CtlUserRoleDao getCtlUserRoleDao() {
		return ctlUserRoleDao;
	}

    /**
     * コントロールユーザロールDAOを設定します。
     * 
     * @param ctlUserRoleDao コントロールユーザロールDAO
     */
	public void setCtlUserRoleDao(CtlUserRoleDao ctlUserRoleDao) {
		this.ctlUserRoleDao = ctlUserRoleDao;
	}
	
	
	/**
	 * ロールリストセットする
	 */
	public void execute(String userId, String firstUser, List userRole) {
		getCtlUserRoleDao().deleteUserRole(userId);
//        boolean chofukuFlg = true;
		for (int i = 0; i < userRole.size(); i++) {
            UIRole uIRole = (UIRole) userRole.get(i);
			if(uIRole.getBatFlg()==null){
				uIRole.setBatFlg("0");
			}
//            if(uIRole.getRoleCd().equals("001")){
//                chofukuFlg = false;            
//            }
			if((uIRole.isCheckedRole()) && (!uIRole.getBatFlg().equals("1"))){
				Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
                CtlUserRole ctlUserRole = new CtlUserRole();
				ctlUserRole.setUserId(userId);
				ctlUserRole.setRoleCd(uIRole.getRoleCd());
                //20060627 CHANGE START 全ユーザーロール設定処理　K.INAZAWA(ASPAC)
// change start xkhata 画面で設定できるロールは全て編集可能
                ctlUserRole.setBatFlg("0");
//                if(uIRole.getRoleCd().equals("001")){
//                    ctlUserRole.setBatFlag("1");
//                }else{
//                    ctlUserRole.setBatFlag("0");
//                }
// change end
                //20060627 CHANGE END 全ユーザーロール設定処理　K.INAZAWA(ASPAC)
				ctlUserRole.setFirstUser(firstUser);
				ctlUserRole.setFirstPgm("BSA011");
				ctlUserRole.setFirstTmsp(currentTimestamp);
				ctlUserRole.setLastUser(firstUser);
				ctlUserRole.setLastPgm("BSA011");
				ctlUserRole.setLastTmsp(currentTimestamp);
//			System.out.println(uIRole.getRoleCd());
				getCtlUserRoleDao().insertUserRole(ctlUserRole);
			}
		}
// change start xkhata 画面で設定できるロールは全て編集可能
        //20060627 ADD START 全ユーザーロール設定処理　K.INAZAWA(ASPAC)
//        if(chofukuFlg){
//            zenUserRole(userId,firstUser);
//        }
// change end
        //20060627 ADD START 全ユーザーロール設定処理　K.INAZAWA(ASPAC)
		
	}
//    /**
//     * 全ユーザーロールセット処理
//     * 2006/06/27
//     * @author inazawa
//     */
//    private void zenUserRole(String userId,String firstUser){
//        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
//        ctlUserRole.setUserId(userId);
//        ctlUserRole.setRoleCd("001");
//        ctlUserRole.setBatFlg("0");
//        ctlUserRole.setFirstUser(firstUser);
//        ctlUserRole.setFirstPgm("BSA011");
//        ctlUserRole.setFirstTmsp(currentTimestamp);
//        ctlUserRole.setLastUser(firstUser);
//        ctlUserRole.setLastPgm("BSA011");
//        ctlUserRole.setLastTmsp(currentTimestamp);
//        getCtlUserRoleDao().insertUserRole(ctlUserRole);
//        
//    }
    
}
