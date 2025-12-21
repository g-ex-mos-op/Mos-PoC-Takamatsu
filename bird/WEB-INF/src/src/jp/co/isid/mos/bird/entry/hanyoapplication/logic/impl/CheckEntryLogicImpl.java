/*
 * 作成日: 2006/06/07
 */
package jp.co.isid.mos.bird.entry.hanyoapplication.logic.impl;

import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.entry.hanyoapplication.common.HanyoApplicationCommon;
import jp.co.isid.mos.bird.entry.hanyoapplication.dto.HanyoApplicationDto;
import jp.co.isid.mos.bird.entry.hanyoapplication.entity.UIEntryOwner;
import jp.co.isid.mos.bird.entry.hanyoapplication.entity.UIEntryState;
import jp.co.isid.mos.bird.entry.hanyoapplication.logic.CheckEntryLogic;
import jp.co.isid.mos.bird.framework.exception.DuplicateDataException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotRelevantException;
import jp.co.isid.mos.bird.framework.util.verifier.NumericVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.ZenkakuVerifier;


/**
 * 登録内容チェック
 * @author kusama
 */
public class CheckEntryLogicImpl implements CheckEntryLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN005L01";


	/**
     * 登録内容のチェックを行う
     * @param HanyoEntryDto dto     
     * */
    public List execute(HanyoApplicationDto dto) {
    	// １．必須のパラメータが満たされていること。E0506（@パラメータ）
        validate(dto);

        // 重複チェック
        for (int i = 0; i < dto.getListEntryState().size(); i++) {
            UIEntryState uiEntryState = (UIEntryState) dto.getListEntryState().get(i);
            if (!"".equals(uiEntryState.getStaffId())) {
                for (int j = i + 1; j < dto.getListEntryState().size(); j++) {
                    UIEntryState uiEntryState2 = (UIEntryState) dto.getListEntryState().get(j);
                    if (uiEntryState.getStaffId() != null && uiEntryState2.getStaffId() != null) {
                        if (uiEntryState.getStaffId().equals(uiEntryState2.getStaffId())) {
// change start 20060716 inazawa 複数チェックの順番が0から数えているため実際の数より1小さい番号が表示される修正
//                            throw new DuplicateDataException(i + "番目と" + j + "番目のスタッフ");
                            throw new DuplicateDataException((i+1) + "番目と" + (j+1) + "番目のスタッフ");
// chnafe end                            
                        }
                    }
                }
            }
        }
        
        // 申込責任者情報のチェック
        checkMosikomiSekinin(dto);
        
        // 結果送付先情報のチェック
        if (dto.getEntryCd().equals(HanyoApplicationCommon.ENTRYCD_SHUTTYO)) {
            checkKekkaSofu(dto);
        }
        
        // エントリー状況情報のチェック
        int index = 1;
        for (Iterator ite = dto.getListEntryState().iterator(); ite.hasNext();) {
            UIEntryState uiEntryState = (UIEntryState) ite.next();
            if (!isNull(uiEntryState.getStaffId())) {
                checkEntryState(uiEntryState, index, dto.getEntryCd());
            }
            index++;
        }
        
        return null;
    }

    /**
     * 必須、妥当性チェック
     */
    private void validate(HanyoApplicationDto dto) {
    }

    /**
     * 申込責任者情報のチェック
     */
    private void checkMosikomiSekinin(HanyoApplicationDto dto) {
        ZenkakuVerifier zenkakuVerifier = new ZenkakuVerifier();
        String value = "";
        
        UIEntryOwner uiEntryOwnerMosikomiSekinin = dto.getUiEntryOwnerMosikomiSekinin();
        //氏名
        value = uiEntryOwnerMosikomiSekinin.getName();
        if (isNull(value)) {
            throw new NotNullException("申込責任者", "MosikomiSekininName", null);
        }
        if (isLengthOver(value, 40)) {
            throw new NotRelevantException("申込責任者", "全角２０文字以内", "MosikomiSekininName", null);
        }
        if (!zenkakuVerifier.validate(value)) {
            throw new InvalidInputException("申込責任者", "MosikomiSekininName", null);
        }
        //職位
        value = uiEntryOwnerMosikomiSekinin.getJobType();
        if (isNull(value)) {
            throw new NotNullException("職位", "MosikomiSekininJobType", null);
        }
        if (isLengthOver(value, 20)) {
            throw new NotRelevantException("職位", "全角１０文字以内", "MosikomiSekininJobType", null);
        }
        if (!zenkakuVerifier.validate(value)) {
            throw new InvalidInputException("職位", "MosikomiSekininJobType", null);
        }

    }
    /**
     * 結果送付先情報のチェック
     */
    private void checkKekkaSofu(HanyoApplicationDto dto) {
        ZenkakuVerifier zenkakuVerifier = new ZenkakuVerifier();
//        HankakuEisujiVerifier hankakuEisujiVerifier = new HankakuEisujiVerifier();
        NumericVerifier NumericVerifier = new NumericVerifier();
        
        String value = "";
        
        UIEntryOwner uiEntryOwnerKekkaHokokusaki = dto.getUiEntryOwnerMosikomiSekinin();
        //氏名
        value = uiEntryOwnerKekkaHokokusaki.getSoufuName();
        if (isNull(value)) {
            throw new NotNullException("結果報告先 氏名", "MosikomiSekininKekkaHokokuName", null);
        }
        if (isLengthOver(value, 40)) {
            throw new NotRelevantException("結果報告先 氏名", "全角２０文字以内", "MosikomiSekininKekkaHokokuName", null);
        }
        if (!zenkakuVerifier.validate(value)) {
            throw new InvalidInputException("結果報告先 氏名", "MosikomiSekininKekkaHokokuName", null);
        }
        //郵便番号
        value = uiEntryOwnerKekkaHokokusaki.getZip();
        if (isNull(value)) {
            throw new NotNullException("結果報告先 郵便番号", "MosikomiSekininKekkaHokokuZip", null);
        }
        if (isLengthOver(value, 8)) {
            throw new NotRelevantException("結果報告先 郵便番号", "半角８文字以内", "MosikomiSekininKekkaHokokuZip", null);
        }
        if (!NumericVerifier.validate(value)) {
            throw new InvalidInputException("結果報告先 郵便番号", "MosikomiSekininKekkaHokokuZip", null);
        }
        //住所１
        value = uiEntryOwnerKekkaHokokusaki.getAddress1();
        if (isNull(value)) {
            throw new NotNullException("結果報告先 住所１", "MosikomiSekininKekkaHokokuAddress1", null);
        }
        if (isLengthOver(value, 60)) {
            throw new NotRelevantException("結果報告先 住所１", "全角３０文字以内", "MosikomiSekininKekkaHokokuAddress1", null);
        }
        if (!zenkakuVerifier.validate(value)) {
            throw new InvalidInputException("結果報告先 住所１", "MosikomiSekininKekkaHokokuAddress1", null);
        }
        //住所２
        value = uiEntryOwnerKekkaHokokusaki.getAddress1();
//        if (isNull(value)) {
//            throw new NotNullException("結果報告先 住所２", "MosikomiSekininKekkaHokokuAddress1", null);
//        }
        if (isLengthOver(value, 60)) {
            throw new NotRelevantException("結果報告先 住所２", "全角３０文字以内", "MosikomiSekininKekkaHokokuAddress1", null);
        }
        if (!zenkakuVerifier.validate(value)) {
            throw new InvalidInputException("結果報告先 住所２", "MosikomiSekininKekkaHokokuAddress1", null);
        }
        //住所３
        value = uiEntryOwnerKekkaHokokusaki.getAddress1();
//        if (isNull(value)) {
//            throw new NotNullException("結果報告先 住所３", "MosikomiSekininKekkaHokokuAddress1", null);
//        }
        if (isLengthOver(value, 60)) {
            throw new NotRelevantException("結果報告先 住所３", "全角３０文字以内", "MosikomiSekininKekkaHokokuAddress1", null);
        }
        if (!zenkakuVerifier.validate(value)) {
            throw new InvalidInputException("結果報告先 住所３", "MosikomiSekininKekkaHokokuAddress1", null);
        }
    }
    
    /**
     * エントリー状況のチェック
     */
    private void checkEntryState(UIEntryState entity, int index, String entryCd) {
        ZenkakuVerifier zenkakuVerifier = new ZenkakuVerifier();
//        HankakuEisujiVerifier hankakuEisujiVerifier = new HankakuEisujiVerifier();
        NumericVerifier numericVerifier = new NumericVerifier();
        
        String value = "";
        int elementIndex = index - 1;

        //氏名
        value = entity.getStaffId();
        if (isNull(value)) {
            throw new NotNullException(index + "：氏名", "job", elementIndex);
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
//        if (isNull(value)) {
//            throw new NotNullException(index + "：社員歴 年", "empExpYear", elementIndex);
//        }
        if (isLengthOver(value, 2)) {
            throw new NotRelevantException(index + "：社員歴 年", "半角２文字以内", "empExpYear", elementIndex);
        }
        if (!numericVerifier.validate(value)) {
            throw new InvalidInputException(index + "：社員歴 年", "empExpYear", elementIndex);
        }
        //社員歴 月
        value = entity.getEmpExpMonth();
//        if (isNull(value)) {
//            throw new NotNullException(index + "：社員歴 月", "empExpMonth", elementIndex);
//        }
        if (isLengthOver(value, 2)) {
            throw new NotRelevantException(index + "：社員歴 月", "半角２文字以内", "empExpMonth", elementIndex);
        }
        if (!numericVerifier.validate(value)) {
            throw new InvalidInputException(index + "：社員歴 月", "empExpMonth", elementIndex);
        }
        //パート歴 年
        value = entity.getPaExpYear();
//        if (isNull(value)) {
//            throw new NotNullException(index + "：社員歴 年", "paExpYear", elementIndex);
//        }
        if (isLengthOver(value, 2)) {
            throw new NotRelevantException(index + "：パート歴 年", "半角２文字以内", "paExpYear", elementIndex);
        }
        if (!numericVerifier.validate(value)) {
            throw new InvalidInputException(index + "：パート歴 年", "paExpYear", elementIndex);
        }
        //パート歴 月
        value = entity.getPaExpMonth();
//        if (isNull(value)) {
//            throw new NotNullException(index + "：パート歴 月", "paExpMonth", elementIndex);
//        }
        if (isLengthOver(value, 2)) {
            throw new NotRelevantException(index + "：パート歴 月", "半角２文字以内", "paExpMonth", elementIndex);
        }
        if (!numericVerifier.validate(value)) {
            throw new InvalidInputException(index + "：パート歴 月", "paExpMonth", elementIndex);
        }
        // 受講案内送付先（出張研修のみ）
        if (entryCd.equals(HanyoApplicationCommon.ENTRYCD_SHUTTYO)) {
            //受講案内送付先区分
            String guideKbn = entity.getGuideKbn();
            if (guideKbn.equals(HanyoApplicationCommon.GUIDE_KBN_OTHER)) {
                //その他の送付先
// add start xkhata 20060822 必須チェック
              entity.setGuideName( entity.getGuideNameInput() );  
// add end
                value = entity.getGuideName();
                if (isNull(value)) {
                    throw new NotNullException(index + "：その他の送付先", "guideName", elementIndex);
                }
                if (isLengthOver(value, 40)) {
                    throw new NotRelevantException(index + "：その他の送付先", "全角２０文字以内", "guideName", elementIndex);
                }
                if (!zenkakuVerifier.validate(value)) {
                    throw new InvalidInputException(index + "：その他の送付先", "guideName", elementIndex);
                }
                //郵便番号
                value = entity.getGuideZip();
                if (isNull(value)) {
                    throw new NotNullException(index + "：郵便番号", "guideZip", elementIndex);
                }
                if (isLengthOver(value, 8)) {
                    throw new NotRelevantException(index + "：郵便番号", "半角８文字以内", "guideZip", elementIndex);
                }
                if (!numericVerifier.validate(value)) {
                    throw new InvalidInputException(index + "：郵便番号", "guideZip", elementIndex);
                }
                //住所１
                value = entity.getGuideAdrs1();
                if (isNull(value)) {
                    throw new NotNullException(index + "：住所１", "guideAdrs1", elementIndex);
                }
                if (isLengthOver(value, 60)) {
                    throw new NotRelevantException(index + "：住所１", "全角３０文字以内", "guideAdrs1", elementIndex);
                }
                if (!zenkakuVerifier.validate(value)) {
                    throw new InvalidInputException(index + "：住所１", "guideAdrs1", elementIndex);
                }
                //住所２
                value = entity.getGuideAdrs1();
//                if (isNull(value)) {
//                    throw new NotNullException(index + "：住所２", "guideAdrs2", elementIndex);
//                }
                if (isLengthOver(value, 60)) {
                    throw new NotRelevantException(index + "：住所２", "全角３０文字以内", "guideAdrs2", elementIndex);
                }
                if (!zenkakuVerifier.validate(value)) {
                    throw new InvalidInputException(index + "：住所２", "guideAdrs2", elementIndex);
                }
                //住所３
                value = entity.getGuideAdrs1();
//                if (isNull(value)) {
//                    throw new NotNullException(index + "：住所３", "guideAdrs3", elementIndex);
//                }
                if (isLengthOver(value, 60)) {
                    throw new NotRelevantException(index + "：住所３", "全角３０文字以内", "guideAdrs3", elementIndex);
                }
                if (!zenkakuVerifier.validate(value)) {
                    throw new InvalidInputException(index + "：住所３", "guideAdrs3", elementIndex);
                }
            }
        }
        
    }
    
    /**
     * 店舗経験入力チェック
     * @param empYear
     * @param empMonth
     * @param paYear
     * @param paMonth
     */
    private boolean checkTenpoExp(UIEntryState entity) {
        String empYear = entity.getEmpExpYear();
        String empMonth = entity.getEmpExpMonth();
        String paYear = entity.getPaExpYear();
        String paMonth = entity.getPaExpMonth();
        
        if(isNull(empYear) && isNull(empMonth) && isNull(paYear) && isNull(paMonth)) {
            return false;
        }
        
        if (!isNull(empYear) && isNull(empMonth)) {
            entity.setEmpExpMonth("0");
        }
        else if (isNull(empYear) && !isNull(empMonth)) {
            entity.setEmpExpYear("0");
        }
        else if (isNull(empYear) && isNull(empMonth)) {
            entity.setEmpExpYear("");
            entity.setEmpExpMonth("");
        }
        
        if (!isNull(paYear) && isNull(paMonth)) {
            entity.setPaExpMonth("0");
        }
        else if (isNull(paYear) && !isNull(paMonth)) {
            entity.setPaExpYear("0");
        }
        else if (isNull(paYear) && isNull(paMonth)) {
            entity.setPaExpMonth("");
            entity.setPaExpYear("");
        }
        
        return true;
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
}