package jp.co.isid.mos.bird.bizreport.campsuiiref.logic.impl;

import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizreport.campsuiiref.logic.GetCampMenuInfoLogic;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestSuiiDto;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.SessionSuiiDto;
import jp.co.isid.mos.bird.bizreport.common.camp.logic.GetCampMenuLogic;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;

/**
 * 会社対象条件情報設定
 * @author xnkusama
 *
 */
public class GetCampMenuInfoLogicImpl implements GetCampMenuInfoLogic {
    /** ロジックID */    
    public static final String LOGIC_ID = "BBR012L01";

    /* LOGIC */
    private GetCampMenuLogic commonCampaignGetCampMenuLogic;
    
    
    /**
     * 条件項目の取得設定
     * @param requestDto
     * @param sessionDto
     */
    public void execute(RequestSuiiDto requestDto) {
        SessionSuiiDto sessionDto = (SessionSuiiDto) requestDto.getSelfSessionDto();
        // １．事前条件処理
        validate(requestDto);
        // ２．DTO【自画面Request】.List[[メニュー集計区分]]の件数分の下記の処理を行う。
        for (Iterator ite = sessionDto.getListMenuTotaled().iterator(); ite.hasNext();) {
            SelectItem selectItem = (SelectItem) ite.next();
            String menuShukeiKbn = (String) selectItem.getValue();
            List listMenu = getCommonCampaignGetCampMenuLogic().execute(requestDto.getCampId(), menuShukeiKbn);
            if (listMenu != null) {
                sessionDto.setListMenu(requestDto.getCampId(), menuShukeiKbn, listMenu);
            }
        }
        
    }

    /**
     * 事前条件処理
     * @param requestDto
     */
    private void validate(RequestSuiiDto requestDto) {
        if (requestDto == null) {
            throw new MissingDataException("Request用DTOデータ");
        }
    }

    public GetCampMenuLogic getCommonCampaignGetCampMenuLogic() {
        return commonCampaignGetCampMenuLogic;
    }

    public void setCommonCampaignGetCampMenuLogic(
            GetCampMenuLogic commonCampaignGetCampMenuLogic) {
        this.commonCampaignGetCampMenuLogic = commonCampaignGetCampMenuLogic;
    }

}
