/**
 * 
 */
package jp.co.isid.mos.bird.config.urisokuregist.action.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.commonform.menusearch.dto.MenuSearchDto;
import jp.co.isid.mos.bird.commonform.menusearch.util.MenuSearchUtil;
import jp.co.isid.mos.bird.config.urisokuregist.action.EditAction;
import jp.co.isid.mos.bird.config.urisokuregist.dto.RequestDto;
import jp.co.isid.mos.bird.config.urisokuregist.dto.SessionDto;
import jp.co.isid.mos.bird.config.urisokuregist.entity.TrnUsrShohinGroup;
import jp.co.isid.mos.bird.config.urisokuregist.logic.CheckInputDataLogic;
import jp.co.isid.mos.bird.config.urisokuregist.logic.CopyMenuDataLogic;
import jp.co.isid.mos.bird.config.urisokuregist.logic.GetShohinMenuLogic;
import jp.co.isid.mos.bird.config.urisokuregist.util.UrisokuRegistUtil;
import jp.co.isid.mos.bird.framework.dto.ErrHtmlElementDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * [機能設定]【売上速報設定】
 * 編集画面アクションクラス
 * 
 * 作成日:2008/08/06
 * @author xkinu
 *
 */
public class EditActionImpl implements EditAction {
    /* アクションID：初期処理 */
    public static final String initialize_ACTION_ID 
    	= UrisokuRegistUtil.SCREEN_ID+"A11";
    /* アクションID：タブ切り替え */
    public static final String changeTab_ACTION_ID 
    	= UrisokuRegistUtil.SCREEN_ID+"A12";
    /* アクションID：前月データコピー */
    public static final String lastMonthDataCopy_ACTION_ID 
    	= UrisokuRegistUtil.SCREEN_ID+"A13";
    /* アクションID：全クリア */
    public static final String allClear_ACTION_ID 
    	= UrisokuRegistUtil.SCREEN_ID+"A14";
    /* アクションID：メニュー検索 */
    public static final String searchMenu_ACTION_ID 
    	= UrisokuRegistUtil.SCREEN_ID+"A15";
    /* アクションID：確認 */
    public static final String confirm_ACTION_ID 
    	= UrisokuRegistUtil.SCREEN_ID+"A16";
    /* アクションID：戻る */
    public static final String back_ACTION_ID 
    	= UrisokuRegistUtil.SCREEN_ID+"A17";
    
	/** LOGIC【売上速報レポート用商品メニューの取得】*/
	private GetShohinMenuLogic urisokuRegistGetShohinMenuLogic;
	/** LOGIC【前月分メニュー情報コピー処理】*/
	private CopyMenuDataLogic urisokuRegistCopyMenuDataLogic;
	/** LOGIC【入力データチェック】*/
	private CheckInputDataLogic urisokuRegistCheckInputDataLogic;

	/** 共通DTO【例外情報用】*/
	private ErrHtmlElementDto errHtmlElementDto;
	/** 共通DTO【メニュー選択】*/
	private MenuSearchDto menuSearchDto;
    /** DTO【自画面Session】 */
    private SessionDto urisokuRegistSesDto;
    /** DTO【自画面Request】 */
    private RequestDto urisokuRegistReqDto;
    
