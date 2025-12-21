/*
 * 作成日: 2006/04/13
 *
 */
package jp.co.isid.mos.bird.analysis.common.menubetu.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.analysis.common.menubetu.code.RowType;
import jp.co.isid.mos.bird.analysis.common.menubetu.code.TaishoJoken;
import jp.co.isid.mos.bird.analysis.common.menubetu.dto.MenuBetuReqDto;
import jp.co.isid.mos.bird.analysis.common.menubetu.entity.UIAnalysis;
import jp.co.isid.mos.bird.analysis.common.menubetu.entity.UIJikanBetu;
import jp.co.isid.mos.bird.analysis.common.menubetu.logic.SearchJikanBetuLogic;
import jp.co.isid.mos.bird.analysis.common.menubetu.util.MenubetuUtil;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.MissingConfigException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * 時間帯別メニュー別売上
 * CSVダウンロードロジック
 * 
 * @author xkinu
 */
public class CsvJikanBetuLogicImpl implements CsvOutputLogic {
    /** ロジックID */
    public static final String LOGIC_ID = MenubetuUtil.SCREEN_ID+"L03";
    /** LOGIC【時間帯別メニュー別売上検索結果取得】*/
    private SearchJikanBetuLogic analysisMenuBetuSearchJikanBetuLogic;
    // Formatter
    NumericFormatter numFmtdgt0 = new NumericFormatter(true, 0);
    NumericFormatter numFmtdgt2 = new NumericFormatter(true, 2);

