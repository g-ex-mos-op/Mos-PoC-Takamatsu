/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.vansmastregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.vansmastregist.logic.InputAreaAddLogic;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.logic.SetShohinNameKjLogic;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.common.VansmastRegistCommon;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.common.VansmastRegistConstants;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.dao.UIShohinInfoDao;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.entity.UIVansShohinInfo;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.entity.UIKanrimotoInfo;

import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 代表商品入力欄追加ロジック
 * @author narita
 */
public class InputAreaAddLogicImpl implements InputAreaAddLogic {

    /** ロジックID 定義 */
	public static final String LOGIC_ID = "BBS029L05";
	
	/** バンズ倉庫別登録 注文商品情報取得DAO */
    private UIShohinInfoDao uIShohinInfoDao;
    
    /** バンズ倉庫別登録 商品名のセットロジック */
    private SetShohinNameKjLogic setShohinNameKjLogic;

    /**
     * 代表商品入力欄の追加を行う
     * @param shohinList 管理元別商品リスト
     * @param addMode 追加区分
     * @param addSelectSoko 倉庫コード
     * @param addSelectKanri 管理元コード
     * @return shohinList 管理元別商品リスト
     */
    public List execute(List shohinList,int addMode,String addSelectSoko,String addSelectKanri) {
    
    	// 追加モードが、初期表示の場合
    	if(addMode == VansmastRegistConstants.ADD_MODE_INIT){
    		
    		
    		
    		return executeInit(shohinList);
    		
    	// 追加モードが、リンクの場合
    	}else if(addMode == VansmastRegistConstants.ADD_MODE_LINK){
    		
            if (VansmastRegistCommon.isNull(addSelectSoko)) {
                throw new NotNullException("バンズ倉庫別登録情報");
            }
            if (VansmastRegistCommon.isNull(addSelectKanri)) {
                throw new NotNullException("バンズ倉庫別登録情報");
            }
            
            // 商品名の取得ロジック実行
            getSetShohinNameKjLogic().execute(shohinList);
            
    		return executeLink(shohinList,addSelectSoko,addSelectKanri);
    	}
    	
    	return shohinList;
    }
    
    /**
     * 初期表示時の代表商品入力欄の追加を行う
     * @param shohinList 代表商品リスト 
     * @return shohinList 代表商品リスト  
     */
    private List executeInit(List shohinList) { 	
    	
    	// 代表商品リストの開始データを取得   	
    	UIVansShohinInfo uIVansShohinInfo = new UIVansShohinInfo();
    	
    	// 代表商品リストの初期設定
    	String sokoCd = "";
		
    	int shohinCount = 0;
    	
    	// 取引先別の処理
    	for(int y = 0;y < shohinList.size(); y++){
    		
    		// 取引先別の商品リストを取得
    		UIKanrimotoInfo kanriBetuList = (UIKanrimotoInfo)shohinList.get(y);
    		
	    	// 商品の処理
	    	for(int i = 0;i < kanriBetuList.getShohinList().size(); i++){
	    		
	    		// 商品リストを取得
	    		uIVansShohinInfo = (UIVansShohinInfo)kanriBetuList.getShohinList().get(i);
	    		
		    	//***********************************************
		    	//*　入力オブジェクトのフォーカスキー設定
		    	//***********************************************

	    		uIVansShohinInfo.setDispIdShoCdDai(
		       			VansmastRegistConstants.FOCUS_SHO_CD_DAI + 
		       			kanriBetuList.getKanriMotoCd() + 
		       			String.valueOf(i) );
		       	
		       	uIVansShohinInfo.setDispIdNisugata(
		       			VansmastRegistConstants.FOCUS_SHO_NISUGATA + 
		       			kanriBetuList.getKanriMotoCd() + 
		       			String.valueOf(i) );
		       	
	    		if(i == 0){
	    			// 最初のデータには、ファースト管理フラグを設定
	    			uIVansShohinInfo.setFirstKanriFlg(true);
	    		}else{
	    			uIVansShohinInfo.setFirstKanriFlg(false);
	    		}
	    		uIVansShohinInfo.setFirstSokoFlg(false);
	    		//初期表示時のデータは全て更新対象とする。
	    		uIVansShohinInfo.setDataMode(VansmastRegistConstants.DATA_MODE_UPDATE);
	    		
	    		// 倉庫の表示フラグの設定
	    		if(uIVansShohinInfo.getSokoCd().equals(sokoCd)){
	    			
	    		}else{
	    			// 開始フラグの設定
		    		uIVansShohinInfo.setFirstSokoFlg(true);
		    		sokoCd = uIVansShohinInfo.getSokoCd();
		    		
		    		shohinCount = this.getShohinCount( kanriBetuList.getShohinList(),sokoCd,i);
		    		// 商品数が０の場合、入力欄を３個追加する。
			       	if(shohinCount <= 0){
			       		// 最初の倉庫データの設定
			       		uIVansShohinInfo.setShohinCount(3);
			       		uIVansShohinInfo.setDispShohinCount(3);// 確認画面表示時は１行
			       		uIVansShohinInfo.setFirstKanriFlg(true);
			       		uIVansShohinInfo.setFirstSokoFlg(true);
			       		uIVansShohinInfo.setDataMode(VansmastRegistConstants.DATA_MODE_INSERT);
			       		// 商品データの追加
			       		kanriBetuList.getShohinList().add(i+1, getNewShohinData(uIVansShohinInfo,i+1));
			       		kanriBetuList.getShohinList().add(i+2, getNewShohinData(uIVansShohinInfo,i+2));
			       		i = i + 2;
			       	}else{
			       		// 商品数の設定
			       		uIVansShohinInfo.setShohinCount(shohinCount);
			       		uIVansShohinInfo.setDispShohinCount(shohinCount);
			       	}
	    		}
	    	}
    	}
	    
    	return shohinList;
    }
    
