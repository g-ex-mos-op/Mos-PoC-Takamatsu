/*
 * 作成日: 2006/03/24
 */
package jp.co.isid.mos.bird.bizsupport.noinputoner.logic.impl;

import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.noinputoner.dao.UIPLDataStateInfoDao;
import jp.co.isid.mos.bird.bizsupport.noinputoner.dao.UITenpoInfoDao;
import jp.co.isid.mos.bird.bizsupport.noinputoner.entity.UIPLDataStateInfo;
import jp.co.isid.mos.bird.bizsupport.noinputoner.entity.UITenpoInfo;
import jp.co.isid.mos.bird.bizsupport.noinputoner.logic.GetPLDataStateLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotSelectedException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 過去13ヶ月のPLデータの入力状況を取得ロジック インターフェイス
 * @author xnkusama
 * 更新日:2013/02/13 xkinu 対象年月 日付妥当性チェック不具合対応
 *        日付の妥当性チェック処理でyyyyMM形式の値をyyMMdd形式の値として処理が行われていた不具合を解消
 * 
 */
public class GetPLDataStateLogicImpl implements GetPLDataStateLogic {

    /* ロジックID */
	public static final String LOGIC_ID = "BBS004L01";
    /* 検索対象月数 */
    private static final int NENGETU_DISPLAY_MONTH = 13;
    /* DAO */
    private UIPLDataStateInfoDao uiPlDataStateInfoDao;
    private UITenpoInfoDao uiTenpoInfoDao;
    
    /**
     * 過去13ヶ月のPLデータの入力状況を取得
     * @param String 企業コード
     * @param String 対象年月
     * @param String 支部コード
     * @param String 入力未入力情報 0:未入力 1:入力
     * @param boolean クローズ店を含むフラグ true:含む false:含まない
     * @return List  店PL情報
     * @exception ApplicationException
     */
    public List execute(String companyCd, String nengetu, String sibuCd, String kbnInput, boolean closeMiseFlg) throws ApplicationException {
        
        // パラメータチェック
        validate(companyCd, nengetu, sibuCd, kbnInput);
        String dateFrom ="";
        String dateTo = nengetu;
        
        try {
            //2006/04/07 過去13ヶ月に変更
        	//dateFrom = DateManager.getPrevMonth(nengetu, 26);
            dateFrom = DateManager.getPrevMonth(nengetu, NENGETU_DISPLAY_MONTH);
        }
        catch (Exception ex) {
            throw new FtlSystemException("過去"+NENGETU_DISPLAY_MONTH+"ヶ月のPLデータの入力状況の取得");
        }
        
        /* ２．DAO【店PL情報．店情報の取得】を実行する */
        List listTenpo = getUiTenpoInfoDao().getTenpoInfo(companyCd, sibuCd, dateFrom, dateTo, closeMiseFlg);
        /* ３．DAO【PLデータ情報．入力状況の取得】*/
        List listPL = getUiPlDataStateInfoDao().getInputState(companyCd, sibuCd, dateFrom, dateTo, closeMiseFlg);
        /* ３で取得データを２の店情報にセットする */
        for (Iterator iteMise = listTenpo.iterator(); iteMise.hasNext();) {
            UITenpoInfo entityMise = (UITenpoInfo) iteMise.next();
            String miseCd = entityMise.getMiseCd();
            String onerCd = entityMise.getOnerCd();
            for (Iterator itePL = listPL.iterator(); itePL.hasNext();) {
                UIPLDataStateInfo entityPL = (UIPLDataStateInfo) itePL.next();

                // 店舗PL
                if (!isNull(miseCd)) {
                    if (miseCd.equals(entityPL.getMiseCd()) && companyCd.equals(entityPL.getCompanyCd())) {
                        setPLData(entityMise, nengetu, entityPL.getPlYm(), entityPL.getErrFlg());
                        itePL.remove();
                    }
                }
                else {
                    // 本社PL(オーナー)
                    if (onerCd.equals(entityPL.getMiseCd()) && companyCd.equals(entityPL.getCompanyCd())) {
                        setPLData(entityMise, nengetu, entityPL.getPlYm(), entityPL.getErrFlg());
                        itePL.remove();
                    }
                }
            }
        }
        /* ４．入力/未入力の絞込み */
//---2006/04/07 未入力/エラーの絞込み
//        for (Iterator ite = listTenpo.iterator(); ite.hasNext();) {
//            UITenpoInfo entity = (UITenpoInfo) ite.next();
//            if ("1".equals(kbnInput)) {
//                if ("".equals(entity.getPlDt1())) {
//                    ite.remove();
//                }
//            }
//            else if ("0".equals(kbnInput)) {
//                if ("0".equals(entity.getPlDt1())) {
//                    ite.remove();
//                }                
//            }
//        }
        for (Iterator ite = listTenpo.iterator(); ite.hasNext();) {
            UITenpoInfo entity = (UITenpoInfo) ite.next();
            if ("1".equals(kbnInput)) {
                if (!"1".equals(entity.getPlDt1())) {
                    ite.remove();
                }
            }
            else if ("0".equals(kbnInput)) {
                if (!("".equals(entity.getPlDt1()) || "1".equals(entity.getPlDt1()) || "9".equals(entity.getPlDt1()))) {
                    ite.remove();
                }                
            }
        }
        
        if (listTenpo == null || listTenpo.size() == 0) {
            throw new NoResultException();
        }
        return listTenpo;
    }
    
