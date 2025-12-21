/**
 * 
 */
package jp.co.isid.mos.bird.inforegist.notificationregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.UIViewKanrenBunsho;

/**
 * 作成日:2010/06/01
 * @author xkinu
 *
 */
public interface UIKanrenBunshoDao {
    public Class BEAN = UIViewKanrenBunsho.class;

    public static final String select_ARGS = "lastTmspDt";
    
    /**
     * 照会用関連文書の取得
     * 
     * 対象文書全体の関連文書を取得できます。
     * 
     * @param lastTmspDt yyyy-MM-dd
     * 
     * @return
     */
    public List select(String lastTmspDt);
}
