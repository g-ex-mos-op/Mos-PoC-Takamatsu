package jp.co.isid.mos.bird.storemanage.claimtotal.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.storemanage.claimtotal.dao.ClaimTotalDataDao;
import jp.co.isid.mos.bird.storemanage.claimtotal.dao.CodBunruiMDao;
import jp.co.isid.mos.bird.storemanage.claimtotal.dto.ClaimTotalRequestDto;
import jp.co.isid.mos.bird.storemanage.claimtotal.dto.ClaimTotalSessionDto;
import jp.co.isid.mos.bird.storemanage.claimtotal.entity.CodBunruiM;
import jp.co.isid.mos.bird.storemanage.claimtotal.entity.UIClaimTotalData;
import jp.co.isid.mos.bird.storemanage.claimtotal.logic.SearchLogic;
import jp.co.isid.mos.bird.storemanage.common.util.StoreManageUtil;

public class SearchLogicImpl implements SearchLogic {

    /** ロジックID */
    public static final String LOGIC_ID = "BSM017L02";
    /** タイプコード */
    private static final String TYPE_CD_CLAIM = "1";
    private static final String TYPE_CD_OHOME = "4";
    private static final String TYPE_CD_SUMROW = "S";
    private static final String TYPE_CD_ZENSUMROW = "Z";
    
    private ClaimTotalDataDao claimtotalDataDao;
    private CodBunruiMDao claimtotalCodBunruiMDao;
    
    /* 年月フォーマッター(yyyy/MM) */
    private static final DateFormatter dateFormatter = new DateFormatter(DateFormatter.DATE_TYPE_YM, DateFormatter.PATTERN_MONTH_KANJI_YM);
    
