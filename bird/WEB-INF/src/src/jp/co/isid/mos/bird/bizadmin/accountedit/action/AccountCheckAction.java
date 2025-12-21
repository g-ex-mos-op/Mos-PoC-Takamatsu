/*
 * 作成日: 2006/02/16
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.bizadmin.accountedit.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * @author 慮
 *
 */
public interface AccountCheckAction extends CommonAction {
	public String initialize();
	public String update_check();
	public String back();
	public String searchOner();
	public String searchMise();
	public String businessDelete();
	public String businessSearch();
}
