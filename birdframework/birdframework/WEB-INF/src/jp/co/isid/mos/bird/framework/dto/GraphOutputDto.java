package jp.co.isid.mos.bird.framework.dto;


/**
 * グラフ用DTO
 * @author xnkusama
 */
public interface GraphOutputDto {
    /** 画像ファイルURL設定処理 */
    public void setGraphUrl(String graphUrl);
    /** 画像ファイルURL取得処理 */
    public String getGraphUrl();
}
