package jp.co.isid.mos.bird.bizreport.moscardniporef.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.code.NipoZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.common.code.ShukeiKbn;
import jp.co.isid.mos.bird.bizreport.common.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.moscardniporef.common.MoscardNipoConstants;
import jp.co.isid.mos.bird.bizreport.moscardniporef.dao.MstSegmentInfoDao;
import jp.co.isid.mos.bird.bizreport.moscardniporef.dao.TrnUriageSegInfoDao;
import jp.co.isid.mos.bird.bizreport.moscardniporef.entity.MstSegmentInfo;
import jp.co.isid.mos.bird.bizreport.moscardniporef.entity.MstSibuInfo;
import jp.co.isid.mos.bird.bizreport.moscardniporef.entity.TrnSibuKyakusuNipoRelate;
import jp.co.isid.mos.bird.bizreport.moscardniporef.entity.TrnSibuUriageNipoRelate;
import jp.co.isid.mos.bird.bizreport.moscardniporef.entity.TrnSibuUriageNipoSegRelate;
import jp.co.isid.mos.bird.bizreport.moscardniporef.entity.TrnUriageInfo;
import jp.co.isid.mos.bird.bizreport.moscardniporef.logic.ViewUriYosanLogic;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.util.Calculator;

/**
 *
 * @author   xyamauchi
 *
 */
public class ViewUriYosanLogicImpl implements ViewUriYosanLogic{

    public static final String LOGIC_ID = "BBR015L04";

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

    // 合計１
    private String rClassSum1 = "body_sum1";
    // 合計２
    private String rClassSum2 = "body_sum2";
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

    // エリア区分
    private String area = "AREA";
    // 事業部区分
    private String jigyo = "JIGYO";
    // 統括本部区分
    private String toukatu = "TOUKATU";

    // 数値型比較用定数
    private BigDecimal bigZero = new BigDecimal(0.00);


    // セグメント情報取得
    private MstSegmentInfoDao moscardNipoRefTrnSegmentInfoDao;

    /**
     * moscardNipoRefTrnSegmentInfoDao取得
     * @return
     */
    public MstSegmentInfoDao getMstSegmentInfoDao() {
        return this.moscardNipoRefTrnSegmentInfoDao;
    }

    /**
     * mstSegmentInfoDao設定
     * @param mstSegmentInfoDao
     */
    public void setMstSegmentInfoDao( MstSegmentInfoDao moscardNipoRefTrnSegmentInfoDao ) {
        this.moscardNipoRefTrnSegmentInfoDao = moscardNipoRefTrnSegmentInfoDao;
    }
    // セグメント指定売上取得
    private TrnUriageSegInfoDao moscardNipoRefTrnUriageSegInfoDao;
    /**
     * trnUriageSegInfoDao設定
     * @return
     */
    public TrnUriageSegInfoDao getTrnUriageSegInfoDao() {
        return this.moscardNipoRefTrnUriageSegInfoDao;
    }
    /**
     * trnUriageSegInfoDao設定
     * @param trnUriageSegInfoDao
     */
    public void setTrnUriageSegInfoDao( TrnUriageSegInfoDao moscardNipoRefTrnUriageSegInfoDao ) {
        this.moscardNipoRefTrnUriageSegInfoDao = moscardNipoRefTrnUriageSegInfoDao;
    }

    /**
     * 予算売上のデータを整理する
     */
    public Map execute(Map map) {

        Map kekkaMap = getReadjustMap( map );
        Map paramMap = (Map)map.get("param");

        String areaDai = (String)map.get("areadai");
        String companyCd = (String)paramMap.get( COMPANY_CD );

        List uriageList = (List)kekkaMap.get("uriage");

        // 本部発行データを画面表示用に加工
        TrnSibuUriageNipoRelate  honbuHakkouUriage = getHonbuUriage( (List)map.get(MoscardNipoConstants.MAP_HONBUURIAGE_LIST) );

        // モス以外は支部しか存在しないため
        if ( CommonUtil.COMPANY_CD_MOS.equals(companyCd) || CommonUtil.COMPANY_CD_TOMOS.equals(companyCd) ) {
            if ( areaDai.equals(ShukeiKbn.OUT_RC)) {
                // エリア合計を作成
                Map resultMap = getResultMap( uriageList);
                uriageList = (List)resultMap.get("uriage");
            }
        }

        // 総合計取得を変更(セグメント計→支部計)
        String dataShu = (String)paramMap.get("dataShu");
        // 前年同月営業日補正のとき(データは前年同月)
        if ( dataShu.equals(NipoZennenDataShubetu.CODE_HOSEI)) {
            paramMap.put("dataShu",NipoZennenDataShubetu.CODE_DOGETU);
        }
        paramMap.put("uriageList",uriageList );
        paramMap.put("honbuUriageList",honbuHakkouUriage);

        Map segMap = segList( paramMap );

        List uriageSegList = (List)segMap.get("uriage");

        uriageList.addAll( uriageSegList );

        Map returnMap = new HashMap();
        returnMap.put("uriage",uriageList);
        // 売上平均を作成
        Map mapGross = calHeikin( returnMap );

        TrnSibuUriageNipoRelate trnUriage = (TrnSibuUriageNipoRelate)uriageSegList.get(  uriageSegList.size() - 1 ) ;

        mapGross.put("tenpoCount",trnUriage.getTenpoCount() );
        //予算対象店舗数をリターン値Mapへ格納する。
        mapGross.put(MoscardNipoConstants.MAP_YOSAN_TENPO_COUNT, trnUriage.getYosanMiseCnt() );

        Map retMap = mapGross;

        // 前年同月営業日補正のとき(データは前年同月)
        if ( dataShu.equals(NipoZennenDataShubetu.CODE_HOSEI)) {

            List uriHoseiYouList = (List)map.get("hoseiyou");
            map.put("uriage",uriHoseiYouList);
            Map kekkaHoseiYouMap = getReadjustMap( map );

            List uriYouList = (List)kekkaHoseiYouMap.get("uriage");
            // モス以外は支部しか存在しないため
            if ( CommonUtil.COMPANY_CD_MOS.equals(companyCd) || CommonUtil.COMPANY_CD_TOMOS.equals(companyCd) ) {
                if ( areaDai.equals("SIBU_CD")) {
                    Map resultMap = getResultMap( uriYouList);
                    uriYouList = (List)resultMap.get("uriage");
                }
            }

            // 総合計取得を変更(セグメント計→支部計)
            paramMap.put("uriageList",uriYouList );
            TrnSibuUriageNipoRelate  honbuHakkouHoseiUriage = getHonbuUriage( (List)map.get(MoscardNipoConstants.MAP_HONBU_HOSEI_URIAGE_LIST) );
            paramMap.put("honbuUriageList",honbuHakkouHoseiUriage);

            paramMap.put("dataShu",NipoZennenDataShubetu.CODE_HOSEI);

            Map segYouMap = segList( paramMap );

            List uriYouSegList = (List)segYouMap.get("uriage");

            uriYouList.addAll( uriYouSegList );


            Map returnYouMap = new HashMap();
            returnYouMap.put("uriage",uriYouList);

            Map mapNet = calHeikin( returnYouMap );

            TrnSibuUriageNipoRelate trnYouUriage = (TrnSibuUriageNipoRelate)uriYouSegList.get(  uriYouSegList.size() - 1 ) ;
            mapNet.put("tenpoCount",trnYouUriage.getTenpoCount());

            retMap = getHoseiInfo( mapGross,mapNet );

        }
        return retMap;
    }

