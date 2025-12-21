package jp.co.isid.mos.bird.bizreport.moscardniporef.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.code.NipoZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.common.code.ShukeiKbn;
import jp.co.isid.mos.bird.bizreport.common.code.TaishoTenpo;
import jp.co.isid.mos.bird.bizreport.common.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;
import jp.co.isid.mos.bird.bizreport.common.util.NipoRefUtil;
import jp.co.isid.mos.bird.bizreport.moscardniporef.common.MoscardNipoCommon;
import jp.co.isid.mos.bird.bizreport.moscardniporef.common.MoscardNipoConstants;
import jp.co.isid.mos.bird.bizreport.moscardniporef.entity.TrnSibuUriageNipoRelate;
import jp.co.isid.mos.bird.bizreport.moscardniporef.logic.HonbuUriageInfoLogic;
import jp.co.isid.mos.bird.bizreport.moscardniporef.logic.UriageInfoLogic;
import jp.co.isid.mos.bird.bizreport.moscardniporef.logic.ViewSibuInfoLogic;
import jp.co.isid.mos.bird.bizreport.moscardniporef.logic.ViewUriYosanLogic;
import jp.co.isid.mos.bird.bizreport.moscardniporef.logic.SearchLogic;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.Calculator;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * 支部別営業日報CSVダウンロードロジック
 *
 * @author   xyamauchi
 */
public class SibuCsvOutputLogicImpl implements CsvOutputLogic {
    /** 支部別営業日報CSVダウンロードロジックID */
    public static final String LOGIC_ID = "BBR015L05";

    /** 検索条件取得ロジック */ 
    private SearchLogic groupEigyoNipoSearchLogic;

    /** 売上情報取得ロジック */ 
    private UriageInfoLogic uriageInfoLogic;

    /** 表示支部取得ロジック */ 
    private ViewSibuInfoLogic viewSibuInfoLogic;

    /** 表示売上予算情報取得ロジック */ 
    private ViewUriYosanLogic viewUriYosanLogic;
    
    /** LOGIC【本部売上情報取得】 */ 
    private HonbuUriageInfoLogic honbuUriageInfoLogic;

