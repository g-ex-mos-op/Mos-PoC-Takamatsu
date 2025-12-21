package jp.co.isid.mos.bird.storemanage.mlresultregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dao.MstInterviewDao;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.GetMstInterviewLogic;

/**
 * 面接マスタ取得ロジック 
 * @author xnkusama
 */
public class GetMstInterviewLogicImpl implements GetMstInterviewLogic {

    /* ロジックID */    
    public static final String LOGIC_ID = "BSM008L06";
    
    MstInterviewDao mlrrMstInterviewDao;
    
    /**
     * 面接マスタを取得する
     * @return List
     * @exception ApplicationException
     */
    public List execute() throws ApplicationException {
        List listMstInterview;
        //面接マスタの取得
        listMstInterview = getMlrrMstInterviewDao().getMstInterview();

        return listMstInterview;
    }

    public MstInterviewDao getMlrrMstInterviewDao() {
        return mlrrMstInterviewDao;
    }

    public void setMlrrMstInterviewDao(MstInterviewDao mlrrMstInterviewDao) {
        this.mlrrMstInterviewDao = mlrrMstInterviewDao;
    }

}