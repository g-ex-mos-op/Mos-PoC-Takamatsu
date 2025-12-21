/*
 * 作成日: 2006/04/04
 */
package jp.co.isid.mos.bird.bizsupport.plinfoview.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.plinfoview.dao.MstMiseInfoDao;
import jp.co.isid.mos.bird.bizsupport.plinfoview.dao.MstOnerInfoDao;
import jp.co.isid.mos.bird.bizsupport.plinfoview.dao.UIPLDataInfoDao;
import jp.co.isid.mos.bird.bizsupport.plinfoview.dao.UIPLRCDataInfoDao;
import jp.co.isid.mos.bird.bizsupport.plinfoview.dto.PlInfoViewDto;
import jp.co.isid.mos.bird.bizsupport.plinfoview.entity.MstMiseInfo;
import jp.co.isid.mos.bird.bizsupport.plinfoview.entity.MstOnerInfo;
import jp.co.isid.mos.bird.bizsupport.plinfoview.entity.UIPLDataInfo;
import jp.co.isid.mos.bird.bizsupport.plinfoview.entity.UIPLRCDataInfo;
import jp.co.isid.mos.bird.bizsupport.plinfoview.logic.GetPLInfoLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.Calculator;
import jp.co.isid.mos.bird.framework.util.DateManager;
/**
 * PL情報取得ロジック
 * @author xnkusama
 */
public class GetPLInfoLogicImpl implements GetPLInfoLogic {
    
    /* ロジックID */    
    public static final String LOGIC_ID = "BBS006L03";
    /* DAO */
    private UIPLDataInfoDao     uiPlDataInfoDao;
    private UIPLRCDataInfoDao   uiPlRCDataInfoDao;
    private MstMiseInfoDao      mstMiseInfoDao;
    private MstOnerInfoDao      mstOnerInfoDao;
    
    /* 店舗種別 */
    private static final String TENPO_SHU_ZENTEN = "";
    private static final String TENPO_SHU_ZENNENTAISHO = "1";
    private static final String TENPO_SHU_YOSANTAISHO = "2";
    private static final String TENPO_SHU_SINTEN = "3";
    /* 検索タイプ（オーナー検索） */
    private static final String SEARCH_TYPE_ALL = "";
    private static final String SEARCH_TYPE_ONER = "1";
    private static final String SEARCH_TYPE_TENPO_SUM = "2";
    private static final String SEARCH_TYPE_TENPO_AVG = "3";
    private static final String SEARCH_TYPE_MISE = "4";
    /* 対象店舗 */
    private static final String TAISHO_TENPO_JIGYO = "2";
    private static final String TAISHO_TENPO_AREA  = "3";
    private static final String TAISHO_TENPO_SIBU  = "4";
    private static final String TAISHO_TENPO_BLOCK = "5";
    private static final String TAISHO_TENPO_MISE  = "6";
    private static final String TAISHO_TENPO_ONER  = "7";
    /* PL_TYPE */
    private static final String PLTYPE_MISE = "1";
    private static final String PLTYPE_ONER = "0";
    // ユーザータイプコード：本部
    private static final String USER_TYPE_HONBU = "01";
    private static final String USER_TYPE_ONER  = "02";
    private static final String USER_TYPE_TENPO = "03";
    
    /* 該当データがない月のマークとしてPL_TYPEにセット */
    private static final String PL_TYPE_EMPTY_MARK = "Z";
    
