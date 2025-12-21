package jp.co.isid.mos.bird.bizsupport.budgetregist.logic.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import jp.co.isid.mos.bird.bizsupport.budgetregist.common.BudgetRegistConstants;
import jp.co.isid.mos.bird.bizsupport.budgetregist.dao.MstMiseInfoDao;
import jp.co.isid.mos.bird.bizsupport.budgetregist.dao.TrnBudgetInfoDao;
import jp.co.isid.mos.bird.bizsupport.budgetregist.dto.BudgetRegistDto;
import jp.co.isid.mos.bird.bizsupport.budgetregist.logic.BudgetRegistClearLogic;
import jp.co.isid.mos.bird.bizsupport.common.code.BudgetConstants;
import jp.co.isid.mos.bird.bizsupport.common.dao.CtlYosanControlDateDao;
import jp.co.isid.mos.bird.bizsupport.common.entity.CtlYosanControlDate;
import jp.co.isid.mos.bird.bizsupport.common.logic.YosanControlDateLogic;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 下期予算クリアロジック
 * 
 * @author Aspac
 */
public class BudgetRegistClearLogicImpl implements BudgetRegistClearLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS022L05";


    
    /**
     * 店情報DAO
     */
    private MstMiseInfoDao mstMiseInfoDao;
    /**
     * 予算登録制御日付管理取得ロジック
     */
    private YosanControlDateLogic yosanControlDateLogic;
    /**
     * 予算登録制御日付管理Dao
     */
    private CtlYosanControlDateDao ctlYosanControlDateDao;
    
    /**
     * 店情報DAOを取得します。
     * @return 店情報DAO
     */
    public MstMiseInfoDao getMstMiseInfoDao() {
        return mstMiseInfoDao;
    }
    
    /**
     * 店情報DAOを設定します。
     * @param lumpTakeInPlYmVerifier 店情報DAO
     */
    public void setMstMiseInfoDao(MstMiseInfoDao mstMiseInfoDao) {
        this.mstMiseInfoDao = mstMiseInfoDao;
    }
    
    
    
    /**
     * 予算更新DAO
     */
    private TrnBudgetInfoDao trnBudgetInfoDao;
    
    /**
     * 予算更新DAOを取得します。 
     * @return
     */
    public TrnBudgetInfoDao getTrnBudgetInfoDao() {
        return trnBudgetInfoDao;
    }
    
    /**
     * 予算更新DAOを設定します。
     * @param trnBudgetInfoDao
     */
    public void setTrnBudgetInfoDao(TrnBudgetInfoDao trnBudgetInfoDao) {
        this.trnBudgetInfoDao = trnBudgetInfoDao;
    }
    
    
    
    
    
    /**
     * 下期予算クリアロジック
     * ※クリア処理は企業ごとに１回のみ実行可能
     * ※9月のみ実行可能
     * @param budgetRegistDto 予算登録CSV取込DTO
     * @return 予算登録データ
     */
    public void execute(BudgetRegistDto budgetRegistDto) {
        
        String sysdate = "";
        String companyCd = budgetRegistDto.getCondCompanyCd();
        
//--- 2007/10/05 update start 下期予算クリアの実行可能時期を変更（９月～３月に実行可能）        
//        //システム日付が９月であることを確認する
//        try {
//            sysdate = getBirdDateInfo().getSysDate();
//            SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("M");
//            DateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
//            String month = simpleDateFormatter.format(dateFormatter.parse(sysdate));
//            if(!month.equals(BudgetRegistConstants.BUDGET_CLEAR_MONTH)){
//                return ;
//            }
//        } catch (ParseException e) {
//            throw new FtlSystemException(
//            "予算登録クリアの処理月判定処理中に、年度算出ロジックでエラーが発生しました。");
//        }
        try {
            sysdate = budgetRegistDto.getBirdDateInfo().getSysDate();
            SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("M");
            DateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
            String month = simpleDateFormatter.format(dateFormatter.parse(sysdate));
            if(month.compareTo("4") >= 0 && month.compareTo("8") <= 0) {
                return ;
            }
        } catch (ParseException e) {
            throw new FtlSystemException(
            "予算登録クリアの処理月判定処理中に、年度算出ロジックでエラーが発生しました。");
        }
//--- 2007/10/05 update end
//--- 2007/10/09 add start        
        //上期予算置換え済みかチェック
        //    「画面で選択した年月 >= 上期置換えTo年月」の場合はエラー
        CtlYosanControlDate ctlYosan 
            = getYosanControlDateLogic()
                        .execute(budgetRegistDto.getCondCompanyCd(), budgetRegistDto.getSysNendo(), BudgetConstants.YOSAN_CONTROL_SHORI_KBN_KAMIKI_OKIKAE);
        if (ctlYosan != null 
                && (BudgetConstants.YOSAN_CONTROL_STATE_FLG_ZUMI.equals(ctlYosan.getStateFlg())
                        ||  BudgetConstants.YOSAN_CONTROL_STATE_FLG_IRAIZUMI.equals(ctlYosan.getStateFlg()))) 
        {
            CtlYosanControlDate ctlYosanKamikiTo 
                = getYosanControlDateLogic()
                        .execute(budgetRegistDto.getCondCompanyCd(), budgetRegistDto.getSysNendo(), BudgetConstants.YOSAN_CONTROL_SHORI_KBN_KAMIKI_OKIKAE_TO);
            if (budgetRegistDto.getSimokiClearFromMonth().compareTo(ctlYosanKamikiTo.getShoriDt()) <= 0) {
                if (BudgetConstants.YOSAN_CONTROL_STATE_FLG_ZUMI.equals(ctlYosan.getStateFlg())) {
                    throw new CannotExecuteException("上期予算売上置換処理済の年月は予算クリア");
                }
                else if (BudgetConstants.YOSAN_CONTROL_STATE_FLG_IRAIZUMI.equals(ctlYosan.getStateFlg())) {
                    throw new CannotExecuteException("上期予算売上置換処理依頼済の年月は予算クリア");
                }
            }
        }
            
//--- 2007/10/09 add end
        
        //システム日付から本年度と翌年度を取得し、下期予算の範囲(9月～3月)を生成する
        //  -->範囲Fromは画面で選択された年月に変更 2007/10/05
        //  -->クリア済みチェックは4月～3月とする
        String sysNendo = "";
        String nxtNendo = "";
        try {
            sysNendo = DateManager.getCurrentYear(sysdate);
            nxtNendo = DateManager.getNextYear(sysNendo,1);
        } catch (Exception e) {
            throw new FtlSystemException(
            "予算登録クリアの更新月生成処理中に、年度算出ロジックでエラーが発生しました。");
        }
//--- 2007/10/05 update クリアの開始年月は画面で選択された開始月にする。        
//        String fromDate = sysNendo + BudgetRegistConstants.BUDGET_GLEAR_MONTH_START;
        String fromDate = budgetRegistDto.getSimokiClearFromMonth();
        String checkFromDate = sysNendo + BudgetRegistConstants.BUDGET_NENDO_FROM_MONTH;
        String toDate   = nxtNendo + BudgetRegistConstants.BUDGET_GLEAR_MONTH_END;
        
        
        //対象企業の予算クリアの実績を確認する。クリア処理は企業ごとに１回のみ可能
        if(getTrnBudgetInfoDao().selectBudgetInfoClearDone(companyCd, checkFromDate, toDate) > 0){
            throw new GenericMessageException("既に予算クリア済みです。予算クリア処理は会社ごとに１回のみ実行可能です。");
        }
        
        String userid = budgetRegistDto.getUserId();
        String lstPgm = BudgetRegistConstants.BUDGET_PRG_ID;
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        
        
        //予算をクリアする(削除フラグを更新する)
        getTrnBudgetInfoDao().updateBudgetInfoCancel(companyCd, fromDate, toDate,
                userid, lstPgm, sysdate, currentTimestamp);

        //予算登録制御日付管理 登録処理
        doYosanControlDate(budgetRegistDto);
    }
    
    /**
     * 予算登録制御日付管理TBL登録処理
     * @param budgetRegistDto
     */
    private void doYosanControlDate(BudgetRegistDto dto) {
        //登録済みデータを検索
        CtlYosanControlDate entity = getYosanControlDateLogic().execute(dto.getCondCompanyCd(), dto.getSysNendo(), BudgetConstants.YOSAN_CONTROL_SHORI_KBN_SIMOKI_CLEAR_FROM);
        if (entity == null) {
            //新規登録
            entity = new CtlYosanControlDate();
            entity.setCompanyCd(dto.getCondCompanyCd());
            entity.setNendo(dto.getSysNendo());
            entity.setShoriKbn(BudgetConstants.YOSAN_CONTROL_SHORI_KBN_SIMOKI_CLEAR_FROM);
            entity.setStateFlg(BudgetConstants.YOSAN_CONTROL_STATE_FLG_IRAIZUMI);
            entity.setShoriDt(dto.getSimokiClearFromMonth());
            entity.setLastPgm(LOGIC_ID.substring(0, 6));
            entity.setLastUser(dto.getUserId());
            entity.setLastTmsp(dto.getCurrentTimestamp());
            getCtlYosanControlDateDao().insert(entity);
        }
        else {
            //更新
            entity.setShoriDt(dto.getSimokiClearFromMonth());
            entity.setStateFlg(BudgetConstants.YOSAN_CONTROL_STATE_FLG_IRAIZUMI);
            entity.setLastUser(dto.getUserId());
            getCtlYosanControlDateDao().update(entity);
        }
    }

    public YosanControlDateLogic getYosanControlDateLogic() {
        return yosanControlDateLogic;
    }

    public void setYosanControlDateLogic(
            YosanControlDateLogic yosanControlDateLogicImpl) {
        this.yosanControlDateLogic = yosanControlDateLogicImpl;
    }

    public CtlYosanControlDateDao getCtlYosanControlDateDao() {
        return ctlYosanControlDateDao;
    }

    public void setCtlYosanControlDateDao(
            CtlYosanControlDateDao ctlYosanControlDateDao) {
        this.ctlYosanControlDateDao = ctlYosanControlDateDao;
    }
}
