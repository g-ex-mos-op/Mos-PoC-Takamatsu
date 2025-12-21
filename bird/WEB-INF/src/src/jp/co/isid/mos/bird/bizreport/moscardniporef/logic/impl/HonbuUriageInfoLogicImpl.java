package jp.co.isid.mos.bird.bizreport.moscardniporef.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.code.NipoZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.common.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.moscardniporef.dao.TrnUriageInfoDao;
import jp.co.isid.mos.bird.bizreport.moscardniporef.logic.HonbuUriageInfoLogic;

// 売上を取得するロジック
public class HonbuUriageInfoLogicImpl implements HonbuUriageInfoLogic{

    public static final String LOGIC_ID = "BBR015L03";

    private TrnUriageInfoDao moscardNipoRefTrnUriageInfoDao;

    /**
     * trnMosCardInfoDao設定
     * @return
     */
    public TrnUriageInfoDao getTrnMosCardInfoDao() {
        return this.moscardNipoRefTrnUriageInfoDao;
    }
    /**
     * trnMosCardInfoDao設定
     * @param trnMosCardInfoDao
     */
    public void setTrnMosCardInfoDao( TrnUriageInfoDao moscardNipoRefTrnUriageInfoDao ) {
        this.moscardNipoRefTrnUriageInfoDao = moscardNipoRefTrnUriageInfoDao;
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
        String taishoJoken = "dogetu";

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

        if (dataShu.equals( NipoZennenDataShubetu.CODE_DOGETU )) {
            taishoJoken = "dogetu";
        } else if (dataShu.equals( NipoZennenDataShubetu.CODE_DOYO )) {
            taishoJoken = "doyo";
        } else if (dataShu.equals( NipoZennenDataShubetu.CODE_HOSEI)) {
            if ( taishoKikan.equals("MONTH") || taishoKikan.equals("APPMONTH") || taishoKikan.equals("KIBETU")  ) {
                taishoJoken = "hosei";
            } else {
                taishoJoken = "dogetu";
            }
        }
// add USI蔡 2018/08/13 begin --前年同日を追加
        else if (dataShu.equals( NipoZennenDataShubetu.CODE_DOJITU )) {
        	taishoJoken = "dojitu";
        }
// add USI蔡 2018/08/13 end

        List uriageList = new ArrayList();

        if ( taishoKikan.equals("APPMONTH")) {
            kikanFrom = kikanFrom.substring(0,6);
        }
        // 月指定
        if ( taishoKikan.equals("MONTH") || taishoKikan.equals("KIBETU") || taishoKikan.equals("APPMONTH") ) {
            uriageList = getTrnMosCardInfoDao().getUriageHonbuGepo(companyCd
                                                                    ,userId
                                                                    ,tenShu
                                                                    ,dataShu
                                                                    ,taishoKikan
                                                                    ,kikanFrom
                                                                    ,kikanTo
                                                                    ,limitFlg
                                                                    ,areaDaiFlg
                                                                    ,taishoTenpo
                                                                    ,dataShuKbn
                                                                    ,taishoJoken);

       // 日報
       } else if (taishoKikan.equals("DAYS") || taishoKikan.equals("DAY1") ){
                uriageList = getTrnMosCardInfoDao().getUriageHonbuNipo(companyCd
                                                                        ,userId
                                                                        ,tenShu
                                                                        ,dataShu
                                                                        ,taishoKikan
                                                                        ,kikanFrom
                                                                        ,kikanTo
                                                                        ,limitFlg
                                                                        ,areaDaiFlg
                                                                        ,taishoTenpo
                                                                        ,dataShuKbn
                                                                        ,taishoJoken);

       }

        return uriageList;
    }

}
