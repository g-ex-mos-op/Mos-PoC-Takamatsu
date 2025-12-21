/*
 * 作成日: 2010/03/17
 *
 */
package jp.co.isid.mos.bird.bizadmin.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserOner;


/**
 * ユーザー対応オーナーDao
 * 
 * 作成日:2010/03/17
 * @author xkinu
 * @modifier xkinu 2013/01/24 海外売上集信対応　
 *
 */
public interface UIUserOnerDao {
	
	public static final Class BEAN = UIUserOner.class;
	
	public static final String select_ARGS = "userId, zokuseiKbn, isForeignIn";
	/**
	 * 
     * @modifier xkinu 2013/01/24 海外売上集信対応　引数にisForeignInを追加
	 * 
	 * @param userId
	 * @param zokuseiKbn
     * @param isForeignIn true:海外の会社を含む false:海外の会社は含まない
	 * @return
	 */
	public List select(String userId, String zokuseiKbn, boolean isForeignIn);
	
	public static final String selectAllComp_ARGS = "userId, zokuseiKbn, isForeignIn";
	/**
     * 管理会社(BC05KCOM)に登録されている全ての管理会社情報へ
     * ユーザー対応オーナー所属管理会社の情報を設定したデータを取得します。
	 * 
     * @modifier xkinu 2013/01/24 海外売上集信対応　引数にisForeignInを追加
     * 
	 * @param userId
	 * @param zokuseiKbn
     * @param isForeignIn true:海外の会社を含む false:海外の会社は含まない
	 * @return
	 */
	public List selectAllComp(String userId, String zokuseiKbn, boolean isForeignIn);
}