    /**
     * 前年同月営業日補正用データ作成
     *
     * @param mapGross Gross値Map
     * @param mapNet   Net値Map
     * @return
     */
    public Map getHoseiInfo(Map mapGross, Map mapNet) {
        Map retMap = new HashMap();

        List uriageList = (List)mapGross.get("uriage");
        List uriHeiList = (List)mapGross.get("uriHei");
        List uriageHoseiList = (List)mapNet.get("uriage");
        List uriHeiHoseiList = (List)mapNet.get("uriHei");

        BigDecimal tenpoCnt = (BigDecimal)mapGross.get("tenpoCount");

        retMap.put("uriHosei",uriageHoseiList);
        retMap.put("uriHeiHosei",uriHeiHoseiList );
        retMap.put("uriDou",uriageList);
        retMap.put("uriheiDou",uriHeiList );
        retMap.put("tenpoCount",tenpoCnt );

        List uriList = new ArrayList();
        List uriAvgList = new ArrayList();

        for ( int i = 0; i < uriageList.size(); i++ ) {
            TrnSibuUriageNipoRelate uriage = new TrnSibuUriageNipoRelate();
            TrnSibuUriageNipoRelate uriHei = new TrnSibuUriageNipoRelate();

            TrnSibuUriageNipoRelate douUri = (TrnSibuUriageNipoRelate)uriageList.get(i);
            TrnSibuUriageNipoRelate douUriHose = (TrnSibuUriageNipoRelate)uriageHoseiList.get(i);
            TrnSibuUriageNipoRelate douUriHei = (TrnSibuUriageNipoRelate)uriHeiList.get(i);
            TrnSibuUriageNipoRelate douUriHoseHei = (TrnSibuUriageNipoRelate)uriHeiHoseiList.get(i);

            // 売上
            uriage.setRClass( douUri.getRClass() );
            uriage.setCompanyCd( douUri.getCompanyCd() );
            uriage.setDispKbn( douUri.getDispKbn() );
            uriage.setHonbuCd( douUri.getHonbuCd() );
            uriage.setHonbuName( douUri.getHonbuName() );
            uriage.setJigyoCd( douUri.getJigyoCd() );
            uriage.setJigyoName( douUri.getJigyoName() );
            uriage.setSlareaCd( douUri.getSlareaCd() );
            uriage.setSlareaName( douUri.getSlareaName() );
            uriage.setSibuNameClass( douUri.getSibuNameClass() );
            uriage.setSibuCd( douUri.getSibuCd() );
            uriage.setSibuName( douUri.getSibuName() );
            uriage.setTenpoCount( douUri.getTenpoCount() );
            uriage.setZenTenpoCount( douUri.getZenTenpoCount() );
            uriage.setHoseiTenpoCnt( douUriHose.getTenpoCount() );
            uriage.setHoseiZenTenpoCnt( douUriHose.getZenTenpoCount() );
            uriage.setUriage( douUri.getUriage() );
            uriage.setSibuYosan( douUri.getSibuYosan() );
            uriage.setKyakusu( douUri.getKyakusu() );
            uriage.setZenKyakusu( douUriHose.getZenKyakusu() );
            uriage.setTasseiYosan( douUri.getTasseiYosan() );
            uriage.setZenUriage( douUriHose.getZenUriage() );
            uriage.setZenHiSa( douUriHose.getZenHiSa() );
            uriage.setIssueCntClass( douUri.getIssueCntClass() );
            uriage.setIssueCnt( douUri.getIssueCnt() );
            uriage.setZenIssueCntClass( douUri.getZenIssueCntClass() );
            uriage.setZenIssueCnt( douUri.getZenIssueCnt() );
            uriage.setZenIssueCntHirituClass( douUriHose.getZenIssueCntHirituClass() );
            uriage.setZenIssueCntHiritu( douUriHose.getZenIssueCntHiritu() );
            uriage.setChargeKinClass( douUri.getChargeKinClass() );
            uriage.setChargeKin( douUri.getChargeKin() );
            uriage.setZenChargeKenClass( douUri.geZenChargeKenClass() );
            uriage.setZenChargeKen( douUri.getZenChargeKen() );
            uriage.setZenChargeKinClass( douUri.getZenChargeKinClass() );
            uriage.setZenChargeKin( douUri.getZenChargeKin() );
            uriage.setUrihiChargeKinClass( douUri.getUrihiChargeKinClass() );
            uriage.setUrihiChargeKin( douUri.getUrihiChargeKin() );
            uriage.setZenHiChargeKinClass( douUriHose.getZenHiChargeKinClass() );
            uriage.setZenHiChargeKin( douUriHose.getZenHiChargeKin() );
            uriage.setChargeKenClass( douUri.getChargeKenClass() );
            uriage.setChargeKen( douUri.getChargeKen() );
            uriage.setChargeKenKyakuHiClass( douUri.getChargeKenKyakuHiClass() );
            uriage.setChargeKenKyakuHi( douUri.getChargeKenKyakuHi() );
            uriage.setChargeKenZenHiClass( douUriHose.getChargeKenZenHiClass() );
            uriage.setChargeKenZenHi( douUriHose.getChargeKenZenHi() );
            uriage.setChargeTankaClass( douUri.getChargeTankaClass() );
            uriage.setChargeTanka( douUri.getChargeTanka() );
            uriage.setChargeTankahiClass( douUri.getChargeTankahiClass() );
            uriage.setChargeTankahi( douUri.getChargeTankahi() );
            uriage.setZenChargeTankahiClass( douUriHose.getZenChargeTankahiClass() );
            uriage.setZenChargeTankahi( douUriHose.getZenChargeTankahi() );
            uriage.setKessaiKinClass( douUri.getKessaiKinClass() );
            uriage.setKessaiKin( douUri.getKessaiKin() );
            uriage.setZenKessaiKin( douUri.getZenKessaiKin() );
            uriage.setKessaiKinUriHiClass( douUri.getKessaiKinUriHiClass() );
            uriage.setKessaiKinUriHi( douUri.getKessaiKinUriHi() );
            uriage.setKessaiKinZenHiClass( douUriHose.getKessaiKinZenHiClass() );
            uriage.setKessaiKinZenHi( douUriHose.getKessaiKinZenHi() );
            uriage.setKessaiKenClass( douUri.getKessaiKenClass() );
            uriage.setKessaiKen( douUri.getKessaiKen() );
            uriage.setZenKessaiKen( douUri.getZenKessaiKen() );
            uriage.setKessaiKenKyakuHiClass( douUri.getKessaiKenKyakuHiClass() );
            uriage.setKessaiKenKyakuHi( douUri.getKessaiKenKyakuHi() );
            uriage.setKessaiKenZenHiClass( douUriHose.getKessaiKenZenHiClass() );
            uriage.setKessaiKenZenHi( douUriHose.getKessaiKenZenHi() );
            uriage.setKessaiKinTankaClass( douUri.getKessaiKinTankaClass() );
            uriage.setKessaiKinTanka( douUri.getKessaiKinTanka() );
            uriage.setKessaiKinTankaHiClass( douUri.getKessaiKinTankaHiClass() );
            uriage.setKessaiKinTankaHi( douUri.getKessaiKinTankaHi() );
            uriage.setKessaiKinZenTankaHiClass( douUriHose.getKessaiKinZenTankaHiClass() );
            uriage.setKessaiKinZenTankaHi( douUriHose.getKessaiKinZenTankaHi() );
            uriage.setChargeKinCancel( douUri.getChargeKinCancel() );
            uriage.setChargeKenCancel( douUri.getChargeKenCancel() );
            uriage.setUseKinCancel( douUri.getUseKinCancel() );
            uriage.setUseKenCancel( douUri.getUseKenCancel() );
            uriage.setBonusVIssue( douUri.getBonusVIssue() );
            uriage.setBonusVUse( douUri.getBonusVUse() );
            uriage.setCouponVIssue( douUri.getCouponVIssue() );
            uriage.setCouponVUse( douUri.getCouponVUse() );
            uriage.setZandaka( douUri.getZandaka() );
            uriage.setSvCd( douUri.getSvCd() );
            uriage.setUsernamekj( douUri.getUsernamekj() );
            uriage.setKyakuTanka( douUri.getKyakuTanka() );
            uriage.setZenTanka( douUri.getZenTanka() );
            uriage.setKyakuTankaZenhi( douUriHose.getKyakuTankaZenhi() );

            uriage.setZenSaIssueCnt( douUriHose.getZenSaIssueCnt() );
            uriage.setZenSaChargeKin( douUriHose.getZenSaChargeKin() );
            uriage.setZenSaChargeKen( douUriHose.getZenSaChargeKen() );
            uriage.setZenSaKessaiKin( douUriHose.getZenSaKessaiKin() );
            uriage.setZenSaKessaiKen( douUriHose.getZenSaKessaiKen() );

            // 売上平均
            uriHei.setRClass( douUriHei.getRClass() );
            uriHei.setCompanyCd( douUriHei.getCompanyCd() );
            uriHei.setDispKbn( douUriHei.getDispKbn() );
            uriHei.setHonbuCd( douUriHei.getHonbuCd() );
            uriHei.setHonbuName( douUriHei.getHonbuName() );
            uriHei.setJigyoCd( douUriHei.getJigyoCd() );
            uriHei.setJigyoName( douUriHei.getJigyoName() );
            uriHei.setSlareaCd( douUriHei.getSlareaCd() );
            uriHei.setSlareaName( douUriHei.getSlareaName() );
            uriHei.setSibuNameClass( douUriHei.getSibuNameClass() );
            uriHei.setSibuCd( douUriHei.getSibuCd() );
            uriHei.setSibuName( douUriHei.getSibuName() );
            uriHei.setTenpoCount( douUriHei.getTenpoCount() );
            uriHei.setZenTenpoCount( douUriHei.getZenTenpoCount() );
            uriHei.setHoseiTenpoCnt( douUriHoseHei.getTenpoCount() );
            uriHei.setHoseiZenTenpoCnt( douUriHoseHei.getZenTenpoCount() );
            uriHei.setUriage( douUriHei.getUriage() );
            uriHei.setSibuYosan( douUriHei.getSibuYosan() );
            uriHei.setKyakusu( douUriHei.getKyakusu() );
            uriHei.setZenKyakusu( douUriHoseHei.getZenKyakusu() );
            uriHei.setTasseiYosan( douUriHei.getTasseiYosan() );
            uriHei.setZenUriage( douUriHoseHei.getZenUriage() );
            uriHei.setZenHiSa( douUriHoseHei.getZenHiSa() );
            uriHei.setIssueCntClass( douUriHei.getIssueCntClass() );
            uriHei.setIssueCnt( douUriHei.getIssueCnt() );
            uriHei.setZenIssueCntClass( douUriHei.getZenIssueCntClass() );
            uriHei.setZenIssueCnt( douUriHei.getZenIssueCnt() );
            uriHei.setZenIssueCntHirituClass( douUriHoseHei.getZenIssueCntHirituClass() );
            uriHei.setZenIssueCntHiritu( douUriHoseHei.getZenIssueCntHiritu() );
            uriHei.setChargeKinClass( douUriHei.getChargeKinClass() );
            uriHei.setChargeKin( douUriHei.getChargeKin() );
            uriHei.setZenChargeKinClass( douUriHei.getZenChargeKinClass() );
            uriHei.setZenChargeKin( douUriHei.getZenChargeKin() );
            uriHei.setUrihiChargeKinClass( douUriHei.getUrihiChargeKinClass() );
            uriHei.setUrihiChargeKin( douUriHei.getUrihiChargeKin() );
            uriHei.setZenHiChargeKinClass( douUriHoseHei.getZenHiChargeKinClass() );
            uriHei.setZenHiChargeKin( douUriHoseHei.getZenHiChargeKin() );
            uriHei.setChargeKenClass( douUriHei.getChargeKenClass() );
            uriHei.setChargeKen( douUriHei.getChargeKen() );
            uriHei.setZenChargeKenClass( douUriHoseHei.geZenChargeKenClass() );
            uriHei.setZenChargeKen( douUriHoseHei.getZenChargeKen() );
            uriHei.setChargeKenKyakuHiClass( douUriHei.getChargeKenKyakuHiClass() );
            uriHei.setChargeKenKyakuHi( douUriHei.getChargeKenKyakuHi() );
            uriHei.setChargeKenZenHiClass( douUriHoseHei.getChargeKenZenHiClass() );
            uriHei.setChargeKenZenHi( douUriHoseHei.getChargeKenZenHi() );
            uriHei.setChargeTankaClass( douUriHei.getChargeTankaClass() );
            uriHei.setChargeTanka( douUriHei.getChargeTanka() );
            uriHei.setChargeTankahiClass( douUriHei.getChargeTankahiClass() );
            uriHei.setChargeTankahi( douUriHei.getChargeTankahi() );
            uriHei.setZenChargeTankahiClass( douUriHoseHei.getZenChargeTankahiClass() );
            uriHei.setZenChargeTankahi( douUriHoseHei.getZenChargeTankahi() );
            uriHei.setKessaiKinClass( douUriHei.getKessaiKinClass() );
            uriHei.setKessaiKin( douUriHei.getKessaiKin() );
            uriHei.setZenKessaiKin( douUriHei.getZenKessaiKin() );
            uriHei.setKessaiKinUriHiClass( douUriHei.getKessaiKinUriHiClass() );
            uriHei.setKessaiKinUriHi( douUriHei.getKessaiKinUriHi() );
            uriHei.setKessaiKinZenHiClass( douUriHoseHei.getKessaiKinZenHiClass() );
            uriHei.setKessaiKinZenHi( douUriHoseHei.getKessaiKinZenHi() );
            uriHei.setKessaiKenClass( douUriHei.getKessaiKenClass() );
            uriHei.setKessaiKen( douUriHei.getKessaiKen() );
            uriHei.setZenKessaiKen( douUriHei.getZenKessaiKen() );
            uriHei.setKessaiKenKyakuHiClass( douUriHei.getKessaiKenKyakuHiClass() );
            uriHei.setKessaiKenKyakuHi( douUriHei.getKessaiKenKyakuHi() );
            uriHei.setKessaiKenZenHiClass( douUriHoseHei.getKessaiKenZenHiClass() );
            uriHei.setKessaiKenZenHi( douUriHoseHei.getKessaiKenZenHi() );
            uriHei.setKessaiKinTankaClass( douUriHei.getKessaiKinTankaClass() );
            uriHei.setKessaiKinTanka( douUriHei.getKessaiKinTanka() );
            uriHei.setKessaiKinTankaHiClass( douUriHei.getKessaiKinTankaHiClass() );
            uriHei.setKessaiKinTankaHi( douUriHei.getKessaiKinTankaHi() );
            uriHei.setKessaiKinZenTankaHiClass( douUriHoseHei.getKessaiKinZenTankaHiClass() );
            uriHei.setKessaiKinZenTankaHi( douUriHoseHei.getKessaiKinZenTankaHi() );
            uriHei.setChargeKinCancel( douUriHei.getChargeKinCancel() );
            uriHei.setChargeKenCancel( douUriHei.getChargeKenCancel() );
            uriHei.setUseKinCancel( douUriHei.getUseKinCancel() );
            uriHei.setUseKenCancel( douUriHei.getUseKenCancel() );
            uriHei.setBonusVIssue( douUriHei.getBonusVIssue() );
            uriHei.setBonusVUse( douUriHei.getBonusVUse() );
            uriHei.setCouponVIssue( douUriHei.getCouponVIssue() );
            uriHei.setCouponVUse( douUriHei.getCouponVUse() );
            uriHei.setZandaka( douUriHei.getZandaka() );

            uriHei.setKyakuTanka( douUriHei.getKyakuTanka() );
            uriHei.setZenTanka( douUriHei.getZenTanka() );
            uriHei.setKyakuTankaZenhi( douUriHoseHei.getKyakuTankaZenhi() );

            uriHei.setZenSaIssueCnt( douUriHose.getZenSaIssueCnt() );
            uriHei.setZenSaChargeKin( douUriHose.getZenSaChargeKin() );
            uriHei.setZenSaChargeKen( douUriHose.getZenSaChargeKen() );
            uriHei.setZenSaKessaiKin( douUriHose.getZenSaKessaiKin() );
            uriHei.setZenSaKessaiKen( douUriHose.getZenSaKessaiKen() );

            uriHei = calHeikinUriage( uriHei );

            uriList.add( uriage );
            uriAvgList.add( uriHei );

        }

        retMap.put("uriage",uriList);
        retMap.put("uriHei",uriAvgList);

        return retMap;

    }
    /**
     * モス合計、モス以外合計、総合計を作成する
     * @param map
     * @return
     */
    public Map getSegReadJustMap(Map map ) {
        Map returnMap = new HashMap();

        List uriage = (List)map.get("uriage");
        TrnSibuUriageNipoRelate honbuUriageDate = (TrnSibuUriageNipoRelate) map.get("honbuUriageDate");
        String companyCd = (String)map.get("companyCd");

        List uri = new ArrayList();

        for (int i = 0; i < uriage.size(); i++ ) {
            TrnSibuUriageNipoSegRelate uriSeg = (TrnSibuUriageNipoSegRelate)uriage.get(i);
            TrnSibuUriageNipoRelate uriNipo = new TrnSibuUriageNipoRelate();

            uriNipo.setCompanyCd( uriSeg.getCompanyCd() );
            uriNipo.setRClass( rClassSum4 );
            uriNipo.setSibuCd( uriSeg.getSegmentType() );
            uriNipo.setSibuName( uriSeg.getSegmentName().trim() );
            uriNipo.setSibuNameClass( noClass );
            uriNipo.setTenpoCount( uriSeg.getOpenKbn() );
            uriNipo.setZenTenpoCount( uriSeg.getOpenKbnZen() );
            uriNipo.setUriage( uriSeg.getUriage() );
            uriNipo.setSibuYosan( uriSeg.getSibuYosan() );
//          2007/06/05 ADD start xkinu  クローズ店予算表示対応
            //予算対象店舗数設定
            uriNipo.setYosanMiseCnt( uriSeg.getMiseCount() );
//2007/06/05 ADD start xkinu  クローズ店予算表示対応
            uriNipo.setZenUriage( uriSeg.getUriageZen() );
            //発行枚数
            uriNipo.setIssueCnt( uriSeg.getIssueCnt() );
            uriNipo.setIssueCntClass( bodyNumClass );
            //過去発行枚数
            uriNipo.setZenIssueCnt( uriSeg.getIssueCntZen() );
            uriNipo.setZenIssueCntClass( bodyNumClass );
            //チャージ金額
            uriNipo.setChargeKin( uriSeg.getChargeKin() );
            uriNipo.setChargeKinClass( bodyNumClass );
            //前年チャージ金額
            uriNipo.setZenChargeKin( uriSeg.getChargeKinZen() );
            uriNipo.setZenChargeKinClass( bodyNumClass );
            //チャージ件数
            uriNipo.setChargeKen( uriSeg.getChargeKen() );
            uriNipo.setChargeKenClass( bodyNumClass );
            //前年チャージ件数
            uriNipo.setZenChargeKen( uriSeg.getChargeKenZen() );
            uriNipo.setZenChargeKenClass( bodyNumClass );
            //客数
            uriNipo.setKyakusu( uriSeg.getKyakusu() );
            //前年客数
            uriNipo.setZenKyakusu( uriSeg.getKyakusuZen() );
            //決済金額
            uriNipo.setKessaiKin(uriSeg.getKessaiKin());
            uriNipo.setKessaiKinClass(bodyNumClass);
            //前年決済件数
            uriNipo.setZenKessaiKin(uriSeg.getKessaiKinZen());
            //決済件数
            uriNipo.setKessaiKen(uriSeg.getKessaiKen());
            uriNipo.setKessaiKenClass(bodyNumClass);
            //前年決済件数
            uriNipo.setZenKessaiKen(uriSeg.getkessaiKenZen());
            //入金取消金額
            uriNipo.setChargeKinCancel(uriSeg.getChargeKinCancel());
            //入金取消件数
            uriNipo.setChargeKenCancel(uriSeg.getChargeKenCancel());
            //利用取消金額
            uriNipo.setUseKinCancel(uriSeg.getUseKinCancel());
            //利用取消件数
            uriNipo.setUseKenCancel(uriSeg.getUseKenCancel());
            //発行ボーナスバリュー
            uriNipo.setBonusVIssue(uriSeg.getBonusVIssue());
            //利用ボーナスバリュー
            uriNipo.setBonusVUse(uriSeg.getBonusVUse());
            //発行クーポンバリュー
            uriNipo.setCouponVIssue(uriSeg.getCouponVIssue());
            //利用クーポンバリュー
            uriNipo.setCouponVUse(uriSeg.getCouponVUse());
            //前受残高
            uriNipo.setZandaka(uriSeg.getZandaka());

            uriNipo.setDispKbn( "DISP" );
            uriNipo = calcHiritu( uriNipo );

            uri.add(uriNipo);

            Map paramMap = new HashMap();
            paramMap.put("uriage",uriNipo );

        }

        // 総合計
        BigDecimal souUri = new BigDecimal(0);
        BigDecimal souCount = new BigDecimal(0);
        BigDecimal souZenCount = new BigDecimal(0);
        BigDecimal souYos = new BigDecimal(0);
        BigDecimal souZenUri = new BigDecimal(0);
        BigDecimal souYosanMiseCnt = new BigDecimal(0);
        BigDecimal souIssueCnt = new BigDecimal(0);
        BigDecimal souIssueCntZen = new BigDecimal(0);
        BigDecimal souChargeKin = new BigDecimal(0);
        BigDecimal souChargeKinZen = new BigDecimal(0);
        BigDecimal souChargeKen = new BigDecimal(0);
        BigDecimal souChargeKenZen = new BigDecimal(0);
        BigDecimal souKyakusu = new BigDecimal(0);
        BigDecimal souZenKyakusu = new BigDecimal(0);
        BigDecimal souKessaiKin = new BigDecimal(0);
        BigDecimal souKessaiKen = new BigDecimal(0);
        BigDecimal souZenKessaiKin = new BigDecimal(0);
        BigDecimal souZenKessaiKen = new BigDecimal(0);
        BigDecimal souChargeKinCancel = new BigDecimal(0);
        BigDecimal souChargeKenCancel = new BigDecimal(0);
        BigDecimal souUseKinCancel = new BigDecimal(0);
        BigDecimal souUseKenCancel = new BigDecimal(0);
        BigDecimal souBonusVIssue = new BigDecimal(0);
        BigDecimal souBonusVUse = new BigDecimal(0);
        BigDecimal souCouponVIssue = new BigDecimal(0);
        BigDecimal souCouponVUse = new BigDecimal(0);
        BigDecimal souZandaka = new BigDecimal(0);


        List uriageList = new ArrayList();
        uriageList = (List)map.get("sibuUriList");

        for (int i = 0; i < uriageList.size(); i++ ) {
            TrnSibuUriageNipoRelate uriSeg = (TrnSibuUriageNipoRelate)uriageList.get(i);
            // 支部のみ対象(rclassがnoclass("")であること
            if ( uriSeg.getRClass().equals( noClass ) ) {
                souUri = souUri.add( numNull(uriSeg.getUriage()) );
                souYos = souYos.add(  numNull(uriSeg.getSibuYosan()) );
                souZenUri = souZenUri.add(  numNull(uriSeg.getZenUriage()) );
                souCount = souCount.add(  numNull(uriSeg.getTenpoCount()) );
                souZenCount = souZenCount.add(  numNull(uriSeg.getZenTenpoCount()) );
                souYosanMiseCnt = souYosanMiseCnt.add( numNull(uriSeg.getYosanMiseCnt()) );

                souIssueCnt = souIssueCnt.add( numNull(uriSeg.getIssueCnt()) );
                souChargeKin = souChargeKin.add( numNull(uriSeg.getChargeKin()) );
                souChargeKen = souChargeKen.add( numNull(uriSeg.getChargeKen()) );
                souIssueCntZen = souIssueCntZen.add( numNull(uriSeg.getZenIssueCnt()) );
                souChargeKinZen = souChargeKinZen.add( numNull(uriSeg.getZenChargeKin()) );
                souChargeKenZen = souChargeKenZen.add( numNull(uriSeg.getZenChargeKen()) );
                souKyakusu = souKyakusu.add( numNull(uriSeg.getKyakusu()) );
                souZenKyakusu = souZenKyakusu.add( numNull(uriSeg.getZenKyakusu()) );
                souKessaiKin = souKessaiKin.add(numNull(uriSeg.getKessaiKin()));
                souZenKessaiKin = souZenKessaiKin.add(numNull(uriSeg.getZenKessaiKin()));
                souKessaiKen = souKessaiKen.add(numNull(uriSeg.getKessaiKen()));
                souZenKessaiKen = souZenKessaiKen.add(numNull(uriSeg.getZenKessaiKen()));

                souChargeKinCancel = souChargeKinCancel.add( numNull(uriSeg.getChargeKinCancel()) );
                souChargeKenCancel = souChargeKenCancel.add( numNull(uriSeg.getChargeKenCancel()) );
                souUseKinCancel = souUseKinCancel.add( numNull(uriSeg.getUseKinCancel()) );
                souUseKenCancel = souUseKenCancel.add( numNull(uriSeg.getUseKenCancel()) );
                souBonusVIssue = souBonusVIssue.add(numNull(uriSeg.getBonusVIssue()));
                souBonusVUse = souBonusVUse.add(numNull(uriSeg.getBonusVUse()));
                souCouponVIssue = souCouponVIssue.add(numNull(uriSeg.getCouponVIssue()));
                souCouponVUse = souCouponVUse.add(numNull(uriSeg.getCouponVUse()));
                souZandaka = souZandaka.add(numNull(uriSeg.getZandaka()));

                companyCd = uriSeg.getCompanyCd();
            }
        }

        //本部合計を追加
        if(honbuUriageDate != null){
            souCount = souCount.add(  numNull(honbuUriageDate.getTenpoCount()) );
            souZenCount = souZenCount.add(  numNull(honbuUriageDate.getZenTenpoCount()) );
            souYosanMiseCnt = souYosanMiseCnt.add( numNull(honbuUriageDate.getYosanMiseCnt()) );
            souUri = souUri.add( numNull(honbuUriageDate.getUriage()) );
            souYos = souYos.add(  numNull(honbuUriageDate.getSibuYosan()) );
            souZenUri = souZenUri.add(  numNull(honbuUriageDate.getZenUriage()) );
            souKyakusu = souKyakusu.add( numNull(honbuUriageDate.getKyakusu()) );
            souZenKyakusu = souZenKyakusu.add( numNull(honbuUriageDate.getZenKyakusu()) );
            souIssueCnt = souIssueCnt.add( numNull(honbuUriageDate.getIssueCnt()) );
            souChargeKin = souChargeKin.add( numNull(honbuUriageDate.getChargeKin()) );
            souChargeKen = souChargeKen.add( numNull(honbuUriageDate.getChargeKen()) );
            souIssueCntZen = souIssueCntZen.add( numNull(honbuUriageDate.getZenIssueCnt()) );
            souChargeKinZen = souChargeKinZen.add( numNull(honbuUriageDate.getZenChargeKin()) );
            souChargeKenZen = souChargeKenZen.add( numNull(honbuUriageDate.getZenChargeKen()) );
            souKessaiKin = souKessaiKin.add(numNull(honbuUriageDate.getKessaiKin()));
            souZenKessaiKin = souZenKessaiKin.add(numNull(honbuUriageDate.getZenKessaiKin()));
            souKessaiKen = souKessaiKen.add(numNull(honbuUriageDate.getKessaiKen()));
            souZenKessaiKen = souZenKessaiKen.add(numNull(honbuUriageDate.getZenKessaiKen()));
            souChargeKinCancel = souChargeKinCancel.add( numNull(honbuUriageDate.getChargeKinCancel()) );
            souChargeKenCancel = souChargeKenCancel.add( numNull(honbuUriageDate.getChargeKenCancel()) );
            souUseKinCancel = souUseKinCancel.add( numNull(honbuUriageDate.getUseKinCancel()) );
            souUseKenCancel = souUseKenCancel.add( numNull(honbuUriageDate.getUseKenCancel()) );
            souBonusVIssue = souBonusVIssue.add(numNull(honbuUriageDate.getBonusVIssue()));
            souBonusVUse = souBonusVUse.add(numNull(honbuUriageDate.getBonusVUse()));
            souCouponVIssue = souCouponVIssue.add(numNull(honbuUriageDate.getCouponVIssue()));
            souCouponVUse = souCouponVUse.add(numNull(honbuUriageDate.getCouponVUse()));
            souZandaka = souZandaka.add(numNull(honbuUriageDate.getZandaka()));

        }

        TrnSibuUriageNipoRelate uriTou = new TrnSibuUriageNipoRelate();

        uriTou.setRClass( rClassSum5 );
        uriTou.setCompanyCd( companyCd );
        uriTou.setSibuCd( "ALL" );
        uriTou.setSibuName( "総合計");
        uriTou.setTenpoCount( souCount );
        uriTou.setZenTenpoCount( souZenCount );
        uriTou.setSibuNameClass( noClass );
        uriTou.setUriage( souUri );
        uriTou.setSibuYosan( souYos );
        uriTou.setYosanMiseCnt( souYosanMiseCnt );
        uriTou.setZenUriage( souZenUri );
        uriTou.setDispKbn( "DISP" );
        uriTou.setIssueCnt( souIssueCnt );
        uriTou.setIssueCntClass( bodyNumClass );
        uriTou.setChargeKin( souChargeKin );
        uriTou.setChargeKinClass( bodyNumClass );
        uriTou.setChargeKen( souChargeKen );
        uriTou.setChargeKenClass( bodyNumClass );
        uriTou.setZenIssueCnt( souIssueCntZen );
        uriTou.setZenChargeKin( souChargeKinZen );
        uriTou.setZenChargeKen( souChargeKenZen );
        uriTou.setZenKyakusu( souZenKyakusu );
        uriTou.setKyakusu( souKyakusu );
        uriTou.setKessaiKin( souKessaiKin );
        uriTou.setKessaiKinClass( bodyNumClass );
        uriTou.setZenKessaiKin( souZenKessaiKin );
        uriTou.setKessaiKen( souKessaiKen );
        uriTou.setKessaiKenClass( bodyNumClass );
        uriTou.setZenKessaiKen( souZenKessaiKen );
        uriTou.setChargeKinCancel(souChargeKinCancel);
        uriTou.setChargeKenCancel(souChargeKenCancel);
        uriTou.setUseKinCancel(souUseKinCancel);
        uriTou.setUseKenCancel(souUseKenCancel);
        uriTou.setBonusVIssue(souBonusVIssue);
        uriTou.setBonusVUse(souBonusVUse);
        uriTou.setCouponVIssue(souCouponVIssue );
        uriTou.setCouponVUse(souCouponVUse );
        uriTou.setZandaka(souZandaka );

        uriTou = calcHiritu( uriTou );

        uri.add( honbuUriageDate );
        uri.add( uriTou );

        Map paramMap = new HashMap();
        paramMap.put("uriage",uriTou);
        returnMap.put("uriage",uri);

        return returnMap;
    }
    /**
     * セグメントごとの売上、予算の取得(モス、モス以外合計)
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
    public Map segList(Map paramMap ) {

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
        String yosanDtFrom = (String)paramMap.get( KIKAN_FROM );
        String yosanDtTo = (String)paramMap.get( KIKAN_TO );

        if ( tenpoShu.equals( TenpoShubetu.CODE_ZENNEN ) ) {
            dataShuKbn = "1";
        } else if ( tenpoShu.equals( TenpoShubetu.CODE_YOSAN ) ) {
            dataShuKbn = "2";
        } else if ( tenpoShu.equals( TenpoShubetu.CODE_SIN ) ) {
            dataShuKbn = "3";
        } else {
        }

        String taishoJoken = "dogetu";
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
// add USI蔡 2018/08/10 begin --前年同日を追加
        else if (dataShu.equals( NipoZennenDataShubetu.CODE_DOJITU )) {
        	taishoJoken = "dojitu";
        }
// add USI蔡 2018/08/10 end

        // セグメントリスト作成
        List segmentList = new ArrayList();

        List segtpList = getMstSegmentInfoDao().getSegmentInfo( companyCd );

        for ( int i = 0 ; i < segtpList.size() ; i++ ) {
            MstSegmentInfo entity = (MstSegmentInfo)segtpList.get(i);
            segmentList.add( entity.getSegmentType() );
        }
        List uriageSegList = new ArrayList();

        if (  taishoKikan.equals("APPMONTH") ) {
            kikanFrom = kikanFrom.substring(0,6);
        }

        // 月報
        if ( taishoKikan.equals("MONTH") || taishoKikan.equals("KIBETU" )) {
            uriageSegList = getTrnUriageSegInfoDao().getDougetuSegUriageGepo( companyCd
                                                                                ,userId
                                                                                ,tenpoShu
                                                                                ,dataShu
                                                                                ,taishoKikan
                                                                                ,kikanFrom
                                                                                ,kikanTo
                                                                                ,limitFlg
                                                                                ,areaDaiFlg
                                                                                ,taishoTenpo
                                                                                ,dataShuKbn
                                                                                ,segmentList,
                                                                                taishoJoken);
        // 期日指定日報
        } else if ( taishoKikan.equals("DAY1" ) || taishoKikan.equals("DAYS" ) ) {
            uriageSegList = getTrnUriageSegInfoDao().getDougetuSegUriage( companyCd
                                                                        ,userId
                                                                        ,tenpoShu
                                                                        ,dataShu
                                                                        ,taishoKikan
                                                                        ,kikanFrom
                                                                        ,kikanTo
                                                                        ,limitFlg
                                                                        ,areaDaiFlg
                                                                        ,taishoTenpo
                                                                        ,dataShuKbn
                                                                        ,segmentList
                                                                        ,taishoJoken);

        } else if ( taishoKikan.equals("APPMONTH")) {
            uriageSegList = getTrnUriageSegInfoDao().getSegUriageTogetuGepo( companyCd
                    ,userId
                    ,tenpoShu
                    ,dataShu
                    ,taishoKikan
                    ,kikanFrom
                    ,kikanTo
                    ,limitFlg
                    ,areaDaiFlg
                    ,taishoTenpo
                    ,dataShuKbn
                    ,segmentList
                    ,taishoJoken
                    ,yosanDtFrom
                    ,yosanDtTo);


       }

        Map resultMap = new HashMap();



        resultMap.put("uriage",uriageSegList);
// 総合計取得処理変更(セグメント計→支部計)
        resultMap.put("sibuUriList", paramMap.get("uriageList") ) ;
        resultMap.put("honbuUriageDate", paramMap.get("honbuUriageList") ) ;
        resultMap.put("companyCd", companyCd);

        resultMap = getSegReadJustMap( resultMap );
        BigDecimal yosanMiseCnt = new BigDecimal("0");
        if(uriageSegList.size() > 0) {
            TrnSibuUriageNipoSegRelate trnYosan =
              (TrnSibuUriageNipoSegRelate)uriageSegList.get(uriageSegList.size() - 1 );
          yosanMiseCnt = trnYosan.getMiseCount();

        }
        //予算対象店舗数設定
        resultMap.put(MoscardNipoConstants.MAP_YOSAN_TENPO_COUNT, yosanMiseCnt);

        return resultMap;
    }
    /**
     * 支部単位 → エリア単位 → 事業単位 → 本部単位へサマリする(売上用)
     * @param list
     * @return
     *
     * @modifier xkinu  2007/06/05 クローズ店予算表示対応
     */
    public List sumUriageList( List uriageList ,String kbnTani) {

        TrnSibuUriageNipoRelate uriNipo = (TrnSibuUriageNipoRelate)uriageList.get(0);

        // エリア計(売上系)
        BigDecimal areaTouTenpoKei = uriNipo.getTenpoCount();
        BigDecimal areaZenTenpoKei = uriNipo.getZenTenpoCount();
        BigDecimal areaUriageKei = uriNipo.getUriage();
        BigDecimal areaYosanKei = uriNipo.getSibuYosan();
        BigDecimal areaZenUriKei =  uriNipo.getZenUriage();
        BigDecimal areaIssueCnt = uriNipo.getIssueCnt();
        BigDecimal areaZenIssueCnt =  uriNipo.getZenIssueCnt();
        BigDecimal areaChargeKin= uriNipo.getChargeKin();
        BigDecimal areaZenChargeKin= uriNipo.getZenChargeKin();
        BigDecimal areaChargeKen= uriNipo.getChargeKen();
        BigDecimal areaZenChargeKen= uriNipo.getZenChargeKen();
        BigDecimal areaKyakusu= uriNipo.getKyakusu();
        BigDecimal areaZenKyakusu= uriNipo.getZenKyakusu();
        BigDecimal areaKessaiKin= uriNipo.getKessaiKin();
        BigDecimal areaZenKessaiKin= uriNipo.getZenKessaiKin();
        BigDecimal areaKessaiKen= uriNipo.getKessaiKen();
        BigDecimal areaZenKessaiKen= uriNipo.getZenKessaiKen();
        BigDecimal areaChargeKinCancel = uriNipo.getChargeKinCancel();
        BigDecimal areaChargeKenCancel = uriNipo.getChargeKenCancel();
        BigDecimal areaUseKinCancel = uriNipo.getUseKinCancel();
        BigDecimal areaUseKenCancel = uriNipo.getUseKenCancel();
        BigDecimal areaBonusVIssue = uriNipo.getBonusVIssue();
        BigDecimal areaBonusVUse = uriNipo.getBonusVUse();
        BigDecimal areaCouponVIssue = uriNipo.getCouponVIssue();
        BigDecimal areaCouponVUse = uriNipo.getCouponVUse();
        BigDecimal areaZandaka = uriNipo.getZandaka();

// 2007/06/05 ADD start xkinu クローズ店予算表示対応
        BigDecimal yosanTenpoKei =  uriNipo.getYosanMiseCnt();
// 2007/06/05 ADD end xkinu クローズ店予算表示対応
        List areaUriageList = new ArrayList();

        // 合計を求める
        for ( int i = 1; i < uriageList.size(); i++ ) {
            uriNipo = (TrnSibuUriageNipoRelate)uriageList.get(i);
            TrnSibuUriageNipoRelate befUriNipo = (TrnSibuUriageNipoRelate)uriageList.get( i - 1 ) ;

            String curCd = new String();
            String befCurCd = new String();

            // 対象コード特定
            if ( kbnTani.equals( area ) ) {
                curCd = uriNipo.getSlareaCd().trim();
                befCurCd = befUriNipo.getSlareaCd().trim();
            } else if ( kbnTani.equals( jigyo ) ) {
                curCd = uriNipo.getJigyoCd().trim();
                befCurCd = befUriNipo.getJigyoCd().trim();
            } else if ( kbnTani.equals( toukatu ) ) {
                curCd = uriNipo.getHonbuCd().trim();
                befCurCd = befUriNipo.getHonbuCd().trim();
            }

            // エリア毎の計を求める(売上)
            if ( curCd.equals( befCurCd ) ) {
                areaTouTenpoKei = areaTouTenpoKei.add( uriNipo.getTenpoCount() );
                areaZenTenpoKei = areaZenTenpoKei.add( uriNipo.getZenTenpoCount() );
                areaIssueCnt = areaIssueCnt.add( uriNipo.getIssueCnt() );
                areaZenIssueCnt = areaZenIssueCnt.add( uriNipo.getZenIssueCnt() );
                areaChargeKin = areaChargeKin.add( uriNipo.getChargeKin() );
                areaZenChargeKen = areaZenChargeKen.add( uriNipo.getZenChargeKen() );
                areaZenChargeKin = areaZenChargeKin.add( uriNipo.getZenChargeKin() );
                areaChargeKen = areaChargeKen.add( uriNipo.getChargeKen() );
                areaUriageKei = areaUriageKei.add( uriNipo.getUriage() );
                areaZenUriKei = areaZenUriKei.add( uriNipo.getZenUriage() );
                areaKyakusu = areaKyakusu.add( uriNipo.getKyakusu() );
                areaZenKyakusu = areaZenKyakusu.add( uriNipo.getZenKyakusu() );
                areaYosanKei = areaYosanKei.add( uriNipo.getSibuYosan() );
                areaKessaiKin = areaKessaiKin.add( uriNipo.getKessaiKin() );
                areaZenKessaiKin = areaZenKessaiKin.add( uriNipo.getZenKessaiKin() );
                areaKessaiKen = areaKessaiKen.add( uriNipo.getKessaiKen() );
                areaZenKessaiKen = areaZenKessaiKen.add( uriNipo.getZenKessaiKen() );
                areaChargeKinCancel = areaChargeKinCancel.add(uriNipo.getChargeKinCancel());
                areaChargeKenCancel = areaChargeKenCancel.add(uriNipo.getChargeKenCancel());
                areaUseKinCancel = areaUseKinCancel.add(uriNipo.getUseKinCancel());
                areaUseKenCancel = areaUseKenCancel.add(uriNipo.getUseKenCancel());
                areaBonusVIssue = areaBonusVIssue.add(uriNipo.getBonusVIssue());
                areaBonusVUse = areaBonusVUse.add(uriNipo.getBonusVUse());
                areaCouponVIssue = areaCouponVIssue.add( uriNipo.getCouponVIssue() );
                areaCouponVUse = areaCouponVUse.add( uriNipo.getCouponVUse() );
                areaZandaka = areaZandaka.add( uriNipo.getZandaka() );
// 2007/06/05 ADD start xkinu クローズ店予算表示対応
                yosanTenpoKei = yosanTenpoKei.add( uriNipo.getYosanMiseCnt() );
// 2007/06/05 ADD end xkinu クローズ店予算表示対応
            } else {
                TrnSibuUriageNipoRelate uriKei = new TrnSibuUriageNipoRelate();

                if ( kbnTani.equals( area ) ) {
                    uriKei.setRClass( rClassSum1 );
                    uriKei.setSibuCd( befUriNipo.getSlareaCd() );
                    uriKei.setSibuName( befUriNipo.getSlareaName().trim() + "計");
                    uriKei.setSibuNameClass( noClass );
                } else if ( kbnTani.equals( jigyo ) ) {
                    uriKei.setRClass( rClassSum2 );
                    uriKei.setSibuCd( befUriNipo.getJigyoCd() );
                    uriKei.setSibuName( befUriNipo.getJigyoName().trim() + "計");
                    uriKei.setSibuNameClass( noClass );
                } else if ( kbnTani.equals( toukatu ) ) {
                    uriKei.setRClass( rClassSum3 );
                    uriKei.setSibuCd( befUriNipo.getHonbuCd() );
                    uriKei.setSibuName( befUriNipo.getHonbuName().trim() + "計" );
                    uriKei.setSibuNameClass( noClass );
                }
                uriKei.setCompanyCd( befUriNipo.getCompanyCd() );
                uriKei.setDispKbn( "DISP" );
                uriKei.setHonbuCd( befUriNipo.getHonbuCd() );
                uriKei.setHonbuName( befUriNipo.getHonbuName() );
                uriKei.setJigyoCd( befUriNipo.getJigyoCd() );
                uriKei.setJigyoName( befUriNipo.getJigyoName() );
                uriKei.setSlareaCd( befUriNipo.getSlareaCd() );
                uriKei.setSlareaName( befUriNipo.getSlareaName() );
                uriKei.setTenpoCount( areaTouTenpoKei );
                uriKei.setZenTenpoCount( areaZenTenpoKei );
                uriKei.setIssueCnt( areaIssueCnt );
                uriKei.setIssueCntClass( bodyNumClass );
// 2007/06/05 ADD start xkinu クローズ店予算表示対応
                uriKei.setYosanMiseCnt( yosanTenpoKei );
// 2007/06/05 ADD end xkinu クローズ店予算表示対応
                uriKei.setChargeKin( areaChargeKin );
                uriKei.setChargeKinClass( bodyNumClass );
                uriKei.setZenUriage( areaZenUriKei );
                uriKei.setZenIssueCnt( areaZenIssueCnt );
                uriKei.setZenIssueCntClass( bodyNumClass );
                uriKei.setUriage( areaUriageKei );
                uriKei.setZenChargeKin( areaZenChargeKin );
                uriKei.setZenChargeKinClass( bodyNumClass );
                uriKei.setChargeKen( areaChargeKen );
                uriKei.setChargeKenClass( bodyNumClass );
                uriKei.setZenChargeKen( areaZenChargeKen );
                uriKei.setZenChargeKenClass( bodyNumClass );
                uriKei.setKyakusu( areaKyakusu );
                uriKei.setZenKyakusu( areaZenKyakusu );
                uriKei.setKessaiKin( areaKessaiKin );
                uriKei.setKessaiKinClass( bodyNumClass );
                uriKei.setZenKessaiKin( areaZenKessaiKin );
                uriKei.setKessaiKen( areaKessaiKen );
                uriKei.setKessaiKenClass( bodyNumClass );
                uriKei.setZenKessaiKen( areaZenKessaiKen );
                uriKei.setSibuYosan( areaYosanKei );
                uriKei.setChargeKinCancel(areaUseKinCancel);
                uriKei.setChargeKenCancel(areaChargeKenCancel);
                uriKei.setUseKinCancel(areaUseKinCancel);
                uriKei.setUseKenCancel(areaUseKenCancel);
                uriKei.setBonusVIssue(areaBonusVIssue);
                uriKei.setBonusVUse(areaBonusVUse);
                uriKei.setCouponVIssue(areaCouponVIssue );
                uriKei.setCouponVUse(areaCouponVUse );
                uriKei.setZandaka(areaZandaka );

                TrnSibuUriageNipoRelate areaUriKei = calcHiritu( uriKei );

                areaUriageList.add( areaUriKei );

                // 設定
                areaTouTenpoKei = uriNipo.getTenpoCount();
                areaZenTenpoKei = uriNipo.getZenTenpoCount();
                areaIssueCnt = uriNipo.getIssueCnt();
                areaChargeKin =  uriNipo.getChargeKin();
                areaZenChargeKin =  uriNipo.getZenChargeKin();
                areaZenIssueCnt = uriNipo.getZenIssueCnt();
                areaZenUriKei = uriNipo.getZenUriage();
                areaUriageKei = uriNipo.getUriage();
                areaChargeKen =  uriNipo.getChargeKen();
                areaKyakusu =  uriNipo.getKyakusu();
                areaZenKyakusu =  uriNipo.getZenKyakusu();
                areaZenChargeKen =  uriNipo.getZenChargeKen();
                areaKessaiKin =  uriNipo.getKessaiKin();
                areaZenKessaiKin =  uriNipo.getZenKessaiKin();
                areaKessaiKen =  uriNipo.getKessaiKen();
                areaZenKessaiKen =  uriNipo.getZenKessaiKen();
                areaYosanKei = uriNipo.getSibuYosan();
// 2007/06/05 ADD start xkinu クローズ店予算表示対応
                yosanTenpoKei =  uriNipo.getYosanMiseCnt();
// 2007/06/05 ADD end xkinu クローズ店予算表示対応
                areaChargeKinCancel = uriNipo.getChargeKinCancel();
                areaChargeKenCancel = uriNipo.getChargeKenCancel();
                areaUseKinCancel = uriNipo.getUseKinCancel();
                areaUseKenCancel = uriNipo.getUseKenCancel();
                areaBonusVIssue = uriNipo.getBonusVIssue();
                areaBonusVUse = uriNipo.getBonusVUse();
                areaCouponVIssue = uriNipo.getCouponVIssue();
                areaCouponVUse = uriNipo.getCouponVUse();
                areaZandaka = uriNipo.getZandaka();

            }
        }

        TrnSibuUriageNipoRelate uriKei = new TrnSibuUriageNipoRelate();

        if ( kbnTani.equals( area ) ) {
            uriKei.setRClass( rClassSum1 );
            uriKei.setSibuCd( uriNipo.getSlareaCd() );
            uriKei.setSibuName( uriNipo.getSlareaName().trim() + "計" );
        } else if ( kbnTani.equals( jigyo ) ) {
            uriKei.setRClass( rClassSum2 );
            uriKei.setSibuCd( uriNipo.getJigyoCd() );
            uriKei.setSibuName( uriNipo.getJigyoName().trim() + "計" );
        } else if ( kbnTani.equals( toukatu ) ) {
            uriKei.setRClass( rClassSum3 );
            uriKei.setSibuCd( uriNipo.getHonbuCd() );
            uriKei.setSibuName( uriNipo.getHonbuName().trim() + "計" );
        }
        uriKei.setCompanyCd( uriNipo.getCompanyCd() );
        uriKei.setDispKbn( "DISP" );
        uriKei.setHonbuCd( uriNipo.getHonbuCd() );
        uriKei.setHonbuName( uriNipo.getHonbuName() );
        uriKei.setJigyoCd( uriNipo.getJigyoCd() );
        uriKei.setJigyoName( uriNipo.getJigyoName() );
        uriKei.setSlareaCd( uriNipo.getSlareaCd() );
        uriKei.setSlareaName( uriNipo.getSlareaName() );
        uriKei.setTenpoCount( areaTouTenpoKei );
        uriKei.setZenTenpoCount( areaZenTenpoKei );
        uriKei.setIssueCnt( areaIssueCnt );
        uriKei.setIssueCntClass( bodyNumClass );
// 2007/06/05 ADD start xkinu クローズ店予算表示対応
        uriKei.setYosanMiseCnt( yosanTenpoKei );
// 2007/06/05 ADD end xkinu クローズ店予算表示対応
        uriKei.setChargeKin( areaChargeKin );
        uriKei.setChargeKinClass( bodyNumClass );
        uriKei.setZenUriage( areaZenUriKei );
        uriKei.setZenIssueCnt( areaZenIssueCnt );
        uriKei.setZenIssueCntClass( bodyNumClass );
        uriKei.setUriage( areaUriageKei );
        uriKei.setZenChargeKin( areaZenChargeKin );
        uriKei.setZenChargeKinClass( bodyNumClass );
        uriKei.setChargeKen( areaChargeKen );
        uriKei.setChargeKenClass( bodyNumClass );
        uriKei.setZenChargeKen( areaZenChargeKen );
        uriKei.setZenChargeKenClass( bodyNumClass );
        uriKei.setKyakusu( areaKyakusu );
        uriKei.setZenKyakusu( areaZenKyakusu );
        uriKei.setKessaiKin( areaKessaiKin );
        uriKei.setKessaiKinClass( bodyNumClass );
        uriKei.setZenKessaiKin( areaZenKessaiKin );
        uriKei.setKessaiKen( areaKessaiKen );
        uriKei.setKessaiKenClass( bodyNumClass );
        uriKei.setZenKessaiKen( areaZenKessaiKen );
        uriKei.setSibuYosan( areaYosanKei );

        uriKei.setChargeKinCancel(areaChargeKinCancel);
        uriKei.setChargeKenCancel(areaChargeKenCancel);
        uriKei.setUseKinCancel(areaUseKinCancel);
        uriKei.setUseKenCancel(areaUseKenCancel);
        uriKei.setBonusVIssue(areaBonusVIssue);
        uriKei.setBonusVUse(areaBonusVUse);
        uriKei.setCouponVIssue(areaCouponVIssue );
        uriKei.setCouponVUse(areaCouponVUse );
        uriKei.setZandaka(areaZandaka );

        TrnSibuUriageNipoRelate areaUriKei = calcHiritu( uriKei );

        areaUriageList.add( areaUriKei );

        return areaUriageList;
    }


