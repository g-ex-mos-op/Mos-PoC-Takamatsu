package jp.co.isid.mos.bird.entry.mlentry.logic;

import java.util.List;


/**
 * 受験地情報取得ロジック
 * @author Aspac
 */
public interface GetPlaceListLogic {

    public List execute(String entryCd, String entryYear, String entryKai);
    
}
