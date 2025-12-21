package jp.co.isid.mos.bird.bizreport.groupeigyoniporef.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.dao.MstSibuInfoDao;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.entity.MstSibuInfo;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.logic.ViewSibuInfoLogic;
import jp.co.isid.mos.bird.framework.exception.NoResultException;

public class ViewSibuInfoLogicImpl implements ViewSibuInfoLogic {

    public static final String LOGIC_ID = "BBR001L01";
    
    
    // Mapのパラメータ
    public String COMPANY_CD = "companyCd";
    public String USER_ID = "userId";
    public String TENSHU = "tenpoShu";
    public String DATASHU = "dataShu";
    public String TAISHO_KIKAN = "taishoKikan";
    public String KIKAN_FROM  = "kikanFrom";
    public String KIKAN_TO = "kikanTo";
    public String LIMIT_FLG = "limitFlg";
    public String AREA_DAI_FLG = "areaDaiFlg";
    public String TAISHO_TENPO = "taishoTenpo";

    public String NO_DISP = "NOTDISP";
    // MstSibuInfoDao
    private MstSibuInfoDao mstSibuInfoDao;
    
    /**
     * mstSibuInfoDao取得
     * @return
     */
    public MstSibuInfoDao getMstSibuInfoDao() {
        return this.mstSibuInfoDao;
    }
    
    /**
     * mstSibuInfoDao設定
     * @param mstSibuInfoDao
     */
    public void setMstSibuInfoDao( MstSibuInfoDao mstSibuInfoDao ) {
        this.mstSibuInfoDao = mstSibuInfoDao;
    }
    
    /**
     * 表示支部の取得
     * @param paramMap
     * 管理会社企業コード:COMPANY_CD
     * ユーザーID：USER_ID
     * 店舗種別：TENSHU
     * 前年データ種別：DATASHU
     * 対象期間：TAISHO_KIKAN
     * 期間FROM：KIKAN_FROM
     * 期間TO：KIKAN_TO
     * 支部制限フラグ：LIMIT_FLG
     * エリア大：AREA_DAI_FLG
     */
    public List execute(Map paramMap) {
        // TODO 自動生成されたメソッド・スタブ
        
        String companyCd = (String)paramMap.get( COMPANY_CD );
        String userId = (String)paramMap.get( USER_ID );
        String tenpoShu = (String)paramMap.get( TENSHU );
        String dataShu = (String)paramMap.get( DATASHU );
        String taishoKikan = (String)paramMap.get( TAISHO_KIKAN );
        String kikanFrom = (String)paramMap.get( KIKAN_FROM );
        String kikanTo = (String)paramMap.get( KIKAN_TO );
        boolean limitFlg = ( (Boolean)paramMap.get( LIMIT_FLG ) ).booleanValue();
        String areaDaiFlg = (String)paramMap.get( AREA_DAI_FLG );
        String taishoTenpo =(String)paramMap.get( TAISHO_TENPO );
        String dataShuKbn = new String();
        if ( tenpoShu.equals( TenpoShubetu.CODE_ZENNEN ) ) {
            dataShuKbn = "1";
        } else if ( tenpoShu.equals( TenpoShubetu.CODE_YOSAN ) ) {
            dataShuKbn = "2";
        } else if ( tenpoShu.equals( TenpoShubetu.CODE_SIN ) ) {
            dataShuKbn = "3";
        } else {
        }
        List resultList = new ArrayList();
        
        if ( taishoKikan.equals("APPMONTH")) {
            kikanFrom = kikanFrom.substring(0,6);            
        }
        
        if ( taishoKikan.equals("MONTH") || taishoKikan.equals("APPMONTH") || taishoKikan.equals("KIBETU") ) {
            resultList = getMstSibuInfoDao().getMstSibuGepoInfo( companyCd
                                                                ,userId
                                                                ,tenpoShu
                                                                ,dataShu
                                                                ,taishoKikan
                                                                ,kikanFrom
                                                                ,kikanTo
                                                                ,limitFlg
                                                                ,areaDaiFlg
                                                                ,taishoTenpo
                                                                ,dataShuKbn);
        } else {
            resultList = getMstSibuInfoDao().getMstSibuNipoInfo( companyCd
                                                                 ,userId
                                                                 ,tenpoShu
                                                                 ,dataShu
                                                                 ,taishoKikan
                                                                 ,kikanFrom
                                                                 ,kikanTo
                                                                 ,limitFlg
                                                                 ,areaDaiFlg
                                                                 ,taishoTenpo
                                                                 ,dataShuKbn);
        }
        // データが０件の場合は結果無し
        if ( resultList == null || resultList.size() == 0 ) {
            throw new NoResultException();
        }
        
        return resultList;
    }
    
    
    /**
     * 表示支部のみ抽出
     * @param resultList
     * @return
     */
    public List validateList(List resultList) {
        List validateList = new ArrayList();
        
        for ( int i = 0; i < resultList.size(); i++ ) {
            MstSibuInfo msi = (MstSibuInfo)resultList.get(i);
            if ( !msi.getDispKbn().equals( NO_DISP ) ){
                validateList.add( msi );
            }
        }
        return validateList;
    }

}
