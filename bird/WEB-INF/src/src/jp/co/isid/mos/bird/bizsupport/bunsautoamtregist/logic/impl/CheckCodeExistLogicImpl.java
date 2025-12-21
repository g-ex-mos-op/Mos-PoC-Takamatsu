/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.logic.impl;

import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.code.ConditionTaishoJoken;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.dao.UIExistCodeDao;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.logic.CheckCodeExistLogic;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.util.BunsAutoAmtRegistUtil;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.HankakuEisujiVerifier;

/**
 * 【コード存在チェック】ロジック
 * 
 * @author xkinu
 *
 */
public class CheckCodeExistLogicImpl implements CheckCodeExistLogic {
    /** ロジックID */
    public static final String LOGIC_ID = BunsAutoAmtRegistUtil.SCREEN_ID+"L03";
    /**
     * DAO【存在コード情報】
     */
    private UIExistCodeDao bunsAutoAmtRegisttUIExistCodeDao;
    /** 
     * 実行処理
     * 
     * @param params
     * @return 前ゼロ付加のSVコード
     * @see jp.co.isid.mos.bird.entry.nationalviewlist.logic.CheckSvExistLogic#execute(java.util.Map)
     */
    public List execute(Map params) {
        //１．事前条件判断処理
        validate(params);
        //会社コード
        String companyCd = (String)params.get(PK_COMPANY_CD);
        //パラメーター対象条件
        String taishouJoken = (String)params.get(PK_TAISHOJOKEN);
        String taishoCd = (String)params.get(PK_TAISHO_CD);
        //２．DAO【存在コード情報】を実行する。
        List list = null;
        if(ConditionTaishoJoken.VALUE_ONER.equals(taishouJoken)){
            list = getBunsAutoAmtRegisttUIExistCodeDao().selectOner(companyCd, taishoCd);
            //３．存在チェックを行います。
            if(list == null || list.size() < 1){
                //MSG【E0103】’が存在しません。’
                throw new NotExistException("該当オーナーコード", "targetOnerCd",0);
            } 
        }
        else if(ConditionTaishoJoken.VALUE_MISE.equals(taishouJoken)){
            list = getBunsAutoAmtRegisttUIExistCodeDao().selectMise(companyCd, taishoCd);
            //３．存在チェックを行います。
            if(list == null || list.size() < 1){
                //MSG【E0103】’が存在しません。’
                throw new NotExistException("該当店舗コード", "targetMiseCd",0);
            } 
        }
        else if(ConditionTaishoJoken.VALUE_SV.equals(taishouJoken)){
            list = getBunsAutoAmtRegisttUIExistCodeDao().selectSv(companyCd, taishoCd);
            //３．存在チェックを行います。
            if(list == null || list.size() < 1){
                //MSG【E0103】’が存在しません。’
                throw new NotExistException("該当SVコード", "targetSvCd",0);
            } 
        }
        return list;
    }
    /**
     * 事前条件処理
     * @param map
     * @throws ApplicationException
     */
    private void validate(final Map params) {
        //会社コード
        String companyCd = (String)params.get(PK_COMPANY_CD);
        if (BunsAutoAmtRegistUtil.isNull(companyCd)) {
            throw new MissingDataException("会社コード");
        }
        //パラメーター対象条件必須チェック
        String taishouJoken = (String)params.get(PK_TAISHOJOKEN);
        if(BunsAutoAmtRegistUtil.isNull(taishouJoken)){
            throw new NotNullException("対象条件", "targetTaishouJoken", 0);
        }
        String taishoCd = (String)params.get(PK_TAISHO_CD);
        if(ConditionTaishoJoken.VALUE_ONER.equals(taishouJoken)){
            if(BunsAutoAmtRegistUtil.isNull(taishoCd)){
                throw new NotNullException("オーナーコード", "targetOnerCd", 0);
            }
            //オーナーコード妥当性チェック
            CodeVerifier codeVerifier = new CodeVerifier();
            if(!codeVerifier.validate(taishoCd) || taishoCd.length() > 5){
                throw new GenericMessageException("オーナーコードは半角数字5桁以内で入力してください。", "targetOnerCd", 0);               
            }
        }
        else if(ConditionTaishoJoken.VALUE_MISE.equals(taishouJoken)){
            if(BunsAutoAmtRegistUtil.isNull(taishoCd)){
                throw new NotNullException("店舗コード", "targetMiseCd", 0);
            }
            //店舗コード妥当性チェック
            CodeVerifier codeVerifier = new CodeVerifier();
            if(!codeVerifier.validate(taishoCd) || taishoCd.length() > 5){
                throw new GenericMessageException("店舗コードは半角数字5桁以内で入力してください。", "targetMiseCd", 0);               
            }
        }
        else if(ConditionTaishoJoken.VALUE_SV.equals(taishouJoken)){
            //SVコード必須チェック
            if(BunsAutoAmtRegistUtil.isNull(taishoCd)){
                throw new NoInputException("SVコード", "targetSvCd", 0);
            }
            //SVコード妥当性チェック
            HankakuEisujiVerifier verifierEisuji = new HankakuEisujiVerifier();
            if(!verifierEisuji.validate(taishoCd) || taishoCd.length() > 8){
                throw new GenericMessageException("SVコードは半角英数字8桁以内で入力してください。", "targetSvCd", 0);               
            }
        }
    }
    /**
     * @return bunsAutoAmtRegisttUIExistCodeDao を戻します。
     */
    public UIExistCodeDao getBunsAutoAmtRegisttUIExistCodeDao() {
        return bunsAutoAmtRegisttUIExistCodeDao;
    }
    /**
     * @param bunsAutoAmtRegisttUIExistCodeDao 設定する bunsAutoAmtRegisttUIExistCodeDao。
     */
    public void setBunsAutoAmtRegisttUIExistCodeDao(
            UIExistCodeDao bunsAutoAmtRegisttUIExistCodeDao) {
        this.bunsAutoAmtRegisttUIExistCodeDao = bunsAutoAmtRegisttUIExistCodeDao;
    }
}
