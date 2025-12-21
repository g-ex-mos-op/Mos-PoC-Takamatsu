/**
 * 
 */
package jp.co.isid.mos.bird.config.urisokuregist.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.config.urisokuregist.action.ConfirmAction;
import jp.co.isid.mos.bird.config.urisokuregist.dto.RequestDto;
import jp.co.isid.mos.bird.config.urisokuregist.dto.SessionDto;
import jp.co.isid.mos.bird.config.urisokuregist.entity.UIUsrCamp;
import jp.co.isid.mos.bird.config.urisokuregist.logic.RegistLogic;
import jp.co.isid.mos.bird.config.urisokuregist.util.UrisokuRegistUtil;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;

/**
 * 機能設定]【売上速報設定】
 * 確認画面アクションクラス
 * 
 * 作成日:2008/08/06
 * @author xkinu
 *
 */
public class ConfirmActionImpl implements ConfirmAction {
    /* アクションID：初期処理 */
    public static final String initialize_ACTION_ID 
    	= UrisokuRegistUtil.SCREEN_ID+"A21";
    /* アクションID：登録・終了 */
    public static final String auth_ACTION_ID 
    	= UrisokuRegistUtil.SCREEN_ID+"A22";
    /* アクションID：戻る */
    public static final String back_ACTION_ID 
    	= UrisokuRegistUtil.SCREEN_ID+"A23";
    
	/** LOGIC【売上速報レポート設定情報登録処理】*/
	private RegistLogic urisokuRegistLogic;

    /** 共通DTO【プルダウンメニューDTO】*/
    private PullDownMenuDto pullDownMenuDto;
    /** DTO【自画面SessionDto】 */
    private SessionDto urisokuRegistSesDto;
    /** DTO【自画面RequestDto】 */
    private RequestDto urisokuRegistReqDto;

	/**
	 * 初期化処理
	 * @return null(自画面VIEW_ID)
	 * @see jp.co.isid.mos.bird.config.urisokuregist.action.ConfirmAction#initialize()
	 */
	public String initialize() {
		//１．クラス変数【セッションキー作成クラス】で新規セッションKeyを生成する。
		//２．処理７で生成した新規セッションKeyを
		//        DTO【自画面Session】.現行セッションKeyへ設定する。
		//３．処理７で生成した新規セッションKeyを
		//        DTO【自画面Request】.セッションKey保持Mapへ設定する。
        getUrisokuRegistReqDto().setSesstionKey(getUrisokuRegistSesDto().makeSessionKey());
    	String lastCampId = "";
    	boolean lastCampTitleFlg = false;
    	boolean lastCampJissekiFlg = false;
    	List listData = getUrisokuRegistSesDto().getListRegistDataCamp();
    	//１．for分でList[[編集データ]]の件数分下記の処理を行います。
    	for(int i=0; i<listData.size(); i++) {
    		//for-1.現行インデックスの位置にある[編集データ]を取得します。
    		UIUsrCamp entity = (UIUsrCamp)listData.get(i);
    		if(!lastCampId.equals(entity.getCampId())) {
    			lastCampId = entity.getCampId();
    	    	lastCampTitleFlg = entity.isTitle();
    	    	lastCampJissekiFlg = entity.isJisseki();
    		}
    		entity.setTitle(lastCampTitleFlg);
    		entity.setJisseki(lastCampJissekiFlg);
    	}

        //４．nullをリターンします。
		return null;
	}

	/**
	 * 登録・終了
	 * @return 初期画面VIEW_ID
	 * @see jp.co.isid.mos.bird.config.urisokuregist.action.ConfirmAction#auth()
	 */
	public String auth() {
    	//１．【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
		if (!getUrisokuRegistSesDto().isValidSessionKey(getUrisokuRegistReqDto())) {
			return CommonUtil.operationErr_VIEW_ID;
		}
		//２．LOGIC【売上速報レポート設定情報登録処理】を実行し、登録処理を行います。
		getUrisokuRegistLogic().execute(getUrisokuRegistSesDto());
		//３．DTO【プルダウンメニュー】.クリアフラグをtrueに設定します。
		getPullDownMenuDto().setClearFlg(true);
		//４．初期画面VIEW_IDをリターンします。
		return UrisokuRegistUtil.VIEW_ID_CONDITION;
	}

	/**
     * 戻る
     * @return 編集画面VIEW_ID
	 * @see jp.co.isid.mos.bird.config.urisokuregist.action.ConfirmAction#back()
	 */
	public String back() {
    	// 【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
		if (!getUrisokuRegistSesDto().isValidSessionKey(getUrisokuRegistReqDto())) {
			return CommonUtil.operationErr_VIEW_ID;
		}
		//２．編集画面VIEW_IDをリターンします。
		return getUrisokuRegistReqDto().getSelectedTabMain();
	}

	/**
	 * @return urisokuRegistReqDto を戻します。
	 */
	public RequestDto getUrisokuRegistReqDto() {
		return urisokuRegistReqDto;
	}

	/**
	 * @param dto urisokuRegistReqDtoへ設定します。
	 */
	public void setUrisokuRegistReqDto(RequestDto dto) {
		this.urisokuRegistReqDto = dto;
	}

	/**
	 * @return urisokuRegistSesDto を戻します。
	 */
	public SessionDto getUrisokuRegistSesDto() {
		return urisokuRegistSesDto;
	}

	/**
	 * @param dto urisokuRegistSesDtoへ設定します。
	 */
	public void setUrisokuRegistSesDto(SessionDto dto) {
		this.urisokuRegistSesDto = dto;
	}

	/**
	 *  LOGIC【売上速報レポート設定情報登録処理】取得処理
	 *  
	 * @return urisokuRegistLogic を戻します。
	 */
	public RegistLogic getUrisokuRegistLogic() {
		return urisokuRegistLogic;
	}

	/**
	 *  LOGIC【売上速報レポート設定情報登録処理】設定処理
	 *  
	 * @param logic urisokuRegistLogicへ設定します。
	 */
	public void setUrisokuRegistLogic(RegistLogic logic) {
		this.urisokuRegistLogic = logic;
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
