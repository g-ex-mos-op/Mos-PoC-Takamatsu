package jp.co.isid.mos.bird.common.logic.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.common.dao.PublicTargetKobetuMiseDao;
import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.common.entity.PublicTargetKobetuMise;
import jp.co.isid.mos.bird.common.entity.TrnControlGyotaiTenpo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;


/**
 * 公開対象 個別店舗設定一覧情報ダウンロード
 * 
 * @author kusama
 */
public class PublicTargetMiseCsvOutputLogicImpl implements CsvOutputLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BCN00L20";

    /** 情報種別 */
    private static final String INFO_SHU_RENRAKU = "01";
    private static final String INFO_SHU_TUTATU = "02";
    private static final String INFO_SHU_BUNSYO = "03";
    private static final String INFO_SHU_FORM = "04";
    private static final String INFO_SHU_OSIRASE = "05";
    
    private PublicTargetKobetuMiseDao publicTargetKobetuMiseDao;
    
    /** 
     * [インナークラス]比較用
     */
    private class SortComparator implements Comparator {

        public int compare(Object obj1, Object obj2) {

            //比較対象エンティティ
            TrnControlGyotaiTenpo ent1 = (TrnControlGyotaiTenpo)obj1;
            TrnControlGyotaiTenpo ent2 = (TrnControlGyotaiTenpo)obj2;

            String str1 = ent1.getMiseCd();
            
            String str2 = ent2.getMiseCd();
            
            return str1.compareTo(str2);
        }
    }
    
    /**
     * ファイル名取得
     * @param csvOutputDto CSV出力用DTO
     * @return ファイル名
     */
    public String getFileName(CsvOutputDto csvOutputDto) {
        PublicTargetDto publicTargetDto = (PublicTargetDto) csvOutputDto;
        
        String filename = "";
        String infoShu = publicTargetDto.getInfoShu();
        
        if (INFO_SHU_RENRAKU.equals(infoShu)) {
            filename = "RENRAKU_KOTEN.csv";
        }
        else if (INFO_SHU_TUTATU.equals(infoShu)) {
            filename = "TUTATU_KOTEN.csv";
        }
        else if (INFO_SHU_BUNSYO.equals(infoShu)) {
            filename = "BUNSYO_KOTEN.csv";
        }
        else if (INFO_SHU_FORM.equals(infoShu)) {
            filename = "FORM_KOTEN.csv";
        }
        else if (INFO_SHU_OSIRASE.equals(infoShu)) {
            filename = "OSIRASE_KOTEN.csv";
        }
        // テンプレートCSVファイル名
        return filename;
    }

    /**
     * 出力データ取得処理
     * @param csvOutputDto CSV出力用DTO
     * @return CSV出力データリスト
     */
    public List getOutputData(CsvOutputDto csvOutputDto) {

        PublicTargetDto publicTargetDto = (PublicTargetDto) csvOutputDto;
        List listCsvData = new ArrayList();
        List listGyotaiData = publicTargetDto.getListTrnControlGyotaiTenpo();

        //店コードでソート
        Collections.sort(listGyotaiData, new SortComparator());
        for (Iterator ite = listGyotaiData.iterator(); ite.hasNext();) {
            TrnControlGyotaiTenpo entity = (TrnControlGyotaiTenpo) ite.next();
            List listRowData = new ArrayList();
            listRowData.add(entity.getMiseCd());
            listRowData.add(this.trim(entity.getMiseNameKj()));
            PublicTargetKobetuMise publicTargetKobetuMise 
                = getPublicTargetKobetuMiseDao().getMiseInfo(entity.getMiseCd(), entity.getGyotaiKbn());
            listRowData.add(publicTargetKobetuMise.getSibuName());
            listRowData.add(publicTargetKobetuMise.getGyotaiKbnName());
            
            listCsvData.add(listRowData);
        }
        
        return listCsvData;
    }

    /**
     * 事前条件チェック処理
     * @param csvOutputDto CSV出力用DTO
     */
    public void validate(CsvOutputDto csvOutputDto) {
    }

    public PublicTargetKobetuMiseDao getPublicTargetKobetuMiseDao() {
        return publicTargetKobetuMiseDao;
    }

    public void setPublicTargetKobetuMiseDao(
            PublicTargetKobetuMiseDao publicTargetKobetuMiseDao) {
        this.publicTargetKobetuMiseDao = publicTargetKobetuMiseDao;
    }
    
    /**
     * Trim処理（nullはブランクに変換されます）
     */
    private String trim(String value) {
        return value == null ? "" : value.trim();
    }
}