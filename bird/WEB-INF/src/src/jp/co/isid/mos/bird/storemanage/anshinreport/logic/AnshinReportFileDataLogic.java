package jp.co.isid.mos.bird.storemanage.anshinreport.logic;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.storemanage.anshinreport.dto.AnshinReportDto;

public interface AnshinReportFileDataLogic {

    /**
     * @param args
     */
    public void execute(AnshinReportDto anshinReportDto, BirdUserInfo userInfo, BirdDateInfo dateInfo);

}
