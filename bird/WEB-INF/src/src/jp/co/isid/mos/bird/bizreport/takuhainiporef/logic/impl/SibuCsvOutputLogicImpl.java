package jp.co.isid.mos.bird.bizreport.takuhainiporef.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.code.NipoZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.common.code.ShukeiKbn;
import jp.co.isid.mos.bird.bizreport.common.code.TaishoTenpo;
import jp.co.isid.mos.bird.bizreport.common.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.common.BizReportConstants;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;
import jp.co.isid.mos.bird.bizreport.common.util.NipoRefUtil;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.common.TakuhaiNipoCommon;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.common.TakuhaiNipoConstants;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.entity.SibuDetail;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.logic.SibuInfoLogic;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * 支部別宅配売上情報CSVダウンロードロジック
 *
 * @author xjung
 */
public class SibuCsvOutputLogicImpl  implements CsvOutputLogic {
	/** 支部別宅配売上情報CVSダウンロードロジックID */
    public static final String LOGIC_ID = "BBR003L03";

	/** 支部別宅配売上情報取得ロジック */
    private SibuInfoLogic sibuInfoLogic;
	
	/**
	 * CSVファイル名称を取得する
	 * @param	csvOutputDto	CSV出力DTO
	 * @return	String 			CSVファイル名称
	 */
	public String getFileName(CsvOutputDto csvOutputDto) {
   		return TakuhaiNipoConstants.CSV_FILE_NAME;
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

		// 支部別の宅配売上情報を取得
		Map sibuInfoMap = getSibuInfoLogic().execute(dto, true);
 
		// 支部別の宅配売上情報リスト
    	List sibuInfoList = (List) sibuInfoMap.get(TakuhaiNipoConstants.MAP_RST_LIST);

		// 前年データ種別
		String zenDataShu = TenpoShubetu.CODE_ZENNEN.equals(dto.getTenpoShubetuCd()) ?
			dto.getZenDataZennenCd() : dto.getZenDataZennenOthCd();

        // データ部情報取得    
		for (int i = 0; i < sibuInfoList.size(); i++) {
			List rowList = new ArrayList();
			SibuDetail info = (SibuDetail) sibuInfoList.get(i);

            // 集計区分が「直営業店を含む」の場合
            if (ShukeiKbn.IN_RC.equals(dto.getShukeiKbnCd())) {
                if (!TakuhaiNipoCommon.isNull(info.getSibuName())) {
                    rowList.add(TakuhaiNipoCommon.setEmpty(info.getSibuName()));
                } else {
                    rowList.add(TakuhaiNipoCommon.setEmpty(info.getHonbuName()));
                }
                
            // 集計区分が「直営業店を含まない」の場合
            } else {
                // 事業本部コード、事業本部名称
                if (TakuhaiNipoCommon.isNull(info.getJigyouCd())) {
                    // 事業本部コードがNullの場合、本部コード・本部名称を設定
                    rowList.add(TakuhaiNipoCommon.setEmpty(info.getHonbuCd()));
                    rowList.add(TakuhaiNipoCommon.setEmpty(info.getHonbuName()));                       
                } else {
                    // 事業本部コード、事業本部名称設定
                    rowList.add(TakuhaiNipoCommon.setEmpty(info.getJigyouCd())); 
                    rowList.add(TakuhaiNipoCommon.setEmpty(info.getJigyouName()));  
                }
                // エリアコード・名称、支部コード・名称
                rowList.add(TakuhaiNipoCommon.setEmpty(info.getSlareaCd()));
                rowList.add(TakuhaiNipoCommon.setEmpty(info.getSlareaName()));
                rowList.add(TakuhaiNipoCommon.setEmpty(info.getSibuCd()));
                rowList.add(TakuhaiNipoCommon.setEmpty(info.getSibuName()));
            }
			// 種別
			if (BizReportConstants.SUB_TAG_0.equals(dto.getSubTagKbn())
				|| BizReportConstants.SUB_TAG_1.equals(dto.getSubTagKbn())) {
                // サブタグ区分が売上・件数の場合、宅配名称
				rowList.add(TakuhaiNipoCommon.setEmpty(info.getTakuName()));
			} else {
				// サブタグ区分が詳細売上・詳細件数の場合、宅配明細名称
				rowList.add(TakuhaiNipoCommon.setEmpty(info.getTakuDetailName()));  
			}				  		
			// 前年データ種別が前年同月営業日補正の時
			if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)){
				// 当年店舗数、前年比対象店舗数 
				rowList.add(numF.format(String.valueOf(info.getTenpoCount())));
				rowList.add(numF.format(String.valueOf(info.getTenpoCountHose())));
				// 売上、前年比対象売上、前年実績、前年比(売上)		
				rowList.add(numF.format(info.getUriage()));
				rowList.add(numF.format(info.getUriageHose()));
				rowList.add(numF.format(info.getUriageZen()));
				rowList.add(numFtdgt2.format(info.getZenHiUriage()));
				// 宅配売上、前年比対象宅配売上、前年宅配実績、前年比(宅配売上)、構成比(売上)     		
				rowList.add(numF.format(info.getUriageTaku()));
				rowList.add(numF.format(info.getUriageTakuHose()));
				rowList.add(numF.format(info.getUriageTakuZen()));
				rowList.add(numFtdgt2.format(info.getZenHiUriageTaku()));
				rowList.add(numFtdgt2.format(info.getKouseiHiUri()));
				// 客数、前年比対象客数、前年客数、前年比(客数)   		
				rowList.add(numF.format(info.getKyakusu()));
				rowList.add(numF.format(info.getKyakusuHose()));
				rowList.add(numF.format(info.getKyakusuZen()));
				rowList.add(numFtdgt2.format(info.getZenHiKyakusu()));
				// 宅配件数、前年比対象宅配件数、前年宅配件数、前年比(宅配件数)、構成比(件数)  
				rowList.add(numF.format(info.getKyakusuTaku()));
				rowList.add(numF.format(info.getKyakusuTakuHose()));
				rowList.add(numF.format(info.getKyakusuTakuZen()));
				rowList.add(numFtdgt2.format(info.getZenHiKyakusuTaku()));
				rowList.add(numFtdgt2.format(info.getKouseiHiKyaku()));
				// 客単価、前年比対象客単価、前年客単価、前年比(客単価）
				rowList.add(numF.format(info.getKyakuTanka()));
				rowList.add(numF.format(info.getKyakuTankaHose()));
				rowList.add(numF.format(info.getZenKyakuTanka()));
				rowList.add(numFtdgt2.format(info.getZenHiKyakuTanka()));

			// 前年データ種別が前年同月営業日補正以外の時
			} else {
				// 店舗数
				rowList.add(String.valueOf(info.getTenpoCount()));
				// 売上、前年実績、前年比(売上)
				rowList.add(numF.format(info.getUriage()));
				rowList.add(numF.format(info.getUriageZen()));
				rowList.add(numFtdgt2.format(info.getZenHiUriage()));
				// 宅配売上、前年宅配実績、前年比(宅配)、構成比  
				rowList.add(numF.format(info.getUriageTaku()));
				rowList.add(numF.format(info.getUriageTakuZen()));
				rowList.add(numFtdgt2.format(info.getZenHiUriageTaku()));
				rowList.add(numFtdgt2.format(info.getKouseiHiUri()));
				// 客数、前年客数、前年比(客数）   		
				rowList.add(numF.format(info.getKyakusu()));
				rowList.add(numF.format(info.getKyakusuZen()));
				rowList.add(numFtdgt2.format(info.getZenHiKyakusu()));
				// 宅配客数、前年宅配件数、前年比(宅配件数)、構成比 		
				rowList.add(numF.format(info.getKyakusuTaku()));
 				rowList.add(numF.format(info.getKyakusuTakuZen()));
				rowList.add(numFtdgt2.format(info.getZenHiKyakusuTaku()));
				rowList.add(numFtdgt2.format(info.getKouseiHiKyaku()));
				// 客単価、前年客単価、前年比(客単価）   		
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

		// 前年データ種別
		String zenDataShu =  TenpoShubetu.CODE_ZENNEN.equals(dto.getTenpoShubetuCd()) ?
		dto.getZenDataZennenCd() : dto.getZenDataZennenOthCd();

		// １行目:会社、対象店舗
		List head1stList = new ArrayList();
        head1stList.add(TakuhaiNipoConstants.CSV_HD_COMPANY);
        head1stList.add(dto.getCodCompany().getCompanyName());
        head1stList.add(TakuhaiNipoConstants.EMPTY);
        head1stList.add(TakuhaiNipoConstants.CSV_HD_TAISHO_TENPO);
        head1stList.add(TaishoTenpo.getName(dto.getTaishoTenpoCd()));

		// ２行目:店舗種別、対象期間
		List head2ndList = new ArrayList();
        head2ndList.add(TakuhaiNipoConstants.CSV_HD_TENPO_SHU);
        head2ndList.add(TenpoShubetu.getName(dto.getTenpoShubetuCd()));
        head2ndList.add(TakuhaiNipoConstants.EMPTY);
        head2ndList.add(TakuhaiNipoConstants.CSV_HD_TAISHO_KIKAN);
        head2ndList.add(NipoRefUtil.getCsvTaishoKikan(dto));
 
        // ３行目:集計区分、前年データ種別
        List head3rdList = new ArrayList();
        head3rdList.add(TakuhaiNipoConstants.CSV_HD_SHUKEI_KBN);
        head3rdList.add(ShukeiKbn.getName(dto.getShukeiKbnCd()));
        head3rdList.add(TakuhaiNipoConstants.EMPTY);
        head3rdList.add(TakuhaiNipoConstants.CSV_HD_ZEN_DATA_SHU);
        head3rdList.add(NipoZennenDataShubetu.getName
            (zenDataShu, dto.getBirdUserInfo().getMstUser().getUserTypeCd()));
        head3rdList.add(TakuhaiNipoConstants.EMPTY);
        // サブタグ区分が売上・件数の場合、有無別
        if (BizReportConstants.SUB_TAG_0.equals(dto.getSubTagKbn())
            || BizReportConstants.SUB_TAG_1.equals(dto.getSubTagKbn())) {
            head3rdList.add(TakuhaiNipoConstants.CSV_HD_UMU);
        // サブタグ区分が詳細売上・詳細件数の場合、タイプ別
        } else {
            head3rdList.add(TakuhaiNipoConstants.CSV_HD_TYPE);
        }

		// ４行目
		List head4thList = new ArrayList();

		// ５行目:データ部ヘッダ作成
		List head5thList = new ArrayList();
        // 集計区分が「直営業店を含む」の場合
        if (ShukeiKbn.IN_RC.equals(dto.getShukeiKbnCd())) {
            head5thList.add(TakuhaiNipoConstants.CSV_DT_SHUKEI_KBN);
        // 集計区分が「直営業店を含まない」の場合
        } else {
    		head5thList.add(TakuhaiNipoConstants.CSV_DT_JIGYOU_CD);		
    		head5thList.add(TakuhaiNipoConstants.CSV_DT_JIGYOU_NAME);		
    		head5thList.add(TakuhaiNipoConstants.CSV_DT_SLAREA_CD);		
    		head5thList.add(TakuhaiNipoConstants.CSV_DT_SLAREA_NAME);		
    		head5thList.add(TakuhaiNipoConstants.CSV_DT_SIBU_CD);		
    		head5thList.add(TakuhaiNipoConstants.CSV_DT_SIBU_NAME);
        }
		head5thList.add(TakuhaiNipoConstants.CSV_DT_TAKUHAI_KBN);

		// 前年データ種別が前年同月営業日補正のとき
		if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)){
			head5thList.add(TakuhaiNipoConstants.CSV_DT_DOGETU_TENPO_COUNT);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_HOSE_TENPO_COUNT);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_URIAGE);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_HOSE_URIAGE);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_HOSE_URIAGE_ZEN);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_ZEN_HI_URIAGE);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_URIAGE_TAKU);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_HOSE_URIAGE_TAKU);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_HOSE_URIAGE_TAKU_ZEN);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_ZEN_HI_TAKU_URI);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_KOSEIHI_URI);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_KYAKUSU);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_HOSE_KYAKUSU);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_HOSE_KYAKUSU_ZEN);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_ZEN_HI_KYAKUSU);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_KYAKUSU_TAKU);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_HOSE_KYAKUSU_TAKU);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_HOSE_KYAKUSU_TAKU_ZEN);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_ZEN_HI_TAKU_KYA);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_KOSEIHI_KYA);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_KYAKU_TANKA);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_HOSE_KYAKU_TANKA);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_HOSE_KYAKU_TANKA_ZEN);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_ZEN_HI_KYAKU_TANKA);

		// 前年データ種別が前年同月営業日補正以外のとき
		} else {
			head5thList.add(TakuhaiNipoConstants.CSV_DT_TENPO_COUNT);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_URIAGE);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_URIAGE_ZEN);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_ZEN_HI_URIAGE);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_URIAGE_TAKU);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_URIAGE_TAKU_ZEN);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_ZEN_HI_TAKU_URI);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_KOSEIHI_URI);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_KYAKUSU);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_KYAKUSU_ZEN);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_ZEN_HI_KYAKUSU);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_KYAKUSU_TAKU);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_KYAKUSU_TAKU_ZEN);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_ZEN_HI_TAKU_KYA);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_KOSEIHI_KYA);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_KYAKU_TANKA);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_KYAKU_TANKA_ZEN);
			head5thList.add(TakuhaiNipoConstants.CSV_DT_ZEN_HI_KYAKU_TANKA);
		}

		headerList.add(head1stList);
		headerList.add(head2ndList);
		headerList.add(head3rdList);
		headerList.add(head4thList);
		headerList.add(head5thList);
 
		return headerList;
	}

	/**
	 * 支部別宅配売上情報取得ロジックを取得する
	 * @return	SibuInfoLogic 支部別宅配売上情報取得ロジック
	 */
	public SibuInfoLogic getSibuInfoLogic() {
		return this.sibuInfoLogic;
	}

	/**
	 * 支部別宅配売上情報取得ロジックを設定する
	 * @param sibuInfoLogic 支部別宅配売上情報取得ロジック
	 */
	public void setSibuInfoLogic(SibuInfoLogic sibuInfoLogic) {
		this.sibuInfoLogic = sibuInfoLogic;
	}
}