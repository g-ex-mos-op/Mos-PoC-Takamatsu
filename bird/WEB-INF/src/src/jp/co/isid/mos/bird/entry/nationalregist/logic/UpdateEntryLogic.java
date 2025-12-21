package jp.co.isid.mos.bird.entry.nationalregist.logic;

import jp.co.isid.mos.bird.entry.nationalregist.dto.NationalRegistDto;

/**
 * 全国大会マスタ情報登録ロジック インターフェース
 *
 * @author xjung
 */
public interface UpdateEntryLogic {

    /**
     * 全国大会マスタ情報を登録、更新する。
     * @param dto 全国大会マスタ登録情報DTO
     */
    public void execute(NationalRegistDto dto);

}