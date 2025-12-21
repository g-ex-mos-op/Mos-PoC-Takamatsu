package jp.co.isid.mos.bird.bizreport.groupeigyoniporef.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.common.code.NipoZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.common.common.BizReportConstants;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;
import jp.co.isid.mos.bird.bizreport.common.util.NipoRefUtil;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.common.EigyoNipoConstants;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.entity.TrnUriageNipoInfo;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.logic.GroupEigyoNipoSearchLogic;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.logic.UriageNipoInfoLogic;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * 店舗別営業日報(オーナー)CSVダウンロードロジック
 *
 * @author   xkhata
 * @modifier xjung  2006/10/03 総合営業日報タブ連携対応
 */
public class GroupEigyoNipoOnerCsvOutputLogicImpl implements CsvOutputLogic {
    /** 店舗別営業日報(オーナー)CSVダウンロードロジックID */
    public static final String LOGIC_ID = "BBR001L10";
    
    /** 検索条件取得ロジック */ 
    private GroupEigyoNipoSearchLogic groupEigyoNipoSearchLogic;

    /** 店別売上予算情報取得ロジック */ 
    private UriageNipoInfoLogic uriageNipoInfoLogic;

    /**
     * CSVファイル名称を取得する
     * @param   csvOutputDto    CSV出力DTO
     * @return  String          CSVファイル名称
     */
    public String getFileName(CsvOutputDto csvOutputDto) {   
        return EigyoNipoConstants.CSV_FILE_NAME_SIBU;
    }

    /**
     * 入力チェックをする
     * @param csvOutputDto  CSV出力DTO
     */
    public void validate(CsvOutputDto csvOutputDto) {
        // 処理なし
    }

    /**
     * CSV出力データを作成する
     * @param   csvOutputDto    CSV出力DTO
     * @return  List            CSV出力データリスト
     */  
    public List getOutputData(CsvOutputDto csvOutputDto) {
        // 数値タイプの文字列フォーマッタ
        NumericFormatter formatter = new NumericFormatter();
        // 数値タイプの文字列フォーマッタ(定数)
        // 数値タイプの文字列フォーマッタ(定数)
        NumericFormatter formatterUriage = new NumericFormatter(true, BizReportConstants.FORMAT_JPY, true);

        // CSV出力データリスト、ヘッダー部情報取得
        List outputList = getHeadList(csvOutputDto);
 
        // 条件部情報Dto取得
        NipoRefConditionParameterDto dto = (NipoRefConditionParameterDto) csvOutputDto;

        // 検索条件取得
        Map paramMap = getGroupEigyoNipoSearchLogic().getSearchData(dto);

        // 売上情報取得
        Map resultMap = getUriageNipoInfoLogic().execute(paramMap);
        List resultList = (List) resultMap.get(EigyoNipoConstants.MAP_RESULT); 
        // データ部情報取得
        for(int i = 0; i < resultList.size(); i++) {
            List lineList = new ArrayList();
            TrnUriageNipoInfo result = (TrnUriageNipoInfo)resultList.get(i);
            // 店行の場合、店コード・名称設定
            if (result.getRClass().equals(EigyoNipoConstants.EMPTY)) {
                lineList.add(result.getMiseCd());
                lineList.add(result.getMiseNameKj());
            // 合計行の場合、ブランク、ブロック名称設定
            } else {
                lineList.add(EigyoNipoConstants.EMPTY);
                lineList.add(result.getBlockName());
            }
            // 売上、予算、達成率
            lineList.add(formatterUriage.format(result.getUriage(), true));
            lineList.add(formatter.format(result.getYosan(), true));
            lineList.add(formatter.format(result.getTassei(), true));
            // 期日指定日報の場合天候、以外の場合営業日数設定
            if(TaishoKikan.DAY1.equals(dto.getTaishoKikanCd())) {
                lineList.add(result.getTenkoKbnJpn());
            } else {
                lineList.add(formatter.format(result.getEigyoDays()));
            }
            // 前年売上、前年比(売上)
            lineList.add(formatterUriage.format(result.getUriageZen(), true));
            lineList.add(formatter.format(result.getZenHiUri(), true));
            // 客数、前年客数、前年比(客数)
            lineList.add(formatter.format(result.getKyakusu(), true));
            lineList.add(formatter.format(result.getKyakusuZen(), true));
            lineList.add(formatter.format(result.getZenHiKyaku(), true));
            // 客単価、前年客単価、前年比(客単価)
            lineList.add(formatterUriage.format(result.getTanka(), true));
            lineList.add(formatterUriage.format(result.getTankaZen(), true));
            lineList.add(formatter.format(result.getZenHiTanka(), true));
            // 期日指定の場合前年天候、以外の場合前年営業日数設定
            if(TaishoKikan.DAY1.equals(dto.getTaishoKikanCd())) {
                lineList.add(result.getTenkoKbnZenJpn());
            } else {
                lineList.add(formatter.format(result.getEigyoDaysZen()));
            }
 
            outputList.add(lineList);
        }
        return outputList;
    }

