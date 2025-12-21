/**
 * 
 */
package jp.co.isid.mos.bird.analysis.jikanbeturef.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.analysis.common.menubetu.code.TaishoJoken;
import jp.co.isid.mos.bird.analysis.common.menubetu.code.TaishoKikan;
import jp.co.isid.mos.bird.analysis.common.menubetu.dto.MenuBetuReqDto;
import jp.co.isid.mos.bird.analysis.common.menubetu.entity.UIAnalysis;
import jp.co.isid.mos.bird.analysis.common.menubetu.util.MenubetuUtil;
import jp.co.isid.mos.bird.analysis.jikanbeturef.code.RowType;
import jp.co.isid.mos.bird.analysis.jikanbeturef.dto.RequestDto;
import jp.co.isid.mos.bird.analysis.jikanbeturef.entity.UIWeekShubetu;
import jp.co.isid.mos.bird.analysis.jikanbeturef.logic.SearchWeekShubetuLogic;
import jp.co.isid.mos.bird.analysis.jikanbeturef.util.JikanBetuRefUtil;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.MissingConfigException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * 売上種別CSVダウンロードロジック
 * 
 * 作成日:2008/09/10
 * @author xkinu
 *
 */
public class CsvWeekShubetuLogicImpl implements CsvOutputLogic {
	/** ロジックID */
	public static final String LOGIC_ID = JikanBetuRefUtil.LOGIC_ID_CSV_SHUBETU;
	
