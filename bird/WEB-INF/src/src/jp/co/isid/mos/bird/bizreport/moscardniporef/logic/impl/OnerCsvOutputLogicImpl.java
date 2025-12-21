package jp.co.isid.mos.bird.bizreport.moscardniporef.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.common.code.NipoZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;
import jp.co.isid.mos.bird.bizreport.common.util.NipoRefUtil;
import jp.co.isid.mos.bird.bizreport.moscardniporef.common.MoscardNipoConstants;
import jp.co.isid.mos.bird.bizreport.moscardniporef.entity.TrnUriageNipoInfo;
import jp.co.isid.mos.bird.bizreport.moscardniporef.logic.SearchLogic;
import jp.co.isid.mos.bird.bizreport.moscardniporef.logic.UriageNipoInfoLogic;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * 店舗別営業日報(オーナー)CSVダウンロードロジック
 *
 * @author   xyamauchi
 */
public class OnerCsvOutputLogicImpl implements CsvOutputLogic {
    /** 店舗別営業日報(オーナー)CSVダウンロードロジックID */
    public static final String LOGIC_ID = "BBR015L10";
    
    /** 検索条件取得ロジック */ 
    private SearchLogic groupEigyoNipoSearchLogic;

    /** 店別売上予算情報取得ロジック */ 
    private UriageNipoInfoLogic uriageNipoInfoLogic;

