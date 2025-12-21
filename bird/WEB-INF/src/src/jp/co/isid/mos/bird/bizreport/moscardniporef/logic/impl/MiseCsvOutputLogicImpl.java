package jp.co.isid.mos.bird.bizreport.moscardniporef.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.code.NipoZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.common.code.ShukeiKbn;
import jp.co.isid.mos.bird.bizreport.common.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.common.code.TaishoTenpo;
import jp.co.isid.mos.bird.bizreport.common.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.common.NipoRefConstants;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;
import jp.co.isid.mos.bird.bizreport.common.util.NipoRefUtil;
import jp.co.isid.mos.bird.bizreport.moscardniporef.common.MoscardNipoCommon;
import jp.co.isid.mos.bird.bizreport.moscardniporef.common.MoscardNipoConstants;
import jp.co.isid.mos.bird.bizreport.moscardniporef.dao.TrnYosanInfoDao;
import jp.co.isid.mos.bird.bizreport.moscardniporef.entity.MstSibuInfo;
import jp.co.isid.mos.bird.bizreport.moscardniporef.entity.TrnUriageNipoInfo;
import jp.co.isid.mos.bird.bizreport.moscardniporef.entity.TrnYosanInfo;
import jp.co.isid.mos.bird.bizreport.moscardniporef.logic.SearchLogic;
import jp.co.isid.mos.bird.bizreport.moscardniporef.logic.TargetSibuInfoLogic;
import jp.co.isid.mos.bird.bizreport.moscardniporef.logic.UriageNipoInfoLogic;
import jp.co.isid.mos.bird.bizreport.moscardniporef.logic.ViewSibuInfoLogic;
import jp.co.isid.mos.bird.common.dao.MstSibuDao;
import jp.co.isid.mos.bird.common.entity.MstSibu;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.Calculator;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * 店舗別営業日報CSVダウンロードロジック
 *
 * @author   xyamauchi
 */
public class MiseCsvOutputLogicImpl implements CsvOutputLogic {
    /** 店舗別営業日報CSVダウンロードロジックID */
    public static final String LOGIC_ID = "BBR015L09";

    /** 検索条件取得ロジック */ 
    private SearchLogic groupEigyoNipoSearchLogic;

    /** 店別売上予算情報取得ロジック */ 
    private UriageNipoInfoLogic uriageNipoInfoLogic;
 
    /** 表示対象の支部一覧取得ロジック */
    private TargetSibuInfoLogic targetSibuInfoLogic;
 
    /** 表示支部取得ロジック */
    private ViewSibuInfoLogic  viewSibuInfoLogic;
    
    private TrnYosanInfoDao trnYosanInfoDao;
    
    private MstSibuDao mstSibuDao;
    
    private static final String COMPANY_CD = "companyCd";
    public String USER_ID = "userId";
    public String TENSHU = "tenpoShu";
    public String DATASHU = "dataShu";
    public String TAISHO_KIKAN = "taishoKikan";
    public String KIKAN_FROM  = "kikanFrom";
    public String KIKAN_TO = "kikanTo";
    public String LIMIT_FLG = "limitFlg";
    public String AREA_DAI_FLG = "areaDaiFlg";
    public String TAISHO_TENPO = "taishoTenpo";
    public String SIBU_CD = "sibuCd";
    public String KBN_CD = "kbnCd";

