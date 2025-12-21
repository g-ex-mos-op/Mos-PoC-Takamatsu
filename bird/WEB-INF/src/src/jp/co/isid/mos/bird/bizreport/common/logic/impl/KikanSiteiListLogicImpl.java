package jp.co.isid.mos.bird.bizreport.common.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.entity.CodKikanSitei;
import jp.co.isid.mos.bird.bizreport.common.logic.KikanSiteiMapLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 期間指定データ検索ロジック
 * 
 * パラメーター：下記の値を保持したMapを引数とする。
 *               「kikanType」対象期間
 *               「listSize」対象期間数
 * @author xkinu
 */
public class KikanSiteiListLogicImpl implements KikanSiteiMapLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BBR000L09";
    /**
     * 対象期間：年月日 yyyy/MM/dd
     */
    public static final String CODE_DAY = "DAY";
    /**
     * 対象期間：年月 yyyy/MM
     */
    public static final String CODE_MONTH = "MONTH";
    /**
     * 対象期間：期別 ○○期
     */
    public static final String CODE_KIGETU = "KIBETU";
    /**
     * 対象期間：年度 yyyy
     */
    public static final String CODE_NENDO = "NENDO";
    /**
     * パラメーター名：期間タイプ
     */
    public static final String PM_KIKANTYPE = "kikanType";
    /**
     * パラメーター名：リスト個数（アプリ日付含めて過去何個分か）
     */
    public static final String PM_SIZE = "listSize";
    /**
     * 期別：第一四半期
     */
    public static final String KIGETU_SIHANKI1   = "SIHANKI1";
    /**
     * 期別：第二四半期
     */
    public static final String KIGETU_SIHANKI2   = "SIHANKI2";
    /**
     * 期別：第三四半期
     */
    public static final String KIGETU_SIHANKI3   = "SIHANKI3";
    /**
     * 期別：第四四半期
     */
    public static final String KIGETU_SIHANKI4   = "SIHANKI4";
    /**
     * 期別：上期
     */
    public static final String KIGETU_KAMI   = "KAMI";
    /**
     * 期別：下期
     */
    public static final String KIGETU_SIMO   = "SIMO";
    /**
     * 期別：下期
     */
    public static final String KIGETU_NENDO   = CODE_NENDO;
    /**
     * 事前条件処理
     * @param map
     * @throws ApplicationException
     */
    private void validate(final Map map) throws ApplicationException {
        //期間タイプ
        String kikanType = (String)map.get(PM_KIKANTYPE);
        //リスト個数
        String listSize = (String)map.get(PM_SIZE);
        if (isNull(kikanType)) {
            throw new NotNullException("期間タイプ");
        }
        if (isNull(listSize) && !CODE_KIGETU.equals(kikanType)) {
            throw new NotNullException("リスト個数");
        }
    }
    
    /**
     * 処理実行
     * 
     * @param map
     * @return
     * @throws ApplicationException
     */
    public Map execute(final Map map) throws ApplicationException {
        //事前条件処理実行
        validate(map);
        String appDate = getBirdDateInfo().getAppDate();
        String kikanType = (String)map.get(PM_KIKANTYPE);
        int listSize = Integer.valueOf((String)map.get(PM_SIZE)).intValue();
        Map returnMap = new HashMap();
        List list = null;
        if(CODE_DAY.equals(kikanType)) {
            // 期間指定年月日データ取得（過去listSize日分）
            list = executeListDay(appDate, listSize);
        }else if(CODE_MONTH.equals(kikanType)) {
            // 期間指定年月データ取得（過去listSizeヶ月分）
            list = executeListMonth(appDate, listSize);
        }else if(CODE_KIGETU.equals(kikanType)) {
            returnMap = executeListKibetu(appDate, listSize);
        }else if(CODE_NENDO.equals(kikanType)) {
            // 期間指定年度データ取得（過去listSize年度分）
            list = executeListNendo(appDate, listSize);
        }
        if(list != null) {
            // 表示対象データ取得
            returnMap.put("list1", list);
        }
        
        //検索データを戻す。
        return returnMap;
    }
    /**
     * 期間指定の種類を取得
     * 
     * アプリ年月含めて過去「リスト個数」日分を生成します。
     * 
     * @param String アプリ日付
     * @param int リスト個数
     * @return List 期間指定データ
     * @exception ApplicationException
     */
    private List executeListDay(String appDate, int cnt) throws ApplicationException {
        List list = new ArrayList();
        String app = appDate;
        DateFormatter dformat = new DateFormatter();
        for (int index=0; index<cnt; index++) {
            String code = "";
            try {
                code = DateManager.getPrevDate(app, index);
            }catch (Exception ex) {
                throw new FtlSystemException("期間指定生成"
                        , "アプリ日付["+app+"]から["+index+"]を引く際のDateManager.getPrevMonthメソッド処理で例外が発生しました。"
                        , ex);
            }
            String name = dformat.format(code, DateFormatter.PATTERN_SLASH, DateFormatter.DATE_TYPE_YMD);
            list.add(index, createEntity(code, name));
        }
        if (list == null || list.size() == 0) {
            throw new NotExistException("期間指定 年月情報");
        }
        return list;
    }
    /**
     * 期間指定の種類を取得
     * 
     * アプリ年月含めて過去「リスト個数」月分を生成します。
     * 
     * @param String アプリ日付
     * @param int リスト個数
     * @return List 期間指定データ
     * @exception ApplicationException
     */
    private List executeListMonth(String appDate, int cnt) throws ApplicationException {
        List list = new ArrayList();
        String app = appDate.substring(0, 6);
        DateFormatter dformat = new DateFormatter();
        for (int index=0; index<cnt; index++) {
            String code = "";
            try {
                code = DateManager.getPrevMonth(app, index);
            }catch (Exception ex) {
                throw new FtlSystemException("期間指定生成"
                        , "アプリ日付年月["+app+"]から["+index+"]を引く際のDateManager.getPrevMonthメソッド処理で例外が発生しました。"
                        , ex);
            }
            String name = dformat.format(code, DateFormatter.PATTERN_MONTH_SLASH, DateFormatter.DATE_TYPE_YM);
            list.add(index, createEntity(code, name));
        }
        if (list == null || list.size() == 0) {
            throw new NotExistException("期間指定 年月情報");
        }
        return list;
    }
    /**
     * 期間指定の種類を取得
     * 
     * @param Map map 検索条件データ保持Map
     * @return List 検索データ
     * @exception ApplicationException
     */
    private Map executeListKibetu(String appDate, int cnt) throws ApplicationException {
        Map returnMap = new HashMap();
        returnMap.put("list1", executeListNendo(appDate, cnt));
        List list = new ArrayList();
        int index = 0;
        list.add(index++, createEntity(KIGETU_SIHANKI1, "第一四半期"));
        list.add(index++, createEntity(KIGETU_SIHANKI2, "第二四半期"));
        list.add(index++, createEntity(KIGETU_KAMI, "上期"));
        list.add(index++, createEntity(KIGETU_SIHANKI3, "第三四半期"));
        list.add(index++, createEntity(KIGETU_SIHANKI4, "第四四半期"));
        list.add(index++, createEntity(KIGETU_SIMO, "下期"));
        list.add(index++, createEntity(KIGETU_NENDO, "年度"));
        returnMap.put("list2", list);
        return returnMap;
    }
    /**
     * 年度指定の種類を取得
     * 
     * アプリ年度含めて過去「リスト個数」年度分を生成します。
     * 
     * @param String アプリ日付
     * @param int リスト個数
     * @return List 期間指定データ
     * @exception ApplicationException
     */
    private List executeListNendo(String appDate, int cnt) throws ApplicationException {
        List list = new ArrayList();
        String app = DateManager.getCurrentYear(appDate);
        for (int index=0; index<cnt; index++) {
            String code = "";
            try {
                code = DateManager.getPrevYear(app, index);
            }catch (Exception ex) {
                throw new FtlSystemException("期間指定生成"
                        , "アプリ日付年度["+app+"]から["+index+"]を引く際のDateManager.getPrevYearメソッド処理で例外が発生しました。"
                        , ex);
            }
            String name = code;
            list.add(index, createEntity(code, name));
        }
        if (list == null || list.size() == 0) {
            throw new NotExistException("期間指定 年度情報");
        }
        return list;
    }
    /**
     * エンティティ生成処理
     * @param code
     * @param name
     * @return
     */
    private CodKikanSitei createEntity(String code, String name) {
        CodKikanSitei entity = new CodKikanSitei();
        entity.setCode(code);
        entity.setName(name);
        return entity;
    }
    /**
     * 
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }
    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    private BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }
    /**
     * Null判断処理
     * @param str
     * @return boolean true：空かNullの場合
     */
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
}
