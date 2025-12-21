/*
 * 作成日: 2006/01/20
 *
 */
package jp.co.isid.mos.bird.commonform.businesssearch.dao;

import java.util.List;

import jp.co.isid.mos.bird.commonform.businesssearch.entity.CtlManagementCompany;

/**
 * DAO【業態選択対象管理会社】
 * @author xyuchida
 * @modifier xkinu 2013/01/24 海外売上集信対応　
 */
public interface CtlManagementCompanyDao {

    public static final Class BEAN = CtlManagementCompany.class;

    public static final String select_ARGS = "isForeignIn";
    /**
     * 検索
     * @param isForeignIn true:海外の会社を含む false:海外の会社は含まない
     * @return
     * @modifier xkinu 2013/01/24 海外売上集信対応　isForeignIn引数を追加
     */
    public List select(boolean isForeignIn);
}