    /**
     * CSVファイル名称を取得する
     * @param   csvOutputDto    CSV出力DTO
     * @return  String          CSVファイル名称
     */
    public String getFileName(CsvOutputDto csvOutputDto) {   
        return MoscardNipoConstants.CSV_FILE_NAME;
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

        // 営業日報条件部DTO取得
        NipoRefConditionParameterDto dto = (NipoRefConditionParameterDto) csvOutputDto;

        // 予算・売上情報整理(CSV用)
        Map resultMap = this.searchSibuUriageInfo(dto);        
        List uriageList = (List) resultMap.get(MoscardNipoConstants.MAP_URIAGE_LIST);
        List uriHeiList = (List) resultMap.get(MoscardNipoConstants.MAP_RST_URI_AVE);
        
        List uriHoseiList = (List) resultMap.get(MoscardNipoConstants.MAP_RST_URI_HOSE);
        List uriHeiHoseiList = (List) resultMap.get(MoscardNipoConstants.MAP_RST_URI_AVE_HOSE);
 
        // 売上情報が0件の場合
        if (uriageList.size() != uriageList.size()) {
            throw new FtlSystemException(MoscardNipoConstants.EMPTY);
        }
 
        // 前年データ種別
        String zenDataShu = TenpoShubetu.CODE_ZENNEN.equals(dto.getTenpoShubetuCd()) ?
            dto.getZenDataZennenCd() : dto.getZenDataZennenOthCd();

        // データ部情報取得 
        for (int i = 0; i < uriageList.size(); i++) {
            List lineList = new ArrayList();
            TrnSibuUriageNipoRelate uriage = (TrnSibuUriageNipoRelate) uriageList.get(i);
            TrnSibuUriageNipoRelate uriHei = (TrnSibuUriageNipoRelate) uriHeiList.get(i);

            TrnSibuUriageNipoRelate uriHose = new TrnSibuUriageNipoRelate();
            TrnSibuUriageNipoRelate uriHoseHei = new TrnSibuUriageNipoRelate();
 
            // 前年データ種別が前年同月営業日補正の時            
            if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu))  {
                uriHose = (TrnSibuUriageNipoRelate)uriHoseiList.get(i);
                uriHoseHei = (TrnSibuUriageNipoRelate)uriHeiHoseiList.get(i);
            }
            // 事業本部コード、事業本部名称、エリアコード、エリア名称、支部コード、支部名称
            if (MoscardNipoConstants.NO_CLASS.equals(uriage.getRClass())) {
                // 指定無し
                if (ShukeiKbn.OUT_RC.equals(dto.getShukeiKbnCd())){
                    lineList.add(MoscardNipoCommon.setEmpty(uriage.getJigyoCd()));
                    lineList.add(MoscardNipoCommon.setEmpty(uriage.getJigyoName()));
                    lineList.add(MoscardNipoCommon.setEmpty(uriage.getSlareaCd()));
                    lineList.add(MoscardNipoCommon.setEmpty(uriage.getSlareaName()));
                    lineList.add(MoscardNipoCommon.setEmpty(uriage.getSibuCd()));
                }
                lineList.add(MoscardNipoCommon.setEmpty(uriage.getSibuName()));
            // エリア計の場合
            } else if (MoscardNipoConstants.TR_AREA_SUM.equals(uriage.getRClass())) {
                lineList.add(MoscardNipoCommon.setEmpty(uriage.getJigyoCd()));
                lineList.add(MoscardNipoCommon.setEmpty(uriage.getJigyoName()));
                lineList.add(MoscardNipoCommon.setEmpty(uriage.getSibuCd()));
                lineList.add(MoscardNipoCommon.setEmpty(uriage.getSibuName()));
                lineList.add(MoscardNipoConstants.EMPTY);
                lineList.add(MoscardNipoConstants.EMPTY);
            // 事業本部計又は本部計の場合
            } else if (MoscardNipoConstants.TR_JIGYOU_SUM.equals(uriage.getRClass())
                || MoscardNipoConstants.TR_HONBU_SUM.equals(uriage.getRClass())) {
                lineList.add(MoscardNipoCommon.setEmpty(uriage.getSibuCd()));
                lineList.add(MoscardNipoCommon.setEmpty(uriage.getSibuName()));
                lineList.add(MoscardNipoConstants.EMPTY);
                lineList.add(MoscardNipoConstants.EMPTY);
                lineList.add(MoscardNipoConstants.EMPTY);
                lineList.add(MoscardNipoConstants.EMPTY);
            // 統括計又は総合計の場合
            } else if (MoscardNipoConstants.TR_GYOTAI_SUM.equals(uriage.getRClass())
                    || MoscardNipoConstants.TR_TOTAL_SUM.equals(uriage.getRClass())) {
                // 集計区分が直営業店を含まないの時
                if (ShukeiKbn.OUT_RC.equals(dto.getShukeiKbnCd())) {
                    lineList.add(MoscardNipoConstants.EMPTY);
                }                
                lineList.add(MoscardNipoCommon.setEmpty(uriage.getSibuName()));
                // 集計区分が直営業店を含まないの時
                if (ShukeiKbn.OUT_RC.equals(dto.getShukeiKbnCd())) {
                    lineList.add(MoscardNipoConstants.EMPTY);
                    lineList.add(MoscardNipoConstants.EMPTY);
                    lineList.add(MoscardNipoConstants.EMPTY);
                    lineList.add(MoscardNipoConstants.EMPTY);
                }
            }
            // 当年店数、前年店数設定
            lineList.add(formatter.format(uriage.getTenpoCount(), true));
            lineList.add(formatter.format(uriage.getZenTenpoCount(), true));
            // 前年同月営業日補正の場合、前年比対象・店数、前年店数設定
            if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu))  {
                lineList.add(formatter.format(uriage.getHoseiTenpoCnt(), true));
                lineList.add(formatter.format(uriage.getHoseiZenTenpoCnt(), true));
            }
            // 売上、予算、達成率
            lineList.add(formatter.format(uriage.getUriage(), true));
            lineList.add(formatter.format(uriage.getSibuYosan(), true));
            lineList.add(formatter.format(uriage.getTasseiYosan(), true));
            // 前年同月営業日補正の場合、前年比対象・売上、前年実績、前年比(売上) 
            if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
                lineList.add(formatter.format(uriHose.getUriage(), true));
                lineList.add(formatter.format(uriHose.getZenUriage(), true));
                lineList.add(formatter.format(uriHose.getZenHiSa(), true));
            // 前年同月営業日補正以外の場合、前年実績、前年比(売上) 
            } else {
                lineList.add(formatter.format(uriage.getZenUriage(), true));
                lineList.add(formatter.format(uriage.getZenHiSa(), true));
            }
            // 前年データ種別が前年同月同曜以外の時
            if (!NipoZennenDataShubetu.CODE_DOYO.equals(zenDataShu)) {
                // 売上平均、予算平均、予算差
                lineList.add(formatter.format(uriHei.getUriage(), true));
                lineList.add(formatter.format(uriHei.getSibuYosan(), true));
                lineList.add(formatter.format(uriHei.getTasseiYosan(), true));
                // 前年同月営業日補正の場合、前年比対象・売上平均、前年売上平均、前年差(売上)設定
                if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
                    lineList.add(formatter.format(uriHoseHei.getUriage(), true));
                    lineList.add(formatter.format(uriHoseHei.getZenUriage(), true));
                    lineList.add(formatter.format(uriHoseHei.getZenHiSa(), true));
                // 前年同月営業日補正以外の場合、売上平均、前年差(売上)設定
                } else { 
                    lineList.add(formatter.format(uriHei.getZenUriage(), true));
                    lineList.add(formatter.format(uriHei.getZenHiSa(), true));                    
                }
            }
            // 客数
            lineList.add(formatter.format(uriage.getKyakusu(), true));
            // 前年同月営業日補正の場合           
            if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
                // 前年比対象・客数、前年客数、前年比(客数)
                lineList.add(formatter.format(uriHose.getKyakusu(), true));//NET客数(当年)
                lineList.add(formatter.format(uriHose.getZenKyakusu(), true));//NET客数(前年)
                lineList.add(formatter.format(
                		Calculator.percentage(uriHose.getKyakusu(), uriHose.getZenKyakusu()
                				, 2), true));//NET客数(前年比)
                // 客単価、前年比対象・客単価、前年客単価、前年比(客単価)
                lineList.add(formatter.format(uriage.getKyakuTanka(), true));//客単価(当年)
                lineList.add(formatter.format(uriHose.getKyakuTanka(), true));//NET客単価(当年)
                lineList.add(formatter.format(uriHose.getZenTanka(), true));//NET客単価(前年)
                lineList.add(formatter.format(uriHose.getKyakuTankaZenhi(), true));//NET客単価(前年比)
            // 前年同月営業日補正以外の場合
            } else {
                // 前年客数、前年比(客数)、客単価、前年客単価、前年比(客単価)
                lineList.add(formatter.format(uriage.getZenKyakusu(), true));//客数(前年)
                lineList.add(formatter.format(
                		Calculator.percentage(uriage.getKyakusu(), uriage.getZenKyakusu()
                				, 2), true));//客数(前年比)
                lineList.add(formatter.format(uriage.getKyakuTanka(), true));//客単価(当年)
                lineList.add(formatter.format(uriage.getZenTanka(),true));//客単価(前年)
                lineList.add(formatter.format(uriage.getKyakuTankaTanhi(), true));//客単価(前年比)
            }
            // 前年データ種別が前年同月同曜以外の時   
            if (!NipoZennenDataShubetu.CODE_DOYO.equals(zenDataShu)) {
                // 客数平均
                lineList.add(formatter.format(uriHei.getKyakusu(), true)) ;
                // 前年同月営業日補正の場合、前年比対象・客数平均、前年客数平均、前年差(客数)設定
                if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
                    lineList.add(formatter.format(uriHoseHei.getKyakusu(), true));
                    lineList.add(formatter.format(uriHoseHei.getZenKyakusu(), true));
                    lineList.add(formatter.format(uriHoseHei.getZenSaKyakusu(), true));
                // 前年同月営業日補正以外の場合、客数平均、前年差(客数)設定
                } else {
                    lineList.add(formatter.format(uriHei.getZenKyakusu(), true));
                    lineList.add(formatter.format(uriHei.getZenSaKyakusu(), true));
                }

            }      
            // 発行枚数
            lineList.add(formatter.format(uriage.getIssueCnt(), true));
            // 前年データ種別が前年同月同曜の時
            if (!NipoZennenDataShubetu.CODE_DOYO.equals(zenDataShu)) {
                if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
                    lineList.add(formatter.format(uriHose.getIssueCnt(), true));
                    lineList.add(formatter.format(uriHose.getZenIssueCnt(), true));
                    lineList.add(formatter.format(uriHose.getZenIssueCntHiritu(), true));
//                    lineList.add(formatter.format(uriHei.getIssueCnt(), true));
//                    lineList.add(formatter.format(uriHoseHei.getIssueCnt(), true));
//                    lineList.add(formatter.format(uriHoseHei.getZenIssueCnt(), true));
//                    lineList.add(formatter.format(uriHoseHei.getZenSaIssueCnt(), true));
                } else {
                    lineList.add(formatter.format(uriage.getZenIssueCnt(), true));
                    lineList.add(formatter.format(uriage.getZenIssueCntHiritu(), true));
//                    lineList.add(formatter.format(uriHei.getIssueCnt(), true));
//                    lineList.add(formatter.format(uriHei.getZenIssueCnt(), true));
//                    lineList.add(formatter.format(uriHei.getZenSaIssueCnt(), true));
                }                
            } else {
                lineList.add(formatter.format(uriage.getZenIssueCnt(), true));
                lineList.add(formatter.format(uriage.getZenIssueCntHiritu(), true));
            }
            // チャージ金額
            lineList.add(formatter.format(uriage.getChargeKin(), true));
            //当年値のみからの比率値は、実績値を元に算出された値を設定します。
            lineList.add(formatter.format(uriage.getUrihiChargeKin(), true));//売上比

            // 前年データ種別が前年同月同曜の時
            if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
                lineList.add(formatter.format(uriHose.getChargeKin(), true));
                lineList.add(formatter.format(uriHose.getZenChargeKin(), true));
                lineList.add(formatter.format(uriHose.getZenHiChargeKin(), true));
