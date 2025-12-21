/*
 * 作成日: 2006/12/26
 *
 */
package jp.co.isid.mos.bird.entry.longserviceviewlist.logic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.entry.common.code.ConditionTaishoJoken;
import jp.co.isid.mos.bird.entry.longserviceviewlist.dao.UILongServiceViewListTableDao;
import jp.co.isid.mos.bird.entry.longserviceviewlist.entity.UILSViewListEvent;
import jp.co.isid.mos.bird.entry.longserviceviewlist.logic.LongserviceViewListResultLogic;
import jp.co.isid.mos.bird.entry.longserviceviewlist.util.LongserviceViewListUtil;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 永年勤続オーナー別申請状況一覧 ロジック
 * 
 * @author xamaruyama
 * 更新日：2012/12/04 xkinu「本部申込開始日」と「ｸﾛｰｽﾞ日」を比較、
 *                     本部申込開始日より前にクローズした店舗は、画面に表示させないようにする。
 * 更新日：2009/08/11 支部制限時のユーザーIDがパラメータに存在せず、
 *                    SQLでuserIdのパラメータありきで設定されているため、
 *                    常に支部制限のユーザは該当データ無しになっていました。
 *                    パラメータuserIdを追加し、対応しました。
 */
public class LongserviceViewListResultLogicImpl implements LongserviceViewListResultLogic {

    /** ロジックID */
    public static final String LOGIC_ID = LongserviceViewListUtil.SCREEN_ID+"L02";
    /** Dao【オーナー別申請状況一覧Dao】*/
    private UILongServiceViewListTableDao uILongServiceViewListTableDao;
    
    /** 
     * パラメーターKey：ログインユーザーID
     */
    public static final String PK_USER_ID = "userId";
    /** 
     * パラメーターKey：システム日付 
     */
    public static final String PK_SYSDATE = "sysDate";
    
    /**
     * パラメーターKey：対象コースエンティティー
     */
    public static final String PK_ENTITY_COURSE= "entityCourse";
    
    /**
     * パラメーターKey：条件項目『対象条件』
     */
    public static final String PK_TAISHOUJOKEN = "taishouJoken";
    
    /**
     * パラメーターKey：企業コード
     */
    public static final String PK_COMPANY_CD= "companyCd";
    
    /**
     * パラメーターKey：支部コード
     */
    public static final String PK_SIBU_CD= "sibuCd";
    
    /**
     * パラメーターKey：SVコード
     */
    public static final String PK_SV_CD= "svCd";
    
    /**
     * パラメーターKey：制限ユーザフラグ
     */
    public static final String PK_LIMIT= "limit";
    
    /**
     * リターンKey：申請情報
     */
    public static final String RK_LIST_APPLY_ONER = "listApplyOner";
    
