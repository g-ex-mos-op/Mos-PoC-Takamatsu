/*
 * 作成日: 2006/04/03
 *
 */
package jp.co.isid.mos.bird.bizsupport.lumptakein.logic;

/**
 * オーナーコード存在判定ロジック
 * 
 * @author xyuchida
 */
public interface ExistOwnerCodeLogic {

    /**
     * オーナーコード存在判定
     * @param onerCd オーナーコード
     * @return オーナーコード存在有無
     */
    public boolean execute(String onerCd);
}
