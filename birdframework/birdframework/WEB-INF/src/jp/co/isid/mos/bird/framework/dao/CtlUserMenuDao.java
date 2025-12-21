/*
 * 作成日: 2006/02/06
 *
 */
package jp.co.isid.mos.bird.framework.dao;

import java.util.List;

import jp.co.isid.mos.bird.framework.entity.CtlUserMenu;

/**
 * ユーザメニュー
 * @author xytamura
 */
public interface CtlUserMenuDao {
    
    public Class BEAN = CtlUserMenu.class;

//    public static final String getEnableMenu_ARGS   = "userId, pgmId";
//    public static final String getDisableMenu_ARGS  = "userId, pgmId";
    public static final String getExtraMenu_ARGS    = "userId, pgmId";
    
//    /**
//     * アクセス可能メニューを取得する
//     * @param userId ユーザＩＤ
//     * @param pgmId　機能ＩＤ
//     * @return アクセス可能なメニュー
//     */   
//    public List getEnableMenu(String userId, String pgmId);
//
//    /**
//     * アクセス不可能メニューを取得する
//     * @param userId ユーザＩＤ
//     * @param pgmId  機能ＩＤ
//     * @return　アクセス不可能なメニュー
//     */
//    public List getDisableMenu(String userId, String pgmId);

    /**
     * 上限拡張可能メニューを取得する
     * @param  userId ユーザＩＤ
     * @param  pgmId  機能ＩＤ
     * @return 上限拡張可能なメニュー
     */   
    public List getExtraMenu(String userId, String pgmId);
}
