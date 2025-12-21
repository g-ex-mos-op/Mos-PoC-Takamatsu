package jp.co.isid.mos.bird.bizreport.groupeigyoniporef.logic.impl;

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
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.common.EigyoNipoCommon;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.common.EigyoNipoConstants;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.entity.MstSibuInfo;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.entity.TrnUriageNipoInfo;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.logic.GroupEigyoNipoSearchLogic;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.logic.TargetSibuInfoLogic;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.logic.UriageNipoInfoLogic;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.logic.ViewSibuInfoLogic;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * 店舗別営業日報CSVダウンロードロジック
 *
 * @author   xkhata
 * @modifier xjung  2006/10/03 総合営業日報タブ連携対応
 * @modifier xkinu 2013/01/24 海外売上集信対応
 */
public class GroupEigyoNipoMiseCsvOutputLogicImpl implements CsvOutputLogic {
    /** 店舗別営業日報CSVダウンロードロジックID */
    public static final String LOGIC_ID = "BBR001L09";

    /** 検索条件取得ロジック */
    private GroupEigyoNipoSearchLogic groupEigyoNipoSearchLogic;

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
     *
     * @modifier xkinu 2013/01/24 海外売上集信対応
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

/* 20081209追加 SV対応 start ----------------------------*/
        String sibuCd = "";
        String kbnCd  = "";
        if(!parameterDto.isSvFlg()){
            // 支部コードの入力チェック
            if (EigyoNipoCommon.isNull(parameterDto.getSibuCd())) {
                throw new NotNullException(EigyoNipoConstants.MSG_SIBU_CD);
            }
            sibuCd = parameterDto.getSibuCd();
            kbnCd  = EigyoNipoCommon.getSumKbn(parameterDto.getClassName());
        }

        // 検索条件取得
        Map paramMap = getGroupEigyoNipoSearchLogic().getSearchData(parameterDto);
        paramMap.put(EigyoNipoConstants.MAP_SIBU_CD, sibuCd);
        paramMap.put(EigyoNipoConstants.MAP_KBN_CD , kbnCd);
//        paramMap.put(EigyoNipoConstants.MAP_SIBU_CD, parameterDto.getSibuCd());
//        paramMap.put(EigyoNipoConstants.MAP_KBN_CD, EigyoNipoCommon.getSumKbn(parameterDto.getClassName()));
/* 20081209追加 End ------------------------------------*/

        List resultList = new ArrayList();
        List uriageList = new ArrayList();
        List kyakusuList = new ArrayList();
        List uriageHoseiList = new ArrayList();
        List kyakusuHoseiList = new ArrayList();

