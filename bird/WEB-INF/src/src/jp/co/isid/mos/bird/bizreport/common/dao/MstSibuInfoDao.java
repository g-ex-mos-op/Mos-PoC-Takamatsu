package jp.co.isid.mos.bird.bizreport.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.entity.MstSibuInfo;

/**
 * 対象支部情報取得Daoクラス
 * 
 * @author xjung
 */
public interface MstSibuInfoDao {

    /** 対象支部情報エンティティクラス */
    public static final Class BEAN = MstSibuInfo.class;

    /** 対象支部情報取得時のパラメータ */
    public static final String selectSibuInfo_ARGS =
							    	  "companyCd"
							    	+ ", limitFlg"
							    	+ ", userId"
    								+ ", shukeiKbn";
    /**
     * 対象支部リストを取得する
     * @param companyCd 会社コード
     * @param limitFlg	 制限区分
     * @param userId	 ユーザID
     * @param shukeiKbn 集計区分
     * @return List     対象支部情報
     */
    public List selectSibuInfo(
		String companyCd,
		boolean limitFlg,
		String userId,
		String shukeiKbn);
}