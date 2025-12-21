/*
 * 作成日: 2006/4/17
 */
package jp.co.isid.mos.bird.entry.longserviceregist.dto;

import java.util.List;
import java.util.HashMap;

import jp.co.isid.mos.bird.entry.common.dto.CommonDto;
import jp.co.isid.mos.bird.entry.longserviceregist.entity.UIEntryMst;
import jp.co.isid.mos.bird.entry.longserviceregist.common.LongserviceRegistConstants;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

/**
 * 永年勤続マスタ登録用DTO<br>
 * @author narita
 */
public class LongserviceRegistDto extends CommonDto {
    
/* 制御用 */
    /** セッション関連情報管理クラス */
    private MakeSessionKey mkSession = new MakeSessionKey();
    
    /** 編集モード */
    private HashMap editMode = new HashMap();
    
    /** 永年勤続マスタ情報リスト */
    private HashMap mstInfoList = new HashMap();

    /** 永年勤続日付情報リスト */
    private HashMap dateInfoList = new HashMap();
    
    /** 永年勤続マスタ情報リスト存在フラグ */
    private HashMap emptyMstInfoList = new HashMap();
    
/* 画面 */
    /** 選択ラジオボタンインデックス */
    private HashMap selectIndex = new HashMap();
    
    /** 永年勤続マスタ情報 */
    private HashMap uiEntryMst = new HashMap();
       
    /** リードオンリーフラグ */
    private HashMap readOnlyFlg = new HashMap();
    
    /** 編集・削除可能データ存在フラグ */
    private HashMap buttonFlg = new HashMap();
    
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
    public int getSelectIndex() {
        return Integer.parseInt((String)selectIndex.get(new Integer(getWindowId())));
    }
    public void setSelectIndex(int selectIndex) {
        this.selectIndex.put(new Integer(getWindowId()), String.valueOf(selectIndex));
    }
    public UIEntryMst getUiEntryMst() {
        return (UIEntryMst) uiEntryMst.get(new Integer(getWindowId()));
    }
    public void setUiEntryMst(UIEntryMst uiEntryMst) {
        this.uiEntryMst.put(new Integer(getWindowId()), uiEntryMst);
    }
    public String getEditModeName() {
        String modeName = "";
        if (LongserviceRegistConstants.EDIT_MODE_INSERT == getEditMode()) {
            modeName = LongserviceRegistConstants.PROCESS_INSERT;
        }
        else if (LongserviceRegistConstants.EDIT_MODE_UPDATE == getEditMode()) {
            modeName = LongserviceRegistConstants.PROCESS_UPDATE;
        }
        else if (LongserviceRegistConstants.EDIT_MODE_DELETE == getEditMode()) {
            modeName = LongserviceRegistConstants.PROCESS_DELETE;      
        }
        return modeName;       
    }
    public boolean isModeNew() {
        return LongserviceRegistConstants.EDIT_MODE_INSERT == getEditMode() ? true : false;
    }
    public boolean isModeDelete() {
        return LongserviceRegistConstants.EDIT_MODE_DELETE == getEditMode() ? true : false;
    }
    /* isModeDeleteをhiddenタグで使用する為の対処 */
    public void setModeDelete(boolean mode) {
    }
    public String getDeleteConfirmMsg() {
        return "この研修を削除します。現在までの申込の方のデータダウンロードは終了していますか？\n"
              + "まだダウンロードが終了していない場合はキャンセルボタンを押してください。\n"
              + "申込状況確認画面から該当研修を選択し、データをダウンロードしてください。";
    }
    public void setDeleteConfirmMsg(String msg) {
    }
	public int getEditMode() {
		return Integer.parseInt((String)editMode.get(new Integer(getWindowId())));
	}
	public void setEditMode(int editMode) {
		this.editMode.put(new Integer(getWindowId()), String.valueOf(editMode));
	}
	public List getDateInfoList() {
		return (List) dateInfoList.get(new Integer(getWindowId()));
	}
	public void setDateInfoList(List dateInfoList) {
		this.dateInfoList.put(new Integer(getWindowId()), dateInfoList);
	}
	public List getMstInfoList() {
		return (List) mstInfoList.get(new Integer(getWindowId()));
	}
	public void setMstInfoList(List mstInfoList) {
		this.mstInfoList.put(new Integer(getWindowId()), mstInfoList);
	}
	