//                    lineList.add(formatter.format(uriHei.getChargeKin(), true));
//                    lineList.add(formatter.format(uriHoseHei.getChargeKin(), true));
//                    lineList.add(formatter.format(uriHoseHei.getZenChargeKin(), true));
//                    lineList.add(formatter.format(uriHoseHei.getZenSaChargeKin(), true));   
                
            } else {
                lineList.add(formatter.format(uriage.getZenChargeKin(), true));
                lineList.add(formatter.format(uriage.getZenHiChargeKin(), true));
                if (!NipoZennenDataShubetu.CODE_DOYO.equals(zenDataShu)) {
//                    lineList.add(formatter.format(uriHei.getChargeKin(), true));
//                    lineList.add(formatter.format(uriHei.getZenChargeKin(), true));
//                    lineList.add(formatter.format(uriHei.getZenSaChargeKin(), true));
                }
            }
            // チャージ件数
            lineList.add(formatter.format(uriage.getChargeKen(), true));
            //当年値のみからの比率値は、実績値を元に算出された値を設定します。
            lineList.add(formatter.format(uriage.getChargeKenKyakuHi(), true));//客数比
            if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
                lineList.add(formatter.format(uriHose.getChargeKen(), true));
                lineList.add(formatter.format(uriHose.getZenChargeKen(), true));
                lineList.add(formatter.format(uriHose.getChargeKenZenHi(), true));
            } else {
                lineList.add(formatter.format(uriage.getZenChargeKen(), true));
                lineList.add(formatter.format(uriage.getChargeKenZenHi(), true));                
            }
            //チャージ単価
            lineList.add(formatter.format(uriage.getChargeTanka(), true));
            if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
                lineList.add(formatter.format(uriHose.getChargeTanka(), true));
                lineList.add(formatter.format(uriHose.getZenChargeTanka(), true));
                lineList.add(formatter.format(uriHose.getZenChargeTankahi(), true));                
            } else {
                lineList.add(formatter.format(uriage.getZenChargeTanka(), true));
                lineList.add(formatter.format(uriage.getZenChargeTankahi(), true));
            }

            // 前年データ種別が前年同月同曜の時
