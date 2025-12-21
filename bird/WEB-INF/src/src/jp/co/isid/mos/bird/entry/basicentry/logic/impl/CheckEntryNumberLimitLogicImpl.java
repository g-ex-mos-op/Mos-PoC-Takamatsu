/*
 * 作成日: 2006/06/07
 */
package jp.co.isid.mos.bird.entry.basicentry.logic.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.entry.basicentry.common.BasicEntryCommon;
import jp.co.isid.mos.bird.entry.basicentry.dao.UIEntryMstDao;
import jp.co.isid.mos.bird.entry.basicentry.dao.UIEntryOnerStatusDao;
import jp.co.isid.mos.bird.entry.basicentry.dao.UIEntryStateDao;
import jp.co.isid.mos.bird.entry.basicentry.dto.BasicEntryDto;
import jp.co.isid.mos.bird.entry.basicentry.entity.UIEntryMst;
import jp.co.isid.mos.bird.entry.basicentry.entity.UIEntryState;
import jp.co.isid.mos.bird.entry.basicentry.logic.CheckEntryNumberLimitLogic;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteException;

import org.seasar.framework.log.Logger;

/**
 * 申込可能チェック
 * @author kusama
 */
public class CheckEntryNumberLimitLogicImpl implements CheckEntryNumberLimitLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN002L07";

    private static Logger logger_ = Logger.getLogger(CheckEntryNumberLimitLogicImpl.class);

    private static String CANNOT_EXECUTE_MSG = "申込状況が「満」の場合、既に申請しているオーナー（店コード）以外は登録";
    
    private UIEntryMstDao uiEntryMstBasicEntryDao;
    private UIEntryOnerStatusDao uiEntryOnerStatusBasicEntryDao;
    private UIEntryStateDao uiEntryStateBasicEntryDao;
    
	/**
     * 登録内容のチェックを行う
     * @param HanyoEntryDto dto     
     * */
    public void execute(BasicEntryDto dto, int mode) {
    	// １．必須のパラメータが満たされていること。E0506（@パラメータ）
        validate(dto);
        
        // ２．エントリーマスタ検索
        UIEntryMst uiEntryMst = getUiEntryMstBasicEntryDao().getEntryInfo(dto.getEntryCd(), dto.getEntryYear(), dto.getEntryKai());
        
        // 申込定員数を超えてる場合は、エラー
        if (Integer.valueOf(uiEntryMst.getSomosikomiNum()).intValue() 
                    >= Integer.valueOf(uiEntryMst.getNumberLimit()).intValue()) 
        {
            if (BasicEntryCommon.NUMBER_CHECK_MODE_SELECT == mode) {
                List listEntryOner = getUiEntryOnerStatusBasicEntryDao().getEntryOner(dto.getEntryCd(), dto.getEntryYear(), dto.getEntryKai(), dto.getCondCompanyCd(), dto.getCondOnerCd());
                
                if (listEntryOner == null || listEntryOner.size() == 0) {
                    throw new CannotExecuteException(CANNOT_EXECUTE_MSG);
                }
            }
            else if (BasicEntryCommon.NUMBER_CHECK_MODE_EDIT == mode) {
                throw new CannotExecuteException("定員を超えている為、入力欄を追加");
            }
        }
        
        // DB登録済みのデータと画面入力欄の合計が定員を超えるかチェック
        List listEntryState = dto.getListEntryState();
        if (BasicEntryCommon.NUMBER_CHECK_MODE_EDIT == mode) {
            int insModeCnt = 0;
            for (Iterator ite = listEntryState.iterator(); ite.hasNext();) {
                UIEntryState uiEntryState = (UIEntryState) ite.next();
                if (isNull(uiEntryState.getSelectedStaffId())) {
                    insModeCnt++;
                }
            }
            int totalCnt = Integer.valueOf(uiEntryMst.getSomosikomiNum()).intValue() + insModeCnt;
            if (totalCnt >= Integer.valueOf(uiEntryMst.getNumberLimit()).intValue()) {
                throw new CannotExecuteException("定員を超えている為、入力欄を追加");
            }
            
        }
        
        
        // ３．同一オーナーで１１人以上申込チェック
        //List listEntryState = getUiEntryStateBasicEntryDao().getEntry(dto.getEntryCd(), dto.getEntryYear(), dto.getEntryKai(), dto.getCondCompanyCd(), dto.getCondOnerCd());
        if ((listEntryState.size() + 1) > BasicEntryCommon.MOSIKOMI_ALERT_LIMIT) {
            dto.setFlgEdtiAlert(true);
        }
        else {
            dto.setFlgEdtiAlert(false);
        }
    }

    /**
     * 必須、妥当性チェック
     */
    private void validate(BasicEntryDto dto) {
        /* 必須チェック */
    }
    
    /**
     * nullチェック
     */
    private boolean isNull(String val) {
        return (val == null || val.trim().equals("")) ? true : false;
    }
    
    public UIEntryMstDao getUiEntryMstBasicEntryDao() {
        return uiEntryMstBasicEntryDao;
    }

    public void setUiEntryMstBasicEntryDao(UIEntryMstDao uiEntryMstBasicEntryDao) {
        this.uiEntryMstBasicEntryDao = uiEntryMstBasicEntryDao;
    }

    public UIEntryOnerStatusDao getUiEntryOnerStatusBasicEntryDao() {
        return uiEntryOnerStatusBasicEntryDao;
    }

    public void setUiEntryOnerStatusBasicEntryDao(
            UIEntryOnerStatusDao uiEntryOnerStatusBasicEntryDao) {
        this.uiEntryOnerStatusBasicEntryDao = uiEntryOnerStatusBasicEntryDao;
    }

    public UIEntryStateDao getUiEntryStateBasicEntryDao() {
        return uiEntryStateBasicEntryDao;
    }

    public void setUiEntryStateBasicEntryDao(
            UIEntryStateDao uiEntryStateBasicEntryDao) {
        this.uiEntryStateBasicEntryDao = uiEntryStateBasicEntryDao;
    }
}