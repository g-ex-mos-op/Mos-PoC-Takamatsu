package jp.co.isid.mos.bird.bizreport.netorderniporef.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.code.NipoZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.common.code.ShukeiKbn;
import jp.co.isid.mos.bird.bizreport.common.code.TaishoTenpo;
import jp.co.isid.mos.bird.bizreport.common.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.common.BizReportConstants;
import jp.co.isid.mos.bird.bizreport.common.common.NipoRefConstants;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;
import jp.co.isid.mos.bird.bizreport.common.entity.CodCompany;
import jp.co.isid.mos.bird.bizreport.common.util.NipoRefUtil;
import jp.co.isid.mos.bird.bizreport.netorderniporef.common.NetorderNipoCommon;
import jp.co.isid.mos.bird.bizreport.netorderniporef.common.NetorderNipoConstants;
import jp.co.isid.mos.bird.bizreport.netorderniporef.entity.TrnSibuKyakusuNipoRelate;
import jp.co.isid.mos.bird.bizreport.netorderniporef.entity.TrnSibuUriageNipoRelate;
import jp.co.isid.mos.bird.bizreport.netorderniporef.logic.NetorderNipoSearchLogic;
import jp.co.isid.mos.bird.bizreport.netorderniporef.logic.UriageInfoLogic;
import jp.co.isid.mos.bird.bizreport.netorderniporef.logic.ViewSibuInfoLogic;
import jp.co.isid.mos.bird.bizreport.netorderniporef.logic.ViewUriYosanLogic;
import jp.co.isid.mos.bird.bizreport.netorderniporef.logic.YosanInfoLogic;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.Calculator;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * 支部別営業日報CSVダウンロードロジック
 */
public class NetorderNipoSibuCsvOutputLogicImpl implements CsvOutputLogic {
    /** 支部別営業日報CSVダウンロードロジックID */
    public static final String LOGIC_ID = "BBR017L05";

    /** 検索条件取得ロジック */ 
    private NetorderNipoSearchLogic netorderNipoSearchLogic;

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
        return NetorderNipoConstants.CSV_FILE_NAME;
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
        // 数値タイプの文字列フォーマッタ(定数)
        NumericFormatter formatter = new NumericFormatter(true);
        formatter.setDefaultText("0");
        // 数値タイプの文字列フォーマッタ(定数)
        NumericFormatter formatterUriage = new NumericFormatter(true, BizReportConstants.FORMAT_JPY, true);
        // CSV出力データリスト、ヘッダー部情報取得
        List outputList = getHeaderData(csvOutputDto);

        // ネット注文日報条件部DTO取得
        NipoRefConditionParameterDto parameterDto = (NipoRefConditionParameterDto) csvOutputDto;
        // 予算・売上情報整理(CSV用)
        Map resultMap = this.searchSibuUriageInfo(parameterDto);        
        List uriageList = (List) resultMap.get(NetorderNipoConstants.MAP_URIAGE_LIST) ;
        List kyakuList = (List) resultMap.get(NetorderNipoConstants.MAP_KYAKUSU_LIST);
        List uriHeiList = (List) resultMap.get(NetorderNipoConstants.MAP_RST_URI_AVE);
        List kyakuHeiList = (List) resultMap.get(NetorderNipoConstants.MAP_RST_KYAKUSU_AVE);
        
