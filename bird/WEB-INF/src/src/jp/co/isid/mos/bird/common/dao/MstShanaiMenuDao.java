package jp.co.isid.mos.bird.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.MstShanaiMenu;


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
    public static final String select_ARGS = "listMenuCd, sysDate";
    /**
     * 検索情報取得
     * 
     * 全件検索を行う場合は、パラメータをnullで指定します。
     * @param listMenuCd
     * @param sysDate
     * @return
     */
    public List select(List listMenuCd, String sysDate);

}
