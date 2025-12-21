package jp.co.isid.mos.bird.bizreport.netorderniporef.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.code.NipoZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.common.code.ShukeiKbn;
import jp.co.isid.mos.bird.bizreport.common.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.common.code.TaishoTenpo;
import jp.co.isid.mos.bird.bizreport.common.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.common.BizReportConstants;
import jp.co.isid.mos.bird.bizreport.common.common.NipoRefConstants;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;
import jp.co.isid.mos.bird.bizreport.common.entity.CodCompany;
import jp.co.isid.mos.bird.bizreport.common.util.NipoRefUtil;
import jp.co.isid.mos.bird.bizreport.netorderniporef.common.NetorderNipoCommon;
import jp.co.isid.mos.bird.bizreport.netorderniporef.common.NetorderNipoConstants;
import jp.co.isid.mos.bird.bizreport.netorderniporef.entity.MstSibuInfo;
import jp.co.isid.mos.bird.bizreport.netorderniporef.entity.TrnUriageNipoInfo;
import jp.co.isid.mos.bird.bizreport.netorderniporef.logic.NetorderNipoSearchLogic;
import jp.co.isid.mos.bird.bizreport.netorderniporef.logic.TargetSibuInfoLogic;
import jp.co.isid.mos.bird.bizreport.netorderniporef.logic.UriageNipoInfoLogic;
import jp.co.isid.mos.bird.bizreport.netorderniporef.logic.ViewSibuInfoLogic;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * ネット注文日報CSVダウンロードロジック
 *
 */
public class NetorderNipoMiseCsvOutputLogicImpl implements CsvOutputLogic {
    /** 店舗別ネット注文日報CSVダウンロードロジックID */
    public static final String LOGIC_ID = "BBR017L09";

    /** 検索条件取得ロジック */ 
    private NetorderNipoSearchLogic netorderNipoSearchLogic;

    /** 店別売上予算情報取得ロジック */ 
    private UriageNipoInfoLogic uriageNipoInfoLogic;
 
    /** 表示対象の支部一覧取得ロジック */
    private TargetSibuInfoLogic targetSibuInfoLogic;
 
    /** 表示支部取得ロジック */
    private ViewSibuInfoLogic  viewSibuInfoLogic;