    /**
     * エリア毎の計を求める
     * @param uriageList
     * @param kyakusuList
     * @return
     */
    public Map getResultMap( List uriageList ) {

        Map returnMap = new HashMap();

        // エリア計
        List areaUriageList = sumUriageList( uriageList,area);
        List totalUriageList = totalUriageInsertList( uriageList, areaUriageList, area );

        // 事業計
        List jigyoUriageList = sumUriageList( areaUriageList, jigyo );
        totalUriageList = totalUriageInsertList( totalUriageList,jigyoUriageList,jigyo);

        // 統括本部計
        List tokatuUriageList = sumUriageList( jigyoUriageList,toukatu);
        totalUriageList = totalUriageInsertList( totalUriageList,tokatuUriageList,toukatu);

        returnMap.put( "uriage",totalUriageList);

        return returnMap;
    }
    /**
     * 売上系、元リストに合計リストを挿入する
     * @param totalList
     * @param instList
     * @param kbnTani
     * @return
     */
    public List totalKyakuInsertList(List totalList, List instList, String kbnTani ) {
        List returnList = new ArrayList();

        for ( int i = 0; i < instList.size(); i++ ) {
            TrnSibuKyakusuNipoRelate kyakuKei = (TrnSibuKyakusuNipoRelate)instList.get(i);
            String curCd = new String();
            String befCurCd = new String();
            List onceList = new ArrayList();
            for ( int j = 0; j < totalList.size(); j++ ) {
                TrnSibuKyakusuNipoRelate toKyakuKei = (TrnSibuKyakusuNipoRelate)totalList.get(j);
                // 対象コードを特定
                if ( kbnTani.equals( area ) ) {
                   curCd = kyakuKei.getSlareaCd().trim();
                   befCurCd = toKyakuKei.getSlareaCd().trim();
                } else if ( kbnTani.equals( jigyo ) ) {
                    curCd = kyakuKei.getJigyoCd().trim();
                    befCurCd = toKyakuKei.getJigyoCd().trim();
                } else if ( kbnTani.equals( toukatu ) ) {
                    curCd = kyakuKei.getHonbuCd().trim();
                    befCurCd = toKyakuKei.getHonbuCd().trim();
                }
                if ( curCd.equals( befCurCd ) ) {
                    onceList.add( toKyakuKei );
                }
            }

            returnList.addAll( onceList );
            returnList.add( kyakuKei );
        }

        return returnList;
    }