    /**
     * PL情報を取得する
     * @param PlInfoViewDto
     * @param String sysDt
     * @param String appDt
     * @param String userId
     * @return UIPLDataInfo
     * @exception ApplicationException
     */
    public UIPLDataInfo execute(PlInfoViewDto dto, String sysDt, String appDt, String userId) throws ApplicationException {
        validate(dto);
        
        // 検索条件：期間のFrom 指定年月の13ヵ月前
        String condZennenYM = "";
        String condYMFrom = ""; 
        try {
            condZennenYM = DateManager.getPrevMonth(dto.getCondTargetYM(), 12);
            condYMFrom = DateManager.getPrevMonth(dto.getCondTargetYM(), 13);
        }
        catch (Exception ex) {
        	throw new FtlSystemException("PL情報取得");
        }

        List listEntity = null;
        try {
            if (USER_TYPE_HONBU.equals(dto.getUserTypeCd())) {
                /*本部ユーザー*/
                if (TAISHO_TENPO_MISE.equals(dto.getCondTaishoTenpo())) {
                    // 対象店舗＝店舗の場合、指定店舗が直営店かチェック
                    MstMiseInfo mstMise = getMstMiseInfoDao()
                                                .getMiseInfo(dto.getCondCompanyCd(), 
                                                              dto.getCondMiseCd());
                    
                    if (mstMise == null) {
                        throw new NotExistException("店舗");
                    }
                    // 店区分が「_1_」の場合はFC
                    if ("1".equals(mstMise.getMiseKbn().substring(1, 2))) {
                        // FC
                        listEntity = getPlDataHonbu(dto, sysDt, appDt, condYMFrom, condZennenYM, userId);
                        dto.setResultFcRC(false);
                    }
                    else {
                        // RC
                        listEntity = getPlDataRC(dto, condYMFrom, condZennenYM);
                        dto.setResultFcRC(true);
                    }
                    // 店名称セット
                    MstMiseInfo mstMiseInfo = getMstMiseInfoDao().getMiseInfo(dto.getCondCompanyCd(), dto.getCondMiseCd());
                    dto.setCondMiseName(mstMiseInfo.getMiseNameKj());
                    dto.setCondMiseOpenDt(mstMiseInfo.getOpenDt());
                    dto.setCondMiseOnerCd(mstMiseInfo.getOnerCd());
                    dto.setCondMiseOnerName(mstMiseInfo.getOnerNameKj());
                }
                else if (TAISHO_TENPO_ONER.equals(dto.getCondTaishoTenpo())) {
                    // 対象店舗＝オーナー
                    listEntity = getPlDataOner(dto, sysDt, appDt, condYMFrom, condZennenYM, userId);
                    dto.setResultFcRC(false);
                    // オーナー名称セット
                    MstOnerInfo mstOnerInfo = getMstOnerInfoDao().getOnerInfo(dto.getCondCompanyCd(), dto.getCondOnerCd());
                    dto.setCondOnerName(mstOnerInfo.getOnerNameKj());
                }
                else {
                    // 対象店舗＝店舗以外
                	listEntity = getPlDataHonbu(dto, sysDt, appDt, condYMFrom, condZennenYM, userId);
                    dto.setResultFcRC(false);
                }
                
            }
            else if (USER_TYPE_ONER.equals(dto.getUserTypeCd())) {
                /*オーナー*/
                dto.setCondSearchType(dto.getCondTaishoTenpoOner());
                dto.setCondMiseCd(dto.getCondMiseCdOner());
                dto.setCondTaishoTenpo(TAISHO_TENPO_ONER);
                dto.setCondLastSearchTaishoTenpo(TAISHO_TENPO_ONER);
                listEntity = getPlDataOner(dto, sysDt, appDt, condYMFrom, condZennenYM, userId);
                dto.setResultFcRC(false);
                // オーナー名称セット
                MstOnerInfo mstOnerInfo = getMstOnerInfoDao().getOnerInfo(dto.getCondCompanyCd(), dto.getCondOnerCd());
                dto.setCondOnerName(mstOnerInfo.getOnerNameKj());
                if (SEARCH_TYPE_MISE.equals(dto.getCondSearchType())) {
                    // 店名称セット
                    MstMiseInfo mstMiseInfo = getMstMiseInfoDao().getMiseInfo(dto.getCondCompanyCd(), dto.getCondMiseCd());
                    dto.setCondMiseName(mstMiseInfo.getMiseNameKj());
                    dto.setCondMiseOpenDt(mstMiseInfo.getOpenDt());
                    dto.setCondMiseOnerCd(mstMiseInfo.getOnerCd());
                    dto.setCondMiseOnerName(mstMiseInfo.getOnerNameKj());
                }
            }
            else if (USER_TYPE_TENPO.equals(dto.getUserTypeCd())) {
                /*店舗*/
                MstMiseInfo mstMise = getMstMiseInfoDao()
                                            .getMiseInfo(dto.getCondCompanyCd(), 
                                                          dto.getCondMiseCd());
                
                if (mstMise == null) {
                    throw new NotExistException("店舗");
                }
                
                // ユーザータイプ＝店舗の場合は、対象条件＝店舗にする
                dto.setCondTaishoTenpo(TAISHO_TENPO_MISE);
                
                // 店区分が「_1_」の場合はFC
                if ("1".equals(mstMise.getMiseKbn().substring(1, 2))) {
                    // FC
                    listEntity = getPlDataHonbu(dto, sysDt, appDt, condYMFrom, condZennenYM, userId);
                    dto.setResultFcRC(false);
                }
                else {
                    // RC
                    listEntity = getPlDataRC(dto, condYMFrom, condZennenYM);
                    dto.setResultFcRC(true);
                }
                // 店名称セット
                MstMiseInfo mstMiseInfo = getMstMiseInfoDao().getMiseInfo(dto.getCondCompanyCd(), dto.getCondMiseCd());
                dto.setCondMiseName(mstMiseInfo.getMiseNameKj());
                dto.setCondMiseOpenDt(mstMiseInfo.getOpenDt());
                dto.setCondMiseOnerCd(mstMiseInfo.getOnerCd());
                dto.setCondMiseOnerName(mstMiseInfo.getOnerNameKj());
            }
        }
        catch (ApplicationException aex) {
            throw aex;
        }
        catch (Exception ex) {
        	throw new FtlSystemException("PLデータ取得");
        }
        
        dto.setListEntityTogetuPlusZennen(listEntity);
        return null;
    }
    
