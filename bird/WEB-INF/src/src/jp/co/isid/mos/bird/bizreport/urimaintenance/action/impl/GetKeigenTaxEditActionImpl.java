package jp.co.isid.mos.bird.bizreport.urimaintenance.action.impl;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.bizreport.urimaintenance.action.GetKeigenTaxEditAction;
import jp.co.isid.mos.bird.bizreport.urimaintenance.common.UriMaintenanceCommon;
import jp.co.isid.mos.bird.bizreport.urimaintenance.common.UriMaintenanceConstants;
import jp.co.isid.mos.bird.bizreport.urimaintenance.dto.UriMaintenanceDto;
import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.GetKeigenTaxData;
import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.UIKKbnReviseData;
import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.UIMeisaiKeigenTaxInfo;
import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.UIUriMainteWorkInfo;
import jp.co.isid.mos.bird.bizreport.urimaintenance.logic.GetKeigenTaxLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.BatchProcessingException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.NumericVerifier;

/**
 * 売上修正（軽減税率編集画面）
 *
 * 軽減税率対応です。
 *
 * 作成日:2019/07/09
 * @author USI欒
 *
 */
public class GetKeigenTaxEditActionImpl implements GetKeigenTaxEditAction {

    /* アクションID */
    public static final String revise_ACTION_ID       = "BBR008A60";
    public static final String initialize_ACTION_ID   = "BBR008A61";
    public static final String returnEdit_ACTION_ID   = "BBR008A62";
    public static final String changeTab_ACTION_ID    = "BBR008A63";
    public static final String calculate_ACTION_ID    = "BBR008A64";
    public static final String confirm_ACTION_ID      = "BBR008A65";

    protected static final String VIEW_ID = "BBR008V06";

    /** ACTION【集計区分編集画面】*/
    private UriMaintenanceEditActionImpl uriMaintenanceEditAction;
    /** LOGIC【軽減税率売上修正検索】*/
    private GetKeigenTaxLogic uriMaintenanceGetKeigenTaxLogic;
    /** DTO【Session情報】*/
    private UriMaintenanceDto sessionDto;

    private ApplicationException throwApplicationException;

    private String targetTabNo;
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

