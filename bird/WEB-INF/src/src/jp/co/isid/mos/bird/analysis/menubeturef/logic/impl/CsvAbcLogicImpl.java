/*
 * 作成日: 2006/04/13
 *
 */
package jp.co.isid.mos.bird.analysis.menubeturef.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.analysis.common.menubetu.code.RowType;
import jp.co.isid.mos.bird.analysis.common.menubetu.code.TaishoKikan;
import jp.co.isid.mos.bird.analysis.common.menubetu.entity.UIAnalysis;
import jp.co.isid.mos.bird.analysis.common.menubetu.util.MenubetuUtil;
import jp.co.isid.mos.bird.analysis.menubeturef.dto.RequestDto;
import jp.co.isid.mos.bird.analysis.menubeturef.entity.UIAbcDay;
import jp.co.isid.mos.bird.analysis.menubeturef.entity.UIAbcMonth;
import jp.co.isid.mos.bird.analysis.menubeturef.logic.SearchAbcLogic;
import jp.co.isid.mos.bird.analysis.menubeturef.util.MenuBetuRefUtil;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.MissingConfigException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * ABC分析表用
 * CSVダウンロードロジック
 * 
 * @author xkinu
 */
public class CsvAbcLogicImpl implements CsvOutputLogic {
    /** ロジックID */
    public static final String LOGIC_ID = MenuBetuRefUtil.LOGIC_ID_CSV_ABC;
    
    /** LOGIC【ABC分析表検索結果取得】*/
    private SearchAbcLogic menuBetuRefSearchAbcLogic;
    // Formatter
    NumericFormatter numFmtdgt0 = new NumericFormatter(true, 0);
    NumericFormatter numFmtdgt2 = new NumericFormatter(true, 2);

