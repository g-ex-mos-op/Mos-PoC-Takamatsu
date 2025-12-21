package jp.co.isid.mos.bird.bizreport.campkako.util;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.campkako.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizreport.campkako.logic.SearchLogic;
import jp.co.isid.mos.bird.bizreport.campkako.logic.SuiiSearchLogic;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestNipoDto;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestSuiiDto;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.SessionNipoDto;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.SessionSuiiDto;
import jp.co.isid.mos.bird.bizreport.common.camp.entity.CodCampMenu;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;

/**
 * キャンペーン過去履歴共通処理クラス
 * 
 * @author xnkusama
 *
 */
public class CampKakoUtil {
	/** 画面ID */
	public static final String SCREEN_ID = "BBR014";
    /** VIEWID：支部一覧画面 */
    public static final String VIEW_ID_SIBU = SCREEN_ID + "V01";
    /** VIEWID：店舗一覧画面 */
    public static final String VIEW_ID_MISE = SCREEN_ID + "V02";
    /** VIEWID：推移画面 */
    public static final String VIEW_ID_SUII = SCREEN_ID + "V03";
    /** VIEWID：店検索 */
    public static final String VIEW_ID_MISESEARCH = "BCO008V01";
    /** VIEWID：店舗一覧画面 */
    public static final String CssClassHiritu = "hiritu";
    
    /** キャンペーン一覧のソート項目：表示期間開始日 */
    public static final int CAMP_LIST_SORT_DISP_FROM = 2;
    