    /**
     * 対象処理を取得する
     * @return 対象処理
     */
    public String getTargetProcess() {
        if(LongserviceRegistConstants.EDIT_MODE_INSERT == getEditMode()){
            return LongserviceRegistConstants.PROCESS_INSERT;
        }
        else if(LongserviceRegistConstants.EDIT_MODE_UPDATE == getEditMode()){
            return LongserviceRegistConstants.PROCESS_UPDATE;
        }
        else if(LongserviceRegistConstants.EDIT_MODE_DELETE == getEditMode()){
            return LongserviceRegistConstants.PROCESS_DELETE;
        }
        return LongserviceRegistConstants.EMPTY;
    }

    /**
     * 本部又はオーナーの入力可否を判定する
     * @return true:入力不可、false:入力可能
     */
    public boolean isReadOnly() {
        
    	String sys = getSysDate();
    	String honbu = getUiEntryMst().getHonbuEntryFrom();
    	String oner = getUiEntryMst().getOnerEntryFrom();

    	// 本部：申込開始日 > システム日付の場合
    	if(sys != null && honbu != null && oner != null){
    		if(Integer.parseInt(honbu) > Integer.parseInt(sys)){
    			// システム日付が申込日前なので入力可能とする
    			return false;
    		}else{
    			return true;
    		}
    	}
    	// データ不正の場合は入力可能とする。
    	return false;
    }
    
    /**
     * 削除可能の判断をする
     * @return true:削除可能、false:削除不可
     */
    public boolean isDeleteCheck() {
        
    	String sys = getSysDate();
    	String honbu = getUiEntryMst().getHonbuEntryFrom();
    	String oner = getUiEntryMst().getOnerEntryFrom();

    	// 本部：申込開始日 ≦ システム日付の場合
    	if(sys != null && honbu != null && oner != null){
    		if(Integer.parseInt(honbu) <= Integer.parseInt(sys)){
    			// システム日付が申込日に達しているので削除不可とする。
    			return false;
    		}else{
    			return true;
    		}
    	}
    	// データ不正の場合は削除不可とする。
    	return false;
    }

    /**
     * システム日付の年度を取得する
     * @return システム日付の年度
     */
    public String getSysNendo() {
        try{
            return DateManager.getCurrentYear(getSysDate());
        }catch(Exception e){
            throw new FtlSystemException("年度算出ロジックでエラーが発生しました。");
        }
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
     * 永年勤続マスタ登録情報を削除する
     */
    public void clear() {
        //setSelectIndex(0);
        setUiEntryMst(new UIEntryMst());
        setMstInfoList(null);
        setDateInfoList(null);
        setReadOnlyFlg(false);
    }

	public boolean isEmptyMstInfoList() {
		return Boolean.valueOf((String)emptyMstInfoList.get(new Integer(getWindowId()))).booleanValue();
	}

	public void setEmptyMstInfoList(boolean emptyMstInfoList) {
		this.emptyMstInfoList.put(new Integer(getWindowId()), String.valueOf(emptyMstInfoList));
	}

	public boolean isReadOnlyFlg() {
		return Boolean.valueOf((String)readOnlyFlg.get(new Integer(getWindowId()))).booleanValue();
	}

	public void setReadOnlyFlg(boolean readOnlyFlg) {
		this.readOnlyFlg.put(new Integer(getWindowId()), String.valueOf(readOnlyFlg));
	}

	public boolean isButtonFlg() {
		return Boolean.valueOf((String)buttonFlg.get(new Integer(getWindowId()))).booleanValue();
	}

	public void setButtonFlg(boolean buttonFlg) {
		this.buttonFlg.put(new Integer(getWindowId()), String.valueOf(buttonFlg));
	}
}