	/**
	 * 初期化処理
	 * @return null(自画面VIEW_ID)
	 * @see jp.co.isid.mos.bird.config.urisokuregist.action.EditAction#initialize()
	 */
	public String initialize() {
		//１．共通DTO【メニュー選択】がNullでなく、かつ共通DTO【メニュー選択】.アクション種類が”非稼動”以外の場合は、
		//    共通メニュー選択画面からの遷移と判断し、下記の処理を行います。
		if(getMenuSearchDto() != null && MenuSearchUtil.ACTION_KIND_NULL != getMenuSearchDto().getActionKind()) {
			//1.共通DTO【メニュー選択】.アクション種類が”決定”の場合は、下記の処理を行います。
			if(MenuSearchUtil.ACTION_KIND_SELECT == getMenuSearchDto().getActionKind()) {
				//選択したメニュー情報をDTO【自画面Session】へ設定します。
				UrisokuRegistUtil.setSelectedMenuInfo(getUrisokuRegistSesDto(), getMenuSearchDto());		
			}
			//2.共通DTO【メニュー選択】.アクション種類へ”非稼動”の値を設定します。
			getMenuSearchDto().setActionKind(MenuSearchUtil.ACTION_KIND_NULL);
			//3.DTO【自画面Request】.ウィンドウIDへ共通DTO【メニュー選択】.遷移元ウィンドウIDを設定します。
			getUrisokuRegistReqDto().setWindowId(getMenuSearchDto().getRequesterWindowId());
			//4.DTO【自画面Request】.選択メインタブへDTO【自画面Session】.選択メインタブを設定します。
			getUrisokuRegistReqDto().setSelectedTabMain(getUrisokuRegistSesDto().getSelectedTabMain());
			//5.DTO【自画面Request】.選択サブタブへDTO【自画面Session】.選択サブタブを設定します。
			getUrisokuRegistReqDto().setSelectedTabSub(getUrisokuRegistSesDto().getSelectedTabSub());
			//6.DTO【自画面Request】.選択メニューインデックスへDTO【自画面Session】.選択メニューインデックスを設定します。
			getUrisokuRegistReqDto().setSelectedSearchMenuIndex(getUrisokuRegistSesDto().getSelectedSearchMenuIndex());
		}
		//２．クラス変数【セッションキー作成クラス】で新規セッションKeyを生成する。
		//３．処理７で生成した新規セッションKeyを
		//        DTO【自画面Session】.現行セッションKeyへ設定する。
		//４．処理７で生成した新規セッションKeyを
		//        DTO【自画面Request】.セッションKey保持Mapへ設定する。
        getUrisokuRegistReqDto().setSesstionKey(getUrisokuRegistSesDto().makeSessionKey());
        if(getUrisokuRegistSesDto().getCheckException() != null) {
        	ApplicationException appEx = getUrisokuRegistSesDto().getCheckException();
        	getUrisokuRegistSesDto().setCheckException(null);
        	throw appEx;
        }
        //５．nullをリターンします。
		return null;
	}

    /**
     * タブ切り替え
     * @return null(自画面VIEW_ID)
	 * @see jp.co.isid.mos.bird.config.urisokuregist.action.EditAction#changeTab()
	 */
	public String changeTab() {
    	//１．【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
		if (!getUrisokuRegistSesDto().isValidSessionKey(getUrisokuRegistReqDto())) {
			return CommonUtil.operationErr_VIEW_ID;
		}
		//２．LOGIC【入力データチェック】を実行し、戻り値Map[エラー情報]を取得します。
		Map checkInfo = getUrisokuRegistCheckInputDataLogic().execute(getUrisokuRegistSesDto(), getRequestViewId());
		if(checkInfo.get(CheckInputDataLogic.RP_ERR_EXCEPTION) != null){
			//２－１．変数.エラー画面IDを生成し、戻り値Map[エラー情報].エラーメインタブの値を設定します。
			String errViewId = (String)checkInfo.get(CheckInputDataLogic.RP_ERR_TAB_MAIN);
			//２－２．変数.ApplicationExceptionを生成し、戻り値Map[エラー情報].例外の値を設定します。
			ApplicationException appEx = (ApplicationException)checkInfo.get(CheckInputDataLogic.RP_ERR_EXCEPTION);
			//２－３．DTO【自画面Request】.メイン選択タブへ変数.エラー画面IDの値を設定します。
			getUrisokuRegistReqDto().setSelectedTabMain(errViewId);
			//２－４．戻り値Map[エラー情報].エラーサブタブがnullでない場合、
			//        DTO【自画面Request】.選択サブタブへ戻り値Map[エラー情報].エラーサブタブの値を設定します。
			if((String)checkInfo.get(CheckInputDataLogic.RP_ERR_TAB_SUB) != null) {
				getUrisokuRegistReqDto().setSelectedTabSub((String)checkInfo.get(CheckInputDataLogic.RP_ERR_TAB_SUB));
			}
			//２－５．変数.ApplicationExceptionをthrowします。
			throw appEx;
		}
		//３．DTO【自画面Request】サブ選択タブへ"01"を設定します。
		getUrisokuRegistReqDto().setSelectedTabSub(UrisokuRegistUtil.FRAME_KBN_LEFT);
		//４．nullをリターンします。
		return getUrisokuRegistReqDto().getSelectedTabMain();
	}

