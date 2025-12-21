package jp.co.isid.mos.bird.bizreport.groupeigyoniporef.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.code.NipoZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.common.code.ShukeiKbn;
import jp.co.isid.mos.bird.bizreport.common.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.common.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.common.NipoRefConstants;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.common.EigyoNipoConstants;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.dao.TrnUriageGepoInfoDao;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.dao.TrnUriageNipoInfoDao;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.dao.TrnYosanInfoDao;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.entity.TrnUriageNipoInfo;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.entity.TrnYosanInfo;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.logic.UriageNipoInfoLogic;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.dao.MstSibuDao;
import jp.co.isid.mos.bird.common.entity.MstSibu;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.util.Calculator;

/**
 *
 *
 * @author   xkhata
 * @modifier xkinu 2007/06/05 T.Kinugawa(ASPAC) クローズ店予算表示対応
 * @modifier xkinu 2008/02/29 T.Kinugawa(ASPAC) PK-010102-003.01 総合営業日報検索機能追加 欠陥一覧.xls　欠陥No.1対応
 *
 */
public class UriageNipoInfoLogicImpl implements UriageNipoInfoLogic {

    public static final String LOGIC_ID = "BBR001L06";

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

            /** ２．直営店を含まない(支部コードでの検索)の時    **********************************/
            if ( areaDaiFlg.equals( ShukeiKbn.OUT_RC )) {
                List totalList = createTotalList("TOTAL", companyCd);
            	if(resultList.size() > 0) {
            		underTotalList = createTotalList( resultList ,kbnInfo);
                    totalList = createTotalList( resultList, "TOTAL");
            	}

                //2.店舗種別が『全店』or 『新店』の場合。
                if ( tenpoShu.equals( TenpoShubetu.CODE_ALL) || tenpoShu.equals( TenpoShubetu.CODE_SIN ) ) {
                	//2-1. [[X店番予算]]を取得
                    List xYosanList = new ArrayList();
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
                    BigDecimal xYosan = new BigDecimal(0);

                    if ( xYosanList != null && xYosanList.size() != 0) {
                        TrnYosanInfo tyi = (TrnYosanInfo)xYosanList.get(0);
                        xYosan = tyi.getYosan();
                    }

                    List sibuNameList = getMstSibuDao().getSibu( companyCd,sibuCd );
                    MstSibu mSib = (MstSibu)sibuNameList.get(0);

                    totalList.add(0, createYosanKeiInfo( mSib.getSibuCd().trim(), mSib.getSibuName().trim(), xYosan ));

                    TrnUriageNipoInfo total = (TrnUriageNipoInfo)totalList.get( totalList.size() - 1 );
                    total.setYosan( total.getYosan().add( xYosan ) );
                }
                //3.店舗種別が『前年対象店』or 『予算対象店』の場合。
                else {
// 2008/02/29 ADD start T.Kinugawa(ASPAC) PK-010102-003.01 総合営業日報検索機能追加 欠陥一覧.xls　欠陥No.1対応
                    if ( !dataShu.equals( NipoZennenDataShubetu.CODE_HOSEI ) ) {
// 2008/02/29 ADD end T.Kinugawa(ASPAC) PK-010102-003.01 総合営業日報検索機能追加 欠陥一覧.xls　欠陥No.1対応
                	//[[営業日報情報]]に結果件数が0件でデータが存在しない場合はException発生。
                    if ( resultList == null || resultList.size() == 0 ) {
                        throw new NoResultException("");
                    }
// 2008/02/29 ADD start T.Kinugawa(ASPAC) PK-010102-003.01 総合営業日報検索機能追加 欠陥一覧.xls　欠陥No.1対応
                    }
// 2008/02/29 ADD end T.Kinugawa(ASPAC) PK-010102-003.01 総合営業日報検索機能追加 欠陥一覧.xls　欠陥No.1対応
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
// 2008/02/29 ADD start T.Kinugawa(ASPAC) PK-010102-003.01 総合営業日報検索機能追加 欠陥一覧.xls　欠陥No.1対応
                if ( !dataShu.equals( NipoZennenDataShubetu.CODE_HOSEI ) ) {
// 2008/02/29 ADD end T.Kinugawa(ASPAC) PK-010102-003.01 総合営業日報検索機能追加 欠陥一覧.xls　欠陥No.1対応
                	//1．[[営業日報情報]]に結果件数が0件でデータが存在しない場合はException発生。
	                if ( resultList == null || resultList.size() == 0 ) {
	                    throw new NoResultException("");
	                }
// 2008/02/29 ADD start T.Kinugawa(ASPAC) PK-010102-003.01 総合営業日報検索機能追加 欠陥一覧.xls　欠陥No.1対応
                }
// 2008/02/29 ADD end T.Kinugawa(ASPAC) PK-010102-003.01 総合営業日報検索機能追加 欠陥一覧.xls　欠陥No.1対応
            }
            //売上値小数桁数取得
            int digitCnt = ((Integer)paramMap.get( EigyoNipoConstants.MAP_DIGIT_CNT )).intValue();
            resultList = createSortList( resultList, digitCnt);

            resultMap = createSeparateList( resultList );

            // 前年データ種別が売上補正のとき(取得するデータは前年同月のデータ)
            if ( dataShu.equals( NipoZennenDataShubetu.CODE_HOSEI ) ) {
                List uriageHoseiList = (List)resultMap.get("uriage");
                List kyakusuHoseiList = (List)resultMap.get("kyakusu");

                dataShu = NipoZennenDataShubetu.CODE_DOGETU;

                paramMap.put(DATASHU,dataShu);

                List dogetuList = getNipoInfo( paramMap );
// 2008/02/29 ADD start T.Kinugawa(ASPAC) PK-010102-003.01 総合営業日報検索機能追加 欠陥一覧.xls　欠陥No.1対応
            	//前年同月の[[営業日報情報]]に結果件数が0件でデータが存在しない場合はException発生。
                if ( dogetuList == null || dogetuList.size() == 0 ) {
                    throw new NoResultException("");
                }
// 2008/02/29 ADD start T.Kinugawa(ASPAC) PK-010102-003.01 総合営業日報検索機能追加 欠陥一覧.xls　欠陥No.1対応
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

                dogetuList = createSortList( dogetuList, digitCnt);
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
            uriageList = createSortList( uriageList, 0);

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
     * @param map
     * @return
     */
    private Map getHoseiList( Map map) {
        List uriageHoseiList = (List)map.get("uriageHosei");
        List kyakusuHoseiList = (List)map.get("kyakusuHosei");
        List uriageDogetuList = (List)map.get("uriageDogetu");
        List kyakusuDogetuList = (List)map.get("kyakusuDogetu");

        List uriageList = new ArrayList();

        // 売上のデータ作成
        for( int j = 0; j < uriageDogetuList.size(); j++ ) {
            TrnUriageNipoInfo uriDou = (TrnUriageNipoInfo)uriageDogetuList.get(j);
            TrnUriageNipoInfo uriage = new TrnUriageNipoInfo();

            // 前年売上のBigDecimal
            BigDecimal uriageZen = new BigDecimal(0);
            // 前年値引のBigDecimal add 2019/04/18 xou.zoubun 前年値引対応
            BigDecimal nebikiZen = new BigDecimal(0);
            // 前年売上(値引後)のBigDecimal add 2019/04/18 xou.zoubun 前年値引対応
            BigDecimal uriageZenAfterNebiki = new BigDecimal(0);

            String zenUriClass = new String();
            // 合計行のときと通常行（店の行)のときのクラスが違うため、どのクラスか判断する
            if ( uriDou.getRClass().equals( noClass ) ) {
                zenUriClass = bodyHirituClassM;
            } else {
                zenUriClass = bodyHirituClass;
            }

            String zenAfterNebikiUriClass = new String();
            // 合計行のときと通常行（店の行)のときのクラスが違うため、どのクラスか判断する
            if ( uriDou.getRClass().equals( noClass ) ) {
                zenAfterNebikiUriClass = bodyHirituClassM;
            } else {
                zenAfterNebikiUriClass = bodyHirituClass;
            }

            // 前年比(売上)のBigDecimal
            BigDecimal zenHiUri = new BigDecimal(0.00);
            // 前年比(売上)(値引後)のBigDecimal  add 2019/04/18 xou.zoubun 前年値引対応
            BigDecimal zenHiUriAfterNebiki = new BigDecimal(0.00);

            // 対象の売上補正のデータを探す
            for( int i = 0; i < uriageHoseiList.size(); i++ ) {
                TrnUriageNipoInfo uriHose = (TrnUriageNipoInfo)uriageHoseiList.get(i);

                if ( uriDou.getCompanyCd().equals( uriHose.getCompanyCd() )
                        && uriDou.getMiseCd().equals( uriHose.getMiseCd() )
                        && uriDou.getSibuCd().equals( uriHose.getSibuCd() )) {
                    uriageZen = uriHose.getUriageZen();
                    zenUriClass = uriHose.getZenUriClass();
                    zenHiUri = uriHose.getZenHiUri();
                    nebikiZen = uriHose.getNebikiZen(); //add 2019/04/18 xou.zoubun 前年値引対応
                    uriageZenAfterNebiki = uriHose.getUriageZenAfterNebiki(); //add 2019/04/18 xou.zoubun 前年値引対応
                    zenHiUriAfterNebiki = uriHose.getZenHiUriAfterNebiki(); //add 2019/04/18 xou.zoubun 前年値引対応
                    zenAfterNebikiUriClass = uriHose.getZenAfterNebikiUriClass(); //add 2019/06/20 xou.zoubun 前年値引対応
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
            //begin add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
            uriage.setNebiki( uriDou.getNebiki() );
            uriage.setUriageAfterNebiki( uriDou.getUriageAfterNebiki() );
            uriage.setTasseiAfterNebiki( uriDou.getTasseiAfterNebiki() );
            //begin add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
            uriage.setYosan( uriDou.getYosan() );
            uriage.setTenkoKbn( uriDou.getTenkoKbn() );
            uriage.setTenkoKbnZen( uriDou.getTenkoKbnZen() );
            uriage.setEigyoDays( uriDou.getEigyoDays() );
            uriage.setEigyoDaysZen( uriDou.getEigyoDaysZen() );

            uriage.setTasseiUriClass( uriDou.getTasseiUriClass() );
            uriage.setTassei( uriDou.getTassei() );
            uriage.setUriageZen( uriageZen );
            uriage.setZenUriClass( zenUriClass );
            uriage.setZenHiUri( zenHiUri );

            //begin add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
            uriage.setTasseiAfterNebikiUriClass( uriDou.getTasseiAfterNebikiUriClass() );
            uriage.setNebikiZen( nebikiZen );
            uriage.setUriageZenAfterNebiki( uriageZenAfterNebiki );
            uriage.setZenHiUriAfterNebiki( zenHiUriAfterNebiki );
            uriage.setZenAfterNebikiUriClass( zenAfterNebikiUriClass );
            //begin add 2019/04/18 xou.zoubun モスダイニング支部時、値引表示対応

            uriageList.add( uriage);

        }

        List kyakusuList = new ArrayList();

        // 客数のデータ作成
        for( int j = 0; j < kyakusuDogetuList.size(); j++ ) {
            TrnUriageNipoInfo kyaDou = (TrnUriageNipoInfo)kyakusuDogetuList.get(j);
            TrnUriageNipoInfo kyaku = new TrnUriageNipoInfo();

            BigDecimal kyakuZen = new BigDecimal(0);
            String zenKyaClass = new String();
            String zenTankaClass = new String();

            // 合計行と通常行(店行)で試用するクラスが異なるため、どちらかを特定する
            if ( kyaDou.getRClass().equals( noClass ) ) {
                zenKyaClass = bodyHirituClassM;
                zenTankaClass = bodyHirituClassM;
            } else {
                zenKyaClass = bodyHirituClass;
                zenTankaClass = bodyHirituClass;
            }

            BigDecimal zenHiKyaku = new BigDecimal(0.00);
            BigDecimal tankaZen = new BigDecimal(0);
            BigDecimal zenHiTanka = new BigDecimal(0.00);

            // 対象の客数補正のデータを探す
            for( int i = 0; i < kyakusuHoseiList.size(); i++ ) {
                TrnUriageNipoInfo kyaHose = (TrnUriageNipoInfo)kyakusuHoseiList.get(i);

                if ( kyaDou.getCompanyCd().equals( kyaHose.getCompanyCd() )
                      && kyaDou.getMiseCd().equals( kyaHose.getMiseCd() )
                      && kyaDou.getSibuCd().equals( kyaHose.getSibuCd() ) ) {
                    kyakuZen = kyaHose.getKyakusuZen();
                    zenKyaClass = kyaHose.getZenKyaClass();
                    zenHiKyaku = kyaHose.getZenHiKyaku();
                    tankaZen = kyaHose.getTankaZen();
                    zenTankaClass = kyaHose.getZenTanClass();
                    zenHiTanka = kyaHose.getZenHiTanka();
                    break;
                }
            }

            // trのクラスは前年同月のクラスに合わせる
            kyaku.setRClass( kyaDou.getRClass() );
            kyaku.setCompanyCd( kyaDou.getCompanyCd() );
            kyaku.setBlockCd( kyaDou.getBlockCd() );
            kyaku.setBlockName( kyaDou.getBlockName() );
            kyaku.setEigyoDays( kyaDou.getEigyoDays() );
            kyaku.setEigyoDaysZen( kyaDou.getEigyoDaysZen() );
            kyaku.setHonbuCd( kyaDou.getHonbuCd() );
            kyaku.setHonbuName( kyaDou.getHonbuName() );
            kyaku.setJigyoCd( kyaDou.getJigyoCd() );
            kyaku.setSibuCd( kyaDou.getSibuCd() );
            kyaku.setSibuName( kyaDou.getSibuName() );
            kyaku.setJigyoName( kyaDou.getJigyoName()) ;
            kyaku.setMiseCd( kyaDou.getMiseCd() );
            kyaku.setMiseNameKj( kyaDou.getMiseNameKj() );
            kyaku.setKbn1( kyaDou.getKbn1() );
            kyaku.setKyakusu( kyaDou.getKyakusu() );
            kyaku.setTanka( kyaDou.getTanka() );
            kyaku.setEigyoDays( kyaDou.getEigyoDays() );
            kyaku.setEigyoDaysZen( kyaDou.getEigyoDaysZen() );
            kyaku.setTenkoKbn( kyaDou.getTenkoKbn() );
            kyaku.setTenkoKbnZen( kyaDou.getTenkoKbnZen());

            kyaku.setKyakusuZen( kyakuZen );
            kyaku.setZenKyaClass( zenKyaClass );
            kyaku.setZenHiKyaku( zenHiKyaku );
            kyaku.setTankaZen( tankaZen );
            kyaku.setZenTanClass( zenTankaClass );
            kyaku.setZenHiTanka( zenHiTanka );

            kyakusuList.add( kyaku );

        }

        map.put("uriage",uriageList);
        map.put("kyakusu",kyakusuList);

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
        String svCd   = (String) paramMap.get(EigyoNipoConstants.SV_CD);

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
// add start xkhata 期報時の予算取得変更
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
// add end
        }

        for ( int i = 0; i < resultList.size(); i++ ) {
            TrnUriageNipoInfo nipo = (TrnUriageNipoInfo)resultList.get(i);
            nipo.setRClass( noClass );
        }

        return resultList;

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
    /**
     * 売上リスト、客数リストを作成
     * @param resultList
     * @return
     */
    private Map createSeparateList (List resultList ) {

        Map resultMap = new HashMap();

        List uriageList = new ArrayList();
        List kyakusuList = new ArrayList();

        for (int i = 0; i < resultList.size();i++ ) {
            TrnUriageNipoInfo uriage = new TrnUriageNipoInfo();
            TrnUriageNipoInfo kyakusu = new TrnUriageNipoInfo();
            TrnUriageNipoInfo result = (TrnUriageNipoInfo)resultList.get(i);

            uriage.setCompanyCd( result.getCompanyCd() );
            kyakusu.setCompanyCd( result.getCompanyCd() );
            uriage.setRClass( result.getRClass() );
            kyakusu.setRClass( result.getRClass() );
            uriage.setHonbuCd( result.getHonbuCd() );
            kyakusu.setHonbuCd( result.getHonbuCd() );
            uriage.setHonbuName( result.getHonbuName() );
            kyakusu.setHonbuName( result.getHonbuName() );
            uriage.setJigyoCd( result.getJigyoCd() );
            kyakusu.setJigyoCd( result.getJigyoName() );
            uriage.setJigyoName( result.getJigyoName() );
            kyakusu.setJigyoName( result.getJigyoName() );
            uriage.setSibuCd( result.getSibuCd() );
            kyakusu.setSibuCd( result.getSibuCd() );
            uriage.setSibuName( result.getSibuName() );
            kyakusu.setSibuName( result.getSibuName() );
            uriage.setBlockCd( result.getBlockCd() );
            kyakusu.setBlockCd( result.getBlockCd() );
            uriage.setBlockName( result.getBlockName() );
            kyakusu.setBlockName( result.getBlockName() );
            uriage.setMiseCd( result.getMiseCd() );
            kyakusu.setMiseCd( result.getMiseCd() );
            uriage.setMiseNameKj( result.getMiseNameKj() );
            kyakusu.setMiseNameKj( result.getMiseNameKj() );
            uriage.setKbn1( result.getKbn1() );
            kyakusu.setKbn1( result.getKbn1() );
            uriage.setTenkoKbn( result.getTenkoKbn() );
            kyakusu.setTenkoKbn( result.getTenkoKbn() );
            uriage.setTenkoKbnZen( result.getTenkoKbnZen() );
            kyakusu.setTenkoKbnZen( result.getTenkoKbnZen() );
            uriage.setEigyoDays( result.getEigyoDays() );
            kyakusu.setEigyoDays( result.getEigyoDays() );
            uriage.setEigyoDaysZen( result.getEigyoDaysZen() );
            kyakusu.setEigyoDaysZen( result.getEigyoDaysZen() );

            uriage.setUriage( result.getUriage() );
            //begin add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
            uriage.setNebiki( result.getNebiki() );
            uriage.setUriageAfterNebiki( result.getUriageAfterNebiki() );
            uriage.setTasseiAfterNebiki( result.getTasseiAfterNebiki() );
            //begin add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
            uriage.setYosan( result.getYosan() );
            uriage.setTasseiUriClass( result.getTasseiUriClass() );
            uriage.setTassei( result.getTassei() );
            uriage.setUriageZen( result.getUriageZen() );
            uriage.setZenUriClass( result.getZenUriClass() );
            uriage.setZenHiUri( result.getZenHiUri() );
            //begin add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
            uriage.setTasseiAfterNebikiUriClass( result.getTasseiAfterNebikiUriClass() );
            uriage.setNebikiZen( result.getNebikiZen() );
            uriage.setUriageZenAfterNebiki( result.getUriageZenAfterNebiki() );
            uriage.setZenHiUriAfterNebiki( result.getZenHiUriAfterNebiki() );
            uriage.setZenAfterNebikiUriClass( result.getZenAfterNebikiUriClass() );
            //begin add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応

            kyakusu.setKyakusu( result.getKyakusu() );
            kyakusu.setKyakusuZen( result.getKyakusuZen() );
            kyakusu.setZenKyaClass( result.getZenKyaClass() );
            kyakusu.setZenHiKyaku( result.getZenHiKyaku() );
            kyakusu.setTanka( result.getTanka() );
            kyakusu.setTankaZen( result.getTankaZen() );

            kyakusu.setZenTanClass( result.getZenTanClass() );
            kyakusu.setZenHiTanka( result.getZenHiTanka() );

            uriageList.add( uriage );

            if ( !result.getRClass().equals( NipoRefConstants.CSS_TR_CLASS_SEGMENT ) ) {
                kyakusuList.add( kyakusu );
            }
        }

        resultMap.put("uriage",uriageList );
        resultMap.put("kyakusu",kyakusuList );
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
     * 新店予定予算計
     * @param totalYosan
     * @param sibuYosan
     * @return
     */
    private TrnUriageNipoInfo createYosanKeiInfo(String sibuCd, String sibuName, BigDecimal xYosan) {
        TrnUriageNipoInfo uriage = new TrnUriageNipoInfo();

        uriage.setSibuCd( sibuCd );
        uriage.setSibuName( sibuName );
//        uriage.setBlockName("新店予定予算計");
        uriage.setBlockName("新店・その他予算計");
        uriage.setRClass( NipoRefConstants.CSS_TR_CLASS_SEGMENT );
        uriage.setYosan( xYosan );

        return uriage;
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
        //begin add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
        BigDecimal nebiki = uriNipo.getNebiki();
        BigDecimal uriageAfterNebiki = uriNipo.getUriageAfterNebiki();
        //end add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
        //begin add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
        BigDecimal nebikiZen = uriNipo.getNebikiZen();
        BigDecimal uriageZenAfterNebiki = uriNipo.getUriageZenAfterNebiki();
        //end add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
        BigDecimal kyakusu = uriNipo.getKyakusu();
        BigDecimal yosan = uriNipo.getYosan();
        BigDecimal uriageZen = uriNipo.getUriageZen();
        BigDecimal kyakusuZen = uriNipo.getKyakusuZen();
        BigDecimal eigyoCnt = uriNipo.getEigyoDays();
        BigDecimal eigyoZenCnt = uriNipo.getEigyoDaysZen();

        List resultList = new ArrayList();

        TrnUriageNipoInfo uriNipoInfo = new TrnUriageNipoInfo();
        TrnUriageNipoInfo uriNipoInfoBef = new TrnUriageNipoInfo();

        String curCd = new String();
        String curName = new String();
        String befCd = new String();
        String befName = new String();
        String curCdUpper = new String();
//        String curNameUpper = new String();
        String befCdUpper = new String();
//        String befNameUpper = new String();
        String rclass = new String();

        uriNipoInfo = uriNipo;

        // サイズが１のときに使用する
        if ( kbnInfo.equals("BLOCK" ) ) {
            curCd = uriNipo.getBlockCd();
            curName = uriNipo.getBlockName().trim();
            curCdUpper = uriNipo.getSibuCd();
//            curNameUpper = uriNipo.getSibuName().trim();
            rclass = NipoRefConstants.CSS_TR_CLASS_SLAREA;
        } else if  (kbnInfo.equals("TOTAL" ) ) {
            curCd = uriNipo.getCompanyCd();
            curName = "支部合";
            curCdUpper = uriNipo.getCompanyCd();
            rclass = NipoRefConstants.CSS_TR_CLASS_TOTAL;
        } else if ( kbnInfo.equals("ONER") ) {
            curCd = uriNipo.getCompanyCd();
            curName = "総合";
            curCdUpper = uriNipo.getCompanyCd();
            rclass = NipoRefConstants.CSS_TR_CLASS_TOTAL;
        }
/* 20081209追加 SV対応 start ----------------------------*/
        else if  (kbnInfo.equals("SV_SIBU_TOTAL" ) ) {
            curCd      = uriNipo.getCompanyCd();
            curName    = "支部合";
            curCdUpper = uriNipo.getCompanyCd();
            rclass     = NipoRefConstants.CSS_TR_CLASS_HONBU;
        }else if  (kbnInfo.equals("SV_TOTAL" ) ) {
            curCd      = uriNipo.getCompanyCd();
            curName    = "総合";
            curCdUpper = uriNipo.getCompanyCd();
            rclass     = NipoRefConstants.CSS_TR_CLASS_TOTAL;
        }
/* 20081209追加 End -------------------------------------*/

        for ( int i = 1; i < sortList.size(); i++ ) {
            uriNipoInfo = (TrnUriageNipoInfo)sortList.get(i);
            uriNipoInfoBef = (TrnUriageNipoInfo)sortList.get(i - 1);
            if ( kbnInfo.equals("BLOCK" ) ) {
                curCd = uriNipoInfo.getBlockCd();
                curName = uriNipoInfo.getBlockName().trim();
                curCdUpper = uriNipoInfo.getSibuCd();
//                curNameUpper = uriNipoInfo.getSibuName().trim();
                befCd = uriNipoInfoBef.getBlockCd();
                befName = uriNipoInfoBef.getBlockName().trim();
                befCdUpper = uriNipoInfoBef.getSibuCd();
//                befNameUpper = uriNipoInfoBef.getSibuName();
                rclass = NipoRefConstants.CSS_TR_CLASS_SLAREA;
            } else if  (kbnInfo.equals("TOTAL" ) ) {
                curCd = uriNipoInfo.getCompanyCd();
                curName = "支部合";
                curCdUpper = uriNipoInfo.getCompanyCd();
                befCd = uriNipoInfoBef.getCompanyCd();
                befName = "支部合";
                befCdUpper = uriNipoInfoBef.getCompanyCd();
                rclass = NipoRefConstants.CSS_TR_CLASS_TOTAL;
            } else if ( kbnInfo.equals( "SIBU" ) ) {
                curCd = uriNipoInfo.getSibuCd();
                curName = uriNipoInfo.getSibuName().trim();
                curCdUpper = uriNipoInfo.getCompanyCd();
                befCd = uriNipoInfoBef.getSibuCd();
                befName = uriNipoInfoBef.getSibuName().trim();
                befCdUpper = uriNipoInfoBef.getCompanyCd();
                rclass = NipoRefConstants.CSS_TR_CLASS_TOTAL;
            } else if (kbnInfo.equals("ONER" ) ) {
                curCd = uriNipoInfo.getCompanyCd();
                curName = "総合";
                befCd = uriNipoInfoBef.getCompanyCd();
                befName = "総合";
                befCdUpper = uriNipoInfoBef.getCompanyCd();
                rclass = NipoRefConstants.CSS_TR_CLASS_TOTAL;
            }
/* 20081209追加 SV対応 start ----------------------------*/
            else if  (kbnInfo.equals("SV_SIBU_TOTAL" ) ) {
                curCd = uriNipoInfo.getCompanyCd();
                curName    = "支部合";
                curCdUpper = uriNipoInfo.getCompanyCd();
                befCd      = uriNipoInfoBef.getCompanyCd();
                befName    = "支部合";
                befCdUpper = uriNipoInfoBef.getCompanyCd();
                rclass     = NipoRefConstants.CSS_TR_CLASS_HONBU;
            }else if  (kbnInfo.equals("SV_TOTAL" ) ) {
                curCd      = uriNipoInfo.getCompanyCd();
                curName    = "総合";
                curCdUpper = uriNipoInfo.getCompanyCd();
                befCd      = uriNipoInfoBef.getCompanyCd();
                befName    = "総合";
                befCdUpper = uriNipoInfoBef.getCompanyCd();
                rclass     = NipoRefConstants.CSS_TR_CLASS_TOTAL;
            }
    /* 20081209追加 End -------------------------------------*/

            if ( (curCdUpper.equals( befCdUpper ) && curCd.equals( befCd )) ) {
                uriage = uriage.add( uriNipoInfo.getUriage() );
                //begin add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
                if (!kbnInfo.equals("ONER" ) ) {
                    nebiki = nebiki.add(uriNipoInfo.getNebiki());
                    uriageAfterNebiki = uriageAfterNebiki.add(uriNipoInfo.getUriageAfterNebiki());
                    //begin add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
                    nebikiZen = nebikiZen.add(uriNipoInfo.getNebikiZen());
                    uriageZenAfterNebiki = uriageZenAfterNebiki.add(uriNipoInfo.getUriageZenAfterNebiki());
                    //end add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
                }
                //end add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
                kyakusu = kyakusu.add( uriNipoInfo.getKyakusu() );
                yosan = yosan.add( uriNipoInfo.getYosan() );
                uriageZen = uriageZen.add( uriNipoInfo.getUriageZen() );
                kyakusuZen = kyakusuZen.add( uriNipoInfo.getKyakusuZen() );
                eigyoCnt = eigyoCnt.add( uriNipoInfo.getEigyoDays() );
                eigyoZenCnt = eigyoZenCnt.add( uriNipoInfo.getEigyoDaysZen() );
            } else {
                TrnUriageNipoInfo totalUri = new TrnUriageNipoInfo();
                totalUri.setCompanyCd( uriNipoInfoBef.getCompanyCd() );
                totalUri.setRClass( rclass );
                totalUri.setSibuCd( uriNipoInfoBef.getSibuCd() );
                totalUri.setSibuName( uriNipoInfoBef.getSibuName() );
                totalUri.setBlockCd( befCd );
                totalUri.setBlockName( befName + "計" );
                totalUri.setMiseCd( befCd );
                totalUri.setMiseNameKj( befName );
                totalUri.setHonbuCd( uriNipoInfoBef.getHonbuCd() );
                totalUri.setHonbuName( uriNipoInfoBef.getHonbuName() );
                totalUri.setJigyoCd( uriNipoInfoBef.getJigyoCd() );
                totalUri.setJigyoName( uriNipoInfoBef.getJigyoName() );
                totalUri.setSlareaCd( uriNipoInfoBef.getSlareaCd() );
                totalUri.setSlareaName( uriNipoInfoBef.getJigyoName() );
                totalUri.setUriage( uriage );
                //begin add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
                totalUri.setNebiki(nebiki);
                totalUri.setUriageAfterNebiki(uriageAfterNebiki);
                //end add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
                //begin add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
                totalUri.setNebikiZen(nebikiZen);
                totalUri.setUriageZenAfterNebiki(uriageZenAfterNebiki);
                //end add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
                totalUri.setKyakusu( kyakusu );
                totalUri.setYosan( yosan );
                totalUri.setKyakusuZen( kyakusuZen );
                totalUri.setUriageZen( uriageZen );
                totalUri.setEigyoDays( eigyoCnt );
                totalUri.setEigyoDaysZen( eigyoZenCnt );

                resultList.add( totalUri );

                uriage = uriNipoInfo.getUriage();
                //begin add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
                if (!kbnInfo.equals("ONER" ) ) {
                    nebiki = uriNipoInfo.getNebiki();
                    uriageAfterNebiki = uriNipoInfo.getUriageAfterNebiki();
                    //begin add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
                    nebikiZen = uriNipoInfo.getNebikiZen();
                    uriageZenAfterNebiki = uriNipoInfo.getUriageZenAfterNebiki();
                    //end add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
                }
                //end add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
                kyakusu = uriNipoInfo.getKyakusu();
                yosan = uriNipoInfo.getYosan();
                kyakusuZen = uriNipoInfo.getKyakusuZen();
                uriageZen = uriNipoInfo.getUriageZen();
                eigyoCnt = uriNipoInfo.getEigyoDays();
                eigyoZenCnt = uriNipoInfo.getEigyoDaysZen();
            }
        }
        TrnUriageNipoInfo totalUri = new TrnUriageNipoInfo();
        totalUri.setCompanyCd( uriNipoInfo.getCompanyCd() );
        totalUri.setRClass( rclass );
        totalUri.setSibuCd( uriNipoInfo.getSibuCd() );
        totalUri.setSibuName( uriNipoInfo.getSibuName() );
        totalUri.setBlockCd( curCd );
        totalUri.setBlockName( curName + "計" );
        totalUri.setMiseCd( curCd );
        totalUri.setMiseNameKj( curName );
        totalUri.setHonbuCd( uriNipoInfo.getHonbuCd() );
        totalUri.setHonbuName( uriNipoInfo.getHonbuName() );
        totalUri.setJigyoCd( uriNipoInfo.getJigyoCd() );
        totalUri.setJigyoName( uriNipoInfo.getJigyoName() );
        totalUri.setSlareaCd( uriNipoInfo.getSlareaCd() );
        totalUri.setSlareaName( uriNipoInfo.getJigyoName() );
        totalUri.setUriage( uriage );
        //begin add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
        totalUri.setNebiki(nebiki);
        totalUri.setUriageAfterNebiki(uriageAfterNebiki);
        //end add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
        //begin add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
        totalUri.setNebikiZen(nebikiZen);
        totalUri.setUriageZenAfterNebiki(uriageZenAfterNebiki);
        //end add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
        totalUri.setKyakusu( kyakusu );
        totalUri.setYosan( yosan );
        totalUri.setKyakusuZen( kyakusuZen );
        totalUri.setUriageZen( uriageZen );
        totalUri.setEigyoDays( eigyoCnt );
        totalUri.setEigyoDaysZen( eigyoZenCnt );

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
        //begin add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
        BigDecimal nebiki = new BigDecimal("0");
        BigDecimal uriageAfterNebiki = new BigDecimal("0");
        //end add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
        //begin add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
        BigDecimal nebikiZen = new BigDecimal("0");
        BigDecimal uriageZenAfterNebiki = new BigDecimal("0");
        //end add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
        BigDecimal kyakusu = new BigDecimal("0");
        BigDecimal yosan = new BigDecimal("0");
        BigDecimal uriageZen = new BigDecimal("0");
        BigDecimal kyakusuZen = new BigDecimal("0");
        BigDecimal eigyoCnt = new BigDecimal("0");
        BigDecimal eigyoZenCnt = new BigDecimal("0");

        List resultList = new ArrayList();

        TrnUriageNipoInfo uriNipoInfo = new TrnUriageNipoInfo();

        String curCd = new String();
        String curName = new String();
        String rclass = new String();

        // サイズが１のときに使用する
        if  (kbnInfo.equals("TOTAL" ) ) {
            curCd = companyCd;
            curName = "支部合";
            rclass = NipoRefConstants.CSS_TR_CLASS_TOTAL;
        }


        TrnUriageNipoInfo totalUri = new TrnUriageNipoInfo();
        totalUri.setCompanyCd( companyCd );
        totalUri.setRClass( rclass );
        totalUri.setSibuCd( uriNipoInfo.getSibuCd() );
        totalUri.setSibuName( uriNipoInfo.getSibuName() );
        totalUri.setBlockCd( curCd );
        totalUri.setBlockName( curName + "計" );
        totalUri.setMiseCd( curCd );
        totalUri.setMiseNameKj( curName );
        totalUri.setHonbuCd( uriNipoInfo.getHonbuCd() );
        totalUri.setHonbuName( uriNipoInfo.getHonbuName() );
        totalUri.setJigyoCd( uriNipoInfo.getJigyoCd() );
        totalUri.setJigyoName( uriNipoInfo.getJigyoName() );
        totalUri.setSlareaCd( uriNipoInfo.getSlareaCd() );
        totalUri.setSlareaName( uriNipoInfo.getJigyoName() );
        totalUri.setUriage( uriage );
        //begin add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
        totalUri.setNebiki( nebiki );
        totalUri.setUriageAfterNebiki( uriageAfterNebiki );
        //end add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
        //begin add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
        totalUri.setNebikiZen( nebikiZen );
        totalUri.setUriageZenAfterNebiki( uriageZenAfterNebiki );
        //end add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
        totalUri.setKyakusu( kyakusu );
        totalUri.setYosan( yosan );
        totalUri.setKyakusuZen( kyakusuZen );
        totalUri.setUriageZen( uriageZen );
        totalUri.setEigyoDays( eigyoCnt );
        totalUri.setEigyoDaysZen( eigyoZenCnt );

        resultList.add( totalUri );

        return resultList;
    }
    /**
     * 取得した情報のうち、計算によって求めるものをセットする
     * @param paramList
     * @param digitCnt　小数桁数 2013/02 海外売上集信対応
     * @return
     */
    private List createSortList(List paramList, int digitCnt ) {

        BigDecimal ritu = new BigDecimal(100);

        for ( int i = 0; i < paramList.size(); i++ ) {

            TrnUriageNipoInfo nipo = (TrnUriageNipoInfo)paramList.get(i);

            if ( !nipo.getRClass().equals( NipoRefConstants.CSS_TR_CLASS_SEGMENT ) ) {

                nipo.setTanka( Calculator.divide( nipo.getUriage(),nipo.getKyakusu(), digitCnt) );
                nipo.setTankaZen( Calculator.divide( nipo.getUriageZen(),nipo.getKyakusuZen(), digitCnt) );

                BigDecimal tasseiRitu = Calculator.percentage( nipo.getUriage(),nipo.getYosan(),2);

                if ( tasseiRitu.intValue() == 0 ||tasseiRitu.intValue()<0) {
                    tasseiRitu = new BigDecimal("0.00");
                }

                nipo.setTassei( tasseiRitu );

                //begin add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
                BigDecimal tasseiRituAfterNebiki = Calculator.percentage( nipo.getUriageAfterNebiki(),nipo.getYosan(),2);

                if ( tasseiRituAfterNebiki.intValue() == 0 ||tasseiRituAfterNebiki.intValue()<0) {
                	tasseiRituAfterNebiki = new BigDecimal("0.00");
                }

                nipo.setTasseiAfterNebiki( tasseiRituAfterNebiki );

                if (nipo.getTasseiAfterNebiki().compareTo( ritu) < 0 ) {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setTasseiAfterNebikiUriClass( bodyHirituClassM);
                    } else {
                        nipo.setTasseiAfterNebikiUriClass( bodyHirituClass );
                    }

                } else {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setTasseiAfterNebikiUriClass( bodyNumClassN );
                    } else {
                        nipo.setTasseiAfterNebikiUriClass( bodyNumClass );
                    }
                }
                //end add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応

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


                BigDecimal zenhiUriRitu = Calculator.percentage( nipo.getUriage(),nipo.getUriageZen(),2);

                if ( zenhiUriRitu.intValue() == 0 ) {
                    zenhiUriRitu = new BigDecimal("0.00");
                }
                nipo.setZenHiUri( zenhiUriRitu );

                //begin add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応
                BigDecimal zenhiUriRituAfterNebiki = Calculator.percentage( nipo.getUriageAfterNebiki(),nipo.getUriageZenAfterNebiki(),2);

                if ( zenhiUriRituAfterNebiki.intValue() == 0 ) {
                    zenhiUriRituAfterNebiki = new BigDecimal("0.00");
                }
                nipo.setZenHiUriAfterNebiki( zenhiUriRituAfterNebiki );

                if ( nipo.getZenHiUriAfterNebiki().compareTo( ritu ) < 0 ) {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setZenAfterNebikiUriClass( bodyHirituClassM );
                    } else {
                        nipo.setZenAfterNebikiUriClass( bodyHirituClass );
                    }
                } else {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setZenAfterNebikiUriClass( bodyNumClassN );
                    } else {
                        nipo.setZenAfterNebikiUriClass( bodyNumClass );
                    }
                }
                //end add 2019/04/18 xou.zoubun モスダイニング支部時、前年値引表示対応

                if ( nipo.getZenHiUri().compareTo( ritu ) < 0 ) {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setZenUriClass( bodyHirituClassM );
                    } else {
                        nipo.setZenUriClass( bodyHirituClass );
                    }
                } else {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setZenUriClass( bodyNumClassN );
                    } else {
                        nipo.setZenUriClass( bodyNumClass );
                    }
                }

                BigDecimal zenhiKyakuRitu = Calculator.percentage( nipo.getKyakusu(),nipo.getKyakusuZen(),2 );

                if ( zenhiKyakuRitu.intValue() == 0 ) {
                    zenhiKyakuRitu = new BigDecimal("0.00");
                }
                nipo.setZenHiKyaku( zenhiKyakuRitu );

                if ( nipo.getZenHiKyaku().compareTo( ritu ) < 0 ) {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setZenKyaClass( bodyHirituClassM ) ;
                    } else {
                        nipo.setZenKyaClass( bodyHirituClass ) ;
                    }
                } else {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setZenKyaClass( bodyNumClassN );
                    } else {
                        nipo.setZenKyaClass( bodyNumClass );
                    }
                }

                BigDecimal zenhiTankaRitu = Calculator.percentage( nipo.getTanka(),nipo.getTankaZen(),2 );

                if ( zenhiTankaRitu.intValue() == 0 ) {
                    zenhiTankaRitu = new BigDecimal("0.00");
                }
                nipo.setZenHiTanka( zenhiTankaRitu );

                if (nipo.getZenHiTanka().compareTo( ritu ) < 0 ) {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setZenTanClass( bodyHirituClassM ) ;
                    } else {
                        nipo.setZenTanClass( bodyHirituClass ) ;
                    }
                } else {
                    if ( nipo.getRClass().equals( noClass ) ) {
                        nipo.setZenTanClass( bodyNumClassN );
                    } else {
                        nipo.setZenTanClass( bodyNumClass );
                    }
                }
            }
        }

        return paramList;

    }
}
