package jp.co.isid.mos.bird.bill.detailbilldownload.dao;

import java.util.List;
import jp.co.isid.mos.bird.bill.detailbilldownload.entity.UIUrikakeList;

/**
 * 売掛先一覧情報
 * @author xwatanabe
 */
public interface UIUrikakeListDao {

    public static final Class BEAN = UIUrikakeList.class;

    public static final String getUrikakeList_ARGS = "companyCd, ownerCd";
    
    /**
     * 売掛先一覧の取得
     * @param  companyCd 会社コード
     * @param  ownerCd   オーナーコード
     * @return List
     */
    public List getUrikakeList(String companyCd,String ownerCd);
    
}