    /**
     * CSVファイル名称を取得する
     * @param   csvOutputDto    CSV出力DTO
     * @return  String          CSVファイル名称
     */
    public String getFileName(CsvOutputDto csvOutputDto) {   
        // 営業日報条件部DTO取得
        NipoRefConditionParameterDto csvDto = (NipoRefConditionParameterDto) csvOutputDto;
        // 集計区分コードを取得
        String strShukeiKbnCd= csvDto.getShukeiKbnCd();
        // 集計区分コードがSV_CD(SV担当店舗）の場合、CSVファイルの名称をMOSCARDSV.csvに設定
        if (strShukeiKbnCd.equals(ShukeiKbn.SV_CD)) {
            return MoscardNipoConstants.CSV_FILE_NAME_SV;
        } else {
            return MoscardNipoConstants.CSV_FILE_NAME_SIBU;
        }

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
        formatter.setDefaultText("0");
        
        // CSV出力データリスト、ヘッダー部情報取得
        List outputList = getHeaderData(csvOutputDto);
  
        // 条件部情報Dto取得
        NipoRefConditionParameterDto dto = (NipoRefConditionParameterDto) csvOutputDto;
        
/* 20081209追加 SV対応 start ----------------------------*/        
        String sibuCd = "";
        String kbnCd  = "";
        if(!dto.getShukeiKbnCd().equals(ShukeiKbn.SV_CD)){
            // 支部コードの入力チェック
            if (MoscardNipoCommon.isNull(dto.getSibuCd())) {
                throw new NotNullException(MoscardNipoConstants.MSG_SIBU_CD);
            }           
            sibuCd = dto.getSibuCd();
            kbnCd  = MoscardNipoCommon.getSumKbn(dto.getClassName());
        }

        // 検索条件取得
        Map paramMap = getSearchLogic().getSearchData(dto);
        paramMap.put(MoscardNipoConstants.MAP_SIBU_CD, sibuCd);        
        paramMap.put(MoscardNipoConstants.MAP_KBN_CD , kbnCd);
        
        String companyCd = (String)paramMap.get( COMPANY_CD );
        String tenpoShu = (String)paramMap.get( TENSHU );
        String areaDaiFlg = (String)paramMap.get( AREA_DAI_FLG );            
        
/* 20081209追加 End ------------------------------------*/
        // 前年データ種別
        String zenDataShu = TenpoShubetu.CODE_ZENNEN.equals(dto.getTenpoShubetuCd()) ?
            dto.getZenDataZennenCd() : dto.getZenDataZennenOthCd();

        List resultList = new ArrayList();
        List uriageList = new ArrayList();
        List uriageHoseiList = new ArrayList();
        List xYosanList = new ArrayList();
        List targetSibuList = new ArrayList();            

        // リンク区分が支部の場合
        if (dto.getShukeiKbnCd().equals(ShukeiKbn.SV_CD)) {
        	MstSibuInfo dispSibu = new MstSibuInfo();
        	dispSibu.setCompanyCd(companyCd);
        	dispSibu.setSibuCd(sibuCd);
        	targetSibuList.add(dispSibu);
        }
        else if (MoscardNipoConstants.LINK_SIBU.equals(MoscardNipoCommon.getSumKbn(dto.getClassName()))) {
        	MstSibuInfo dispSibu = new MstSibuInfo();
        	dispSibu.setCompanyCd(companyCd);
        	dispSibu.setSibuCd(sibuCd);
        	dispSibu.setSibuName(dto.getSibuName());
        	targetSibuList.add(dispSibu);
         
        // リンク区分が支部以外の場合    
        } else {
            paramMap.put(MoscardNipoConstants.MAP_KBN_CD, MoscardNipoCommon.getSumKbn(dto.getClassName()));
            List sibuList = getTargetSibuInfoLogic().execute(paramMap);
            List dispSibuList = getViewSibuInfoLogic().execute(paramMap);
            for (int j = 0; j < sibuList.size(); j++) {
                MstSibuInfo sibu = (MstSibuInfo)sibuList.get(j);
                for (int i = 0; i < dispSibuList.size(); i++) {
                    MstSibuInfo dispSibu = (MstSibuInfo)dispSibuList.get(i);
                    if ((sibu.getCompanyCd().equals( dispSibu.getCompanyCd())) 
                        && ((sibu.getSibuCd().trim()).equals((dispSibu.getSibuCd().trim()))))  {
                        if (dispSibu.getDispKbn().equals(MoscardNipoConstants.DISP)) {
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
        }
        //出力対象支部検索結果を取得します。
        for ( int i = 0; i < targetSibuList.size(); i++ ) {
            MstSibuInfo sibu = (MstSibuInfo)targetSibuList.get(i);
            if (!dto.getShukeiKbnCd().equals(ShukeiKbn.SV_CD) 
                   && !MoscardNipoConstants.LINK_SIBU.equals(MoscardNipoCommon.getSumKbn(dto.getClassName()))) {
	            paramMap.put(MoscardNipoConstants.MAP_SIBU_CD, sibu.getSibuCd().trim());
	            paramMap.put(MoscardNipoConstants.MAP_KBN_CD, MoscardNipoConstants.LINK_SIBU);
            }
            Map uriageNipoMap = getUriageNipoInfoLogic().execute(paramMap);
            uriageList.addAll((List)uriageNipoMap.get(MoscardNipoConstants.MAP_URIAGE_LIST));
            // 前年データ種別が前年同月営業日補正の時                  
            if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {    
                uriageHoseiList.addAll((List)uriageNipoMap.get(MoscardNipoConstants.MAP_RST_URIAGE_HOSE));
            } else {
                resultList.addAll((List)uriageNipoMap.get(MoscardNipoConstants.MAP_RESULT));
            }
            //新店・その他の予算情報を取得します。
            if ( ShukeiKbn.OUT_RC.equals(areaDaiFlg)) {
                //2.店舗種別が『全店』or 『新店』の場合。
                if ( tenpoShu.equals( TenpoShubetu.CODE_ALL) || tenpoShu.equals( TenpoShubetu.CODE_SIN ) ) {         
                    xYosanList = (List) uriageNipoMap.get("yosanList");
                    
                    BigDecimal xYosan = new BigDecimal(0);
                    
                    if ( xYosanList != null && xYosanList.size() != 0) {
                        TrnYosanInfo tyi = (TrnYosanInfo)xYosanList.get(0);
                        xYosan = tyi.getYosan();
                    }              
                    List sibuNameList = getMstSibuDao().getSibu( companyCd, sibu.getSibuCd().trim() );
                    MstSibu mSib = (MstSibu)sibuNameList.get(0);
                    resultList.add(resultList.size() - 1 , createYosanKeiInfo( mSib.getSibuCd().trim(), mSib.getSibuName().trim(), xYosan ));
                    
                    TrnUriageNipoInfo total = (TrnUriageNipoInfo)resultList.get( resultList.size() - 1 );
                    total.setYosan( total.getYosan().add( xYosan ) );
                    //新店・その他の予算を含めた達成率を再計算します。
                    total.setTassei( Calculator.percentage(total.getUriage(), total.getYosan(), 2));
               
                }
            } 
        }
        // 前年データ種別が前年同月営業日補正以外の時、データ部情報取得
        if (!NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
            for (int i = 0; i < resultList.size(); i++) {
                List lineList = new ArrayList();
                TrnUriageNipoInfo nipo = (TrnUriageNipoInfo)resultList.get(i);
                if(NipoRefConstants.CSS_TR_CLASS_SEGMENT.equals(nipo.getClass())) {
                	//新店・その他のデータのデフォルトはブランクにします。
                	formatter.setDefaultText("");
                }
                // 集計区分で『SV指定(担当店一覧)』が選択された場合 20081209追加
                if(ShukeiKbn.SV_CD.equals(dto.getShukeiKbnCd())) {
                    // 支部コード・名称設定
                    lineList.add(MoscardNipoCommon.setEmpty(nipo.getSibuCd()));
                    lineList.add(MoscardNipoCommon.setEmpty(nipo.getSibuName()));                 
                    // ブロックコード・名称
                    if (!(MoscardNipoConstants.NO_CLASS.equals(nipo.getRClass())
                        || MoscardNipoConstants.TR_AREA_SUM.equals(nipo.getRClass()))){
                        lineList.add(MoscardNipoConstants.EMPTY);
                        lineList.add(MoscardNipoCommon.setEmpty(nipo.getBlockName())); 
//                        lineList.add(MoscardNipoConstants.EMPTY);
//                        lineList.add(MoscardNipoConstants.EMPTY);                        
                    } else if (dto.isMosCompany()) {
                        lineList.add(MoscardNipoCommon.setEmpty(nipo.getBlockCd()));
                        lineList.add(MoscardNipoCommon.setEmpty(nipo.getBlockName()));
//                        lineList.add(MoscardNipoCommon.setEmpty(nipo.getSvCd()));
//                        lineList.add(MoscardNipoCommon.setEmpty(nipo.getUsernamekj()));  
                    }                   
                }
                // 集計区分が直営店を含まないの場合
                else if(ShukeiKbn.OUT_RC.equals(dto.getShukeiKbnCd())) {
                    // 集計区分が支部計以外の場合、支部コード・名称設定
                    if (!MoscardNipoConstants.LINK_SIBU.equals(MoscardNipoCommon.getSumKbn(dto.getClassName()))) {
                        lineList.add(MoscardNipoCommon.setEmpty(nipo.getSibuCd()));
                        lineList.add(MoscardNipoCommon.setEmpty(nipo.getSibuName()));
                    }                    
                    // ブロックコード・名称
                    if (!(MoscardNipoConstants.NO_CLASS.equals(nipo.getRClass())
                        || MoscardNipoConstants.TR_AREA_SUM.equals(nipo.getRClass()))){
                        lineList.add(MoscardNipoConstants.EMPTY);
                        lineList.add(MoscardNipoCommon.setEmpty(nipo.getBlockName())); 
//                        lineList.add(MoscardNipoConstants.EMPTY);
//                        lineList.add(MoscardNipoConstants.EMPTY);                        
                    } else if (dto.isMosCompany()) {
                        lineList.add(MoscardNipoCommon.setEmpty(nipo.getBlockCd()));
                        lineList.add(MoscardNipoCommon.setEmpty(nipo.getBlockName()));
//                        lineList.add(MoscardNipoCommon.setEmpty(nipo.getSvCd()));
//                        lineList.add(MoscardNipoCommon.setEmpty(nipo.getUsernamekj()));  
                    }                   
                }
                // 集計区分が直営店を含む且つ集計区分が支部計以外の場合、支部名称設定
                else if (!MoscardNipoConstants.LINK_SIBU.equals(MoscardNipoCommon.getSumKbn(dto.getClassName()))) {
                    lineList.add(MoscardNipoCommon.setEmpty(nipo.getSibuName()));
                }
                // 店コード・名称
                if (MoscardNipoConstants.NO_CLASS.equals(nipo.getRClass())) {
                    lineList.add(MoscardNipoCommon.setEmpty(nipo.getMiseCd()));
                    lineList.add(MoscardNipoCommon.setEmpty(nipo.getMiseNameKj()));                    
                } else if (dto.isMosCompany()){
                    lineList.add(MoscardNipoConstants.EMPTY);
                    lineList.add(MoscardNipoConstants.EMPTY);
                }
                // 店舗種別が全店の場合、店種出力
                if (TenpoShubetu.CODE_ALL.equals(dto.getTenpoShubetuCd())) {
                    lineList.add(MoscardNipoCommon.setEmpty(nipo.getZenKbn()));
                }
                // 休業
                lineList.add(nipo.getOpenKbnJpn());
                // 期日指定日報の場合天候、以外の場合営業日数設定
                if(TaishoKikan.DAY1.equals(dto.getTaishoKikanCd())) {
                    lineList.add(nipo.getTenkoKbnJpn());
                } else {
                    lineList.add(formatter.format(nipo.getEigyoDays()));
                }
                // 売上、予算、達成率、前年売上、前年比(売上)
                lineList.add(formatter.format(nipo.getUriage(),true));
                lineList.add(formatter.format(nipo.getYosan(),true));
                lineList.add(formatter.format(nipo.getTassei(),true));
                lineList.add(formatter.format(nipo.getUriageZen(),true));
                lineList.add(formatter.format(nipo.getZenHiUri(),true));
                // 客数、前年客数、前年比(客数)
                lineList.add(formatter.format(nipo.getKyakusu(),true));
                lineList.add(formatter.format(nipo.getKyakusuZen(), true));
                lineList.add(formatter.format(nipo.getZenHiKyaku(), true));
                // 単価、前年単価、前年比(単価)、前年休業
                lineList.add(formatter.format(nipo.getTanka(), true));
                lineList.add(formatter.format(nipo.getTankaZen(), true));
                lineList.add(formatter.format(nipo.getZenHiTanka(),true));
                lineList.add(nipo.getOpenKbnZenJpn());
                // 期日指定日報の場合前年天候、以外の場合前年営業日数設定
                if(TaishoKikan.DAY1.equals(dto.getTaishoKikanCd())) {
                    lineList.add(nipo.getTenkoKbnZenJpn());
                } else {
                    lineList.add(formatter.format(nipo.getEigyoDaysZen()));
                }
                //発行枚数
                lineList.add(formatter.format(nipo.getIssueCnt()));
                lineList.add(formatter.format(nipo.getZenIssueCnt()));
                lineList.add(formatter.format(nipo.getZenIssueCntHiritu()));
                //ﾁｬｰｼﾞ金額
                lineList.add(formatter.format(nipo.getChargeKin()));
                lineList.add(formatter.format(nipo.getChargeKinUriHi()));
                lineList.add(formatter.format(nipo.getZenChargeKin()));
                lineList.add(formatter.format(nipo.getChargeKinZenHi()));
                //ﾁｬｰｼﾞ件数
                lineList.add(formatter.format(nipo.getChargeKen()));
                lineList.add(formatter.format(nipo.getChargeKenKyakuHi()));
                lineList.add(formatter.format(nipo.getZenChargeKen()));
                lineList.add(formatter.format(nipo.getChargeKenZenHi()));
                //ﾁｬｰｼﾞ単価
                lineList.add(formatter.format(nipo.getChargeTanka()));
                lineList.add(formatter.format(nipo.getZenChargeTanka()));
                lineList.add(formatter.format(nipo.getChargeTankaZenHi()));
                //決済金額
                lineList.add(formatter.format(nipo.getKessaiKin()));
                lineList.add(formatter.format(nipo.getKessaiKinUrihi()));
                lineList.add(formatter.format(nipo.getZenKessaiKin()));
                lineList.add(formatter.format(nipo.getKessaiKinZenhi()));
                //決済件数
                lineList.add(formatter.format(nipo.getKessaiKen()));
                lineList.add(formatter.format(nipo.getKessaiKenKyakuhi()));
                lineList.add(formatter.format(nipo.getZenKessaiKen()));
                lineList.add(formatter.format(nipo.getKessaiKenZenhi()));
                //決済単価
                lineList.add(formatter.format(nipo.getKessaiTanka()));
                lineList.add(formatter.format(nipo.getKessaiTankaHi()));
                lineList.add(formatter.format(nipo.getZenKessaiTanka()));
                lineList.add(formatter.format(nipo.getKessaiTankaZenhi()));
//                //入金取消金額、入金取消件数
//                lineList.add(formatter.format(nipo.getChargeKinCancel()));
//                lineList.add(formatter.format(nipo.getChargeKenCancel()));
//                //利用取消金額、利用取消件数
//                lineList.add(formatter.format(nipo.getUseKinCancel()));
//                lineList.add(formatter.format(nipo.getUseKenCancel()));
//                //発行ボーナスバリュー、利用ボーナスバリュー
//                lineList.add(formatter.format(nipo.getBonusVIssue()));
//                lineList.add(formatter.format(nipo.getBonusVUse()));
//                //発行クーポンバリュー、利用クーポンバリュー
//                lineList.add(formatter.format(nipo.getCouponVIssue()));
//                lineList.add(formatter.format(nipo.getCouponVUse()));
//                //前受残高
//                lineList.add(formatter.format(nipo.getZandaka()));      
                
                outputList.add(lineList);

            }
        // 前年データ種別が前年同月営業日補正の時、データ部情報取得
        } else {
            for (int j = 0; j < uriageList.size();j++) {
                List lineList = new ArrayList();
                TrnUriageNipoInfo uriList = (TrnUriageNipoInfo)uriageList.get(j);

                BigDecimal uriHose = new BigDecimal(0);
                BigDecimal uriHoseZen = new BigDecimal(0);
                BigDecimal uriHoseZenHi = new BigDecimal("0.00");
                BigDecimal kyaHose = new BigDecimal(0);
                BigDecimal kyaHoseZen = new BigDecimal(0);
                BigDecimal kyaHoseZenHi = new BigDecimal("0.00");
                BigDecimal tanHose = new BigDecimal(0);
                BigDecimal tanHoseZen = new BigDecimal(0);
                BigDecimal tanHoseZenHi = new BigDecimal("0.00");
                BigDecimal issueCntHose = new BigDecimal(0);
                BigDecimal issueCntHoseZen = new BigDecimal(0);
                BigDecimal issueCntHoseZenHi = new BigDecimal("0.00");
                BigDecimal chargeKinHose = new BigDecimal(0);
                BigDecimal chargeKinHoseZen = new BigDecimal(0);
                BigDecimal chargeKinHoseZenHi = new BigDecimal("0.00");
                BigDecimal chargeKenHose = new BigDecimal(0);
                BigDecimal chargeKenHoseZen = new BigDecimal(0);
                BigDecimal chargeKenHoseZenHi = new BigDecimal("0.00");
                BigDecimal kessaiKinHose = new BigDecimal(0);
                BigDecimal kessaiKinHoseZen = new BigDecimal(0);
                BigDecimal kessaiKinHoseZenHi = new BigDecimal("0.00");
                BigDecimal kessaiKenHose = new BigDecimal(0);
                BigDecimal kessaiKenHoseZen = new BigDecimal(0);
                BigDecimal kessaiKenHoseZenHi = new BigDecimal("0.00");
                BigDecimal chargeTankaHose = new BigDecimal(0);
                BigDecimal chargeTankaHoseZen = new BigDecimal(0);
                BigDecimal chargeTankaHoseZenHi = new BigDecimal("0.00");
                BigDecimal kessaiTankaHose = new BigDecimal(0);
                BigDecimal kessaiTankaHoseZen = new BigDecimal(0);
                BigDecimal kessaiTankaHoseZenHi = new BigDecimal("0.00");

                for ( int i = 0; i < uriageHoseiList.size(); i++ ) {
                    TrnUriageNipoInfo uriage = (TrnUriageNipoInfo)uriageHoseiList.get(i);

                    if (uriList.getMiseCd().equals(uriage.getMiseCd()) 
                            && trim(uriList.getSibuCd()).equals(trim(uriage.getSibuCd()))) 
                    {
                        uriHose = uriage.getUriage();
                        uriHoseZen = uriage.getUriageZen();
                        uriHoseZenHi = uriage.getZenHiUri();
                        kyaHose = uriage.getKyakusu();
                        kyaHoseZen = uriage.getKyakusuZen();
                        kyaHoseZenHi = uriage.getZenHiKyaku();
                        tanHose = uriage.getTanka();
                        tanHoseZen = uriage.getTankaZen();
                        tanHoseZenHi = uriage.getZenHiTanka();
                        issueCntHose = uriage.getIssueCnt();
                        issueCntHoseZen = uriage.getZenIssueCnt();
                        issueCntHoseZenHi = uriage.getZenIssueCntHiritu();
                        chargeKinHose = uriage.getChargeKin();
                        chargeKinHoseZen = uriage.getZenChargeKin();
                        chargeKinHoseZenHi = uriage.getChargeKinZenHi();
                        chargeKenHose = uriage.getChargeKen();
                        chargeKenHoseZen = uriage.getZenChargeKen();
                        chargeKenHoseZenHi = uriage.getChargeKenZenHi();
                        chargeTankaHose = uriage.getChargeTanka();
                        chargeTankaHoseZen = uriage.getZenChargeTanka();
                        chargeTankaHoseZenHi = uriage.getChargeTankaZenHi();
                        kessaiKinHose = uriage.getKessaiKin();
                        kessaiKinHoseZen = uriage.getZenKessaiKin();
                        kessaiKinHoseZenHi = uriage.getKessaiKinZenhi();
                        kessaiKenHose = uriage.getKessaiKen();
                        kessaiKenHoseZen = uriage.getZenKessaiKen();
                        kessaiKenHoseZenHi = uriage.getKessaiKenZenhi();
                        kessaiTankaHose = uriage.getKessaiTanka();
                        kessaiTankaHoseZen = uriage.getZenKessaiTanka();
                        kessaiTankaHoseZenHi = uriage.getKessaiTankaZenhi();
                        
                        break;
                    }
                }
                
                // 集計区分で『SV指定(担当店一覧)』が選択された場合 20081209追加
                if(ShukeiKbn.SV_CD.equals(dto.getShukeiKbnCd())) {
                    // 支部コード・名称設定
                    lineList.add(MoscardNipoCommon.setEmpty(uriList.getSibuCd()));
                    lineList.add(MoscardNipoCommon.setEmpty(uriList.getSibuName()));
                    
                    // ブロックコード・名称
                    if (!(MoscardNipoConstants.NO_CLASS.equals(uriList.getRClass())
                        || MoscardNipoConstants.TR_AREA_SUM.equals(uriList.getRClass()))){
                        lineList.add(MoscardNipoConstants.EMPTY);
                        lineList.add(MoscardNipoCommon.setEmpty(uriList.getBlockName()));
//                        lineList.add(MoscardNipoConstants.EMPTY);
//                        lineList.add(MoscardNipoConstants.EMPTY);
                    } else if (dto.isMosCompany()) {
                        lineList.add(MoscardNipoCommon.setEmpty(uriList.getBlockCd()));
                        lineList.add(MoscardNipoCommon.setEmpty(uriList.getBlockName()));
//                        lineList.add(MoscardNipoCommon.setEmpty(uriList.getSvCd()));
//                        lineList.add(MoscardNipoCommon.setEmpty(uriList.getUsernamekj()));                        
                    }                   
                }
                // 集計区分が直営店を含まないの場合
                else if(ShukeiKbn.OUT_RC.equals(dto.getShukeiKbnCd())) {
                    // 集計区分が支部計以外の場合、支部コード・名称設定
                    if (!MoscardNipoConstants.LINK_SIBU.equals(MoscardNipoCommon.getSumKbn(dto.getClassName()))) {
                        lineList.add(MoscardNipoCommon.setEmpty(uriList.getSibuCd()));
                        lineList.add(MoscardNipoCommon.setEmpty(uriList.getSibuName()));
                    }                    
                    // ブロックコード・名称
                    if (!(MoscardNipoConstants.NO_CLASS.equals(uriList.getRClass())
                        || MoscardNipoConstants.TR_AREA_SUM.equals(uriList.getRClass()))){
                        lineList.add(MoscardNipoConstants.EMPTY);
                        lineList.add(MoscardNipoCommon.setEmpty(uriList.getBlockName()));
//                        lineList.add(MoscardNipoConstants.EMPTY);
//                        lineList.add(MoscardNipoConstants.EMPTY);
                    } else if (dto.isMosCompany()) {
                        lineList.add(MoscardNipoCommon.setEmpty(uriList.getBlockCd()));
                        lineList.add(MoscardNipoCommon.setEmpty(uriList.getBlockName()));
//                        lineList.add(MoscardNipoCommon.setEmpty(uriList.getSvCd()));
//                        lineList.add(MoscardNipoCommon.setEmpty(uriList.getUsernamekj()));
                    }                   
                }
                // 集計区分が直営店を含む且つ集計区分が支部計以外の場合、支部名称設定
                else if (!MoscardNipoConstants.LINK_SIBU.equals(MoscardNipoCommon.getSumKbn(dto.getClassName()))) {
                    lineList.add(MoscardNipoCommon.setEmpty(uriList.getSibuName()));
                }
                // 店コード・名称
                if (MoscardNipoConstants.NO_CLASS.equals(uriList.getRClass())) {
                    lineList.add(MoscardNipoCommon.setEmpty(uriList.getMiseCd()));
                    lineList.add(MoscardNipoCommon.setEmpty(uriList.getMiseNameKj()));                    
                } else if (dto.isMosCompany()){
                    lineList.add(MoscardNipoConstants.EMPTY);
                    lineList.add(MoscardNipoConstants.EMPTY);
                }
                // 休業
                lineList.add(uriList.getOpenKbnJpn());
                // 期日指定日報の場合天候、以外の場合営業日数設定
                if(TaishoKikan.DAY1.equals(dto.getTaishoKikanCd())) {
                    lineList.add(uriList.getTenkoKbnJpn());
                } else {
                    lineList.add(formatter.format(uriList.getEigyoDays()));
                }             
                // 売上、予算、達成率
                lineList.add(formatter.format(uriList.getUriage(), true));
                lineList.add(formatter.format(uriList.getYosan(), true));
                lineList.add(formatter.format(uriList.getTassei(), true));
                // 前年比対象売上、前年比対象前年売上、前年比(売上)
                lineList.add(formatter.format(uriHose, true));                
                lineList.add(formatter.format(uriHoseZen, true));
                lineList.add(formatter.format(uriHoseZenHi, true));
                // 客数、前年比対象客数、前年比対象前年客数、前年比(客数)
                lineList.add(formatter.format(uriList.getKyakusu(), true));
                lineList.add(formatter.format(kyaHose,true));
                lineList.add(formatter.format(kyaHoseZen, true));
                lineList.add(formatter.format(kyaHoseZenHi, true));
                // 客単価、前年比対象客単価、前年比対象前年客単価、前年比(客単価)
                lineList.add(formatter.format(uriList.getTanka(), true));
                lineList.add(formatter.format(tanHose, true));
                lineList.add(formatter.format(tanHoseZen, true));
                lineList.add(formatter.format(tanHoseZenHi, true));
                // 前年休業
                lineList.add(uriList.getOpenKbnZenJpn());
                // 期日指定日報の場合前年天候、以外の場合前年営業日数設定
                if(TaishoKikan.DAY1.equals(dto.getTaishoKikanCd())) {
                    lineList.add(uriList.getTenkoKbnZenJpn());
                } else {
                    lineList.add(formatter.format(uriList.getEigyoDaysZen()));
                }
                //発行枚数
                lineList.add(formatter.format(uriList.getIssueCnt()));
                lineList.add(formatter.format(issueCntHose));
                lineList.add(formatter.format(issueCntHoseZen));
                lineList.add(formatter.format(issueCntHoseZenHi));
                //ﾁｬｰｼﾞ金額
                lineList.add(formatter.format(uriList.getChargeKin()));
                lineList.add(formatter.format(uriList.getChargeKinUriHi()));
                lineList.add(formatter.format(chargeKinHose));
                lineList.add(formatter.format(chargeKinHoseZen));
                lineList.add(formatter.format(chargeKinHoseZenHi));
                //ﾁｬｰｼﾞ件数
                lineList.add(formatter.format(uriList.getChargeKen()));
                lineList.add(formatter.format(uriList.getChargeKenKyakuHi()));
                lineList.add(formatter.format(chargeKenHose));
                lineList.add(formatter.format(chargeKenHoseZen));
                lineList.add(formatter.format(chargeKenHoseZenHi));
                //ﾁｬｰｼﾞ単価
                lineList.add(formatter.format(uriList.getChargeTanka()));
                lineList.add(formatter.format(chargeTankaHose));
                lineList.add(formatter.format(chargeTankaHoseZen));
                lineList.add(formatter.format(chargeTankaHoseZenHi));
                //決済金額
                lineList.add(formatter.format(uriList.getKessaiKin()));
                lineList.add(formatter.format(uriList.getKessaiKinUrihi()));
                lineList.add(formatter.format(kessaiKinHose));
                lineList.add(formatter.format(kessaiKinHoseZen));
                lineList.add(formatter.format(kessaiKinHoseZenHi));
                //決済件数
                lineList.add(formatter.format(uriList.getKessaiKen()));
                lineList.add(formatter.format(uriList.getKessaiKenKyakuhi()));
                lineList.add(formatter.format(kessaiKenHose));
                lineList.add(formatter.format(kessaiKenHoseZen));
                lineList.add(formatter.format(kessaiKenHoseZenHi));
                //決済単価
                lineList.add(formatter.format(uriList.getKessaiTanka()));
                lineList.add(formatter.format(uriList.getKessaiTankaHi()));
                lineList.add(formatter.format(kessaiTankaHose));
                lineList.add(formatter.format(kessaiTankaHoseZen));
                lineList.add(formatter.format(kessaiTankaHoseZenHi));
//                //入金取消金額、入金取消件数
//                lineList.add(formatter.format(uriList.getChargeKinCancel()));
//                lineList.add(formatter.format(uriList.getChargeKenCancel()));
//                //利用取消金額、利用取消件数
//                lineList.add(formatter.format(uriList.getUseKinCancel()));
//                lineList.add(formatter.format(uriList.getUseKenCancel()));
//                //発行ボーナスバリュー、利用ボーナスバリュー
//                lineList.add(formatter.format(uriList.getBonusVIssue()));
//                lineList.add(formatter.format(uriList.getBonusVUse()));
//                //発行クーポンバリュー、利用クーポンバリュー
//                lineList.add(formatter.format(uriList.getCouponVIssue()));
//                lineList.add(formatter.format(uriList.getCouponVUse()));
//                //前受残高
//                lineList.add(formatter.format(uriList.getZandaka()));         
                
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
        NipoRefConditionParameterDto dto = (NipoRefConditionParameterDto) csvOutputDto;

        // 前年データ種別
        String zenDataShu = TenpoShubetu.CODE_ZENNEN.equals(dto.getTenpoShubetuCd()) ?
            dto.getZenDataZennenCd() : dto.getZenDataZennenOthCd();
        
        // １行目:会社、対象店舗
        List head1stList = new ArrayList();
        head1stList.add(MoscardNipoConstants.CSV_HD_COMPANY);
        head1stList.add(dto.getCompanyName());
        head1stList.add(MoscardNipoConstants.EMPTY);
        head1stList.add(MoscardNipoConstants.CSV_HD_TAISHO_TENPO);
        head1stList.add(TaishoTenpo.getName(dto.getTaishoTenpoCd()));

        // ２行目:店舗種別、対象期間
        List head2ndList = new ArrayList();
        head2ndList.add(MoscardNipoConstants.CSV_HD_TENPO_SHU);
        head2ndList.add(TenpoShubetu.getName(dto.getTenpoShubetuCd()));
        head2ndList.add(MoscardNipoConstants.EMPTY);
        head2ndList.add(MoscardNipoConstants.CSV_HD_TAISHO_KIKAN);
        head2ndList.add(NipoRefUtil.getCsvTaishoKikan(dto));
 
        // ３行目:集計区分、前年データ種別、リンク区分
        List head3rdList = new ArrayList();
        head3rdList.add(MoscardNipoConstants.CSV_HD_SHUKEI_KBN);
        /* 2008/12/09追加 SV指定の場合 */
        if(ShukeiKbn.SV_CD.equals(dto.getShukeiKbnCd())){
            head3rdList.add(MoscardNipoConstants.CSV_HD_SV);
        }else{
            head3rdList.add(ShukeiKbn.getName(dto.getShukeiKbnCd()));    
        }

        head3rdList.add(MoscardNipoConstants.EMPTY);
        head3rdList.add(MoscardNipoConstants.CSV_HD_ZEN_DATA_SHU);
        head3rdList.add(NipoZennenDataShubetu.getName
            (zenDataShu, dto.getBirdUserInfo().getMstUser().getUserTypeCd()));
        head3rdList.add(MoscardNipoConstants.EMPTY);

        StringBuffer linkInfo = new StringBuffer();

        if(ShukeiKbn.SV_CD.equals(dto.getShukeiKbnCd())){
            /* 2008/12/09追加
             * SV指定の場合、SVコード、SV名称を表示 */
            linkInfo.append(MoscardNipoConstants.CSV_HD_SV_TANTO);
            linkInfo.append(MoscardNipoCommon.setEmpty(dto.getHyojiSvName()));
            
        } else if (MoscardNipoConstants.LINK_SIBU.equals(MoscardNipoCommon.getSumKbn(dto.getClassName()))) {
            linkInfo.append(MoscardNipoConstants.CSV_HD_SIBU);
            linkInfo.append(dto.getSibuName());
        } else if (MoscardNipoConstants.LINK_SLAREA.equals(MoscardNipoCommon.getSumKbn(dto.getClassName()))) {
            linkInfo.append(MoscardNipoConstants.CSV_HD_SLAREA);
            linkInfo.append(dto.getSibuName());
        } else if (MoscardNipoConstants.LINK_JIGYOU.equals(MoscardNipoCommon.getSumKbn(dto.getClassName()))) {
            linkInfo.append(MoscardNipoConstants.CSV_HD_JIGYOU);
            linkInfo.append(dto.getSibuName());
        } else if (MoscardNipoConstants.LINK_HONBU.equals(MoscardNipoCommon.getSumKbn(dto.getClassName()))) {
            linkInfo.append(MoscardNipoConstants.CSV_HD_HONBU);
            linkInfo.append(dto.getSibuName());
        } else if (MoscardNipoConstants.LINK_GYOTAI.equals(MoscardNipoCommon.getSumKbn(dto.getClassName()))) {
            linkInfo.append(MoscardNipoConstants.CSV_HD_GYOTAI);
            linkInfo.append(dto.getSibuName());
        } else if (MoscardNipoConstants.LINK_ALL.equals(MoscardNipoCommon.getSumKbn(dto.getClassName()))) {
            linkInfo.append(MoscardNipoConstants.CSV_HD_ALL);
        } 
        
        head3rdList.add(linkInfo.toString());            
        
        // ４行目
        List head4thList = new ArrayList();
        
        // ５行目
        List head5thList = new ArrayList();
        
        // 集計区分で『SV指定(担当店一覧)』が選択された場合 20081209追加
        if (ShukeiKbn.SV_CD.equals(dto.getShukeiKbnCd())) {
            head5thList.add(MoscardNipoConstants.CSV_DT_SIBU_CD);
            head5thList.add(MoscardNipoConstants.CSV_DT_SIBU_NAME);
            // 会社コードがモスフードサービの場合、ブロックコード・名称設定
            if (dto.isMosCompany()) {
                head5thList.add(MoscardNipoConstants.CSV_DT_BLOCK_CD);
                head5thList.add(MoscardNipoConstants.CSV_DT_BLOCK_NAME);    
//                head5thList.add(MoscardNipoConstants.CSV_DT_SV_CD);
//                head5thList.add(MoscardNipoConstants.CSV_DT_SV_NAME);  
            }
        }
        // 合計区分が支部以外の場合
        else if (!MoscardNipoConstants.LINK_SIBU.equals(MoscardNipoCommon.getSumKbn(dto.getClassName()))) {
            // 集計区分が直営店を含むの場合
            if (ShukeiKbn.IN_RC.equals(dto.getShukeiKbnCd())) {
                head5thList.add(MoscardNipoConstants.CSV_DT_SHUKEI_KBN);
            // 集計区分が直営店を含まないの場合
            } else {
                head5thList.add(MoscardNipoConstants.CSV_DT_SIBU_CD);
                head5thList.add(MoscardNipoConstants.CSV_DT_SIBU_NAME);
            }
        }
        // 会社コードがモスフードサービ且つ集計区分が直営店を含まないの場合、ブロックコード・名称設定
        if (dto.isMosCompany()
                && ShukeiKbn.OUT_RC.equals(dto.getShukeiKbnCd())) {
            head5thList.add(MoscardNipoConstants.CSV_DT_BLOCK_CD);
            head5thList.add(MoscardNipoConstants.CSV_DT_BLOCK_NAME);
//            head5thList.add(MoscardNipoConstants.CSV_DT_SV_CD);
//            head5thList.add(MoscardNipoConstants.CSV_DT_SV_NAME);  
        }        
        // 店コード・名称
        head5thList.add(MoscardNipoConstants.CSV_DT_MISE_CD);
        head5thList.add(MoscardNipoConstants.CSV_DT_MISE_NAME);
        // 店舗種別が全店の場合、店種出力
        if (TenpoShubetu.CODE_ALL.equals(dto.getTenpoShubetuCd())) {
            head5thList.add(MoscardNipoConstants.CSV_DT_TENSHU);
        }
        // 休業
        head5thList.add(MoscardNipoConstants.CSV_DT_KYUGYO);
        // 期日指定日報の場合天候、以外の場合営業日数設定
        head5thList.add(TaishoKikan.DAY1.equals(dto.getTaishoKikanCd()) ?
            MoscardNipoConstants.CSV_DT_TENKOU : MoscardNipoConstants.CSV_DT_EIGYO_DAYS);        
        // 売上、予算、達成率
        head5thList.add(MoscardNipoConstants.CSV_DT_URIAGE);
        head5thList.add(MoscardNipoConstants.CSV_DT_YOSAN);
        head5thList.add(MoscardNipoConstants.CSV_DT_TASSEI);
        // 前年同月営業日補正の場合、前年比対象・売上、前年売上設定
        if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
            head5thList.add(MoscardNipoConstants.CSV_DT_HOSEI_URIAGE);
            head5thList.add(MoscardNipoConstants.CSV_DT_HOSEI_URIAGE_ZEN);
        // 前年同月営業日補正以外の場合、前年売上設定
        } else {            
            head5thList.add(MoscardNipoConstants.CSV_DT_URIAGE_ZEN);
        }
        // 前年比(売上)、客数
        head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HI_URIAGE);
        head5thList.add(MoscardNipoConstants.CSV_DT_KYAKUSU);
        // 前年同月営業日補正の場合、前年比対象・客数、前年客数設定       
        if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
            head5thList.add(MoscardNipoConstants.CSV_DT_HOSEI_KYAKUSU);
            head5thList.add(MoscardNipoConstants.CSV_DT_HOSEI_KYAKUSU_ZEN);
        // 前年同月営業日補正以外の場合、前年客数設定
        } else {
            head5thList.add(MoscardNipoConstants.CSV_DT_KYAKUSU_ZEN);
        }
        // 前年比(客数)、客単価
        head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HI_KYAKUSU);        
        head5thList.add(MoscardNipoConstants.CSV_DT_KYAKU_TANKA);
        // 前年同月営業日補正の場合、前年比対象・客単価、前年客単価設定
        if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
            head5thList.add(MoscardNipoConstants.CSV_DT_HOSEI_KYAKU_TANKA);
            head5thList.add(MoscardNipoConstants.CSV_DT_HOSEI_KYAKU_TANKA_ZEN);
        // 前年同月営業日補正以外の場合、前年客単価設定
        } else {            
            head5thList.add(MoscardNipoConstants.CSV_DT_KYAKU_TANKA_ZEN);
        }
        // 前年比(客単価)、前年休業
        head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HI_KYAKU_TANKA);
        // 前年同曜日以外の場合
        head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_KYUGYO);
        // 期日指定日報の場合前年天候、以外の場合前年営業日数設定
        head5thList.add(TaishoKikan.DAY1.equals(dto.getTaishoKikanCd()) ?
            MoscardNipoConstants.CSV_DT_ZEN_TENKOU : MoscardNipoConstants.CSV_DT_ZEN_EIGYO_DAYS);
        //発行枚数
        head5thList.add(MoscardNipoConstants.CSV_DT_ISSUE_CNT);
        if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_ISSUE_CNT);
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_ZENISSUE_CNT);            
        } else {
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_ISSUE_CNT);
        }
        head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_URIHI_ISSUE_CNT);
        //チャージ金額
        head5thList.add(MoscardNipoConstants.CSV_DT_CHARGEKIN);
        head5thList.add(MoscardNipoConstants.CSV_DT_URIHI_CHARGEKIN);
        
        if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_CHARGEKIN);
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_ZENCHARGEKIN);            
        } else {
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_CHARGEKIN);
        }        
        head5thList.add(MoscardNipoConstants.CSV_DT_ZENHI_CHARGEKIN);
        //チャージ件数
        head5thList.add(MoscardNipoConstants.CSV_DT_CHARGEKEN);
        head5thList.add(MoscardNipoConstants.CSV_DT_URIHI_CHARGEKEN);
        
        if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_CHARGEKEN);
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_ZENCHARGEKEN);            
        } else {
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_CHARGEKEN);
        }        
        head5thList.add(MoscardNipoConstants.CSV_DT_ZENHI_CHARGEKEN);
        //チャージ単価
        head5thList.add(MoscardNipoConstants.CSV_DT_CHARGE_TANKA);
        if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_CHARGE_TANKA);
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_ZENCHARGE_TANKA);       
        } else {
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_CHARGE_TANKA);
        }
        head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_TANKAHI);
        //決済金額
        head5thList.add(MoscardNipoConstants.CSV_DT_KESSAI_KIN);
        head5thList.add(MoscardNipoConstants.CSV_DT_URIHI_KESSAI_KIN);
        if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_KESSAI_KIN);
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_ZENKESSAI_KIN);   
        } else {
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_KESSAI_KIN);
        }        
        head5thList.add(MoscardNipoConstants.CSV_DT_ZENHI_KESSAI_KIN);
        //決済件数
        head5thList.add(MoscardNipoConstants.CSV_DT_KESSAI_KEN);
        head5thList.add(MoscardNipoConstants.CSV_DT_URIHI_KESSAI_KEN);
        if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_KESSAI_KEN);
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_ZENKESSAI_KEN);
        } else {
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_KESSAI_KEN);
        }        
        head5thList.add(MoscardNipoConstants.CSV_DT_ZENHI_KESSAI_KEN);
        //決済単価
        head5thList.add(MoscardNipoConstants.CSV_DT_KESSAI_TANKA);
        head5thList.add(MoscardNipoConstants.CSV_DT_TANKAHI_KESSAI_TANKA);
        if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_KESSAI_TANKA);
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_ZENKESSAI_TANKA);
        } else {
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_KESSAI_TANKA);
        }                
        head5thList.add(MoscardNipoConstants.CSV_DT_ZENHI_KESSAI_TANKA);

