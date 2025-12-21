/*
 * 作成日: 2006/12/01
 *
 */
package jp.co.isid.mos.bird.entry.nationalviewlist.logic.impl;

import java.util.HashMap;
import java.util.Map;

import jp.co.isid.mos.bird.entry.common.code.ConditionTaishoJoken;
import jp.co.isid.mos.bird.entry.nationalviewlist.dao.UIAttendAbsentCntDao;
import jp.co.isid.mos.bird.entry.nationalviewlist.dao.UISibuOnerStatusListDao;
import jp.co.isid.mos.bird.entry.nationalviewlist.entity.UIStatusInfo;
import jp.co.isid.mos.bird.entry.nationalviewlist.logic.CheckSvExistLogic;
import jp.co.isid.mos.bird.entry.nationalviewlist.logic.SearchLogic;
import jp.co.isid.mos.bird.entry.nationalviewlist.util.NationalViewListUtil;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.util.ProjectPlanStatusInfoUtil;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.HankakuVerifier;

/**
 * 【検索対象情報取得】ロジック
 * 
 * @author xkinu
 */
public class SearchLogicImpl implements SearchLogic {

    /** ロジックID */
    public static final String LOGIC_ID = NationalViewListUtil.SCREEN_ID+"L02";
    /**
     *  ロジック【SV存在チェック】
     */
    private CheckSvExistLogic nationalViewListCheckSvExistLogic;
    /**
     * DAO【出欠件数情報】
     */
    private UIAttendAbsentCntDao nationalViewListUIAttendAbsentCntDao;
    /**
     * DAO【申込状況一覧】
     */
    private UISibuOnerStatusListDao nationalViewListUISibuOnerStatusListDao;

    /** 
     * パラメーターKey：BIRDユーザー情報 
     */
    public static final String PK_USERINFO = "userInfo";
    /** 
     * パラメーターKey：システム日付 
     */
    public static final String PK_SYSDATE = "sysDate";
    /**
     * パラメーターKey：対象エントリーエンティティー
     */
    public static final String PK_ENTITY_COURSE= "entityCourse";
    /**
     * パラメーターKey：条件項目『対象条件』
     */
    public static final String PK_TAISHOJOKEN = "taishoJoken";
    /**
     * パラメーターKey：会社コード
     */
    public static final String PK_COMPANY_CD= "companyCd";
    /**
     * パラメーターKey：支部コード
     */
    public static final String PK_SIBU_CD= "sibuCd";
    /**
     * パラメーターKey：申込開始日
     */
    public static final String PK_FROM_DT= "fromDt";
    /**
     * パラメーターKey：SVコード
     */
    public static final String PK_SV_CD= "svCd";
    /**
     * リターンKey：出欠件数情報
     */
    public static final String RK_LIST_ATTENDANCE = "listAttendance";
    /**
     * リターンKey：申込状況一覧
     */
    public static final String RK_LIST_STATUS= "listStatus";

