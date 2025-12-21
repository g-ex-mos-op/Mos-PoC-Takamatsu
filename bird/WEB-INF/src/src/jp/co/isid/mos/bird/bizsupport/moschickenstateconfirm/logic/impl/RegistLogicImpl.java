package jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.logic.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.dao.UIMCStatusInfoDao;
import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.entity.UIMCStatusInfo;
import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.logic.RegistLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.util.MosChickenStateConfirmUtil;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 予約・在庫状況確認(モスチキン用画面）
 * 計算ロジック
 * 
 * @author xkinu
 *
 */
public class RegistLogicImpl implements RegistLogic {
    /** ロジックID */
    public static final String LOGIC_ID = MosChickenStateConfirmUtil.SCREEN_ID+"L03";
    /* パラメーターキー：更新対象データ保持List */
    public static final String PK_REGLIST = "regList";
    /* パラメーターキー：ユーザー情報 */
    public static final String PK_USERINFO = "userInfo";
    /* パラメーターキー：日付 */
    public static final String PK_DATE = "date";
    /* DAO【予約・在庫状況情報】*/
    private UIMCStatusInfoDao mosChickenStateUIMCStatusInfoDao;

    /**
     * @return mosChickenStateUIMCStatusInfoDao を戻します。
     */
    public UIMCStatusInfoDao getMosChickenStateUIMCStatusInfoDao() {
        return mosChickenStateUIMCStatusInfoDao;
    }
    /**
     * @param mosChickenStateUIMCStatusInfoDao 設定する mosChickenStateUIMCStatusInfoDao。
     */
    public void setMosChickenStateUIMCStatusInfoDao(
            UIMCStatusInfoDao mosChickenStateUIMCStatusInfoDao) {
        this.mosChickenStateUIMCStatusInfoDao = mosChickenStateUIMCStatusInfoDao;
    }
    /**
     * 事前条件処理
     * @param map
     * @throws ApplicationException
     */
    private void validate(final Map params) {
        BirdUserInfo userInfo = (BirdUserInfo)params.get(PK_USERINFO);
        if(userInfo == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("ユーザー情報");
        }
        String dateYmd = (String)params.get(PK_DATE);
        if(dateYmd == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("日付");
        }
        List regList = (List)params.get(PK_REGLIST);
        if(regList == null || regList.size() == 0){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("登録対象データ");
        }
    }
    public List execute(Map params) {
        //１．事前条件判断処理
        validate(params);
        //２．タイムスタンプを生成する。
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        BirdUserInfo userInfo = (BirdUserInfo)params.get(PK_USERINFO);
        List regList = (List)params.get(PK_REGLIST);
        //処理対象　ユーザーID
        String userId = userInfo.getUserID();
        String userPgm = LOGIC_ID.substring(0, 7);
        String eigyoDt = (String)params.get(PK_DATE);
        for (int i=0; i<regList.size(); i++){
            UIMCStatusInfo entity = (UIMCStatusInfo)regList.get(i);
            //対象日付
            entity.setEigyoDt(eigyoDt);
            //更新ユーザー ＝　パラメーター.ユーザーID
            entity.setLastUser(userId);
            //更新PGM ＝　画面ID＋"L"
            entity.setLastPgm(userPgm);
            if(entity.getFirstTmsp() == null){
                //登録ユーザー ＝　パラメーター.ユーザーID
                entity.setFirstUser(userId);
                //登録PGM ＝　画面ID＋"L"
                entity.setFirstPgm(userPgm);
                //登録PGM ＝　画面ID＋"L"
                entity.setFirstTmsp(currentTimestamp);
                //登録PGM ＝　画面ID＋"L"
                entity.setLastTmsp(currentTimestamp);
                getMosChickenStateUIMCStatusInfoDao().insert(entity);
            }else{
                getMosChickenStateUIMCStatusInfoDao().update(entity);
            }
        }
        
        return null;
    }

}
