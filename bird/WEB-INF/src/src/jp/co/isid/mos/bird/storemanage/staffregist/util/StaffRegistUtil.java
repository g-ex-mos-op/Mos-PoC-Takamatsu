/**
 * 
 */
package jp.co.isid.mos.bird.storemanage.staffregist.util;

import jp.co.isid.mos.bird.framework.dto.GamenRoleDto;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.logic.GetGamenRoleLogic;

/**
 * 作成日:2009/08/31
 * @author xkinu
 *
 */
public class StaffRegistUtil {
	public static final String SCREEN_ID = "BSM004";
	/**同姓同名確認アラート出力判断値:出力ON(未了承) */
	public static final String ALERT_ON = "ON";
	/** 同姓同名確認アラート出力判断値:出力OFF(了承済み) */
	public static final String ALERT_OFF = "OFF";
    /**
     * 対象汎用画面別ロール設定情報取得処理
     * 
     * @return true･･･表示可能なユーザ、false･･･表示可能なユーザでない
     */
    public static boolean existGamenRole(
    		final GetGamenRoleLogic gamenRoleLogic
    		, String userId, String bunruiCd)
    {
        //引数用DTO作成
        GamenRoleDto dto = new GamenRoleDto();
        dto.setUserId(userId);
        dto.setGamenId(SCREEN_ID);
        dto.setBunruiCd(bunruiCd);
        
        try{
        	gamenRoleLogic.excute(dto);
        }catch(NotExistException ne){
            return false;
        }
        
        return true;
    }
}
