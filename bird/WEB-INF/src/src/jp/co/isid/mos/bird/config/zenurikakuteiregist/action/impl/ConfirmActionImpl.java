/**
 * 
 */
package jp.co.isid.mos.bird.config.zenurikakuteiregist.action.impl;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.config.zenurikakuteiregist.action.ConfirmAction;
import jp.co.isid.mos.bird.config.zenurikakuteiregist.dto.RequestDto;
import jp.co.isid.mos.bird.config.zenurikakuteiregist.dto.SessionDto;
import jp.co.isid.mos.bird.config.zenurikakuteiregist.logic.RegistLogic;
import jp.co.isid.mos.bird.config.zenurikakuteiregist.util.ZenurikakuteiRegistUtil;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;

/**
 * 機能設定]【売上速報前月売上確定登録】
 * 確認画面アクションクラス
 * 
 * 作成日:2008/08/15
 * @author xkinu
 *
 */
public class ConfirmActionImpl implements ConfirmAction {
    /* アクションID：初期処理 */
    public static final String initialize_ACTION_ID 
    	= ZenurikakuteiRegistUtil.SCREEN_ID+"A11";
    /* アクションID：登録・終了 */
    public static final String auth_ACTION_ID 
    	= ZenurikakuteiRegistUtil.SCREEN_ID+"A12";
    /* アクションID：戻る */
    public static final String back_ACTION_ID 
    	= ZenurikakuteiRegistUtil.SCREEN_ID+"A13";
    
	/** LOGIC【売上速報レポート設定情報登録処理】*/
	private RegistLogic zenurikakuteiRegistLogic;

    /** 共通DTO【プルダウンメニューDTO】*/
    private PullDownMenuDto pullDownMenuDto;
    /** DTO【自画面SessionDto】 */
    private SessionDto zenurikakuteiRegistSesDto;
    /** DTO【自画面RequestDto】 */
    private RequestDto zenurikakuteiRegistReqDto;

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.config.zenurikakuteiregist.action.ConfirmAction#initialize()
	 */
	public String initialize() {
		//１．クラス変数【セッションキー作成クラス】で新規セッションKeyを生成する。
		//２．処理７で生成した新規セッションKeyを
		//        DTO【自画面Session】.現行セッションKeyへ設定する。
		//３．処理７で生成した新規セッションKeyを
		//        処理３で設定したWindowIDをKeyに
		//        DTO【自画面Session】.セッションKey保持Mapへ設定する。
        getZenurikakuteiRegistReqDto().setSesstionKey(getZenurikakuteiRegistSesDto().makeSessionKey());
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	/**
	 * 登録・終了
	 * @return 初期画面VIEW_ID
	 * @see jp.co.isid.mos.bird.config.zenurikakuteiregist.action.ConfirmAction#auth()
	 */
	public String auth() {
    	//１．【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
		if (!getZenurikakuteiRegistSesDto().isValidSessionKey(getZenurikakuteiRegistReqDto())) {
			return CommonUtil.operationErr_VIEW_ID;
		}
		//２．LOGIC【売上速報レポート設定情報登録処理】を実行し、登録処理を行います。
		getZenurikakuteiRegistLogic().execute(getZenurikakuteiRegistSesDto());
		//３．DTO【プルダウンメニュー】.クリアフラグをtrueに設定します。
		getPullDownMenuDto().setClearFlg(true);
		//４．編集(初期)画面VIEW_IDをリターンします。
		return ZenurikakuteiRegistUtil.VIEW_ID_EDIT;
	}

	/**
     * 戻る
     * @return 編集画面VIEW_ID
	 * @see jp.co.isid.mos.bird.config.zenurikakuteiregist.action.ConfirmAction#back()
	 */
	public String back() {
    	// 【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
		if (!getZenurikakuteiRegistSesDto().isValidSessionKey(getZenurikakuteiRegistReqDto())) {
			return CommonUtil.operationErr_VIEW_ID;
		}
		//２．編集画面VIEW_IDをリターンします。
		return ZenurikakuteiRegistUtil.VIEW_ID_EDIT;
	}

	/**
	 * @return zenurikakuteiRegistLogic を戻します。
	 */
	public RegistLogic getZenurikakuteiRegistLogic() {
		return zenurikakuteiRegistLogic;
	}

	/**
	 * @param zenurikakuteiRegistLogic zenurikakuteiRegistLogicへ設定します。
	 */
	public void setZenurikakuteiRegistLogic(RegistLogic zenurikakuteiRegistLogic) {
		this.zenurikakuteiRegistLogic = zenurikakuteiRegistLogic;
	}

	/**
	 * @return zenurikakuteiRegistReqDto を戻します。
	 */
	public RequestDto getZenurikakuteiRegistReqDto() {
		return zenurikakuteiRegistReqDto;
	}

	/**
	 * @param zenurikakuteiRegistReqDto zenurikakuteiRegistReqDtoへ設定します。
	 */
	public void setZenurikakuteiRegistReqDto(RequestDto zenurikakuteiRegistReqDto) {
		this.zenurikakuteiRegistReqDto = zenurikakuteiRegistReqDto;
	}

	/**
	 * @return zenurikakuteiRegistSesDto を戻します。
	 */
	public SessionDto getZenurikakuteiRegistSesDto() {
		return zenurikakuteiRegistSesDto;
	}

	/**
	 * @param zenurikakuteiRegistSesDto zenurikakuteiRegistSesDtoへ設定します。
	 */
	public void setZenurikakuteiRegistSesDto(SessionDto zenurikakuteiRegistSesDto) {
		this.zenurikakuteiRegistSesDto = zenurikakuteiRegistSesDto;
	}

	/**
	 * @return pullDownMenuDto を戻します。
	 */
	public PullDownMenuDto getPullDownMenuDto() {
		return pullDownMenuDto;
	}

	/**
	 * @param pullDownMenuDto pullDownMenuDtoへ設定します。
	 */
	public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
		this.pullDownMenuDto = pullDownMenuDto;
	}

}
