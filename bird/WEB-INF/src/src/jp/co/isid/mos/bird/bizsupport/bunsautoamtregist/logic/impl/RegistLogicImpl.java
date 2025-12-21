package jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.logic.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.dao.UIAutoAmountDataDao;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.entity.UIAutoAmountData;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.logic.RegistLogic;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.util.BunsAutoAmtRegistUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoTargetException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * データ登録ロジック
 * 
 * 更新日：2007/03/12 数量登録時、数量データTBL（TM28SAUT）のマスターバージョンの登録について、
 *                    新規登録時は１ですが、編集時は、更新を行う必要がなかった。
 *                    そのため、更新登録時はマスターヴァージョンを変更しないよう修正を行いました。
 * @author xkinu
 *
 */
public class RegistLogicImpl implements RegistLogic {
    /** ロジックID */
    public static final String LOGIC_ID = BunsAutoAmtRegistUtil.SCREEN_ID+"L06";
    /* DAO【バンズ自動設定数量データ】 */
    private UIAutoAmountDataDao bunsAutoAmtRegistUIAutoAmountDataDao;
 
    /**
     * 実行処理
     */
    public List execute(Map params) {
        //１．事前条件処理を実行する。
        validate(params);
        //２．タイムスタンプを生成する。
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        BirdUserInfo userInfo = (BirdUserInfo)params.get(PK_USERINFO);
        List regList = (List)params.get(PK_LIST_REGDATA);
        //処理対象　ユーザーID
        String userId = userInfo.getUserID();
        String userPgm = LOGIC_ID.substring(0, 7);
        for (int i=0; i<regList.size(); i++){
            UIAutoAmountData entity = (UIAutoAmountData)regList.get(i);
             //更新ユーザー ＝　パラメーター.ユーザーID
            entity.setLastUser(userId);
            //更新PGM ＝　画面ID＋"L"
            entity.setLastPgm(userPgm);
            if(entity.getFirstTmsp() == null){
                //登録ユーザー ＝　パラメーター.ユーザーID
                entity.setUnitJ("01");
                //登録ユーザー ＝　パラメーター.ユーザーID
                entity.setFirstUser(userId);
                //登録PGM ＝　画面ID＋"L"
                entity.setFirstPgm(userPgm);
                //登録PGM ＝　画面ID＋"L"
                entity.setFirstTmsp(currentTimestamp);
                //登録PGM ＝　画面ID＋"L"
                entity.setLastTmsp(currentTimestamp);
                //マスターヴァージョン
                entity.countUpMstVer();
                getBunsAutoAmtRegistUIAutoAmountDataDao().insert(entity);
            }else{
//2007/03/12 DELETE start T.Kinugawa 
                //マスターヴァージョン
//                entity.countUpMstVer();
//2007/03/12 DELETE end T.Kinugawa 
                getBunsAutoAmtRegistUIAutoAmountDataDao().update(entity);
            }
        }
        return null;
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
        BirdDateInfo dateInfo = (BirdDateInfo)params.get(PK_DATEINFO);
        if(dateInfo == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("日付情報");
        }
        //処理対象　登録対象データ
        List listKikan = (List)params.get(PK_LIST_REGDATA);
        if (listKikan == null || listKikan.size() == 0) {
            //MSG【E0203】ありません。
            //処理対象が存在しない。
            throw new NoTargetException("登録対象データ");
        }
    }
    /**
     * @return bunsAutoAmtRegistAutoAmountDataDao を戻します。
     */
    public UIAutoAmountDataDao getBunsAutoAmtRegistUIAutoAmountDataDao() {
        return bunsAutoAmtRegistUIAutoAmountDataDao;
    }
    /**
     * @param dao 設定する bunsAutoAmtRegistAutoAmountDataDao。
     */
    public void setBunsAutoAmtRegistUIAutoAmountDataDao(
            UIAutoAmountDataDao dao) {
        this.bunsAutoAmtRegistUIAutoAmountDataDao = dao;
    }

}
