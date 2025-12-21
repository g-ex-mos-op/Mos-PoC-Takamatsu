/**
 * 
 */
package jp.co.isid.mos.bird.analysis.menubeturef.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.analysis.common.menubetu.code.RowType;
import jp.co.isid.mos.bird.analysis.common.menubetu.code.TaishoKikan;
import jp.co.isid.mos.bird.analysis.common.menubetu.dto.MenuBetuReqDto;
import jp.co.isid.mos.bird.analysis.common.menubetu.entity.UIAnalysis;
import jp.co.isid.mos.bird.analysis.common.menubetu.util.MenubetuUtil;
import jp.co.isid.mos.bird.analysis.menubeturef.dto.RequestDto;
import jp.co.isid.mos.bird.analysis.menubeturef.entity.UIUriageShubetu;
import jp.co.isid.mos.bird.analysis.menubeturef.logic.SearchUriageShubetuLogic;
import jp.co.isid.mos.bird.analysis.menubeturef.util.MenuBetuRefUtil;
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
public class CsvUriageShubetuLogicImpl implements CsvOutputLogic {
	/** ロジックID */
	public static final String LOGIC_ID = MenuBetuRefUtil.LOGIC_ID_CSV_SHUBETU;
	
	private SearchUriageShubetuLogic menuBetuRefSearchUriageShubetuLogic;
    // Formatter
    NumericFormatter numFmtdgt0 = new NumericFormatter(true, 0);
    NumericFormatter numFmtdgt2 = new NumericFormatter(true, 2);

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getFileName(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public String getFileName(CsvOutputDto csvOutputDto) {
        RequestDto reqestDto = (RequestDto) csvOutputDto;
    	if(TaishoKikan.DAY.equals(reqestDto.getTaishoKikan())){
    		//『期日指定』の場合
        	return "MENUSHU"+reqestDto.getKikanSitei()+".csv";
        }
    	//『月別』の場合
    	return "MENUSHU"+reqestDto.getKikanSitei()+".csv";
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public List getOutputData(CsvOutputDto csvOutputDto) {
        // DTO
		MenuBetuReqDto requestDto = (MenuBetuReqDto) csvOutputDto;
		//１．LOGIC【検索結果取得】検索を実行し、検索結果List[[CSV出力用リスト]]を取得します。
		List listCsvData = getMenuBetuRefSearchUriageShubetuLogic().execute(requestDto);
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
        listHeader1Row.add(requestDto.getHyojiTaishoName());
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
		listHeader1.add("");
		listHeader1.add("");
		listHeader1.add("Ｅ／Ｉ");
		listHeader1.add("");
		listHeader1.add("Ｔ／Ｏ");
		listHeader1.add("");
		listHeader1.add("ＴＥＬ");
		listHeader1.add("");
		listHeader1.add("Ｄ／Ｔ");
		listHeader1.add("");
		listHeader1.add("宅配");
		listHeader1.add("");
		listHeader1.add("外販");
		listHeader1.add("");
		listHeader1.add("種別７");
		listHeader1.add("");

//modify 2023/09/25 fukasawa.morimasa 「売上種別8、9、13、14、15」のヘッダ文言を変更 begin
		//listHeader1.add("種別８");
		//listHeader1.add("");
		//listHeader1.add("種別９");
		listHeader1.add("委託宅配");
		listHeader1.add("");
		listHeader1.add("シェアデリ");
//modify 2023/09/25 fukasawa.morimasa 「売上種別8、9、13、14、15」のヘッダ文言を変更 end
		listHeader1.add("");

//modify 2019/01/28 fukasawa.morimasa 「売上種別１０ → Ubereats」にヘッダ文言を変更（e-mossles/BusinessObjects 売上種別追加の件） begin
//		listHeader1.add("種別１０");
		listHeader1.add("Ubereats");
//modify 2019/01/28 fukasawa.morimasa 「売上種別１０ → Ubereats」にヘッダ文言を変更（e-mossles/BusinessObjects 売上種別追加の件） end

		listHeader1.add("");
		listHeader1.add("ネットテイク");
		listHeader1.add("");
		listHeader1.add("ネット宅配");
		listHeader1.add("");

//modify 2023/09/25 fukasawa.morimasa 「売上種別8、9、13、14、15」のヘッダ文言を変更 begin
//		listHeader1.add("種別１３");
//		listHeader1.add("");
//		listHeader1.add("種別１４");
//		listHeader1.add("");
//		listHeader1.add("種別１５");
		listHeader1.add("ネットイート");
		listHeader1.add("");
		listHeader1.add("フルセルフイート");
		listHeader1.add("");
		listHeader1.add("フルセルフテイク");
//modify 2023/09/25 fukasawa.morimasa 「売上種別8、9、13、14、15」のヘッダ文言を変更 end

//		listHeader1.add("");
//		listHeader1.add("外販・その他");
		listCSV.add(listHeader1);
		
		/** ヘッダー部2行目 */
		List listHeader2 = new ArrayList(0);
		listHeader2.add("ﾒﾆｭｰｺｰﾄﾞ");
		listHeader2.add("メニュー名");
//		for(int i=0; i<6; i++) {
		for(int i=0; i<15; i++) {
			listHeader2.add("個数");
			listHeader2.add("構成比");
		}
		listCSV.add(listHeader2);
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
			UIUriageShubetu entity = (UIUriageShubetu)listCsvData.get(i);
			if(RowType.CD_SUM_MENU.equals(entity.getRowType())) {
				//集計メニュー行の場合はスキップします。
				continue;
			}
			List rowData = new ArrayList(0);
			rowData.add(MenubetuUtil.changeBlank(entity.getMenuCd()));
			rowData.add(MenubetuUtil.changeBlank(entity.getMenuNameKj()));
			rowData.add(numFmtdgt0.format(entity.getKosuEat()));
			rowData.add(numFmtdgt2.format(entity.getKosuKouseiHiEat()));
			rowData.add(numFmtdgt0.format(entity.getKosuTake()));
			rowData.add(numFmtdgt2.format(entity.getKosuKouseiHiTake()));
			rowData.add(numFmtdgt0.format(entity.getKosuTel()));
			rowData.add(numFmtdgt2.format(entity.getKosuKouseiHiTel()));
			rowData.add(numFmtdgt0.format(entity.getKosuDrive()));
			rowData.add(numFmtdgt2.format(entity.getKosuKouseiHiDrive()));
			rowData.add(numFmtdgt0.format(entity.getKosuTakuhai()));
			rowData.add(numFmtdgt2.format(entity.getKosuKouseiHiTakuhai()));
			rowData.add(numFmtdgt0.format(entity.getKosuGaihan()));
			rowData.add(numFmtdgt2.format(entity.getKosuKouseiHiGaihan()));
			rowData.add(numFmtdgt0.format(entity.getKosuSyubetsu07()));
			rowData.add(numFmtdgt2.format(entity.getKosuKouseiHiSyubetsu07()));
			rowData.add(numFmtdgt0.format(entity.getKosuSyubetsu08()));
			rowData.add(numFmtdgt2.format(entity.getKosuKouseiHiSyubetsu08()));
			rowData.add(numFmtdgt0.format(entity.getKosuSyubetsu09()));
			rowData.add(numFmtdgt2.format(entity.getKosuKouseiHiSyubetsu09()));
			rowData.add(numFmtdgt0.format(entity.getKosuSyubetsu10()));
			rowData.add(numFmtdgt2.format(entity.getKosuKouseiHiSyubetsu10()));
			rowData.add(numFmtdgt0.format(entity.getKosuNettake()));
			rowData.add(numFmtdgt2.format(entity.getKosuKouseiHiNettake()));
			rowData.add(numFmtdgt0.format(entity.getKosuNettakuhai()));
			rowData.add(numFmtdgt2.format(entity.getKosuKouseiHiNettakuhai()));
			rowData.add(numFmtdgt0.format(entity.getKosuSyubetsu13()));
			rowData.add(numFmtdgt2.format(entity.getKosuKouseiHiSyubetsu13()));
			rowData.add(numFmtdgt0.format(entity.getKosuSyubetsu14()));
			rowData.add(numFmtdgt2.format(entity.getKosuKouseiHiSyubetsu14()));
			rowData.add(numFmtdgt0.format(entity.getKosuSyubetsu15()));
			rowData.add(numFmtdgt2.format(entity.getKosuKouseiHiSyubetsu15()));
//			rowData.add(numFmtdgt0.format(entity.getKosuEtc()));
//			rowData.add(numFmtdgt2.format(entity.getKosuKouseiHiEtc()));
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
	 * @return menuBetuRefSearchUriageShubetuLogic を戻します。
	 */
	public SearchUriageShubetuLogic getMenuBetuRefSearchUriageShubetuLogic() {
		return menuBetuRefSearchUriageShubetuLogic;
	}

	/**
	 * @param logic を クラス変数menuBetuRefSearchUriageShubetuLogicへ設定します。
	 */
	public void setMenuBetuRefSearchUriageShubetuLogic(
			SearchUriageShubetuLogic logic) {
		this.menuBetuRefSearchUriageShubetuLogic = logic;
	}


}
