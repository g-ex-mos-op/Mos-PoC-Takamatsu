/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dao;

import java.util.List;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.entity.TrnPayDetaileStatement;

/**
 * テナント入金明細PDF情報Dao
 * 
 * 作成日:2009/06/19
 * @author xkinu
 *
 */
public interface TrnPayDetaileStatementDao {
    /**
     * 情報格納エンティティクラス
     */
    public static final Class BEAN = TrnPayDetaileStatement.class;
    
    /**
     * 検索情報取得時のパラメータ
     */
    public static final String select_ARGS = "companyCd, miseCd, taishoYm, kaisuu";
    /**
     * 更新するときに、このプロパティだけをSQLに含めたいという場合もあります。 
     * そのような場合は、PERSISTENT_PROPSアノテーションを使います。
     * insertメソッドで、プライマリーキー、versionNo、timestampのプロパティに加えて、 
     * PERSISTENT_PROPSアノテーションで指定したプロパティが永続化の対象になります。
     * 
     */
    public static final String update_PERSISTENT_PROPS = "hakkoDt, fileName, lastUser, lastPgm";
    /**
     * timestampによる排他制御用のプロパティ名を
     * デフォルトのtimestampから変えるときに使うのがTIMESTAMP_PROPERTYアノテーションです
     */
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";
    /**
     * 検索情報取得
     * 
     * @param companyCd
     * @param miseCd
     * @param yyyyMM
     * @param kai
     * 
     * @return
     */
    public List select(String companyCd, String miseCd, String taishoYm, String kaisuu);
    /**
     * 新規登録
     * @param entity
     * @return
     */
    public int insert(TrnPayDetaileStatement entity);
    
    /**
     * 更新
     * @param entity
     * @return
     */
    public int update(TrnPayDetaileStatement entity);

    /**
     * 更新
     * @param entity
     * @return
     */
    public int delete(TrnPayDetaileStatement entity);

}
