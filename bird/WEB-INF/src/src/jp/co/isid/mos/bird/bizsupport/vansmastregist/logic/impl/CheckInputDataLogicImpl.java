/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.vansmastregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.vansmastregist.dao.UIShohinInfoDao;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.logic.CheckInputDataLogic;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.logic.InputAreaAddLogic;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.entity.UIKanrimotoInfo;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.entity.UIVansShohinInfo;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.entity.UINisugataInfo;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.common.VansmastRegistConstants;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.common.VansmastRegistCommon;

import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.DuplicateDataException;
import jp.co.isid.mos.bird.framework.exception.InputConstraintException;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;

/**
 * 入力内容チェックロジック
 * @author narita
 */
public class CheckInputDataLogicImpl implements CheckInputDataLogic {

    /** ロジックID 定義 */
	public static final String LOGIC_ID = "BBS029L04";
    
	/** バンズ倉庫別登録 注文商品情報取得DAO */
    private UIShohinInfoDao uIShohinInfoDao;
    
    /** バンズ倉庫別登録 入力欄追加ロジック */
    private InputAreaAddLogic inputAreaAddLogic;
        
	/**
     * 登録内容のチェックを行う
     * @param inputList 代表商品リスト
     * @param nisugataList 荷姿リスト
     * @return inputList 代表商品リスト
     */
    public List execute(List inputList,List nisugataList) {
    	
        if (inputList == null) {
            throw new NotNullException("バンズ倉庫別登録情報");
        }
        
        // 入力必須チェック/妥当性チェック
        checkValidity(inputList,nisugataList);
                
        return inputList;
    }
    
