package jp.co.isid.mos.bird.bizreport.netorderniporef.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.code.NipoZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.common.code.ShukeiKbn;
import jp.co.isid.mos.bird.bizreport.common.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.common.NipoRefConstants;
import jp.co.isid.mos.bird.bizreport.netorderniporef.common.NetorderNipoConstants;
import jp.co.isid.mos.bird.bizreport.netorderniporef.dao.MstSegmentInfoDao;
import jp.co.isid.mos.bird.bizreport.netorderniporef.dao.TrnUriageSegInfoDao;
import jp.co.isid.mos.bird.bizreport.netorderniporef.dao.TrnYosanSegInfoDao;
import jp.co.isid.mos.bird.bizreport.netorderniporef.entity.MstSegmentInfo;
import jp.co.isid.mos.bird.bizreport.netorderniporef.entity.MstSibuInfo;
import jp.co.isid.mos.bird.bizreport.netorderniporef.entity.TrnSibuKyakusuNipoRelate;
import jp.co.isid.mos.bird.bizreport.netorderniporef.entity.TrnSibuUriageNipoRelate;
import jp.co.isid.mos.bird.bizreport.netorderniporef.entity.TrnSibuUriageNipoSegRelate;
import jp.co.isid.mos.bird.bizreport.netorderniporef.entity.TrnSibuYosanNipoSegRelate;
import jp.co.isid.mos.bird.bizreport.netorderniporef.entity.TrnUriageInfo;
import jp.co.isid.mos.bird.bizreport.netorderniporef.entity.TrnYosanInfo;
import jp.co.isid.mos.bird.bizreport.netorderniporef.logic.ViewUriYosanLogic;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.util.Calculator;

/**
 *
 */
public class ViewUriYosanLogicImpl implements ViewUriYosanLogic{

    public static final String LOGIC_ID = "BBR017L04";

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


    // セグメント情報取得
    private MstSegmentInfoDao mstSegmentInfoDao;

    /**
     * mstSegmentInfoDao取得
     * @return
     */
    public MstSegmentInfoDao getMstSegmentInfoDao() {
        return this.mstSegmentInfoDao;
    }

    /**
     * mstSegmentInfoDao設定
     * @param mstSegmentInfoDao
     */
    public void setMstSegmentInfoDao( MstSegmentInfoDao mstSegmentInfoDao ) {
        this.mstSegmentInfoDao = mstSegmentInfoDao;
    }
    // セグメント指定売上取得
    private TrnUriageSegInfoDao trnUriageSegInfoDao;
    /**
     * trnUriageSegInfoDao設定
     * @return
     */
    public TrnUriageSegInfoDao getTrnUriageSegInfoDao() {
        return this.trnUriageSegInfoDao;
    }
    /**
     * trnUriageSegInfoDao設定
     * @param trnUriageSegInfoDao
     */
    public void setTrnUriageSegInfoDao( TrnUriageSegInfoDao trnUriageSegInfoDao ) {
        this.trnUriageSegInfoDao = trnUriageSegInfoDao;
    }

    // セグメント指定予算取得
    private TrnYosanSegInfoDao trnYosanSegInfoDao;
    /**
     * trnYosanSegInfoDao設定
     * @param trnYosanSegInfoDao
     * @return
     */
    public TrnYosanSegInfoDao getTrnYosanSegInfoDao() {
        return this.trnYosanSegInfoDao;
    }
    /**
     * trnYosanSegInfoDao設定
     * @param trnYosanSegInfoDao
     */
    public void setTrnYosanSegInfoDao( TrnYosanSegInfoDao trnYosanSegInfoDao ) {
        this.trnYosanSegInfoDao = trnYosanSegInfoDao;
    }

