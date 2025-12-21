/*
 * 作成日: 2006/07/05
 *
 */
package jp.co.isid.mos.bird.office.bbsregist.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.framework.dto.GamenRoleDto;
import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.logic.GetGamenRoleLogic;
import jp.co.isid.mos.bird.office.bbsregist.dto.KeijibanRegistDto;
import jp.co.isid.mos.bird.office.bbsregist.entity.UIKeijiban;
import jp.co.isid.mos.bird.office.bbsregist.logic.CheckEditAllowedLogic;

/**
 * 更新可能ユーザチェック
 * @author xytamura
 */
public class CheckEditAllowedLogicImpl implements CheckEditAllowedLogic {
    
    //汎用画面別ロール制御の取得ロジック
    private GetGamenRoleLogic getGamenRoleLogic;
    
    
    /* (非 Javadoc)
     * @see jp.co.isid.mos.bird.office.bbsregist.logic.impl.CheckEditAllowedLogic#execute(jp.co.isid.mos.bird.office.bbsregist.entity.UIKeijiban, jp.co.isid.mos.bird.framework.entity.MstUser)
     */
    public boolean execute(UIKeijiban uiKeijiban, MstUser user) throws ApplicationException {
        if(isSystemAdministrator(user) || isFirstUser(uiKeijiban, user)){
            return true;
        }
        return false;
    }
    

    /**
     * 汎用画面別ロール制御の取得ロジックを取得します。
     * @return 汎用画面別ロール制御の取得ロジック 
     */
    public GetGamenRoleLogic getGetGamenRoleLogic() {
        return getGamenRoleLogic;
    }

    /**
     * 汎用画面別ロール制御の取得ロジックを設定します。
     * @param 汎用画面別ロール制御の取得ロジック
     */
    public void setGetGamenRoleLogic(GetGamenRoleLogic getGamenRoleLogic) {
        this.getGamenRoleLogic = getGamenRoleLogic;
    }
    
    /**
     * システム管理者か判定します。
     * @param ユーザ情報
     * @return
     */
    private boolean isSystemAdministrator(MstUser user){
        GamenRoleDto gamenRoleDto = new GamenRoleDto();
        gamenRoleDto.setUserId(user.getUser_id());
        gamenRoleDto.setGamenId(KeijibanRegistDto.VIEW_ID);
        gamenRoleDto.setBunruiCd("01");
        List list = new ArrayList();
        try{
            list = getGamenRoleLogic.excute(gamenRoleDto);
        }catch (NotExistException e) {
            //件数０件をキャッチ
        }
        if(list.size() > 0){
            return true;
        }
        return false;
    }

    /**
     * 登録ユーザか判定します。
     * @return
     */
    private boolean isFirstUser(UIKeijiban uiKeijiban, MstUser user){
        //登録者とログインユーザが同じ場合
        if(user.getUser_id().equals(uiKeijiban.getFirstUser())){
            return true;
        }
        return false;
    }

}