    /**
     * 妥当性チェック
     * @param inputList 代表商品リスト
     * @param nisugataList 荷姿リスト
     */
    private void checkValidity(List inputList,List nisugataList) {

    	int shohinCount = 0;
    	String checkShoCd = "";
    	String checkSokoCd = "";
    	int checkFlg = -1;
    	
    	boolean firstNotingFlg = false;
    	
    	CodeVerifier codeVerifier = new CodeVerifier();
    	NumericFormatter numFormatter = new NumericFormatter();
    	
    	// 取引先別の処理
    	for(int y = 0;y < inputList.size(); y++){
    		
    		// 取引先別の商品リストを取得
    		UIKanrimotoInfo kanriBetuList = (UIKanrimotoInfo)inputList.get(y);
    		
    		// 商品の処理
		    for(int i = 0;i < kanriBetuList.getShohinList().size(); i++){
		    	
		       	UIVansShohinInfo uIVansShohinInfo = (UIVansShohinInfo)kanriBetuList.getShohinList().get(i);
		       			       				       	
		    	//***********************************************
		    	//*　入力値チェック処理
		    	//***********************************************
		       	
		       	// 代表商品コード レングスチェック及び半角数値チェック
		        if (VansmastRegistCommon.isLengthOver(uIVansShohinInfo.getShoCdDai(), 5) ||
		        		!codeVerifier.validate(uIVansShohinInfo.getShoCdDai()) ){
		            throw new InputConstraintException("商品コード", "半角数字", uIVansShohinInfo.getDispIdShoCdDai(), 0);
		        }
		        
		    	// 新規登録時に商品コードが入力されていて、荷姿が未入力の場合
		    	if (uIVansShohinInfo.getDataMode() == VansmastRegistConstants.DATA_MODE_INSERT && 
		    			!VansmastRegistCommon.isNull(uIVansShohinInfo.getShoCdDai()) && 
		    			uIVansShohinInfo.getShoNisugata().equals(VansmastRegistConstants.NISUGATA_CD_NOTING) ){
		    		throw new NotNullException("荷姿", uIVansShohinInfo.getDispIdNisugata(), 0);
		    	}
			    	
		    	// 削除チェックボックスがＯＦＦで、荷姿が未選択の場合
		    	if (uIVansShohinInfo.getDataMode() == VansmastRegistConstants.DATA_MODE_UPDATE && 
		    			uIVansShohinInfo.isDeleteFlg() == false && 
		    			uIVansShohinInfo.getShoNisugata().equals(VansmastRegistConstants.NISUGATA_CD_NOTING) ){
		    		throw new NotNullException("荷姿", uIVansShohinInfo.getDispIdNisugata(), 0);
		    	}
			    	
		    	// 商品コードの存在チェック
		    	if(!VansmastRegistCommon.isNull(uIVansShohinInfo.getShoCdDai())){
		    		
		    		// 代表商品コードを5桁変換してから、商品登録数を取得	        
			    	int count = getUIShohinInfoDao().getShohinCount(
			    			numFormatter.format(uIVansShohinInfo.getShoCdDai(),VansmastRegistConstants.SHO_CD_FORMAT));
			    	if(count <= 0){
			    		throw new NotExistException("商品コード", uIVansShohinInfo.getDispIdShoCdDai(), 0);
			    	}
		    	}
			    
		    	// 重複チェック
		    	if(uIVansShohinInfo.getDataMode() == VansmastRegistConstants.DATA_MODE_INSERT || 
		    			(uIVansShohinInfo.getDataMode() == VansmastRegistConstants.DATA_MODE_UPDATE && 
		    			uIVansShohinInfo.isDeleteFlg() == false) ){
		    		// チェック対象の商品コードを取得
		    		checkShoCd = uIVansShohinInfo.getShoCdDai();
		    		checkSokoCd = uIVansShohinInfo.getSokoCd();
		    		checkFlg = cheakShoCdDai(kanriBetuList.getShohinList(),checkShoCd,checkSokoCd,i);
			    	if(checkFlg >= 0){
			    		// 重複先のポインタでエラー発生箇所のフォーカス名を設定する。
			    		String focusName = VansmastRegistConstants.FOCUS_SHO_CD_DAI + 
				       			kanriBetuList.getKanriMotoCd() + 
				       			String.valueOf(checkFlg);
			    		
			    		throw new DuplicateDataException("商品コード", focusName, 0);
			    	}
		    	}
		    	
		    	//***********************************************
		    	//*　荷姿名称のセット処理
		    	//***********************************************
		    	
		    	String nisugataName = this.getNisugataName(uIVansShohinInfo.getShoNisugata(), nisugataList);
		    	uIVansShohinInfo.setShoNisugataKj(nisugataName);
		    	
		    	//***********************************************
		    	//*　確認画面での表示・非表示に伴う設定
		    	//***********************************************
		    	
		    	// 商品コードが入力されている入力欄のカウント取得
		    	if(uIVansShohinInfo.isFirstSokoFlg()){
		    		shohinCount = getInputAreaAddLogic().getShohinCount( kanriBetuList.getShohinList(),uIVansShohinInfo.getSokoCd(),i);
		    		if(shohinCount > 0){
		    			uIVansShohinInfo.setDispShohinCount(shohinCount);
		    		}else{
		    			// 全て未登録のため表示行数は１とする
		    			uIVansShohinInfo.setDispShohinCount(1);
		    		}
		    	}

		    	// 未設定データの設定
		    	if(uIVansShohinInfo.getDataMode() == VansmastRegistConstants.DATA_MODE_INSERT && 
		    		VansmastRegistCommon.isNull(uIVansShohinInfo.getShoCdDai()) ){
		    		// 未設定フラグを立てて、商品データをクリアする
		    		uIVansShohinInfo.setDispSokoInfoFlg(false);
		    		uIVansShohinInfo.setNotingFlg(true);
		    		uIVansShohinInfo.setShoCdDai("");
		    		uIVansShohinInfo.setShoNameKj("");
		    		uIVansShohinInfo.setShoNisugata("");
		    		uIVansShohinInfo.setShoNisugataKj("");
		    		
		    		// 確認画面での表示・非表示の設定
		    		if(uIVansShohinInfo.isFirstSokoFlg() && shohinCount == 0){
		    			// 商品未登録の場合は最初の商品データは表示
		    			uIVansShohinInfo.setNoDispFlg(false);
		    		}else{
		    			uIVansShohinInfo.setNoDispFlg(true);
		    		}
		    	}else{
		    		uIVansShohinInfo.setNotingFlg(false);
		    		uIVansShohinInfo.setNoDispFlg(false);
		    		uIVansShohinInfo.setDispSokoInfoFlg(false);
		    	}
		    	
		    	//***********************************************
		    	//*　一覧表示時に３行追加した場合限定の設定処理
		    	//***********************************************
		    	
		       	if(uIVansShohinInfo.isFirstSokoFlg()){
		       		firstNotingFlg = false;
		       	}
		    	
		    	// 新規データを処理対象とする
		    	if(uIVansShohinInfo.getDataMode() == VansmastRegistConstants.DATA_MODE_INSERT){
		    		// ファーストデータの未登録チェック
		    		if(uIVansShohinInfo.isFirstSokoFlg() &&
			    		VansmastRegistCommon.isNull(uIVansShohinInfo.getShoCdDai()) ){
				    	// ファーストデータに未登録をセット
				    	firstNotingFlg = true;
		    		}
		    		// ファーストデータが未登録の時にファースト以外のデータに商品が設定されている場合
			    	if(firstNotingFlg && 
				    	!uIVansShohinInfo.isFirstSokoFlg() &&
						!VansmastRegistCommon.isNull(uIVansShohinInfo.getShoCdDai()) ){
			    		
			    		// ３行追加時の倉庫情報表示フラグ
			    		uIVansShohinInfo.setDispSokoInfoFlg(true);
			    		// 商品数の設定
			    		uIVansShohinInfo.setDispShohinCount(shohinCount);
			    		// セカンドデータのに商品数をセットした後にフラグを初期化する
			    		firstNotingFlg = false;
			    	}
		    	}
    		}
        }
    }

