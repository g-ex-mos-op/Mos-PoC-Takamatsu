/*
 * 作成日: 2008/11/07
 *
 */
package jp.co.isid.mos.bird.togouser.filterregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteException;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteWithReasonException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.togouser.filterregist.dao.UIUserFilterDao;
import jp.co.isid.mos.bird.togouser.filterregist.entity.UIBumonFilter;
import jp.co.isid.mos.bird.togouser.filterregist.entity.UITogoUserSaisin;
import jp.co.isid.mos.bird.togouser.filterregist.entity.UIUserFilter;
import jp.co.isid.mos.bird.togouser.filterregist.logic.UpdateUserFilterLogic;

/**
 * @author S.yamauchi
 *
 */
public class UpdateUserFilterLogicImpl implements UpdateUserFilterLogic{

    /* ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BUR002L04";
    
    private String VIEW_ID = "BUR002";
	private UIUserFilterDao uiUserFilterDao;
    private UIBumonFilter uiBumonFilter;
    private UITogoUserSaisin uiTogoUserSaisin;
    
    public List execute(List bumonlist, List userList, String sysDate, String loginUser) throws ApplicationException {
        String userId = "";
        String kbunSpace = "";
        String one = "1";
        String groupName = null;
        int userDateListSize;
        List UserDateList = bumonlist;
        List userSaisinList = userList;
        
        
        uiTogoUserSaisin = (UITogoUserSaisin)userSaisinList.get(0);
        kbunSpace = uiTogoUserSaisin.getKbnSpare1();
        userId = uiTogoUserSaisin.getUserId();
        
        //マスタ管理だけ先に取得
        uiBumonFilter =  (UIBumonFilter)UserDateList.get(5);
        groupName = uiBumonFilter.getGroupName();
        String strMaster = uiBumonFilter.getRenkeiFlg();
        String strBumonjokyo = uiBumonFilter.getGroupCdRflg();
        
        //部門状況がnullのときブランクをセットします。
        if (strBumonjokyo == null) {
            strBumonjokyo = "";
        }
        
        userDateListSize = UserDateList.size();
        for(int i = 0; i < userDateListSize; i++) {
            uiBumonFilter =  (UIBumonFilter)UserDateList.get(i);
            groupName = uiBumonFilter.getGroupName();
//            if (strBumonjokyo != null) {    
//                if (groupName.equals("マスタ管理")) {
////                    if (strBumonjokyo.equals(one) && strMaster.equals("0")){
////                        String msg = groupName + "は登録";
////                        throw new CannotExecuteWithReasonException("当該ユーザーの部門はマスタ管理で許可されていない",msg);
////                    }
//                }else if (groupName.equals("販売管理")){
//                    String strHanbai = uiBumonFilter.getRenkeiFlg();
//                    if (!strMaster.equals(one) && (strBumonjokyo.equals("") || !strBumonjokyo.equals(one))) {
//                        check(groupName,strHanbai);
//                    }
//                }else if (groupName.equals("POS集配信")) {
//                    String strPos = uiBumonFilter.getRenkeiFlg();
//                    if ((!strMaster.equals(one)) && (strBumonjokyo.equals("") || !strBumonjokyo.equals(one))) {
//                        check(groupName,strPos);
//                    }
//                }else if (groupName.equals("NightGENESIS")) {
//                    String strNgenesis = uiBumonFilter.getRenkeiFlg();
//                    if ((!strMaster.equals(one)) && (strBumonjokyo.equals("") || !strBumonjokyo.equals(one))) {
//                        check(groupName,strNgenesis);
//                    }
//                }else if (groupName.equals("直営")) {
//                    String strTyokuei = uiBumonFilter.getRenkeiFlg();                
//                    if ((!strMaster.equals(one)) && (strBumonjokyo.equals("") || !strBumonjokyo.equals(one))) {
//                        check(groupName,strTyokuei);
//                    }
//                }
                if (groupName.equals("会計")) {
                    String strKaikei = uiBumonFilter.getRenkeiFlg();
                    if(strKaikei.equals(one) && kbunSpace.equals(one)) {
                        throw new CannotExecuteException("画面登録ユーザーを会計に連携することは");
                    }    
                }  
//            }    
        }
        
        //一旦ﾕｰｻﾞフィルターを削除
        UIUserFilter uiUserFilter = new UIUserFilter();
        getUIUserFilterDao().delete(userId);
        uiUserFilter.setLastUser(loginUser);
        uiUserFilter.setLastPgm(VIEW_ID);
        uiUserFilter.setLastTmsp(DateManager.getCurrentTimestamp());
        uiUserFilter.setTekiyouDt(sysDate);
        uiUserFilter.setUserId(userId);

        int listSize = UserDateList.size();
        for (int i = 0;i < listSize; i++){
            UIBumonFilter uiBumonFilter = new UIBumonFilter();
            uiBumonFilter = (UIBumonFilter)UserDateList.get(i);
            if (!uiBumonFilter.getRenkeiFlg().equals("9")){
                uiUserFilter.setGroupCd(uiBumonFilter.getGroupCd());
                uiUserFilter.setRenkeiFlg(uiBumonFilter.getRenkeiFlg());
                getUIUserFilterDao().insert(uiUserFilter);
            }
        }
        
        return null;
	}
    
    public void check(String groupName, String renkeiFlg) {
        if (renkeiFlg.equals("1")) {
            String msg = groupName + "は登録";
            throw new CannotExecuteWithReasonException("該当ユーザはマスタ管理で部門許可または、個別許可されていない",msg);
        }
    }
    
    public UIUserFilterDao getUIUserFilterDao() {
        return uiUserFilterDao;
    }

    public void setUIUserFilterDao(UIUserFilterDao uiUserFilterDao) {
        this.uiUserFilterDao = uiUserFilterDao;
    }

}