    /**
     * 予算売上のデータを整理する
     *
     */
    public Map execute(Map map) {
        Map paramMap = (Map)map.get("param");
        //売上値小数桁数取得(海外売上)
        int digitCnt = ((Integer)paramMap.get( NetorderNipoConstants.MAP_DIGIT_CNT )).intValue();
        //売上、客数リスト作成
        Map kekkaMap = getReadjustMap(map,digitCnt);

        String areaDai = (String)map.get("areadai");
        String companyCd = (String)paramMap.get( COMPANY_CD );

        List uriageList = (List)kekkaMap.get("uriage");
        List kyakusuList = (List)kekkaMap.get("kyakusu");

        // モス以外は支部しか存在しないため
        if ( CommonUtil.COMPANY_CD_MOS.equals(companyCd)) {
            if ( areaDai.equals(ShukeiKbn.OUT_RC)) {
            	// エリア毎の計を求める
                Map resultMap = getResultMap( uriageList,kyakusuList, digitCnt);
                uriageList = (List)resultMap.get("uriage");
                kyakusuList = (List)resultMap.get("kyakusu");
            }
        }

        // 総合計取得を変更(セグメント計→支部計)
        String dataShu = (String)paramMap.get("dataShu");
        // 前年同月営業日補正のとき(データは前年同月)
        if ( dataShu.equals(NipoZennenDataShubetu.CODE_HOSEI)) {
            paramMap.put("dataShu",NipoZennenDataShubetu.CODE_DOGETU);
        }
        paramMap.put("uriageList",uriageList );
        paramMap.put("kyakusuList", kyakusuList );

        Map segMap = segList( paramMap, digitCnt );

        List uriageSegList = (List)segMap.get("uriage");
        List kyakuSegList = (List)segMap.get("kyakusu");

        uriageList.addAll( uriageSegList );
        kyakusuList.addAll( kyakuSegList );

        Map returnMap = new HashMap();
        returnMap.put("uriage",uriageList);
        returnMap.put("kyakusu",kyakusuList );

        Map mapGross = calHeikin( returnMap, digitCnt );

        TrnSibuUriageNipoRelate trnUriage = (TrnSibuUriageNipoRelate)uriageSegList.get(uriageSegList.size() - 1 ) ;

        mapGross.put("tenpoCount",trnUriage.getTenpoCount() );
        //予算対象店舗数をリターン値Mapへ格納する。
        mapGross.put(NetorderNipoConstants.MAP_YOSAN_TENPO_COUNT, trnUriage.getYosanMiseCnt() );

        Map retMap = mapGross;

        // 前年同月営業日補正のとき(データは前年同月)
        if ( dataShu.equals(NipoZennenDataShubetu.CODE_HOSEI)) {

            List uriHoseiYouList = (List)map.get("hoseiyou");
            map.put("uriage",uriHoseiYouList);
            Map kekkaHoseiYouMap = getReadjustMap( map, digitCnt);

            List uriYouList = (List)kekkaHoseiYouMap.get("uriage");
            List kyakuYouList = (List)kekkaHoseiYouMap.get("kyakusu");

            // モス以外は支部しか存在しないため
            if ( CommonUtil.COMPANY_CD_MOS.equals(companyCd)) {
                if ( areaDai.equals("SIBU_CD")) {
                    Map resultMap = getResultMap( uriYouList,kyakuYouList, digitCnt);
                    uriYouList = (List)resultMap.get("uriage");
                    kyakuYouList = (List)resultMap.get("kyakusu");
                }
            }

            //総合計取得を変更(セグメント計→支部計)
            paramMap.put("uriageList",uriYouList );
            paramMap.put("kyakusuList", kyakuYouList );

            paramMap.put("dataShu",NipoZennenDataShubetu.CODE_HOSEI);

            Map segYouMap = segList( paramMap, digitCnt );

            List uriYouSegList = (List)segYouMap.get("uriage");
            List kyakuYouSegList = (List)segYouMap.get("kyakusu");

            uriYouList.addAll( uriYouSegList );
            kyakuYouList.addAll( kyakuYouSegList );

            Map returnYouMap = new HashMap();
            returnYouMap.put("uriage",uriYouList);
            returnYouMap.put("kyakusu",kyakuYouList );

            Map mapNet = calHeikin( returnYouMap, digitCnt );

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
        List kyakusuList = (List)mapGross.get("kyakusu");
        List uriHeiList = (List)mapGross.get("uriHei");
        List kyakuHeiList = (List)mapGross.get("kyakuHei");

        List uriageHoseiList = (List)mapNet.get("uriage");
        List kyakusuHoseiList = (List)mapNet.get("kyakusu");
        List uriHeiHoseiList = (List)mapNet.get("uriHei");
        List kyakuHeiHoseiList = (List)mapNet.get("kyakuHei");

        BigDecimal tenpoCnt = (BigDecimal)mapGross.get("tenpoCount");

        retMap.put("uriHosei",uriageHoseiList);
        retMap.put("kyakuHosei",kyakusuHoseiList );
        retMap.put("uriHeiHosei",uriHeiHoseiList );
        retMap.put("kyakuHeiHosei",kyakuHeiHoseiList );
        retMap.put("uriDou",uriageList);
        retMap.put("kyakuDou",kyakusuList );
        retMap.put("uriheiDou",uriHeiList );
        retMap.put("kyakuHeiDou",kyakuHeiList );
        retMap.put("tenpoCount",tenpoCnt );

        List uriList = new ArrayList();
        List kyaList = new ArrayList();
        List uriAvgList = new ArrayList();
        List kyaAvgList = new ArrayList();

        for ( int i = 0; i < uriageList.size(); i++ ) {
            TrnSibuUriageNipoRelate uriage = new TrnSibuUriageNipoRelate();
            TrnSibuKyakusuNipoRelate kyaku = new TrnSibuKyakusuNipoRelate();
            TrnSibuUriageNipoRelate uriHei = new TrnSibuUriageNipoRelate();
            TrnSibuKyakusuNipoRelate kyakuHei = new TrnSibuKyakusuNipoRelate();

            TrnSibuUriageNipoRelate douUri = (TrnSibuUriageNipoRelate)uriageList.get(i);
            TrnSibuUriageNipoRelate douUriHose = (TrnSibuUriageNipoRelate)uriageHoseiList.get(i);
            TrnSibuUriageNipoRelate douUriHei = (TrnSibuUriageNipoRelate)uriHeiList.get(i);
            TrnSibuUriageNipoRelate douUriHoseHei = (TrnSibuUriageNipoRelate)uriHeiHoseiList.get(i);

            TrnSibuKyakusuNipoRelate douKyaku = (TrnSibuKyakusuNipoRelate)kyakusuList.get(i);
            TrnSibuKyakusuNipoRelate douKyakuHose = (TrnSibuKyakusuNipoRelate)kyakusuHoseiList.get(i);
            TrnSibuKyakusuNipoRelate douKyakuHei = (TrnSibuKyakusuNipoRelate)kyakuHeiList.get(i);
            TrnSibuKyakusuNipoRelate douKyakuHoseHei = (TrnSibuKyakusuNipoRelate)kyakuHeiHoseiList.get(i);

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
            uriage.setUriageClass( douUri.getUriageClass() );
            uriage.setUriage( douUri.getUriage() );
            uriage.setYosanClass( douUri.getYosanClass() );
            uriage.setYosan( douUri.getYosan() );
            uriage.setTasseiYosanClass( douUri.getTasseiYosanClass() );
            uriage.setTasseiYosan( douUri.getTasseiYosan() );
            uriage.setZenUriageClass( douUriHose.getZenUriageClass() );
            uriage.setZenUriage( douUriHose.getZenUriage() );
            uriage.setZenHiSaClass( douUriHose.getZenHiSaClass() );
            uriage.setZenHiSa( douUriHose.getZenHiSa() );
            //ネット注文
            uriage.setMiseKbnNsum(douUri.getMiseKbnNsum());
            uriage.setUriageNsum(douUri.getUriageNsum());
            uriage.setUriageNsumZen(douUriHose.getUriageNsumZen());
            uriage.setUriageNsumZenHi(douUriHose.getUriageNsumZenHi());
            uriage.setUriageNsumKoseiHi(douUri.getUriageNsumKoseiHi());
            uriage.setMiseKbnNtake(douUri.getMiseKbnNtake());
            uriage.setUriageNtake(douUri.getUriageNtake());
            uriage.setUriageNtakeZen(douUriHose.getUriageNtakeZen());
            uriage.setUriageNtakeZenHi(douUriHose.getUriageNtakeZenHi());
            uriage.setUriageNtakeKoseiHi(douUri.getUriageNtakeKoseiHi());
            uriage.setMiseKbnNtaku(douUri.getMiseKbnNtaku());
            uriage.setUriageNtaku(douUri.getUriageNtaku());
            uriage.setUriageNtakuZen(douUriHose.getUriageNtakuZen());
            uriage.setUriageNtakuZenHi(douUriHose.getUriageNtakuZenHi());
            uriage.setUriageNtakuKoseiHi(douUri.getUriageNtakuKoseiHi());

            // 客数
            kyaku.setRClass( douKyaku.getRClass() );
            kyaku.setCompanyCd( douKyaku.getCompanyCd() );
            kyaku.setDispKbn( douKyaku.getDispKbn() );
            kyaku.setHonbuCd( douKyaku.getHonbuCd() );
            kyaku.setHonbuName( douKyaku.getHonbuName() );
            kyaku.setJigyoCd( douKyaku.getJigyoCd() );
            kyaku.setJigyoName( douKyaku.getJigyoName() );
            kyaku.setSlareaCd( douKyaku.getSlareaCd() );
            kyaku.setSlareaName( douKyaku.getSlareaName() );
            kyaku.setSibuNameClass( douKyaku.getSibuNameClass() );
            kyaku.setSibuCd( douKyaku.getSibuCd() );
            kyaku.setSibuName( douKyaku.getSibuName() );
            kyaku.setTenpoCount( douKyaku.getTenpoCount() );
            kyaku.setZenTenpoCount( douKyaku.getZenTenpoCount() );
            kyaku.setKyakusuClass( douKyaku.getKyakusuClass() );
            kyaku.setKyakusu( douKyaku.getKyakusu() );
            kyaku.setZenKyakusuClass( douKyakuHose.getZenKyakusuClass() );
            kyaku.setZenKyakusu( douKyakuHose.getZenKyakusu() );
            kyaku.setZenHiClass( douKyakuHose.getZenHiClass() );
            kyaku.setZenHi( douKyakuHose.getZenHi() );
            kyaku.setKyakuTankaClass( douKyaku.getKyakuTankaClass() );
            kyaku.setKyakuTanka( douKyaku.getKyakuTanka() );
            kyaku.setZenHiTankaClass( douKyakuHose.getZenHiTankaClass() );
            kyaku.setZenHiTanka( douKyakuHose.getZenHiTanka() );
            //ネット注文
            kyaku.setMiseKbnNsum(douKyaku.getMiseKbnNsum());
            kyaku.setKyakusuNsum(douKyaku.getKyakusuNsum());
            kyaku.setKyakusuNsumZen(douKyakuHose.getKyakusuNsumZen());
            kyaku.setKyakusuNsumZenHi(douKyakuHose.getKyakusuNsumZenHi());
            kyaku.setKyakusuNsumKoseiHi(douKyaku.getKyakusuNsumKoseiHi());
            kyaku.setKyakuTankaNsum(douKyaku.getKyakuTankaNsum());
            kyaku.setMiseKbnNtake(douKyaku.getMiseKbnNtake());
            kyaku.setKyakusuNtake(douKyaku.getKyakusuNtake());
            kyaku.setKyakusuNtakeZen(douKyaku.getKyakusuNtakeZen());
            kyaku.setKyakusuNtakeZenHi(douKyakuHose.getKyakusuNtakeZenHi());
            kyaku.setKyakusuNtakeKoseiHi(douKyaku.getKyakusuNtakeKoseiHi());
            kyaku.setKyakuTankaNtake(douKyaku.getKyakuTankaNtake());
            kyaku.setMiseKbnNtaku(douKyaku.getMiseKbnNtaku());
            kyaku.setKyakusuNtaku(douKyaku.getKyakusuNtaku());
            kyaku.setKyakusuNtakuZen(douKyakuHose.getKyakusuNtakuZen());
            kyaku.setKyakusuNtakuZenHi(douKyakuHose.getKyakusuNtakuZenHi());
            kyaku.setKyakusuNtakuKoseiHi(douKyaku.getKyakusuNtakuKoseiHi());
            kyaku.setKyakuTankaNtaku(douKyaku.getKyakuTankaNtaku());

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
            uriHei.setUriageClass( douUriHei.getUriageClass() );
            uriHei.setUriage( douUriHei.getUriage() );
            uriHei.setYosanClass( douUriHei.getYosanClass() );
            uriHei.setYosan( douUriHei.getYosan() );
            uriHei.setTasseiYosanClass( douUriHei.getTasseiYosanClass() );
            uriHei.setTasseiYosan( douUriHei.getTasseiYosan() );
            uriHei.setZenUriageClass( douUriHoseHei.getZenUriageClass() );
            uriHei.setZenUriage( douUriHoseHei.getZenUriage() );

            uriHei.setUriageNsum(douUriHei.getUriageNsum());
            uriHei.setUriageNtake(douUriHei.getUriageNtake());
            uriHei.setUriageNtaku(douUriHei.getUriageNtaku());

            uriHei = calHeikinUriage( uriHei );


            // 客数平均
            kyakuHei.setRClass( douKyakuHei.getRClass() );
            kyakuHei.setCompanyCd( douKyakuHei.getCompanyCd() );
            kyakuHei.setDispKbn( douKyakuHei.getDispKbn() );
            kyakuHei.setHonbuCd( douKyakuHei.getHonbuCd() );
            kyakuHei.setHonbuName( douKyakuHei.getHonbuName() );
            kyakuHei.setJigyoCd( douKyakuHei.getJigyoCd() );
            kyakuHei.setJigyoName( douKyakuHei.getJigyoName() );
            kyakuHei.setSlareaCd( douKyakuHei.getSlareaCd() );
            kyakuHei.setSlareaName( douKyakuHei.getSlareaName() );
            kyakuHei.setSibuNameClass( douKyakuHei.getSibuNameClass() );
            kyakuHei.setSibuCd( douKyakuHei.getSibuCd() );
            kyakuHei.setSibuName( douKyakuHei.getSibuName() );
            kyakuHei.setTenpoCount( douKyakuHei.getTenpoCount() );
            kyakuHei.setZenTenpoCount( douKyakuHei.getZenTenpoCount() );
            kyakuHei.setKyakusuClass( douKyakuHei.getKyakusuClass() );
            kyakuHei.setKyakusu( douKyakuHei.getKyakusu() );
            kyakuHei.setZenKyakusuClass( douKyakuHoseHei.getZenKyakusuClass() );
            kyakuHei.setZenKyakusu( douKyakuHoseHei.getZenKyakusu() );
            kyakuHei.setZenHiClass( douKyakuHoseHei.getZenHiClass() );
            kyakuHei.setZenHi( douKyakuHoseHei.getZenHi() );
            kyakuHei.setKyakuTankaClass( douKyakuHei.getKyakuTankaClass() );
            kyakuHei.setKyakuTanka( douKyakuHei.getKyakuTanka() );
            kyakuHei.setZenHiTankaClass( douKyakuHoseHei.getZenHiTankaClass() );
            kyakuHei.setZenHiTanka( douKyakuHoseHei.getZenHiTanka() );

            kyakuHei.setKyakusuNsum(douKyakuHei.getKyakusuNsum());
            kyakuHei.setKyakusuNtake(douKyakuHei.getKyakusuNtake());
            kyakuHei.setKyakusuNtaku(douKyakuHei.getKyakusuNtaku());

            kyakuHei = calHeikinKyakusu( kyakuHei );

            uriList.add( uriage );
            kyaList.add( kyaku );
            uriAvgList.add( uriHei );
            kyaAvgList.add( kyakuHei );

        }

        retMap.put("uriage",uriList);
        retMap.put("kyakusu",kyaList);
        retMap.put("uriHei",uriAvgList);
        retMap.put("kyakuHei",kyaAvgList);

        return retMap;

    }
    /**
     * モス合計、モス以外合計、総合計を作成する
     * @param map
     * @param digitCnt
     * @return
     */
    public Map getSegReadJustMap(Map map, int digitCnt) {
        Map returnMap = new HashMap();

        List uriage = (List)map.get("uriage");
        List yosan = (List)map.get("yosan");
        String companyCd = (String)map.get("companyCd");

        List uri = new ArrayList();
        List kyaku = new ArrayList();

        for (int i = 0; i < uriage.size(); i++ ) {
            TrnSibuUriageNipoSegRelate uriSeg = (TrnSibuUriageNipoSegRelate)uriage.get(i);
            for ( int j = 0; j < yosan.size(); j++ ) {
                TrnSibuYosanNipoSegRelate yosSeg = (TrnSibuYosanNipoSegRelate)yosan.get(j);
                if ( uriSeg.getSegmentType().equals( yosSeg.getSegmentType() ) ) {
                    TrnSibuUriageNipoRelate uriNipo = new TrnSibuUriageNipoRelate();

                    uriNipo.setCompanyCd( uriSeg.getCompanyCd() );
                    uriNipo.setRClass( NipoRefConstants.CSS_TR_CLASS_SEGMENT );
                    uriNipo.setSibuCd( uriSeg.getSegmentType() );
                    uriNipo.setSibuName( uriSeg.getSegmentName().trim() );
                    uriNipo.setSibuNameClass( noClass );
                    uriNipo.setTenpoCount( uriSeg.getOpenKbn() );
                    uriNipo.setZenTenpoCount( uriSeg.getOpenKbnZen() );
                    uriNipo.setUriage( uriSeg.getUriage() );
                    uriNipo.setUriageClass( bodyNumClass );
                    uriNipo.setYosan( yosSeg.getYosan() );
                    // クローズ店予算表示対応
                    //予算対象店舗数設定
                    uriNipo.setYosanMiseCnt( yosSeg.getMiseCnt() );
                    uriNipo.setYosanClass( bodyNumClass );
                    uriNipo.setZenUriage( uriSeg.getUriageZen() );
                    uriNipo.setZenUriageClass( bodyNumClass );
                    uriNipo.setDispKbn( "DISP" );

                    uriNipo.setMiseKbnNsum(uriSeg.getMiseKbnNsum());
                    uriNipo.setUriageNsum(uriSeg.getUriageNsum());
                    uriNipo.setMiseKbnNtake(uriSeg.getMiseKbnNtake());
                    uriNipo.setUriageNtake(uriSeg.getUriageNtake());
                    uriNipo.setMiseKbnNtaku(uriSeg.getMiseKbnNtaku());
                    uriNipo.setUriageNtaku(uriSeg.getUriageNtaku());
                    uriNipo.setUriageNsumZen(uriSeg.getUriageNsumZen());
                    uriNipo.setUriageNtakeZen(uriSeg.getUriageNtakeZen());
                    uriNipo.setUriageNtakuZen(uriSeg.getUriageNtakuZen());

                    uriNipo = calcHiritu( uriNipo );
                    uri.add(uriNipo);

                    TrnSibuKyakusuNipoRelate kyakuNipo = new TrnSibuKyakusuNipoRelate();

                    kyakuNipo.setCompanyCd( uriSeg.getCompanyCd() );
                    kyakuNipo.setRClass( NipoRefConstants.CSS_TR_CLASS_SEGMENT );
                    kyakuNipo.setSibuCd( uriSeg.getSegmentType() );
                    kyakuNipo.setSibuName( uriSeg.getSegmentName().trim() );
                    kyakuNipo.setSibuNameClass( noClass );
                    kyakuNipo.setTenpoCount( uriSeg.getOpenKbn() );
                    kyakuNipo.setZenTenpoCount( uriSeg.getOpenKbnZen() );
                    kyakuNipo.setKyakusu( uriSeg.getKyakusu() );
                    kyakuNipo.setKyakusuClass( bodyNumClass );
                    kyakuNipo.setZenKyakusu( uriSeg.getKyakusuZen() );
                    kyakuNipo.setZenKyakusuClass( bodyNumClass );
                    kyakuNipo.setDispKbn( "DISP" );

                    kyakuNipo.setMiseKbnNsum(uriSeg.getMiseKbnNsum());
                    kyakuNipo.setKyakusuNsum( uriSeg.getKyakusuNsum() );
                    kyakuNipo.setMiseKbnNtake(uriSeg.getMiseKbnNtake());
                    kyakuNipo.setKyakusuNtake( uriSeg.getKyakusuNtake() );
                    kyakuNipo.setMiseKbnNtaku(uriSeg.getMiseKbnNtaku());
                    kyakuNipo.setKyakusuNtaku( uriSeg.getKyakusuNtaku() );
                    kyakuNipo.setKyakusuNsumZen( uriSeg.getKyakusuNsumZen() );
                    kyakuNipo.setKyakusuNtakeZen( uriSeg.getKyakusuNtakeZen() );
                    kyakuNipo.setKyakusuNtakuZen( uriSeg.getKyakusuNtakuZen() );

                    Map paramMap = new HashMap();
                    paramMap.put("uriage",uriNipo );
                    paramMap.put("kyakusu",kyakuNipo );

                    kyakuNipo = calcHiritu( paramMap, digitCnt );

                    kyaku.add( kyakuNipo );
                }
            }
        }

        // 総合計
        BigDecimal souUri = new BigDecimal(0);
        BigDecimal souCount = new BigDecimal(0);
        BigDecimal souZenCount = new BigDecimal(0);
        BigDecimal souYos = new BigDecimal(0);
        BigDecimal souZenUri = new BigDecimal(0);
        BigDecimal souYosanMiseCnt = new BigDecimal(0);
        BigDecimal souMiseKbnNsum = new BigDecimal(0);
        BigDecimal souUriageNsum = new BigDecimal(0);
        BigDecimal souUriageNsumZen = new BigDecimal(0);
        BigDecimal souMiseKbnNtake = new BigDecimal(0);
        BigDecimal souUriageNtake = new BigDecimal(0);
        BigDecimal souUriageNtakeZen = new BigDecimal(0);
        BigDecimal souMiseKbnNtaku = new BigDecimal(0);
        BigDecimal souUriageNtaku = new BigDecimal(0);
        BigDecimal souUriageNtakuZen = new BigDecimal(0);

        List uriageList = new ArrayList();
        uriageList = (List)map.get("sibuUriList");

        for (int i = 0; i < uriageList.size(); i++ ) {
            TrnSibuUriageNipoRelate uriSeg = (TrnSibuUriageNipoRelate)uriageList.get(i);
            // 支部のみ対象(rclassがnoclass("")であること
            if ( uriSeg.getRClass().equals( noClass ) ) {
                souUri = souUri.add( numNull(uriSeg.getUriage()) );
                souYos = souYos.add(  numNull(uriSeg.getYosan()) );
                souZenUri = souZenUri.add(  numNull(uriSeg.getZenUriage()) );
                souCount = souCount.add(  numNull(uriSeg.getTenpoCount()) );
                souZenCount = souZenCount.add(  numNull(uriSeg.getZenTenpoCount()) );
                souYosanMiseCnt = souYosanMiseCnt.add( numNull(uriSeg.getYosanMiseCnt()) );
                souMiseKbnNsum = souMiseKbnNsum.add( numNull(uriSeg.getMiseKbnNsum()) );
                souUriageNsum = souUriageNsum.add(numNull(uriSeg.getUriageNsum()));
                souUriageNsumZen = souUriageNsumZen.add(numNull(uriSeg.getUriageNsumZen()));
                souMiseKbnNtake = souMiseKbnNtake.add( numNull(uriSeg.getMiseKbnNtake()) );
                souUriageNtake = souUriageNtake.add(numNull(uriSeg.getUriageNtake()));
                souUriageNtakeZen = souUriageNtakeZen.add(numNull(uriSeg.getUriageNtakeZen()));
                souMiseKbnNtaku = souMiseKbnNtaku.add( numNull(uriSeg.getMiseKbnNtaku()) );
                souUriageNtaku = souUriageNtaku.add(numNull(uriSeg.getUriageNtaku()));
                souUriageNtakuZen = souUriageNtakuZen.add(numNull(uriSeg.getUriageNtakuZen()));
                companyCd = uriSeg.getCompanyCd();
            }
        }

        TrnSibuUriageNipoRelate uriTou = new TrnSibuUriageNipoRelate();

        uriTou.setRClass( NipoRefConstants.CSS_TR_CLASS_TOTAL );
        uriTou.setCompanyCd( companyCd );
        uriTou.setSibuCd( "ALL" );
        uriTou.setSibuName( "総合計");
        uriTou.setTenpoCount( souCount );
        uriTou.setZenTenpoCount( souZenCount );
        uriTou.setSibuNameClass( noClass );
        uriTou.setUriage( souUri );
        uriTou.setUriageClass( bodyNumClass );
        uriTou.setYosan( souYos );
        uriTou.setYosanClass( bodyNumClass );
        uriTou.setYosanMiseCnt( souYosanMiseCnt );
        uriTou.setZenUriage( souZenUri );
        uriTou.setZenUriageClass( bodyNumClass );
        uriTou.setDispKbn( "DISP" );

        uriTou.setMiseKbnNsum(souMiseKbnNsum);
        uriTou.setUriageNsum(souUriageNsum);
        uriTou.setUriageNsumZen(souUriageNsumZen);
        uriTou.setMiseKbnNtake(souMiseKbnNtake);
        uriTou.setUriageNtake(souUriageNtake);
        uriTou.setUriageNtakeZen(souUriageNtakeZen);
        uriTou.setMiseKbnNtaku(souMiseKbnNtaku);
        uriTou.setUriageNtaku(souUriageNtaku);
        uriTou.setUriageNtakuZen(souUriageNtakuZen);

        uriTou = calcHiritu( uriTou );
        uri.add( uriTou );

        BigDecimal souKyaku = new BigDecimal(0);
        BigDecimal souZenKya = new BigDecimal(0);
        BigDecimal souTenCount = new BigDecimal(0);
        BigDecimal souZenTenCount = new BigDecimal(0);
        BigDecimal souKyakuMiseKbnNsum = new BigDecimal(0);
        BigDecimal souKyakusuNsum = new BigDecimal(0);
        BigDecimal souKyakusuNsumZen = new BigDecimal(0);
        BigDecimal souKyakuMiseKbnNtake = new BigDecimal(0);
        BigDecimal souKyakusuNtake = new BigDecimal(0);
        BigDecimal souKyakusuNtakeZen = new BigDecimal(0);
        BigDecimal souKyakuMiseKbnNtaku = new BigDecimal(0);
        BigDecimal souKyakusuNtaku = new BigDecimal(0);
        BigDecimal souKyakusuNtakuZen = new BigDecimal(0);
        List kyakusuList = new ArrayList();
        kyakusuList = (List)map.get("sibuKyakuList");

        for ( int i = 0; i < kyakusuList.size();i++ ) {
            TrnSibuKyakusuNipoRelate kyakuSeg = (TrnSibuKyakusuNipoRelate)kyakusuList.get(i);

            if ( kyakuSeg.getRClass().equals( noClass ) ) {
                souKyaku = souKyaku.add( numNull(kyakuSeg.getKyakusu()) );
                souZenKya = souZenKya.add( numNull(kyakuSeg.getZenKyakusu()) );
                souTenCount = souTenCount.add( numNull(kyakuSeg.getTenpoCount()) );
                souZenTenCount = souZenTenCount.add( numNull(kyakuSeg.getZenTenpoCount()) );
                souKyakuMiseKbnNsum = souKyakuMiseKbnNsum.add(numNull(kyakuSeg.getMiseKbnNsum()));
                souKyakusuNsum = souKyakusuNsum.add(numNull(kyakuSeg.getKyakusuNsum()));
                souKyakusuNsumZen = souKyakusuNsumZen.add(numNull(kyakuSeg.getKyakusuNsumZen()));
                souKyakuMiseKbnNtake = souKyakuMiseKbnNtake.add(numNull(kyakuSeg.getMiseKbnNtake()));
                souKyakusuNtake = souKyakusuNtake.add(numNull(kyakuSeg.getKyakusuNtake()));
                souKyakusuNtakeZen = souKyakusuNtakeZen.add(numNull(kyakuSeg.getKyakusuNtakeZen()));
                souKyakuMiseKbnNtaku = souKyakuMiseKbnNtaku.add(numNull(kyakuSeg.getMiseKbnNtaku()));
                souKyakusuNtaku = souKyakusuNtaku.add(numNull(kyakuSeg.getKyakusuNtaku()));
                souKyakusuNtakuZen = souKyakusuNtakuZen.add(numNull(kyakuSeg.getKyakusuNtakuZen()));
            }
        }

        TrnSibuKyakusuNipoRelate touKyaku = new TrnSibuKyakusuNipoRelate();

        touKyaku.setRClass( NipoRefConstants.CSS_TR_CLASS_TOTAL );
        touKyaku.setCompanyCd( companyCd );
        touKyaku.setSibuCd( "ALL" );
        touKyaku.setSibuName( "総合計" );
        touKyaku.setTenpoCount( souTenCount );
        touKyaku.setZenTenpoCount( souZenCount );
        touKyaku.setSibuNameClass( noClass );
        touKyaku.setKyakusu( souKyaku );
        touKyaku.setKyakusuClass( bodyNumClass );
        touKyaku.setZenKyakusu( souZenKya );
        touKyaku.setZenKyakusuClass( bodyNumClass );
        touKyaku.setDispKbn( "DISP" );

        touKyaku.setMiseKbnNsum(souKyakuMiseKbnNsum);
        touKyaku.setKyakusuNsum(souKyakusuNsum);
        touKyaku.setKyakusuNsumZen(souKyakusuNsumZen);
        touKyaku.setMiseKbnNtake(souKyakuMiseKbnNtake);
        touKyaku.setKyakusuNtake(souKyakusuNtake);
        touKyaku.setKyakusuNtakeZen(souKyakusuNtakeZen);
        touKyaku.setMiseKbnNtaku(souKyakuMiseKbnNtaku);
        touKyaku.setKyakusuNtaku(souKyakusuNtaku);
        touKyaku.setKyakusuNtakuZen(souKyakusuNtakuZen);

        Map paramMap = new HashMap();
        paramMap.put("uriage",uriTou);
        paramMap.put("kyakusu",touKyaku );

        touKyaku = calcHiritu(paramMap, digitCnt);
        kyaku.add(touKyaku);

        returnMap.put("uriage",uri);
        returnMap.put("kyakusu",kyaku);

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
     * @param digitCnt 売上値小数桁数
     */
    public Map segList(Map paramMap, int digitCnt ) {

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
        if ( tenpoShu.equals( TenpoShubetu.CODE_ZENNEN ) ) {
            dataShuKbn = "1";
        } else if ( tenpoShu.equals( TenpoShubetu.CODE_YOSAN ) ) {
            dataShuKbn = "2";
        } else if ( tenpoShu.equals( TenpoShubetu.CODE_SIN ) ) {
            dataShuKbn = "3";
        } else {
        }

        // セグメントリスト作成
        List segmentList = new ArrayList();

        List segtpList = getMstSegmentInfoDao().getSegmentInfo( companyCd );

        for ( int i = 0 ; i < segtpList.size() ; i++ ) {
            MstSegmentInfo entity = (MstSegmentInfo)segtpList.get(i);
            segmentList.add( entity.getSegmentType() );
        }

        List uriageSegList = new ArrayList();
        List yosanSegList = new ArrayList();

        if (  taishoKikan.equals("APPMONTH") ) {
            kikanFrom = kikanFrom.substring(0,6);
        }

        // 前年同月
        if ( dataShu.equals( NipoZennenDataShubetu.CODE_DOGETU ) ) {
            // 月報
            if ( taishoKikan.equals("MONTH") || taishoKikan.equals("APPMONTH") ) {
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
                                                                                    ,segmentList);
                // 期別期報
                } else if ( taishoKikan.equals("KIBETU") ) {
                    uriageSegList = getTrnUriageSegInfoDao().getDougetuSegUriageGepoKikan( companyCd
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
														                            ,segmentList);
            // 期日指定日報
            } else if ( taishoKikan.equals("DAY1" ) ) {
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
                                                                            ,segmentList);
            // 期間指定
            } else if ( taishoKikan.equals("DAYS" ) ){
                uriageSegList = getTrnUriageSegInfoDao().getDougetuSegUriageKikan( companyCd
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
                                                                                ,segmentList);

            }
        // 前年同曜
        } else if (dataShu.equals( NipoZennenDataShubetu.CODE_DOYO ) ) {
            // 月報
            if ( taishoKikan.equals("MONTH") || taishoKikan.equals("APPMONTH") ) {
                uriageSegList = getTrnUriageSegInfoDao().getDouyouSegUriageGepo(companyCd
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
                                                                            ,segmentList);
                // 期別期報
                } else if ( taishoKikan.equals("KIBETU") ) {
                	  uriageSegList = getTrnUriageSegInfoDao().getDouyouSegUriageGepoKikan(companyCd
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
                                                                           ,segmentList);

            // 期日指定日報
            } else if ( taishoKikan.equals("DAY1" ) ) {
                uriageSegList = getTrnUriageSegInfoDao().getDouyouSegUriage(companyCd
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
                                                                            ,segmentList);
            // 期間指定
            } else if ( taishoKikan.equals("DAYS" ) ){
                uriageSegList = getTrnUriageSegInfoDao().getDouyouSegUriageKikan( companyCd
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
                                                                                ,segmentList);

            }

        } else if ( dataShu.equals( NipoZennenDataShubetu.CODE_HOSEI ) ) {
            // 月報
            if ( taishoKikan.equals("MONTH") || taishoKikan.equals("APPMONTH") ) {
                uriageSegList = getTrnUriageSegInfoDao().getDougetuHoseiSegUriageGepo(companyCd
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
                                                                                    ,segmentList);

                // 期別期報
            } else if ( taishoKikan.equals("KIBETU") ) {
                uriageSegList = getTrnUriageSegInfoDao().getDougetuHoseiSegUriageGepoKikan(companyCd
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
                                                                                  ,segmentList);
            // 期日指定日報
            } else if ( taishoKikan.equals("DAY1" ) ) {
                uriageSegList = getTrnUriageSegInfoDao().getDougetuHoseiSegUriage(companyCd
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
                                                                                ,segmentList);
            // 期間指定
            } else if ( taishoKikan.equals("DAYS" ) ){
                uriageSegList = getTrnUriageSegInfoDao().getDougetuHoseiSegUriageKikan( companyCd
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
                                                                                        ,segmentList);

            }

        }
// add USI蔡 2018/08/13 begin --前年同日処理を追加
     // 前年同日
        else if (dataShu.equals( NipoZennenDataShubetu.CODE_DOJITU ) ) {
            // 月報
            if ( taishoKikan.equals("MONTH") || taishoKikan.equals("APPMONTH") ) {
                uriageSegList = getTrnUriageSegInfoDao().getDoujituSegUriageGepo(companyCd
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
                                                                                ,segmentList);
            // 期別期報
            } else if ( taishoKikan.equals("KIBETU") ) {
                uriageSegList = getTrnUriageSegInfoDao().getDoujituSegUriageGepoKikan(companyCd
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
                                                                       ,segmentList);
            // 期日指定日報
            } else if ( taishoKikan.equals("DAY1" ) ) {
                uriageSegList = getTrnUriageSegInfoDao().getDoujituSegUriage(companyCd
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
                                                                            ,segmentList);
            // 期間指定
            } else if ( taishoKikan.equals("DAYS" ) ){
                uriageSegList = getTrnUriageSegInfoDao().getDoujituSegUriageKikan( companyCd
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
                                                                                 ,segmentList);
            }
        }
// add USI蔡 2018/08/13 end

        if ( taishoKikan.equals("APPMONTH") ) {
            kikanFrom = kikanFrom + "01";
        }
        if ( taishoKikan.equals("MONTH") || taishoKikan.equals("KIBETU") ) {
            yosanSegList = getTrnYosanSegInfoDao().getSegYosanGepo( companyCd
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
                                                                    ,segmentList);
        // 日報時
        } else {
            yosanSegList = getTrnYosanSegInfoDao().getSegYosanNipo( companyCd
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
                                                                    ,segmentList);
        }

        Map resultMap = new HashMap();



        resultMap.put("uriage",uriageSegList);
        resultMap.put("yosan",yosanSegList);
        // 総合計取得処理変更(セグメント計→支部計)
        resultMap.put("sibuUriList", paramMap.get("uriageList") ) ;
        resultMap.put("sibuKyakuList", paramMap.get("kyakusuList") ) ;
        resultMap.put("companyCd", companyCd);
        //モス合計、モス以外合計、総合計を作成する
        resultMap = getSegReadJustMap( resultMap, digitCnt );
        BigDecimal yosanMiseCnt = new BigDecimal("0");
        if(yosanSegList.size() > 0) {
        	TrnSibuYosanNipoSegRelate trnYosan =
        		(TrnSibuYosanNipoSegRelate)yosanSegList.get(yosanSegList.size() - 1 );
        	yosanMiseCnt = trnYosan.getMiseCnt();
        }
        //予算対象店舗数設定
        resultMap.put(NetorderNipoConstants.MAP_YOSAN_TENPO_COUNT, yosanMiseCnt);

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
        BigDecimal areaYosanKei = uriNipo.getYosan();
        BigDecimal areaZenUriKei =  uriNipo.getZenUriage();
        BigDecimal areaMiseKbnNsum = uriNipo.getMiseKbnNsum();
        BigDecimal areaUriageNsumKei = uriNipo.getUriageNsum();
        BigDecimal areaMiseKbnNtake = uriNipo.getMiseKbnNtake();
        BigDecimal areaUriageNtakeKei = uriNipo.getUriageNtake();
        BigDecimal areaMiseKbnNtaku = uriNipo.getMiseKbnNtaku();
        BigDecimal areaUriageNtakuKei = uriNipo.getUriageNtaku();
        BigDecimal areaUriageNsumKeiZen = uriNipo.getUriageNsumZen();
        BigDecimal areaUriageNtakeKeiZen = uriNipo.getUriageNtakeZen();
        BigDecimal areaUriageNtakuKeiZen = uriNipo.getUriageNtakuZen();
        //ローズ店予算表示対応
        BigDecimal yosanTenpoKei =  uriNipo.getYosanMiseCnt();
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
                areaUriageKei = areaUriageKei.add( uriNipo.getUriage() );
                areaYosanKei = areaYosanKei.add( uriNipo.getYosan() );
                areaZenUriKei = areaZenUriKei.add( uriNipo.getZenUriage() );

                areaMiseKbnNsum = areaMiseKbnNsum.add(uriNipo.getMiseKbnNsum());
                areaUriageNsumKei = areaUriageNsumKei.add(uriNipo.getUriageNsum() );
                areaMiseKbnNtake = areaMiseKbnNtake.add(uriNipo.getMiseKbnNtake());
                areaUriageNtakeKei = areaUriageNtakeKei.add(uriNipo.getUriageNtake() );
                areaMiseKbnNtaku = areaMiseKbnNtaku.add(uriNipo.getMiseKbnNtaku());
                areaUriageNtakuKei = areaUriageNtakuKei.add(uriNipo.getUriageNtaku() );
                areaUriageNsumKeiZen = areaUriageNsumKeiZen.add(uriNipo.getUriageNsumZen() );
                areaUriageNtakeKeiZen = areaUriageNtakeKeiZen.add(uriNipo.getUriageNtakeZen() );
                areaUriageNtakuKeiZen = areaUriageNtakuKeiZen.add(uriNipo.getUriageNtakuZen() );
                //クローズ店予算表示対応
                yosanTenpoKei = yosanTenpoKei.add( uriNipo.getYosanMiseCnt() );
            } else {
                TrnSibuUriageNipoRelate uriKei = new TrnSibuUriageNipoRelate();

                if ( kbnTani.equals( area ) ) {
                    uriKei.setRClass( NipoRefConstants.CSS_TR_CLASS_SLAREA );
                    uriKei.setSibuCd( befUriNipo.getSlareaCd() );
                    uriKei.setSibuName( befUriNipo.getSlareaName().trim() + "計");
                    uriKei.setSibuNameClass( noClass );
                } else if ( kbnTani.equals( jigyo ) ) {
                    uriKei.setRClass( NipoRefConstants.CSS_TR_CLASS_JIGYOU );
                    uriKei.setSibuCd( befUriNipo.getJigyoCd() );
                    uriKei.setSibuName( befUriNipo.getJigyoName().trim() + "計");
                    uriKei.setSibuNameClass( noClass );
                } else if ( kbnTani.equals( toukatu ) ) {
                    uriKei.setRClass( NipoRefConstants.CSS_TR_CLASS_HONBU );
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
                uriKei.setUriage( areaUriageKei );
                uriKei.setUriageClass( bodyNumClass );
                //クローズ店予算表示対応
                uriKei.setYosanMiseCnt( yosanTenpoKei );
                uriKei.setYosan( areaYosanKei );
                uriKei.setYosanClass( bodyNumClass );
                uriKei.setZenUriage( areaZenUriKei );
                uriKei.setZenUriageClass( bodyNumClass );

                uriKei.setMiseKbnNsum(areaMiseKbnNsum);
                uriKei.setUriageNsum(areaUriageNsumKei);
                uriKei.setUriageNsumZen(areaUriageNsumKeiZen);
                uriKei.setMiseKbnNtake(areaMiseKbnNtake);
                uriKei.setUriageNtake(areaUriageNtakeKei);
                uriKei.setMiseKbnNtaku(areaMiseKbnNtaku);
                uriKei.setUriageNtakeZen(areaUriageNtakeKeiZen);
                uriKei.setUriageNtaku(areaUriageNtakuKei);
                uriKei.setUriageNtakuZen(areaUriageNtakuKeiZen);

                TrnSibuUriageNipoRelate areaUriKei = calcHiritu( uriKei );

                areaUriageList.add( areaUriKei );

                //合計値初期化設定
                areaTouTenpoKei = uriNipo.getTenpoCount();
                areaZenTenpoKei = uriNipo.getZenTenpoCount();
                areaUriageKei = uriNipo.getUriage();
                areaYosanKei =  uriNipo.getYosan();
                areaZenUriKei = uriNipo.getZenUriage();
                areaMiseKbnNsum = uriNipo.getMiseKbnNsum();
                areaUriageNsumKei = uriNipo.getUriageNsum();
                areaMiseKbnNtake = uriNipo.getMiseKbnNtake();
                areaUriageNtakeKei = uriNipo.getUriageNtake();
                areaMiseKbnNtaku = uriNipo.getMiseKbnNtaku();
                areaUriageNtakuKei = uriNipo.getUriageNtaku();
                areaUriageNsumKeiZen = uriNipo.getUriageNsumZen();
                areaUriageNtakeKeiZen = uriNipo.getUriageNtakeZen();
                areaUriageNtakuKeiZen = uriNipo.getUriageNtakuZen();
                //クローズ店予算表示対応
                yosanTenpoKei =  uriNipo.getYosanMiseCnt();
            }
        }