//        head5thList.add(MoscardNipoConstants.CSV_DT_CHARGE_KIN_CANCEL);
//        head5thList.add(MoscardNipoConstants.CSV_DT_CHARGE_KEN_CANCEL);
//        head5thList.add(MoscardNipoConstants.CSV_DT_USE_KIN_CANCEL);
//        head5thList.add(MoscardNipoConstants.CSV_DT_USE_KEN_CANCEL);
//        head5thList.add(MoscardNipoConstants.CSV_DT_BONUS_V_ISSUE);
//        head5thList.add(MoscardNipoConstants.CSV_DT_BONUS_V_USE);
//        head5thList.add(MoscardNipoConstants.CSV_DT_COUPON_V_ISSUE);
//        head5thList.add(MoscardNipoConstants.CSV_DT_COUPON_V_USE);
//        head5thList.add(MoscardNipoConstants.CSV_DT_ZANDAKA);
        
        headerList.add(head1stList);
        headerList.add(head2ndList);
        headerList.add(head3rdList);
        headerList.add(head4thList);
        headerList.add(head5thList);
             
        return headerList;        
    }
    
    /**
     * 新店予定予算計 
     * @param totalYosan
     * @param sibuYosan
     * @return
     */
    private TrnUriageNipoInfo createYosanKeiInfo(String sibuCd, String sibuName, BigDecimal xYosan) {
        TrnUriageNipoInfo uriage = new TrnUriageNipoInfo();
        
        uriage.setSibuCd( sibuCd );
        uriage.setSibuName( sibuName );
        uriage.setBlockName("新店・その他予算計");
        uriage.setYosan( xYosan );
        
        return uriage;
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
    
    /**
     * trnYosanInfoDaoの設定
     * @return
     */
    public TrnYosanInfoDao getTrnYosanInfoDao() {
        return this.trnYosanInfoDao;
    }
    /**
     * trnYosanInfoDaoの設定
     * @param trnYosanInfoDao
     */
    public void setTrnYosanInfoDao( TrnYosanInfoDao trnYosanInfoDao ) {
        this.trnYosanInfoDao = trnYosanInfoDao;
    }
    
    /**
     * mstSibuDaoの設定
     * @return
     */
    public MstSibuDao getMstSibuDao() {
        return this.mstSibuDao;
    }
    /**
     * mstSibuDaoの設定
     * @param mstSibuDao
     */
    public void setMstSibuDao( MstSibuDao mstSibuDao ) {
        this.mstSibuDao = mstSibuDao;
    }
    
    private String trim(String value) {
        if (value == null) {
            return "";
        }
        return value.trim();
    }
}