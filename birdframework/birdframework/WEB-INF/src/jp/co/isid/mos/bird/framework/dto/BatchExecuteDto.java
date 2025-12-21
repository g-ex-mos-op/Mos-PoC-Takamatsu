/*
 * 作成日: 2005/11/16
 */
package jp.co.isid.mos.bird.framework.dto;

/**
 * バッチ起動用ＤＴＯ。
 * @author xytamura
 */
public interface BatchExecuteDto {

    /**
     * 実行するジョブを取得します
     * @return 配列の０番目は実行するジョブ、以降はパラメータ
     */
    public String[] getExecuteJobName();

    /**
     * 実行するジョブを設定します。
     * @param executeJobName 配列の０番目は実行するジョブ、以降はパラメータ
     */
    public void setExecuteJobName(final String[] executeJobName);

    /**
     * 遷移先を取得します。
     * @return 遷移先
     */
    public String getPageKey();

    /**
     * 遷移先を設定します。
     * @param pageKey 遷移先
     */
    public void setPageKey(final String pageKey);

}