    /**
     * 商品コードの重複チェック
     * @param shohinList 商品リスト
     * @param shoCdDai 商品コード
     * @param sokoCd 倉庫コード
     * @param pointer チェック対象ポインター
     * @return int　異常時:エラー対象ポインター／正常時:-1
     */
    private int cheakShoCdDai(List shohinList,String shoCdDai,String sokoCd, int pointer) {
		
		NumericFormatter numFormatter = new NumericFormatter();
		UIVansShohinInfo cheakShohinInfo = new UIVansShohinInfo();
		
		String shoCdDaiSaki = "";
		
		// 代表商品コードの5桁変換
		shoCdDai = numFormatter.format(shoCdDai,VansmastRegistConstants.SHO_CD_FORMAT);
		
		for(int i = pointer;i < shohinList.size(); i++){
	    	
			cheakShohinInfo = (UIVansShohinInfo)shohinList.get(i);
			
			if(	cheakShohinInfo.getSokoCd().equals(sokoCd)){
										        
				// 新規以外のデータのチェック
				if(cheakShohinInfo.getDataMode() != VansmastRegistConstants.DATA_MODE_INSERT){
					
					// 削除フラグがfalseで、同一商品がある場合
					if(	cheakShohinInfo.isDeleteFlg() == false &&
							cheakShohinInfo.getShoCdDai().equals(shoCdDai) && 
							pointer != i){
							
						return i;
					}
					
				// 新規データのチェック
				}else if(cheakShohinInfo.getDataMode() == VansmastRegistConstants.DATA_MODE_INSERT){
										
					// 同一商品がある場合
					if(	!VansmastRegistCommon.isNull(cheakShohinInfo.getShoCdDai()) && 
							pointer != i){
						
						// 代表商品コードの5桁変換
						shoCdDaiSaki = numFormatter.format(cheakShohinInfo.getShoCdDai(),VansmastRegistConstants.SHO_CD_FORMAT);
						
						if(	shoCdDaiSaki.equals(shoCdDai)){
								
							return i;
						}
					}
				}
			}
	    }
		
		return -1;
	}
	
    /**
     * 荷姿名称の取得
     * @param nisugataCd 荷姿コード
     * @param nisugataList 荷姿リスト
     * @return String 荷姿名称
     */
	private String getNisugataName(String nisugataCd , List nisugataList) {
		
		UINisugataInfo uINisugataInfo = new UINisugataInfo();
		
		for(int i = 0;i < nisugataList.size(); i++){
	    	
			uINisugataInfo = (UINisugataInfo)nisugataList.get(i);
			
			// 荷姿コードが一致した場合
			if(	uINisugataInfo.getShoNisugata().equals(nisugataCd) ){
				// 荷姿名称を返す
				return uINisugataInfo.getNisuName();
			}
	    }
		return null;
	}
	
	public UIShohinInfoDao getUIShohinInfoDao() {
		return uIShohinInfoDao;
	}

	public void setUIShohinInfoDao(UIShohinInfoDao shohinInfoDao) {
		uIShohinInfoDao = shohinInfoDao;
	}

	public InputAreaAddLogic getInputAreaAddLogic() {
		return inputAreaAddLogic;
	}

	public void setInputAreaAddLogic(InputAreaAddLogic inputAreaAddLogic) {
		this.inputAreaAddLogic = inputAreaAddLogic;
	}
}
