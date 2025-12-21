/*
 * 作成日: 2006/04/17
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.bizsupport.pllumpextract.dao;

import jp.co.isid.mos.bird.bizsupport.pllumpextract.entity.UIPlMaxMonthInfo;


/**
 * @author xtoshi
 *
 * TODO この生成された型コメントのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
public interface UIPlMaxMonthDao {
	public static final Class BEAN = UIPlMaxMonthInfo.class;
    
    public static final String getPlMaxMonth_ARGS = "";
    
    /**
     * PL年月のMAX年月の取得
     * @return PL年月情報
     */
    public String getPlMaxMonth();
}
