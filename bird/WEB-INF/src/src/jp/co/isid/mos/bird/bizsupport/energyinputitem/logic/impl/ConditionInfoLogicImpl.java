package jp.co.isid.mos.bird.bizsupport.energyinputitem.logic.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizsupport.energyinputitem.common.EnergyInputItemConst;
import jp.co.isid.mos.bird.bizsupport.energyinputitem.dto.EnergyInputItemDto;
import jp.co.isid.mos.bird.bizsupport.energyinputitem.logic.ConditionInfoLogic;
import jp.co.isid.mos.bird.common.logic.GetSibuHoyuTenpoLogic;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

public class ConditionInfoLogicImpl implements ConditionInfoLogic {

    /** ロジックID */
    public static final String LOGIC_ID = "BBS033L01";
    
    private GetSibuHoyuTenpoLogic sibuHoyuTenpoLogic;
    
    public void execute(EnergyInputItemDto dto) {
        //事前条件
        validate(dto);
        
        //支部一覧取得
        List listSibu = getSibuHoyuTenpoLogic().execute(dto.getCompanyCd(), null, false);
        dto.setListSibu(listSibu);
        
        //メーター区分プルダウン作成
        List listMeterKbn = new ArrayList();
        listMeterKbn.add(new SelectItem(EnergyInputItemConst.METER_KBN_TENPO, EnergyInputItemConst.METER_KBN_TENPO_NAME));
        listMeterKbn.add(new SelectItem(EnergyInputItemConst.METER_KBN_OFFICE, EnergyInputItemConst.METER_KBN_OFFICE_NAME));
        dto.setListMeterKbn(listMeterKbn);

    }
    /**
     * 事前条件
     * @param companyCd
     * @param sysDate
     */
    private void validate(EnergyInputItemDto dto) {
        if (CommonUtil.isNull(dto.getCompanyCd())) {
            throw new NotNullException("会社コード");
        }
        if (CommonUtil.isNull(dto.getUserTypeCd())) {
            throw new NotNullException("ユーザー情報");
        }
    }

    public GetSibuHoyuTenpoLogic getSibuHoyuTenpoLogic() {
        return sibuHoyuTenpoLogic;
    }

    public void setSibuHoyuTenpoLogic(GetSibuHoyuTenpoLogic sibuHoyuTenpoLogic) {
        this.sibuHoyuTenpoLogic = sibuHoyuTenpoLogic;
    }

}
