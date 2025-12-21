package jp.co.isid.mos.bird.bizreport.moscardniporef.dao;

import java.util.List;
                                                                                                                                                                                                                                                                                                                                              
import jp.co.isid.mos.bird.bizreport.moscardniporef.entity.TrnUriageInfo;

public interface TrnUriageInfoDao {
    
    public static final Class BEAN = TrnUriageInfo.class;

    public static final String getUriageDougetuNipo_ARGS = "companyCd" +
                                                        ",userId" +
                                                        ",tenShu" +
                                                        ",dataShu" +
                                                        ",taishoKikan" +
                                                        ",kikanFrom" +
                                                        ",kikanTo" +
                                                        ",limitFlg" + 
                                                        ",areaDaiFlg" +
                                                        ",taishoTenpo" +
                                                        ",dataShuKbn" +
                                                        ",taishoJoken";

    public static final String getUriageDougetuGepo_ARGS = "companyCd" +
                                                                ",userId" +
                                                                ",tenShu" +
                                                                ",dataShu" +
                                                                ",taishoKikan" +
                                                                ",kikanFrom" +
                                                                ",kikanTo" +
                                                                ",limitFlg" + 
                                                                ",areaDaiFlg" +
                                                                ",taishoTenpo" +
                                                                ",dataShuKbn" +
                                                                ",taishoJoken";

    public static final String getUriageDougetuTogetuGepo_ARGS = "companyCd" +
                                                                ",userId" +
                                                                ",tenShu" +
                                                                ",dataShu" +
                                                                ",taishoKikan" +
                                                                ",kikanFrom" +
                                                                ",kikanTo" +
                                                                ",limitFlg" + 
                                                                ",areaDaiFlg" +
                                                                ",taishoTenpo" +
                                                                ",dataShuKbn" +
                                                                ",taishoJoken" +
                                                                ",yosanDtFrom" +
                                                                ",yosanDtTo";
    
    public static final String getUriageHonbuNipo_ARGS  = "companyCd" +
                                                               ",userId" +
                                                                ",tenShu" +
                                                                ",dataShu" +
                                                                ",taishoKikan" +
                                                                ",kikanFrom" +
                                                                ",kikanTo" +
                                                                ",limitFlg" + 
                                                                ",areaDaiFlg" +
                                                                ",taishoTenpo" +
                                                                ",dataShuKbn" +
                                                                ",taishoJoken";
    
    public static final String getUriageHonbuGepo_ARGS = "companyCd" +
                                                                ",userId" +
                                                                ",tenShu" +
                                                                ",dataShu" +
                                                                ",taishoKikan" +
                                                                ",kikanFrom" +
                                                                ",kikanTo" +
                                                                ",limitFlg" + 
                                                                ",areaDaiFlg" +
                                                                ",taishoTenpo" +
                                                                ",dataShuKbn" +
                                                                ",taishoJoken";

    /**
     * 売上取得(支部用)(同月)(日報)(期間指定)
     * @param companyCd
     * @param taishoTenpo
     * @param taishoKikan
     * @param tenShu
     * @param kikanFrom
     * @param kikanTo
     * @param areaDaiFlg
     * @param limitFlg
     * @return
     */
    public List getUriageDougetuNipo(String companyCd
                                , String userId
                                , String tenShu
                                , String dataShu
                                , String taishoKikan
                                , String kikanFrom
                                , String kikanTo
                                , boolean limitFlg 
                                , String areaDaiFlg
                                , String taishoTenpo
                                , String dataShuKbn
                                , String taishoJoken) ;

    /**
     * 売上取得(支部用)(同月)(月報)(月指定)
     * @param companyCd
     * @param taishoTenpo
     * @param taishoKikan
     * @param tenShu
     * @param kikanFrom
     * @param kikanTo
     * @param areaDaiFlg
     * @param limitFlg
     * @return
     */
    public List getUriageDougetuGepo(String companyCd
                                    , String userId
                                    , String tenShu
                                    , String dataShu
                                    , String taishoKikan
                                    , String kikanFrom
                                    , String kikanTo
                                    , boolean limitFlg 
                                    , String areaDaiFlg
                                    , String taishoTenpo
                                    , String dataShuKbn
                                    , String taishoJoken) ;

    /**
     * 売上取得(支部用)(同月)(月報)(月指定)
     * @param companyCd
     * @param taishoTenpo
     * @param taishoKikan
     * @param tenShu
     * @param kikanFrom
     * @param kikanTo
     * @param areaDaiFlg
     * @param limitFlg
     * @return
     */
    public List getUriageDougetuTogetuGepo(String companyCd
                                    , String userId
                                    , String tenShu
                                    , String dataShu
                                    , String taishoKikan
                                    , String kikanFrom
                                    , String kikanTo
                                    , boolean limitFlg 
                                    , String areaDaiFlg
                                    , String taishoTenpo
                                    , String dataShuKbn
                                    , String taishoJoken
                                    , String yosanDtFrom
                                    , String yosanDtTo) ;
    
    /**
     * 本部売上取得(支部用)(同月)(日報)(期間指定)
     * @param companyCd
     * @param taishoTenpo
     * @param taishoKikan
     * @param tenShu
     * @param kikanFrom
     * @param kikanTo
     * @param areaDaiFlg
     * @param limitFlg
     * @return
     */
    public List getUriageHonbuNipo(String companyCd
                                       , String userId
                                       , String tenShu
                                       , String dataShu
                                       , String taishoKikan
                                       , String kikanFrom
                                       , String kikanTo
                                       , boolean limitFlg 
                                       , String areaDaiFlg
                                       , String taishoTenpo
                                       , String dataShuKbn
                                       , String taishoJoken) ;
    
    /**
     * 本部売上取得(支部用)(同月)(月報)(月指定)
     * @param companyCd
     * @param taishoTenpo
     * @param taishoKikan
     * @param tenShu
     * @param kikanFrom
     * @param kikanTo
     * @param areaDaiFlg
     * @param limitFlg
     * @return
     */
    public List getUriageHonbuGepo(String companyCd
                                    , String userId
                                    , String tenShu
                                    , String dataShu
                                    , String taishoKikan
                                    , String kikanFrom
                                    , String kikanTo
                                    , boolean limitFlg 
                                    , String areaDaiFlg
                                    , String taishoTenpo
                                    , String dataShuKbn
                                    , String taishoJoken) ;

}