    public void execute(ClaimTotalSessionDto sessionDto, ClaimTotalRequestDto requestDto) {
        // 事前条件
        validate(sessionDto, requestDto);
        
        List listAllData = new ArrayList();
        boolean flgExistData = false;
        String hyojitaishoName = "";
        
        //中分類一覧取得
        List listBunrui = getClaimtotalCodBunruiMDao().select(TYPE_CD_CLAIM + "%");
        if (listBunrui == null || listBunrui.isEmpty()) {
            throw new NoResultException();
        }
        //クレームデータ取得
        String kikanTo = requestDto.getTaishoNengetu();
        String kikanFrom = "";
        String kikanFromZen = "";;
        String kikanToZen = "";
        try {
            kikanFrom = DateManager.getPrevMonth(kikanTo, 11);
            kikanFromZen = DateManager.getPrevMonth(kikanTo, 23);
            kikanToZen = DateManager.getPrevMonth(kikanTo, 12);
        }
        catch (Exception ex) {
            throw new FtlSystemException("日付算出", null, ex);
        }
        List listClaimData = getClaimtotalDataDao().select(requestDto.getCompanyCd(), 
                                                           TYPE_CD_CLAIM, 
                                                           kikanFrom, 
                                                           kikanTo, 
                                                           requestDto.getTaishoJoken(), 
                                                           requestDto.getHyojiTaisho(),
                                                           sessionDto.getBirdUserInfo().getMstUser().getUserTypeCd(),
                                                           sessionDto.getBirdUserInfo().getUserID(),
                                                           sessionDto.getBirdUserInfo().isLimit());
        if (!(listClaimData == null || listClaimData.isEmpty())) {
            flgExistData  =true;
            hyojitaishoName = getHyojiTaishoName(listClaimData);
        }
        //お褒めデータ取得
        List listOhome = getClaimtotalDataDao().select(requestDto.getCompanyCd(), 
                                                       TYPE_CD_OHOME, 
                                                       kikanFrom, 
                                                       kikanTo, 
                                                       requestDto.getTaishoJoken(), 
                                                       requestDto.getHyojiTaisho(),
                                                       sessionDto.getBirdUserInfo().getMstUser().getUserTypeCd(),
                                                       sessionDto.getBirdUserInfo().getUserID(),
                                                       sessionDto.getBirdUserInfo().isLimit());
        if (!flgExistData && !(listOhome == null || listOhome.isEmpty())) {
            flgExistData  =true;
            hyojitaishoName = getHyojiTaishoName(listOhome);
        }
        
        //画面表示用データ作成
        try {
            listAllData = createMeisai(kikanFrom, kikanTo, listClaimData, listOhome, listBunrui);
        }
        catch (Exception ex) {
            throw new FtlSystemException("お客様の声集計", null, ex);
        }
        finally {
            listClaimData = null;
            listOhome = null;
        }
        
        //合計行セット
        createSumRow(listAllData);
        
        // 前年合計セット
        List listClaimZennen = getClaimtotalDataDao().selectZennen(requestDto.getCompanyCd(), 
                                                                   TYPE_CD_CLAIM, kikanFromZen, 
                                                                   kikanToZen, 
                                                                   requestDto.getTaishoJoken(), 
                                                                   requestDto.getHyojiTaisho(),
                                                                   sessionDto.getBirdUserInfo().getMstUser().getUserTypeCd(),
                                                                   sessionDto.getBirdUserInfo().getUserID(),
                                                                   sessionDto.getBirdUserInfo().isLimit());
        List listOhomeZennen = getClaimtotalDataDao().selectZennen(requestDto.getCompanyCd(), 
                                                                   TYPE_CD_OHOME, 
                                                                   kikanFromZen, 
                                                                   kikanToZen, 
                                                                   requestDto.getTaishoJoken(), 
                                                                   requestDto.getHyojiTaisho(),
                                                                   sessionDto.getBirdUserInfo().getMstUser().getUserTypeCd(),
                                                                   sessionDto.getBirdUserInfo().getUserID(),
                                                                   sessionDto.getBirdUserInfo().isLimit());
        listAllData.add(createZennenRow(listBunrui, listClaimZennen, listOhomeZennen));
        if (!flgExistData && !(listClaimZennen == null || listClaimZennen.isEmpty())) {
            flgExistData  =true;
            hyojitaishoName = getHyojiTaishoName(listClaimZennen);
        }
        if (!flgExistData && !(listOhomeZennen == null || listOhomeZennen.isEmpty())) {
            flgExistData  =true;
            hyojitaishoName = getHyojiTaishoName(listOhomeZennen);
        }
        
        //該当データなし処理
        if (!flgExistData) {
            throw new NoResultException();
        }
        
        //テーブルヘッダーリスト作成
        requestDto.setListTableHeader(getTableHeader(listBunrui));
        
        //セッションDTOへデータをセット
        requestDto.setListSearchData(listAllData);
        
        //表示対象名称セット
        if (CommonUtil.isNull(hyojitaishoName)) {
            if (TaishoJoken.CODE_ALL.equals(requestDto.getTaishoJoken())) {
                hyojitaishoName = "全社";
            }
            else {
                UIClaimTotalData entityHyojitaishoName 
                    = getClaimtotalDataDao().getHyojitaishoName(requestDto.getCompanyCd(),
                                                                requestDto.getTaishoJoken(),
                                                                requestDto.getHyojiTaisho());
                if (entityHyojitaishoName == null) {
                    //表示対象が存在しない場合、該当データなしエラー
                    throw new NoResultException();
                }
                hyojitaishoName = entityHyojitaishoName.getHyojitaishoName();
            }
        }
        requestDto.setHyojiTaishoName(hyojitaishoName);
        //対象期間セット
        requestDto.setTaishoKikanFrom(dateFormatter.format(kikanFrom, true));
        requestDto.setTaishoKikanTo(dateFormatter.format(kikanTo, true));
    }

    /**
     * 表示対象名称取得
     * @param listData
     * @return
     */
    private String getHyojiTaishoName(List listData) {
        return ((UIClaimTotalData) listData.get(0)).getHyojitaishoName();
    }
    /**
     * テーブルヘッダー用リスト作成
     * @param listBunrui
     * @return
     */
    private List getTableHeader(List listBunrui) {
        CodBunruiM entityBunrui = new CodBunruiM();
        entityBunrui.setBnrMName("年月");
        listBunrui.add(0, entityBunrui);
        entityBunrui = new CodBunruiM();
        entityBunrui.setBnrMName("クレーム計");
        listBunrui.add(listBunrui.size(), entityBunrui);
        entityBunrui = new CodBunruiM();
        entityBunrui.setBnrMName("お褒め<br/>(全国)");
        listBunrui.add(listBunrui.size(), entityBunrui);
        return listBunrui;
    }
    
