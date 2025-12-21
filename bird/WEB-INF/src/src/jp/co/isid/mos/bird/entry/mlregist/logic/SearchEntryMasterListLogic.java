/*
 * 作成日: 2006/06/09
 *
 */
package jp.co.isid.mos.bird.entry.mlregist.logic;

import jp.co.isid.mos.bird.entry.mlregist.dto.MlRegistDto;

/**
 * エントリーマスタ管理検索ロジック
 * 
 * @author xyuchida
 */
public interface SearchEntryMasterListLogic {

    /**
     * エントリーマスタ管理検索
     * @param mlRegistDto マスタライセンスマスタ登録DTO
     */
    public void execute(MlRegistDto mlRegistDto);
}
