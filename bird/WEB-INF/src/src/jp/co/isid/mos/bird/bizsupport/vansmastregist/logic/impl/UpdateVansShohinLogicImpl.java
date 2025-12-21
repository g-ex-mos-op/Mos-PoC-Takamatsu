/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.vansmastregist.logic.impl;

import java.sql.Timestamp;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.vansmastregist.logic.UpdateVansShohinLogic;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.common.VansmastRegistConstants;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.dao.UIVansShohinInfoDao;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.entity.UIKanrimotoInfo;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.entity.UIVansShohinInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteWithReasonException;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * 代表商品情報の登録・修正・削除ロジック
 * @author narita
 */
public class UpdateVansShohinLogicImpl implements UpdateVansShohinLogic {

    /** ロジックID 定義 */
	public static final String LOGIC_ID = "BBS029L06";

	/** バンズ倉庫別登録 代表商品情報DAO */
    private UIVansShohinInfoDao uIVansShohinInfoDao;
    
    /**
     * 代表商品情報の登録・修正・削除を行う
     * @param shohinList 代表商品リスト 
     * @param BirdUserInfo ログインユーザ情報
     * @param Timestamp    タイムスタンプ
     * @return int 結果コード
     */
    public int execute(List shohinList,BirdUserInfo birdUserInfo,Timestamp currentTimestamp) {
    	    
    	// 代表商品リスト
    	UIVansShohinInfo uIVansShohinInfo = new UIVansShohinInfo();
    	
    	int result = 9;
    	
    	// 取引先別の処理
    	for(int y = 0;y < shohinList.size(); y++){
    		
    		// 取引先別の商品リストを取得
    		UIKanrimotoInfo kanriBetuList = (UIKanrimotoInfo)shohinList.get(y);
    		
	    	// 商品の処理
	    	for(int i = 0;i < kanriBetuList.getShohinList().size(); i++){
	    		// 商品リストを取得
	    		uIVansShohinInfo = (UIVansShohinInfo)kanriBetuList.getShohinList().get(i);
        	        	        	
	        	// 更新対象の商品
	        	if( uIVansShohinInfo.getDataMode().equals(VansmastRegistConstants.DATA_MODE_UPDATE) ){
	        		
	        		// 削除フラグの判断
	        		if( !uIVansShohinInfo.isDeleteFlg() ){
	        		
	    	        	// エントリー情報のセット
	    	        	setUpdateData(uIVansShohinInfo,birdUserInfo,currentTimestamp,false);
	    	        	
		               	// バンズ倉庫別登録情報の更新
		               	result = getUIVansShohinInfoDao().updateVansShohin(uIVansShohinInfo);
			           	if(result == 0) {
			               	// 登録に失敗
			           		throw new CannotExecuteWithReasonException(
			           				VansmastRegistConstants.MSG_EXCLUSION_CHK,
			           				VansmastRegistConstants.MSG_UPDATE);
			            }
	        			
	        		}else{
	    	        	
		               	// バンズ倉庫別登録情報の削除
		               	result = getUIVansShohinInfoDao().deleteVansShohin(uIVansShohinInfo);
			           	if(result == 0) {
			               	// 登録に失敗
			           		throw new CannotExecuteWithReasonException(
			           				VansmastRegistConstants.MSG_EXCLUSION_CHK,
			           				VansmastRegistConstants.MSG_UPDATE);
			            }
	        		}
	        	// 代表商品の新規登録処理
	        	}else if(uIVansShohinInfo.getDataMode().equals(VansmastRegistConstants.DATA_MODE_INSERT)){
	        		
	        		// 未設定フラグが立っていないもの
	        		if(!uIVansShohinInfo.isNotingFlg()){
	        		
	    	        	// エントリー情報のセット
	    	        	setUpdateData(uIVansShohinInfo,birdUserInfo,currentTimestamp,true);
	    	        	
		               	// バンズ倉庫別登録情報の新規登録
		               	result = getUIVansShohinInfoDao().insertVansShohin(uIVansShohinInfo);
			           	if(result == 0) {
			               	// 登録に失敗
			           		throw new CannotExecuteWithReasonException(
			           				VansmastRegistConstants.MSG_EXCLUSION_CHK,
			           				VansmastRegistConstants.MSG_UPDATE);
			            }
	        		}
	        	}
	    	}
        }
        
	    return result;
    }
    
    /**
     * エントリー情報のセット
     * @param UIVansShohinInfo 代表商品エンティティ
     * @param BirdUserInfo ログインユーザ情報
     * @param Timestamp    タイムスタンプ  
     * @param setFlg セットフラグ
     * @return UIVansShohinInfo 代表商品エンティティ
     */
    private void setUpdateData(UIVansShohinInfo uIVansShohinInfo,BirdUserInfo birdUserInfo,Timestamp currentTimestamp,boolean setFlg){
    	
    	NumericFormatter numFormatter = new NumericFormatter();
    	
		// 商品コードの5桁変換
		if(uIVansShohinInfo.getShoCdDai().length() < 5){
			uIVansShohinInfo.setShoCdDai( numFormatter.format(uIVansShohinInfo.getShoCdDai(),"00000") );
		}

	    uIVansShohinInfo.setFirstUser(birdUserInfo.getUserID());
	    uIVansShohinInfo.setFirstPgm(VansmastRegistConstants.SCREEN_ID);
	    uIVansShohinInfo.setFirstTmsp(currentTimestamp);
        uIVansShohinInfo.setLastUser(birdUserInfo.getUserID());
        uIVansShohinInfo.setLastPgm(VansmastRegistConstants.SCREEN_ID);
        if(setFlg){
        	uIVansShohinInfo.setLastTmsp(currentTimestamp);
        }
    }

	public UIVansShohinInfoDao getUIVansShohinInfoDao() {
		return uIVansShohinInfoDao;
	}

	public void setUIVansShohinInfoDao(UIVansShohinInfoDao vansShohinInfoDao) {
		uIVansShohinInfoDao = vansShohinInfoDao;
	}
}
