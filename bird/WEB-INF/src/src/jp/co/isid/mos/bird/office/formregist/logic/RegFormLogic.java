/*
 * 作成日: 2006/01/19
 */
package jp.co.isid.mos.bird.office.formregist.logic;

import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.office.formregist.dto.FormRegistFormDto;

/**
 * フォーム情報の登録ロジック インターフェイス
 * @author xytamura
 */
public interface RegFormLogic {
    /**
     * フォーム情報の登録
     * @param FormRegistFormDto 編集画面DTO
     * @param PublicTargetDto 公開対象 共通DTO
     * @param MstUser ユーザー情報
     * @exception ApplicationException
     */
    public void execute(FormRegistFormDto dto, 
                                PublicTargetDto publicTargetDto,
                                MstUser user) throws ApplicationException;
}