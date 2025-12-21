/*
 * 作成日: 2006/12/26
 */
package jp.co.isid.mos.bird.entry.longserviceoffer.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.entry.longserviceoffer.common.LongserviceOfferCommon;
import jp.co.isid.mos.bird.entry.longserviceoffer.common.LongserviceOfferConstants;
import jp.co.isid.mos.bird.entry.longserviceoffer.dao.UIOfferMstDao;
import jp.co.isid.mos.bird.entry.longserviceoffer.dto.LongserviceOfferDto;
import jp.co.isid.mos.bird.entry.longserviceoffer.entity.UIOfferEntry;
import jp.co.isid.mos.bird.entry.longserviceoffer.entity.UIOfferMst;
import jp.co.isid.mos.bird.entry.longserviceoffer.logic.CheckOfferLogic;
import jp.co.isid.mos.bird.entry.longserviceoffer.util.verifier.HankakuDaiEijiVerifier;
import jp.co.isid.mos.bird.entry.nationalentry.util.NationalEntryUtil;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotRelevantException;
import jp.co.isid.mos.bird.framework.util.DateUtils;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.NumericVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.PhoneVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.ZenkakuVerifier;

/**
 * 登録内容チェック
 *
 * @author narita
 * 更新日 2010/04/23 xkinu 店舗経験プルダウン値追加対応 25年・30年を追加対応
 */
