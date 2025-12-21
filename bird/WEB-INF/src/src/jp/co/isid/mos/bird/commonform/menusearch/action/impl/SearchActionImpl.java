/**
 * 
 */
package jp.co.isid.mos.bird.commonform.menusearch.action.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.commonform.menusearch.action.SearchAction;
import jp.co.isid.mos.bird.commonform.menusearch.dto.MenuSearchDto;
import jp.co.isid.mos.bird.commonform.menusearch.dto.RequestDto;
import jp.co.isid.mos.bird.commonform.menusearch.dto.SessionDto;
import jp.co.isid.mos.bird.commonform.menusearch.entity.UIMenuInfo;
import jp.co.isid.mos.bird.commonform.menusearch.logic.ConditionLogic;
import jp.co.isid.mos.bird.commonform.menusearch.logic.SearchLogic;
import jp.co.isid.mos.bird.commonform.menusearch.util.MenuSearchUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.NotSelectedException;

/**
 * メニュー選択画面アクション
 * （BIRD共通　各画面呼び出し専用）
 * 
 * 作成日:2008/08/20
 * @author xkinu
 *
 */
public class SearchActionImpl implements SearchAction {
    /** アクションID：初期化処理 */
    public static final String initialize_ACTION_ID 
    	= MenuSearchUtil.SCREEN_ID+"A01";
    /** アクションID：検索処理 */
    public static final String search_ACTION_ID 
		= MenuSearchUtil.SCREEN_ID+"A02";
    /** アクションID：50音検索処理 */
    public static final String search50_ACTION_ID 
		= MenuSearchUtil.SCREEN_ID+"A03";
    /** アクションID:選択処理 */
    public static final String select_ACTION_ID 
		= MenuSearchUtil.SCREEN_ID+"A04";
    /** アクションID:戻る処理 */
    public static final String back_ACTION_ID 
		= MenuSearchUtil.SCREEN_ID+"A05";
    
    /** BIRDユーザー情報 */
    private BirdUserInfo birdUserInfo;
    /** BIRD日付情報 */
    private BirdDateInfo birdDateInfo;   
    
    /** LOGIC【条件項目情報の取得と設定】*/
    private ConditionLogic menuSearchConditionLogic;
    /** LOGIC【検索処理と検索情報の取得】*/
    private SearchLogic menuSearchLogic;
    
    /** DTO【リクエスト元情報保持DTO】*/
    private MenuSearchDto menuSearchDto;
    /** DTO【自画面Session】*/
    private SessionDto menuSearchSesDto;
    /** DTO【自画面Request】*/
    private RequestDto menuSearchReqDto;
    
	/**
	 * 初期化処理
	 * 
	 * @see jp.co.isid.mos.bird.commonform.menusearch.action.SearchAction#initialize()
	 */
	public String initialize() {
		//１．DTO【リクエスト元情報保持DTO】.アクション種類が”初期化”の場合、
		//　　別画面からメニュー選択画面が呼び出されたと判断し、下記の処理を行います。
		if(MenuSearchUtil.ACTION_KIND_INIT == getMenuSearchDto().getActionKind()) {
            //１.DTO【リクエスト元情報保持DTO】.アクション種類へ”稼動中”を設定します。
			getMenuSearchDto().setActionKind(MenuSearchUtil.ACTION_KIND_EXEC);
        	//２.DTO【自画面Session】.クリア処理を行います。(以前の条件項目が残るのを防止するため)
            getMenuSearchReqDto().clear();
            //３.DTO【自画面Session】.WindowID作成処理を行い、複数ウィンドウ機能で新規WindowID設定します。
            int windowId = getMenuSearchSesDto().createWindowId();
            //４.DTO【自画面Request】.WindowIDへ処理3の新規WindowIDを設定します。
            getMenuSearchReqDto().setWindowId(windowId);
            //５.DTO【自画面Session】.リクエスト元(遷移元)VIEW_IDへDTO【自画面Request】.リクエスト元(遷移元)VIEW_IDを設定します。
            getMenuSearchSesDto().setRequesterViewId(windowId, getMenuSearchDto().getRequesterViewId());
            //６.DTO【自画面Session】.リクエスト元(遷移元)WindowIdへDTO【自画面Request】.リクエスト元(遷移元)WindowIdを設定します。
            getMenuSearchSesDto().setRequesterWindowId(windowId, getMenuSearchDto().getRequesterWindowId());
            //選択モードセット
            getMenuSearchSesDto().setSelectMode(windowId, getMenuSearchDto().getSelectMode());
            getMenuSearchDto().setSelectMode(MenuSearchUtil.SELECT_MODE_SINGLE);
            //７.LOGIC【条件項目値設定】を実行し、初期データ設定処理を行います。
           	getMenuSearchConditionLogic().execute(getMenuSearchSesDto(), getMenuSearchReqDto());
            //リクエストDTOへ選択モードをセット
            getMenuSearchReqDto().setSelectMode(getMenuSearchSesDto().getSelectMode(windowId));
		}
		//２．nullをリターンします。
		return null;
	}

