package jp.co.isid.mos.bird.bizreport.groupeigyoniporef.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.code.ShukeiKbn;
import jp.co.isid.mos.bird.bizreport.common.code.TaishoTenpo;
import jp.co.isid.mos.bird.bizreport.common.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.code.NipoZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.common.common.BizReportConstants;
import jp.co.isid.mos.bird.bizreport.common.common.NipoRefConstants;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;
import jp.co.isid.mos.bird.bizreport.common.entity.CodCompany;
import jp.co.isid.mos.bird.bizreport.common.util.NipoRefUtil;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.common.EigyoNipoCommon;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.common.EigyoNipoConstants;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.entity.TrnSibuKyakusuNipoRelate;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.entity.TrnSibuUriageNipoRelate;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.logic.GroupEigyoNipoSearchLogic;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.logic.UriageInfoLogic;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.logic.ViewSibuInfoLogic;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.logic.ViewUriYosanLogic;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.logic.YosanInfoLogic;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.Calculator;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * 支部別営業日報CSVダウンロードロジック
 *
 * @author   xkhata
 * @modifier xjung  2006/10/03 総合営業日報タブ連携対応
 * @modifier xkinu 2013/01/24 海外売上集信対応 
 */
public class GroupEigyoNipoSibuCsvOutputLogicImpl implements CsvOutputLogic {
    /** 支部別営業日報CSVダウンロードロジックID */
    public static final String LOGIC_ID = "BBR001L05";

    /** 検索条件取得ロジック */ 
    private GroupEigyoNipoSearchLogic groupEigyoNipoSearchLogic;

    /** 売上情報取得ロジック */ 
    private UriageInfoLogic uriageInfoLogic;

    /** 予算情報取得ロジック */ 
    private YosanInfoLogic yosanInfoLogic;

    /** 表示支部取得ロジック */ 
    private ViewSibuInfoLogic viewSibuInfoLogic;

    /** 表示売上予算情報取得ロジック */ 
    private ViewUriYosanLogic viewUriYosanLogic;

