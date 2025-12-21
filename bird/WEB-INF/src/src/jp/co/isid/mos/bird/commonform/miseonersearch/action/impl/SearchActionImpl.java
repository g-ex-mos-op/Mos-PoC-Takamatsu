/**
 * 
 */
package jp.co.isid.mos.bird.commonform.miseonersearch.action.impl;

import jp.co.isid.mos.bird.common.dto.PortalSearchInfoDto;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.commonform.miseonersearch.action.SearchAction;
import jp.co.isid.mos.bird.commonform.miseonersearch.dto.RequestDto;
import jp.co.isid.mos.bird.commonform.miseonersearch.dto.SessionDto;
import jp.co.isid.mos.bird.commonform.miseonersearch.entity.UISearchInfo;
import jp.co.isid.mos.bird.commonform.miseonersearch.logic.SearchLogic;
import jp.co.isid.mos.bird.commonform.miseonersearch.util.MiseOnerSearchUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 店・オーナー検索画面アクション
 * （BIRD共通　各画面呼び出し専用）
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
public class SearchActionImpl implements SearchAction {
    /** アクションID：初期化処理 */
    public static final String initialize_ACTION_ID 
    	= MiseOnerSearchUtil.SCREEN_ID+"A01";
    /** アクションID：検索処理 */
    public static final String search_ACTION_ID 
		= MiseOnerSearchUtil.SCREEN_ID+"A02";
    /** アクションID：50音検索処理 */
    public static final String callMisePortal_ACTION_ID 
		= MiseOnerSearchUtil.SCREEN_ID+"A03";
    /** アクションID:選択処理 */
    public static final String callOnerPortal_ACTION_ID 
		= MiseOnerSearchUtil.SCREEN_ID+"A04";
    
    /** BIRDユーザー情報 */
    private BirdUserInfo birdUserInfo;
    /** BIRD日付情報 */
    private BirdDateInfo birdDateInfo;   
    
    /** LOGIC【検索処理と検索情報の取得】*/
    private SearchLogic miseOnerSearchLogic;
    
    /** BIRD共通DTO【画面間Request】*/
    private PortalSearchInfoDto portalSearchInfoDto;
    /** DTO【自画面Session】*/
    private SessionDto miseOnerSearchSesDto;
    /** DTO【自画面Request】*/
    private RequestDto miseOnerSearchReqDto;
    
	/**
	 * 初期化処理
	 * 
	 * @see jp.co.isid.mos.bird.commonform.miseonersearch.action.SearchAction#initialize()
	 */
	public String initialize() {
		//１．DTO【リクエスト元情報保持DTO】.アクション種類が”初期化”の場合、
		//　　別画面からメニュー選択画面が呼び出されたと判断し、下記の処理を行います。
		if(CommonUtil.ACTION_KIND_INIT == getPortalSearchInfoDto().getActionKind()) {
            //１.DTO【リクエスト元情報保持DTO】.アクション種類へ”稼動中”を設定します。
			getPortalSearchInfoDto().setActionKind(CommonUtil.ACTION_KIND_EXEC);
        	//２.DTO【自画面Session】.クリア処理を行います。(以前の条件項目が残るのを防止するため)
            getMiseOnerSearchReqDto().clear();
            //３．BIRD情報をDTO【自画面Session】へ設定します。
            getMiseOnerSearchSesDto().setBirdDateInfo(getBirdDateInfo());
            getMiseOnerSearchSesDto().setBirdUserInfo(getBirdUserInfo());
            //４.DTO【自画面Session】.WindowID作成処理を行い、複数ウィンドウ機能で新規WindowID設定します。
            int windowId = getMiseOnerSearchSesDto().createWindowId();
            //５.DTO【自画面Request】.WindowIDへ処理3の新規WindowIDを設定します。
            getMiseOnerSearchReqDto().setWindowId(windowId);
            //６.DTO【自画面Session】.リクエスト元(遷移元)VIEW_IDへDTO【自画面Request】.リクエスト元(遷移元)VIEW_IDを設定します。
            getMiseOnerSearchSesDto().setRequesterViewId(windowId, getPortalSearchInfoDto().getRequesterViewId());
            //７.DTO【自画面Session】.リクエスト元(遷移元)WindowIdへDTO【自画面Request】.リクエスト元(遷移元)WindowIdを設定します。
            getMiseOnerSearchSesDto().setRequesterWindowId(windowId, getPortalSearchInfoDto().getRequesterWindowId());
            getMiseOnerSearchReqDto().setSearchWord(getPortalSearchInfoDto().getSearchWord());
            //８.ロジック【検索情報の取得】を実行する。戻り値のMap[[条件項目]]を取得します。
            getMiseOnerSearchReqDto().setListSearchData(getMiseOnerSearchLogic().execute(getMiseOnerSearchReqDto()));
    		//９．DTO【自画面Session】へ処検索済み条件値を設定する。					
            getMiseOnerSearchSesDto().setSearchedData(getMiseOnerSearchReqDto());
		}
		//２．nullをリターンします。
		return null;
	}