    /**
     * 売上系、元リストに合計リストを挿入する
     * @param totalList
     * @param instList
     * @param kbnTani
     * @return
     */
    public List totalUriageInsertList(List totalList, List instList, String kbnTani ) {
        List returnList = new ArrayList();

        for ( int i = 0; i < instList.size(); i++ ) {
            TrnSibuUriageNipoRelate uriKei = (TrnSibuUriageNipoRelate)instList.get(i);
            String curCd = new String();
            String befCurCd = new String();
            List onceList = new ArrayList();
            for ( int j = 0; j < totalList.size(); j++ ) {
                TrnSibuUriageNipoRelate toUriKei = (TrnSibuUriageNipoRelate)totalList.get(j);
                // 対象コードを特定
                if ( kbnTani.equals( area ) ) {
                   curCd = uriKei.getSlareaCd().trim();
                   befCurCd = toUriKei.getSlareaCd().trim();
                } else if ( kbnTani.equals( jigyo ) ) {
                    curCd = uriKei.getJigyoCd().trim();
                    befCurCd = toUriKei.getJigyoCd().trim();
                } else if ( kbnTani.equals( toukatu ) ) {
                    curCd = uriKei.getHonbuCd().trim();
                    befCurCd = toUriKei.getHonbuCd().trim();
                }
                if ( curCd.equals( befCurCd ) ) {
                    onceList.add( toUriKei );
                }
            }

            returnList.addAll( onceList );
            returnList.add( uriKei );
        }

        return returnList;
    }