    private void validate(PlInfoViewDto dto) {
        if (dto == null) {
            throw new NotNullException("検索条件");
        }
        
        /* 本部ユーザーの場合 */
        if (USER_TYPE_HONBU.equals(dto.getUserTypeCd())) {
        	// 対象店舗が店舗、オーナーの場合は、各コードが必須
        	if (TAISHO_TENPO_MISE.equals(dto.getCondTaishoTenpo())) {
                if (isNull(dto.getCondMiseCd())) {
                    throw new NoInputException("店コード", "condMiseCd", null);
                }
        	}
            if (TAISHO_TENPO_ONER.equals(dto.getCondTaishoTenpo())) {
                if (isNull(dto.getCondOnerCd())) {
                    throw new NoInputException("オーナーコード", "condOnerCd", null);
                }
            }
        }
    }

    /**
     * 本部ユーザー向けPLデータ取得処理
     * @param dto
     * @param sysDt
     * @param appDt
     * @param condYMFrom
     * @param condZennenYM
     * @return
     */
    private List getPlDataHonbu(PlInfoViewDto dto, 
                                 String sysDt, 
                                 String appDt, 
                                 String condYMFrom, 
                                 String condYMZennen,
                                 String userId) throws ApplicationException {
        // 店舗種別の基準日付
        Map mapTenpoShuBaseDt = getTenpoShuBaseDt(dto, sysDt, appDt);
        String openDtCond1 = null;
        String openDtCond2 = null;
        String openDtCond3 = null;

        if (TENPO_SHU_ZENTEN.equals(dto.getCondTenpoShu())) {
            //条件なし
        }
        if (TENPO_SHU_ZENNENTAISHO.equals(dto.getCondTenpoShu())) {
            //
            openDtCond1 = (String) mapTenpoShuBaseDt.get("kizyunDt3");
        }
        if (TENPO_SHU_YOSANTAISHO.equals(dto.getCondTenpoShu())) {
            //
            openDtCond1 = (String) mapTenpoShuBaseDt.get("kizyunDt3");
            openDtCond2 = (String) mapTenpoShuBaseDt.get("kizyunDt3");
            openDtCond3 = (String) mapTenpoShuBaseDt.get("kizyunDt2");
        }
        if (TENPO_SHU_SINTEN.equals(dto.getCondTenpoShu())) {
            //
            openDtCond1 = (String) mapTenpoShuBaseDt.get("kizyunDt2");
        }
        // PL_TYPE
        dto.setCondPlType(PLTYPE_MISE);
        
        // 対象店舗
        String condTaishoTenpo = dto.getCondTaishoTenpo();
        
        // 対象店舗パラメータ
        String code1 = null;
        String code2 = null;
        if (TAISHO_TENPO_JIGYO.equals(condTaishoTenpo)) {
            code1 = dto.getCondJigyouCd();
        }
        else if (TAISHO_TENPO_AREA.equals(condTaishoTenpo)) {
            //areaCd = dto.getCondSlareaCd();
            code1 = dto.getCondSlareaCd();
        }
        else if (TAISHO_TENPO_SIBU.equals(condTaishoTenpo)) {
            //sibuCd = dto.getCondSibuCd();
            code1 = dto.getCondSibuCd();
            code2 = dto.getCondBlockCd();
        }
        else if (TAISHO_TENPO_MISE.equals(condTaishoTenpo)) {
            //miseCd = dto.getCondMiseCd();
            code1 = dto.getCondMiseCd();;
        }
        else if (TAISHO_TENPO_ONER.equals(condTaishoTenpo)) {
            //onerCd = dto.getCondOnerCd();
            code1 = dto.getCondOnerCd();
        }
        
        // 店舗種別により実行SQLを決める
        List listEntity;
        listEntity = getUiPlDataInfoDao()
                        .getPLData(
                                dto.getCondPlType(),
                                dto.getCondTargetYM(),
                                condYMFrom,
                                dto.getCondCompanyCd(),
                                code1,
                                code2,
                                condTaishoTenpo,
                                dto.getCondTenpoShu(),
                                openDtCond1,
                                openDtCond2,
                                openDtCond3,
                                userId, 
                                dto.isLimitKbn(),
// add start xkahta 2007/01/15
                                dto.getUriageFlg());
// add end
        
        // データ存在チェック
        if (listEntity == null || listEntity.size() == 0) {
            throw new NoResultException();
        }
        
        //抜けている月に空Entityをセット
        setBlankEntity(dto, listEntity);
        
        // 原材料原価、野菜原価、包装資材原価、物販原価の前月値をセット
        setZengetuData(listEntity);
        // 13ヵ月前のデータ削除（対象年月2006/04の場合、2005/03を削除）
        deleteBefore13Row(listEntity, dto.getCondTargetYM());
        // 当月、前年同月データセット
        dto.setEntityTougetu(getPlData(listEntity, dto.getCondTargetYM()));
        dto.setEntityZennen(getPlData(listEntity, condYMZennen));
        // 原価情報 12ヶ月合計、各月平均
        calcGenkaInfo12Avg(dto, listEntity);
        
        return listEntity;
    }
    