	/**
     * 修正処理
     *
     * 集計区分の画面から呼び出される処理です。
     * @see jp.co.isid.mos.bird.bizreport.urimaintenance.action.GetKeigenTaxEditAction#getKeigenTax()
     */
    public String getKeigenTax() {
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
				throw appEx;
			}
			setThrowApplicationException(appEx);
		}
    	//３．会計区分編集画面VIEW_IDをリターン。
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
     * 戻る
     * @return
     */
    public String returnEdit() {
        //ユーザーオペレーション整合性チェック
        if(UriMaintenanceCommon.isUserOperationError(getUriMaintenanceDto())){
            return UriMaintenanceConstants.VIEW_ID_OPERATION_ERR;
        }
        if(sessionDto.getListGetKeigenTaxData().size() != 0) {
	        //１．変更無効として変更前値に戻します。
			for(int t=0; t<sessionDto.getListGetKeigenTaxData().size(); t++) {
				GetKeigenTaxData  eGetKeigenTaxData = (GetKeigenTaxData)sessionDto.getListGetKeigenTaxData().get(t);
				eGetKeigenTaxData.setEigyoDt(eGetKeigenTaxData.getEigyoDt());
				eGetKeigenTaxData.setUriage1(eGetKeigenTaxData.getUriage1());
				eGetKeigenTaxData.setUriage2(eGetKeigenTaxData.getUriage2());
				eGetKeigenTaxData.setUriage3(eGetKeigenTaxData.getUriage3());
				eGetKeigenTaxData.setUriage4(eGetKeigenTaxData.getUriage4());
				eGetKeigenTaxData.setUriage5(eGetKeigenTaxData.getUriage5());
				eGetKeigenTaxData.setTax1(eGetKeigenTaxData.getTax1());
				eGetKeigenTaxData.setTax2(eGetKeigenTaxData.getTax2());
				eGetKeigenTaxData.setTax3(eGetKeigenTaxData.getTax3());
				eGetKeigenTaxData.setTax4(eGetKeigenTaxData.getTax4());
				eGetKeigenTaxData.setTax5(eGetKeigenTaxData.getTax5());

			}//end of for(int t=0; t<listTabResult.size(); t++)
        }
        //２．編集画面VIEW_IDをリターン。
        return UriMaintenanceConstants.VIEWID_EDIT;
    }

    /**
     * 決定
     * @return
     */
    public String decide() {
        //ユーザーオペレーション整合性チェック
        if(UriMaintenanceCommon.isUserOperationError(getUriMaintenanceDto())){
            return UriMaintenanceConstants.VIEW_ID_OPERATION_ERR;
        }
        //１．入力値チェック処理
        checkKaikeiInputData();
        //２．再計算処理
        //calculateKaikei(true);
        //３．変更前値に決定値を上書きします。
    	for(int t=0; t<sessionDto.getListGetKeigenTaxData().size(); t++) {
    		UIUriMainteWorkInfo entity =  (UIUriMainteWorkInfo)getUriMaintenanceDto().getListUri().get(t);
    		GetKeigenTaxData  eGetKeigenTaxData = (GetKeigenTaxData)sessionDto.getListGetKeigenTaxData().get(t);
    		entity.setU01Uriage(eGetKeigenTaxData.getUriage1().add(eGetKeigenTaxData.getUriage2()).add(eGetKeigenTaxData.getUriage3()).add(eGetKeigenTaxData.getUriage4()).add(eGetKeigenTaxData.getUriage5()).toString());
    		entity.setU06Tax(eGetKeigenTaxData.getTax1().add(eGetKeigenTaxData.getTax2()).add(eGetKeigenTaxData.getTax3()).add(eGetKeigenTaxData.getTax4()).add(eGetKeigenTaxData.getTax5()).toString());
    	}
		//５．ACTION【集計区分編集画面】.再計算処理を実行します。
        getUriMaintenanceEditAction().calculateSyukeiKbn();
        //６．売上修正編集画面のVIEW_ID(BBR008V02)をリターン。
        return UriMaintenanceConstants.VIEWID_EDIT;
    }

    /**
     * 再計算
     * @return
     */
    public String calculate() {
        //０．ユーザーオペレーション整合性チェック
        if(UriMaintenanceCommon.isUserOperationError(getUriMaintenanceDto())){
            return UriMaintenanceConstants.VIEW_ID_OPERATION_ERR;
        }
        //１．入力値チェック処理 + 再計算処理
        checkKaikeiInputData();
        //２．再計算処理
        //calculateKaikei(false);
        //３．nullをリターン。
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
        //１．入力値チェック処理
        checkKaikeiInputData();
        //２．DTO【セッション情報】．集計会計区分タブNoへDTO【セッション情報】．対象タブＮｏを設定します。
        getUriMaintenanceDto().setSkTabNo("01");
        //３．nullをリターン。
        return null;
    }
    /**
     * 入力値チェック処理
     *
     */
    private void checkKaikeiInputData() {
		GetKeigenTaxData getKeigenTaxData = new GetKeigenTaxData();
		UIMeisaiKeigenTaxInfo uiMeisaiKeigenTaxInfo = new UIMeisaiKeigenTaxInfo();
		BigDecimal total[] = {new BigDecimal("0"), new BigDecimal("0"),new BigDecimal("0"), new BigDecimal("0"),
				new BigDecimal("0"), new BigDecimal("0"),new BigDecimal("0"), new BigDecimal("0"),
				new BigDecimal("0"), new BigDecimal("0"),new BigDecimal("0"), new BigDecimal("0")};
		//合計行を外(-1)した日数分の入力値をチェックします。
		for(int i=0; i<sessionDto.getListGetKeigenTaxData().size()-1; i++) {
			try {
				//for2-1.現行行Entity[現金在高（日次）売上消費税明細情報]を取得します。
				getKeigenTaxData = (GetKeigenTaxData)sessionDto.getListGetKeigenTaxData().get(i);
				//明細値チェック
				checkNum(getKeigenTaxData.getUriage1(), 7, "売上_通常税率対象", "uriage1", i);
				checkNum(getKeigenTaxData.getUriage2(), 7, "売上_軽減税率対象", "uriage2", i);
				checkNum(getKeigenTaxData.getUriage3(), 7, "売上_予備3", "uriage3", i);
				checkNum(getKeigenTaxData.getUriage4(), 7, "売上_予備4", "uriage4", i);
				checkNum(getKeigenTaxData.getUriage5(), 7, "売上_予備5", "uriage5", i);
				checkNum(getKeigenTaxData.getTax1(), 7, "消費税_通常税率対象", "tax1", i);
				checkNum(getKeigenTaxData.getTax2(), 7, "消費税_軽減税率対象", "tax2", i);
				checkNum(getKeigenTaxData.getTax3(), 7, "消費税_予備3", "tax3", i);
				checkNum(getKeigenTaxData.getTax4(), 7, "消費税_予備4", "tax4", i);
				checkNum(getKeigenTaxData.getTax5(), 7, "消費税_予備5", "tax5", i);
				//毎行目合計値チェック
				checkNum(getKeigenTaxData.getUriage1().add(getKeigenTaxData.getUriage2()).add(getKeigenTaxData.getUriage3()).add(getKeigenTaxData.getUriage4()).add(getKeigenTaxData.getUriage5()), 13 ,"売上_合計","uriage1", i);
				checkNum(getKeigenTaxData.getTax1().add(getKeigenTaxData.getTax2()).add(getKeigenTaxData.getTax3()).add(getKeigenTaxData.getTax4()).add(getKeigenTaxData.getTax5()), 7, "消費税_合計", "sumTax",i);
				//合計行の値を取得
				total[0] = total[0].add(getKeigenTaxData.getUriage1());
				total[1] = total[1].add(getKeigenTaxData.getUriage2());
				total[2] = total[2].add(getKeigenTaxData.getUriage3());
				total[3] = total[3].add(getKeigenTaxData.getUriage4());
				total[4] = total[4].add(getKeigenTaxData.getUriage5());
				total[5] = total[5].add(getKeigenTaxData.getTax1());
				total[6] = total[6].add(getKeigenTaxData.getTax2());
				total[7] = total[7].add(getKeigenTaxData.getTax3());
				total[8] = total[8].add(getKeigenTaxData.getTax4());
				total[9] = total[9].add(getKeigenTaxData.getTax5());
				total[10] = total[10].add(getKeigenTaxData.getUriage1()).add(getKeigenTaxData.getUriage2()).add(getKeigenTaxData.getUriage3()).add(getKeigenTaxData.getUriage4()).add(getKeigenTaxData.getUriage5());
				total[11] = total[11].add(getKeigenTaxData.getTax1()).add(getKeigenTaxData.getTax2()).add(getKeigenTaxData.getTax3()).add(getKeigenTaxData.getTax4()).add(getKeigenTaxData.getTax5());
			}
			catch (GenericMessageException gme) {
				if(getThrowApplicationException() == null) {
					setThrowApplicationException(gme);
				}
			}
		}
		try {
			//合計行の値をチェック
			checkNum(total[0], 7, "売上_通常税率対象_合計", "totalUriage1", 1);
			checkNum(total[1], 7, "売上_軽減税率対象_合計", "totalUriage2", 1);
			checkNum(total[2], 7, "売上_予備3_合計", "totalUriage3", 1);
			checkNum(total[3], 7, "売上_予備4", "totalUriage4", 1);
			checkNum(total[4], 7, "売上_予備5", "totalUriage5", 1);
			checkNum(total[5], 7, "消費税_通常税率対象_合計", "totalTax1", 1);
			checkNum(total[6], 7, "消費税_軽減税率対象_合計", "totalTax2", 1);
			checkNum(total[7], 7, "消費税_予備3_合計", "total1Tax3", 1);
			checkNum(total[8], 7, "消費税_予備4_合計", "total1Tax4", 1);
			checkNum(total[9], 7, "消費税_予備5_合計", "total1Tax5", 1);
			checkNum(total[10], 7, "すべて合計", "totalUriage", 1);
			checkNum(total[11], 7, "すべて合計", "totalTax", 1);
		}
		catch (GenericMessageException gme) {
			if(getThrowApplicationException() == null) {
				setThrowApplicationException(gme);
			}
		}
		if(getThrowApplicationException() == null) {
			uiMeisaiKeigenTaxInfo.setSumUriage1(total[0]);
			uiMeisaiKeigenTaxInfo.setSumUriage2(total[1]);
			uiMeisaiKeigenTaxInfo.setSumUriage3(total[2]);
			uiMeisaiKeigenTaxInfo.setSumUriage4(total[3]);
			uiMeisaiKeigenTaxInfo.setSumUriage5(total[4]);
			uiMeisaiKeigenTaxInfo.setSumTax1(total[5]);
			uiMeisaiKeigenTaxInfo.setSumTax2(total[6]);
			uiMeisaiKeigenTaxInfo.setSumTax3(total[7]);
			uiMeisaiKeigenTaxInfo.setSumTax4(total[8]);
			uiMeisaiKeigenTaxInfo.setSumTax5(total[9]);
			uiMeisaiKeigenTaxInfo.setTotalUriage(total[10]);
			uiMeisaiKeigenTaxInfo.setTotalTax(total[11]);
			sessionDto.setUiMeisaiKeigenTaxInfo(uiMeisaiKeigenTaxInfo);
		}
    }

    /**
     * 整数チェック
     * @param value チェック対象数値
     * @param length 最大桁数
     * @param elm チェック対象名称
     */
    private void checkNumericAndLength(String value, int length, String elm, int index, String msg) {

        //マイナス不許可、桁数指定、小数点以下桁数0
        NumericVerifier numericVerifier = new NumericVerifier(false, length, 0);
        try {
	        if (value!=null && !numericVerifier.validate(value)) {
	            throw new GenericMessageException(msg, elm, index);
	        }
        }
        finally{
        	numericVerifier = null;
        }
    }

    /**
     * 整数チェック
     * @param value チェック対象数値
     * @param length 最大桁数
     * @param elm チェック対象名称
     */
    private void checkNumericAndLength(BigDecimal value, int length, String elm, int index, String msg) {
    	checkNumericAndLength(value.toString(), length, elm, index, msg);
    }
    /**
     * 必須チェック＆正の整数チェック
     * @param value
     * @param length
     * @param strElm
     */
    private void checkNum(BigDecimal value, int length, String strElm, String elm, int index) {
        checkRequire(value, strElm, elm, index);
        checkPositiveNumericAndLength(value.toString(), length, strElm, elm, index);
    }

    /**
     * 合計値チェック
     *
     * @param value
     * @param length
     * @param strElm
     * @param elm
     * @param index
     * @return
     */
    private void checkNumTotal(BigDecimal value, int length, String strElm, String elm, int index) {
    	String msg = "全てのタブの"+strElm+"の合計が"+String.valueOf(length)+"桁以内になるよう入力してください。";
    	checkNumericAndLength(value, length, elm, index, msg);
    }


    /**
     * 必須チェック
     * @param value チェック対象数値
     * @param strElm チェック対象名称
     */
    private void checkRequire(BigDecimal value, String strElm, String elm, int index) {
        if (this.isNullNum(value)) {
            throw new NotNullException(strElm, elm, index);
        }
    }

    // add 2019/07/09 USI欒 #34 begin
	/**
	 * BigDecimal用Null判断処理
	 * @param num
	 * @return boolean true：空かNullの場合
	 */
	private static boolean isNullNum(BigDecimal num) {
		return (num == null) ? true : false;
	}
	// add 2019/07/09 USI欒 #34 end

    /**Num
     * 正の整数チェック
     * @param value チェック対象数値
     * @param length 最大桁数
     * @param strElm チェック対象名称
     */
    private void checkPositiveNumericAndLength(String value, int length, String strElm, String elm, int index) {
    	String msg = strElm+"は正の整数"+String.valueOf(length)+"桁まで入力してください。";
        checkNumericAndLength(value, length, elm, index, msg);
    }

    public UriMaintenanceDto getUriMaintenanceDto() {
        return sessionDto;
    }
    public void setUriMaintenanceDto(UriMaintenanceDto sessionDto) {
        this.sessionDto = sessionDto;
    }
	/**
	 * @return クラス変数uriMaintenanceEditAction を戻します。
	 */
	public UriMaintenanceEditActionImpl getUriMaintenanceEditAction() {
		return uriMaintenanceEditAction;
	}
	/**
	 * @param uriMaintenanceEditAction を クラス変数uriMaintenanceEditActionへ設定します。
	 */
	public void setUriMaintenanceEditAction(
			UriMaintenanceEditActionImpl uriMaintenanceEditAction) {
		this.uriMaintenanceEditAction = uriMaintenanceEditAction;
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
}
