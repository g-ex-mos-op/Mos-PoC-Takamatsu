/*
 * 作成日: 2008/11/10
 */
package jp.co.isid.mos.bird.togouser.filterregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.togouser.filterregist.entity.CodBumonName;

/**
 * BIRDユーザ登録
 * @author S.yamauchi
 */
public interface CodBumonDao {

    public static final Class BEAN = CodBumonName.class;
    public static final String select_ARGS = "BUMON_CD";
    /**
	 * 統合ユーザの最新情報取得
	 * 
	 * @param uiTogoUserSaisin
	 */
    public List select(String BUMON_CD);

}
