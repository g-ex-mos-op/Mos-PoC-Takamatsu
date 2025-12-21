package jp.co.isid.mos.bird.bizsupport.budgetregist.logic.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizsupport.budgetregist.dto.BudgetRegistDto;
import jp.co.isid.mos.bird.bizsupport.budgetregist.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizsupport.common.dao.CodCompanyDao;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 予算登録画面の検索条件を取得します。
 * @author Aspac
 */
public class ConditionLogicImpl implements ConditionLogic {
    
    /* ロジックID */
    public static final String LOGIC_ID = "BBS022L01";

    /*【DAO】*/
    private CodCompanyDao codCompanyInfoDao;

    public CodCompanyDao getCodCompanyInfoDao() {
        return codCompanyInfoDao;
    }

    public void setCodCompanyInfoDao(CodCompanyDao codCompanyInfoDao) {
        this.codCompanyInfoDao = codCompanyInfoDao;
    }
    
    /**
     * 予算登録画面の検索条件を取得します。
     * @param String userId ユーザーID
     * @return List  
     * @exception ApplicationException
     */
    public void execute(BudgetRegistDto dto) throws ApplicationException {
        
        try {
            
            //システム日付を取得
            setSystemDate(dto);
            
            //下期予算クリア処理の許可を判断
            judgeAllowClear(dto);
            
            //対象年度リスト生成
            createYearList(dto);
        }
        catch(Exception e){
            throw new FtlSystemException(
            "初期表示処理中に、年度算出ロジックでエラーが発生しました。", "", e);
        }
        
        return;
    }
    
    
    /**
     * システム日付取得
     * 
     */
    private void setSystemDate(BudgetRegistDto dto) throws ParseException {
        // システム日付取得
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        String sysdate = dto.getBirdDateInfo().getSysDate();
        String sysNendo = DateManager.getCurrentYear(sysdate);
        dto.setSysdate(sysdate);
        dto.setCurrentTimestamp(currentTimestamp);
        dto.setSysDateYearMonth(getYearMonth(sysdate));
        dto.setSysNendo(sysNendo);
    }
    
    
    /**
     * 下期予算クリア処理が可能かを判断
     * ※システム日付が９月の場合のみ下期予算クリアが可能
     */
    private void judgeAllowClear(BudgetRegistDto dto) throws ParseException {
        
        // 下期予算クリア処理が可能かを判断
        String month = getMonth(dto.getSysdate());
//--- 2007/10/05 クリア処理可能判断を変更 --> 9月から3月まで処理可能
//        if(month.equals(BudgetRegistConstants.BUDGET_CLEAR_MONTH)) {
//            dto.setClearAllowFlg(true);
//        }
        if(month.compareTo("09") >= 0 || month.compareTo("03") <= 0) 
        {
            dto.setClearAllowFlg(true);
        }
        else {
            dto.setClearAllowFlg(false);
        }
    }
    
    /**
     * 年度リスト生成
     * ※年度リストを生成・設定する
     * ※年度リストの初期表示を設定する
     * 　上期(4月～9月)には本年度を表示する
     * 　下期(10月～3月)には来年度を表示する
     *  2007/10/16変更
     *   4月～12月には本年度を表示する
     *   1月～3月には来年度を表示する
     */
    private void createYearList(BudgetRegistDto dto) throws Exception {
        
        List listYear = new ArrayList();
        String sysdate = dto.getBirdDateInfo().getSysDate();        
        String sysNendo = DateManager.getCurrentYear(sysdate);
        String nxtNendo = DateManager.getNextYear(sysNendo,1);
        
        listYear.add(new SelectItem(sysNendo, sysNendo));
        listYear.add(new SelectItem(nxtNendo, nxtNendo));
        
        // Dto設定
        String month = getMonth(sysdate);
//        if(month.compareTo("4") >= 0 && month.compareTo("9") <= 0) {
//            dto.setCondYear(sysNendo);
//        }
//        else {
//            dto.setCondYear(nxtNendo);
//        }
        if(month.compareTo("01") >= 0 && month.compareTo("03") <= 0) {
            dto.setCondYear(nxtNendo);
        }
        else {
            dto.setCondYear(sysNendo);
        }
        dto.setListYear(listYear);
        
    }
    
    
    
    /**
     * 年月を取得
     */
    private String getYearMonth(String sysdate) throws ParseException {
        SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("yyyyMM");
        DateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormatter.format(dateFormatter.parse(sysdate));
    }
    
    /**
     * 月を取得
     */
    private String getMonth(String sysdate) throws ParseException {
        SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("MM");
        DateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormatter.format(dateFormatter.parse(sysdate));
    }
    
    
}