    /**
     * CSVファイル名称を取得する
     * @param   csvOutputDto    CSV出力DTO
     * @return  String          CSVファイル名称
     */
    public String getFileName(CsvOutputDto csvOutputDto) {   
        return MoscardNipoConstants.CSV_FILE_NAME_SIBU;
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
        formatter.setDefaultText("0");

        // CSV出力データリスト、ヘッダー部情報取得
        List outputList = getHeadList(csvOutputDto);
 
        // 条件部情報Dto取得
        NipoRefConditionParameterDto dto = (NipoRefConditionParameterDto) csvOutputDto;

        // 検索条件取得
        Map paramMap = getSearchLogic().getSearchData(dto);

        // 売上情報取得
        Map resultMap = getUriageNipoInfoLogic().execute(paramMap);
        List resultList = (List) resultMap.get(MoscardNipoConstants.MAP_RESULT); 
        // データ部情報取得
        for(int i = 0; i < resultList.size(); i++) {
            List lineList = new ArrayList();
            TrnUriageNipoInfo result = (TrnUriageNipoInfo)resultList.get(i);
            // 店行の場合、店コード・名称設定
            if (result.getRClass().equals(MoscardNipoConstants.EMPTY)) {
                lineList.add(result.getMiseCd());
                lineList.add(result.getMiseNameKj());
            // 合計行の場合、ブランク、ブロック名称設定
            } else {
                lineList.add(MoscardNipoConstants.EMPTY);
                lineList.add(result.getBlockName());
            }
            // 売上、予算、達成率
            lineList.add(formatter.format(result.getUriage(), true));
            lineList.add(formatter.format(result.getYosan(), true));
            lineList.add(formatter.format(result.getTassei(), true));
            // 期日指定日報の場合天候、以外の場合営業日数設定
            if(TaishoKikan.DAY1.equals(dto.getTaishoKikanCd())) {
                lineList.add(result.getTenkoKbnJpn());
            } else {
                lineList.add(formatter.format(result.getEigyoDays()));
            }
            // 前年売上、前年比(売上)
            lineList.add(formatter.format(result.getUriageZen(), true));
            lineList.add(formatter.format(result.getZenHiUri(), true));
            // 客数、前年客数、前年比(客数)
            lineList.add(formatter.format(result.getKyakusu(), true));
            lineList.add(formatter.format(result.getKyakusuZen(), true));
            lineList.add(formatter.format(result.getZenHiKyaku(), true));
            // 客単価、前年客単価、前年比(客単価)
            lineList.add(formatter.format(result.getTanka(), true));
            lineList.add(formatter.format(result.getTankaZen(), true));
            lineList.add(formatter.format(result.getZenHiTanka(), true));
            // 期日指定の場合前年天候、以外の場合前年営業日数設定
            if(TaishoKikan.DAY1.equals(dto.getTaishoKikanCd())) {
                lineList.add(result.getTenkoKbnZenJpn());
            } else {
                lineList.add(formatter.format(result.getEigyoDaysZen()));
            }
            // 発行枚数、前年発行枚数、前年比（売上）
            lineList.add(formatter.format(result.getIssueCnt(), true));
            lineList.add(formatter.format(result.getZenIssueCnt(), true));
            lineList.add(formatter.format(result.getZenIssueCntHiritu(), true));
            //ﾁｬｰｼﾞ金額、ﾁｬｰｼﾞ金額（売上比）、前年ﾁｬｰｼﾞ金額、ﾁｬｰｼﾞ金額（前年比）
            lineList.add(formatter.format(result.getChargeKin(), true));
            lineList.add(formatter.format(result.getChargeKinUriHi(), true));
            lineList.add(formatter.format(result.getZenChargeKin(), true));
            lineList.add(formatter.format(result.getChargeKinZenHi(), true));
            //ﾁｬｰｼﾞ件数、ﾁｬｰｼﾞ件数（客数比）、前年ﾁｬｰｼﾞ件数、ﾁｬｰｼﾞ件数（前年比）
            lineList.add(formatter.format(result.getChargeKen(), true));
            lineList.add(formatter.format(result.getChargeKenKyakuHi(), true));
            lineList.add(formatter.format(result.getZenChargeKen(), true));
            lineList.add(formatter.format(result.getChargeKenZenHi(), true));
            //ﾁｬｰｼﾞ単価、前年ﾁｬｰｼﾞ単価、ﾁｬｰｼﾞ単価（前年比）
            lineList.add(formatter.format(result.getChargeTanka(), true));
            lineList.add(formatter.format(result.getZenChargeTanka(), true));
            lineList.add(formatter.format(result.getChargeTankaZenHi(), true));
            //決済金額、決済金額（売上比）、前年決済金額、決済金額（前年比）
            lineList.add(formatter.format(result.getKessaiKin(), true));
            lineList.add(formatter.format(result.getKessaiKinUrihi(), true));
            lineList.add(formatter.format(result.getZenKessaiKin(), true));
            lineList.add(formatter.format(result.getKessaiKinZenhi(), true));
            //決済件数、決済件数（客数比）、前年決済件数、決済件数（前年比）
            lineList.add(formatter.format(result.getKessaiKen(), true));
            lineList.add(formatter.format(result.getKessaiKenKyakuhi(), true));
            lineList.add(formatter.format(result.getZenKessaiKen(), true));
            lineList.add(formatter.format(result.getKessaiKenZenhi(), true));            
            // 決済単価、決済単価（単価比）、前年決済単価、決済単価（前年比）
            lineList.add(formatter.format(result.getKessaiTanka(), true));
            lineList.add(formatter.format(result.getKessaiTankaHi(), true));
            lineList.add(formatter.format(result.getZenKessaiTanka(), true));
            lineList.add(formatter.format(result.getKessaiTankaZenhi(), true));
//            //入金取消金額、入金取消件数
//            lineList.add(formatter.format(result.getChargeKinCancel(), true));
//            lineList.add(formatter.format(result.getChargeKenCancel(), true));
//            //利用取消金額、利用取消件数
//            lineList.add(formatter.format(result.getUseKinCancel(), true));
//            lineList.add(formatter.format(result.getUseKenCancel(), true));
//            //発行ボーナスバリュー、利用ボーナスバリュー
//            lineList.add(formatter.format(result.getBonusVIssue(), true));
//            lineList.add(formatter.format(result.getBonusVUse(), true));
//            //発行クーポンバリュー、利用クーポンバリュー
//            lineList.add(formatter.format(result.getCouponVIssue(), true));
//            lineList.add(formatter.format(result.getCouponVUse(), true));
//            //前受残高
//            lineList.add(formatter.format(result.getZandaka(), true));
            
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
        headRow1List.add(MoscardNipoConstants.CSV_HD_COMPANY);
        headRow1List.add(dto.getCompanyName());

        // ２行目：前年データ種別
        List headRow2List = new ArrayList();
        headRow2List.add(MoscardNipoConstants.CSV_HD_ZEN_DATA_SHU);
        headRow2List.add(NipoZennenDataShubetu.getName
            (dto.getZenDataZennenOthCd(), dto.getUserTypeCd()));

        // ３行目：対象期間
        List headRow3List = new ArrayList();
        headRow3List.add(MoscardNipoConstants.CSV_HD_TAISHO_KIKAN);
        headRow3List.add(NipoRefUtil.getCsvTaishoKikan(dto));

        // ４行目
        List headRow4List = new ArrayList();

        // ５行目
        List headRow5List = new ArrayList(); 

        // 店コード・名称
        headRow5List.add(MoscardNipoConstants.CSV_DT_MISE_CD);
        headRow5List.add(MoscardNipoConstants.CSV_DT_MISE_NAME);
        // 売上、予算、達成率
        headRow5List.add(MoscardNipoConstants.CSV_DT_URIAGE);
        headRow5List.add(MoscardNipoConstants.CSV_DT_YOSAN);
        headRow5List.add(MoscardNipoConstants.CSV_DT_TASSEI);
        // 期日指定の場合天候、以外の場合営業日数設定
        headRow5List.add(TaishoKikan.DAY1.equals(dto.getTaishoKikanCd()) ?
            MoscardNipoConstants.CSV_DT_TENKOU : MoscardNipoConstants.CSV_DT_EIGYO_DAYS);
        // 前年売上、前年比(売上)
        headRow5List.add(MoscardNipoConstants.CSV_DT_URIAGE_ZEN);        
        headRow5List.add(MoscardNipoConstants.CSV_DT_ZEN_HI_URIAGE);
        // 客数、前年客数、前年比(客数)
        headRow5List.add(MoscardNipoConstants.CSV_DT_KYAKUSU);
        headRow5List.add(MoscardNipoConstants.CSV_DT_KYAKUSU_ZEN);
        headRow5List.add(MoscardNipoConstants.CSV_DT_ZEN_HI_KYAKUSU);
        // 客単価、前年客単価、前年比(客単価)
        headRow5List.add(MoscardNipoConstants.CSV_DT_KYAKU_TANKA);
        headRow5List.add(MoscardNipoConstants.CSV_DT_KYAKU_TANKA_ZEN);
        headRow5List.add(MoscardNipoConstants.CSV_DT_ZEN_HI_KYAKU_TANKA);
        // 期日指定の場合前年天候、以外の場合前年営業日数設定
        headRow5List.add(TaishoKikan.DAY1.equals(dto.getTaishoKikanCd()) ?
            MoscardNipoConstants.CSV_DT_ZEN_TENKOU : MoscardNipoConstants.CSV_DT_ZEN_EIGYO_DAYS);

        //発行枚数、前年発行枚数、前年比（売上）
        headRow5List.add(MoscardNipoConstants.CSV_DT_ISSUE_CNT);
        headRow5List.add(MoscardNipoConstants.CSV_DT_ZEN_ISSUE_CNT);
        headRow5List.add(MoscardNipoConstants.CSV_DT_ZEN_URIHI_ISSUE_CNT);
        //ﾁｬｰｼﾞ金額、ﾁｬｰｼﾞ金額（売上比）、前年ﾁｬｰｼﾞ金額、ﾁｬｰｼﾞ金額（前年比）
        headRow5List.add(MoscardNipoConstants.CSV_DT_CHARGEKIN);
        headRow5List.add(MoscardNipoConstants.CSV_DT_URIHI_CHARGEKIN);
        headRow5List.add(MoscardNipoConstants.CSV_DT_ZEN_CHARGEKIN);
        headRow5List.add(MoscardNipoConstants.CSV_DT_ZENHI_CHARGEKIN);
        //ﾁｬｰｼﾞ件数、ﾁｬｰｼﾞ件数（客数比）、前年ﾁｬｰｼﾞ件数、ﾁｬｰｼﾞ件数（前年比）
        headRow5List.add(MoscardNipoConstants.CSV_DT_CHARGEKEN);
        headRow5List.add(MoscardNipoConstants.CSV_DT_URIHI_CHARGEKEN);
        headRow5List.add(MoscardNipoConstants.CSV_DT_ZEN_CHARGEKEN);
        headRow5List.add(MoscardNipoConstants.CSV_DT_ZENHI_CHARGEKEN);
        //ﾁｬｰｼﾞ単価、前年ﾁｬｰｼﾞ単価、ﾁｬｰｼﾞ単価（前年比）
        headRow5List.add(MoscardNipoConstants.CSV_DT_CHARGE_TANKA);
        headRow5List.add(MoscardNipoConstants.CSV_DT_ZEN_CHARGE_TANKA);
        headRow5List.add(MoscardNipoConstants.CSV_DT_ZEN_TANKAHI);
        //決済金額、決済金額（売上比）、前年決済金額、決済金額（前年比）
        headRow5List.add(MoscardNipoConstants.CSV_DT_KESSAI_KIN);
        headRow5List.add(MoscardNipoConstants.CSV_DT_URIHI_KESSAI_KIN);
        headRow5List.add(MoscardNipoConstants.CSV_DT_ZEN_KESSAI_KIN);
        headRow5List.add(MoscardNipoConstants.CSV_DT_ZENHI_KESSAI_KIN);
        //決済件数、決済件数（客数比）、前年決済件数、決済件数（前年比）
        headRow5List.add(MoscardNipoConstants.CSV_DT_KESSAI_KEN);
        headRow5List.add(MoscardNipoConstants.CSV_DT_URIHI_KESSAI_KEN);
        headRow5List.add(MoscardNipoConstants.CSV_DT_ZEN_KESSAI_KEN);
        headRow5List.add(MoscardNipoConstants.CSV_DT_ZENHI_KESSAI_KEN);
        //決済単価、決済単価（単価比）、前年決済単価、決済単価（前年比）
        headRow5List.add(MoscardNipoConstants.CSV_DT_KESSAI_TANKA);
        headRow5List.add(MoscardNipoConstants.CSV_DT_TANKAHI_KESSAI_TANKA);
        headRow5List.add(MoscardNipoConstants.CSV_DT_ZEN_KESSAI_TANKA);
        headRow5List.add(MoscardNipoConstants.CSV_DT_ZENHI_KESSAI_TANKA);
//        //入金取消金額、入金取消件数
//        headRow5List.add(MoscardNipoConstants.CSV_DT_CHARGE_KIN_CANCEL);
//        headRow5List.add(MoscardNipoConstants.CSV_DT_CHARGE_KEN_CANCEL);
//       //利用取消金額、利用取消件数
//        headRow5List.add(MoscardNipoConstants.CSV_DT_USE_KIN_CANCEL);
//        headRow5List.add(MoscardNipoConstants.CSV_DT_USE_KEN_CANCEL);
//        //発行ボーナスバリュー、利用ボーナスバリュー
//        headRow5List.add(MoscardNipoConstants.CSV_DT_BONUS_V_ISSUE);
//        headRow5List.add(MoscardNipoConstants.CSV_DT_BONUS_V_USE);
//        //発行クーポンバリュー、利用クーポンバリュー
//        headRow5List.add(MoscardNipoConstants.CSV_DT_COUPON_V_ISSUE);
//        headRow5List.add(MoscardNipoConstants.CSV_DT_COUPON_V_USE);
//        //前受残高
//        headRow5List.add(MoscardNipoConstants.CSV_DT_ZANDAKA); 
        
        headerList.add(headRow1List);
        headerList.add(headRow2List);
        headerList.add(headRow3List);
        headerList.add(headRow4List);
        headerList.add(headRow5List);
 
        return headerList;
    }

    /**
     * 検索条件取得ロジックを取得する
     * @return SearchLogic 検索条件取得ロジック
     */
    public SearchLogic getSearchLogic() {
        return groupEigyoNipoSearchLogic;
    }

    /**
     *  検索条件取得ロジックを設定する
     * @param groupEigyoNipoSearchLogic 検索条件取得ロジック
     */
    public void setSearchLogic(SearchLogic groupEigyoNipoSearchLogic) {
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