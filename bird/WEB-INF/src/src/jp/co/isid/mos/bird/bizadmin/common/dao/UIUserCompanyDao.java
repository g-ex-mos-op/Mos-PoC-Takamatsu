/*
 * 作成日: 2006/02/09
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.bizadmin.common.dao;

import java.util.List;


import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserCompany;

/**
 * ユーザー所属会社Dao
 * 
 * @author 慮
 * @modifier xkinu 2013/01/24 海外売上集信対応　
 *
 */
public interface UIUserCompanyDao {
	
	public static final Class BEAN = UIUserCompany.class;
	
	public static final String select_ARGS = "userId, miseFlg, isForeignIn";
	public static final String selectAllCompany_ARGS = "userId, miseFlg, isForeignIn";
    /**
     * ユーザー所属会社情報取得処理
     * 
     * @modifier xkinu 2013/01/24 海外売上集信対応　引数にisForeignInを追加
     * 
     * @param userId
     * @param miseFlg true:店舗保有会社(BC05KCOM)のみ false:BC02COMP全て
     * @param isForeignIn true:海外の会社を含む false:海外の会社は含まない
     * @return
     */
    public List select(String userId, boolean miseFlg, boolean isForeignIn);
    /**
     * 管理会社(BC05KCOM)・ユーザー所属会社情報取得処理
     * 
     * 管理会社(BC05KCOM)に登録されている全ての管理会社情報へ
     * ユーザー所属会社の情報を設定したデータを取得します。
     * 
     * @modifier xkinu 2013/01/24 海外売上集信対応　引数にisForeignInを追加
     * 
     * @param userId
     * @param miseFlg true:店舗保有会社(BC05KCOM)のみ false:BC02COMP全て
     * @param isForeignIn true:海外の会社を含む false:海外の会社は含まない
     * @return
     */
    public List selectAllCompany(String userId, boolean miseFlg, boolean isForeignIn);
}
