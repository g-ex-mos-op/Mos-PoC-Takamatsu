/**
 * 
 */
package jp.co.isid.mos.bird.config.zenurikakuteiregist.action.impl;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.config.zenurikakuteiregist.action.EditAction;
import jp.co.isid.mos.bird.config.zenurikakuteiregist.dto.RequestDto;
import jp.co.isid.mos.bird.config.zenurikakuteiregist.dto.SessionDto;
import jp.co.isid.mos.bird.config.zenurikakuteiregist.logic.GetRegistDataLogic;
import jp.co.isid.mos.bird.config.zenurikakuteiregist.util.ZenurikakuteiRegistUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;


/**
 * 機能設定]【売上速報前月売上確定登録】
 * 編集(初期)画面アクションクラス
 * 
 * 作成日:2008/08/15
 * @author xkinu
 *
 */
public class EditActionImpl implements EditAction {
    /* アクションID：初期処理 */
    public static final String initialize_ACTION_ID 
    	= ZenurikakuteiRegistUtil.SCREEN_ID+"A01";
    /* アクションID：検索処理 */
    public static final String edit_ACTION_ID 
    	= ZenurikakuteiRegistUtil.SCREEN_ID+"A02";
    
    /** BIRDユーザー情報 */
    private BirdUserInfo birdUserInfo;
    /** BIRD日付情報 */
    private BirdDateInfo birdDateInfo;
    /** 共通DTO【プルダウンメニューDTO】*/
    private PullDownMenuDto pullDownMenuDto;
    /** DTO【自画面Session】 */
    private SessionDto zenurikakuteiRegistSesDto;
    /** DTO【自画面Request】 */
    private RequestDto zenurikakuteiRegistReqDto;
    
    /* LOGIC【編集情報の取得】ロジック */
    private GetRegistDataLogic zenurikakuteiRegistGetRegistDataLogic;

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.config.zenurikakuteiregist.action.EditAction#initialize()
	 */
	public String initialize() {
        //１．DTO【プルダウンメニュー】.クリアフラグがtrueの場合下記の処理を行う。
        if(getPullDownMenuDto().isClearFlg()){
            //１.DTO【プルダウンメニュー】.クリアフラグをfalseに設定する。
            getPullDownMenuDto().setClearFlg(false);
        	//２.DTO【自画面Session】.クリア処理をおこなう。
            //(以前の条件項目が残るのを防止するため)
//          getZenurikakuteiRegistReqDto().clear();
            //３.DTO【自画面Session】.WindowID作成処理を行い、複数ウィンドウ機能で新規WindowID設定する。
            int windowId = getZenurikakuteiRegistSesDto().createWindowId();
            //４.DTO【自画面Request】.WindowIDへ処理3の新規WindowIDを設定します。
            getZenurikakuteiRegistReqDto().setWindowId(windowId);
            //５.BIRD日付情報をDTO【自画面Session】へ設定します。
            getZenurikakuteiRegistSesDto().setBirdDateInfo(birdDateInfo);
            //６.BIRDログインユーザー情報をDTO【自画面Session】へ設定します。
            getZenurikakuteiRegistSesDto().setBirdUserInfo(birdUserInfo);
			
            //７．条件項目のプルダウンリストList[[対象レポート年月]]を作成し、
			//    DTO【自画面Session】.List[[対象レポート年月]]へ設定します。
			//    (システム日付の当月含めて未来13ヶ月分のyyyyMMを設定します。)
	        getZenurikakuteiRegistSesDto().setListRegistData(
	        		getZenurikakuteiRegistGetRegistDataLogic().execute(getBirdDateInfo().getSysDate()));
        }
		//２．クラス変数【セッションキー作成クラス】で新規セッションKeyを生成する。
		//３．処理７で生成した新規セッションKeyを
		//        DTO【自画面Session】.現行セッションKeyへ設定する。
		//４．処理７で生成した新規セッションKeyを
		//        処理３で設定したWindowIDをKeyに
		//        DTO【自画面Session】.セッションKey保持Mapへ設定する。
        getZenurikakuteiRegistReqDto().setSesstionKey(getZenurikakuteiRegistSesDto().makeSessionKey());
        //２．nullをリターンします。
		return null;
	}
	/**
	 * 確認
	 * @return 確認画面VIEW_ID
	 * @see jp.co.isid.mos.bird.config.zenurikakuteiregist.action.EditAction#confirm()
	 */
	public String confirm() {
    	//１．【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
		if (!getZenurikakuteiRegistSesDto().isValidSessionKey(getZenurikakuteiRegistReqDto())) {
			return CommonUtil.operationErr_VIEW_ID;
		}
		//３．確認画面VIEW_IDをリターンします。
		return ZenurikakuteiRegistUtil.VIEW_ID_CONFIRM;
	}
	/**
	 * @return birdDateInfo を戻します。
	 */
	public BirdDateInfo getBirdDateInfo() {
		return birdDateInfo;
	}

	/**
	 * @param birdDateInfo birdDateInfoへ設定します。
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
	 * @param birdUserInfo birdUserInfoへ設定します。
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
	 * @param pullDownMenuDto pullDownMenuDtoへ設定します。
	 */
	public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
		this.pullDownMenuDto = pullDownMenuDto;
	}

	/**
	 * @return zenurikakuteiRegistGetRegistDataLogic を戻します。
	 */
	public GetRegistDataLogic getZenurikakuteiRegistGetRegistDataLogic() {
		return zenurikakuteiRegistGetRegistDataLogic;
	}

	/**
	 * @param zenurikakuteiRegistGetRegistDataLogic zenurikakuteiRegistGetRegistDataLogicへ設定します。
	 */
	public void setZenurikakuteiRegistGetRegistDataLogic(
			GetRegistDataLogic zenurikakuteiRegistGetRegistDataLogic) {
		this.zenurikakuteiRegistGetRegistDataLogic = zenurikakuteiRegistGetRegistDataLogic;
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

}
