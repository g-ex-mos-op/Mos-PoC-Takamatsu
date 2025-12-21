package jp.co.isid.mos.bird.bizreport.takuhainiporef.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.code.NipoZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.common.code.ShukeiKbn;
import jp.co.isid.mos.bird.bizreport.common.code.TaishoTenpo;
import jp.co.isid.mos.bird.bizreport.common.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.code.UserType;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;
import jp.co.isid.mos.bird.bizreport.common.util.NipoRefUtil;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.common.EigyoNipoConstants;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.common.TakuhaiNipoCommon;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.common.TakuhaiNipoConstants;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.entity.MiseDetail;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.logic.MiseInfoLogic;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * 店舗別宅配売上情報CSVダウンロードロジック
 *
 * @author xjung
 */
public class MiseCsvOutputLogicImpl implements CsvOutputLogic {
    /** 店舗別宅配売上情報CSVダウンロードロジックID */
	public static final String LOGIC_ID = "BBR003L04";

	/** 店舗別宅配売上情報取得ロジック */
    private MiseInfoLogic miseInfoLogic;
 
	/**
	 * CSVファイル名称を取得する
	 * @param	csvOutputDto	CSV出力DTO
	 * @return	String 			CSVファイル名称
	 */
	public String getFileName(CsvOutputDto csvOutputDto) {
		return TakuhaiNipoConstants.CSV_FILE_NAME_SIBU;
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
	 * @param	csvOutputDto	CSV出力DTO
	 * @return	List 			CSV出力データリスト
	 */
	public List getOutputData(CsvOutputDto csvOutputDto) {
		// 数値タイプの文字列フォーマッタ(定数)
		NumericFormatter numF = new NumericFormatter(true, 0, true);
		// 数値タイプの文字列フォーマッタ(小数点以下２桁)
		NumericFormatter numFtdgt2 = new NumericFormatter(true, 2);

        // CSV出力データリスト、ヘッダー部情報取得
        List outputList = getHeaderData(csvOutputDto);

		// 宅配売上日報条件部DTO取得
		NipoRefConditionParameterDto dto = (NipoRefConditionParameterDto) csvOutputDto;

		// 店舗別の宅配売上情報を取得
		Map resultMap = getMiseInfoLogic().execute(dto);

		// 店舗別の宅配売上情報リスト
		List miseInfoList = (List) resultMap.get(TakuhaiNipoConstants.MAP_MISE_RST_LIST);

		// 前年データ種別
		String zenDataShu = null;
		// オーナーユーザの場合
		if (UserType.ONER.equals(dto.getBirdUserInfo().getMstUser().getUserTypeCd())) {
			zenDataShu = dto.getZenDataZennenOthCd();
        // 本部ユーザの場合
		} else {
			zenDataShu = TenpoShubetu.CODE_ZENNEN.equals(dto.getTenpoShubetuCd()) ?
		    	dto.getZenDataZennenCd() : dto.getZenDataZennenOthCd();
		}

        // データ部情報取得 
		for (int i = 0; i < miseInfoList.size(); i++) {
    		List rowList = new ArrayList();
    		MiseDetail info = (MiseDetail) miseInfoList.get(i);
            /** オーナーユーザの場合 *****************************************************/
            if (UserType.ONER.equals(dto.getBirdUserInfo().getMstUser().getUserTypeCd())) {
    			// 店ｺｰﾄﾞがNULLの場合、空白、総合計
                if (TakuhaiNipoCommon.isNull(info.getMiseCd())) {
                    rowList.add(TakuhaiNipoConstants.EMPTY);
                    rowList.add(TakuhaiNipoConstants.LABEL_TOTAL_SUM);
                //  店ｺｰﾄﾞがNOT NULの場合、店コード、店名称
                } else {
                    rowList.add(TakuhaiNipoCommon.setEmpty(info.getMiseCd()));
                    rowList.add(TakuhaiNipoCommon.setEmpty(info.getMiseName()));                    
                }
        	/** 本部ユーザの場合 *********************************************************/
            } else {
                
                /* 2008/12/09追加 
                 * 集計区分で『SV指定(担当店一覧)』が選択された場合
                 * 支部合計：画面用店コード・名称、行CSSクラス、比TDCSSクラス
                 */
                if(ShukeiKbn.SV_CD.equals(dto.getShukeiKbnCd())){
                    // 支部コード・名称、ブロックコード・名称、店コード・名称
                    rowList.add(TakuhaiNipoCommon.setEmpty(info.getSibuCd()));
                    rowList.add(TakuhaiNipoCommon.setEmpty(info.getSibuName()));
                    // モスフードサービスのみブロックコードがある
                    if (dto.getCompanyCd().equals(TakuhaiNipoConstants.MOS_COMPANY_CD)){
                        rowList.add(TakuhaiNipoCommon.setEmpty(info.getBlockCd()));
                        rowList.add(TakuhaiNipoCommon.setEmpty(info.getBlockName()));                        
                    }
                    rowList.add(TakuhaiNipoCommon.setEmpty(info.getMiseCd()));
                    rowList.add(TakuhaiNipoCommon.setEmpty(info.getMiseName()));
                }else{
                   // リンク区分が支部以外の場合
                    if (!TaishoJoken.CODE_SIBU.equals
                            (TakuhaiNipoCommon.getLinkKbnCd(dto.getClassName(), dto.getShukeiKbnCd()))) {
                        // 集計区分が「直営業店を含まない」の場合
                        if (ShukeiKbn.OUT_RC.equals(dto.getShukeiKbnCd())) {
                            // 支部コード
                            rowList.add(TakuhaiNipoCommon.setEmpty(info.getSibuCd()));
                        }
                        // 支部名称
                        rowList.add(TakuhaiNipoCommon.setEmpty(info.getSibuName()));
                    }
                    // 会社コードがモスフードサービ且つ集計区分が直営店を含まないの場合
                    if (dto.getCompanyCd().equals(TakuhaiNipoConstants.MOS_COMPANY_CD)
                            && ShukeiKbn.OUT_RC.equals(dto.getShukeiKbnCd())) {
                        // ブロックコード・名称、店コード・名称
                        rowList.add(TakuhaiNipoCommon.setEmpty(info.getBlockCd()));
                        rowList.add(TakuhaiNipoCommon.setEmpty(info.getBlockName()));
                        rowList.add(TakuhaiNipoCommon.setEmpty(info.getMiseCd()));
                        rowList.add(TakuhaiNipoCommon.setEmpty(info.getMiseName()));
                    // 会社コードがモスフードサービ且つ集計区分が直営店を含まない以外の場合
                    } else {
                        // 店コード・名称
                        rowList.add(TakuhaiNipoCommon.setEmpty(info.getMiseCd()));
                        rowList.add(TakuhaiNipoCommon.setEmpty(TakuhaiNipoCommon.isNull(info.getMiseCd()) ?
                                info.getBlockName() : info.getMiseName()));
                    }
                }
            }
            // 種別                       
            rowList.add(TakuhaiNipoCommon.setEmpty(info.getTakuDetailName()));
            // 前年データ種別が前年同月営業日補正の時
            if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)){
	    		// 宅配日数、前年比対象宅配日数		
				rowList.add(numF.format(String.valueOf(info.getEigyoDaysTaku())));
				rowList.add(numF.format(String.valueOf(info.getEigyoDaysTakuHose())));
				// 売上、前年比対象売上、前年比対象前年実績、前年比(売上)
	    		rowList.add(numF.format(info.getUriage()));
	    		rowList.add(numF.format(info.getUriageHose()));
	    		rowList.add(numF.format(info.getUriageZen()));
	    		rowList.add(numFtdgt2.format(info.getZenHiUriage()));
				// 宅配売上、前年比対象宅配売上、前年比対象前年宅配売上、前年比(宅配売上)
	    		rowList.add(numF.format(info.getUriageTaku()));
	    		rowList.add(numF.format(info.getUriageTakuHose()));
	    		rowList.add(numF.format(info.getUriageTakuZen()));
	    		rowList.add(numFtdgt2.format(info.getZenHiUriageTaku()));
				// 構成比(売上)、宅配平均売上、前年比対象宅配平均売上
	    		rowList.add(numFtdgt2.format(info.getKouseiHiUri()));
	    		rowList.add(numF.format(info.getTakuHeikinUri()));
	    		rowList.add(numF.format(info.getTakuHeikinUriHose()));
				// 客数、前年比対象客数、前年比対象前年件数、前年比(客数)
	    		rowList.add(numF.format(info.getKyakusu()));
	    		rowList.add(numF.format(info.getKyakusuHose()));
	    		rowList.add(numF.format(info.getKyakusuZen()));
	    		rowList.add(numFtdgt2.format(info.getZenHiKyakusu()));
				// 宅配件数、前年比対象(宅配件数)、前年比対象、前年比(宅配件数)
                rowList.add(numF.format(info.getKyakusuTaku()));
	    		rowList.add(numF.format(info.getKyakusuTakuHose()));
	    		rowList.add(numF.format(info.getKyakusuTakuZen()));
	    		rowList.add(numFtdgt2.format(info.getZenHiKyakusuTaku()));
	    		// 構成比・件数、宅配平均件数、前年比対象宅配平均件数
	    		rowList.add(numFtdgt2.format(info.getKouseiHiKyaku()));
	    		rowList.add(numF.format(info.getTakuHeikinKyakusu()));
	    		rowList.add(numF.format(info.getTakuHeikinKyakusuHose()));
				// 客単価、前年比対象客単価、前年比対象前年客単価、前年比(客単価)
	    		rowList.add(numF.format(info.getKyakuTanka()));
	    		rowList.add(numF.format(info.getKyakuTankaHose()));
	    		rowList.add(numF.format(info.getZenKyakuTanka()));
	    		rowList.add(numFtdgt2.format(info.getZenHiKyakuTanka()));

    		// 前年データ種別が前年同月営業日補正以外の時
    		} else {
				// 宅配日数
				rowList.add(String.valueOf(info.getEigyoDaysTaku()));
				// 売上、前年実績、前年比(売上)
	    		rowList.add(numF.format(info.getUriage()));
	    		rowList.add(numF.format(info.getUriageZen()));
	    		rowList.add(numFtdgt2.format(info.getZenHiUriage()));
				// 宅配売上、前年宅配実績、前年比(宅配)、構成比・売上、宅配平均売上
	    		rowList.add(numF.format(info.getUriageTaku()));
	    		rowList.add(numF.format(info.getUriageTakuZen()));
	    		rowList.add(numFtdgt2.format(info.getZenHiUriageTaku()));
	    		rowList.add(numFtdgt2.format(info.getKouseiHiUri()));
				rowList.add(numF.format(info.getTakuHeikinUri()));
				// 客数、前年客数、前年比(客数)
	    		rowList.add(numF.format(info.getKyakusu()));
	    		rowList.add(numF.format(info.getKyakusuZen()));
	    		rowList.add(numFtdgt2.format(info.getZenHiKyakusu()));
                // 宅配件数、前年宅配件数、前年比(宅配件数)、構成比・件数、宅配平均件数
	    		rowList.add(numF.format(info.getKyakusuTaku()));
	    		rowList.add(numF.format(info.getKyakusuTakuZen()));
	    		rowList.add(numFtdgt2.format(info.getZenHiKyakusuTaku()));
	    		rowList.add(numFtdgt2.format(info.getKouseiHiKyaku()));
	    		rowList.add(numF.format(info.getTakuHeikinKyakusu()));
				// 客単価、前年客単価、前年比(客単価)
	    		rowList.add(numF.format(info.getKyakuTanka()));
	    		rowList.add(numF.format(info.getZenKyakuTanka()));
	    		rowList.add(numFtdgt2.format(info.getZenHiKyakuTanka()));
            }

	    	outputList.add(rowList);
		}

