package jp.co.isid.mos.bird.storemanage.msssurveydataref.logic;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.storemanage.msssurveydataref.dto.MssSuerveyBatchDto;
import jp.co.isid.mos.bird.storemanage.msssurveydataref.dto.MssSuerveyDataRefDto;

public interface MssSurveyCondLogic {

    public String batchStatus(MssSuerveyBatchDto dto);

    public String batchClearErr();

    public String execute(MssSuerveyDataRefDto dto) throws ApplicationException;
}