    /**
     * 画面表示用に整理する
     *
     * @param sibuList
     * @param uriageList
     * @param yosanList
     * @return
     *
     * @modifier xkinu  2007/06/05 クローズ店予算表示対応
     */
    public Map getReadjustMap(Map paramMap) {

        List sibuList = (List)paramMap.get("sibu");
        List uriageList = (List)paramMap.get("uriage");

        List resultUriageList = new ArrayList();
        Map returnMap = new HashMap();

        for( int i = 0; i < sibuList.size(); i++ ) {
            MstSibuInfo sibu = (MstSibuInfo)sibuList.get(i);
            TrnUriageInfo uriage = new TrnUriageInfo();
            if ( !sibu.getDispKbn().equals("NOTDISP") ) {
                //対象支部の売上を取得する
                for ( int j = 0; j < uriageList.size(); j++ ) {
                    TrnUriageInfo uri = (TrnUriageInfo)uriageList.get(j);
                    if ( (uri.getCompanyCd().trim()).equals( sibu.getCompanyCd().trim() )
                            && (uri.getSibuCd().trim()).equals( sibu.getSibuCd().trim() ) ) {
                        uriage = uri;
                        break;
                    }
                }
                // 売上タブ内容
                TrnSibuUriageNipoRelate uriNipo = new TrnSibuUriageNipoRelate();

                // DISPONLY対策
                BigDecimal urikin = new BigDecimal(0);
                BigDecimal urizen = new BigDecimal(0);
                BigDecimal kyaten = new BigDecimal(0);
                BigDecimal kyazen = new BigDecimal(0);
                BigDecimal kyaCou = new BigDecimal(0);
                BigDecimal couZen = new BigDecimal(0);
                BigDecimal issueCnt = new BigDecimal(0);
                BigDecimal chargeKin = new BigDecimal(0);
                BigDecimal chargeKen = new BigDecimal(0);
                BigDecimal zenIssueCnt = new BigDecimal(0);
                BigDecimal zenChargeKin = new BigDecimal(0);
                BigDecimal zenChargeKen = new BigDecimal(0);
                BigDecimal kessaiKin = new BigDecimal(0);
                BigDecimal kessaiKen = new BigDecimal(0);
                BigDecimal zenKessaiKin = new BigDecimal(0);
                BigDecimal zenKessaiKen = new BigDecimal(0);
                BigDecimal chargeKinCancel = new BigDecimal(0);
                BigDecimal chargeKenCancel = new BigDecimal(0);
                BigDecimal useKinCancel = new BigDecimal(0);
                BigDecimal useKenCancel = new BigDecimal(0);
                BigDecimal bonusVIssue = new BigDecimal(0);
                BigDecimal bonusVUse = new BigDecimal(0);
                BigDecimal couponVIssue = new BigDecimal(0);
                BigDecimal couponVUse = new BigDecimal(0);
                BigDecimal zandaka = new BigDecimal(0);

                if ( sibu.getDispKbn().equals( "DISP" )) {
                    urikin = uriage.getUriage();
                    urizen = uriage.getUriageZen();
                    kyaten = uriage.getOpenKbn();
                    kyazen = uriage.getOpenKbnZen();
                    kyaCou = uriage.getKyakusu();
                    couZen = uriage.getKyakusuZen();
                    issueCnt = uriage.getIssueCnt();
                    chargeKin = uriage.getChargeKin();
                    chargeKen = uriage.getChargeKen();
                    zenIssueCnt = uriage.getZenIssueCnt();
                    zenChargeKin = uriage.getZenChargeKin();
                    zenChargeKen = uriage.getZenChargeKen();
                    kessaiKin = uriage.getKessaiKin();
                    kessaiKen = uriage.getKessaiKen();
                    zenKessaiKin = uriage.getZenKessaiKin();
                    zenKessaiKen = uriage.getZenKessaiKen();
                    chargeKinCancel = uriage.getChargeKinCancel();
                    chargeKenCancel = uriage.getChargeKenCancel();
                    useKinCancel = uriage.getUseKinCancel();
                    useKenCancel = uriage.getUseKenCancel();
                    bonusVIssue = uriage.getBonusVIssue();
                    bonusVUse = uriage.getBonusVUse();
                    couponVIssue = uriage.getCouponVIssue();
                    couponVUse = uriage.getCouponVUse();
                    zandaka = uriage.getZandaka();

                }

                uriNipo.setRClass( noClass );
                uriNipo.setCompanyCd( sibu.getCompanyCd() );
                uriNipo.setSibuCd( sibu.getSibuCd() );
                uriNipo.setSibuName( sibu.getSibuName().trim() );
                uriNipo.setSibuNameClass( noClass );
                uriNipo.setHonbuCd( sibu.getHonbuCd() );
                uriNipo.setHonbuName( sibu.getHonbuName().trim() );
                uriNipo.setJigyoCd( sibu.getJigyouCd() );
                uriNipo.setJigyoName( sibu.getJigyouName().trim() );
                uriNipo.setSlareaCd( sibu.getSlareaCd() );
                uriNipo.setSlareaName( sibu.getSlareaName().trim() );
                uriNipo.setUriage( urikin );
                uriNipo.setIssueCnt( issueCnt );
                uriNipo.setIssueCntClass( bodyNumClass );
                uriNipo.setChargeKin( chargeKin );
                uriNipo.setChargeKinClass( bodyNumClass );
                uriNipo.setChargeKen( chargeKen );
                uriNipo.setChargeKenClass( bodyNumClass );
                uriNipo.setZenUriage( urizen );
                uriNipo.setTenpoCount( kyaten );
                uriNipo.setZenTenpoCount( kyazen );
                uriNipo.setZenIssueCnt( zenIssueCnt );
                uriNipo.setZenChargeKin( zenChargeKin );
                uriNipo.setZenChargeKen( zenChargeKen );
                uriNipo.setKyakusu( kyaCou );
                uriNipo.setZenKyakusu( couZen );
                uriNipo.setKessaiKin( kessaiKin );
                uriNipo.setKessaiKinClass( bodyNumClass );
                uriNipo.setKessaiKen( kessaiKen );
                uriNipo.setKessaiKenClass( bodyNumClass );
                uriNipo.setZenKessaiKin( zenKessaiKin );
                uriNipo.setZenKessaiKen( zenKessaiKen );
                uriNipo.setChargeKinCancel(chargeKinCancel);
                uriNipo.setChargeKenCancel(chargeKenCancel);
                uriNipo.setUseKinCancel(useKinCancel);
                uriNipo.setUseKenCancel(useKenCancel);
                uriNipo.setBonusVIssue(bonusVIssue);
                uriNipo.setBonusVUse(bonusVUse);
                uriNipo.setCouponVIssue(couponVIssue );
                uriNipo.setCouponVUse(couponVUse );
                uriNipo.setZandaka(zandaka );

                if ( uriage.getSibuYosan() != null ) {
                    uriNipo.setSibuYosan( uriage.getSibuYosan() );
// 2007/06/05 ADD start xkinu  クローズ店予算表示対応
                    //予算対象店舗数設定
                    uriNipo.setYosanMiseCnt( uriage.getMiseCount() );
// 2007/06/05 ADD start xkinu  クローズ店予算表示対応
                } else {
                    uriNipo.setSibuYosan( new BigDecimal(0) );
                }
                uriNipo.setDispKbn( sibu.getDispKbn() );

                TrnSibuUriageNipoRelate resultUriage = calcHiritu( uriNipo );

                resultUriageList.add( resultUriage );

                Map map = new HashMap();
                map.put("uriage",resultUriage );

            }
        }
        returnMap.put("uriage",resultUriageList);

        return returnMap;

    }

