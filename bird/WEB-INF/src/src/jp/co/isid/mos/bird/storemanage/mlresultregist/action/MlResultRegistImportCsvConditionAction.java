/*
 * 作成日: 2006/07/21
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * CSV取込条件Action
 * 
 * @author xyuchida
 */
public interface MlResultRegistImportCsvConditionAction extends CommonAction {

    /**
     * 受験結果CSV取込
     * 
     * @return 画面遷移情報
     */
    public String importResultCsv();

    /**
     * ライセンス発行CSV取込
     * 
     * @return 画面遷移情報
     */
    public String importIssuLicenseCsv();

    /**
     * ライセンス更新CSV取込
     * 
     * @return 画面遷移情報
     */
    public String importUpdateLicenseCsv();

    /**
     * 戻る
     * 
     * @return 画面遷移情報
     */
    public String back();
}
