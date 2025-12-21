package jp.co.isid.mos.bird.entry.mlviewlist.dao;

import java.util.List;
import jp.co.isid.mos.bird.entry.mlviewlist.entity.UIMlEntryCsvInfo;


/**
 * マスターライセンスエントリーCSV一覧情報(UIMlEntryCsvListDao)
 * @author Aspac
 */
public interface UIMlEntryCsvListDao {

    public static final Class  BEAN = UIMlEntryCsvInfo.class;
//---2006/08/14 パラメータ追加    
//    public static final String getMlEntryCsvInfo_ARGS = "sysdate,entryCd,entryYear,entryKai,searchSelectFlg,sibuCd,onerCd,miseCd";
    public static final String getMlEntryCsvInfo_ARGS = "sysdate,entryCd,entryYear,entryKai,searchSelectFlg,sibuCd,onerCd,miseCd, listReEntryBaseYear";

    /**
     * マスターライセンスエントリーCSV一覧情報取得(getMlEntryCsvInfo)
     * @param sysdate,entryCd,entryYear,entryKai,searchSelectFlg,sibuCd,miseCd
     * @param List listReEntryBaseYear
     * @return List 検索結果
     * 修正 2006/08/14 再エントリー基準年度のパラメータを追加
     */
    //public List getMlEntryCsvInfo(String sysdate, String entryCd, String entryYear, String entryKai, String searchSelectFlg, String sibuCd, String onerCd, String miseCd);
    public List getMlEntryCsvInfo(String sysdate, String entryCd, String entryYear, String entryKai, String searchSelectFlg, String sibuCd, String onerCd, String miseCd, List listReEntryBaseYear);

}
