/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.logic.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.entity.UIAutoAmountData;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.logic.CheckInputDataLogic;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.util.BunsAutoAmtRegistUtil;
import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.util.MosChickenStateConfirmUtil;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.InputConstraintException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.util.verifier.NumericVerifier;

/**
 * バンズ自動設定数量変更
 * 入力値チェック ロジック
 * @author xkinu
 *
 */
public class CheckInputDataImpl implements CheckInputDataLogic {
    /** ロジックID */
    public static final String LOGIC_ID = BunsAutoAmtRegistUtil.SCREEN_ID+"L04";

    /* (非 Javadoc)
     * @see jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.logic.CheckInputDataLogic#execute(java.util.Map)
     */
    public String execute(Map params) {
        //１．事前条件判断処理
        validate(params);
        List listRegData = (List)params.get(PK_LIST_REGDATA);
        for (int i=0; i<listRegData.size(); i++) {
            UIAutoAmountData entity = (UIAutoAmountData)listRegData.get(i);
            checkingAmt(entity.getStrAmtWeek(), "strAmtWeek", "平日納品", i);
            checkingAmt(entity.getStrAmtSatd(), "strAmtSatd", "土曜納品", i);
            checkingAmt(entity.getStrAmtHold(), "strAmtHold", "日祝納品", i);
        }
        
        
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }
    /**
     * 事前条件処理
     * @param map
     * @throws ApplicationException
     */
    private void validate(final Map params) {
        List listRegData = (List)params.get(PK_LIST_REGDATA);
        if(listRegData == null) {
            throw new MissingDataException("登録対象データ");
        }
    }
    /**
     * 数値チェック処理
     * 
     * チェック１．空(orNull)でないか？　falseの場合は MSG　NoInputExceptionを発生させる。
     * チェック２．半角数字のみ入力されているか？falseの場合は MSG　InputConstraintExceptionを発生させる。
     * チェック３．ゼロより大きいか？　
     * 
     * @param strAmt
     * @param htmlLabel
     * @param htmlName
     * @param htmlIndex
     */
    private static String checkingAmt(String strAmt, String htmlName, String htmlLabel, int htmlIndex){
        NumericVerifier numVer = new NumericVerifier(true, 2, 0);
        if(MosChickenStateConfirmUtil.isNull(strAmt)){
            //MSG【E0507】○○を入力してください。
            throw new NoInputException(htmlLabel, htmlName, htmlIndex);
        }
        if(!numVer.validate(strAmt)){
            // MSG【E0508】
            throw new InputConstraintException(htmlLabel, "半角数字", htmlName, htmlIndex);
        }
        try{
            BigDecimal decAmt = new BigDecimal(strAmt);
            if(decAmt.compareTo(new BigDecimal("0")) < 0){
                //マイナス不可
                //MSG【E0508】
                throw new InputConstraintException(htmlLabel, "0以上", htmlName, htmlIndex);
            }
        }
        catch(NumberFormatException numEx){
        }
        return strAmt;
        
    }

}