    private void setPLData(UITenpoInfo entity, String nengetu, String targetYM, String errFlg) throws ApplicationException {
        int loopIndex = -1;
        String setValue = "";
        for (int i = 0; i < NENGETU_DISPLAY_MONTH; i++) {
            String subNengetu = "";
            try {
            	subNengetu = DateManager.getPrevMonth(nengetu, i);
            }
            catch (Exception ex) {
                throw new FtlSystemException("過去"+NENGETU_DISPLAY_MONTH+"ヶ月のPLデータの入力状況の取得");
            }
            if (targetYM.equals(subNengetu)) {
                loopIndex = i;
                if ("1".equals(errFlg)) { 
                    setValue = "1";
                }
                else if ("9".equals(errFlg)) {
                    setValue = "9";
                }
                else {
                    setValue = "0";
                }
                break;
            }
        }
        switch (loopIndex) {
            case 0:
                entity.setPlDt1(setValue);
                break;
            case 1:
                entity.setPlDt2(setValue);
                break;
            case 2:
                entity.setPlDt3(setValue);
                break;
            case 3:
                entity.setPlDt4(setValue);
                break;
            case 4:
                entity.setPlDt5(setValue);
                break;
            case 5:
                entity.setPlDt6(setValue);
                break;
            case 6:
                entity.setPlDt7(setValue);
                break;
            case 7:
                entity.setPlDt8(setValue);
                break;
            case 8:
                entity.setPlDt9(setValue);
                break;
            case 9:
                entity.setPlDt10(setValue);
                break;
            case 10:
                entity.setPlDt11(setValue);
                break;
            case 11:
                entity.setPlDt12(setValue);
                break;
            case 12:
                entity.setPlDt13(setValue);
                break;
            case 13:
                entity.setPlDt14(setValue);
                break;
            case 14:
                entity.setPlDt15(setValue);
                break;
            case 15:
                entity.setPlDt16(setValue);
                break;
            case 16:
                entity.setPlDt17(setValue);
                break;
            case 17:
                entity.setPlDt18(setValue);
                break;
            case 18:
                entity.setPlDt19(setValue);
                break;
            case 19:
                entity.setPlDt20(setValue);
                break;
            case 20:
                entity.setPlDt21(setValue);
                break;
            case 21:
                entity.setPlDt22(setValue);
                break;
            case 22:
                entity.setPlDt23(setValue);
                break;
            case 23:
                entity.setPlDt24(setValue);
                break;
            case 24:
                entity.setPlDt25(setValue);
                break;
            case 25:
                entity.setPlDt26(setValue);
                break;
            case 26:
                entity.setPlDt27(setValue);
                break;
        }
    }
    /**
     * 
     * @param rCompanyCd
     * @param nengetu
     * @param sibuCd
     * @param kbnInput
     * @throws ApplicationException
	 * 更新日:2013/02/13 xkinu 対象年月 日付妥当性チェック不具合対応
	 *        日付の妥当性チェック処理でyyyyMM形式の値をyyMMdd形式の値として処理が行われていた不具合を解消
	 *        日付妥当性チェック処理を削除
     */
    private void validate(String rCompanyCd, String nengetu, String sibuCd, String kbnInput) throws ApplicationException {
        // 企業コード
        if (isNull(rCompanyCd)) {
            throw new NotNullException("企業コード");
        }
        // 対象年月
        if (isNull(nengetu)) {
            throw new NotNullException("対象年月");
        }
//        // 支部コード
//        if (isNull(sibuCd)) {
//            throw new NotNullException("支部コード");
//        }
//--- 2006/04/05 支部コード、未／到着のどちらかが必須
//        // 入力未入力情報
//        if (isNull(kbnInput)) {
//            throw new NotNullException("入力未入力情報");
//        }
        if (isNull(sibuCd) && isNull(kbnInput)) {
            //throw new NotSelectedException("未／到着または支部コード");
            throw new NotSelectedException("状況または支部");
        }
    }
    
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
    
	public UIPLDataStateInfoDao getUiPlDataStateInfoDao() {
		return uiPlDataStateInfoDao;
	}
	public void setUiPlDataStateInfoDao(
			UIPLDataStateInfoDao uiPlDataStateInfoDao) {
		this.uiPlDataStateInfoDao = uiPlDataStateInfoDao;
	}
	public UITenpoInfoDao getUiTenpoInfoDao() {
		return uiTenpoInfoDao;
	}
	public void setUiTenpoInfoDao(UITenpoInfoDao uiTenpoInfoDao) {
		this.uiTenpoInfoDao = uiTenpoInfoDao;
	}
}