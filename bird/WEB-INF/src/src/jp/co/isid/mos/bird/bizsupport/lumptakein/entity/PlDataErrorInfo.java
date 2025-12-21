/*
 * 作成日: 2006/03/14
 *
 */
package jp.co.isid.mos.bird.bizsupport.lumptakein.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * P/Lデータエラー情報
 * 
 * @author xyuchida
 */
public class PlDataErrorInfo implements Cloneable {

    /**
     * エラーコード
     */
    public static final String ERRORCODE_REQUIRED = "0001";
    public static final String ERRORCODE_INVALID = "0002";
    public static final String ERRORCODE_DUPLICATE = "0003";
    public static final String ERRORCODE_CLOSED = "0004";
    public static final String ERRORCODE_NUM_REQUIRED = "1001";
    public static final String ERRORCODE_NUM_INVALID = "1002";
    public static final String ERRORCODE_NUM_LIMIT = "1003";
    public static final String ERRORCODE_NUM_SALES = "1004";
    public static final String ERRORCODE_NUM_ADJUST = "1005";
    public static final String ERRORCODE_NUM_ADJUST_DETAIL = "1006";
    public static final String ERRORCODE_NUM_NOINPUT = "1007";
    public static final String ERRORCODE_NUM_ADJUST_LOAN = "1008";

    /**
     * エラーコードセット
     */
    protected static final Set ERRORCODE_SET = new HashSet();
    static {
        ERRORCODE_SET.add(ERRORCODE_REQUIRED);
        ERRORCODE_SET.add(ERRORCODE_INVALID);
        ERRORCODE_SET.add(ERRORCODE_DUPLICATE);
        ERRORCODE_SET.add(ERRORCODE_NUM_REQUIRED);
        ERRORCODE_SET.add(ERRORCODE_NUM_INVALID);
        ERRORCODE_SET.add(ERRORCODE_NUM_ADJUST);
        ERRORCODE_SET.add(ERRORCODE_NUM_ADJUST_DETAIL);
    }

    /**
     * ワーニングコードセット
     */
    protected static final Set WARNINGCODE_SET = new HashSet();
    static {
        WARNINGCODE_SET.add(ERRORCODE_CLOSED);
        WARNINGCODE_SET.add(ERRORCODE_NUM_LIMIT);
        WARNINGCODE_SET.add(ERRORCODE_NUM_SALES);
        WARNINGCODE_SET.add(ERRORCODE_NUM_NOINPUT);
        WARNINGCODE_SET.add(ERRORCODE_NUM_ADJUST_LOAN);
    }

    /**
     * エラー情報
     */
    protected Map errorInfoMap = new HashMap();

    /**
     * エラー情報を取得します。
     * @return エラー情報
     */
    protected Map getErrorInfoMap() {
        return errorInfoMap;
    }

    /**
     * エラー情報を設定します。
     * @param errorInfoMap エラー情報
     */
    protected void setErrorInfoMap(Map errorInfoMap) {
        this.errorInfoMap = errorInfoMap;
    }

    /**
     * エラー情報追加
     * @param itemCode 項目名
     * @param errorCode エラーコード
     */
    public void add(String itemCode, String errorCode) {
        Set errorCodeSet = (Set) getErrorInfoMap().get(itemCode);
        if (errorCodeSet == null) {
            errorCodeSet = new HashSet();
            getErrorInfoMap().put(itemCode, errorCodeSet);
        }
        errorCodeSet.add(errorCode);
    }

    /**
     * 指定項目エラー情報削除
     * @param itemCode 項目名
     */
    public void remove(String itemCode) {
        getErrorInfoMap().remove(itemCode);
    }

    /**
     * 指定項目指定エラー情報削除
     * @param itemCode 項目名
     * @param errorCode エラーコード
     */
    public void remove(String itemCode, String errorCode) {
        if (getErrorInfoMap().containsKey(itemCode)) {
            Set errorCodeSet = (Set) getErrorInfoMap().get(itemCode);
            errorCodeSet.remove(errorCode);
            if (errorCodeSet.isEmpty()) {
                getErrorInfoMap().remove(itemCode);
            }
        }
    }

    /**
     * エラー情報クリア
     */
    public void clear() {
        getErrorInfoMap().clear();
    }

    /**
     * エラー有無判定
     * @return エラー有無
     */
    public boolean isExistError() {
        return isExistError(ERRORCODE_SET);
    }

    /**
     * ワーニング有無判定
     * @return ワーニング有無
     */
    public boolean isExistWarning() {
        return isExistError(WARNINGCODE_SET);
    }

