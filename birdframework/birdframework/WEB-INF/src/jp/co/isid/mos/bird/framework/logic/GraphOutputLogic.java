package jp.co.isid.mos.bird.framework.logic;

import jp.co.isid.mos.bird.framework.dto.GraphOutputDto;

import org.jfree.chart.JFreeChart;

/**
 * グラフ出力用 ロジックInterface
 * @author xnkusama
 */
public interface GraphOutputLogic {
    public static final int FILE_TYPE_JPEG = 1;
    public static final int FILE_TYPE_PNG  = 2;

    /**
     * ファイル名取得
     */
    public String getFileName(final GraphOutputDto graphOutputDto);
    
    /**
     * 画像URL取得
     */
    public String getImageUrl(final GraphOutputDto graphOutputDto);
    
    /**
     * 出力データ取得処理
     */
    public JFreeChart createOutputData(final GraphOutputDto graphOutputDto);
    
    /**
     *  ファイルタイプ取得処理
     *    １：JPEG
     *    ２：PNG 
     */
    public int getFileType();
    
    /**
     * ファイルパス取得処理
     */
    public String getFilePath();
    
    /**
     * ユーザーID取得
     */
    public String getUserId(final GraphOutputDto graphOutputDto);
}
