/*
 * 作成日: 2006/02/27
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.sysadmin.useractregist.action;

/**
 * ユーザー別アクセス権限設定（編集画面）アクションインターフェース
 * 
 * @author xkhata
 *
 * 更新日:2009/12/16 xkinu 確認画面追加対応　登録アクションを削除し、確認アクションを追加しました。
 */
public interface UserActRegistAction {
	
	/* 初期化 */
	public String initialize();
	
	/* 戻るボタン */
	public String back();

	/* 確認ボタン */
	public String confirm();


}
