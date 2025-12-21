/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.util;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dto.RequestDto;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dto.SearchedDto;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.entity.TrnPayDetaileStatement;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.logic.SearchLogic;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * テナント入金明細PDF登録 共通定数処理クラス
 * 
 * 作成日:2009/06/19
 * @author xkinu
 *
 */
public class TenantPayPdfRegistUtil {
    /** 画面ID */
	public static final String SCREEN_ID = "BBS034";
    /** VIEWID：初期画面 */
    public static final String VIEW_ID_CONDITION= SCREEN_ID+"V01";
    /** VIEWID：編集画面 */
    public static final String VIEW_ID_EDIT= SCREEN_ID+"V02";
    /** VIEWID：確認画面 */
    public static final String VIEW_ID_CONFIRM= SCREEN_ID+"V03";
    /** ACTIONID：照会画面 */
    public static final String ACTION_ID= SCREEN_ID+"A";
    
    /** LOGIC_ID：条件画面情報ロジック */
    public static final String LOGIC_ID_CONDITION= SCREEN_ID+"L01";
    /** LOGIC_ID：検索ロジック */
    public static final String LOGIC_ID_SEARCH= SCREEN_ID+"L02";
    /** LOGIC_ID：ダウンロードロジック */
    public static final String LOGIC_ID_DOWNLOAD = SCREEN_ID+"L03";
    /** LOGIC_ID：アップロードロジック */
    public static final String LOGIC_ID_UPLOAD= SCREEN_ID+"L04";
    /** LOGIC_ID：登録内容チェックロジック */
    public static final String LOGIC_ID_CHECK= SCREEN_ID+"L05";
    /** LOGIC_ID：登録ロジック */
    public static final String LOGIC_ID_REGIST= SCREEN_ID+"L06";
    
    /** 登録タイプ：新規 */
    public static final int REGIST_TYPE_INSERT = 1;
    /** 登録タイプ：更新 */
    public static final int REGIST_TYPE_UPDATE = 2;
    /** 登録タイプ：削除 */
    public static final int REGIST_TYPE_DELETE = 9;
    
    public static final int FILE_NAME_MAX_LENGTH = 60;
    /** DateFormatter */
    private static DateFormatter formatter = new DateFormatter();
    
    private TenantPayPdfRegistUtil() {
    	super();
    }
    /**
     * 年月リストを取得する
     * 
     * システム日付の翌月・当月含めて過去14ヶ月分のList[[対象年月]]を作成します。
     * @param appDate  システム日付
     * @param cnts   システム日付の翌月含めて過去？ヶ月
     * @return
     */
    public static List creatListNengetu(String sysDate, int cnts) {
        List list = new ArrayList();
        String startNengetu = sysDate.substring(0,6);
        try {
        	startNengetu =  DateManager.getNextMonth(startNengetu,1);
        }
        catch(Exception ex) {
            throw new FtlSystemException("年月リストを取得"
                    , "指定年月["+startNengetu+"]から翌月を計算する際のDateManager.getNextMonthメソッド処理で例外が発生しました。"
                    , ex);
        }
        for (int index=0; index<cnts; index++) {
            try {
           		String nengetu = DateManager.getPrevMonth(startNengetu, index);
	            String name = formatter.format(nengetu, DateFormatter.PATTERN_MONTH_SLASH, DateFormatter.DATE_TYPE_YM);
	            list.add(index, createSelectItem(nengetu, name));
	        }catch (Exception ex) {
	            throw new FtlSystemException("年月リストを取得"
	                    , "指定開始日["+startNengetu+"]から["+index+"]を計算する際のDateManager.getPrevMonthメソッド処理で例外が発生しました。"
	                    , ex);
	        }
        }
        return list;
    }
    /**
     * エンティティ生成処理
     * @param code
     * @param name
     * @return
     */
    private static SelectItem createSelectItem(String code, String name) {
        SelectItem entity = new SelectItem();
        entity.setValue(code);
        entity.setLabel(name);
        return entity;
    }
	/**
	 * 検索処理
	 * 
	 * @param logic
	 * @param sessionDto
	 * @param searchedDto
	 */
    public static void search(SearchLogic logic, SearchedDto searchedDto) {
		try {
			//１．ロジック【検索・結果取得】を実行し、戻り値のList[[検索結果]]を取得します。
			searchedDto.setSearched(true);
			List listSearch = (List)logic.execute(searchedDto);
			//２．DTO【自画面Session】．List[[検索結果]]へ処理１の戻り値のList[[検索結果]]を設定する。					
			searchedDto.getSessionDto().setListSearchData(listSearch);
		    //３．DTO【自画面Session】へ処検索済み条件値を設定する。					
			searchedDto.settingSearchedData();
		}
		//５．NoResultExceptionをキャッチし、下記の処理を行います。
		catch (NoResultException appEx) {
			//1．DTO【自画面Session】．List[[検索結果]]へ処理１の戻り値のList[[検索結果]]を設定する。					
			searchedDto.getSessionDto().setListSearchData(null);
		    //2．DTO【自画面Session】へ処検索済み条件値を設定する。					
			searchedDto.removeSearchedData();
			//3.NoResultExceptionをthrowします。
			throw appEx;
		}
	}
	/**
	 * 表示検索データ設定処理
	 * 
	 * @param logic
	 * @param requestDto
	 * @param viewDto
	 */
	public static void setView(SearchLogic logic, RequestDto requestDto, SearchedDto searchedDto) {
        //１.DTO【自画面Request】へプルダウンリストを設定します。
        requestDto.setPullDownData();
		searchedDto.setWindowId(requestDto.getWindowId());
		searchedDto.setViewId(requestDto.getViewId());
		if(searchedDto.isSearched()) {
			searchedDto.gettingSearchData();
		}
	}
	/**
	 * 指定エンティティのクローン処理
	 * 
	 * @param eOriginal
	 * @return
	 */
	public static TrnPayDetaileStatement cloneEntity(TrnPayDetaileStatement eOriginal) {
		TrnPayDetaileStatement clone = new TrnPayDetaileStatement();
		clone.setCompanyCd(eOriginal.getCompanyCd());
		clone.setUrikakeCd(eOriginal.getUrikakeCd());
		clone.setUrikakeName(eOriginal.getUrikakeName());
		clone.setKeisanYm(eOriginal.getKeisanYm());
		clone.setKeiCategory(eOriginal.getKeiCategory());
		clone.setKeiShu(eOriginal.getKeiShu());
		clone.setSeq(eOriginal.getSeq());
		clone.setHakkoDt(eOriginal.getHakkoDt());
		clone.setFileName(eOriginal.getFileName());
		clone.setFirstUser(eOriginal.getFirstUser());
		clone.setFirstPgm(eOriginal.getFirstPgm());
		clone.setFirstTmsp(eOriginal.getFirstTmsp());
		clone.setLastUser(eOriginal.getLastUser());
		clone.setLastPgm(eOriginal.getLastPgm());
		clone.setLastTmsp(eOriginal.getLastTmsp());
		return clone;
	}
	/**
	 * 登録タイプ名称取得処理
	 */
	public static String getRegistTypeName(int type) {
		if(type == REGIST_TYPE_INSERT) {
			return "新規";
		}
		else if(type == REGIST_TYPE_UPDATE) {
			return "更新";
		}
		else if(type == REGIST_TYPE_DELETE) {
			return "削除";
		}
		return "";
	}

}
