/*
 * 作成日: 2006/2/10
 */
package jp.co.isid.mos.bird.inforegist.informregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.inforegist.informregist.dto.InformRegistDto;
import jp.co.isid.mos.bird.inforegist.informregist.entity.UINews;

/**
 * お知らせ情報の更新ロジックインターフェース
 * @author itamoto
 */
public interface RenewNewsLogic {

    /**
     * 登録内容のチェック処理
     * @param uiNews
     */
    public List execute(UINews uiNews, InformRegistDto informRegistDto,
			PublicTargetDto publicTargetDto);
}
