/*
 * 作成日: 2006/06/07
 */
package jp.co.isid.mos.bird.entry.hanyoapplication.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.entry.hanyoapplication.common.HanyoApplicationCommon;
import jp.co.isid.mos.bird.entry.hanyoapplication.dao.UIEntryMstDao;
import jp.co.isid.mos.bird.entry.hanyoapplication.dao.UIEntryOnerStatusDao;
import jp.co.isid.mos.bird.entry.hanyoapplication.dao.UIEntryStateDao;
import jp.co.isid.mos.bird.entry.hanyoapplication.dto.HanyoApplicationDto;
import jp.co.isid.mos.bird.entry.hanyoapplication.entity.UIEntryMst;
import jp.co.isid.mos.bird.entry.hanyoapplication.logic.CheckEntryNumberLimitLogic;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteException;

/**
 * 申込可能チェック
 * 更新日: 2007/05/24
 * 　　　　　・入力欄追加時の定員チェック方法を変更
 * 　　　　　　　編集対象オーナー以外の申込済みスタッフ数＋編集画面の入力欄 ＞ 定員  の場合エラーにする
 * 　　　　　・「登録・終了」時の定員チェックを追加
 * @author kusama
 */
public class CheckEntryNumberLimitLogicImpl implements CheckEntryNumberLimitLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN005L07";

    private static String CANNOT_EXECUTE_MSG = "申込状況が「満」の場合、既に申請しているオーナー（店コード）以外は登録";
    
    private UIEntryMstDao uiEntryMstHanyoApplicationDao;
    private UIEntryOnerStatusDao uIEntryOnerStatusDao;
    private UIEntryStateDao uiEntryStateHanyoAppDao;
    
	/**
     * 登録内容のチェックを行う
     * @param HanyoEntryDto dto     
     * */
    public void execute(HanyoApplicationDto dto, int mode) {
    	// １．必須のパラメータが満たされていること。E0506（@パラメータ）
        validate(dto);
        
        // ２．エントリーマスタ検索
        UIEntryMst uiEntryMst = getUiEntryMstHanyoApplicationDao().getEntryInfo(dto.getEntryCd(), dto.getEntryYear(), dto.getEntryKai());
        
        // 編集対象オーナーの申込済みスタッフ数取得(DB上のスタッフ数)
        List listEntryState = getUiEntryStateHanyoAppDao().getEntry(dto.getEntryCd(), dto.getEntryYear(), dto.getEntryKai(), dto.getCondCompanyCd(), dto.getCondOnerCd());
        
        // 申込定員数を超えてる場合は、エラー
        if (uiEntryMst.getStaffSu().compareTo(uiEntryMst.getNumberLimit()) >= 0) {
            if (HanyoApplicationCommon.NUMBER_CHECK_MODE_SELECT == mode) {
                List listEntryOner = getUIEntryOnerStatusDao().getEntryOner(dto.getEntryCd(), dto.getEntryYear(), dto.getEntryKai(), dto.getCondCompanyCd(), dto.getCondOnerCd());
                if (listEntryOner == null || listEntryOner.size() == 0) {
                    throw new CannotExecuteException(CANNOT_EXECUTE_MSG);
                }
            }
            else if (HanyoApplicationCommon.NUMBER_CHECK_MODE_EDIT == mode) {
// add start xkhata 20070521 研修参加申込画面チェック不具合
                if ( uiEntryMst.getStaffSu().compareTo(uiEntryMst.getPlaceLimit()) >= 0 ) {
                    throw new CannotExecuteException("定員を超えている為、入力欄を追加");
                }
// add end
            }
        }

//---2007/05/24 update start チェック方法を変更        
        // DB登録済みのデータと画面入力欄の合計が定員を超えるかチェック
        //  全オーナーの申込済み人数
        int cntAllMosikomi = uiEntryMst.getStaffSu().intValue();
        //  編集対象オーナーの申込済み人数
        int cntOnerMosikomi = 0;
        if (listEntryState != null) {
            cntOnerMosikomi = listEntryState.size();
        }
        //  編集対象オーナー以外の申込済み人数
        int cntNotOnerMosikomi = cntAllMosikomi - cntOnerMosikomi;
//        List listEntryState = dto.getListEntryState();
//        if (HanyoApplicationCommon.NUMBER_CHECK_MODE_EDIT == mode) {
//            int insModeCnt = 0;
//            for (Iterator ite = listEntryState.iterator(); ite.hasNext();) {
//                UIEntryState uiEntryState = (UIEntryState) ite.next();
//                if (isNull(uiEntryState.getSelectedStaffId())) {
//                    insModeCnt++;
//                }
//            }
//            int totalCnt = uiEntryMst.getStaffSu().intValue() + insModeCnt;
////  change start xkhata 20070521 研修参加申込画面チェック不具合
////            if (totalCnt >= uiEntryMst.getNumberLimit().intValue()) {
//            if (totalCnt >= uiEntryMst.getPlaceLimit().intValue()) {
//// change end
//                throw new CannotExecuteException("定員を超えている為、入力欄を追加");
//            }
//            
//        }
        if (HanyoApplicationCommon.NUMBER_CHECK_MODE_EDIT == mode) {
            if (dto.getListEntryState().size() + cntNotOnerMosikomi >= uiEntryMst.getPlaceLimit().intValue()) {
                throw new CannotExecuteException("定員を超えている為、入力欄を追加");
            }
        }
        else if (HanyoApplicationCommon.NUMBER_CHECK_MODE_REGIST == mode) {
            if (dto.getListEntryState().size() + cntNotOnerMosikomi > uiEntryMst.getPlaceLimit().intValue()) {
                throw new CannotExecuteException("定員を超えている為、登録");
            }
        }
//--- 2007/05/24 update end
        
//      delete start inazawa 2006/01/09 マスタライセンス４次対応
        // ３．同一オーナーで１１人以上申込チェック
        //List listEntryState = getUiEntryStateHanyoAppDao().getEntry(dto.getEntryCd(), dto.getEntryYear(), dto.getEntryKai(), dto.getCondCompanyCd(), dto.getCondOnerCd());
        //if ((listEntryState.size() + 1) > HanyoApplicationCommon.MOSIKOMI_ALERT_LIMIT) {
        //  dto.setFlgEdtiAlert(true);
        //}
        //else {
        //dto.setFlgEdtiAlert(false);
        //}
        
//      delete end
    }

    /**
     * 必須、妥当性チェック
     */
    private void validate(HanyoApplicationDto dto) {
        /* 必須チェック */
    }
    
    /**
	 * nullチェック
	 */
    private boolean isNull(String val) {
        if (val == null) {
            return true;
        }
        if ("".equals(val.trim())) {
            return true;
        }
        return false;
    }
    