	/**
	 * 検索
	 * 
	 * @see jp.co.isid.mos.bird.commonform.menusearch.action.SearchAction#search()
	 */
	public String search() {
		//１．DTO【自画面Request】.終了メニュー含むか否か判断値の値がfalse(”含まない”)の場合、
		//    DTO【自画面Request】.システム日付へBIRD日付情報.システム日付を設定します。
		if(!getMenuSearchReqDto().isHanbaiEndIn()) {
			getMenuSearchReqDto().setSysDate(getBirdDateInfo().getSysDate());
		}
		//２．ロジック【キャンペーン日報情報の取得】を実行する。戻り値のMap[[条件項目]]を取得します。
		getMenuSearchReqDto().setListSearchData(getMenuSearchLogic().execute(getMenuSearchReqDto()));
		//３．DTO【自画面Session】へ処検索済み条件値を設定する。					
		getMenuSearchSesDto().setSearchedData(getMenuSearchReqDto());
        //リクエストDTOへ選択モードをセット
        getMenuSearchReqDto().setSelectMode(getMenuSearchSesDto().getSelectMode(getMenuSearchReqDto().getWindowId()));
		//４．nullをリターンします。
		return null;
	}
	/**
	 * 50音検索
	 * 
	 * @see jp.co.isid.mos.bird.commonform.menusearch.action.SearchAction#search50()
	 */
	public String search50() {
		//１．50音検索用として新しいDTO【50音検索用Request】をインスタンス化します。
		RequestDto search50Dto = new RequestDto();
		//２．DTO【50音検索用Request】.ウィンドウIDへDTO【自画面Request】.ウィンドウIDを設定します。
		search50Dto.setWindowId(getMenuSearchReqDto().getWindowId());
		//３．選択されたアンカーの文字を取得し、DTO【50音検索用Request】.メニュー名称へ設定します。
		search50Dto.setSearch50Key(getMenuSearchReqDto().getSearch50Key());
		if(!search50Dto.isHanbaiEndIn()) {
			search50Dto.setSysDate(getBirdDateInfo().getSysDate());
		}
		//４．ロジック【キャンペーン日報情報の取得】を実行する。戻り値のMap[[条件項目]]を取得します。
		getMenuSearchReqDto().setListSearchData(getMenuSearchLogic().execute(search50Dto));
		//５．DTO【自画面Session】へ処検索済み条件値を設定する。					
		getMenuSearchSesDto().setSearchedData(getMenuSearchReqDto());
        //リクエストDTOへ選択モードをセット
        getMenuSearchReqDto().setSelectMode(getMenuSearchSesDto().getSelectMode(getMenuSearchReqDto().getWindowId()));
		//６．nullをリターンします。
		return null;
	}

	/**
	 * 選択処理
	 * @see jp.co.isid.mos.bird.commonform.menusearch.action.SearchAction#select()
	 */
	public String select() {
		int windowId = getMenuSearchReqDto().getWindowId();
        List listSearchData = getMenuSearchSesDto().getListSearchData(windowId);
        //３．DTO【リクエスト元情報保持DTO】.エンティティ[社内メニュー情報]へ
        if (getMenuSearchSesDto().getSelectMode(getMenuSearchReqDto().getWindowId()) == MenuSearchUtil.SELECT_MODE_MULTI) {
            getMenuSearchReqDto().setSelectMode(getMenuSearchSesDto().getSelectMode(windowId));
            //複数選択モードの時、選択メニューがあるかチェック・メニューリストをDTOへセット
            getMenuSearchDto().setListSelectMenu(getSelectMenuList());
        }
        else {
            //   DTO【自画面Request】.選択インデックスの位置にあるDTO【自画面Session】.List[[検索結果]][選択結果]を設定します。
            getMenuSearchDto().setSelectedMenuInfo((UIMenuInfo)(listSearchData.get(getMenuSearchReqDto().getSelectedIndex())));
        }
        
		//１．DTO【リクエスト元情報保持DTO】.リクエスト元(遷移元)VIEW_IDへ
		//   DTO【自画面Session】.リクエスト元(遷移元)VIEW_IDを設定します。
		String requestViewId = getMenuSearchSesDto().getRequesterViewId(windowId);
		getMenuSearchDto().setRequesterViewId(requestViewId);
		//２．DTO【リクエスト元情報保持DTO】.リクエスト元(遷移元)ウィンドウIDへ
		//   DTO【自画面Session】.リクエスト元(遷移元)ウィンドウIDを設定します。
		getMenuSearchDto().setRequesterWindowId(getMenuSearchSesDto().getRequesterWindowId(windowId));
        //４．DTO【リクエスト元情報保持DTO】.アクション種類へ”選択”を設定します。
		getMenuSearchDto().setActionKind(MenuSearchUtil.ACTION_KIND_SELECT);
		//５．DTO【自画面Session】.リクエスト元(遷移元)VIEW_IDをリターンします。
		return requestViewId;
	}

