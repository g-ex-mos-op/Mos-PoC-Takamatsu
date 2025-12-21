/*
 * 作成日: 2006/01/19
 */
package jp.co.isid.mos.bird.inforegist.documentregist.logic;

import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.inforegist.documentregist.dto.DocumentRegistRegistFormDto;

/**
 * 文書情報の登録ロジック インターフェイス
 * @author xnkusama
 */
public interface RegBunshoLogic {
    /**
     * 文書情報の登録
     * @param RegistFormDto 編集画面DTO
     * @param PublicTargetDto 公開対象 共通DTO
     * @param MstUser ユーザー情報
     * @exception ApplicationException
     */
    public void registBunsho(DocumentRegistRegistFormDto dto, 
                                PublicTargetDto publicTargetDto,
                                MstUser user) throws ApplicationException;
}