    /**
     * リンク押下時の代表商品入力欄の追加を行う
     * @param shohinList 代表商品リスト 
     * @param addSelectSoko 倉庫コード
     * @param addSelectKanri 管理元コード
     * @return shohinList 代表商品リスト  
     */
    private List executeLink(List shohinList, String addSelectSoko, String addSelectKanri) { 	
    	
    	boolean sokoMatchFlg = false;
    	int shoCount = 0;
    	int maxShoCount = 0;
    	int addShoCount = 0;
    	int hitCount = 0;
    	int shoNo = 0;
    	    	
    	// 取引先別の処理
    	for(int y = 0;y < shohinList.size(); y++){
    		
    		// 取引先別の商品リストを取得
    		UIKanrimotoInfo kanriBetuList = (UIKanrimotoInfo)shohinList.get(y);
    		
    		// 管理元コードの同一チェック
    		if(kanriBetuList.getKanriMotoCd().equals(addSelectKanri)){
		    	
    			// 商品の処理
		    	for(int i = 0;i < kanriBetuList.getShohinList().size(); i++){
		    		
		    		// 商品リストを取得
		    		UIVansShohinInfo uIVansShohinInfo = (UIVansShohinInfo)kanriBetuList.getShohinList().get(i);
		    		
		    		// 倉庫コードの同一チェック
		    		if(uIVansShohinInfo.getSokoCd().equals(addSelectSoko)){
		    			
		    			shoNo++;
		    					    			
		    			if(!sokoMatchFlg){
		    				// 最初のデータ位置
		    				hitCount = i;
		    				// 追加前の商品数を取得
		    				shoCount = uIVansShohinInfo.getShohinCount();
		    				
			    			// 商品数のカウントアップ
		    				maxShoCount = uIVansShohinInfo.getShohinCount() + 1;
			    			uIVansShohinInfo.setShohinCount(maxShoCount);
			    			uIVansShohinInfo.setDispShohinCount(maxShoCount);
		    			}
		    			
		    			sokoMatchFlg = true;
		    			
		    			// 追加データのカウント取得
		    			if(uIVansShohinInfo.getDataMode() == VansmastRegistConstants.DATA_MODE_INSERT){
		    				addShoCount++;
		    			}
		    		}
		    		
		    		// 倉庫別の最終データのチェック
		    		if(sokoMatchFlg == true && shoCount == shoNo){
		    			// 入力欄の追加
		    			kanriBetuList.getShohinList().add(i+1, getNewShohinData(uIVansShohinInfo,i+1) );
		    			addShoCount++;
		    			
		    			// 入力欄が5個以上の場合は、入力欄追加ボタンをテキスト表示にする
		    			if(addShoCount >= 5){
		    				UIVansShohinInfo hitUIVansShohinInfo = (UIVansShohinInfo)kanriBetuList.getShohinList().get(hitCount);
		    				hitUIVansShohinInfo.setAddLinkFlg(true);
		    			}
		    			
		    			return shohinList;
		    		}
		    	}
    		}
    	}
	    	    	
    	return shohinList;
    }  
    
