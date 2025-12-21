/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.vansmastregist.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.vansmastregist.action.VansmastRegistEditAction;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.common.VansmastRegistConstants;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.dao.UIShohinInfoDao;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.dto.VansmastRegistDto;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.logic.CheckInputDataLogic;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.logic.InputAreaAddLogic;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.logic.SetShohinNameKjLogic;

/**
 * バンズ倉庫別登録　編集画面アクションクラス
 * @author narita
 */
public class VansmastRegistEditActionImpl implements VansmastRegistEditAction {

	/** アクションID定義 */
    public static final String initialize_ACTION_ID 	= "BBS029A11";
    public static final String insert_ACTION_ID 		= "BBS029A12";
    public static final String enforcement_ACTION_ID 	= "BBS029A13";
    public static final String back_ACTION_ID 		= "BBS029A14";
        
    /** バンズ倉庫別登録DTO */
    private VansmastRegistDto vansmastRegistDto;
    
    /** バンズ倉庫別登録 入力内容チェックロジック */
    private CheckInputDataLogic checkInputDataLogic;
    
    /** バンズ倉庫別登録 入力欄追加ロジック */
    private InputAreaAddLogic inputAreaAddLogic;
    
    /** バンズ倉庫別登録 商品名のセットロジック */
    private SetShohinNameKjLogic setShohinNameKjLogic;
    	
    /** 入力欄追加対象の倉庫コード */
	private String addSelectSoko;
	
	/** 入力欄追加対象の管理元コード */
	private String addSelectKanri;
	
	/** バンズ倉庫別登録 注文商品情報取得DAO */
    private UIShohinInfoDao uIShohinInfoDao;
	
	/**
     * 初期処理
     * @return 画面遷移情報
     */
	public String initialize() {
        // セッションキー有効チェックを行い、有効でない場合は操作エラー画面へ遷移
        if (!getVansmastRegistDto().isValidSessionKey()) {
            return VansmastRegistConstants.VIEW_ID_ERR;
        }
                
        return null;
    }

    /**
     * 入力欄追加
     * @return 画面遷移情報
     */
    public String insert() {
        // セッションキー有効チェックを行い、有効でない場合は操作エラー画面へ遷移
        if (!getVansmastRegistDto().isValidSessionKey()) {
            return VansmastRegistConstants.VIEW_ID_ERR;
        }
        
    	List shohinList = getVansmastRegistDto().getUIVansShohinList();
    		
        // 入力欄追加処理を実行
        getVansmastRegistDto().setUIVansShohinList(getInputAreaAddLogic().execute(
        		shohinList,VansmastRegistConstants.ADD_MODE_LINK, addSelectSoko,addSelectKanri));
    	
        getVansmastRegistDto().setAddFlg(true);
        getVansmastRegistDto().setErrFlg(false);
        
        // 追加フォーカス名の設定
        String focusName = getInputAreaAddLogic().getAddFocusName(shohinList,addSelectKanri,addSelectSoko);
        getVansmastRegistDto().setAddFocusName(focusName);
        
	    // 編集画面を表示する。
	    return null;
    }
    
    /**
     * 確認
     * @return 画面遷移情報
     */
    public String enforcement() {
        // セッションキー有効チェックを行い、有効でない場合は操作エラー画面へ遷移
        if (!getVansmastRegistDto().isValidSessionKey()) {
            return VansmastRegistConstants.VIEW_ID_ERR;
        }
        
    	List shohinList = getVansmastRegistDto().getUIVansShohinList();
    	List nisugataList = getVansmastRegistDto().getUINisugataList();
    	
    	getVansmastRegistDto().setErrFlg(true);
    	getVansmastRegistDto().setAddFlg(false);
    	
        // 入力内容チェック処理を実行
        getVansmastRegistDto().setUIVansShohinList(
        		getCheckInputDataLogic().execute(shohinList,nisugataList));
                    	
        // 商品名の取得ロジック実行
        getSetShohinNameKjLogic().execute(getVansmastRegistDto().getUIVansShohinList());
        
        getVansmastRegistDto().setErrFlg(false);
        
    	// 確認画面を表示する。
    	return VansmastRegistConstants.VIEW_ID_CONFIRM;
	}
    
    /**
     * 戻る
     * @return 画面遷移情報
     */
    public String back() {
        // セッションキー有効チェックを行い、有効でない場合は操作エラー画面へ遷移
        if (!getVansmastRegistDto().isValidSessionKey()) {
            return VansmastRegistConstants.VIEW_ID_ERR;
        }
        
    	// 登録画面で編集・入力されたデータをクリアする。
        getVansmastRegistDto().clear();
    	
    	// 初期画面を表示する
    	return VansmastRegistConstants.VIEW_ID_SELECT;
	}

	public VansmastRegistDto getVansmastRegistDto() {
		return vansmastRegistDto;
	}

	public void setVansmastRegistDto(VansmastRegistDto vansmastRegistDto) {
		this.vansmastRegistDto = vansmastRegistDto;
	}

	public CheckInputDataLogic getCheckInputDataLogic() {
		return checkInputDataLogic;
	}

	public void setCheckInputDataLogic(CheckInputDataLogic checkInputDataLogic) {
		this.checkInputDataLogic = checkInputDataLogic;
	}

	public InputAreaAddLogic getInputAreaAddLogic() {
		return inputAreaAddLogic;
	}

	public void setInputAreaAddLogic(InputAreaAddLogic inputAreaAddLogic) {
		this.inputAreaAddLogic = inputAreaAddLogic;
	}

	public String getAddSelectSoko() {
		return addSelectSoko;
	}

	public void setAddSelectSoko(String addSelectSoko) {
		this.addSelectSoko = addSelectSoko;
	}

	public String getAddSelectKanri() {
		return addSelectKanri;
	}

	public void setAddSelectKanri(String addSelectKanri) {
		this.addSelectKanri = addSelectKanri;
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

