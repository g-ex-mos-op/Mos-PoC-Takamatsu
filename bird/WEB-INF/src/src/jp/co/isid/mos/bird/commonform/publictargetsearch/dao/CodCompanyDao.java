/*
 * 作成日: 2006/02/09
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.commonform.publictargetsearch.dao;

import java.util.List;

import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.CodCompany;

/**
 * ユーザー所属会社Dao
 * 
 * @author 慮
 *
 */
public interface CodCompanyDao {
	
	public static final Class BEAN = CodCompany.class;

    /**
     * ユーザー所属会社情報取得処理
     * 
     * @param userId
     * @return
     */
    public List select();
}
