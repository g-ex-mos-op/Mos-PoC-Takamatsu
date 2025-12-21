package jp.co.isid.mos.bird.bizreport.takuhainiporef.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.takuhainiporef.entity.TakuMstInfo;

/**
 * 宅配種別情報Daoクラス
 * 
 * @author xjung
 */
public interface TakuMstInfoDao {

    /**
     * 宅配種別情報エンティティクラス
     */
    public static final Class BEAN = TakuMstInfo.class;
 
    /**
     * 宅配種別情報を取得する
     * @return List 宅配種別情報
     */
    public List select();    
}