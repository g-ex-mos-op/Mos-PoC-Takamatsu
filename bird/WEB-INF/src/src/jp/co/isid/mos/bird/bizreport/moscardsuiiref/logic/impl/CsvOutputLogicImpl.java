/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.moscardsuiiref.logic.impl;

import java.util.ArrayList;
import java.util.List;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.bizreport.common.suiiref.code.TaishoJoken;
import jp.co.isid.mos.bird.bizreport.common.suiiref.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefParameterDto;
import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefResultDto;
import jp.co.isid.mos.bird.bizreport.common.suiiref.util.SuiiRefUtil;
import jp.co.isid.mos.bird.bizreport.moscardsuiiref.entity.UIMoscard;
import jp.co.isid.mos.bird.bizreport.moscardsuiiref.logic.SearchLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;

/**
 * LOGIC【CSVダウンロード】
 * 
 * 作成日:2013/05/07
 * @author xkinu
 *
 */
public class CsvOutputLogicImpl implements CsvOutputLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BBR016L02";

    /** LOGIC【検索結果取得】*/
    private SearchLogic moscardSuiiRefSearchLogic;

	/* (非 Javadoc)
	 * @param CsvOutputDto BIRDFW共通DTO【CSV出力用】
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getFileName(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public String getFileName(CsvOutputDto csvOutputDto) {
		//０.BIRDFW共通DTO【CSV出力用】を推移表共通DTO【検索条件】へキャストし、DTO【検索条件】とします。
		SuiiRefParameterDto paramDto = (SuiiRefParameterDto)csvOutputDto;
		//１．月次タブのCSVの場合は下記の処理でCSVファイル名をリターンします。
		if(paramDto.isSuiiTypeGepo()) {
			if(TaishoKikan.CODE_NENDO.equals(paramDto.getTaishoKikan())) {
				//1.年度の月次タブのCSVの場合は、"MOSCARDSUIIMONTH"+DTO【検索結果】.期間指定(yyyy)+".csv"の文字列をリターンします。
				return "MOSCSUIIMONTH"+paramDto.getKikanSitei()+".csv";
			}
			//2.任意月指定の月次タブのCSVの場合は、"MOSCARDSUIIMONTHtab_gepo.csv"の文字列をリターンします。
			return "MOSCSUIIMONTHtab_gepo.csv";
		}
		//２.月次タブ以外のCSVの場合は、"MOSCARDSUIIDAY"+DTO【検索結果】.フォーカスタブ+".csv"の文字列をリターンします。
		return "MOSCSUIIDAY"+paramDto.getFocusTab()+".csv";
	}

	/**
	 * 出力データ取得処理
	 * 
	 * @param csvOutputDto BIRDFW共通DTO【CSV出力用】
	 * @return List[[出力データ]]
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public List getOutputData(CsvOutputDto csvOutputDto) {
		//０.BIRDFW共通DTO【CSV出力用】を推移表共通DTO【検索条件】へキャストし、DTO【検索条件】とします。
		SuiiRefParameterDto dto = (SuiiRefParameterDto)csvOutputDto;
		boolean isCsv = true;
		//１.LOGIC【検索結果取得】を実行し、戻り値DTO【検索結果】を取得します。
		SuiiRefResultDto suiiRefResultDto =  getMoscardSuiiRefSearchLogic().execute(
				isCsv, dto, null);
		//２.List[[出力データ]]を生成します。
		List listCsv = new ArrayList(0);
		//３.推移表共通定数クラス.ヘッダー情報作成処理を実行します。
		SuiiRefUtil.csvSetHeader(getBirdUserInfo(), dto, suiiRefResultDto.getFocusUITabData(), listCsv);
        //４.データ項目名作成取得処理を実行し、戻り値List[[項目名]]をList[[出力データ]]へ追加します。
		listCsv.add(getListTitle(dto));
        //５.データ設定処理を実行します。
        addData(dto, suiiRefResultDto.getListFocusTabResult(), listCsv);
        //６.List[[出力データ]]をリターンします。
		return listCsv;
	}

	/* (非 Javadoc)
	 * @param CsvOutputDto BIRDFW共通DTO【CSV出力用】
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#validate(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public void validate(CsvOutputDto csvOutputDto) {
		// TODO 自動生成されたメソッド・スタブ

	}
	/*
	 * データ項目名作成取得処理
	 * @param parameterDto 推移表共通DTO【検索条件】
	 * @return　List[[項目名]]
	 * @see jp.co.isid.mos.bird.bizreport.groupsuiiref.logic.impl.CsvOutputLogicImpl#createDataTitleName(jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefParameterDto)
	 */
    private List getListTitle(SuiiRefParameterDto parameterDto) {
    	//１.推移表共通定数クラス.データ項目名作成取得処理を実行し、戻り値をList[[項目名]]とします。
    	//modify xou.zoubun 2019/11/13 List listCsvTableHeader = SuiiRefUtil.csvGetDataTitle(parameterDto);
        List listCsvTableHeader = SuiiRefUtil.csvGetDataTitle(parameterDto,false);

		boolean isHosei  = parameterDto.isSelectHosei();
        //２．List[[項目名]]へ下記の処理で項目名を設定します。
        listCsvTableHeader.add("発行枚数");
        if (isHosei) {
            listCsvTableHeader.add("前年比対象発行枚数");
            listCsvTableHeader.add("前年比対象前年発行枚数");            
        } else {
            listCsvTableHeader.add("前年発行枚数");
        }
        listCsvTableHeader.add("前年比(発行枚数)");
        //チャージ金額
        listCsvTableHeader.add("チャージ金額");
        listCsvTableHeader.add("売上比(チャージ金額)");
        
        if (isHosei) {
            listCsvTableHeader.add("前年比対象チャージ金額");
            listCsvTableHeader.add("前年比対象前年チャージ金額");            
        } else {
            listCsvTableHeader.add("前年チャージ金額");
        }        
        listCsvTableHeader.add("前年比(チャージ金額)");
        //チャージ件数
        listCsvTableHeader.add("チャージ件数");
        listCsvTableHeader.add("客数比(チャージ件数)");
        
        if (isHosei) {
            listCsvTableHeader.add("前年比対象チャージ件数");
            listCsvTableHeader.add("前年比対象前年チャージ件数");            
        } else {
            listCsvTableHeader.add("前年チャージ件数");
        }        
        listCsvTableHeader.add("前年比(チャージ件数)");
        //チャージ単価
        listCsvTableHeader.add("チャージ単価");
        if (isHosei) {
            listCsvTableHeader.add("前年比対象チャージ単価");
            listCsvTableHeader.add("前年比対象前年チャージ単価");       
        } else {
            listCsvTableHeader.add("前年チャージ単価");
        }
        listCsvTableHeader.add("前年比(チャージ単価)");
        //決済金額
        listCsvTableHeader.add("決済金額");
        listCsvTableHeader.add("売上比(決済金額)");
        
        if (isHosei) {
            listCsvTableHeader.add("前年比対象決済金額");
            listCsvTableHeader.add("前年比対象前年決済金額");            
        } else {
            listCsvTableHeader.add("前年決済金額");
        }        
        listCsvTableHeader.add("前年比(決済金額)");
        //決済件数
        listCsvTableHeader.add("決済件数");
        listCsvTableHeader.add("客数比(決済件数)");
        
        if (isHosei) {
            listCsvTableHeader.add("前年比対象決済件数");
            listCsvTableHeader.add("前年比対象前年決済件数");            
        } else {
            listCsvTableHeader.add("前年決済件数");
        }        
        listCsvTableHeader.add("前年比(決済件数)");
        //決済単価
        listCsvTableHeader.add("決済単価");
        listCsvTableHeader.add("客単価比(決済単価)");
        if (isHosei) {
            listCsvTableHeader.add("前年比対象決済単価");
            listCsvTableHeader.add("前年比対象前年決済単価");       
        } else {
            listCsvTableHeader.add("前年決済単価");
        }
        listCsvTableHeader.add("前年比(決済単価)");

//        listCsvTableHeader.add("入金取消金額");
//        listCsvTableHeader.add("入金取消件数");
//        listCsvTableHeader.add("利用取消金額");
//        listCsvTableHeader.add("利用取消件数");
//        listCsvTableHeader.add("発行ボーナスバリュー");
//        listCsvTableHeader.add("利用ボーナスバリュー");
//        listCsvTableHeader.add("発行クーポンバリュー");
//        listCsvTableHeader.add("利用クーポンバリュー");
//        listCsvTableHeader.add("前受残高");
        //３．List[[項目名]]をリターンします。
        return listCsvTableHeader;
    }
	/**
	 * 推移データ格納処理
	 * 
	 * @param dto　　　　推移表共通DTO【検索条件】
	 * @param listEntity List[[検索結果]]
	 * @param listCsv    List[[出力データ]]
	 */
	private void addData(SuiiRefParameterDto dto, List listEntity, List listCsv) {
        //１.List[[検索結果]]件数分下記の処理を行います。
        for (int i=0; i<listEntity.size(); i++) {
        	//for-1.List[[行データ]]を生成します。
            List listRow = new ArrayList(0);
            //for-2.List[[検索結果]]の現行インデックスのUIMoscard[検索結果]を取得します。
            UIMoscard entity = (UIMoscard)listEntity.get(i);
            boolean isGepo = dto.isSuiiTypeGepo();
            boolean isHosei = dto.isSelectHosei();
            boolean isTaishoJokenMise = dto.getTaishoJoken().equals(TaishoJoken.CODE_MISE);
            //for-3.推移表共通定数クラス.データ設定処理を実行し、売上推移表データをList[[行データ]]へ格納します。
//modify 2019/07/18 #35 USI張 begin
//    		SuiiRefUtil.csvSetData(entity, listRow, isGepo, isHosei, isTaishoJokenMise, 0);
    		SuiiRefUtil.csvSetData(entity, listRow, isGepo, isHosei, isTaishoJokenMise,false, 0);
//modify 2019/07/18 #35 USI張 end
            //for-4.UIMoscard[検索結果].発行枚数をList[[行データ]]へ格納します。
            listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getIssueCnt()));
            if(isHosei) {
    	        listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNetIssueCnt()));
    	        listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNetZenIssueCnt()));
    	        listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getNetIssueCntZennenhi()));
            }
            else {
    	        listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getZenIssueCnt()));
    	        listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getIssueCntZennenhi()));
            }
            //ﾁｬｰｼﾞ金額
            listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getChargeKin()));
            listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getChargeUriagehi()));
            if(isHosei) {
                listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNetChargeKin()));
    	        listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNetZenChargeKin()));
    	        listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getNetChargeKinZennenhi()));
            }
            else {
    	        listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getZenChargeKin()));
    	        listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getChargeKinZennenhi()));
            }
            //ﾁｬｰｼﾞ件数
            listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getChargeKen()));
            listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getChargeKyakusuhi()));
            if(isHosei) {
                listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNetChargeKen()));
    	        listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNetZenChargeKen()));
    	        listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getNetChargeKenZennenhi()));
            }
            else {
    	        listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getZenChargeKen()));
    	        listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getChargeKenZennenhi()));
            }
            //ﾁｬｰｼﾞ単価
            listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getChargeTanka()));
            if(isHosei) {
                listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNetChargeTanka()));
    	        listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNetZenChargeTanka()));
    	        listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getNetChargeTankaZennenhi()));
            }
            else {
    	        listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getZenChargeTanka()));
    	        listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getChargeTankaZennenhi()));
            }
            //決済金額
            listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getKessaiKin()));
            listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getKessaiUriagehi()));
            if(isHosei) {
                listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNetKessaiKin()));
    	        listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNetZenKessaiKin()));
    	        listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getNetKessaiKinZennenhi()));
            }
            else {
    	        listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getZenKessaiKin()));
    	        listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getKessaiKinZennenhi()));
            }
            //決済件数
            listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getKessaiKen()));
            listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getKessaiKyakusuhi()));
            if(isHosei) {
                listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNetKessaiKen()));
    	        listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNetZenKessaiKen()));
    	        listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getNetKessaiKenZennenhi()));
            }
            else {
    	        listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getZenKessaiKen()));
    	        listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getKessaiKenZennenhi()));
            }
            //決済単価
            listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getKessaiTanka()));
            listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getKessaiTankahi()));
            if(isHosei) {
                listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNetKessaiTanka()));
    	        listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNetZenKessaiTanka()));
    	        listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getNetKessaiTankaZennenhi()));
            }
            else {
    	        listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getZenKessaiTanka()));
    	        listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getKessaiTankaZennenhi()));
            }
