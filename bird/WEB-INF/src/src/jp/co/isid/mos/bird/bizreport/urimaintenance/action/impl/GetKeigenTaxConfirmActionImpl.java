package jp.co.isid.mos.bird.bizreport.urimaintenance.action.impl;

import jp.co.isid.mos.bird.bizreport.urimaintenance.action.GetKeigenTaxConfirmAction;
import jp.co.isid.mos.bird.bizreport.urimaintenance.common.UriMaintenanceCommon;
import jp.co.isid.mos.bird.bizreport.urimaintenance.common.UriMaintenanceConstants;
import jp.co.isid.mos.bird.bizreport.urimaintenance.dto.UriMaintenanceDto;
import jp.co.isid.mos.bird.bizreport.urimaintenance.logic.GetKeigenTaxLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.BatchProcessingException;

/**
 * 売上修正（軽減税率確認画面）
 *
 * 軽減税率対応です。
 * 作成日:2019/07/09
 * @author USI欒
 *
 */
public class GetKeigenTaxConfirmActionImpl implements GetKeigenTaxConfirmAction {

    /* アクションID */
    public static final String view_ACTION_ID         = "BBR008A70";
    public static final String initialize_ACTION_ID   = "BBR008A71";
    public static final String changeTab_ACTION_ID    = "BBR008A72";
    public static final String back_ACTION_ID         = "BBR008A73";

    protected static final String VIEW_ID = "BBR008V07";
    /** ACTION【軽減税率確認画面】*/
    private UriMaintenanceConfirmActionImpl uriMaintenanceConfirmAction;
    /** DTO【Session情報】 */
    private UriMaintenanceDto sessionDto;
    /** LOGIC【会計区分売上修正検索】*/
    private GetKeigenTaxLogic uriMaintenanceGetKeigenTaxLogic;

    private ApplicationException throwApplicationException;
    private String targetTabNo;


    /**
     * 明細
     *
     * 現金在高（日次）売上消費税明細の画面から呼び出される処理です。
     */
    public String getKeigenTaxData() {
        //０．ユーザーオペレーション整合性チェック
        if(UriMaintenanceCommon.isUserOperationError(getUriMaintenanceDto())){
            return UriMaintenanceConstants.VIEW_ID_OPERATION_ERR;
        }
        try {
        	//LOGIC【軽減税率売上修正検索】を実行する。
        	//１．DTO【Session情報】.Map[集計区分別会計区分結果情報]が空の場合、
        	if(sessionDto.getListGetKeigenTaxData().size()==0) {
        		getUriMaintenanceGetKeigenTaxLogic().execute(sessionDto);
        	}

        	//２．DTO【Session情報】.集計区分タブNoへデフォルト値("01")を設定する。
        	getUriMaintenanceDto().setSkTabNo("01");
        }
        catch (ApplicationException appEx) {
        	if (appEx instanceof BatchProcessingException) {
        		//バッチ排他の例外はそのままスルー。
				throw appEx;
			}
			setThrowApplicationException(appEx);
		}
    	//５．会計区分確認画面VIEW_IDをリターン。
    	return VIEW_ID;
    }
    /**
     * 初期表示
     * @return
     */
    public String initialize() {
    	if(getThrowApplicationException() != null) {
    		throw getThrowApplicationException();
    	}
        return null;
    }

    /**
     * タブ切替
     * @return
     */
    public String changeTab() {
        //ユーザーオペレーション整合性チェック
        if(UriMaintenanceCommon.isUserOperationError(getUriMaintenanceDto())){
            return UriMaintenanceConstants.VIEW_ID_OPERATION_ERR;
        }
        //１．DTO【セッション情報】．対象タブＮｏを設定します。
        getUriMaintenanceDto().setSkTabNo("01");
        //２．nullをリターン。
        return null;
    }

    /**
     * 戻る
     * @return
     */
    public String back() {
        //ユーザーオペレーション整合性チェック
        if(UriMaintenanceCommon.isUserOperationError(getUriMaintenanceDto())){
            return UriMaintenanceConstants.VIEW_ID_OPERATION_ERR;
        }
        //確認画面へ遷移する
        return UriMaintenanceConstants.VIEWID_CONFIRM;
    }

    /**
     * DTO【Session情報】
     * @return
     */
    public UriMaintenanceDto getUriMaintenanceDto() {
        return sessionDto;
    }
    /**
     * DTO【Session情報】
     */
    public void setUriMaintenanceDto(UriMaintenanceDto dto) {
        this.sessionDto = dto;
    }

	/**
	 * ACTION【集計区分確認画面】
	 * @return クラス変数uriMaintenanceConfirmAction を戻します。
	 */
	public UriMaintenanceConfirmActionImpl getUriMaintenanceConfirmAction() {
		return uriMaintenanceConfirmAction;
	}
	/**
	 * ACTION【集計区分確認画面】
	 * @param skConfirmAction を クラス変数uriMaintenanceConfirmActionへ設定します。
	 */
	public void setUriMaintenanceConfirmAction(
			UriMaintenanceConfirmActionImpl skConfirmAction) {
		this.uriMaintenanceConfirmAction = skConfirmAction;
	}
	/**
	 * LOGIC【会計区分売上修正検索】
	 * @return クラス変数uriMaintenanceKaikeiKbnSearchLogic を戻します。
	 */
	public GetKeigenTaxLogic getUriMaintenanceGetKeigenTaxLogic() {
		return uriMaintenanceGetKeigenTaxLogic;
	}
	/**
	 * LOGIC【会計区分売上修正検索】
	 * @param searchLogic を クラス変数uriMaintenanceKaikeiKbnSearchLogicへ設定します。
	 */
	public void setUriMaintenanceGetKeigenTaxLogic(
			GetKeigenTaxLogic searchLogic) {
		this.uriMaintenanceGetKeigenTaxLogic = searchLogic;
	}
	/**
	 * @return クラス変数throwApplicationException を戻します。
	 */
	public ApplicationException getThrowApplicationException() {
		return throwApplicationException;
	}
	/**
	 * @param throwApplicationException を クラス変数throwApplicationExceptionへ設定します。
	 */
	public void setThrowApplicationException(
			ApplicationException throwApplicationException) {
		this.throwApplicationException = throwApplicationException;
	}
	/**
	 * @return クラス変数targetTabNo を戻します。
	 */
	public String getTargetTabNo() {
		return targetTabNo;
	}
	/**
	 * @param targetTabNo を クラス変数targetTabNoへ設定します。
	 */
	public void setTargetTabNo(String targetTabNo) {
		this.targetTabNo = targetTabNo;
	}

}