    private static final BigDecimal dec100 = new BigDecimal("100");
	private CampKakoUtil() {
		super();
	}
	/**
	 * 初期化処理
	 * 
	 * @param sessionDto
	 * @param requestDto
	 * @param birdDateInfo
	 * @param birdUserInfo
	 * @param conditionLogic
	 */
	public static void initialize(SessionNipoDto sessionDto, RequestNipoDto requestDto
			, BirdDateInfo birdDateInfo, BirdUserInfo birdUserInfo, ConditionLogic conditionLogic) {
        //2．複数WindowID設定
        int windowId = sessionDto.updateWindowid();
        requestDto.setWindowId(windowId);
        //3.BIRD情報をDTO【自画面Session】へ設定します。
        sessionDto.setBirdDateInfo(birdDateInfo);
        sessionDto.setBirdUserInfo(birdUserInfo);
        //4.DTO【自画面Request】へDTO【自画面Session】を設定します。
        requestDto.setSelfSessionDto(sessionDto);
        try {
            //5.LOGIC【条件項目値設定】を実行し、初期データ設定処理を行います。
            conditionLogic.execute(requestDto);
        }
        catch (NotExistException e) {
            //何もしない。
        }
        //6.DTO【自画面Request】へ初期値を設定します。
        requestDto.setInitialazeDataKako(sessionDto);
		
	}
	/**
	 * 検索処理
	 * 
	 * @param logic
	 * @param sessionDto
	 * @param requestDto
	 */
	public static void search(SearchLogic logic, SessionNipoDto sessionDto, RequestNipoDto requestDto) {
        //１.DTO【自画面Request】へDTO【自画面セッション用】を設定します。
		requestDto.setSelfSessionDto(sessionDto);
		//２．検索処理の実行を行います。
		search(logic, requestDto);
	}
    /**
     * 検索処理
     * 
     * @param logic
     * @param sessionDto
     * @param requestDto
     */
    public static void searchSuii(SuiiSearchLogic logic, SessionSuiiDto sessionDto, RequestSuiiDto requestDto) {
        //１.DTO【自画面Request】へDTO【自画面セッション用】を設定します。
        requestDto.setSelfSessionDto(sessionDto);
        //２．検索処理の実行を行います。
        //１．ロジック【キャンペーン日報情報の取得】を実行する。戻り値のMap[[条件項目]]を取得します。
        List listSearch = (List)logic.execute(sessionDto, requestDto);
        //２．処理１の戻り値のList[[検索結果]]が０件の場合、下記の処理を行う。
        int searchCnt = 0;
        if(listSearch != null) {
            searchCnt = listSearch.size();
        }
        if(searchCnt <= 1) {
            //３．DTO【自画面Session】の該当データ情報設定処理を行います。
            requestDto.getSelfSessionDto().setNoResultSearchedData(requestDto.getWindowId());
            throw new NoResultException();
        }
        //３．DTO【自画面Session】．List[[検索結果]]へ処理１の戻り値のList[[検索結果]]を設定する。                 
        requestDto.getSelfSessionDto().setListSearchData(requestDto.getWindowId(), listSearch);
        //４．DTO【自画面Session】へ処検索済み条件値を設定する。                  
        ((SessionSuiiDto)(requestDto.getSelfSessionDto())).setSearchedData(requestDto);
    }
	/**
	 * 表示検索データ設定処理
	 * 
	 * @param logic
	 * @param requestDto
	 * @param viewDto
	 */
	public static void setView(SearchLogic logic, SessionNipoDto sessionDto, RequestNipoDto requestDto, RequestNipoDto viewDto) {
        //１.DTO【自画面Request】へプルダウンリストを設定します。
        requestDto.setPullDownData(sessionDto);
		if(requestDto.isMustSearch()) {
			//セッションに検索データが存在しない場合、検索処理の実行を行います。
			search(logic, requestDto);
		}
        //２．表示検索データが存在する場合、下記の処理を行う。
		if(requestDto.isExistSearchedData()) {
			//DTO【自画面照会情報保持】へDTO【自画面セッション用】.検索済み条件値格納リクエストDTOを設定します。
			sessionDto.getSearchedDataDto(requestDto.getWindowId(), viewDto);
            viewDto.setViewId(requestDto.getViewId());
		}
	}
    /**
     * 表示検索データ設定処理
     * 
     * @param logic
     * @param requestDto
     * @param viewDto
     */
    public static void setViewSuii(SuiiSearchLogic logic, SessionSuiiDto sessionDto, RequestSuiiDto requestDto, RequestSuiiDto viewDto) {
        //１.DTO【自画面Request】へプルダウンリストを設定します。
        requestDto.setPullDownData(sessionDto);
        if(requestDto.isMustSearch() && sessionDto.isSearchedFlg(requestDto.getWindowId())) {
            //セッションに検索データが存在しない場合、検索処理の実行を行います。
            searchSuii(logic, sessionDto, requestDto);
        }
        //２．表示検索データが存在する場合、下記の処理を行う。
        if(requestDto.isExistSearchedData()) {
            //DTO【自画面照会情報保持】へDTO【自画面セッション用】.検索済み条件値格納リクエストDTOを設定します。
            sessionDto.getSearchedDataDto(requestDto.getWindowId(), viewDto);
            viewDto.setViewId(requestDto.getViewId());
        }
    }
	/**
	 * 比率スタイルクラス取得処理
	 * 
	 * @return
	 */
	public static String getCssClassHiritu(String rowType, BigDecimal hiritu) {
    	if(dec100.compareTo(hiritu)>-1) {
    		return CssClassHiritu+"_red";
    	}
		return CssClassHiritu;
	}
	/**
	 * メニューリストから全てのメニュー名称羅列処理
	 * 
	 * @param listCodCampMenu
	 * @return
	 */
	public static String enumerateMenuList(List listCodCampMenu) {
		StringBuffer enumerateMenu = new StringBuffer();
		for(Iterator ite = listCodCampMenu.iterator(); ite.hasNext();) {
			CodCampMenu entity = (CodCampMenu) ite.next();
            if (enumerateMenu.length() != 0) {
                enumerateMenu.append("、");
            }
			enumerateMenu.append(entity.getName());
		}
		return enumerateMenu.toString();
	}
	/**
	 * 検索処理
	 * 
	 * @param logic
	 * @param sessionDto
	 * @param requestDto
	 */
	private static void search(SearchLogic logic, RequestNipoDto requestDto) {
		//１．ロジック【キャンペーン日報情報の取得】を実行する。戻り値のMap[[条件項目]]を取得します。
		List listSearch = (List)logic.execute(requestDto);
		//２．処理１の戻り値のList[[検索結果]]が０件の場合、下記の処理を行う。
		int searchCnt = 0;
		if(listSearch != null) {
			searchCnt = listSearch.size();
		}
		if(searchCnt <= 1) {
		    //３．DTO【自画面Session】の該当データ情報設定処理を行います。
			requestDto.getSelfSessionDto().setNoResultSearchedData(requestDto.getWindowId());
			throw new NoResultException();
		}
	    //３．DTO【自画面Session】．List[[検索結果]]へ処理１の戻り値のList[[検索結果]]を設定する。					
		requestDto.getSelfSessionDto().setListSearchData(requestDto.getWindowId(), listSearch);
	    //４．DTO【自画面Session】へ処検索済み条件値を設定する。					
		((SessionNipoDto)(requestDto.getSelfSessionDto())).setSearchedData(requestDto);
//		//５．処理１の戻り値のList[[検索結果]]のインデックス0番目の対象期間を
//		//    DTO【自画面Request】.対象期間へ設定します。
//		requestDto.setTaishoKikan(((UITabSearchData)listSearch.get(0)).getKey());
	}
}
