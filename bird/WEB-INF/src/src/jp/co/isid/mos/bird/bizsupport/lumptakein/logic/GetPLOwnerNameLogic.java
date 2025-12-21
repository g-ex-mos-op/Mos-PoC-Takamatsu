/*
 * 作成日: 2006/04/03
 *
 */
package jp.co.isid.mos.bird.bizsupport.lumptakein.logic;

/**
 * P/Lデータオーナー名称取得ロジック
 * 
 * @author xyuchida
 */
public interface GetPLOwnerNameLogic {

    /**
     * P/Lデータオーナー名称取得
     * @param onerCd オーナーコード
     * @return オーナー名称
     */
    public String execute(String onerCd);
}