    /**
     * 売上比率算出＋クラス指定
     * @param uriNipo
     * @return
     */
    public TrnSibuUriageNipoRelate calcHiritu( TrnSibuUriageNipoRelate uriNipo ) {
        //達成率
        BigDecimal tasRitu = Calculator.percentage(uriNipo.getUriage(),uriNipo.getSibuYosan(),2);
        //売上率
        BigDecimal uriHi = Calculator.percentage(uriNipo.getUriage(),uriNipo.getZenUriage(),2);
        //発行枚数前年比計算
        BigDecimal rituIssueCnt = Calculator.percentage(uriNipo.getIssueCnt(),uriNipo.getZenIssueCnt(),2);
        //チャージ金額売上比計算
        BigDecimal uriHiChargeKin = Calculator.percentage(uriNipo.getChargeKin(),uriNipo.getUriage(),2);
        //チャージ金額前年比計算
        BigDecimal rituChargeKin = Calculator.percentage(uriNipo.getChargeKin(),uriNipo.getZenChargeKin(),2);
        //チャージ件数客数比計算
        BigDecimal rituChargeKyaku = Calculator.percentage(uriNipo.getChargeKen(),uriNipo.getKyakusu(),2);
        //チャージ件数前年比計算
        BigDecimal rituChargeKen = Calculator.percentage(uriNipo.getChargeKen(),uriNipo.getZenChargeKen(),2);
        // チャージ単価計算
        BigDecimal chargeTanka = Calculator.divide(uriNipo.getChargeKin(),uriNipo.getChargeKen());
        // 前年チャージ単価計算
        BigDecimal zenChargeTanka = Calculator.divide(uriNipo.getZenChargeKin(),uriNipo.getZenChargeKen());
        // 客単価計算
        BigDecimal kyakusuTanka = Calculator.divide(uriNipo.getUriage(),uriNipo.getKyakusu());
        // 前年客単価計算
        BigDecimal zenKyakuTanka = Calculator.divide(uriNipo.getZenUriage(),uriNipo.getZenKyakusu());
        // チャージ単価比
        BigDecimal chargeTankahi = Calculator.percentage(chargeTanka,kyakusuTanka,2);
        // チャージ前年比
        BigDecimal zenChargeTankahi = Calculator.percentage(chargeTanka, zenChargeTanka,2);
        // 決済金売上比計算
        BigDecimal uriHiKessaiKin = Calculator.percentage(uriNipo.getKessaiKin(),uriNipo.getUriage(),2);
        // 決済金前年比計算
        BigDecimal zenHiKessaiKin = Calculator.percentage(uriNipo.getKessaiKin(),uriNipo.getZenKessaiKin(),2);
        // 決済件数客数比計算
        BigDecimal kyakuHiKessaiKen = Calculator.percentage(uriNipo.getKessaiKen(),uriNipo.getKyakusu(),2);
        // 決済件数前年比計算
        BigDecimal zenHiKessaiKen = Calculator.percentage(uriNipo.getKessaiKen(),uriNipo.getZenKessaiKen(),2);
        // 決済金単価計算
        BigDecimal tankaKessaiKin = Calculator.divide(uriNipo.getKessaiKin(),uriNipo.getKessaiKen());
        // 前年決済金単価計算
        BigDecimal zenTankaKessaiKin = Calculator.divide(uriNipo.getZenKessaiKin(),uriNipo.getZenKessaiKen());
        // 決済金単価（単価比）計算
        BigDecimal tankaHiKessaiKin = Calculator.percentage(tankaKessaiKin,kyakusuTanka,2);
        // 決済金単価（前年比）計算
        BigDecimal zenTankaHiKessaiKin = Calculator.percentage(tankaKessaiKin,zenTankaKessaiKin,2);
        // 客単価（前年比）
        BigDecimal kyakuHi = Calculator.percentage( kyakusuTanka,zenKyakuTanka,2);
        // 客単価（単価比）
        BigDecimal kyakuTankaHi = Calculator.percentage( kyakusuTanka,zenKyakuTanka,2);


        if ( tasRitu.compareTo(bigZero) != 1 ) {
            tasRitu = new BigDecimal("0.00");
        }
        if ( uriHi.compareTo(bigZero) != 1 ) {
            uriHi = new BigDecimal("0.00");
        }
        if ( rituIssueCnt.compareTo(bigZero) != 1 ) {
            rituIssueCnt = new BigDecimal("0.00");
        }
        if ( uriHiChargeKin.compareTo(bigZero) != 1 ) {
            uriHiChargeKin = new BigDecimal("0.00");
        }
        if ( rituChargeKin.compareTo(bigZero) != 1 ) {
            rituChargeKin = new BigDecimal("0.00");
        }
        if ( rituChargeKyaku.compareTo(bigZero) != 1 ) {
            rituChargeKyaku = new BigDecimal("0.00");
        }
        if ( rituChargeKen.compareTo(bigZero) != 1 ) {
            rituChargeKen = new BigDecimal("0.00");
        }
        if ( zenChargeTankahi.compareTo(bigZero) != 1 ) {
            zenChargeTankahi = new BigDecimal("0.00");
        }
        if ( chargeTankahi.compareTo(bigZero) != 1 ) {
            chargeTankahi = new BigDecimal("0.00");
        }
        if ( zenChargeTankahi.compareTo(bigZero) != 1 ) {
            zenChargeTankahi = new BigDecimal("0.00");
        }
        //決済タブ設定
        if ( uriHiKessaiKin.compareTo(bigZero) != 1 ) {
            uriHiKessaiKin = new BigDecimal("0.00");
        }
        if ( zenHiKessaiKin.compareTo(bigZero) != 1 ) {
            zenHiKessaiKin = new BigDecimal("0.00");
        }
        if ( uriHiKessaiKin.compareTo(bigZero) != 1 ) {
            uriHiKessaiKin = new BigDecimal("0.00");
        }
        if ( kyakuHiKessaiKen.compareTo(bigZero) != 1 ) {
            kyakuHiKessaiKen = new BigDecimal("0.00");
        }
        if ( zenHiKessaiKen.compareTo(bigZero) != 1 ) {
            zenHiKessaiKen = new BigDecimal("0.00");
        }

        if ( tankaHiKessaiKin.compareTo(bigZero) != 1 ) {
            tankaHiKessaiKin = new BigDecimal("0.00");
        }
        if ( zenTankaHiKessaiKin.compareTo(bigZero) != 1 ) {
            zenTankaHiKessaiKin = new BigDecimal("0.00");
        }
        if ( kyakusuTanka.compareTo(bigZero) != 1 ) {
            kyakusuTanka = new BigDecimal("0");
        }
        if ( kyakuHi.compareTo(bigZero) != 1 ) {
            kyakuHi = new BigDecimal("0.00");
        }

        if ( kyakuTankaHi.compareTo(bigZero) != 1 ) {
            kyakuTankaHi = new BigDecimal("0.00");
        }

        uriNipo.setTasseiYosan( tasRitu );
        uriNipo.setZenHiSa( uriHi );

        uriNipo.setZenIssueCntHiritu( rituIssueCnt );
        uriNipo.setUrihiChargeKin( uriHiChargeKin );
        uriNipo.setZenHiChargeKin( rituChargeKin );
        uriNipo.setChargeKenKyakuHi( rituChargeKyaku );
        uriNipo.setChargeKenZenHi( rituChargeKen );
        uriNipo.setChargeTanka( chargeTanka );
        uriNipo.setZenChargeTanka( zenChargeTanka );
        uriNipo.setChargeTankaClass( bodyNumClass );
        uriNipo.setChargeTankahi( chargeTankahi );
        uriNipo.setZenChargeTankahi(zenChargeTankahi);
        uriNipo.setKyakuTanka( kyakusuTanka );
        uriNipo.setKyakuTankaZenhi(kyakuHi);
        uriNipo.setZenTanka( zenKyakuTanka );
        uriNipo.setKyakuTankaTanhi(kyakuTankaHi);

        //決済タブ
        uriNipo.setKessaiKinUriHi(uriHiKessaiKin);
        uriNipo.setKessaiKinZenHi(zenHiKessaiKin);
        uriNipo.setKessaiKenKyakuHi(kyakuHiKessaiKen);
        uriNipo.setKessaiKenZenHi(zenHiKessaiKen);
        uriNipo.setKessaiKinTanka(tankaKessaiKin);
        uriNipo.setKessaiKinTankaClass(bodyNumClass);
        uriNipo.setKessaiKinTankaHi(tankaHiKessaiKin);
        uriNipo.setKessaiKinZenTankaHi(zenTankaHiKessaiKin);
        uriNipo.setZenKessaiKinTanka(zenTankaKessaiKin);

        BigDecimal hi = new BigDecimal(100);

        if ( uriNipo.getRClass().equals( noClass ) ) {
            if ( rituIssueCnt.compareTo( hi ) < 0 ) {
                uriNipo.setZenIssueCntHirituClass( bodyHirituClassM );
            } else {
                uriNipo.setZenIssueCntHirituClass( bodyNumClassN );
            }
            if ( uriHiChargeKin.compareTo( hi ) < 0 ) {
                uriNipo.setUrihiChargeKinClass( bodyHirituClassM );
            } else {
                uriNipo.setUrihiChargeKinClass( bodyNumClassN );
            }
            if ( rituChargeKin.compareTo( hi ) < 0 ) {
                uriNipo.setZenHiChargeKinClass( bodyHirituClassM );
            } else {
                uriNipo.setZenHiChargeKinClass( bodyNumClassN );
            }
            if ( rituChargeKyaku.compareTo( hi ) < 0 ) {
                uriNipo.setChargeKenKyakuHiClass( bodyHirituClassM );
            } else {
                uriNipo.setChargeKenKyakuHiClass( bodyNumClassN );
            }
            if ( rituChargeKen.compareTo( hi ) < 0 ) {
                uriNipo.setChargeKenZenHiClass( bodyHirituClassM );
            } else {
                uriNipo.setChargeKenZenHiClass( bodyNumClassN );
            }
            if ( chargeTankahi.compareTo( hi ) < 0 ) {
                uriNipo.setChargeTankahiClass( bodyHirituClassM );
            } else {
                uriNipo.setChargeTankahiClass( bodyNumClassN );
            }
            if ( zenChargeTankahi.compareTo( hi ) < 0 ) {
                uriNipo.setZenChargeTankahiClass( bodyHirituClassM );
            } else {
                uriNipo.setZenChargeTankahiClass( bodyNumClassN );
            }
            if ( uriHiKessaiKin.compareTo( hi ) < 0 ) {
                uriNipo.setKessaiKinUriHiClass( bodyHirituClassM );
            } else {
                uriNipo.setKessaiKinUriHiClass( bodyNumClassN );
            }
            if ( zenHiKessaiKin.compareTo( hi ) < 0 ) {
                uriNipo.setKessaiKinZenHiClass( bodyHirituClassM );
            } else {
                uriNipo.setKessaiKinZenHiClass( bodyNumClassN );
            }
            if ( kyakuHiKessaiKen.compareTo( hi ) < 0 ) {
                uriNipo.setKessaiKenKyakuHiClass( bodyHirituClassM );
            } else {
                uriNipo.setKessaiKenKyakuHiClass( bodyNumClassN );
            }
            if ( zenHiKessaiKen.compareTo( hi ) < 0 ) {
                uriNipo.setKessaiKenZenHiClass( bodyHirituClassM );
            } else {
                uriNipo.setKessaiKenZenHiClass( bodyNumClassN );
            }
            if ( tankaHiKessaiKin.compareTo( hi ) < 0 ) {
                uriNipo.setKessaiKinTankaHiClass( bodyHirituClassM );
            } else {
                uriNipo.setKessaiKinTankaHiClass( bodyNumClassN );
            }
            if ( zenTankaHiKessaiKin.compareTo( hi ) < 0 ) {
                uriNipo.setKessaiKinZenTankaHiClass( bodyHirituClassM );
            } else {
                uriNipo.setKessaiKinZenTankaHiClass( bodyNumClassN );
            }
        } else {
            if ( rituIssueCnt.compareTo( hi ) < 0 ) {
                uriNipo.setZenIssueCntHirituClass( bodyHirituClass );
            } else {
                uriNipo.setZenIssueCntHirituClass( bodyNumClass );
            }
            if ( uriHiChargeKin.compareTo( hi ) < 0 ) {
                uriNipo.setUrihiChargeKinClass( bodyHirituClass );
            } else {
                uriNipo.setUrihiChargeKinClass( bodyNumClass );
            }
            if ( rituChargeKin.compareTo( hi ) < 0 ) {
                uriNipo.setZenHiChargeKinClass( bodyHirituClass );
            } else {
                uriNipo.setZenHiChargeKinClass( bodyNumClass );
            }
            if ( rituChargeKyaku.compareTo( hi ) < 0 ) {
                uriNipo.setChargeKenKyakuHiClass( bodyHirituClass );
            } else {
                uriNipo.setChargeKenKyakuHiClass( bodyNumClass );
            }
            if ( rituChargeKen.compareTo( hi ) < 0 ) {
                uriNipo.setChargeKenZenHiClass( bodyHirituClass );
            } else {
                uriNipo.setChargeKenZenHiClass( bodyNumClass );
            }
            if ( chargeTankahi.compareTo( hi ) < 0 ) {
                uriNipo.setChargeTankahiClass( bodyHirituClass );
            } else {
                uriNipo.setChargeTankahiClass( bodyNumClass );
            }
            if ( zenChargeTankahi.compareTo( hi ) < 0 ) {
                uriNipo.setZenChargeTankahiClass( bodyHirituClass );
            } else {
                uriNipo.setZenChargeTankahiClass( bodyNumClass );
            }
            if ( uriHiKessaiKin.compareTo( hi ) < 0 ) {
                uriNipo.setKessaiKinUriHiClass( bodyHirituClass );
            } else {
                uriNipo.setKessaiKinUriHiClass( bodyNumClass );
            }
            if ( zenHiKessaiKin.compareTo( hi ) < 0 ) {
                uriNipo.setKessaiKinZenHiClass( bodyHirituClass );
            } else {
                uriNipo.setKessaiKinZenHiClass( bodyNumClass );
            }
            if ( kyakuHiKessaiKen.compareTo( hi ) < 0 ) {
                uriNipo.setKessaiKenKyakuHiClass( bodyHirituClass );
            } else {
                uriNipo.setKessaiKenKyakuHiClass( bodyNumClass );
            }
            if ( zenHiKessaiKen.compareTo( hi ) < 0 ) {
                uriNipo.setKessaiKenZenHiClass( bodyHirituClass );
            } else {
                uriNipo.setKessaiKenZenHiClass( bodyNumClass );
            }
            if ( tankaHiKessaiKin.compareTo( hi ) < 0 ) {
                uriNipo.setKessaiKinTankaHiClass( bodyHirituClass );
            } else {
                uriNipo.setKessaiKinTankaHiClass( bodyNumClass );
            }
            if ( zenTankaHiKessaiKin.compareTo( hi ) < 0 ) {
                uriNipo.setKessaiKinZenTankaHiClass( bodyHirituClass );
            } else {
                uriNipo.setKessaiKinZenTankaHiClass( bodyNumClass );
            }
        }

        return uriNipo;
    }