//            if (!NipoZennenDataShubetu.CODE_DOYO.equals(zenDataShu)) {
//                lineList.add(formatter.format(uriHei.getChargeKen(), true));
//                if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
//                    lineList.add(formatter.format(uriHoseHei.getChargeKen(), true));
//                    lineList.add(formatter.format(uriHoseHei.getZenChargeKen(), true));
//                    lineList.add(formatter.format(uriHoseHei.getZenSaChargeKen(), true));
//                } else {
//                    lineList.add(formatter.format(uriHei.getZenChargeKen(), true));
//                    lineList.add(formatter.format(uriHei.getZenSaChargeKen(), true));                    
//                }
//            }
            // 決済金額
            lineList.add(formatter.format(uriage.getKessaiKin(), true));
            //当年値のみからの比率値は、実績値を元に算出された値を設定します。
            lineList.add(formatter.format(uriage.getKessaiKinUriHi(), true));//売上比
            if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
                lineList.add(formatter.format(uriHose.getKessaiKin(), true));
                lineList.add(formatter.format(uriHose.getZenKessaiKin(), true));
                lineList.add(formatter.format(uriHose.getKessaiKinZenHi(), true));
//              lineList.add(formatter.format(uriHei.getKessaiKin(), true));
//              lineList.add(formatter.format(uriHoseHei.getKessaiKin(), true));
//              lineList.add(formatter.format(uriHoseHei.getZenKessaiKin(), true));
//              lineList.add(formatter.format(uriHoseHei.getZenSaKessaiKin(), true));    
                
            } else {
                lineList.add(formatter.format(uriage.getZenKessaiKin(), true));
                lineList.add(formatter.format(uriage.getKessaiKinZenHi(), true));
                if (!NipoZennenDataShubetu.CODE_DOYO.equals(zenDataShu)) {
//    				lineList.add(formatter.format(uriHei.getKessaiKin(), true));
//    				lineList.add(formatter.format(uriHei.getZenKessaiKin(), true));
//    				lineList.add(formatter.format(uriHei.getZenSaKessaiKin(), true));    
                }
            }
            
            // 決済件数
            lineList.add(formatter.format(uriage.getKessaiKen(), true));
            //当年値のみからの比率値は、実績値を元に算出された値を設定します。
            lineList.add(formatter.format(uriage.getKessaiKenKyakuHi(), true));//客数比
            if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
                lineList.add(formatter.format(uriHose.getKessaiKen(), true));
                lineList.add(formatter.format(uriHose.getZenKessaiKen(), true));
                lineList.add(formatter.format(uriHose.getKessaiKenZenHi(), true));
            } else {
                lineList.add(formatter.format(uriage.getZenKessaiKen(), true));
                lineList.add(formatter.format(uriage.getKessaiKenZenHi(), true));                
            }
            //決済単価
            lineList.add(formatter.format(uriage.getKessaiKinTanka(), true));
            //当年値のみからの比率値は、実績値を元に算出された値を設定します。
            lineList.add(formatter.format(uriage.getKessaiKinTankaHi(), true));//単価比
            if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
                lineList.add(formatter.format(uriHose.getKessaiKinTanka(), true));
                lineList.add(formatter.format(uriHose.getZenKessaiKinTanka(), true));
                lineList.add(formatter.format(uriHose.getKessaiKinZenTankaHi(), true));
            } else {
                lineList.add(formatter.format(uriage.getZenKessaiKinTanka(), true));
                lineList.add(formatter.format(uriage.getKessaiKinZenTankaHi(), true));    
            }
            
            // 前年データ種別が前年同月同曜の時
