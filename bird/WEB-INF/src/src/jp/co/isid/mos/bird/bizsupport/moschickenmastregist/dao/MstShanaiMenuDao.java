package jp.co.isid.mos.bird.bizsupport.moschickenmastregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.entity.MstShanaiMenu;

/**
 * 社内メニューマスタDaoインターフェース
 * 
 * @author xkinu
 *
 */
public interface MstShanaiMenuDao {
    /**
     * 格納エンティティクラス
     */
    public static final Class BEAN = MstShanaiMenu.class;
    
    /**
     * 検索情報取得時のパラメータ
     */
    public static final String select_ARGS = "cdFrom, cdTo, name, sysDate";
    /**
     * 検索情報取得
     * 
     * 必須条件：販売終了日＞システム日付
     * 
     * @param cdFrom
     * @param cdTo
     * @param name
     * @param sysDate
     * @return
     */
    public List select(String cdFrom, String cdTo, String name, String sysDate);

}
