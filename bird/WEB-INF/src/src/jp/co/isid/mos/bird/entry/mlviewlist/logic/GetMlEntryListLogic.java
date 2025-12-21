package jp.co.isid.mos.bird.entry.mlviewlist.logic;

import java.util.List;

/**
 * マスターライセンス申込状況一覧取得ロジックインターフェース
 * @author Aspac
 */
public interface GetMlEntryListLogic {

    public List execute(String kbn, String sysdate, String entryCd, String entryYear, String entryKai, String searchSelectFlg, String sibuCd, String onerCd, String miseCd);

}