//            if (!NipoZennenDataShubetu.CODE_DOYO.equals(zenDataShu)) {
//                lineList.add(formatter.format(uriHei.getKessaiKen(), true));
//                if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
//                    lineList.add(formatter.format(uriHoseHei.getKessaiKen(), true));
//                    lineList.add(formatter.format(uriHoseHei.getZenKessaiKen(), true));
//                    lineList.add(formatter.format(uriHoseHei.getZenSaKessaiKen(), true));
//                } else {
//                    lineList.add(formatter.format(uriHei.getZenKessaiKen(), true));
//                    lineList.add(formatter.format(uriHei.getZenSaKessaiKen(), true));
//                }
//            }
//            lineList.add(formatter.format(uriage.getChargeKinCancel(), true));
//            lineList.add(formatter.format(uriage.getChargeKenCancel(), true));
//            lineList.add(formatter.format(uriage.getUseKinCancel(), true));
//            lineList.add(formatter.format(uriage.getUseKenCancel(), true));
//            lineList.add(formatter.format(uriage.getBonusVIssue(), true));
//            lineList.add(formatter.format(uriage.getBonusVUse(), true));
//            lineList.add(formatter.format(uriage.getCouponVIssue(), true));
//            lineList.add(formatter.format(uriage.getCouponVUse(), true));
//            lineList.add(formatter.format(uriage.getZandaka(), true));
            
            
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
 
        // ３行目:集計区分、前年データ種別
        List head3rdList = new ArrayList();
        head3rdList.add(MoscardNipoConstants.CSV_HD_SHUKEI_KBN);
        head3rdList.add(ShukeiKbn.getName(dto.getShukeiKbnCd()));
        head3rdList.add(MoscardNipoConstants.EMPTY);
        head3rdList.add(MoscardNipoConstants.CSV_HD_ZEN_DATA_SHU);
        head3rdList.add(NipoZennenDataShubetu.getName
            (zenDataShu, dto.getBirdUserInfo().getMstUser().getUserTypeCd()));

        // ４行目
        List head4thList = new ArrayList();
        
        // ５行目:データ部ヘッダ作成
        List head5thList = new ArrayList();
        // 集計区分が直営店を含まないの場合、事業本部、エリア、支部コード・名称設定
        if (ShukeiKbn.OUT_RC.equals(dto.getShukeiKbnCd())) {
            head5thList.add(MoscardNipoConstants.CSV_DT_JIGYOU_CD);
            head5thList.add(MoscardNipoConstants.CSV_DT_JIGYOU_NAME);
            head5thList.add(MoscardNipoConstants.CSV_DT_SLAREA_CD);
            head5thList.add(MoscardNipoConstants.CSV_DT_SLAREA_NAME);
            head5thList.add(MoscardNipoConstants.CSV_DT_SIBU_CD);
            head5thList.add(MoscardNipoConstants.CSV_DT_SIBU_NAME);
        // 集計区分が直営店を含むの場合、集計区分設定            
        } else {
            head5thList.add(MoscardNipoConstants.CSV_DT_SHUKEI_KBN);
        }
        // 当年店数、前年店数設定
        head5thList.add(MoscardNipoConstants.CSV_DT_TOUNEN_TENPO_COUNT);
        head5thList.add(MoscardNipoConstants.CSV_DT_ZENNEN_TENPO_COUNT);
        // 前年同月営業日補正の場合、前年比対象・店数、前年店数設定	
        if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)){               
            head5thList.add(MoscardNipoConstants.CSV_DT_HOSEI_TENPO_COUNT);
            head5thList.add(MoscardNipoConstants.CSV_DT_HOSEI_ZEN_TENPO_COUNT);
        }
        // 売上、予算、達成率
        head5thList.add(MoscardNipoConstants.CSV_DT_URIAGE);
        head5thList.add(MoscardNipoConstants.CSV_DT_YOSAN);
        head5thList.add(MoscardNipoConstants.CSV_DT_TASSEI);
        // 前年データ種別が前年同曜日の場合、前年売上、前年比(売上設定)
        if (NipoZennenDataShubetu.CODE_DOYO.equals(zenDataShu)) {
            head5thList.add(MoscardNipoConstants.CSV_DT_URIAGE_ZEN);
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HI_URIAGE);
        // 前年データ種別が前年同曜日以外の場合
        } else {
            // 前年同月営業日補正の場合、前年比対象・売上、前年売上設定
            if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)){               
                head5thList.add(MoscardNipoConstants.CSV_DT_HOSEI_URIAGE);
                head5thList.add(MoscardNipoConstants.CSV_DT_HOSEI_URIAGE_ZEN);
            // 前年同月営業日補正以外の場合、前年売上設定
            } else {
                head5thList.add(MoscardNipoConstants.CSV_DT_URIAGE_ZEN);
            }
            // 前年比(売上)、売上平均、予算平均、予算差
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HI_URIAGE);            
            head5thList.add(MoscardNipoConstants.CSV_DT_URIAGE_AVE);
            head5thList.add(MoscardNipoConstants.CSV_DT_YOSAN_AVE);
            head5thList.add(MoscardNipoConstants.CSV_DT_YOSAN_DIFF);    
            // 前年同月営業日補正の場合、前年比対象・売上平均、前年売上平均設定
            if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)){               
                head5thList.add(MoscardNipoConstants.CSV_DT_HOSEI_URIAGE_AVE);
                head5thList.add(MoscardNipoConstants.CSV_DT_HOSEI_ZEN_URIAGE_AVE);
            // 前年同月営業日補正の場合、前年売上平均設定                
            } else {
                head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_URIAGE_AVE);
            }
            // 前年差(売上)
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_DIFF_URIAGE);
        }
        // 客数
        head5thList.add(MoscardNipoConstants.CSV_DT_KYAKUSU);        
        // 前年同月営業日補正の場合、前年比対象・客数、前年客数設定 
        if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)){               
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
        if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)){
            head5thList.add(MoscardNipoConstants.CSV_DT_HOSEI_KYAKU_TANKA);
            head5thList.add(MoscardNipoConstants.CSV_DT_HOSEI_KYAKU_TANKA_ZEN);
        // 前年同月営業日補正以外の場合、前年客単価設定
        } else {
            head5thList.add(MoscardNipoConstants.CSV_DT_KYAKU_TANKA_ZEN);
        }
        // 前年比(客単価)
        head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HI_KYAKU_TANKA);
        // 前年データ種別が前年同曜日以外の場合        
        if (!NipoZennenDataShubetu.CODE_DOYO.equals(zenDataShu)) {
            // 客数平均
            head5thList.add(MoscardNipoConstants.CSV_DT_KYAKUSU_AVE);    
            // 前年同月営業日補正の場合、前年比対象・客数平均、前年客数平均
            if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)){               
                head5thList.add(MoscardNipoConstants.CSV_DT_HOSEI_KYAKUSU_AVE);
                head5thList.add(MoscardNipoConstants.CSV_DT_HOSE_ZEN_KYAKUSU_AVE);
            // 前年同月営業日補正以外の場合、前年客数平均
            } else {
                head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_KYAKUSU_AVE);
            }
            // 前年差(客数)
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_DIFF_KYAKUSU);       
        } 
        //発行枚数
        head5thList.add(MoscardNipoConstants.CSV_DT_ISSUE_CNT);
        // 前年データ種別が前年同月同曜の時
        if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_ISSUE_CNT);
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_ZENISSUE_CNT);
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_URIHI_ISSUE_CNT);
//                head5thList.add(MoscardNipoConstants.CSV_DT_ISSUE_CNT_AVE);
//                head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_ISSUE_CNT_AVE);
//                head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_ZENISSUE_CNT_AVE);
//                head5thList.add(MoscardNipoConstants.CSV_DT_ISSUE_CNT_DIFF);
        } else {
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_ISSUE_CNT);
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_URIHI_ISSUE_CNT);
            if (!NipoZennenDataShubetu.CODE_DOYO.equals(zenDataShu)) {
//                head5thList.add(MoscardNipoConstants.CSV_DT_ISSUE_CNT_AVE);
//                head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_ISSUE_CNT_AVE);
//                head5thList.add(MoscardNipoConstants.CSV_DT_ISSUE_CNT_DIFF);
            }
        }
        //チャージ金額
        head5thList.add(MoscardNipoConstants.CSV_DT_CHARGEKIN);
        head5thList.add(MoscardNipoConstants.CSV_DT_URIHI_CHARGEKIN);
        // 前年データ種別が前年同月同曜の時
        if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_CHARGEKIN);
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_ZENCHARGEKIN);
            head5thList.add(MoscardNipoConstants.CSV_DT_ZENHI_CHARGEKIN);