	private SearchWeekShubetuLogic jikanBetuRefSearchWeekShubetuLogic;
    // Formatter
    NumericFormatter numFmtdgt0 = new NumericFormatter(true, 0);
    NumericFormatter numFmtdgt2 = new NumericFormatter(true, 2);

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getFileName(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public String getFileName(CsvOutputDto csvOutputDto) {
        RequestDto reqestDto = (RequestDto) csvOutputDto;
    	//『月別』の場合
    	return "JIKANURISHU"+reqestDto.getKikanSitei()+".csv";
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public List getOutputData(CsvOutputDto csvOutputDto) {
        // DTO
		RequestDto requestDto = (RequestDto) csvOutputDto;
		//１．DTO【自画面Request】.表示曜日区分へ『三曜日』を設定し、
		requestDto.setCsvMode(true);
		//    LOGIC【売上種別検索結果取得】検索を実行し、検索結果List[[CSV出力用リスト]]を取得します。
		List listCsvData = getJikanBetuRefSearchWeekShubetuLogic().execute(requestDto);
    	requestDto.setListSearchData(listCsvData);
		//２．List[[CSV出力用リスト]]を生成します。
        List listCSV = new ArrayList();
        //３．条件項目ヘッダ作成
		addHeader(listCSV, requestDto);
        //４．明細部ヘッダー作成
		addListHeader(listCSV);
        //５．明細部データ作成
		addList(listCSV, listCsvData);
		//６．List[[CSV出力用リスト]]をリターンします。
		return listCSV;
	}
	/**
	 * ヘッダ部作成設定処理
	 * 
	 * @param listCSV
	 * @param requestDto
	 * @param tanpoCnt
	 */
	private void addHeader(List listCSV, MenuBetuReqDto requestDto) {
        /** 1行目作成 */
        List listHeader1Row = new ArrayList();
        //キャンペーン情報
        listHeader1Row.add("表示対象：");
        // 対象条件が支部の場合、ブロック名称を付与する
        if (TaishoJoken.CODE_SIBU.equals(requestDto.getTaishoJoken())) {
            listHeader1Row.add(requestDto.getHyojiTaishoName() + " " + requestDto.getBlockName());
        } else {
            listHeader1Row.add(requestDto.getHyojiTaishoName());
        }
        listHeader1Row.add("");
        listCSV.add(listHeader1Row);
        
        /** 2行目作成 */
        List listHeader2Row = new ArrayList();
        //検索対象キャンペーン期間情報
        listHeader2Row.add("対象期間：");
        if(TaishoKikan.DAY.equals(requestDto.getTaishoKikan())) {
        	listHeader2Row.add(MenubetuUtil.formattYMDSlash(requestDto.getKikanSitei()));
        }
        else {
        	listHeader2Row.add(MenubetuUtil.formattYMKj(requestDto.getKikanSitei()));
        }
        listHeader2Row.add("");
        listCSV.add(listHeader2Row);
        
        /** 3行目作成 */
        List listHeader3Row = new ArrayList();
        //対象店舗数情報
        listHeader3Row.add("対象店舗数：");
        BigDecimal tenpoCnt = ((UIAnalysis)requestDto.getListSearchData().get(0)).getTenpoCnt();
        listHeader3Row.add(numFmtdgt0.format(tenpoCnt));
        listCSV.add(listHeader3Row);
        
        //空白行
        listCSV.add(new ArrayList());
	}
	/**
	 * データ項目名称格納処理
	 * 
	 * @param listCSV
	 * @param requestDto
	 */
	private void addListHeader(List listCSV) {
		//１．ヘッダー部1行目のリストをインスタンス化します。 */
		List listHeader1 = new ArrayList(0);
		listHeader1.add("曜日平均");
		listHeader1.add("時間帯");
		listHeader1.add("時間");
		listHeader1.add("合計");
		listHeader1.add("時間帯構成比");
		listHeader1.add("Ｅ／Ｉ売上");
		listHeader1.add("Ｅ／Ｉ客数");
		listHeader1.add("Ｔ／Ｏ売上");
		listHeader1.add("Ｔ／Ｏ客数");
		listHeader1.add("ＴＥＬ売上");
		listHeader1.add("ＴＥＬ客数");
		listHeader1.add("Ｄ／Ｔ売上");
		listHeader1.add("Ｄ／Ｔ客数");
		listHeader1.add("宅配売上");
		listHeader1.add("宅配客数");
		listHeader1.add("外販売上");
		listHeader1.add("外販客数");
		listHeader1.add("種別７売上");
		listHeader1.add("種別７客数");

//modify 2023/09/25 fukasawa.morimasa 「売上種別8、9、13、14、15」のヘッダ文言を変更 begin
//		listHeader1.add("種別８売上");
//		listHeader1.add("種別８客数");
//		listHeader1.add("種別９売上");
//		listHeader1.add("種別９客数");
		listHeader1.add("委託宅配売上");
		listHeader1.add("委託宅配客数");
		listHeader1.add("シェアデリ売上");
		listHeader1.add("シェアデリ客数");
//modify 2023/09/25 fukasawa.morimasa 「売上種別8、9、13、14、15」のヘッダ文言を変更 end

//modify 2019/01/28 fukasawa.morimasa 「売上種別１０ → Ubereats」にヘッダ文言を変更（e-mossles/BusinessObjects 売上種別追加の件） begin
//		listHeader1.add("種別１０売上");
//		listHeader1.add("種別１０客数");
		listHeader1.add("Ubereats売上");
		listHeader1.add("Ubereats客数");
//modify 2019/01/28 fukasawa.morimasa 「売上種別１０ → Ubereats」にヘッダ文言を変更（e-mossles/BusinessObjects 売上種別追加の件） end

		listHeader1.add("ネットテイク売上");
		listHeader1.add("ネットテイク客数");
		listHeader1.add("ネット宅配売上");
		listHeader1.add("ネット宅配客数");

//modify 2023/09/25 fukasawa.morimasa 「売上種別8、9、13、14、15」のヘッダ文言を変更 begin
//		listHeader1.add("種別１３売上");
//		listHeader1.add("種別１３客数");
//		listHeader1.add("種別１４売上");
//		listHeader1.add("種別１４客数");
//		listHeader1.add("種別１５売上");
//		listHeader1.add("種別１５客数");
		listHeader1.add("ネットイート売上");
		listHeader1.add("ネットイート客数");
		listHeader1.add("フルセルフイート売上");
		listHeader1.add("フルセルフイート客数");
		listHeader1.add("フルセルフテイク売上");
		listHeader1.add("フルセルフテイク客数");
//modify 2023/09/25 fukasawa.morimasa 「売上種別8、9、13、14、15」のヘッダ文言を変更 end
		
//		listHeader1.add("外販・その他売上");
//		listHeader1.add("外販・その他客数");
		listCSV.add(listHeader1);
	}
	/**
	 * データ項目名称格納処理
	 * 
	 * @param listData
	 * @param requestDto
	 * @param listTabSearchData
	 */
	private void addList(List listCSV, List listCsvData) {
		for(int i=0; i<listCsvData.size(); i++) {
			UIWeekShubetu entity = (UIWeekShubetu)listCsvData.get(i);
			List rowData = new ArrayList(0);
			rowData.add(MenubetuUtil.changeBlank(entity.getWeekName()));
			if(RowType.CD_KOUSEIHI.equals(entity.getRowType())) {
				rowData.add("種別構成比");
				rowData.add("99".equals(entity.getTmKbn())? "":MenubetuUtil.changeBlank(entity.getTmName()));
				rowData.add(numFmtdgt2.format(entity.getKingaku()));
				rowData.add("***");//時間帯構成比
				rowData.add(numFmtdgt2.format(entity.getKingakuEat()));
				rowData.add(numFmtdgt2.format(entity.getKyakusuEat()));
				rowData.add(numFmtdgt2.format(entity.getKingakuTake()));
				rowData.add(numFmtdgt2.format(entity.getKyakusuTake()));
				rowData.add(numFmtdgt2.format(entity.getKingakuTel()));
				rowData.add(numFmtdgt2.format(entity.getKyakusuTel()));
				rowData.add(numFmtdgt2.format(entity.getKingakuDrive()));
				rowData.add(numFmtdgt2.format(entity.getKyakusuDrive()));
				rowData.add(numFmtdgt2.format(entity.getKingakuTakuhai()));
				rowData.add(numFmtdgt2.format(entity.getKyakusuTakuhai()));
				rowData.add(numFmtdgt2.format(entity.getKingakuGaihan()));
		        rowData.add(numFmtdgt2.format(entity.getKyakusuGaihan()));
		        rowData.add(numFmtdgt2.format(entity.getKingakuSyubetsu07()));
		        rowData.add(numFmtdgt2.format(entity.getKyakusuSyubetsu07()));
		        rowData.add(numFmtdgt2.format(entity.getKingakuSyubetsu08()));
		        rowData.add(numFmtdgt2.format(entity.getKyakusuSyubetsu08()));
		        rowData.add(numFmtdgt2.format(entity.getKingakuSyubetsu09()));
		        rowData.add(numFmtdgt2.format(entity.getKyakusuSyubetsu09()));
		        rowData.add(numFmtdgt2.format(entity.getKingakuSyubetsu10()));
		        rowData.add(numFmtdgt2.format(entity.getKyakusuSyubetsu10()));
		        rowData.add(numFmtdgt2.format(entity.getKingakuNettake()));
		        rowData.add(numFmtdgt2.format(entity.getKyakusuNettake()));
		        rowData.add(numFmtdgt2.format(entity.getKingakuNetTakuhai()));
		        rowData.add(numFmtdgt2.format(entity.getKyakusuNetTakuhai()));
		        rowData.add(numFmtdgt2.format(entity.getKingakuSyubetsu13()));
		        rowData.add(numFmtdgt2.format(entity.getKyakusuSyubetsu13()));
		        rowData.add(numFmtdgt2.format(entity.getKingakuSyubetsu14()));
		        rowData.add(numFmtdgt2.format(entity.getKyakusuSyubetsu14()));
		        rowData.add(numFmtdgt2.format(entity.getKingakuSyubetsu15()));
		        rowData.add(numFmtdgt2.format(entity.getKyakusuSyubetsu15()));
//				rowData.add(numFmtdgt2.format(entity.getKingakuEtc()));
//				rowData.add(numFmtdgt2.format(entity.getKyakusuEtc()));
				//1行分のデータを格納します。
				listCSV.add(rowData);
				continue;
			}
			if(RowType.CD_TOTAL.equals(entity.getRowType())) {
				rowData.add("合計");
				rowData.add("");//時間
			}
			else {
				if("99".equals(entity.getTmElem())){
					rowData.add("その他");
				}
				else if(RowType.CD_TIMESLOT.equals(entity.getRowType())) {
					rowData.add(MenubetuUtil.changeBlank(entity.getTmElemName()));
				}
				else {
					rowData.add(JikanBetuRefUtil.getTmElemName(entity.getTmKbn())+"時台");
				}
				rowData.add(MenubetuUtil.changeBlank(entity.getTmName()));//時間

			}
			
			rowData.add(numFmtdgt0.format(entity.getKingaku()));
			rowData.add(numFmtdgt2.format(entity.getKingakuKouseiHi()));
			rowData.add(numFmtdgt0.format(entity.getKingakuEat()));
			rowData.add(numFmtdgt0.format(entity.getKyakusuEat()));
			rowData.add(numFmtdgt0.format(entity.getKingakuTake()));
			rowData.add(numFmtdgt0.format(entity.getKyakusuTake()));
			rowData.add(numFmtdgt0.format(entity.getKingakuTel()));
			rowData.add(numFmtdgt0.format(entity.getKyakusuTel()));
			rowData.add(numFmtdgt0.format(entity.getKingakuDrive()));
			rowData.add(numFmtdgt0.format(entity.getKyakusuDrive()));
			rowData.add(numFmtdgt0.format(entity.getKingakuTakuhai()));
			rowData.add(numFmtdgt0.format(entity.getKyakusuTakuhai()));
			rowData.add(numFmtdgt0.format(entity.getKingakuGaihan()));
		    rowData.add(numFmtdgt0.format(entity.getKyakusuGaihan()));
		    rowData.add(numFmtdgt0.format(entity.getKingakuSyubetsu07()));
		    rowData.add(numFmtdgt0.format(entity.getKyakusuSyubetsu07()));
		    rowData.add(numFmtdgt0.format(entity.getKingakuSyubetsu08()));
		    rowData.add(numFmtdgt0.format(entity.getKyakusuSyubetsu08()));
		    rowData.add(numFmtdgt0.format(entity.getKingakuSyubetsu09()));
		    rowData.add(numFmtdgt0.format(entity.getKyakusuSyubetsu09()));
		    rowData.add(numFmtdgt0.format(entity.getKingakuSyubetsu10()));
		    rowData.add(numFmtdgt0.format(entity.getKyakusuSyubetsu10()));
		    rowData.add(numFmtdgt0.format(entity.getKingakuNettake()));
		    rowData.add(numFmtdgt0.format(entity.getKyakusuNettake()));
		    rowData.add(numFmtdgt0.format(entity.getKingakuNetTakuhai()));
		    rowData.add(numFmtdgt0.format(entity.getKyakusuNetTakuhai()));
		    rowData.add(numFmtdgt0.format(entity.getKingakuSyubetsu13()));
		    rowData.add(numFmtdgt0.format(entity.getKyakusuSyubetsu13()));
		    rowData.add(numFmtdgt0.format(entity.getKingakuSyubetsu14()));
		    rowData.add(numFmtdgt0.format(entity.getKyakusuSyubetsu14()));
		    rowData.add(numFmtdgt0.format(entity.getKingakuSyubetsu15()));
		    rowData.add(numFmtdgt0.format(entity.getKyakusuSyubetsu15()));
//			rowData.add(numFmtdgt0.format(entity.getKingakuEtc()));
//			rowData.add(numFmtdgt0.format(entity.getKyakusuEtc()));
			//1行分のデータを格納します。
			listCSV.add(rowData);
		}
	}
    /**
     * 妥当性チェック
     */
	public void validate(CsvOutputDto csvOutputDto) {
        // 画面DTO
        if (csvOutputDto == null) {
            throw new NotNullException("自画面リクエストDTO");
        }
        if ((csvOutputDto instanceof MenuBetuReqDto) == false) {
            throw new MissingConfigException("ABC分析表CSVダウンロード対象の自画面リクエストDTO");
        }
	}
	/**
	 * @return jikanBetuRefSearchWeekShubetuLogic を戻します。
	 */
	public SearchWeekShubetuLogic getJikanBetuRefSearchWeekShubetuLogic() {
		return jikanBetuRefSearchWeekShubetuLogic;
	}

	/**
	 * @param jikanBetuRefSearchWeekShubetuLogic を クラス変数jikanBetuRefSearchWeekShubetuLogicへ設定します。
	 */
	public void setJikanBetuRefSearchWeekShubetuLogic(SearchWeekShubetuLogic logic) {
		this.jikanBetuRefSearchWeekShubetuLogic = logic;
	}


}
