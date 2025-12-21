package jp.co.isid.mos.bird.analysis.shubetusuiiref.logic.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.analysis.shubetusuiiref.dao.UIShubetuSuiiDao;
import jp.co.isid.mos.bird.analysis.shubetusuiiref.dto.ShubetuSuiiRefDto;
import jp.co.isid.mos.bird.analysis.shubetusuiiref.dto.ShubetuSuiiRefReqDto;
import jp.co.isid.mos.bird.analysis.shubetusuiiref.entity.UIShubetuSuii;
import jp.co.isid.mos.bird.analysis.shubetusuiiref.logic.SearchShubetuSuiiLogic;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.dao.MstSibuDao;
import jp.co.isid.mos.bird.common.entity.MstSibu;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.Calculator;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;


/**
 * 月次データ取得 ロジック
 * @author xnkusama
 *
 */
public class SearchGepoLogicImpl implements SearchShubetuSuiiLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BDT003L04";

    /**DAO*/
    private MstSibuDao mstSibuDao;
    private UIShubetuSuiiDao shubetuSuiiRefDao;
    
    public void execute(ShubetuSuiiRefDto dto, ShubetuSuiiRefReqDto dtoReq) {
        // 初期処理
        validate(dto, dtoReq);

        // 検索条件情報作成
        String targetDtFrom = "";
        try {
            //当月指定の時は12ヶ月前、それ以外の時は11ヶ月前
            if (dto.getBirdDateInfo().getAppDate().substring(0, 6).equals(dtoReq.getKikan())) {
                targetDtFrom = DateManager.getPrevMonth(dtoReq.getKikan(), 12);
            }
            else {
                targetDtFrom = DateManager.getPrevMonth(dtoReq.getKikan(), 11);
            }
            //CSV出力用にFrom日を保持
            dtoReq.setTaishoKikanCsv(targetDtFrom);
                
        }
        catch (Exception ex) {
            throw new FtlSystemException("日付", null, ex);
        }
        String targetDtTo   = dtoReq.getKikan();
        
        // ２．【DAO】種別売上推移日次Dao．日次データの取得を実行
        List listData = getShubetuSuiiRefDao()
                            .selectGepoData(dtoReq.getCompanyCd(),
                                            dtoReq.getHyojiTaishoSibu(),
                                            dtoReq.getHyojiTaishoMise(),
                                            targetDtFrom,
                                            targetDtTo,
                                            dtoReq.getTenpoShu(),
                                            dtoReq.getTaishoJoken(),
                                            dtoReq.getZenDataShu(),
                                            dto.getBirdUserInfo().isLimit(),
                                            dto.getUserTypeCd(),
                                            dto.getUserId());
        
        // ３．該当データなしチェック
        if (listData == null || listData.isEmpty()) {
            //検索結果をクリア
            dto.removeSearchData(dtoReq.getWindowId());
            throw new NoResultException();
        }
        
        // ４．検索結果ヘッダ情報セット
        setHeaderInfo(dto, dtoReq, listData, targetDtFrom, targetDtTo);
        
        // ５．12ヶ月合計・平均行作成
        setSumRow(dto, dtoReq, listData);
        
        // 取得データをDTOへセット
        dtoReq.setListGepoData(listData);
    }

    /**
     * 検索結果ヘッダ情報セット
     * @param dto
     * @param dtoReq
     * @param targetDtFrom
     * @param targetDtTo
     * @param listData
     */
    private void setHeaderInfo(ShubetuSuiiRefDto dto, ShubetuSuiiRefReqDto dtoReq, List listData, String targetDtFrom, String targetDtTo) {
        // 対象期間
        DateFormatter dtFormatter = new DateFormatter(DateFormatter.DATE_TYPE_YM, "yyyy'年'MM'月'");
        dtoReq.setTaishoKikan(dtFormatter.format(dtoReq.getKikan(), true));
        
        // 表示対象
        String hyojiTaisho = "";
        if (TaishoJoken.CODE_ALL.equals(dtoReq.getTaishoJoken())) {
            //全社・全店
            hyojiTaisho = TaishoJoken.getName(dto.getUserTypeCd(), dtoReq.getTaishoJoken());
        }
        else if (TaishoJoken.CODE_SIBU.equals(dtoReq.getTaishoJoken())) {
            //支部
            List listSibu = getMstSibuDao().getSibu(dtoReq.getCompanyCd(), dtoReq.getHyojiTaishoSibu());
            hyojiTaisho = dtoReq.getHyojiTaishoSibu() + " " + ((MstSibu) listSibu.get(0)).getSibuName();
        }
        else if (TaishoJoken.CODE_MISE.equals(dtoReq.getTaishoJoken())) {
            //店舗：検索結果のEntityから取得
            hyojiTaisho = dtoReq.getHyojiTaishoMise() + " " + ((UIShubetuSuii) listData.get(0)).getMiseNameKj();
        }
        dtoReq.setHyojiTaishoDisp(hyojiTaisho);
        
        // 店舗カウントをセット
        UIShubetuSuii entityTenpoCount = getShubetuSuiiRefDao()
                                            .selectGepoMiseCount(dtoReq.getCompanyCd(),
                                                                dtoReq.getHyojiTaishoSibu(),
                                                                dtoReq.getHyojiTaishoMise(),
                                                                targetDtFrom,
                                                                targetDtTo,
                                                                dtoReq.getTenpoShu(),
                                                                dtoReq.getTaishoJoken(),
                                                                dtoReq.getZenDataShu(),
                                                                dto.getBirdUserInfo().isLimit(),
                                                                dto.getUserTypeCd(),
                                                                dto.getUserId());
        dtoReq.setTaishoTenpoCnt(entityTenpoCount.getMiseCnt().toString());
        
        // 店舗種別
        if (UserType.HONBU.equals(dto.getUserTypeCd())) {
            String tenpoShuName = "";
            for (Iterator ite = dto.getListTenpoShu().iterator(); ite.hasNext();) {
                SelectItem item = (SelectItem) ite.next();
                if (item.getValue().equals(dtoReq.getTenpoShu())) {
                    tenpoShuName = item.getLabel();
                }
            }
            dtoReq.setTenpoShuName(tenpoShuName);
        }
        // 前年データ種別
        String zenDataShuName = "";
        for (Iterator ite = dto.getListZenDataShu().iterator(); ite.hasNext();) {
            SelectItem item = (SelectItem) ite.next();
            if (item.getValue().equals(dtoReq.getZenDataShu())) {
                zenDataShuName = item.getLabel();
            }
        }
        dtoReq.setZenDataShuName(zenDataShuName);
    }
    
    /**
     * 12ヶ月合計・平均行作成
     * @param dto
     * @param dtoReq
     * @param listData
     */
    private void setSumRow(ShubetuSuiiRefDto dto, ShubetuSuiiRefReqDto dtoReq, List listData) {
        //合計行Entity
        UIShubetuSuii entitySum = new UIShubetuSuii();
        entitySum.setEigyoDt("12ヶ月合計");
        //平均行Entity
        UIShubetuSuii entityAvg = new UIShubetuSuii();
        entityAvg.setEigyoDt("12ヶ月平均");
        //取得月数カウンタ
        int cntMonth = 0;
        
        //12ヶ月合計を算出。ただし、当月は合計に含めない
        for (Iterator ite = listData.iterator(); ite.hasNext();) {
            UIShubetuSuii entity = (UIShubetuSuii) ite.next();
            if (!dto.getBirdDateInfo().getAppDate().substring(0, 6).equals(entity.getEigyoDt())) {
                cntMonth++;
                entitySum.setEatKen(calcAdd(entitySum.getEatKen(), entity.getEatKen()));
                entitySum.setEatKin(calcAdd(entitySum.getEatKin(), entity.getEatKin()));
                entitySum.setTakeKen(calcAdd(entitySum.getTakeKen(), entity.getTakeKen()));
                entitySum.setTakeKin(calcAdd(entitySum.getTakeKin(), entity.getTakeKin()));
                entitySum.setTelKen(calcAdd(entitySum.getTelKen(), entity.getTelKen()));
                entitySum.setTelKin(calcAdd(entitySum.getTelKin(), entity.getTelKin()));
                entitySum.setDriveKen(calcAdd(entitySum.getDriveKen(), entity.getDriveKen()));
                entitySum.setDriveKin(calcAdd(entitySum.getDriveKin(), entity.getDriveKin()));
                entitySum.setTakuhaiKen(calcAdd(entitySum.getTakuhaiKen(), entity.getTakuhaiKen()));
                entitySum.setTakuhaiKin(calcAdd(entitySum.getTakuhaiKin(), entity.getTakuhaiKin()));
                entitySum.setGaihanKen(calcAdd(entitySum.getGaihanKen(), entity.getGaihanKen()));
                entitySum.setGaihanKin(calcAdd(entitySum.getGaihanKin(), entity.getGaihanKin()));
                entitySum.setSyubetsu07Ken(calcAdd(entitySum.getSyubetsu07Ken(), entity.getSyubetsu07Ken()));
                entitySum.setSyubetsu07Kin(calcAdd(entitySum.getSyubetsu07Kin(), entity.getSyubetsu07Kin()));
                entitySum.setSyubetsu08Ken(calcAdd(entitySum.getSyubetsu08Ken(), entity.getSyubetsu08Ken()));
                entitySum.setSyubetsu08Kin(calcAdd(entitySum.getSyubetsu08Kin(), entity.getSyubetsu08Kin()));
                entitySum.setSyubetsu09Ken(calcAdd(entitySum.getSyubetsu09Ken(), entity.getSyubetsu09Ken()));
                entitySum.setSyubetsu09Kin(calcAdd(entitySum.getSyubetsu09Kin(), entity.getSyubetsu09Kin()));
                entitySum.setSyubetsu10Ken(calcAdd(entitySum.getSyubetsu10Ken(), entity.getSyubetsu10Ken()));
                entitySum.setSyubetsu10Kin(calcAdd(entitySum.getSyubetsu10Kin(), entity.getSyubetsu10Kin()));
                entitySum.setNettakeKen(calcAdd(entitySum.getNettakeKen(), entity.getNettakeKen()));
                entitySum.setNettakeKin(calcAdd(entitySum.getNettakeKin(), entity.getNettakeKin()));
                entitySum.setNettakuhaiKen(calcAdd(entitySum.getNettakuhaiKen(), entity.getNettakuhaiKen()));
                entitySum.setNettakuhaiKin(calcAdd(entitySum.getNettakuhaiKin(), entity.getNettakuhaiKin()));
                entitySum.setSyubetsu13Ken(calcAdd(entitySum.getSyubetsu13Ken(), entity.getSyubetsu13Ken()));
                entitySum.setSyubetsu13Kin(calcAdd(entitySum.getSyubetsu13Kin(), entity.getSyubetsu13Kin()));
                entitySum.setSyubetsu14Ken(calcAdd(entitySum.getSyubetsu14Ken(), entity.getSyubetsu14Ken()));
                entitySum.setSyubetsu14Kin(calcAdd(entitySum.getSyubetsu14Kin(), entity.getSyubetsu14Kin()));
                entitySum.setSyubetsu15Ken(calcAdd(entitySum.getSyubetsu15Ken(), entity.getSyubetsu15Ken()));
                entitySum.setSyubetsu15Kin(calcAdd(entitySum.getSyubetsu15Kin(), entity.getSyubetsu15Kin()));
                entitySum.setOtherKen(calcAdd(entitySum.getOtherKen(), entity.getOtherKen()));
                entitySum.setOtherKin(calcAdd(entitySum.getOtherKin(), entity.getOtherKin()));
                entitySum.setEatKenZen(calcAdd(entitySum.getEatKenZen(), entity.getEatKenZen()));
                entitySum.setEatKinZen(calcAdd(entitySum.getEatKinZen(), entity.getEatKinZen()));
                entitySum.setTakeKenZen(calcAdd(entitySum.getTakeKenZen(), entity.getTakeKenZen()));
                entitySum.setTakeKinZen(calcAdd(entitySum.getTakeKinZen(), entity.getTakeKinZen()));
                entitySum.setTelKenZen(calcAdd(entitySum.getTelKenZen(), entity.getTelKenZen()));
                entitySum.setTelKinZen(calcAdd(entitySum.getTelKinZen(), entity.getTelKinZen()));
                entitySum.setDriveKenZen(calcAdd(entitySum.getDriveKenZen(), entity.getDriveKenZen()));
                entitySum.setDriveKinZen(calcAdd(entitySum.getDriveKinZen(), entity.getDriveKinZen()));
                entitySum.setTakuhaiKenZen(calcAdd(entitySum.getTakuhaiKenZen(), entity.getTakuhaiKenZen()));
                entitySum.setTakuhaiKinZen(calcAdd(entitySum.getTakuhaiKinZen(), entity.getTakuhaiKinZen()));
                entitySum.setGaihanKenZen(calcAdd(entitySum.getGaihanKenZen(), entity.getGaihanKenZen()));
                entitySum.setGaihanKinZen(calcAdd(entitySum.getGaihanKinZen(), entity.getGaihanKinZen()));
                entitySum.setSyubetsu07KenZen(calcAdd(entitySum.getSyubetsu07KenZen(), entity.getSyubetsu07KenZen()));
                entitySum.setSyubetsu07KinZen(calcAdd(entitySum.getSyubetsu07KinZen(), entity.getSyubetsu07KinZen()));
                entitySum.setSyubetsu08KenZen(calcAdd(entitySum.getSyubetsu08KenZen(), entity.getSyubetsu08KenZen()));
                entitySum.setSyubetsu08KinZen(calcAdd(entitySum.getSyubetsu08KinZen(), entity.getSyubetsu08KinZen()));
                entitySum.setSyubetsu09KenZen(calcAdd(entitySum.getSyubetsu09KenZen(), entity.getSyubetsu09KenZen()));
                entitySum.setSyubetsu09KinZen(calcAdd(entitySum.getSyubetsu09KinZen(), entity.getSyubetsu09KinZen()));
                entitySum.setSyubetsu10KenZen(calcAdd(entitySum.getSyubetsu10KenZen(), entity.getSyubetsu10KenZen()));
                entitySum.setSyubetsu10KinZen(calcAdd(entitySum.getSyubetsu10KinZen(), entity.getSyubetsu10KinZen()));
                entitySum.setNettakeKenZen(calcAdd(entitySum.getNettakeKenZen(), entity.getNettakeKenZen()));
                entitySum.setNettakeKinZen(calcAdd(entitySum.getNettakeKinZen(), entity.getNettakeKinZen()));
                entitySum.setNettakuhaiKenZen(calcAdd(entitySum.getNettakuhaiKenZen(), entity.getNettakuhaiKenZen()));
                entitySum.setNettakuhaiKinZen(calcAdd(entitySum.getNettakuhaiKinZen(), entity.getNettakuhaiKinZen()));
                entitySum.setSyubetsu13KenZen(calcAdd(entitySum.getSyubetsu13KenZen(), entity.getSyubetsu13KenZen()));
                entitySum.setSyubetsu13KinZen(calcAdd(entitySum.getSyubetsu13KinZen(), entity.getSyubetsu13KinZen()));
                entitySum.setSyubetsu14KenZen(calcAdd(entitySum.getSyubetsu14KenZen(), entity.getSyubetsu14KenZen()));
                entitySum.setSyubetsu14KinZen(calcAdd(entitySum.getSyubetsu14KinZen(), entity.getSyubetsu14KinZen()));
                entitySum.setSyubetsu15KenZen(calcAdd(entitySum.getSyubetsu15KenZen(), entity.getSyubetsu15KenZen()));
                entitySum.setSyubetsu15KinZen(calcAdd(entitySum.getSyubetsu15KinZen(), entity.getSyubetsu15KinZen()));
                entitySum.setOtherKenZen(calcAdd(entitySum.getOtherKenZen(), entity.getOtherKenZen()));
                entitySum.setOtherKinZen(calcAdd(entitySum.getOtherKinZen(), entity.getOtherKinZen()));
            }
        }
        
        //合計行を追加
        listData.add(entitySum);
        
        //平均を算出
        entityAvg.setEatKen(calcAvg(entitySum.getEatKen(), cntMonth));
        entityAvg.setEatKin(calcAvg(entitySum.getEatKin(), cntMonth));
        entityAvg.setTakeKen(calcAvg(entitySum.getTakeKen(), cntMonth));
        entityAvg.setTakeKin(calcAvg(entitySum.getTakeKin(), cntMonth));
        entityAvg.setTelKen(calcAvg(entitySum.getTelKen(), cntMonth));
        entityAvg.setTelKin(calcAvg(entitySum.getTelKin(), cntMonth));
        entityAvg.setDriveKen(calcAvg(entitySum.getDriveKen(), cntMonth));
        entityAvg.setDriveKin(calcAvg(entitySum.getDriveKin(), cntMonth));
        entityAvg.setTakuhaiKen(calcAvg(entitySum.getTakuhaiKen(), cntMonth));
        entityAvg.setTakuhaiKin(calcAvg(entitySum.getTakuhaiKin(), cntMonth));
        entityAvg.setGaihanKen(calcAvg(entitySum.getGaihanKen(), cntMonth));
        entityAvg.setGaihanKin(calcAvg(entitySum.getGaihanKin(), cntMonth));
        entityAvg.setSyubetsu07Ken(calcAvg(entitySum.getSyubetsu07Ken(), cntMonth));
        entityAvg.setSyubetsu07Kin(calcAvg(entitySum.getSyubetsu07Kin(), cntMonth));
        entityAvg.setSyubetsu08Ken(calcAvg(entitySum.getSyubetsu08Ken(), cntMonth));
        entityAvg.setSyubetsu08Kin(calcAvg(entitySum.getSyubetsu08Kin(), cntMonth));
        entityAvg.setSyubetsu09Ken(calcAvg(entitySum.getSyubetsu09Ken(), cntMonth));
        entityAvg.setSyubetsu09Kin(calcAvg(entitySum.getSyubetsu09Kin(), cntMonth));
        entityAvg.setSyubetsu10Ken(calcAvg(entitySum.getSyubetsu10Ken(), cntMonth));
        entityAvg.setSyubetsu10Kin(calcAvg(entitySum.getSyubetsu10Kin(), cntMonth));
        entityAvg.setNettakeKen(calcAvg(entitySum.getNettakeKen(), cntMonth));
        entityAvg.setNettakeKin(calcAvg(entitySum.getNettakeKin(), cntMonth));
        entityAvg.setNettakuhaiKen(calcAvg(entitySum.getNettakuhaiKen(), cntMonth));
        entityAvg.setNettakuhaiKin(calcAvg(entitySum.getNettakuhaiKin(), cntMonth));
        entityAvg.setSyubetsu13Ken(calcAvg(entitySum.getSyubetsu13Ken(), cntMonth));
        entityAvg.setSyubetsu13Kin(calcAvg(entitySum.getSyubetsu13Kin(), cntMonth));
        entityAvg.setSyubetsu14Ken(calcAvg(entitySum.getSyubetsu14Ken(), cntMonth));
        entityAvg.setSyubetsu14Kin(calcAvg(entitySum.getSyubetsu14Kin(), cntMonth));
        entityAvg.setSyubetsu15Ken(calcAvg(entitySum.getSyubetsu15Ken(), cntMonth));
        entityAvg.setSyubetsu15Kin(calcAvg(entitySum.getSyubetsu15Kin(), cntMonth));
        entityAvg.setOtherKen(calcAvg(entitySum.getOtherKen(), cntMonth));
        entityAvg.setOtherKin(calcAvg(entitySum.getOtherKin(), cntMonth));
        entityAvg.setEatKenZen(calcAvg(entitySum.getEatKenZen(), cntMonth));
        entityAvg.setEatKinZen(calcAvg(entitySum.getEatKinZen(), cntMonth));
        entityAvg.setTakeKenZen(calcAvg(entitySum.getTakeKenZen(), cntMonth));
        entityAvg.setTakeKinZen(calcAvg(entitySum.getTakeKinZen(), cntMonth));
        entityAvg.setTelKenZen(calcAvg(entitySum.getTelKenZen(), cntMonth));
        entityAvg.setTelKinZen(calcAvg(entitySum.getTelKinZen(), cntMonth));
        entityAvg.setDriveKenZen(calcAvg(entitySum.getDriveKenZen(), cntMonth));
        entityAvg.setDriveKinZen(calcAvg(entitySum.getDriveKinZen(), cntMonth));
        entityAvg.setTakuhaiKenZen(calcAvg(entitySum.getTakuhaiKenZen(), cntMonth));
        entityAvg.setTakuhaiKinZen(calcAvg(entitySum.getTakuhaiKinZen(), cntMonth));
        entityAvg.setGaihanKenZen(calcAvg(entitySum.getGaihanKenZen(), cntMonth));
        entityAvg.setGaihanKinZen(calcAvg(entitySum.getGaihanKinZen(), cntMonth));
        entityAvg.setSyubetsu07KenZen(calcAvg(entitySum.getSyubetsu07KenZen(), cntMonth));
        entityAvg.setSyubetsu07KinZen(calcAvg(entitySum.getSyubetsu07KinZen(), cntMonth));
        entityAvg.setSyubetsu08KenZen(calcAvg(entitySum.getSyubetsu08KenZen(), cntMonth));
        entityAvg.setSyubetsu08KinZen(calcAvg(entitySum.getSyubetsu08KinZen(), cntMonth));
        entityAvg.setSyubetsu09KenZen(calcAvg(entitySum.getSyubetsu09KenZen(), cntMonth));
        entityAvg.setSyubetsu09KinZen(calcAvg(entitySum.getSyubetsu09KinZen(), cntMonth));
        entityAvg.setSyubetsu10KenZen(calcAvg(entitySum.getSyubetsu10KenZen(), cntMonth));
        entityAvg.setSyubetsu10KinZen(calcAvg(entitySum.getSyubetsu10KinZen(), cntMonth));
        entityAvg.setNettakeKenZen(calcAvg(entitySum.getNettakeKenZen(), cntMonth));
        entityAvg.setNettakeKinZen(calcAvg(entitySum.getNettakeKinZen(), cntMonth));
        entityAvg.setNettakuhaiKenZen(calcAvg(entitySum.getNettakuhaiKenZen(), cntMonth));
        entityAvg.setNettakuhaiKinZen(calcAvg(entitySum.getNettakuhaiKinZen(), cntMonth));
        entityAvg.setSyubetsu13KenZen(calcAvg(entitySum.getSyubetsu13KenZen(), cntMonth));
        entityAvg.setSyubetsu13KinZen(calcAvg(entitySum.getSyubetsu13KinZen(), cntMonth));
        entityAvg.setSyubetsu14KenZen(calcAvg(entitySum.getSyubetsu14KenZen(), cntMonth));
        entityAvg.setSyubetsu14KinZen(calcAvg(entitySum.getSyubetsu14KinZen(), cntMonth));
        entityAvg.setSyubetsu15KenZen(calcAvg(entitySum.getSyubetsu15KenZen(), cntMonth));
        entityAvg.setSyubetsu15KinZen(calcAvg(entitySum.getSyubetsu15KinZen(), cntMonth));
        entityAvg.setOtherKenZen(calcAvg(entitySum.getOtherKenZen(), cntMonth));
        entityAvg.setOtherKinZen(calcAvg(entitySum.getOtherKinZen(), cntMonth));

        //平均行を追加
        listData.add(entityAvg);
    }
    
    /**
     * 足し算
     * @param val1
     * @param val2
     * @return
     */
    private BigDecimal calcAdd(BigDecimal val1, BigDecimal val2) {
        if (val1 == null) {
           val1 = new BigDecimal("0");
        }
        if (val2 == null) {
            val2 = new BigDecimal("0");
         }
        return val1.add(val2);
    }
    /**
     * 12ヶ月平均
     * @param val
     * @return
     */
    private BigDecimal calcAvg(BigDecimal val, int cntMotnh) {
        return Calculator.divide(val, new BigDecimal(cntMotnh), 0);
    }
    
    /**
     * 初期処理
     * @param dto
     * @param dtoReq
     */
    private void validate(ShubetuSuiiRefDto dto, ShubetuSuiiRefReqDto dtoReq) {
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
                if (dtoReq.getHyojiTaishoMise() == null || "".equals(dtoReq.getHyojiTaishoMise().trim())) {
                    throw new NotNullException("店コード", "condHyojiTaishoMise", 0);
                }
                // レングスチェック
                if (dtoReq.getHyojiTaishoMise().trim().length() > 5) {
                    throw new NoResultException();
                }
            }
        }
        
    }
    
    public UIShubetuSuiiDao getShubetuSuiiRefDao() {
        return shubetuSuiiRefDao;
    }

    public void setShubetuSuiiRefDao(UIShubetuSuiiDao shubetuSuiiRefDao) {
        this.shubetuSuiiRefDao = shubetuSuiiRefDao;
    }

    public MstSibuDao getMstSibuDao() {
        return mstSibuDao;
    }

    public void setMstSibuDao(MstSibuDao mstSibuDao) {
        this.mstSibuDao = mstSibuDao;
    }

}
