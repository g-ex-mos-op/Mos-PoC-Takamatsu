/**
 * 
 */
package jp.co.isid.mos.bird.storemanage.common.util;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.GamenRoleDto;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.logic.GetGamenRoleLogic;

/**
 * @author xkinu
 *
 */
public class StoreManageUtil {
    //お客様の声 BIRD内リンク用パラメータ名
    public static final String CLAIM_BIRDLINK_PARAM_NENGETU = "NENGETU";
    public static final String CLAIM_BIRDLINK_PARAM_TAISHOJOKEN = "TAISHOJOKEN";
    public static final String CLAIM_BIRDLINK_PARAM_HYOJITAISHO = "HYOJITAISHO";
    //  タイプコード（１：クレームデータ　　４：お褒めデータ　９：クレーム計）
    public static final String CLAIM_BIRDLINK_PARAM_TYPECD = "TYPE_CD";
    public static final String CLAIM_BIRDLINK_PARAM_BNRM = "BNR_M";
    // VIEW_ID
    public static final String VIEW_ID_CLAIM_TOTAL = "BSM017V01";
    
    /**
     * MHAユーザ権限判断処理
     * 
     * MHAユーザ様の処理が可能なユーザか否かを判別します。
     * 
     * @param gamenRoleLogic 共通ロジック【ユーザの汎用画面ロール制御情報取得】
     * @param userId ユーザID
     * @param gamenId 画面ID
     * @param bunruiCd 分類コード
     * @return true･･･可能なユーザ、false･･･可能なユーザでない
     */
    public static boolean isMhaUser(GetGamenRoleLogic gamenRoleLogic
    		, String userId
    		, String gamenId, String bunruiCd) {

        //引数用DTO作成
        GamenRoleDto dto = new GamenRoleDto();
        dto.setUserId(userId);
        dto.setGamenId(gamenId);
        dto.setBunruiCd(bunruiCd);
        
        List list;
        try{
            list = gamenRoleLogic.excute(dto);
        }catch(NotExistException ne){
            return false;
        }
        
        if(list != null && list.size() > 0){
            return true;
        }
        
        return false;
    }
    /**
     * Nullチェック処理
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }

}
