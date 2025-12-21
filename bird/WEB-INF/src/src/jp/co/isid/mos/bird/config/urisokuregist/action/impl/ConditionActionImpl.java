/**
 * 
 */
package jp.co.isid.mos.bird.config.urisokuregist.action.impl;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.config.urisokuregist.action.ConditionAction;
import jp.co.isid.mos.bird.config.urisokuregist.dto.RequestDto;
import jp.co.isid.mos.bird.config.urisokuregist.dto.SessionDto;
import jp.co.isid.mos.bird.config.urisokuregist.logic.GetRegistDataLogic;
import jp.co.isid.mos.bird.config.urisokuregist.util.UrisokuRegistUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;

/**
 * 機能設定]【売上速報設定】
 * 初期画面アクションクラス
 * 
 * 作成日:2008/08/06
 * @author xkinu
 *
 */
public class ConditionActionImpl implements ConditionAction {
    /* アクションID：初期処理 */
    public static final String initialize_ACTION_ID 
    	= UrisokuRegistUtil.SCREEN_ID+"A01";
    /* アクションID：検索処理 */
    public static final String edit_ACTION_ID 
    	= UrisokuRegistUtil.SCREEN_ID+"A02";
    
    /** BIRDユーザー情報 */
    private BirdUserInfo birdUserInfo;
    /** BIRD日付情報 */
    private BirdDateInfo birdDateInfo;
    /** 共通DTO【プルダウンメニューDTO】*/
    private PullDownMenuDto pullDownMenuDto;
    /** DTO【自画面Session】 */
    private SessionDto urisokuRegistSesDto;
    /** DTO【自画面Request】 */
    private RequestDto urisokuRegistReqDto;
    
    /* LOGIC【編集情報の取得】ロジック */
    private GetRegistDataLogic urisokuRegistGetRegistDataLogic;

	/**
	 * 初期化処理
	 * @return null(自画面VIEW_ID)
	 * @see jp.co.isid.mos.bird.config.urisokuregist.action.ConditionAction#initialize()
	 */
	public String initialize() {
        //１．DTO【プルダウンメニュー】.クリアフラグがtrueの場合下記の処理を行う。
        if(getPullDownMenuDto().isClearFlg()){
            //１.DTO【プルダウンメニュー】.クリアフラグをfalseに設定する。
            getPullDownMenuDto().setClearFlg(false);
        	//２.DTO【自画面Session】.クリア処理をおこなう。
            //(以前の条件項目が残るのを防止するため)
            getUrisokuRegistReqDto().clear();
            //３.DTO【自画面Session】.WindowID作成処理を行い、複数ウィンドウ機能で新規WindowID設定する。
            int windowId = getUrisokuRegistSesDto().createWindowId();
            //４.DTO【自画面Request】.WindowIDへ処理3の新規WindowIDを設定します。
            getUrisokuRegistReqDto().setWindowId(windowId);
            //５.BIRD日付情報をDTO【自画面Session】へ設定します。
            getUrisokuRegistSesDto().setBirdDateInfo(birdDateInfo);
            //６.BIRDログインユーザー情報をDTO【自画面Session】へ設定します。
            getUrisokuRegistSesDto().setBirdUserInfo(birdUserInfo);
        				
            //１０．条件項目のプルダウンリストList[[対象レポート年月]]を作成し、
			//    DTO【自画面Session】.List[[対象レポート年月]]へ設定します。
			//    (システム日付の当月含めて未来13ヶ月分のyyyyMMを設定します。)
	        getUrisokuRegistSesDto().setListTaishoYm(
	        		UrisokuRegistUtil.creatListNengetu(getBirdDateInfo().getSysDate(), 13));
	        //１１．DTO【自画面Request】.レポート年月へデフォルト値として翌月の値を設定します。
	        SelectItem sitem = (SelectItem)(getUrisokuRegistSesDto().getListTaishoYm().get(1));
	        getUrisokuRegistReqDto().setTaishoYm((String)sitem.getValue());
        }
		//２．クラス変数【セッションキー作成クラス】で新規セッションKeyを生成する。
		//３．処理７で生成した新規セッションKeyを
		//        DTO【自画面Session】.現行セッションKeyへ設定する。
		//４．処理７で生成した新規セッションKeyを
		//        DTO【自画面Request】.セッションKey保持Mapへ設定する。
        getUrisokuRegistReqDto().setSesstionKey(getUrisokuRegistSesDto().makeSessionKey());
        //５．nullをリターンします。
		return null;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.config.urisokuregist.action.ConditionAction#edit()
	 */
	public String edit() {
    	//１．ロジック【編集情報の取得】を実行し、
		//    戻り値List[[編集情報]]をDTO【自画面Session】.List[[編集情報]]へ設定します。
		getUrisokuRegistSesDto().setListRegistData(
				getUrisokuRegistGetRegistDataLogic().execute(getUrisokuRegistReqDto().getTaishoYm()));
		//２．DTO【自画面Session】.対象レポート年月へDTO【自画面Request】.対象レポート年月を設定します。
		getUrisokuRegistSesDto().setTaishoYm(getUrisokuRegistReqDto().getTaishoYm());
		//３．DTO【自画面Request】.選択タブへ「前月末店舗数設定」値を設定します。
		getUrisokuRegistReqDto().setSelectedTabMain(UrisokuRegistUtil.VIEW_ID_EDIT_MISECNT);
		//３．DTO【自画面Request】.選択タブへ「前月末店舗数設定」値を設定します。
		getUrisokuRegistReqDto().setSelectedTabSub(UrisokuRegistUtil.FRAME_KBN_LEFT);
		//４．編集画面のVIEW_IDをリターンします。
		return UrisokuRegistUtil.VIEW_ID_EDIT_MISECNT;
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

	/**
	 * @return urisokuRegistGetRegistDataLogic を戻します。
	 */
	public GetRegistDataLogic getUrisokuRegistGetRegistDataLogic() {
		return urisokuRegistGetRegistDataLogic;
	}

	/**
	 * @param urisokuRegistGetRegistDataLogic urisokuRegistGetRegistDataLogicへ設定します。
	 */
	public void setUrisokuRegistGetRegistDataLogic(
			GetRegistDataLogic urisokuRegistGetRegistDataLogic) {
		this.urisokuRegistGetRegistDataLogic = urisokuRegistGetRegistDataLogic;
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
	 * @return birdDateInfo を戻します。
	 */
	public BirdDateInfo getBirdDateInfo() {
		return birdDateInfo;
	}

	/**
	 * @param birdDateInfo 設定する birdDateInfo。
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
	 * @param birdUserInfo 設定する birdUserInfo。
	 */
	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}
}
