/*
 * çÏê¨ì˙: 2005/12/22
 *
 */
package jp.co.isid.mos.bird.commonform.misesearch.logic;

import java.util.List;

import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchConditionDto;

/**
 * @author xyuchida
 *
 */
public interface SearchMiseLogic {

    public List execute(MiseSearchConditionDto miseSearchDto);
}
