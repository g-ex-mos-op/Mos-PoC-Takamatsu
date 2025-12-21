/*
 * 作成日: 2006/01/23
 */
package jp.co.isid.mos.bird.inforegist.notificationregist.logic;

import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.inforegist.notificationregist.dto.RegistFormDto;

/**
 * 登録された内容をチェックする
 * @author m.onodera
 */
public interface CheckTutatuLogic {
    /**
     * 既存の文書情報一覧の取得
     * @param RegistFormDto 編集画面DTO
     * @param PublicTargetDto 公開対象共通DTO
     * @exception ApplicationException
     */
    public void checkTutatu(RegistFormDto dto, PublicTargetDto publicTargetDto) throws ApplicationException;
}