    /**
     * 検索データから指定年月・分類のデータを取得
     * @param listData
     * @param ym
     * @param bunrui
     * @param typeCd
     * @return
     */
    private UIClaimTotalData getClaimData(List listData, String ym, String bunrui, String typeCd) {
        UIClaimTotalData entity = new UIClaimTotalData();
        entity.setJusinDt(ym);
        entity.setClaimCount(new BigDecimal("0"));
        entity.setBnrM(bunrui);
        entity.setTypeCd(typeCd);
        entity.setRowType(typeCd);
        
        for (Iterator ite = listData.iterator(); ite.hasNext();) {
            UIClaimTotalData entityDB = (UIClaimTotalData) ite.next();
            if (ym.equals(entityDB.getJusinDt()) && bunrui.equals(entityDB.getBnrM())) {
                entity.setClaimCount(entityDB.getClaimCount());
                break;
            }
        }
        return entity;
    }
    
    /**
     * 検索お褒めデータから指定年月のデータを取得
     * @return
     */
    private UIClaimTotalData getOhomeData(List listData, String ym) {
        UIClaimTotalData entity = null;
        for (Iterator ite = listData.iterator(); ite.hasNext();) {
            UIClaimTotalData ohomeEntity = (UIClaimTotalData) ite.next();
            if (ym.equals(ohomeEntity.getJusinDt().substring(0, 6))) {
                entity = ohomeEntity;
                break;
            }
        }
        if (entity == null) {
            entity = new UIClaimTotalData();
            entity.setJusinDt(ym);
            entity.setClaimCount(new BigDecimal("0"));
        }
        return entity;
    }
    
    /**
     * 年月ごとの画面表示用List作成
     */
    private List createMeisai(String kikanFrom, String kikanTo, List listClaimData, List listOhome, List listBunrui) throws Exception{
        int loopCnt = 0;
        BigDecimal claimSum;
        List listAllData = new ArrayList();
        
        while (kikanFrom.compareTo(DateManager.getNextMonth(kikanTo, loopCnt)) <= 0) {
            claimSum = new BigDecimal("0");
            String dateYM = DateManager.getNextMonth(kikanTo, loopCnt);
            List listRowData = new ArrayList();
            //年月
            listRowData.add(dateFormatter.format(dateYM, true));
            //クレームの各中分類件数をセット
            for (Iterator ite = listBunrui.iterator(); ite.hasNext();) {
                CodBunruiM codBunrui = (CodBunruiM) ite.next();
                UIClaimTotalData entityData = getClaimData(listClaimData, dateYM, codBunrui.getBnrM(), TYPE_CD_CLAIM);
                claimSum = claimSum.add(entityData.getClaimCount());
                listRowData.add(entityData);
            }
            //クレーム計をセット
            UIClaimTotalData entityClaimSum = new UIClaimTotalData();
            entityClaimSum.setClaimCount(claimSum);
            entityClaimSum.setJusinDt(dateYM);
            entityClaimSum.setTypeCd(TYPE_CD_CLAIM);
            entityClaimSum.setRowType(TYPE_CD_CLAIM);
            listRowData.add(entityClaimSum);
            //お褒め件数をセット
            listRowData.add(getOhomeData(listOhome, dateYM));
            
            listAllData.add(listRowData);
            loopCnt--;
        }
        return listAllData;
    }
    
    /**
     * 合計行をセット
     * @return
     */
    private void createSumRow(List listAllData) {
        List listSumRow = new ArrayList();
        //「年月」列に「合計」をセット
        listSumRow.add("合計");
        for (int i = 0; i < listAllData.size(); i++) {
            List listRow = (List) listAllData.get(i);
            for (int j = 0; j < listRow.size(); j++) {
                if (j == 0) {
                    //1要素目は年月なので処理無し
                    continue;
                }
                if (i == 0) {
                    //1行目は、タイプコードを「S」にセット
                    UIClaimTotalData entity = (UIClaimTotalData) listRow.get(j);
                    UIClaimTotalData entitySumRow = new UIClaimTotalData();
                    entitySumRow.setClaimCount(entity.getClaimCount());
                    entitySumRow.setRowType(TYPE_CD_SUMROW);
                    listSumRow.add(entitySumRow);
                }
                else {
                    UIClaimTotalData entity = (UIClaimTotalData) listRow.get(j);
                    UIClaimTotalData entitySumRow = (UIClaimTotalData) listSumRow.get(j);
                    entitySumRow.setClaimCount(entitySumRow.getClaimCount().add(entity.getClaimCount()));
                    listSumRow.set(j, entitySumRow);
                }
            }
        }
        listAllData.add(listSumRow);

    }
    