    private void setBlankEntity(PlInfoViewDto dto, List listEntity) {
        //抜けている月に空Entityをセット
        List listBlankEntity = new ArrayList();
        for (int i = 0; i < 12; i++) {
            String targetYM = "";
            boolean isExist = false;
            try {
                targetYM = DateManager.getPrevMonth(dto.getCondTargetYM(), i);
            }
            catch (Exception ex) { 
                throw new FtlSystemException("PL照会データ取得", null, ex);
            }
            for (Iterator ite = listEntity.iterator(); ite.hasNext();) {
                UIPLDataInfo entity = (UIPLDataInfo) ite.next();
                if (entity.getPlYm().equals(targetYM)) {
                    isExist = true;
                    break;
                }
                
            }
            if (!isExist) {
                UIPLDataInfo blankEntity = new UIPLDataInfo();
                blankEntity.setPlType(PL_TYPE_EMPTY_MARK);
                blankEntity.setPlYm(targetYM);
                listBlankEntity.add(blankEntity);
            }
        }
        for (Iterator ite = listBlankEntity.iterator(); ite.hasNext();) {
            listEntity.add(ite.next());
        }
        // PL_YMでソート
        Comparator sortComparator = new Comparator() {
            public boolean equals(Object obj) {
                return (super.equals(obj));
            }
            public int compare(Object obj1, Object obj2) {
                String key1 = ((UIPLDataInfo) obj1).getPlYm();
                String key2 = ((UIPLDataInfo) obj2).getPlYm();
                return key1.compareTo(key2);
            }
        };
        Collections.sort(listEntity, sortComparator);
    }
    
