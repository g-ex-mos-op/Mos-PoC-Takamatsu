/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.vansmastregist.logic;

import java.util.List;

/**
 * 荷姿情報の取得ロジックインターフェース
 * @author narita
 */
public interface SearchNisugataLogic {

    /**
     * 荷姿情報の取得を行う
     * return nisugataList 荷姿情報のリスト
     */
    public List execute();
}
