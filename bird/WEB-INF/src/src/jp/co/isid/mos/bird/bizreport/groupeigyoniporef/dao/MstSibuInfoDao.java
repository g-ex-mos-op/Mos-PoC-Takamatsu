package jp.co.isid.mos.bird.bizreport.groupeigyoniporef.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.entity.MstSibuInfo;

public interface MstSibuInfoDao {

    public static final Class BEAN = MstSibuInfo.class;
    
    public static final String getMstSibuNipoInfo_ARGS = "companyCd" +
                                                            ",userId" +
                                                            ",tenShu" +
                                                            ",dataShu" +
                                                            ",taishoKikan" +
                                                            ",kikanFrom" +
                                                            ",kikanTo" +
                                                            ",limitFlg" + 
                                                            ",areaDaiFlg" +
                                                            ",taishoTenpo" +
                                                            ",dataShuKbn";

    public static final String getMstSibuGepoInfo_ARGS = "companyCd" +
                                                            ",userId" +
                                                            ",tenShu" +
                                                            ",dataShu" +
                                                            ",taishoKikan" +
                                                            ",kikanFrom" +
                                                            ",kikanTo" +
                                                            ",limitFlg" + 
                                                            ",areaDaiFlg" +
                                                            ",taishoTenpo" +
                                                            ",dataShuKbn";
    
    public static final String getAllSibuInfo_ARGS = "companyCd, limitFlg, areaDaiFlg, userId";
    
    public static final String getTargetSibuInfo_ARGS = "companyCd, limitFlg, areaDaiFlg, userId ,sibuCd, kbnCd, kikan";

    /**
     * ëŒè€éxïîÇÃéÊìæÅiì˙ïÒ)
     * @param companyCd
     * @param userId
     * @param tenShu
     * @param dataShu
     * @param taishoKikan
     * @param kikanFrom
     * @param kikanTo
     * @param areaDaiFlg
     * @return
     */
    public List getMstSibuNipoInfo( String companyCd
                                   , String userId
                                   , String tenShu
                                   , String dataShu
                                   , String taishoKikan
                                   , String kikanFrom
                                   , String kikanTo
                                   , boolean limitFlg
                                   , String areaDaiFlg 
                                   , String taishoTenpo
                                   , String dataShuKbn);

    /**
     * ëŒè€éxïîÇÃéÊìæ(åéïÒ)
     * @param companyCd
     * @param userId
     * @param tenShu
     * @param dataShu
     * @param taishoKikan
     * @param kikanFrom
     * @param kikanTo
     * @param areaDaiFlg
     * @return
     */
    public List getMstSibuGepoInfo( String companyCd
                                   , String userId
                                   , String tenShu
                                   , String dataShu
                                   , String taishoKikan
                                   , String kikanFrom
                                   , String kikanTo
                                   , boolean limitFlg
                                   , String areaDaiFlg 
                                   , String taishoTenpo
                                   , String dataShuKbn);

    /**
     * éxïîéÊìæ
     * @param companyCd
     * @param limitFlg
     * @param areaDaiFlg
     * @param userId
     * @return
     */
    public List getAllSibuInfo(String companyCd, boolean limitFlg, String areaDaiFlg, String userId);
    
    /**
     * éxïîéÊìæ
     * @param companyCd
     * @param limitFlg
     * @param areaDaiFlg
     * @param userId
     * @return
     */
    public List getTargetSibuInfo(String companyCd, boolean limitFlg, String areaDaiFlg, String userId, String sibuCd, String kbnCd, String kikan);
}