        TrnSibuUriageNipoRelate uriKei = new TrnSibuUriageNipoRelate();

        if ( kbnTani.equals( area ) ) {
            uriKei.setRClass( NipoRefConstants.CSS_TR_CLASS_SLAREA );
            uriKei.setSibuCd( uriNipo.getSlareaCd() );
            uriKei.setSibuName( uriNipo.getSlareaName().trim() + "計" );
        } else if ( kbnTani.equals( jigyo ) ) {
            uriKei.setRClass( NipoRefConstants.CSS_TR_CLASS_JIGYOU );
            uriKei.setSibuCd( uriNipo.getJigyoCd() );
            uriKei.setSibuName( uriNipo.getJigyoName().trim() + "計" );
        } else if ( kbnTani.equals( toukatu ) ) {
            uriKei.setRClass( NipoRefConstants.CSS_TR_CLASS_HONBU );
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
        uriKei.setUriage( areaUriageKei );
        uriKei.setUriageClass( bodyNumClass );
        //クローズ店予算表示対応
        uriKei.setYosanMiseCnt( yosanTenpoKei );
        uriKei.setYosan( areaYosanKei );
        uriKei.setYosanClass( bodyNumClass );
        uriKei.setZenUriage( areaZenUriKei );
        uriKei.setZenUriageClass( bodyNumClass );

        uriKei.setMiseKbnNsum(areaMiseKbnNsum);
        uriKei.setUriageNsum(areaUriageNsumKei);
        uriKei.setUriageNsumZen(areaUriageNsumKeiZen);
        uriKei.setMiseKbnNtake(areaMiseKbnNtake);
        uriKei.setUriageNtake(areaUriageNtakeKei);
        uriKei.setMiseKbnNtaku(areaMiseKbnNtaku);
        uriKei.setUriageNtakeZen(areaUriageNtakeKeiZen);
        uriKei.setUriageNtaku(areaUriageNtakuKei);
        uriKei.setUriageNtakuZen(areaUriageNtakuKeiZen);

        TrnSibuUriageNipoRelate areaUriKei = calcHiritu( uriKei );

        areaUriageList.add( areaUriKei );

        return areaUriageList;
    }

