package jp.co.isid.mos.bird.analysis.kakouriage.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.analysis.kakouriage.code.KakoUriageConst;
import jp.co.isid.mos.bird.analysis.kakouriage.dao.TrnKakoUriageDao;
import jp.co.isid.mos.bird.analysis.kakouriage.dto.KakoUriageDto;
import jp.co.isid.mos.bird.analysis.kakouriage.dto.KakoUriageReqDto;
import jp.co.isid.mos.bird.analysis.kakouriage.entity.TrnKakoUriage;
import jp.co.isid.mos.bird.analysis.kakouriage.entity.UIKakoUriage;
import jp.co.isid.mos.bird.analysis.kakouriage.logic.SearchKakoUriageLogic;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.dao.MstSibuDao;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.Calculator;
import jp.co.isid.mos.bird.framework.util.DateManager;


/**
 * 日次データ取得 ロジック
 * @author xnkusama
 *
 */
public class SearchKakoUriageLogicImpl implements SearchKakoUriageLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BDT005L02";

    /**DAO*/
    private MstSibuDao mstSibuDao;
    private TrnKakoUriageDao kakouriageTrnKakoUriageDao;
    
    public void execute(KakoUriageDto dto, KakoUriageReqDto dtoReq) {
        // 初期処理
        validate(dto, dtoReq);

        // 検索条件情報作成
        String nendoTo   = dtoReq.getTaishoKikan();
        String nendoFrom = "";
        try {
            nendoFrom = DateManager.getPrevYear(dtoReq.getTaishoKikan(), KakoUriageConst.KIKAN_PULLDOWN_NENDOSU + 1);
        }
        catch (Exception ex) {
            throw new FtlSystemException("過去売上検索", null, ex);
        }
        
        // ２．【DAO】種別売上推移日次Dao．日次データの取得を実行
        List listData = getKakouriageTrnKakoUriageDao()
                            .selectKakoUriage(dtoReq.getCompanyCd(),
                                            dtoReq.getTaishoJoken(),
                                            dtoReq.getHyojiTaisho(),
                                            nendoFrom,
                                            nendoTo,
                                            dto.getBirdUserInfo().isLimit(),
                                            dto.getUserTypeCd(),
                                            dto.getUserId());
        
        // ３．該当データなしチェック（集計行があるので結果が1行の場合は、該当データなし）
        if (listData == null || listData.size() == 1) {
            //検索済フラグリセット
            dto.setSearchedFlg(dtoReq.getWindowId(), false);
            //検索結果をクリア
            dto.removeSearchData(dtoReq.getWindowId());
            throw new NoResultException();
        }
        
        // ４．検索結果ヘッダ情報セット
        setHeaderInfo(dto, dtoReq, listData);
        
        // ５．検索結果 表示用Entity作成
        dtoReq.setListData(makeDispListData(dto, dtoReq, listData));
        
    }

    /**
     * 検索結果 表示用Entity作成
     */
    private List makeDispListData(KakoUriageDto dto, KakoUriageReqDto dtoReq, List listData) {
        
        //各行のEntityを作成 （12ヶ月＋年度合計＋前年比）
        UIKakoUriage entity4 = new UIKakoUriage("04月");
        UIKakoUriage entity5 = new UIKakoUriage("05月");
        UIKakoUriage entity6 = new UIKakoUriage("06月");
        UIKakoUriage entity7 = new UIKakoUriage("07月");
        UIKakoUriage entity8 = new UIKakoUriage("08月");
        UIKakoUriage entity9 = new UIKakoUriage("09月");
        UIKakoUriage entity10= new UIKakoUriage("10月");
        UIKakoUriage entity11= new UIKakoUriage("11月");
        UIKakoUriage entity12= new UIKakoUriage("12月");
        UIKakoUriage entity1 = new UIKakoUriage("01月");
        UIKakoUriage entity2 = new UIKakoUriage("02月");
        UIKakoUriage entity3 = new UIKakoUriage("03月");
        UIKakoUriage entitySum = new UIKakoUriage("年度合計");
        UIKakoUriage entityAvg = new UIKakoUriage("対前年比", true);
        
        List listDispData = new ArrayList();
        listDispData.add(entitySum);
        listDispData.add(entity4);
        listDispData.add(entity5);
        listDispData.add(entity6);
        listDispData.add(entity7);
        listDispData.add(entity8);
        listDispData.add(entity9);
        listDispData.add(entity10);
        listDispData.add(entity11);
        listDispData.add(entity12);
        listDispData.add(entity1);
        listDispData.add(entity2);
        listDispData.add(entity3);
        
        //各月、年度合計をセット
        for (Iterator ite = listData.iterator(); ite.hasNext();) {
            TrnKakoUriage entity = (TrnKakoUriage) ite.next();
            
            if (CommonUtil.isNull(entity.getNendo()) && CommonUtil.isNull(entity.getEigyoDt())) {
                //前年度の総合計行 --> 処理なし
            }
            else if (!CommonUtil.isNull(entity.getNendo()) && CommonUtil.isNull(entity.getEigyoDt())) {
                //年度合計
                setDispDataDetail(dtoReq, entity, entitySum);
            }
            else {
                for (Iterator ite2 = listDispData.iterator(); ite2.hasNext();) {
                    UIKakoUriage entityTemp = (UIKakoUriage) ite2.next();
                    if (entityTemp.getMonth().startsWith(entity.getEigyoDt().substring(4, 6))) {
                        setDispDataDetail(dtoReq, entity, entityTemp);
                        break;
                    }
                }
            }
        }
        //対前年比セット
        entityAvg.setNendo7(Calculator.percentage(entitySum.getNendo7(), entitySum.getNendo6(), 2));
        entityAvg.setNendo7Kyakusu(Calculator.percentage(entitySum.getNendo7Kyakusu(), entitySum.getNendo6Kyakusu(), 2));
        entityAvg.setNendo6(Calculator.percentage(entitySum.getNendo6(), entitySum.getNendo5(), 2));
        entityAvg.setNendo6Kyakusu(Calculator.percentage(entitySum.getNendo6Kyakusu(), entitySum.getNendo5Kyakusu(), 2));
        entityAvg.setNendo5(Calculator.percentage(entitySum.getNendo5(), entitySum.getNendo4(), 2));
        entityAvg.setNendo5Kyakusu(Calculator.percentage(entitySum.getNendo5Kyakusu(), entitySum.getNendo4Kyakusu(), 2));
        entityAvg.setNendo4(Calculator.percentage(entitySum.getNendo4(), entitySum.getNendo3(), 2));
        entityAvg.setNendo4Kyakusu(Calculator.percentage(entitySum.getNendo4Kyakusu(), entitySum.getNendo3Kyakusu(), 2));
        entityAvg.setNendo3(Calculator.percentage(entitySum.getNendo3(), entitySum.getNendo2(), 2));
        entityAvg.setNendo3Kyakusu(Calculator.percentage(entitySum.getNendo3Kyakusu(), entitySum.getNendo2Kyakusu(), 2));
        entityAvg.setNendo2(Calculator.percentage(entitySum.getNendo2(), entitySum.getNendo1(), 2));
        entityAvg.setNendo2Kyakusu(Calculator.percentage(entitySum.getNendo2Kyakusu(), entitySum.getNendo1Kyakusu(), 2));
        
        //当年度指定時の対前年比を再セット
        if (DateManager.getCurrentYear(dto.getBirdDateInfo().getAppDate()).equals(dtoReq.getTaishoKikan())) {
            BigDecimal bigZennen = new BigDecimal("0");
            BigDecimal bigTounen = new BigDecimal("0");
            BigDecimal bigZennenKyakusu = new BigDecimal("0");
            BigDecimal bigTounenKyakusu = new BigDecimal("0");
            //過去売上テーブルの最新年月を取得
            TrnKakoUriage trnKakoUriMaxYM = getKakouriageTrnKakoUriageDao().selectMaxYM(dtoReq.getCompanyCd(), dtoReq.getTaishoKikan()+"04");
            
            if (trnKakoUriMaxYM != null && !CommonUtil.isNull(trnKakoUriMaxYM.getEigyoDt())
                        && DateManager.getCurrentYear(trnKakoUriMaxYM.getEigyoDt()).equals(DateManager.getCurrentYear(dto.getBirdDateInfo().getAppDate()))) 
            {
                for (int i = 1; i < 13; i++) {
                    UIKakoUriage entity = (UIKakoUriage) listDispData.get(i);
                    
                    bigZennen = bigZennen.add(entity.getNendo6());
                    bigTounen = bigTounen.add(entity.getNendo7());
                    bigZennenKyakusu = bigZennenKyakusu.add(entity.getNendo6Kyakusu());
                    bigTounenKyakusu = bigTounenKyakusu.add(entity.getNendo7Kyakusu());

                    if (entity.getMonth().substring(0, 2).equals(trnKakoUriMaxYM.getEigyoDt().substring(4, 6))) {
                        break;
                    }
                }
            }
            entityAvg.setNendo7(Calculator.percentage(bigTounen, bigZennen, 2));
            entityAvg.setNendo7Kyakusu(Calculator.percentage(bigTounenKyakusu, bigZennenKyakusu, 2));
        }
        //前年比Entityを2行目にセット
        listDispData.add(1, entityAvg);
        
        //作成したデータをリターン
        return listDispData;
        
    }
    
    private void setDispDataDetail(KakoUriageReqDto dtoReq, TrnKakoUriage entityDb, UIKakoUriage entityUi) {
        //検索条件の対象期間TO年度
        int taishoKikanToNendo = (new Integer(dtoReq.getTaishoKikan())).intValue();
        //対象データの年度
        int dataNendo = (new Integer(entityDb.getNendo())).intValue();
        
        switch (taishoKikanToNendo - dataNendo) {
        case 0:
            entityUi.setNendo7(entityDb.getUriage());
            entityUi.setNendo7Kyakusu(entityDb.getKyakusu());
            break;
        case 1:
            entityUi.setNendo6(entityDb.getUriage());
            entityUi.setNendo6Kyakusu(entityDb.getKyakusu());
            break;
        case 2:
            entityUi.setNendo5(entityDb.getUriage());
            entityUi.setNendo5Kyakusu(entityDb.getKyakusu());
            break;
        case 3:
            entityUi.setNendo4(entityDb.getUriage());
            entityUi.setNendo4Kyakusu(entityDb.getKyakusu());
            break;
        case 4:
            entityUi.setNendo3(entityDb.getUriage());
            entityUi.setNendo3Kyakusu(entityDb.getKyakusu());
            break;
        case 5:
            entityUi.setNendo2(entityDb.getUriage());
            entityUi.setNendo2Kyakusu(entityDb.getKyakusu());
            break;
        case 6:
            entityUi.setNendo1(entityDb.getUriage());
            entityUi.setNendo1Kyakusu(entityDb.getKyakusu());
            break;
        }
    }
    
    /**
     * 検索結果ヘッダ情報セット
     * @param dto
     * @param dtoReq
     * @param listData
     */
    private void setHeaderInfo(KakoUriageDto dto, KakoUriageReqDto dtoReq, List listData) {
        // 対象期間
        for (Iterator ite = dto.getListTaishoKikan().iterator(); ite.hasNext();) {
            SelectItem item = (SelectItem) ite.next();
            if (item.getValue().equals(dtoReq.getTaishoKikan())) {
                dtoReq.setTaishoKikanDisp(item.getLabel());
            }
        }
        
        // 表示対象
        String hyojiTaisho = "";
        if (TaishoJoken.CODE_ALL.equals(dtoReq.getTaishoJoken())) {
            //全社・全店
            hyojiTaisho = TaishoJoken.getName(dto.getUserTypeCd(), dtoReq.getTaishoJoken());
        }
        else if (TaishoJoken.CODE_MISE.equals(dtoReq.getTaishoJoken())) {
            //店舗：検索結果のEntityから取得
            hyojiTaisho = dtoReq.getHyojiTaisho() + " " +  ((TrnKakoUriage) listData.get(0)).getMiseNameKj();
        }
        dtoReq.setHyojiTaishoDisp(hyojiTaisho);
        
        // 対象店舗数：検索結果最終行の店舗カウントをセット
        BigDecimal bigTenpoCnt = new BigDecimal("0");
        for (Iterator ite = listData.iterator(); ite.hasNext();) {
            TrnKakoUriage entity = (TrnKakoUriage) ite.next();
            if (entity.getEigyoDt() == null && entity.getNendo() != null) {
                if (entity.getMiseCnt().compareTo(bigTenpoCnt) > 0) {
                    bigTenpoCnt = entity.getMiseCnt();
                }
            }
        }
        dtoReq.setTaishoTenpoCnt(bigTenpoCnt.toString());
        
        // テーブルヘッダ
        List listHeader = new ArrayList();
        listHeader.add("月");
        try {
            for (int i = KakoUriageConst.KIKAN_PULLDOWN_NENDOSU; i >= 0; i--) {
                listHeader.add(DateManager.getPrevYear(dtoReq.getTaishoKikan(), i) + "年度");
            }
        }
        catch (Exception ex) {
            throw new FtlSystemException("検索", null, ex);
        }
        dtoReq.setListHeader(listHeader);
    }
    
    /**
     * 初期処理
     * @param dto
     * @param dtoReq
     */
    private void validate(KakoUriageDto dto, KakoUriageReqDto dtoReq) {
        //必須チェック
        if (dto == null) {
            throw new NotNullException("画面情報");
        }
        if (dtoReq == null) {
            throw new NotNullException("画面情報");
        }
        //本部ユーザーのみ店コードチェック
        if (UserType.HONBU.equals(dto.getUserTypeCd())) {
            if (TaishoJoken.CODE_MISE.equals(dtoReq.getTaishoJoken())) {
                // 店コード必須チェック
                if (CommonUtil.isNull(dtoReq.getHyojiTaisho())) {
                    throw new NotNullException("店コード", "condHyojiTaishoMise", 0);
                }
                // レングスチェック
                if (dtoReq.getHyojiTaisho().trim().getBytes().length > 5) {
                    throw new NoResultException();
                }
            }
        }
        
    }
    
    public MstSibuDao getMstSibuDao() {
        return mstSibuDao;
    }

    public void setMstSibuDao(MstSibuDao mstSibuDao) {
        this.mstSibuDao = mstSibuDao;
    }

    public TrnKakoUriageDao getKakouriageTrnKakoUriageDao() {
        return kakouriageTrnKakoUriageDao;
    }

    public void setKakouriageTrnKakoUriageDao(
            TrnKakoUriageDao kakouriageTrnKakoUriageDao) {
        this.kakouriageTrnKakoUriageDao = kakouriageTrnKakoUriageDao;
    }

}
