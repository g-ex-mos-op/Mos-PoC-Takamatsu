/*
 * 作成日: 2006/06/01
 *
 */
package jp.co.isid.mos.bird.common.logic.impl;

import jp.co.isid.mos.bird.common.dao.UIKanrenBunshoInfoDao;
import jp.co.isid.mos.bird.common.logic.DeleteKanrenSakiBunshoLogic;

/**
 * 関連文書として登録している情報削除ロジック
 * @author xytamura
 */
public class DeleteKanrenSakiBunshoLogicImpl implements DeleteKanrenSakiBunshoLogic  {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BCN000L09";
    
    /**
     * 関連文書Dao
     */
    private UIKanrenBunshoInfoDao uIKanrenBunshoInfoDao;

    /**
     * @see jp.co.isid.mos.bird.common.logic.DeleteKanrenSakiBunshoLogic#execute(java.lang.String, java.lang.String, java.lang.String)
     */
    public void execute(String infoShu, String regDate, String seq) {
        //データを削除
        getUIKanrenBunshoInfoDao().deleteKanrenSakiBunsho(infoShu, regDate, seq);
    }
    
    /**
     * 関連文書Daoを取得します。
     * @return 関連文書Dao
     */
    public UIKanrenBunshoInfoDao getUIKanrenBunshoInfoDao() {
        return uIKanrenBunshoInfoDao;
    }

    /**
     * 関連文書Daoを設定します。
     * @param 関連文書Dao
     */
    public void setUIKanrenBunshoInfoDao(
            UIKanrenBunshoInfoDao kanrenBunshoInfoDao) {
        uIKanrenBunshoInfoDao = kanrenBunshoInfoDao;
    }

}