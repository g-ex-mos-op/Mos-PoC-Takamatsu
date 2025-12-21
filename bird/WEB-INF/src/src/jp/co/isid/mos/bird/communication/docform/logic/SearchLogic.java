/**
 *
 */
package jp.co.isid.mos.bird.communication.docform.logic;

import java.util.Map;

import jp.co.isid.mos.bird.communication.docform.dto.RequestDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.FullTextSearchDto;

/**
 * 検索ロジックインターフェース
 *
 * 作成日:2008/12/12
 * @author xkinu
 *
 */
public interface SearchLogic {
    /**
     * 照会対象の検索処理とその結果を取得する
     *
     * @param birdDateInfo
     * @param birdUserInfo
     * @param infoShu
     * @param mapKeyExistFlg
     * @param requestDto
     * @param fullTextSearchDto
     * @return
     */
    public Map execute( BirdDateInfo birdDateInfo, BirdUserInfo birdUserInfo
            , String infoShu, String mapKeyExistFlg
			, RequestDto requestDto
    		, FullTextSearchDto fullTextSearchDto);
}