public class CheckOfferLogicImpl implements CheckOfferLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN018L01";

    /**
     * エントリーマスタ管理（UIOfferMstDao）
     */
    private UIOfferMstDao uIOfferMstDao;
    /**
     * 事前処理
     *
     * @param dto
     * @author xkinu
     * 作成日:2010/04/23
     */
    private void validate(LongserviceOfferDto dto) {
        if (dto == null) {
            throw new NotNullException("永年勤続申請情報");
        }
        if (dto.getUIOfferMst() == null) {
            throw new NotNullException("永年勤続申請　責任者情報");
        }
        if (dto.getUIOfferEntryList() == null) {
            throw new NotNullException("永年勤続申請　申請者情報");
        }
    }
	/**
     * 登録内容のチェックを行う
     *
     * @param longserviceOfferDto
     * @param sysDate
     *
     * 更新日 2010/04/23 xkinu 店舗経験プルダウン値追加対応 25年・30年を追加対応
     * */
    public void execute(LongserviceOfferDto dto,String sysDate,int mode) {
    	//事前処理
    	validate(dto);

        UIOfferMst uIOfferMst = dto.getUIOfferMst();
        List entryList = dto.getUIOfferEntryList();
        dto.setErrFlg(true);

        // キー情報のnullチェック
        checkKeyData(dto);
        // 申込責任者　入力チェック
        checkMstValidity(uIOfferMst);
        // 申請者　入力チェック
        checkEntryValidity(entryList,sysDate);

    	// 登録チェックのためにカウントを検索
    	int mstCount = getUIOfferMstDao().getOfferMstCount(
    									uIOfferMst.getEntryCd(),
							    		uIOfferMst.getEntryYear(),
							    		uIOfferMst.getEntryKai(),
							    		uIOfferMst.getCompanyCd(),
							    		uIOfferMst.getOnerCd(),
							    		uIOfferMst.getAtesakiKbn());
    	// 申込責任者カウントのセット
    	dto.setMstCount(mstCount);
        // 申請者が全てクリアされているかチェックする
        dto.setEntryCleaFlg( checkEntryClearData(entryList) );

    	// 申込責任者情報がクリアの場合はエラーメッセージを表示する。
		if(uIOfferMst.isCleaFlg()){
			throw new NotNullException("申込責任者", LongserviceOfferConstants.FOCUS_NAME, 0);
		}
		// 申請者が登録されていない状態で「登録・終了」ボタンを押した場合、エラーメッセージを表示する。
		if(dto.getEditMode() == LongserviceOfferConstants.EDIT_MODE_UPDATE &&
				mstCount == 0 && dto.isEntryCleaFlg()){
			throw new NoInputException("申請者", LongserviceOfferConstants.FOCUS_MISE, 0);
		}

		dto.setErrFlg(false);
    }

    /**
     * 申込責任者　入力チェック
     * @param uIOfferMst
     *
     * @update 2010/04/23 xkinu 店舗経験プルダウン値追加対応 25年・30年を追加
     */
    private UIOfferMst checkMstValidity(UIOfferMst uIOfferMst) {

    	ZenkakuVerifier zenkakuVerifier = new ZenkakuVerifier();
    	PhoneVerifier phoneVerifier = new PhoneVerifier( true );

    	if (LongserviceOfferCommon.isNull(uIOfferMst.getName()) &&
        		LongserviceOfferCommon.isNull(uIOfferMst.getSoufuName()) &&
        		LongserviceOfferCommon.isNull(uIOfferMst.getTel()) &&
        		LongserviceOfferCommon.isNull(uIOfferMst.getJobType())) {

    		// クリア情報と判断する
        	uIOfferMst.setCleaFlg(true);

    	}else{

    		// 必須入力チェック

	    	// 申込責任者
	        if (LongserviceOfferCommon.isNull(uIOfferMst.getName())){
	            throw new NotNullException("申込責任者", LongserviceOfferConstants.FOCUS_NAME, 0);
	        }
	        // 申込担当
	        if (LongserviceOfferCommon.isNull(uIOfferMst.getSoufuName())){
	            throw new NotNullException("申込担当者", LongserviceOfferConstants.FOCUS_SOUFU_NAME, 0);
	        }
	        // 電話番号
	        if (LongserviceOfferCommon.isNull(uIOfferMst.getTel())) {
	            throw new NotNullException("電話番号", LongserviceOfferConstants.FOCUS_TEL, 0);
	        }

	        // レングスチェック・文字チェック

	        // 申込責任者 レングスチェック及び全角チェック
	        if (LongserviceOfferCommon.isLengthOver(uIOfferMst.getName(), 40)){
	            throw new NotRelevantException("申込責任者", "全角２０文字以内", LongserviceOfferConstants.FOCUS_NAME, 0);
	        }
	        if (!zenkakuVerifier.validate(uIOfferMst.getName())) {
	        	InvalidInputException invalidInputEx = new InvalidInputException("申込責任者"
	        			, "入力された”"+uIOfferMst.getName()+"”の中に登録できない文字が含まれています。");
	        	invalidInputEx.setHtmlElementIndex(0);
	        	invalidInputEx.setHtmlElementName(LongserviceOfferConstants.FOCUS_NAME);
	        	throw invalidInputEx;
	        }
	        // 申込担当 レングスチェック及び全角チェック
	        if (LongserviceOfferCommon.isLengthOver(uIOfferMst.getSoufuName(), 40)){
	            throw new NotRelevantException("申込担当者", "全角２０文字以内", LongserviceOfferConstants.FOCUS_SOUFU_NAME, 0);
	        }
	        if (!zenkakuVerifier.validate(uIOfferMst.getSoufuName())) {
	        	InvalidInputException invalidInputEx = new InvalidInputException("申込担当者"
	        			, "入力された”"+uIOfferMst.getSoufuName()+"”の中に登録できない文字が含まれています。");
	        	invalidInputEx.setHtmlElementIndex(0);
	        	invalidInputEx.setHtmlElementName(LongserviceOfferConstants.FOCUS_SOUFU_NAME);
	        	throw invalidInputEx;
	        }
	        // 電話番号 レングスチェック及び日付妥当性チェック
	        if (LongserviceOfferCommon.isLengthOver(uIOfferMst.getTel(), 15) ||
	        		!phoneVerifier.validate(uIOfferMst.getTel()) ){
	            throw new NotRelevantException("電話番号", "半角１５文字以内", LongserviceOfferConstants.FOCUS_TEL, 0);
	        }
	        // 役職 レングスチェック及び全角チェック
	        if (!LongserviceOfferCommon.isNull(uIOfferMst.getJobType())) {
		        if (LongserviceOfferCommon.isLengthOver(uIOfferMst.getJobType(), 20) ||
		        		!zenkakuVerifier.validate(uIOfferMst.getJobType()) ){
		            throw new NotRelevantException("役職", "全角１０文字以内", LongserviceOfferConstants.FOCUS_JOB_TYPE, 0);
		        }
	        }else{
	        	// nullの場合は、ブランクをセットする
	        	uIOfferMst.setJobType(LongserviceOfferConstants.EMPTY);
	        }

	        // 有効情報と判断する
        	uIOfferMst.setCleaFlg(false);
        	// null項目にブランクをセットする
        	uIOfferMst.setAddress1(LongserviceOfferConstants.EMPTY);
        	uIOfferMst.setAddress2(LongserviceOfferConstants.EMPTY);
        	uIOfferMst.setAddress3(LongserviceOfferConstants.EMPTY);
        	uIOfferMst.setZip(LongserviceOfferConstants.EMPTY);
        }

        return uIOfferMst;
    }

    /**
     * 申請者　入力チェック
     * @param longserviceOfferDto
     */
    private List checkEntryValidity(List entryList,String sysDate) {

    	ZenkakuVerifier zenkakuVerifier = new ZenkakuVerifier();
    	HankakuDaiEijiVerifier hankakuDaiEijiVerifier = new HankakuDaiEijiVerifier();
        NumericVerifier numericVerifier = new NumericVerifier();
        DateVerifier dateVrifier = new DateVerifier(DateVerifier.DATE_TYPE_YMD);

        for(int i = 0;i < entryList.size(); i++){
            int index = 1;
            index = index + i;
        	UIOfferEntry uIOfferEntry = (UIOfferEntry)entryList.get(i);
	        //店舗経験ラジオボタン値(社員orＰ／Ａ)
	        String staffType= uIOfferEntry.getStaffType();
        	if ( (LongserviceOfferCommon.isNull(uIOfferEntry.getMiseCd()) ||
        			uIOfferEntry.getMiseCd().equals(LongserviceOfferConstants.ZERO) ) &&
        		LongserviceOfferCommon.isNull(uIOfferEntry.getLNameKj()) &&
        		LongserviceOfferCommon.isNull(uIOfferEntry.getFNameKj()) &&
        		LongserviceOfferCommon.isNull(uIOfferEntry.getLNameRm()) &&
        		LongserviceOfferCommon.isNull(uIOfferEntry.getFNameRm()) &&
        		LongserviceOfferCommon.isNull(uIOfferEntry.getBirthday_Year()) &&
        		LongserviceOfferCommon.isNull(uIOfferEntry.getBirthday_Month()) &&
        		LongserviceOfferCommon.isNull(uIOfferEntry.getBirthday_Day()) &&
        		LongserviceOfferCommon.isNull(uIOfferEntry.getAge()) &&
        		LongserviceOfferCommon.isNull(uIOfferEntry.getEntryDate_Year()) &&
        		LongserviceOfferCommon.isNull(uIOfferEntry.getEntryDate_Month()) &&
        		LongserviceOfferCommon.isNull(uIOfferEntry.getEntryDate_Day()) &&
        		CommonUtil.isNull(staffType) &&
        		CommonUtil.isNull(uIOfferEntry.getExpKbn()) &&
        		LongserviceOfferCommon.isNull(uIOfferEntry.getSex())) {

        		// クリア情報と判断する
        		uIOfferEntry.setCleaFlg(true);
        	}else{
        		// 必須入力チェック

        		// 店
        		if (LongserviceOfferCommon.isNull(uIOfferEntry.getMiseCd()) ||
        				uIOfferEntry.getMiseCd().equals(LongserviceOfferConstants.ZERO)) {
		            throw new NotNullException(index +  "：店舗", LongserviceOfferConstants.FOCUS_MISE, i);
		        }
        		// 氏：漢字
        		if (LongserviceOfferCommon.isNull(uIOfferEntry.getLNameKj())) {
		            throw new NotNullException(index +  "：氏：漢字", LongserviceOfferConstants.FOCUS_L_NAME_KJ, i);
		        }
		        // 名：漢字
		        if (LongserviceOfferCommon.isNull(uIOfferEntry.getFNameKj())) {
		            throw new NotNullException(index +  "：名：漢字", LongserviceOfferConstants.FOCUS_F_NAME_KJ, i);
		        }
		        // 氏：ローマ字
		        if (LongserviceOfferCommon.isNull(uIOfferEntry.getLNameRm())) {
		            throw new NotNullException(index +  "：氏：ローマ字", LongserviceOfferConstants.FOCUS_L_NAME_RM, i);
		        }
		        // 名：ローマ字
		        if (LongserviceOfferCommon.isNull(uIOfferEntry.getFNameRm())) {
		            throw new NotNullException(index +  "：名：ローマ字", LongserviceOfferConstants.FOCUS_F_NAME_RM, i);
		        }
		        // 性別
		        if (LongserviceOfferCommon.isNull(uIOfferEntry.getSex())) {
		            throw new NotNullException(index +  "：性別", LongserviceOfferConstants.FOCUS_SEX, i);
		        }
		        // 生年月日:年
		        if (LongserviceOfferCommon.isNull(uIOfferEntry.getBirthday_Year())) {
		            throw new NotNullException(index +  "：生年月日:年", LongserviceOfferConstants.FOCUS_BIRTHDAY_YEAR, i);
		        }
		        // 生年月日:月
		        if (LongserviceOfferCommon.isNull(uIOfferEntry.getBirthday_Month())) {
		            throw new NotNullException(index +  "：生年月日:月", LongserviceOfferConstants.FOCUS_BIRTHDAY_MONTH, i);
		        }
		        // 生年月日:日
		        if (LongserviceOfferCommon.isNull(uIOfferEntry.getBirthday_Day())) {
		            throw new NotNullException(index +  "：生年月日:日", LongserviceOfferConstants.FOCUS_BIRTHDAY_DAY, i);
		        }
		        // 年齢
		        if (LongserviceOfferCommon.isNull(uIOfferEntry.getAge())) {
		            throw new NotNullException(index +  "：年齢", LongserviceOfferConstants.FOCUS_AGE, i);
		        }
		        // 入社年月日：年
		        if (LongserviceOfferCommon.isNull(uIOfferEntry.getEntryDate_Year())) {
		            throw new NotNullException(index +  "：入社年月日：年", LongserviceOfferConstants.FOCUS_ENTRY_YEAR, i);
		        }
		        // 入社年月日：月
		        if (LongserviceOfferCommon.isNull(uIOfferEntry.getEntryDate_Month())) {
		            throw new NotNullException(index +  "：入社年月日：月", LongserviceOfferConstants.FOCUS_ENTRY_MONTH, i);
		        }
		        // 入社年月日：日
		        if (LongserviceOfferCommon.isNull(uIOfferEntry.getEntryDate_Day())) {
		            throw new NotNullException(index +  "：入社年月日：日", LongserviceOfferConstants.FOCUS_ENTRY_DAY, i);
		        }
		        if( CommonUtil.isNull(staffType) ) {
	        		//店舗経験のラジオボタン社員暦へフォーカスを当てます。
		        	throw new NotNullException(index +  "：店舗経験", "years"+String.valueOf(i), 0);
		        }
		        //店舗経験年数
		        String expYears = !CommonUtil.isNull(uIOfferEntry.getExpKbn())?uIOfferEntry.getExpKbn().substring(1):"";
	        	//店舗経験 必須チェック
		        if (CommonUtil.isNull(expYears)) {
		        	if(LongserviceOfferConstants.JOB_TYPE_EMPLOYEE.equals(staffType)) {
			        	throw new NotNullException(index +  "：店舗経験", "employeeYears", i);
		        	}
		        	if(LongserviceOfferConstants.JOB_TYPE_PARTTIMER.equals(staffType)) {
			        	throw new NotNullException(index +  "：店舗経験", "parttimerYears", i);
		        	}
		        }

		        // レングスチェック・文字チェック

		        // 氏名(氏)全角チェック
		        if(!zenkakuVerifier.validate(uIOfferEntry.getLNameKj())) {
		        	InvalidInputException invalidInputEx = new InvalidInputException(index +  "：氏名(氏)"
		        			, "入力された”"+uIOfferEntry.getLNameKj()+"”の中に登録できない文字が含まれています。");
                    index = index -1;
		        	invalidInputEx.setHtmlElementIndex(index);
		        	invalidInputEx.setHtmlElementName(LongserviceOfferConstants.FOCUS_L_NAME_KJ);
		        	throw invalidInputEx;
		        }
		        //氏名(氏)文字数チェック
		        if (LongserviceOfferCommon.isLengthOver(uIOfferEntry.getLNameKj(), 40)){
		            throw new NotRelevantException(index + "：氏名(氏)", "全角２０文字以内", LongserviceOfferConstants.FOCUS_L_NAME_KJ, i);
		        }
		        //氏名(名)全角チェック
		        if(!zenkakuVerifier.validate(uIOfferEntry.getFNameKj())) {
		        	InvalidInputException invalidInputEx = new InvalidInputException(index + "：氏名(名)"
		        			, "入力された”"+uIOfferEntry.getFNameKj()+"”の中に登録できない文字が含まれています。");
                    index = index -1;
		        	invalidInputEx.setHtmlElementIndex(index);
		        	invalidInputEx.setHtmlElementName(LongserviceOfferConstants.FOCUS_F_NAME_KJ);
		        	throw invalidInputEx;
		        }
		        //氏名(名)レングスチェック
		        if (LongserviceOfferCommon.isLengthOver(uIOfferEntry.getFNameKj(), 40)){
		            throw new NotRelevantException(index + "：氏名(名)", "全角２０文字以内", LongserviceOfferConstants.FOCUS_F_NAME_KJ, i);
		        }
		        // 氏：ローマ字 レングスチェック及び半角英字チェック
		        if (LongserviceOfferCommon.isLengthOver(uIOfferEntry.getLNameRm(), 30) ||
		        		!hankakuDaiEijiVerifier.validate(uIOfferEntry.getLNameRm()) ){
		            throw new NotRelevantException(index + "：ローマ字（氏）", "半角大文字３０文字以内", LongserviceOfferConstants.FOCUS_L_NAME_RM, i);
		        }
		        // 名：ローマ字 レングスチェック及び半角英字チェック
		        if (LongserviceOfferCommon.isLengthOver(uIOfferEntry.getFNameRm(), 30) ||
		        		!hankakuDaiEijiVerifier.validate(uIOfferEntry.getFNameRm()) ){
		            throw new NotRelevantException(index + "：ローマ字（名）", "半角大文字３０文字以内", LongserviceOfferConstants.FOCUS_F_NAME_RM, i);
		        }
		        // 生年月日：年 レングスチェック及び半角数値チェック
		        if (uIOfferEntry.getBirthday_Year().length() != 4 ||
		        		!numericVerifier.validate(uIOfferEntry.getBirthday_Year()) ){
		            throw new NotRelevantException(index + "：生年月日：年", "半角数値４文字", LongserviceOfferConstants.FOCUS_BIRTHDAY_YEAR, i);
		        }
		        // 生年月日：月 レングスチェック及び半角数値チェック
		        if (LongserviceOfferCommon.isLengthOver(uIOfferEntry.getBirthday_Month(), 2) ||
		        		!numericVerifier.validate(uIOfferEntry.getBirthday_Month()) ){
		            throw new NotRelevantException(index + "：生年月日：月", "半角数値２文字以内", LongserviceOfferConstants.FOCUS_BIRTHDAY_MONTH, i);
		        }
		        // 生年月日：日 レングスチェック及び半角数値チェック
		        if (LongserviceOfferCommon.isLengthOver(uIOfferEntry.getBirthday_Day(), 2) ||
		        		!numericVerifier.validate(uIOfferEntry.getBirthday_Day()) ){
		            throw new NotRelevantException(index + "：生年月日：日", "半角数値２文字以内", LongserviceOfferConstants.FOCUS_BIRTHDAY_DAY, i);
		        }

				CodeFormatter formatter = new CodeFormatter(2);
				formatter.setFormatPattern("00");
				// 生年月日の設定
				uIOfferEntry.setBirthday(
						uIOfferEntry.getBirthday_Year() + formatter.format(uIOfferEntry.getBirthday_Month(), true)
								+ formatter.format(uIOfferEntry.getBirthday_Day(), true));
				if (!dateVrifier.validate(uIOfferEntry.getBirthday())) {
					throw new NotExistException(index + "：生年月日", LongserviceOfferConstants.FOCUS_BIRTHDAY_DAY, i);
				}

		        uIOfferEntry.setAge(String.valueOf(NationalEntryUtil.chgToAgeFrDate(
		        		uIOfferEntry.getBirthday().trim(), sysDate)));
		        // 年齢 レングスチェック及び半角数値チェック
		        if (LongserviceOfferCommon.isLengthOver(uIOfferEntry.getAge(), 3) ||
		        		!numericVerifier.validate(uIOfferEntry.getAge()) ){
		            throw new NotRelevantException(index + "：年齢", "半角数値３文字以内", LongserviceOfferConstants.FOCUS_AGE, i);
		        }
		        // 年齢 妥当性チェック 16歳以上であること
		        int intAge = Integer.parseInt( uIOfferEntry.getAge() );
		        if(intAge <= LongserviceOfferConstants.AGE){
		        	throw new NotRelevantException(index + "：年齢", "１６歳以上", LongserviceOfferConstants.FOCUS_AGE, i);
		        }
		        // 頭の0を削除した年齢をセット
		        uIOfferEntry.setAge(String.valueOf(intAge));

		        // 入社年月日：年 レングスチェック及び半角数値チェック
		        if (uIOfferEntry.getEntryDate_Year().length() != 4 ||
		        		!numericVerifier.validate(uIOfferEntry.getEntryDate_Year()) ){
		            throw new NotRelevantException(index + "：入社年月日：年", "半角数値４文字", LongserviceOfferConstants.FOCUS_ENTRY_YEAR, i);
		        }
		        // 入社年月日：月 レングスチェック及び半角数値チェック
		        if (LongserviceOfferCommon.isLengthOver(uIOfferEntry.getEntryDate_Month(), 2) ||
		        		!numericVerifier.validate(uIOfferEntry.getEntryDate_Month()) ){
		            throw new NotRelevantException(index + "：入社年月日：月", "半角数値２文字以内", LongserviceOfferConstants.FOCUS_ENTRY_MONTH, i);
		        }
		        // 入社年月日：日 レングスチェック及び半角数値チェック
		        if (LongserviceOfferCommon.isLengthOver(uIOfferEntry.getEntryDate_Day(), 2) ||
		        		!numericVerifier.validate(uIOfferEntry.getEntryDate_Day()) ){
		            throw new NotRelevantException(index + "：入社年月日：日", "半角数値２文字以内", LongserviceOfferConstants.FOCUS_ENTRY_DAY, i);
		        }
		        // 入社年月日を作成
				String entryDate = uIOfferEntry.getEntryDate_Year()
						+ formatter.format(uIOfferEntry.getEntryDate_Month(), true)
						+ formatter.format(uIOfferEntry.getEntryDate_Day(), true);

		        // 入社年月日の設定
		        uIOfferEntry.setEntryDate(entryDate);

		        // 入社年月日の妥当性チェック
		        if (LongserviceOfferCommon.isNull(entryDate)) {
		        	throw new NotExistException(index + "：入社年月日", LongserviceOfferConstants.FOCUS_ENTRY_NENGO, i);
		        }
		        if (!dateVrifier.validate(uIOfferEntry.getEntryDate())) {
		            throw new NotExistException(index + "：入社年月日", LongserviceOfferConstants.FOCUS_ENTRY_NENGO, i);
		        }

		        // 入社年月日の妥当性チェック(システム日付以前であること)
		        if(Integer.parseInt(uIOfferEntry.getEntryDate()) > Integer.parseInt(sysDate)){
		        	throw new InvalidInputException(index + "：入社年月日", LongserviceOfferConstants.FOCUS_ENTRY_YEAR, i);
		        }

		        int entYear = Integer.parseInt( DateUtils.getYear( uIOfferEntry.getEntryDate() ) );
		        int sysYear = Integer.parseInt( DateUtils.getYear( sysDate ));

		        // 経験年数が15年以上であること（年齢 - 経験年数 < １５）
		        int age = Integer.parseInt( uIOfferEntry.getAge() ); // 年齢
		        int expNensu = Integer.parseInt( expYears); // 店舗経験

		        if( (age - expNensu) < (LongserviceOfferConstants.AGE)){
		        	throw new InvalidInputException(index + "：年齢入力または経験年数", LongserviceOfferConstants.FOCUS_AGE, i);
		        }
		        // 入社時の年齢が15歳以上であること（　年齢 - (システム年 - 入社年) < １５　）
		        if( (age - (sysYear - entYear) ) < (LongserviceOfferConstants.AGE)){
		        	throw new InvalidInputException(index + "：年齢入力または入社年月日入力", LongserviceOfferConstants.FOCUS_AGE, i);
		        }

		        // 実質経験年数のセット

		        // 入社月
		        int entMonth = Integer.parseInt( DateUtils.getMonth( uIOfferEntry.getEntryDate() ) );
		        // システム月
		        int sysMonth = Integer.parseInt( DateUtils.getMonth( sysDate ) );
		        // 実質経験年数
		        int workNensu = 0;

		        if(entMonth < 10 && sysMonth >= 10){
		        	// 入社月が10月以前　システム月が10月以降
		        	workNensu = sysYear - entYear + 1;
		        }else if(entMonth >= 10 && sysMonth < 10){
		        	// 入社月が10月以降　システム月が10月以前
		        	workNensu = sysYear - entYear - 1;
		        }else{
		        	// 上記２パターン以外
		        	workNensu = sysYear - entYear;
		        }
		        // 実質経験年数が、店舗経験以上であること
		        if( expNensu > workNensu){
		        	if(LongserviceOfferConstants.JOB_TYPE_EMPLOYEE.equals(staffType)) {
			        	throw new GenericMessageException(index + "：店舗経験年数が経過していません。"
			        			, "employeeYears", i);
		        	}
		        	if(LongserviceOfferConstants.JOB_TYPE_PARTTIMER.equals(staffType)) {
			        	throw new GenericMessageException(index + "：店舗経験年数が経過していません。"
			        			, "parttimerYears", i);
		        	}
		        }

		        // 有効情報と判断する
		        uIOfferEntry.setCleaFlg(false);
	        	// null項目にブランクをセットする
		        uIOfferEntry.setNote(LongserviceOfferConstants.EMPTY);
	        }
        }
        return entryList;
    }

    /**
     * キー情報のnullチェック
     * @param longserviceOfferDto
     */
    private void checkKeyData(LongserviceOfferDto dto) {

    	// 申込責任者情報のキーチェック
        if (LongserviceOfferCommon.isNull(dto.getEntryCd())) {
            throw new NotNullException("エントリーコード");
        }
        if (LongserviceOfferCommon.isNull(dto.getEntryYear())) {
            throw new NotNullException("エントリー年");
        }
        if (LongserviceOfferCommon.isNull(dto.getEntryKai())) {
            throw new NotNullException("エントリー回");
        }
        if (LongserviceOfferCommon.isNull(dto.getCompanyCd())) {
            throw new NotNullException("企業コード");
        }
        if (LongserviceOfferCommon.isNull(dto.getOnerCd())) {
            throw new NotNullException("オーナーコード");
        }
        if (LongserviceOfferCommon.isNull(dto.getAtesakiKbn())) {
            throw new NotNullException("宛先区分");
        }

        // 申請者情報のキーチェック
        List entryList = dto.getUIOfferEntryList();

        for(int i = 0;i < entryList.size(); i++){

        	UIOfferEntry uIOfferEntry = (UIOfferEntry)entryList.get(i);

            if (LongserviceOfferCommon.isNull(uIOfferEntry.getEntryCd())) {
                throw new NotNullException("エントリーコード");
            }
            if (LongserviceOfferCommon.isNull(uIOfferEntry.getEntryYear())) {
                throw new NotNullException("エントリー年");
            }
            if (LongserviceOfferCommon.isNull(uIOfferEntry.getEntryKai())) {
                throw new NotNullException("エントリー回");
            }
            if (LongserviceOfferCommon.isNull(uIOfferEntry.getCompanyCd())) {
                throw new NotNullException("企業コード");
            }
            if (LongserviceOfferCommon.isNull(uIOfferEntry.getOnerCd())) {
                throw new NotNullException("オーナーコード");
            }
            if (LongserviceOfferCommon.isNull(uIOfferEntry.getSeqNo())) {
                throw new NotNullException("ソート番号");
            }
        }
    }

    /**
     * 申請者の登録チェック
     * @param List 申請者リスト
     * @return 登録フラグ true:全クリア false:クリアされていない
     */
    private boolean checkEntryClearData(List entryList) {

        for(int i = 0;i < entryList.size(); i++){

        	UIOfferEntry uIOfferEntry = (UIOfferEntry)entryList.get(i);

            if (!uIOfferEntry.isCleaFlg()) {
                return false;
            }
        }
        return true;
    }

	public UIOfferMstDao getUIOfferMstDao() {
		return uIOfferMstDao;
	}

	public void setUIOfferMstDao(UIOfferMstDao offerMstDao) {
		uIOfferMstDao = offerMstDao;
	}
}