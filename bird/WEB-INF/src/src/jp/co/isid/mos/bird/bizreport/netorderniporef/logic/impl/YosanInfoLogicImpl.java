package jp.co.isid.mos.bird.bizreport.netorderniporef.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.netorderniporef.dao.TrnYosanInfoDao;
import jp.co.isid.mos.bird.bizreport.netorderniporef.logic.YosanInfoLogic;

// ó\éZéÊìæ
public class YosanInfoLogicImpl implements YosanInfoLogic {

    public static final String LOGIC_ID = "BBR017L03";
    /**
     * ìXï‹éÌï ÅFëSìX
     */
    public static final String CODE_ALL = "ALL";
    /**
     * ìXï‹éÌï ÅFëOîNëŒè€ìX
     */
    public static final String CODE_ZENNEN = "1";
    /**
     * ìXï‹éÌï ÅFó\éZëŒè€ìX
     */
    public static final String CODE_YOSAN = "2";
    /**
     * ìXï‹éÌï ÅFêVìX
     */
    public static final String CODE_SIN = "3";
    
    private TrnYosanInfoDao trnYosanInfoDao;
    
    /**
     * trnYosanInfoDaoÇÃê›íË
     * @return
     */
    public TrnYosanInfoDao getTrnYosanInfoDao() {
        return this.trnYosanInfoDao;
    }
    /**
     * trnYosanInfoDaoÇÃê›íË
     * @param trnYosanInfoDao
     */
    public void setTrnYosanInfoDao( TrnYosanInfoDao trnYosanInfoDao ) {
        this.trnYosanInfoDao = trnYosanInfoDao;
    }
    public List execute(Map map) {
        String companyCd = (String)map.get("companyCd");
        String userId = (String)map.get("userId");
        String tenShu = (String)map.get("tenpoShu");
        String dataShu = (String)map.get("dataShu");
        String taishoKikan = (String)map.get("taishoKikan");
        String kikanFrom = (String)map.get("kikanFrom");
        String kikanTo = (String)map.get("kikanTo");
        boolean limitFlg = ( (Boolean)map.get("limitFlg") ).booleanValue();
        String areaDaiFlg = (String)map.get("areaDaiFlg");
        String taishoTenpo = (String)map.get("taishoTenpo");
        
        String dataShuKbn = new String();
        if ( tenShu.equals( CODE_ZENNEN ) ) {
            dataShuKbn = "1";
        } else if ( tenShu.equals( CODE_YOSAN ) ) {
            dataShuKbn = "2";
        } else if ( tenShu.equals( CODE_SIN ) ) {
            dataShuKbn = "3";
        } else {
        }
        
        List yosanList = new ArrayList();
        
           if ( taishoKikan.equals("MONTH") ||  taishoKikan.equals("KIBETU") ) {
            yosanList = getTrnYosanInfoDao().getYosanGepo(companyCd
                                                            ,userId
                                                            ,tenShu
                                                            ,dataShu
                                                            ,taishoKikan
                                                            ,kikanFrom
                                                            ,kikanTo
                                                            ,limitFlg
                                                            ,areaDaiFlg
                                                            ,taishoTenpo
                                                            ,dataShuKbn);
        } else {            
            yosanList = getTrnYosanInfoDao().getYosanNipo(companyCd
                                                          ,userId
                                                          ,tenShu
                                                          ,dataShu
                                                          ,taishoKikan
                                                          ,kikanFrom
                                                          ,kikanTo
                                                          ,limitFlg
                                                          ,areaDaiFlg
                                                          ,taishoTenpo
                                                          ,dataShuKbn);
        }
                
        return yosanList;
    }

}
