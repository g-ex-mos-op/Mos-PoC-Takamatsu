package jp.co.isid.mos.bird.bizreport.urimaintenance.action.impl;

import java.math.BigDecimal;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity.UIUriMaintenanceResult;
import jp.co.isid.mos.bird.bizreport.urimaintenance.action.KaikeiKbnEditAction;
import jp.co.isid.mos.bird.bizreport.urimaintenance.common.UriMaintenanceCommon;
import jp.co.isid.mos.bird.bizreport.urimaintenance.common.UriMaintenanceConstants;
import jp.co.isid.mos.bird.bizreport.urimaintenance.dto.UriMaintenanceDto;
import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.UIKKbnReviseData;
import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.UIUriMainteWorkInfo;
import jp.co.isid.mos.bird.bizreport.urimaintenance.logic.KaikeiKbnSearchLogic;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.BatchProcessingException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.NumericVerifier;

/**
 * 売上修正（会計区分編集画面）
 * 
 * 会計区分追加対応時(2012/07)に新規追加された機能です。
 * 
 * 作成日:2012/07/27
 * @author xkinu
 *
 */
public class KaikeiKbnEditActionImpl implements KaikeiKbnEditAction {
   
    /* アクションID */
    public static final String revise_ACTION_ID       = "BBR008A40";
    public static final String initialize_ACTION_ID   = "BBR008A41";
    public static final String returnEdit_ACTION_ID   = "BBR008A42";
    public static final String changeTab_ACTION_ID    = "BBR008A43";
    public static final String calculate_ACTION_ID    = "BBR008A44";
    public static final String confirm_ACTION_ID      = "BBR008A45";
    
    protected static final String VIEW_ID = "BBR008V04";
    