	/**
	 * 検索
	 * 
	 * @see jp.co.isid.mos.bird.commonform.miseonersearch.action.SearchAction#search()
	 */
	public String search() {
		//１．ロジック【キャンペーン日報情報の取得】を実行する。戻り値のMap[[条件項目]]を取得します。
		getMiseOnerSearchReqDto().setListSearchData(getMiseOnerSearchLogic().execute(getMiseOnerSearchReqDto()));
		//２．DTO【自画面Session】へ処検索済み条件値を設定する。					
		getMiseOnerSearchSesDto().setSearchedData(getMiseOnerSearchReqDto());
		//３．nullをリターンします。
		return null;
	}
	/**
	 * 個店ポータル呼び出し
	 * 
	 * @see jp.co.isid.mos.bird.commonform.miseonersearch.action.SearchAction#callMisePortal()
	 */
	public String callMisePortal() {
    	//１.BIRD内遷移フラグをリセット
        getCommonCodeDto().setUseCommonDto(true);
        UISearchInfo callInfo = (UISearchInfo)getMiseOnerSearchSesDto().getListSearchData(getMiseOnerSearchReqDto().getWindowId()).get(getMiseOnerSearchReqDto().getSelectedIndex());
        //２．共通DTO【店舗選択】初期化フラグへtrueを設定します。
        getCommonCodeDto().setCompanyCd(callInfo.getCompanyCd());
        getCommonCodeDto().setMiseCd(callInfo.getMiseCd());
        //３．選択画面遷移VIEWIDをリターンします。
        return CommonUtil.VIEW_ID_MISEREF;
	}
	/**
	 * オーナーポータル呼び出し
	 * 
	 * @see jp.co.isid.mos.bird.commonform.miseonersearch.action.SearchAction#callOnerPortal()
	 */
	public String callOnerPortal() {
    	//１.BIRD内遷移フラグをリセット
        getCommonCodeDto().setUseCommonDto(true);
        UISearchInfo callInfo = (UISearchInfo)getMiseOnerSearchSesDto().getListSearchData(getMiseOnerSearchReqDto().getWindowId()).get(getMiseOnerSearchReqDto().getSelectedIndex());
        //２．共通DTO【店舗選択】初期化フラグへtrueを設定します。
        getCommonCodeDto().setCompanyCd(callInfo.getCompanyCd());
        getCommonCodeDto().setOnerCd(callInfo.getOnerCd());
        //３．選択画面遷移VIEWIDをリターンします。
        return CommonUtil.VIEW_ID_ONERREF;
	}
	/**
	 * BIRD間の遷移時使用情報保持DTO取得処理
	 * 
	 * @return
	 */
    public CommonCodeDto getCommonCodeDto() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        CommonCodeDto commonCodeDto = (CommonCodeDto) container.getComponent(CommonCodeDto.class);
        if (commonCodeDto == null) {
            commonCodeDto = new CommonCodeDto();
        }
        return commonCodeDto;
    }

	/**
	 * @return miseOnerSearchLogic を戻します。
	 */
	public SearchLogic getMiseOnerSearchLogic() {
		return miseOnerSearchLogic;
	}

	/**
	 * @param miseOnerSearchLogic を クラス変数miseOnerSearchLogicへ設定します。
	 */
	public void setMiseOnerSearchLogic(SearchLogic miseOnerSearchLogic) {
		this.miseOnerSearchLogic = miseOnerSearchLogic;
	}

	/**
	 * @return miseOnerSearchReqDto を戻します。
	 */
	public RequestDto getMiseOnerSearchReqDto() {
		return miseOnerSearchReqDto;
	}

	/**
	 * @param miseOnerSearchReqDto を クラス変数miseOnerSearchReqDtoへ設定します。
	 */
	public void setMiseOnerSearchReqDto(RequestDto miseOnerSearchReqDto) {
		this.miseOnerSearchReqDto = miseOnerSearchReqDto;
	}

	/**
	 * @return miseOnerSearchSesDto を戻します。
	 */
	public SessionDto getMiseOnerSearchSesDto() {
		return miseOnerSearchSesDto;
	}

	/**
	 * @param miseOnerSearchSesDto を クラス変数miseOnerSearchSesDtoへ設定します。
	 */
	public void setMiseOnerSearchSesDto(SessionDto miseOnerSearchSesDto) {
		this.miseOnerSearchSesDto = miseOnerSearchSesDto;
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

}
