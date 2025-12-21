package jp.co.isid.mos.bird.storemanage.mlresultregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.EntryDate;


/**
 * エントリー日付管理
 * @author kusama
 */
public interface EntryDateDao {

    public static final Class BEAN = EntryDate.class;

    public static final String getList_ARGS = "companyCd, sysDt";
    
    /**
     * 登録対象一覧の取得
     * @param companyCd 企業コード
     * @param sysDt     システム日付
     * @return List
     */
    public List getList(String companyCd, String sysDt);
    
}