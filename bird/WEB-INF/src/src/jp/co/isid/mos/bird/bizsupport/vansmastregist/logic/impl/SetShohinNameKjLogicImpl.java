/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.vansmastregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.vansmastregist.logic.SetShohinNameKjLogic;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.common.VansmastRegistCommon;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.common.VansmastRegistConstants;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.dao.UIShohinInfoDao;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.entity.UIVansShohinInfo;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.entity.UIKanrimotoInfo;

import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * 商品名のセットロジック
 * @author narita
 */
public class SetShohinNameKjLogicImpl implements SetShohinNameKjLogic {

    /** ロジックID 定義 */
	public static final String LOGIC_ID = "BBS029L07";
    
	/** バンズ倉庫別登録 注文商品情報取得DAO */
    private UIShohinInfoDao uIShohinInfoDao;
            
	/**
     * 商品名のセットを行う
     * @param shohinList 代表商品リスト 
     * @return shohinList 代表商品リスト 
     */
    public List execute(List shohinList) {
    	
    	UIKanrimotoInfo kanriBetuList = new UIKanrimotoInfo();
    	UIVansShohinInfo uIVansShohinInfo = new UIVansShohinInfo();
    	    	
    	NumericFormatter numFormatter = new NumericFormatter();  	
    	String shoNameKj = "";
    	
    	// 取引先別の処理
    	for(int y = 0;y < shohinList.size(); y++){
    		
    		// 取引先別の商品リストを取得
    		kanriBetuList = (UIKanrimotoInfo)shohinList.get(y);
		    	
    		// 商品の処理
		    for(int i = 0;i < kanriBetuList.getShohinList().size(); i++){
		    		
		    	// 商品リストを取得
		    	uIVansShohinInfo = (UIVansShohinInfo)kanriBetuList.getShohinList().get(i);

		    	// 商品名称セット処理
		    	
		    	// 商品名の取得対象チェック
		    	if(uIVansShohinInfo.getDataMode() == VansmastRegistConstants.DATA_MODE_INSERT){ 
		    		if(!VansmastRegistCommon.isNull(uIVansShohinInfo.getShoCdDai())){

			    		// 商品コードの5桁変換
			    		if(uIVansShohinInfo.getShoCdDai().length() < 5){
			    			uIVansShohinInfo.setShoCdDai(numFormatter.format(
			    					uIVansShohinInfo.getShoCdDai(),VansmastRegistConstants.SHO_CD_FORMAT) );
			    		}
			    		// 商品名の取得
			    		shoNameKj = getUIShohinInfoDao().getShohinNameKj(uIVansShohinInfo.getShoCdDai());
			    		uIVansShohinInfo.setShoNameKj(shoNameKj);
			    		
		    		}else{
		    			// 商品コードが未入力の場合は、商品名称をクリアする
		    			uIVansShohinInfo.setShoNameKj("");
			    	}
		    	}
		    }
    	}
    	return shohinList;
    }
    
	public UIShohinInfoDao getUIShohinInfoDao() {
		return uIShohinInfoDao;
	}

	public void setUIShohinInfoDao(UIShohinInfoDao shohinInfoDao) {
		uIShohinInfoDao = shohinInfoDao;
	}
}