//                head5thList.add(MoscardNipoConstants.CSV_DT_CHARGEKIN_AVE);
//                head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKASKU_CHARGEKIN_AVE);
//                head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKASKU_ZENCHARGEKIN_AVE);
//                head5thList.add(MoscardNipoConstants.CSV_DT_CHARGEKIN_DIFF);
        } else {
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_CHARGEKIN);
            head5thList.add(MoscardNipoConstants.CSV_DT_ZENHI_CHARGEKIN);
            if (!NipoZennenDataShubetu.CODE_DOYO.equals(zenDataShu)) {
//                head5thList.add(MoscardNipoConstants.CSV_DT_CHARGEKIN_AVE);
//                head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_CHARGEKIN_AVE);
//                head5thList.add(MoscardNipoConstants.CSV_DT_CHARGEKIN_DIFF);
            }
        }
        
        //チャージ件数
        head5thList.add(MoscardNipoConstants.CSV_DT_CHARGEKEN);
        head5thList.add(MoscardNipoConstants.CSV_DT_URIHI_CHARGEKEN);
        if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_CHARGEKEN);
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_ZENCHARGEKEN);
        } else {
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_CHARGEKEN);
        } 
        head5thList.add(MoscardNipoConstants.CSV_DT_ZENHI_CHARGEKEN);//前年比(チャージ件数)
        //チャージ単価
        head5thList.add(MoscardNipoConstants.CSV_DT_CHARGE_TANKA);
        if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_CHARGE_TANKA);
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_ZENCHARGE_TANKA);
        } else {
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_CHARGE_TANKA);
        }
        head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_TANKAHI);//前年比(チャージ単価)          

        // 前年データ種別が前年同月同曜の時
