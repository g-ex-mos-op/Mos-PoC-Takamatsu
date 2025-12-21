/**
 * 
 */
package jp.co.isid.mos.bird.config.zenurikakuteiregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.config.zenurikakuteiregist.entity.TrnUsrStatus;

/**
 * 売上速報レポート確定状況Dao
 * 
 * 作成日:2008/08/15
 * @author xkinu
 *
 */
public interface TrnUsrStatusDao {

    public static final Class BEAN = TrnUsrStatus.class;
    /**
     * 検索時のパラメータ
     */
    public static final String select_ARGS = "taishoYmFrom, taishoYmTo";
    /**
     * 新規登録処理時のパラメータ
     */
    public static final String insert_ARGS = "entity";
    /**
     * 削除処理時のパラメータ
     */
    public static final String update_ARGS = "entity";
    
    public static final String update_PERSISTENT_PROPS 
    = "statusFlg, kakuteiFlg, lastUser, lastPgm";

    /**
     * 対象期間ステータス情報取得処理
     * 
     * @param taishoYmFrom
     * @param taishoYmTo
     * @return
     */
    public List select(String taishoYmFrom, String taishoYmTo);
    /**
     * 新規登録処理
     * 
     * @param entity　エンティティ
     * @return
     */
    public int insert(TrnUsrStatus entity);
    /**
     * 更新登録処理
     * 
     * @param entity　エンティティ
     * @return
     */
    public int update(TrnUsrStatus entity);

}
