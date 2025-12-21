package jp.co.isid.mos.bird.storeinfo.miseinformationextract.action;

import java.io.IOException;

/**
* 店マスタ情報一括抽出アクション・インターフェース
*
* @author   boukoumei
*/
public interface MiseInfoExtractAction {

    /**
     * 初期処理(店マスタ情報一括抽出)
     * @return String
     */
    public String initialize();

    /**
     * 対象支部リスト取得
     * @return String
     */
    public String reSearchTaishoSibuList();

    /**
     * アクション【SV検索ボタン】処理
     * @return String
     */
    public String callSvForm();

    /**
     * ダウンロードCSV
     */
    public void downloadCsv() throws IOException;

    /**
     * ダウンロードExcel
     */
    public void downloadExcel() throws IOException;
}
