/**
 * 
 */
package jp.co.isid.mos.bird.commonform.screensearch.action.impl;

import jp.co.isid.mos.bird.common.action.impl.OutLinkActionImpl;
import jp.co.isid.mos.bird.common.dto.PortalSearchInfoDto;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.commonform.screensearch.action.SearchAction;
import jp.co.isid.mos.bird.commonform.screensearch.dto.RequestDto;
import jp.co.isid.mos.bird.commonform.screensearch.dto.SessionDto;
import jp.co.isid.mos.bird.commonform.screensearch.logic.SearchLogic;
import jp.co.isid.mos.bird.commonform.screensearch.util.ScreenSearchUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * 画面メニュー検索画面アクション
 * 
 * 使用する場合：下記のコンポーネントを呼び出し元画面のdiconに設定して下さい。
 * 設定：<component name  ="portalSearchInfoDto" 
 *            class       ="jp.co.isid.mos.bird.common.dto.PortalSearchInfoDto" 
 *            autoBinding ="none" 
 *            instance    ="request"/>
 * 
 * 作成日:2008/11/17
 * @author xkinu
 *
 */
public class SearchActionImpl extends OutLinkActionImpl implements SearchAction {
    /** アクションID：初期化処理 */
    public static final String initialize_ACTION_ID 
    	= ScreenSearchUtil.SCREEN_ID+"A01";
    /** アクションID：検索処理 */
    public static final String search_ACTION_ID 
		= ScreenSearchUtil.SCREEN_ID+"A02";
    public static String linkUp_ACTION_ID
    	= ScreenSearchUtil.SCREEN_ID+"A03";
    
    /** BIRDユーザー情報 */
    private BirdUserInfo birdUserInfo;
    /** BIRD日付情報 */
    private BirdDateInfo birdDateInfo;   

    /** LOGIC【検索処理と検索情報の取得】*/
    private SearchLogic screenSearchLogic;
    
    /** BIRD共通DTO【画面間Request】*/
    private PortalSearchInfoDto portalSearchInfoDto;
    /** DTO【自画面Session】*/
    private SessionDto screenSearchSesDto;
    /** DTO【自画面Request】*/
    private RequestDto screenSearchReqDto;
    
	/**
	 * 初期化処理
	 * 
	 * @see jp.co.isid.mos.bird.commonform.screensearch.action.SearchAction#initialize()
	 */
	public String initialize() {
		//１．DTO【リクエスト元情報保持DTO】.アクション種類が”初期化”の場合、
		//　　別画面からメニュー選択画面が呼び出されたと判断し、下記の処理を行います。
		if(CommonUtil.ACTION_KIND_INIT == getPortalSearchInfoDto().getActionKind()) {
            //１.DTO【リクエスト元情報保持DTO】.アクション種類へ”稼動中”を設定します。
			getPortalSearchInfoDto().setActionKind(CommonUtil.ACTION_KIND_EXEC);
        	//２.DTO【自画面Session】.クリア処理を行います。(以前の条件項目が残るのを防止するため)
            getScreenSearchReqDto().clear();
            //３．BIRD情報をDTO【自画面Session】へ設定します。
            getScreenSearchSesDto().setBirdDateInfo(getBirdDateInfo());
            getScreenSearchSesDto().setBirdUserInfo(getBirdUserInfo());
            //４.DTO【自画面Session】.WindowID作成処理を行い、複数ウィンドウ機能で新規WindowID設定します。
            int windowId = getScreenSearchSesDto().createWindowId();
            //５.DTO【自画面Request】.WindowIDへ処理3の新規WindowIDを設定します。
            getScreenSearchReqDto().setWindowId(windowId);
            //６.DTO【自画面Session】.リクエスト元(遷移元)VIEW_IDへDTO【自画面Request】.リクエスト元(遷移元)VIEW_IDを設定します。
            getScreenSearchSesDto().setRequesterViewId(windowId, getPortalSearchInfoDto().getRequesterViewId());
            //７.DTO【自画面Session】.リクエスト元(遷移元)WindowIdへDTO【自画面Request】.リクエスト元(遷移元)WindowIdを設定します。
            getScreenSearchSesDto().setRequesterWindowId(windowId, getPortalSearchInfoDto().getRequesterWindowId());
            getScreenSearchReqDto().setSearchWord(getPortalSearchInfoDto().getSearchWord());
            //８.ロジック【検索情報の取得】を実行する。戻り値のMap[[条件項目]]を取得します。
    		getScreenSearchReqDto().setListSearchData(getScreenSearchLogic().execute(getScreenSearchReqDto()));
    		//９．DTO【自画面Session】へ処検索済み条件値を設定する。					
    		getScreenSearchSesDto().setSearchedData(getScreenSearchReqDto());
		}
		//２．nullをリターンします。
		return null;
	}

	/**
	 * 検索
	 * 
	 * @see jp.co.isid.mos.bird.commonform.screensearch.action.SearchAction#search()
	 */
	public String search() {
		//１．ロジック【検索情報の取得】を実行する。戻り値のMap[[条件項目]]を取得します。
		getScreenSearchReqDto().setListSearchData(getScreenSearchLogic().execute(getScreenSearchReqDto()));
		//２．DTO【自画面Session】へ処検索済み条件値を設定する。					
		getScreenSearchSesDto().setSearchedData(getScreenSearchReqDto());
		//３．nullをリターンします。
		return null;
	}
	/**
	 * @return screenSearchLogic を戻します。
	 */
	public SearchLogic getScreenSearchLogic() {
		return screenSearchLogic;
	}

	/**
	 * @param screenSearchLogic を クラス変数screenSearchLogicへ設定します。
	 */
	public void setScreenSearchLogic(SearchLogic screenSearchLogic) {
		this.screenSearchLogic = screenSearchLogic;
	}

	/**
	 * @return portalSearchInfoDto を戻します。
	 */
	public PortalSearchInfoDto getPortalSearchInfoDto() {
		return portalSearchInfoDto;
	}

	/**
	 * @param portalSearchInfoDto を クラス変数portalSearchInfoDtoへ設定します。
	 */
	public void setPortalSearchInfoDto(PortalSearchInfoDto portalSearchInfoDto) {
		this.portalSearchInfoDto = portalSearchInfoDto;
	}

	/**
	 * @return screenSearchReqDto を戻します。
	 */
	public RequestDto getScreenSearchReqDto() {
		return screenSearchReqDto;
	}

	/**
	 * @param screenSearchReqDto を クラス変数screenSearchReqDtoへ設定します。
	 */
	public void setScreenSearchReqDto(RequestDto screenSearchReqDto) {
		this.screenSearchReqDto = screenSearchReqDto;
	}

	/**
	 * @return screenSearchSesDto を戻します。
	 */
	public SessionDto getScreenSearchSesDto() {
		return screenSearchSesDto;
	}

	/**
	 * @param screenSearchSesDto を クラス変数screenSearchSesDtoへ設定します。
	 */
	public void setScreenSearchSesDto(SessionDto screenSearchSesDto) {
		this.screenSearchSesDto = screenSearchSesDto;
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

}
