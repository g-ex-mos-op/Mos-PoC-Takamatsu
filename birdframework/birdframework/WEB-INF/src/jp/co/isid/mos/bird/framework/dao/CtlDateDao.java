/*
 * 作成日: 2006/01/18
 *
 */
package jp.co.isid.mos.bird.framework.dao;

import java.util.List;

import jp.co.isid.mos.bird.framework.entity.CtlDate;


/**
 * コントロール日付情報を取得します
 * @author xnkusama
 */
public interface CtlDateDao {

    public Class BEAN = CtlDate.class;

    public static final String getDateInfo_SQL =            
         " select  "
    +    "        day_kbn "
    +    " ,      cnt_date "
    +    " from   br33cday "
    +    " order by day_kbn";
    
    /**
     * コントロール日付情報を取得する
     * @return 
     */   
    public List getDateInfo();

}
