/*
 * 作成日: 2006/04/13
 *
 */
package jp.co.isid.mos.bird.analysis.jikanbeturef.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.analysis.common.menubetu.code.RowType;
import jp.co.isid.mos.bird.analysis.common.menubetu.code.TaishoJoken;
import jp.co.isid.mos.bird.analysis.common.menubetu.code.TaishoKikan;
import jp.co.isid.mos.bird.analysis.common.menubetu.dto.MenuBetuReqDto;
import jp.co.isid.mos.bird.analysis.common.menubetu.entity.UIMenu;
import jp.co.isid.mos.bird.analysis.common.menubetu.util.MenubetuUtil;
import jp.co.isid.mos.bird.analysis.jikanbeturef.dto.RequestDto;
import jp.co.isid.mos.bird.analysis.jikanbeturef.entity.UISuiiNipo;
import jp.co.isid.mos.bird.analysis.jikanbeturef.logic.SearchSuiiNipoLogic;
import jp.co.isid.mos.bird.analysis.jikanbeturef.util.JikanBetuRefUtil;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.entity.CodHyojiTaisho;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.MissingConfigException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * 日別推移用
 * CSVダウンロードロジック
 * 
 * @author xkinu
 */
public class CsvSuiiNipoLogicImpl implements CsvOutputLogic {
    /** ロジックID */
    public static final String LOGIC_ID = JikanBetuRefUtil.LOGIC_ID_CSV_SUII;
    /** LOGIC【検索結果取得】*/
    private SearchSuiiNipoLogic jikanBetuRefBetuSearchSuiiNipoLogic;
    // Formatter
    NumericFormatter numFmtdgt0 = new NumericFormatter(true, 0);
    NumericFormatter numFmtdgt2 = new NumericFormatter(true, 2);

