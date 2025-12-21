/*
 * ì¬“ú: 2006/06/07
 */
package jp.co.isid.mos.bird.entry.basicentry.logic.impl;

import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.entry.basicentry.common.BasicEntryCommon;
import jp.co.isid.mos.bird.entry.basicentry.dto.BasicEntryDto;
import jp.co.isid.mos.bird.entry.basicentry.entity.UIEntryOner;
import jp.co.isid.mos.bird.entry.basicentry.entity.UIEntryState;
import jp.co.isid.mos.bird.entry.basicentry.logic.CheckEntryLogic;
import jp.co.isid.mos.bird.framework.exception.DuplicateDataException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotRelevantException;
import jp.co.isid.mos.bird.framework.util.verifier.HankakuEisujiVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.NumericVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.ZenkakuVerifier;

import org.seasar.framework.log.Logger;

/**
 * “o˜^“à—eƒ`ƒFƒbƒN
 * @author kusama
 */
public class CheckEntryLogicImpl implements CheckEntryLogic {

    /** ƒƒWƒbƒNID ’è‹` */
    public static final String LOGIC_ID = "BEN002L01";

    private static Logger logger_ = Logger.getLogger(CheckEntryLogicImpl.class);

	/**
     * “o˜^“à—e‚Ìƒ`ƒFƒbƒN‚ğs‚¤
     * @param HanyoEntryDto dto     
     */
    public List execute(BasicEntryDto dto) {
    	// ‚PD•K{‚Ìƒpƒ‰ƒ[ƒ^‚ª–‚½‚³‚ê‚Ä‚¢‚é‚±‚ÆBE0506i@ƒpƒ‰ƒ[ƒ^j
        validate(dto);

        // d•¡ƒ`ƒFƒbƒN
        for (int i = 0; i < dto.getListEntryState().size(); i++) {
            UIEntryState uiEntryState = (UIEntryState) dto.getListEntryState().get(i);
            if (!"".equals(uiEntryState.getStaffId())) {
                for (int j = i + 1; j < dto.getListEntryState().size(); j++) {
                    UIEntryState uiEntryState2 = (UIEntryState) dto.getListEntryState().get(j);
                    if (uiEntryState.getStaffId() != null && uiEntryState2.getStaffId() != null) {
                        if (uiEntryState.getStaffId().equals(uiEntryState2.getStaffId())) {
                            throw new DuplicateDataException((i + 1) + "”Ô–Ú‚Æ" + (j + 1) + "”Ô–Ú‚ÌƒXƒ^ƒbƒt");
                        }
                    }
                }
            }
        }
        
        // \Ó”CÒî•ñ‚Ìƒ`ƒFƒbƒN
        checkMosikomiSekinin(dto);
        
        // Œ‹‰Ê‘—•tæî•ñ‚Ìƒ`ƒFƒbƒN
        checkKekkaSofu(dto);
        
        // ƒGƒ“ƒgƒŠ[ó‹µî•ñ‚Ìƒ`ƒFƒbƒN
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
     * •K{A‘Ã“–«ƒ`ƒFƒbƒN
     */
    private void validate(BasicEntryDto dto) {
    }

    /**
     * \Ó”CÒî•ñ‚Ìƒ`ƒFƒbƒN
     */
    private void checkMosikomiSekinin(BasicEntryDto dto) {
        ZenkakuVerifier zenkakuVerifier = new ZenkakuVerifier();
        String value = "";
        
        UIEntryOner uiEntryOwnerMosikomiSekinin = dto.getUiEntryOnerMosikomiSekinin();
        //–¼
        value = uiEntryOwnerMosikomiSekinin.getName();
        if (isNull(value)) {
            throw new NotNullException("\Ó”CÒ", "MosikomiSekininName", null);
        }
        if (isLengthOver(value, 40)) {
            throw new NotRelevantException("\Ó”CÒ", "‘SŠp‚Q‚O•¶šˆÈ“à", "MosikomiSekininName", null);
        }
        if (!zenkakuVerifier.validate(value)) {
            throw new InvalidInputException("\Ó”CÒ", "MosikomiSekininName", null);
        }
        //EˆÊ
        value = uiEntryOwnerMosikomiSekinin.getJobType();
        if (isNull(value)) {
            throw new NotNullException("EˆÊ", "MosikomiSekininJobType", null);
        }
        if (isLengthOver(value, 20)) {
            throw new NotRelevantException("EˆÊ", "‘SŠp‚P‚O•¶šˆÈ“à", "MosikomiSekininJobType", null);
        }
        if (!zenkakuVerifier.validate(value)) {
            throw new InvalidInputException("EˆÊ", "MosikomiSekininJobType", null);
        }

    }
    /**
     * Œ‹‰Ê‘—•tæî•ñ‚Ìƒ`ƒFƒbƒN
     */
    private void checkKekkaSofu(BasicEntryDto dto) {
        ZenkakuVerifier zenkakuVerifier = new ZenkakuVerifier();
        HankakuEisujiVerifier hankakuEisujiVerifier = new HankakuEisujiVerifier();
        NumericVerifier numericVerifier = new NumericVerifier();
        
        String value = "";
        
        UIEntryOner uiEntryOwnerKekkaHokokusaki = dto.getUiEntryOnerMosikomiSekinin();
        //–¼
        value = uiEntryOwnerKekkaHokokusaki.getSoufuName();
        if (isNull(value)) {
            throw new NotNullException("Œ‹‰Ê•ñæ –¼", "MosikomiSekininKekkaHokokuName", null);
        }
        if (isLengthOver(value, 40)) {
            throw new NotRelevantException("Œ‹‰Ê•ñæ –¼", "‘SŠp‚Q‚O•¶šˆÈ“à", "MosikomiSekininKekkaHokokuName", null);
        }
        if (!zenkakuVerifier.validate(value)) {
            throw new InvalidInputException("Œ‹‰Ê•ñæ –¼", "MosikomiSekininKekkaHokokuName", null);
        }
        //—X•Ö”Ô†
        value = uiEntryOwnerKekkaHokokusaki.getZip();
        if (isNull(value)) {
            throw new NotNullException("Œ‹‰Ê•ñæ —X•Ö”Ô†", "MosikomiSekininKekkaHokokuZip", null);
        }
        if (isLengthOver(value, 8)) {
            throw new NotRelevantException("Œ‹‰Ê•ñæ —X•Ö”Ô†", "”¼Šp‚W•¶šˆÈ“à", "MosikomiSekininKekkaHokokuZip", null);
        }
// change start xkhata 20060821 ”’lƒ`ƒFƒbƒN
//        if (!hankakuEisujiVerifier.validate(value)) {
        if ( !numericVerifier.validate( value ) ) {
// change end
            throw new InvalidInputException("Œ‹‰Ê•ñæ —X•Ö”Ô†", "MosikomiSekininKekkaHokokuZip", null);
        }
        //ZŠ‚P
        value = uiEntryOwnerKekkaHokokusaki.getAddress1();
        if (isNull(value)) {
            throw new NotNullException("Œ‹‰Ê•ñæ ZŠ‚P", "MosikomiSekininKekkaHokokuAddress1", null);
        }
        if (isLengthOver(value, 60)) {
            throw new NotRelevantException("Œ‹‰Ê•ñæ ZŠ‚P", "‘SŠp‚R‚O•¶šˆÈ“à", "MosikomiSekininKekkaHokokuAddress1", null);
        }
        if (!zenkakuVerifier.validate(value)) {
            throw new InvalidInputException("Œ‹‰Ê•ñæ ZŠ‚P", "MosikomiSekininKekkaHokokuAddress1", null);
        }
        //ZŠ‚Q
        value = uiEntryOwnerKekkaHokokusaki.getAddress1();
//        if (isNull(value)) {
//            throw new NotNullException("Œ‹‰Ê•ñæ ZŠ‚Q", "MosikomiSekininKekkaHokokuAddress1", null);
//        }
        if (isLengthOver(value, 60)) {
            throw new NotRelevantException("Œ‹‰Ê•ñæ ZŠ‚Q", "‘SŠp‚R‚O•¶šˆÈ“à", "MosikomiSekininKekkaHokokuAddress1", null);
        }
        if (!zenkakuVerifier.validate(value)) {
            throw new InvalidInputException("Œ‹‰Ê•ñæ ZŠ‚Q", "MosikomiSekininKekkaHokokuAddress1", null);
        }
        //ZŠ‚R
        value = uiEntryOwnerKekkaHokokusaki.getAddress1();
//        if (isNull(value)) {
//            throw new NotNullException("Œ‹‰Ê•ñæ ZŠ‚R", "MosikomiSekininKekkaHokokuAddress1", null);
//        }
        if (isLengthOver(value, 60)) {
            throw new NotRelevantException("Œ‹‰Ê•ñæ ZŠ‚R", "‘SŠp‚R‚O•¶šˆÈ“à", "MosikomiSekininKekkaHokokuAddress1", null);
        }
        if (!zenkakuVerifier.validate(value)) {
            throw new InvalidInputException("Œ‹‰Ê•ñæ ZŠ‚R", "MosikomiSekininKekkaHokokuAddress1", null);
        }
    }
    
    /**
     * ƒGƒ“ƒgƒŠ[ó‹µ‚Ìƒ`ƒFƒbƒN
     */
    private void checkEntryState(UIEntryState entity, int index, String entryCd) {
        ZenkakuVerifier zenkakuVerifier = new ZenkakuVerifier();
        HankakuEisujiVerifier hankakuEisujiVerifier = new HankakuEisujiVerifier();
        NumericVerifier numericVerifier = new NumericVerifier();
        
        String value = "";
        int elementIndex = index - 1;

        //–¼
        value = entity.getStaffId();
        if (isNull(value)) {
            throw new NotNullException(index + "F–¼", "job", elementIndex);
        }
        
        //EˆÊ
        value = entity.getJob();
        if (isNull(value)) {
            throw new NotNullException(index + "FEˆÊ", "job", elementIndex);
        }
        if (isLengthOver(value, 20)) {
            throw new NotRelevantException(index + "FEˆÊ", "‘SŠp‚P‚O•¶šˆÈ“à", "job", elementIndex);
        }
        if (!zenkakuVerifier.validate(value)) {
            throw new InvalidInputException(index + "FEˆÊ", "job", elementIndex);
        }

        //“X•ÜŒoŒ±
        if (!checkTenpoExp(entity)) {
            throw new NotNullException(index + "F“X•ÜŒoŒ±", "empExpYear", elementIndex);
        }
        //Ğˆõ—ğ ”N
        value = entity.getEmpExpYear();
//        if (isNull(value)) {
//            throw new NotNullException(index + "FĞˆõ—ğ ”N", "empExpYear", elementIndex);
//        }
        if (isLengthOver(value, 2)) {
            throw new NotRelevantException(index + "FĞˆõ—ğ ”N", "”¼Šp‚Q•¶šˆÈ“à", "empExpYear", elementIndex);
        }
        if (!numericVerifier.validate(value)) {
            throw new InvalidInputException(index + "FĞˆõ—ğ ”N", "empExpYear", elementIndex);
        }
        //Ğˆõ—ğ Œ
        value = entity.getEmpExpMonth();
//        if (isNull(value)) {
//            throw new NotNullException(index + "FĞˆõ—ğ Œ", "empExpMonth", elementIndex);
//        }
        if (isLengthOver(value, 2)) {
            throw new NotRelevantException(index + "FĞˆõ—ğ Œ", "”¼Šp‚Q•¶šˆÈ“à", "empExpMonth", elementIndex);
        }
        if (!numericVerifier.validate(value)) {
            throw new InvalidInputException(index + "FĞˆõ—ğ Œ", "empExpMonth", elementIndex);
        }
        //ƒp[ƒg—ğ ”N
        value = entity.getPaExpYear();
//        if (isNull(value)) {
//            throw new NotNullException(index + "FĞˆõ—ğ ”N", "paExpYear", elementIndex);
//        }
        if (isLengthOver(value, 2)) {
            throw new NotRelevantException(index + "Fƒp[ƒg—ğ ”N", "”¼Šp‚Q•¶šˆÈ“à", "paExpYear", elementIndex);
        }
        if (!numericVerifier.validate(value)) {
            throw new InvalidInputException(index + "Fƒp[ƒg—ğ ”N", "paExpYear", elementIndex);
        }
        //ƒp[ƒg—ğ Œ
        value = entity.getPaExpMonth();
//        if (isNull(value)) {
//            throw new NotNullException(index + "Fƒp[ƒg—ğ Œ", "paExpMonth", elementIndex);
//        }
        if (isLengthOver(value, 2)) {
            throw new NotRelevantException(index + "Fƒp[ƒg—ğ Œ", "”¼Šp‚Q•¶šˆÈ“à", "paExpMonth", elementIndex);
        }
        if (!numericVerifier.validate(value)) {
            throw new InvalidInputException(index + "Fƒp[ƒg—ğ Œ", "paExpMonth", elementIndex);
        }
        //óuˆÄ“à‘—•tæ‹æ•ª
        String guideKbn = entity.getGuideKbn();
        if (guideKbn.equals(BasicEntryCommon.GUIDE_KBN_OTHER)) {
            //‚»‚Ì‘¼‚Ì‘—•tæ
// add start xkhata 20060821 ‚»‚Ì‘¼‚Ì‘—•tæ•K{ƒ`ƒFƒbƒN
            entity.setGuideName( entity.getGuideNameInput() );
// add end
            value = entity.getGuideName();
            if (isNull(value)) {
                throw new NotNullException(index + "F‚»‚Ì‘¼‚Ì‘—•tæ", "guideName", elementIndex);
            }
            if (isLengthOver(value, 40)) {
                throw new NotRelevantException(index + "F‚»‚Ì‘¼‚Ì‘—•tæ", "‘SŠp‚Q‚O•¶šˆÈ“à", "guideName", elementIndex);
            }
            if (!zenkakuVerifier.validate(value)) {
                throw new InvalidInputException(index + "F‚»‚Ì‘¼‚Ì‘—•tæ", "guideName", elementIndex);
            }
            //—X•Ö”Ô†
            value = entity.getGuideZip();
            if (isNull(value)) {
                throw new NotNullException(index + "F—X•Ö”Ô†", "guideZip", elementIndex);
            }
            if (isLengthOver(value, 8)) {
                throw new NotRelevantException(index + "F—X•Ö”Ô†", "”¼Šp‚W•¶šˆÈ“à", "guideZip", elementIndex);
            }
// change start xkhata 20060821 ”’lƒ`ƒFƒbƒN
//            if (!hankakuEisujiVerifier.validate(value)) {
            if ( !numericVerifier.validate( value ) ) {
// change end
                throw new InvalidInputException(index + "F—X•Ö”Ô†", "guideZip", elementIndex);
            }
            //ZŠ‚P
            value = entity.getGuideAdrs1();
            if (isNull(value)) {
                throw new NotNullException(index + "FZŠ‚P", "guideAdrs1", elementIndex);
            }
            if (isLengthOver(value, 60)) {
                throw new NotRelevantException(index + "FZŠ‚P", "‘SŠp‚R‚O•¶šˆÈ“à", "guideAdrs1", elementIndex);
            }
            if (!zenkakuVerifier.validate(value)) {
                throw new InvalidInputException(index + "FZŠ‚P", "guideAdrs1", elementIndex);
            }
            //ZŠ‚Q
            value = entity.getGuideAdrs1();
            if (isLengthOver(value, 60)) {
                throw new NotRelevantException(index + "FZŠ‚Q", "‘SŠp‚R‚O•¶šˆÈ“à", "guideAdrs2", elementIndex);
            }
            if (!zenkakuVerifier.validate(value)) {
                throw new InvalidInputException(index + "FZŠ‚Q", "guideAdrs2", elementIndex);
            }
            //ZŠ‚R
            value = entity.getGuideAdrs1();
            if (isLengthOver(value, 60)) {
                throw new NotRelevantException(index + "FZŠ‚R", "‘SŠp‚R‚O•¶šˆÈ“à", "guideAdrs3", elementIndex);
            }
            if (!zenkakuVerifier.validate(value)) {
                throw new InvalidInputException(index + "FZŠ‚R", "guideAdrs3", elementIndex);
            }
        }
        // ãi–¼
        value = entity.getBossName();
        if (isNull(value)) {
            throw new NotNullException(index + "Fãi –¼", "bossName", elementIndex);
        }
        if (isLengthOver(value, 40)) {
            throw new NotRelevantException(index + "Fãi –¼", "‘SŠp‚Q‚O•¶šˆÈ“à", "bossName", elementIndex);
        }
        if (!zenkakuVerifier.validate(value)) {
            throw new InvalidInputException(index + "Fãi –¼", "bossName", elementIndex);
        }
        // ãiŠ‘®
        value = entity.getBossGroup();
        if (isNull(value)) {
            throw new NotNullException(index + "Fãi Š‘®", "bossGroup", elementIndex);
        }
        if (isLengthOver(value, 20)) {
            throw new NotRelevantException(index + "Fãi Š‘®", "‘SŠp‚P‚O•¶šˆÈ“à", "bossGroup", elementIndex);
        }
        if (!zenkakuVerifier.validate(value)) {
            throw new InvalidInputException(index + "Fãi Š‘®", "bossGroup", elementIndex);
        }
        // ãiEˆÊ
        value = entity.getBossJobType();
        if (isNull(value)) {
            throw new NotNullException(index + "Fãi EˆÊ", "bossJobType", elementIndex);
        }
        if (isLengthOver(value, 20)) {
            throw new NotRelevantException(index + "Fãi EˆÊ", "‘SŠp‚P‚O•¶šˆÈ“à", "bossJobType", elementIndex);
        }
        if (!zenkakuVerifier.validate(value)) {
            throw new InvalidInputException(index + "Fãi EˆÊ", "bossJobType", elementIndex);
        }
        // ãiƒRƒƒ“ƒg
        value = entity.getBossComment();
        if (isNull(value)) {
            throw new NotNullException(index + "Fãi ƒRƒƒ“ƒg", "bossComment", elementIndex);
        }
        if (isLengthOver(value, 240)) {
            throw new NotRelevantException(index + "Fãi ƒRƒƒ“ƒg", "‘SŠp‚P‚Q‚O•¶šˆÈ“à", "bossComment", elementIndex);
        }
        if (!zenkakuVerifier.validate(removeBackSlash(value))) {
            throw new InvalidInputException(index + "Fãi ƒRƒƒ“ƒg", "bossComment", elementIndex);
        }
    }
    
    /**
     * “X•ÜŒoŒ±“ü—Íƒ`ƒFƒbƒN
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
    
    /*
     * ‰üs‚Ì’uŠ·‚¦•¶šu`v‚ğœ‹
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
	 * nullƒ`ƒFƒbƒN
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
     * ƒŒƒ“ƒOƒXƒ`ƒFƒbƒN
     * @return boolean true:ƒ`ƒFƒbƒNNG
     */
    private boolean isLengthOver(String val, int length) {
        if (val == null) {
            return false;
        }
         return val.trim().getBytes().length > length ? true : false;
    }
}