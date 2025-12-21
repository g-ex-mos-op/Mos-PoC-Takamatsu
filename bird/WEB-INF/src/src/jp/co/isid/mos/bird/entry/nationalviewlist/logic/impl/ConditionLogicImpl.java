package jp.co.isid.mos.bird.entry.nationalviewlist.logic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.entry.nationalviewlist.dao.CodCompanyDao;
import jp.co.isid.mos.bird.entry.nationalviewlist.dao.CodSibuDao;
import jp.co.isid.mos.bird.entry.nationalviewlist.dao.UIStatusInfoDao;
import jp.co.isid.mos.bird.entry.nationalviewlist.entity.CodCompany;
import jp.co.isid.mos.bird.entry.nationalviewlist.logic.ConditionLogic;
import jp.co.isid.mos.bird.entry.nationalviewlist.util.NationalViewListUtil;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;


/**
 * 【条件項目情報の取得】ロジック
 * 
 * @author xkinu
 *
 */
public class ConditionLogicImpl implements ConditionLogic{
    /** ロジックID */
    public static final String LOGIC_ID = NationalViewListUtil.SCREEN_ID+"L01";
    /** 
     * パラメーターKey：BIRDユーザー情報 
     */
    public static final String PK_USERINFO = "userInfo";
    /** 
     * パラメーターKey：システム日付 
     */
    public static final String PK_SYSDATE = "sysDate";
    /** 
     * パラメーターKey：会社コード
     */
    public static final String PK_COMPANY_CD = "companyCd";
    /**
     * パラメーターKey： エントリーコード
     */
    public static final String PK_ENTRY_CD = "entryCd";
    /**
     * パラメーターKey：エントリー年度
     */
    public static final String PK_ENTRY_YEAR= "entryYear";
    /**
     * パラメーターKey：エントリー回
     */
    public static final String PK_ENTRY_KAI= "entryKai";
    /**
     * リターンKey：対象全国大会状況情報
     */
    public static final String RK_LIST_STATUS_INFO= "listStatusInfo";
    /**
     * リターンKey：会社コード情報取得
     */
    public static final String RK_LIST_COMPANY= "listCompanyCd";
    /**
     * リターンKey：支部コード情報取得
     */
    public static final String RK_LIST_SIBU= "listSibuCd";
    
    /* DAO【対象エントリー情報】 */
    private UIStatusInfoDao nationalStatusInfoUIStatusInfoDao;
    /* DAO【会社リスト】*/
    private CodCompanyDao nationalStatusInfoCodCompanyDao;
    /* DAO【支部リスト】*/
    private CodSibuDao nationalStatusInfoCodSibuDao;

    public Map execute(Map params) {
        //１．事前条件処理を実行する。
        validate(params);
        //２．リターン値Mapをインスタンス化する。
        Map remap = new HashMap();
        //ユーザー情報
        BirdUserInfo userInfo = (BirdUserInfo)params.get(PK_USERINFO);
        boolean limitKbn = userInfo.isLimit();
        String userId = userInfo.getUserID();
        //システム日付
        String sysDate = (String)params.get(PK_SYSDATE);
        //会社コード
        String companyCd = (String)params.get(PK_COMPANY_CD);
        //エントリーコード
        String entryCd = (String)params.get(PK_ENTRY_CD);
        //エントリー年
        String entryYear = (String)params.get(PK_ENTRY_YEAR);
        //エントリー回
        String entryKai = (String)params.get(PK_ENTRY_KAI);
        
        //３．DAO【会社リスト】．検索 を実行し、
        //    実行結果[[会社リスト]]を取得する。
        List listCompany = getNationalStatusInfoCodCompanyDao().select(userId);
        //４．処理３の実行結果をリターン値Mapへ格納する。
        remap.put(RK_LIST_COMPANY, listCompany);
        if(NationalViewListUtil.isNull(companyCd)){
            companyCd = ((CodCompany)listCompany.get(0)).getCompanyCd();
        }
        //５． DAO【支部リスト】．検索 を実行し、
        //    実行結果[[支部リスト]]を取得する。
        List listSibu = getNationalStatusInfoCodSibuDao().select(limitKbn, userId, companyCd);
        //６．処理５の実行結果をリターン値Mapへ格納する。
        remap.put(RK_LIST_SIBU, listSibu);
        //７．Dao【対象エントリー情報】.検索を実行し、 実行結果[[対象エントリー情報]]を取得する。
        List listStatusInfo = getNationalStatusInfoUIStatusInfoDao().select(entryCd, entryYear, entryKai, sysDate);
        //８．処理７の実行結果をリターン値Mapへ格納する。
        remap.put(RK_LIST_STATUS_INFO, listStatusInfo);
        
        //９．リターン値Mapをリターンする。
        return remap;
    }
    /**
     * 事前条件処理
     * @param map
     * @throws ApplicationException
     */
    private void validate(final Map params) {
        BirdUserInfo userInfo = (BirdUserInfo)params.get(PK_USERINFO);
        if(userInfo == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("ユーザー情報");
        }
        //システム日付
        String sysDate = (String)params.get(PK_SYSDATE);
        if (NationalViewListUtil.isNull(sysDate)) {
            throw new MissingDataException("システム日付");
        }
        //エントリーコード
        String entryCd = (String)params.get(PK_ENTRY_CD);
        if (NationalViewListUtil.isNull(entryCd)) {
            throw new MissingDataException("エントリーコード");
        }
        //エントリー年
        String entryYear = (String)params.get(PK_ENTRY_YEAR);
        if (NationalViewListUtil.isNull(entryYear)) {
            throw new MissingDataException("エントリー年");
        }
        //エントリー回
        String entryKai = (String)params.get(PK_ENTRY_KAI);
        if (NationalViewListUtil.isNull(entryKai)) {
            throw new MissingDataException("エントリー回");
        }
    }
    /**
     * @return nationalStatusInfoCodCompanyDao を戻します。
     */
    public CodCompanyDao getNationalStatusInfoCodCompanyDao() {
        return nationalStatusInfoCodCompanyDao;
    }
    /**
     * @param nationalStatusInfoCodCompanyDao 設定する nationalStatusInfoCodCompanyDao。
     */
    public void setNationalStatusInfoCodCompanyDao(CodCompanyDao dao) {
        this.nationalStatusInfoCodCompanyDao = dao;
    }
    /**
     * @return nationalStatusInfoUCodSibuDao を戻します。
     */
    public CodSibuDao getNationalStatusInfoCodSibuDao() {
        return nationalStatusInfoCodSibuDao;
    }
    /**
     * @param nationalStatusInfoUCodSibuDao 設定する nationalStatusInfoUCodSibuDao。
     */
    public void setNationalStatusInfoCodSibuDao(CodSibuDao dao) {
        this.nationalStatusInfoCodSibuDao = dao;
    }
    /**
     * @return nationalStatusInfoUIStatusInfoDao を戻します。
     */
    public UIStatusInfoDao getNationalStatusInfoUIStatusInfoDao() {
        return nationalStatusInfoUIStatusInfoDao;
    }
    /**
     * @param nationalStatusInfoUIStatusInfoDao 設定する nationalStatusInfoUIStatusInfoDao。
     */
    public void setNationalStatusInfoUIStatusInfoDao(UIStatusInfoDao dao) {
        this.nationalStatusInfoUIStatusInfoDao = dao;
    }
}
