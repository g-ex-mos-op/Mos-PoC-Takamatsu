package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity.UserCompanyInfo;

/**
 * ユーザ所属会社情報Daoクラス
 * 
 * @author xjung
 */
public interface UserCompanyInfoDao {

    /**
     * ユーザ所属会社情報エンティティクラス
     */
    public static final Class BEAN = UserCompanyInfo.class;

    /**
     * ユーザ所属会社情報取得時のパラメータ
     */
    public static final String selectMiseGepoInfo_ARGS = "userId";
 
    /**
     * ユーザ所属会社情報リストを取得する
     * @param  userId ユーザID
     * @return List  ユーザ所属会社情報リスト
     */
    public List select(String userId);    
}