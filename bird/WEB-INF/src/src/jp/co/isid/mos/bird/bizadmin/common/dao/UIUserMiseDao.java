/*
 * 作成日: 2006/02/09
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.bizadmin.common.dao;

import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserMise;

/**
 * @author 慮
 *
 */
public interface UIUserMiseDao {

	public static final Class BEAN = UIUserMise.class;

	public static final String select_ARGS = "user_id";
    public UIUserMise select(String user_id);
}