    private void calcGenkaInfo12Avg(PlInfoViewDto dto, List listEntity) throws ApplicationException {
        int cnt = 0;
        // 12ヶ月合計
        UIPLDataInfo entitySum = new UIPLDataInfo();
        entitySum.setPlYm("12ヶ月合計");
        entitySum.setRowType(UIPLDataInfo.ROW_TYPE_12SUM);
        UIPLDataInfo entityAvg = new UIPLDataInfo();
        entityAvg.setPlYm("各月平均");
        entityAvg.setRowType(UIPLDataInfo.ROW_TYPE_12AVG);
        
        // 12ヶ月合計
        BigDecimal bigGenzairyo1 = new BigDecimal("0");
        BigDecimal bigGenzairyo2 = new BigDecimal("0");
        BigDecimal bigGenzairyo3 = new BigDecimal("0");
        BigDecimal bigGenzairyo4 = new BigDecimal("0");
        BigDecimal bigYasai1 = new BigDecimal("0");
        BigDecimal bigYasai2 = new BigDecimal("0");
        BigDecimal bigYasai3 = new BigDecimal("0");
        BigDecimal bigYasai4 = new BigDecimal("0");
        BigDecimal bigHouso1 = new BigDecimal("0");
        BigDecimal bigHouso2 = new BigDecimal("0");
        BigDecimal bigHouso3 = new BigDecimal("0");
        BigDecimal bigHouso4 = new BigDecimal("0");
        BigDecimal bigBuppan1 = new BigDecimal("0");
        BigDecimal bigBuppan2 = new BigDecimal("0");
        BigDecimal bigBuppan3 = new BigDecimal("0");
        BigDecimal bigBuppan4 = new BigDecimal("0");
        BigDecimal bigKonetu1 = new BigDecimal("0");
        BigDecimal bigKonetu2 = new BigDecimal("0");
        BigDecimal bigKonetu3 = new BigDecimal("0");
        BigDecimal bigKonetu4 = new BigDecimal("0");
        BigDecimal bigKonetu5 = new BigDecimal("0");
        BigDecimal bigKehi1 = new BigDecimal("0");
        BigDecimal bigKehi2 = new BigDecimal("0");
        BigDecimal bigKehi3 = new BigDecimal("0");
        BigDecimal bigKehi4 = new BigDecimal("0");
        BigDecimal bigUriagedaka = new BigDecimal("0");
        BigDecimal bigUriageZen  = new BigDecimal("0");
        
        // 12ヶ月合計の基準月
        String s12GokeiKizyunYM = "";
        try {
        	s12GokeiKizyunYM = DateManager.getPrevMonth(dto.getCondTargetYM(), 11);
        }
        catch (Exception ex) {
        	throw new FtlSystemException("PL照会情報取得", null, ex);
        }

        for (Iterator ite = listEntity.iterator(); ite.hasNext();) {
        	UIPLDataInfo entity = (UIPLDataInfo) ite.next();
            
            if (entity.getPlYm().compareTo(s12GokeiKizyunYM) < 0) {
                continue;
            }
            bigGenzairyo1 = bigGenzairyo1.add(entity.getGenzairyoZenZaiko());
            bigGenzairyo2 = bigGenzairyo2.add(entity.getGenzairyoShire());
            bigGenzairyo3 = bigGenzairyo3.add(entity.getGenzairyoZaiko());
            bigGenzairyo4 = bigGenzairyo4.add(entity.getGenzairyoKei());
            bigYasai1 = bigYasai1.add(entity.getYasaiZenZaiko());
            bigYasai2 = bigYasai2.add(entity.getYasaiShire());
            bigYasai3 = bigYasai3.add(entity.getYasaiZaiko());
            bigYasai4 = bigYasai4.add(entity.getYasaiKei());
            bigHouso1 = bigHouso1.add(entity.getHouzaiZenZaiko());
            bigHouso2 = bigHouso2.add(entity.getHouzaiShire());
            bigHouso3 = bigHouso3.add(entity.getHouzaiZaiko());
            bigHouso4 = bigHouso4.add(entity.getHouzaiKei());
            bigBuppan1 = bigBuppan1.add(entity.getBuppanZenZaiko());
            bigBuppan2 = bigBuppan2.add(entity.getBuppanShire());
            bigBuppan3 = bigBuppan3.add(entity.getBuppanZaiko());
            bigBuppan4 = bigBuppan4.add(entity.getBuppanKei());
            bigKonetu1 = bigKonetu1.add(entity.getElec());
            bigKonetu2 = bigKonetu2.add(entity.getGas());
            bigKonetu3 = bigKonetu3.add(entity.getWater());
            bigKonetu4 = bigKonetu4.add(entity.getSonota());
            bigKonetu5 = bigKonetu5.add(entity.getSuikouUchiwake());
            bigKehi1 = bigKehi1.add(entity.getYobi());
            bigKehi2 = bigKehi2.add(entity.getYobi());
            bigKehi3 = bigKehi3.add(entity.getYobi());
            bigKehi4 = bigKehi4.add(entity.getKeihiShokei());
            
            bigUriagedaka = bigUriagedaka.add(entity.getUriagedaka());
            bigUriageZen  = bigUriageZen.add(entity.getUriagedakaZen());
            if (!PL_TYPE_EMPTY_MARK.equals(entity.getPlType())) {
            	cnt++;
            }
        }
        //売上高
        entitySum.setUriagedaka(bigUriagedaka);
        entitySum.setUriagedakaZen(bigUriageZen);
        entityAvg.setUriagedaka(Calculator.divide(bigUriagedaka, new BigDecimal(cnt), 0));
        entityAvg.setUriagedakaZen(Calculator.divide(bigUriageZen, new BigDecimal(cnt), 0));
        //原材料
        entitySum.setGenzairyoZenZaiko(bigGenzairyo1);
        entitySum.setGenzairyoShire(bigGenzairyo2);
        entitySum.setGenzairyoZaiko(bigGenzairyo3);
        entitySum.setGenzairyoKei(bigGenzairyo4);
        entityAvg.setGenzairyoZenZaiko(Calculator.divide(bigGenzairyo1, new BigDecimal(cnt), 0));
        entityAvg.setGenzairyoShire(Calculator.divide(bigGenzairyo2, new BigDecimal(cnt), 0));
        entityAvg.setGenzairyoZaiko(Calculator.divide(bigGenzairyo3, new BigDecimal(cnt), 0));
        entityAvg.setGenzairyoKei(Calculator.divide(bigGenzairyo4, new BigDecimal(cnt), 0));
        //野菜
        entitySum.setYasaiZenZaiko(bigYasai1);
        entitySum.setYasaiShire(bigYasai2);
        entitySum.setYasaiZaiko(bigYasai3);
        entitySum.setYasaiKei(bigYasai4);
        entityAvg.setYasaiZenZaiko(Calculator.divide(bigYasai1, new BigDecimal(cnt), 0));
        entityAvg.setYasaiShire(Calculator.divide(bigYasai2, new BigDecimal(cnt), 0));
        entityAvg.setYasaiZaiko(Calculator.divide(bigYasai3, new BigDecimal(cnt), 0));
        entityAvg.setYasaiKei(Calculator.divide(bigYasai4, new BigDecimal(cnt), 0));
        //包装資材
        entitySum.setHouzaiZenZaiko(bigHouso1);
        entitySum.setHouzaiShire(bigHouso2);
        entitySum.setHouzaiZaiko(bigHouso3);
        entitySum.setHouzaiKei(bigHouso4);
        entityAvg.setHouzaiZenZaiko(Calculator.divide(bigHouso1, new BigDecimal(cnt), 0));
        entityAvg.setHouzaiShire(Calculator.divide(bigHouso2, new BigDecimal(cnt), 0));
        entityAvg.setHouzaiZaiko(Calculator.divide(bigHouso3, new BigDecimal(cnt), 0));
        entityAvg.setHouzaiKei(Calculator.divide(bigHouso4, new BigDecimal(cnt), 0));
        //物販原価
        entitySum.setBuppanZenZaiko(bigBuppan1);
        entitySum.setBuppanShire(bigBuppan2);
        entitySum.setBuppanZaiko(bigBuppan3);
        entitySum.setBuppanKei(bigBuppan4);
        entityAvg.setBuppanZenZaiko(Calculator.divide(bigBuppan1, new BigDecimal(cnt), 0));
        entityAvg.setBuppanShire(Calculator.divide(bigBuppan2, new BigDecimal(cnt), 0));
        entityAvg.setBuppanZaiko(Calculator.divide(bigBuppan3, new BigDecimal(cnt), 0));
        entityAvg.setBuppanKei(Calculator.divide(bigBuppan4, new BigDecimal(cnt), 0));
        //水道光熱費
        entitySum.setElec(bigKonetu1);
        entitySum.setGas(bigKonetu2);
        entitySum.setWater(bigKonetu3);
        entitySum.setSonota(bigKonetu4);
        entitySum.setSuikouUchiwake(bigKonetu5);
        entityAvg.setElec(Calculator.divide(bigKonetu1, new BigDecimal(cnt), 0));
        entityAvg.setGas(Calculator.divide(bigKonetu2, new BigDecimal(cnt), 0));
        entityAvg.setWater(Calculator.divide(bigKonetu3, new BigDecimal(cnt), 0));
        entityAvg.setSonota(Calculator.divide(bigKonetu4, new BigDecimal(cnt), 0));
        entityAvg.setSuikouUchiwake(Calculator.divide(bigKonetu5, new BigDecimal(cnt), 0));
        //経費
        entitySum.setYobi(bigKehi1);
        entitySum.setKeihiShokei(bigKehi4);
        entityAvg.setYobi(Calculator.divide(bigKehi1, new BigDecimal(cnt), 0));
        entityAvg.setKeihiShokei(Calculator.divide(bigKehi4, new BigDecimal(cnt), 0));
        
        
        
        listEntity.add(entitySum);
        listEntity.add(entityAvg);
    }
    /**
     * オーナーユーザー向けPLデータ取得処理
     * @param dto
     * @param sysDt
     * @param appDt
     * @param condYMFrom
     * @param condZennenYM
     * @return
     */
    private List getPlDataOner(PlInfoViewDto dto, String sysDt, String appDt, String condYMFrom, String condZennenYM, String userId) {
        // 対象店舗
        String condSearchType = dto.getCondSearchType();
        // PL_TYPE
        if (SEARCH_TYPE_ONER.equals(dto.getCondSearchType())) {
        	dto.setCondPlType(PLTYPE_ONER);
        }
        else if (SEARCH_TYPE_TENPO_SUM.equals(dto.getCondSearchType())
                    || SEARCH_TYPE_TENPO_AVG.equals(dto.getCondSearchType()))
        {
            dto.setCondPlType(PLTYPE_MISE);
        }
        else {
        	dto.setCondPlType("");
        }
        
        // 店舗種別により実行SQLを決める
        List listEntity;
        
        if (SEARCH_TYPE_TENPO_AVG.equals(condSearchType)) {
            // 平均
            listEntity = getUiPlDataInfoDao()
                            .getPLDataOnerAvg(
                                    dto.getCondPlType(),
                                    dto.getCondTargetYM(),
                                    condYMFrom,
                                    dto.getCondCompanyCd(),
                                    dto.getCondSearchType(),
                                    dto.getCondMiseCd(),
                                    dto.getCondOnerCd(),
                                    userId,
                                    dto.isLimitKbn(),
                                    dto.getUriageFlg());
        }
        else {
            // 合計
            listEntity = getUiPlDataInfoDao()
                            .getPLDataOnerSum(
                                    dto.getCondPlType(),
                                    dto.getCondTargetYM(),
                                    condYMFrom,
                                    dto.getCondCompanyCd(),
                                    dto.getCondSearchType(),
                                    dto.getCondMiseCd(),
                                    dto.getCondOnerCd(),
                                    userId,
                                    dto.isLimitKbn(),
                                    dto.getUriageFlg());
        }
        
        // データ存在チェック
        if (listEntity == null || listEntity.size() == 0) {
            throw new NoResultException();
        }
        
        //抜けている月に空Entityをセット
        setBlankEntity(dto, listEntity);

        // 原材料原価、野菜原価、包装資材原価、物販原価の前月値をセット
        setZengetuData(listEntity);
        
        // 13ヵ月前のデータ削除（対象年月2006/04の場合、2005/03を削除）
        deleteBefore13Row(listEntity, dto.getCondTargetYM());
        // 当月、前年同月データセット
        dto.setEntityTougetu(getPlData(listEntity, dto.getCondTargetYM()));
        dto.setEntityZennen(getPlData(listEntity, condZennenYM));

        // 原価情報 12ヶ月合計、各月平均
        calcGenkaInfo12Avg(dto, listEntity);

        return listEntity;
    }
    
