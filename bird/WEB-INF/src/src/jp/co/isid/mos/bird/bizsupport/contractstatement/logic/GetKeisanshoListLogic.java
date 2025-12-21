/* 作成日 2007/07/05
 * 
 */
package jp.co.isid.mos.bird.bizsupport.contractstatement.logic;

import jp.co.isid.mos.bird.bizsupport.contractstatement.dto.ContractStatementDto;

/**
 * 計算書一覧取得ロジック
 * @author xnkusama
 *
 */
public interface GetKeisanshoListLogic {
    /**
     * 計算書一覧取得
     * @param DTO
     * @param システム日付
     * @return 
     */
    public void execute(ContractStatementDto dto, String sysDt);

}
