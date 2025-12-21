/*
 * 作成日: 2006/07/05
 *
 */
package jp.co.isid.mos.bird.office.bbsregist.logic.impl;

import java.sql.Timestamp;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.office.bbsregist.dao.UIKeijibanDao;
import jp.co.isid.mos.bird.office.bbsregist.dto.KeijibanRegistDto;
import jp.co.isid.mos.bird.office.bbsregist.entity.UIKeijiban;
import jp.co.isid.mos.bird.office.bbsregist.logic.RegKeijibanLogic;

/**
 * 掲示板情報の登録
 * @author xytamura
 */
public class RegKeijibanLogicImpl implements RegKeijibanLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BOF003L05";

    private static final int REG_MODE_INSERT = 1;

    private static final int REG_MODE_UPDATE = 2;

    private static final int REG_MODE_DELETE = 3;

    // DAO
    private UIKeijibanDao uIKeijibanDao;

    /*
     * (非 Javadoc)
     * @see jp.co.isid.mos.bird.office.bbsregist.logic.impl.RegKeijibanLogic#execute(jp.co.isid.mos.bird.office.bbsregist.dto.KeijibanRegistDto,
     * jp.co.isid.mos.bird.framework.entity.MstUser,
     * jp.co.isid.mos.bird.framework.control.BirdDateInfo)
     */
    public void execute(KeijibanRegistDto dto, MstUser user,
            BirdDateInfo birdDateInfo) throws ApplicationException {
        // Entity
        UIKeijiban uiKeijiban = dto.getEditEntity();

        Timestamp beforeUpdate = uiKeijiban.getLastTmsp();

        uiKeijiban.setLastUser(user.getUser_id());
        uiKeijiban.setLastPgm(KeijibanRegistDto.VIEW_ID);
        uiKeijiban.setLastTmsp(DateManager.getCurrentTimestamp());

        /* 新規 */
        if (dto.getRegMode() == REG_MODE_INSERT) {
            // 現在のMAX SEQ番号 + 1
            int maxSeq = getUIKeijibanDao()
                    .getNumber(birdDateInfo.getSysDate()) + 1;

            // SEQをセットしてから登録
            CodeFormatter conv = new CodeFormatter(4, "0000");
            conv.setFormatPattern("0000");
            String strMaxSeq = conv.format(String.valueOf(maxSeq), false);
            uiKeijiban.setSeq(strMaxSeq);
            uiKeijiban.setRegDate(birdDateInfo.getSysDate());
            uiKeijiban.setSakujoFlg("0");
            uiKeijiban.setFirstUser(user.getUser_id());
            uiKeijiban.setFirstPgm(KeijibanRegistDto.VIEW_ID);
            uiKeijiban.setFirstTmsp(DateManager.getCurrentTimestamp());
            getUIKeijibanDao().insertKeijibanInfo(uiKeijiban);

            /* 更新 */
        } else if (dto.getRegMode() == REG_MODE_UPDATE) {
            uiKeijiban.setLastPgm(KeijibanRegistDto.VIEW_ID);
            getUIKeijibanDao().updateKeijibanInfo(uiKeijiban);
            uiKeijiban.setLastTmsp(beforeUpdate);

            /* 削除 */
        } else if (dto.getRegMode() == REG_MODE_DELETE) {
            // 削除フラグ＝'1'にしてから更新
            uiKeijiban.setSakujoFlg("1");
            getUIKeijibanDao().updateKeijibanInfo(uiKeijiban);
            uiKeijiban.setLastTmsp(beforeUpdate);

        }
    }

    /**
     * 掲示板DAOを取得します。
     * @return 掲示板DAO
     */
    public UIKeijibanDao getUIKeijibanDao() {
        return uIKeijibanDao;
    }

    /**
     * 掲示板DAOを設定します。
     * @param 掲示板DAO
     */
    public void setUIKeijibanDao(UIKeijibanDao keijibanDao) {
        uIKeijibanDao = keijibanDao;
    }

}