    /**
     * ファイル名取得
     */
	public String getFileName(CsvOutputDto csvOutputDto) {
        MenuBetuReqDto requestDto = (MenuBetuReqDto) csvOutputDto;
    	return "JIKANSUIIDAY"+requestDto.getKikanSitei()+".csv";
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
    	if(UserType.ONER.equals(requestDto.getUserTypeCd()) 
    			&& TaishoJoken.CODE_ALL.equals(requestDto.getTaishoJoken())) {
    		//２－１．DTO【自画面Session】.[[表示対象(店舗)]]を取得します。
    		List listTaishoTenpo = requestDto.getMenuBetuSesDto().getListHyojiTaisho(TaishoJoken.CODE_MISE);
    		for(int i=0; i<listTaishoTenpo.size(); i++) {
    			//１．DTO【自画面Request】.対象条件へ『店舗』を設定します。
    			requestDto.setTaishoJoken(TaishoJoken.CODE_MISE);
    			//２．DTO【自画面Request】.表示対象へ現行インデックスにある
    			//    DTO【自画面Session】.[[表示対象(店舗)]].[対象店舗].表示対象コードを設定します。
    			requestDto.setHyojiTaisho(((CodHyojiTaisho)listTaishoTenpo.get(i)).getHyojitaishoCd());
    			try {
	        		//３．DTO【自画面Request】.対象期間の値を判断し、CSV出力データList[[日別推移]]を取得します。
		        	requestDto.setListSearchData(getJikanBetuRefSearchSuiiNipoLogic().execute(requestDto));
    			}
    			catch (NoResultException noResult) {
    				continue;
    			}
		        //４．条件項目ヘッダ作成
				addHeader(listCSV, requestDto);
		        //５．明細部ヘッダー作成
				addListHeader(listCSV);
		        //６．明細部データ作成
				addList(listCSV, requestDto.getListSearchData());
				//７．空白行
				listCSV.add(new ArrayList(0));
    		}
    		if(listCSV.size()==0) {
    			//１．DTO【自画面Request】.対象条件へ『店舗』を設定します。
    			requestDto.setTaishoJoken(TaishoJoken.CODE_ALL);
    			requestDto.setHyojiTaisho(((CodHyojiTaisho)listTaishoTenpo.get(0)).getHyojitaishoCd());
    			throw new NoResultException();
    		}
    	}
    	else {
    		//２．DTO【自画面Request】.対象期間の値を判断し、CSV出力データList[[日別推移]]を取得します。
    		List listCsvData = getJikanBetuRefSearchSuiiNipoLogic().execute(requestDto);
        	requestDto.setListSearchData(listCsvData);
	        //３．条件項目ヘッダ作成
			addHeader(listCSV, requestDto);
	        //４．明細部ヘッダー作成
			addListHeader(listCSV);
	        //５．明細部データ作成
			addList(listCSV, listCsvData);
    	}
		
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
        
        if(UserType.HONBU.equals(requestDto.getUserTypeCd())) {
	        /** 3行目作成 */
	        List listHeader3Row = new ArrayList();
	        //対象店舗数情報
	        listHeader3Row.add("対象店舗数：");
	        BigDecimal tenpoCnt = ((UIMenu)requestDto.getListSearchData().get(0)).getTenpoCnt();
	        listHeader3Row.add(numFmtdgt0.format(tenpoCnt));
	        listCSV.add(listHeader3Row);
        }
        
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
		
		/** ヘッダー部2行目 */
		List listHeader2 = new ArrayList(0);
		listHeader2.add("営業日");
		for(int i=0;i<=24; i++) {
			listHeader2.add("売上");
			listHeader2.add("客数");
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
		DateFormatter formatter = new DateFormatter();
		for(int i=0; i<listCsvData.size(); i++) {
			UISuiiNipo entity = (UISuiiNipo)listCsvData.get(i);
			if(RowType.CD_SUM_MENU.equals(entity.getRowType())) {
				//集計メニュー行の場合はスキップします。
				continue;
			}
			List rowData = new ArrayList(0);
			rowData.add(formatter.format(entity.getTimeDt(), "dd'日('E')'"
					, DateFormatter.DATE_TYPE_YMD));
			//00時～23時
			rowData.add(numFmtdgt0.format(entity.getUriage00()));
			rowData.add(numFmtdgt0.format(entity.getKazu00()));
			rowData.add(numFmtdgt0.format(entity.getUriage01()));
			rowData.add(numFmtdgt0.format(entity.getKazu01()));
			rowData.add(numFmtdgt0.format(entity.getUriage02()));
			rowData.add(numFmtdgt0.format(entity.getKazu02()));
			rowData.add(numFmtdgt0.format(entity.getUriage03()));
			rowData.add(numFmtdgt0.format(entity.getKazu03()));
			rowData.add(numFmtdgt0.format(entity.getUriage04()));
			rowData.add(numFmtdgt0.format(entity.getKazu04()));
			rowData.add(numFmtdgt0.format(entity.getUriage05()));
			rowData.add(numFmtdgt0.format(entity.getKazu05()));
			rowData.add(numFmtdgt0.format(entity.getUriage06()));
			rowData.add(numFmtdgt0.format(entity.getKazu06()));
			rowData.add(numFmtdgt0.format(entity.getUriage07()));
			rowData.add(numFmtdgt0.format(entity.getKazu07()));
			rowData.add(numFmtdgt0.format(entity.getUriage08()));
			rowData.add(numFmtdgt0.format(entity.getKazu08()));
			rowData.add(numFmtdgt0.format(entity.getUriage09()));
			rowData.add(numFmtdgt0.format(entity.getKazu09()));
			rowData.add(numFmtdgt0.format(entity.getUriage10()));
			rowData.add(numFmtdgt0.format(entity.getKazu10()));
			rowData.add(numFmtdgt0.format(entity.getUriage11()));
			rowData.add(numFmtdgt0.format(entity.getKazu11()));
			rowData.add(numFmtdgt0.format(entity.getUriage12()));
			rowData.add(numFmtdgt0.format(entity.getKazu12()));
			rowData.add(numFmtdgt0.format(entity.getUriage13()));
			rowData.add(numFmtdgt0.format(entity.getKazu13()));
			rowData.add(numFmtdgt0.format(entity.getUriage14()));
			rowData.add(numFmtdgt0.format(entity.getKazu14()));
			rowData.add(numFmtdgt0.format(entity.getUriage15()));
			rowData.add(numFmtdgt0.format(entity.getKazu15()));
			rowData.add(numFmtdgt0.format(entity.getUriage16()));
			rowData.add(numFmtdgt0.format(entity.getKazu16()));
			rowData.add(numFmtdgt0.format(entity.getUriage17()));
			rowData.add(numFmtdgt0.format(entity.getKazu17()));
			rowData.add(numFmtdgt0.format(entity.getUriage18()));
			rowData.add(numFmtdgt0.format(entity.getKazu18()));
			rowData.add(numFmtdgt0.format(entity.getUriage19()));
			rowData.add(numFmtdgt0.format(entity.getKazu19()));
			rowData.add(numFmtdgt0.format(entity.getUriage20()));
			rowData.add(numFmtdgt0.format(entity.getKazu20()));
			rowData.add(numFmtdgt0.format(entity.getUriage21()));
			rowData.add(numFmtdgt0.format(entity.getKazu21()));
			rowData.add(numFmtdgt0.format(entity.getUriage22()));
			rowData.add(numFmtdgt0.format(entity.getKazu22()));
			rowData.add(numFmtdgt0.format(entity.getUriage23()));
			rowData.add(numFmtdgt0.format(entity.getKazu23()));
			//その他
			rowData.add(numFmtdgt0.format(entity.getUriageOther()));
			rowData.add(numFmtdgt0.format(entity.getKazuOther()));
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
            throw new MissingConfigException("日別推移CSVダウンロード対象の自画面リクエストDTO");
        }
        	
        	

	}
	/**
	 * @return jikanBetuRefBetuSearchSuiiNipoLogic を戻します。
	 */
	public SearchSuiiNipoLogic getJikanBetuRefSearchSuiiNipoLogic() {
		return jikanBetuRefBetuSearchSuiiNipoLogic;
	}
	/**
	 * @param logic を クラス変数jikanBetuRefBetuSearchSuiiNipoLogicへ設定します。
	 */
	public void setJikanBetuRefSearchSuiiNipoLogic(SearchSuiiNipoLogic logic) {
		this.jikanBetuRefBetuSearchSuiiNipoLogic = logic;
	}
}
