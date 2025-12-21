package jp.co.isid.mos.bird.bizreport.netorderniporef.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.netorderniporef.entity.TrnSibuYosanNipoSegRelate;

public interface TrnYosanSegInfoDao {
                   
    public static final Class BEAN  = TrnSibuYosanNipoSegRelate.class;
    
    public static final String getSegYosanNipo_ARGS = "companyCd" +
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
                                                        ",segmentList";

    public static final String getSegYosanGepo_ARGS = "companyCd" +
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
                                                        ",segmentList";
    
    
    /**
     * ƒZƒOƒƒ“ƒg‚²‚Æ‚Ì—\Z‚ğæ“¾‚·‚é(“ú•ñ)
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
     * @return
     */

    public List getSegYosanNipo(String companyCd
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
                                , List segmentList);
  
    /**
     * ƒZƒOƒƒ“ƒg‚²‚Æ‚Ì—\Z‚ğæ“¾‚·‚é(Œ•ñ)
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
     * @return
     */
    public List getSegYosanGepo(String companyCd
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
                                , List segmentList);

}
