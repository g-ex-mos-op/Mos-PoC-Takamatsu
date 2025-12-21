/*
 * 作成日: 2006/03/24
 *
 */
package jp.co.isid.mos.bird.bizsupport.lumptakein.logic;

/**
 * P/Lデータ店名称取得ロジック
 * 
 * @author xyuchida
 */
public interface GetPLMiseNameLogic {

    /**
     * P/Lデータ店名称取得
     * @param plType P/Lの種類
     * @param miseCd 店コード
     * @return 店名称
     */
    public String execute(String plType, String miseCd);
}
