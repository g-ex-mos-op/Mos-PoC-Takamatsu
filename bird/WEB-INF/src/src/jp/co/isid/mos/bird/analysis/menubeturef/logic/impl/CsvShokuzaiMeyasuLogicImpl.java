/**
 * 
 */
package jp.co.isid.mos.bird.analysis.menubeturef.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.analysis.common.menubetu.code.RowType;
import jp.co.isid.mos.bird.analysis.common.menubetu.code.TaishoKikan;
import jp.co.isid.mos.bird.analysis.common.menubetu.dto.MenuBetuReqDto;
import jp.co.isid.mos.bird.analysis.common.menubetu.util.MenubetuUtil;
import jp.co.isid.mos.bird.analysis.menubeturef.dto.RequestDto;
import jp.co.isid.mos.bird.analysis.menubeturef.entity.UIShokuzaiMeyasu;
import jp.co.isid.mos.bird.analysis.menubeturef.logic.SearchShokuzaiMeyasuLogic;
import jp.co.isid.mos.bird.analysis.menubeturef.util.MenuBetuRefUtil;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.MissingConfigException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * 食材準備目安表CSVダウンロードロジック
 * 
 * 作成日:2008/09/10
 * @author xkinu
 *
 */
public class CsvShokuzaiMeyasuLogicImpl implements CsvOutputLogic {
	/** ロジックID */
	public static final String LOGIC_ID = MenuBetuRefUtil.LOGIC_ID_CSV_MEYASU;
	
	private SearchShokuzaiMeyasuLogic menuBetuRefSearchShokuzaiMeyasuLogic;
    // Formatter
    NumericFormatter numFmtdgt0 = new NumericFormatter(true, 0);
    NumericFormatter numFmtdgt2 = new NumericFormatter(true, 2);

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getFileName(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public String getFileName(CsvOutputDto csvOutputDto) {
    	return "MENU.csv";
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public List getOutputData(CsvOutputDto csvOutputDto) {
        // DTO
		MenuBetuReqDto requestDto = (MenuBetuReqDto) csvOutputDto;
		//１．LOGIC【検索結果取得】検索を実行し、検索結果List[[CSV出力用リスト]]を取得します。
		List listCsvData = getMenuBetuRefSearchShokuzaiMeyasuLogic().execute(requestDto);
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
        listHeader1Row.add("");
        listHeader1Row.add("対象店舗：");
        listHeader1Row.add(requestDto.getHyojiTaishoName());
        listHeader1Row.add("");
        listCSV.add(listHeader1Row);
        
        /** 2行目作成 */
        List listHeader2Row = new ArrayList();
        //検索対象キャンペーン期間情報
        listHeader2Row.add("");
        listHeader2Row.add("対象期間：");
        if(TaishoKikan.DAY.equals(requestDto.getTaishoKikan())) {
        	listHeader2Row.add(MenubetuUtil.formattYMDSlash(requestDto.getKikanSitei()));
        }
        else if(TaishoKikan.MONTH.equals(requestDto.getTaishoKikan())){
        	listHeader2Row.add(MenubetuUtil.formattYMSlash(requestDto.getKikanSitei()));
        }
        else if(TaishoKikan.KIKAN.equals(requestDto.getTaishoKikan())){
        	String kikan = MenubetuUtil.formattYMDSlash(requestDto.getKikanSitei())+"～"+MenubetuUtil.formattYMDSlash(((RequestDto)requestDto).getKikanSiteiTo());
        	listHeader2Row.add(kikan);
        }
        listHeader2Row.add("");
        listCSV.add(listHeader2Row);
        
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
		listHeader1.add("ﾒﾆｭｰｺｰﾄﾞ");
		listHeader1.add("メニュー名");
		listHeader1.add("販売個数");
		listHeader1.add("単価");
		listHeader1.add("売上金額");
		listHeader1.add("金額構成比");
		listHeader1.add("EAT-IN");
		listHeader1.add("EAT-IN金額");
		listHeader1.add("TAKE-OUT");
		listHeader1.add("TAKE-OUT金額");
		listHeader1.add("TEL-ORDER");
		listHeader1.add("TEL-ORDER金額");
		listHeader1.add("DRIVE-THROUGH");
		listHeader1.add("DRIVE-THROUGH金額");
		listHeader1.add("その他数");
		listHeader1.add("その他金額");
		listHeader1.add("販売店舗数");
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
			UIShokuzaiMeyasu entity = (UIShokuzaiMeyasu)listCsvData.get(i);
//			if(!CommonUtil.isNull(entity.getRowType())) {
//				//合計行の場合はスキップします。
//				continue;
//			}
			List rowData = new ArrayList(0);
			rowData.add(MenubetuUtil.changeBlank(entity.getMenuCd()));
			rowData.add(MenubetuUtil.changeBlank(entity.getMenuNameKj()));
			rowData.add(numFmtdgt0.format(entity.getKosu()));
			rowData.add(numFmtdgt0.format(entity.getTanka()));
			rowData.add(numFmtdgt0.format(entity.getKingaku()));
            if (RowType.CD_TOTAL.equals(entity.getRowType())) {
                rowData.add("");
            }
            else {
                rowData.add(numFmtdgt2.format(entity.getKingakuKouseiHi()));
            }
			//EAT-IN
			rowData.add(numFmtdgt0.format(entity.getKosuEat()));
			rowData.add(numFmtdgt0.format(entity.getKingakuEat()));
			//TAKE-OUT
			rowData.add(numFmtdgt0.format(entity.getKosuTake()));
			rowData.add(numFmtdgt0.format(entity.getKingakuTake()));
			//TEL
			rowData.add(numFmtdgt0.format(entity.getKosuTel()));
			rowData.add(numFmtdgt0.format(entity.getKingakuTel()));
			//DRIVE
			rowData.add(numFmtdgt0.format(entity.getKosuDrive()));
			rowData.add(numFmtdgt0.format(entity.getKingakuDrive()));
			//その他
			rowData.add(numFmtdgt0.format(entity.getKosuEtc()));
			rowData.add(numFmtdgt0.format(entity.getKingakuEtc()));
			//販売店舗数
            if (RowType.CD_TOTAL.equals(entity.getRowType())) {
                rowData.add("");
            }
            else {
                rowData.add(numFmtdgt0.format(entity.getTenpoCnt()));
            }
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
            throw new MissingConfigException("食材準備目安表CSVダウンロード対象の自画面リクエストDTO");
        }
	}

	/**
	 * @return menuBetuRefSearchShokuzaiMeyasuLogic を戻します。
	 */
	public SearchShokuzaiMeyasuLogic getMenuBetuRefSearchShokuzaiMeyasuLogic() {
		return menuBetuRefSearchShokuzaiMeyasuLogic;
	}

	/**
	 * @param menuBetuRefSearchShokuzaiMeyasuLogic を クラス変数menuBetuRefSearchShokuzaiMeyasuLogicへ設定します。
	 */
	public void setMenuBetuRefSearchShokuzaiMeyasuLogic(SearchShokuzaiMeyasuLogic logic) {
		this.menuBetuRefSearchShokuzaiMeyasuLogic = logic;
	}

}