//        if (!NipoZennenDataShubetu.CODE_DOYO.equals(zenDataShu)) {    
//            head5thList.add(MoscardNipoConstants.CSV_DT_CHARGEKEN_AVE);
//            if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
//                head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_CHARGEKEN_AVE);
//                head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_ZENCHARGEKEN_AVE);
//                head5thList.add(MoscardNipoConstants.CSV_DT_CHARGEKEN_DIFF);                
//            } else {
//                head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_CHARGEKEN_AVE);
//                head5thList.add(MoscardNipoConstants.CSV_DT_CHARGEKEN_DIFF);                
//            }
//        }
        //決済金額
        head5thList.add(MoscardNipoConstants.CSV_DT_KESSAI_KIN);
        head5thList.add(MoscardNipoConstants.CSV_DT_URIHI_KESSAI_KIN);
        // 前年データ種別が前年同月同曜の時
        if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_KESSAI_KIN);
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_ZENKESSAI_KIN);
            head5thList.add(MoscardNipoConstants.CSV_DT_ZENHI_KESSAI_KIN);
//                head5thList.add(MoscardNipoConstants.CSV_DT_KESSAI_KIN_AVG);
//                head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_KESSAI_KIN_AVG);
//                head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_ZENKESSAI_KIN_AVG);
//                head5thList.add(MoscardNipoConstants.CSV_DT_CHARGEKIN_DIFF);
        } else {
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_KESSAI_KIN);
            head5thList.add(MoscardNipoConstants.CSV_DT_ZENHI_KESSAI_KIN);
            if (!NipoZennenDataShubetu.CODE_DOYO.equals(zenDataShu)) {
//                head5thList.add(MoscardNipoConstants.CSV_DT_KESSAI_KIN_AVG);
//                head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_KESSAI_KIN_AVG);
//                head5thList.add(MoscardNipoConstants.CSV_DT_KESSAI_KIN_DIFF);
            }
        }

        //チャージ件数
        head5thList.add(MoscardNipoConstants.CSV_DT_KESSAI_KEN);
        head5thList.add(MoscardNipoConstants.CSV_DT_URIHI_KESSAI_KEN);
        if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_KESSAI_KEN);
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_ZENKESSAI_KEN);
        } else {
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_KESSAI_KEN);
        }
        head5thList.add(MoscardNipoConstants.CSV_DT_ZENHI_KESSAI_KEN);//前年比(決済件数)          
        //チャージ単価
        head5thList.add(MoscardNipoConstants.CSV_DT_KESSAI_TANKA);
        head5thList.add(MoscardNipoConstants.CSV_DT_TANKAHI_KESSAI_TANKA);
        if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_KESSAI_TANKA);
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_ZENKESSAI_TANKA);
        } else {
            head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_KESSAI_TANKA);
        }
        head5thList.add(MoscardNipoConstants.CSV_DT_ZENHI_KESSAI_TANKA);//前年比(決済単価)

        // 前年データ種別が前年同月同曜の時
