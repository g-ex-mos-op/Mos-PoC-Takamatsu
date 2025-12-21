package jp.co.isid.mos.bird.entry.manageregist.logic;

import jp.co.isid.mos.bird.entry.manageregist.dto.ManageRegistDto;

/**
 * 全国店長勉強会マスタ情報チェックロジック インターフェース
 *
 * @author xjung
 */
public interface CheckEntryLogic {

    /**
     * 全国店長勉強会マスタ情報の事前チェック(入力必須、妥当性、相関)を行う。
     * @param dto 全国店長勉強会マスタ登録情報DTO
     */
    public void execute(ManageRegistDto dto);
}