    /**
     * 事前条件処理
     * 
     * @param map
     * @throws ApplicationException
     * 
	 * 更新日：2009/08/11 支部制限時のユーザーIDがパラメータに存在せず、
	 *                    SQLでuserIdのパラメータありきで設定されているため、
	 *                    常に支部制限のユーザは該当データ無しになっていました。
	 *                    パラメータuserIdを追加し、対応しました。
     */
    private Map validate(Map params) {
        
        //パラメーター対象コースエンティティー
        UILSViewListEvent entity = (UILSViewListEvent)params.get(PK_ENTITY_COURSE);
        if (entity == null) {
            throw new MissingDataException("対象コース情報");
        }
        
        Boolean limit = (Boolean)params.get(PK_LIMIT);
        if (limit == null) {
            throw new MissingDataException("制限ユーザフラグ");
        }
        
        //ログインユーザーID
        String userId = (String)params.get(PK_USER_ID);
        if (LongserviceViewListUtil.isNull(userId)) {
            throw new MissingDataException("ユーザーID");
        }
        //システム日付
        String sysDate = (String)params.get(PK_SYSDATE);
        if (LongserviceViewListUtil.isNull(sysDate)) {
            throw new MissingDataException("システム日付");
        }
        
        //企業コード妥当性チェック
        String companyCd = (String)params.get(PK_COMPANY_CD);
        if(LongserviceViewListUtil.isNull(companyCd)){
            throw new MissingDataException("企業コード");
        }
        
        //パラメーター対象条件必須チェック
        String taishouJokenChoice = (String)params.get(PK_TAISHOUJOKEN);
        if(LongserviceViewListUtil.isNull(taishouJokenChoice)){
            throw new NotNullException("対象条件", "taishouJokenChoice", 0);
        }
        
        //支部コード妥当性チェック
        if(ConditionTaishoJoken.VALUE_SIBU.equals(taishouJokenChoice)){
            String sibuListChoice = (String)params.get(PK_SIBU_CD);
            if(LongserviceViewListUtil.isNull(sibuListChoice)){
                throw new NotNullException("支部コード", "sibuChoice", 0);
            }
        }
        
        //SVコードの妥当性チェック
        else if(ConditionTaishoJoken.VALUE_SV.equals(taishouJokenChoice)){
            String svCd = (String)params.get(PK_SV_CD);
            if(LongserviceViewListUtil.isNull(svCd)){
                throw new NoInputException("SVコード", "svCd", 0);
            }
        }
        return params;
    }
    
    /**
     * 実行処理
     * 
	 * 更新日：2012/12/04 xkinu「本部申込開始日」と「ｸﾛｰｽﾞ日」を比較、
	 *                     本部申込開始日より前にクローズした店舗は、画面に表示させないようにする。
	 * 更新日：2009/08/11 支部制限時のユーザーIDがパラメータに存在せず、
	 *                    SQLでuserIdのパラメータありきで設定されているため、
	 *                    常に支部制限のユーザは該当データ無しになっていました。
	 *                    DAOにパラメータuserIdを追加し、対応しました。
     */
    public Map execute(final Map params) {
        
        //１．事前条件処理を実行する。
        validate(params);
        
        //２．リターン値Mapをインスタンス化する。
        Map rparam = new HashMap();
        //ログインユーザーID
        String userId = (String)params.get(PK_USER_ID);
        //システム日付
        String sysDate = (String)params.get(PK_SYSDATE);
        //パラメーター対象コースエンティティー
        UILSViewListEvent entity = (UILSViewListEvent)params.get(PK_ENTITY_COURSE);
        //パラメーター会社コード
        String companyCd = (String)params.get(PK_COMPANY_CD);
        //パラメーター対象条件
        String taishouJokenChoice = (String)params.get(PK_TAISHOUJOKEN);
        //パラメーター支部コード
        String sibuChoice = (String)params.get(PK_SIBU_CD);
        //パラメーターSVコード
        String svCd = (String)params.get(PK_SV_CD);
        //パラメーター制限ユーザフラグ
        boolean limit = ((Boolean)params.get(PK_LIMIT)).booleanValue();
        //パラメーター本部申込開始日(2012/12/04追加)
        String fromEntryDt = entity.getHonbuFromDt();
        
        String entryCd = entity.getEntryCd();
        String entryYear = entity.getEntryYear();
        String entryKai = entity.getEntryKai();
        //DAO【オーナー別申請状況一覧】の検索処理を実行します。
        List total = getUILongServiceViewListTableDao().getLongServiceTable(userId, limit
                , entryCd, entryYear, entryKai, sysDate, companyCd, taishouJokenChoice, sibuChoice, svCd, fromEntryDt);
        //リターン値MapへList[[オーナー別申請状況一覧]]を設定します。
        rparam.put(RK_LIST_APPLY_ONER, total);
        
        return rparam;
    }
   
    public UILongServiceViewListTableDao getUILongServiceViewListTableDao() {
        return uILongServiceViewListTableDao;
    }
    
    public void setUILongServiceViewListTableDao(UILongServiceViewListTableDao uILongServiceViewListTableDao) {
        this.uILongServiceViewListTableDao = uILongServiceViewListTableDao;
    }
}
