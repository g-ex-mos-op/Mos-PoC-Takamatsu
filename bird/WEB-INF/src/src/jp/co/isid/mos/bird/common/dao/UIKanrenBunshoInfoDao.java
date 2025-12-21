/*
 * 
 * 作成日: 2006/02/07
 */
package jp.co.isid.mos.bird.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.UIKanrenBunshoInfo;

/**
 * 関連文書
 * @author xytamura
 */
public interface UIKanrenBunshoInfoDao {

    public Class BEAN = UIKanrenBunshoInfo.class;

    public static final String getKanrenBunsho_ARGS = "infoShu, regDate, seq";
    
    public static final String getViewKanrenBunsho_ARGS = "infoShu, regDate, seq, sysDate";

    public static final String deleteKanrenBunsho_ARGS = "infoShu, regDate, seq";

    public static final String getKanrenSakiBunsho_ARGS = "relInfoShu, relRegDate, relSeq";

    public static final String deleteKanrenSakiBunsho_ARGS = "relInfoShu, relRegDate, relSeq";

    /**
     * 関連文書の取得
     * @param infoShu 情報種別
     * @param regDate 登録日
     * @param seq シーケンス番号
     * @param sysDate システム日付
     * @return 関連文書
     */
    public List getKanrenBunsho(String infoShu, String regDate, String seq);
    
    /**
     * 照会用関連文書の取得
     * @param infoShu
     * @param regDate
     * @param seq
     * @param sysDate
     * @return
     */
    public List getViewKanrenBunsho(String infoShu, String regDate, String seq, String sysDate);
    /**
     * 関連文書の追加(SQL自動生成)
     * @param uIKanrenBunshoInfo
     */
    public  void insertKanrenBunsho(UIKanrenBunshoInfo uIKanrenBunshoInfo);
        
    /**
     * 関連文書の削除
     * @param infoShu 情報種別
     * @param regDate 登録日
     * @param seq シーケンス番号
     */
    public void deleteKanrenBunsho(String infoShu, String regDate, String seq);
        

    /**
     * 自分を関連先として登録している文書の取得
     * @param relInfoShu 関連先情報種別
     * @param relRegDate 関連先登録日
     * @param relSeq 関連先シーケンス番号
     */
    public List getKanrenSakiBunsho(String relInfoShu, String relRegDate, String relSeq);

    /**
     * 自分を関連先として登録している文書を削除
     * @param relInfoShu 関連先情報種別
     * @param relRegDate 関連先登録日
     * @param relSeq 関連先シーケンス番号
     */
    public void deleteKanrenSakiBunsho(String relInfoShu, String relRegDate, String relSeq);

}