    /**
     * CSVファイル名称を取得する
     * @param   csvOutputDto    CSV出力DTO
     * @return  String          CSVファイル名称
     */
    public String getFileName(CsvOutputDto csvOutputDto) {   
        return NetorderNipoConstants.CSV_FILE_NAME_SIBU;
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
     */
    public List getOutputData(CsvOutputDto csvOutputDto) {
        // 数値タイプの文字列フォーマッタ(定数)        
        NumericFormatter formatter = new NumericFormatter();
        // 数値タイプの文字列フォーマッタ(定数)
        NumericFormatter formatterUriage = new NumericFormatter(true, BizReportConstants.FORMAT_JPY, true);
        formatterUriage.setDefaultText("0");
        
        // CSV出力データリスト、ヘッダー部情報取得
        List outputList = getHeaderData(csvOutputDto);
  
        // 条件部情報Dto取得
        NipoRefConditionParameterDto parameterDto = (NipoRefConditionParameterDto) csvOutputDto;
        
        //SV対応       
        String sibuCd = "";
        String kbnCd  = "";
        if(!parameterDto.isSvFlg()){
            // 支部コードの入力チェック
            if (NetorderNipoCommon.isNull(parameterDto.getSibuCd())) {
                throw new NotNullException(NetorderNipoConstants.MSG_SIBU_CD);
            }           
            sibuCd = parameterDto.getSibuCd();
            kbnCd  = NetorderNipoCommon.getSumKbn(parameterDto.getClassName());
        }

        // 検索条件取得
        Map paramMap = getNetorderNipoSearchLogic().getSearchData(parameterDto);
        paramMap.put(NetorderNipoConstants.MAP_SIBU_CD, sibuCd);        
        paramMap.put(NetorderNipoConstants.MAP_KBN_CD , kbnCd);
        paramMap.put(NetorderNipoConstants.CSV,"CSV");

        List resultList = new ArrayList();
        List uriageList = new ArrayList();
        List kyakusuList = new ArrayList();
        List uriageHoseiList = new ArrayList();
        List kyakusuHoseiList = new ArrayList();

        // リンク区分が支部の場合
        if (parameterDto.isSvFlg()
                || TaishoJoken.CODE_SIBU.equals(NetorderNipoCommon.getSumKbn(parameterDto.getClassName()))) {
            Map resultMap = getUriageNipoInfoLogic().execute(paramMap);            
            resultList = (List) resultMap.get(NetorderNipoConstants.MAP_RESULT);
            uriageList = (List) resultMap.get(NetorderNipoConstants.MAP_URIAGE_LIST);
            kyakusuList = (List) resultMap.get(NetorderNipoConstants.MAP_KYAKUSU_LIST);
            // 前年データ種別が前年同月営業日補正の時    
            if (parameterDto.isSelectHosei()) {
                uriageHoseiList = (List) resultMap.get(NetorderNipoConstants.MAP_RST_URIAGE_HOSE);
                kyakusuHoseiList = (List) resultMap.get(NetorderNipoConstants.MAP_RST_KYAKUSU_HOSE);
            }
        // リンク区分が支部以外の場合    
        } else {
            paramMap.put(NetorderNipoConstants.MAP_KBN_CD, NetorderNipoCommon.getSumKbn(parameterDto.getClassName()));
            List sibuList = getTargetSibuInfoLogic().execute(paramMap);
            List dispSibuList = getViewSibuInfoLogic().execute(paramMap);
            List targetSibuList = new ArrayList();            
            for (int j = 0; j < sibuList.size(); j++) {
                MstSibuInfo sibu = (MstSibuInfo)sibuList.get(j);
                for (int i = 0; i < dispSibuList.size(); i++) {
                    MstSibuInfo dispSibu = (MstSibuInfo)dispSibuList.get(i);
                    if ((sibu.getCompanyCd().equals( dispSibu.getCompanyCd())) 
                        && ((sibu.getSibuCd().trim()).equals((dispSibu.getSibuCd().trim()))))  {
                        if (dispSibu.getDispKbn().equals(NetorderNipoConstants.DISP)) {
                            targetSibuList.add(sibu) ;
                            break;
                        }
                        //支部予算のみ存在する場合。
                        if ( "DISPONLY".equals(dispSibu.getDispKbn()) ) {
                            targetSibuList.add(sibu) ;
                            break;
                        }
                    }
                }
            }                
            for ( int i = 0; i < targetSibuList.size(); i++ ) {
                MstSibuInfo sibu = (MstSibuInfo)targetSibuList.get(i);
                paramMap.put(NetorderNipoConstants.MAP_SIBU_CD, sibu.getSibuCd().trim());
                paramMap.put(NetorderNipoConstants.MAP_KBN_CD, TaishoJoken.CODE_SIBU);
                try {
	                Map uriageNipoMap = getUriageNipoInfoLogic().execute(paramMap);
	                uriageList.addAll((List)uriageNipoMap.get(NetorderNipoConstants.MAP_URIAGE_LIST));
	                kyakusuList.addAll((List)uriageNipoMap.get(NetorderNipoConstants.MAP_KYAKUSU_LIST));
	                // 前年データ種別が前年同月営業日補正の時                  
	                if (parameterDto.isSelectHosei()) {    
	                    uriageHoseiList.addAll((List)uriageNipoMap.get(NetorderNipoConstants.MAP_RST_URIAGE_HOSE));
	                    kyakusuHoseiList.addAll((List)uriageNipoMap.get(NetorderNipoConstants.MAP_RST_KYAKUSU_HOSE));
	                } else {
	                    resultList.addAll((List)uriageNipoMap.get(NetorderNipoConstants.MAP_RESULT));
	                }
                }
                catch (NoResultException noResult) {
                	//処理続行
                }
            }
        }
        //対象の会社が海外の会社でかつ、
        //円換算済のデータでのダウンロード要求だった場合、円換算処理を行います。
    	CodCompany codCompany = parameterDto.getCodCompany();
    	formatterUriage.setFormatPattern(codCompany.getDispFormat());
        // 前年データ種別が前年同月営業日補正以外の時、データ部情報取得
        if (!parameterDto.isSelectHosei()) {
        	if(resultList.isEmpty()) {
        		throw new NoResultException();
        	}
            if(BizReportConstants.FOREING_ON.equals(codCompany.getForeignFlg())) {
                //．日報共通DTO【条件部情報】換算済判断フラグがtrue(換算済)の場合下記の処理を行います。
            	if (parameterDto.isKansan()) {
    	        	//外貨値をCodCompany[管理会社情報].為替相場で円換算を行います。
    	        	BigDecimal decRate = codCompany.getRate();
    	        	resultList = NetorderNipoCommon.changeCurrency(resultList, decRate, false);
    	            formatterUriage.setFormatPattern(BizReportConstants.FORMAT_JPY);
            	}
            }
            for (int i = 0; i < resultList.size(); i++) {
                List lineList = new ArrayList();
                TrnUriageNipoInfo nipo = (TrnUriageNipoInfo)resultList.get(i);
                
                // 集計区分で『SV指定(担当店一覧)』が選択された場合 20081209追加
                if(ShukeiKbn.SV_CD.equals(parameterDto.getShukeiKbnCd())) {
                    // 支部コード・名称設定
                    lineList.add(NetorderNipoCommon.setEmpty(nipo.getSibuCd()));
                    lineList.add(NetorderNipoCommon.setEmpty(nipo.getSibuName()));                 
                    // ブロックコード・名称
                    if (!(NipoRefConstants.CSS_TR_CLASS.equals(nipo.getRClass())
                        || NipoRefConstants.CSS_TR_CLASS_SLAREA.equals(nipo.getRClass()))){
                        lineList.add(NetorderNipoConstants.EMPTY);
                        lineList.add(NetorderNipoCommon.setEmpty(nipo.getBlockName())); 
                    } else if (parameterDto.isMosCompany()) {
                        lineList.add(NetorderNipoCommon.setEmpty(nipo.getBlockCd()));
                        lineList.add(NetorderNipoCommon.setEmpty(nipo.getBlockName()));
                    }                   
                }
                // 集計区分が直営店を含まないの場合
                else if(ShukeiKbn.OUT_RC.equals(parameterDto.getShukeiKbnCd())) {
                    // 集計区分が支部計以外の場合、支部コード・名称設定
                    if (!TaishoJoken.CODE_SIBU.equals(NetorderNipoCommon.getSumKbn(parameterDto.getClassName()))) {
                        lineList.add(NetorderNipoCommon.setEmpty(nipo.getSibuCd()));
                        lineList.add(NetorderNipoCommon.setEmpty(nipo.getSibuName()));
                    }                    
                    // ブロックコード・名称
                    if (!(NipoRefConstants.CSS_TR_CLASS.equals(nipo.getRClass())
                        || NipoRefConstants.CSS_TR_CLASS_SLAREA.equals(nipo.getRClass()))){
                        lineList.add(NetorderNipoConstants.EMPTY);
                        lineList.add(NetorderNipoCommon.setEmpty(nipo.getBlockName())); 
                    } else if (parameterDto.isMosCompany()) {
                        lineList.add(NetorderNipoCommon.setEmpty(nipo.getBlockCd()));
                        lineList.add(NetorderNipoCommon.setEmpty(nipo.getBlockName()));
                    }                   
                }
                // 集計区分が直営店を含む且つ集計区分が支部計以外の場合、支部名称設定
                else if (!TaishoJoken.CODE_SIBU.equals(NetorderNipoCommon.getSumKbn(parameterDto.getClassName()))) {
                    lineList.add(NetorderNipoCommon.setEmpty(nipo.getSibuName()));
                }
                // 店コード・名称
                if (NipoRefConstants.CSS_TR_CLASS.equals(nipo.getRClass())) {
                    lineList.add(NetorderNipoCommon.setEmpty(nipo.getMiseCd()));
                    lineList.add(NetorderNipoCommon.setEmpty(nipo.getMiseNameKj()));                    
                } else if (parameterDto.isMosCompany()){
                    lineList.add(NetorderNipoConstants.EMPTY);
                    lineList.add(NetorderNipoConstants.EMPTY);
                }
                // 店舗種別が全店の場合、店種出力
                if (TenpoShubetu.CODE_ALL.equals(parameterDto.getTenpoShubetuCd())) {
                    lineList.add(NetorderNipoCommon.setEmpty(nipo.getZenKbn()));
                }
                // 休業
                lineList.add(nipo.getOpenKbnJpn());
                // 期日指定日報の場合天候、以外の場合営業日数設定
                if(TaishoKikan.DAY1.equals(parameterDto.getTaishoKikanCd())) {
                    lineList.add(nipo.getTenkoKbnJpn());
                } else {
                    lineList.add(formatter.format(nipo.getEigyoDays()));
                }
                if(NipoRefConstants.CSS_TR_CLASS_SEGMENT.equals(nipo.getRClass())) {
                	//新店・その他
                    // 売上、予算、達成率、前年売上、前年比(売上)
                    lineList.add("");
                    lineList.add(formatter.format(nipo.getYosan(),true));
                    lineList.add("");
                    lineList.add("");
                    lineList.add("");
                    // 客数、前年客数、前年比(客数)
                    lineList.add("");
                    lineList.add("");
                    lineList.add("");
                    // 単価、前年単価、前年比(単価)、前年休業
                    lineList.add("");
                    lineList.add("");
                	
                }
                else {
	                // 売上、予算、達成率、前年売上、前年比(売上)
	                lineList.add(formatterUriage.format(nipo.getUriage(),true));
	                lineList.add(formatter.format(nipo.getYosan(),true));
	                lineList.add(formatter.format(nipo.getTassei(),true));
	                lineList.add(formatterUriage.format(nipo.getUriageZen(),true));
	                lineList.add(formatter.format(nipo.getZenHiUri(),true));
	                // 客数、前年客数、前年比(客数)
	                lineList.add(formatter.format(nipo.getKyakusu(),true));
	                lineList.add(formatter.format(nipo.getKyakusuZen(), true));
	                lineList.add(formatter.format(nipo.getZenHiKyaku(), true));
	                // 単価、前年単価、前年比(単価)、前年休業
	                lineList.add(formatterUriage.format(nipo.getTanka(), true));
	                lineList.add(formatterUriage.format(nipo.getTankaZen(), true));
                }
                lineList.add(formatter.format(nipo.getZenHiTanka(),true));
                lineList.add(nipo.getOpenKbnZenJpn());
                // 期日指定日報の場合前年天候、以外の場合前年営業日数設定
                if(TaishoKikan.DAY1.equals(parameterDto.getTaishoKikanCd())) {
                    lineList.add(nipo.getTenkoKbnZenJpn());
                } else {
                    lineList.add(formatter.format(nipo.getEigyoDaysZen()));
                }
                
//              ネット注文
                //ネット注文日数、ネット注文売上
                lineList.add(formatter.format(nipo.getEigyoDaysNsum()));
                lineList.add(formatter.format(nipo.getUriageNsum()));
            	//前年ネット注文実績、前年比(ネット注文売上)
            	lineList.add(formatter.format(nipo.getUriageNsumZen()));
            	lineList.add(formatter.format(nipo.getZenHiUriageNsum()));
                //構成比(売上)
                lineList.add(formatter.format(nipo.getKouseiHiUriageNsum()));
                //ネット注文件数
                lineList.add(formatter.format(nipo.getKyakusuNsum()));
            	//前年ネット注文実績、前年比(ネット注文件数)
            	lineList.add(formatter.format(nipo.getKyakusuNsumZen()));
            	lineList.add(formatter.format(nipo.getZenHiKyakusuNsum()));
                //構成比(件数)
                lineList.add(formatter.format(nipo.getKouseiHiKyakusuNsum()));
                //客単価（ネット注文）
                lineList.add(formatter.format(nipo.getKyakuTankaNsum()));
            	//前年客単価（ネット注文）、前年比(客単価)
            	lineList.add(formatter.format(nipo.getZenKyakuTankaNsum()));
            	lineList.add(formatter.format(nipo.getZenHikyakuTankaNsum()));
                
//              ネットテイク
                //ネットテイク日数、ネットテイク売上
                lineList.add(formatter.format(nipo.getEigyoDaysNtake()));
                lineList.add(formatter.format(nipo.getUriageNtake()));
            	//前年ネットテイク実績、前年比(ネットテイク売上)
            	lineList.add(formatter.format(nipo.getUriageNtakeZen()));
            	lineList.add(formatter.format(nipo.getZenHiUriageNtake()));
                //構成比(売上)
                lineList.add(formatter.format(nipo.getKouseiHiUriageNtake()));
                //ネットテイク件数
                lineList.add(formatter.format(nipo.getKyakusuNtake()));
            	//前年ネットテイク実績、前年比(ネットテイク件数)
            	lineList.add(formatter.format(nipo.getKyakusuNtakeZen()));
            	lineList.add(formatter.format(nipo.getZenHiKyakusuNtake()));
                //構成比(件数)
                lineList.add(formatter.format(nipo.getKouseiHiKyakusuNtake()));
                //客単価（ネットテイク）
                lineList.add(formatter.format(nipo.getKyakuTankaNtake()));
            	//前年客単価（ネットテイク）、前年比(客単価)
            	lineList.add(formatter.format(nipo.getZenKyakuTankaNtake()));
            	lineList.add(formatter.format(nipo.getZenHikyakuTankaNtake()));
                
//              ネット宅配
                //ネット宅配日数、ネット宅配売上
                lineList.add(formatter.format(nipo.getEigyoDaysNtaku()));
                lineList.add(formatter.format(nipo.getUriageNtaku()));
            	//前年ネット宅配実績、前年比(ネット宅配売上)
            	lineList.add(formatter.format(nipo.getUriageNtakuZen()));
            	lineList.add(formatter.format(nipo.getZenHiUriageNtaku()));
                //構成比(売上)
                lineList.add(formatter.format(nipo.getKouseiHiUriageNtaku()));
                //ネット宅配件数
                lineList.add(formatter.format(nipo.getKyakusuNtaku()));
            	//前年ネット宅配実績、前年比(ネット宅配件数)
            	lineList.add(formatter.format(nipo.getKyakusuNtakuZen()));
            	lineList.add(formatter.format(nipo.getZenHiKyakusuNtaku()));
                //構成比(件数)
                lineList.add(formatter.format(nipo.getKouseiHiKyakusuNtaku()));
                //客単価（ネット宅配）
                lineList.add(formatter.format(nipo.getKyakuTankaNtaku()));
            	//前年客単価（ネット宅配）、前年比(客単価)
            	lineList.add(formatter.format(nipo.getZenKyakuTankaNtaku()));
            	lineList.add(formatter.format(nipo.getZenHikyakuTankaNtaku()));

                outputList.add(lineList);
            }
        // 前年データ種別が前年同月営業日補正の時、データ部情報取得
        } else {
        	if(uriageList.isEmpty()) {
        		throw new NoResultException();
        	}
            if(BizReportConstants.FOREING_ON.equals(codCompany.getForeignFlg())) {
                //．日報共通DTO【条件部情報】換算済判断フラグがtrue(換算済)の場合下記の処理を行います。
            	if (parameterDto.isKansan()) {
    	        	//外貨値をCodCompany[管理会社情報].為替相場で円換算を行います。
    	        	BigDecimal decRate = codCompany.getRate();
    	            uriageList = NetorderNipoCommon.changeCurrencyUriageMise(uriageList, decRate, false);
    	            kyakusuList = NetorderNipoCommon.changeCurrencyKyakusuMise(uriageList, kyakusuList, decRate, false);
    	            
    	            uriageHoseiList = NetorderNipoCommon.changeCurrencyUriageMise(uriageHoseiList, decRate, false);
    	            kyakusuHoseiList = NetorderNipoCommon.changeCurrencyKyakusuMise(uriageHoseiList, kyakusuHoseiList, decRate, false);
    	            formatterUriage.setFormatPattern(BizReportConstants.FORMAT_JPY);
            	}
            }
            for (int j = 0; j < uriageList.size();j++) {
                List lineList = new ArrayList();
                TrnUriageNipoInfo uriList = (TrnUriageNipoInfo)uriageList.get(j);
                TrnUriageNipoInfo kyakuList =(TrnUriageNipoInfo)kyakusuList.get(j);

                BigDecimal uriHose = new BigDecimal(0);
                BigDecimal uriHoseZen = new BigDecimal(0);
                BigDecimal uriHoseZenHi = new BigDecimal(0.00);
                BigDecimal kyaHose = new BigDecimal(0);
                BigDecimal kyaHoseZen = new BigDecimal(0);
                BigDecimal kyaHoseZenHi = new BigDecimal(0.00);
                BigDecimal tanHose = new BigDecimal(0);
                BigDecimal tanHoseZen = new BigDecimal(0);
                BigDecimal tanHoseZenHi = new BigDecimal(0.00);
                
                BigDecimal uriHoseNsum = new BigDecimal(0);
                BigDecimal uriHoseNsumZen = new BigDecimal(0);
                BigDecimal uriHoseNsumZenhi = new BigDecimal(0.00);
                BigDecimal kyaHoseNsum = new BigDecimal(0);
                BigDecimal kyaHoseNsumZen = new BigDecimal(0);
                BigDecimal kyaHoseNsumZenhi = new BigDecimal(0.00);
                BigDecimal tanHoseNsum = new BigDecimal(0);
                BigDecimal tanHoseNsumZen = new BigDecimal(0);
                BigDecimal tanHoseNsumZenHi = new BigDecimal(0.00);
                
                BigDecimal uriHoseNtake = new BigDecimal(0);
                BigDecimal uriHoseNtakeZen = new BigDecimal(0);
                BigDecimal uriHoseNtakeZenhi = new BigDecimal(0.00);
                BigDecimal kyaHoseNtake = new BigDecimal(0);
                BigDecimal kyaHoseNtakeZen = new BigDecimal(0);
                BigDecimal kyaHoseNtakeZenhi = new BigDecimal(0.00);
                BigDecimal tanHoseNtake = new BigDecimal(0);
                BigDecimal tanHoseNtakeZen = new BigDecimal(0);
                BigDecimal tanHoseNtakeZenHi = new BigDecimal(0.00);
                
                BigDecimal uriHoseNtaku = new BigDecimal(0);
                BigDecimal uriHoseNtakuZen = new BigDecimal(0);
                BigDecimal uriHoseNtakuZenhi = new BigDecimal(0.00);
                BigDecimal kyaHoseNtaku = new BigDecimal(0);
                BigDecimal kyaHoseNtakuZen = new BigDecimal(0);
                BigDecimal kyaHoseNtakuZenhi = new BigDecimal(0.00);
                BigDecimal tanHoseNtaku = new BigDecimal(0);
                BigDecimal tanHoseNtakuZen = new BigDecimal(0);
                BigDecimal tanHoseNtakuZenHi = new BigDecimal(0.00);

                for ( int i = 0; i < uriageHoseiList.size(); i++ ) {
                    TrnUriageNipoInfo uriage = (TrnUriageNipoInfo)uriageHoseiList.get(i);
                    TrnUriageNipoInfo kyakus = (TrnUriageNipoInfo)kyakusuHoseiList.get(i);

                    if (uriList.getMiseCd().equals(uriage.getMiseCd()) 
                            && trim(uriList.getSibuCd()).equals(trim(uriage.getSibuCd()))) 
                    {
                        uriHose = uriage.getUriage();
                        uriHoseZen = uriage.getUriageZen();
                        uriHoseZenHi = uriage.getZenHiUri();
                        kyaHose = kyakus.getKyakusu();
                        kyaHoseZen = kyakus.getKyakusuZen();
                        kyaHoseZenHi = kyakus.getZenHiKyaku();
                        tanHose = kyakus.getTanka();
                        tanHoseZen = kyakus.getTankaZen();
                        tanHoseZenHi = kyakus.getZenHiTanka();
                        
                        uriHoseNsum = uriage.getUriageNsum();
                        uriHoseNsumZen = uriage.getUriageNsumZen();
                        uriHoseNsumZenhi = uriage.getZenHiUriageNsum();
                        kyaHoseNsum = kyakus.getKyakusuNsum();
                        kyaHoseNsumZen = kyakus.getKyakusuNsumZen();
                        kyaHoseNsumZenhi = kyakus.getZenHiKyakusuNsum();
                        tanHoseNsum = kyakus.getKyakuTankaNsum();
                        tanHoseNsumZen = kyakus.getZenKyakuTankaNsum();
                        tanHoseNsumZenHi = kyakus.getZenHikyakuTankaNsum();
                        
                        uriHoseNtake = uriage.getUriageNtake();
                        uriHoseNtakeZen = uriage.getUriageNtakeZen();
                        uriHoseNtakeZenhi = uriage.getZenHiUriageNtake();
                        kyaHoseNtake = kyakus.getKyakusuNtake();
                        kyaHoseNtakeZen = kyakus.getKyakusuNtakeZen();
                        kyaHoseNtakeZenhi = kyakus.getZenHiKyakusuNtake();
                        tanHoseNtake = kyakus.getKyakuTankaNtake();
                        tanHoseNtakeZen = kyakus.getZenKyakuTankaNtake();
                        tanHoseNtakeZenHi = kyakus.getZenHikyakuTankaNtake();
                        
                        uriHoseNtaku = uriage.getUriageNtaku();
                        uriHoseNtakuZen = uriage.getUriageNtakuZen();
                        uriHoseNtakuZenhi = uriage.getZenHiUriageNtaku();
                        kyaHoseNtaku = kyakus.getKyakusuNtaku();
                        kyaHoseNtakuZen = kyakus.getKyakusuNtakuZen();
                        kyaHoseNtakuZenhi = kyakus.getZenHiKyakusuNtaku();
                        tanHoseNtaku = kyakus.getKyakuTankaNtaku();
                        tanHoseNtakuZen = kyakus.getZenKyakuTankaNtaku();
                        tanHoseNtakuZenHi = kyakus.getZenHikyakuTankaNtaku();
                        break;
                    }
                }
                
                // 集計区分で『SV指定(担当店一覧)』が選択された場合 20081209追加
                if(ShukeiKbn.SV_CD.equals(parameterDto.getShukeiKbnCd())) {
                    // 支部コード・名称設定
                    lineList.add(NetorderNipoCommon.setEmpty(uriList.getSibuCd()));
                    lineList.add(NetorderNipoCommon.setEmpty(uriList.getSibuName()));
                    
                    // ブロックコード・名称
                    if (!(NipoRefConstants.CSS_TR_CLASS.equals(uriList.getRClass())
                        || NipoRefConstants.CSS_TR_CLASS_SLAREA.equals(uriList.getRClass()))){
                        lineList.add(NetorderNipoConstants.EMPTY);
                        lineList.add(NetorderNipoCommon.setEmpty(uriList.getBlockName())); 
                    } else if (parameterDto.isMosCompany()) {
                        lineList.add(NetorderNipoCommon.setEmpty(uriList.getBlockCd()));
                        lineList.add(NetorderNipoCommon.setEmpty(uriList.getBlockName()));
                    }                   
                }
                // 集計区分が直営店を含まないの場合
                else if(ShukeiKbn.OUT_RC.equals(parameterDto.getShukeiKbnCd())) {
                    // 集計区分が支部計以外の場合、支部コード・名称設定
                    if (!TaishoJoken.CODE_SIBU.equals(NetorderNipoCommon.getSumKbn(parameterDto.getClassName()))) {
                        lineList.add(NetorderNipoCommon.setEmpty(uriList.getSibuCd()));
                        lineList.add(NetorderNipoCommon.setEmpty(uriList.getSibuName()));
                    }                    
                    // ブロックコード・名称
                    if (!(NipoRefConstants.CSS_TR_CLASS.equals(uriList.getRClass())
                        || NipoRefConstants.CSS_TR_CLASS_SLAREA.equals(uriList.getRClass()))){
                        lineList.add(NetorderNipoConstants.EMPTY);
                        lineList.add(NetorderNipoCommon.setEmpty(uriList.getBlockName())); 
                    } else if (parameterDto.isMosCompany()) {
                        lineList.add(NetorderNipoCommon.setEmpty(uriList.getBlockCd()));
                        lineList.add(NetorderNipoCommon.setEmpty(uriList.getBlockName()));
                    }                   
                }
                // 集計区分が直営店を含む且つ集計区分が支部計以外の場合、支部名称設定
                else if (!TaishoJoken.CODE_SIBU.equals(NetorderNipoCommon.getSumKbn(parameterDto.getClassName()))) {
                    lineList.add(NetorderNipoCommon.setEmpty(uriList.getSibuName()));
                }
                // 店コード・名称
                if (NipoRefConstants.CSS_TR_CLASS.equals(uriList.getRClass())) {
                    lineList.add(NetorderNipoCommon.setEmpty(uriList.getMiseCd()));
                    lineList.add(NetorderNipoCommon.setEmpty(uriList.getMiseNameKj()));                    
                } else if (parameterDto.isMosCompany()){
                    lineList.add(NetorderNipoConstants.EMPTY);
                    lineList.add(NetorderNipoConstants.EMPTY);
                }
                // 休業
                lineList.add(uriList.getOpenKbnJpn());
                // 期日指定日報の場合天候、以外の場合営業日数設定
                if(TaishoKikan.DAY1.equals(parameterDto.getTaishoKikanCd())) {
                    lineList.add(uriList.getTenkoKbnJpn());
                } else {
                    lineList.add(formatter.format(uriList.getEigyoDays()));
                }             
                // 売上、予算、達成率
                lineList.add(formatterUriage.format(uriList.getUriage(), true));
                lineList.add(formatter.format(uriList.getYosan(), true));
                lineList.add(formatter.format(uriList.getTassei(), true));
                // 前年比対象売上、前年比対象前年売上、前年比(売上)
                lineList.add(formatterUriage.format(uriHose, true));                
                lineList.add(formatterUriage.format(uriHoseZen, true));
                lineList.add(formatter.format(uriHoseZenHi, true));
                // 客数、前年比対象客数、前年比対象前年客数、前年比(客数)
                lineList.add(formatter.format(kyakuList.getKyakusu(), true));
                lineList.add(formatter.format(kyaHose,true));
                lineList.add(formatter.format(kyaHoseZen, true));
                lineList.add(formatter.format(kyaHoseZenHi, true));
                // 客単価、前年比対象客単価、前年比対象前年客単価、前年比(客単価)
                lineList.add(formatterUriage.format(kyakuList.getTanka(), true));
                lineList.add(formatterUriage.format(tanHose, true));
                lineList.add(formatterUriage.format(tanHoseZen, true));
                lineList.add(formatter.format(tanHoseZenHi, true));
                // 前年休業
                lineList.add(uriList.getOpenKbnZenJpn());
                // 期日指定日報の場合前年天候、以外の場合前年営業日数設定
                if(TaishoKikan.DAY1.equals(parameterDto.getTaishoKikanCd())) {
                    lineList.add(uriList.getTenkoKbnZenJpn());
                } else {
                    lineList.add(formatter.format(uriList.getEigyoDaysZen()));
                }
                
//              ネット注文
                //ネット注文日数、ネット注文売上
                lineList.add(formatter.format(uriList.getEigyoDaysNsum()));
                lineList.add(formatter.format(uriList.getUriageNsum()));
                //前年同月営業日補正の場合、前年比対象・ネット注文売上、前年ネット注文実績設定
            	//前年比対象ネット注文売上、前年比対象前年ネット注文実績、前年比(ネット注文売上)
            	lineList.add(formatter.format(uriHoseNsum));
            	lineList.add(formatter.format(uriHoseNsumZen));
            	lineList.add(formatter.format(uriHoseNsumZenhi));
                //構成比(売上)
                lineList.add(formatter.format(uriList.getKouseiHiUriageNsum()));
                //ネット注文件数
                lineList.add(formatter.format(kyakuList.getKyakusuNsum()));
            	//前年比対象ネット注文件数、前年比対象前年ネット注文件数、前年比(ネット注文件数)
            	lineList.add(formatter.format(kyaHoseNsum));
            	lineList.add(formatter.format(kyaHoseNsumZen));
            	lineList.add(formatter.format(kyaHoseNsumZenhi));
                //構成比(件数)
                lineList.add(formatter.format(kyakuList.getKouseiHiKyakusuNsum()));
                //客単価（ネット注文）
                lineList.add(formatter.format(kyakuList.getKyakuTankaNsum()));
                //前年同月営業日補正の場合、前年比対象・対象客単価（ネット注文）、対象前年客単価（ネット注文）設定
            	//前年比対象客単価（ネット注文）、前年比対象前年客単価（ネット注文）、前年比(客単価)
            	lineList.add(formatter.format(tanHoseNsum));
            	lineList.add(formatter.format(tanHoseNsumZen));
            	lineList.add(formatter.format(tanHoseNsumZenHi));
            	
//              ネットテイク
                //ネットテイク日数、ネットテイク売上
                lineList.add(formatter.format(uriList.getEigyoDaysNtake()));
                lineList.add(formatter.format(uriList.getUriageNtake()));
                //前年同月営業日補正の場合、前年比対象・ネットテイク売上、前年ネットテイク実績設定
            	//前年比対象ネットテイク売上、前年比対象前年ネットテイク実績、前年比(ネットテイク売上)
            	lineList.add(formatter.format(uriHoseNtake));
            	lineList.add(formatter.format(uriHoseNtakeZen));
            	lineList.add(formatter.format(uriHoseNtakeZenhi));
                //構成比(売上)
                lineList.add(formatter.format(uriList.getKouseiHiUriageNtake()));
                //ネットテイク件数
                lineList.add(formatter.format(kyakuList.getKyakusuNtake()));
            	//前年比対象ネットテイク件数、前年比対象前年ネットテイク件数、前年比(ネットテイク件数)
            	lineList.add(formatter.format(kyaHoseNtake));
            	lineList.add(formatter.format(kyaHoseNtakeZen));
            	lineList.add(formatter.format(kyaHoseNtakeZenhi));
                //構成比(件数)
                lineList.add(formatter.format(kyakuList.getKouseiHiKyakusuNtake()));
                //客単価（ネットテイク）
                lineList.add(formatter.format(kyakuList.getKyakuTankaNtake()));
                //前年同月営業日補正の場合、前年比対象・対象客単価（ネットテイク）、対象前年客単価（ネットテイク）設定
            	//前年比対象客単価（ネットテイク）、前年比対象前年客単価（ネットテイク）、前年比(客単価)
            	lineList.add(formatter.format(tanHoseNtake));
            	lineList.add(formatter.format(tanHoseNtakeZen));
            	lineList.add(formatter.format(tanHoseNtakeZenHi));
                
//              ネット宅配
                //ネット宅配日数、ネット宅配売上
                lineList.add(formatter.format(uriList.getEigyoDaysNtaku()));
                lineList.add(formatter.format(uriList.getUriageNtaku()));
                //前年同月営業日補正の場合、前年比対象・ネット宅配売上、前年ネット宅配実績設定
            	//前年比対象ネット宅配売上、前年比対象前年ネット宅配実績、前年比(ネット宅配売上)
            	lineList.add(formatter.format(uriHoseNtaku));
            	lineList.add(formatter.format(uriHoseNtakuZen));
            	lineList.add(formatter.format(uriHoseNtakuZenhi));
                //構成比(売上)
                lineList.add(formatter.format(uriList.getKouseiHiUriageNtaku()));
                //ネット宅配件数
                lineList.add(formatter.format(kyakuList.getKyakusuNtaku()));
            	//前年比対象ネット宅配件数、前年比対象前年ネット宅配件数、前年比(ネット宅配件数)
            	lineList.add(formatter.format(kyaHoseNtaku));
            	lineList.add(formatter.format(kyaHoseNtakuZen));
            	lineList.add(formatter.format(kyaHoseNtakuZenhi));
                //構成比(件数)
                lineList.add(formatter.format(kyakuList.getKouseiHiKyakusuNtaku()));
                //客単価（ネット宅配）
                lineList.add(formatter.format(kyakuList.getKyakuTankaNtaku()));
                //前年同月営業日補正の場合、前年比対象・対象客単価（ネット宅配）、対象前年客単価（ネット宅配）設定
            	//前年比対象客単価（ネット宅配）、前年比対象前年客単価（ネット宅配）、前年比(客単価)
            	lineList.add(formatter.format(tanHoseNtaku));
            	lineList.add(formatter.format(tanHoseNtakuZen));
            	lineList.add(formatter.format(tanHoseNtakuZenHi));
            	
                outputList.add(lineList);
            }
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
 
        // ３行目:集計区分、前年データ種別、リンク区分
        List head3rdList = new ArrayList();
        head3rdList.add(NetorderNipoConstants.CSV_HD_SHUKEI_KBN);
        //SV指定の場合
        if(ShukeiKbn.SV_CD.equals(parameterDto.getShukeiKbnCd())){
            head3rdList.add(NetorderNipoConstants.CSV_HD_SV);
        }else{
            head3rdList.add(ShukeiKbn.getName(parameterDto.getShukeiKbnCd()));    
        }

        head3rdList.add(NetorderNipoConstants.EMPTY);
        head3rdList.add(NetorderNipoConstants.CSV_HD_ZEN_DATA_SHU);
        head3rdList.add(NipoZennenDataShubetu.getName
            (zenDataShu, parameterDto.getBirdUserInfo().getMstUser().getUserTypeCd()));
        head3rdList.add(NetorderNipoConstants.EMPTY);

        StringBuffer linkInfo = new StringBuffer();

        if(ShukeiKbn.SV_CD.equals(parameterDto.getShukeiKbnCd())){
            //SV指定の場合、SVコード、SV名称を表示 
            linkInfo.append(NetorderNipoConstants.CSV_HD_SV_TANTO);
            linkInfo.append(NetorderNipoCommon.setEmpty(parameterDto.getHyojiSvName()));
            
        } else if (TaishoJoken.CODE_SIBU.equals(NetorderNipoCommon.getSumKbn(parameterDto.getClassName()))) {
            linkInfo.append(NetorderNipoConstants.CSV_HD_SIBU);
            linkInfo.append(parameterDto.getSibuName());
        } else if (TaishoJoken.CODE_SLAREA.equals(NetorderNipoCommon.getSumKbn(parameterDto.getClassName()))) {
            linkInfo.append(NetorderNipoConstants.CSV_HD_SLAREA);
            linkInfo.append(parameterDto.getSibuName());
        } else if (TaishoJoken.CODE_JIGYOU.equals(NetorderNipoCommon.getSumKbn(parameterDto.getClassName()))) {
            linkInfo.append(NetorderNipoConstants.CSV_HD_JIGYOU);
            linkInfo.append(parameterDto.getSibuName());
        } else if (TaishoJoken.CODE_HONBU.equals(NetorderNipoCommon.getSumKbn(parameterDto.getClassName()))) {
            linkInfo.append(NetorderNipoConstants.CSV_HD_HONBU);
            linkInfo.append(parameterDto.getSibuName());
        } else if (TaishoJoken.CODE_SEGMENT.equals(NetorderNipoCommon.getSumKbn(parameterDto.getClassName()))) {
            linkInfo.append(NetorderNipoConstants.CSV_HD_GYOTAI);
            linkInfo.append(parameterDto.getSibuName());
        } else if (TaishoJoken.CODE_ALL.equals(NetorderNipoCommon.getSumKbn(parameterDto.getClassName()))) {
            linkInfo.append(NetorderNipoConstants.CSV_HD_ALL);
        } 
        
        head3rdList.add(linkInfo.toString());
        //海外の会社の場合は表示通貨を設定します。
        if(BizReportConstants.FOREING_ON.equals(parameterDto.getCodCompany().getForeignFlg())) {
    		head3rdList.add("");
            //．日報共通DTO【条件部情報】換算済判断フラグがtrue(換算済)の場合下記の処理を行います。
       		head3rdList.add("表示通貨："+(parameterDto.isKansan()?"日本円":parameterDto.getCodCompany().getCurrencyName()));
        }
        // ４行目
        List head4thList = new ArrayList();
        
        // ５行目
        List head5thList = new ArrayList();
        
        // ６行目
        List head6thList = new ArrayList();
        
        // 集計区分で『SV指定(担当店一覧)』が選択された場合
        if (ShukeiKbn.SV_CD.equals(parameterDto.getShukeiKbnCd())) {
            head6thList.add(NetorderNipoConstants.CSV_DT_SIBU_CD);
            head6thList.add(NetorderNipoConstants.CSV_DT_SIBU_NAME);
            // 会社コードがモスフードサービの場合、ブロックコード・名称設定
            if (parameterDto.isMosCompany()) {
                head6thList.add(NetorderNipoConstants.CSV_DT_BLOCK_CD);
                head6thList.add(NetorderNipoConstants.CSV_DT_BLOCK_NAME);            
            }
        }
        // 合計区分が支部以外の場合
        else if (!TaishoJoken.CODE_SIBU.equals(NetorderNipoCommon.getSumKbn(parameterDto.getClassName()))) {
            // 集計区分が直営店を含むの場合
            if (ShukeiKbn.IN_RC.equals(parameterDto.getShukeiKbnCd())) {
                head6thList.add(NetorderNipoConstants.CSV_DT_SHUKEI_KBN);
            // 集計区分が直営店を含まないの場合
            } else {
                head6thList.add(NetorderNipoConstants.CSV_DT_SIBU_CD);
                head6thList.add(NetorderNipoConstants.CSV_DT_SIBU_NAME);
            }
        }
        // 会社コードがモスフードサービ且つ集計区分が直営店を含まないの場合、ブロックコード・名称設定
        if (parameterDto.isMosCompany()
                && ShukeiKbn.OUT_RC.equals(parameterDto.getShukeiKbnCd())) {
            head6thList.add(NetorderNipoConstants.CSV_DT_BLOCK_CD);
            head6thList.add(NetorderNipoConstants.CSV_DT_BLOCK_NAME);            
        }
        // 店コード・名称
        head6thList.add(NetorderNipoConstants.CSV_DT_MISE_CD);
        head6thList.add(NetorderNipoConstants.CSV_DT_MISE_NAME);
        // 店舗種別が全店の場合、店種出力
        if (TenpoShubetu.CODE_ALL.equals(parameterDto.getTenpoShubetuCd())) {
            head6thList.add(NetorderNipoConstants.CSV_DT_TENSHU);
        }
        // 休業
        head6thList.add(NetorderNipoConstants.CSV_DT_KYUGYO);
        // 期日指定日報の場合天候、以外の場合営業日数設定
        head6thList.add(TaishoKikan.DAY1.equals(parameterDto.getTaishoKikanCd()) ?
        		NetorderNipoConstants.CSV_DT_TENKOU : NetorderNipoConstants.CSV_DT_EIGYO_DAYS);        
        // 売上、予算、達成率
        head6thList.add(NetorderNipoConstants.CSV_DT_URIAGE);
        head6thList.add(NetorderNipoConstants.CSV_DT_YOSAN);
        head6thList.add(NetorderNipoConstants.CSV_DT_TASSEI);
        // 前年同月営業日補正の場合、前年比対象・売上、前年売上設定
        if (parameterDto.isSelectHosei()) {
            head6thList.add(NetorderNipoConstants.CSV_DT_HOSEI_URIAGE);
            head6thList.add(NetorderNipoConstants.CSV_DT_HOSEI_URIAGE_ZEN);
        // 前年同月営業日補正以外の場合、前年売上設定
        } else {            
            head6thList.add(NetorderNipoConstants.CSV_DT_URIAGE_ZEN);
        }
        // 前年比(売上)、客数
        head6thList.add(NetorderNipoConstants.CSV_DT_ZEN_HI_URIAGE);
        head6thList.add(NetorderNipoConstants.CSV_DT_KYAKUSU);
        // 前年同月営業日補正の場合、前年比対象・客数、前年客数設定       
        if (parameterDto.isSelectHosei()) {
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
        if (parameterDto.isSelectHosei()) {
            head6thList.add(NetorderNipoConstants.CSV_DT_HOSEI_KYAKU_TANKA);
            head6thList.add(NetorderNipoConstants.CSV_DT_HOSEI_KYAKU_TANKA_ZEN);
        // 前年同月営業日補正以外の場合、前年客単価設定
        } else {            
            head6thList.add(NetorderNipoConstants.CSV_DT_KYAKU_TANKA_ZEN);
        }
        // 前年比(客単価)、前年休業
        head6thList.add(NetorderNipoConstants.CSV_DT_ZEN_HI_KYAKU_TANKA);
        // 前年同曜日以外の場合
        head6thList.add(NetorderNipoConstants.CSV_DT_ZEN_KYUGYO);
        // 期日指定日報の場合前年天候、以外の場合前年営業日数設定
        head6thList.add(TaishoKikan.DAY1.equals(parameterDto.getTaishoKikanCd()) ?
        		NetorderNipoConstants.CSV_DT_ZEN_TENKOU : NetorderNipoConstants.CSV_DT_ZEN_EIGYO_DAYS);
        
//      ネット注文
        //ネット注文日数、ネット注文売上
        int index1 = head6thList.toArray().length;
        for(int i=0;i<index1;i++){
        	head5thList.add("");
        }
        head5thList.add("ネット注文(合算)");
        head6thList.add(NetorderNipoConstants.CSV_DT_EIGYO_DAYS_NSUM);
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
        //ネットテイク日数、ネットテイク売上
        int index2 = head6thList.toArray().length;
        for (int i=index1+1;i<index2;i++)
        {
        	head5thList.add("");
        }
        head5thList.add("ネット注文(テイク)");
        head6thList.add(NetorderNipoConstants.CSV_DT_EIGYO_DAYS_NTAKE);
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
        //ネット宅配日数、ネットテイク売上
        int index3 = head6thList.toArray().length;
        for (int i=index2+1;i<index3;i++)
        {
        	head5thList.add("");
        }
        head5thList.add("ネット注文(宅配)");
        head6thList.add(NetorderNipoConstants.CSV_DT_EIGYO_DAYS_NTAKU);
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
     * 店別売上予算情報取得ロジックを取得する
     * @return UriageNipoInfoLogic 店別売上予算情報取得ロジック
     */
    public UriageNipoInfoLogic getUriageNipoInfoLogic() {
        return this.uriageNipoInfoLogic;
    }

    /**
     * 店別売上予算情報取得ロジックを設定する
     * @param uriageNipoInfoLogic 店別売上予算情報取得ロジック
     */
    public void setUriageNipoInfoLogic(UriageNipoInfoLogic uriageNipoInfoLogic) {
        this.uriageNipoInfoLogic = uriageNipoInfoLogic;
    }

    /**
     * 表示対象の支部一覧取得ロジックを取得する
     * @return TargetSibuInfoLogic 表示対象の支部一覧取得ロジック
     */
    public TargetSibuInfoLogic getTargetSibuInfoLogic() {
        return targetSibuInfoLogic;
    }
    
    /**
     * 表示対象の支部一覧取得ロジックを設定する
     * @param targetSibuInfoLogic 表示対象の支部一覧取得ロジック
     */
    public void setTargetSibuInfoLogic(TargetSibuInfoLogic targetSibuInfoLogic) {
        this.targetSibuInfoLogic = targetSibuInfoLogic;
    }

    /**
     * 表示支部取得ロジックを取得する
     * @return ViewSibuInfoLogic 表示支部取得ロジック
     */
    public ViewSibuInfoLogic getViewSibuInfoLogic() {
        return this.viewSibuInfoLogic;
    }

    /**
     * 表示支部取得ロジックを設定する
     * @param viewSibuInfoLogic 表示支部取得ロジック
     */
    public void setViewSibuInfoLogic( ViewSibuInfoLogic viewSibuInfoLogic) {
        this.viewSibuInfoLogic = viewSibuInfoLogic;
    }    
    
    private String trim(String value) {
        if (value == null) {
            return "";
        }
        return value.trim();
    }
}