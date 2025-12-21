/*
 * 作成日: 2008/09/05
 */
package jp.co.isid.mos.bird.config.summenuregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.config.summenuregist.entity.MstSumMenu;

/**
 * 店舗メーター管理状況Dao
 * 
 * @author xnkusama
 */
public interface MstSumMenuDao {

    public static final Class BEAN = MstSumMenu.class;
    public static final String selectSumMenuList_ARGS = "menuCd";
    public static final String selectKoMenuList_ARGS = "sumMenuCd";
    public static final String checkKoExist_ARGS = "menuCd";
    /**
     * 管理状況の取得
     * @return List
     */
    public List selectSumMenuList(String menuCd);
    
    /**
     * 子メニュー一覧取得
     * @param sumMenuCd
     * @return List
     */
    public List selectKoMenuList(String sumMenuCd);
    
    /**
     * 子メニュー登録済みチェック
     * @param menuCd
     * @return
     */
    public MstSumMenu checkKoExist(String menuCd);
    
    /**
     * 集約設定一覧CSVデータ取得
     * @return
     */
    public List selectSumMenuCsv();
    
    /**
     * 新規登録
     * @param entity
     */
    public void insertMenu(MstSumMenu entity);
    
    /**
     * 更新登録
     * @param entity
     */
    public void updateMenu(MstSumMenu entity);
    
    /**
     * 指定集約メニュー関連メニュー削除
     * @param sumMenuCd
     */
    public void deleteMenu(MstSumMenu entity);
    
}