    /**
     * ファイル名取得
     */
	public String getFileName(CsvOutputDto csvOutputDto) {
        // DTO
		MenuBetuReqDto requestDto = (MenuBetuReqDto) csvOutputDto;
    	//『月別』のみ
    	return "JIKANMENU"+requestDto.getKikanSitei()+".csv";
	}
	/**
	 * CSV出力処理
	 * 
     *  (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public List getOutputData(CsvOutputDto csvOutputDto)  {
        // DTO
		MenuBetuReqDto requestDto = (MenuBetuReqDto) csvOutputDto;
		//１．DTO【自画面Request】.対象期間の値を判断し、CSV出力データList[[時間帯別メニュー別売上]]を取得します。
		List listCsvData = getAnalysisMenuBetuSearchJikanBetuLogic().execute(requestDto);
		requestDto.setListSearchData(listCsvData);
		
		//２．List[[CSV出力用リスト]]を生成します。
        List listCSV = new ArrayList();
        //３．条件項目ヘッダ作成
		addHeader(listCSV, requestDto, ((UIAnalysis)listCsvData.get(0)).getTenpoCnt());
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
	 * @param listCSV List[[CSV出力用リスト]]
	 * @param requestDto
	 * @param tanpoCnt
	 */
	private void addHeader(List listCSV, MenuBetuReqDto requestDto, BigDecimal tenpoCnt) {
        /** 1行目作成 */
        List listHeader1Row = new ArrayList();
        //キャンペーン情報
        listHeader1Row.add("表示対象：");
        if(TaishoJoken.CODE_ALL.equals(requestDto.getTaishoJoken())
        		&& UserType.HONBU.equals(requestDto.getUserTypeCd())) {
        	listHeader1Row.add(requestDto.getHyojiTaishoName()+"("+requestDto.getTenpoShubetuName()+")");
        } else if (TaishoJoken.CODE_SIBU.equals(requestDto.getTaishoJoken())
                && UserType.HONBU.equals(requestDto.getUserTypeCd())) {
            listHeader1Row.add(requestDto.getHyojiTaishoName() + " " + requestDto.getBlockName());
        }
        else {
            listHeader1Row.add(requestDto.getHyojiTaishoName());
        }
        listHeader1Row.add("");
        listCSV.add(listHeader1Row);
        
        /** 2行目作成 */
        List listHeader2Row = new ArrayList();
        //検索対象キャンペーン期間情報
        listHeader2Row.add("対象期間：");
       	listHeader2Row.add(MenubetuUtil.formattYMKj(requestDto.getKikanSitei()));
        listHeader2Row.add("");
        listCSV.add(listHeader2Row);
        
        /** 3行目作成 */
        List listHeader3Row = new ArrayList();
        //対象店舗数情報
        listHeader3Row.add("対象店舗数：");
        listHeader3Row.add(numFmtdgt0.format(tenpoCnt));
        listCSV.add(listHeader3Row);
        
        //空白行
        listCSV.add(new ArrayList());
	}
	/**
	 * データ項目名称格納処理
	 * 
	 * @param listCSV List[[CSV出力用リスト]]
	 * @param requestDto
	 */
	private void addListHeader(List listCSV, MenuBetuReqDto requestDto) {
		//１．ヘッダー部1行目のリストをインスタンス化します。 */
		List listHeader1 = new ArrayList(0);
		listHeader1.add("");
		listHeader1.add("");
		listHeader1.add("合計");
		listHeader1.add("");
		listHeader1.add("ﾒﾆｭｰ構成比");
		listHeader1.add("");
		//00時～23時までの項目名称を設定します。
		CodeFormatter cdf = new CodeFormatter(2, "00");
        cdf.setFormatPattern("00");
		for(int i=0; i<=23; i++) {
			listHeader1.add(cdf.format(String.valueOf(i), true)+"時台");
			listHeader1.add("");
		}
		listHeader1.add("その他");
		listHeader1.add("");
		//２.引数List[[CSV出力用リスト]]へヘッダー部1行目のリストを追加します。
		listCSV.add(listHeader1);
		
		//３．ヘッダー部2行目のリストをインスタンス化します。 */
		List listHeader2 = new ArrayList(0);
		listHeader2.add("ﾒﾆｭｰｺｰﾄﾞ");
		listHeader2.add("メニュー名");
		for(int i=0;i<=26; i++) {
			listHeader2.add("金額");
			listHeader2.add("個数");
		}
		//４.引数List[[CSV出力用リスト]]へヘッダー部2行目のリストを追加します。
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
			UIJikanBetu eJikan = (UIJikanBetu)listCsvData.get(i);
			if(RowType.CD_SUM_MENU.equals(eJikan.getRowType())) {
				//集計メニュー行の場合はスキップします。
				continue;
			}
			List rowData = new ArrayList(0);
			rowData.add(MenubetuUtil.changeBlank(eJikan.getMenuCd()));
			rowData.add(MenubetuUtil.changeBlank(eJikan.getMenuNameKj()));
			//合計
			rowData.add(numFmtdgt0.format(eJikan.getKingaku()));
			rowData.add(numFmtdgt0.format(eJikan.getKosu()));
			//ﾒﾆｭｰ構成比
			if(RowType.CD_JKOUSEIHI.equals(eJikan.getRowType())) {
				rowData.add("***");
				rowData.add("***");
				//00時～23時
				rowData.add(numFmtdgt2.format(eJikan.getKin00()));
				rowData.add(numFmtdgt2.format(eJikan.getKosu00()));
				rowData.add(numFmtdgt2.format(eJikan.getKin01()));
				rowData.add(numFmtdgt2.format(eJikan.getKosu01()));
				rowData.add(numFmtdgt2.format(eJikan.getKin02()));
				rowData.add(numFmtdgt2.format(eJikan.getKosu02()));
				rowData.add(numFmtdgt2.format(eJikan.getKin03()));
				rowData.add(numFmtdgt2.format(eJikan.getKosu03()));
				rowData.add(numFmtdgt2.format(eJikan.getKin04()));
				rowData.add(numFmtdgt2.format(eJikan.getKosu04()));
				rowData.add(numFmtdgt2.format(eJikan.getKin05()));
				rowData.add(numFmtdgt2.format(eJikan.getKosu05()));
				rowData.add(numFmtdgt2.format(eJikan.getKin06()));
				rowData.add(numFmtdgt2.format(eJikan.getKosu06()));
				rowData.add(numFmtdgt2.format(eJikan.getKin07()));
				rowData.add(numFmtdgt2.format(eJikan.getKosu07()));
				rowData.add(numFmtdgt2.format(eJikan.getKin08()));
				rowData.add(numFmtdgt2.format(eJikan.getKosu08()));
				rowData.add(numFmtdgt2.format(eJikan.getKin09()));
				rowData.add(numFmtdgt2.format(eJikan.getKosu09()));
				rowData.add(numFmtdgt2.format(eJikan.getKin10()));
				rowData.add(numFmtdgt2.format(eJikan.getKosu10()));
				rowData.add(numFmtdgt2.format(eJikan.getKin11()));
				rowData.add(numFmtdgt2.format(eJikan.getKosu11()));
				rowData.add(numFmtdgt2.format(eJikan.getKin12()));
				rowData.add(numFmtdgt2.format(eJikan.getKosu12()));
				rowData.add(numFmtdgt2.format(eJikan.getKin13()));
				rowData.add(numFmtdgt2.format(eJikan.getKosu13()));
				rowData.add(numFmtdgt2.format(eJikan.getKin14()));
				rowData.add(numFmtdgt2.format(eJikan.getKosu14()));
				rowData.add(numFmtdgt2.format(eJikan.getKin15()));
				rowData.add(numFmtdgt2.format(eJikan.getKosu15()));
				rowData.add(numFmtdgt2.format(eJikan.getKin16()));
				rowData.add(numFmtdgt2.format(eJikan.getKosu16()));
				rowData.add(numFmtdgt2.format(eJikan.getKin17()));
				rowData.add(numFmtdgt2.format(eJikan.getKosu17()));
				rowData.add(numFmtdgt2.format(eJikan.getKin18()));
				rowData.add(numFmtdgt2.format(eJikan.getKosu18()));
				rowData.add(numFmtdgt2.format(eJikan.getKin19()));
				rowData.add(numFmtdgt2.format(eJikan.getKosu19()));
				rowData.add(numFmtdgt2.format(eJikan.getKin20()));
				rowData.add(numFmtdgt2.format(eJikan.getKosu20()));
				rowData.add(numFmtdgt2.format(eJikan.getKin21()));
				rowData.add(numFmtdgt2.format(eJikan.getKosu21()));
				rowData.add(numFmtdgt2.format(eJikan.getKin22()));
				rowData.add(numFmtdgt2.format(eJikan.getKosu22()));
				rowData.add(numFmtdgt2.format(eJikan.getKin23()));
				rowData.add(numFmtdgt2.format(eJikan.getKosu23()));
				//その他
				rowData.add(numFmtdgt2.format(eJikan.getKinEtc()));
				rowData.add(numFmtdgt2.format(eJikan.getKosuEtc()));
			}
			else {
				rowData.add(numFmtdgt0.format(eJikan.getKingakuKouseiHi()));
				rowData.add(numFmtdgt0.format(eJikan.getKosuKouseiHi()));
				//00時～23時
				rowData.add(numFmtdgt0.format(eJikan.getKin00()));
				rowData.add(numFmtdgt0.format(eJikan.getKosu00()));
				rowData.add(numFmtdgt0.format(eJikan.getKin01()));
				rowData.add(numFmtdgt0.format(eJikan.getKosu01()));
				rowData.add(numFmtdgt0.format(eJikan.getKin02()));
				rowData.add(numFmtdgt0.format(eJikan.getKosu02()));
				rowData.add(numFmtdgt0.format(eJikan.getKin03()));
				rowData.add(numFmtdgt0.format(eJikan.getKosu03()));
				rowData.add(numFmtdgt0.format(eJikan.getKin04()));
				rowData.add(numFmtdgt0.format(eJikan.getKosu04()));
				rowData.add(numFmtdgt0.format(eJikan.getKin05()));
				rowData.add(numFmtdgt0.format(eJikan.getKosu05()));
				rowData.add(numFmtdgt0.format(eJikan.getKin06()));
				rowData.add(numFmtdgt0.format(eJikan.getKosu06()));
				rowData.add(numFmtdgt0.format(eJikan.getKin07()));
				rowData.add(numFmtdgt0.format(eJikan.getKosu07()));
				rowData.add(numFmtdgt0.format(eJikan.getKin08()));
				rowData.add(numFmtdgt0.format(eJikan.getKosu08()));
				rowData.add(numFmtdgt0.format(eJikan.getKin09()));
				rowData.add(numFmtdgt0.format(eJikan.getKosu09()));
				rowData.add(numFmtdgt0.format(eJikan.getKin10()));
				rowData.add(numFmtdgt0.format(eJikan.getKosu10()));
				rowData.add(numFmtdgt0.format(eJikan.getKin11()));
				rowData.add(numFmtdgt0.format(eJikan.getKosu11()));
				rowData.add(numFmtdgt0.format(eJikan.getKin12()));
				rowData.add(numFmtdgt0.format(eJikan.getKosu12()));
				rowData.add(numFmtdgt0.format(eJikan.getKin13()));
				rowData.add(numFmtdgt0.format(eJikan.getKosu13()));
				rowData.add(numFmtdgt0.format(eJikan.getKin14()));
				rowData.add(numFmtdgt0.format(eJikan.getKosu14()));
				rowData.add(numFmtdgt0.format(eJikan.getKin15()));
				rowData.add(numFmtdgt0.format(eJikan.getKosu15()));
				rowData.add(numFmtdgt0.format(eJikan.getKin16()));
				rowData.add(numFmtdgt0.format(eJikan.getKosu16()));
				rowData.add(numFmtdgt0.format(eJikan.getKin17()));
				rowData.add(numFmtdgt0.format(eJikan.getKosu17()));
				rowData.add(numFmtdgt0.format(eJikan.getKin18()));
				rowData.add(numFmtdgt0.format(eJikan.getKosu18()));
				rowData.add(numFmtdgt0.format(eJikan.getKin19()));
				rowData.add(numFmtdgt0.format(eJikan.getKosu19()));
				rowData.add(numFmtdgt0.format(eJikan.getKin20()));
				rowData.add(numFmtdgt0.format(eJikan.getKosu20()));
				rowData.add(numFmtdgt0.format(eJikan.getKin21()));
				rowData.add(numFmtdgt0.format(eJikan.getKosu21()));
				rowData.add(numFmtdgt0.format(eJikan.getKin22()));
				rowData.add(numFmtdgt0.format(eJikan.getKosu22()));
				rowData.add(numFmtdgt0.format(eJikan.getKin23()));
				rowData.add(numFmtdgt0.format(eJikan.getKosu23()));
				//その他
				rowData.add(numFmtdgt0.format(eJikan.getKinEtc()));
				rowData.add(numFmtdgt0.format(eJikan.getKosuEtc()));
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
            throw new MissingConfigException("時間帯別メニュー別売上CSVダウンロード対象の自画面リクエストDTO");
        }
        	
        	

	}
	/**
	 * @return analysisMenuBetuSearchJikanBetuLogic を戻します。
	 */
	public SearchJikanBetuLogic getAnalysisMenuBetuSearchJikanBetuLogic() {
		return analysisMenuBetuSearchJikanBetuLogic;
	}
	/**
	 * @param analysisMenuBetuSearchJikanBetuLogic を クラス変数analysisMenuBetuSearchJikanBetuLogicへ設定します。
	 */
	public void setAnalysisMenuBetuSearchJikanBetuLogic(
			SearchJikanBetuLogic analysisMenuBetuSearchJikanBetuLogic) {
		this.analysisMenuBetuSearchJikanBetuLogic = analysisMenuBetuSearchJikanBetuLogic;
	}
}
