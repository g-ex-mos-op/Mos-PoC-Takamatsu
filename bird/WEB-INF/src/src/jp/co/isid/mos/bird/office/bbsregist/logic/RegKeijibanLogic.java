/*
 * 作成日: 2006/07/05
 *
 */
package jp.co.isid.mos.bird.office.bbsregist.logic;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.office.bbsregist.dto.KeijibanRegistDto;

public interface RegKeijibanLogic {

    /**
     * 掲示板情報の登録
     * @param dto パラメータ
     * @param user ユーザ情報
     * @param birdDateInfo 日付情報
     * @throws ApplicationException
     */
    public void execute(KeijibanRegistDto dto, MstUser user,
            BirdDateInfo birdDateInfo) throws ApplicationException;

}