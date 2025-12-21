/*
 * 作成日: 2006/07/05
 *
 */
package jp.co.isid.mos.bird.office.bbsregist.logic;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.office.bbsregist.dto.KeijibanRegistDto;

public interface CheckKeijibanInfoLogic {

    /**
     * 登録内容のチェック
     * @param dto パラメータ
     * @throws ApplicationException
     */
    public void execute(KeijibanRegistDto dto)
            throws ApplicationException;

}