    /**
     * ファイル名取得
     */
	public String getFileName(CsvOutputDto csvOutputDto) {
		RequestDto requestDto = (RequestDto) csvOutputDto;
		//『集約』の場合
		if(requestDto.isOutputSmenu()) {
			return "ABCMENU_GROUP"+requestDto.getKikanSitei()+".csv";
		}
		//『単品』の場合
		return "ABCMENU"+requestDto.getKikanSitei()+".csv";
    	
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
		//１．LOGIC【検索結果取得】検索を実行し、検索結果List[[CSV出力用リスト]]を取得します。
        requestDto.setCsv(true);
		List listCsvData = getMenuBetuRefSearchAbcLogic().execute(requestDto);
    	requestDto.setListSearchData(listCsvData);
    	
		//２．List[[CSV出力用リスト]]を生成します。
        List listCSV = new ArrayList();
        //３．条件項目ヘッダ作成
		addHeader(listCSV, requestDto);
        //４．明細部ヘッダー作成
		addListHeader(listCSV, requestDto);
        //５．明細部データ作成
		String disableRowType = requestDto.isOutputSmenu()?RowType.CD_MENU:RowType.CD_SUM_MENU;
		addList(disableRowType, listCSV, listCsvData);
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
        listHeader1Row.add("表示対象：");
        listHeader1Row.add(requestDto.getHyojiTaishoName());
        listHeader1Row.add("");
        listHeader1Row.add("メニュー：");
        listHeader1Row.add(requestDto.isOutputSmenu()?"集約":"単品");
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
	private void addListHeader(List listCSV, RequestDto requestDto) {
		String taishoKikan = requestDto.getTaishoKikan();
		//１．ヘッダー部1行目のリストをインスタンス化します。 */
		List listHeader1 = new ArrayList(0);
		listHeader1.add("");
		listHeader1.add("");
		//２．期間指定の項目名称を設定します。
		if(TaishoKikan.DAY.equals(taishoKikan)) {
			//『期日指定』の場合、名称『指定日』を設定します。
			listHeader1.add("指定日");
		}
		else if(TaishoKikan.MONTH.equals(taishoKikan)) {
			//『月別』の場合、期間指定の値をyyyy年MM月の表示形式で設定します。
			listHeader1.add(MenubetuUtil.formattYMKj(requestDto.getKikanSitei()));
		}
		if(requestDto.isOutputSmenu()==false) {
			listHeader1.add("");
		}
		listHeader1.add("");
		listHeader1.add("");
		listHeader1.add("");
		//３．『月別』の場合、前月の項目を設定します。
		if(TaishoKikan.MONTH.equals(taishoKikan)) {
			listHeader1.add(MenubetuUtil.formattYMKj(((RequestDto)requestDto).getKikanSiteiLastMonth()));
			listHeader1.add("");
			listHeader1.add("");
		}
		//前年データ種別の名称を設定します。『月別』の場合、名称『前年同月』を『期日指定』の場合、名称『前年同曜日』を設定します。
		listHeader1.add(requestDto.getZennenDataShubetuName());
		listCSV.add(listHeader1);
		
		/** ヘッダー部2行目 */
		List listHeader2 = new ArrayList(0);
		listHeader2.add("ﾒﾆｭｰｺｰﾄﾞ");
		listHeader2.add("メニュー名");
		if(requestDto.isOutputSmenu()==false) {
			listHeader2.add("単価");
		}
		listHeader2.add("販売店舗数");
		listHeader2.add("販売個数");
		listHeader2.add("売上金額");
		listHeader2.add("金額構成比");
		if(TaishoKikan.MONTH.equals(taishoKikan)) {
			listHeader2.add("販売個数");
			listHeader2.add("売上金額");
			listHeader2.add("金額構成比");
		}
		listHeader2.add("販売個数");
		listHeader2.add("売上金額");
		listHeader2.add("金額構成比");
		listCSV.add(listHeader2);
	}
	/**
	 * データ項目名称格納処理
	 * 
	 * @param disableMenu 非表示行タイプ
	 * @param listCSV
	 * @param listCsvData
	 */
	private void addList(String disableRowType, List listCSV, List listCsvData) {
		for(int i=0; i<listCsvData.size(); i++) {
			UIAbcDay entity = (UIAbcDay)listCsvData.get(i);
			if(disableRowType.equals(entity.getRowType())) {
				//非表示対象行の場合はスキップします。
				continue;
			}
			List rowData = new ArrayList(0);
			if (RowType.CD_SUM_MENU.equals(entity.getRowType()) ) {
				rowData.add(MenubetuUtil.changeBlank(entity.getSumMenuCd()));//集約メニューコード
				rowData.add(MenubetuUtil.changeBlank(entity.getSumMenuNameKj()));//集約メニュー名称
			}
			else {
				rowData.add(MenubetuUtil.changeBlank(entity.getMenuCd()));//メニューコード
				rowData.add(MenubetuUtil.changeBlank(entity.getMenuNameKj()));//メニュー名称
			}
			if(RowType.CD_MENU.equals(disableRowType) == false) {
				rowData.add(numFmtdgt0.format(entity.getTanka()));//単価
			}
			if(RowType.CD_TOTAL.equals(entity.getRowType())
					|| RowType.CD_MENU_BUNRUI.equals(entity.getRowType())) {
				//合計行と分類計行は対象店舗数を空で表示する。
				rowData.add("");
			}	else {
				rowData.add(numFmtdgt0.format(entity.getHanbaiTenpoCnt()));
			}
			rowData.add(numFmtdgt0.format(entity.getKosu()));
			rowData.add(numFmtdgt0.format(entity.getKingaku()));
			rowData.add(numFmtdgt2.format(entity.getKingakuKouseiHi()));
			if(entity instanceof UIAbcMonth) {
				rowData.add(numFmtdgt0.format(((UIAbcMonth)entity).getKosuZenGetu()));
				rowData.add(numFmtdgt0.format(((UIAbcMonth)entity).getKingakuZenGetu()));
				rowData.add(numFmtdgt2.format(((UIAbcMonth)entity).getKingakuKouseiHiZenGetu()));
			}
			rowData.add(numFmtdgt0.format(entity.getKosuZenNen()));
			rowData.add(numFmtdgt0.format(entity.getKingakuZenNen()));
			rowData.add(numFmtdgt2.format(entity.getKingakuKouseiHiZenNen()));
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
        if ((csvOutputDto instanceof RequestDto) == false) {
            throw new MissingConfigException("ABC分析表CSVダウンロード対象の自画面リクエストDTO");
        }
        if (CommonUtil.isNull(((RequestDto)csvOutputDto).getCsvDataKbn())) {
        	throw new MissingConfigException("ABC分析表CSVダウンロード対象 単品又は集約判断値");
        }
        	
        	

	}
	/**
	 * @return menuBetuRefSearchAbcLogic を戻します。
	 */
	public SearchAbcLogic getMenuBetuRefSearchAbcLogic() {
		return menuBetuRefSearchAbcLogic;
	}
	/**
	 * @param logic を クラス変数menuBetuRefSearchAbcLogicへ設定します。
	 */
	public void setMenuBetuRefSearchAbcLogic(
			SearchAbcLogic logic) {
		this.menuBetuRefSearchAbcLogic = logic;
	}
}
