package jp.co.isid.mos.bird.bizreport.moscardniporef.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.code.NipoZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.common.code.ShukeiKbn;
import jp.co.isid.mos.bird.bizreport.common.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.common.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.moscardniporef.entity.TrnYosanInfo;
import jp.co.isid.mos.bird.bizreport.moscardniporef.common.MoscardNipoConstants;
import jp.co.isid.mos.bird.bizreport.moscardniporef.dao.TrnUriageGepoInfoDao;
import jp.co.isid.mos.bird.bizreport.moscardniporef.dao.TrnUriageNipoInfoDao;
import jp.co.isid.mos.bird.bizreport.moscardniporef.dao.TrnYosanInfoDao;
import jp.co.isid.mos.bird.bizreport.moscardniporef.entity.TrnUriageNipoInfo;
import jp.co.isid.mos.bird.bizreport.moscardniporef.logic.UriageNipoInfoLogic;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.dao.MstSibuDao;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.util.Calculator;

/**
 * LOGIC【店舗売上情報取得】<br/>
 * 作成日:2013/02/01
 * @author   xyamauchi
 * <br/>
 * 更新日:2013/06/05 ADD 営業日補正時に比率値が値に関係なく全て赤で表示されていた不具合の対応<br/>
 *
 */
public class UriageNipoInfoLogicImpl implements UriageNipoInfoLogic {
	/** LOGIC ID:BBR015L06 */
    public static final String LOGIC_ID = "BBR015L06";
    
    // 合計１
    private String rClassSum1 = "body_sum1";
    // 合計２
//    private String rClassSum2 = "body_sum2";
    // 合計３
    private String rClassSum3 = "body_sum3";
    // 合計４
    private String rClassSum4 = "body_sum4";
    // 合計５
    private String rClassSum5 = "body_sum5";
    
    // 数字右寄せ(合計行専用)
    private String bodyNumClass = "body_num";
    // 比率100以下またはマイナス(合計行専用) 
    private String bodyHirituClass = "body_hiritu";
    // 比率100以下またはマイナス(通常行専用) 
    private String bodyHirituClassM = "body_hiritu_m";
    // 比率100以上またはプラス(通常行専用)
    private String bodyNumClassN = "body_num_n";
    // 指定無し(全てのクラスに使用可)
    private String noClass = "";
    
    // Mapのパラメータ
    private static final String COMPANY_CD = "companyCd";
    public String USER_ID = "userId";
    public String TENSHU = "tenpoShu";
    public String DATASHU = "dataShu";
    public String TAISHO_KIKAN = "taishoKikan";
    public String KIKAN_FROM  = "kikanFrom";
    public String KIKAN_TO = "kikanTo";
    public String LIMIT_FLG = "limitFlg";
    public String AREA_DAI_FLG = "areaDaiFlg";
    public String TAISHO_TENPO = "taishoTenpo";
    public String SIBU_CD = "sibuCd";
    public String KBN_CD = "kbnCd";
    public String USER_TYPE_CD = "userTypeCd";
    public String ONER_CD = "onerCd";
    public String NO_DISP = "NOTDISP";
    
    private TrnUriageNipoInfoDao trnUriageNipoInfoDao;
    
    private TrnYosanInfoDao trnYosanInfoDao;
    
    private TrnUriageGepoInfoDao trnUriageGepoInfoDao;
    
    private MstSibuDao mstSibuDao;
    
    // 数値型比較用定数
    private BigDecimal bigZero = new BigDecimal(0.00);
    
    /**
     * trnUriageNipoInfoDaoの設定
     * @return
     */
    public TrnUriageNipoInfoDao getTrnUriageNipoInfoDao() {
        return this.trnUriageNipoInfoDao;
    }
    /**
     * trnUriageNipoInfoDaoの設定
     * @param trnUriageNipoInfoDao
     */
    public void setTrnUriageNipoInfoDao( TrnUriageNipoInfoDao trnUriageNipoInfoDao ) {
        this.trnUriageNipoInfoDao = trnUriageNipoInfoDao;
    }
    
    /**
     * 
     * @return
     */
    public TrnUriageGepoInfoDao getTrnUriageGepoInfoDao() {
        return this.trnUriageGepoInfoDao;
    }
    
    /**
     * 
     * @param trnUriageGepoInfoDao
     */
    public void setTrnUriageGepoInfoDao( TrnUriageGepoInfoDao trnUriageGepoInfoDao ) {
        this.trnUriageGepoInfoDao = trnUriageGepoInfoDao;
    }
    /**
     * trnYosanInfoDaoの設定
     * @return
     */
    public TrnYosanInfoDao getTrnYosanInfoDao() {
        return this.trnYosanInfoDao;
    }
    /**
     * trnYosanInfoDaoの設定
     * @param trnYosanInfoDao
     */
    public void setTrnYosanInfoDao( TrnYosanInfoDao trnYosanInfoDao ) {
        this.trnYosanInfoDao = trnYosanInfoDao;
    }
    