//    /**
//     * レングスチェック
//     * @return boolean true:チェックNG
//     */
//    private boolean isLengthOver(String val, int length) {
//        if (val == null) {
//            return false;
//        }
//        return val.trim().getBytes().length > length ? true : false;
//    }

    public UIEntryMstDao getUiEntryMstHanyoApplicationDao() {
        return uiEntryMstHanyoApplicationDao;
    }

    public void setUiEntryMstHanyoApplicationDao(
            UIEntryMstDao uiEntryMstHanyoApplicationDao) {
        this.uiEntryMstHanyoApplicationDao = uiEntryMstHanyoApplicationDao;
    }

    public UIEntryOnerStatusDao getUIEntryOnerStatusDao() {
        return uIEntryOnerStatusDao;
    }

    public void setUIEntryOnerStatusDao(UIEntryOnerStatusDao entryOnerStatusDao) {
        uIEntryOnerStatusDao = entryOnerStatusDao;
    }

    public UIEntryStateDao getUiEntryStateHanyoAppDao() {
        return uiEntryStateHanyoAppDao;
    }

    public void setUiEntryStateHanyoAppDao(UIEntryStateDao uiEntryStateHanyoAppDao) {
        this.uiEntryStateHanyoAppDao = uiEntryStateHanyoAppDao;
    }

}