	/**
     * 前月分コピー
     * (商品メニュー設定用)
     * @return null(自画面VIEW_ID)
	 * 
	 * @see jp.co.isid.mos.bird.config.urisokuregist.action.EditAction#lastMonthDataCopy()
	 */
	public String lastMonthDataCopy() {
    	//１．【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
		if (!getUrisokuRegistSesDto().isValidSessionKey(getUrisokuRegistReqDto())) {
			return CommonUtil.operationErr_VIEW_ID;
		}
		//２．LOGIC【前月分メニュー情報コピー処理】を実行し、値の設定を行います。
		getUrisokuRegistCopyMenuDataLogic().execute(getUrisokuRegistSesDto(), getUrisokuRegistReqDto());
		//３．nullをリターンします。
		return null;
	}

	/**
     * 全クリア
     * (商品メニュー設定用)
     * @return null(自画面VIEW_ID)
	 * 
	 * @see jp.co.isid.mos.bird.config.urisokuregist.action.EditAction#allClear()
	 */
	public String allClear() {
    	//１．【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
		if (!getUrisokuRegistSesDto().isValidSessionKey(getUrisokuRegistReqDto())) {
			return CommonUtil.operationErr_VIEW_ID;
		}
		//２．全クリア対象データを取得します。
		UILists frame = UrisokuRegistUtil.getFrameData(getUrisokuRegistSesDto(), getUrisokuRegistReqDto().getSelectedTabSub());
		List listClearData = frame.getListData();
		for(int i=0; i<listClearData.size(); i++) {
			((TrnUsrShohinGroup)listClearData.get(i)).setMenuCd("");
			((TrnUsrShohinGroup)listClearData.get(i)).setDataKbn("");
			((TrnUsrShohinGroup)listClearData.get(i)).setDispWord("");
		}
		//３．nullをリターンします。
		return null;
	}

	/**
	 * メニュー検索画面遷移
	 * 
     * @return メニュー選択画面VIEW_ID
	 * @see jp.co.isid.mos.bird.config.urisokuregist.action.EditAction#searchMenu()
	 */
	public String searchMenu() {
    	//１．【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
		if (!getUrisokuRegistSesDto().isValidSessionKey(getUrisokuRegistReqDto())) {
			return CommonUtil.operationErr_VIEW_ID;
		}
		//２．DTO【自画面Session】.選択メインタブへDTO【自画面Request】.選択メインタブを設定します。
		getUrisokuRegistSesDto().setSelectedTabMain(getUrisokuRegistReqDto().getSelectedTabMain());
		//３．DTO【自画面Session】.選択サブタブへDTO【自画面Request】.選択サブタブを設定します。
		getUrisokuRegistSesDto().setSelectedTabSub(getUrisokuRegistReqDto().getSelectedTabSub());
		//４．．DTO【自画面Session】.選択メニューインデックスへDTO【自画面Request】.選択メニューインデックスを設定します。
		getUrisokuRegistSesDto().setSelectedSearchMenuIndex(getUrisokuRegistReqDto().getSelectedSearchMenuIndex());

		int windowId = getUrisokuRegistReqDto().getWindowId();
		//５．DTO【リクエスト元情報保持DTO】.初期化処理を行います。
		getMenuSearchDto().initialize();
		//６.DTO【リクエスト元情報保持DTO】.リクエスト元(呼び出し元)VIEW_IDへ
		//   DTO【自画面Session】.リクエスト元(呼び出し元)VIEW_IDを設定します。
		getMenuSearchDto().setRequesterViewId(getUrisokuRegistReqDto().getSelectedTabMain());
		//７.DTO【リクエスト元情報保持DTO】.リクエスト元(呼び出し元)ウィンドウIDへ
		//   DTO【自画面Session】.リクエスト元(呼び出し元)ウィンドウIDを設定します。
		getMenuSearchDto().setRequesterWindowId(windowId);
		//８．メニュー選択画面のVIEW_IDをリターンします。
		return MenuSearchUtil.VIEW_ID;
	}