        List uriHoseiList = (List) resultMap.get(NetorderNipoConstants.MAP_RST_URI_HOSE);
        List kyakuHoseiList = (List) resultMap.get(NetorderNipoConstants.MAP_RST_KYAKU_HOSE);
        List uriHeiHoseiList = (List) resultMap.get(NetorderNipoConstants.MAP_RST_URI_AVE_HOSE);
        List kyakuHeiHoseiList = (List) resultMap.get(NetorderNipoConstants.MAP_RST_KYAKUSU_AVE_HOSE);
        // 売上情報が0件の場合
        if (uriageList.size() != uriageList.size()) {
            throw new FtlSystemException(NetorderNipoConstants.EMPTY);
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
	            uriageList = NetorderNipoCommon.changeCurrencyUriage(uriageList, decRate, false);
	            kyakuList = NetorderNipoCommon.changeCurrencyKyakusu(uriageList, kyakuList, decRate, false);
	            uriHeiList = NetorderNipoCommon.changeCurrencyUriage(uriHeiList, decRate, parameterDto.isAveDispFlg());
	            kyakuHeiList = NetorderNipoCommon.changeCurrencyKyakusu(uriHeiList, kyakuHeiList, decRate, parameterDto.isAveDispFlg());
	            
	            uriHoseiList = NetorderNipoCommon.changeCurrencyUriage(uriHoseiList, decRate, false);
	            kyakuHoseiList = NetorderNipoCommon.changeCurrencyKyakusu(uriHoseiList, kyakuHoseiList, decRate, false);
	            uriHeiHoseiList = NetorderNipoCommon.changeCurrencyUriage(uriHeiHoseiList, decRate, parameterDto.isAveDispFlg());
	            kyakuHeiHoseiList = NetorderNipoCommon.changeCurrencyKyakusu(uriHeiHoseiList, kyakuHeiHoseiList, decRate, parameterDto.isAveDispFlg());
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
                    lineList.add(NetorderNipoCommon.setEmpty(uriage.getJigyoCd()));
                    lineList.add(NetorderNipoCommon.setEmpty(uriage.getJigyoName()));
                    lineList.add(NetorderNipoCommon.setEmpty(uriage.getSlareaCd()));
                    lineList.add(NetorderNipoCommon.setEmpty(uriage.getSlareaName()));
                    lineList.add(NetorderNipoCommon.setEmpty(uriage.getSibuCd()));
                }
                lineList.add(NetorderNipoCommon.setEmpty(uriage.getSibuName()));
            // エリア計の場合
            } else if (NipoRefConstants.CSS_TR_CLASS_SLAREA.equals(uriage.getRClass())) {
                lineList.add(NetorderNipoCommon.setEmpty(uriage.getJigyoCd()));
                lineList.add(NetorderNipoCommon.setEmpty(uriage.getJigyoName()));
                lineList.add(NetorderNipoCommon.setEmpty(uriage.getSibuCd()));
                lineList.add(NetorderNipoCommon.setEmpty(uriage.getSibuName()));
                lineList.add(NetorderNipoConstants.EMPTY);
                lineList.add(NetorderNipoConstants.EMPTY);
            // 事業本部計又は本部計の場合
            } else if (NipoRefConstants.CSS_TR_CLASS_JIGYOU.equals(uriage.getRClass())
                || NipoRefConstants.CSS_TR_CLASS_HONBU.equals(uriage.getRClass())) {
                lineList.add(NetorderNipoCommon.setEmpty(uriage.getSibuCd()));
                lineList.add(NetorderNipoCommon.setEmpty(uriage.getSibuName()));
                lineList.add(NetorderNipoConstants.EMPTY);
                lineList.add(NetorderNipoConstants.EMPTY);
                lineList.add(NetorderNipoConstants.EMPTY);
                lineList.add(NetorderNipoConstants.EMPTY);
            // 統括計又は総合計の場合
            } else if (NipoRefConstants.CSS_TR_CLASS_SEGMENT.equals(uriage.getRClass())
                    || NipoRefConstants.CSS_TR_CLASS_TOTAL.equals(uriage.getRClass())) {
                // 集計区分が直営業店を含まないの時
                if (ShukeiKbn.OUT_RC.equals(parameterDto.getShukeiKbnCd())) {
                    lineList.add(NetorderNipoConstants.EMPTY);
                }                
                lineList.add(NetorderNipoCommon.setEmpty(uriage.getSibuName()));
                // 集計区分が直営業店を含まないの時
                if (ShukeiKbn.OUT_RC.equals(parameterDto.getShukeiKbnCd())) {
                    lineList.add(NetorderNipoConstants.EMPTY);
                    lineList.add(NetorderNipoConstants.EMPTY);
                    lineList.add(NetorderNipoConstants.EMPTY);
                    lineList.add(NetorderNipoConstants.EMPTY);
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
 
//          ネット注文
            //ネット注文実績店舗数、ネット注文売上
            lineList.add(formatter.format(uriage.getMiseKbnNsum()));
            lineList.add(formatter.format(uriage.getUriageNsum()));
            //前年同月営業日補正の場合、前年比対象・ネット注文売上、前年ネット注文実績設定
            if (parameterDto.isSelectHosei()){
            	//前年比対象ネット注文売上、前年比対象前年ネット注文実績、前年比(ネット注文売上)
            	lineList.add(formatter.format(uriHose.getUriageNsum()));
            	lineList.add(formatter.format(uriHose.getUriageNsumZen()));
            	lineList.add(formatter.format(uriHose.getUriageNsumZenHi()));
            }else{
            	//前年ネット注文実績、前年比(ネット注文売上)
            	lineList.add(formatter.format(uriage.getUriageNsumZen()));
            	lineList.add(formatter.format(uriage.getUriageNsumZenHi()));
            }
            //構成比(売上)
            lineList.add(formatter.format(uriage.getUriageNsumKoseiHi()));
            //前年データ種別が前年同曜日以外の場合 、ネット注文売上平均
            if (!NipoZennenDataShubetu.CODE_DOYO.equals(zenDataShu)){
            	lineList.add(formatter.format(uriHei.getUriageNsum()));
            }
            //ネット注文件数
            lineList.add(formatter.format(kyakusu.getKyakusuNsum()));
            //前年同月営業日補正の場合、前年比対象・ネット注文件数、前年ネット注文件数設定
            if (parameterDto.isSelectHosei()){
            	//前年比対象ネット注文件数、前年比対象前年ネット注文件数、前年比(ネット注文件数)
            	lineList.add(formatter.format(kyakuHose.getKyakusuNsum()));
            	lineList.add(formatter.format(kyakuHose.getKyakusuNsumZen()));
            	lineList.add(formatter.format(kyakuHose.getKyakusuNsumZenHi()));
            }else{
            	//前年ネット注文実績、前年比(ネット注文件数)
            	lineList.add(formatter.format(kyakusu.getKyakusuNsumZen()));
            	lineList.add(formatter.format(kyakusu.getKyakusuNsumZenHi()));
            }
            //構成比(件数)
            lineList.add(formatter.format(kyakusu.getKyakusuNsumKoseiHi()));
            //前年データ種別が前年同曜日以外の場合 、ネット注文件数平均
            if (!NipoZennenDataShubetu.CODE_DOYO.equals(zenDataShu)){
            	lineList.add(formatter.format(kyakuHei.getKyakusuNsum()));
            }
            //客単価（ネット注文）
            lineList.add(formatter.format(kyakusu.getKyakuTankaNsum()));
            //前年同月営業日補正の場合、前年比対象・対象客単価（ネット注文）、対象前年客単価（ネット注文）設定
            if (parameterDto.isSelectHosei()){
            	//前年比対象客単価（ネット注文）、前年比対象前年客単価（ネット注文）、前年比(客単価)
            	lineList.add(formatter.format(kyakuHose.getKyakuTankaNsum()));
            	lineList.add(formatter.format(kyakuHose.getKyakuTankaNsumZen()));
            	lineList.add(formatter.format(kyakuHose.getKyakuTankaNsumZenHi()));
            }else{
            	//前年客単価（ネット注文）、前年比(客単価)
            	lineList.add(formatter.format(kyakusu.getKyakuTankaNsumZen()));
            	lineList.add(formatter.format(kyakusu.getKyakuTankaNsumZenHi()));
            }
            
//          ネットテイク
            //ネットテイク実績店舗数、ネットテイク売上
            lineList.add(formatter.format(uriage.getMiseKbnNtake()));
            lineList.add(formatter.format(uriage.getUriageNtake()));
            //前年同月営業日補正の場合、前年比対象・ネットテイク売上、前年ネットテイク実績設定
            if (parameterDto.isSelectHosei()){
            	//前年比対象ネットテイク売上、前年比対象前年ネットテイク実績、前年比(ネットテイク売上)
            	lineList.add(formatter.format(uriHose.getUriageNtake()));
            	lineList.add(formatter.format(uriHose.getUriageNtakeZen()));
            	lineList.add(formatter.format(uriHose.getUriageNtakeZenHi()));
            }else{
            	//前年ネットテイク実績、前年比(ネットテイク売上)
            	lineList.add(formatter.format(uriage.getUriageNtakeZen()));
            	lineList.add(formatter.format(uriage.getUriageNtakeZenHi()));
            }
            //構成比(売上)
            lineList.add(formatter.format(uriage.getUriageNtakeKoseiHi()));
            //前年データ種別が前年同曜日以外の場合 、ネットテイク売上平均
            if (!NipoZennenDataShubetu.CODE_DOYO.equals(zenDataShu)){
            	lineList.add(formatter.format(uriHei.getUriageNtake()));
            }
            //ネットテイク件数
            lineList.add(formatter.format(kyakusu.getKyakusuNtake()));
            //前年同月営業日補正の場合、前年比対象・ネットテイク件数、前年ネットテイク件数設定
            if (parameterDto.isSelectHosei()){
            	//前年比対象ネットテイク件数、前年比対象前年ネットテイク件数、前年比(ネットテイク件数)
            	lineList.add(formatter.format(kyakuHose.getKyakusuNtake()));
            	lineList.add(formatter.format(kyakuHose.getKyakusuNtakeZen()));
            	lineList.add(formatter.format(kyakuHose.getKyakusuNtakeZenHi()));
            }else{
            	//前年ネットテイク実績、前年比(ネットテイク件数)
            	lineList.add(formatter.format(kyakusu.getKyakusuNtakeZen()));
            	lineList.add(formatter.format(kyakusu.getKyakusuNtakeZenHi()));
            }
            //構成比(件数)
            lineList.add(formatter.format(kyakusu.getKyakusuNtakeKoseiHi()));
            //前年データ種別が前年同曜日以外の場合 、ネットテイク件数平均
            if (!NipoZennenDataShubetu.CODE_DOYO.equals(zenDataShu)){
            	lineList.add(formatter.format(kyakuHei.getKyakusuNtake()));
            }
            //客単価（ネットテイク）
            lineList.add(formatter.format(kyakusu.getKyakuTankaNtake()));
            //前年同月営業日補正の場合、前年比対象・対象客単価（ネットテイク）、対象前年客単価（ネットテイク）設定
            if (parameterDto.isSelectHosei()){
            	//前年比対象客単価（ネットテイク）、前年比対象前年客単価（ネットテイク）、前年比(客単価)
            	lineList.add(formatter.format(kyakuHose.getKyakuTankaNtake()));
            	lineList.add(formatter.format(kyakuHose.getKyakuTankaNtakeZen()));
            	lineList.add(formatter.format(kyakuHose.getKyakuTankaNtakeZenHi()));
            }else{
            	//前年客単価（ネットテイク）、前年比(客単価)
            	lineList.add(formatter.format(kyakusu.getKyakuTankaNtakeZen()));
            	lineList.add(formatter.format(kyakusu.getKyakuTankaNtakeZenHi()));
            }
            
//          ネット宅配
            //ネット宅配実績店舗数、ネット宅配売上
            lineList.add(formatter.format(uriage.getMiseKbnNtaku()));
            lineList.add(formatter.format(uriage.getUriageNtaku()));
            //前年同月営業日補正の場合、前年比対象・ネット宅配売上、前年ネット宅配実績設定
            if (parameterDto.isSelectHosei()){
            	//前年比対象ネット宅配売上、前年比対象前年ネット宅配実績、前年比(ネット宅配売上)
            	lineList.add(formatter.format(uriHose.getUriageNtaku()));
            	lineList.add(formatter.format(uriHose.getUriageNtakuZen()));
            	lineList.add(formatter.format(uriHose.getUriageNtakuZenHi()));
            }else{
            	//前年ネット宅配実績、前年比(ネット宅配売上)
            	lineList.add(formatter.format(uriage.getUriageNtakuZen()));
            	lineList.add(formatter.format(uriage.getUriageNtakuZenHi()));
            }
            //構成比(売上)
            lineList.add(formatter.format(uriage.getUriageNtakuKoseiHi()));
            //前年データ種別が前年同曜日以外の場合 、ネット宅配売上平均
            if (!NipoZennenDataShubetu.CODE_DOYO.equals(zenDataShu)){
            	lineList.add(formatter.format(uriHei.getUriageNtaku()));
            }
            //ネット宅配件数
            lineList.add(formatter.format(kyakusu.getKyakusuNtaku()));
            //前年同月営業日補正の場合、前年比対象・ネット宅配件数、前年ネット宅配件数設定
            if (parameterDto.isSelectHosei()){
            	//前年比対象ネット宅配件数、前年比対象前年ネット宅配件数、前年比(ネット宅配件数)
            	lineList.add(formatter.format(kyakuHose.getKyakusuNtaku()));
            	lineList.add(formatter.format(kyakuHose.getKyakusuNtakuZen()));
            	lineList.add(formatter.format(kyakuHose.getKyakusuNtakuZenHi()));
            }else{
            	//前年ネット宅配実績、前年比(ネット宅配件数)
            	lineList.add(formatter.format(kyakusu.getKyakusuNtakuZen()));
            	lineList.add(formatter.format(kyakusu.getKyakusuNtakuZenHi()));
            }
            //構成比(件数)
            lineList.add(formatter.format(kyakusu.getKyakusuNtakuKoseiHi()));
            //前年データ種別が前年同曜日以外の場合 、ネット宅配件数平均
            if (!NipoZennenDataShubetu.CODE_DOYO.equals(zenDataShu)){
            	lineList.add(formatter.format(kyakuHei.getKyakusuNtaku()));
            }
            //客単価（ネット宅配）
            lineList.add(formatter.format(kyakusu.getKyakuTankaNtaku()));
            //前年同月営業日補正の場合、前年比対象・対象客単価（ネット宅配）、対象前年客単価（ネット宅配）設定
            if (parameterDto.isSelectHosei()){
            	//前年比対象客単価（ネット宅配）、前年比対象前年客単価（ネット宅配）、前年比(客単価)
            	lineList.add(formatter.format(kyakuHose.getKyakuTankaNtaku()));
            	lineList.add(formatter.format(kyakuHose.getKyakuTankaNtakuZen()));
            	lineList.add(formatter.format(kyakuHose.getKyakuTankaNtakuZenHi()));
            }else{
            	//前年客単価（ネット宅配）、前年比(客単価)
            	lineList.add(formatter.format(kyakusu.getKyakuTankaNtakuZen()));
            	lineList.add(formatter.format(kyakusu.getKyakuTankaNtakuZenHi()));
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
        head1stList.add(NetorderNipoConstants.CSV_HD_COMPANY);
        head1stList.add(parameterDto.getCompanyName());
        head1stList.add(NetorderNipoConstants.EMPTY);
        head1stList.add(NetorderNipoConstants.CSV_HD_TAISHO_TENPO);
        head1stList.add(TaishoTenpo.getName(parameterDto.getTaishoTenpoCd()));

        // ２行目:店舗種別、対象期間
        List head2ndList = new ArrayList();
        head2ndList.add(NetorderNipoConstants.CSV_HD_TENPO_SHU);
        head2ndList.add(TenpoShubetu.getName(parameterDto.getTenpoShubetuCd()));
        head2ndList.add(NetorderNipoConstants.EMPTY);
        head2ndList.add(NetorderNipoConstants.CSV_HD_TAISHO_KIKAN);
        head2ndList.add(NipoRefUtil.getCsvTaishoKikan(parameterDto));
 
        // ３行目:集計区分、前年データ種別
        List head3rdList = new ArrayList();
        head3rdList.add(NetorderNipoConstants.CSV_HD_SHUKEI_KBN);
        head3rdList.add(ShukeiKbn.getName(parameterDto.getShukeiKbnCd()));
        head3rdList.add(NetorderNipoConstants.EMPTY);
        head3rdList.add(NetorderNipoConstants.CSV_HD_ZEN_DATA_SHU);
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
        
        //　５行目:データ部ヘッダ作成「大分類」
        List head5thList = new ArrayList();
        
        // ６行目:データ部ヘッダ作成
        List head6thList = new ArrayList();
        // 集計区分が直営店を含まないの場合、事業本部、エリア、支部コード・名称設定
        if (ShukeiKbn.OUT_RC.equals(parameterDto.getShukeiKbnCd())) {
        	//事業本部ｺｰﾄﾞ
            head6thList.add(NetorderNipoConstants.CSV_DT_JIGYOU_CD);
            //事業本部名称
            head6thList.add(NetorderNipoConstants.CSV_DT_JIGYOU_NAME);
            //ｴﾘｱｺｰﾄﾞ
            head6thList.add(NetorderNipoConstants.CSV_DT_SLAREA_CD);
            //ｴﾘｱｺｰﾄﾞ
            head6thList.add(NetorderNipoConstants.CSV_DT_SLAREA_NAME);
            //ｴﾘｱｺｰﾄﾞ
            head6thList.add(NetorderNipoConstants.CSV_DT_SIBU_CD);
            //支部名称
            head6thList.add(NetorderNipoConstants.CSV_DT_SIBU_NAME);
        // 集計区分が直営店を含むの場合、集計区分設定            
        } else {
        	//集計区分
            head6thList.add(NetorderNipoConstants.CSV_DT_SHUKEI_KBN);
        }
        //当年店数
        head6thList.add(NetorderNipoConstants.CSV_DT_TOUNEN_TENPO_COUNT);
        //前年店数
        head6thList.add(NetorderNipoConstants.CSV_DT_ZENNEN_TENPO_COUNT);
        // 前年同月営業日補正の場合、前年比対象・店数、前年店数設定
        if (parameterDto.isSelectHosei()){
        	//前年比対象店数
            head6thList.add(NetorderNipoConstants.CSV_DT_HOSEI_TENPO_COUNT);
            //前年比対象前年店数
            head6thList.add(NetorderNipoConstants.CSV_DT_HOSEI_ZEN_TENPO_COUNT);
        }
        //売上
        head6thList.add(NetorderNipoConstants.CSV_DT_URIAGE);
        //予算
        head6thList.add(NetorderNipoConstants.CSV_DT_YOSAN);
        //達成率
        head6thList.add(NetorderNipoConstants.CSV_DT_TASSEI);
        // 前年データ種別が前年同曜日の場合、前年実績、前年比(売上設定)
        if (NipoZennenDataShubetu.CODE_DOYO.equals(zenDataShu)) {
        	//前年実績
            head6thList.add(NetorderNipoConstants.CSV_DT_URIAGE_ZEN);
            //前年比(売上)
            head6thList.add(NetorderNipoConstants.CSV_DT_ZEN_HI_URIAGE);
        // 前年データ種別が前年同曜日以外の場合
        } else {
            // 前年同月営業日補正の場合、前年比対象・売上、前年売上設定
            if (parameterDto.isSelectHosei()){
            	//前年比対象売上
                head6thList.add(NetorderNipoConstants.CSV_DT_HOSEI_URIAGE);
                //前年比対象前年売上
                head6thList.add(NetorderNipoConstants.CSV_DT_HOSEI_URIAGE_ZEN);
            // 前年同月営業日補正以外の場合、前年売上設定
            } else {
            	//前年実績
                head6thList.add(NetorderNipoConstants.CSV_DT_URIAGE_ZEN);
            }
            // 前年比(売上)、売上平均、予算平均、予算差
            head6thList.add(NetorderNipoConstants.CSV_DT_ZEN_HI_URIAGE);            
            head6thList.add(NetorderNipoConstants.CSV_DT_URIAGE_AVE);
            head6thList.add(NetorderNipoConstants.CSV_DT_YOSAN_AVE);
            head6thList.add(NetorderNipoConstants.CSV_DT_YOSAN_DIFF);    
            // 前年同月営業日補正の場合、前年比対象・売上平均、前年売上平均設定
            if (parameterDto.isSelectHosei()){
            	//前年比対象売上平均
                head6thList.add(NetorderNipoConstants.CSV_DT_HOSEI_URIAGE_AVE);
                //前年比対象前年売上平均
                head6thList.add(NetorderNipoConstants.CSV_DT_HOSEI_ZEN_URIAGE_AVE);
            // 前年同月営業日補正の場合、前年売上平均設定                
            } else {
            	//前年実績平均
                head6thList.add(NetorderNipoConstants.CSV_DT_ZEN_URIAGE_AVE);
            }
            // 前年差(売上)
            head6thList.add(NetorderNipoConstants.CSV_DT_ZEN_DIFF_URIAGE);
        }
        // 客数
        head6thList.add(NetorderNipoConstants.CSV_DT_KYAKUSU);        
        // 前年同月営業日補正の場合、前年比対象・客数、前年客数設定 
        if (parameterDto.isSelectHosei()){               
            head6thList.add(NetorderNipoConstants.CSV_DT_HOSEI_KYAKUSU);
            head6thList.add(NetorderNipoConstants.CSV_DT_HOSEI_KYAKUSU_ZEN);
        // 前年同月営業日補正以外の場合、前年客数設定
        } else {
            head6thList.add(NetorderNipoConstants.CSV_DT_KYAKUSU_ZEN);
        }
        // 前年比(客数)、客単価
        head6thList.add(NetorderNipoConstants.CSV_DT_ZEN_HI_KYAKUSU);        
        head6thList.add(NetorderNipoConstants.CSV_DT_KYAKU_TANKA);
        // 前年同月営業日補正の場合、前年比対象・客単価、前年客単価設定
        if (parameterDto.isSelectHosei()){
            head6thList.add(NetorderNipoConstants.CSV_DT_HOSEI_KYAKU_TANKA);
            head6thList.add(NetorderNipoConstants.CSV_DT_HOSEI_KYAKU_TANKA_ZEN);
        // 前年同月営業日補正以外の場合、前年客単価設定
        } else {
            head6thList.add(NetorderNipoConstants.CSV_DT_KYAKU_TANKA_ZEN);
        }
        // 前年比(客単価)
        head6thList.add(NetorderNipoConstants.CSV_DT_ZEN_HI_KYAKU_TANKA);
        // 前年データ種別が前年同曜日以外の場合        
        if (!NipoZennenDataShubetu.CODE_DOYO.equals(zenDataShu)) {
            // 客数平均
            head6thList.add(NetorderNipoConstants.CSV_DT_KYAKUSU_AVE);    
            // 前年同月営業日補正の場合、前年比対象・客数平均、前年客数平均
            if (parameterDto.isSelectHosei()){               
                head6thList.add(NetorderNipoConstants.CSV_DT_HOSEI_KYAKUSU_AVE);
                head6thList.add(NetorderNipoConstants.CSV_DT_HOSE_ZEN_KYAKUSU_AVE);
            // 前年同月営業日補正以外の場合、前年客数平均
            } else {
                head6thList.add(NetorderNipoConstants.CSV_DT_ZEN_KYAKUSU_AVE);
            }
            // 前年差(客数)
            head6thList.add(NetorderNipoConstants.CSV_DT_ZEN_DIFF_KYAKUSU);       
        }
//ネット注文
        //ネット注文実績店舗数、ネット注文売上
        int index1 = head6thList.toArray().length;
        for(int i=0;i<index1;i++){
        	head5thList.add("");
        }
        head5thList.add("ネット注文(合算)");
        head6thList.add(NetorderNipoConstants.CSV_DT_MISE_KBN_NSUM);
        head6thList.add(NetorderNipoConstants.CSV_DT_URIAGE_NSUM);
        //前年同月営業日補正の場合、前年比対象・ネット注文売上、前年ネット注文実績設定
        if (parameterDto.isSelectHosei()){
        	//前年比対象ネット注文売上、前年比対象前年ネット注文実績
        	head6thList.add(NetorderNipoConstants.CSV_DT_HOSEI_URIAGE_NSUM);
        	head6thList.add(NetorderNipoConstants.CSV_DT_ZEN_HOSEI_URIAGE_NSUM);
        }else{
        	//前年ネット注文実績
        	head6thList.add(NetorderNipoConstants.CSV_DT_ZEN_URIAGE_NSUM);
        }
        //前年比(ネット注文売上)、構成比(売上)
        head6thList.add(NetorderNipoConstants.CSV_DT_ZEN_HI_URIAGE_NSUM);
        head6thList.add(NetorderNipoConstants.CSV_DT_KOSEI_HI_URIAGE_NSUM);
        //前年データ種別が前年同曜日以外の場合 、ネット注文売上平均
        if (!NipoZennenDataShubetu.CODE_DOYO.equals(zenDataShu)){
        	head6thList.add(NetorderNipoConstants.CSV_DT_URIAGE_NSUM_AVE);
        }
        //ネット注文件数
        head6thList.add(NetorderNipoConstants.CSV_DT_KYAKUSU_NSUM);
        //前年同月営業日補正の場合、前年比対象・ネット注文件数、前年ネット注文件数設定
        if (parameterDto.isSelectHosei()){
        	//前年比対象ネット注文件数、前年比対象前年ネット注文件数
        	head6thList.add(NetorderNipoConstants.CSV_DT_HOSEI_KYAKUSU_NSUM);
        	head6thList.add(NetorderNipoConstants.CSV_DT_ZEN_HOSEI_KYAKUSU_NSUM);
        }else{
        	//前年ネット注文実績
        	head6thList.add(NetorderNipoConstants.CSV_DT_ZEN_KYAKUSU_NSUM);
        }
        //前年比(ネット注文件数)、構成比(件数)
        head6thList.add(NetorderNipoConstants.CSV_DT_ZEN_HI_KYAKUSU_NSUM);
        head6thList.add(NetorderNipoConstants.CSV_DT_KOSEI_HI_KYAKUSU_NSUM);
        //前年データ種別が前年同曜日以外の場合 、ネット注文件数平均
        if (!NipoZennenDataShubetu.CODE_DOYO.equals(zenDataShu)){
        	head6thList.add(NetorderNipoConstants.CSV_DT_KYAKUSU_NSUM_AVE);
        }
        //客単価（ネット注文）
        head6thList.add(NetorderNipoConstants.CSV_DT_KYAKU_TANKA_NSUM);
        //前年同月営業日補正の場合、前年比対象・対象客単価（ネット注文）、対象前年客単価（ネット注文）設定
        if (parameterDto.isSelectHosei()){
        	//前年比対象客単価（ネット注文）、前年比対象前年客単価（ネット注文）
        	head6thList.add(NetorderNipoConstants.CSV_DT_HOSEI_KYAKU_TANKA_NSUM);
        	head6thList.add(NetorderNipoConstants.CSV_DT_HOSEI_KYAKU_TANKA_NSUM_ZEN);
        }else{
        	//前年客単価（ネット注文）
        	head6thList.add(NetorderNipoConstants.CSV_DT_KYAKU_TANKA_NSUM_ZEN);
        }
        //前年比(客単価) 
        head6thList.add(NetorderNipoConstants.CSV_DT_ZEN_HI_KYAKU_TANKA_NSUM);
        
//      ネットテイク
        //ネットテイク実績店舗数、ネットテイク売上
        int index2 = head6thList.toArray().length;
        for (int i=index1+1;i<index2;i++)
        {
        	head5thList.add("");
        }
        head5thList.add("ネット注文(テイク)");
        head6thList.add(NetorderNipoConstants.CSV_DT_MISE_KBN_NTAKE);
        head6thList.add(NetorderNipoConstants.CSV_DT_URIAGE_NTAKE);
        //前年同月営業日補正の場合、前年比対象・ネットテイク売上、前年ネットテイク実績設定
        if (parameterDto.isSelectHosei()){
        	//前年比対象ネットテイク売上、前年比対象前年ネットテイク実績
        	head6thList.add(NetorderNipoConstants.CSV_DT_HOSEI_URIAGE_NTAKE);
        	head6thList.add(NetorderNipoConstants.CSV_DT_ZEN_HOSEI_URIAGE_NTAKE);
        }else{
        	//前年ネットテイク実績
        	head6thList.add(NetorderNipoConstants.CSV_DT_ZEN_URIAGE_NTAKE);
        }
        //前年比(ネットテイク売上)、構成比(売上)
        head6thList.add(NetorderNipoConstants.CSV_DT_ZEN_HI_URIAGE_NTAKE);
        head6thList.add(NetorderNipoConstants.CSV_DT_KOSEI_HI_URIAGE_NTAKE);
        //前年データ種別が前年同曜日以外の場合 、ネットテイク売上平均
        if (!NipoZennenDataShubetu.CODE_DOYO.equals(zenDataShu)){
        	head6thList.add(NetorderNipoConstants.CSV_DT_URIAGE_NTAKE_AVE);
        }
        //ネットテイク件数
        head6thList.add(NetorderNipoConstants.CSV_DT_KYAKUSU_NTAKE);
        //前年同月営業日補正の場合、前年比対象・ネットテイク件数、前年ネットテイク件数設定
        if (parameterDto.isSelectHosei()){
        	//前年比対象ネットテイク件数、前年比対象前年ネットテイク件数
        	head6thList.add(NetorderNipoConstants.CSV_DT_HOSEI_KYAKUSU_NTAKE);
        	head6thList.add(NetorderNipoConstants.CSV_DT_ZEN_HOSEI_KYAKUSU_NTAKE);
        }else{
        	//前年ネットテイク実績
        	head6thList.add(NetorderNipoConstants.CSV_DT_ZEN_KYAKUSU_NTAKE);
        }
        //前年比(ネットテイク件数)、構成比(件数)
        head6thList.add(NetorderNipoConstants.CSV_DT_ZEN_HI_KYAKUSU_NTAKE);
        head6thList.add(NetorderNipoConstants.CSV_DT_KOSEI_HI_KYAKUSU_NTAKE);
        //前年データ種別が前年同曜日以外の場合 、ネットテイク件数平均
        if (!NipoZennenDataShubetu.CODE_DOYO.equals(zenDataShu)){
        	head6thList.add(NetorderNipoConstants.CSV_DT_KYAKUSU_NTAKE_AVE);
        }
        //客単価（ネットテイク）
        head6thList.add(NetorderNipoConstants.CSV_DT_KYAKU_TANKA_NTAKE);
        //前年同月営業日補正の場合、前年比対象・対象客単価（ネットテイク）、対象前年客単価（ネットテイク）設定
        if (parameterDto.isSelectHosei()){
        	//前年比対象客単価（ネットテイク）、前年比対象前年客単価（ネットテイク）
        	head6thList.add(NetorderNipoConstants.CSV_DT_HOSEI_KYAKU_TANKA_NTAKE);
        	head6thList.add(NetorderNipoConstants.CSV_DT_HOSEI_KYAKU_TANKA_NTAKE_ZEN);
        }else{
        	//前年客単価（ネットテイク）
        	head6thList.add(NetorderNipoConstants.CSV_DT_KYAKU_TANKA_NTAKE_ZEN);
        }
        //前年比(客単価)
        head6thList.add(NetorderNipoConstants.CSV_DT_ZEN_HI_KYAKU_TANKA_NTAKE);
        
//      ネット宅配
        //ネット宅配実績店舗数、ネットテイク売上
        int index3 = head6thList.toArray().length;
        for (int i=index2+1;i<index3;i++)
        {
        	head5thList.add("");
        }
        head5thList.add("ネット注文(宅配)");
        head6thList.add(NetorderNipoConstants.CSV_DT_MISE_KBN_NTAKU);
        head6thList.add(NetorderNipoConstants.CSV_DT_URIAGE_NTAKU);
        //前年同月営業日補正の場合、前年比対象・ネット宅配売上、前年ネット宅配実績設定
        if (parameterDto.isSelectHosei()){
        	//前年比対象ネット宅配売上、前年比対象前年ネット宅配実績
        	head6thList.add(NetorderNipoConstants.CSV_DT_HOSEI_URIAGE_NTAKU);
        	head6thList.add(NetorderNipoConstants.CSV_DT_ZEN_HOSEI_URIAGE_NTAKU);
        }else{
        	//前年ネット宅配実績
        	head6thList.add(NetorderNipoConstants.CSV_DT_ZEN_URIAGE_NTAKU);
        }
        //前年比(ネット宅配売上)、構成比(売上)
        head6thList.add(NetorderNipoConstants.CSV_DT_ZEN_HI_URIAGE_NTAKU);
        head6thList.add(NetorderNipoConstants.CSV_DT_KOSEI_HI_URIAGE_NTAKU);
        //前年データ種別が前年同曜日以外の場合 、ネット宅配売上平均
        if (!NipoZennenDataShubetu.CODE_DOYO.equals(zenDataShu)){
        	head6thList.add(NetorderNipoConstants.CSV_DT_URIAGE_NTAKU_AVE);
        }
        //ネット宅配件数
        head6thList.add(NetorderNipoConstants.CSV_DT_KYAKUSU_NTAKU);
        //前年同月営業日補正の場合、前年比対象・ネット宅配件数、前年ネット宅配件数設定
        if (parameterDto.isSelectHosei()){
        	//前年比対象ネット宅配件数、前年比対象前年ネット宅配件数
        	head6thList.add(NetorderNipoConstants.CSV_DT_HOSEI_KYAKUSU_NTAKU);
        	head6thList.add(NetorderNipoConstants.CSV_DT_ZEN_HOSEI_KYAKUSU_NTAKU);
        }else{
        	//前年ネット宅配実績
        	head6thList.add(NetorderNipoConstants.CSV_DT_ZEN_KYAKUSU_NTAKU);
        }
        //前年比(ネット宅配件数)、構成比(件数)
        head6thList.add(NetorderNipoConstants.CSV_DT_ZEN_HI_KYAKUSU_NTAKU);
        head6thList.add(NetorderNipoConstants.CSV_DT_KOSEI_HI_KYAKUSU_NTAKU);
        //前年データ種別が前年同曜日以外の場合 、ネット宅配件数平均
        if (!NipoZennenDataShubetu.CODE_DOYO.equals(zenDataShu)){
        	head6thList.add(NetorderNipoConstants.CSV_DT_KYAKUSU_NTAKU_AVE);
        }
        //客単価（ネット宅配）
        head6thList.add(NetorderNipoConstants.CSV_DT_KYAKU_TANKA_NTAKU);
        //前年同月営業日補正の場合、前年比対象・対象客単価（ネット宅配）、対象前年客単価（ネット宅配）設定
        if (parameterDto.isSelectHosei()){
        	//前年比対象客単価（ネット宅配）、前年比対象前年客単価（ネット宅配）
        	head6thList.add(NetorderNipoConstants.CSV_DT_HOSEI_KYAKU_TANKA_NTAKU);
        	head6thList.add(NetorderNipoConstants.CSV_DT_HOSEI_KYAKU_TANKA_NTAKU_ZEN);
        }else{
        	//前年客単価（ネット宅配）
        	head6thList.add(NetorderNipoConstants.CSV_DT_KYAKU_TANKA_NTAKU_ZEN);
        }
        //前年比(客単価)
        head6thList.add(NetorderNipoConstants.CSV_DT_ZEN_HI_KYAKU_TANKA_NTAKU);
       
        headerList.add(head1stList);
        headerList.add(head2ndList);
        headerList.add(head3rdList);
        headerList.add(head4thList);
        headerList.add(head5thList);
        headerList.add(head6thList);
        
        return headerList;
    }

    /**
     * CSV出力用売上・予算情報を取得する
     * @param parameterDto 条件部情報Dto
     * @return Map CSV出力用売上・予算情報
     */
    private Map searchSibuUriageInfo(NipoRefConditionParameterDto parameterDto) {
        //検索条件の取得
        Map paramMap = getNetorderNipoSearchLogic().getSearchData(parameterDto);
 
        // 支部マスタの取得
        List sibuList = getViewSibuInfoLogic().execute(paramMap);
        
        // 売上情報の取得
        List uriageList = getUriageInfoLogic().execute(paramMap);
        
        // 予算情報の取得
        List yosanList = getYosanInfoLogic().execute(paramMap);
        
        Map uriYosanMap = new HashMap();
        uriYosanMap.put(NetorderNipoConstants.MAP_SIBU_LIST, sibuList);
        uriYosanMap.put(NetorderNipoConstants.MAP_URIAGE_LIST, uriageList);
        uriYosanMap.put(NetorderNipoConstants.MAP_YOSAN_LIST, yosanList);
        uriYosanMap.put(NetorderNipoConstants.MAP_SHUKEI_KBN, parameterDto.getShukeiKbnCd());
        uriYosanMap.put(NetorderNipoConstants.MAP_PARAMS, paramMap);
                
        // 前年データ種別が前年同月営業日補正の場合
        List hoseiUriList = new ArrayList();
        if (parameterDto.isSelectHosei()) {
            paramMap.put(NetorderNipoConstants.MAP_DATASHU, NipoZennenDataShubetu.CODE_DOGETU);
            // CSVの前年比（売上）が画面と一致していない問題の対応
            // 売上と前年比対象売上が逆になっているので、上で取得した物を前年比対象売上とし
            //　前年同月売上を取得する
            hoseiUriList = uriageList;
            uriageList = getUriageInfoLogic().execute(paramMap);
            uriYosanMap.put(NetorderNipoConstants.MAP_URIAGE_LIST, uriageList);          
            uriYosanMap.put(NetorderNipoConstants.MAP_HOSE_URI_LIST, hoseiUriList);
            paramMap.put(NetorderNipoConstants.MAP_DATASHU, NipoZennenDataShubetu.CODE_HOSEI);
        }          
        uriYosanMap.put(NetorderNipoConstants.MAP_SIBU_LIST, sibuList);
        uriYosanMap.put(NetorderNipoConstants.MAP_URIAGE_LIST, uriageList);
        uriYosanMap.put(NetorderNipoConstants.MAP_YOSAN_LIST, yosanList);
        uriYosanMap.put(NetorderNipoConstants.MAP_SHUKEI_KBN, parameterDto.getShukeiKbnCd());
        uriYosanMap.put(NetorderNipoConstants.MAP_PARAMS, paramMap);
 
        return getViewUriYosanLogic().execute(uriYosanMap);
    }

    /**
     * 検索条件取得ロジックを取得する
     * @return NetorderNipoSearchLogic 検索条件取得ロジック
     */
    public NetorderNipoSearchLogic getNetorderNipoSearchLogic() {
        return netorderNipoSearchLogic;
    }

    /**
     *  検索条件取得ロジックを設定する
     * @param netorderNipoSearchLogic 検索条件取得ロジック
     */
    public void setNetorderNipoSearchLogic(NetorderNipoSearchLogic netorderNipoSearchLogic) {
        this.netorderNipoSearchLogic = netorderNipoSearchLogic;
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