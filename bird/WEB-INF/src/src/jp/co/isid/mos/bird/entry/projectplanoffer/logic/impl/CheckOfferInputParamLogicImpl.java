package jp.co.isid.mos.bird.entry.projectplanoffer.logic.impl;

import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.entry.projectplanoffer.dto.ProjectPlanOfferDto;
import jp.co.isid.mos.bird.entry.projectplanoffer.entity.UIOfferDutyPersonInfo;
import jp.co.isid.mos.bird.entry.projectplanoffer.entity.UIOfferEntrustInfo;
import jp.co.isid.mos.bird.entry.projectplanoffer.entity.UIOfferJoinPersonInfo;
import jp.co.isid.mos.bird.entry.projectplanoffer.logic.CheckOfferInputParamLogic;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.verifier.NumericVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.PhoneVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.ZenkakuKatakanaVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.ZenkakuVerifier;
/**
 * 事業方針説明会申込入力データチェックロジック
 * @author xlee
 * 2006/11/21
 */
public class CheckOfferInputParamLogicImpl implements CheckOfferInputParamLogic {
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN011L04";

    public void validater(ProjectPlanOfferDto projectPlanOfferDto, Map paramMap) {

        ZenkakuVerifier zenkakuVerifier = new ZenkakuVerifier();
        NumericVerifier numericVerifier = new NumericVerifier();
        PhoneVerifier phoneVerifier = new PhoneVerifier( true );
        ZenkakuKatakanaVerifier zenkakuKatakanaVerifier = new ZenkakuKatakanaVerifier();

    	UIOfferDutyPersonInfo uiOfferDutyPersonInfo = (UIOfferDutyPersonInfo)paramMap.get(ProjectPlanOfferDto.MAP_KEY_DUTY);
    	List joinList = (List)paramMap.get(ProjectPlanOfferDto.MAP_KEY_JOIN);
    	UIOfferEntrustInfo uiOfferEntrustInfo = (UIOfferEntrustInfo)paramMap.get(ProjectPlanOfferDto.MAP_KEY_ININ);

    	//エラーが発生したら、該当タブが選択されるように
    	//1.必須チェック
    	//2.桁数チェック
    	//申込責任者-連絡先名、電話番号、申込責任者、郵便番号、住所１

    	projectPlanOfferDto.setPrmTabNo(ProjectPlanOfferDto.TABNO_DUTY);
    	if(isNull(uiOfferDutyPersonInfo.getSoufuName())) {
    		projectPlanOfferDto.setErrFlg("1");
    		throw new NotNullException("連絡先名","prmDutySoufuName",null);
    	}
    	if(isNull(uiOfferDutyPersonInfo.getTel())) {
    		projectPlanOfferDto.setErrFlg("1");
    		throw new NotNullException("電話番号", "prmDutyTel", null);
    	}
    	if(isNull(uiOfferDutyPersonInfo.getName())) {
    		projectPlanOfferDto.setErrFlg("1");
    		throw new NotNullException("申込責任者", "prmDutyName", null);
    	}
    	if(isNull(uiOfferDutyPersonInfo.getZip())) {
    		projectPlanOfferDto.setErrFlg("1");
    		throw new NotNullException("郵便番号", "prmDutyZip", null);
    	}
    	if(isNull(uiOfferDutyPersonInfo.getAddress1())) {
    		projectPlanOfferDto.setErrFlg("1");
    		throw new NotNullException("住所", "prmDutyAddress1", null);
    	}

        if(isLengthOver(uiOfferDutyPersonInfo.getSoufuName(), 40)) {
        	 projectPlanOfferDto.setErrFlg("1");
        	 throw new NoInputException("連絡先名は全角２０文字以内", "prmDutySoufuName", null);
        }
        // 連絡先名全角チェック
        if(!zenkakuVerifier.validate(uiOfferDutyPersonInfo.getSoufuName())) {
        	InvalidInputException invalidInputEx = new InvalidInputException("連絡先名"
        			, "入力された”"+uiOfferDutyPersonInfo.getSoufuName()+"”の中に登録できない文字が含まれています。");
        	invalidInputEx.setHtmlElementIndex(0);
        	invalidInputEx.setHtmlElementName("prmDutySoufuName");
        	throw invalidInputEx;
        }
        if(isLengthOver(uiOfferDutyPersonInfo.getName(), 40)) {
        	projectPlanOfferDto.setErrFlg("1");
            throw new NoInputException("申込責任者は全角２０文字以内", "prmDutyName", null);
        }
        // 申込責任者全角チェック
        if(!zenkakuVerifier.validate(uiOfferDutyPersonInfo.getName())) {
        	InvalidInputException invalidInputEx = new InvalidInputException("申込責任者"
        			, "入力された”"+uiOfferDutyPersonInfo.getName()+"”の中に登録できない文字が含まれています。");
        	invalidInputEx.setHtmlElementIndex(0);
        	invalidInputEx.setHtmlElementName("prmDutyName");
        	throw invalidInputEx;
        }
        if (isLengthOver(uiOfferDutyPersonInfo.getTel(), 15)) {
        	projectPlanOfferDto.setErrFlg("1");
            throw new NoInputException("電話番号は半角１５文字以内", "prmDutyName", null);
        }

        if (!phoneVerifier.validate(uiOfferDutyPersonInfo.getTel().trim())) {
        	projectPlanOfferDto.setErrFlg("1");
        	throw new NoInputException("電話番号は半角１５文字以内", "prmDutyTel", null);
        }

        if(!zenkakuVerifier.validate(uiOfferDutyPersonInfo.getSoufuName().trim())) {
        	projectPlanOfferDto.setErrFlg("1");
        	throw new NoInputException("連絡先名は全角２０文字以内", "prmDutySoufuName", null);
        }

        if(!zenkakuVerifier.validate(uiOfferDutyPersonInfo.getName().trim())) {
        	projectPlanOfferDto.setErrFlg("1");
            throw new NoInputException("申込責任者は全角２０文字以内", "prmDutyTel", null);
        }

        if (isLengthOver(uiOfferDutyPersonInfo.getZip().trim(), 8)) {
        	projectPlanOfferDto.setErrFlg("1");
            throw new NoInputException("郵便番号 〒は半角数字とハイフン「-」(１コ）", "prmDutyZip", null);
        }

        if (!isPostZipChkVal(uiOfferDutyPersonInfo.getZip().trim())) {
        	projectPlanOfferDto.setErrFlg("1");
            throw new NoInputException("郵便番号 〒は半角数字とハイフン「-」(１コ）", "prmDutyZip", null);
        }

        if(isLengthOver(uiOfferDutyPersonInfo.getJobType(), 40)) {
        	projectPlanOfferDto.setErrFlg("1");
            throw new NoInputException("役職は全角２０文字以内", "prmDutyJobType", null);
        }

        if(isLengthOver(uiOfferDutyPersonInfo.getAddress1(),60)){
        	projectPlanOfferDto.setErrFlg("1");
            throw new NoInputException("オーナー住所は全角３０文字以内", "prmDutyAddress1", null);
        }

        if(isLengthOver(uiOfferDutyPersonInfo.getAddress2(),60)){
        	projectPlanOfferDto.setErrFlg("1");
            throw new NoInputException("オーナー住所は全角３０文字以内", "prmDutyAddress2", null);
        }

        if(isLengthOver(uiOfferDutyPersonInfo.getAddress3(),60)){
        	projectPlanOfferDto.setErrFlg("1");
            throw new NoInputException("オーナー住所は全角３０文字以内", "prmDutyAddress3", null);
        }

		if(!zenkakuVerifier.validate(uiOfferDutyPersonInfo.getJobType().trim())) {
			projectPlanOfferDto.setErrFlg("1");
		    throw new NoInputException("役職は全角２０文字以内", "prmDutyJobType", null);
		}

		if(!zenkakuVerifier.validate(uiOfferDutyPersonInfo.getAddress1().trim())) {
			projectPlanOfferDto.setErrFlg("1");
		    throw new NoInputException("オーナー住所は全角３０文字以内", "prmDutyAddress1", null);
		}

		if(!zenkakuVerifier.validate(uiOfferDutyPersonInfo.getAddress2().trim())) {
			projectPlanOfferDto.setErrFlg("1");
		    throw new NoInputException("オーナー住所は全角３０文字以内", "prmDutyAddress2", null);
		}

		if(!zenkakuVerifier.validate(uiOfferDutyPersonInfo.getAddress3().trim())) {
			projectPlanOfferDto.setErrFlg("1");
		    throw new NoInputException("オーナー住所は全角３０文字以内", "prmDutyAddress3", null);
		}

    	//申込参加者
		if(joinList != null) {
	    	for(int i = 0 ; i< joinList.size(); i++) {
	    		UIOfferJoinPersonInfo uiOfferJoinPersonInfo = (UIOfferJoinPersonInfo)joinList.get(i);
                String index = "";
                index = uiOfferJoinPersonInfo.getTabNo();
		    	//申込参加者-店舗、フリガナ氏名、漢字氏名、性別、年齢、事業方針説明会、懇親会、共栄会定時総会、本部手配宿泊
		    	if(isNull(uiOfferJoinPersonInfo.getMiseCd())) {
		    		projectPlanOfferDto.setPrmTabNo(uiOfferJoinPersonInfo.getTabNo());
		    		projectPlanOfferDto.setErrFlg("1");
		    		throw new NotNullException(index + "：店舗", "prmMiseCd", i);
		    	}
		    	if(isNull(uiOfferJoinPersonInfo.getLNameKna())) {
		    		projectPlanOfferDto.setPrmTabNo(uiOfferJoinPersonInfo.getTabNo());
		    		projectPlanOfferDto.setErrFlg("1");
		    		throw new NotNullException(index + "：フリガナ(氏)", "lNameKna", i);
		    	}
		    	if(isNull(uiOfferJoinPersonInfo.getFNameKna())) {
		    		projectPlanOfferDto.setPrmTabNo(uiOfferJoinPersonInfo.getTabNo());
		    		projectPlanOfferDto.setErrFlg("1");
		    		throw new NotNullException(index + "：フリガナ(名)", "fNameKna", i);
		    	}
		    	String lNameKj = uiOfferJoinPersonInfo.getLNameKj();
		    	if(isNull(lNameKj)) {
		    		projectPlanOfferDto.setPrmTabNo(uiOfferJoinPersonInfo.getTabNo());
		    		projectPlanOfferDto.setErrFlg("1");
		    		throw new NotNullException(index + "：氏名(氏)", "lNameKj", i);
		    	}
		    	String fNameKj = uiOfferJoinPersonInfo.getFNameKj();
		    	if(isNull(uiOfferJoinPersonInfo.getFNameKj())) {
		    		projectPlanOfferDto.setPrmTabNo(uiOfferJoinPersonInfo.getTabNo());
		    		projectPlanOfferDto.setErrFlg("1");
		    		throw new NotNullException(index + "：氏名(名)", "fNameKj", i);
		    	}
		    	if(isNull(uiOfferJoinPersonInfo.getSex())) {
		    		projectPlanOfferDto.setPrmTabNo(uiOfferJoinPersonInfo.getTabNo());
		    		projectPlanOfferDto.setErrFlg("1");
		    		throw new NotNullException(index + "：性別","sex", i);
		    	}
		    	if(isNull(uiOfferJoinPersonInfo.getAge())) {
		    		projectPlanOfferDto.setPrmTabNo(uiOfferJoinPersonInfo.getTabNo());
		    		projectPlanOfferDto.setErrFlg("1");
		    		throw new NotNullException(index + "：年齢", "age", i);
		    	}
		    	if(isNull(uiOfferJoinPersonInfo.getJigyoFlg())) {
		    		projectPlanOfferDto.setPrmTabNo(uiOfferJoinPersonInfo.getTabNo());
		    		projectPlanOfferDto.setErrFlg("1");
		    		throw new NotNullException(index + "：チェーン方針説明会", "jigyoFlg0", i);
		    	}
		    	if(isNull(uiOfferJoinPersonInfo.getKonshinFlg())) {
		    		projectPlanOfferDto.setPrmTabNo(uiOfferJoinPersonInfo.getTabNo());
		    		projectPlanOfferDto.setErrFlg("1");
		    		throw new NotNullException(index + "：懇親会", "konshinFlg0", i);
		    	}
		    	if(isNull(uiOfferJoinPersonInfo.getKyoeiFlg())) {
		    		projectPlanOfferDto.setPrmTabNo(uiOfferJoinPersonInfo.getTabNo());
		    		projectPlanOfferDto.setErrFlg("1");
		    		throw new NotNullException(index + "：共栄会定時総会", "kyoeiFlg0", i);
		    	}
		    	if(isNull(uiOfferJoinPersonInfo.getShukuhakuFlg())) {
		    		projectPlanOfferDto.setPrmTabNo(uiOfferJoinPersonInfo.getTabNo());
		    		projectPlanOfferDto.setErrFlg("1");
		    		throw new NotNullException(index + "：本部手配宿泊", "shukuhakuFlg0", i);
		    	}
		    	if(uiOfferJoinPersonInfo.getJigyoFlg().equals("1")) {
			    	if(isNull(uiOfferJoinPersonInfo.getAbsenceReason()) ||
			    			isZenkakuSpc(uiOfferJoinPersonInfo.getAbsenceReason())) {
			    		projectPlanOfferDto.setPrmTabNo(uiOfferJoinPersonInfo.getTabNo());
			    		projectPlanOfferDto.setErrFlg("1");
			    		throw new NotNullException(index + "：チェーン方針説明会欠席理由", "absenceReason", i);
			    	}
		    	}
		        if(isLengthOver(uiOfferJoinPersonInfo.getLNameKna(),20)) {
		        	projectPlanOfferDto.setPrmTabNo(uiOfferJoinPersonInfo.getTabNo());
		        	projectPlanOfferDto.setErrFlg("1");
		            throw new NoInputException(index + "：フリガナ(氏)は全角カタカナ10文字以内", "lNameKna", i);
		        }

		        if(isLengthOver(uiOfferJoinPersonInfo.getFNameKna(),20)) {
		        	projectPlanOfferDto.setPrmTabNo(uiOfferJoinPersonInfo.getTabNo());
		        	projectPlanOfferDto.setErrFlg("1");
		            throw new NoInputException(index + "：フリガナ(名)は全角カタカナ10文字以内", "fNameKna", i);
		        }

		        if(isLengthOver(lNameKj, 20)) {
		        	projectPlanOfferDto.setPrmTabNo(uiOfferJoinPersonInfo.getTabNo());
		        	projectPlanOfferDto.setErrFlg("1");
		            throw new NoInputException(index + "：氏名(氏)は全角１０文字以内", "lNameKj", i);
		        }

		        if(isLengthOver(fNameKj, 20)) {
		        	projectPlanOfferDto.setPrmTabNo(uiOfferJoinPersonInfo.getTabNo());
		        	projectPlanOfferDto.setErrFlg("1");
		            throw new NoInputException(index + "：氏名(名)は全角１０文字以内", "fNameKj", i);
		        }

		        if(isLengthOver(uiOfferJoinPersonInfo.getNote(),100))  {
		        	projectPlanOfferDto.setPrmTabNo(uiOfferJoinPersonInfo.getTabNo());
		        	projectPlanOfferDto.setErrFlg("1");
		            throw new NoInputException(index + "：備考は全角５０文字以内", "note", i);
		        }

		        if(isLengthOver(uiOfferJoinPersonInfo.getAbsenceReason(),100)) {
		        	projectPlanOfferDto.setPrmTabNo(uiOfferJoinPersonInfo.getTabNo());
		        	projectPlanOfferDto.setErrFlg("1");
		            throw new NoInputException(index + "：チェーン方針説明会欠席理由は全角５０文字以内", "absenceReason", i);
		        }

		        if(!zenkakuKatakanaVerifier.validate(uiOfferJoinPersonInfo.getLNameKna().trim())) {
		        	projectPlanOfferDto.setPrmTabNo(uiOfferJoinPersonInfo.getTabNo());
		        	projectPlanOfferDto.setErrFlg("1");
		            throw new InvalidInputException(index + "：フリガナ(氏)", "lNameKna", i);
		        }

		        if(!zenkakuKatakanaVerifier.validate(uiOfferJoinPersonInfo.getFNameKna().trim())) {
		        	projectPlanOfferDto.setPrmTabNo(uiOfferJoinPersonInfo.getTabNo());
		        	projectPlanOfferDto.setErrFlg("1");
		            throw new InvalidInputException(index + "：フリガナ(名)", "fNameKna", i);
		        }

		        if(!zenkakuVerifier.validate(lNameKj.trim())) {
		        	projectPlanOfferDto.setPrmTabNo(uiOfferJoinPersonInfo.getTabNo());
		        	projectPlanOfferDto.setErrFlg("1");
		        	InvalidInputException invalidInputEx = new InvalidInputException(index + "：氏名(氏)"
		        			, "入力された”"+lNameKj+"”の中に登録できない文字が含まれています。");
		        	invalidInputEx.setHtmlElementIndex(0);
		        	invalidInputEx.setHtmlElementName("lNameKj");
		        	throw invalidInputEx;
		        }

		        if(!zenkakuVerifier.validate(fNameKj.trim())) {
		        	projectPlanOfferDto.setPrmTabNo(uiOfferJoinPersonInfo.getTabNo());
		        	projectPlanOfferDto.setErrFlg("1");
		        	InvalidInputException invalidInputEx = new InvalidInputException(index + "：氏名(名)"
		        			, "入力された”"+fNameKj+"”の中に登録できない文字が含まれています。");
		        	invalidInputEx.setHtmlElementIndex(0);
		        	invalidInputEx.setHtmlElementName("fNameKj");
		        	throw invalidInputEx;
		        }

		    	if(!numericVerifier.validate(uiOfferJoinPersonInfo.getAge())) {
		    		projectPlanOfferDto.setPrmTabNo(uiOfferJoinPersonInfo.getTabNo());
		    		projectPlanOfferDto.setErrFlg("1");
		    		throw new NoInputException(index + "：年齢は半角数値3文字以内", "age", i);
		    	} else {
		    		if(Integer.parseInt(uiOfferJoinPersonInfo.getAge()) <= 0) {
			    		projectPlanOfferDto.setPrmTabNo(uiOfferJoinPersonInfo.getTabNo());
			    		projectPlanOfferDto.setErrFlg("1");
			    		throw new InvalidInputException(index + "：年齢", "age", i);
		    		}
		    	}
		        if(!zenkakuVerifier.validate(uiOfferJoinPersonInfo.getNote().trim())) {
		        	projectPlanOfferDto.setPrmTabNo(uiOfferJoinPersonInfo.getTabNo());
		        	projectPlanOfferDto.setErrFlg("1");
		            throw new NoInputException(index + "：備考は全角５０文字以内", "note", i);
		        }

		        if(!zenkakuVerifier.validate(uiOfferJoinPersonInfo.getAbsenceReason().trim())) {
		        	projectPlanOfferDto.setPrmTabNo(uiOfferJoinPersonInfo.getTabNo());
		        	projectPlanOfferDto.setErrFlg("1");
		            throw new NoInputException(index + "：チェーン方針説明会欠席理由は全角５０文字以内", "absenceReason", i);
		        }
	    	}
	    	if(joinList.size() == 0) {
	    		//登録の時、参加者情報がない場合
	    		projectPlanOfferDto.setPrmTabNo(String.valueOf(1));
	    		UIOfferJoinPersonInfo uiOfferJoinPersonInfo = new UIOfferJoinPersonInfo();

		    	if(isNull(uiOfferJoinPersonInfo.getLNameKna())) {
		    		projectPlanOfferDto.setPrmTabNo(uiOfferJoinPersonInfo.getTabNo());
		    		projectPlanOfferDto.setErrFlg("1");
		    		throw new NotNullException("フリガナ(氏)", "lNameKna", 0);
		    	}
	    	}
		}

		boolean kyoeiChkFlg = chkKyoeiFlg(projectPlanOfferDto.getPrmJoinPersonList());
    	//参加者全員欠席、委任状画面からの処理であれば
		if(kyoeiChkFlg && projectPlanOfferDto.getValChkMode().equals(ProjectPlanOfferDto.VALCHK_MODE_ININ)) {
	    	if(uiOfferEntrustInfo != null) {
		    	//委任状
		    	if(isNull(uiOfferEntrustInfo.getIninFName())) {
		    		projectPlanOfferDto.setErrFlg("1");
		    		throw new NotNullException("氏名(氏)", "prmIninFName", null);
		    	}

		    	if(isNull(uiOfferEntrustInfo.getIninLName())) {
		    		projectPlanOfferDto.setErrFlg("1");
		    		throw new NotNullException("氏名(名)", "prmIninLName", null);
		    	}

		    	if(isNull(uiOfferEntrustInfo.getIninMiseTotal())) {
		    		projectPlanOfferDto.setErrFlg("1");
		    		throw new NotNullException("店舗数（所有店舗数）", "prmIninMiseTotal", null);
		    	}

		        if(isLengthOver(uiOfferEntrustInfo.getIninFName(),20)) {
		        	projectPlanOfferDto.setErrFlg("1");
		            throw new NoInputException("氏名(氏)は全角１０文字以内", "prmIninFName", null);
		        }

		        if(isLengthOver(uiOfferEntrustInfo.getIninLName(),20)) {
		        	projectPlanOfferDto.setErrFlg("1");
		            throw new NoInputException("氏名(名)は全角１０文字以内", "prmIninLName", null);
		        }

		        if(isLengthOver(uiOfferEntrustInfo.getIninMiseTotal(),5) ) {
		        	projectPlanOfferDto.setErrFlg("1");
		            throw new NoInputException("店舗数（所有店舗数）は半角数値", "prmIninMiseTotal", null);
		        }

		        // 氏名(氏)全角チェック
		        if(!zenkakuVerifier.validate(uiOfferEntrustInfo.getIninFName().trim())) {
		        	InvalidInputException invalidInputEx = new InvalidInputException("氏名(氏)"
		        			, "入力された”"+uiOfferEntrustInfo.getIninFName().trim()+"”の中に登録できない文字が含まれています。");
		        	invalidInputEx.setHtmlElementIndex(0);
		        	invalidInputEx.setHtmlElementName("prmIninFName");
		        	throw invalidInputEx;
		        }
		        // 氏名(氏)全角チェック
		        if(!zenkakuVerifier.validate(uiOfferEntrustInfo.getIninLName().trim())) {
		        	InvalidInputException invalidInputEx = new InvalidInputException("氏名(名)"
		        			, "入力された”"+uiOfferEntrustInfo.getIninLName().trim()+"”の中に登録できない文字が含まれています。");
		        	invalidInputEx.setHtmlElementIndex(0);
		        	invalidInputEx.setHtmlElementName("prmIninLName");
		        	throw invalidInputEx;
		        }

		        if(!numericVerifier.validate(uiOfferEntrustInfo.getIninMiseTotal().trim())) {
		        	projectPlanOfferDto.setErrFlg("1");
		            throw new NoInputException("店舗数（所有店舗数）は半角数値", "prmIninMiseTotal", null);
		        } else {
		        	if(Integer.parseInt(uiOfferEntrustInfo.getIninMiseTotal().trim()) < 0) {
			        	projectPlanOfferDto.setErrFlg("1");
			            throw new InvalidInputException("店舗数（所有店舗数）", "prmIninMiseTotal", null);
		        	}
		        }
	    	}
		}
    }