    /**
     * 売上平均を算出する
     * @param map
     * @return
     *
     *
     * @modifier xkinu  2007/06/05 クローズ店予算表示対応
     */
    public Map calHeikin( Map map ) {
        Map returnMap = new HashMap();

        // 売上平均を算出
        List uriageList = (List)map.get("uriage");
        List uriHeiList = new ArrayList();

        for ( int i = 0; i < uriageList.size(); i++ ) {
            TrnSibuUriageNipoRelate uri = (TrnSibuUriageNipoRelate)uriageList.get(i);
            TrnSibuUriageNipoRelate uriHei = new TrnSibuUriageNipoRelate();

            uriHei.setCompanyCd( uri.getCompanyCd() );
            uriHei.setRClass( uri.getRClass() );
            uriHei.setHonbuCd( uri.getHonbuCd() );
            uriHei.setHonbuName( uri.getHonbuName() );
            uriHei.setJigyoCd( uri.getJigyoCd() );
            uriHei.setJigyoName( uri.getJigyoName() );
            uriHei.setSibuCd( uri.getSibuCd() );
            uriHei.setSibuName( uri.getSibuName() );
            uriHei.setSibuNameClass( uri.getSibuNameClass() );
            uriHei.setTenpoCount( uri.getTenpoCount() );
            uriHei.setZenTenpoCount( uri.getZenTenpoCount() );
            uriHei.setDispKbn( uri.getDispKbn() );
            uriHei.setUriage( Calculator.divide(uri.getUriage(),uri.getTenpoCount() ) );
            uriHei.setSibuYosan( Calculator.divide( uri.getSibuYosan(), uri.getYosanMiseCnt() ) );
            uriHei.setZenUriage( Calculator.divide( uri.getZenUriage(),uri.getZenTenpoCount() ) );
            //客数平均
            uriHei.setKyakusu( Calculator.divide( uri.getKyakusu(),uri.getTenpoCount() ) );
            //前年客数平均
            uriHei.setZenKyakusu( Calculator.divide(uri.getZenKyakusu(),uri.getZenTenpoCount() ) );
            //発行枚数平均
            uriHei.setIssueCnt( Calculator.divide(uri.getIssueCnt(), uri.getTenpoCount() ) );
            uriHei.setIssueCntClass( uri.getIssueCntClass() );
            //前年発行枚数平均
            uriHei.setZenIssueCnt( Calculator.divide(uri.getZenIssueCnt(), uri.getZenTenpoCount() ) );
            uriHei.setZenIssueCntClass( uri.getZenIssueCntClass() );
            //チャージ金額平均
            uriHei.setChargeKin( Calculator.divide(uri.getChargeKin(), uri.getTenpoCount() ) );
            uriHei.setChargeKinClass( uri.getChargeKinClass() );
            //前年チャージ金額平均
            uriHei.setZenChargeKin( Calculator.divide(uri.getZenChargeKin(), uri.getZenTenpoCount() ) );
            uriHei.setZenChargeKinClass( uri.getZenChargeKinClass() );
            //チャージ件数平均
            uriHei.setChargeKen( Calculator.divide(uri.getChargeKen() , uri.getTenpoCount() ) );
            uriHei.setChargeKenClass( uri.getChargeKenClass() );
           //前年チャージ件数平均
            uriHei.setZenChargeKen( Calculator.divide(uri.getZenChargeKen(), uri.getZenTenpoCount() ) );
            uriHei.setZenChargeKenClass( uri.geZenChargeKenClass() );
            //決済金額平均
            uriHei.setKessaiKin( Calculator.divide(uri.getKessaiKin(), uri.getTenpoCount() ) );
            uriHei.setKessaiKinClass( uri.getKessaiKinClass() );
            //前年決済金額平均
            uriHei.setZenKessaiKin( Calculator.divide(uri.getZenKessaiKin(), uri.getZenTenpoCount() ) );
            //決済件数平均
            uriHei.setKessaiKen( Calculator.divide(uri.getKessaiKen(), uri.getTenpoCount() ) );
            uriHei.setKessaiKenClass( uri.getKessaiKenClass() );
            //前年決済件数平均
            uriHei.setZenKessaiKen( Calculator.divide(uri.getZenKessaiKen(), uri.getZenTenpoCount() ) );

            uriHei = calHeikinUriage( uriHei );

            uriHeiList.add( uriHei );
        }

        returnMap.put("uriage",uriageList);
        returnMap.put("uriHei",uriHeiList);

        return returnMap;
    }

