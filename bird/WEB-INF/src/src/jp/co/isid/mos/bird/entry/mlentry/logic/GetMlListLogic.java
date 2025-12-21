package jp.co.isid.mos.bird.entry.mlentry.logic;

import java.util.List;

/**
 * マスターライセンス一覧取得ロジックインターフェース
 * @author Aspac
 */
public interface GetMlListLogic {

    public List execute(String sysdate, String sysnextdate, String entrycd, String onerCd, String userTypeCd);
    
}
