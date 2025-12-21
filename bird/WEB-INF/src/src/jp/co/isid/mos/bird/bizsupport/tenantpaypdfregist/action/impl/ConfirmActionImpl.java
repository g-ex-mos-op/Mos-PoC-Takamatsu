/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.action.impl;

import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.action.ConfirmAction;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dto.RequestDto;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dto.SessionDto;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.logic.RegistLogic;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.util.TenantPayPdfRegistUtil;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.action.impl.FileUploadActionImpl;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;

/**
 * テナント入金明細PDF登録画面
 * 確認画面アクション
 * 
 * 作成日:2009/06/19
 * @author xkinu
 *
 */
public class ConfirmActionImpl extends FileUploadActionImpl implements ConfirmAction {
    /* アクションID：初期処理 */
    public static final String initialize_ACTION_ID = TenantPayPdfRegistUtil.ACTION_ID+"21";
    /* アクションID：戻る処理 */
    public static final String back_ACTION_ID = TenantPayPdfRegistUtil.ACTION_ID+"22";
    /* アクションID：登録処理 */
    public static final String regist_ACTION_ID = TenantPayPdfRegistUtil.ACTION_ID+"23";

    /** 共通DTO【プルダウンメニューDTO】*/
    private PullDownMenuDto pullDownMenuDto;
    /** DTO【自画面Session】 */
    private SessionDto tenantPayPdfRegistSesDto;
    /** DTO【自画面Request】 */
    private RequestDto tenantPayPdfRegistReqDto;
    /** LOGIC【登録】 */
    private RegistLogic tenantPayPdfRegistRegistLogic;

    /* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.action.ConfirmAction#initialize()
	 */
	public String initialize() {
		//１．クラス変数【セッションキー作成クラス】で新規セッションKeyを生成する。
		//２．処理７で生成した新規セッションKeyを
		//        DTO【自画面Session】.現行セッションKeyへ設定する。
		//３．処理７で生成した新規セッションKeyを
		//        DTO【自画面Request】.セッションKey保持Mapへ設定する。
        getTenantPayPdfRegistReqDto().setSesstionKey(getTenantPayPdfRegistSesDto().makeSessionKey());
		return null;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.action.ConfirmAction#back()
	 */
	public String back() {
    	//１．【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
		if (!getTenantPayPdfRegistSesDto().isValidSessionKey(getTenantPayPdfRegistReqDto())) {
			return CommonUtil.operationErr_VIEW_ID;
		}
		//２．編集画面VIEW_IDをリターンします。
		return TenantPayPdfRegistUtil.VIEW_ID_EDIT;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.action.ConfirmAction#regist()
	 */
	public String regist() {
    	//１．【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
		if (!getTenantPayPdfRegistSesDto().isValidSessionKey(getTenantPayPdfRegistReqDto())) {
			return CommonUtil.operationErr_VIEW_ID;
		}
		//２．LOGIC【登録】を実行し、登録処理を行います。
		getTenantPayPdfRegistRegistLogic().execute(getTenantPayPdfRegistSesDto());
		//３．DTO【プルダウンメニュー】.クリアフラグをtrueに設定します。
		getPullDownMenuDto().setClearFlg(true);
		if (TenantPayPdfRegistUtil.REGIST_TYPE_DELETE != getTenantPayPdfRegistSesDto().getRegistType()) {
			//４．superクラスのファイル書込み処理を実行し、
			//    テンポラリーファイルの正規のファイルとして再設定を行います。
			upload();
		}
		//５．初期画面VIEW_IDをリターンします。
		return TenantPayPdfRegistUtil.VIEW_ID_CONDITION;
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
	 * @return tenantPayPdfRegistReqDto を戻します。
	 */
	public RequestDto getTenantPayPdfRegistReqDto() {
		return tenantPayPdfRegistReqDto;
	}

	/**
	 * @param requestDto を クラス変数tenantPayPdfRegistReqDtoへ設定します。
	 */
	public void setTenantPayPdfRegistReqDto(RequestDto requestDto) {
		this.tenantPayPdfRegistReqDto = requestDto;
	}

	/**
	 * @return tenantPayPdfRegistSesDto を戻します。
	 */
	public SessionDto getTenantPayPdfRegistSesDto() {
		return tenantPayPdfRegistSesDto;
	}

	/**
	 * @param sessionDto を クラス変数tenantPayPdfRegistSesDtoへ設定します。
	 */
	public void setTenantPayPdfRegistSesDto(SessionDto sessionDto) {
		this.tenantPayPdfRegistSesDto = sessionDto;
	}

	/**
	 * @return tenantPayPdfRegistRegistLogic を戻します。
	 */
	public RegistLogic getTenantPayPdfRegistRegistLogic() {
		return tenantPayPdfRegistRegistLogic;
	}

	/**
	 * @param logic を クラス変数tenantPayPdfRegistRegistLogicへ設定します。
	 */
	public void setTenantPayPdfRegistRegistLogic(
			RegistLogic logic) {
		this.tenantPayPdfRegistRegistLogic = logic;
	}

}
