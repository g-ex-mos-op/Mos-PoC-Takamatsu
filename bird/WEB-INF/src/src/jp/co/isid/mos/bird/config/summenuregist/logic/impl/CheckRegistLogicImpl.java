package jp.co.isid.mos.bird.config.summenuregist.logic.impl;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.config.summenuregist.dao.MstSumMenuDao;
import jp.co.isid.mos.bird.config.summenuregist.dto.SumMenuRegistDto;
import jp.co.isid.mos.bird.config.summenuregist.entity.MstSumMenu;
import jp.co.isid.mos.bird.config.summenuregist.logic.SumMenuRegistLogic;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.MissingConfigException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.verifier.NumericVerifier;

/**
 * 登録チェック
 * @author xnkusama
 *
 */
public class CheckRegistLogicImpl implements SumMenuRegistLogic {
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
        int menuCnt = 0;
        // １．事前条件処理
        validate(dto);
        // ２．パラメータ．セッションDTO．子メニューリストの全件に対して下記の処理を行う
        for (int i = 0; i < dto.getListKoMenu().size(); i++) {
            MstSumMenu entity = (MstSumMenu) dto.getListKoMenu().get(i);
            //換算値が未入力の場合
            if (CommonUtil.isNull(entity.getConvValue())) {
                throw new NotNullException("換算値", "convValue", i);
            }
            //換算値が半角数字以外の場合
            NumericVerifier numVerifier = new NumericVerifier(false, 3);
            if (!numVerifier.validate(entity.getConvValue())) {
                throw new GenericMessageException("換算値は半角数字で入力してください。", "convValue", i);
            }
            //換算値 ≧ 1 以外の場合
            if ((new Integer(entity.getConvValue())).intValue() < 1) {
                throw new InvalidInputException("換算値", "convValue", i);
            }
            if (!entity.isSakujoFlg()) {
                menuCnt++;
            }
        }
        // ３．子メニュー登録チェック
        if (menuCnt == 1) {
            //add 2008/09/24 SP020406
            throw new MissingConfigException("子メニュー");
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