	/**
	 * 戻る
	 * 
	 * @see jp.co.isid.mos.bird.commonform.menusearch.action.SearchAction#back()
	 */
	public String back() {
		int windowId = getMenuSearchReqDto().getWindowId();
		//１.DTO【リクエスト元情報保持DTO】.初期化処理を実行します。
		getMenuSearchDto().initialize();
		//２.DTO【リクエスト元情報保持DTO】.リクエスト元(遷移元)VIEW_IDへ
		//   DTO【自画面Session】.リクエスト元(遷移元)VIEW_IDを設定します。
		String requestViewId = getMenuSearchSesDto().getRequesterViewId(windowId);
		getMenuSearchDto().setRequesterViewId(requestViewId);
		//３.DTO【リクエスト元情報保持DTO】.リクエスト元(遷移元)ウィンドウIDへ
		//   DTO【自画面Request】.選択インデックスの位置にあるDTO【自画面Session】.List[[検索結果]][選択結果]を設定します。
		getMenuSearchDto().setRequesterWindowId(getMenuSearchSesDto().getRequesterWindowId(windowId));
        //４.DTO【リクエスト元情報保持DTO】.アクション種類へ”キャンセル”を設定します。
		getMenuSearchDto().setActionKind(MenuSearchUtil.ACTION_KIND_CANCEL);
		//５．DTO【自画面Session】.リクエスト元(遷移元)VIEW_IDをリターンします。
		return requestViewId;
	}

    /**
     * メニュー選択チェック（複数選択モードのみ）
     *
     */
    private List getSelectMenuList() {
        //メニュー選択チェック
        if (CommonUtil.isNull(getMenuSearchReqDto().getMenuSelectIndex())) {
            throw new NotSelectedException("メニュー");
        }
        //選択Indexを配列に展開
        String[] indexes = getMenuSearchReqDto().getMenuSelectIndex().split(",");
        //選択メニューのEntityを戻り値Listにセット
        List listMenu = new ArrayList();
        for (int i = 0; i < indexes.length; i++) {
            UIMenuInfo entity = (UIMenuInfo) getMenuSearchSesDto().getListSearchData(getMenuSearchReqDto().getWindowId()).get((new Integer(indexes[i]).intValue()));
            listMenu.add(entity.getMenuCd());
        }
        return listMenu;
    }
	/**
	 * @return menuSearchConditionLogic を戻します。
	 */
	public ConditionLogic getMenuSearchConditionLogic() {
		return menuSearchConditionLogic;
	}

	/**
	 * @param menuSearchConditionLogic を クラス変数menuSearchConditionLogicへ設定します。
	 */
	public void setMenuSearchConditionLogic(ConditionLogic menuSearchConditionLogic) {
		this.menuSearchConditionLogic = menuSearchConditionLogic;
	}

	/**
	 * @return menuSearchLogic を戻します。
	 */
	public SearchLogic getMenuSearchLogic() {
		return menuSearchLogic;
	}

	/**
	 * @param menuSearchLogic を クラス変数menuSearchLogicへ設定します。
	 */
	public void setMenuSearchLogic(SearchLogic menuSearchLogic) {
		this.menuSearchLogic = menuSearchLogic;
	}

	/**
	 * @return menuSearchDto を戻します。
	 */
	public MenuSearchDto getMenuSearchDto() {
		return menuSearchDto;
	}

	/**
	 * @param menuSearchDto を クラス変数menuSearchDtoへ設定します。
	 */
	public void setMenuSearchDto(MenuSearchDto menuSearchDto) {
		this.menuSearchDto = menuSearchDto;
	}

	/**
	 * @return menuSearchReqDto を戻します。
	 */
	public RequestDto getMenuSearchReqDto() {
		return menuSearchReqDto;
	}

	/**
	 * @param menuSearchReqDto を クラス変数menuSearchReqDtoへ設定します。
	 */
	public void setMenuSearchReqDto(RequestDto menuSearchReqDto) {
		this.menuSearchReqDto = menuSearchReqDto;
	}

	/**
	 * @return menuSearchSesDto を戻します。
	 */
	public SessionDto getMenuSearchSesDto() {
		return menuSearchSesDto;
	}

	/**
	 * @param menuSearchSesDto を クラス変数menuSearchSesDtoへ設定します。
	 */
	public void setMenuSearchSesDto(SessionDto menuSearchSesDto) {
		this.menuSearchSesDto = menuSearchSesDto;
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
