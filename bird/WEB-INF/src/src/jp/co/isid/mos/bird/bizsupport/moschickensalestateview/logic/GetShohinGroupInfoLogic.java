/*
 * 作成日: 2006/09/19
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickensalestateview.logic;

import java.util.List;

/**
 *  商品グループ情報取得ロジック
 * 
 * @author xlee
 */
public interface GetShohinGroupInfoLogic {

    /**
     *  商品グループ情報取得
     * @param ckanriNo タイトルの管理番号
     * @return　 商品グループ情報
     */
    public List execute(String titleKanriNo);
}