    /**
     * ヘッダー部情報を作成する
     * @param   csvOutputDto    CSV出力DTO
     * @return  List            ヘッダー部情報リスト
     */
    private List getHeadList(CsvOutputDto csvOutputDto) {
        // ヘッダー部情報リスト
        List headerList = new ArrayList();

        // 営業日報条件部DTO取得
        NipoRefConditionParameterDto dto = (NipoRefConditionParameterDto) csvOutputDto;

        // １行目:会社、対象店舗
        List headRow1List = new ArrayList();
        headRow1List.add(EigyoNipoConstants.CSV_HD_COMPANY);
        headRow1List.add(dto.getCompanyName());

        // ２行目：前年データ種別
        List headRow2List = new ArrayList();
        headRow2List.add(EigyoNipoConstants.CSV_HD_ZEN_DATA_SHU);
        headRow2List.add(NipoZennenDataShubetu.getName
            (dto.getZenDataZennenOthCd(), dto.getUserTypeCd()));

        // ３行目：対象期間
        List headRow3List = new ArrayList();
        headRow3List.add(EigyoNipoConstants.CSV_HD_TAISHO_KIKAN);
        headRow3List.add(NipoRefUtil.getCsvTaishoKikan(dto));

        // ４行目
        List headRow4List = new ArrayList();

        // ５行目
        List headRow5List = new ArrayList(); 

        // 店コード・名称
        headRow5List.add(EigyoNipoConstants.CSV_DT_MISE_CD);
        headRow5List.add(EigyoNipoConstants.CSV_DT_MISE_NAME);
        // 売上、予算、達成率
        headRow5List.add(EigyoNipoConstants.CSV_DT_URIAGE);
        headRow5List.add(EigyoNipoConstants.CSV_DT_YOSAN);
        headRow5List.add(EigyoNipoConstants.CSV_DT_TASSEI);
        // 期日指定の場合天候、以外の場合営業日数設定
        headRow5List.add(TaishoKikan.DAY1.equals(dto.getTaishoKikanCd()) ?
            EigyoNipoConstants.CSV_DT_TENKOU : EigyoNipoConstants.CSV_DT_EIGYO_DAYS);
        // 前年売上、前年比(売上)
        headRow5List.add(EigyoNipoConstants.CSV_DT_URIAGE_ZEN);        
        headRow5List.add(EigyoNipoConstants.CSV_DT_ZEN_HI_URIAGE);
        // 客数、前年客数、前年比(客数)
        headRow5List.add(EigyoNipoConstants.CSV_DT_KYAKUSU);
        headRow5List.add(EigyoNipoConstants.CSV_DT_KYAKUSU_ZEN);
        headRow5List.add(EigyoNipoConstants.CSV_DT_ZEN_HI_KYAKUSU);
        // 客単価、前年客単価、前年比(客単価)
        headRow5List.add(EigyoNipoConstants.CSV_DT_KYAKU_TANKA);
        headRow5List.add(EigyoNipoConstants.CSV_DT_KYAKU_TANKA_ZEN);
        headRow5List.add(EigyoNipoConstants.CSV_DT_ZEN_HI_KYAKU_TANKA);
        // 期日指定の場合前年天候、以外の場合前年営業日数設定
        headRow5List.add(TaishoKikan.DAY1.equals(dto.getTaishoKikanCd()) ?
            EigyoNipoConstants.CSV_DT_ZEN_TENKOU : EigyoNipoConstants.CSV_DT_ZEN_EIGYO_DAYS);

        headerList.add(headRow1List);
        headerList.add(headRow2List);
        headerList.add(headRow3List);
        headerList.add(headRow4List);
        headerList.add(headRow5List);
 
        return headerList;
    }

    /**
     * 検索条件取得ロジックを取得する
     * @return GroupEigyoNipoSearchLogic 検索条件取得ロジック
     */
    public GroupEigyoNipoSearchLogic getGroupEigyoNipoSearchLogic() {
        return groupEigyoNipoSearchLogic;
    }

    /**
     *  検索条件取得ロジックを設定する
     * @param groupEigyoNipoSearchLogic 検索条件取得ロジック
     */
    public void setGroupEigyoNipoSearchLogic(GroupEigyoNipoSearchLogic groupEigyoNipoSearchLogic) {
        this.groupEigyoNipoSearchLogic = groupEigyoNipoSearchLogic;
    }

    /**
     * 店別売上予算情報取得ロジックを取得する
     * @return UriageNipoInfoLogic 店別売上予算情報取得ロジック
     */
    public UriageNipoInfoLogic getUriageNipoInfoLogic() {
        return this.uriageNipoInfoLogic;
    }

    /**
     *  店別売上予算情報取得ロジックを設定する
     * @param uriageNipoInfoLogic 店別売上予算情報取得ロジック
     */
    public void setUriageNipoInfoLogic(UriageNipoInfoLogic uriageNipoInfoLogic) {
        this.uriageNipoInfoLogic = uriageNipoInfoLogic;
    }
}