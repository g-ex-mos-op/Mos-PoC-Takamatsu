/*
 * 作成日:2007/01/17
 */
package jp.co.isid.mos.bird.entry.ownerchange.logic.impl;

import jp.co.isid.mos.bird.entry.ownerchange.logic.CheckRegistOwnerLogic;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NotSelectedException;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;


/**
 * 登録前オーナーチェック
 * @author xkonishi
 *
 */
public class CheckRegistOwnerLogicImpl implements CheckRegistOwnerLogic {
    
    /**
     * ロジックID定義
     */
    public static final String LOGIC_ID = "BEN023L08";

    /**
     * 登録前オーナーチェック
     * @param オーナーコード
     * @param 更新後オーナーコード
     */
    public void execute(String oldOnerCd, String newOnerCd, String moveDt) {

        // 変更後オーナーコードが未入力の場合
        if(newOnerCd == null || newOnerCd.length() == 0){
            throw new NotSelectedException("オーナー");
        }
        
        // 妥当性チェック
        CodeVerifier codeVerifier = new CodeVerifier(5, false);
        codeVerifier.setNullable(false);
        
        if (!codeVerifier.validate(newOnerCd)) {
            throw new InvalidInputException("オーナー");
        }
        
        // 変更前と変更後のオーナーコードが同じ場合
        if(oldOnerCd.equals(newOnerCd)){
            throw new GenericMessageException("オーナーを変更してください。");
        }
        
        // 異動日が未入力の場合
        if(moveDt == null || moveDt.length() == 0){
            throw new NoInputException("異動日");
        }

        // 異動日フォーマットチェック
        DateVerifier dateVerifier = new DateVerifier(DateVerifier.DATE_TYPE_YMD);
        dateVerifier.setNullable(false);
        
        if(!dateVerifier.validate(moveDt)){
            throw new InvalidInputException("異動日");
        }
    }
}