    /**
     * アプリ日付と検索条件で指定した対象年月の年度の差を求め
     * 店舗種別ごとの基準となる日付を求める
     * @return int 年度の差
     */
    private Map getTenpoShuBaseDt(PlInfoViewDto dto, String sysDt, String appDt) throws ApplicationException {
        Map mapBaseDt = new HashMap();
        int intSubstract = 0;
        
        try {
        	String condNendo = DateManager.getCurrentYear(dto.getCondTargetYM());
            String appNendo  = DateManager.getCurrentYear(appDt);
            
            // システム日付と検索条件：年月の年度の差
            BigDecimal val1 = new BigDecimal(appNendo);
            BigDecimal val2 = new BigDecimal(condNendo);
            
            intSubstract = val1.subtract(val2).intValue();
        
            // 年度差により基準の日付を算出
            String kizyunDt1 = "";
            String kizyunDt2 = "";
            String kizyunDt3 = "";
            switch (intSubstract) {
                case 0:
                    // システム日付
                    kizyunDt1 = sysDt;
                    // システム日付の年度初め(????0401)
                    kizyunDt2 = DateManager.getCurrentYear(sysDt) + "0401";
                    // システム日付の前年度
                    kizyunDt3 = DateManager.getPrevYear(DateManager.getCurrentYear(sysDt), 1) + "0401";
                    break;
                case 1:
                    // システム日付の年度初め(????0401)
                    kizyunDt1 = DateManager.getCurrentYear(sysDt) + "0401";
                    // システム日付の前年度
                    kizyunDt2 = DateManager.getPrevYear(DateManager.getCurrentYear(sysDt), 1) + "0401";
                    // システム日付の前々年度
                    kizyunDt3 = DateManager.getPrevYear(DateManager.getCurrentYear(sysDt), 2) + "0401";
                    break;
                case 2:
                    // システム日付の前年度
                    kizyunDt1 = DateManager.getPrevYear(DateManager.getCurrentYear(sysDt), 1) + "0401";
                    // システム日付の前々年度
                    kizyunDt2 = DateManager.getPrevYear(DateManager.getCurrentYear(sysDt), 2) + "0401";
                    // システム日付の３年度前
                    kizyunDt3 = DateManager.getPrevYear(DateManager.getCurrentYear(sysDt), 3) + "0401";
                    break;
                case 3:
                    // システム日付の前々年度
                    kizyunDt1 = DateManager.getPrevYear(DateManager.getCurrentYear(sysDt), 2) + "0401";
                    // システム日付の３年度前
                    kizyunDt2 = DateManager.getPrevYear(DateManager.getCurrentYear(sysDt), 3) + "0401";
                    // システム日付の４年度前
                    kizyunDt3 = DateManager.getPrevYear(DateManager.getCurrentYear(sysDt), 4) + "0401";
                    break;
            }
            mapBaseDt.put("kizyunDt1", kizyunDt1);
            mapBaseDt.put("kizyunDt2", kizyunDt2);
            mapBaseDt.put("kizyunDt3", kizyunDt3);
        
        }
        catch (Exception ex) {
            throw new FtlSystemException("店舗種別");
        }
        return mapBaseDt;
    }
    
