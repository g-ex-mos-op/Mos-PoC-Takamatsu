/*
 * 作成日: 2008/10/7
 */
package jp.co.isid.mos.bird.bizreport.groupeigyoniporef.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.entity.UIAvgUriage;;


/**
 * オーナー営業月報ＣＳＶ用データ検索Dao
 * 
 * @author xayumi
 */
public interface UIOnerGepoAvgCSVDao {

    public static final Class BEAN = UIAvgUriage.class;

    public static final String selectAvg_ARGS = "companyCd, onerCd, taishoYM";

    /**
     * 
     * @param companyCd 会社コード
     * @param onerCd    オーナーコード
     * @param avgKBN
     * @param taishoYM
     * @return
     */
    public List selectAvg(String companyCd, String onerCd, String taishoYM);

}