    /**
     * CSVファイル名称を取得する
     * @param   csvOutputDto    CSV出力DTO
     * @return  String          CSVファイル名称
     */
    public String getFileName(CsvOutputDto csvOutputDto) {   
        return EigyoNipoConstants.CSV_FILE_NAME;
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
     * 
     * @modifier xkinu 2013/01/24 海外売上集信対応 
     */
    public List getOutputData(CsvOutputDto csvOutputDto) {
        // 数値タイプの文字列フォーマッタ(定数)
        NumericFormatter formatter = new NumericFormatter(true);
        formatter.setDefaultText("0");
        // 数値タイプの文字列フォーマッタ(定数)
        NumericFormatter formatterUriage = new NumericFormatter(true, BizReportConstants.FORMAT_JPY, true);
        // CSV出力データリスト、ヘッダー部情報取得
        List outputList = getHeaderData(csvOutputDto);

        // 営業日報条件部DTO取得
        NipoRefConditionParameterDto parameterDto = (NipoRefConditionParameterDto) csvOutputDto;
        // 予算・売上情報整理(CSV用)
        Map resultMap = this.searchSibuUriageInfo(parameterDto);        
        List uriageList = (List) resultMap.get(EigyoNipoConstants.MAP_URIAGE_LIST) ;
        List kyakuList = (List) resultMap.get(EigyoNipoConstants.MAP_KYAKUSU_LIST);
        List uriHeiList = (List) resultMap.get(EigyoNipoConstants.MAP_RST_URI_AVE);
        List kyakuHeiList = (List) resultMap.get(EigyoNipoConstants.MAP_RST_KYAKUSU_AVE);
        
        List uriHoseiList = (List) resultMap.get(EigyoNipoConstants.MAP_RST_URI_HOSE);
        List kyakuHoseiList = (List) resultMap.get(EigyoNipoConstants.MAP_RST_KYAKU_HOSE);
        List uriHeiHoseiList = (List) resultMap.get(EigyoNipoConstants.MAP_RST_URI_AVE_HOSE);
        List kyakuHeiHoseiList = (List) resultMap.get(EigyoNipoConstants.MAP_RST_KYAKUSU_AVE_HOSE);
        // 売上情報が0件の場合
        if (uriageList.size() != uriageList.size()) {
            throw new FtlSystemException(EigyoNipoConstants.EMPTY);
        }
        //対象の会社が海外の会社でかつ、
        //円換算済のデータでのダウンロード要求だった場合、円換算処理を行います。
        //(2013/01/25追加:海外売上集信対応)
    	CodCompany codCompany = parameterDto.getCodCompany();
    	formatterUriage.setFormatPattern(codCompany.getDispFormat());
        if(BizReportConstants.FOREING_ON.equals(codCompany.getForeignFlg())) {
            //．日報共通DTO【条件部情報】換算済判断フラグがtrue(換算済)の場合下記の処理を行います。
        	if (parameterDto.isKansan()) {
	        	//外貨値をCodCompany[管理会社情報].為替相場で円換算を行います。
	        	BigDecimal decRate = codCompany.getRate();
	            uriageList = EigyoNipoCommon.changeCurrencyUriage(uriageList, decRate, false);
	            kyakuList = EigyoNipoCommon.changeCurrencyKyakusu(uriageList, kyakuList, decRate, false);
	            uriHeiList = EigyoNipoCommon.changeCurrencyUriage(uriHeiList, decRate, parameterDto.isAveDispFlg());
	            kyakuHeiList = EigyoNipoCommon.changeCurrencyKyakusu(uriHeiList, kyakuHeiList, decRate, parameterDto.isAveDispFlg());
	            
	            uriHoseiList = EigyoNipoCommon.changeCurrencyUriage(uriHoseiList, decRate, false);
	            kyakuHoseiList = EigyoNipoCommon.changeCurrencyKyakusu(uriHoseiList, kyakuHoseiList, decRate, false);
	            uriHeiHoseiList = EigyoNipoCommon.changeCurrencyUriage(uriHeiHoseiList, decRate, parameterDto.isAveDispFlg());
	            kyakuHeiHoseiList = EigyoNipoCommon.changeCurrencyKyakusu(uriHeiHoseiList, kyakuHeiHoseiList, decRate, parameterDto.isAveDispFlg());
	            formatterUriage.setFormatPattern(BizReportConstants.FORMAT_JPY);
        	}
        }
    	//[表示形式]指定の場合、[表示形式]から小数桁を確定します。
        String pattern = formatterUriage.getFormatPattern();
    	int digitsNum = 0;
    	if(pattern.indexOf(".")>=0) {
    		digitsNum = (pattern.length() - pattern.indexOf(".") - 1);
    	}

        // 前年データ種別
        String zenDataShu = TenpoShubetu.CODE_ZENNEN.equals(parameterDto.getTenpoShubetuCd()) ?
            parameterDto.getZenDataZennenCd() : parameterDto.getZenDataZennenOthCd();

        // データ部情報取得 
        for (int i = 0; i < uriageList.size(); i++) {
            List lineList = new ArrayList();
            TrnSibuUriageNipoRelate uriage = (TrnSibuUriageNipoRelate) uriageList.get(i);
            TrnSibuKyakusuNipoRelate kyakusu = (TrnSibuKyakusuNipoRelate) kyakuList.get(i);
            TrnSibuUriageNipoRelate uriHei = (TrnSibuUriageNipoRelate) uriHeiList.get(i);
            TrnSibuKyakusuNipoRelate kyakuHei = (TrnSibuKyakusuNipoRelate) kyakuHeiList.get(i);

            TrnSibuUriageNipoRelate uriHose = new TrnSibuUriageNipoRelate();
            TrnSibuKyakusuNipoRelate kyakuHose = new TrnSibuKyakusuNipoRelate();
            TrnSibuUriageNipoRelate uriHoseHei = new TrnSibuUriageNipoRelate();
            TrnSibuKyakusuNipoRelate kyakuHoseHei = new TrnSibuKyakusuNipoRelate();
 
            // 前年データ種別が前年同月営業日補正の時            
            if (parameterDto.isSelectHosei())  {
                uriHose = (TrnSibuUriageNipoRelate)uriHoseiList.get(i);
                kyakuHose = (TrnSibuKyakusuNipoRelate)kyakuHoseiList.get(i);
                uriHoseHei = (TrnSibuUriageNipoRelate)uriHeiHoseiList.get(i);
                kyakuHoseHei = (TrnSibuKyakusuNipoRelate)kyakuHeiHoseiList.get(i);
            }
            // 事業本部コード、事業本部名称、エリアコード、エリア名称、支部コード、支部名称
            if (NipoRefConstants.CSS_TR_CLASS.equals(uriage.getRClass())) {
                // 指定無し
                if (ShukeiKbn.OUT_RC.equals(parameterDto.getShukeiKbnCd())){
                    lineList.add(EigyoNipoCommon.setEmpty(uriage.getJigyoCd()));
                    lineList.add(EigyoNipoCommon.setEmpty(uriage.getJigyoName()));
                    lineList.add(EigyoNipoCommon.setEmpty(uriage.getSlareaCd()));
                    lineList.add(EigyoNipoCommon.setEmpty(uriage.getSlareaName()));
                    lineList.add(EigyoNipoCommon.setEmpty(uriage.getSibuCd()));
                }
                lineList.add(EigyoNipoCommon.setEmpty(uriage.getSibuName()));
            // エリア計の場合
            } else if (NipoRefConstants.CSS_TR_CLASS_SLAREA.equals(uriage.getRClass())) {
                lineList.add(EigyoNipoCommon.setEmpty(uriage.getJigyoCd()));
                lineList.add(EigyoNipoCommon.setEmpty(uriage.getJigyoName()));
                lineList.add(EigyoNipoCommon.setEmpty(uriage.getSibuCd()));
                lineList.add(EigyoNipoCommon.setEmpty(uriage.getSibuName()));
                lineList.add(EigyoNipoConstants.EMPTY);
                lineList.add(EigyoNipoConstants.EMPTY);
            // 事業本部計又は本部計の場合
            } else if (NipoRefConstants.CSS_TR_CLASS_JIGYOU.equals(uriage.getRClass())
                || NipoRefConstants.CSS_TR_CLASS_HONBU.equals(uriage.getRClass())) {
                lineList.add(EigyoNipoCommon.setEmpty(uriage.getSibuCd()));
                lineList.add(EigyoNipoCommon.setEmpty(uriage.getSibuName()));
                lineList.add(EigyoNipoConstants.EMPTY);
                lineList.add(EigyoNipoConstants.EMPTY);
                lineList.add(EigyoNipoConstants.EMPTY);
                lineList.add(EigyoNipoConstants.EMPTY);
            // 統括計又は総合計の場合
            } else if (NipoRefConstants.CSS_TR_CLASS_SEGMENT.equals(uriage.getRClass())
                    || NipoRefConstants.CSS_TR_CLASS_TOTAL.equals(uriage.getRClass())) {
                // 集計区分が直営業店を含まないの時
                if (ShukeiKbn.OUT_RC.equals(parameterDto.getShukeiKbnCd())) {
                    lineList.add(EigyoNipoConstants.EMPTY);
                }                
                lineList.add(EigyoNipoCommon.setEmpty(uriage.getSibuName()));
                // 集計区分が直営業店を含まないの時
                if (ShukeiKbn.OUT_RC.equals(parameterDto.getShukeiKbnCd())) {
                    lineList.add(EigyoNipoConstants.EMPTY);
                    lineList.add(EigyoNipoConstants.EMPTY);
                    lineList.add(EigyoNipoConstants.EMPTY);
                    lineList.add(EigyoNipoConstants.EMPTY);
                }
            }
            // 当年店数、前年店数設定
            lineList.add(formatter.format(uriage.getTenpoCount()));
            lineList.add(formatter.format(uriage.getZenTenpoCount()));
            // 前年同月営業日補正の場合、前年比対象・店数、前年店数設定
            if (parameterDto.isSelectHosei())  {
                lineList.add(formatter.format(uriage.getHoseiTenpoCnt()));
                lineList.add(formatter.format(uriage.getHoseiZenTenpoCnt()));
            }
            // 売上、予算、達成率
            lineList.add(formatterUriage.format(uriage.getUriage()));
            lineList.add(formatter.format(uriage.getYosan()));
            lineList.add(formatter.format(uriage.getTasseiYosan()));
            // 前年同月営業日補正の場合、前年比対象・売上、前年実績、前年比(売上)         
            if (parameterDto.isSelectHosei()) {
                lineList.add(formatterUriage.format(uriHose.getUriage()));
                lineList.add(formatterUriage.format(uriHose.getZenUriage()));
                lineList.add(formatter.format(uriHose.getZenHiSa()));
            // 前年同月営業日補正以外の場合、前年実績、前年比(売上) 
            } else {
                lineList.add(formatterUriage.format(uriage.getZenUriage()));
                lineList.add(formatter.format(uriage.getZenHiSa()));
            }
            // 前年データ種別が前年同月同曜以外の時
            if (!NipoZennenDataShubetu.CODE_DOYO.equals(zenDataShu)) {
                // 売上平均、予算平均、予算差
                lineList.add(formatterUriage.format(uriHei.getUriage()));
                lineList.add(formatter.format(uriHei.getYosan()));
                lineList.add(formatterUriage.format(uriHei.getTasseiYosan()));
                // 前年同月営業日補正の場合、前年比対象・売上平均、前年売上平均、前年差(売上)設定
                if (parameterDto.isSelectHosei()) {
                    lineList.add(formatterUriage.format(uriHoseHei.getUriage()));
                    lineList.add(formatterUriage.format(uriHoseHei.getZenUriage()));
                    lineList.add(formatterUriage.format(uriHoseHei.getZenHiSa()));
                // 前年同月営業日補正以外の場合、売上平均、前年差(売上)設定
                } else { 
                    lineList.add(formatterUriage.format(uriHei.getZenUriage()));
                    lineList.add(formatterUriage.format(uriHei.getZenHiSa()));                    
                }
            }
            // 客数
            lineList.add(formatter.format(kyakusu.getKyakusu()));
            // 前年同月営業日補正の場合           
            if (parameterDto.isSelectHosei()) {
                // 前年比対象・客数、前年客数、前年比(客数)
                lineList.add(formatter.format(kyakuHose.getKyakusu()));
                lineList.add(formatter.format(kyakuHose.getZenKyakusu()));
                lineList.add(formatter.format(kyakuHose.getZenHi()));
                // 客単価、前年比対象・客単価、前年客単価、前年比(客単価)
                lineList.add(formatterUriage.format(kyakusu.getKyakuTanka()));
                lineList.add(formatterUriage.format(kyakuHose.getKyakuTanka()));
                lineList.add(formatter.format(
                    Calculator.divide(uriHose.getZenUriage(), kyakuHose.getZenKyakusu(), digitsNum)));
                lineList.add(formatter.format(kyakuHose.getZenHiTanka()));
            // 前年同月営業日補正以外の場合
            } else {
                // 前年客数、前年比(客数)、客単価、前年客単価、前年比(客単価)
                lineList.add(formatter.format(kyakusu.getZenKyakusu()));
                lineList.add(formatter.format(kyakusu.getZenHi()));
                lineList.add(formatterUriage.format(kyakusu.getKyakuTanka()));
                lineList.add(formatterUriage.format(
                    Calculator.divide(uriage.getZenUriage(), kyakusu.getZenKyakusu(), digitsNum)));
                lineList.add(formatter.format(kyakusu.getZenHiTanka()));
            }
            // 前年データ種別が前年同月同曜以外の時   
            if (!NipoZennenDataShubetu.CODE_DOYO.equals(zenDataShu)) {
                // 客数平均
                lineList.add(formatter.format(kyakuHei.getKyakusu())) ;
                // 前年同月営業日補正の場合、前年比対象・客数平均、前年客数平均、前年差(客数)設定
                if (parameterDto.isSelectHosei()) {
                    lineList.add(formatter.format(kyakuHoseHei.getKyakusu()));
                    lineList.add(formatter.format(kyakuHoseHei.getZenKyakusu()));
                    lineList.add(formatter.format(kyakuHoseHei.getZenHi()));
                // 前年同月営業日補正以外の場合、客数平均、前年差(客数)設定
                } else {
                    lineList.add(formatter.format(kyakuHei.getZenKyakusu()));
                    lineList.add(formatter.format(kyakuHei.getZenHi()));
                }
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
    private List getHeaderData(CsvOutputDto csvOutputDto) {
        // ヘッダー部情報リスト
        List headerList = new ArrayList();

        // 営業日報条件部DTO取得
        NipoRefConditionParameterDto parameterDto = (NipoRefConditionParameterDto) csvOutputDto;

        // 前年データ種別
        String zenDataShu = TenpoShubetu.CODE_ZENNEN.equals(parameterDto.getTenpoShubetuCd()) ?
            parameterDto.getZenDataZennenCd() : parameterDto.getZenDataZennenOthCd();

        // １行目:会社、対象店舗
        List head1stList = new ArrayList();
        head1stList.add(EigyoNipoConstants.CSV_HD_COMPANY);
        head1stList.add(parameterDto.getCompanyName());
        head1stList.add(EigyoNipoConstants.EMPTY);
        head1stList.add(EigyoNipoConstants.CSV_HD_TAISHO_TENPO);
        head1stList.add(TaishoTenpo.getName(parameterDto.getTaishoTenpoCd()));

        // ２行目:店舗種別、対象期間
        List head2ndList = new ArrayList();
        head2ndList.add(EigyoNipoConstants.CSV_HD_TENPO_SHU);
        head2ndList.add(TenpoShubetu.getName(parameterDto.getTenpoShubetuCd()));
        head2ndList.add(EigyoNipoConstants.EMPTY);
        head2ndList.add(EigyoNipoConstants.CSV_HD_TAISHO_KIKAN);
        head2ndList.add(NipoRefUtil.getCsvTaishoKikan(parameterDto));
 
        // ３行目:集計区分、前年データ種別
        List head3rdList = new ArrayList();
        head3rdList.add(EigyoNipoConstants.CSV_HD_SHUKEI_KBN);
        head3rdList.add(ShukeiKbn.getName(parameterDto.getShukeiKbnCd()));
        head3rdList.add(EigyoNipoConstants.EMPTY);
        head3rdList.add(EigyoNipoConstants.CSV_HD_ZEN_DATA_SHU);
        head3rdList.add(NipoZennenDataShubetu.getName
            (zenDataShu, parameterDto.getBirdUserInfo().getMstUser().getUserTypeCd()));

        //海外の会社の場合は表示通貨を設定します。
        if(BizReportConstants.FOREING_ON.equals(parameterDto.getCodCompany().getForeignFlg())) {
    		head3rdList.add("");
            //．日報共通DTO【条件部情報】換算済判断フラグがtrue(換算済)の場合下記の処理を行います。
       		head3rdList.add("表示通貨："+(parameterDto.isKansan()?"日本円":parameterDto.getCodCompany().getCurrencyName()));
        }
        // ４行目
        List head4thList = new ArrayList();
        
        // ５行目:データ部ヘッダ作成
        List head5thList = new ArrayList();
        // 集計区分が直営店を含まないの場合、事業本部、エリア、支部コード・名称設定
        if (ShukeiKbn.OUT_RC.equals(parameterDto.getShukeiKbnCd())) {
            head5thList.add(EigyoNipoConstants.CSV_DT_JIGYOU_CD);
            head5thList.add(EigyoNipoConstants.CSV_DT_JIGYOU_NAME);
            head5thList.add(EigyoNipoConstants.CSV_DT_SLAREA_CD);
            head5thList.add(EigyoNipoConstants.CSV_DT_SLAREA_NAME);
            head5thList.add(EigyoNipoConstants.CSV_DT_SIBU_CD);
            head5thList.add(EigyoNipoConstants.CSV_DT_SIBU_NAME);
        // 集計区分が直営店を含むの場合、集計区分設定            
        } else {
            head5thList.add(EigyoNipoConstants.CSV_DT_SHUKEI_KBN);
        }
        // 当年店数、前年店数設定
        head5thList.add(EigyoNipoConstants.CSV_DT_TOUNEN_TENPO_COUNT);
        head5thList.add(EigyoNipoConstants.CSV_DT_ZENNEN_TENPO_COUNT);
        // 前年同月営業日補正の場合、前年比対象・店数、前年店数設定
        if (parameterDto.isSelectHosei()){               
            head5thList.add(EigyoNipoConstants.CSV_DT_HOSEI_TENPO_COUNT);
            head5thList.add(EigyoNipoConstants.CSV_DT_HOSEI_ZEN_TENPO_COUNT);
        }
        // 売上、予算、達成率
        head5thList.add(EigyoNipoConstants.CSV_DT_URIAGE);
        head5thList.add(EigyoNipoConstants.CSV_DT_YOSAN);
        head5thList.add(EigyoNipoConstants.CSV_DT_TASSEI);
        // 前年データ種別が前年同曜日の場合、前年売上、前年比(売上設定)
        if (NipoZennenDataShubetu.CODE_DOYO.equals(zenDataShu)) {
            head5thList.add(EigyoNipoConstants.CSV_DT_URIAGE_ZEN);
            head5thList.add(EigyoNipoConstants.CSV_DT_ZEN_HI_URIAGE);
        // 前年データ種別が前年同曜日以外の場合
        } else {
            // 前年同月営業日補正の場合、前年比対象・売上、前年売上設定
            if (parameterDto.isSelectHosei()){               
                head5thList.add(EigyoNipoConstants.CSV_DT_HOSEI_URIAGE);
                head5thList.add(EigyoNipoConstants.CSV_DT_HOSEI_URIAGE_ZEN);
            // 前年同月営業日補正以外の場合、前年売上設定
            } else {
                head5thList.add(EigyoNipoConstants.CSV_DT_URIAGE_ZEN);
            }
            // 前年比(売上)、売上平均、予算平均、予算差
            head5thList.add(EigyoNipoConstants.CSV_DT_ZEN_HI_URIAGE);            
            head5thList.add(EigyoNipoConstants.CSV_DT_URIAGE_AVE);
            head5thList.add(EigyoNipoConstants.CSV_DT_YOSAN_AVE);
            head5thList.add(EigyoNipoConstants.CSV_DT_YOSAN_DIFF);    
            // 前年同月営業日補正の場合、前年比対象・売上平均、前年売上平均設定
            if (parameterDto.isSelectHosei()){               
                head5thList.add(EigyoNipoConstants.CSV_DT_HOSEI_URIAGE_AVE);
                head5thList.add(EigyoNipoConstants.CSV_DT_HOSEI_ZEN_URIAGE_AVE);
            // 前年同月営業日補正の場合、前年売上平均設定                
            } else {
                head5thList.add(EigyoNipoConstants.CSV_DT_ZEN_URIAGE_AVE);
            }
            // 前年差(売上)
            head5thList.add(EigyoNipoConstants.CSV_DT_ZEN_DIFF_URIAGE);
        }
        // 客数
        head5thList.add(EigyoNipoConstants.CSV_DT_KYAKUSU);        
        // 前年同月営業日補正の場合、前年比対象・客数、前年客数設定 
        if (parameterDto.isSelectHosei()){               
            head5thList.add(EigyoNipoConstants.CSV_DT_HOSEI_KYAKUSU);
            head5thList.add(EigyoNipoConstants.CSV_DT_HOSEI_KYAKUSU_ZEN);
        // 前年同月営業日補正以外の場合、前年客数設定
        } else {
            head5thList.add(EigyoNipoConstants.CSV_DT_KYAKUSU_ZEN);
        }
        // 前年比(客数)、客単価
        head5thList.add(EigyoNipoConstants.CSV_DT_ZEN_HI_KYAKUSU);        
        head5thList.add(EigyoNipoConstants.CSV_DT_KYAKU_TANKA);
        // 前年同月営業日補正の場合、前年比対象・客単価、前年客単価設定
        if (parameterDto.isSelectHosei()){
            head5thList.add(EigyoNipoConstants.CSV_DT_HOSEI_KYAKU_TANKA);
            head5thList.add(EigyoNipoConstants.CSV_DT_HOSEI_KYAKU_TANKA_ZEN);
        // 前年同月営業日補正以外の場合、前年客単価設定
        } else {
            head5thList.add(EigyoNipoConstants.CSV_DT_KYAKU_TANKA_ZEN);
        }
        // 前年比(客単価)
        head5thList.add(EigyoNipoConstants.CSV_DT_ZEN_HI_KYAKU_TANKA);
        // 前年データ種別が前年同曜日以外の場合        
        if (!NipoZennenDataShubetu.CODE_DOYO.equals(zenDataShu)) {
            // 客数平均
            head5thList.add(EigyoNipoConstants.CSV_DT_KYAKUSU_AVE);    
            // 前年同月営業日補正の場合、前年比対象・客数平均、前年客数平均
            if (parameterDto.isSelectHosei()){               
                head5thList.add(EigyoNipoConstants.CSV_DT_HOSEI_KYAKUSU_AVE);
                head5thList.add(EigyoNipoConstants.CSV_DT_HOSE_ZEN_KYAKUSU_AVE);
            // 前年同月営業日補正以外の場合、前年客数平均
            } else {
                head5thList.add(EigyoNipoConstants.CSV_DT_ZEN_KYAKUSU_AVE);
            }
            // 前年差(客数)
            head5thList.add(EigyoNipoConstants.CSV_DT_ZEN_DIFF_KYAKUSU);       
        }        
        headerList.add(head1stList);
        headerList.add(head2ndList);
        headerList.add(head3rdList);
        headerList.add(head4thList);
        headerList.add(head5thList);
        
        return headerList;
    }

    /**
     * CSV出力用売上・予算情報を取得する
     * @param parameterDto 条件部情報Dto
     * @return Map CSV出力用売上・予算情報
     */
    private Map searchSibuUriageInfo(NipoRefConditionParameterDto parameterDto) {
        //検索条件の取得
        Map paramMap = getGroupEigyoNipoSearchLogic().getSearchData(parameterDto);
 
        // 支部マスタの取得
        List sibuList = getViewSibuInfoLogic().execute(paramMap);
        
        // 売上情報の取得
        List uriageList = getUriageInfoLogic().execute(paramMap);
        
        // 予算情報の取得
        List yosanList = getYosanInfoLogic().execute(paramMap);
        
        Map uriYosanMap = new HashMap();
        uriYosanMap.put(EigyoNipoConstants.MAP_SIBU_LIST, sibuList);
        uriYosanMap.put(EigyoNipoConstants.MAP_URIAGE_LIST, uriageList);
        uriYosanMap.put(EigyoNipoConstants.MAP_YOSAN_LIST, yosanList);
        uriYosanMap.put(EigyoNipoConstants.MAP_SHUKEI_KBN, parameterDto.getShukeiKbnCd());
        uriYosanMap.put(EigyoNipoConstants.MAP_PARAMS, paramMap);
                
        // 前年データ種別が前年同月営業日補正の場合
        List hoseiUriList = new ArrayList();
        if (parameterDto.isSelectHosei()) {
            paramMap.put(EigyoNipoConstants.MAP_DATASHU, NipoZennenDataShubetu.CODE_DOGETU);
// 2008/03/21 update start CSVの前年比（売上）が画面と一致していない問題の対応
//  　売上と前年比対象売上が逆になっているので、上で取得した物を前年比対象売上とし
//　　前年同月売上を取得する
            //hoseiUriList = getUriageInfoLogic().execute(paramMap);
            hoseiUriList = uriageList;
            uriageList = getUriageInfoLogic().execute(paramMap);
            uriYosanMap.put(EigyoNipoConstants.MAP_URIAGE_LIST, uriageList);
// 2008/03/21 update end            
            uriYosanMap.put(EigyoNipoConstants.MAP_HOSE_URI_LIST, hoseiUriList);
            paramMap.put(EigyoNipoConstants.MAP_DATASHU, NipoZennenDataShubetu.CODE_HOSEI);
        }          
        uriYosanMap.put(EigyoNipoConstants.MAP_SIBU_LIST, sibuList);
        uriYosanMap.put(EigyoNipoConstants.MAP_URIAGE_LIST, uriageList);
        uriYosanMap.put(EigyoNipoConstants.MAP_YOSAN_LIST, yosanList);
        uriYosanMap.put(EigyoNipoConstants.MAP_SHUKEI_KBN, parameterDto.getShukeiKbnCd());
        uriYosanMap.put(EigyoNipoConstants.MAP_PARAMS, paramMap);
 
        return getViewUriYosanLogic().execute(uriYosanMap);
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
     * 売上情報取得ロジックを取得する
     * @return UriageInfoLogic 売上情報取得ロジック
     */
    public UriageInfoLogic getUriageInfoLogic() {
        return this.uriageInfoLogic;
    }

    /**
     *  売上情報取得ロジックを設定する
     * @param uriageInfoLogic 売上情報取得ロジック
     */
    public void setUriageInfoLogic(UriageInfoLogic uriageInfoLogic) {
        this.uriageInfoLogic = uriageInfoLogic;
    }

    /**
     * 予算情報取得ロジックを取得する
     * @return YosanInfoLogic 予算情報取得ロジック
     */
    public YosanInfoLogic getYosanInfoLogic() {
        return this.yosanInfoLogic;
    }

    /**
     *  予算情報取得ロジックを設定する
     * @param yosanInfoLogic 予算情報取得ロジック
     */
    public void setYosanInfoLogic(YosanInfoLogic yosanInfoLogic) {
        this.yosanInfoLogic = yosanInfoLogic;
    }

    /**
     * 表示支部取得ロジックを取得する
     * @return ViewSibuInfoLogic 表示支部取得ロジック
     */
    public ViewSibuInfoLogic getViewSibuInfoLogic() {
        return this.viewSibuInfoLogic;
    }

    /**
     *  表示支部取得ロジックを設定する
     * @param viewSibuInfoLogic 表示支部取得ロジック
     */
    public void setViewSibuInfoLogic(ViewSibuInfoLogic viewSibuInfoLogic) {
        this.viewSibuInfoLogic = viewSibuInfoLogic;
    }   

    /**
     * 表示売上予算情報取得ロジックを取得する
     * @return ViewUriYosanLogic 表示売上予算情報取得ロジック
     */
    public ViewUriYosanLogic getViewUriYosanLogic() {
        return this.viewUriYosanLogic;
    }

    /**
     *  表示売上予算情報取得ロジックを設定する
     * @param viewUriYosanLogic 表示売上予算情報取得ロジック
     */
    public void setViewUriYosanLogic( ViewUriYosanLogic viewUriYosanLogic) {
        this.viewUriYosanLogic = viewUriYosanLogic;
    }    
}