/**
 * 
 */
package jp.co.isid.mos.bird.analysis.rankref.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.analysis.rankref.action.ViewAction;
import jp.co.isid.mos.bird.analysis.rankref.dto.RequestDto;
import jp.co.isid.mos.bird.analysis.rankref.dto.SessionDto;
import jp.co.isid.mos.bird.analysis.rankref.logic.ConditionLogic;
import jp.co.isid.mos.bird.analysis.rankref.logic.SearchLogic;
import jp.co.isid.mos.bird.analysis.rankref.util.RankRefUtil;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 【データ分析】売上ランク画面
 * 照会アクション
 * 
 * 作成日:2008/10/21
 * @author xkinu
 *
 */
public class ViewActionImpl extends CsvOutput2ActionImpl implements ViewAction {
    /* アクションID：初期処理(初期画面用) */
    public static final String initialize_ACTION_ID = RankRefUtil.ACTION_ID+"01";
    /* アクションID：検索処理 */
    public static final String search_ACTION_ID = RankRefUtil.ACTION_ID+"02";
    /* アクションID：CSVダウンロード処理 */
    public static final String download_ACTION_ID = RankRefUtil.ACTION_ID+"03";
    /* アクションID：ランク対象変更 */
    public static final String changeTab_ACTION_ID =RankRefUtil.ACTION_ID+"04";
    
    /** BIRDユーザー情報 */
    private BirdUserInfo birdUserInfo;
    /** BIRD日付情報 */
    private BirdDateInfo birdDateInfo;
    /** 共通DTO【プルダウンメニューDTO】*/
    private PullDownMenuDto pullDownMenuDto;
    /** DTO【自画面Session】 */
    private SessionDto rankRefSesDto;
    /** DTO【自画面Request】 */
    private RequestDto rankRefReqDto;
    /** DTO【自画面View】 */
    private RequestDto rankRefViewDto;
    /** LOGIC【条件項目取得】*/
    private ConditionLogic rankRefConditionLogic;
    /** LOGIC【検索結果取得】*/
    private SearchLogic rankRefSearchLogic;
    

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.analysis.rankref.action.ViewAction#initialize()
	 */
	public String initialize() {
        //１．DTO【プルダウンメニュー】.クリアフラグがtrueの場合下記の処理を行います。
        if(getPullDownMenuDto().isClearFlg()){
            //１.DTO【プルダウンメニュー】.クリアフラグをfalseに設定します。
            getPullDownMenuDto().setClearFlg(false);
            //２．BIRD情報をDTO【自画面Session】へ設定します。
            getRankRefSesDto().setBirdDateInfo(getBirdDateInfo());
            getRankRefSesDto().setBirdUserInfo(getBirdUserInfo());
            //３．LOGIC【条件項目値設定】を実行し、初期データ設定処理を行います。
            getRankRefConditionLogic().execute(getRankRefSesDto(), getRankRefReqDto());
            //４．自画面DTOの初期化処理を行います。
            // 1.DTO【自画面Session】.WindowID作成処理を行い、複数ウィンドウ機能で新規WindowID設定します。
            // 2.DTO【自画面Request】.WindowIDへ処理2の新規WindowIDを設定します。
            getRankRefReqDto().initialaze();
        				
        }
        //２．処理１以外の場合下記の処理を行います。
        else {
        	//1.【自画面共通】表示検索データ設定処理
        	setView();
        }
        //３．Nullをリターンします。
        return null;
	}
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.analysis.rankref.action.ViewAction#search()
	 */
	public String search() {
		//１．DTO【自画面Request】.別ウィンドウオープン判断フラグがtrueの場合、下記の処理を行います。
        if(getRankRefReqDto().isNewWindowFlg()) {
            //1.DTO【自画面Session】.ウィンドウID更新処理で新しいWindowIDを生成します。
            int windowId = getRankRefSesDto().createWindowId();
            //2.DTO【自画面Request】.ウィンドウIDへ処理1で生成した新しいWindowIDを設定します。
            getRankRefReqDto().setWindowId(windowId);
            //3.DTO【自画面Request】.別ウィンドウオープン判断フラグにfalseを設定します。
            getRankRefReqDto().setNewWindowFlg(false);
    	}
        //検索処理
		search(getRankRefReqDto());
		//２．Nullをリターンします。
		return null;
	}
	/**
	 * 表示検索データ設定処理
	 * 
	 * @param logic 検索ロジックオブジェクト
	 * @param sessionDto DTO【自画面セッション】
	 * @param requestDto DTO【自画面リクエスト】
	 * @param viewDto DTO【自画面照会情報保持】
	 */
	private void setView() {
		int windowid = getRankRefReqDto().getWindowId();
        //１.DTO【自画面Request】へプルダウンリストを設定します。
        getRankRefReqDto().setPullDownLists();
        //２．セッションに検索データが存在しない場合、検索処理の実行を行います。
		if(getRankRefSesDto().isMustSearch(windowid)) {
			search(getRankRefReqDto());
		}
        //３．表示検索データが存在する場合、下記の処理を行う。
		if(getRankRefSesDto().isExistSearchedData(windowid)) {
			//DTO【自画面照会情報保持】へDTO【自画面セッション用】.検索済み条件値格納リクエストDTOを設定します。
			getRankRefViewDto().getSearchedDataDto(windowid);
		}
	}
	/**
	 * ランク対象タブ切り替え処理
	 */
	public String changeTab() {
        //１．DTO【自画面Request】.データ区分へ『』の値を設定します。
		search(getRankRefViewDto());
		//２．Nullをリターンします。
		return null;
	}
	/**
	 * 検索処理
	 * 
	 * @param logic
	 * @param sessionDto
	 * @param requestDto
	 */
	private void search(RequestDto requestDto) {
		List listSearchData = null;
		try {
			//２．ロジック【情報の取得】を実行します。戻り値のList[[検索結果]]を取得します。
			listSearchData = (List)getRankRefSearchLogic().execute(requestDto);
		}
		//３．処理１の戻り値のList[[検索結果]]が０件の場合、下記の処理を行う。
		catch (ApplicationException appEx) {
		    //検索結果該当データなし対象情報を全てNullを設定します。
			requestDto.setNoResultSearchedData();
			throw appEx;
		}
	    //４．DTO【自画面Session】へ処検索済み条件値を設定します。
		requestDto.setSearchedData(listSearchData);
	}
	/**
	 * @return birdDateInfo を戻します。
	 */
	public BirdDateInfo getBirdDateInfo() {
		return birdDateInfo;
	}

