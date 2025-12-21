/*
 * 作成日: 2006/01/31
 */
package jp.co.isid.mos.bird.office.formregist.action;

/**
 * フォーム登録 フォーム照会順位設定画面アクション インターフェイス
 * @author xytamura
 */
public interface FormRegistAction {
    /**
     * 初期処理
     * @return
     */
    public String initialize();
    
//    /**
//     * タブ変更処理
//     * @return
//     */
//    public String changeTab();
    
    /**
     * 取消  
     * @return
     */
    public String cancel();
    
    /**
     * 登録処理
     * @return String
     */
    public String regist();
    
    /**
     * 公開対象選択フォーム
     * @return
     */
    public String callFormKokai();
    
    /**
     * 文書ファイル削除処理
     * @return
     */
    public String deleteMainFile();
    
    /**
     * 添付ファイル削除処理
     * @return
     */
    public String deleteAttachFile();
    
    /**
     * 文書ファイルアップロード処理
     * @return
     */
    public String uploadMainFile();
    
    /**
     * 添付ファイルアップロード処理
     * @return
     */
    public String uploadAttachFile();
    
    /**
     * 関連文書選択フォーム
     * @return
     */
    public String callFormKanrenBunsho();
    
    /**
     * 関連文書削除
     * @return
     */
    public String deleteKanrenBunsho();
    
    /**
     * カテゴリ変更
     */
    public String changeCategory();
}
