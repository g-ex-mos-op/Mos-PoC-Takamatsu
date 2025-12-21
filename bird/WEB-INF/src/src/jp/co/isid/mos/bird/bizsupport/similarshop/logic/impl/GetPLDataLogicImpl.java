/*
 * 作成日: 2006/4/12
 */
package jp.co.isid.mos.bird.bizsupport.similarshop.logic.impl;


import java.math.BigDecimal;
import jp.co.isid.mos.bird.bizsupport.similarshop.dto.RuijitenReferenceDto;
import jp.co.isid.mos.bird.bizsupport.similarshop.dao.UIRuijiPLTenpoDaoInfo;
import jp.co.isid.mos.bird.bizsupport.similarshop.entity.UIRuijiPLTenpoInfo;
import jp.co.isid.mos.bird.bizsupport.similarshop.logic.GetPLDataLogic;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 類似店情報情報の取得ロジックアクション
 * @author Nakajima
 */
public class GetPLDataLogicImpl implements GetPLDataLogic {

    /* ロジックID */
    private static final String LOGIC_ID = "BBS008L02";

    /**
     * 類似店情報情報（UIRuijiPLTenpoDaoInfo）
     */
    private UIRuijiPLTenpoDaoInfo uIRuijiPLTenpoDaoInfo;
    
	/**
	 * PLデータDaoの設定
	 * @return uIRuijiPLTenpoDaoInfo を戻します。
	 */
	public UIRuijiPLTenpoDaoInfo getUIRuijiPLTenpoDaoInfo() {
		return uIRuijiPLTenpoDaoInfo;
	}
	/**
	 * PLデータDaoの設定
	 * @param UIRuijiPLTenpoDaoInfo uIRuijiPLTenpoDaoInfo を設定。
	 */
	public void setUIRuijiPLTenpoDaoInfo(UIRuijiPLTenpoDaoInfo uIRuijiPLTenpoDaoInfo) {
		this.uIRuijiPLTenpoDaoInfo = uIRuijiPLTenpoDaoInfo;
	}
    
    

	/**
	 * 対象店舗と類似店のPLデータの取得
	 * @return 検索結果
	 */
	public void execute(RuijitenReferenceDto ruijitenReferenceDto, String miseCd, String sysdate, String area, String tenpoKeitai, String ritti, String uriageSitei, BigDecimal uriageMin, BigDecimal uriageMax, String openDtMin, String openDtMax, String userId, String userTypeCd, boolean limitFlg) {
		
		//対象月(YYYYMM形式)取得
        String prevPlYm = null;
        try {
        	//15日までは前々月、16日以降は前月データを用いる
        	if(Integer.valueOf(sysdate.substring(6,8)).intValue() < 16){
        		//前々月
        		prevPlYm = DateManager.getPrevMonth(sysdate.substring(0,6), 2);
        	}else{
        		//前月
        		prevPlYm = DateManager.getPrevMonth(sysdate.substring(0,6), 1);
        	}
        } catch (Exception e) {
            // 無処理
        }
        
        //対象月(YYYYMM形式)セット
        ruijitenReferenceDto.setPrevPlYm(prevPlYm);
		

        //類似店情報.対象店舗のPLデータの取得
        UIRuijiPLTenpoInfo misePLData = uIRuijiPLTenpoDaoInfo.getPLData(prevPlYm, miseCd, userId, userTypeCd, limitFlg);
        
        if (misePLData != null) {
            // 類似店情報.類似店の平均PLデータの取得
            ruijitenReferenceDto.setMiseRuijiPLData(uIRuijiPLTenpoDaoInfo.getAveragePLData(prevPlYm, area, tenpoKeitai, ritti, uriageSitei, uriageMin, uriageMax, openDtMin, openDtMax));
        
        }
        ruijitenReferenceDto.setMisePLData(misePLData);
    	
      
		
        
	}
}
