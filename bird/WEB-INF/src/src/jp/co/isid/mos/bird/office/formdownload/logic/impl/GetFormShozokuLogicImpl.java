package jp.co.isid.mos.bird.office.formdownload.logic.impl;

import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.office.formdownload.dao.UIViewShozokuInfoDao;
import jp.co.isid.mos.bird.office.formdownload.logic.GetFormShozokuLogic;

public class GetFormShozokuLogicImpl implements GetFormShozokuLogic {

    
    public static final String LOGIC_ID = "BOF001L03";
    
    private UIViewShozokuInfoDao uiViewShozokuInfoDao;
    
    public List execute(Map paramMap) {
        // TODO 自動生成されたメソッド・スタブ
        String infoShu = (String)paramMap.get("infoShu");
        String regDate = (String)paramMap.get("regDate");
        String seq = (String)paramMap.get("seq");       
        return getUiViewShozokuInfoDao().getFormShozoku(infoShu,regDate,seq);
    }

    public UIViewShozokuInfoDao getUiViewShozokuInfoDao() {
        return uiViewShozokuInfoDao;
    }

    public void setUiViewShozokuInfoDao(UIViewShozokuInfoDao uiViewShozokuInfoDao) {
        this.uiViewShozokuInfoDao = uiViewShozokuInfoDao;
    }

}
