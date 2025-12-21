package jp.co.isid.mos.bird.analysis.posdata.logic;

import java.io.IOException;
import java.util.List;

import jp.co.isid.mos.bird.analysis.posdata.dto.PosDataFormDto;
import jp.co.isid.mos.bird.framework.logic.DownloadLogic;

/**
 *  LOGIC【Zipファイル取得】インターフェース
 * 作成日:2013/02/26
 * @author xkinu
 *
 */
public interface ZipOutputLogic extends DownloadLogic {
    
    /**
     * 実行処理
     * @param sessionDto DTO【POSデータ集信Sesshon情報】
     * @return
     */
    public List execute (PosDataFormDto sessionDto);
}