    /**
     * mstSibuDaoの設定
     * @return
     */
    public MstSibuDao getMstSibuDao() {
        return this.mstSibuDao;
    }
    /**
     * mstSibuDaoの設定
     * @param mstSibuDao
     */
    public void setMstSibuDao( MstSibuDao mstSibuDao ) {
        this.mstSibuDao = mstSibuDao;
    }
    /**
     * 対象支部、オーナーの売上、予算その他諸々を取得する(店用)
     * @param Map
     * @return List
     */
    public Map execute(Map paramMap) {
        Map resultMap = new HashMap();
        String userTypeCd = (String)paramMap.get( USER_TYPE_CD );
        
        // 本部ユーザのとき
        if ( userTypeCd.equals( UserType.HONBU ) ) {
            String companyCd = (String)paramMap.get( COMPANY_CD );
            String userId = (String)paramMap.get( USER_ID );
            String tenpoShu = (String)paramMap.get( TENSHU );
            String dataShu = (String)paramMap.get( DATASHU );
            String taishoKikan = (String)paramMap.get( TAISHO_KIKAN );
            String kikanFrom = (String)paramMap.get( KIKAN_FROM );
            String kikanTo = (String)paramMap.get( KIKAN_TO );
            boolean limitFlg = ( (Boolean)paramMap.get( LIMIT_FLG ) ).booleanValue();
            String areaDaiFlg = (String)paramMap.get( AREA_DAI_FLG );            
            String sibuCd = ((String)paramMap.get( SIBU_CD )).trim();        
            //１．[[営業日報情報]]の取得
            List resultList = getNipoInfo( paramMap);
            String kbnInfo = new String();
            
            kbnInfo = "BLOCK";
            
            List underTotalList = new ArrayList();
            List xYosanList = new ArrayList();
                
            /** ２．直営店を含まない(支部コードでの検索)の時    **********************************/      
            if ( areaDaiFlg.equals( ShukeiKbn.OUT_RC )) {
                List totalList = createTotalList("TOTAL", companyCd);
            	if(resultList.size() > 0) {
            		underTotalList = createTotalList( resultList ,kbnInfo);
                    totalList = createTotalList( resultList, "TOTAL");
            	}
                //  2.店舗種別が『全店』or 『新店』の場合。
                if ( tenpoShu.equals( TenpoShubetu.CODE_ALL) || tenpoShu.equals( TenpoShubetu.CODE_SIN ) ) {
                    //2-1. [[X店番予算]]を取得
                    if ( taishoKikan.equals( TaishoKikan.DAY1 ) 
                            || taishoKikan.equals( TaishoKikan.DAYS ) 
                            || taishoKikan.equals( TaishoKikan.MONTHAPP)) {
                        //DAO【予算情報】.日次用X店番予算取得処理を実行する。
                         xYosanList = getTrnYosanInfoDao().getXYosanNipo( 
                                 companyCd, userId, sibuCd,taishoKikan,kikanFrom,kikanTo,areaDaiFlg ,getKeyList(),limitFlg);
                    } else {
                        //DAO【予算情報】.月次用X店番予算取得処理を実行する。
                        xYosanList = getTrnYosanInfoDao().getXYosanGepo( 
                                companyCd, userId, sibuCd,taishoKikan,kikanFrom,kikanTo,areaDaiFlg ,getKeyList(),limitFlg);
                    }
                    if(xYosanList.size() == 0) {
                        if(resultList == null || resultList.size() == 0){
                            //売上データも新店予算データも存在しない場合はException発生。
                            throw new NoResultException("");
                        }                       
                    }            

                }
                //3.店舗種別が『前年対象店』or 『予算対象店』の場合。
                else {
                    if ( !dataShu.equals( NipoZennenDataShubetu.CODE_HOSEI ) ) {
                	//[[営業日報情報]]に結果件数が0件でデータが存在しない場合はException発生。
                    if ( resultList == null || resultList.size() == 0 ) {
                        throw new NoResultException("");
                    }                	
                    }
                }
// モスフードサービスのみブロック計を表示
                if ( companyCd.equals( CommonUtil.COMPANY_CD_MOS ) && resultList.size() > 0) {
                    resultList = createConnectList( resultList,underTotalList,kbnInfo );
                }
                
                resultList.addAll( totalList );
                
            }
            /** 集計区分で『SV指定(担当店一覧)』が選択された場合 2008/12/09追加 **********************/
            else if ( areaDaiFlg.equals( ShukeiKbn.SV_CD )) {
                
                if ( resultList == null || resultList.size() == 0 ) {
                    throw new NoResultException("");
                }  
                
                //検索結果Listにブロック計と支部合計と総合計行を追加する。
                List svResultList = createSvSumList(resultList, paramMap);
                
                resultList.clear();
                resultList.addAll(svResultList);
                
            }
            /** ３．直営・販社含む(エリア大コードの検索)の時 *****************************************/
            else {
                if ( !dataShu.equals( NipoZennenDataShubetu.CODE_HOSEI ) ) {
                	//1．[[営業日報情報]]に結果件数が0件でデータが存在しない場合はException発生。
	                if ( resultList == null || resultList.size() == 0 ) {
	                    throw new NoResultException("");
	                }
                }
            }
            resultList = createSortList( resultList );
            
            resultMap = createSeparateList( resultList );
            //取得したX店番予算をセット
            if ( tenpoShu.equals( TenpoShubetu.CODE_ALL) || tenpoShu.equals( TenpoShubetu.CODE_SIN ) ) {
                resultMap.put("yosanList",xYosanList);
            }
            
            // 前年データ種別が売上補正のとき(取得するデータは前年同月のデータ)
            if ( dataShu.equals( NipoZennenDataShubetu.CODE_HOSEI ) ) {
                List uriageHoseiList = (List)resultMap.get("uriage");
                List kyakusuHoseiList = (List)resultMap.get("kyakusu");
                
                dataShu = NipoZennenDataShubetu.CODE_DOGETU;
                
                paramMap.put(DATASHU,dataShu);
                
                List dogetuList = getNipoInfo( paramMap );
            	//前年同月の[[営業日報情報]]に結果件数が0件でデータが存在しない場合はException発生。
                if ( dogetuList == null || dogetuList.size() == 0 ) {
                    throw new NoResultException("");
                }                	
                /** 直営店を含まない(支部コードでの検索)の時    ***************************/
                if ( areaDaiFlg.equals( ShukeiKbn.OUT_RC )) {
                    List totalDogetuList = createTotalList("TOTAL", companyCd);
                    List underDogetuList = createTotalList(dogetuList,"BLOCK");
                    totalDogetuList = createTotalList( underDogetuList, "TOTAL");
                    if(companyCd.equals( CommonUtil.COMPANY_CD_MOS ) && dogetuList.size() > 0) {
                    	//モスフードサービスのみブロック計を表示
	                    dogetuList = createConnectList( dogetuList,underDogetuList,"BLOCK");
                    }
                    
                    dogetuList.addAll( totalDogetuList);
                }
                /** 集計区分で『SV指定(担当店一覧)』が選択された場合 2008/12/09追加 ******/
                else if ( areaDaiFlg.equals( ShukeiKbn.SV_CD )) {
                    //検索結果Listにブロック計と支部合計と総合計行を追加する。
                    List svDogetuList = createSvSumList(dogetuList, paramMap);
                    
                    dogetuList.clear();
                    dogetuList.addAll(svDogetuList);
                }
                
                dogetuList = createSortList( dogetuList );
                Map dogetuMap = createSeparateList( dogetuList );
                
                List uriageDogetuList = (List)dogetuMap.get("uriage");
                List kyakusuDogetuList = (List)dogetuMap.get("kyakusu");
                
                Map hoseiMap = new HashMap();
                hoseiMap.put("uriageHosei",uriageHoseiList);
                hoseiMap.put("kyakusuHosei",kyakusuHoseiList);
                hoseiMap.put("uriageDogetu",uriageDogetuList);
                hoseiMap.put("kyakusuDogetu",kyakusuDogetuList);
                
                resultMap = getHoseiList( hoseiMap );
                
                dataShu = NipoZennenDataShubetu.CODE_HOSEI;
                
                paramMap.put(DATASHU,dataShu);
            }
        // オーナーユーザの時
        } else if ( userTypeCd.equals( UserType.ONER ) ) {
            String companyCd = (String)paramMap.get( COMPANY_CD );
            String dataOnerInfo = (String)paramMap.get( DATASHU );
            String kikanInfo = (String)paramMap.get( TAISHO_KIKAN );
            String kikanFrom = (String)paramMap.get( KIKAN_FROM );
            String kikanTo = (String)paramMap.get( KIKAN_TO );
            String onerCd = (String)paramMap.get( ONER_CD );
            List uriageList = new ArrayList();
            
            // 日報系(期日指定日報、期間指定)のとき
            if ( kikanInfo.equals( TaishoKikan.DAY1 ) || kikanInfo.equals( TaishoKikan.DAYS ) ) {
                uriageList = getTrnUriageNipoInfoDao().getOnerUriageNipo( companyCd
                                                                        , kikanInfo
                                                                        , kikanFrom
                                                                        , kikanTo
                                                                        , onerCd
                                                                        , dataOnerInfo);
            // 月報系(当月月報、月別月報告)のとき
            } else {
                if (kikanFrom != null && kikanFrom.length() > 6) {
                    kikanFrom = kikanFrom.substring(0, 6);
                }
                uriageList = getTrnUriageGepoInfoDao().getOnerUriageGepo( companyCd
                                                                        , kikanInfo
                                                                        , kikanFrom
                                                                        , kikanTo
                                                                        , onerCd
                                                                        , dataOnerInfo);
            }
            
            // 該当検索データがないとき
            if ( uriageList == null || uriageList.size() == 0 ) {
                throw new NoResultException("");
            }

            // trのクラス(noclass)をセット
            for ( int i = 0; i < uriageList.size(); i++ ) {
                TrnUriageNipoInfo uriage = (TrnUriageNipoInfo)uriageList.get(i);
                uriage.setRClass( noClass );
            }
            
            // 対象店舗数を取得
            Integer miseCnt = new Integer( uriageList.size() );
            
            
            List totalList = createTotalList( uriageList,"ONER");
            // 取得した合計行を最終行に追加
            uriageList.addAll( totalList );
            uriageList = createSortList( uriageList );
             
            resultMap = createSeparateList( uriageList );
            
            resultMap.put("miseCnt",miseCnt );
            
        }
        
        return resultMap;
        
    }
    
    
    /**
     * 集計区分で『SV指定(担当店一覧)』が選択された場合の検索結果に
     * ブロック計と支部合計と総合計行を追加する。
     * 
     * @param  resultList   検索結果List
     * @param  paramMap     
     * @return svResultList ブロック計と支部合計と総合計行を追加したList
     */
    private List createSvSumList(List resultList, Map paramMap){
        String companyCd    = (String)paramMap.get( COMPANY_CD );
        
        String beforeSibu   = "";
        String nowSibu      = "";
        List tmpList        = new ArrayList();
        List underTotalList = new ArrayList();
        List totalList      = new ArrayList();
        List svResultList   = new ArrayList();
        int maxSize        = resultList.size();
        for( int i = 0; i < maxSize + 1 ; i++ ) {
            TrnUriageNipoInfo trnUriageNipoInfo = null;
            
            if(i!=maxSize){
                trnUriageNipoInfo = (TrnUriageNipoInfo) resultList.get(i);
                beforeSibu = nowSibu;
                nowSibu    = trnUriageNipoInfo.getSibuCd();                         
            }
                            
            if (i==maxSize || (!beforeSibu.equals("") && !beforeSibu.equals(nowSibu))) {
                //ブロック合計行取得
                underTotalList = createTotalList( tmpList, "BLOCK");
                //支部合計行取得
                totalList      = createTotalList( tmpList, "SV_SIBU_TOTAL");                
                if ( companyCd.equals( CommonUtil.COMPANY_CD_MOS ) && tmpList.size() > 0) {
                    tmpList = createConnectList( tmpList, underTotalList, "BLOCK" );
                }
                
                svResultList.addAll( tmpList );                         
                svResultList.addAll( totalList );
                tmpList.clear();
                
                if(i==maxSize){
                    break;
                }
            }            
            tmpList.add(trnUriageNipoInfo);
        }
        
        //総合計行の取得
        List svTotalList = createTotalList( resultList, "SV_TOTAL");
        //総合計行の支部コード、支部名称に空白セット
        TrnUriageNipoInfo uriNipoInfo = (TrnUriageNipoInfo) svTotalList.get(0);
        uriNipoInfo.setSibuCd("");
        uriNipoInfo.setSibuName("");
        
        svResultList.addAll( svTotalList );
        
        return svResultList;
    }

