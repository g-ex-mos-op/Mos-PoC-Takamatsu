/*
 * 作成日: 2005/11/25
 */
package jp.co.isid.mos.bird.framework.action;


/**
 * Excel出力
 * @author xkitamoto
 */
public interface ExcelOutputAction {

    /**
     * excelファイルダウンロード
     * @return
     */
    public void downloadExcel ();

    /**
     * excelファイル保存
     * @return
     */
    public void saveExcel();

    /**
     * excelファイル生成
     * @return
     */
    public void createExcel();
}