package jp.co.isid.mos.bird.bizreport.urimaintenanceview.logic;

import jp.co.isid.mos.bird.bizreport.urimaintenanceview.dto.MeisaiRequestDto;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.dto.UriMainteViewMeisaiResultDto;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.dto.UriMainteViewSesDto;

public interface SearchMeisaiLogic {

    /**
     * 明細検索
     * @param proceedsManageGepoDto 売上修正セッションDTOクラス
     * @param meisaiRequestJokenDto 売上修正明細照会・条件部DTOクラス
     * @param meisaiRequestResultDto 売上修正明細照会・結果部DTOクラス
     */
    public void execute(
            UriMainteViewSesDto uriMainteViewSesDto
          , MeisaiRequestDto meisaiRequestJokenDto
          , UriMainteViewMeisaiResultDto meisaiRequestResultDto);
}
