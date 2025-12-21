/*
 * 作成日: 2009/02/23
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.togouser.userregist.dao;

import jp.co.isid.mos.bird.togouser.userregist.entity.CodCompany;
import jp.co.isid.mos.bird.togouser.userregist.entity.RankCode;

/**
 * @author K.Nihonyanagi
 *
 */
public interface RankCodeDao {
    public static final Class BEAN = RankCode.class;
    public RankCode getRankCode(String bumonCd);
	
}