    /** ACTION【集計区分編集画面】*/
    private UriMaintenanceEditActionImpl uriMaintenanceEditAction;
    /** LOGIC【会計区分売上修正検索】*/
    private KaikeiKbnSearchLogic uriMaintenanceKaikeiKbnSearchLogic;
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
     * @see jp.co.isid.mos.bird.bizreport.urimaintenance.action.MeisaiEditAction#revise()
     */
    public String revise() {
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
        if(sessionDto.getSyukeiKbnResultData() != null) {
	        //１．変更無効として変更前値に戻します。
	        List listTabResult = sessionDto.getSyukeiKbnResultData().getListTabResult();
			for(int t=0; t<listTabResult.size(); t++) {	
				UIUriMaintenanceResult uiTabResult = (UIUriMaintenanceResult)listTabResult.get(t);
				for(int d=0; d<uiTabResult.getListData().size(); d++) {
					//修正前の値と同じ値を設定する。
					UIKKbnReviseData eRevising = (UIKKbnReviseData)uiTabResult.getListData().get(d);
					eRevising.setReviseKen1(eRevising.getBd31Ken1());
					eRevising.setReviseKin1(eRevising.getBd31Kin1());
					eRevising.setReviseKen2(eRevising.getBd31Ken2());
					eRevising.setReviseKin2(eRevising.getBd31Kin2());
					eRevising.setReviseKen3(eRevising.getBd31Ken3());
					eRevising.setReviseKin3(eRevising.getBd31Kin3());
					eRevising.setReviseKen4(eRevising.getBd31Ken4());
					eRevising.setReviseKin4(eRevising.getBd31Kin4());
					eRevising.setStrReviseKen1(eRevising.getBd31Ken1().toString());
					eRevising.setStrReviseKin1(eRevising.getBd31Kin1().toString());
					if(eRevising.isExistsMeisai2()){
						eRevising.setStrReviseKen2(eRevising.getBd31Ken2().toString());
						eRevising.setStrReviseKin2(eRevising.getBd31Kin2().toString());
					}
					if(eRevising.isExistsMeisai3()){
						eRevising.setStrReviseKen3(eRevising.getBd31Ken3().toString());
						eRevising.setStrReviseKin3(eRevising.getBd31Kin3().toString());
					}
					if(eRevising.isExistsMeisai4()){
						eRevising.setStrReviseKen4(eRevising.getBd31Ken4().toString());
						eRevising.setStrReviseKin4(eRevising.getBd31Kin4().toString());
					}
					eRevising.setReviseKenTotal(eRevising.getBd31KenTotal());
					eRevising.setReviseKinTotal(eRevising.getBd31KinTotal());
				}//end of for(int d=0; d<uiTabResult.getListData().size(); d++)
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
        calculateKaikei(true);
        //３．変更前値に決定値を上書きします。
        List listTabResult = sessionDto.getSyukeiKbnResultData().getListTabResult();
        for (int t=0; t<listTabResult.size(); t++) {
        	UIUriMaintenanceResult uiTabResult = (UIUriMaintenanceResult)listTabResult.get(t);
	        for (int i=0; i<uiTabResult.getListData().size(); i++) {
	        	UIKKbnReviseData eRevising = (UIKKbnReviseData)uiTabResult.getListData().get(i);
	        	eRevising.setBd31Ken1(eRevising.getReviseKen1());
	        	eRevising.setBd31Kin1(eRevising.getReviseKin1());
	        	eRevising.setBd31Ken2(eRevising.getReviseKen2());
	        	eRevising.setBd31Kin2(eRevising.getReviseKin2());
	        	eRevising.setBd31Ken3(eRevising.getReviseKen3());
	        	eRevising.setBd31Kin3(eRevising.getReviseKin3());
	        	eRevising.setBd31Ken4(eRevising.getReviseKen4());
	        	eRevising.setBd31Kin4(eRevising.getReviseKin4());
	        	eRevising.setBd31KenTotal(eRevising.getReviseKenTotal());
	        	eRevising.setBd31KinTotal(eRevising.getReviseKinTotal());
	        }
        }
		//４．編集対象の集計区分情報のデータへ変更後の値を上書きします。
		for(int s=0; s<getUriMaintenanceDto().getListUri().size(); s++) {
			UIUriMainteWorkInfo entity = (UIUriMainteWorkInfo)getUriMaintenanceDto().getListUri().get(s);
			UIKKbnReviseData eRevising = (UIKKbnReviseData)sessionDto.getResultData().getListData().get(s);
			String syukeiKbnKen = (eRevising.getReviseKenTotal()).toString();
			String syukeiKbnKin = (eRevising.getReviseKinTotal()).toString();
			if("02".equals(getUriMaintenanceDto().getSyukeiKbn())) {
				entity.setU15KaikeiKen2(syukeiKbnKen);
				entity.setU16KaikeiKin2(syukeiKbnKin);
			}
			if("03".equals(getUriMaintenanceDto().getSyukeiKbn())) {
				entity.setU17KaikeiKen3(syukeiKbnKen);
				entity.setU18KaikeiKin3(syukeiKbnKin);
			}
			if("04".equals(getUriMaintenanceDto().getSyukeiKbn())) {
				entity.setU19KaikeiKen4(syukeiKbnKen);
				entity.setU20KaikeiKin4(syukeiKbnKin);
			}
			if("05".equals(getUriMaintenanceDto().getSyukeiKbn())) {
				entity.setU21KaikeiKen5(syukeiKbnKen);
				entity.setU22KaikeiKin5(syukeiKbnKin);
			}
			if("06".equals(getUriMaintenanceDto().getSyukeiKbn())) {
				entity.setU23KaikeiKen6(syukeiKbnKen);
				entity.setU24KaikeiKin6(syukeiKbnKin);
			}
			if("07".equals(getUriMaintenanceDto().getSyukeiKbn())) {
				entity.setU25KaikeiKen7(syukeiKbnKen);
				entity.setU26KaikeiKin7(syukeiKbnKin);
			}
			if("08".equals(getUriMaintenanceDto().getSyukeiKbn())) {
				entity.setU27KaikeiKen8(syukeiKbnKen);
				entity.setU28KaikeiKin8(syukeiKbnKin);
			}
			if("09".equals(getUriMaintenanceDto().getSyukeiKbn())) {
				entity.setU29KaikeiKen9(syukeiKbnKen);
				entity.setU30KaikeiKin9(syukeiKbnKin);
			}
			if("10".equals(getUriMaintenanceDto().getSyukeiKbn())) {
				entity.setU31KaikeiKen10(syukeiKbnKen);
				entity.setU32KaikeiKin10(syukeiKbnKin);
			}
			if("11".equals(getUriMaintenanceDto().getSyukeiKbn())) {
				entity.setU33KaikeiKen11(syukeiKbnKen);
				entity.setU34KaikeiKin11(syukeiKbnKin);
			}
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
        //１．入力値チェック処理
        checkKaikeiInputData();
        //２．再計算処理
        calculateKaikei(false);
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
        //２．再計算処理
        calculateKaikei(false);
        //３．DTO【セッション情報】．集計会計区分タブNoへDTO【セッション情報】．対象タブＮｏを設定します。
        getUriMaintenanceDto().setSkTabNo(getTargetTabNo());
        //４．nullをリターン。
        return null;
    }
    /**
     * 入力値チェック処理
     *
     */
    private void checkKaikeiInputData() {
		BigDecimal total[][] = {{new BigDecimal("0"), new BigDecimal("0")}
		, {new BigDecimal("0"), new BigDecimal("0")}
		, {new BigDecimal("0"), new BigDecimal("0")}
		, {new BigDecimal("0"), new BigDecimal("0")}
		};
		
		UIUriMaintenanceResult resultData = sessionDto.getResultData();
		//合計行を外(-1)した日数分の入力値をチェックします。
		for(int i=0; i<resultData.getListData().size()-1; i++) {
			try {
				//for2-1.現行行Entity[会計区分修正状況]を取得します。
				UIKKbnReviseData eRevising = (UIKKbnReviseData)resultData.getListData().get(i);
				if(eRevising.isExistsMeisai1()) {
					//変数.合計件数1　＝ 変数.合計件数　＋件数1修正値
					checkNum(eRevising.getStrReviseKen1(), 4, "件数", "reviseKen1", i);
					total[0][0] = total[0][0].add(eRevising.getReviseKen1());
					//変数.合計金額1　＝ 変数.合計金額　＋金額1修正値
					checkNum(eRevising.getStrReviseKin1(), 7, "金額", "reviseKin1", i);
					total[0][1] = total[0][1].add(eRevising.getReviseKin1());
				}
				if(eRevising.isExistsMeisai2()) {
					//変数.合計件数1　＝ 変数.合計件数　＋件数2修正値
					checkNum(eRevising.getStrReviseKen2(), 4, "件数", "reviseKen2", i);
					total[1][0] = total[1][0].add(eRevising.getReviseKen2());
					//変数.合計金額1　＝ 変数.合計金額　＋金額2修正値
					checkNum(eRevising.getStrReviseKin2(), 7, "金額", "reviseKin2", i);
					total[1][1] = total[1][1].add(eRevising.getReviseKin2());
				}
				if(eRevising.isExistsMeisai3()) {
					//変数.合計件数1　＝ 変数.合計件数　＋件数3修正値
					checkNum(eRevising.getStrReviseKen3(), 4, "件数", "reviseKen3", i);
					total[2][0] = total[2][0].add(eRevising.getReviseKen3());
					//変数.合計金額1　＝ 変数.合計金額　＋金額3修正値
					checkNum(eRevising.getStrReviseKin3(), 7, "金額", "reviseKin3", i);
					total[2][1] = total[2][1].add(eRevising.getReviseKin3());
				}
				if(eRevising.isExistsMeisai4()) {
					//変数.合計件数1　＝ 変数.合計件数　＋件数4修正値
					checkNum(eRevising.getStrReviseKen4(), 4, "件数", "reviseKen4", i);
					total[3][0] = total[3][0].add(eRevising.getReviseKen4());
					//変数.合計金額1　＝ 変数.合計金額　＋金額4修正値
					checkNum(eRevising.getStrReviseKin4(), 7, "金額", "reviseKin4", i);
					total[3][1] = total[3][1].add(eRevising.getReviseKin4());
				}
			}
			catch (GenericMessageException gme) {
				if(getThrowApplicationException() == null) {
					setThrowApplicationException(gme);
				}
			}
		}
		if(getThrowApplicationException() == null) {
			resultData.getReviseTotal().setReviseKen1(total[0][0]);
			resultData.getReviseTotal().setReviseKen2(total[1][0]);
			resultData.getReviseTotal().setReviseKen3(total[2][0]);
			resultData.getReviseTotal().setReviseKen4(total[3][0]);
			resultData.getReviseTotal().setReviseKin1(total[0][1]);
			resultData.getReviseTotal().setReviseKin2(total[1][1]);
			resultData.getReviseTotal().setReviseKin3(total[2][1]);
			resultData.getReviseTotal().setReviseKin4(total[3][1]);
		}
    }
    /**
     * 再計算処理
     *
     */
    private void calculateKaikei(boolean isDoki) {
		//1.売上修正共通【静的処理クラス】.メソッド【日別合計値取得】を実行します。
        UriMaintenanceCommon.calculateDayTotal(sessionDto.getSyukeiKbnResultData().getListTabResult(), isDoki);
		if(getThrowApplicationException() == null) {
			for(int i=0; i<sessionDto.getResultData().getListData().size()-1; i++) {
				DateFormatter dateFormatter = new DateFormatter(DateFormatter.DATE_TYPE_YMD, "dd'日('E')'");
				UIKKbnReviseData eRevising = (UIKKbnReviseData)sessionDto.getResultData().getListData().get(i);
				String fmEigyoDt = dateFormatter.format(eRevising.getEigyoDt(), true);
				checkNumTotal(eRevising.getReviseKenTotal(), 4, "営業日"+fmEigyoDt+"の件数", "reviseKen1", i);
				checkNumTotal(eRevising.getReviseKinTotal(), 7, "営業日"+fmEigyoDt+"の金額", "reviseKin1", i);
			}
		}
		else {
			ApplicationException ex = getThrowApplicationException();
			setThrowApplicationException(null);
			throw ex;
		}		
    }
    /**
     * 整数チェック
     * @param value チェック対象数値
     * @param length 最大桁数
     * @param strElm チェック対象名称
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
     * @param strElm チェック対象名称
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
    private void checkNum(String value, int length, String strElm, String elm, int index) {
        checkRequire(value, strElm, elm, index);
        checkPositiveNumericAndLength(value, length, strElm, elm, index);
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
    private void checkRequire(String value, String strElm, String elm, int index) {
        if (CommonUtil.isNull(value)) {
            throw new NotNullException(strElm, elm, index);
        }
    }

    /**
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
}