    /**
     * Null判断処理
     * @param str
     * @return boolean true：空かNullの場合
     */
    public boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }

    /**
     * レングスチェック
     * @return boolean true:チェックNG
     */
    private boolean isZenkakuSpc(String val) {
        if (val == null) {
            return true;
        }
        if(val.replaceAll("　", "").length() == 0) {
        	return true;
        }
        return false;
    }

	public boolean chkKyoeiFlg(List joinPersInfo) {

		boolean retFlg = false;
		String flg[] = new String[3];

		for(int i = 0; i < joinPersInfo.size(); i++) {
			UIOfferJoinPersonInfo uiOfferJoinPersonInfo= (UIOfferJoinPersonInfo)joinPersInfo.get(i);
			flg[i] = uiOfferJoinPersonInfo.getKyoeiFlg();
		}
		if(isNull(flg[0]) && isNull(flg[1]) && isNull(flg[2])) {
			return retFlg;
		} else if((isNull(flg[0]) || flg[0].equals("1")) &&
				(isNull(flg[1]) || flg[1].equals("1")) &&
				(isNull(flg[2]) || flg[2].equals("1"))) {
			retFlg = true;
		}
		return retFlg;
	}

	public boolean chkJoinPersInfo(List joinPersInfo) {
		boolean retFlg = false;
		boolean flg[] = new boolean[3];
		for(int i = 0; i < joinPersInfo.size(); i++) {
			UIOfferJoinPersonInfo uiOfferJoinPersonInfo= (UIOfferJoinPersonInfo)joinPersInfo.get(i);
			if(isNull(uiOfferJoinPersonInfo.getLNameKna()) &&
				isNull(uiOfferJoinPersonInfo.getFNameKna()) &&
				isNull(uiOfferJoinPersonInfo.getSex()) &&
				isNull(uiOfferJoinPersonInfo.getLNameKj()) &&
				isNull(uiOfferJoinPersonInfo.getFNameKj()) &&
				isNull(uiOfferJoinPersonInfo.getAge()) &&
				isNull(uiOfferJoinPersonInfo.getJigyoFlg()) &&
				isNull(uiOfferJoinPersonInfo.getKonshinFlg()) &&
				isNull(uiOfferJoinPersonInfo.getKyoeiFlg()) &&
				isNull(uiOfferJoinPersonInfo.getShukuhakuFlg()) &&
				isNull(uiOfferJoinPersonInfo.getNote()) &&
				isNull(uiOfferJoinPersonInfo.getAbsenceReason())) {
				flg[i] = true;
			} else {
				flg[i] = false;
			}
		}

		if(flg[0] && flg[1] && flg[2]) {
			retFlg = true;
		}
		return retFlg;
	}

    /**
     * 指定申込者情報が登録対象かチェックを行う
     * @param entity 申込者情報
     * @return true:登録対象
     */
    public boolean checkRegistValidtity(UIOfferJoinPersonInfo entity) {
        return !isNull(entity.getLNameKna()) ||
                !isNull(entity.getFNameKna()) ||
                !isNull(entity.getLNameKj()) ||
                !isNull(entity.getFNameKna()) ||
                !isNull(entity.getSex()) ||
                !isNull(entity.getAge()) ||
                !isNull(entity.getJigyoFlg()) ||
                !isNull(entity.getKonshinFlg()) ||
                !isNull(entity.getKyoeiFlg()) ||
                !isNull(entity.getShukuhakuFlg()) ||
                !isNull(entity.getNote()) ||
                !isNull(entity.getAbsenceReason());
    }

    /**
     * レングスチェック
     * @return boolean true:チェックNG
     */
    private boolean isLengthOver(String val, int length) {
        if (val == null) {
            return false;
        }
        return val.trim().getBytes().length > length ? true : false;
    }

    /**
     *　郵便番号チェック
     * @return boolean false:チェックNG
     */
    private boolean isPostZipChkVal(String text) {
    	boolean hypFlg = false;
        if (text == null || text.trim().equals("")) {
            return false;
        }

        if (text.charAt(0) == '-') {
            return false;
        }
        if (text.charAt(text.length() - 1) == '-') {
            return false;
        }
        // 文字チェック
        for (int i = 0; i < text.length(); i++) {

        	char c = text.charAt(i);

            if (c >= '\u0030' && c <= '\u0039') {
                // 数字
            }
            else if (true && (c == '-')) {
                // Hyphen
            	hypFlg = true;
            }
            else {
                return false;
            }
        }
        //レングスチェック
        int digitNum = 0;
        if(hypFlg) {
        	digitNum = 8;
        } else {
        	digitNum = 7;
        }
        if (digitNum != 0 && text.getBytes().length > digitNum) {
            return false;
        }
        return true;
    }
}
