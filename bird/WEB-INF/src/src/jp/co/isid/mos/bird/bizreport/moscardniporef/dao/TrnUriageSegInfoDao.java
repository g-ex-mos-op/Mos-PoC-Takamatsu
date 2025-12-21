package jp.co.isid.mos.bird.bizreport.moscardniporef.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.moscardniporef.entity.TrnSibuUriageNipoSegRelate;

public interface TrnUriageSegInfoDao {
    
    public static final Class BEAN  = TrnSibuUriageNipoSegRelate.class;
    
    public static final String getDougetuSegUriage_ARGS = "companyCd" +
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
                                                              ",segmentList" +
                                                              ",taishoJoken";
        
    public static final String getDougetuSegUriageGepo_ARGS = "companyCd" +
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
                                                                ",segmentList" +
                                                                ",taishoJoken";
    
    public static final String getSegUriageTogetuGepo_ARGS = "companyCd" +
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
                                                               ",segmentList" +
                                                               ",taishoJoken" +
                                                               ",yosanDtFrom" +
                                                               ",yosanDtTo";
    
    /**
     * セグメントごとの売上を取得する(同月)(日別)
     * @param companyCd
     * @param userId
     * @param tenShu
     * @param dataShu
     * @param taishoKikan
     * @param kikanFrom
     * @param kikanTo
     * @param limitFlg
     * @param areaDaiFlg
     * @param taishoTenpo
     * @param dataShuKbn
     * @param segmentList
     * @param taishoJoken
     * @return
     */
    public List getDougetuSegUriage(String companyCd
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
                                    , List segmentList
                                    , String taishoJoken);  

    /**
     * セグメントごとの売上を取得する(同月)(月報)
     * @param companyCd
     * @param userId
     * @param tenShu
     * @param dataShu
     * @param taishoKikan
     * @param kikanFrom
     * @param kikanTo
     * @param limitFlg
     * @param areaDaiFlg
     * @param taishoTenpo
     * @param dataShuKbn
     * @param segmentList
     * @param taishoJoken
     * @return
     */
    public List getDougetuSegUriageGepo(String companyCd
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
                                    , List segmentList
                                    , String taishoJoken);
    
    /**
     * セグメントごとの売上を取得する(同月)(月報)
     * @param companyCd
     * @param userId
     * @param tenShu
     * @param dataShu
     * @param taishoKikan
     * @param kikanFrom
     * @param kikanTo
     * @param limitFlg
     * @param areaDaiFlg
     * @param taishoTenpo
     * @param segmentList
     * @param taishoJoken
     * @param yosanDtFrom
     * @param yosanDtTo
     * @return
     */
    public List getSegUriageTogetuGepo(String companyCd
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
                                    , List segmentList
                                    , String taishoJoken
                                    , String yosanDtFrom
                                    , String yosanDtTo);


}
