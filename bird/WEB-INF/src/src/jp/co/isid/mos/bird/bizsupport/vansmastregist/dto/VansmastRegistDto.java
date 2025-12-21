/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.vansmastregist.dto;

import java.util.List;
import java.util.HashMap;

import jp.co.isid.mos.bird.entry.common.dto.CommonDto;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

/**
 * バンズ倉庫別登録用DTO<br>
 * @author narita
 */
public class VansmastRegistDto extends CommonDto {
    
/* 制御用 */
    /** セッション関連情報管理クラス */
    private MakeSessionKey mkSession = new MakeSessionKey();
    
    /** 編集モード */
    private HashMap editMode = new HashMap();
        
    /** エラー発生フラグ */
    private HashMap errFlg = new HashMap();
    
    /** 追加ボタン押下フラグ */
    private HashMap addFlg = new HashMap();
    
    /** 追加ボタン押下後のフォーカス */
    private HashMap addFocusName = new HashMap();
    
/* データ */
    /** 代表商品リスト */
    private HashMap uIVansShohinList = new HashMap();
    
    /** 対象倉庫情報リスト */
    private List uIVansSokoList;
    
    /** 荷姿リスト */
    private List uINisugataList;
    
/* キー情報 */
    /** 管理元コード */
    private HashMap kanriMoto = new HashMap();
    /** 管理元名称 */
    private HashMap kanriMotoKj = new HashMap();
    
    /**
     * セッション関連情報を取得する
     * @return セッション関連情報 
     */
    public MakeSessionKey getMkSession() {
        return mkSession;
    }

    /**
     * セッション関連情報を設定する
     * @param mkSession セッション関連情報
     */
    public void setMkSession(MakeSessionKey mkSession) {
        this.mkSession = mkSession;
    }
    
	public int getEditMode() {
		return Integer.parseInt((String)editMode.get(new Integer(getWindowId())));
	}
	public void setEditMode(int editMode) {
		this.editMode.put(new Integer(getWindowId()), String.valueOf(editMode));
	}

    /**
     * セッションキー有効チェックを行う
     * @return true：有効、false：無効
     */
    public boolean isValidSessionKey(){
        return mkSession.isValidSessionKey(
            getNowSessionKey(), getSessionKey());
    }

    /** 
     * バンズ倉庫別登録情報をクリアする
     */
    public void clear() {
    	//this.setKanriMoto(null);
    	//this.setKanriMotoKj(null);
    	this.setEditMode(0);
    	this.setErrFlg(false);	
    	//this.setUIVansSokoList(null);
    	this.setUIVansShohinList(null);
    	//this.setUINisugataList(null);
    }
    
    public String getKanriMoto() {
		return (String)kanriMoto.get(new Integer(getWindowId()));
	}
	public void setKanriMoto(String kanriMoto) {
		this.kanriMoto.put(new Integer(getWindowId()), kanriMoto);
	}
	
    public String getKanriMotoKj() {
		return (String)kanriMotoKj.get(new Integer(getWindowId()));
	}
	public void setKanriMotoKj(String kanriMotoKj) {
		this.kanriMotoKj.put(new Integer(getWindowId()), kanriMotoKj);
	}
	
	public String getAddFocusName() {
		return (String)addFocusName.get(new Integer(getWindowId()));
	}
	public void setAddFocusName(String addFocusName) {
		this.addFocusName.put(new Integer(getWindowId()), addFocusName);
	}
		
	public boolean isErrFlg() {
		return Boolean.valueOf((String)errFlg.get(new Integer(getWindowId()))).booleanValue();
	}
	public void setErrFlg(boolean errFlg) {
		this.errFlg.put(new Integer(getWindowId()), String.valueOf(errFlg));
	}
	
	public boolean isAddFlg() {
		return Boolean.valueOf((String)addFlg.get(new Integer(getWindowId()))).booleanValue();
	}
	public void setAddFlg(boolean addFlg) {
		this.addFlg.put(new Integer(getWindowId()), String.valueOf(addFlg));
	}

    /** 
     * 代表商品リスト
     */
	public List getUIVansShohinList() {
		return (List) uIVansShohinList.get(new Integer(getWindowId()));
	}
	public void setUIVansShohinList(List vansShohinList) {
		this.uIVansShohinList.put(new Integer(getWindowId()), vansShohinList);
	}

	public List getUINisugataList() {
		return uINisugataList;
	}
	public void setUINisugataList(List nisugataList) {
		uINisugataList = nisugataList;
	}

	public List getUIVansSokoList() {
		return uIVansSokoList;
	}
	public void setUIVansSokoList(List vansSokoList) {
		uIVansSokoList = vansSokoList;
	}
}