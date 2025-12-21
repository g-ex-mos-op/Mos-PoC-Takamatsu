/*
 * 作成日: 2006/01/24
 *
 */
package jp.co.isid.mos.bird.commonform.publictargetsearch.logic;

import jp.co.isid.mos.bird.commonform.publictargetsearch.dto.PublicTargetSearchConditionDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * CSVファイルの読み込みロジック
 * @author xytamura
 *
 */
public interface ReadCsvFileLogic {
    /**
     * CSVファイルの読み込み
     * @param publicTargetSearchDto
     * @return
     * @throws ApplicationException
     */
    public abstract PublicTargetSearchConditionDto execute(
            PublicTargetSearchConditionDto publicTargetSearchCondionDto)
            throws ApplicationException;
}