package jp.co.isid.mos.bird.bizsupport.moschickenmastregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.entity.MstKanriMenuGroup;

/**
 * モスチキン管理対象メニューグループDao
 * 
 * @author xkinu
 *
 */
public interface MstKanriMenuGroupDao {
    /**
     * 格納エンティティクラス
     */
    public static final Class BEAN = MstKanriMenuGroup.class;
    
    /**
     * 指定管理番号の管理対象メニューグループ情報を取得する時のパラメータ
     */
    public static final String select_ARGS = "ckanriNo";
    /**
     * 新規登録処理時のパラメータ
     */
    public static final String insert_ARGS = "entity";
    /**
     * 削除登録処理時のパラメータ
     */
    public static final String delete_ARGS = "ckanriNo";
    
    /**
     * 指定管理番号の管理対象メニューグループ情報を取得する
     * 
     * @param ckanriNo　管理番号
     * @return
     */
    public List select(String ckanriNo);
    /**
     * 新規登録処理
     * 
     * @param entity　管理対象メニューグループエンティティ
     * @return
     */
    public void insert(MstKanriMenuGroup entity);
    /**
     * 削除登録処理
     * 
     * @param ckanriNo　管理番号
     * @return
     */
    public void delete(String ckanriNo);

}