    /**
     * 営業日補正用データ整理
     * 
     * 更新日:2013/06/05 ADD 営業日補正時に比率値が値に関係なく全て赤で表示されていた不具合の対応<br/>
     *        補正用前年比クラス上書の処理を追加しました。<br/>
     * 
     * @param map
     * @return
     */
    private Map getHoseiList( Map map) {
        List uriageHoseiList = (List)map.get("uriageHosei");
        List uriageDogetuList = (List)map.get("uriageDogetu");
    
        List uriageList = new ArrayList();
        
        // 売上のデータ作成
        for( int j = 0; j < uriageDogetuList.size(); j++ ) {
            TrnUriageNipoInfo uriDou = (TrnUriageNipoInfo)uriageDogetuList.get(j);
            TrnUriageNipoInfo uriage = new TrnUriageNipoInfo();
            
            // 前年売上のBigDecimal
            BigDecimal uriageZen = new BigDecimal(0);
            
            String zenIssueCntHirituClass = new String();
            String chargeKinZenHiClass = new String();
            String chargeKenZenHiClass = new String();
            String chargeTankaZenHiClass = new String();
            String kessaiKinZenhiClass = new String();
            String kessaiKenZenhiClass = new String();
            String kessaiTankaZenhiClass = new String();
            
            // 合計行のときと通常行（店の行)のときのクラスが違うため、どのクラスか判断する
            if ( uriDou.getRClass().equals( noClass ) ) {
                zenIssueCntHirituClass = bodyHirituClassM;
                chargeKinZenHiClass = bodyHirituClassM;
                chargeKenZenHiClass = bodyHirituClassM;
                chargeTankaZenHiClass = bodyHirituClassM;
                kessaiKinZenhiClass = bodyHirituClassM;
                kessaiKenZenhiClass = bodyHirituClassM;
                kessaiTankaZenhiClass = bodyHirituClassM;
                
            } else {
                zenIssueCntHirituClass = bodyHirituClass;
                chargeKinZenHiClass = bodyHirituClass;
                chargeKenZenHiClass = bodyHirituClass;
                chargeTankaZenHiClass = bodyHirituClass;
                kessaiKinZenhiClass = bodyHirituClass;
                kessaiKenZenhiClass = bodyHirituClass;
                kessaiTankaZenhiClass = bodyHirituClass;
            }

            // 前年比(売上)のBigDecimal
            BigDecimal zenHiUri = new BigDecimal(0.00);
            // 前年比(発行枚数)のBigDecimal getZenIssueCntHiritu
            BigDecimal zenIssueCntHiritu = new BigDecimal(0.00);
            // 前年比(チャージ金額)のBigDecimal
            BigDecimal chargeKinZenHi = new BigDecimal(0.00);
            // 前年比(チャージ件数)のBigDecimal
            BigDecimal chargeKenZenHi = new BigDecimal(0.00);
            // 前年比(チャージ単価)のBigDecimal
            BigDecimal chargeTankaZenHi = new BigDecimal(0.00);
            // 前年比(決済金額)のBigDecimal
            BigDecimal ｋessaiKinZenhi = new BigDecimal(0.00);
            // 前年比(決済件数)のBigDecimal
            BigDecimal ｋessaiKenZenhi = new BigDecimal(0.00);
            // 前年比(決済単価)のBigDecimal
            BigDecimal ｋessaiTankaZenhi = new BigDecimal(0.00);
            

            // 対象の売上補正のデータを探す
            for( int i = 0; i < uriageHoseiList.size(); i++ ) {
                TrnUriageNipoInfo uriHose = (TrnUriageNipoInfo)uriageHoseiList.get(i);

                if ( uriDou.getCompanyCd().equals( uriHose.getCompanyCd() ) 
                        && uriDou.getMiseCd().equals( uriHose.getMiseCd() ) 
                        && uriDou.getSibuCd().equals( uriHose.getSibuCd() )) {
                    uriageZen = uriHose.getUriageZen();
                    zenHiUri = uriHose.getZenHiUri();
                    zenIssueCntHiritu = uriHose.getZenIssueCntHiritu();
                    chargeKinZenHi = uriHose.getChargeKinZenHi();
                    chargeKenZenHi = uriHose.getChargeKenZenHi();
                    chargeTankaZenHi = uriHose.getChargeTankaZenHi();
                    ｋessaiKinZenhi = uriHose.getKessaiKinZenhi();
                    ｋessaiKenZenhi = uriHose.getKessaiKenZenhi();
                    ｋessaiTankaZenhi = uriHose.getKessaiTankaZenhi();
                    //補正前年比クラス上書き処理(追加:2013/06/05)
                    zenIssueCntHirituClass = uriHose.getZenIssueCntHirituClass();
                    chargeKinZenHiClass = uriHose.getChargeKinZenHiClass();
                    chargeKenZenHiClass = uriHose.getChargeKenZenHiClass();
                    chargeTankaZenHiClass = uriHose.getChargeTankaZenHiClass();
                    kessaiKinZenhiClass = uriHose.getKessaiKinZenhiClass();
                    kessaiKenZenhiClass = uriHose.getKessaiKenZenhiClass();
                    kessaiTankaZenhiClass = uriHose.getKessaiTankaZenhiClass();
                    
                    break;
                }
            }

            // trのクラスは前年同月のクラスに合わせる
            uriage.setRClass( uriDou.getRClass() );
            uriage.setCompanyCd( uriDou.getCompanyCd() );
            uriage.setBlockCd( uriDou.getBlockCd() );
            uriage.setBlockName( uriDou.getBlockName() );
            uriage.setEigyoDays( uriDou.getEigyoDays() );
            uriage.setEigyoDaysZen( uriDou.getEigyoDaysZen() );
            uriage.setHonbuCd( uriDou.getHonbuCd() );
            uriage.setHonbuName( uriDou.getHonbuName() );
            uriage.setJigyoCd( uriDou.getJigyoCd() );
            uriage.setJigyoName( uriDou.getJigyoName() );
            uriage.setSibuCd( uriDou.getSibuCd() );
            uriage.setSibuName( uriDou.getSibuName() );
            uriage.setMiseCd( uriDou.getMiseCd() );
            uriage.setMiseNameKj( uriDou.getMiseNameKj() );
            uriage.setKbn1( uriDou.getKbn1() );
            uriage.setUriage( uriDou.getUriage() );
            uriage.setYosan( uriDou.getYosan() );
            uriage.setTenkoKbn( uriDou.getTenkoKbn() );
            uriage.setTenkoKbnZen( uriDou.getTenkoKbnZen() );
            uriage.setEigyoDays( uriDou.getEigyoDays() );
            uriage.setEigyoDaysZen( uriDou.getEigyoDaysZen() );
            
            uriage.setTasseiUriClass( uriDou.getTasseiUriClass() );
            uriage.setTassei( uriDou.getTassei() );
            uriage.setUriageZen( uriageZen );
            uriage.setZenHiUri( zenHiUri );
            
            uriage.setIssueCnt( uriDou.getIssueCnt() );
            uriage.setZenIssueCnt( uriDou.getZenIssueCnt() );
            uriage.setChargeKin( uriDou.getChargeKin() );
            uriage.setZenChargeKin( uriDou.getZenChargeKin() );
            uriage.setChargeKen( uriDou.getChargeKen() );
            uriage.setZenChargeKen( uriDou.getZenChargeKen() );
            uriage.setKessaiKin( uriDou.getKessaiKin() );
            uriage.setZenKessaiKin( uriDou.getZenKessaiKin() );
            uriage.setKessaiKen( uriDou.getKessaiKen() );
            uriage.setZenKessaiKen( uriDou.getZenKessaiKen() );                
             
            uriage.setZenIssueCntHiritu( zenIssueCntHiritu);
            uriage.setZenIssueCntHirituClass( zenIssueCntHirituClass );
            uriage.setChargeKinUriHi( uriDou.getChargeKinUriHi() );
            uriage.setChargeKinUriHiClass( uriDou.getChargeKinUriHiClass() );
            uriage.setChargeKinZenHi( chargeKinZenHi );
            uriage.setChargeKinZenHiClass( chargeKinZenHiClass);
            uriage.setChargeKenKyakuHi( uriDou.getChargeKenKyakuHi() );
            uriage.setChargeKenKyakuHiClass( uriDou.getChargeKenKyakuHiClass() );                  
            uriage.setChargeKenZenHi( chargeKenZenHi );
            uriage.setChargeKenZenHiClass( chargeKenZenHiClass );      
            uriage.setChargeTanka( uriDou.getChargeTanka() );
            uriage.setChargeTankaClass( uriDou.getChargeTankaClass() );
            uriage.setZenChargeTanka( uriDou.getZenChargeTanka() );
            uriage.setChargeTankaHi( uriDou.getChargeTankaHi() );
            uriage.setChargeTankaHiClass( uriDou.getChargeTankaHiClass() );     
            uriage.setChargeTankaZenHi( chargeTankaZenHi );
            uriage.setChargeTankaZenHiClass( chargeTankaZenHiClass );  
            
            uriage.setKessaiKinUrihi( uriDou.getKessaiKinUrihi() );
            uriage.setKessaiKinUrihiClass( uriDou.getKessaiKinUrihiClass() );
            uriage.setKessaiKinZenhi( ｋessaiKinZenhi );
            uriage.setKessaiKinZenhiClass( kessaiKinZenhiClass );
            uriage.setKessaiKenKyakuhi( uriDou.getKessaiKenKyakuhi() );
            uriage.setKessaiKenKyakuhiClass( uriDou.getKessaiKenKyakuhiClass() );
            uriage.setKessaiKenZenhi( ｋessaiKenZenhi );
            uriage.setKessaiKenZenhiClass( kessaiKenZenhiClass);
            uriage.setKessaiTanka( uriDou.getKessaiTanka() );
            uriage.setZenKessaiTanka( uriDou.getZenKessaiTanka() );
            uriage.setKessaiTankaHi( uriDou.getKessaiTankaHi() );
            uriage.setKessaiTankaHiClass( uriDou.getKessaiTankaHiClass() );
            uriage.setKessaiTankaZenhi( ｋessaiTankaZenhi );
            uriage.setKessaiTankaZenhiClass( kessaiTankaZenhiClass );
            uriage.setSvCd( uriDou.getSvCd() );
            uriage.setUsernamekj( uriDou.getUsernamekj() );
            
            uriage.setChargeKinCancel( uriDou.getChargeKinCancel() );
            uriage.setChargeKenCancel( uriDou.getChargeKenCancel() );
            uriage.setUseKinCancel( uriDou.getUseKinCancel() );
            uriage.setUseKenCancel( uriDou.getUseKenCancel() );
            uriage.setBonusVIssue( uriDou.getBonusVIssue() );
            uriage.setBonusVUse( uriDou.getBonusVUse() );
            uriage.setCouponVIssue( uriDou.getCouponVIssue() );
            uriage.setCouponVUse( uriDou.getCouponVUse() );
            uriage.setZandaka( uriDou.getZandaka() );
            uriage.setSvCd( uriDou.getSvCd() );
            uriage.setUsernamekj( uriDou.getUsernamekj() );
            uriage.setKyakusu( uriDou.getKyakusu() );
            uriage.setKyakusuZen( uriDou.getKyakusuZen() );
            uriage.setZenHiKyaku( uriDou.getZenHiKyaku() );
            uriage.setTanka( uriDou.getTanka() );
            uriage.setTankaZen( uriDou.getTankaZen() );
            uriage.setZenHiTanka( uriDou.getZenHiTanka() );          
            
            uriageList.add( uriage);

        }
        
        map.put("uriage",uriageList);
        
        return map;
        
    }
    /**
     * 営業日報情報の取得
     * @param paramMap
     * @return
     * @modifier xkinu 2007/06/05 T.Kinugawa(ASPAC) クローズ店予算表示対応
     */
    private List getNipoInfo(Map paramMap) {
    	
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
    	String userTypeCd = (String)paramMap.get( USER_TYPE_CD );
        
        //SV情報 2008/12/09追加
        String svCd   = (String) paramMap.get(MoscardNipoConstants.SV_CD);
        
        if ( taishoKikan.equals( TaishoKikan.MONTHAPP )) {
            kikanFrom = kikanFrom.substring(0,6);
        }
        
        String sibuCd = ((String)paramMap.get( SIBU_CD )).trim();
        String kbnCd = (String)paramMap.get(KBN_CD );
        List resultList = new ArrayList();
        
        if ( taishoKikan.equals( TaishoKikan.DAY1 ) || taishoKikan.equals( TaishoKikan.DAYS ) ) {
            resultList = getTrnUriageNipoInfoDao().selectMiseList(
            		userTypeCd, userId, limitFlg, companyCd, tenpoShu, dataShu
            		, taishoKikan, kikanFrom, kikanTo
            		, areaDaiFlg
            		, taishoTenpo
            		, kbnCd, sibuCd
                    , svCd);
            
        } else if ( taishoKikan.equals( TaishoKikan.MONTHAPP ) 
        		|| taishoKikan.equals( TaishoKikan.MONTH )|| taishoKikan.equals( TaishoKikan.KIBETU ) ) {
            resultList = getTrnUriageGepoInfoDao().selectMiseList(
            		userTypeCd, userId, limitFlg, companyCd, tenpoShu, dataShu
            		, taishoKikan, kikanFrom, kikanTo
            		, areaDaiFlg
            		, taishoTenpo
            		, kbnCd, sibuCd
                    , svCd);
        }
        
        if(taishoKikan.equals( TaishoKikan.KIBETU )) {
            List yosanList = getTrnYosanInfoDao().getMiseYosanGepoKiho(companyCd
                                                                    ,userId
                                                                    ,tenpoShu
                                                                    ,dataShu
                                                                    ,taishoKikan
                                                                    ,kikanFrom
                                                                    ,kikanTo
                                                                    ,limitFlg
                                                                    ,areaDaiFlg
                                                                    ,taishoTenpo
                                                                    ,tenpoShu
                                                                    ,sibuCd
                                                                    ,svCd);
            for( int i = 0; i < resultList.size(); i++ ) {
                TrnUriageNipoInfo uriageInfo = (TrnUriageNipoInfo)resultList.get(i);
                String uriCompanyCd = uriageInfo.getCompanyCd();
                String uriMiseCd = uriageInfo.getMiseCd();
                for( int j = 0; j < yosanList.size(); j++ ) {
                    TrnYosanInfo yosanInfo = (TrnYosanInfo)yosanList.get(j);
                    String yosCompanyCd = yosanInfo.getCompanyCd();
                    String yosMiseCd = yosanInfo.getMiseCd();
                    
                    if ( uriCompanyCd.equals(yosCompanyCd) && uriMiseCd.equals(yosMiseCd)) {
                        uriageInfo.setYosan(yosanInfo.getYosan());
                        break;
                    }
                }
            }
        }
        
        for ( int i = 0; i < resultList.size(); i++ ) {
            TrnUriageNipoInfo nipo = (TrnUriageNipoInfo)resultList.get(i);
            nipo.setRClass( noClass );
        }

        return resultList;
        
    }
    /**
     * 売上リスト、客数リストを作成
     * @param resultList
     * @return
     */
    private Map createSeparateList (List resultList ) {
        
        Map resultMap = new HashMap();
        
        List uriageList = new ArrayList();
        
        for (int i = 0; i < resultList.size();i++ ) {
            TrnUriageNipoInfo uriage = new TrnUriageNipoInfo();
            TrnUriageNipoInfo result = (TrnUriageNipoInfo)resultList.get(i);
            
            uriage.setCompanyCd( result.getCompanyCd() );
            uriage.setRClass( result.getRClass() );
            uriage.setHonbuCd( result.getHonbuCd() );
            uriage.setHonbuName( result.getHonbuName() );
            uriage.setJigyoCd( result.getJigyoCd() );
            uriage.setJigyoName( result.getJigyoName() );
            uriage.setSibuCd( result.getSibuCd() );
            uriage.setSibuName( result.getSibuName() );
            uriage.setBlockCd( result.getBlockCd() );
            uriage.setBlockName( result.getBlockName() );
            uriage.setMiseCd( result.getMiseCd() );
            uriage.setMiseNameKj( result.getMiseNameKj() );
            uriage.setKbn1( result.getKbn1() );
            uriage.setTenkoKbn( result.getTenkoKbn() );
            uriage.setTenkoKbnZen( result.getTenkoKbnZen() );
            
            uriage.setIssueCnt( result.getIssueCnt() );
            uriage.setZenIssueCnt( result.getZenIssueCnt() );
            uriage.setChargeKin( result.getChargeKin() );
            uriage.setZenChargeKin( result.getZenChargeKin() );
            uriage.setChargeKen( result.getChargeKen() );
            uriage.setZenChargeKen( result.getZenChargeKen() );
            uriage.setKessaiKin( result.getKessaiKin() );
            uriage.setZenKessaiKin( result.getZenKessaiKin() );
            uriage.setKessaiKen( result.getKessaiKen() );
            uriage.setZenKessaiKen( result.getZenKessaiKen() );                   
             
            uriage.setZenIssueCntHiritu( result.getZenIssueCntHiritu() );
            uriage.setZenIssueCntHirituClass( result.getZenIssueCntHirituClass() );
            uriage.setChargeKinUriHi( result.getChargeKinUriHi() );
            uriage.setChargeKinUriHiClass( result.getChargeKinUriHiClass() );
            uriage.setChargeKinZenHi( result.getChargeKinZenHi() );
            uriage.setChargeKinZenHiClass( result.getChargeKinZenHiClass() );
            uriage.setChargeKenKyakuHi( result.getChargeKenKyakuHi() );
            uriage.setChargeKenKyakuHiClass( result.getChargeKenKyakuHiClass() );                  
            uriage.setChargeKenZenHi( result.getChargeKenZenHi() );
            uriage.setChargeKenZenHiClass( result.getChargeKenZenHiClass() );      
            uriage.setChargeTanka( result.getChargeTanka() );
            uriage.setChargeTankaClass( result.getChargeTankaClass() );
            uriage.setZenChargeTanka( result.getZenChargeTanka() );
            uriage.setChargeTankaHi( result.getChargeTankaHi() );
            uriage.setChargeTankaHiClass( result.getChargeTankaHiClass() );     
            uriage.setChargeTankaZenHi( result.getChargeTankaZenHi() );
            uriage.setChargeTankaZenHiClass( result.getChargeTankaZenHiClass() );
            
            uriage.setKessaiKinUrihi( result.getKessaiKinUrihi() );
            uriage.setKessaiKinUrihiClass( result.getKessaiKinUrihiClass() );
            uriage.setKessaiKinZenhi( result.getKessaiKinZenhi() );
            uriage.setKessaiKinZenhiClass( result.getKessaiKinZenhiClass() );
            uriage.setKessaiKenKyakuhi( result.getKessaiKenKyakuhi() );
            uriage.setKessaiKenKyakuhiClass( result.getKessaiKenKyakuhiClass() );
            uriage.setKessaiKenZenhi( result.getKessaiKenZenhi() );
            uriage.setKessaiKenZenhiClass( result.getKessaiKenZenhiClass() );            
            uriage.setKessaiTanka( result.getKessaiTanka() );
            uriage.setZenKessaiTanka( result.getZenKessaiTanka() );
            uriage.setKessaiTankaHi( result.getKessaiTankaHi() );
            uriage.setKessaiTankaHiClass( result.getKessaiTankaHiClass() );
            uriage.setKessaiTankaZenhi( result.getKessaiTankaZenhi() );
            uriage.setKessaiTankaZenhiClass( result.getKessaiTankaZenhiClass() );
            
            uriage.setEigyoDays( result.getEigyoDays() );
            uriage.setEigyoDaysZen( result.getEigyoDaysZen() );
            uriage.setUriage( result.getUriage() );
            uriage.setYosan( result.getYosan() );
            uriage.setTasseiUriClass( result.getTasseiUriClass() );
            uriage.setTassei( result.getTassei() );
            uriage.setUriageZen( result.getUriageZen() );
            uriage.setZenHiUri( result.getZenHiUri() );
            uriage.setSvCd( result.getSvCd() );
            uriage.setUsernamekj( result.getUsernamekj() );
            uriage.setKyakusu( result.getKyakusu() );
            uriage.setKyakusuZen( result.getKyakusuZen() );
            uriage.setZenHiKyaku( result.getZenHiKyaku() );
            uriage.setTanka( result.getTanka() );
            uriage.setTankaZen( result.getTankaZen() );
            uriage.setZenHiTanka( result.getZenHiTanka() );
            
            uriage.setChargeKinCancel( result.getChargeKinCancel() );
            uriage.setChargeKenCancel( result.getChargeKenCancel() );
            uriage.setUseKinCancel( result.getUseKinCancel() );
            uriage.setUseKenCancel( result.getUseKenCancel() );
            uriage.setBonusVIssue( result.getBonusVIssue() );
            uriage.setBonusVUse( result.getBonusVUse() );
            uriage.setCouponVIssue( result.getCouponVIssue() );
            uriage.setCouponVUse( result.getCouponVUse() );
            uriage.setZandaka( result.getZandaka() );
      
            uriageList.add( uriage );
            
        }
        
        resultMap.put("uriage",uriageList );
        resultMap.put("result",resultList);
        
        return resultMap;
    }
    /**
     * 合計行挿入(ブロック計のみ)
     * @param resultList
     * @param underTotalList
     * @param kbnInfo
     * @return
     */
    private List createConnectList(List resultList, List underTotalList, String kbnInfo ) {
        
        List returnList = new ArrayList();
        
        for ( int i = 0; i < underTotalList.size(); i++ ) {
            TrnUriageNipoInfo uriTol = (TrnUriageNipoInfo)underTotalList.get(i);
            String uriTolCd = new String();
            String resltCd = new String();
            
            List connectList = new ArrayList();
            
            for (int j = 0; j < resultList.size(); j++ ) {
                TrnUriageNipoInfo uriRes = (TrnUriageNipoInfo)resultList.get(j);
                if ( kbnInfo.equals("BLOCK" ) ) {
                    uriTolCd = uriTol.getBlockCd();
                    resltCd = uriRes.getBlockCd();
                }
                if ( uriTolCd.equals( resltCd) ) {
                    connectList.add( uriRes );
                }
            }
            returnList.addAll( connectList);
            returnList.add( uriTol );
            
        }
        
        return returnList;
        
    }
    

