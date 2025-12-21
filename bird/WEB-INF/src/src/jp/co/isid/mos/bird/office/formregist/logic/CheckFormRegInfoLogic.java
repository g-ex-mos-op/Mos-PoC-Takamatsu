/*
 * 作成日: 2006/01/23
 */
package jp.co.isid.mos.bird.office.formregist.logic;

import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.office.formregist.dto.FormRegistFormDto;

/**
 * 登録された内容をチェックする
 * @author xytamura
 */
public interface CheckFormRegInfoLogic {
    /**
     * 既存のフォーム情報一覧の取得
     * @param FormRegistFormDto 編集画面DTO
     * @param PublicTargetDto 公開対象共通DTO
     * @exception ApplicationException
     */
    public void execute(FormRegistFormDto dto, PublicTargetDto publicTargetDto) throws ApplicationException;
}