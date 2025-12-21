package jp.co.isid.mos.bird.storemanage.claimtotal.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * お客様の声分類別集計 条件画面Action
 *
 */
public interface ClaimTotalAction extends CommonAction {

    /**
     * 店検索
     * @return
     */
    public String doSearchMise();
    
    /**
     * オーナー検索
     */
    public String doSearchOner();
    
    /**
     * 検索
     * @return
     */
    public String doExecute();
    
    /**
     * お客様の声リンク
     */
    public String linkClaim();
}