//            //入金取消金額
//            listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getChargeKinCancel()));
//            //入金取消件数
//            listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getChargeKenCancel()));
//            //利用取消金額
//            listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getUseKinCancel()));
//            //利用取消件数
//            listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getUseKenCancel()));
//            //発行ボーナスバリュー
//            listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getBonusVIssue()));
//            //利用ボーナスバリュー
//            listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getBonusVUse()));
//            //発行クーポンバリュー
//            listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getCouponVIssue()));
//            //利用クーポンバリュー
//            listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getCouponVUse()));
//            //前受残高
//            if(SuiiRefUtil.EIGYO_DT_LABEL_12AVG.equals(entity.getEigyoDtLabel()) 
//            		|| SuiiRefUtil.EIGYO_DT_LABEL_12TOTAL.equals(entity.getEigyoDtLabel())
//            		|| SuiiRefUtil.EIGYO_DT_LABEL_TUUKI.equals(entity.getEigyoDtLabel()))
//            {
//            	//集計行の意味に相応しくない為、ブランク(空)表示。
//
//            }
//            else {
//            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getZandaka()));
//            }

            listCsv.add(listRow);
        }
    }
    /**
     * 
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }
    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }

	/**
	 * LOGIC【検索結果取得】
	 * @return クラス変数moscardSuiiRefSearchLogic を戻します。
	 */
	public SearchLogic getMoscardSuiiRefSearchLogic() {
		return moscardSuiiRefSearchLogic;
	}

	/**
	 * LOGIC【検索結果取得】
	 * @param searchLogic を クラス変数moscardSuiiRefSearchLogicへ設定します。
	 */
	public void setMoscardSuiiRefSearchLogic(SearchLogic searchLogic) {
		this.moscardSuiiRefSearchLogic = searchLogic;
	}

}