    private List  createZennenRow(List listBunrui, List listClaim, List listOhome) {
        List listZennen = new ArrayList();
        listZennen.add("前年合計");
        BigDecimal claimSum = new BigDecimal("0");
        // クレームデータをセット
        for (Iterator ite = listBunrui.iterator(); ite.hasNext();) {
            CodBunruiM codBunrui = (CodBunruiM) ite.next();
            UIClaimTotalData entitySum = null;
            for (Iterator ite2 = listClaim.iterator(); ite2.hasNext();) {
                UIClaimTotalData entity = (UIClaimTotalData) ite2.next();
                if (codBunrui.getBnrM().equals(entity.getBnrM())) {
                    entitySum = entity;
                    break;
                }
            }
            if (entitySum == null) {
                entitySum = new UIClaimTotalData();
                entitySum.setClaimCount(new BigDecimal("0"));
            }
            entitySum.setRowType(TYPE_CD_ZENSUMROW);
            listZennen.add(entitySum);
            //クレーム計算出
            claimSum = claimSum.add(entitySum.getClaimCount());
        }
        // クレーム計セット
        UIClaimTotalData entityClaimTotal = new UIClaimTotalData();
        entityClaimTotal.setClaimCount(claimSum);
        entityClaimTotal.setRowType(TYPE_CD_ZENSUMROW);
        listZennen.add(entityClaimTotal);
        
        // お褒めセット
        UIClaimTotalData entityOhome = null;
        if (listOhome == null || listOhome.size() == 0) {
            entityOhome = new UIClaimTotalData(); 
            entityOhome.setClaimCount(new BigDecimal("0"));
        }
        else {
            entityOhome = (UIClaimTotalData) listOhome.get(0);
        }
        entityOhome.setRowType(TYPE_CD_ZENSUMROW);
        listZennen.add(entityOhome);
        
        return listZennen;
    }
    
    /**
     * 事前条件
     * @param sessionDto
     * @param requestDto
     */
    private void validate(ClaimTotalSessionDto sessionDto, ClaimTotalRequestDto requestDto) {
        if (sessionDto == null) {
            throw new MissingDataException("セッション情報");
        }
        if (requestDto == null) {
            throw new MissingDataException("リクエスト情報");
        }
        if (TaishoJoken.CODE_MISE.equals(requestDto.getTaishoJoken())) {
            
            if (StoreManageUtil.isNull(requestDto.getHyojiTaisho())) {
                throw new NotNullException("店コード");
            }
            if (requestDto.getHyojiTaisho().getBytes().length > 5) {
                throw new NoResultException();
            }
        }
        if (TaishoJoken.CODE_ONER.equals(requestDto.getTaishoJoken())) {
            if (StoreManageUtil.isNull(requestDto.getHyojiTaisho())) {
                throw new NotNullException("オーナーコード");
            }
            if (requestDto.getHyojiTaisho().getBytes().length > 5) {
                throw new NoResultException();
            }
        }
    }
    
    public ClaimTotalDataDao getClaimtotalDataDao() {
        return claimtotalDataDao;
    }

    public void setClaimtotalDataDao(ClaimTotalDataDao claimtotalDataDao) {
        this.claimtotalDataDao = claimtotalDataDao;
    }

    public CodBunruiMDao getClaimtotalCodBunruiMDao() {
        return claimtotalCodBunruiMDao;
    }

    public void setClaimtotalCodBunruiMDao(CodBunruiMDao claimtotalCodBunruiMDao) {
        this.claimtotalCodBunruiMDao = claimtotalCodBunruiMDao;
    }

}