	/**
	 * @param birdDateInfo を クラス変数birdDateInfoへ設定します。
	 */
	public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
		this.birdDateInfo = birdDateInfo;
	}

	/**
	 * @return birdUserInfo を戻します。
	 */
	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}

	/**
	 * @param birdUserInfo を クラス変数birdUserInfoへ設定します。
	 */
	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}

	/**
	 * @return pullDownMenuDto を戻します。
	 */
	public PullDownMenuDto getPullDownMenuDto() {
		return pullDownMenuDto;
	}

	/**
	 * @param pullDownMenuDto を クラス変数pullDownMenuDtoへ設定します。
	 */
	public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
		this.pullDownMenuDto = pullDownMenuDto;
	}
	/**
	 * @return rankRefConditionLogic を戻します。
	 */
	public ConditionLogic getRankRefConditionLogic() {
		return rankRefConditionLogic;
	}
	/**
	 * @param logic を クラス変数rankRefConditionLogicへ設定します。
	 */
	public void setRankRefConditionLogic(ConditionLogic logic) {
		this.rankRefConditionLogic = logic;
	}
	/**
	 * @return rankRefReqDto を戻します。
	 */
	public RequestDto getRankRefReqDto() {
		return rankRefReqDto;
	}
	/**
	 * @param rankRefReqDto を クラス変数rankRefReqDtoへ設定します。
	 */
	public void setRankRefReqDto(RequestDto rankRefReqDto) {
		this.rankRefReqDto = rankRefReqDto;
	}
	/**
	 * @return rankRefSesDto を戻します。
	 */
	public SessionDto getRankRefSesDto() {
		return rankRefSesDto;
	}
	/**
	 * @param rankRefSesDto を クラス変数rankRefSesDtoへ設定します。
	 */
	public void setRankRefSesDto(SessionDto rankRefSesDto) {
		this.rankRefSesDto = rankRefSesDto;
	}
	/**
	 * @return rankRefViewDto を戻します。
	 */
	public RequestDto getRankRefViewDto() {
		return rankRefViewDto;
	}
	/**
	 * @param rankRefViewDto を クラス変数rankRefViewDtoへ設定します。
	 */
	public void setRankRefViewDto(RequestDto rankRefViewDto) {
		this.rankRefViewDto = rankRefViewDto;
	}
	/**
	 * @return rankRefSearchLogic を戻します。
	 */
	public SearchLogic getRankRefSearchLogic() {
		return rankRefSearchLogic;
	}
	/**
	 * @param rankRefSearchLogic を クラス変数rankRefSearchLogicへ設定します。
	 */
	public void setRankRefSearchLogic(SearchLogic rankRefSearchLogic) {
		this.rankRefSearchLogic = rankRefSearchLogic;
	}
}
