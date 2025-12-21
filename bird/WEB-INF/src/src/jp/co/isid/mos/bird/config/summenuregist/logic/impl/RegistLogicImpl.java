package jp.co.isid.mos.bird.config.summenuregist.logic.impl;

import java.sql.Timestamp;
import java.util.Iterator;

import org.seasar.framework.exception.SQLRuntimeException;

import jp.co.isid.mos.bird.config.summenuregist.common.SumMenuRegistConst;
import jp.co.isid.mos.bird.config.summenuregist.dao.MstSumMenuDao;
import jp.co.isid.mos.bird.config.summenuregist.dto.SumMenuRegistDto;
import jp.co.isid.mos.bird.config.summenuregist.entity.MstSumMenu;
import jp.co.isid.mos.bird.config.summenuregist.logic.SumMenuRegistLogic;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteWithReasonException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 登録チェック
 * @author xnkusama
 *
 */
public class RegistLogicImpl implements SumMenuRegistLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BCF009L04";

    /*DAO*/
    private MstSumMenuDao sumMenuRegistMstSumMenuDao;
    
    /**
     * 指定メニュー登録済みチェック
     * @param dto
     * @param menuCd
     * @param mode
     */
    public void execute(SumMenuRegistDto dto) {
        // １．事前条件処理
        validate(dto);
        // ２．パラメータ．セッションDTO．子メニューリストの全件に対して下記の処理を行う
        for (Iterator ite = dto.getListKoMenu().iterator(); ite.hasNext();) {
            MstSumMenu entity = (MstSumMenu) ite.next();
            Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
            // [集約メニューマスタ]．新規行フラグ ＝ trueの場合、[集約メニューマスタ]に下記の値をセット
            if (entity.isInsertFlg()) {
                entity.setFirstUser(dto.getUserId());
                entity.setFirstPgm(SumMenuRegistConst.GAMEN_ID);
                entity.setFirstTmsp(currentTimestamp);
                entity.setLastUser(dto.getUserId());
                entity.setLastPgm(SumMenuRegistConst.GAMEN_ID);
            }
            //[集約メニューマスタ]．新規行フラグ ＝ false かつ 削除フラグ＝falseの場合、[集約メニューマスタ]に下記の値をセット
            else if (!entity.isInsertFlg() && !entity.isSakujoFlg()) {
                entity.setLastUser(dto.getUserId());
                entity.setLastPgm(SumMenuRegistConst.GAMEN_ID);
            }
            
            try {
                // [集約メニューマスタ]．新規行フラグ＝true かつ [集約メニューマスタ]．削除フラグ＝falseの場合
                if (entity.isInsertFlg() && !entity.isSakujoFlg()) {
                        getSumMenuRegistMstSumMenuDao().insertMenu(entity);
                }
                //  [集約メニューマスタ]．新規行フラグ＝false かつ [集約メニューマスタ]．削除フラグ＝trueの場合
                else if (!entity.isInsertFlg() && entity.isSakujoFlg()) {
                    getSumMenuRegistMstSumMenuDao().deleteMenu(entity);   
                }
                //  [集約メニューマスタ]．新規行フラグ＝false かつ [集約メニューマスタ]．削除フラグ＝falseの場合
                else if (!entity.isSakujoFlg()) {
                    getSumMenuRegistMstSumMenuDao().updateMenu(entity);
                }
            }
            catch (SQLRuntimeException sqlex) {
                throw new CannotExecuteWithReasonException("このデータは既に更新されている", "更新");
            }
        }
    }

    /**
     * 初期処理
     * @param menuCd
     */
    private void validate(SumMenuRegistDto dto) {
        if (dto == null) {
            throw new NotNullException("画面情報");
        }
        if (dto.getListKoMenu() == null || dto.getListKoMenu().isEmpty()) {
            throw new MissingDataException("登録情報");
        }
    }

    public MstSumMenuDao getSumMenuRegistMstSumMenuDao() {
        return sumMenuRegistMstSumMenuDao;
    }

    public void setSumMenuRegistMstSumMenuDao(
            MstSumMenuDao sumMenuRegistMstSumMenuDao) {
        this.sumMenuRegistMstSumMenuDao = sumMenuRegistMstSumMenuDao;
    }

}