	/**
     * 入力欄の作成を行う
     * @param shoEntity 代表商品エンティティ
     * @param pointer リストのポインタ
     * @return uIVansShohinInfo 新規代表商品エンティティ
     */
    private UIVansShohinInfo getNewShohinData(UIVansShohinInfo shoEntity, int pointer) {
    	
    	// 入力欄の新規作成
    	UIVansShohinInfo uIVansShohinInfo = new UIVansShohinInfo();
    	
    	// デフォルト値のセット
    	uIVansShohinInfo.setKanriMoto(shoEntity.getKanriMoto());
    	uIVansShohinInfo.setKanriMotoKj(shoEntity.getKanriMotoKj());
    	uIVansShohinInfo.setSokoCd(shoEntity.getSokoCd());
    	uIVansShohinInfo.setSokoNameKj(shoEntity.getSokoNameKj());
    	uIVansShohinInfo.setShoCdDai("");
    	uIVansShohinInfo.setShoNameKj("");
    	uIVansShohinInfo.setShoNisugata("");
    	uIVansShohinInfo.setDataMode(VansmastRegistConstants.DATA_MODE_INSERT);
   		uIVansShohinInfo.setFirstSokoFlg(false);
   		uIVansShohinInfo.setNoDispFlg(true);
   		
    	//***********************************************
    	//*　入力オブジェクトのフォーカスキー設定
    	//***********************************************

		uIVansShohinInfo.setDispIdShoCdDai(
       			VansmastRegistConstants.FOCUS_SHO_CD_DAI + 
       			shoEntity.getKanriMoto() + 
       			String.valueOf(pointer) );
       	
       	uIVansShohinInfo.setDispIdNisugata(
       			VansmastRegistConstants.FOCUS_SHO_NISUGATA + 
       			shoEntity.getKanriMoto() + 
       			String.valueOf(pointer) );
    	
    	return uIVansShohinInfo;
    }
    
	/**
     * 登録商品数の取得を行う
     * @param shohinList 代表商品リスト 
     * @return int 登録商品数
     */
    public int getShohinCount(List shohinList,String sokoCd,int firstCount ) {
    	
    	int shoCount = 0;
    	boolean hitFlg = false;
    	
    	// 代表商品リストの開始データを取得
    	UIVansShohinInfo uIVansShohinInfo = (UIVansShohinInfo)shohinList.get(0);
    	
    	for(int i = firstCount;i < shohinList.size(); i++){
            
    		uIVansShohinInfo = (UIVansShohinInfo)shohinList.get(i);
    		if(uIVansShohinInfo.getSokoCd().equals(sokoCd)){
        		// 商品コードが存在する場合
        		if( !VansmastRegistCommon.isNull(uIVansShohinInfo.getShoCdDai()) ){
    	   			shoCount++;
    	   			hitFlg = true;
        		}
    		}else if(hitFlg){
    			// 対象の倉庫データを発見後に、倉庫コードが一致しなくなった場合は処理を終了する。
    			return shoCount;
    		}
    	}  	
    	return shoCount;
    }
    
    public String getAddFocusName(List shohinList,String addSelectKanri,String addSelectSoko){
    	
    	String focusName = "";
    	
    	// 取引先別の処理
    	for(int y = 0;y < shohinList.size(); y++){
    		
    		// 取引先別の商品リストを取得
    		UIKanrimotoInfo kanriBetuList = (UIKanrimotoInfo)shohinList.get(y);
    		    		    		
    		// 管理元コードの同一チェック
    		if(kanriBetuList.getKanriMotoCd().equals(addSelectKanri)){
		    	
    			// 商品の処理
		    	for(int i = 0;i < kanriBetuList.getShohinList().size(); i++){
		    		
		    		// 商品リストを取得
		    		UIVansShohinInfo uIVansShohinInfo = (UIVansShohinInfo)kanriBetuList.getShohinList().get(i);
		    		
		    		// 管理元コードの同一チェック
		    		if(uIVansShohinInfo.getSokoCd().equals(addSelectSoko)){
		    			
		    			if(uIVansShohinInfo.getDataMode() == VansmastRegistConstants.DATA_MODE_INSERT){
		    				focusName = uIVansShohinInfo.getDispIdShoCdDai();
		    			}
		    		}
		    	}
    		}
    	}
    	return focusName;
    }
    
    public UIShohinInfoDao getUIShohinInfoDao() {
		return uIShohinInfoDao;
	}

	public void setUIShohinInfoDao(UIShohinInfoDao shohinInfoDao) {
		uIShohinInfoDao = shohinInfoDao;
	}

	public SetShohinNameKjLogic getSetShohinNameKjLogic() {
		return setShohinNameKjLogic;
	}

	public void setSetShohinNameKjLogic(SetShohinNameKjLogic setShohinNameKjLogic) {
		this.setShohinNameKjLogic = setShohinNameKjLogic;
	}
}
