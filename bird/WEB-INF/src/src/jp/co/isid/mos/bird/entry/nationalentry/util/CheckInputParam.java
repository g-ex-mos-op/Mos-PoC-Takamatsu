/**
 *
 */
package jp.co.isid.mos.bird.entry.nationalentry.util;

import java.util.List;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.entry.nationalentry.dto.NationalEntryDto;
import jp.co.isid.mos.bird.entry.nationalentry.entity.UINatiEntryJoinInfo;
import jp.co.isid.mos.bird.framework.exception.GenericErrorException;
import jp.co.isid.mos.bird.framework.exception.InputConstraintException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotSelectedException;
import jp.co.isid.mos.bird.framework.util.MojiConverter;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.HankakuVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.NumericVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.PhoneVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.ZenkakuKatakanaVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.ZenkakuVerifier;

/**
 * 入力値チェッククラス
 *
 * 作成日:2012/06/26
 * @author xkinu
 *
 */
public class CheckInputParam {
    /**
     *
     * 更新日:2012/06/25 改修対応
     * 　　　1.エラーメッセージの改善対応
     *       2.仕様追加(仕様番号:SP010316・SP010317)
     *         内容)オプショナルの（宿泊関連）又は（ツアー関連）の選択可能個数はそれぞれ１つのみ。
     *              ２つ以上選択された場合はエラーメッセージを出力する処理を追加。
     */
    public static void validater(NationalEntryDto nationalEntryDto, String procKbn) {

        ZenkakuVerifier zenkakuVerifier = new ZenkakuVerifier();
        NumericVerifier numericVerifier = new NumericVerifier();
        PhoneVerifier phoneVerifier = new PhoneVerifier( true );
        ZenkakuKatakanaVerifier zenkakuKatakanaVerifier = new ZenkakuKatakanaVerifier();
        //エラー有りに設定します。
        nationalEntryDto.setErrFlg("1");
        //参加者情報
        List joinList = nationalEntryDto.getPrmJoinList();


        //2012/06/25 追加 start ---------------------------------------------------
        /**
         * 全国大会用のインナーExceptionクラス
         * 入力エラーException
         *
         * 作成日:2012/06/27
         * @author xkinu
         *
         */
        class InputErrorException extends InputConstraintException {
            static final long serialVersionUID = 1L;
            /**
             * コンストラクター
             */
            public InputErrorException(String msg1, String msg2, int index, String htmlElementName) {
                super("入力エラー。"+msg1, msg2);
                setHtmlElementIndex(index);
                setHtmlElementName(htmlElementName);
            }

            public InputErrorException(String msg1, String msg2, final String detail, int index, String htmlElementName) {
                super("入力エラー。"+msg1, msg2 ,detail);
                setHtmlElementIndex(index);
                setHtmlElementName(htmlElementName);
            }
        }

        /**
         * 全国大会用のインナーExceptionクラス
         * 登録不可能文字Exception
         *
         * 作成日:2012/06/27
         * @author xkinu
         *
         */
        class ImpossibleWordException extends GenericErrorException {
            static final long serialVersionUID = 1L;
            /**
             * コンストラクター
             */
            public ImpossibleWordException(String msg1, int index, String htmlElementName) {
                super("入力", msg1+"に登録できない文字が使用されています。");
                setHtmlElementIndex(index);
                setHtmlElementName(htmlElementName);
            }
        }

        //--参加必須チェック
        if(CommonUtil.isNull(nationalEntryDto.getPrmDutyEntryState())) {
            throw new NotSelectedException("「参加」・「不参加」");
        }
        //--申込責任者必須チェック
        String prmDutyName = nationalEntryDto.getPrmDutyName();
        if(CommonUtil.isNull(prmDutyName)) {
            throw new NotNullException("「申込責任者」","prmDutyName",null);
        }
        //--申込責任者全角・半角チェック
        if(!isAllZenkaku(prmDutyName)) {
            throw new InputErrorException("「申込責任者」", "全角２０文字以内",0,"prmDutyName");
        }
        //--申込責任者不可文字チェック
        if(!zenkakuVerifier.validate(prmDutyName)) {
            throw new ImpossibleWordException("「申込責任者」", 0, "prmDutyName");
        }
        //--申込責任者文字数チェック
        if(isLengthOver(prmDutyName, 40)) {
            throw new InputErrorException("「申込責任者」", "全角２０文字以内",0,"prmDutyName");
        }
        //--電話番号必須チェック
        String prmDutyTel = nationalEntryDto.getPrmDutyTel();
        if(CommonUtil.isNull(prmDutyTel)) {
            throw new NotNullException("「電話番号」", "prmDutyTel", null);
        }
        //--電話番号全角・半角チェック
        if(!isAllHankaku(prmDutyTel)) {
            throw new InputErrorException("「電話番号」", "半角数字・ハイフン１５文字以内",0,"prmDutyTel");
        }
        //--電話番号不可文字チェック
        if (!phoneVerifier.validate(nationalEntryDto.getPrmDutyTel())) {
            throw new ImpossibleWordException("「電話番号」", 0, "prmDutyTel");
        }
        //--電話番号文字数チェック
        if (isLengthOver(nationalEntryDto.getPrmDutyTel(), 15)) {
            throw new InputErrorException("「電話番号」", "半角数字・ハイフン１５文字以内",0,"prmDutyTel");
        }
        //--申込担当者必須チェック
        String prmDutySoufuName = nationalEntryDto.getPrmDutySoufuName();
        if(CommonUtil.isNull(prmDutySoufuName)) {
            throw new NotNullException("「申込担当者」", "prmDutySoufuName", null);
        }
        //--申込担当者全角・半角チェック
        if(!isAllZenkaku(prmDutySoufuName)) {
            throw new InputErrorException("「申込担当者」", "全角２０文字以内", 0, "prmDutySoufuName");
        }
        //--申込担当者不可文字チェック
        if(!zenkakuVerifier.validate(prmDutySoufuName)) {
            throw new ImpossibleWordException("「申込担当者」", 0, "prmDutySoufuName");
        }
        //--申込担当者文字数チェック
        if(isLengthOver(prmDutySoufuName, 40)) {
            throw new InputErrorException("「申込担当者」", "全角２０文字以内", 0, "prmDutySoufuName");
        }
        //--役職全角・半角チェック
        String prmDutyJobType = nationalEntryDto.getPrmDutyJobType();
        if(!isAllZenkaku(prmDutyJobType)) {
            throw new InputErrorException("「役職」", "全角１０文字以内",0,"prmDutyJobType");
        }
        //--役職不可文字チェック
        if(!zenkakuVerifier.validate(prmDutyJobType)) {
            throw new ImpossibleWordException("「役職」", 0, "prmDutyJobType");
        }
        //--役職文字数チェック
        if(isLengthOver(prmDutyJobType, 40)) {
            throw new InputErrorException("「役職」", "全角１０文字以内",0,"prmDutyJobType");
        }
        //2012/06/25 追加 end -----------------------------------------------------

        //申込参加者の参加状況が不参加の場合は参加者の内容チェックはしない
        if(!nationalEntryDto.getPrmDutyEntryState().equals("0")) {
            //申込参加者:店舗、氏名、性別、フリガナ、年齢、申込区分
            int notNullCnt = 0;
            for(int i = 0 ; i < joinList.size(); i++) {
                UINatiEntryJoinInfo uiNatiEntryJoinInfo = (UINatiEntryJoinInfo)joinList.get(i);
                int index = 1;
                index = index + i;
                //必須項目及びすべての項目が未入力でない場合、チェックを行う
                if(!(checkRegistValidtity(uiNatiEntryJoinInfo) &&
                        chkAllJoinInfo(uiNatiEntryJoinInfo))) {
                    String msgParticipant = "参加者" +index;
                    if(CommonUtil.isNull(uiNatiEntryJoinInfo.getMiseCd())) {
                        throw new NotNullException( msgParticipant + ":「店舗」", "miseCd", i);
                    }

                    //--職位全角・半角チェック
                    String jobType = uiNatiEntryJoinInfo.getJobType();
                    if(!isAllZenkaku(jobType)) {
                        throw new InputErrorException(
                                msgParticipant + ":「職位」", "全角１０文字以内",i,"jobType");
                    }
                    if(!CommonUtil.isNull(jobType)) {
                        //--職位不可文字チェック
                        if(!zenkakuVerifier.validate(jobType)) {
                            throw new ImpossibleWordException(msgParticipant + ":「職位」", i, "jobType");
                        }
                        //--職位文字数チェック
                        if(isLengthOver(jobType, 20)) {
                            throw new InputErrorException(
                                    msgParticipant + ":「職位」", "全角１０文字以内", i, "jobType");
                        }
                    }
                    uiNatiEntryJoinInfo.setJobType(nationalEntryDto.getSyokuiName(uiNatiEntryJoinInfo.getJobTypeCd()));
                    //--氏名(氏)必須チェック
                    String lNameKj = uiNatiEntryJoinInfo.getLNameKj();
                    if(CommonUtil.isNull(lNameKj)) {
                        throw new NotNullException(msgParticipant + ":「氏名(氏)」", "lNameKj", i);
                    }
                    //--氏名(氏)全角・半角チェック
                    if(!isAllZenkaku(lNameKj)) {
                        throw new InputErrorException(
                                msgParticipant + ":「氏名(氏)」", "全角１０文字以内",i,"lNameKj");
                    }
                    //--氏名(氏)不可文字チェック
                    if(!zenkakuVerifier.validate(lNameKj)) {
                        throw new ImpossibleWordException(msgParticipant + ":「氏名(氏)」", i, "lNameKj");
                    }
                    //--氏名(氏)文字数チェック
                    if(isLengthOver(lNameKj, 20)) {
                        throw new InputErrorException(
                                msgParticipant + ":「氏名(氏)」","全角１０文字以内", i, "lNameKj");
                    }
                    //--氏名(名)必須チェック
                    String fNameKj = uiNatiEntryJoinInfo.getFNameKj();
                    if(CommonUtil.isNull(fNameKj)) {
                        throw new NotNullException(msgParticipant + ":「氏名(名)」", "fNameKj", i);
                    }
                    //--氏名(名)全角・半角チェック
                    if(!isAllZenkaku(fNameKj)) {
                        throw new InputErrorException(
                                msgParticipant + ":「氏名(名)」", "全角１０文字以内",i,"fNameKj");
                    }
                    //--氏名(名)不可文字チェック
                    if(!zenkakuVerifier.validate(fNameKj)) {
                        throw new ImpossibleWordException(msgParticipant + ":「氏名(名)」", i, "fNameKj");
                    }
                    //--氏名(名)文字数チェック
                    if(isLengthOver(fNameKj, 20)) {
                        throw new InputErrorException(
                                msgParticipant + ":「氏名(名)」","全角１０文字以内", i, "fNameKj");
                    }
                    //--フリガナ(氏)必須チェック
                    String lNameKna = uiNatiEntryJoinInfo.getLNameKna();
                    if(CommonUtil.isNull(lNameKna)) {
                        throw new NotNullException(msgParticipant + ":「フリガナ(氏)」", "lNameKna", i);
                    }
                    //--フリガナ(氏)全角・半角チェック
                    if(!isAllZenkaku(lNameKna)) {
                        throw new InputErrorException(
                                msgParticipant + ":「フリガナ(氏)」", "全角カタカナ１０文字以内",i,"lNameKna");
                    }
                    //--フリガナ（氏）不可文字チェック
                    if(!zenkakuKatakanaVerifier.validate(lNameKna)) {
                        throw new InputErrorException(
                                msgParticipant + ":「フリガナ(氏)」", "全角カタカナ１０文字以内",i,"lNameKna");
                    }
                    //--フリガナ（氏）文字数チェック
                    if(isLengthOver(lNameKna,20)) {
                        throw new InputErrorException(
                                msgParticipant + ":「フリガナ（氏）」","全角カタカナ１０文字以内", i, "lNameKna");
                    }
                    // --フリガナ(名)必須チェック
                    String fNameKna = uiNatiEntryJoinInfo.getFNameKna();
                    if(CommonUtil.isNull(fNameKna)) {
                        throw new NotNullException(msgParticipant + ":「フリガナ(名)」", "fNameKna", i);
                    }
                    //--フリガナ(名)全角・半角チェック
                    if(!isAllZenkaku(fNameKna)) {
                        throw new InputErrorException(
                                msgParticipant + ":「フリガナ(名)」", "全角カタカナ１０文字以内",i,"fNameKna");
                    }
                    //--フリガナ(名)不可文字チェック
                    if(!zenkakuKatakanaVerifier.validate(fNameKna)) {
                        throw new InputErrorException(
                                msgParticipant + ":「フリガナ(名)」", "全角カタカナ１０文字以内",i,"fNameKna");
                    }
                    //--フリガナ（名）文字数チェック
                    if(isLengthOver(fNameKna,20)) {
                        throw new InputErrorException(
                                msgParticipant + ":「フリガナ（名）」","全角カタカナ１０文字以内", i, "fNameKna");
                    }
                    //--性別必須チェック
                    if(CommonUtil.isNull(uiNatiEntryJoinInfo.getSex())) {
                        throw new NotNullException(msgParticipant + ":「性別」", "sex", i);
                    }
                    //
                    if(CommonUtil.isNull(uiNatiEntryJoinInfo.getBirthday_Year())){
                    	throw new NotNullException(msgParticipant + ":「生年月日－年」", "birthdayYear", i);
                    }
                    //
                    if(CommonUtil.isNull(uiNatiEntryJoinInfo.getBirthday_Month())){
                    	throw new NotNullException(msgParticipant + ":「生年月日－月」", "birthdayMonth", i);
                    }
                    //
                    if(CommonUtil.isNull(uiNatiEntryJoinInfo.getBirthday_Day())){
                    	throw new NotNullException(msgParticipant + ":「生年月日－日」", "birthdayDay", i);
                    }
                    //
                    if (!numericVerifier.validate(uiNatiEntryJoinInfo.getBirthday_Year()) ||
                    		Integer.parseInt(uiNatiEntryJoinInfo.getBirthday_Year()) <= 1900) {
                    	throw new ImpossibleWordException(msgParticipant + "：生年月日－年",i, "birthdayYear");
                    }
                    //
                    if (!numericVerifier.validate(uiNatiEntryJoinInfo.getBirthday_Month()) ||
                    		Integer.parseInt(uiNatiEntryJoinInfo.getBirthday_Month()) <= 0) {
                    	throw new ImpossibleWordException(msgParticipant + "：生年月日－月",i, "birthdayMonth");
                    }
                    //
                    if (!numericVerifier.validate(uiNatiEntryJoinInfo.getBirthday_Day()) ||
                    		Integer.parseInt(uiNatiEntryJoinInfo.getBirthday_Day()) <= 0) {
                    	throw new ImpossibleWordException(msgParticipant + "：生年月日－日",i, "birthdayDay");
                    }
                    // 生年月日：年 レングスチェック及び半角数値チェック
    		        if (uiNatiEntryJoinInfo.getBirthday_Year().length() != 4 ||
    		        		!isAllHankaku(uiNatiEntryJoinInfo.getBirthday_Year()) ){
    		            throw new InputErrorException(msgParticipant + "：生年月日－年", "半角数値４文字",i, "birthdayYear");
    		        }
    		        // 生年月日：月 レングスチェック及び半角数値チェック
    		        if (isLengthOver(uiNatiEntryJoinInfo.getBirthday_Month(), 2) ||
    		        		!isAllHankaku(uiNatiEntryJoinInfo.getBirthday_Month()) ){
    		            throw new InputErrorException(msgParticipant + "：生年月日－月", "半角数値２文字以内",i,"birthdayMonth");
    		        }
    		        // 生年月日：日 レングスチェック及び半角数値チェック
    		        if (isLengthOver(uiNatiEntryJoinInfo.getBirthday_Day(), 2) ||
    		        		!isAllHankaku(uiNatiEntryJoinInfo.getBirthday_Day()) ){
    		            throw new InputErrorException(msgParticipant + "：生年月日－日", "半角数値２文字以内",i, "birthdayDay");
    		        }
    		        //
    	        	int syusseiYear = Integer.parseInt(uiNatiEntryJoinInfo.getBirthday_Year());
    	        	int syusseiMonth = Integer.parseInt(uiNatiEntryJoinInfo.getBirthday_Month());
    	        	int syusseiDay = Integer.parseInt(uiNatiEntryJoinInfo.getBirthday_Day());

	        		CodeFormatter formatter = new CodeFormatter(2);
	                formatter.setFormatPattern("00");

    		        // 生年月日の設定
					uiNatiEntryJoinInfo.setBirthday(uiNatiEntryJoinInfo.getBirthday_Year()
							+ formatter.format(uiNatiEntryJoinInfo.getBirthday_Month(), true)
							+ formatter.format(uiNatiEntryJoinInfo.getBirthday_Day(), true));

    		        // 生年月日の妥当性チェック
    		        if (CommonUtil.isNull(uiNatiEntryJoinInfo.getBirthday())) {
    		        	throw new NotExistException(index + "：生年月日", "birthdayDate", i);
    		        }
    		        DateVerifier dateVrifier = new DateVerifier();
    		        if (!dateVrifier.validate(uiNatiEntryJoinInfo.getBirthday())) {
    		            throw new NotExistException(index + "：生年月日", "birthdayDate", i);
    		        }

    		        uiNatiEntryJoinInfo.setAge(String.valueOf(NationalEntryUtil.chgToAgeFrDate(
    		        		uiNatiEntryJoinInfo.getBirthday().trim(), nationalEntryDto.getSysDate())));
                    //--年齢必須チェック
                    String age = uiNatiEntryJoinInfo.getAge();
                    if(CommonUtil.isNull(age)) {
                        throw new NotNullException(msgParticipant + ":「年齢」", "age", i);
                    }
                    //--年齢全角・半角チェック
                    if(!isAllHankaku(age)) {
                        throw new InputErrorException(
                                msgParticipant + ":「年齢」", "半角数字３文字以内",i,"age");
                    }
                    //--年齢不可文字チェック
                    if(!numericVerifier.validate(age) || Integer.parseInt(age) <= 0) {
                        throw new ImpossibleWordException(msgParticipant + ":「年齢」", i, "age");
                    }
                    //--申込区分必須チェック
                    String proposeKbn = uiNatiEntryJoinInfo.getProposeKbn();
                    if(CommonUtil.isNull(proposeKbn)) {
                        throw new NotNullException(msgParticipant + ":「申込区分」", "proposeKbn", i);
                    }

                    //2012/06/26 xkinu 仕様追加(仕様番号:SP010316・SP010317)
                    //オプショナル（宿泊関連）選択個数チェック
                    int checkCnt = 0;
                    if(uiNatiEntryJoinInfo.getOptional1Check()) {
                        checkCnt++;
                    }
                    if(uiNatiEntryJoinInfo.getOptional2Check()) {
                        checkCnt++;
                    }
                    if(uiNatiEntryJoinInfo.getOptional3Check()) {
                        checkCnt++;
                    }
                    if(uiNatiEntryJoinInfo.getOptional4Check()) {
                        checkCnt++;
                    }
                    if(uiNatiEntryJoinInfo.getOptional11Check()) {
                        checkCnt++;
                    }
                    if(uiNatiEntryJoinInfo.getOptional12Check()) {
                        checkCnt++;
                    }
                    if(uiNatiEntryJoinInfo.getOptional13Check()) {
                        checkCnt++;
                    }
                    if(uiNatiEntryJoinInfo.getOptional14Check()) {
                        checkCnt++;
                    }
                    if(uiNatiEntryJoinInfo.getOptional15Check()) {
                        checkCnt++;
                    }
                    if(uiNatiEntryJoinInfo.getOptional16Check()) {
                        checkCnt++;
                    }
                    if(1<checkCnt) {
                        GenericErrorException invalidInputEx = new GenericErrorException(
                                "入力", msgParticipant + ":"+"「オプショナル（宿泊関連）」は１つしか選択できません。");
                                invalidInputEx.setHtmlElementIndex(i);
                                invalidInputEx.setHtmlElementName("optionalstay");
                                throw invalidInputEx;
                    }
                    //オプショナル（ツアー関連）選択個数チェック
                    int tourCheckCnt = 0;
                    if(uiNatiEntryJoinInfo.getOptional5Check()) {
                        tourCheckCnt++;
                    }
                    if(uiNatiEntryJoinInfo.getOptional6Check()) {
                        tourCheckCnt++;
                    }
                    if(uiNatiEntryJoinInfo.getOptional7Check()) {
                        tourCheckCnt++;
                    }
                    if(uiNatiEntryJoinInfo.getOptional8Check()) {
                        tourCheckCnt++;
                    }
                    if(uiNatiEntryJoinInfo.getOptional9Check()) {
                        tourCheckCnt++;
                    }
                    if(uiNatiEntryJoinInfo.getOptional10Check()) {
                        tourCheckCnt++;
                    }
                    if(1<tourCheckCnt) {
                        GenericErrorException invalidInputEx = new GenericErrorException(
                                "入力", msgParticipant + ":"+"「オプショナル（ツアー関連）」は１つしか選択できません。");
                        invalidInputEx.setHtmlElementIndex(i);
                        invalidInputEx.setHtmlElementName("optionaltour");
                        throw invalidInputEx;
                    }

                    int otherCheckCnt = 0;
                    if(uiNatiEntryJoinInfo.getOptional21Check()){
                    	otherCheckCnt++;
                    }
                    if(uiNatiEntryJoinInfo.getOptional22Check()){
                    	otherCheckCnt++;
                    }
                    if(uiNatiEntryJoinInfo.getOptional23Check()){
                    	otherCheckCnt++;
                    }
                    if(uiNatiEntryJoinInfo.getOptional24Check()){
                    	otherCheckCnt++;
                    }
                    if(uiNatiEntryJoinInfo.getOptional25Check()){
                    	otherCheckCnt++;
                    }
                    if(uiNatiEntryJoinInfo.getOptional26Check()){
                    	otherCheckCnt++;
                    }
                    if(uiNatiEntryJoinInfo.getOptional27Check()){
                    	otherCheckCnt++;
                    }
                    if(uiNatiEntryJoinInfo.getOptional28Check()){
                    	otherCheckCnt++;
                    }
                    if(uiNatiEntryJoinInfo.getOptional29Check()){
                    	otherCheckCnt++;
                    }
                    if(uiNatiEntryJoinInfo.getOptional30Check()){
                    	otherCheckCnt++;
                    }

                    String note1 = uiNatiEntryJoinInfo.getNote1();
                    if(!CommonUtil.isNull(note1)) {
                        //--備考1全角・半角チェック
                        if(!isAllZenkaku(note1.replaceAll("`", ""))) {
                            throw new InputErrorException(
                                    msgParticipant + ":「備考1」", "全角５０文字以内",i,"note1");
                        }
                        //--備考1特殊文字チェック
                        note1 = note1.replaceAll("`", "\n");
                        if(!zenkakuVerifier.validate(note1)) {
                            throw new ImpossibleWordException(msgParticipant + ":「備考1」", i, "note1");
                        }
                    }
                    //--備考1文字数チェック
                    if(!CommonUtil.isNull(note1)) {
                        String errMsgStart = "現在";
                        String errMsgEnd = "文字入力されています。";
                        String errMsg = errMsgStart + note1.length() + errMsgEnd;

                        if(isLengthOver(note1, 100)) {
                            throw new InputErrorException(
                                    msgParticipant + ":「備考1」", "全角５０文字以内", errMsg ,i, "note1");
                        }
                    }
                    //
                    String note = uiNatiEntryJoinInfo.getNote();
                    if(!CommonUtil.isNull(note)) {
                        //--備考2全角・半角チェック
                        if(!isAllZenkaku(note.replaceAll("`", ""))) {
                            throw new InputErrorException(
                                    msgParticipant + ":「備考2」", "全角５０文字以内",i,"note");
                        }
                        //--備考2特殊文字チェック
                        note = note.replaceAll("`", "\n");
                        if(!zenkakuVerifier.validate(note)) {
                            throw new ImpossibleWordException(msgParticipant + ":「備考2」", i, "note");
                        }
                    }
                    //--備考2文字数チェック
                    if(!CommonUtil.isNull(note)) {
                        String errMsgStart = "現在";
                        String errMsgEnd = "文字入力されています。";
                        String errMsg = errMsgStart + note.length() + errMsgEnd;

                        if(isLengthOver(note, 100)) {
                            throw new InputErrorException(
                                    msgParticipant + ":「備考2」", "全角５０文字以内", errMsg ,i, "note");
                        }
                    }

//                        if(note.split("\n").length>2) {
//                            GenericErrorException invalidInputEx = new GenericErrorException(
//                                    "入力", msgParticipant + ":「備考」は、２行以内で記述してください。");
//                                invalidInputEx.setHtmlElementIndex(i);
//                                invalidInputEx.setHtmlElementName("note");
//                                throw invalidInputEx;
//                        }
//                    }

                    notNullCnt++;
                }
            }
            if(notNullCnt == 0) {
                //新規登録で参加者情報が１件もない場合、参加者情報の設定を促すメッセージを表示します。
                NotNullException ex = new NotNullException("「参加者」","参加者情報を登録してください。");
                ex.setHtmlElementIndex(0);
                ex.setHtmlElementName("lNameKj");
                throw ex;
            }
            nationalEntryDto.setErrFlg("");
        }// end of if(!nationalEntryDto.getPrmDutyEntryState().equals("0"))
    }



