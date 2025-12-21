package jp.co.isid.mos.bird.entry.mlentry.logic.impl;

import jp.co.isid.mos.bird.entry.mlentry.dao.MstMiseDao;
import jp.co.isid.mos.bird.entry.mlentry.dao.UIStaffDao;
import jp.co.isid.mos.bird.entry.mlentry.entity.MstMise;
import jp.co.isid.mos.bird.entry.mlentry.entity.UIStaff;
import jp.co.isid.mos.bird.entry.mlentry.logic.GetStaffInfoLogic;

/**
 * スタッフ情報取得ロジック
 * @author Aspac
 */
public class GetStaffInfoLogicImpl implements GetStaffInfoLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BEN008L11";

    
    /**
     * スタッフ情報
     */
    private UIStaffDao uIStaffDao;
    
    /**
     * 店舗情報
     */
    private MstMiseDao mstMiseDao;
    
    

    /**
	 * エントリー状況取得
	 * @return 検索結果
	 */
	public UIStaff execute(String staffId) {
        
        if(staffId==null) return null;
        
        UIStaff uIStaff = getUIStaffDao().getStaffInfo(staffId);
        
        if(uIStaff!=null) {
            String miseCd = uIStaff.getMiseCd1();
            String compCd = uIStaff.getCompanyCd();
            MstMise mstMise = getMstMiseDao().getMiseInfo(compCd,miseCd);
            uIStaff.setMiseNameKj(mstMise.getMiseNameKj());
        }
        return uIStaff;
        
	}

    public UIStaffDao getUIStaffDao() {
        return uIStaffDao;
    }
    public void setUIStaffDao(UIStaffDao staffDao) {
        uIStaffDao = staffDao;
    }

    public MstMiseDao getMstMiseDao() {
        return mstMiseDao;
    }
    public void setMstMiseDao(MstMiseDao mstMiseDao) {
        this.mstMiseDao = mstMiseDao;
    }
 

}