		return outputList;
	}

	/**
	 * ヘッダー部情報を作成する
	 * @param	csvOutputDto	CSV出力DTO
	 * @return	List 			ヘッダー部情報リスト
	 */
	private List getHeaderData(CsvOutputDto csvOutputDto) {
		// ヘッダー部情報リスト
		List headerList = new ArrayList();

		// 宅配売上日報条件部DTO取得
		NipoRefConditionParameterDto dto = (NipoRefConditionParameterDto) csvOutputDto;
 
        // リンク区分コード
        String linkKbnCd = TakuhaiNipoCommon.getLinkKbnCd(dto.getClassName(), dto.getShukeiKbnCd());

		// 前年データ種別
		String zenDataShu = null;

        //ヘッダ部作成
        /** オーナーユーザの場合 *****************************************************/
        if (UserType.ONER.equals(dto.getBirdUserInfo().getMstUser().getUserTypeCd())) {
            // 前年データ種別取得
            zenDataShu = dto.getZenDataZennenOthCd();

            // １行目:会社
            List headRow1List = new ArrayList();
            headRow1List.add(TakuhaiNipoConstants.CSV_HD_COMPANY);
            headRow1List.add(dto.getCompanyName());

            // ２行目：前年データ種別
            List headRow2List = new ArrayList();
            headRow2List.add(TakuhaiNipoConstants.CSV_HD_ZEN_DATA_SHU);
            headRow2List.add(NipoZennenDataShubetu.getName
                (zenDataShu, dto.getBirdUserInfo().getMstUser().getUserTypeCd()));
            // ３行目：対象期間
            List headRow3List = new ArrayList();
            headRow3List.add(TakuhaiNipoConstants.CSV_HD_TAISHO_KIKAN);
            headRow3List.add(NipoRefUtil.getCsvTaishoKikan(dto));
            
            headerList.add(headRow1List);
            headerList.add(headRow2List);
            headerList.add(headRow3List);
            
        /** 本部ユーザの場合 *********************************************************/    
        } else {
        	String[] linkParams = TakuhaiNipoCommon.getLinkParams(dto.getLinkParams());
			// 前年データ種別取得
			zenDataShu = TenpoShubetu.CODE_ZENNEN.equals(dto.getTenpoShubetuCd())
				? dto.getZenDataZennenCd() : dto.getZenDataZennenOthCd();
            // １行目:会社、対象店舗
            List headRow1List = new ArrayList();
            headRow1List.add(TakuhaiNipoConstants.CSV_HD_COMPANY);
            headRow1List.add(dto.getCompanyName());
            headRow1List.add(TakuhaiNipoConstants.EMPTY);
            headRow1List.add(TakuhaiNipoConstants.CSV_HD_TAISHO_TENPO);
            headRow1List.add(TaishoTenpo.getName(dto.getTaishoTenpoCd()));

            // ２行目:店舗種別、対象期間
            List headRow2List = new ArrayList();
            headRow2List.add(TakuhaiNipoConstants.CSV_HD_TENPO_SHU);
            headRow2List.add(TenpoShubetu.getName(dto.getTenpoShubetuCd()));
            headRow2List.add(TakuhaiNipoConstants.EMPTY);
            headRow2List.add(TakuhaiNipoConstants.CSV_HD_TAISHO_KIKAN);
            headRow2List.add(NipoRefUtil.getCsvTaishoKikan(dto));
     
            // ３行目:集計区分、前年データ種別、リンク区分
            List headRow3List = new ArrayList();
            headRow3List.add(TakuhaiNipoConstants.CSV_HD_SHUKEI_KBN);
            /* 2008/12/09追加 SV指定の場合 */
            if(ShukeiKbn.SV_CD.equals(dto.getShukeiKbnCd())){
                headRow3List.add(TakuhaiNipoConstants.CSV_HD_SV);
            }else{
                headRow3List.add(ShukeiKbn.getName(dto.getShukeiKbnCd()));    
            }
            headRow3List.add(TakuhaiNipoConstants.EMPTY);
            headRow3List.add(TakuhaiNipoConstants.CSV_HD_ZEN_DATA_SHU);
            headRow3List.add(NipoZennenDataShubetu.getName
                (zenDataShu, dto.getBirdUserInfo().getMstUser().getUserTypeCd()));
            headRow3List.add(TakuhaiNipoConstants.EMPTY);
            // リンク区分
            StringBuffer linkInfo = new StringBuffer();
            if (TaishoJoken.CODE_SIBU.equals(linkKbnCd)) {
                linkInfo.append(TakuhaiNipoConstants.CSV_HD_SIBU);
                linkInfo.append(TakuhaiNipoCommon.setEmpty(dto.getSibuName()));
            } else if (TaishoJoken.CODE_SLAREA.equals(linkKbnCd)) {
                linkInfo.append(TakuhaiNipoConstants.CSV_HD_SLAREA);
                linkInfo.append(TakuhaiNipoCommon.setEmpty(dto.getSibuName()));
            } else if (TaishoJoken.CODE_JIGYOU.equals(linkKbnCd) ) {
                linkInfo.append(TakuhaiNipoConstants.CSV_HD_JIGYOU);
                linkInfo.append(TakuhaiNipoCommon.setEmpty(dto.getSibuName()));
            } else if (TaishoJoken.CODE_HONBU.equals(linkKbnCd) ) {
                linkInfo.append(TakuhaiNipoConstants.CSV_HD_HONBU);
                linkInfo.append(TakuhaiNipoCommon.setEmpty(dto.getSibuName()));
            } else if (TaishoJoken.CODE_ALL.equals(linkKbnCd)) {
                linkInfo.append(TakuhaiNipoConstants.CSV_HD_ALL);
            }
            /* 2008/12/09追加
             * SV指定の場合、SVコード、SV名称を表示 */
            else if(ShukeiKbn.SV_CD.equals(dto.getShukeiKbnCd())){
                    linkInfo.append(TakuhaiNipoConstants.CSV_HD_SV_TANTO);
                    linkInfo.append(TakuhaiNipoCommon.setEmpty(dto.getHyojiSvName()));                    
            }
            // 種別区分
            if(!CommonUtil.isNull(linkParams[1])) {
	            linkInfo.append(TakuhaiNipoConstants.SPACE);
	            linkInfo.append(TakuhaiNipoCommon.setEmpty(linkParams[1]));
            }

            headRow3List.add(linkInfo.toString());

			headerList.add(headRow1List);
			headerList.add(headRow2List);
			headerList.add(headRow3List);
		}

		// ４行目
		List headRow4List = new ArrayList();

		// ５行目:データ部作成
		List headRow5List = new ArrayList();
		// 本部ユーザの場合
        if (UserType.HONBU.equals(dto.getBirdUserInfo().getMstUser().getUserTypeCd())) {
            
            // 集計区分で『SV指定(担当店一覧)』が選択された場合 2008/12/09追加 */
            if(ShukeiKbn.SV_CD.equals(dto.getShukeiKbnCd())){
                // 支部コード、支部名称
                headRow5List.add(TakuhaiNipoConstants.CSV_DT_SIBU_CD);
                headRow5List.add(TakuhaiNipoConstants.CSV_DT_SIBU_NAME);                
            }
            // リンク区分が支部以外の場合
            else if (!TaishoJoken.CODE_SIBU.equals(linkKbnCd)) {
                // 集計区分が「直営業店を含む」の場合
                if (ShukeiKbn.IN_RC.equals(dto.getShukeiKbnCd())) {
                    headRow5List.add(TakuhaiNipoConstants.CSV_DT_SHUKEI_KBN);
                // 集計区分が「直営業店を含まない」の場合
                } else {
                    // 支部コード、支部名称
                    headRow5List.add(TakuhaiNipoConstants.CSV_DT_SIBU_CD);
                    headRow5List.add(TakuhaiNipoConstants.CSV_DT_SIBU_NAME);
                }
            }
            // モスフードサービスのみブロックコードがある
            if (TakuhaiNipoConstants.MOS_COMPANY_CD.equals(dto.getCompanyCd())
                    && (ShukeiKbn.OUT_RC.equals(dto.getShukeiKbnCd()) 
                            || ShukeiKbn.SV_CD.equals(dto.getShukeiKbnCd()))) {
                headRow5List.add(EigyoNipoConstants.CSV_DT_BLOCK_CD);
                headRow5List.add(EigyoNipoConstants.CSV_DT_BLOCK_NAME);
            }
        }
        // 店コード、店名称
        headRow5List.add(TakuhaiNipoConstants.CSV_DT_MISE_CD);
        headRow5List.add(TakuhaiNipoConstants.CSV_DT_MISE_NAME);
		// 種別
        headRow5List.add(TakuhaiNipoConstants.CSV_DT_TAKUHAI_KBN);
		// 前年データ種別が前年同月営業日補正のとき
		if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)){
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_DOGETU_EIGYO_DAYS);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_HOSE_EIGYO_DAYS);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_URIAGE);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_HOSE_URIAGE);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_HOSE_URIAGE_ZEN);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_ZEN_HI_URIAGE);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_URIAGE_TAKU);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_HOSE_URIAGE_TAKU);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_HOSE_URIAGE_TAKU_ZEN);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_ZEN_HI_TAKU_URI);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_KOSEIHI_URI);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_TAKU_AVE_URI);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_HOSE_TAKU_AVE_URI);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_KYAKUSU);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_HOSE_KYAKUSU);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_HOSE_KYAKUSU_ZEN);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_ZEN_HI_KYAKUSU);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_KYAKUSU_TAKU);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_HOSE_KYAKUSU_TAKU);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_HOSE_KYAKUSU_TAKU_ZEN);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_ZEN_HI_TAKU_KYA);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_KOSEIHI_KYA);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_TAKU_AVE_KYAKUSU);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_HOSE_TAKU_AVE_KYAKUSU);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_KYAKU_TANKA);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_HOSE_KYAKU_TANKA);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_HOSE_KYAKU_TANKA_ZEN);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_ZEN_HI_KYAKU_TANKA);

		// 前年データ種別が前年同月営業日補正以外の時
		} else {
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_EIGYO_DAYS);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_URIAGE);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_URIAGE_ZEN);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_ZEN_HI_URIAGE);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_URIAGE_TAKU);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_URIAGE_TAKU_ZEN);
            headRow5List.add(TakuhaiNipoConstants.CSV_DT_ZEN_HI_TAKU_URI);
			headRow5List.add(TakuhaiNipoConstants.CSV_DT_KOSEIHI_URI);
			headRow5List.add(TakuhaiNipoConstants.CSV_DT_TAKU_AVE_URI);
			headRow5List.add(TakuhaiNipoConstants.CSV_DT_KYAKUSU);
			headRow5List.add(TakuhaiNipoConstants.CSV_DT_KYAKUSU_ZEN);
			headRow5List.add(TakuhaiNipoConstants.CSV_DT_ZEN_HI_KYAKUSU);
			headRow5List.add(TakuhaiNipoConstants.CSV_DT_KYAKUSU_TAKU);
			headRow5List.add(TakuhaiNipoConstants.CSV_DT_KYAKUSU_TAKU_ZEN);
			headRow5List.add(TakuhaiNipoConstants.CSV_DT_ZEN_HI_TAKU_KYA);
			headRow5List.add(TakuhaiNipoConstants.CSV_DT_KOSEIHI_KYA);
			headRow5List.add(TakuhaiNipoConstants.CSV_DT_TAKU_AVE_KYAKUSU);
			headRow5List.add(TakuhaiNipoConstants.CSV_DT_KYAKU_TANKA);
			headRow5List.add(TakuhaiNipoConstants.CSV_DT_KYAKU_TANKA_ZEN);
			headRow5List.add(TakuhaiNipoConstants.CSV_DT_ZEN_HI_KYAKU_TANKA);
		}

		headerList.add(headRow4List);
		headerList.add(headRow5List);
 
		return headerList;
	}

	/**
	 * 店舗別宅配売上情報取得ロジックを取得する
	 * @return	MiseInfoLogic 店舗別宅配売上情報取得ロジック
	 */
	public MiseInfoLogic getMiseInfoLogic() {
		return miseInfoLogic;
	}

	/**
	 * 店舗別宅配売上情報取得ロジックを設定する
	 * @param miseInfoLogic 店舗別宅配売上情報取得ロジック
	 */
	public void setMiseInfoLogic(MiseInfoLogic miseInfoLogic) {
		this.miseInfoLogic = miseInfoLogic;
	}
}