	/**
	 * 確認
	 * @return 確認画面VIEW_ID
	 * @see jp.co.isid.mos.bird.config.urisokuregist.action.EditAction#confirm()
	 */
	public String confirm() {
    	//１．【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
		if (!getUrisokuRegistSesDto().isValidSessionKey(getUrisokuRegistReqDto())) {
			return CommonUtil.operationErr_VIEW_ID;
		}
		//２．LOGIC【入力データチェック】を実行し、戻り値Map[エラー情報]を取得します。
		Map checkInfo = getUrisokuRegistCheckInputDataLogic().execute(getUrisokuRegistSesDto(), CheckInputDataLogic.DATA_KBN_ALL);
		if(checkInfo.get(CheckInputDataLogic.RP_ERR_EXCEPTION) != null){
			//２－１．変数.エラー画面IDを生成し、戻り値Map[エラー情報].エラーメインタブの値を設定します。
			String errViewId = (String)checkInfo.get(CheckInputDataLogic.RP_ERR_TAB_MAIN);
			//２－２．変数.ApplicationExceptionを生成し、戻り値Map[エラー情報].例外の値を設定します。
			ApplicationException appEx = (ApplicationException)checkInfo.get(CheckInputDataLogic.RP_ERR_EXCEPTION);
			//２－３．DTO【自画面Request】.メイン選択タブへ変数.エラー画面IDの値を設定します。
			getUrisokuRegistReqDto().setSelectedTabMain(errViewId);
			//２－４．戻り値Map[エラー情報].エラーサブタブがnullでない場合、
			//        DTO【自画面Request】.選択サブタブへ戻り値Map[エラー情報].エラーサブタブの値を設定します。
			if((String)checkInfo.get(CheckInputDataLogic.RP_ERR_TAB_SUB) != null) {
				getUrisokuRegistReqDto().setSelectedTabSub((String)checkInfo.get(CheckInputDataLogic.RP_ERR_TAB_SUB));
			}
			//２－５．変数.エラー画面IDが確認ボタン押下時の画面IDと同じ場合、変数.ApplicationExceptionをthrowします。
			if(getRequestViewId().equals(errViewId)) {
				throw appEx;
			}
			//２－６．DTO【自画面Session】.例外へ変数.ApplicationExceptionを設定します。
			getUrisokuRegistSesDto().setCheckException(appEx);
			return errViewId;

		}
		//３．確認画面VIEW_IDをリターンします。
		return UrisokuRegistUtil.VIEW_ID_CONFIRM;
	}

	/**
     * 戻る
     * @return 初期画面VIEW_ID
	 * @see jp.co.isid.mos.bird.config.urisokuregist.action.EditAction#back()
	 */
	public String back() {
    	//１．【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
		if (!getUrisokuRegistSesDto().isValidSessionKey(getUrisokuRegistReqDto())) {
			return CommonUtil.operationErr_VIEW_ID;
		}
		getUrisokuRegistReqDto().setTaishoYm(getUrisokuRegistSesDto().getTaishoYm());
		//２．初期画面VIEW_IDをリターンします。
		return UrisokuRegistUtil.VIEW_ID_CONDITION;
	}