        // リンク区分が支部の場合
        if (parameterDto.isSvFlg()
                || TaishoJoken.CODE_SIBU.equals(EigyoNipoCommon.getSumKbn(parameterDto.getClassName()))) {
            Map resultMap = getUriageNipoInfoLogic().execute(paramMap);
            resultList = (List) resultMap.get(EigyoNipoConstants.MAP_RESULT);
            uriageList = (List) resultMap.get(EigyoNipoConstants.MAP_URIAGE_LIST);
            kyakusuList = (List) resultMap.get(EigyoNipoConstants.MAP_KYAKUSU_LIST);
            // 前年データ種別が前年同月営業日補正の時
            if (parameterDto.isSelectHosei()) {
                uriageHoseiList = (List) resultMap.get(EigyoNipoConstants.MAP_RST_URIAGE_HOSE);
                kyakusuHoseiList = (List) resultMap.get(EigyoNipoConstants.MAP_RST_KYAKUSU_HOSE);
            }
        // リンク区分が支部以外の場合
        } else {
            paramMap.put(EigyoNipoConstants.MAP_KBN_CD, EigyoNipoCommon.getSumKbn(parameterDto.getClassName()));
            List sibuList = getTargetSibuInfoLogic().execute(paramMap);
            List dispSibuList = getViewSibuInfoLogic().execute(paramMap);
            List targetSibuList = new ArrayList();
            for (int j = 0; j < sibuList.size(); j++) {
                MstSibuInfo sibu = (MstSibuInfo)sibuList.get(j);
                for (int i = 0; i < dispSibuList.size(); i++) {
                    MstSibuInfo dispSibu = (MstSibuInfo)dispSibuList.get(i);
                    if ((sibu.getCompanyCd().equals( dispSibu.getCompanyCd()))
                        && ((sibu.getSibuCd().trim()).equals((dispSibu.getSibuCd().trim()))))  {
                        if (dispSibu.getDispKbn().equals(EigyoNipoConstants.DISP)) {
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
                paramMap.put(EigyoNipoConstants.MAP_SIBU_CD, sibu.getSibuCd().trim());
                paramMap.put(EigyoNipoConstants.MAP_KBN_CD, TaishoJoken.CODE_SIBU);
                try {
	                Map uriageNipoMap = getUriageNipoInfoLogic().execute(paramMap);
	                uriageList.addAll((List)uriageNipoMap.get(EigyoNipoConstants.MAP_URIAGE_LIST));
	                kyakusuList.addAll((List)uriageNipoMap.get(EigyoNipoConstants.MAP_KYAKUSU_LIST));
	                // 前年データ種別が前年同月営業日補正の時
	                if (parameterDto.isSelectHosei()) {
	                    uriageHoseiList.addAll((List)uriageNipoMap.get(EigyoNipoConstants.MAP_RST_URIAGE_HOSE));
	                    kyakusuHoseiList.addAll((List)uriageNipoMap.get(EigyoNipoConstants.MAP_RST_KYAKUSU_HOSE));
	                } else {
	                    resultList.addAll((List)uriageNipoMap.get(EigyoNipoConstants.MAP_RESULT));
	                }
                }
                catch (NoResultException noResult) {
                	//処理続行
                }
            }
        }
        //対象の会社が海外の会社でかつ、
        //円換算済のデータでのダウンロード要求だった場合、円換算処理を行います。
        //(2013/01/25追加:海外売上集信対応)
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
    	        	resultList = EigyoNipoCommon.changeCurrency(resultList, decRate, false);
    	            formatterUriage.setFormatPattern(BizReportConstants.FORMAT_JPY);
            	}
            }
            for (int i = 0; i < resultList.size(); i++) {
                List lineList = new ArrayList();
                TrnUriageNipoInfo nipo = (TrnUriageNipoInfo)resultList.get(i);

                // 集計区分で『SV指定(担当店一覧)』が選択された場合 20081209追加
                if(ShukeiKbn.SV_CD.equals(parameterDto.getShukeiKbnCd())) {
                    // 支部コード・名称設定
                    lineList.add(EigyoNipoCommon.setEmpty(nipo.getSibuCd()));
                    lineList.add(EigyoNipoCommon.setEmpty(nipo.getSibuName()));
                    // ブロックコード・名称
                    if (!(NipoRefConstants.CSS_TR_CLASS.equals(nipo.getRClass())
                        || NipoRefConstants.CSS_TR_CLASS_SLAREA.equals(nipo.getRClass()))){
                        lineList.add(EigyoNipoConstants.EMPTY);
                        lineList.add(EigyoNipoCommon.setEmpty(nipo.getBlockName()));
                    } else if (parameterDto.isMosCompany()) {
                        lineList.add(EigyoNipoCommon.setEmpty(nipo.getBlockCd()));
                        lineList.add(EigyoNipoCommon.setEmpty(nipo.getBlockName()));
                    }
                }
                // 集計区分が直営店を含まないの場合
                else if(ShukeiKbn.OUT_RC.equals(parameterDto.getShukeiKbnCd())) {
                    // 集計区分が支部計以外の場合、支部コード・名称設定
                    if (!TaishoJoken.CODE_SIBU.equals(EigyoNipoCommon.getSumKbn(parameterDto.getClassName()))) {
                        lineList.add(EigyoNipoCommon.setEmpty(nipo.getSibuCd()));
                        lineList.add(EigyoNipoCommon.setEmpty(nipo.getSibuName()));
                    }
                    // ブロックコード・名称
                    if (!(NipoRefConstants.CSS_TR_CLASS.equals(nipo.getRClass())
                        || NipoRefConstants.CSS_TR_CLASS_SLAREA.equals(nipo.getRClass()))){
                        lineList.add(EigyoNipoConstants.EMPTY);
                        lineList.add(EigyoNipoCommon.setEmpty(nipo.getBlockName()));
                    } else if (parameterDto.isMosCompany()) {
                        lineList.add(EigyoNipoCommon.setEmpty(nipo.getBlockCd()));
                        lineList.add(EigyoNipoCommon.setEmpty(nipo.getBlockName()));
                    }
                }
                // 集計区分が直営店を含む且つ集計区分が支部計以外の場合、支部名称設定
                else if (!TaishoJoken.CODE_SIBU.equals(EigyoNipoCommon.getSumKbn(parameterDto.getClassName()))) {
                    lineList.add(EigyoNipoCommon.setEmpty(nipo.getSibuName()));
                }
                // 店コード・名称
                if (NipoRefConstants.CSS_TR_CLASS.equals(nipo.getRClass())) {
                    lineList.add(EigyoNipoCommon.setEmpty(nipo.getMiseCd()));
                    lineList.add(EigyoNipoCommon.setEmpty(nipo.getMiseNameKj()));
                } else if (parameterDto.isMosCompany()){
                    lineList.add(EigyoNipoConstants.EMPTY);
                    lineList.add(EigyoNipoConstants.EMPTY);
                }
                // 店舗種別が全店の場合、店種出力
                if (TenpoShubetu.CODE_ALL.equals(parameterDto.getTenpoShubetuCd())) {
                    lineList.add(EigyoNipoCommon.setEmpty(nipo.getZenKbn()));
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
                    // 売上、値引、値引後売上、予算、達成率、値引後達成率、前年実績、値引後前年実績、前年比(売上)、値引後前年比
                    lineList.add("");
	                //begin add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
	                if (parameterDto.isAllowedDispNebiki()) {
	                    lineList.add("");
	                    lineList.add("");
	                }
	                //end add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
                    lineList.add(formatter.format(nipo.getYosan(),true));
                    lineList.add("");
	                //begin add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
	                if (parameterDto.isAllowedDispNebiki()) {
	                    lineList.add("");
	                }
	                //end add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
                    lineList.add("");
                    //begin add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
                    if (parameterDto.isAllowedDispNebiki()) {
                        lineList.add("");
                    }
                    //end add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
                    lineList.add("");
                    //begin add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
                    if (parameterDto.isAllowedDispNebiki()) {
                        lineList.add("");
                    }
                    //end add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
                    // 客数、前年客数、前年比(客数)
                    lineList.add("");
                    lineList.add("");
                    lineList.add("");
                    // 単価、前年単価、前年比(単価)、前年休業
                    lineList.add("");
                    lineList.add("");

                }
                else {
	                // 売上、値引、値引後売上、予算、達成率、値引後達成率、前年実績、値引後前年実績、前年比(売上)、値引後前年比
	                lineList.add(formatterUriage.format(nipo.getUriage(),true));
	                //begin add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
	                if (parameterDto.isAllowedDispNebiki()) {
	                	//値引
	                	lineList.add(formatter.format(nipo.getNebiki(), true));
	                	//値引後売上
	                    lineList.add(formatterUriage.format(nipo.getUriageAfterNebiki(), true));
	                }
	                //end add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
	                lineList.add(formatter.format(nipo.getYosan(),true));
	                lineList.add(formatter.format(nipo.getTassei(),true));
	                //begin add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
	                if (parameterDto.isAllowedDispNebiki()) {
	                    lineList.add(formatter.format(nipo.getTasseiAfterNebiki(), true));
	                }
	                //end add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
	                lineList.add(formatterUriage.format(nipo.getUriageZen(),true));
                    //begin add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
                    if (parameterDto.isAllowedDispNebiki()) {
                        //値引後前年実績
                        lineList.add(formatterUriage.format(nipo.getUriageZenAfterNebiki(),true));
                    }
                    //end add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
	                lineList.add(formatter.format(nipo.getZenHiUri(),true));
                    //begin add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
                    if (parameterDto.isAllowedDispNebiki()) {
                        //値引後前年比
                        lineList.add(formatter.format(nipo.getZenHiUriAfterNebiki(),true));
                    }
                    //end add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
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
    	            uriageList = EigyoNipoCommon.changeCurrencyUriageMise(uriageList, decRate, false);
    	            kyakusuList = EigyoNipoCommon.changeCurrencyKyakusuMise(uriageList, kyakusuList, decRate, false);

    	            uriageHoseiList = EigyoNipoCommon.changeCurrencyUriageMise(uriageHoseiList, decRate, false);
    	            kyakusuHoseiList = EigyoNipoCommon.changeCurrencyKyakusuMise(uriageHoseiList, kyakusuHoseiList, decRate, false);
    	            formatterUriage.setFormatPattern(BizReportConstants.FORMAT_JPY);
            	}
            }
            for (int j = 0; j < uriageList.size();j++) {
                List lineList = new ArrayList();
                TrnUriageNipoInfo uriList = (TrnUriageNipoInfo)uriageList.get(j);
                TrnUriageNipoInfo kyakuList =(TrnUriageNipoInfo)kyakusuList.get(j);

                BigDecimal uriHose = new BigDecimal(0);
                BigDecimal uriHoseNebiki = new BigDecimal(0);   //add 2019/04/18 xou.zoubun 前年対応
                BigDecimal uriHoseAfterNebiki = new BigDecimal(0);   //add 2019/04/18 xou.zoubun 前年対応
                BigDecimal uriHoseZen = new BigDecimal(0);
                BigDecimal uriHoseZenNebiki = new BigDecimal(0);   //add 2019/04/18 xou.zoubun 前年対応
                BigDecimal uriHoseZenAfterNebiki = new BigDecimal(0);   //add 2019/04/18 xou.zoubun 前年対応
                BigDecimal kyaHose = new BigDecimal(0);
                BigDecimal kyaHoseZen = new BigDecimal(0);
                BigDecimal tanHose = new BigDecimal(0);
                BigDecimal tanHoseZen = new BigDecimal(0);

                for ( int i = 0; i < uriageHoseiList.size(); i++ ) {
                    TrnUriageNipoInfo uriage = (TrnUriageNipoInfo)uriageHoseiList.get(i);
                    TrnUriageNipoInfo kyakus = (TrnUriageNipoInfo)kyakusuHoseiList.get(i);

                    if (uriList.getMiseCd().equals(uriage.getMiseCd())
                            && trim(uriList.getSibuCd()).equals(trim(uriage.getSibuCd())))
                    {
                        uriHose = uriage.getUriage();
                        uriHoseNebiki = uriage.getNebiki();   //add 2019/04/18 xou.zoubun 前年対応
                        uriHoseAfterNebiki = uriage.getUriageAfterNebiki();   //add 2019/04/18 xou.zoubun 前年対応
                        uriHoseZen = uriage.getUriageZen();
                        uriHoseZenNebiki = uriage.getNebikiZen();   //add 2019/04/18 xou.zoubun 前年対応
                        uriHoseZenAfterNebiki = uriage.getUriageZenAfterNebiki();   //add 2019/04/18 xou.zoubun 前年対応
                        kyaHose = kyakus.getKyakusu();
                        kyaHoseZen = kyakus.getKyakusuZen();
                        tanHose = kyakus.getTanka();
                        tanHoseZen = kyakus.getTankaZen();
                        break;
                    }
                }

                // 集計区分で『SV指定(担当店一覧)』が選択された場合 20081209追加
                if(ShukeiKbn.SV_CD.equals(parameterDto.getShukeiKbnCd())) {
                    // 支部コード・名称設定
                    lineList.add(EigyoNipoCommon.setEmpty(uriList.getSibuCd()));
                    lineList.add(EigyoNipoCommon.setEmpty(uriList.getSibuName()));

                    // ブロックコード・名称
                    if (!(NipoRefConstants.CSS_TR_CLASS.equals(uriList.getRClass())
                        || NipoRefConstants.CSS_TR_CLASS_SLAREA.equals(uriList.getRClass()))){
                        lineList.add(EigyoNipoConstants.EMPTY);
                        lineList.add(EigyoNipoCommon.setEmpty(uriList.getBlockName()));
                    } else if (parameterDto.isMosCompany()) {
                        lineList.add(EigyoNipoCommon.setEmpty(uriList.getBlockCd()));
                        lineList.add(EigyoNipoCommon.setEmpty(uriList.getBlockName()));
                    }
                }
                // 集計区分が直営店を含まないの場合
                else if(ShukeiKbn.OUT_RC.equals(parameterDto.getShukeiKbnCd())) {
                    // 集計区分が支部計以外の場合、支部コード・名称設定
                    if (!TaishoJoken.CODE_SIBU.equals(EigyoNipoCommon.getSumKbn(parameterDto.getClassName()))) {
                        lineList.add(EigyoNipoCommon.setEmpty(uriList.getSibuCd()));
                        lineList.add(EigyoNipoCommon.setEmpty(uriList.getSibuName()));
                    }
                    // ブロックコード・名称
                    if (!(NipoRefConstants.CSS_TR_CLASS.equals(uriList.getRClass())
                        || NipoRefConstants.CSS_TR_CLASS_SLAREA.equals(uriList.getRClass()))){
                        lineList.add(EigyoNipoConstants.EMPTY);
                        lineList.add(EigyoNipoCommon.setEmpty(uriList.getBlockName()));
                    } else if (parameterDto.isMosCompany()) {
                        lineList.add(EigyoNipoCommon.setEmpty(uriList.getBlockCd()));
                        lineList.add(EigyoNipoCommon.setEmpty(uriList.getBlockName()));
                    }
                }
                // 集計区分が直営店を含む且つ集計区分が支部計以外の場合、支部名称設定
                else if (!TaishoJoken.CODE_SIBU.equals(EigyoNipoCommon.getSumKbn(parameterDto.getClassName()))) {
                    lineList.add(EigyoNipoCommon.setEmpty(uriList.getSibuName()));
                }
                // 店コード・名称
                if (NipoRefConstants.CSS_TR_CLASS.equals(uriList.getRClass())) {
                    lineList.add(EigyoNipoCommon.setEmpty(uriList.getMiseCd()));
                    lineList.add(EigyoNipoCommon.setEmpty(uriList.getMiseNameKj()));
                } else if (parameterDto.isMosCompany()){
                    lineList.add(EigyoNipoConstants.EMPTY);
                    lineList.add(EigyoNipoConstants.EMPTY);
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
                //begin add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
                if (parameterDto.isAllowedDispNebiki()) {
                	//値引
                	lineList.add(formatter.format(uriList.getNebiki(), true));
                	//値引後売上
                    lineList.add(formatterUriage.format(uriList.getUriageAfterNebiki(), true));
                }
                //end add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
                lineList.add(formatter.format(uriList.getYosan(), true));
                lineList.add(formatter.format(uriList.getTassei(), true));
                //begin add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
                if (parameterDto.isAllowedDispNebiki()) {
                    lineList.add(formatter.format(uriList.getTasseiAfterNebiki(), true));
                }
                //end add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
                // 前年比対象売上、前年比対象前年売上、前年比(売上)
                lineList.add(formatterUriage.format(uriHose, true));
                //begin add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
                if (parameterDto.isAllowedDispNebiki()) {
                    //前年比対象値引
                    lineList.add(formatter.format(uriHoseNebiki, true));
                    //値引後前年比対象売上
                    lineList.add(formatterUriage.format(uriHoseAfterNebiki,true));
                }
                //end add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
                lineList.add(formatterUriage.format(uriHoseZen, true));
                //begin add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
                if (parameterDto.isAllowedDispNebiki()) {
                    //値引後前年実績
                    lineList.add(formatterUriage.format(uriHoseZenAfterNebiki,true));
                }
                //end add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
                lineList.add(formatter.format(uriList.getZenHiUri(), true));
                //begin add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
                if (parameterDto.isAllowedDispNebiki()) {
                    //値引後前年比
                    lineList.add(formatter.format(uriList.getZenHiUriAfterNebiki(), true));
                }
                //end add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
                // 客数、前年比対象客数、前年比対象前年客数、前年比(客数)
                lineList.add(formatter.format(kyakuList.getKyakusu(), true));
                lineList.add(formatter.format(kyaHose,true));
                lineList.add(formatter.format(kyaHoseZen, true));
                lineList.add(formatter.format(kyakuList.getZenHiKyaku(), true));
                // 客単価、前年比対象客単価、前年比対象前年客単価、前年比(客単価)
                lineList.add(formatterUriage.format(kyakuList.getTanka(), true));
                lineList.add(formatterUriage.format(tanHose, true));
                lineList.add(formatterUriage.format(tanHoseZen, true));
                lineList.add(formatter.format(kyakuList.getZenHiTanka(), true));
                // 前年休業
                lineList.add(uriList.getOpenKbnZenJpn());
                // 期日指定日報の場合前年天候、以外の場合前年営業日数設定
                if(TaishoKikan.DAY1.equals(parameterDto.getTaishoKikanCd())) {
                    lineList.add(uriList.getTenkoKbnZenJpn());
                } else {
                    lineList.add(formatter.format(uriList.getEigyoDaysZen()));
                }
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

        // ３行目:集計区分、前年データ種別、リンク区分
        List head3rdList = new ArrayList();
        head3rdList.add(EigyoNipoConstants.CSV_HD_SHUKEI_KBN);
        /* 2008/12/09追加 SV指定の場合 */
        if(ShukeiKbn.SV_CD.equals(parameterDto.getShukeiKbnCd())){
            head3rdList.add(EigyoNipoConstants.CSV_HD_SV);
        }else{
            head3rdList.add(ShukeiKbn.getName(parameterDto.getShukeiKbnCd()));
        }

        head3rdList.add(EigyoNipoConstants.EMPTY);
        head3rdList.add(EigyoNipoConstants.CSV_HD_ZEN_DATA_SHU);
        head3rdList.add(NipoZennenDataShubetu.getName
            (zenDataShu, parameterDto.getBirdUserInfo().getMstUser().getUserTypeCd()));
        head3rdList.add(EigyoNipoConstants.EMPTY);

        StringBuffer linkInfo = new StringBuffer();

        if(ShukeiKbn.SV_CD.equals(parameterDto.getShukeiKbnCd())){
            /* 2008/12/09追加
             * SV指定の場合、SVコード、SV名称を表示 */
            linkInfo.append(EigyoNipoConstants.CSV_HD_SV_TANTO);
            linkInfo.append(EigyoNipoCommon.setEmpty(parameterDto.getHyojiSvName()));

        } else if (TaishoJoken.CODE_SIBU.equals(EigyoNipoCommon.getSumKbn(parameterDto.getClassName()))) {
            linkInfo.append(EigyoNipoConstants.CSV_HD_SIBU);
            linkInfo.append(parameterDto.getSibuName());
        } else if (TaishoJoken.CODE_SLAREA.equals(EigyoNipoCommon.getSumKbn(parameterDto.getClassName()))) {
            linkInfo.append(EigyoNipoConstants.CSV_HD_SLAREA);
            linkInfo.append(parameterDto.getSibuName());
        } else if (TaishoJoken.CODE_JIGYOU.equals(EigyoNipoCommon.getSumKbn(parameterDto.getClassName()))) {
            linkInfo.append(EigyoNipoConstants.CSV_HD_JIGYOU);
            linkInfo.append(parameterDto.getSibuName());
        } else if (TaishoJoken.CODE_HONBU.equals(EigyoNipoCommon.getSumKbn(parameterDto.getClassName()))) {
            linkInfo.append(EigyoNipoConstants.CSV_HD_HONBU);
            linkInfo.append(parameterDto.getSibuName());
        } else if (TaishoJoken.CODE_SEGMENT.equals(EigyoNipoCommon.getSumKbn(parameterDto.getClassName()))) {
            linkInfo.append(EigyoNipoConstants.CSV_HD_GYOTAI);
            linkInfo.append(parameterDto.getSibuName());
        } else if (TaishoJoken.CODE_ALL.equals(EigyoNipoCommon.getSumKbn(parameterDto.getClassName()))) {
            linkInfo.append(EigyoNipoConstants.CSV_HD_ALL);
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

        // 集計区分で『SV指定(担当店一覧)』が選択された場合 20081209追加
        if (ShukeiKbn.SV_CD.equals(parameterDto.getShukeiKbnCd())) {
            head5thList.add(EigyoNipoConstants.CSV_DT_SIBU_CD);
            head5thList.add(EigyoNipoConstants.CSV_DT_SIBU_NAME);
            // 会社コードがモスフードサービの場合、ブロックコード・名称設定
            if (parameterDto.isMosCompany()) {
                head5thList.add(EigyoNipoConstants.CSV_DT_BLOCK_CD);
                head5thList.add(EigyoNipoConstants.CSV_DT_BLOCK_NAME);
            }
        }
        // 合計区分が支部以外の場合
        else if (!TaishoJoken.CODE_SIBU.equals(EigyoNipoCommon.getSumKbn(parameterDto.getClassName()))) {
            // 集計区分が直営店を含むの場合
            if (ShukeiKbn.IN_RC.equals(parameterDto.getShukeiKbnCd())) {
                head5thList.add(EigyoNipoConstants.CSV_DT_SHUKEI_KBN);
            // 集計区分が直営店を含まないの場合
            } else {
                head5thList.add(EigyoNipoConstants.CSV_DT_SIBU_CD);
                head5thList.add(EigyoNipoConstants.CSV_DT_SIBU_NAME);
            }
        }
        // 会社コードがモスフードサービ且つ集計区分が直営店を含まないの場合、ブロックコード・名称設定
        if (parameterDto.isMosCompany()
                && ShukeiKbn.OUT_RC.equals(parameterDto.getShukeiKbnCd())) {
            head5thList.add(EigyoNipoConstants.CSV_DT_BLOCK_CD);
            head5thList.add(EigyoNipoConstants.CSV_DT_BLOCK_NAME);
        }
        // 店コード・名称
        head5thList.add(EigyoNipoConstants.CSV_DT_MISE_CD);
        head5thList.add(EigyoNipoConstants.CSV_DT_MISE_NAME);
        // 店舗種別が全店の場合、店種出力
        if (TenpoShubetu.CODE_ALL.equals(parameterDto.getTenpoShubetuCd())) {
            head5thList.add(EigyoNipoConstants.CSV_DT_TENSHU);
        }
        // 休業
        head5thList.add(EigyoNipoConstants.CSV_DT_KYUGYO);
        // 期日指定日報の場合天候、以外の場合営業日数設定
        head5thList.add(TaishoKikan.DAY1.equals(parameterDto.getTaishoKikanCd()) ?
            EigyoNipoConstants.CSV_DT_TENKOU : EigyoNipoConstants.CSV_DT_EIGYO_DAYS);
        // 売上、予算、達成率
        head5thList.add(EigyoNipoConstants.CSV_DT_URIAGE);
        //begin add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
        if (parameterDto.isAllowedDispNebiki()) {
            head5thList.add(EigyoNipoConstants.CSV_DT_NEBIKI);
            head5thList.add(EigyoNipoConstants.CSV_DT_URIAGE_AFTER_NEBIKI);
        }
        //end add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
        head5thList.add(EigyoNipoConstants.CSV_DT_YOSAN);
        head5thList.add(EigyoNipoConstants.CSV_DT_TASSEI);
        //begin add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
        if (parameterDto.isAllowedDispNebiki()) {
            head5thList.add(EigyoNipoConstants.CSV_DT_TASSEI_AFTER_NEBIKI);
        }
        //end add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
        // 前年同月営業日補正の場合、前年比対象・売上、前年売上設定
        if (parameterDto.isSelectHosei()) {
            head5thList.add(EigyoNipoConstants.CSV_DT_HOSEI_URIAGE);
            //begin add 2019/04/18 xou.zoubun モスダイニング支部時、前年比対象・値引、前年比対象・値引後売上
            if (parameterDto.isAllowedDispNebiki()) {
                head5thList.add(EigyoNipoConstants.CSV_DT_HOSEI_NEBIKI);
                head5thList.add(EigyoNipoConstants.CSV_DT_HOSEI_URIAGE_AFTER_NEBIKI);
            }
            head5thList.add(EigyoNipoConstants.CSV_DT_HOSEI_URIAGE_ZEN);
        // 前年同月営業日補正以外の場合、前年売上設定
        } else {
            head5thList.add(EigyoNipoConstants.CSV_DT_URIAGE_ZEN);
        }
        //begin add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
        if (parameterDto.isAllowedDispNebiki()) {
            if (parameterDto.isSelectHosei()) {
                head5thList.add(EigyoNipoConstants.CSV_DT_HOSEI_URIAGE_ZEN_AFTER_NEBIKI);
            } else {
                head5thList.add(EigyoNipoConstants.CSV_DT_URIAGE_ZEN_AFTER_NEBIKI);
            }
        }
        //end add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
        // 前年比(売上)、客数
        head5thList.add(EigyoNipoConstants.CSV_DT_ZEN_HI_URIAGE);
        //begin add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
        if (parameterDto.isAllowedDispNebiki()) {
            head5thList.add(EigyoNipoConstants.CSV_DT_ZEN_HI_URIAGE_AFTER_NEBIKI);
        }
        //end add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
        head5thList.add(EigyoNipoConstants.CSV_DT_KYAKUSU);
        // 前年同月営業日補正の場合、前年比対象・客数、前年客数設定
        if (parameterDto.isSelectHosei()) {
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
        if (parameterDto.isSelectHosei()) {
            head5thList.add(EigyoNipoConstants.CSV_DT_HOSEI_KYAKU_TANKA);
            head5thList.add(EigyoNipoConstants.CSV_DT_HOSEI_KYAKU_TANKA_ZEN);
        // 前年同月営業日補正以外の場合、前年客単価設定
        } else {
            head5thList.add(EigyoNipoConstants.CSV_DT_KYAKU_TANKA_ZEN);
        }
        // 前年比(客単価)、前年休業
        head5thList.add(EigyoNipoConstants.CSV_DT_ZEN_HI_KYAKU_TANKA);
        // 前年同曜日以外の場合
        head5thList.add(EigyoNipoConstants.CSV_DT_ZEN_KYUGYO);
        // 期日指定日報の場合前年天候、以外の場合前年営業日数設定
        head5thList.add(TaishoKikan.DAY1.equals(parameterDto.getTaishoKikanCd()) ?
            EigyoNipoConstants.CSV_DT_ZEN_TENKOU : EigyoNipoConstants.CSV_DT_ZEN_EIGYO_DAYS);
        headerList.add(head1stList);
        headerList.add(head2ndList);
        headerList.add(head3rdList);
        headerList.add(head4thList);
        headerList.add(head5thList);

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