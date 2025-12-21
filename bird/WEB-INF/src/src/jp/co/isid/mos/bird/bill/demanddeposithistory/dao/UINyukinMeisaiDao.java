/*
 * 作成日: 2006/08/17
 */
package jp.co.isid.mos.bird.bill.demanddeposithistory.dao;

import java.util.List;
import jp.co.isid.mos.bird.bill.demanddeposithistory.entity.UINyukinMeisai;

/**
 * 入金明細取得
 * 
 * @author xwatanabe
 */
public interface UINyukinMeisaiDao {

    public static final Class BEAN = UINyukinMeisai.class;
    
    public static final String getNyukinList_ARGS = "companyCd, onerCd, startYm, endYm";

    /**
     * 入金明細の取得
     * @param String companyCd  会社コード
     * @param String onerCd     オーナーコード
     * @param String startYm    表示開始年月
     * @param String endYm      表示終了年月
     * @return List
     */
    public List getNyukinList(String companyCd, String onerCd, String startYm, String endYm);

}
