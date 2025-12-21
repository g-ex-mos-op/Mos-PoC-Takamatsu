/*
 * 作成日:2007/01/17
 */
package jp.co.isid.mos.bird.entry.ownerchange.logic.impl;

import jp.co.isid.mos.bird.entry.ownerchange.common.OwnerChangeConstants;
import jp.co.isid.mos.bird.entry.ownerchange.dto.OwnerChangeDto;
import jp.co.isid.mos.bird.entry.ownerchange.logic.CheckInputLogic;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;

/**
 * 入力値チェックロジック
 * @author xkonishi
 *
 */
public class CheckInputLogicImpl implements CheckInputLogic {

    /**
     * ロジックID定義
     */
    public static final String LOGIC_ID = "BEN023L03";
    
    public void execute(OwnerChangeDto ownerChangeDto) {
        
        /**
         * 会社コード
         */
        // 必須チェック
        if(ownerChangeDto.getCompanyCd() == null || 
           ownerChangeDto.getCompanyCd().length() == 0) {
            throw new NoInputException("会社コード", "companyList", null);            
        }
        // 妥当性チェック
        CodeVerifier companyCdVerifier = new CodeVerifier(2, false);
        companyCdVerifier.setNullable(false);
        
        if (!companyCdVerifier.validate(ownerChangeDto.getCompanyCd())) {
            throw new InvalidInputException("会社コード", "companyList", null);
        }
        
        // 店コード･オーナーコード用妥当性チェック設定
        // 5桁・英字不可・null不可
        CodeVerifier codeVerifier = new CodeVerifier(5, false);
        codeVerifier.setNullable(false);
        
        /**
         * 店コード
         * 
         **/
        if(ownerChangeDto.getKbn().equals(OwnerChangeConstants.KBN_MISE)){
            // 必須チェック
            if(ownerChangeDto.getMiseCd() == null || 
               ownerChangeDto.getMiseCd().length() == 0) {
                throw new NoInputException("店舗コード", "miseText", null);
            }
            // 妥当性チェック
            if (!codeVerifier.validate(ownerChangeDto.getMiseCd())) {
                throw new InvalidInputException("店舗コード", "miseText", null);
            }
        }
        
        /**
         * オーナーコード
         */
        if(ownerChangeDto.getKbn().equals(OwnerChangeConstants.KBN_OWNER)){
            // 必須チェック
            if(ownerChangeDto.getOnerCd() == null ||
               ownerChangeDto.getOnerCd().length() == 0) {
                throw new NoInputException("オーナーコード", "ownerText", null);
            }
            // 妥当性チェック
            if (!codeVerifier.validate(ownerChangeDto.getOnerCd())) {
                throw new InvalidInputException("オーナーコード", "ownerText", null);
            }
        }    
    }
}