package jp.co.isid.mos.bird.entry.manageregist.logic;

import jp.co.isid.mos.bird.entry.manageregist.dto.ManageRegistDto;

/**
 * 全国店長勉強会マスタ情報登録ロジック インターフェース
 *
 * @author xjung
 */
public interface UpdateEntryLogic {

    /**
     * 全国店長勉強会マスタ情報を登録、更新する。
     * @param dto 全国店長勉強会マスタ登録情報DTO
     */
    public void execute(ManageRegistDto dto);

}