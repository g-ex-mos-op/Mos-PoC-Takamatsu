package jp.co.isid.mos.bird.bizreport.takuhaisuiiref.common;

import java.util.ArrayList;
import java.util.List;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 宅配売上推移表 共通クラス
 * 
 * @author xwatanabe
 */
public class TakuhaiSuiiCommon {

    /**
     * 期間指定の種類を取得
     * 
     * 指定年月含めて未来「リスト個数」月分を生成します。
     * 
     * @param  String 年月(YYYYMM)
     * @param  int    リスト個数 
     * @return List   期間データ(YYYYMMのリスト)
     * @exception ApplicationException
     */
    public static List makeListMonth(String yyyymm, int cnt) throws ApplicationException {

        //返却用リスト
        List list = new ArrayList();

        if(yyyymm == null || yyyymm.length() != 6 ){
            //空のリストを返却
            return list;
        }
        
        for (int i = 0; i < cnt; i++) {
            String code = "";
            try {
                code = DateManager.getNextMonth(yyyymm, i);
            }catch (Exception ex) {
                throw new FtlSystemException("期間指定生成"
                        , "アプリ日付年月["+yyyymm+"]から["+i+"]を引く際のDateManager.getNextMonthメソッド処理で例外が発生しました。"
                        , ex);
            }
            list.add(code);
        }
        return list;
    }

    /**
     * 指定した年度(YYYY)の値より直近の年月を求める
     * @param  String 年度(YYYY)
     * @param  String 現在の日付(yyyymmdd)
     * @return String 年月(YYYYMM)
     */
    public static String getTyokkinYm(String nendo, String date) {

        if (nendo == null || nendo.length() != 4){
            return null;
        }
        
        //現在の年月
        String nowYm = "";
        if(date != null && date.length() == 8){
            nowYm = date.substring(0,6);
        }

        //現在の年度
        String nowNendo = DateManager.getCurrentYear(nowYm);

        //指定した年度が、現在の年度の場合は、現在の年月を返却
        if(nendo.equals(nowNendo)) {
            return nowYm;
        }
        //それ以外の場合は、指定年度の最新年月を返却
        else {
            
            try {
                return DateManager.getNextYear(nendo,1) + "03";
            } catch (Exception e) {
                
            }
        }
        return null;
    }
}