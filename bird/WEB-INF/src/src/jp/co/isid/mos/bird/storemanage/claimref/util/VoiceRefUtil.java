package jp.co.isid.mos.bird.storemanage.claimref.util;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.storemanage.claimref.code.HyojiNaiyo;
import jp.co.isid.mos.bird.storemanage.claimref.dto.RequestDto;
import jp.co.isid.mos.bird.storemanage.claimref.dto.SessionDto;
import jp.co.isid.mos.bird.storemanage.claimref.entity.UIVoiceInfo;
import jp.co.isid.mos.bird.storemanage.claimref.logic.ConditionLogic;
import jp.co.isid.mos.bird.storemanage.claimref.logic.SearchLogic;

/**
 * 個店管理：『お客様の声』
 * 定数クラス
 * 
 * @author xkinu
 *
 */
public class VoiceRefUtil {
	public static final String SCREEN_ID = "BSM016";
	/** 画面ID：一覧(初期)画面 */
	public static final String VIEW_ID_LIST = SCREEN_ID+"V01";
	/** 画面ID：詳細画面 */
	public static final String VIEW_ID_INFO = SCREEN_ID+"V02";
	/**
	 * 対象条件：本部ユーザー用コード配列
	 */
	public static final String[] TAISHOJOKEN_CODES_HONBU = new String []{TaishoJoken.CODE_ALL
		, TaishoJoken.CODE_JIGYOU, TaishoJoken.CODE_SLAREA, TaishoJoken.CODE_SIBU
		, TaishoJoken.CODE_ONER};
    /** DateFormatter */
    private static DateFormatter formatter = new DateFormatter();

	/**
	 * 初期化処理
	 * 
	 * @param sessionDto
	 * @param requestDto
	 * @param birdDateInfo
	 * @param birdUserInfo
	 * @param conditionLogic
	 */
	public static void initialize(ConditionLogic conditionLogic, SessionDto sessionDto, RequestDto requestDto
			, BirdDateInfo birdDateInfo, BirdUserInfo birdUserInfo) {
        //2．複数WindowID設定
        int windowId = windowId = sessionDto.updateWindowid();
        requestDto.setWindowId(windowId);
        //3.BIRD情報をDTO【自画面Session】へ設定します。
        sessionDto.setBirdDateInfo(birdDateInfo);
        sessionDto.setBirdUserInfo(birdUserInfo);
        //4.DTO【自画面Request】へDTO【自画面Session】を設定します。
        requestDto.setSelfSessionDto(sessionDto);
        try {
            //5.LOGIC【条件項目値設定】を実行し、初期データ設定処理を行います。
        	conditionLogic.execute(sessionDto, requestDto);
        }
        catch (NotExistException e) {
        	//何もしない。
        }
        //6.DTO【自画面Request】へ初期値を設定します。
        requestDto.setInitialazeData(sessionDto);
		
	}
	/**
	 * 検索処理
	 * 
	 * @param logic
	 * @param sessionDto
	 * @param requestDto
	 */
	public static void search(SearchLogic logic, SessionDto sessionDto, RequestDto requestDto) {
        //１.DTO【自画面Request】へDTO【自画面セッション用】を設定します。
		requestDto.setSelfSessionDto(sessionDto);
		try {
			if(HyojiNaiyo.CODE_OHOME.equals(requestDto.getHyojiNaiyo())) {
				requestDto.setTaishoJoken(TaishoJoken.CODE_ALL);
				requestDto.setHyojiTaisho("");
			}
			//２．ロジック【キャンペーン日報情報の取得】を実行する。戻り値のMap[[条件項目]]を取得します。
			List listSearch = (List)logic.execute(sessionDto, requestDto);
		    //３．DTO【自画面Session】．List[[検索結果]]へ処理１の戻り値のList[[検索結果]]を設定する。					
			requestDto.getSelfSessionDto().setListSearchData(requestDto.getWindowId(), listSearch);
		    //４．DTO【自画面Session】へ処検索済み条件値を設定する。					
			((SessionDto)(requestDto.getSelfSessionDto())).setSearchedData(requestDto);
		}
		catch(NoResultException noResultEx) {
			//３．処理１の戻り値のList[[検索結果]]が０件の場合、下記の処理を行う。
		    //DTO【自画面Session】の該当データ情報設定処理を行います。
			requestDto.getSelfSessionDto().setNoResultSearchedData(requestDto.getWindowId());
			throw noResultEx;
		}
	}
	/**
	 * 表示検索データ設定処理
	 * 
	 * @param logic
	 * @param requestDto
	 * @param viewDto
	 */
	public static void setView(SearchLogic logic, SessionDto sessionDto, RequestDto requestDto, RequestDto viewDto) {
        //１.DTO【自画面Request】へDTO【自画面セッション用】を設定します。
		requestDto.setSelfSessionDto(sessionDto);
        //１.DTO【自画面Request】へプルダウンリストを設定します。
        requestDto.setPullDownData(sessionDto);
		if(requestDto.isMustSearch()) {
			//セッションに検索データが存在しない場合、検索処理の実行を行います。
			search(logic, sessionDto, requestDto);
		}
        //２．表示検索データが存在する場合、下記の処理を行う。
		if(sessionDto.isExistSearchedData(requestDto.getWindowId())) {
			//DTO【自画面照会情報保持】へDTO【自画面セッション用】.検索済み条件値格納リクエストDTOを設定します。
			sessionDto.getSearchedDataDto(requestDto.getWindowId(), viewDto);
			viewDto.setViewId(requestDto.getViewId());
		}
	}
    /**
     * 年月リストを取得する
     * 
     * @param fromDt 指定開始日
     * @param toDt   指定終了日
     * @return
     */
    public static List creatListNengetu(String sysDate, int cnts) {
        List list = new ArrayList();
        String startNengetu = sysDate.substring(0,6);
        for (int index=0; index<cnts; index++) {
            try {
           		String nengetu = DateManager.getPrevMonth(startNengetu, index);
	            String name = formatter.format(nengetu, DateFormatter.PATTERN_MONTH_SLASH, DateFormatter.DATE_TYPE_YM);
	            list.add(index, createKikanEntity(nengetu, name));
	        }catch (Exception ex) {
	            throw new FtlSystemException("期間指定生成"
	                    , "指定開始日["+startNengetu+"]から["+index+"]を計算する際のDateManagerメソッド処理で例外が発生しました。"
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
    private static SelectItem createKikanEntity(String code, String name) {
        SelectItem entity = new SelectItem();
        entity.setValue(code);
        entity.setLabel(name);
        return entity;
    }
    /**
     * 指定管理番号情報取得処理
     * 
     * @param listSearchData
     * @param kanriNo
     * @return
     */
    public static UIVoiceInfo getInfoData(List listSearchData, String kanriNo) {
    	if(listSearchData == null || CommonUtil.isNull(kanriNo)) {
    		return null;
    	}
    	for(int i=0; i<listSearchData.size(); i++) {
    		UIVoiceInfo entity = (UIVoiceInfo)listSearchData.get(i);
    		if(kanriNo.equals(entity.getClaimNo())) {
    			return entity;
    		}
    	}
    	return null;
    }
}
