package jp.co.isid.mos.bird.storemanage.msschousadataref.logic.impl;

import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.storemanage.msschousadataref.dto.MssChousaDataRefDto;
import jp.co.isid.mos.bird.storemanage.msschousadataref.logic.GetChousaDataLogic;

public class GetChousaDataLogicImpl implements GetChousaDataLogic{
    /*ユーザータイプ*/
    private static final String HONBU_USER = "01";
    /*LogicID*/
    public static final String LOGIC_ID =    "BSM011L02";

    public void execute(MssChousaDataRefDto dto){
        errCheck(dto);
    }
    /**
     * エラーチェックを行う
     * @param dto
     */
    private void errCheck(MssChousaDataRefDto dto) {
        CodeVerifier codeVerifier = new CodeVerifier();
        if (dto.getCompanyCd() == null) {
            throw new NotNullException("会社", "companyCd", 0);
        }
        if(dto.getSelectFlg()==1 && dto.getUsertypeCd().equals(HONBU_USER)){
            if(isNull(dto.getOnerCd())){
                throw new NotNullException("オーナーコード","onerT",null);
            }else{
                if(!codeVerifier.validate(dto.getOnerCd().trim())){
                    throw new InvalidInputException("オーナーコード","onerT",null);
                }
            }   
        }
        if(dto.getSelectFlg()==0){
            if(isNull(dto.getMiseCd())){
                throw new NotNullException("店舗コード","miseT",null);
            }else{
                if(!codeVerifier.validate(dto.getMiseCd().trim())){
                    throw new InvalidInputException("店舗コード","miseT",null);
                }
            }
        }
    
        if(!isNull(dto.getOnerCd())){
            if(dto.getOnerCd().getBytes().length > 5){
                throw new InvalidInputException("オーナーコード","onerT",null);
            }
        }
        if(!isNull(dto.getMiseCd())){
            if(dto.getMiseCd().getBytes().length > 5){
                throw new InvalidInputException("店舗コード","miseT",null);
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
    

