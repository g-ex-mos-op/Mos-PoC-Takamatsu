/*
 * 作成日: 2006/2/9
 */
package jp.co.isid.mos.bird.inforegist.informregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.inforegist.informregist.entity.UINews;

/**
 * 登録内容のチェックロジックインターフェース
 * @author itamoto
 */
public interface CheckNewsLogic {

    /**
     * 登録内容のチェック処理
     * @param uiNews
     */
    public List execute(UINews uiNews, PublicTargetDto publicTargetDto);
}
