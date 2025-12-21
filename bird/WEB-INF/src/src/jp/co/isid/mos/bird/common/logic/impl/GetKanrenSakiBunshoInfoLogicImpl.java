/*
 * 作成日: 2006/06/01
 *
 */
package jp.co.isid.mos.bird.common.logic.impl;

import java.util.List;
import jp.co.isid.mos.bird.common.dao.UIKanrenBunshoInfoDao;
import jp.co.isid.mos.bird.common.logic.GetKanrenSakiBunshoInfoLogic;

/**
 * 自分を関連先として登録している文書の取得
 * @author xytamura
 */
public class GetKanrenSakiBunshoInfoLogicImpl implements GetKanrenSakiBunshoInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BCN000L10";
    /**
     * 関連文書Dao
     */
    private UIKanrenBunshoInfoDao uIKanrenBunshoInfoDao;

    
    /**
     * @see jp.co.isid.mos.bird.common.logic.impl.GetKanrenSakiBunshoInfoLogic#execute(java.lang.String, java.lang.String, java.lang.String)
     */
    public List execute(String infoShu, String regDate, String seq) {
        //関連文書を取得
        List  listKanrenBunsho =  getUIKanrenBunshoInfoDao().getKanrenSakiBunsho(infoShu, regDate, seq);
        return listKanrenBunsho;
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