package jp.co.isid.mos.bird.bizsupport.noinputoner.logic;

import java.util.HashMap;


/**
 * P/Lデータチェックロジック
 * 
 * @author Aspac
 */
public interface CheckPLDataNoInputLogic {

    /**
     * P/Lデータチェック
     * @param P/Lデータ
     */
    public void execute(HashMap hashMap);
}
