/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.netordersuiiref.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.suiiref.code.TaishoJoken;
import jp.co.isid.mos.bird.bizreport.common.suiiref.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefParameterDto;
import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefResultDto;
import jp.co.isid.mos.bird.bizreport.common.suiiref.util.SuiiRefUtil;
import jp.co.isid.mos.bird.bizreport.netordersuiiref.entity.UINetorder;
import jp.co.isid.mos.bird.bizreport.netordersuiiref.logic.SearchLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * LOGIC【CSVダウンロード】
 *
 */
public class CsvOutputLogicImpl implements CsvOutputLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BBR018L02";

    /** LOGIC【検索結果取得】*/
    private SearchLogic netorderSuiiRefSearchLogic;

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
				//1.年度の月次タブのCSVの場合は、"NETORDERSUIIMONTH"+DTO【検索結果】.期間指定(yyyy)+".csv"の文字列をリターンします。
				return "NETORDERSUIIMONTH"+paramDto.getKikanSitei()+".csv";
			}
			//2.任意月指定の月次タブのCSVの場合は、"NETORDERSUIIMONTH_gepo.csv"の文字列をリターンします。
			return "NETORDERSUIIMONTH_gepo.csv";
		}
		//２.月次タブ以外のCSVの場合は、"NETORDERSUIIDAY"+DTO【検索結果】.フォーカスタブ+".csv"の文字列をリターンします。
		return "NETORDERSUIIDAY"+paramDto.getFocusTab()+".csv";
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
		boolean isGepo = dto.isSuiiTypeGepo();
		//１.LOGIC【検索結果取得】を実行し、戻り値DTO【検索結果】を取得します。
		SuiiRefResultDto suiiRefResultDto =  getNetorderSuiiRefSearchLogic().execute(
				isCsv, dto, null);
		//２.List[[出力データ]]を生成します。
		List listCsv = new ArrayList(0);
		//３.推移表共通定数クラス.ヘッダー情報作成処理を実行します。
		SuiiRefUtil.csvSetHeader(getBirdUserInfo(), dto, suiiRefResultDto.getFocusUITabData(), listCsv);
		listCsv.remove(listCsv.toArray().length-1);
		listCsv.remove(listCsv.toArray().length-1);
		NumericFormatter numFmt = new NumericFormatter();
		List listHeaderRowTaishoTenpoSu = new ArrayList();
		listHeaderRowTaishoTenpoSu.add("ネット注文対象店舗数");
        listHeaderRowTaishoTenpoSu.add(numFmt.format(String.valueOf(suiiRefResultDto.getFocusUITabData().getMiseCnt())));
        listCsv.add(listHeaderRowTaishoTenpoSu);
        listCsv.add(new ArrayList());
		//４.月次の場合、
		//任意月指定での選択が当月（システム日付を含む月）の場合、”12ヶ月合計と平均には当月は含みません” 
		//任意月指定での選択が前月以前の場合、”12ヶ月合計と平均には前年同月は含みません”
		List temp = new ArrayList(0);
		if (isGepo && !TaishoKikan.CODE_NENDO.equals(dto.getTaishoKikan())){
			if (getBirdDateInfo().getAppDate().substring(0, 6).equals(dto.getKikanSitei())) {
				temp = (ArrayList)listCsv.get(0);
				temp.add("12ヶ月合計と平均には当月は含みません。 ");
	        }else{
	        	temp = (ArrayList)listCsv.get(0);
				temp.add("12ヶ月合計と平均には前年同月は含みません。 ");
	        }
		}
        //５.データ項目名作成取得処理を実行し、戻り値List[[項目名]]をList[[出力データ]]へ追加します。
		setListTitle(dto,listCsv);
        //６.データ設定処理を実行します。
        addData(dto, suiiRefResultDto.getListFocusTabResult(), listCsv);
        //７.List[[出力データ]]をリターンします。
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
    private void setListTitle(SuiiRefParameterDto parameterDto,List listCsv) {
    	//１.推移表共通定数クラス.データ項目名作成取得処理を実行し、戻り値をList[[項目名]]とします。
    	List listCsvDaiHeader = new ArrayList();
    	//moidfy xou.zoubun 2019/11/13 List listCsvTableHeader = SuiiRefUtil.csvGetDataTitle(parameterDto);
		List listCsvTableHeader = SuiiRefUtil.csvGetDataTitle(parameterDto,false);
    	boolean isGepo = parameterDto.isSuiiTypeGepo();
		boolean isHosei  = parameterDto.isSelectHosei();
		String taishoJoiken = parameterDto.getTaishoJoken();
		
		for (int i=0;i<listCsvTableHeader.toArray().length;i++){
			listCsvDaiHeader.add("");
		}
        //２．List[[項目名]]へ下記の処理で項目名を設定します。
		if (isGepo && taishoJoiken.equals(TaishoJoken.CODE_MISE)){
			listCsvDaiHeader.add("ネット注文(合算)");
			listCsvTableHeader.add("ネット注文日数");
		}
		else{
			listCsvDaiHeader.add("ネット注文(合算)");
			listCsvTableHeader.add("ネット注文実績店舗数");
		}
		listCsvDaiHeader.add("");
		listCsvTableHeader.add("ネット注文売上");
        if(isHosei) {
        	listCsvDaiHeader.add("");
        	listCsvDaiHeader.add("");
            listCsvTableHeader.add("前年比対象ネット注文売上");
            listCsvTableHeader.add("前年比対象前年ネット注文実績");
        }else{
        	listCsvDaiHeader.add("");
            listCsvTableHeader.add("前年ネット注文実績");
        }
        listCsvDaiHeader.add("");
        listCsvDaiHeader.add("");
        listCsvTableHeader.add("前年比(ネット注文売上)");
        listCsvTableHeader.add("構成比(売上)");
        if(!taishoJoiken.equals(TaishoJoken.CODE_MISE)) {
        	listCsvDaiHeader.add("");
        	listCsvTableHeader.add("ネット注文売上平均");
        }
        listCsvDaiHeader.add("");
        listCsvTableHeader.add("ネット注文件数");
        if(isHosei) {
        	listCsvDaiHeader.add("");
        	listCsvDaiHeader.add("");
            listCsvTableHeader.add("前年比対象ネット注文件数");
            listCsvTableHeader.add("前年比対象前年ネット注文件数");
        }else{
        	listCsvDaiHeader.add("");
            listCsvTableHeader.add("前年ネット注文件数");
        }
        listCsvDaiHeader.add("");
        listCsvDaiHeader.add("");
        listCsvTableHeader.add("前年比(ネット注文件数)");
        listCsvTableHeader.add("構成比(件数)");
        if(!taishoJoiken.equals(TaishoJoken.CODE_MISE)) {
        	listCsvDaiHeader.add("");
        	listCsvTableHeader.add("ネット注文件数平均");
        }
        listCsvDaiHeader.add("");
        listCsvTableHeader.add("客単価(ネット注文)");
        if(isHosei) {
        	listCsvDaiHeader.add("");
        	listCsvDaiHeader.add("");
            listCsvTableHeader.add("前年比対象客単価(ネット注文)");
            listCsvTableHeader.add("前年比対象前年客単価(ネット注文)");
        }else{
        	listCsvDaiHeader.add("");
            listCsvTableHeader.add("前年客単価(ネット注文)");
        }
        listCsvDaiHeader.add("");
        listCsvTableHeader.add("前年比(客単価)");
        
        if (isGepo && taishoJoiken.equals(TaishoJoken.CODE_MISE)){
        	listCsvDaiHeader.add("ネット注文(テイク)");
			listCsvTableHeader.add("ネットテイク日数");
		}else{
			listCsvDaiHeader.add("ネット注文(テイク)");
			listCsvTableHeader.add("ネットテイク実績店舗数");
		}
		listCsvDaiHeader.add("");
		listCsvTableHeader.add("ネットテイク売上");
        if(isHosei) {
        	listCsvDaiHeader.add("");
        	listCsvDaiHeader.add("");
            listCsvTableHeader.add("前年比対象ネットテイク売上");
            listCsvTableHeader.add("前年比対象前年ネットテイク実績");
        }else{
        	listCsvDaiHeader.add("");
            listCsvTableHeader.add("前年ネットテイク実績");
        }
        listCsvDaiHeader.add("");
        listCsvDaiHeader.add("");
        listCsvTableHeader.add("前年比(ネットテイク売上)");
        listCsvTableHeader.add("構成比(売上)");
        if(!taishoJoiken.equals(TaishoJoken.CODE_MISE)) {
        	listCsvDaiHeader.add("");
        	listCsvTableHeader.add("ネットテイク売上平均");
        }
        listCsvDaiHeader.add("");
        listCsvTableHeader.add("ネットテイク件数");
        if(isHosei) {
        	listCsvDaiHeader.add("");
        	listCsvDaiHeader.add("");
            listCsvTableHeader.add("前年比対象ネットテイク件数");
            listCsvTableHeader.add("前年比対象前年ネットテイク件数");
        }else{
        	listCsvDaiHeader.add("");
            listCsvTableHeader.add("前年ネットテイク件数");
        }
        listCsvDaiHeader.add("");
        listCsvDaiHeader.add("");
        listCsvTableHeader.add("前年比(ネットテイク件数)");
        listCsvTableHeader.add("構成比(件数)");
        if(!taishoJoiken.equals(TaishoJoken.CODE_MISE)) {
        	listCsvDaiHeader.add("");
        	listCsvTableHeader.add("ネットテイク件数平均");
        }
        listCsvDaiHeader.add("");
        listCsvTableHeader.add("客単価(ネットテイク)");
        if(isHosei) {
        	listCsvDaiHeader.add("");
        	listCsvDaiHeader.add("");
            listCsvTableHeader.add("前年比対象客単価(ネットテイク)");
            listCsvTableHeader.add("前年比対象前年客単価(ネットテイク)");
        }else{
        	listCsvDaiHeader.add("");
            listCsvTableHeader.add("前年客単価(ネットテイク)");
        }
        listCsvDaiHeader.add("");
        listCsvTableHeader.add("前年比(客単価)");
        
        if (isGepo && taishoJoiken.equals(TaishoJoken.CODE_MISE)){
        	listCsvDaiHeader.add("ネット注文(宅配)");
			listCsvTableHeader.add("ネット宅配日数");
		}
        else{
			listCsvDaiHeader.add("ネット注文(宅配)");
			listCsvTableHeader.add("ネット宅配実績店舗数");
		}
		listCsvTableHeader.add("ネット宅配売上");
        if(isHosei) {
            listCsvTableHeader.add("前年比対象ネット宅配売上");
            listCsvTableHeader.add("前年比対象前年ネット宅配実績");
        }else{
            listCsvTableHeader.add("前年ネット宅配実績");
        }
        listCsvTableHeader.add("前年比(ネット宅配売上)");
        listCsvTableHeader.add("構成比(売上)");
        if(!taishoJoiken.equals(TaishoJoken.CODE_MISE)) {
        	listCsvTableHeader.add("ネット宅配売上平均");
        }
        listCsvTableHeader.add("ネット宅配件数");
        if(isHosei) {
            listCsvTableHeader.add("前年比対象ネット宅配件数");
            listCsvTableHeader.add("前年比対象前年ネット宅配件数");
        }else{
            listCsvTableHeader.add("前年ネット宅配件数");
        }
        listCsvTableHeader.add("前年比(ネット宅配件数)");
        listCsvTableHeader.add("構成比(件数)");
        if(!taishoJoiken.equals(TaishoJoken.CODE_MISE)) {
        	listCsvTableHeader.add("ネット宅配件数平均");
        }
        listCsvTableHeader.add("客単価(ネット宅配)");
        if(isHosei) {
            listCsvTableHeader.add("前年比対象客単価(ネット宅配)");
            listCsvTableHeader.add("前年比対象前年客単価(ネット宅配)");
        }else{
            listCsvTableHeader.add("前年客単価(ネット宅配)");
        }
        listCsvTableHeader.add("前年比(客単価)");
        
        listCsv.add(listCsvDaiHeader);
        listCsv.add(listCsvTableHeader);
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
            //for-2.List[[検索結果]]の現行インデックスのUINetorder[検索結果]を取得します。
            UINetorder entity = (UINetorder)listEntity.get(i);
            boolean isGepo = dto.isSuiiTypeGepo();
            boolean isHosei = dto.isSelectHosei();
            boolean isTaishoJokenMise = dto.getTaishoJoken().equals(TaishoJoken.CODE_MISE);
            //for-3.推移表共通定数クラス.データ設定処理を実行し、売上推移表データをList[[行データ]]へ格納します。
//modify 2019/07/18 #35 USI張 begin
//    		SuiiRefUtil.csvSetData(entity, listRow, isGepo, isHosei, isTaishoJokenMise, 0);
    		SuiiRefUtil.csvSetData(entity, listRow, isGepo, isHosei, isTaishoJokenMise,false, 0);
//modify 2019/07/18 #35 USI張 end
            //for-4.UINetorder[検索結果].ネット注文をList[[行データ]]へ格納します。
    		//ネット注文
            if (isGepo && isTaishoJokenMise){
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getEigyoDaysNsum()));
    		}
    		if (!(isGepo && isTaishoJokenMise)){
    			if (SuiiRefUtil.EIGYO_DT_LABEL_HANKI_1.equals(entity.getEigyoDtLabel())
	    					||SuiiRefUtil.EIGYO_DT_LABEL_HANKI_2.equals(entity.getEigyoDtLabel())
	    					||SuiiRefUtil.EIGYO_DT_LABEL_HANKI_3.equals(entity.getEigyoDtLabel())
	    					||SuiiRefUtil.EIGYO_DT_LABEL_HANKI_4.equals(entity.getEigyoDtLabel())
	    					||SuiiRefUtil.EIGYO_DT_LABEL_KAMI.equals(entity.getEigyoDtLabel())
	    					||SuiiRefUtil.EIGYO_DT_LABEL_SIMO.equals(entity.getEigyoDtLabel())
	    					||SuiiRefUtil.EIGYO_DT_LABEL_RUIKEI.equals(entity.getEigyoDtLabel())
	    					||SuiiRefUtil.EIGYO_DT_LABEL_TUUKI.equals(entity.getEigyoDtLabel())
	    					||SuiiRefUtil.EIGYO_DT_LABEL_12TOTAL.equals(entity.getEigyoDtLabel())){
    				listRow.add("");
    			}else{
    				listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getMiseCntKbnNsum()));
    			}
    		}
    		listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getUriageNsum()));
            if(isHosei) {
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNetUriageNsum()));
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNetUriageNsumZen()));
            	listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getNetNsumUriageZenhi()));
            }else{
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getUriageNsumZen()));
            	listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getNsumUriageZenhi()));
            }
            listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getNsumUriageKouseihi()));
            if(!isTaishoJokenMise) {
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNsumUriageAvg()));
            }
            listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getKyakusuNsum()));
            if(isHosei) {
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNetKyakusuNsum()));
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNetKyakusuNsumZen()));
            	listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getNetNsumKyakusuZenhi()));
            }else{
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getKyakusuNsumZen()));
            	listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getNsumKyakusuZenhi()));
            }
            listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getNsumKyakusuKouseihi()));
            if(!isTaishoJokenMise) {
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNsumKyakusuAvg()));
            }
            listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNsumKyakuTanka()));
            if(isHosei) {
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNetNsumKyakuTanka()));
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNetNsumKyakuTankaZen()));
            }else{
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNsumKyakuTankaZen()));
            }
            if(isHosei){
            	listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getNetNsumKyakuTankaZenhi()));
            }else{
            	listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getNsumKyakuTankaZenhi()));
            }
            //ネットテイク
            if (isGepo && isTaishoJokenMise){
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getEigyoDaysNtake()));
    		}
    		if (!(isGepo && isTaishoJokenMise)){
    			if (SuiiRefUtil.EIGYO_DT_LABEL_HANKI_1.equals(entity.getEigyoDtLabel())
    					||SuiiRefUtil.EIGYO_DT_LABEL_HANKI_2.equals(entity.getEigyoDtLabel())
    					||SuiiRefUtil.EIGYO_DT_LABEL_HANKI_3.equals(entity.getEigyoDtLabel())
    					||SuiiRefUtil.EIGYO_DT_LABEL_HANKI_4.equals(entity.getEigyoDtLabel())
    					||SuiiRefUtil.EIGYO_DT_LABEL_KAMI.equals(entity.getEigyoDtLabel())
    					||SuiiRefUtil.EIGYO_DT_LABEL_SIMO.equals(entity.getEigyoDtLabel())
    					||SuiiRefUtil.EIGYO_DT_LABEL_RUIKEI.equals(entity.getEigyoDtLabel())
    					||SuiiRefUtil.EIGYO_DT_LABEL_TUUKI.equals(entity.getEigyoDtLabel())
    					||SuiiRefUtil.EIGYO_DT_LABEL_12TOTAL.equals(entity.getEigyoDtLabel())){
    				listRow.add("");
    			}else{
    				listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getMiseCntKbnNtake()));
    			}
    		}
    		listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getUriageNtake()));
            if(isHosei) {
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNetUriageNtake()));
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNetUriageNtakeZen()));
            	listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getNetNtakeUriageZenhi()));
            }else{
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getUriageNtakeZen()));
            	listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getNtakeUriageZenhi()));
            }
            listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getNtakeUriageKouseihi()));
            if(!isTaishoJokenMise) {
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNtakeUriageAvg()));
            }
            listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getKyakusuNtake()));
            if(isHosei) {
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNetKyakusuNtake()));
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNetKyakusuNtakeZen()));
            	listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getNetNtakeKyakusuZenhi()));
            }else{
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getKyakusuNtakeZen()));
            	listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getNtakeKyakusuZenhi()));
            }
            listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getNtakeKyakusuKouseihi()));
            if(!isTaishoJokenMise) {
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNtakeKyakusuAvg()));
            }
            listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNtakeKyakuTanka()));
            if(isHosei) {
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNetNtakeKyakuTanka()));
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNetNtakeKyakuTankaZen()));
            }else{
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNtakeKyakuTankaZen()));
            }
            if(isHosei){
            	listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getNetNtakeKyakuTankaZenhi()));
            }else{
            	listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getNtakeKyakuTankaZenhi()));
            }
            //ネット宅配
            if (isGepo && isTaishoJokenMise){
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getEigyoDaysNtaku()));
    		}
    		if (!(isGepo && isTaishoJokenMise)){
    			if (SuiiRefUtil.EIGYO_DT_LABEL_HANKI_1.equals(entity.getEigyoDtLabel())
    					||SuiiRefUtil.EIGYO_DT_LABEL_HANKI_2.equals(entity.getEigyoDtLabel())
    					||SuiiRefUtil.EIGYO_DT_LABEL_HANKI_3.equals(entity.getEigyoDtLabel())
    					||SuiiRefUtil.EIGYO_DT_LABEL_HANKI_4.equals(entity.getEigyoDtLabel())
    					||SuiiRefUtil.EIGYO_DT_LABEL_KAMI.equals(entity.getEigyoDtLabel())
    					||SuiiRefUtil.EIGYO_DT_LABEL_SIMO.equals(entity.getEigyoDtLabel())
    					||SuiiRefUtil.EIGYO_DT_LABEL_RUIKEI.equals(entity.getEigyoDtLabel())
    					||SuiiRefUtil.EIGYO_DT_LABEL_TUUKI.equals(entity.getEigyoDtLabel())
    					||SuiiRefUtil.EIGYO_DT_LABEL_12TOTAL.equals(entity.getEigyoDtLabel())){
    				listRow.add("");
    			}else{
    				listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getMiseCntKbnNtaku()));
    			}
    		}
    		listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getUriageNtaku()));
            if(isHosei) {
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNetUriageNtaku()));
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNetUriageNtakuZen()));
            	listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getNetNtakuUriageZenhi()));
            }else{
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getUriageNtakuZen()));
            	listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getNtakuUriageZenhi()));
            }
            listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getNtakuUriageKouseihi()));
            if(!isTaishoJokenMise) {
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNtakuUriageAvg()));
            }
            listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getKyakusuNtaku()));
            if(isHosei) {
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNetKyakusuNtaku()));
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNetKyakusuNtakuZen()));
            	listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getNetNtakuKyakusuZenhi()));
            }else{
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getKyakusuNtakuZen()));
            	listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getNtakuKyakusuZenhi()));
            }
            listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getNtakuKyakusuKouseihi()));
            if(!isTaishoJokenMise) {
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNtakuKyakusuAvg()));
            }
            listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNtakuKyakuTanka()));
            if(isHosei) {
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNetNtakuKyakuTanka()));
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNetNtakuKyakuTankaZen()));
            }else{
            	listRow.add(SuiiRefUtil.numFmtdgt0.format(entity.getNtakuKyakuTankaZen()));
            }
            if(isHosei){
            	listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getNetNtakuKyakuTankaZenhi()));
            }else{
            	listRow.add(SuiiRefUtil.numFmtdgt2.format(entity.getNtakuKyakuTankaZenhi()));
            }
            
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
    
    private BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }

	/**
	 * @return netorderSuiiRefSearchLogic を戻します。
	 */
	public SearchLogic getNetorderSuiiRefSearchLogic() {
		return netorderSuiiRefSearchLogic;
	}

	/**
	 * @param netorderSuiiRefSearchLogic 設定する netorderSuiiRefSearchLogic。
	 */
	public void setNetorderSuiiRefSearchLogic(SearchLogic netorderSuiiRefSearchLogic) {
		this.netorderSuiiRefSearchLogic = netorderSuiiRefSearchLogic;
	}

}