    /**
     * 支部単位 → エリア単位 → 事業単位 → 本部単位へサマリする(客数用)
     * @param kyakusuList 集約未済みの客数リスト
     * @param uriageList 集約済みの売上リスト
     * @param kbnTani 集約タイプ
     * @return
     */
    public List sumKyakusuList( List kyakusuList, List uriageList , String kbnTani, int digitCnt) {

        TrnSibuKyakusuNipoRelate kyakuNipo = (TrnSibuKyakusuNipoRelate)kyakusuList.get(0);

        // 集計(売上)
        List areaUriageList = sumUriageList( uriageList,kbnTani);

        // 集計(客数)
        BigDecimal areaKyakuTouTenpoKei = new BigDecimal(0);
        BigDecimal areaKyakuZenTenpoKei = new BigDecimal(0);
        BigDecimal areaKyakusuKei = new BigDecimal(0);
        BigDecimal areaZenKyakuKei = new BigDecimal(0);
        BigDecimal areaKyakusuNsumKei = new BigDecimal(0);
        BigDecimal areaKyakusuNtakeKei = new BigDecimal(0);
        BigDecimal areaKyakusuNtakuKei = new BigDecimal(0);
        BigDecimal areaKyakusuNsumKeiZen = new BigDecimal(0);
        BigDecimal areaKyakusuNtakeKeiZen = new BigDecimal(0);
        BigDecimal areaKyakusuNtakuKeiZen = new BigDecimal(0);
        BigDecimal areaMiseKbnNsum = new BigDecimal(0);
        BigDecimal areaMiseKbnNtake = new BigDecimal(0);
        BigDecimal areaMiseKbnNtaku = new BigDecimal(0);

        areaKyakuTouTenpoKei = areaKyakuTouTenpoKei.add( kyakuNipo.getTenpoCount() );
        areaKyakuZenTenpoKei = areaKyakuZenTenpoKei.add( kyakuNipo.getZenTenpoCount() );
        areaKyakusuKei = areaKyakusuKei.add( kyakuNipo.getKyakusu() );
        areaZenKyakuKei = areaZenKyakuKei.add( kyakuNipo.getZenKyakusu() );
        areaKyakusuNsumKei = areaKyakusuNsumKei.add(kyakuNipo.getKyakusuNsum());
        areaKyakusuNtakeKei = areaKyakusuNtakeKei.add(kyakuNipo.getKyakusuNtake());
        areaKyakusuNtakuKei = areaKyakusuNtakuKei.add(kyakuNipo.getKyakusuNtaku());
        areaKyakusuNsumKeiZen = areaKyakusuNsumKeiZen.add(kyakuNipo.getKyakusuNsumZen());
        areaKyakusuNtakeKeiZen = areaKyakusuNtakeKeiZen.add(kyakuNipo.getKyakusuNtakeZen());
        areaKyakusuNtakuKeiZen = areaKyakusuNtakuKeiZen.add(kyakuNipo.getKyakusuNtakuZen());
        areaMiseKbnNsum = areaMiseKbnNsum.add(kyakuNipo.getMiseKbnNsum());
        areaMiseKbnNtake = areaMiseKbnNtake.add(kyakuNipo.getMiseKbnNtake());
        areaMiseKbnNtaku = areaMiseKbnNtaku.add(kyakuNipo.getMiseKbnNtaku());


        List areaKyakuKeiList = new ArrayList();

        // 客数を求める
        for ( int i = 1; i < kyakusuList.size(); i++ ) {
            kyakuNipo = (TrnSibuKyakusuNipoRelate)kyakusuList.get(i);
            TrnSibuKyakusuNipoRelate befKyakuNipo = (TrnSibuKyakusuNipoRelate)kyakusuList.get( i - 1 ) ;

            String curCd = new String();
            String befCurCd = new String();

            // 対象コードを特定
            if ( kbnTani.equals( area ) ) {
               curCd = kyakuNipo.getSlareaCd().trim();
               befCurCd = befKyakuNipo.getSlareaCd().trim();
            } else if ( kbnTani.equals( jigyo ) ) {
                curCd = kyakuNipo.getJigyoCd().trim();
                befCurCd = befKyakuNipo.getJigyoCd().trim();
            } else if ( kbnTani.equals( toukatu ) ) {
                curCd= kyakuNipo.getHonbuCd().trim();
                befCurCd = befKyakuNipo.getHonbuCd().trim();
            }

            // エリア毎の計を求める(客数)
            if ( curCd.equals( befCurCd ) ) {
                areaKyakuTouTenpoKei = areaKyakuTouTenpoKei.add( kyakuNipo.getTenpoCount() );
                areaKyakuZenTenpoKei = areaKyakuZenTenpoKei.add( kyakuNipo.getZenTenpoCount() );
                areaKyakusuKei = areaKyakusuKei.add( kyakuNipo.getKyakusu() );
                areaZenKyakuKei = areaZenKyakuKei.add( kyakuNipo.getZenKyakusu() );
                areaKyakusuNsumKei = areaKyakusuNsumKei.add(kyakuNipo.getKyakusuNsum());
                areaKyakusuNtakeKei = areaKyakusuNtakeKei.add(kyakuNipo.getKyakusuNtake());
                areaKyakusuNtakuKei = areaKyakusuNtakuKei.add(kyakuNipo.getKyakusuNtaku());
                areaKyakusuNsumKeiZen = areaKyakusuNsumKeiZen.add(kyakuNipo.getKyakusuNsumZen());
                areaKyakusuNtakeKeiZen = areaKyakusuNtakeKeiZen.add(kyakuNipo.getKyakusuNtakeZen());
                areaKyakusuNtakuKeiZen = areaKyakusuNtakuKeiZen.add(kyakuNipo.getKyakusuNtakuZen());
                areaMiseKbnNsum = areaMiseKbnNsum.add(kyakuNipo.getMiseKbnNsum());
                areaMiseKbnNtake = areaMiseKbnNtake.add(kyakuNipo.getMiseKbnNtake());
                areaMiseKbnNtaku = areaMiseKbnNtaku.add(kyakuNipo.getMiseKbnNtaku());

            } else {
                TrnSibuKyakusuNipoRelate kyakuKei = new TrnSibuKyakusuNipoRelate();
                if ( kbnTani.equals( area ) ) {
                    kyakuKei.setRClass( NipoRefConstants.CSS_TR_CLASS_SLAREA );
                    kyakuKei.setSibuCd( befKyakuNipo.getSlareaCd() );
                    kyakuKei.setSibuName( befKyakuNipo.getSlareaName().trim() + "計" );
                } else if ( kbnTani.equals( jigyo ) ) {
                    kyakuKei.setRClass( NipoRefConstants.CSS_TR_CLASS_JIGYOU );
                    kyakuKei.setSibuCd( befKyakuNipo.getJigyoCd() );
                    kyakuKei.setSibuName( befKyakuNipo.getJigyoName().trim() + "計" );
                } else if ( kbnTani.equals( toukatu ) ) {
                    kyakuKei.setRClass( NipoRefConstants.CSS_TR_CLASS_HONBU );
                    kyakuKei.setSibuCd( befKyakuNipo.getHonbuCd() );
                    kyakuKei.setSibuName( befKyakuNipo.getHonbuName().trim() + "計" );
                }
                kyakuKei.setCompanyCd( befKyakuNipo.getCompanyCd() );
                kyakuKei.setDispKbn( "DISP" );
                kyakuKei.setHonbuCd( befKyakuNipo.getHonbuCd() );
                kyakuKei.setHonbuName( befKyakuNipo.getHonbuName() );
                kyakuKei.setJigyoCd( befKyakuNipo.getJigyoCd() );
                kyakuKei.setJigyoName( befKyakuNipo.getJigyoName());
                kyakuKei.setSlareaCd( befKyakuNipo.getSlareaCd() );
                kyakuKei.setSlareaName( befKyakuNipo.getSlareaName() );
                kyakuKei.setSibuNameClass( noClass );
                kyakuKei.setTenpoCount( areaKyakuTouTenpoKei );
                kyakuKei.setZenTenpoCount( areaKyakuZenTenpoKei );
                kyakuKei.setKyakusu( areaKyakusuKei );
                kyakuKei.setKyakusuClass( bodyNumClass );
                kyakuKei.setZenKyakusu( areaZenKyakuKei );
                kyakuKei.setZenKyakusuClass( bodyNumClass );


                kyakuKei.setKyakusuNsum( areaKyakusuNsumKei );
                kyakuKei.setKyakusuNtake( areaKyakusuNtakeKei );
                kyakuKei.setKyakusuNtaku( areaKyakusuNtakuKei );
                kyakuKei.setKyakusuNsumZen( areaKyakusuNsumKeiZen );
                kyakuKei.setKyakusuNtakeZen( areaKyakusuNtakeKeiZen );
                kyakuKei.setKyakusuNtakuZen( areaKyakusuNtakuKeiZen );
                kyakuKei.setMiseKbnNsum(areaMiseKbnNsum);
                kyakuKei.setMiseKbnNtake(areaMiseKbnNtake);
                kyakuKei.setMiseKbnNtaku(areaMiseKbnNtaku);


                Map paramMap = new HashMap();

                for( int j = 0; j < areaUriageList.size(); j++ ) {
                    TrnSibuUriageNipoRelate uri = (TrnSibuUriageNipoRelate)areaUriageList.get(j);
                    String uriCd = new String();
                    String kyakuCd = new String();

                    if ( kbnTani.equals(area)) {
                        uriCd = uri.getSlareaCd().trim();
                        kyakuCd =  kyakuKei.getSlareaCd().trim();
                    } else if ( kbnTani.equals(jigyo)) {
                        uriCd = uri.getJigyoCd().trim();
                        kyakuCd =  kyakuKei.getJigyoCd().trim();
                    } else if (kbnTani.equals(toukatu)) {
                        uriCd = uri.getHonbuCd().trim();
                        kyakuCd =  kyakuKei.getHonbuCd().trim();
                    }
                    if ( uriCd.equals(kyakuCd) ) {
                        paramMap.put("uriage",uri );
                        break;
                    }
                }

                paramMap.put("kyakusu",kyakuKei );

                TrnSibuKyakusuNipoRelate areaKyakuKei = calcHiritu( paramMap, digitCnt );

                areaKyakuKeiList.add( areaKyakuKei );

                // 初期化
                areaKyakuTouTenpoKei = new BigDecimal(0);
                areaKyakuZenTenpoKei = new BigDecimal(0);
                areaKyakusuKei = new BigDecimal(0);
                areaZenKyakuKei = new BigDecimal(0);
                areaKyakusuNsumKei = new BigDecimal(0);
                areaKyakusuNtakeKei = new BigDecimal(0);
                areaKyakusuNtakuKei = new BigDecimal(0);
                areaKyakusuNsumKeiZen = new BigDecimal(0);
                areaKyakusuNtakeKeiZen = new BigDecimal(0);
                areaKyakusuNtakuKeiZen = new BigDecimal(0);
                areaMiseKbnNsum = new BigDecimal(0);
                areaMiseKbnNtake = new BigDecimal(0);
                areaMiseKbnNtaku = new BigDecimal(0);

                // 設定
                areaKyakuTouTenpoKei = areaKyakuTouTenpoKei.add( kyakuNipo.getTenpoCount() );
                areaKyakuZenTenpoKei = areaKyakuZenTenpoKei.add( kyakuNipo.getZenTenpoCount() );
                areaKyakusuKei = areaKyakusuKei.add( kyakuNipo.getKyakusu() );
                areaZenKyakuKei = areaZenKyakuKei.add( kyakuNipo.getZenKyakusu() );
                areaKyakusuNsumKei = areaKyakusuNsumKei.add(kyakuNipo.getKyakusuNsum());
                areaKyakusuNtakeKei = areaKyakusuNtakeKei.add(kyakuNipo.getKyakusuNtake());
                areaKyakusuNtakuKei = areaKyakusuNtakuKei.add(kyakuNipo.getKyakusuNtaku());
                areaKyakusuNsumKeiZen = areaKyakusuNsumKeiZen.add(kyakuNipo.getKyakusuNsumZen());
                areaKyakusuNtakeKeiZen = areaKyakusuNtakeKeiZen.add(kyakuNipo.getKyakusuNtakeZen());
                areaKyakusuNtakuKeiZen = areaKyakusuNtakuKeiZen.add(kyakuNipo.getKyakusuNtakuZen());
                areaMiseKbnNsum = areaMiseKbnNsum.add(kyakuNipo.getMiseKbnNsum());
                areaMiseKbnNtake = areaMiseKbnNtake.add(kyakuNipo.getMiseKbnNtake());
                areaMiseKbnNtaku = areaMiseKbnNtaku.add(kyakuNipo.getMiseKbnNtaku());
            }

        }

        TrnSibuKyakusuNipoRelate kyakuKei = new TrnSibuKyakusuNipoRelate();

        if ( kbnTani.equals( area ) ) {
            kyakuKei.setRClass( NipoRefConstants.CSS_TR_CLASS_SLAREA );
            kyakuKei.setSibuCd( kyakuNipo.getSlareaCd() );
            kyakuKei.setSibuName( kyakuNipo.getSlareaName().trim() + "計" );
        } else if ( kbnTani.equals( jigyo ) ) {
            kyakuKei.setRClass( NipoRefConstants.CSS_TR_CLASS_JIGYOU );
            kyakuKei.setSibuCd( kyakuNipo.getJigyoCd() );
            kyakuKei.setSibuName( kyakuNipo.getJigyoName().trim() + "計" );
        } else if ( kbnTani.equals( toukatu ) ) {
            kyakuKei.setRClass( NipoRefConstants.CSS_TR_CLASS_HONBU );
            kyakuKei.setSibuCd( kyakuNipo.getHonbuCd() );
            kyakuKei.setSibuName( kyakuNipo.getHonbuName().trim() + "計" );
        }
        kyakuKei.setSibuNameClass( noClass );
        kyakuKei.setCompanyCd( kyakuNipo.getCompanyCd() );
        kyakuKei.setDispKbn( "DISP" );
        kyakuKei.setHonbuCd( kyakuNipo.getHonbuCd() );
        kyakuKei.setHonbuName( kyakuNipo.getHonbuName() );
        kyakuKei.setJigyoCd( kyakuNipo.getJigyoCd() );
        kyakuKei.setJigyoName(kyakuNipo.getJigyoName() );
        kyakuKei.setSlareaCd( kyakuNipo.getSlareaCd() );
        kyakuKei.setSlareaName( kyakuNipo.getSlareaName() );
        kyakuKei.setTenpoCount( areaKyakuTouTenpoKei );
        kyakuKei.setZenTenpoCount( areaKyakuZenTenpoKei );
        kyakuKei.setKyakusu( areaKyakusuKei );
        kyakuKei.setKyakusuClass( bodyNumClass );
        kyakuKei.setZenKyakusu( areaZenKyakuKei );
        kyakuKei.setZenKyakusuClass( bodyNumClass );

        kyakuKei.setKyakusuNsum( areaKyakusuNsumKei );
        kyakuKei.setKyakusuNtake( areaKyakusuNtakeKei );
        kyakuKei.setKyakusuNtaku( areaKyakusuNtakuKei );
        kyakuKei.setKyakusuNsumZen( areaKyakusuNsumKeiZen );
        kyakuKei.setKyakusuNtakeZen( areaKyakusuNtakeKeiZen );
        kyakuKei.setKyakusuNtakuZen( areaKyakusuNtakuKeiZen );
        kyakuKei.setMiseKbnNsum( areaMiseKbnNsum );
        kyakuKei.setMiseKbnNtake( areaMiseKbnNtake );
        kyakuKei.setMiseKbnNtaku( areaMiseKbnNtaku );


        Map paramMap = new HashMap();

        for( int j = 0; j < areaUriageList.size(); j++ ) {
            TrnSibuUriageNipoRelate uri = (TrnSibuUriageNipoRelate)areaUriageList.get(j);
            if ( (uri.getSlareaCd().trim()).equals( kyakuKei.getSlareaCd().trim() ) ) {
                paramMap.put("uriage",uri );
                break;
            }
        }

        paramMap.put("kyakusu",kyakuKei );
        TrnSibuKyakusuNipoRelate areaKyakuKei = calcHiritu( paramMap, digitCnt );
        areaKyakuKeiList.add( areaKyakuKei );

        return areaKyakuKeiList;
    }
    /**
     * エリア毎の計を求める
     * @param uriageList
     * @param kyakusuList
     * @return
     */
    public Map getResultMap( List uriageList , List kyakusuList, int digitCnt) {

        Map returnMap = new HashMap();

        // エリア計
        List areaUriageList = sumUriageList( uriageList,area);
        List areaKyakusuList = sumKyakusuList( kyakusuList,uriageList,area, digitCnt);
        List totalUriageList = totalUriageInsertList( uriageList, areaUriageList, area );
        List totalKyakusuList = totalKyakuInsertList( kyakusuList, areaKyakusuList, area );

        // 事業計
        List jigyoUriageList = sumUriageList( areaUriageList, jigyo );
        List jigyoKyakusuList = sumKyakusuList( areaKyakusuList,areaUriageList,jigyo, digitCnt);
        totalUriageList = totalUriageInsertList( totalUriageList,jigyoUriageList,jigyo);
        totalKyakusuList = totalKyakuInsertList( totalKyakusuList,jigyoKyakusuList,jigyo);

        // 統括本部計
        List tokatuUriageList = sumUriageList( jigyoUriageList,toukatu);
        List tokatuKyakusuList = sumKyakusuList( jigyoKyakusuList,jigyoUriageList,toukatu, digitCnt);
        totalUriageList = totalUriageInsertList( totalUriageList,tokatuUriageList,toukatu);
        totalKyakusuList = totalKyakuInsertList( totalKyakusuList,tokatuKyakusuList,toukatu);

        returnMap.put( "uriage",totalUriageList);
        returnMap.put("kyakusu",totalKyakusuList);

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
     * @param digitCnt 売上値小数桁数
     * @return
     *
     */
    public Map getReadjustMap(Map paramMap, int digitCnt) {
        //支部・売上・予算情報取得
        List sibuList = (List)paramMap.get("sibu");
        List uriageList = (List)paramMap.get("uriage");
        List yosanList = (List)paramMap.get("yosan");
        //支部・売上・予算整理結果初期化
        List resultUriageList = new ArrayList();
        List resultKyakusuList = new ArrayList();
        Map returnMap = new HashMap();

        for( int i = 0; i < sibuList.size(); i++ ) {
            MstSibuInfo sibu = (MstSibuInfo)sibuList.get(i);
            TrnUriageInfo uriage = new TrnUriageInfo();
            TrnYosanInfo yosan = new TrnYosanInfo();
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
                //対象支部の予算を取得する
                for ( int j = 0; j < yosanList.size(); j++ ) {
                    TrnYosanInfo yos = (TrnYosanInfo)yosanList.get(j);
                    if ( (yos.getCompanyCd().trim()).equals( (sibu.getCompanyCd().trim()))
                            && (yos.getSibuCd().trim()).equals( sibu.getSibuCd().trim() ) ) {
                        yosan = yos;
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
                BigDecimal miseKbnNsum = new BigDecimal(0);
                BigDecimal uriNsum = new BigDecimal(0);
                BigDecimal uriNsumZen = new BigDecimal(0);
                BigDecimal kyaNsum = new BigDecimal(0);
                BigDecimal kyaNsumZen = new BigDecimal(0);
                BigDecimal miseKbnNtake = new BigDecimal(0);
                BigDecimal uriNtake = new BigDecimal(0);
                BigDecimal uriNtakeZen = new BigDecimal(0);
                BigDecimal kyaNtake = new BigDecimal(0);
                BigDecimal kyaNtakeZen = new BigDecimal(0);
                BigDecimal miseKbnNtaku = new BigDecimal(0);
                BigDecimal uriNtaku = new BigDecimal(0);
                BigDecimal uriNtakuZen = new BigDecimal(0);
                BigDecimal kyaNtaku = new BigDecimal(0);
                BigDecimal kyaNtakuZen = new BigDecimal(0);

                if ( sibu.getDispKbn().equals( "DISP" )) {
                    urikin = uriage.getUriage();
                    urizen = uriage.getUriageZen();
                    kyaten = uriage.getOpenKbn();
                    kyazen = uriage.getOpenKbnZen();
                    kyaCou = uriage.getKyakusu();
                    couZen = uriage.getKyakusuZen();
                    miseKbnNsum = uriage.getMiseKbnNsum();
                    uriNsum = uriage.getUriageNsum();
                    uriNsumZen = uriage.getUriageNsumZen();
                    kyaNsum = uriage.getKyakusuNsum();
                    kyaNsumZen = uriage.getKyakusuNsumZen();
                    miseKbnNtake = uriage.getMiseKbnNtake();
                    uriNtake = uriage.getUriageNtake();
                    uriNtakeZen = uriage.getUriageNtakeZen();
                    kyaNtake = uriage.getKyakusuNtake();
                    kyaNtakeZen = uriage.getKyakusuNtakeZen();
                    miseKbnNtaku = uriage.getMiseKbnNtaku();
                    uriNtaku = uriage.getUriageNtaku();
                    uriNtakuZen = uriage.getUriageNtakuZen();
                    kyaNtaku = uriage.getKyakusuNtaku();
                    kyaNtakuZen = uriage.getKyakusuNtakuZen();
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
                uriNipo.setUriageClass( bodyNumClass );
                uriNipo.setTenpoCount( kyaten );
                uriNipo.setZenTenpoCount( kyazen );
                uriNipo.setZenUriage( urizen );
                uriNipo.setZenUriageClass( bodyNumClass );

                uriNipo.setMiseKbnNsum(miseKbnNsum);
                uriNipo.setUriageNsum(uriNsum);
                uriNipo.setUriageNsumZen(uriNsumZen);
                uriNipo.setMiseKbnNtake(miseKbnNtake);
                uriNipo.setUriageNtake(uriNtake);
                uriNipo.setUriageNtakeZen(uriNtakeZen);
                uriNipo.setMiseKbnNtaku(miseKbnNtaku);
                uriNipo.setUriageNtaku(uriNtaku);
                uriNipo.setUriageNtakuZen(uriNtakuZen);

                if ( yosan.getYosan() != null ) {
                    uriNipo.setYosan( yosan.getYosan() );
                    //クローズ店予算表示対応
                    //予算対象店舗数設定
                    uriNipo.setYosanMiseCnt( yosan.getMiseCnt() );
                } else {
                    uriNipo.setYosan( new BigDecimal(0) );
                }

                uriNipo.setYosanClass( bodyNumClass );

                uriNipo.setDispKbn( sibu.getDispKbn() );

                TrnSibuUriageNipoRelate resultUriage = calcHiritu( uriNipo );

                resultUriageList.add( resultUriage );

                // 客数タブ内容
                TrnSibuKyakusuNipoRelate kyakuNipo = new TrnSibuKyakusuNipoRelate();

                kyakuNipo.setRClass( noClass );
                kyakuNipo.setCompanyCd( sibu.getCompanyCd() );
                kyakuNipo.setSibuCd( sibu.getSibuCd() );
                kyakuNipo.setSibuName( sibu.getSibuName().trim() );
                kyakuNipo.setSibuNameClass( noClass );
                kyakuNipo.setTenpoCount( kyaten );
                kyakuNipo.setZenTenpoCount( kyazen );
                kyakuNipo.setHonbuCd( sibu.getHonbuCd() );
                kyakuNipo.setHonbuName( sibu.getHonbuName().trim() );
                kyakuNipo.setJigyoCd( sibu.getJigyouCd() );
                kyakuNipo.setJigyoName( sibu.getJigyouName().trim());
                kyakuNipo.setSlareaCd( sibu.getSlareaCd() );
                kyakuNipo.setSlareaName( sibu.getSlareaName().trim() );

                kyakuNipo.setKyakusu( kyaCou );
                kyakuNipo.setKyakusuClass( bodyNumClass );

                kyakuNipo.setDispKbn( sibu.getDispKbn() );

                kyakuNipo.setZenKyakusu( couZen );
                kyakuNipo.setZenKyakusuClass( bodyNumClass );

                kyakuNipo.setMiseKbnNsum(miseKbnNsum);
                kyakuNipo.setKyakusuNsum(kyaNsum);
                kyakuNipo.setKyakusuNsumZen(kyaNsumZen);
                kyakuNipo.setMiseKbnNtake(miseKbnNtake);
                kyakuNipo.setKyakusuNtake(kyaNtake);
                kyakuNipo.setKyakusuNtakeZen(kyaNtakeZen);
                kyakuNipo.setMiseKbnNtaku(miseKbnNtaku);
                kyakuNipo.setKyakusuNtaku(kyaNtaku);
                kyakuNipo.setKyakusuNtakuZen(kyaNtakuZen);

                Map map = new HashMap();
                map.put("uriage",resultUriage );
                map.put("kyakusu",kyakuNipo );

                resultKyakusuList.add( calcHiritu(map, digitCnt) );
            }
        }


        returnMap.put("uriage",resultUriageList);
        returnMap.put("kyakusu",resultKyakusuList);

        return returnMap;
    }

    /**
     * 売上比率算出＋クラス指定
     * @param uriNipo
     * @return
     */
    public TrnSibuUriageNipoRelate calcHiritu( TrnSibuUriageNipoRelate uriNipo ) {

        BigDecimal tasRitu = Calculator.percentage(uriNipo.getUriage(),uriNipo.getYosan(),2);
        BigDecimal uriHi = Calculator.percentage(uriNipo.getUriage(),uriNipo.getZenUriage(),2);
        BigDecimal uriNsumHi = Calculator.percentage(uriNipo.getUriageNsum(),uriNipo.getUriageNsumZen(),2);
        BigDecimal uriNsumKoseiHi = Calculator.percentage(uriNipo.getUriageNsum(),uriNipo.getUriage(),2);
        BigDecimal uriNtakeHi = Calculator.percentage(uriNipo.getUriageNtake(),uriNipo.getUriageNtakeZen(),2);
        BigDecimal uriNtakeKoseiHi = Calculator.percentage(uriNipo.getUriageNtake(),uriNipo.getUriage(),2);
        BigDecimal uriNtakuHi = Calculator.percentage(uriNipo.getUriageNtaku(),uriNipo.getUriageNtakuZen(),2);
        BigDecimal uriNtakuKoseiHi = Calculator.percentage(uriNipo.getUriageNtaku(),uriNipo.getUriage(),2);

        if ( tasRitu.compareTo(new BigDecimal("0")) !=1 ) {
            tasRitu = new BigDecimal("0.00");
        }
        if ( uriHi.compareTo(new BigDecimal("0")) == 0 ) {
            uriHi = new BigDecimal("0.00");
        }
        if ( uriNsumHi.compareTo(new BigDecimal("0")) == 0 ) {
        	uriNsumHi = new BigDecimal("0.00");
        }
        if ( uriNsumKoseiHi.compareTo(new BigDecimal("0")) == 0 ) {
        	uriNsumKoseiHi = new BigDecimal("0.00");
        }
        if ( uriNtakeHi.compareTo(new BigDecimal("0")) == 0 ) {
        	uriNtakeHi = new BigDecimal("0.00");
        }
        if ( uriNtakeKoseiHi.compareTo(new BigDecimal("0")) == 0 ) {
        	uriNtakeKoseiHi = new BigDecimal("0.00");
        }
        if ( uriNtakuHi.compareTo(new BigDecimal("0")) == 0 ) {
        	uriNtakuHi = new BigDecimal("0.00");
        }
        if ( uriNtakuKoseiHi.compareTo(new BigDecimal("0")) == 0 ) {
        	uriNtakuKoseiHi = new BigDecimal("0.00");
        }

        uriNipo.setTasseiYosan( tasRitu );
        uriNipo.setZenHiSa( uriHi );
        uriNipo.setUriageNsumZenHi(uriNsumHi);
        uriNipo.setUriageNsumKoseiHi(uriNsumKoseiHi);
        uriNipo.setUriageNtakeZenHi(uriNtakeHi);
        uriNipo.setUriageNtakeKoseiHi(uriNtakeKoseiHi);
        uriNipo.setUriageNtakuZenHi(uriNtakuHi);
        uriNipo.setUriageNtakuKoseiHi(uriNtakuKoseiHi);

        BigDecimal hi = new BigDecimal(100);

        if ( uriNipo.getRClass().equals( noClass ) ) {
            if ( uriHi.compareTo( hi ) < 0 ) {
                uriNipo.setZenHiSaClass( bodyHirituClassM );
            } else {
                uriNipo.setZenHiSaClass( bodyNumClassN );
            }
            if ( tasRitu.compareTo( hi ) < 0 ) {
                uriNipo.setTasseiYosanClass( bodyHirituClassM );
            } else {
                uriNipo.setTasseiYosanClass( bodyNumClassN );
            }
        } else {
            if ( uriHi.compareTo( hi ) < 0 ) {
                uriNipo.setZenHiSaClass( bodyHirituClass );
            } else {
                uriNipo.setZenHiSaClass( bodyNumClass );
            }
            if ( tasRitu.compareTo( hi ) < 0 ) {
                uriNipo.setTasseiYosanClass( bodyHirituClass );
            } else {
                uriNipo.setTasseiYosanClass( bodyNumClass );
            }
        }

        return uriNipo;
    }

    /**
     * 客数の比率算出＋クラス指定＋客単価計算
     * @param kyakuNipo
     * @return
     */
    public TrnSibuKyakusuNipoRelate calcHiritu( Map paramMap, int digitCnt ) {
        TrnSibuUriageNipoRelate uriNipo = (TrnSibuUriageNipoRelate)paramMap.get("uriage");
        TrnSibuKyakusuNipoRelate kyakuNipo = (TrnSibuKyakusuNipoRelate)paramMap.get("kyakusu");

        // 当年客単価
        BigDecimal kyakuTanka = Calculator.divide(uriNipo.getUriage(),kyakuNipo.getKyakusu(), digitCnt);
        BigDecimal kyakuTankaNsum = Calculator.divide(uriNipo.getUriageNsum(),kyakuNipo.getKyakusuNsum(), digitCnt);
        BigDecimal kyakuTankaNtake = Calculator.divide(uriNipo.getUriageNtake(),kyakuNipo.getKyakusuNtake(), digitCnt);
        BigDecimal kyakuTankaNtaku = Calculator.divide(uriNipo.getUriageNtaku(),kyakuNipo.getKyakusuNtaku(), digitCnt);
        kyakuNipo.setKyakuTanka( kyakuTanka );
        kyakuNipo.setKyakuTankaNsum(kyakuTankaNsum);
        kyakuNipo.setKyakuTankaNtake(kyakuTankaNtake);
        kyakuNipo.setKyakuTankaNtaku(kyakuTankaNtaku);
        kyakuNipo.setKyakuTankaClass( bodyNumClass );

        // 前年客単価
        BigDecimal zenKyakuTanka = Calculator.divide(uriNipo.getZenUriage(),kyakuNipo.getZenKyakusu(), digitCnt);
        BigDecimal zenKyakuTankaNsum = Calculator.divide(uriNipo.getUriageNsumZen(),kyakuNipo.getKyakusuNsumZen(), digitCnt);
        BigDecimal zenKyakuTankaNtake = Calculator.divide(uriNipo.getUriageNtakeZen(),kyakuNipo.getKyakusuNtakeZen(), digitCnt);
        BigDecimal zenKyakuTankaNtaku = Calculator.divide(uriNipo.getUriageNtakuZen(),kyakuNipo.getKyakusuNtakuZen(), digitCnt);

        BigDecimal kyakuHi = Calculator.percentage( kyakuNipo.getKyakusu(),kyakuNipo.getZenKyakusu(),2);
        BigDecimal tankaHi = Calculator.percentage( kyakuTanka,zenKyakuTanka,2);
        BigDecimal kyakuNsumHi = Calculator.percentage( kyakuNipo.getKyakusuNsum(),kyakuNipo.getKyakusuNsumZen(),2);
        BigDecimal kyakuNsumKoseiHi = Calculator.percentage( kyakuNipo.getKyakusuNsum(),kyakuNipo.getKyakusu(),2);
        BigDecimal tankaNsumHi = Calculator.percentage( kyakuTankaNsum,zenKyakuTankaNsum,2);
        BigDecimal kyakuNtakeHi = Calculator.percentage( kyakuNipo.getKyakusuNtake(),kyakuNipo.getKyakusuNtakeZen(),2);
        BigDecimal kyakuNtakeKoseiHi = Calculator.percentage( kyakuNipo.getKyakusuNtake(),kyakuNipo.getKyakusu(),2);
        BigDecimal tankaNtakeHi = Calculator.percentage( kyakuTankaNtake,zenKyakuTankaNtake,2);
        BigDecimal kyakuNtakuHi = Calculator.percentage( kyakuNipo.getKyakusuNtaku(),kyakuNipo.getKyakusuNtakuZen(),2);
        BigDecimal kyakuNtakuKoseiHi = Calculator.percentage( kyakuNipo.getKyakusuNtaku(),kyakuNipo.getKyakusu(),2);
        BigDecimal tankaNtakuHi = Calculator.percentage( kyakuTankaNtaku,zenKyakuTankaNtaku,2);

        if ( kyakuHi.compareTo(new BigDecimal("0")) == 0 ) {
            kyakuHi = new BigDecimal("0.00");
        }
        if ( tankaHi.compareTo(new BigDecimal("0")) == 0 ) {
            tankaHi = new BigDecimal("0.00");
        }
        if ( kyakuNsumHi.compareTo(new BigDecimal("0")) == 0 ) {
        	kyakuNsumHi = new BigDecimal("0.00");
        }
        if ( kyakuNsumKoseiHi.compareTo(new BigDecimal("0")) == 0 ) {
        	kyakuNsumKoseiHi = new BigDecimal("0.00");
        }
        if ( tankaNsumHi.compareTo(new BigDecimal("0")) == 0 ) {
        	tankaNsumHi = new BigDecimal("0.00");
        }
        if ( kyakuNtakeHi.compareTo(new BigDecimal("0")) == 0 ) {
        	kyakuNtakeHi = new BigDecimal("0.00");
        }
        if ( kyakuNtakeKoseiHi.compareTo(new BigDecimal("0")) == 0 ) {
        	kyakuNtakeKoseiHi = new BigDecimal("0.00");
        }
        if ( tankaNtakeHi.compareTo(new BigDecimal("0")) == 0 ) {
        	tankaNtakeHi = new BigDecimal("0.00");
        }
        if ( kyakuNtakuHi.compareTo(new BigDecimal("0")) == 0 ) {
        	kyakuNtakuHi = new BigDecimal("0.00");
        }
        if ( kyakuNtakuKoseiHi.compareTo(new BigDecimal("0")) == 0 ) {
        	kyakuNtakuKoseiHi = new BigDecimal("0.00");
        }
        if ( tankaNtakuHi.compareTo(new BigDecimal("0")) == 0 ) {
        	tankaNtakuHi = new BigDecimal("0.00");
        }
        kyakuNipo.setZenHi( kyakuHi );
        kyakuNipo.setZenHiTanka( tankaHi );
        kyakuNipo.setKyakusuNsumZenHi( kyakuNsumHi );
        kyakuNipo.setKyakusuNsumKoseiHi(kyakuNsumKoseiHi);
        kyakuNipo.setKyakuTankaNsumZen(zenKyakuTankaNsum);
        kyakuNipo.setKyakuTankaNsumZenHi( tankaNsumHi );
        kyakuNipo.setKyakusuNtakeZenHi( kyakuNtakeHi );
        kyakuNipo.setKyakusuNtakeKoseiHi(kyakuNtakeKoseiHi);
        kyakuNipo.setKyakuTankaNtakeZen(zenKyakuTankaNtake);
        kyakuNipo.setKyakuTankaNtakeZenHi( tankaNtakeHi );
        kyakuNipo.setKyakusuNtakuZenHi( kyakuNtakuHi );
        kyakuNipo.setKyakusuNtakuKoseiHi(kyakuNtakuKoseiHi);
        kyakuNipo.setKyakuTankaNtakuZen(zenKyakuTankaNtaku);
        kyakuNipo.setKyakuTankaNtakuZenHi( tankaNtakuHi );

        BigDecimal hi = new BigDecimal(100);

        if ( kyakuNipo.getRClass().equals( noClass ) ) {
            if ( kyakuHi.compareTo( hi ) < 0 ) {
                kyakuNipo.setZenHiClass( bodyHirituClassM );
            } else {
                kyakuNipo.setZenHiClass( bodyNumClassN );
            }
            if ( tankaHi.compareTo( hi ) < 0 ) {
                kyakuNipo.setZenHiTankaClass( bodyHirituClassM );
            } else {
                kyakuNipo.setZenHiTankaClass( bodyNumClassN );
            }
        } else {
            if ( kyakuHi.compareTo( hi ) < 0 ) {
                kyakuNipo.setZenHiClass( bodyHirituClass );
            } else {
                kyakuNipo.setZenHiClass( bodyNumClass );
            }
            if ( tankaHi.compareTo( hi ) < 0 ) {
                kyakuNipo.setZenHiTankaClass( bodyHirituClass );
            } else {
                kyakuNipo.setZenHiTankaClass( bodyNumClass );
            }
        }

        return kyakuNipo;

    }

    /**
     * 売上平均、客数平均を算出する
     * @param map
     * @return
     */
    public Map calHeikin( Map map, int digitCnt ) {
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
            uriHei.setUriage( Calculator.divide(uri.getUriage(),uri.getTenpoCount(), digitCnt) );
            uriHei.setUriageClass( uri.getUriageClass() );
            //予算平均 = 予算 ÷ 予算対象店舗
            uriHei.setYosan( Calculator.divide( uri.getYosan(), uri.getYosanMiseCnt() ) );
            uriHei.setYosanClass( uri.getYosanClass() );
            uriHei.setZenUriage( Calculator.divide( uri.getZenUriage(),uri.getZenTenpoCount(), digitCnt) );
            uriHei.setZenUriageClass( uri.getZenUriageClass() );

            //ネット注文売上平均
            uriHei.setUriageNsum(Calculator.divide(uri.getUriageNsum(),uri.getMiseKbnNsum(), digitCnt) );
            //ネットテイク売上平均
            uriHei.setUriageNtake(Calculator.divide(uri.getUriageNtake(),uri.getMiseKbnNtake(), digitCnt) );
            //ネット宅配売上平均
            uriHei.setUriageNtaku(Calculator.divide(uri.getUriageNtaku(),uri.getMiseKbnNtaku(), digitCnt) );

            uriHei = calHeikinUriage( uriHei );

            uriHeiList.add( uriHei );
        }

        returnMap.put("uriage",uriageList);
        returnMap.put("uriHei",uriHeiList);

        // 客数平均を算出
        List kyakusuList = (List)map.get("kyakusu");
        List kyakuHeiList = new ArrayList();

        for ( int i = 0; i < kyakusuList.size(); i++ ) {
            TrnSibuKyakusuNipoRelate kyaku = (TrnSibuKyakusuNipoRelate)kyakusuList.get(i);
            TrnSibuKyakusuNipoRelate kyakuHei = new TrnSibuKyakusuNipoRelate();

            kyakuHei.setCompanyCd( kyaku.getCompanyCd() );
            kyakuHei.setRClass( kyaku.getRClass() );
            kyakuHei.setHonbuCd( kyaku.getHonbuCd() );
            kyakuHei.setHonbuName( kyaku.getHonbuName() );
            kyakuHei.setJigyoCd( kyaku.getJigyoCd() );
            kyakuHei.setJigyoName( kyaku.getJigyoName() );
            kyakuHei.setSibuCd( kyaku.getSibuCd() );
            kyakuHei.setSibuName( kyaku.getSibuName() );
            kyakuHei.setSibuNameClass( kyaku.getSibuNameClass() );
            kyakuHei.setTenpoCount( kyaku.getTenpoCount() );
            kyakuHei.setZenTenpoCount( kyaku.getZenTenpoCount() );
            kyakuHei.setDispKbn( kyaku.getDispKbn() );
            kyakuHei.setKyakusu( Calculator.divide( kyaku.getKyakusu(),kyaku.getTenpoCount() ) );
            kyakuHei.setKyakusuClass( kyaku.getKyakusuClass() );
            kyakuHei.setZenKyakusu( Calculator.divide(kyaku.getZenKyakusu(),kyaku.getZenTenpoCount() ) );
            kyakuHei.setZenKyakusuClass( kyaku.getZenKyakusuClass() );

            //ネット注文件数平均
            kyakuHei.setKyakusuNsum(Calculator.divide(kyaku.getKyakusuNsum(),kyaku.getMiseKbnNsum(), digitCnt) );
            //ネットテイク件数平均
            kyakuHei.setKyakusuNtake(Calculator.divide(kyaku.getKyakusuNtake(),kyaku.getMiseKbnNtake(), digitCnt) );
            //ネット宅配件数平均
            kyakuHei.setKyakusuNtaku(Calculator.divide(kyaku.getKyakusuNtaku(),kyaku.getMiseKbnNtaku(), digitCnt) );

            kyakuHei = calHeikinKyakusu( kyakuHei );

            kyakuHeiList.add( kyakuHei );

        }
        returnMap.put("kyakusu",kyakusuList);
        returnMap.put("kyakuHei",kyakuHeiList);

        return returnMap;
    }

    /**
     * 予算差、前年差を求める(売上)
     * @param uriNipo
     * @return
     */
    public TrnSibuUriageNipoRelate calHeikinUriage( TrnSibuUriageNipoRelate uriNipo ) {

        BigDecimal yosanSa = ( uriNipo.getUriage() ).subtract( uriNipo.getYosan() );
        BigDecimal zenSa = (uriNipo.getUriage() ).subtract( uriNipo.getZenUriage() );

        uriNipo.setTasseiYosan( yosanSa );
        uriNipo.setZenHiSa( zenSa );

        BigDecimal zero = new BigDecimal(0);

        if ( uriNipo.getRClass().equals( noClass ) ) {
            if ( yosanSa.compareTo( zero ) < 0 ) {
                uriNipo.setTasseiYosanClass( bodyHirituClassM );
            } else {
                uriNipo.setTasseiYosanClass( bodyNumClassN );
            }
            if ( zenSa.compareTo( zero ) < 0) {
                uriNipo.setZenHiSaClass( bodyHirituClassM );
            } else {
                uriNipo.setZenHiSaClass( bodyNumClassN );
            }
        } else {
            if ( yosanSa.compareTo( zero ) < 0 ) {
                uriNipo.setTasseiYosanClass( bodyHirituClass );
            } else {
                uriNipo.setTasseiYosanClass( bodyNumClass );
            }
            if ( zenSa.compareTo( zero ) < 0) {
                uriNipo.setZenHiSaClass( bodyHirituClass );
            } else {
                uriNipo.setZenHiSaClass( bodyNumClass );
            }
        }

        return uriNipo;

    }

    /**
     * 前年差を求める(客数)
     * @param kyakuNipo
     * @return
     */
    public TrnSibuKyakusuNipoRelate calHeikinKyakusu( TrnSibuKyakusuNipoRelate kyakuNipo ) {

        BigDecimal kyakuSa = ( kyakuNipo.getKyakusu() ).subtract( kyakuNipo.getZenKyakusu() );

        BigDecimal zero = new BigDecimal(0);

        kyakuNipo.setZenHi( kyakuSa );

        if ( kyakuNipo.getRClass().equals( noClass ) ) {
            if ( kyakuSa.compareTo( zero ) < 0 ) {
                kyakuNipo.setZenHiClass( bodyHirituClassM );
            } else {
                kyakuNipo.setZenHiClass( bodyNumClassN );
            }
        } else {
            if ( kyakuSa.compareTo( zero ) < 0 ) {
                kyakuNipo.setZenHiClass( bodyHirituClass );
            } else {
                kyakuNipo.setZenHiClass( bodyNumClass );
            }
        }

        return kyakuNipo;
    }

    private BigDecimal numNull(BigDecimal num) {
        if ( num == null ) {
            return new BigDecimal(0);
        } else {
            return num;
        }
    }
}
