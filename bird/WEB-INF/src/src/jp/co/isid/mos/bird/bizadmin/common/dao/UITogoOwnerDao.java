/*
 * 作成日: 2006/02/16
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.bizadmin.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizadmin.common.entity.UITogoOwner;

/**
 * 
 * 作成日:2010/03/19
 * @author xkinu
 *
 */
public interface UITogoOwnerDao {
	
	public static final Class BEAN = UITogoOwner.class;
	
	public static final String select_ARGS = "onerCd, companyCd";
	
	public List select(String onerCd, String companyCd);

}
