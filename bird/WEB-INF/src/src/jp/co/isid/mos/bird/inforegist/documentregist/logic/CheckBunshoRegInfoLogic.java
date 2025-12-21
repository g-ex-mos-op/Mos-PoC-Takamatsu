/*
 * 作成日: 2006/01/23
 */
package jp.co.isid.mos.bird.inforegist.documentregist.logic;

import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.inforegist.documentregist.dto.DocumentRegistRegistFormDto;

/**
 * 登録された内容をチェックする
 * @author xnkusama
 */
public interface CheckBunshoRegInfoLogic {
    /**
     * 既存の文書情報一覧の取得
     * @param RegistFormDto 編集画面DTO
     * @param PublicTargetDto 公開対象共通DTO
     * @exception ApplicationException
     */
    public void checkBunshoRegInfo(DocumentRegistRegistFormDto dto, PublicTargetDto publicTargetDto) throws ApplicationException;
}