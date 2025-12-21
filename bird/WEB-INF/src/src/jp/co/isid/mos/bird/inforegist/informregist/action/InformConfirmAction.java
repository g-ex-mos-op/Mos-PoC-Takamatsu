/*
 * 作成日: 2006/2/8
 */
package jp.co.isid.mos.bird.inforegist.informregist.action;

/**
 * インフォメーション登録確認アクションインターフェース
 * @author itamoto
 */
public interface InformConfirmAction {

    /**
     * 初期処理
     */
	public String initialize();

    /**
     * 公開対象の表示処理
     */
    public String startPublicTarget();

    /**
     * 登録処理
     */
	public String regist();

    /**
     * 戻る処理
     */
    public String cancel();
    
    /**
     * アップロード
     */
    public String upload();
}
