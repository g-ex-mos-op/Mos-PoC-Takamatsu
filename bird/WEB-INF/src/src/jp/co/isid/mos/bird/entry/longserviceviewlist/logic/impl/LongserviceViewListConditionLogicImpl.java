/*
 * 作成日: 2006/12/27
 */
package jp.co.isid.mos.bird.entry.longserviceviewlist.logic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.entry.longserviceviewlist.dao.UILSViewListEventDao;
import jp.co.isid.mos.bird.entry.longserviceviewlist.dao.UILSViewListSibuDao;
import jp.co.isid.mos.bird.entry.longserviceviewlist.logic.LongserviceViewListConditionLogic;
import jp.co.isid.mos.bird.entry.longserviceviewlist.util.LongserviceViewListUtil;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;

/**
 * 永年勤続申込状況確認 ロジック
 * 
 * @author xamaruyama
 */
public class LongserviceViewListConditionLogicImpl implements LongserviceViewListConditionLogic{
    
    /** 
     * ロジックID 
     */
    public static final String LOGIC_ID = LongserviceViewListUtil.SCREEN_ID+"L01";
    
    /** 
     * パラメーターKey：BIRDユーザー情報 
     */
    public static final String PK_USERINFO = "userInfo";
    
    /** 
     * パラメーターKey：システム日付 
     */
    public static final String PK_SYSDATE = "sysDate";
    
    /**
     * パラメーターKey： 会社コード
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
     * パラメーターKey：ユーザID
     */
    public static final String PK_USER_ID= "userId";
        
    /**
     * リターンKey：永年勤続情報
     */
    public static final String RK_LIST_STATUS_INFO= "listStatusInfo";
    
    /**
     * リターンKey：支部情報取得
     */
    public static final String RK_SIBU= "sibu";
    
    /** Dao【イベント情報取得Dao】 */
    private UILSViewListEventDao uILSViewListEventDao;
    /** Dao【オーナー別申請状況一覧Dao】 */
    private UILSViewListSibuDao uILSViewListSibuDao;
    
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
        
        //３．Dao【永年勤続申請　ヘッダ部表示】.検索を実行する。
        List listStatusInfo = getUILSViewListEventDao().getEvent(entryCd, entryYear, entryKai, companyCd, sysDate);
        remap.put(RK_LIST_STATUS_INFO, listStatusInfo);
        
        //４．Dao【永年勤続申請　支部情報取得】.検索を実行する。
        List sibu = getUILSViewListSibuDao().getSibu(limitKbn, companyCd, userId);
        remap.put(RK_SIBU, sibu);
        
        //５．リターン値Mapをリターンする。
        return remap;
    }
    
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
        //システム日付
        String sysDate = (String)params.get(PK_SYSDATE);
        if (LongserviceViewListUtil.isNull(sysDate)) {
            throw new MissingDataException("システム日付");
        }
        //会社コード
        String companyCd = (String)params.get(PK_COMPANY_CD);
        if (LongserviceViewListUtil.isNull(companyCd)) {
            throw new MissingDataException("企業コード");
        }
        //エントリーコード
        String entryCd = (String)params.get(PK_ENTRY_CD);
        if (LongserviceViewListUtil.isNull(entryCd)) {
            throw new MissingDataException("エントリーコード");
        }
        //エントリー年
        String entryYear = (String)params.get(PK_ENTRY_YEAR);
        if (LongserviceViewListUtil.isNull(entryYear)) {
            throw new MissingDataException("エントリー年");
        }
        //エントリー回
        String entryKai = (String)params.get(PK_ENTRY_KAI);
        if (LongserviceViewListUtil.isNull(entryKai)) {
            throw new MissingDataException("エントリー回");
        }
    }
    
    public UILSViewListEventDao getUILSViewListEventDao() {
        return uILSViewListEventDao;
    }
    public void setUILSViewListEventDao(UILSViewListEventDao viewListEventDao) {
        this.uILSViewListEventDao = viewListEventDao;
    }
    public UILSViewListSibuDao getUILSViewListSibuDao() {
        return uILSViewListSibuDao;
    }
    public void setUILSViewListSibuDao(UILSViewListSibuDao viewListSibuDao) {
        uILSViewListSibuDao = viewListSibuDao;
    }
}