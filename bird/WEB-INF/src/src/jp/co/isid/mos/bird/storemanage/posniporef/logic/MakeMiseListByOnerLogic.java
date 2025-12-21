/*
 * 作成日: 2007/02/15
 */
package jp.co.isid.mos.bird.storemanage.posniporef.logic;

import java.util.List;

/**
 * POS日報　オーナーに紐づく店舗リストの生成
 * @author xwatanabe
 *
 */
public interface MakeMiseListByOnerLogic {

    /**
     * オーナーに紐づく店舗リストの生成。
     * @param  companyCd
     * @param  onerCd
     * @param  date(yyyyMMdd)
     * @return List
     */
    public List execute(String companyCd, String onerCd, String date, String userId, boolean limitFlg);
}
