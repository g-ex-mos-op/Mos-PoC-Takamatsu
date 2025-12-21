/*
 * 作成日: 2006/12/01
 *
 */
package jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.logic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.code.ConditionTaishoJoken;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.code.UserType;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.dao.UIAutoAmountDataDao;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.entity.UIExistCode;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.logic.CheckCodeExistLogic;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.logic.SearchLogic;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.util.BunsAutoAmtRegistUtil;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.HankakuEisujiVerifier;

/**
 * 【検索対象情報取得】ロジック
 * 
 * @author xkinu
 */
public class SearchLogicImpl implements SearchLogic {

    /** ロジックID */
    public static final String LOGIC_ID = BunsAutoAmtRegistUtil.SCREEN_ID+"L02";
    /**
     *  ロジック【SV存在チェック】
     */
    private CheckCodeExistLogic BunsAutoAmtUtilCheckCodeExistLogic;
    /**
     * DAO【バンズ自動設定情報】
     */
    private UIAutoAmountDataDao BunsAutoAmtUtilUIAutoAmountDataDao;

    /** 

    /**
     * 事前条件処理
     * 
     * @param map
     * @throws ApplicationException
     */
    private void validate(final Map params, Map rparam) {
        BirdUserInfo userInfo = (BirdUserInfo)params.get(PK_USERINFO);
        if(userInfo == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("ユーザー情報");
        }
        //システム日付
        String sysDate = (String)params.get(PK_SYSDATE);
        if (BunsAutoAmtRegistUtil.isNull(sysDate)) {
            throw new MissingDataException("システム日付");
        }
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
        String taishoCd = null;
        if(ConditionTaishoJoken.VALUE_ONER.equals(taishouJoken)){
            taishoCd = (String)params.get(PK_ONER_CD);
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
            taishoCd = (String)params.get(PK_MISE_CD);
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
            taishoCd = (String)params.get(PK_SV_CD);
            if(BunsAutoAmtRegistUtil.isNull(taishoCd)){
                throw new NoInputException("SVコード", "targetSvCd", 0);
            }
            //SVコード妥当性チェック
            HankakuEisujiVerifier verifierEisuji = new HankakuEisujiVerifier();
            if(!verifierEisuji.validate(taishoCd) || taishoCd.length() > 8){
                throw new GenericMessageException("SVコードは半角英数字8桁以内で入力してください。", "targetSvCd", 0);               
            }
        }
        //ロジック【SVコード存在チェック】を実行する。
        Map logicParams = new HashMap();
        logicParams.put(CheckCodeExistLogicImpl.PK_COMPANY_CD, companyCd);
        logicParams.put(CheckCodeExistLogicImpl.PK_TAISHOJOKEN, taishouJoken);                
        logicParams.put(CheckCodeExistLogicImpl.PK_TAISHO_CD, taishoCd);                
        List targetCdInfo = getBunsAutoAmtRegistCheckCodeExistLogic().execute(logicParams);
        UIExistCode eCode = (UIExistCode)targetCdInfo.get(0);
        rparam.put(RK_TAEGETCD_NAME, eCode.getTargetNameKj());
    }
    /**
     * 実行処理
     */
    public Map execute(final Map params) {
        //２．リターン値Mapをインスタンス化する。
        Map rparam = new HashMap();
        //１．事前条件処理を実行する。
        validate(params, rparam);
        //ユーザー情報
        BirdUserInfo userInfo = (BirdUserInfo)params.get(PK_USERINFO);
        boolean limitKbn = userInfo.isLimit();
        if(!UserType.HONBU.equals(userInfo.getMstUser().getUserTypeCd())) {
            //本部ユーザー以外は支部制限を無効にする。
            limitKbn = false;
        }
        String userId = userInfo.getUserID();
        //システム日付
        String sysDate = (String)params.get(PK_SYSDATE);
        //会社コード
        String companyCd = (String)params.get(PK_COMPANY_CD);
        //パラメーター対象条件
        String taishoJoken = (String)params.get(PK_TAISHOJOKEN);
        //パラメーターオーナーコード
        String onerCd = (String)params.get(PK_ONER_CD);
        //パラメーター店舗コード
        String miseCd = (String)params.get(PK_MISE_CD);
        //パラメーターSVコード
        String svCd = (String)params.get(PK_SV_CD);
        //３．DAO【バンズ自動設定情報】.検索を実行し、実行結果[検索結果データ]]をリターン値Mapへ格納する。
        List listSearchData = getBunsAutoAmtRegistUIAutoAmountDataDao().select(
                userId, limitKbn, sysDate, companyCd, taishoJoken, onerCd, miseCd, svCd);
        rparam.put(RK_LIST_SEARCHDATA, listSearchData);
        
        //４．検索結果が１件以上の場合は以下の処理を行います。
        if(listSearchData != null && listSearchData.size() > 0) {
            //４－１．DAO【バンズ自動設定情報】.店舗一覧検索を実行する。
            List listMiseList = getBunsAutoAmtRegistUIAutoAmountDataDao().selectMiseList(
                    userId, limitKbn, sysDate, companyCd, taishoJoken, onerCd, miseCd, svCd);
            //４－２．処理４－２で作成した店舗一覧をリターン値Mapへ格納します。
            rparam.put(RK_LIST_SEARCHDATA_MISELIST, listMiseList);
        }
        //５．リターン値Mapをリターンする。
        return rparam;
    }
    /**
     * @return BunsAutoAmtUtilUIAutoAmountDataDao を戻します。
     */
    public UIAutoAmountDataDao getBunsAutoAmtRegistUIAutoAmountDataDao() {
        return BunsAutoAmtUtilUIAutoAmountDataDao;
    }
    /**
     * @param dao 設定する BunsAutoAmtUtilUIAutoAmountDataDao。
     */
    public void setBunsAutoAmtRegistUIAutoAmountDataDao(UIAutoAmountDataDao dao) {
        this.BunsAutoAmtUtilUIAutoAmountDataDao = dao;
    }
    /**
     * @return BunsAutoAmtUtilCheckCodeExistLogic を戻します。
     */
    public CheckCodeExistLogic getBunsAutoAmtRegistCheckCodeExistLogic() {
        return BunsAutoAmtUtilCheckCodeExistLogic;
    }
    /**
     * @param checkCodeExistLogic 設定する BunsAutoAmtUtilCheckCodeExistLogic。
     */
    public void setBunsAutoAmtRegistCheckCodeExistLogic(
            CheckCodeExistLogic checkCodeExistLogic) {
        this.BunsAutoAmtUtilCheckCodeExistLogic = checkCodeExistLogic;
    }
}