    /**
     * 合計行を求める(ブロック毎と総合計のみ）
     * @param sortList
     * @param kbnInfo
     * @return
     */
    private List createTotalList( List sortList, String kbnInfo) {

        TrnUriageNipoInfo uriNipo = (TrnUriageNipoInfo ) sortList.get(0);
        BigDecimal uriage = uriNipo.getUriage();
        BigDecimal kyakusu = uriNipo.getKyakusu();
        BigDecimal yosan = uriNipo.getYosan();
        BigDecimal uriageZen = uriNipo.getUriageZen();
        BigDecimal kyakusuZen = uriNipo.getKyakusuZen();
        BigDecimal issueCnt = uriNipo.getIssueCnt();
        BigDecimal issueCntZen = uriNipo.getZenIssueCnt();
        BigDecimal issueCntZenHi = uriNipo.getZenIssueCntHiritu();
        BigDecimal chargeKin = uriNipo.getChargeKin();
        BigDecimal chargeKinZen = uriNipo.getZenChargeKin();
        BigDecimal chargeKinUrihi = uriNipo.getChargeKinUriHi();
        BigDecimal chargeKinZenihi = uriNipo.getChargeKinZenHi();
        BigDecimal chargeKen = uriNipo.getChargeKen();
        BigDecimal chargeKenZen = uriNipo.getZenChargeKen();
        BigDecimal chargeKenKyakuHi = uriNipo.getChargeKenKyakuHi();
        BigDecimal chargeKenZenHi = uriNipo.getChargeKenZenHi();
        BigDecimal chargeTanka = uriNipo.getChargeTanka();
        BigDecimal zenChargeTanka = uriNipo.getZenChargeTanka();
        BigDecimal chargeTankahi = uriNipo.getChargeTankaHi();
        BigDecimal chargeTankaZenhi = uriNipo.getChargeTankaZenHi();   
        BigDecimal kessaiKin = uriNipo.getKessaiKin();
        BigDecimal kessaiKinZen = uriNipo.getZenKessaiKin();
        BigDecimal kessaiKinUrihi = uriNipo.getKessaiKinUrihi();
        BigDecimal kessaiKinZenhi = uriNipo.getKessaiKinZenhi();
        BigDecimal kessaiKen = uriNipo.getKessaiKen();
        BigDecimal kessaiKenZen = uriNipo.getZenKessaiKen();
        BigDecimal kessaiKenKyakuhi = uriNipo.getKessaiKenKyakuhi();
        BigDecimal kessaiKenZenhi = uriNipo.getKessaiKenZenhi();
        BigDecimal kessaiTanka = uriNipo.getKessaiTanka();
        BigDecimal kessaiZenTanka = uriNipo.getZenKessaiTanka();
        BigDecimal kessaiTankaHi = uriNipo.getKessaiTankaHi();
        BigDecimal kessaiTankaZenhi = uriNipo.getKessaiTankaZenhi();
        BigDecimal eigyoCnt = uriNipo.getEigyoDays();
        BigDecimal eigyoZenCnt = uriNipo.getEigyoDaysZen();
        BigDecimal chargeKinCancel = uriNipo.getChargeKinCancel();
        BigDecimal chargeKenCancel = uriNipo.getChargeKenCancel();
        BigDecimal useKinCancel = uriNipo.getUseKinCancel();
        BigDecimal useKenCancel = uriNipo.getUseKenCancel();
        BigDecimal bonusVIssue = uriNipo.getBonusVIssue();
        BigDecimal bonusVUse = uriNipo.getBonusVUse();
        BigDecimal couponVIssue = uriNipo.getCouponVIssue();
        BigDecimal couponVUse = uriNipo.getCouponVUse();
        BigDecimal zandaka = uriNipo.getZandaka();
        
        
        List resultList = new ArrayList();
        
        TrnUriageNipoInfo uriNipoInfo = new TrnUriageNipoInfo();
        TrnUriageNipoInfo uriNipoInfoBef = new TrnUriageNipoInfo();

        String curCd = new String();
        String curName = new String();
        String befCd = new String();
        String befName = new String();
        String curCdUpper = new String();
        String befCdUpper = new String();
        String rclass = new String();
        String svCd = new String();
        String svName = new String();
        
        uriNipoInfo = uriNipo;
        
        // サイズが１のときに使用する
        if ( kbnInfo.equals("BLOCK" ) ) {
            curCd = uriNipo.getBlockCd();
            curName = uriNipo.getBlockName().trim();
            curCdUpper = uriNipo.getSibuCd();
            rclass = rClassSum1;
        } else if  (kbnInfo.equals("TOTAL" ) ) {
            curCd = uriNipo.getCompanyCd();
            curName = "支部合";
            curCdUpper = uriNipo.getCompanyCd();
            rclass = rClassSum5;
        } else if ( kbnInfo.equals("ONER") ) {
            curCd = uriNipo.getCompanyCd();
            curName = "総合";
            curCdUpper = uriNipo.getCompanyCd();
            rclass = rClassSum5;
        }
/* 20081209追加 SV対応 start ----------------------------*/
        else if  (kbnInfo.equals("SV_SIBU_TOTAL" ) ) {
            curCd      = uriNipo.getCompanyCd();
            curName    = "支部合";
            curCdUpper = uriNipo.getCompanyCd();
            rclass     = rClassSum3;
        }else if  (kbnInfo.equals("SV_TOTAL" ) ) {
            curCd      = uriNipo.getCompanyCd();
            curName    = "総合";
            curCdUpper = uriNipo.getCompanyCd();
            rclass     = rClassSum5;
        }
/* 20081209追加 End -------------------------------------*/       
        
        for ( int i = 1; i < sortList.size(); i++ ) {
            uriNipoInfo = (TrnUriageNipoInfo)sortList.get(i);
            uriNipoInfoBef = (TrnUriageNipoInfo)sortList.get(i - 1);
            if ( kbnInfo.equals("BLOCK" ) ) {
                curCd = uriNipoInfo.getBlockCd();
                curName = uriNipoInfo.getBlockName().trim();
                curCdUpper = uriNipoInfo.getSibuCd();
                befCd = uriNipoInfoBef.getBlockCd();
                befName = uriNipoInfoBef.getBlockName().trim();            
                befCdUpper = uriNipoInfoBef.getSibuCd();
                rclass = rClassSum1;
            } else if  (kbnInfo.equals("TOTAL" ) ) {
                curCd = uriNipoInfo.getCompanyCd();
                curName = "支部合";
                curCdUpper = uriNipoInfo.getCompanyCd();
                befCd = uriNipoInfoBef.getCompanyCd();
                befName = "支部合";
                befCdUpper = uriNipoInfoBef.getCompanyCd();
                rclass = rClassSum5;
            } else if ( kbnInfo.equals( "SIBU" ) ) {
                curCd = uriNipoInfo.getSibuCd();
                curName = uriNipoInfo.getSibuName().trim();
                curCdUpper = uriNipoInfo.getCompanyCd();
                befCd = uriNipoInfoBef.getSibuCd();
                befName = uriNipoInfoBef.getSibuName().trim();
                befCdUpper = uriNipoInfoBef.getCompanyCd();
                rclass = rClassSum5;                
            } else if (kbnInfo.equals("ONER" ) ) {
                curCd = uriNipoInfo.getCompanyCd();
                curName = "総合";
                befCd = uriNipoInfoBef.getCompanyCd();
                befName = "総合";
                befCdUpper = uriNipoInfoBef.getCompanyCd();
                rclass = rClassSum5;
            }
/* 20081209追加 SV対応 start ----------------------------*/
            else if  (kbnInfo.equals("SV_SIBU_TOTAL" ) ) {
                curCd = uriNipoInfo.getCompanyCd();
                curName    = "支部合";
                curCdUpper = uriNipoInfo.getCompanyCd();
                befCd      = uriNipoInfoBef.getCompanyCd();
                befName    = "支部合";
                befCdUpper = uriNipoInfoBef.getCompanyCd();               
                rclass     = rClassSum3;
            }else if  (kbnInfo.equals("SV_TOTAL" ) ) {
                curCd      = uriNipoInfo.getCompanyCd();
                curName    = "総合";
                curCdUpper = uriNipoInfo.getCompanyCd();
                befCd      = uriNipoInfoBef.getCompanyCd();
                befName    = "総合";
                befCdUpper = uriNipoInfoBef.getCompanyCd();              
                rclass     = rClassSum5;
            }
    /* 20081209追加 End -------------------------------------*/     
            
            if ( (curCdUpper.equals( befCdUpper ) && curCd.equals( befCd )) ) {
                uriage = uriage.add( uriNipoInfo.getUriage() );
                kyakusu = kyakusu.add( uriNipoInfo.getKyakusu() );
                yosan = yosan.add( uriNipoInfo.getYosan() );
                uriageZen = uriageZen.add( uriNipoInfo.getUriageZen() );
                kyakusuZen = kyakusuZen.add( uriNipoInfo.getKyakusuZen() );
                issueCnt = issueCnt.add( uriNipoInfo.getIssueCnt() );
                issueCntZen = issueCntZen.add( uriNipoInfo.getZenIssueCnt() );
                issueCntZenHi = issueCntZenHi.add( uriNipoInfo.getZenIssueCntHiritu() );
                chargeKin = chargeKin.add( uriNipoInfo.getChargeKin() );
                chargeKinZen = chargeKinZen.add( uriNipoInfo.getZenChargeKin() );
                chargeKinUrihi = chargeKinUrihi.add( uriNipoInfo.getChargeKinUriHi() );
                chargeKinZenihi = chargeKinZenihi.add( uriNipoInfo.getChargeKinZenHi() );
                chargeKen = chargeKen.add( uriNipoInfo.getChargeKen() );
                chargeKenZen = chargeKenZen.add( uriNipoInfo.getZenChargeKen() );
                chargeKenKyakuHi = chargeKenKyakuHi.add( uriNipoInfo.getChargeKenKyakuHi() );
                chargeKenZenHi = chargeKenZenHi.add( uriNipoInfo.getChargeKenZenHi() );
                chargeTanka = chargeTanka.add( uriNipoInfo.getChargeTanka() );
                chargeTankahi = chargeTankahi.add( uriNipoInfo.getChargeTankaHi() );
                chargeTankaZenhi = chargeTankaZenhi.add(uriNipoInfo.getChargeTankaZenHi());
                kessaiKin = kessaiKin.add(uriNipoInfo.getKessaiKin());
                kessaiKinZen = kessaiKinZen.add(uriNipoInfo.getZenKessaiKin());
                kessaiKinUrihi = kessaiKinUrihi.add(uriNipoInfo.getKessaiKinUrihi());
                kessaiKinZenhi = kessaiKinZenhi.add(uriNipoInfo.getKessaiKinZenhi());
                kessaiKen = kessaiKen.add(uriNipoInfo.getKessaiKen());
                kessaiKenZen = kessaiKenZen.add(uriNipoInfo.getZenKessaiKen());
                kessaiKenKyakuhi = kessaiKenKyakuhi.add(uriNipoInfo.getKessaiKenKyakuhi());
                kessaiKenZenhi = kessaiKenZenhi.add(uriNipoInfo.getKessaiKenZenhi());
                kessaiTanka = kessaiTanka.add(uriNipoInfo.getKessaiTanka());
                kessaiZenTanka = kessaiTanka.add(uriNipoInfo.getZenKessaiTanka());
                kessaiTankaHi = kessaiTankaHi.add(uriNipoInfo.getKessaiTankaHi());
                kessaiTankaZenhi = kessaiTankaZenhi.add(uriNipoInfo.getKessaiTankaZenhi());
                eigyoCnt = eigyoCnt.add( uriNipoInfo.getEigyoDays() );
                eigyoZenCnt = eigyoZenCnt.add( uriNipoInfo.getEigyoDaysZen() );
                chargeKinCancel = chargeKinCancel.add(uriNipoInfo.getChargeKinCancel());
                chargeKenCancel = chargeKenCancel.add(uriNipoInfo.getChargeKenCancel());
                useKinCancel = useKinCancel.add(uriNipoInfo.getUseKinCancel());
                useKenCancel = useKenCancel.add(uriNipoInfo.getUseKenCancel());
                bonusVIssue = bonusVIssue.add(uriNipoInfo.getBonusVIssue());
                bonusVUse = bonusVUse.add(uriNipoInfo.getBonusVUse());
                couponVIssue = couponVIssue.add( uriNipoInfo.getCouponVIssue() );
                couponVUse = couponVUse.add( uriNipoInfo.getCouponVUse() );
                zandaka = zandaka.add( uriNipoInfo.getZandaka() );
                
            } else {
                TrnUriageNipoInfo totalUri = new TrnUriageNipoInfo();
                totalUri.setCompanyCd( uriNipoInfoBef.getCompanyCd() );
                totalUri.setRClass( rclass );
                totalUri.setSibuCd( uriNipoInfoBef.getSibuCd() );
                totalUri.setSibuName( uriNipoInfoBef.getSibuName() );
                totalUri.setBlockCd( befCd );
                totalUri.setBlockName( befName + "計" );
                totalUri.setSvCd( svCd);
                totalUri.setUsernamekj( svName );
                totalUri.setMiseCd( befCd );
                totalUri.setMiseNameKj( befName);
                totalUri.setHonbuCd( uriNipoInfoBef.getHonbuCd() );
                totalUri.setHonbuName( uriNipoInfoBef.getHonbuName() );
                totalUri.setJigyoCd( uriNipoInfoBef.getJigyoCd() );
                totalUri.setJigyoName( uriNipoInfoBef.getJigyoName() );
                totalUri.setSlareaCd( uriNipoInfoBef.getSlareaCd() );
                totalUri.setSlareaName( uriNipoInfoBef.getJigyoName() );
                totalUri.setUriage( uriage );
                totalUri.setKyakusu( kyakusu );
                totalUri.setYosan( yosan );
                totalUri.setKyakusuZen( kyakusuZen );
                totalUri.setUriageZen( uriageZen );
                totalUri.setIssueCnt(issueCnt);
                totalUri.setZenIssueCnt(issueCntZen);
                totalUri.setZenIssueCntHiritu(issueCntZenHi);
                totalUri.setChargeKin(chargeKin);
                totalUri.setZenChargeKin(chargeKinZen);
                totalUri.setChargeKinUriHi(chargeKinUrihi);
                totalUri.setChargeKen(chargeKen);
                totalUri.setZenChargeKen(chargeKenZen);
                totalUri.setChargeKenKyakuHi(chargeKenKyakuHi);
                totalUri.setChargeKenZenHi(chargeKenZenHi);
                totalUri.setChargeTanka(chargeTanka);
                totalUri.setZenChargeTanka(zenChargeTanka );                
                totalUri.setChargeTankaHi(chargeTankahi);
                totalUri.setChargeTankaZenHi(chargeTankaZenhi);
                totalUri.setKessaiKin(kessaiKin);
                totalUri.setZenKessaiKin(kessaiKinZen);
                totalUri.setKessaiKinUrihi(kessaiKinUrihi);
                totalUri.setKessaiKinZenhi(kessaiKinZenhi);
                totalUri.setKessaiKen(kessaiKen);
                totalUri.setZenKessaiKen(kessaiKenZen);
                totalUri.setKessaiKenKyakuhi(kessaiKenKyakuhi);
                totalUri.setKessaiKenZenhi(kessaiKenZenhi);
                totalUri.setKessaiTanka(kessaiTanka);
                totalUri.setZenKessaiTanka(kessaiZenTanka);
                totalUri.setKessaiTankaHi(kessaiTankaHi);
                totalUri.setKessaiTankaZenhi(kessaiTankaZenhi);
                totalUri.setEigyoDays( eigyoCnt );
                totalUri.setEigyoDaysZen( eigyoZenCnt );
                totalUri.setChargeKinCancel(chargeKinCancel);
                totalUri.setChargeKenCancel(chargeKenCancel);
                totalUri.setUseKinCancel(useKinCancel);
                totalUri.setUseKenCancel(useKenCancel);
                totalUri.setBonusVIssue(bonusVIssue);
                totalUri.setBonusVUse(bonusVUse);
                totalUri.setCouponVIssue(couponVIssue );
                totalUri.setCouponVUse(couponVUse );
                totalUri.setZandaka(zandaka );
                
                resultList.add( totalUri );
                
                uriage = uriNipoInfo.getUriage();
                kyakusu = uriNipoInfo.getKyakusu();
                yosan = uriNipoInfo.getYosan();
                kyakusuZen = uriNipoInfo.getKyakusuZen();
                uriageZen = uriNipoInfo.getUriageZen();
                issueCnt = uriNipoInfo.getIssueCnt();
                issueCntZen = uriNipoInfo.getZenIssueCnt();
                issueCntZenHi = uriNipoInfo.getZenIssueCntHiritu();
                chargeKin = uriNipoInfo.getChargeKin();
                chargeKinZen = uriNipoInfo.getZenChargeKin();
                chargeKinUrihi = uriNipoInfo.getChargeKinUriHi();
                chargeKinZenihi = uriNipoInfo.getChargeKinZenHi();
                chargeKen = uriNipoInfo.getChargeKen();
                chargeKenZen = uriNipoInfo.getZenChargeKen();
                chargeKenKyakuHi = uriNipoInfo.getChargeKenKyakuHi();
                chargeKenZenHi = uriNipoInfo.getChargeKenZenHi();
                chargeTanka = uriNipoInfo.getChargeTanka();
                chargeTankahi = uriNipoInfo.getChargeTankaHi();
                chargeTankaZenhi = uriNipoInfo.getChargeTankaZenHi();
                kessaiKin = uriNipoInfo.getKessaiKin();
                kessaiKinZen = uriNipoInfo.getZenKessaiKin();
                kessaiKinUrihi = uriNipoInfo.getKessaiKinUrihi(); 
                kessaiKinZenhi = uriNipoInfo.getKessaiKinZenhi();
                kessaiKen = uriNipoInfo.getKessaiKen();
                kessaiKenZen = uriNipoInfo.getZenKessaiKen();
                kessaiKenZenhi = uriNipoInfo.getKessaiKenZenhi();
                kessaiTanka = uriNipoInfo.getKessaiTanka();
                kessaiZenTanka = uriNipoInfo.getZenKessaiTanka();
                kessaiTankaHi = uriNipoInfo.getKessaiTankaHi();
                kessaiTankaZenhi = uriNipoInfo.getKessaiTankaZenhi();
                eigyoCnt = uriNipoInfo.getEigyoDays();
                eigyoZenCnt = uriNipoInfo.getEigyoDaysZen();
                chargeKinCancel = uriNipo.getChargeKinCancel();
                chargeKenCancel = uriNipo.getChargeKenCancel();
                useKinCancel = uriNipo.getUseKinCancel();
                useKenCancel = uriNipo.getUseKenCancel();
                bonusVIssue = uriNipo.getBonusVIssue();
                bonusVUse = uriNipo.getBonusVUse();
                couponVIssue = uriNipo.getCouponVIssue();
                couponVUse = uriNipo.getCouponVUse();
                zandaka = uriNipo.getZandaka();

            }
        }
        
        TrnUriageNipoInfo totalUri = new TrnUriageNipoInfo();
        totalUri.setCompanyCd( uriNipoInfo.getCompanyCd() );
        totalUri.setRClass( rclass );
        totalUri.setSibuCd( uriNipoInfo.getSibuCd() );
        totalUri.setSibuName( uriNipoInfo.getSibuName() );
        totalUri.setBlockCd( curCd );
        totalUri.setBlockName( curName + "計" );
        totalUri.setSvCd( svCd);
        totalUri.setUsernamekj( svName );        
        totalUri.setMiseCd( curCd );
        totalUri.setMiseNameKj( curName + "計" );
        totalUri.setHonbuCd( uriNipoInfo.getHonbuCd() );
        totalUri.setHonbuName( uriNipoInfo.getHonbuName() );
        totalUri.setJigyoCd( uriNipoInfo.getJigyoCd() );
        totalUri.setJigyoName( uriNipoInfo.getJigyoName() );
        totalUri.setSlareaCd( uriNipoInfo.getSlareaCd() );
        totalUri.setSlareaName( uriNipoInfo.getJigyoName() );
        totalUri.setUriage( uriage );
        totalUri.setKyakusu( kyakusu );
        totalUri.setYosan( yosan );
        totalUri.setKyakusuZen( kyakusuZen );
        totalUri.setUriageZen( uriageZen );
        totalUri.setIssueCnt( issueCnt );
        totalUri.setZenIssueCnt(issueCntZen);
        totalUri.setZenIssueCntHiritu(issueCntZenHi);
        totalUri.setChargeKin(chargeKin);
        totalUri.setZenChargeKin(chargeKinZen);
        totalUri.setChargeKinUriHi( chargeKinUrihi);
        totalUri.setChargeKinZenHi( chargeKinZenihi);
        totalUri.setChargeKen(chargeKen);
        totalUri.setZenChargeKen(chargeKenZen);
        totalUri.setChargeKenKyakuHi( chargeKenKyakuHi );                  
        totalUri.setChargeKenZenHi( chargeKenZenHi );      
        totalUri.setChargeTanka(chargeTanka );
        totalUri.setZenChargeTanka(zenChargeTanka );
        totalUri.setChargeTankaHi(chargeTankahi );
        totalUri.setChargeTankaZenHi( chargeTankaZenhi );
        totalUri.setKessaiKin( kessaiKin );
        totalUri.setZenKessaiKin( kessaiKinZen );
        totalUri.setKessaiKinUrihi( kessaiKinUrihi );
        totalUri.setKessaiKinZenhi( kessaiKinZenhi );
        totalUri.setKessaiKen( kessaiKen );
        totalUri.setZenKessaiKen( kessaiKenZen );
        totalUri.setKessaiKenKyakuhi( kessaiKenKyakuhi );
        totalUri.setKessaiKenZenhi( kessaiKenZenhi );
        totalUri.setKessaiTanka( kessaiTanka );
        totalUri.setZenKessaiTanka( kessaiZenTanka );
        totalUri.setKessaiTankaHi( kessaiTankaHi );
        totalUri.setKessaiTankaZenhi( kessaiTankaZenhi );
        totalUri.setEigyoDays( eigyoCnt );
        totalUri.setEigyoDaysZen( eigyoZenCnt );
        totalUri.setChargeKinCancel(chargeKinCancel);
        totalUri.setChargeKenCancel(chargeKenCancel);
        totalUri.setUseKinCancel(useKinCancel);
        totalUri.setUseKenCancel(useKenCancel);
        totalUri.setBonusVIssue(bonusVIssue);
        totalUri.setBonusVUse(bonusVUse);
        totalUri.setCouponVIssue(couponVIssue );
        totalUri.setCouponVUse(couponVUse );
        totalUri.setZandaka(zandaka );

        resultList.add( totalUri );

        return resultList;
    }
    /**
     * 合計行を求める(ブロック毎と総合計のみ）
     * 
     * @param kbnInfo
     * @return
     */
    private List createTotalList(String kbnInfo, String companyCd) {

        BigDecimal uriage = new BigDecimal("0");
        BigDecimal kyakusu = new BigDecimal("0");
        BigDecimal yosan = new BigDecimal("0");
        BigDecimal uriageZen = new BigDecimal("0");
        BigDecimal kyakusuZen = new BigDecimal("0");
        BigDecimal eigyoCnt = new BigDecimal("0");
        BigDecimal eigyoZenCnt = new BigDecimal("0");
        BigDecimal issueCnt = new BigDecimal("0");
        BigDecimal chargeKin = new BigDecimal("0");
        BigDecimal chargeKen = new BigDecimal("0");
        BigDecimal chargetanka = new BigDecimal("0");
        BigDecimal kessaiKin = new BigDecimal("0");
        BigDecimal kessaiKen = new BigDecimal("0");
        
        List resultList = new ArrayList();
        
        TrnUriageNipoInfo uriNipoInfo = new TrnUriageNipoInfo();

        String curCd = new String();
        String curName = new String();
        String rclass = new String();
        
        // サイズが１のときに使用する
        if  (kbnInfo.equals("TOTAL" ) ) {
            curCd = companyCd;
            curName = "支部合";
            rclass = rClassSum5;
        }
        
        
        TrnUriageNipoInfo totalUri = new TrnUriageNipoInfo();
        totalUri.setCompanyCd( companyCd );
        totalUri.setRClass( rclass );
        totalUri.setSibuCd( uriNipoInfo.getSibuCd() );
        totalUri.setSibuName( uriNipoInfo.getSibuName() );
        totalUri.setBlockCd( curCd );
        totalUri.setBlockName( curName + "計" );
        totalUri.setMiseCd( curCd );
        totalUri.setMiseNameKj( curName + "計" );
        totalUri.setHonbuCd( uriNipoInfo.getHonbuCd() );
        totalUri.setHonbuName( uriNipoInfo.getHonbuName() );
        totalUri.setJigyoCd( uriNipoInfo.getJigyoCd() );
        totalUri.setJigyoName( uriNipoInfo.getJigyoName() );
        totalUri.setSlareaCd( uriNipoInfo.getSlareaCd() );
        totalUri.setSlareaName( uriNipoInfo.getJigyoName() );
        totalUri.setUriage( uriage );
        totalUri.setKyakusu( kyakusu );
        totalUri.setYosan( yosan );
        totalUri.setKyakusuZen( kyakusuZen );
        totalUri.setUriageZen( uriageZen );
        totalUri.setEigyoDays( eigyoCnt );
        totalUri.setEigyoDaysZen( eigyoZenCnt );
        totalUri.setKyakusuZen( kyakusuZen );
        totalUri.setUriageZen( uriageZen );
        totalUri.setIssueCnt( issueCnt );
        totalUri.setChargeKin( chargeKin );
        totalUri.setChargeKen( chargeKen );
        totalUri.setTanka( chargetanka );
        totalUri.setKessaiKin( kessaiKin );
        totalUri.setKessaiKen( kessaiKen );
        
        resultList.add( totalUri );

        return resultList;
    }
    /**
     * 取得した情報のうち、計算によって求めるものをセットする
     * @param paramList
     * @return
     */
    private List createSortList(List paramList ) {
        
        BigDecimal ritu = new BigDecimal(100);
        
        for ( int i = 0; i < paramList.size(); i++ ) {
            
            TrnUriageNipoInfo nipo = (TrnUriageNipoInfo)paramList.get(i);
            
            if ( !nipo.getRClass().equals( rClassSum4 ) ) {
                
                nipo.setTanka( Calculator.divide( nipo.getUriage(),nipo.getKyakusu() ) );
                nipo.setTankaZen( Calculator.divide( nipo.getUriageZen(),nipo.getKyakusuZen() ) );
                
                //発行枚数前年比計算
                BigDecimal issueCntHiritu = Calculator.percentage(nipo.getIssueCnt(),nipo.getZenIssueCnt(),2);
                //チャージ金額売上比計算
                BigDecimal uriHiChargeKin = Calculator.percentage(nipo.getChargeKin(),nipo.getUriage(),2);
                //チャージ金額前年比計算        
                BigDecimal rituChargeKin = Calculator.percentage(nipo.getChargeKin(),nipo.getZenChargeKin(),2);                
                //チャージ件数客数比計算
                BigDecimal rituChargeKyaku = Calculator.percentage(nipo.getChargeKen(),nipo.getKyakusu(),2);
                //チャージ件数前年比計算
                BigDecimal rituChargeKen = Calculator.percentage(nipo.getChargeKen(),nipo.getZenChargeKen(),2);
                // 客単価
                BigDecimal kyakusuTanka = nipo.getTanka();
                // チャージ単価(当年)計算
                BigDecimal chargeTanka = Calculator.divide(nipo.getChargeKin(),nipo.getChargeKen());
                // チャージ単価(前年)計算
                BigDecimal zenChargeTanka = Calculator.divide(nipo.getZenChargeKin(),nipo.getZenChargeKen());
                // チャージ単価(客単価比)        
                BigDecimal chargeTankahi = Calculator.percentage(chargeTanka,kyakusuTanka,2);
                // チャージ単価(前年比)       
                BigDecimal zenChargeTankahi = Calculator.percentage(chargeTanka,zenChargeTanka,2);
                // 決済金額売上比計算        
                BigDecimal uriHiKessaiKin = Calculator.percentage(nipo.getKessaiKin(),nipo.getUriage(),2);
                // 決済金前年比計算        
                BigDecimal zenHiKessaiKin = Calculator.percentage(nipo.getKessaiKin(),nipo.getZenKessaiKin(),2);
                // 決済件数客数比計算        
                BigDecimal kyakuHiKessaiKen = Calculator.percentage(nipo.getKessaiKen(),nipo.getKyakusu(),2);
                // 決済件数前年比計算        
                BigDecimal zenHiKessaiKen = Calculator.percentage(nipo.getKessaiKen(),nipo.getZenKessaiKen(),2);
                // 決済金単価計算        
                BigDecimal tankaKessaiKin = Calculator.divide(nipo.getKessaiKin(),nipo.getKessaiKen());
                // 前年決済金単価計算        
                BigDecimal zenTankaKessaiKin = Calculator.divide(nipo.getZenKessaiKin(),nipo.getZenKessaiKen());
                // 決済金単価（客単価比）計算        
                BigDecimal tankaHiKessaiKin = Calculator.percentage(tankaKessaiKin,kyakusuTanka,2);
                // 決済金単価（前年比）計算        
                BigDecimal zenTankaHiKessaiKin = Calculator.percentage(tankaKessaiKin,zenTankaKessaiKin,2);
                
                BigDecimal tasseiRitu = Calculator.percentage( nipo.getUriage(),nipo.getYosan(),2);
                
                // 達成率チェック
                if ( tasseiRitu.compareTo(bigZero) != 1 ) {
                    tasseiRitu = new BigDecimal("0.00");
                }
                // 達成率をセット
                nipo.setTassei( tasseiRitu );
                // 達成率クラス設定
                if (nipo.getTassei().compareTo( ritu) < 0 ) {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setTasseiUriClass( bodyHirituClassM);
                    } else {
                        nipo.setTasseiUriClass( bodyHirituClass );
                    }
                    
                } else {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setTasseiUriClass( bodyNumClassN );
                    } else {
                        nipo.setTasseiUriClass( bodyNumClass );
                    }
                }
                //発行枚数（前年比）チェック
                if ( issueCntHiritu.compareTo(bigZero) != 1 ) {
                    issueCntHiritu = new BigDecimal("0.00");
                }
                // 発行枚数（前年比）をセット
                nipo.setZenIssueCntHiritu(issueCntHiritu);
                // 発行枚数（前年比）クラス設定
                if (nipo.getZenIssueCntHiritu().compareTo( ritu) < 0 ) {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setZenIssueCntHirituClass( bodyHirituClassM);
                    } else {
                        nipo.setZenIssueCntHirituClass( bodyHirituClass );
                    }
                    
                } else {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setZenIssueCntHirituClass( bodyNumClassN );
                    } else {
                        nipo.setZenIssueCntHirituClass( bodyNumClass );
                    }
                }
                //チャージ金額（売上比）チェック                
                if ( uriHiChargeKin.compareTo(bigZero) != 1 ) {
                    uriHiChargeKin = new BigDecimal("0.00");
                }
                //チャージ金額（売上比）をセット               
                nipo.setChargeKinUriHi(uriHiChargeKin);
                // チャージ金額（売上比）クラス設定                
                if (nipo.getChargeKinUriHi().compareTo( ritu) < 0 ) {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setChargeKinUriHiClass( bodyHirituClassM);
                    } else {
                        nipo.setChargeKinUriHiClass( bodyHirituClass );
                    }
                    
                } else {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setChargeKinUriHiClass( bodyNumClassN );
                    } else {
                        nipo.setChargeKinUriHiClass( bodyNumClass );
                    }
                }
                //チャージ金額（前年比）チェック                    
                if ( rituChargeKin.compareTo(bigZero) != 1 ) {
                    rituChargeKin = new BigDecimal("0.00");
                }
                //チャージ金額（前年比）をセット                     
                nipo.setChargeKinZenHi(rituChargeKin);
                // チャージ金額（前年比）クラス設定                  
                if (nipo.getChargeKinZenHi().compareTo( ritu) < 0 ) {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setChargeKinZenHiClass( bodyHirituClassM);
                    } else {
                        nipo.setChargeKinZenHiClass( bodyHirituClass );
                    }
                    
                } else {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setChargeKinZenHiClass( bodyNumClassN );
                    } else {
                        nipo.setChargeKinZenHiClass( bodyNumClass );
                    }
                }
                
                //チャージ金額（客数比）チェック 
                if ( rituChargeKyaku.compareTo(bigZero) != 1 ) {
                    rituChargeKyaku = new BigDecimal("0.00");
                }
                //チャージ金額（客数比）をセット
                nipo.setChargeKenKyakuHi(rituChargeKyaku);
                // チャージ金額（客数比）クラス設定 
                if (nipo.getChargeKenKyakuHi().compareTo( ritu) < 0 ) {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setChargeKenKyakuHiClass( bodyHirituClassM);
                    } else {
                        nipo.setChargeKenKyakuHiClass( bodyHirituClass );
                    }
                    
                } else {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setChargeKenKyakuHiClass( bodyNumClassN );
                    } else {
                        nipo.setChargeKenKyakuHiClass( bodyNumClass );
                    }
                }
                //チャージ件数（前年比）チェック                
                if ( rituChargeKen.compareTo(bigZero) != 1 ) {
                    rituChargeKen = new BigDecimal("0.00");
                }
                //チャージ金額（前年比）をセット                
                nipo.setChargeKenZenHi(rituChargeKen);
                // チャージ金額（前年比）クラス設定                
                if (nipo.getChargeKenZenHi().compareTo( ritu) < 0 ) {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setChargeKenZenHiClass( bodyHirituClassM);
                    } else {
                        nipo.setChargeKenZenHiClass( bodyHirituClass );
                    }
                    
                } else {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setChargeKenZenHiClass( bodyNumClassN );
                    } else {
                        nipo.setChargeKenZenHiClass( bodyNumClass );
                    }
                }
                
                //チャージ単価をセット
                nipo.setChargeTanka(chargeTanka);
                // チャージ単価クラス設定    
                if (nipo.getChargeTanka().compareTo( ritu) < 0 ) {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setChargeTankaClass( bodyHirituClassM);
                    } else {
                        nipo.setChargeTankaClass( bodyHirituClass );
                    }
                    
                } else {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setChargeTankaClass( bodyNumClassN );
                    } else {
                        nipo.setChargeTankaClass( bodyNumClass );
                    }
                }
                
                //チャージ単価（単価比）チェック
                if ( chargeTankahi.compareTo(bigZero) != 1 ) {
                    chargeTankahi = new BigDecimal("0.00");
                }
                //チャージ単価（単価比）をセット                
                nipo.setChargeTankaHi(chargeTankahi);
                // チャージ単価（単価比）クラス設定                
                if (nipo.getChargeTankaHi().compareTo( ritu) < 0 ) {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setChargeTankaHiClass( bodyHirituClassM);
                    } else {
                        nipo.setChargeTankaHiClass( bodyHirituClass );
                    }
                    
                } else {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setChargeTankaHiClass( bodyNumClassN );
                    } else {
                        nipo.setChargeTankaHiClass( bodyNumClass );
                    }
                }
                //チャージ単価（前年比）チェック
                if ( zenChargeTankahi.compareTo(bigZero) != 1 ) {
                    zenChargeTankahi = new BigDecimal("0.00");
                }
                //チャージ単価（前年比）をセット                 
                nipo.setChargeTankaZenHi(zenChargeTankahi);
                // チャージ単価（前年比）クラス設定                   
                if (nipo.getChargeTankaZenHi().compareTo( ritu) < 0 ) {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setChargeTankaZenHiClass( bodyHirituClassM);
                    } else {
                        nipo.setChargeTankaZenHiClass( bodyHirituClass );
                    }
                    
                } else {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setChargeTankaZenHiClass( bodyNumClassN );
                    } else {
                        nipo.setChargeTankaZenHiClass( bodyNumClass );
                    }
                }
                //決済金額（売上比）チェック
                if ( uriHiKessaiKin.compareTo(bigZero) != 1 ) {
                    uriHiKessaiKin = new BigDecimal("0.00");
                }
                //決済金額（売上比）をセット                      
                nipo.setKessaiKinUrihi(uriHiKessaiKin);
                // 決済金額（売上比）クラス設定
                if (nipo.getKessaiKinUrihi().compareTo( ritu) < 0 ) {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setKessaiKinUrihiClass( bodyHirituClassM);
                    } else {
                        nipo.setKessaiKinUrihiClass( bodyHirituClass );
                    }
                    
                } else {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setKessaiKinUrihiClass( bodyNumClassN );
                    } else {
                        nipo.setKessaiKinUrihiClass( bodyNumClass );
                    }
                }
                //決済金額（前年比）チェック
                if ( zenHiKessaiKin.compareTo(bigZero) != 1 ) {
                    zenHiKessaiKin = new BigDecimal("0.00");
                }
                //決済金額（前年比）をセット                
                nipo.setKessaiKinZenhi(zenHiKessaiKin);
                // 決済金額（前年比）クラス設定
                if (nipo.getKessaiKinZenhi().compareTo( ritu) < 0 ) {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setKessaiKinZenhiClass( bodyHirituClassM);
                    } else {
                        nipo.setKessaiKinZenhiClass( bodyHirituClass );
                    }
                    
                } else {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setKessaiKinZenhiClass( bodyNumClassN );
                    } else {
                        nipo.setKessaiKinZenhiClass( bodyNumClass );
                    }
                }
                //決済件数（客数比）チェック
                if ( kyakuHiKessaiKen.compareTo(bigZero) != 1 ) {
                    kyakuHiKessaiKen = new BigDecimal("0.00");
                }
                //決済件数（客数比）をセット                         
                nipo.setKessaiKenKyakuhi(kyakuHiKessaiKen);
                // 決済件数（客数比）クラス設定
                if (nipo.getKessaiKenKyakuhi().compareTo( ritu) < 0 ) {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setKessaiKenKyakuhiClass( bodyHirituClassM);
                    } else {
                        nipo.setKessaiKenKyakuhiClass( bodyHirituClass );
                    }
                    
                } else {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setKessaiKenKyakuhiClass( bodyNumClassN );
                    } else {
                        nipo.setKessaiKenKyakuhiClass( bodyNumClass );
                    }
                }
                //決済件数（前年比）チェック
                if ( zenHiKessaiKen.compareTo(bigZero) != 1 ) {
                    zenHiKessaiKen = new BigDecimal("0.00");
                }
                //決済件数（前年比）をセット    
                nipo.setKessaiKenZenhi(zenHiKessaiKen);
                // 決済件数（前年比）クラス設定
                if (nipo.getKessaiKenZenhi().compareTo( ritu) < 0 ) {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setKessaiKenZenhiClass( bodyHirituClassM);
                    } else {
                        nipo.setKessaiKenZenhiClass( bodyHirituClass );
                    }
                    
                } else {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setKessaiKenZenhiClass( bodyNumClassN );
                    } else {
                        nipo.setKessaiKenZenhiClass( bodyNumClass );
                    }
                }
                //決済単価をセット
                nipo.setKessaiTanka(tankaKessaiKin);
                // 決済単価クラス設定
                if (nipo.getKessaiTanka().compareTo( ritu) < 0 ) {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setｋessaiTankaClass( bodyHirituClassM);
                    } else {
                        nipo.setｋessaiTankaClass( bodyHirituClass );
                    }
                    
                } else {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setｋessaiTankaClass( bodyNumClassN );
                    } else {
                        nipo.setｋessaiTankaClass( bodyNumClass );
                    }
                    
                }
                //前年決済単価をセット
                nipo.setZenKessaiTanka(zenTankaKessaiKin);
                
                //決済単価（単価比）チェック
                if ( tankaHiKessaiKin.compareTo(bigZero) != 1 ) {
                    tankaHiKessaiKin = new BigDecimal("0.00");
                }
                // 決済単価（単価比）をセット
                nipo.setKessaiTankaHi(tankaHiKessaiKin);
                //決済単価（単価比）クラスクラス設定                
                if (nipo.getKessaiTankaHi().compareTo( ritu) < 0 ) {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setKessaiTankaHiClass( bodyHirituClassM);
                    } else {
                        nipo.setKessaiTankaHiClass( bodyHirituClass );
                    }
                    
                } else {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setKessaiTankaHiClass( bodyNumClassN );
                    } else {
                        nipo.setKessaiTankaHiClass( bodyNumClass );
                    }
                }
                //決済単価（前年比）チェック
                if ( zenTankaHiKessaiKin.compareTo(bigZero) != 1 ) {
                    zenTankaHiKessaiKin = new BigDecimal("0.00");
                }
                // 決済単価（前年比）をセット                
                nipo.setKessaiTankaZenhi(zenTankaHiKessaiKin);
                // 決済単価（前年比）クラスクラス設定                
                if (nipo.getKessaiTankaZenhi().compareTo( ritu) < 0 ) {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setKessaiTankaZenhiClass( bodyHirituClassM);
                    } else {
                        nipo.setKessaiTankaZenhiClass( bodyHirituClass );
                    }
                    
                } else {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setKessaiTankaZenhiClass( bodyNumClassN );
                    } else {
                        nipo.setKessaiTankaZenhiClass( bodyNumClass );
                    }
                }
                //前年チャージ単価チェック
                if ( zenChargeTanka.compareTo(bigZero) != 1 ) {
                    zenChargeTankahi = new BigDecimal("0");
                }
                // 前年チャージ単価をセット                  
                nipo.setZenChargeTanka(zenChargeTanka);
                
                // 前年売上比率クラス設定
                BigDecimal zenhiUriRitu = Calculator.percentage( nipo.getUriage(),nipo.getUriageZen(),2);
                // 前年売上比率チェック
                if ( zenhiUriRitu.compareTo(bigZero) != 1 ) {
                    zenhiUriRitu = new BigDecimal("0.00");
                }
                // 前年売上比率をセット   
                nipo.setZenHiUri( zenhiUriRitu );
                
                // 前年客数比率クラス設定
                BigDecimal zenhiKyakuRitu = Calculator.percentage( nipo.getKyakusu(),nipo.getKyakusuZen(),2 ); 
                // 前年客数比率チェック
                if ( zenhiKyakuRitu.compareTo(bigZero) != 1 ) {
                    zenhiKyakuRitu = new BigDecimal("0.00");
                }
                //前年客数比率をセット
                nipo.setZenHiKyaku( zenhiKyakuRitu );
                
                // 前年単価比率計算
                BigDecimal zenhiTankaRitu = Calculator.percentage( nipo.getTanka(),nipo.getTankaZen(),2 );
                // 前年単価比率チェック
                if ( zenhiTankaRitu.compareTo(bigZero) != 1 ) {
                    zenhiTankaRitu = new BigDecimal("0.00");
                }
                // 前年単価をセット
                nipo.setZenHiTanka( zenhiTankaRitu );
                
            }
        }
        
        return paramList;
        
    }
    
    /**
     * 仮店番のキー情報
     * @return
     */
    private List getKeyList() {
        
        List keyList = new ArrayList();
        
        keyList.add("A");
        keyList.add("B");
        keyList.add("C");
        keyList.add("D");
        keyList.add("E");
        keyList.add("F");
        keyList.add("G");
        keyList.add("H");
        keyList.add("I");
        keyList.add("J");
        keyList.add("K");
        keyList.add("L");
        keyList.add("M");
        keyList.add("N");
        keyList.add("O");
        keyList.add("P");
        keyList.add("Q");
        keyList.add("R");
        keyList.add("S");
        keyList.add("T");
        keyList.add("U");
        keyList.add("V");
        keyList.add("W");
        keyList.add("X");
        keyList.add("Y");
        keyList.add("Z");       
        
        return keyList;

    }
}
