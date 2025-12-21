/*
 * 作成日: 2006/01/18
 */
package jp.co.isid.mos.bird.framework.control;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import jp.co.isid.mos.bird.framework.entity.CtlDate;

/**
 * 日付関連情報の管理クラス<br>
 * 日付区分<br>
 *   01:営業費付け
 *   02:システム日付
 *   03:PL日付
 *   04:GENESIS検収日付
 *   05:直営ＰＬ年月
 * @author xnkusama
 */
public class BirdDateInfo {
    
    private HashMap _map;
    
    public static final String CTL_DATE_INFO = "CTL_DATE_INFO";
    
    /**
     * コンストラクタ
     */
    public BirdDateInfo() {
        super();
    }
    
    /**
     * 情報を格納する
     * @param obj 格納する情報
     * @param key キー
     */
    public void setInfo(final Object obj, final String key) {
        if (_map == null) {
            _map = new HashMap();
        }
        _map.put(key, obj);
    }
    
    /**
     * コントロール日付情報取得
     * @return CtlDate
     */
    public List getCtlDate() {
        return (List) _map.get(CTL_DATE_INFO);
    }

    /**
     * アプリ日付取得処理
     * @return
     */
    public String getAppDate() {
        CtlDate entity = (CtlDate) getCtlDate().get(0);
        return entity.getCntDate();
    }
    
    /**
     * システム日付取得処理
     * @return
     */
    public String getSysDate() {
        CtlDate entity = (CtlDate) getCtlDate().get(1);
        return entity.getCntDate();
    }
    
    /**
     * 検収日付取得処理
     * @return
     */
    public String getKenshuDt() {
        CtlDate entity = (CtlDate) getCtlDate().get(2);
        return entity.getCntDate();
    }
    
    /**
     * 現在日付を「yyyy'年'MM'月'dd'日'　hh'時'mm'分'　現在」フォーマットで取得
     * @return String
     */
    public String getCurrentTime() {
        Date currentTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy'年'MM'月'dd'日'　HH'時'mm'分'　現在", Locale.JAPAN);
        String ret = "";
        synchronized (sdf) {
            sdf.setLenient(true);
            ret = sdf.format(currentTime);
        }
        
        return ret;
    }
}