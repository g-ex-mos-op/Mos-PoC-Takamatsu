/*
 * 作成日: 2006/02/09
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.bizadmin.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizadmin.common.entity.CodCompany;

/**
 * ユーザー所属会社Dao
 * 
 * @author 慮
 *
 */
public interface CodCompanyDao {
	
	public static final Class BEAN = CodCompany.class;
	public static final String select_ARGS = "isForeignIn";

    /**
     * 会社情報取得処理
     * 
     * @param isForeignIn true:海外の会社を含む false:海外の会社は含まない
     * @return
     */
    public List select(boolean isForeignIn);
}