	/**
	 * @return urisokuRegistReqDto を戻します。
	 */
	public RequestDto getUrisokuRegistReqDto() {
		return urisokuRegistReqDto;
	}

	/**
	 * @param urisokuRegistReqDto urisokuRegistReqDtoへ設定します。
	 */
	public void setUrisokuRegistReqDto(RequestDto urisokuRegistReqDto) {
		this.urisokuRegistReqDto = urisokuRegistReqDto;
	}

	/**
	 * @return urisokuRegistSesDto を戻します。
	 */
	public SessionDto getUrisokuRegistSesDto() {
		return urisokuRegistSesDto;
	}

	/**
	 * @param urisokuRegistSesDto urisokuRegistSesDtoへ設定します。
	 */
	public void setUrisokuRegistSesDto(SessionDto urisokuRegistSesDto) {
		this.urisokuRegistSesDto = urisokuRegistSesDto;
	}

	/**
	 * @return urisokuRegistGetShohinMenuLogic を戻します。
	 */
	public GetShohinMenuLogic getUrisokuRegistGetShohinMenuLogic() {
		return urisokuRegistGetShohinMenuLogic;
	}

	/**
	 * @param urisokuRegistGetShohinMenuLogic urisokuRegistGetShohinMenuLogicへ設定します。
	 */
	public void setUrisokuRegistGetShohinMenuLogic(
			GetShohinMenuLogic urisokuRegistGetShohinMenuLogic) {
		this.urisokuRegistGetShohinMenuLogic = urisokuRegistGetShohinMenuLogic;
	}

	/**
	 * @return urisokuRegistCheckInputDataLogic を戻します。
	 */
	public CheckInputDataLogic getUrisokuRegistCheckInputDataLogic() {
		return urisokuRegistCheckInputDataLogic;
	}

	/**
	 * @param urisokuRegistCheckInputDataLogic urisokuRegistCheckInputDataLogicへ設定します。
	 */
	public void setUrisokuRegistCheckInputDataLogic(
			CheckInputDataLogic urisokuRegistCheckInputDataLogic) {
		this.urisokuRegistCheckInputDataLogic = urisokuRegistCheckInputDataLogic;
	}
	/**
	 * リクエストされた画面IDを取得します。
	 * @return
	 */
	private String getRequestViewId() {
		S2Container container = SingletonS2ContainerFactory.getContainer();
		HttpServletRequest request = (HttpServletRequest) container
        .getComponent("request");
		return (String)request.getAttribute("viewIdValue");
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
	 * @return urisokuRegistCopyMenuDataLogic を戻します。
	 */
	public CopyMenuDataLogic getUrisokuRegistCopyMenuDataLogic() {
		return urisokuRegistCopyMenuDataLogic;
	}

	/**
	 * @param urisokuRegistCopyMenuDataLogic を クラス変数urisokuRegistCopyMenuDataLogicへ設定します。
	 */
	public void setUrisokuRegistCopyMenuDataLogic(
			CopyMenuDataLogic urisokuRegistCopyMenuDataLogic) {
		this.urisokuRegistCopyMenuDataLogic = urisokuRegistCopyMenuDataLogic;
	}

	/**
	 * @return errHtmlElementDto を戻します。
	 */
	public ErrHtmlElementDto getErrHtmlElementDto() {
		return errHtmlElementDto;
	}

	/**
	 * @param errHtmlElementDto を クラス変数errHtmlElementDtoへ設定します。
	 */
	public void setErrHtmlElementDto(ErrHtmlElementDto errHtmlElementDto) {
		this.errHtmlElementDto = errHtmlElementDto;
	}
}
