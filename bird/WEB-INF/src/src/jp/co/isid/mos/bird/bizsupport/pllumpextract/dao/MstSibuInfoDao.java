/*
 * 作成日: 2006/04/07
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.bizsupport.pllumpextract.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pllumpextract.entity.MstSibuInfo;

/**
 * @author xkhata
 *
 * TODO この生成された型コメントのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
public interface MstSibuInfoDao {

	public static final Class BEAN = MstSibuInfo.class;

	public static final String getRCSibuInfo_ARGS = "companyCd";

    /**
     * RC支部情報の取得
     * @param companyCd 管理会社企業コード
     * @return 支部一覧情報
     */
	public List getRCSibuInfo(String companyCd);
}
