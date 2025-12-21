package jp.co.isid.mos.bird.office.formdownload.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;

public interface GetUserFormCategoryLogic {

    /**
     * 自分に公開されているカテゴリーを取得する
     * @param String infoShu 情報種別
     * @param String sysDate 日付
     * @param BirdUserInfo
     * @return カテゴリー
     * @exception ApplicationException
     */
    public abstract List execute(String infoShu, String sysDate,
            BirdUserInfo birdUserInfo) throws ApplicationException;

}