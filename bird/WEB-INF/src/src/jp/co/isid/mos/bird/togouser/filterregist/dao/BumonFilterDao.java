/*
 * 作成日: 2008/11/10
 */
package jp.co.isid.mos.bird.togouser.filterregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.togouser.filterregist.entity.UIBumonFilter;

/**
 * BIRDユーザ登録
 * @author S.yamauchi
 */
public interface BumonFilterDao {

    public static final Class BEAN = UIBumonFilter.class;
    public static final String select_ARGS = "USER_ID,BUMON_CD";
    /**
	 * 統合ユーザの最新情報取得
	 * 
	 * @param uiTogoUserSaisin
	 */
    public List select(String USER_ID,String BUMON_CD);

}