    /**
     * 事前条件処理
     * 
     * @param map
     * @throws ApplicationException
     */
    private void validate(final Map params) {
        BirdUserInfo userInfo = (BirdUserInfo)params.get(PK_USERINFO);
        if(userInfo == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("ユーザー情報");
        }
        //パラメーター対象エントリーエンティティー
        UIStatusInfo entity = (UIStatusInfo)params.get(PK_ENTITY_COURSE);
        if (entity == null) {
            throw new MissingDataException("対象エントリー情報");
        }
        //システム日付
        String sysDate = (String)params.get(PK_SYSDATE);
        if (NationalViewListUtil.isNull(sysDate)) {
            throw new MissingDataException("システム日付");
        }
        //会社コード
        String companyCd = (String)params.get(PK_COMPANY_CD);
        if (ProjectPlanStatusInfoUtil.isNull(companyCd)) {
            throw new MissingDataException("会社コード");
        }
        //パラメーター対象条件必須チェック
        String taishouJoken = (String)params.get(PK_TAISHOJOKEN);
        if(NationalViewListUtil.isNull(taishouJoken)){
            throw new NotNullException("対象条件", "targetTaishouJoken", 0);
        }
        if(NationalViewListUtil.isNull((String)params.get(PK_FROM_DT))){
            throw new NotNullException("申込開始日", "fromDt", 0);
        }
        if(ConditionTaishoJoken.VALUE_SIBU.equals(taishouJoken)){
            String sibuListChoice = (String)params.get(PK_SIBU_CD);
            if(NationalViewListUtil.isNull(sibuListChoice)){
                throw new NotNullException("支部", "targetSibuCd", 0);
            }
        }
        else if(ConditionTaishoJoken.VALUE_SV.equals(taishouJoken)){
            //SVコード必須チェック
            String svCd = (String)params.get(PK_SV_CD);
            if(NationalViewListUtil.isNull(svCd)){
                throw new NoInputException("SVコード", "targetSvCd", 0);
            }
            //SVコード妥当性チェック
            HankakuVerifier hankakuVerifier = new HankakuVerifier();
            if(!hankakuVerifier.validate(svCd) || svCd.length() > 8){
                throw new GenericMessageException("SVコードは半角英数字8桁以内で入力してください。", "targetSvCd", 0);               
            }
            //ロジック【SVコード存在チェック】を実行する。
            Map logicParams = new HashMap();
            logicParams.put(CheckSvExistLogicImpl.PK_COMPANY_CD, companyCd);
            logicParams.put(CheckSvExistLogicImpl.PK_SV_CD, svCd);                
            getNationalViewListCheckSvExistLogic().execute(logicParams);
        }
    }
    /**
     * 実行処理
     */
    public Map execute(final Map params) {
        //１．事前条件処理を実行する。
        validate(params);
        //２．リターン値Mapをインスタンス化する。
        Map rparam = new HashMap();
        //ユーザー情報
        BirdUserInfo userInfo = (BirdUserInfo)params.get(PK_USERINFO);
        boolean limitKbn = userInfo.isLimit();
        String userId = userInfo.getUserID();
        //システム日付
        String sysDate = (String)params.get(PK_SYSDATE);
        //会社コード
        String companyCd = (String)params.get(PK_COMPANY_CD);
        //パラメーター対象エントリーエンティティー
        UIStatusInfo entity = (UIStatusInfo)params.get(PK_ENTITY_COURSE);
        //パラメーター対象条件
        String taishoJoken = (String)params.get(PK_TAISHOJOKEN);
        //パラメーター申込開始日
        String fromDt = (String)params.get(PK_FROM_DT);
        //パラメーター支部コード
        String sibuCd = (String)params.get(PK_SIBU_CD);
        //パラメーターSVコード
        String svCd = (String)params.get(PK_SV_CD);
        CodeFormatter cdf = new CodeFormatter(8, "00000000");
        cdf.setFormatPattern("00000000");
        if(!NationalViewListUtil.isNull(svCd)){
            svCd = cdf.format(svCd, true);
        }
        
        String entryCd = entity.getEntryCd();
        String entryYear = entity.getEntryYear();
        String entryKai = entity.getEntryKai();
        //３．DAO【出欠件数情報】.検索を実行し、実行結果[[出欠件数情報]]をリターン値Mapへ格納する。
        rparam.put(RK_LIST_ATTENDANCE
                , getNationalViewListUIAttendAbsentCntDao().select(
                        entryCd, entryYear, entryKai, sysDate, companyCd, taishoJoken, fromDt, sibuCd, svCd));
        //４．DAO【申込状況一覧】.検索を実行し、実行結果[[申込状況一覧]]をリターン値Mapへ格納する。
        rparam.put(RK_LIST_STATUS
                , getNationalViewListUISibuOnerStatusListDao().select(
                        userId, limitKbn, entryCd, entryYear, entryKai, sysDate, companyCd, taishoJoken, fromDt, sibuCd, svCd));
        //５．リターン値Mapをリターンする。
        return rparam;
    }
    /**
     * @return nationalViewListUIAttendAbsentCntDao を戻します。
     */
    public UIAttendAbsentCntDao getNationalViewListUIAttendAbsentCntDao() {
        return nationalViewListUIAttendAbsentCntDao;
    }
    /**
     * @param dao 設定する nationalViewListUIAttendAbsentCntDao。
     */
    public void setNationalViewListUIAttendAbsentCntDao(UIAttendAbsentCntDao dao) {
        this.nationalViewListUIAttendAbsentCntDao = dao;
    }
    /**
     * @return nationalViewListUISibuOnerStatusListDao を戻します。
     */
    public UISibuOnerStatusListDao getNationalViewListUISibuOnerStatusListDao() {
        return nationalViewListUISibuOnerStatusListDao;
    }
    /**
     * @param dao 設定する nationalViewListUISibuOnerStatusListDao。
     */
    public void setNationalViewListUISibuOnerStatusListDao(UISibuOnerStatusListDao dao) {
        this.nationalViewListUISibuOnerStatusListDao = dao;
    }
    /**
     * @return nationalViewListCheckSvExistLogic を戻します。
     */
    public CheckSvExistLogic getNationalViewListCheckSvExistLogic() {
        return nationalViewListCheckSvExistLogic;
    }
    /**
     * @param nationalViewListCheckSvExistLogic 設定する nationalViewListCheckSvExistLogic。
     */
    public void setNationalViewListCheckSvExistLogic(
            CheckSvExistLogic nationalViewListCheckSvExistLogic) {
        this.nationalViewListCheckSvExistLogic = nationalViewListCheckSvExistLogic;
    }
}