    /**
     * レングスチェック
     * @return boolean true:チェックNG
     */
    private static boolean isLengthOver(String val, int length) {
        if (val == null) {
            return false;
        }
        return val.trim().getBytes().length > length ? true : false;
    }
    /**
     * 全角判断処理
     *
     * @param value
     * @return
     */
    private static boolean isAllZenkaku(final String value) {
        String newValue = MojiConverter.convertUnicodetoMS932(value);
        if (newValue == null || "".equals(newValue.trim())) {
            return true;
        }
        HankakuVerifier hankakVer = new HankakuVerifier();
        for (int i = 0; i < newValue.length(); i++) {
            if (hankakVer.validate(newValue.substring(i, i + 1))) {
                return false;
            }
        }
        return true;
    }
    /**
     * 全角判断処理
     *
     * @param value
     * @return
     */
    private static boolean isAllHankaku(final String value) {
        String newValue = MojiConverter.convertUnicodetoMS932(value);
        if (newValue == null || "".equals(newValue.trim())) {
            return true;
        }
        ZenkakuVerifier zenkakuVerifier = new ZenkakuVerifier();
        for (int i = 0; i < newValue.length(); i++) {
            String souce = newValue.substring(i, i + 1);
            if (zenkakuVerifier.validate(souce)) {
                return false;
            }
        }
        return true;
    }
    /**
     * 参加者情報がすべて未入力かどうかをチェックする
     *
     * @param joinInfo　参加者情報
     * @return true　:すべて未入力
     */
    public static boolean chkAllJoinInfo(UINatiEntryJoinInfo uiNatiEntryJoinInfo) {
        return CommonUtil.isNull(uiNatiEntryJoinInfo.getMiseCd()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getJobTypeCd()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getLNameKj()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getFNameKj()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getSex()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getLNameKna()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getFNameKna()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getBirthday_Year()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getBirthday_Month()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getBirthday_Day()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getAge()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getProposeKbn()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getOptional1()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getOptional2()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getOptional3()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getOptional4()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getOptional5()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getOptional6()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getOptional7()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getOptional8()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getOptional9()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getOptional10()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getNote()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getNote1()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getOptional11()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getOptional12()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getOptional13()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getOptional14()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getOptional15()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getOptional16()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getOptional21()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getOptional22()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getOptional23()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getOptional24()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getOptional25()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getOptional26()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getOptional27()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getOptional28()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getOptional29()) &&
            CommonUtil.isNull(uiNatiEntryJoinInfo.getOptional30()) ;
    }
    /**
     * 指定申込者情報が登録対象かチェックを行う
     * @param entity 申込者情報
     * @return true:登録対象
     */
    public static boolean checkRegistValidtity(UINatiEntryJoinInfo entity) {
        return CommonUtil.isNull(entity.getLNameKna()) &&
                CommonUtil.isNull(entity.getFNameKna()) &&
                CommonUtil.isNull(entity.getLNameKj()) &&
                CommonUtil.isNull(entity.getFNameKna()) &&
                CommonUtil.isNull(entity.getMiseCd()) &&
                CommonUtil.isNull(entity.getSex()) &&
                CommonUtil.isNull(entity.getAge()) &&
                CommonUtil.isNull(entity.getBirthday_Year()) &&
                CommonUtil.isNull(entity.getBirthday_Month()) &&
                CommonUtil.isNull(entity.getBirthday_Day()) &&
                CommonUtil.isNull(entity.getNote1()) &&
                CommonUtil.isNull(entity.getProposeKbn());
    }
}
