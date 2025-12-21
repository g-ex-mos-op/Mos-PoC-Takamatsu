/*
 * 作成日: 2006/04/13
 *
 */
package jp.co.isid.mos.bird.analysis.jikanbeturef.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.analysis.common.menubetu.code.TaishoJoken;
import jp.co.isid.mos.bird.analysis.common.menubetu.dto.MenuBetuReqDto;
import jp.co.isid.mos.bird.analysis.common.menubetu.entity.UIAnalysis;
import jp.co.isid.mos.bird.analysis.common.menubetu.util.MenubetuUtil;
import jp.co.isid.mos.bird.analysis.jikanbeturef.code.RowType;
import jp.co.isid.mos.bird.analysis.jikanbeturef.dto.RequestDto;
import jp.co.isid.mos.bird.analysis.jikanbeturef.entity.UIWeek;
import jp.co.isid.mos.bird.analysis.jikanbeturef.logic.SearchWeekLogic;
import jp.co.isid.mos.bird.analysis.jikanbeturef.util.JikanBetuRefUtil;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.MissingConfigException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * 曜日別用
 * CSVダウンロードロジック
 * 
 * 作成日:2008/09/25
 * @author xkinu
 *
 */
public class CsvWeekLogicImpl implements CsvOutputLogic {
    /** ロジックID */
    public static final String LOGIC_ID = JikanBetuRefUtil.LOGIC_ID_CSV_WEEK;
    /** LOGIC【検索結果取得】*/
    private SearchWeekLogic jikanBetuRefSearchWeekLogic;
    // Formatter
    NumericFormatter numFmtdgt0 = new NumericFormatter(true, 0);
    NumericFormatter numFmtdgt2 = new NumericFormatter(true, 2);

    /**
     * ファイル名取得
     */
	public String getFileName(CsvOutputDto csvOutputDto) {
        MenuBetuReqDto requestDto = (MenuBetuReqDto) csvOutputDto;
    	return "JIKANYOBI"+requestDto.getKikanSitei()+".csv";
	}
	/**
	 * CSV出力処理
	 * 
     *  (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public List getOutputData(CsvOutputDto csvOutputDto)  {
        // DTO
		RequestDto requestDto = (RequestDto) csvOutputDto;
		requestDto.setCsvMode(true);
		//１．LOGIC【検索結果取得】検索を実行し、検索結果List[[CSV出力用リスト]]を取得します。
		List listCsvData = getJikanBetuRefSearchWeekLogic().execute(requestDto);
    	requestDto.setListSearchData(listCsvData);
    	
		//２．List[[CSV出力用リスト]]を生成します。
        List listCSV = new ArrayList();
        //３．条件項目ヘッダ作成
		addHeader(listCSV, requestDto);
        //４．明細部ヘッダー作成
		addListHeader(listCSV, requestDto);
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
        listCSV.add(listHeader1Row);
        
        /** 2行目作成 */
        List listHeader2Row = new ArrayList();
        //検索対象キャンペーン期間情報
        listHeader2Row.add("対象期間：");
       	listHeader2Row.add(MenubetuUtil.formattYMKj(requestDto.getKikanSitei()));
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
	private void addListHeader(List listCSV, RequestDto requestDto) {
		//１．ヘッダー部1行目のリストをインスタンス化します。 */
		List listHeader1 = new ArrayList(0);
		listHeader1.add("曜日平均");
		listHeader1.add("時間帯");
		listHeader1.add("時間");
		listHeader1.add("売上");
		listHeader1.add("構成比");
		listHeader1.add("前年売上");
		listHeader1.add("売上前年比");
		listHeader1.add("客数");
		listHeader1.add("前年客数");
		listHeader1.add("客数前年比");
		listHeader1.add("客単価");
		listHeader1.add("前年客単価");
		listHeader1.add("客単価前年比");
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
			UIWeek entity = (UIWeek)listCsvData.get(i);
			List rowData = new ArrayList(0);
			rowData.add(MenubetuUtil.changeBlank(entity.getWeekName()));
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
			rowData.add(numFmtdgt0.format(entity.getKingakuZen()));
			rowData.add(numFmtdgt2.format(entity.getKingakuZennenHi()));
			rowData.add(numFmtdgt0.format(entity.getKyakusu()));
			rowData.add(numFmtdgt0.format(entity.getKyakusuZen()));
			rowData.add(numFmtdgt2.format(entity.getKyakusuZennenHi()));
			rowData.add(numFmtdgt0.format(entity.getKyakutanka()));
			rowData.add(numFmtdgt0.format(entity.getKyakutankaZen()));
			rowData.add(numFmtdgt2.format(entity.getKyakutankaZennenHi()));
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
            throw new MissingConfigException("曜日別CSVダウンロード対象の自画面リクエストDTO");
        }
        	
        	

	}
	/**
	 * @return jikanBetuRefBetuSearchWeekLogic を戻します。
	 */
	public SearchWeekLogic getJikanBetuRefSearchWeekLogic() {
		return jikanBetuRefSearchWeekLogic;
	}
	/**
	 * @param logic を クラス変数jikanBetuRefSearchWeekLogicへ設定します。
	 */
	public void setJikanBetuRefSearchWeekLogic(SearchWeekLogic logic) {
		this.jikanBetuRefSearchWeekLogic = logic;
	}
}