    /**
     * 予算差、前年差を求める(売上) 同月
     * @param uriNipo
     * @return
     */
    public TrnSibuUriageNipoRelate calHeikinUriage( TrnSibuUriageNipoRelate uriNipo ) {

        BigDecimal yosanSa = ( uriNipo.getUriage() ).subtract( uriNipo.getSibuYosan() );
        BigDecimal zenSa = (uriNipo.getUriage() ).subtract( uriNipo.getZenUriage() );
        //発行枚数前年差
        BigDecimal zenSaIssueCnt = (uriNipo.getIssueCnt() ).subtract( uriNipo.getZenIssueCnt() );
        //チャージ金額前年差
        BigDecimal zenSaChargeKin = (uriNipo.getChargeKin() ).subtract( uriNipo.getZenChargeKin() );
        //チャージ件数前年差
        BigDecimal zenSaChargeKen = (uriNipo.getChargeKen() ).subtract( uriNipo.getZenChargeKen() );
        //客数前年差
        BigDecimal kyakuSa = ( uriNipo.getKyakusu() ).subtract( uriNipo.getZenKyakusu() );
        //決済金額差
        BigDecimal kessaiKinSa = ( uriNipo.getKessaiKin() ).subtract( uriNipo.getZenKessaiKin() );
        //決済件数差
        BigDecimal zenSakessaiKen = ( uriNipo.getKessaiKen() ).subtract( uriNipo.getZenKessaiKen() );

        uriNipo.setTasseiYosan( yosanSa );
        uriNipo.setZenHiSa( zenSa );
        //発行枚数前年差セット
        uriNipo.setZenSaIssueCnt( zenSaIssueCnt );
        //チャージ金額前年差
        uriNipo.setZenSaChargeKin( zenSaChargeKin );
        //チャージ件数前年差
        uriNipo.setZenSaChargeKen( zenSaChargeKen );
        //客数前年差
        uriNipo.setZenSaKyakusu(kyakuSa);
        //決済金額差
        uriNipo.setZenSaKessaiKin(kessaiKinSa);
        //決済件数差
        uriNipo.setZenSaKessaiKen(zenSakessaiKen);

        return uriNipo;

    }

    private BigDecimal numNull(BigDecimal num) {
        if ( num == null ) {
            return new BigDecimal(0);
        } else {
            return num;
        }
    }

    /**
     * 本部発行データを画面表示用に整理する
     *
     * @param honbuuriage
     * @return
     *
     * @modifier xkinu  2007/06/05 クローズ店予算表示対応
     */
    public TrnSibuUriageNipoRelate getHonbuUriage(List uriageList) {
        // 売上タブ内容
        TrnSibuUriageNipoRelate uriNipo = new TrnSibuUriageNipoRelate();
        TrnUriageInfo uri = new TrnUriageInfo();

        // DISPONLY対策
        BigDecimal urikin = new BigDecimal(0);
        BigDecimal urizen = new BigDecimal(0);
        BigDecimal kyaten = new BigDecimal(0);
        BigDecimal kyazen = new BigDecimal(0);
        BigDecimal kyaCou = new BigDecimal(0);
        BigDecimal couZen = new BigDecimal(0);
        BigDecimal issueCnt = new BigDecimal(0);
        BigDecimal chargeKin = new BigDecimal(0);
        BigDecimal chargeKen = new BigDecimal(0);
        BigDecimal zenIssueCnt = new BigDecimal(0);
        BigDecimal zenChargeKin = new BigDecimal(0);
        BigDecimal zenChargeKen = new BigDecimal(0);
        BigDecimal kessaiKin = new BigDecimal(0);
        BigDecimal kessaiKen = new BigDecimal(0);
        BigDecimal zenKessaiKin = new BigDecimal(0);
        BigDecimal zenKessaiKen = new BigDecimal(0);
        BigDecimal chargeKinCancel = new BigDecimal(0);
        BigDecimal chargeKenCancel = new BigDecimal(0);
        BigDecimal useKinCancel = new BigDecimal(0);
        BigDecimal useKenCancel = new BigDecimal(0);
        BigDecimal bonusVIssue = new BigDecimal(0);
        BigDecimal bonusVUse = new BigDecimal(0);
        BigDecimal couponVIssue = new BigDecimal(0);
        BigDecimal couponVUse = new BigDecimal(0);
        BigDecimal zandaka = new BigDecimal(0);

        uriNipo.setRClass( rClassSum4 );
        uriNipo.setCompanyCd( uriNipo.getCompanyCd() );
        uriNipo.setSibuCd("");
        uriNipo.setSibuName( "本部発行" );
        uriNipo.setSibuNameClass( noClass );
        uriNipo.setHonbuCd("");
        uriNipo.setHonbuName( "" );
        uriNipo.setJigyoCd( "" );
        uriNipo.setJigyoName( "本部発行" );
        uriNipo.setSlareaCd( "" );
        uriNipo.setSlareaName( "" );

        if (uriageList.size() > 0) {
            for (int i = 0 ; i < uriageList.size(); i++) {
                uri = (TrnUriageInfo)uriageList.get(i);

                urikin = urikin.add(uri.getUriage());
                urizen = urizen.add(uri.getUriageZen());
                kyaten = kyaten.add(uri.getOpenKbn());
                kyazen = kyazen.add(uri.getOpenKbnZen());
                kyaCou = kyaCou.add(uri.getKyakusu());
                couZen = couZen.add(uri.getKyakusuZen());
                issueCnt = issueCnt.add(uri.getIssueCnt());
                chargeKin = chargeKin.add(uri.getChargeKin());
                chargeKen = chargeKen.add(uri.getChargeKen());
                zenIssueCnt = zenIssueCnt.add(uri.getZenIssueCnt());
                zenChargeKin = zenChargeKin.add(uri.getZenChargeKin());
                zenChargeKen = zenChargeKen.add(uri.getZenChargeKen());
                kessaiKin = kessaiKin.add(uri.getKessaiKin());
                kessaiKen = kessaiKen.add(uri.getKessaiKen());
                zenKessaiKin = zenKessaiKin.add(uri.getZenKessaiKin());
                zenKessaiKen = zenKessaiKen.add(uri.getZenKessaiKen());
                chargeKinCancel = chargeKinCancel.add(uri.getChargeKinCancel());
                chargeKenCancel = chargeKenCancel.add(uri.getChargeKenCancel());
                useKinCancel = useKinCancel.add(uri.getUseKinCancel());
                useKenCancel = useKenCancel.add(uri.getUseKenCancel());
                bonusVIssue = bonusVIssue.add(uri.getBonusVIssue());
                bonusVUse = bonusVUse.add(uri.getBonusVUse());
                couponVIssue = couponVIssue.add(uri.getCouponVIssue());
                couponVUse = couponVUse.add(uri.getCouponVUse());
                zandaka = zandaka.add(uri.getZandaka());

            }
        }
        uriNipo.setUriage( urikin );
        uriNipo.setIssueCnt( issueCnt );
        uriNipo.setIssueCntClass( bodyNumClass );
        uriNipo.setChargeKin( chargeKin );
        uriNipo.setChargeKinClass( bodyNumClass );
        uriNipo.setChargeKen( chargeKen );
        uriNipo.setChargeKenClass( bodyNumClass );
        uriNipo.setZenUriage( urizen );
        uriNipo.setTenpoCount( kyaten );
        uriNipo.setZenTenpoCount( kyazen );
        uriNipo.setZenIssueCnt( zenIssueCnt );
        uriNipo.setZenChargeKin( zenChargeKin );
        uriNipo.setZenChargeKen( zenChargeKen );
        uriNipo.setKyakusu( kyaCou );
        uriNipo.setZenKyakusu( couZen );
        uriNipo.setKessaiKin( kessaiKin );
        uriNipo.setKessaiKinClass( bodyNumClass );
        uriNipo.setKessaiKen( kessaiKen );
        uriNipo.setKessaiKenClass( bodyNumClass );
        uriNipo.setZenKessaiKin( zenKessaiKin );
        uriNipo.setZenKessaiKen( zenKessaiKen );
        uriNipo.setChargeKinCancel(chargeKinCancel);
        uriNipo.setChargeKenCancel(chargeKenCancel);
        uriNipo.setUseKinCancel(useKinCancel);
        uriNipo.setUseKenCancel(useKenCancel);
        uriNipo.setBonusVIssue(bonusVIssue);
        uriNipo.setBonusVUse(bonusVUse);
        uriNipo.setCouponVIssue(couponVIssue );
        uriNipo.setCouponVUse(couponVUse );
        uriNipo.setZandaka(zandaka );

        uriNipo.setDispKbn( "HONBUDISP" );

         return calcHiritu( uriNipo );
    }

}