//        if (!NipoZennenDataShubetu.CODE_DOYO.equals(zenDataShu)) {
//            head5thList.add(MoscardNipoConstants.CSV_DT_KESSAI_KEN_AVG);
//            if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
//                head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_KESSAI_KEN_AVG);
//                head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_HIKAKU_ZENKESSAI_KEN_AVG);
//                head5thList.add(MoscardNipoConstants.CSV_DT_KESSAI_KEN_DIFF);
//            } else {
//                head5thList.add(MoscardNipoConstants.CSV_DT_ZEN_KESSAI_KEN_AVG);
//                head5thList.add(MoscardNipoConstants.CSV_DT_KESSAI_KEN_DIFF);                
//            }
//        }
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
     * CSV出力用売上・予算情報を取得する
     * @param dto 条件部情報Dto
     * @return Map CSV出力用売上・予算情報
     */
    private Map searchSibuUriageInfo(NipoRefConditionParameterDto dto) {
        //検索条件の取得
        Map paramMap = getSearchLogic().getSearchData(dto);
 
        // 支部マスタの取得
        List sibuList = getViewSibuInfoLogic().execute(paramMap);
        
        // 売上情報の取得
        List uriageList = getUriageInfoLogic().execute(paramMap);
        
        List honbuUriageList =getHonbuUriageInfoLogic().execute(paramMap); 
        
        Map uriYosanMap = new HashMap();
        uriYosanMap.put(MoscardNipoConstants.MAP_SIBU_LIST, sibuList);
        uriYosanMap.put(MoscardNipoConstants.MAP_URIAGE_LIST, uriageList);
        uriYosanMap.put(MoscardNipoConstants.MAP_HONBUURIAGE_LIST, honbuUriageList);
        uriYosanMap.put(MoscardNipoConstants.MAP_SHUKEI_KBN, dto.getShukeiKbnCd());
        uriYosanMap.put(MoscardNipoConstants.MAP_PARAMS, paramMap);
                
        // 前年データ種別が前年同月営業日補正の場合
        List hoseiUriList = new ArrayList();
        if (NipoZennenDataShubetu.CODE_HOSEI.equals((String) paramMap.get(MoscardNipoConstants.MAP_DATASHU))) {
            paramMap.put(MoscardNipoConstants.MAP_DATASHU, NipoZennenDataShubetu.CODE_DOGETU);
// 2008/03/21 update start CSVの前年比（売上）が画面と一致していない問題の対応
//  　売上と前年比対象売上が逆になっているので、上で取得した物を前年比対象売上とし
//　　前年同月売上を取得する
            //hoseiUriList = getUriageInfoLogic().execute(paramMap);
            hoseiUriList = uriageList;
            uriageList = getUriageInfoLogic().execute(paramMap);
            uriYosanMap.put(MoscardNipoConstants.MAP_URIAGE_LIST, uriageList);
// 2008/03/21 update end            
            uriYosanMap.put(MoscardNipoConstants.MAP_HOSE_URI_LIST, hoseiUriList);
            honbuUriageList = getHonbuUriageInfoLogic().execute(paramMap);
            paramMap.put(MoscardNipoConstants.MAP_DATASHU, NipoZennenDataShubetu.CODE_HOSEI);
            uriYosanMap.put(MoscardNipoConstants.MAP_HONBU_HOSEI_URIAGE_LIST, getHonbuUriageInfoLogic().execute(paramMap));
        }          
        uriYosanMap.put(MoscardNipoConstants.MAP_SIBU_LIST, sibuList);
        uriYosanMap.put(MoscardNipoConstants.MAP_URIAGE_LIST, uriageList);
        uriYosanMap.put(MoscardNipoConstants.MAP_HONBUURIAGE_LIST, honbuUriageList);
        uriYosanMap.put(MoscardNipoConstants.MAP_SHUKEI_KBN, dto.getShukeiKbnCd());
        uriYosanMap.put(MoscardNipoConstants.MAP_PARAMS, paramMap);
 
        return getViewUriYosanLogic().execute(uriYosanMap);
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
    
    /**
     * 本部売上情報取得ロジックを取得する
     * @return honbuUriageInfoLogic 本部売上予算情報取得ロジック
     */
    public HonbuUriageInfoLogic getHonbuUriageInfoLogic() {
        return this.honbuUriageInfoLogic;
    }

    /**
     *  本部売上予算情報取得ロジックを設定する
     * @param honbuUriageInfoLogic 本部売上予算情報取得ロジック
     */
    public void setHonbuUriageInfoLogic( HonbuUriageInfoLogic honbuUriageInfoLogic) {
        this.honbuUriageInfoLogic = honbuUriageInfoLogic;
    }    
}