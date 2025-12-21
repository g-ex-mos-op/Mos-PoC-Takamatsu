package jp.co.isid.mos.bird.bizreport.common.urimaintenance.logic;

import jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity.UriMainteHeader;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 売上修正　共通ヘッダー取得ロジック インターフェース
 * @author mwatanabe
 */
public interface UriMainteHeaderLogic {

    /**
     * 処理実行
     * 
     * @param  companyCd  会社コード
     * @return UriMainteHeaderDto
     * @throws ApplicationException
     */
     public UriMainteHeader execute(String companyCd);

}