    /**
     * 直営データの検索結果リストから指定月のデータをListで取得する
     * @param dto
     * @param condYMFrom
     * @param condZennenYM
     * @return
     */
    private List getPlDataRC(PlInfoViewDto dto, String condYMFrom, String condZennenYM) {
        //PL_TYPE
        dto.setCondPlType(PLTYPE_MISE);
        
        //検索
        List listEntity = getUiPlRCDataInfoDao()
                                .getPLRCData(dto.getCondPlType(),
                                             dto.getCondTargetYM(),
                                             condYMFrom,
                                             dto.getCondCompanyCd(),
                                             dto.getCondMiseCd());
        
        // データ存在チェック
        if (listEntity == null || listEntity.size() == 0) {
            throw new NoResultException();
        }
        
        // 構成比の算出元値をセット
        Map mapKouseihiMoto = new HashMap();
        for (Iterator ite = listEntity.iterator(); ite.hasNext();) {
        	UIPLRCDataInfo entity = (UIPLRCDataInfo) ite.next();
            if ("1".equals(entity.getKouseihiMoto())) {
                mapKouseihiMoto.put(entity.getPlYm(), entity.getKingaku());
            }
        }
        for (Iterator ite = listEntity.iterator(); ite.hasNext();) {
            UIPLRCDataInfo entity = (UIPLRCDataInfo) ite.next();
            entity.setKouseihiMotoKingaku((BigDecimal) mapKouseihiMoto.get(entity.getPlYm()));
        }
        // DTOへ当月と前年の構成比元をセット
        dto.setBigKouseihiMoto((BigDecimal) mapKouseihiMoto.get(dto.getCondTargetYM()));
        dto.setBigKouseihiMotoZennen((BigDecimal) mapKouseihiMoto.get(condZennenYM));
        
        List listTougetu = getPlDataRCTargetYM(listEntity, dto.getCondTargetYM());
        List listZennen  = getPlDataRCTargetYM(listEntity, condZennenYM);
 
        // 当月データが存在しない場合は、「該当データなし」エラー
        if (listTougetu == null || listTougetu.size() == 0) {
        	throw new NoResultException();
        }
        // 前年データを当月データにセット
        for (int i = 0; i < listTougetu.size(); i++) {
            UIPLRCDataInfo entityTougetu = (UIPLRCDataInfo) listTougetu.get(i);
        	for (int j = 0; j < listZennen.size(); j++) {
                UIPLRCDataInfo entityZennen = (UIPLRCDataInfo) listZennen.get(j);
                if (entityTougetu.getKoumokuNo().equals(entityZennen.getKoumokuNo())) {
                    entityTougetu.setKingakuZennen(entityZennen.getKingaku());
                    break;
                }
            }
        }

        // 当月、前年同月データセット
        dto.setListRCTougetu(listTougetu);
        
        return listEntity;
    }
    
