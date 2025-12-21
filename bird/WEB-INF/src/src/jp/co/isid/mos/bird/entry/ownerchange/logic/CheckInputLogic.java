/*
 * 作成日:2007/01/16
 */
package jp.co.isid.mos.bird.entry.ownerchange.logic;

import jp.co.isid.mos.bird.entry.ownerchange.dto.OwnerChangeDto;

/**
 * 入力値チェックロジック
 * @author xkonishi
 *
 */
public interface CheckInputLogic {
    
    /**
     * 入力値チェックロジック
     * @param オーナー異動登録DTO
     * 
     */
    public void execute(OwnerChangeDto ownerChangeDto);
}