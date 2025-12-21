package jp.co.isid.mos.bird.bizreport.netorderniporef.logic.impl;

import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.netorderniporef.dao.MstSibuInfoDao;
import jp.co.isid.mos.bird.bizreport.netorderniporef.logic.TargetSibuInfoLogic;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;

public class TargetSibuInfoLogicImpl implements TargetSibuInfoLogic {

    public static final String LOGIC_ID = "BBR017L08";
    
    private MstSibuInfoDao mstSibuInfoDao;
    
    /**
     * mstsibuDaoを取得します。
     * @return
     */
    public MstSibuInfoDao getMstSibuInfoDao() {
        return this.mstSibuInfoDao;
    }
    /**
     * mstSibuDaoを設定します。
     * @param mstSibuDao
     */
    public void setMstSibuInfoDao( MstSibuInfoDao mstSibuInfoDao ) {
        this.mstSibuInfoDao = mstSibuInfoDao;
    }

    /**
     * 実行処理(結果画面(支部)の表示対象の支部一覧取得処理)
     */
    public List execute(Map paramMap) {

        String companyCd = (String)paramMap.get("companyCd");
        String userId = (String)paramMap.get("userId");
        boolean limitFlg = ( (Boolean)paramMap.get("limitFlg") ).booleanValue();
        String areaDaiFlg = (String)paramMap.get("areaDaiFlg");
        String sibuCd = (String)paramMap.get("sibuCd");
        String kbnCd = (String)paramMap.get("kbnCd");
        
        String taishoKikan = (String)paramMap.get("taishoKikan");
        String kikanFrom = (String)paramMap.get("kikanFrom");
        String kikan = new String();
        
        if ( taishoKikan.equals("DAY1") ) {
            kikan = kikanFrom;
        } else if ( taishoKikan.equals("APPMONTH") || taishoKikan.equals("DAYS")) {
            kikan = kikanFrom;
        } else if (taishoKikan.equals("MONTH")) {
            kikan = kikanFrom + "01";
        } else if ( taishoKikan.equals("KIBETU")) {
            kikan = kikanFrom + "31";
        }
        List sibuList = getMstSibuInfoDao().getTargetSibuInfo(companyCd,limitFlg,areaDaiFlg,userId,sibuCd, kbnCd, kikan);
        
        if ( sibuList == null || sibuList.size() == 0 ) {
            throw new FtlSystemException("");
        }
        return sibuList; 
    }

}
