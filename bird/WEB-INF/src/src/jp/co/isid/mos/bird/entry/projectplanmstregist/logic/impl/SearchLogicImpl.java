package jp.co.isid.mos.bird.entry.projectplanmstregist.logic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.entry.projectplanmstregist.dao.MstKanriDao;
import jp.co.isid.mos.bird.entry.projectplanmstregist.dao.MstKanriDateDao;
import jp.co.isid.mos.bird.entry.projectplanmstregist.dao.MstKanriNoticeDao;
import jp.co.isid.mos.bird.entry.projectplanmstregist.logic.SearchLogic;
import jp.co.isid.mos.bird.entry.projectplanmstregist.util.ProjectPlanMstRegistUtil;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;

/**
 * 事業方針説明会マスタ登録
 * 【事業方針説明会マスタ情報の検索】ロジックインターフェース
 * 
 * @author xkinu
 *
 */
public class SearchLogicImpl implements SearchLogic {
    /** ロジックID */
    public static final String LOGIC_ID = ProjectPlanMstRegistUtil.SCREEN_ID+"L02";
    /**
     * パラメーターKey：エントリーコード
     */
    public static final String PK_ENTRY_CD= "entryCd";
    /**
     * パラメーターKey：エントリー年度
     */
    public static final String PK_ENTRY_YEAR= "entryYear";
    /**
     * パラメーターKey：エントリー回
     */
    public static final String PK_ENTRY_KAI= "entryKai";
    /**
     * リターンパラメーターKey：事業方針説明会マスタ
     */
    public static final String RK_LIST_MST= "listMst";
    /**
     * リターンパラメーターKey：事業方針説明会日付マスタ
     */
    public static final String RK_LIST_MST_DATE= "listMstDate";
    /**
     * リターンパラメーターKey：事業方針説明会文言マスタ
     */
    public static final String RK_LIST_MST_NOTICE= "listMstNotice";
    
    /* DAO【事業方針説明会マスタ】(BR17ENTL)*/
    private MstKanriDao mstKanriDao;
    /* DAO【事業方針説明会日付マスタ】(BR18ENTD) */
    private MstKanriDateDao mstKanriDateDao;
    /* DAO【事業方針説明会文言マスタ】(BR51ENCI) */
    private MstKanriNoticeDao mstKanriNoticeDao;

