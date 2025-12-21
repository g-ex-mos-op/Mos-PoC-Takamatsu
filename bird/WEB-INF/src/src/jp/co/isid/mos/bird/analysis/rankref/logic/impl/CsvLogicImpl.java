/*
 * 作成日: 2008/10/21
 *
 */
package jp.co.isid.mos.bird.analysis.rankref.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.analysis.rankref.code.KibetuKbn;
import jp.co.isid.mos.bird.analysis.rankref.code.TaishoKikan;
import jp.co.isid.mos.bird.analysis.rankref.dto.RequestDto;
import jp.co.isid.mos.bird.analysis.rankref.entity.UIRank;
import jp.co.isid.mos.bird.analysis.rankref.logic.SearchLogic;
import jp.co.isid.mos.bird.analysis.rankref.util.RankRefUtil;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.MissingConfigException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * 売上ランク用
 * CSVダウンロードロジック
 * 
 * @author xkinu
 */
public class CsvLogicImpl implements CsvOutputLogic {
    /** ロジックID */
    public static final String LOGIC_ID = RankRefUtil.LOGIC_ID_CSV;
    /** LOGIC【検索結果取得】*/
    private SearchLogic rankRefSearchLogic;
    // Formatter
    NumericFormatter numFmtdgt0 = new NumericFormatter(true, 0, true);
    NumericFormatter numFmtdgt2 = new NumericFormatter(true, 2);

    /**
     * ファイル名取得
     */
	public String getFileName(CsvOutputDto csvOutputDto) {
		RequestDto requestDto = (RequestDto) csvOutputDto;
		String kikanSitei = requestDto.getKikanSitei();
		if(TaishoKikan.MONTH.equals(requestDto.getTaishoKikan())) {
			return "URIRANK"+kikanSitei+".csv";
		}
		String kikanSiteiToMonth = "";
			try {
				kikanSiteiToMonth = "03";
			}
			catch (Exception ex) {
	            throw new FtlSystemException("年月リストを取得"
	                    , "基準値["+kikanSitei+"]へ1を計算する際のDateManager.getNextYearメソッド処理で例外が発生しました。"
	                    , ex);
			}
			if(KibetuKbn.KI_KAMI.equals(requestDto.getKibetuKbn())) {
				kikanSiteiToMonth = "09";
				kikanSitei = kikanSitei+"04";
			}
			else if(KibetuKbn.KI_SIMO.equals(requestDto.getKibetuKbn())) {
				kikanSitei = kikanSitei+"09";
			}
			else if(KibetuKbn.KI_TOTAL.equals(requestDto.getKibetuKbn())) {
				kikanSitei = kikanSitei+"04";				
			}
		return "URIRANK"+kikanSitei+"-"+kikanSiteiToMonth+".csv";
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
		//１．List[[CSV出力用リスト]]を生成します。
        List listCSV = new ArrayList(0);
    		//２．DTO【自画面Request】.対象期間の値を判断し、CSV出力データList[[日別推移]]を取得します。
    		List listCsvData = getRankRefSearchLogic().execute(requestDto);
        	requestDto.setListSearchData(listCsvData);
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
	private void addHeader(List listCSV, RequestDto requestDto) {
        /** 1行目作成 */
        List listHeader1Row = new ArrayList();
        //キャンペーン情報
        listHeader1Row.add("店舗種別：");
        listHeader1Row.add(requestDto.getTenpoShubetuName());
        listHeader1Row.add("");
        //検索対象キャンペーン期間情報
        listHeader1Row.add("順位：");
        listHeader1Row.add(requestDto.getRankTargetName());
        listCSV.add(listHeader1Row);
        
        /** 2行目作成 */
        List listHeader2Row = new ArrayList();
        //検索対象キャンペーン期間情報
        listHeader2Row.add("対象期間：");
        if(TaishoKikan.MONTH.equals(requestDto.getTaishoKikan())) {
        	listHeader2Row.add(RankRefUtil.formattYMKj(requestDto.getKikanSitei()));
        }
        else {
        	listHeader2Row.add(requestDto.getKikanSitei()+"年度 "+KibetuKbn.getName(requestDto.getKibetuKbn()));
        }
        listCSV.add(listHeader2Row);
        /** 3行目作成 */
        List listHeader3Row = new ArrayList();
        //検索対象キャンペーン期間情報
        listHeader3Row.add("ランク：");
        listHeader3Row.add(requestDto.getRankTypeName());
        listCSV.add(listHeader3Row);
        //空白行
        listCSV.add(new ArrayList());
	}
	/**
	 * データ項目名称格納処理
	 * 
	 * @param listCSV
	 */
	private void addListHeader(List listCSV) {
		//１．ヘッダー部1行目のリストをインスタンス化します。 */
		List listHeader1 = new ArrayList(0);
		listHeader1.add("ランク");
		listHeader1.add("店コード");
		listHeader1.add("店名称");
		listHeader1.add("売上");
		listHeader1.add("達成率");
		listHeader1.add("前年実績(前年同月)");
		listHeader1.add("前年比(売上)");
		listHeader1.add("客数");
		listHeader1.add("前年客数");
		listHeader1.add("前年比(客数)");
		listHeader1.add("客単価");
		listHeader1.add("前年客単価");
		listHeader1.add("前年比(客単価)");
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
			UIRank entity = (UIRank)listCsvData.get(i);
			List rowData = new ArrayList(0);
			//00時～23時
			rowData.add(entity.getRankNo());
			rowData.add(entity.getMiseCd());
			rowData.add(entity.getMiseNameKj());
			rowData.add(numFmtdgt0.format(entity.getUriage()));
			rowData.add(numFmtdgt2.format(entity.getYosanTasseiRitu()));
			rowData.add(numFmtdgt0.format(entity.getUriageZen()));
			rowData.add(numFmtdgt2.format(entity.getUriageZennenHi()));
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
            throw new MissingConfigException("CSV用DTO");
        }
        if ((csvOutputDto instanceof RequestDto) == false) {
            throw new MissingConfigException("自画面リクエストDTO");
        }
        	
        	

	}
	/**
	 * @return rankRefSearchLogic を戻します。
	 */
	public SearchLogic getRankRefSearchLogic() {
		return rankRefSearchLogic;
	}
	/**
	 * @param logic を クラス変数rankRefSearchLogicへ設定します。
	 */
	public void setRankRefSearchLogic(SearchLogic logic) {
		this.rankRefSearchLogic = logic;
	}
}
