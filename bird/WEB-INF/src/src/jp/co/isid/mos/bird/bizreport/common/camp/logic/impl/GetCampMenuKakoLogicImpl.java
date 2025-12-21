/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.common.camp.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.camp.code.MenuTotaledKbn;
import jp.co.isid.mos.bird.bizreport.common.camp.dao.CodCampMenuDao;
import jp.co.isid.mos.bird.bizreport.common.camp.logic.GetCampMenuLogic;
import jp.co.isid.mos.bird.bizreport.common.camp.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * メニュー情報取得ロジック(キャンペーン過去売上用）
 * 
 * 対象キャンペーンのメニューコードリストとサブメニューリストを取得します。
 * 
 * @author xnkusama
 *
 */
public class GetCampMenuKakoLogicImpl implements GetCampMenuLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BBRCMPL03";

	/** メニューDAO */
	private CodCampMenuDao campMenuDao;
    /**
     * 事前条件処理
     * 
     * @param dto
     * @throws ApplicationException
     */
    private void validate(String campId, String menuTotaledKbn)
    {
        if (CommonUtil.isNull(campId)) {
            throw new NotNullException("キャンペーン識別番号");
        }
        //対象条件
        if (CommonUtil.isNull(menuTotaledKbn)) {
            throw new NotNullException("メニュー集計区分");
        }        
    }
	/**
	 * 対象キャンペーンのメニューコードリストとサブメニューリストを取得し返します。
	 * 
	 * @see jp.co.isid.mos.bird.bizreport.common.camp.logic.GetCampMenuLogic#execute(campId)
	 */
	public List execute(String campId, String menuTotaledKbn) {	
		//１．事前条件処理
		validate(campId, menuTotaledKbn);
		//単品メニューリスト設定
		if(MenuTotaledKbn.CODE_TANPIN.equals(menuTotaledKbn)) {
			return getCampMenuDao().selectMenu(campId);
		}
		//単品(集約)リスト設定
		if(MenuTotaledKbn.CODE_SHUYAKU.equals(menuTotaledKbn)) {
			return getCampMenuDao().selectSumMenuKako(campId);	
		}
		return null;
	}
	/**
	 * @return campMenuDao を戻します。
	 */
	public CodCampMenuDao getCampMenuDao() {
		return campMenuDao;
	}

	/**
	 * @param campMenuDao 設定する campMenuDao。
	 */
	public void setCampMenuDao(CodCampMenuDao campMenuDao) {
		this.campMenuDao = campMenuDao;
	}


}
