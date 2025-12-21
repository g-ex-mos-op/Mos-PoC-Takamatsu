package jp.co.isid.mos.bird.communication.docform.logic.impl;

import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.communication.docform.dao.UIViewShozokuInfoDao;
import jp.co.isid.mos.bird.communication.docform.logic.GetDocFormShozokuLogic;

/**
 * 表示対象所属取得ロジック
 * @author xnkusama
 *
 */
public class GetDocFormShozokuLogicImpl implements GetDocFormShozokuLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BCM004L04";
    
    private UIViewShozokuInfoDao docformViewShozokuInfoDao;

    public List execute(Map paramMap) {
        String infoShu = (String)paramMap.get("infoShu");
        String regDate = (String)paramMap.get("regDate");
        String seq = (String)paramMap.get("seq");
        return getDocformViewShozokuInfoDao().getBunshoShozoku(infoShu,regDate,seq);
    }

    public UIViewShozokuInfoDao getDocformViewShozokuInfoDao() {
        return docformViewShozokuInfoDao;
    }

    public void setDocformViewShozokuInfoDao(UIViewShozokuInfoDao uiViewShozokuInfoDao) {
        this.docformViewShozokuInfoDao = uiViewShozokuInfoDao;
    }

}
