/*
 * 作成日: 2006/07/18
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * マスターライセンス結果登録条件アクション
 * 
 * @author kusama
 */
public interface MlResultRegistCondAction extends CommonAction {

    /**
     * 受験結果登録
     */
    public String regist();
    
    /**
     * 一括取込
     */
    public String importCsv();
    
    /**
     * オーナー検索
     */
    public String callOnerForm();
    
    /**
     * 店検索
     */
    public String callMiseForm();
}