    /**
     * エラー存在有無判定
     * @param errorCodeSet 対象エラーコードセット
     * @return エラー存在有無
     */
    protected boolean isExistError(Set errorCodeSet) {
        boolean result = false;
        for (Iterator it = errorCodeSet.iterator(); it.hasNext();) {
            if (isExistError((String) it.next())) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * エラー存在有無判定
     * @param targetErrroCode 対象エラーコード
     * @return エラー存在有無
     */
    protected boolean isExistError(String targetErrroCode) {
        boolean result = false;
        List list = new ArrayList(getErrorInfoMap().values());
        outerloop: for (Iterator i = list.iterator(); i.hasNext();) {
            Set errorCodeSet = (Set) i.next();
            for (Iterator j = errorCodeSet.iterator(); j.hasNext();) {
                String errorCode = (String) j.next();
                if (errorCode != null && errorCode.equals(targetErrroCode)) {
                    result = true;
                    break outerloop;
                }
            }
        }
        return result;
    }

    /**
     * 指定項目エラー有無判定
     * @param itemCode 項目名
     * @return エラー有無
     */
    public boolean isErrorItem(String itemCode) {
        return isProblemItem(itemCode, ERRORCODE_SET);
    }

    /**
     * 指定項目リスト エラー有無判定
     * @param itemCodeList 項目名リスト
     * @return エラー有無
     */
    public boolean isErrorItem(List itemCodeList) {
        return isProblemItem(itemCodeList, ERRORCODE_SET);
    }

    /**
     * 指定項目指定エラー有無判定
     * @param itemCode 項目名
     * @param errorCode エラーコード
     * @return エラー有無
     */
    public boolean isErrorItem(String itemCode, String errorCode) {
        Set errorCodeSet = new HashSet();
        errorCodeSet.add(errorCode);
        return isProblemItem(itemCode, errorCodeSet);
    }

    /**
     * 指定項目ワーニング有無判定
     * @param itemCode 項目名
     * @return ワーニング有無
     */
    public boolean isWarningItem(String itemCode) {
        return isProblemItem(itemCode, WARNINGCODE_SET);
    }

    /**
     * 指定項目リスト ワーニング有無判定
     * @param itemCodeList 項目名リスト
     * @return ワーニング有無
     */
    public boolean isWarningItem(List itemCodeList) {
        return isProblemItem(itemCodeList, WARNINGCODE_SET);
    }

    /**
     * 指定項目エラー/ワーニング有無判定
     * @param itemCode 項目名
     * @param targetSet 対象エラーコードセット
     * @return エラー/ワーニング有無
     */
    protected boolean isProblemItem(String itemCode, Set targetSet) {
        boolean result = false;
        Set errorCodeSet = (Set) getErrorInfoMap().get(itemCode);
        if (errorCodeSet != null) {
            for (Iterator it = errorCodeSet.iterator(); it.hasNext();) {
                if (targetSet.contains(it.next())) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 指定項目エラー/ワーニング有無判定
     * @param itemCodeList 項目名リスト
     * @param targetSet 対象エラーコードセット
     * @return エラー/ワーニング有無
     */
    protected boolean isProblemItem(List itemCodeList, Set targetSet) {
        boolean result = false;
        for (Iterator it = itemCodeList.iterator(); it.hasNext();) {
            if (isProblemItem((String) it.next(), targetSet)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * 指定項目エラー/ワーニング有無判定
     * @param itemCodeList 項目名リスト
     * @param errorCode 対象エラーコード
     * @return エラー/ワーニング有無
     */
    public boolean isErrorItem(List itemCodeList, String errorCode) {
        Set targetSet = new HashSet();
        targetSet.add(errorCode);
        return isProblemItem(itemCodeList, targetSet);
    }

    /**
     * コピー
     * @return コピー後オブジェクト
     */
    public Object clone() {

        // エラー情報のディープコピーを行う
        Map cloneMap = new HashMap();
        for (Iterator i = getErrorInfoMap().keySet().iterator(); i.hasNext();) {
            String itemCode = (String) i.next();
            Set errorCodeSet = (Set) getErrorInfoMap().get(itemCode);

            // 項目単位のエラーコードをコピー
            Set cloneSet = new HashSet();
            for (Iterator j = errorCodeSet.iterator(); j.hasNext();) {
                cloneSet.add(j.next());
            }

            // コピー先Mapにセット
            cloneMap.put(itemCode, cloneSet);
        }

        // コピー先オブジェクト生成
        PlDataErrorInfo plDataErrorInfo = new PlDataErrorInfo();
        plDataErrorInfo.setErrorInfoMap(cloneMap);

        return plDataErrorInfo;
    }

    /**
     * 店コードエラー有無判定
     * @return 店コードエラー有無
     */
    public boolean isExistErrorMiseCd() {
        return isErrorItem(TrnPLInfo.miseCd_COLUMN, ERRORCODE_REQUIRED)
                || isErrorItem(TrnPLInfo.miseCd_COLUMN, ERRORCODE_INVALID);
    }

    /**
     * 店コード重複エラー有無判定
     * @return 店コード重複エラー有無
     */
    public boolean isExistErrorMiseCdDuplicate() {
        return isErrorItem(TrnPLInfo.miseCd_COLUMN, ERRORCODE_DUPLICATE);
    }

    /**
     * クローズ店判定
     * @return クローズ店判定 true:クローズ店
     */
    public boolean isExistErrorMiseCdClosed() {
        return isErrorItem(TrnPLInfo.miseCd_COLUMN, ERRORCODE_CLOSED);
    }

    /**
     * POS売上エラー有無判定
     * @return POS売上エラー有無
     */
    public boolean isExistErrorSales() {
        return isExistError(ERRORCODE_NUM_SALES);
    }

    /**
     * 上下限値エラー有無判定
     * @return 上下限値エラー有無
     */
    public boolean isExistErrorLimit() {
        return isExistError(ERRORCODE_NUM_LIMIT);
    }

    /**
     * All Zeroエラー有無判定
     * @return All Zeroエラー有無
     */
    public boolean isExistErrorAllZero() {
        return isErrorItem(TrnPLInfo.miseCd_COLUMN, ERRORCODE_NUM_INVALID);
    }

    
    /**
     * エラータブINDEX取得
     * @return
     */
    public int getErrorTabIndex() {
        
        int index = 1;
        if(getResultSymbolSubjectProfit().equals("×")) index = 1;
        if(getResultSymbolSubjectDetail().equals("×")) index = 2;
        if(getResultSymbolSubjectLoan().equals("×"))   index = 3;
        
        return index;
    }    
    
    
    // 科目不整合 - 月次損益
    public String getResultSymbolSubjectProfit() {

        // 対象項目リスト作成
        List itemCodeList = new ArrayList();
        itemCodeList.add(TrnPLInfo.uriagedaka_COLUMN);
        itemCodeList.add(TrnPLInfo.uriagegenka_COLUMN);
        itemCodeList.add(TrnPLInfo.uriageSoRieki_COLUMN);
        itemCodeList.add(TrnPLInfo.salary_COLUMN);
        itemCodeList.add(TrnPLInfo.yachin_COLUMN);
        itemCodeList.add(TrnPLInfo.suikouHi_COLUMN);
        itemCodeList.add(TrnPLInfo.royalty_COLUMN);
        itemCodeList.add(TrnPLInfo.tesuryo_COLUMN);
        itemCodeList.add(TrnPLInfo.koukoku_COLUMN);
        itemCodeList.add(TrnPLInfo.shoumou_COLUMN);
        itemCodeList.add(TrnPLInfo.houteiFukuri_COLUMN);
        itemCodeList.add(TrnPLInfo.fukuriKousei_COLUMN);
        itemCodeList.add(TrnPLInfo.kousai_COLUMN);
        itemCodeList.add(TrnPLInfo.ryohi_COLUMN);
        itemCodeList.add(TrnPLInfo.tusin_COLUMN);
        itemCodeList.add(TrnPLInfo.lease_COLUMN);
        itemCodeList.add(TrnPLInfo.sharyo_COLUMN);
        itemCodeList.add(TrnPLInfo.sozei_COLUMN);
        itemCodeList.add(TrnPLInfo.hoken_COLUMN);
        itemCodeList.add(TrnPLInfo.unchin_COLUMN);
        itemCodeList.add(TrnPLInfo.shuzen_COLUMN);
        itemCodeList.add(TrnPLInfo.yobi_COLUMN);
        itemCodeList.add(TrnPLInfo.zappi_COLUMN);
        itemCodeList.add(TrnPLInfo.keihiShokei_COLUMN);
        itemCodeList.add(TrnPLInfo.shokyakuRieki_COLUMN);
        itemCodeList.add(TrnPLInfo.genkaShokyaku_COLUMN);
        itemCodeList.add(TrnPLInfo.eigaiShueki_COLUMN);
        itemCodeList.add(TrnPLInfo.eigaiHiyo_COLUMN);
        itemCodeList.add(TrnPLInfo.honshahiHai_COLUMN);
        itemCodeList.add(TrnPLInfo.rieki_COLUMN);

        // エラーとみなすエラーコードセット作成
        Set invalidSet = new HashSet();
        invalidSet.add(ERRORCODE_NUM_REQUIRED);
        invalidSet.add(ERRORCODE_NUM_INVALID);
        invalidSet.add(ERRORCODE_NUM_ADJUST);

        boolean errorFlag = isProblemItem(itemCodeList, invalidSet);
        boolean warningFlag = isErrorItem(itemCodeList, ERRORCODE_NUM_NOINPUT);

        return createResultSymbol(errorFlag, warningFlag);
    }

    // 科目不整合 - 内訳
    public String getResultSymbolSubjectDetail() {

        // 対象項目リスト作成
        List itemCodeList = new ArrayList();
        itemCodeList.add(TrnPLInfo.uriage_COLUMN);
        itemCodeList.add(TrnPLInfo.buppan_COLUMN);
        itemCodeList.add(TrnPLInfo.uriUchiwake_COLUMN);
        itemCodeList.add(TrnPLInfo.elec_COLUMN);
        itemCodeList.add(TrnPLInfo.gas_COLUMN);
        itemCodeList.add(TrnPLInfo.water_COLUMN);
        itemCodeList.add(TrnPLInfo.sonota_COLUMN);
        itemCodeList.add(TrnPLInfo.suikouUchiwake_COLUMN);
        itemCodeList.add(TrnPLInfo.genzairyoKei_COLUMN);
        itemCodeList.add(TrnPLInfo.genzairyoShire_COLUMN);
        itemCodeList.add(TrnPLInfo.genzairyoZaiko_COLUMN);
        itemCodeList.add(TrnPLInfo.yasaiKei_COLUMN);
        itemCodeList.add(TrnPLInfo.yasaiShire_COLUMN);
        itemCodeList.add(TrnPLInfo.yasaiZaiko_COLUMN);
        itemCodeList.add(TrnPLInfo.houzaiKei_COLUMN);
        itemCodeList.add(TrnPLInfo.houzaiShire_COLUMN);
        itemCodeList.add(TrnPLInfo.houzaiZaiko_COLUMN);
        itemCodeList.add(TrnPLInfo.buppanKei_COLUMN);
        itemCodeList.add(TrnPLInfo.buppanShire_COLUMN);
        itemCodeList.add(TrnPLInfo.buppanZaiko_COLUMN);
        itemCodeList.add(TrnPLInfo.touSiireKei_COLUMN);
        itemCodeList.add(TrnPLInfo.touZaikoKei_COLUMN);
        itemCodeList.add(TrnPLInfo.sashihikiKei_COLUMN);
        itemCodeList.add(TrnPLInfo.yakuinSalary_COLUMN);
        itemCodeList.add(TrnPLInfo.yakuinBonus_COLUMN);
        itemCodeList.add(TrnPLInfo.yakuinRetire_COLUMN);
        itemCodeList.add(TrnPLInfo.yakuinKei_COLUMN);
        itemCodeList.add(TrnPLInfo.salarySalary_COLUMN);
        itemCodeList.add(TrnPLInfo.salaryBonus_COLUMN);
        itemCodeList.add(TrnPLInfo.salaryRetire_COLUMN);
        itemCodeList.add(TrnPLInfo.salaryKei_COLUMN);
        itemCodeList.add(TrnPLInfo.zakkyuSalary_COLUMN);
        itemCodeList.add(TrnPLInfo.zakkyuBonus_COLUMN);
        itemCodeList.add(TrnPLInfo.zakkyuRetire_COLUMN);
        itemCodeList.add(TrnPLInfo.zakkyuKei_COLUMN);
        itemCodeList.add(TrnPLInfo.kyuryoKei_COLUMN);
        itemCodeList.add(TrnPLInfo.bonusKei_COLUMN);
        itemCodeList.add(TrnPLInfo.retireKei_COLUMN);
        itemCodeList.add(TrnPLInfo.salaryUtiKei_COLUMN);

        // エラーとみなすエラーコードセット作成
        Set invalidSet = new HashSet();
        invalidSet.add(ERRORCODE_NUM_REQUIRED);
        invalidSet.add(ERRORCODE_NUM_INVALID);
        invalidSet.add(ERRORCODE_NUM_ADJUST);

        boolean errorFlag = isProblemItem(itemCodeList, invalidSet);
        boolean warningFlag = isErrorItem(itemCodeList, ERRORCODE_NUM_NOINPUT);

        return createResultSymbol(errorFlag, warningFlag);
    }

    // 科目不整合 - 借入金
    public String getResultSymbolSubjectLoan() {

        // 対象項目リスト作成
        List itemCodeList = new ArrayList();
        itemCodeList.add(TrnPLInfo.kashiireUp_COLUMN);
        itemCodeList.add(TrnPLInfo.kashiireDown_COLUMN);
        itemCodeList.add(TrnPLInfo.kashiireZandaka_COLUMN);
        itemCodeList.add(TrnPLInfo.kappuUp_COLUMN);
        itemCodeList.add(TrnPLInfo.kappuDown_COLUMN);
        itemCodeList.add(TrnPLInfo.kappuZandaka_COLUMN);
        itemCodeList.add(TrnPLInfo.leaseUp_COLUMN);
        itemCodeList.add(TrnPLInfo.leaseDown_COLUMN);
        itemCodeList.add(TrnPLInfo.leaseZandaka_COLUMN);
        itemCodeList.add(TrnPLInfo.touZoukaKei_COLUMN);
        itemCodeList.add(TrnPLInfo.touGenshoKei_COLUMN);
        itemCodeList.add(TrnPLInfo.touZandakaKei_COLUMN);

        // エラーとみなすエラーコードセット作成
        Set invalidSet = new HashSet();
        invalidSet.add(ERRORCODE_NUM_REQUIRED);
        invalidSet.add(ERRORCODE_NUM_INVALID);
        invalidSet.add(ERRORCODE_NUM_ADJUST);

        // ワーニングとみなすエラーコードセット作成
        Set warningSet = new HashSet();
        warningSet.add(ERRORCODE_NUM_NOINPUT);
        warningSet.add(ERRORCODE_NUM_ADJUST_LOAN);

        boolean errorFlag = isProblemItem(itemCodeList, invalidSet);
        boolean warningFlag = isProblemItem(itemCodeList, warningSet);

        return createResultSymbol(errorFlag, warningFlag);
    }

    // 内訳不整合 - 売上高
    public String getResultSymbolDetailSales() {

        boolean errorFlag = isErrorItem(TrnPLInfo.uriUchiwake_COLUMN, ERRORCODE_NUM_ADJUST_DETAIL);
        boolean warningFlag = isErrorItem(TrnPLInfo.uriUchiwake_COLUMN, ERRORCODE_NUM_NOINPUT);

        return createResultSymbol(errorFlag, warningFlag);
    }

    // 内訳不整合 - 売上原価
    public String getResultSymbolDetailCost() {

        boolean errorFlag = isErrorItem(TrnPLInfo.sashihikiKei_COLUMN, ERRORCODE_NUM_ADJUST_DETAIL);
        boolean warningFlag = isErrorItem(TrnPLInfo.sashihikiKei_COLUMN, ERRORCODE_NUM_NOINPUT);

        return createResultSymbol(errorFlag, warningFlag);
    }

    // 内訳不整合 - 水光熱費
    public String getResultSymbolDetailWater() {

        boolean errorFlag = isErrorItem(TrnPLInfo.suikouUchiwake_COLUMN, ERRORCODE_NUM_ADJUST_DETAIL);
        boolean warningFlag = isErrorItem(TrnPLInfo.suikouUchiwake_COLUMN, ERRORCODE_NUM_NOINPUT);

        return createResultSymbol(errorFlag, warningFlag);
    }

    // 内訳不整合 - 給料手当
    public String getResultSymbolDetailSalary() {

        boolean errorFlag = isErrorItem(TrnPLInfo.salaryUtiKei_COLUMN, ERRORCODE_NUM_ADJUST_DETAIL);
        boolean warningFlag = isErrorItem(TrnPLInfo.salaryUtiKei_COLUMN, ERRORCODE_NUM_NOINPUT);

        return createResultSymbol(errorFlag, warningFlag);
    }

    // ワーニング - POS売上
    public String getResultSymbolWarningSales() {

        boolean errorFlag = false;
        boolean warningFlag = isExistError(ERRORCODE_NUM_SALES);

        return createResultSymbol(errorFlag, warningFlag);
    }

    // ワーニング - 金額範囲
    public String getResultSymbolWarningLimit() {

        boolean errorFlag = false;
        boolean warningFlag = isExistError(ERRORCODE_NUM_LIMIT);

        return createResultSymbol(errorFlag, warningFlag);
    }

    /**
     * 結果文字生成
     * @param errorFlag エラー有無
     * @param warningFlag ワーニング有無
     * @return 結果文字
     */
    protected String createResultSymbol(boolean errorFlag, boolean warningFlag) {
        String result = null;
        if (errorFlag) {
            result = "×";
        } else if (warningFlag) {
            result = "△";
        } else {
            result = "○";
        }
        return result;
    }
}
