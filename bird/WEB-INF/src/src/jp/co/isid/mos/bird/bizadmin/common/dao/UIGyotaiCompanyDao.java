/*
 * 作成日: 2006/02/16
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.bizadmin.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizadmin.common.entity.UIGyotaiCompany;



/**
 * 業態対象会社コード
 * 
 * 作成日:2010/03/19
 * @author xkinu
 *
 */
public interface UIGyotaiCompanyDao {
	
	public static final Class BEAN = UIGyotaiCompany.class;
	
	public static final String select_ARGS = "gyotaiKbn";
	
	public List select(List gyotaiKubun);

}
