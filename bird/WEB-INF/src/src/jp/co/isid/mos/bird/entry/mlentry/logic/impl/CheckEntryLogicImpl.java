package jp.co.isid.mos.bird.entry.mlentry.logic.impl;

import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.entry.mlentry.common.MlEntryCommon;
import jp.co.isid.mos.bird.entry.mlentry.dto.MlEntryDto;
import jp.co.isid.mos.bird.entry.mlentry.entity.UIEntryOner;
import jp.co.isid.mos.bird.entry.mlentry.entity.UIEntryState;
import jp.co.isid.mos.bird.entry.mlentry.logic.CheckEntryLogic;
import jp.co.isid.mos.bird.framework.exception.DuplicateDataException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotRelevantException;
import jp.co.isid.mos.bird.framework.util.verifier.NumericVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.PhoneVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.ZenkakuVerifier;


/**
 * 登録内容チェック
 * @author Aspac
 */
public class CheckEntryLogicImpl implements CheckEntryLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN008L01";
    
	/**
     * 登録内容のチェックを行う
     * @param HanyoEntryDto dto     
     */
    public List execute(MlEntryDto dto) {
    	// １．必須のパラメータが満たされていること。E0506（@パラメータ）
        validate(dto);
        
        // 重複チェック
        for (int i = 0; i < dto.getListEntryStateRegist().size(); i++) {
            UIEntryState uiEntryState = (UIEntryState) dto.getListEntryStateRegist().get(i);
            if ((uiEntryState.getStateFlg().equals(MlEntryCommon.LIST_STATES_FLG_ENTRY_RECORD) ||
                    uiEntryState.getStateFlg().equals(MlEntryCommon.LIST_STATES_FLG_ENTRY_NO_RECORD) ||
                    uiEntryState.isInsertFlg()) && (uiEntryState.getStaffId() != null && !uiEntryState.getStaffId().equals(""))) {
                for (int j = i + 1; j < dto.getListEntryStateRegist().size(); j++) {
                    UIEntryState uiEntryState2 = (UIEntryState) dto.getListEntryStateRegist().get(j);
                    if ((uiEntryState2.getStateFlg().equals(MlEntryCommon.LIST_STATES_FLG_ENTRY_RECORD) ||
                            uiEntryState2.getStateFlg().equals(MlEntryCommon.LIST_STATES_FLG_ENTRY_NO_RECORD) ||
                            uiEntryState2.isInsertFlg()) && (uiEntryState.getStaffId() != null && !uiEntryState.getStaffId().equals(""))) {
                        if (uiEntryState.getStaffId().equals(uiEntryState2.getStaffId())) {
                            throw new DuplicateDataException((i + 1) + "番目と" + (j + 1) + "番目のスタッフ");
                        }
                    }
                }
            }
        }
        
        // 受験票送付先情報のチェック        
        checkTantoSofu(dto);
        
        // 結果送付先情報のチェック
        checkKekkaSofu(dto);
        
        // エントリー状況情報のチェック
        int index = 1;
        for (Iterator ite = dto.getListEntryStateRegist().iterator(); ite.hasNext();) {
            UIEntryState uiEntryState = (UIEntryState) ite.next();
            
            if (
                  (uiEntryState.getStateFlg().equals(MlEntryCommon.LIST_STATES_FLG_ENTRY_RECORD) ||
                    uiEntryState.getStateFlg().equals(MlEntryCommon.LIST_STATES_FLG_ENTRY_NO_RECORD) ||
                        uiEntryState.isInsertFlg()) 
                            && (uiEntryState.getStaffId() != null && !uiEntryState.getStaffId().equals(""))  ) {
                
                checkEntryState(uiEntryState, index, dto.getEntryCd());
            }
            index++;
        }
        
        return null;
    }

    /**
     * 必須、妥当性チェック
     */
    private void validate(MlEntryDto dto) {
    }    
    
    /**
     * 結果送付先情報のチェック
     */
    private void checkKekkaSofu(MlEntryDto dto) {
        ZenkakuVerifier zenkakuVerifier = new ZenkakuVerifier();
        NumericVerifier numericVerifier = new NumericVerifier();
// add start xkhata 20060904 電話番号-許可対応
        PhoneVerifier phoneVerifier = new PhoneVerifier( true );
// add end
        String value = "";
        
        UIEntryOner uiEntryOwnerKekkaHokokusaki = dto.getUiEntryOnerKekkaHokoku();
        //氏名
        value = uiEntryOwnerKekkaHokokusaki.getName();
        if (isNull(value)) {
            throw new NotNullException("結果報告先 氏名", "KekkaHokokuName", null);
        }
        if (isLengthOver(value, 40)) {
            throw new NotRelevantException("結果報告先 氏名", "全角２０文字以内", "KekkaHokokuName", null);
        }
        if (!zenkakuVerifier.validate(value)) {
            throw new InvalidInputException("結果報告先 氏名", "KekkaHokokuName", null);
        }
        //電話番号
        value = uiEntryOwnerKekkaHokokusaki.getTel();
        if (!isNull(value)) {
            if (isLengthOver(value, 15)) {
                throw new NotRelevantException("結果報告先 電話番号", "半角１５文字以内", "KekkaHokokuTel", null);
            }
// change start xkhata 20060904 電話番号-許可対応
//            if (!numericVerifier.validate(value)) {
            if ( !phoneVerifier.validate( value ) ) {
//              change end
                throw new InvalidInputException("結果報告先 電話番号", "KekkaHokokuTel", null);
            }
        }
        //郵便番号
        value = uiEntryOwnerKekkaHokokusaki.getZip();
        if (isNull(value)) {
            throw new NotNullException("結果報告先 郵便番号", "KekkaHokokuZip", null);
        }
        if (isLengthOver(value, 8)) {
            throw new NotRelevantException("結果報告先 郵便番号", "半角８文字以内", "KekkaHokokuZip", null);
        }
        if (!numericVerifier.validate(value)) {
            throw new InvalidInputException("結果報告先 郵便番号", "KekkaHokokuZip", null);
        }
        //住所１
        value = uiEntryOwnerKekkaHokokusaki.getAddress1();
        if (isNull(value)) {
            throw new NotNullException("結果報告先 住所１", "KekkaHokokuAddress1", null);
        }
        if (isLengthOver(value, 60)) {
            throw new NotRelevantException("結果報告先 住所１", "全角３０文字以内", "KekkaHokokuAddress1", null);
        }
        if (!zenkakuVerifier.validate(value)) {
            throw new InvalidInputException("結果報告先 住所１", "KekkaHokokuAddress1", null);
        }
        //住所２
        value = uiEntryOwnerKekkaHokokusaki.getAddress2();
        if (!isNull(value)) {
            if (isLengthOver(value, 60)) {
                throw new NotRelevantException("結果報告先 住所２", "全角３０文字以内", "KekkaHokokuAddress2", null);
            }
            if (!zenkakuVerifier.validate(value)) {
                throw new InvalidInputException("結果報告先 住所２", "KekkaHokokuAddress2", null);
            }
        }
        //住所３
        value = uiEntryOwnerKekkaHokokusaki.getAddress3();
        if (!isNull(value)) {
            if (isLengthOver(value, 60)) {
                throw new NotRelevantException("結果報告先 住所３", "全角３０文字以内", "KekkaHokokuAddress3", null);
            }
            if (!zenkakuVerifier.validate(value)) {
                throw new InvalidInputException("結果報告先 住所３", "KekkaHokokuAddress3", null);
            }
        }
        //送付先名
        value = uiEntryOwnerKekkaHokokusaki.getSoufuName();
        if (isLengthOver(value, 40)) {
            throw new NotRelevantException("結果報告先 送付先名", "全角２０文字以内", "KekkaHokokuSoufuName", null);
        }
        if (!zenkakuVerifier.validate(value)) {
            throw new InvalidInputException("結果報告先 送付先名", "KekkaHokokuSoufuName", null);
        }
    }
    
    /**
     * 受験票送付先情報のチェック
     */
    private void checkTantoSofu(MlEntryDto dto) {
        ZenkakuVerifier zenkakuVerifier = new ZenkakuVerifier();
        NumericVerifier numericVerifier = new NumericVerifier();
// add start xkhata 20060904 電話番号-許可対応
        PhoneVerifier phoneVerifier = new PhoneVerifier( true );
// add end
        
        String value = "";
        
        UIEntryOner uiEntryOwnerTanto = dto.getUiEntryOnerTanto();
        //氏名
        value = uiEntryOwnerTanto.getName();
        if (isNull(value)) {
            throw new NotNullException("受験票送付先 氏名", "TantoName", null);
        }
        if (isLengthOver(value, 40)) {
            throw new NotRelevantException("受験票送付先 氏名", "全角２０文字以内", "TantoName", null);
        }
        if (!zenkakuVerifier.validate(value)) {
            throw new InvalidInputException("受験票送付先 氏名", "TantoName", null);
        }
        //電話番号
        value = uiEntryOwnerTanto.getTel();
        if (!isNull(value)) {
            if (isLengthOver(value, 15)) {
                throw new NotRelevantException("受験票送付先 電話番号", "半角１５文字以内", "TantoTel", null);
            }
// change start xkhata 20060904 電話番号-許可対応
//            if (!numericVerifier.validate(value)) {
            if ( !phoneVerifier.validate( value ) ) {
// change end
                throw new InvalidInputException("受験票送付先 電話番号", "TantoTel", null);
            }
        }
        //郵便番号
        value = uiEntryOwnerTanto.getZip();
        if (isNull(value)) {
            throw new NotNullException("受験票送付先 郵便番号", "TantoZip", null);
        }
        if (isLengthOver(value, 8)) {
            throw new NotRelevantException("受験票送付先 郵便番号", "半角８文字以内", "TantoZip", null);
        }
        if (!numericVerifier.validate(value)) {
            throw new InvalidInputException("受験票送付先 郵便番号", "TantoZip", null);
        }
        //住所１
        value = uiEntryOwnerTanto.getAddress1();
        if (isNull(value)) {
            throw new NotNullException("受験票送付先 住所１", "TantoAddress1", null);
        }
        if (isLengthOver(value, 60)) {
            throw new NotRelevantException("受験票送付先 住所１", "全角３０文字以内", "TantoAddress1", null);
        }
        if (!zenkakuVerifier.validate(value)) {
            throw new InvalidInputException("受験票送付先 住所１", "TantoAddress1", null);
        }
        //住所２
        value = uiEntryOwnerTanto.getAddress2();
        if (!isNull(value)) {
            if (isLengthOver(value, 60)) {
                throw new NotRelevantException("受験票送付先 住所２", "全角３０文字以内", "TantoAddress2", null);
            }
            if (!zenkakuVerifier.validate(value)) {
                throw new InvalidInputException("受験票送付先 住所２", "TantoAddress2", null);
            }
        }
        //住所３
        value = uiEntryOwnerTanto.getAddress3();
        if (!isNull(value)) {
            if (isLengthOver(value, 60)) {
                throw new NotRelevantException("受験票送付先 住所３", "全角３０文字以内", "TantoAddress3", null);
            }
            if (!zenkakuVerifier.validate(value)) {
                throw new InvalidInputException("受験票送付先 住所３", "TantoAddress3", null);
            }
        }
        //送付先名
        value = uiEntryOwnerTanto.getSoufuName();
        if (isLengthOver(value, 40)) {
            throw new NotRelevantException("受験票送付先 送付先名", "全角２０文字以内", "TantoSoufuName", null);
        }
        if (!zenkakuVerifier.validate(value)) {
            throw new InvalidInputException("受験票送付先 送付先名", "TantoSoufuName", null);
        }
    }
    
    
    /**
     * エントリー状況のチェック
     */
    private void checkEntryState(UIEntryState entity, int index, String entryCd) {
        
        ZenkakuVerifier zenkakuVerifier = new ZenkakuVerifier();
        NumericVerifier numericVerifier = new NumericVerifier();
        
        String value = "";
        int elementIndex = index - 1;
        
        //受験番号
        value = entity.getExamNo();
        if(entity.getStateFlg().equals(MlEntryCommon.LIST_STATES_FLG_ENTRY_RECORD) ||
                entity.getStateFlg().equals(MlEntryCommon.LIST_STATES_FLG_NO_ENTRY_RECORD)) {
            if (isNull(value)) {
                throw new NotNullException(index + "：受験番号", "examNo", elementIndex);
            }
        }
        
        //受験希望地
        value = entity.getEntryPlaceCd();
        if (isNull(value)) {
            throw new NotNullException(index + "：受験希望地", "placeCd", elementIndex);
        }
        
        //職位
        value = entity.getJob();
        if (isNull(value)) {
            throw new NotNullException(index + "：職位", "job", elementIndex);
        }
        if (isLengthOver(value, 20)) {
            throw new NotRelevantException(index + "：職位", "全角１０文字以内", "job", elementIndex);
        }
        if (!zenkakuVerifier.validate(value)) {
            throw new InvalidInputException(index + "：職位", "job", elementIndex);
        }

        //店舗経験
        if (!checkTenpoExp(entity)) {
            throw new NotNullException(index + "：店舗経験", "empExpYear", elementIndex);
        }
        //社員歴 年
        value = entity.getEmpExpYear();
        if (isLengthOver(value, 2)) {
            throw new NotRelevantException(index + "：社員歴 年", "半角２文字以内", "empExpYear", elementIndex);
        }
        if (!numericVerifier.validate(value)) {
            throw new InvalidInputException(index + "：社員歴 年", "empExpYear", elementIndex);
        }
        //社員歴 月
        value = entity.getEmpExpMonth();
        if (isLengthOver(value, 2)) {
            throw new NotRelevantException(index + "：社員歴 月", "半角２文字以内", "empExpMonth", elementIndex);
        }
        if (!numericVerifier.validate(value)) {
            throw new InvalidInputException(index + "：社員歴 月", "empExpMonth", elementIndex);
        }
        if (isErrorMonth(value)) {
            throw new InvalidInputException(index + "：社員歴 月", "empExpMonth", elementIndex);
        }
        //パート歴 年
        value = entity.getPaExpYear();
        if (isLengthOver(value, 2)) {
            throw new NotRelevantException(index + "：パート歴 年", "半角２文字以内", "paExpYear", elementIndex);
        }
        if (!numericVerifier.validate(value)) {
            throw new InvalidInputException(index + "：パート歴 年", "paExpYear", elementIndex);
        }
        //パート歴 月
        value = entity.getPaExpMonth();
        if (isLengthOver(value, 2)) {
            throw new NotRelevantException(index + "：パート歴 月", "半角２文字以内", "paExpMonth", elementIndex);
        }
        if (!numericVerifier.validate(value)) {
            throw new InvalidInputException(index + "：パート歴 月", "paExpMonth", elementIndex);
        }
        if (isErrorMonth(value)) {
            throw new InvalidInputException(index + "：パート歴 月", "paExpMonth", elementIndex);
        }
        //備考
        value = entity.getNote();
        if (isLengthOver(value, 100)) {
            throw new NotRelevantException(index + "：備考", "全角５０文字以内", "note", elementIndex);
        }
        
        //エントリー項目
        String abilityChk = entity.getAbilityChk();
        String examChk = entity.getExamChk();
        String interviewChk = entity.getInterviewChk();
        if(!isNull(abilityChk) && !isNull(examChk) && !isNull(interviewChk)) {
            if((abilityChk.equals(MlEntryCommon.CHK_STATE_MENJO) || abilityChk.equals(MlEntryCommon.CHK_STATE_MIJYUKEN)) &&
                    (examChk.equals(MlEntryCommon.CHK_STATE_MENJO) || examChk.equals(MlEntryCommon.CHK_STATE_MIJYUKEN)) &&
                        (interviewChk.equals(MlEntryCommon.CHK_STATE_MENJO) || interviewChk.equals(MlEntryCommon.CHK_STATE_MIJYUKEN) ||
                            interviewChk.equals(MlEntryCommon.CHK_STATE_JYUKEN_FUKA))) {
                throw new GenericMessageException(index + "：受験するエントリー項目がありません。");
            }
        }
        
    }
    
    /**
     * 店舗経験入力チェック
     * @param UIEntryState
     */
    private boolean checkTenpoExp(UIEntryState entity) {
        String empYear = entity.getEmpExpYear();
        String empMonth = entity.getEmpExpMonth();
        String paYear = entity.getPaExpYear();
        String paMonth = entity.getPaExpMonth();
        
        if(isNull(empYear) && isNull(empMonth) && isNull(paYear) && isNull(paMonth)) {
            return false;
        }
        
        if (isNull(empYear)) {
            entity.setEmpExpYear("0");
        }
        if (isNull(empMonth)) {
            entity.setEmpExpMonth("0");
        }

        if (isNull(paYear)) {
            entity.setPaExpYear("0");
        }
        if (isNull(paMonth)) {
            entity.setPaExpMonth("0");
        }
        
        return true;
    }
    
    /*
     * 改行の置換え文字「`」を除去
     */
    private static String removeBackSlash(String source) {
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<source.length();i++) {
            if ( source.charAt(i) != '`' ) {
                sb.append(source.charAt(i));
            }
        }
        return sb.toString();
    }
    
    /**
	 * nullチェック
	 */
    private boolean isNull(String val) {
        if (val == null) {
            return true;
        }
        if ("".equals(val.trim())) {
            return true;
        }
        return false;
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
     * 月フォーマットチェック
     * @return boolean true;チェックNG
     */
    private boolean isErrorMonth(String month) {
        if(isNull(month)) return false;
        int intMnt = Integer.parseInt(month);
        if(0 > intMnt || intMnt > 12) {
            return true;
        }
        return false;
    }
    
}