    // 前月の在庫データを設定
    private void setZengetuData(List listEntity) throws ApplicationException {
        String oldYM = "";
        BigDecimal bigGenzairyo = new BigDecimal("0");
        BigDecimal bigYasai = new BigDecimal("0");
        BigDecimal bigHouzai = new BigDecimal("0");
        BigDecimal bigBuppan = new BigDecimal("0");
        BigDecimal bigUriagedaka = new BigDecimal("0");
        try {
            for (int i = 0; i < listEntity.size(); i++) {
                UIPLDataInfo entity = (UIPLDataInfo) listEntity.get(i);
                
                if (DateManager.getPrevMonth(entity.getPlYm(), 1).equals(oldYM)) {
                	// 前月のデータが存在した場合
                    entity.setGenzairyoZenZaiko(bigGenzairyo);
                    entity.setYasaiZenZaiko(bigYasai);
                    entity.setHouzaiZenZaiko(bigHouzai);
                    entity.setBuppanZenZaiko(bigBuppan);
                    entity.setUriagedakaZen(bigUriagedaka);
                }
                else {
                    // 存在しない場合
                    entity.setGenzairyoZenZaiko(new BigDecimal("0"));
                    entity.setYasaiZenZaiko(new BigDecimal("0"));
                    entity.setHouzaiZenZaiko(new BigDecimal("0"));
                    entity.setBuppanZenZaiko(new BigDecimal("0"));
                    entity.setUriagedakaZen(new BigDecimal("0"));
                }
                oldYM = entity.getPlYm();
                bigGenzairyo = entity.getGenzairyoZaiko();
                bigYasai = entity.getYasaiZaiko();
                bigHouzai = entity.getHouzaiZaiko();
                bigBuppan = entity.getBuppanZaiko();
                bigUriagedaka = entity.getUriagedaka();
            }
        }
        catch (Exception ex) {
        	throw new FtlSystemException("PL情報取得");
        }
    }
    
    /**
     * 13ヵ月前のデータ削除（対象年月2006/04の場合、2005/03を削除）
     * @param listEntity
     * @param condYM
     */
    private void deleteBefore13Row(List listEntity, String condYM) {
        try {
            String deleteYM = DateManager.getPrevMonth(condYM, 13);
            for (Iterator ite = listEntity.iterator(); ite.hasNext();) {
                UIPLDataInfo entity = (UIPLDataInfo) ite.next();
                if (deleteYM.equals(entity.getPlYm())) {
                    ite.remove();
                    break;
                }
            }
        }
        catch (Exception ex) {
            throw new FtlSystemException("PL情報取得");
        }
    }
    
    /**
     * 指定年月のPLデータ取得
     * @param List 検索結果のList
     * @param ym   取得する年月
     * @return
     */
    private UIPLDataInfo getPlData(List listEntity, String ym) {
        UIPLDataInfo retEntity = null;
        for (Iterator ite = listEntity.iterator(); ite.hasNext();) {
            UIPLDataInfo entity = (UIPLDataInfo) ite.next();
            if (ym.equals(entity.getPlYm())) {
                retEntity = entity;
                break;
            }
        }
        return retEntity;
    }
    
    /**
     * 指定年月のPLデータ取得(直営)
     * @param List 検索結果のList
     * @param ym   取得する年月
     * @return List
     */
    private List getPlDataRCTargetYM(List listEntity, String ym) {
        List retList = new ArrayList();
        int startIndex = -1;
        int lastIndex = -1;
        for (int i = 0; i < listEntity.size(); i++) {
            UIPLRCDataInfo entity = (UIPLRCDataInfo) listEntity.get(i);
            if (ym.equals(entity.getPlYm())) {
                if (startIndex < 0) {
                    startIndex = i;
                }
                else if (startIndex != -1 && !ym.equals(entity.getPlYm())) {
                    lastIndex = i;
                    break;
                }
            }
        }
        if (startIndex != -1 && lastIndex != -1) {
        	retList = listEntity.subList(startIndex, lastIndex);
        }
        else if (startIndex != -1 && lastIndex == -1) {
        	retList = listEntity.subList(startIndex, listEntity.size());
        }
        
        return retList;
    }
    
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
   
	public UIPLDataInfoDao getUiPlDataInfoDao() {
		return uiPlDataInfoDao;
	}
	public void setUiPlDataInfoDao(UIPLDataInfoDao uiPlDataInfoDao) {
		this.uiPlDataInfoDao = uiPlDataInfoDao;
	}
	public MstMiseInfoDao getMstMiseInfoDao() {
		return mstMiseInfoDao;
	}
	public void setMstMiseInfoDao(MstMiseInfoDao mstMiseInfoDao) {
		this.mstMiseInfoDao = mstMiseInfoDao;
	}
	public UIPLRCDataInfoDao getUiPlRCDataInfoDao() {
		return uiPlRCDataInfoDao;
	}
	public void setUiPlRCDataInfoDao(UIPLRCDataInfoDao uiPlRCDataInfoDao) {
		this.uiPlRCDataInfoDao = uiPlRCDataInfoDao;
	}
	public MstOnerInfoDao getMstOnerInfoDao() {
		return mstOnerInfoDao;
	}
	public void setMstOnerInfoDao(MstOnerInfoDao mstOnerInfoDao) {
		this.mstOnerInfoDao = mstOnerInfoDao;
	}
}