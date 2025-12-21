package jp.co.isid.mos.bird.bizreport.urimaintenance.action.impl;

import jp.co.isid.mos.bird.bizreport.urimaintenance.action.KaikeiKbnConfirmAction;
import jp.co.isid.mos.bird.bizreport.urimaintenance.common.UriMaintenanceCommon;
import jp.co.isid.mos.bird.bizreport.urimaintenance.common.UriMaintenanceConstants;
import jp.co.isid.mos.bird.bizreport.urimaintenance.dto.UriMaintenanceDto;
import jp.co.isid.mos.bird.bizreport.urimaintenance.logic.KaikeiKbnSearchLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.BatchProcessingException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;

/**
 * 売上修正（会計区分確認画面）
 * 
 * 会計区分追加対応時(2012/07)に新規追加された機能です。
 * 作成日:2012/07/27
 * @author xkinu
 *
 */
public class KaikeiKbnConfirmActionImpl implements KaikeiKbnConfirmAction {
   
    /* アクションID */
    public static final String view_ACTION_ID         = "BBR008A50";
    public static final String initialize_ACTION_ID   = "BBR008A51";
    public static final String changeTab_ACTION_ID    = "BBR008A52";
    public static final String back_ACTION_ID         = "BBR008A53";
    
    protected static final String VIEW_ID = "BBR008V05";
    /** ACTION【集計区分確認画面】*/
    private UriMaintenanceConfirmActionImpl uriMaintenanceConfirmAction;
    /** DTO【Session情報】 */
    private UriMaintenanceDto sessionDto;
    /** LOGIC【会計区分売上修正検索】*/
    private KaikeiKbnSearchLogic uriMaintenanceKaikeiKbnSearchLogic;
    
    private ApplicationException throwApplicationException;
    private String targetTabNo;

    
    /**
     * 明細
     * 
     * 集計区分の画面から呼び出される処理です。
     * @see jp.co.isid.mos.bird.bizreport.urimaintenance.action.KaikeiKbnConfirmAction#view()
     */
    public String view() {
        //０．ユーザーオペレーション整合性チェック
        if(UriMaintenanceCommon.isUserOperationError(getUriMaintenanceDto())){
            return UriMaintenanceConstants.VIEW_ID_OPERATION_ERR;          
        }
        try {
        	//１．DTO【Session情報】.Map[集計区分別会計区分結果情報]が空の場合、
        	if(sessionDto.getMapSKbnResultData().isEmpty()) {
	        	//LOGIC【会計区分売上修正検索】を実行する。
	        	getUriMaintenanceKaikeiKbnSearchLogic().execute(sessionDto);
        	}
        	//２．DTO【Session情報】.Map[集計区分別会計区分マスタ情報]に
        	//    DTO【Session情報】.集計区分がキーとして存在しない場合、下記のExceptionを発生させる。
        	if(!sessionDto.getMapSyukeiKbnMstData().containsKey(sessionDto.getSyukeiKbn())) {
        		throw new NoResultException();
        	}
        	//３．DTO【Session情報】.[集計区分結果情報保持].タブ件数が0(ゼロ)件の場合、下記のExceptionを発生させる。
        	if(!sessionDto.getSyukeiKbnResultData().isExist()) {
        		//Exception:NoResultException();
        		throw new NoResultException();
        	}
        	//４．DTO【Session情報】.集計区分タブNoへデフォルト値("01")を設定する。
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
        //１．DTO【セッション情報】．集計会計区分タブNoへDTO【セッション情報】．対象タブＮｏを設定します。
        getUriMaintenanceDto().setSkTabNo(getTargetTabNo());
        //３．nullをリターン。
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
	public KaikeiKbnSearchLogic getUriMaintenanceKaikeiKbnSearchLogic() {
		return uriMaintenanceKaikeiKbnSearchLogic;
	}
	/**
	 * LOGIC【会計区分売上修正検索】
	 * @param searchLogic を クラス変数uriMaintenanceKaikeiKbnSearchLogicへ設定します。
	 */
	public void setUriMaintenanceKaikeiKbnSearchLogic(
			KaikeiKbnSearchLogic searchLogic) {
		this.uriMaintenanceKaikeiKbnSearchLogic = searchLogic;
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
