/*
 * 作成日: 2008/02/19
 */
package jp.co.isid.mos.bird.communication.docform.logic;

import java.util.Map;

import jp.co.isid.mos.bird.communication.docform.dto.FullTextSearchDocDto;
import jp.co.isid.mos.bird.communication.docform.dto.FullTextSearchFormDto;
import jp.co.isid.mos.bird.communication.docform.dto.RequestDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * 文書・フォームの情報取得ロジック インターフェイス
 * @author xnkusama
 * 更新日:2008/12/11 xkinu FullTextSearch対応
 */
public interface GetDocFormInfoLogic {
    /**
     * 照会対象の文書・フォームを取得する
     *
     * @param requestDto
     * @param birdDateInfo
     * @param birdUserInfo
     * @param ultDocDto  文書用FullTextSearchDto (2008/12/11追加)
     * @param ultFormDto フォーム用FullTextSearchDto (2008/12/11追加)
     * @return
     */
    public Map execute(RequestDto requestDto
    		, BirdDateInfo birdDateInfo, BirdUserInfo birdUserInfo
            , FullTextSearchDocDto ultDocDto, FullTextSearchFormDto ultFormDto);
}