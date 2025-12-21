package jp.co.isid.mos.bird.bizreport.groupeigyoniporef.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.code.NipoZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.common.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.dao.TrnUriageInfoDao;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.logic.UriageInfoLogic;

// 売上を取得するロジック
public class UriageInfoLogicImpl implements UriageInfoLogic{

    public static final String LOGIC_ID = "BBR001L02";

    private TrnUriageInfoDao trnUriageInfoDao;

    /**
     * trnUriageInfoDao設定
     * @return
     */
    public TrnUriageInfoDao getTrnUriageInfoDao() {
        return this.trnUriageInfoDao;
    }
    /**
     * trnUriageInfoDao設定
     * @param trnUriageInfoDao
     */
    public void setTrnUriageInfoDao( TrnUriageInfoDao trnUriageInfoDao ) {
        this.trnUriageInfoDao = trnUriageInfoDao;
    }

    /**
     * 実行処理(支部一覧
     */
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

        // 店舗種別によってパラメータを変化
        // 前年対象店の時
        String dataShuKbn = new String();
        if ( tenShu.equals( TenpoShubetu.CODE_ZENNEN ) ) {
            dataShuKbn = "1";
        // 予算対象店の時
        } else if ( tenShu.equals( TenpoShubetu.CODE_YOSAN ) ) {
            dataShuKbn = "2";
        // 新店の時
        } else if ( tenShu.equals( TenpoShubetu.CODE_SIN ) ) {
            dataShuKbn = "3";
        // それ以外のとき(全店のときは未設定でよい)
        } else {
        }

        List uriageList = new ArrayList();

        if ( taishoKikan.equals("APPMONTH")) {
            kikanFrom = kikanFrom.substring(0,6);
        }

        // 前年同月
        if ( dataShu.equals( NipoZennenDataShubetu.CODE_DOGETU ) ) {
            // 月指定
            if ( taishoKikan.equals("MONTH") || taishoKikan.equals("APPMONTH") ) {
                uriageList = getTrnUriageInfoDao().getUriageDougetuGepo(companyCd
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
            // 期別期報
            } else if ( taishoKikan.equals("KIBETU") ) {
                uriageList = getTrnUriageInfoDao().getUriageDougetuGepoKikan(companyCd
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

             //　期日指定日報
            } else if ( taishoKikan.equals("DAY1" ) ) {
                    uriageList = getTrnUriageInfoDao().getUriageDougetu(companyCd
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
           // 期間指定日報
           } else if (taishoKikan.equals("DAYS")){
                    uriageList = getTrnUriageInfoDao().getUriageDougetuKikan(companyCd
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
        // 前年同曜
        } else if (dataShu.equals( NipoZennenDataShubetu.CODE_DOYO ) ) {
            // 月指定
            if ( taishoKikan.equals("MONTH") || taishoKikan.equals("APPMONTH") ) {
                uriageList = getTrnUriageInfoDao().getUriageDouyouGepo(companyCd
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

            // 期別期報
            } else if ( taishoKikan.equals("KIBETU") ) {
                uriageList = getTrnUriageInfoDao().getUriageDouyouGepoKikan(companyCd
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
            // 期日指定日報
            } else if ( taishoKikan.equals("DAY1" ) ) {
                uriageList = getTrnUriageInfoDao().getUriageDouyou(companyCd
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

            // 期間指定日報
            } else if (taishoKikan.equals("DAYS") || taishoKikan.equals("APPMONTH") ){
                uriageList = getTrnUriageInfoDao().getUriageDouyouKikan(companyCd
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
        // 前年同月営業日補正
        }  else if ( dataShu.equals( NipoZennenDataShubetu.CODE_HOSEI ) ) {
            // 月指定
            if ( taishoKikan.equals("MONTH")) {
                uriageList = getTrnUriageInfoDao().getUriageDougetuHoseiGepo(companyCd
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

            // 期別期報
            } else if ( taishoKikan.equals("KIBETU") ) {
                uriageList = getTrnUriageInfoDao().getUriageDougetuHoseiGepoKikan(companyCd
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
            // 期日指定日報
            } else if ( taishoKikan.equals("DAY1" ) ) {
                uriageList = getTrnUriageInfoDao().getUriageDougetuHosei(companyCd
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

            // 期間指定日報
            } else if (taishoKikan.equals("DAYS") || taishoKikan.equals("APPMONTH") ){
                uriageList = getTrnUriageInfoDao().getUriageDougetuHoseiKikan(companyCd
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
        }
// add USI蔡 2018/08/10 begin --前年同日を追加
        // 前年同日
        else if ( dataShu.equals( NipoZennenDataShubetu.CODE_DOJITU ) ) {
        	// 月指定
            if ( taishoKikan.equals("MONTH") || taishoKikan.equals("APPMONTH") ) {
                uriageList = getTrnUriageInfoDao().getUriageDoujituGepo(companyCd
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

            // 期別期報
            } else if ( taishoKikan.equals("KIBETU") ) {
                uriageList = getTrnUriageInfoDao().getUriageDoujituGepoKikan(companyCd
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
            // 期日指定日報
            } else if ( taishoKikan.equals("DAY1" ) ) {
                uriageList = getTrnUriageInfoDao().getUriageDoujitu(companyCd
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

            // 期間指定日報
            } else if (taishoKikan.equals("DAYS") || taishoKikan.equals("APPMONTH") ){
                uriageList = getTrnUriageInfoDao().getUriageDoujituKikan(companyCd
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
        }
// add USI蔡 2018/08/10 end
// add start 20061016 xkhata 前年同月OPEN区分対応

// end

        return uriageList;
    }

}
