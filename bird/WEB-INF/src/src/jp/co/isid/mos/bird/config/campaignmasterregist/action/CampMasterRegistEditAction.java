package jp.co.isid.mos.bird.config.campaignmasterregist.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * キャンペーンマスタ登録 編集画面Action Interface
 *
 */
public interface CampMasterRegistEditAction extends CommonAction{

    /**
     * 戻る
     * @return
     */
    public String doBack();
    
    /**
	 * 確認
	 * @return 
	 */
	public String doConfirm();

	/**
     * 確認ダイアログ用確認
     * @return 
     */
    public String doConfirmDialog();
    
    /**
     * CSVダウンロード
     *  登録済み、エラー店舗の両方で使用
     * @return
     */
    public String doCsvDownload();
}