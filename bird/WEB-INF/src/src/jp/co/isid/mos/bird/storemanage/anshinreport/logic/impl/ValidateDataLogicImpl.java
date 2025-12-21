package jp.co.isid.mos.bird.storemanage.anshinreport.logic.impl;

import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.storemanage.anshinreport.code.TaishoJoken;
import jp.co.isid.mos.bird.storemanage.anshinreport.dto.AnshinReportDto;
import jp.co.isid.mos.bird.storemanage.anshinreport.logic.ValidateDataLogic;

public class ValidateDataLogicImpl implements ValidateDataLogic{
    /*ユーザータイプ*/
    private static final String HONBU_USER = "01";
    /*LogicID*/
    public static final String LOGIC_ID =    "BSM018L05";

    public void execute(AnshinReportDto dto){
        errCheck(dto);
    }
    /**
     * エラーチェックを行う
     * @param dto
     */
    private void errCheck(AnshinReportDto dto) {
        CodeVerifier codeVerifier = new CodeVerifier();
        if (dto.getCompanyCd() == null) {
            dto.clearList();
            throw new NotNullException("会社", "companyCd", 0);
        }
        if(dto.getTaishoJoken().equals(TaishoJoken.CODE_ONER) && dto.getUsertypeCd().equals(HONBU_USER)){
            if(isNull(dto.getOnerCd())){
                dto.clearList();
                throw new NotNullException("オーナーコード","onerT",null);
            }else{
                if(!codeVerifier.validate(dto.getOnerCd().trim())){
                    dto.clearList();
                    throw new InvalidInputException("オーナーコード","onerT",null);
                }
            }   
        }
        if(dto.getTaishoJoken().equals(TaishoJoken.CODE_MISE)){
            if(isNull(dto.getMiseCd())){
                dto.clearList();
                throw new NotNullException("店コード","miseT",null);
            }else{
                if(!codeVerifier.validate(dto.getMiseCd().trim())){
                    dto.clearList();
                    throw new InvalidInputException("店コード","miseT",null);
                }
            }
        }
    
        if(!isNull(dto.getOnerCd())){
            if(dto.getOnerCd().getBytes().length > 5){
                dto.clearList();
                throw new InvalidInputException("オーナーコード","onerT",null);
            }
        }
        if(!isNull(dto.getMiseCd())){
            if(dto.getMiseCd().getBytes().length > 5){
                dto.clearList();
                throw new InvalidInputException("店コード","miseT",null);
            }
        }

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
}
    

