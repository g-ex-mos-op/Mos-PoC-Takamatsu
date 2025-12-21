package jp.co.isid.mos.bird.bizreport.groupeigyoniporef.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.entity.TrnYosanInfo;

public interface TrnYosanInfoDao {

    public static final Class BEAN = TrnYosanInfo.class;
    
    
    public static final String getYosanNipo_ARGS = "companyCd" +
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

    public static final String getYosanGepo_ARGS = "companyCd" +
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
                      
    public static final String getMiseYosanGepoKiho_ARGS = "companyCd" +
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
                                                        ",sibuCd" +
                                                        ",svCd";
    
    
    public static final String getXYosanNipo_ARGS = "companyCd" +
                                                         ",userId" +
                                                         ",sibuCd,taishoKikan,kikanFrom,kikanTo,areaDaiFlg,keyList,limitFlg";
    
    public static final String getXYosanGepo_ARGS = "companyCd" +
                                                         ",userId" +
                                                         ",sibuCd,taishoKikan,kikanFrom,kikanTo,areaDaiFlg,keyList,limitFlg";
    
    /**
     * ó\éZéÊìæ(éxïîóp)(ì˙ïÒ)
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
     * @param dataShukbn
     * @return
     */
    public List getYosanNipo( String companyCd
                                , String userId
                                , String tenShu
                                , String dataShu
                                , String taishoKikan
                                , String kikanFrom
                                , String kikanTo
                                , boolean limitFlg
                                , String areaDaiFlg
                                , String taishoTenpo
                                , String dataShukbn);

    /**
     * ó\éZéÊìæ(éxïîóp)(åéïÒ)
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
     * @param dataShukbn
     * @return
     */
    public List getYosanGepo( String companyCd
                                , String userId
                                , String tenShu
                                , String dataShu
                                , String taishoKikan
                                , String kikanFrom
                                , String kikanTo
                                , boolean limitFlg
                                , String areaDaiFlg
                                , String taishoTenpo
                                , String dataShukbn);


    /**
     * ó\éZéÊìæ(ìXï )(ä˙ï ä˙ïÒóp)
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
     * @param dataShukbn
     * 
     * @ param svCd         SVÉRÅ[Éh 2008/12/09í«â¡ SVëŒâû 
     * @return
     */
    public List getMiseYosanGepoKiho( String companyCd
                                , String userId
                                , String tenShu
                                , String dataShu
                                , String taishoKikan
                                , String kikanFrom
                                , String kikanTo
                                , boolean limitFlg
                                , String areaDaiFlg
                                , String taishoTenpo
                                , String dataShukbn
                                , String sibuCd
                                , String svCd);


    /**
     * XìXî‘ópó\éZéÊìæ
     * 
     * @param companyCd
     * @param userId
     * @param sibuCd
     * @param taishoKikan
     * @param kikanFrom
     * @param kikanTo
     * @param areaDaiFlg
     * @param keyList
     * @param limitFlg
     * @return
     */
    public List getXYosanNipo( String companyCd
            , String userId
            , String sibuCd, String taishoKikan, String kikanFrom, String kikanTo, String areaDaiFlg, List keyList ,boolean limitFlg);

    /**
     * XìXî‘ópó\éZéÊìæ
     * 
     * @param companyCd
     * @param userId
     * @param sibuCd
     * @param taishoKikan
     * @param kikanFrom
     * @param kikanTo
     * @param areaDaiFlg
     * @param keyList
     * @param limitFlg
     * @return
     */
    public List getXYosanGepo( String companyCd
            , String userId
            , String sibuCd, String taishoKikan, String kikanFrom, String kikanTo, String areaDaiFlg, List keyList ,boolean limitFlg);

}

