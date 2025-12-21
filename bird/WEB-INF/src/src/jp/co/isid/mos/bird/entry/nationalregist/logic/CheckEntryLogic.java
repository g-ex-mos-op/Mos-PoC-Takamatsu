package jp.co.isid.mos.bird.entry.nationalregist.logic;

import jp.co.isid.mos.bird.entry.nationalregist.dto.NationalRegistDto;

/**
 * 全国大会マスタ情報チェックロジック インターフェース
 *
 * @author xjung
 */
public interface CheckEntryLogic {

    /**
     * 全国大会マスタ情報の事前チェック(入力必須、妥当性、相関)を行う。
     * @param dto 全国大会マスタ登録情報DTO
     */
    public void execute(NationalRegistDto dto);
}