/*
 * 作成日: 2006/4/10
 */
package jp.co.isid.mos.bird.bizsupport.similarshop.action;

/**
 * 類似店検索アクションインターフェース
 * @author Nakajima
 */
public interface RuijitenSearchAction {

    /**
     * 条件画面(初期処理)
     */
    public String initialize();


    /**
     * 検索
     */
    public String execute();
    
    /**
     * 店検索フォーム
     * @return
     */
    public String callMiseForm();
    
    /**
     * 店検索フォーム(結果画面から)
     * @return
     */
    public String callMiseFormResult();
    
}