    /**
     * メイン処理
     * 
     * １．事前条件処理を実行する。
     * 
     * ２．リターン値Mapをインスタンス化する。
     * 
     * ３．DAO【事業方針説明会マスタ】(BR17ENTL)．検索 を実行し、登録対象[[事業方針説明会マスタ]]を取得する。
     *      パラメーター  パラメーター.エントリーコード
     *   　               パラメーター.エントリー年
     *                    パラメーター.エントリー回
     * ３－１．処理３の結果が該当データなしの場合は、下記のエラーを発生させる。
     *      MSG【E0103】事業方針説明会マスタ　が存在しません。
     * 
     * ４．DAO【事業方針説明会マスタ】(BR17ENTL)．検索 を実行し、登録対象[[事業方針説明会マスタ]]を取得する。
     *      パラメーター  パラメーター.エントリーコード
     *   　               パラメーター.エントリー年
     *                    パラメーター.エントリー回
     * ４－１．処理４の結果が該当データなしの場合又は 件数＝5 でない場合は、下記のエラーを発生させる。
     *      MSG【E0103】事業方針説明会日付マスタ　が存在しません。
     * 
     * ５．DAO【事業方針説明会文言マスタ】(BR51ENCI) ．検索 を実行し、登録対象[[事業方針説明会文言マスタ]]を取得する。
     *      パラメーター  パラメーター.エントリーコード
     *   　               パラメーター.エントリー年
     *                    パラメーター.エントリー回
     * ５－１．処理５の結果が該当データなしの場合は、下記のエラーを発生させる。
     *      MSG【E0103】事業方針説明会文言マスタ　が存在しません。
     * 
     * ６．処理３で取得した[[事業方針説明会マスタ]をリターン値へ格納する。
     * 
     * ７．処理４で取得した[[管理対象日付マスタ]]をリターン値へ格納する。
     * 
     * ８．処理５で取得した[[管理対象文言マスタ]]をリターン値へ格納する。
     * 
     * ９．リターン値Mapをリターンする。
     */
    public Map execute(Map params) {
        //１．事前条件処理を実行する。
        validate(params);
        //２．リターン値Mapをインスタンス化する。
        Map rMap = new HashMap(); 
        //パラメーター
        String entryCd = (String)params.get(PK_ENTRY_CD);
        String entryYear = (String)params.get(PK_ENTRY_YEAR);
        String entryKai = (String)params.get(PK_ENTRY_KAI);
        //３．DAO【事業方針説明会マスタ】(BR17ENTL)．検索 を実行し、登録対象[[事業方針説明会マスタ]]を取得する。
        List mstList = getProjectPlanMstRegistMstKanriDao().select(entryCd, entryYear, entryKai);
        //３－１．処理３の結果が該当データなしの場合は、下記のエラーを発生させる。
        if(mstList == null || mstList.size() < 1){
            //MSG【E0103】事業方針説明会マスタ　が存在しません。
            throw new NotExistException("事業方針説明会マスタ");
        }
        //４．DAO【事業方針説明会マスタ】(BR17ENTL)．検索 を実行し、登録対象[[事業方針説明会マスタ]]を取得する。
        List mstDateList = getProjectPlanMstRegistMstKanriDateDao().select(entryCd, entryYear, entryKai);
        if(mstDateList == null || mstDateList.size() != 5){
            //MSG【E0103】事業方針説明会マスタ　が存在しません。
            throw new NotExistException("事業方針説明会日付マスタ");
        }
        //５．DAO【事業方針説明会文言マスタ】(BR51ENCI) ．検索 を実行し、登録対象[[事業方針説明会文言マスタ]]を取得する。
        List mstNoticeList = getProjectPlanMstRegistMstKanriNoticeDao().select(entryCd, entryYear, entryKai);
        if(mstNoticeList == null || mstNoticeList.size() < 1){
            //MSG【E0103】事業方針説明会文言マスタ　が存在しません。
            throw new NotExistException("事業方針説明会文言マスタ");
        }
        
        //６．処理３で取得した[[事業方針説明会マスタ]をリターン値へ格納する。
        rMap.put(RK_LIST_MST, mstList);
        //７．処理４で取得した[[管理対象日付マスタ]]をリターン値へ格納する。
        rMap.put(RK_LIST_MST_DATE, mstDateList);
        //８．処理５で取得した[[管理対象文言マスタ]]をリターン値へ格納する。
        rMap.put(RK_LIST_MST_NOTICE, mstNoticeList);
        //９．リターン値Mapをリターンする。
        return rMap;
    }
    /**
     * 事前条件処理
     * @param map
     * @throws ApplicationException
     */
    private void validate(final Map params) {
        //エントリーコード
        String entityCd = (String)params.get(PK_ENTRY_CD);
        if (ProjectPlanMstRegistUtil.isNull(entityCd)) {
            throw new MissingDataException("エントリーコード");
        }
        //エントリー年
        String entryYear = (String)params.get(PK_ENTRY_YEAR);
        if (ProjectPlanMstRegistUtil.isNull(entryYear)) {
            throw new MissingDataException("エントリー年");
        }
        //エントリー回
        String entryKai = (String)params.get(PK_ENTRY_KAI);
        if (ProjectPlanMstRegistUtil.isNull(entryKai)) {
            throw new MissingDataException("エントリー回");
        }
    }
    /**
     * @return mstKanriDao を戻します。
     */
    public MstKanriDao getProjectPlanMstRegistMstKanriDao() {
        return mstKanriDao;
    }
    /**
     * @param mstKanriDao 設定する。
     */
    public void setProjectPlanMstRegistMstKanriDao(
            MstKanriDao dao) {
        this.mstKanriDao = dao;
    }
    /**
     * @return mstKanrDateDao を戻します。
     */
    public MstKanriDateDao getProjectPlanMstRegistMstKanriDateDao() {
        return mstKanriDateDao;
    }
    /**
     * @param mstKanrDateDao 設定する 。
     */
    public void setProjectPlanMstRegistMstKanriDateDao(
            MstKanriDateDao dao) {
        this.mstKanriDateDao = dao;
    }
    /**
     * @return mstKanriNoticeDao を戻します。
     */
    public MstKanriNoticeDao getProjectPlanMstRegistMstKanriNoticeDao() {
        return mstKanriNoticeDao;
    }
    /**
     * @param mstKanriNoticeDao 設定する。
     */
    public void setProjectPlanMstRegistMstKanriNoticeDao(
            MstKanriNoticeDao dao) {
        this.mstKanriNoticeDao = dao;
    }
 }
