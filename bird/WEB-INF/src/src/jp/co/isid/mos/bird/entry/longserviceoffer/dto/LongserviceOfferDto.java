/*
 * 作成日: 2006/4/17
 */
package jp.co.isid.mos.bird.entry.longserviceoffer.dto;

import java.util.List;
import java.util.HashMap;

import jp.co.isid.mos.bird.entry.common.dto.CommonDto;
import jp.co.isid.mos.bird.entry.longserviceoffer.entity.UIOfferMst;
import jp.co.isid.mos.bird.entry.longserviceoffer.entity.UIOfferOner;
import jp.co.isid.mos.bird.entry.longserviceoffer.common.LongserviceOfferConstants;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

/**
 * 永年勤続マスタ登録用DTO<br>
 * @author narita
 */
public class LongserviceOfferDto extends CommonDto {
    
/* 制御用 */
    /** セッション関連情報管理クラス */
    private MakeSessionKey mkSession = new MakeSessionKey();
    
    /** 編集モード */
    private HashMap editMode = new HashMap();

    /** 最大ソート番号 */
    private HashMap maxSeqNo = new HashMap();
    
    /** スタッフ検索ソート番号 */
    private HashMap staffSelectNo = new HashMap();
    
    /** 申込責任者情報カウント */
    private HashMap mstCount = new HashMap();
    
    /** 申請者情報カウント */
    private HashMap entryCount = new HashMap();
    
    /** 追加ボタン有効フラグ */
    private HashMap insertBtnFlg = new HashMap();
    
    /** 編集可能フラグ */
    private HashMap editFlg = new HashMap();
        
    /** 申請者情報全クリアフラグ */
    private HashMap entryCleaFlg = new HashMap();
    
    /** エラー発生フラグ */
    private HashMap errFlg = new HashMap();
    
/* データ */
    /** 永年勤続マスタ情報リスト */
    private HashMap uIOfferMst = new HashMap();

    /** 永年勤続日付情報リスト */
    private HashMap uIOfferEntryList = new HashMap();
    
    /** 店舗リスト */
    private HashMap uIOfferMiseList = new HashMap();
    
    /** オーナー情報 */
    private HashMap uIOnerInfo = new HashMap();
    
/* キー情報 */
    /** エントリーコード */
    private String entryCd = LongserviceOfferConstants.ENTRY_CD;
    /** エントリー年 */
    private String entryYear;
    /** エントリー回 */
    private String entryKai;
    /** 会社コード */
    private String companyCd;
    /** オーナーコード */
    private String onerCd;
    /** 宛先区分 */
    private String atesakiKbn = LongserviceOfferConstants.ATESAKI_KBN;   
    /** 現在の申請者件数（ 最大ソート番号をセット )*/
    private HashMap nowEntryCount = new HashMap(); 
    /** 選択されたスタッフボタン番号 */
    private HashMap staffBottonNo = new HashMap();    
    
    /**
     * 店舗リストのサイズを設定します。
     * @param  店舗リストのサイズ
     */   
	public int getMiseListSize(){
		if(getUIOfferMiseList() == null){
			return 0;
		}else{
			return getUIOfferMiseList().size();
		}
	}
	
    
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

	public int getStaffSelectNo() {
		return Integer.parseInt((String)staffSelectNo.get(new Integer(getWindowId())));
	}
	public void setStaffSelectNo(int staffSelectNo) {
		this.staffSelectNo.put(new Integer(getWindowId()), String.valueOf(staffSelectNo));
	}
	
	public int getMstCount() {
		return Integer.parseInt((String)mstCount.get(new Integer(getWindowId())));
	}
	public void setMstCount(int mstCount) {
		this.mstCount.put(new Integer(getWindowId()), String.valueOf(mstCount));
	}
	
	public int getEntryCount() {
		return Integer.parseInt((String)entryCount.get(new Integer(getWindowId())));
	}
	public void setEntryCount(int entryCount) {
		this.entryCount.put(new Integer(getWindowId()), String.valueOf(entryCount));
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
    	this.setEntryYear(null);
    	this.setEntryKai(null);
    	this.setCompanyCd(null);
    	this.setOnerCd(null);
    	this.setEditMode(0);
    	this.setMaxSeqNo("00");
    	this.setMstCount(0);
    	this.setEntryCount(0);
    	this.setInsertBtnFlg(false);
    	this.setInsertBtnFlg(false);
    	this.setEntryCleaFlg(false);
    	this.setErrFlg(false);	
    	this.setUIOfferMst(new UIOfferMst());
    	this.setUIOfferEntryList(null);  	
    	this.setUIOfferMiseList(null);  	
    	this.setUIOnerInfo(new UIOfferOner());
        this.setNowEntryCount("00");
        this.setStaffBottonNo("0");
    }
	public UIOfferMst getUIOfferMst() {
		return (UIOfferMst) uIOfferMst.get(new Integer(getWindowId()));
	}

	public void setUIOfferMst(UIOfferMst offerMst) {
		this.uIOfferMst.put(new Integer(getWindowId()), offerMst);
	}

	public List getUIOfferMiseList() {
		return (List) uIOfferMiseList.get(new Integer(getWindowId()));
	}

	public void setUIOfferMiseList(List offerMiseList) {
		this.uIOfferMiseList.put(new Integer(getWindowId()), offerMiseList);
	}

	public String getAtesakiKbn() {
		return atesakiKbn;
	}

	public void setAtesakiKbn(String atesakiKbn) {
		this.atesakiKbn = atesakiKbn;
	}

	public String getCompanyCd() {
		return companyCd;
	}

	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}

	public String getEntryCd() {
		return entryCd;
	}

	public void setEntryCd(String entryCd) {
		this.entryCd = entryCd;
	}

	public String getEntryKai() {
		return entryKai;
	}

	public void setEntryKai(String entryKai) {
		this.entryKai = entryKai;
	}

	public String getEntryYear() {
		return entryYear;
	}

	public void setEntryYear(String entryYear) {
		this.entryYear = entryYear;
	}

	public String getOnerCd() {
		return onerCd;
	}

	public void setOnerCd(String onerCd) {
		this.onerCd = onerCd;
	}

	public List getUIOfferEntryList() {
		return (List) uIOfferEntryList.get(new Integer(getWindowId()));
	}

	public void setUIOfferEntryList(List offerEntryList) {
		this.uIOfferEntryList.put(new Integer(getWindowId()), offerEntryList);
	}

	public UIOfferOner getUIOnerInfo() {
		return (UIOfferOner) uIOnerInfo.get(new Integer(getWindowId()));
	}

	public void setUIOnerInfo(UIOfferOner onerInfo) {
		this.uIOnerInfo.put(new Integer(getWindowId()), onerInfo);
	}

	public String getMaxSeqNo() {
		return (String)maxSeqNo.get(new Integer(getWindowId()));
	}

	public void setMaxSeqNo(String maxSeqNo) {
		this.maxSeqNo.put(new Integer(getWindowId()), maxSeqNo);
	}
	
	public boolean isInsertBtnFlg() {
		return Boolean.valueOf((String)insertBtnFlg.get(new Integer(getWindowId()))).booleanValue();
	}
	
	public void setInsertBtnFlg(boolean insertBtnFlg) {
		this.insertBtnFlg.put(new Integer(getWindowId()), String.valueOf(insertBtnFlg));
	}

	public boolean isEditFlg() {
		return Boolean.valueOf((String)editFlg.get(new Integer(getWindowId()))).booleanValue();
	}
	public void setEditFlg(boolean editFlg) {
		this.editFlg.put(new Integer(getWindowId()), String.valueOf(editFlg));
	}
	public boolean isEntryCleaFlg() {
		return Boolean.valueOf((String)entryCleaFlg.get(new Integer(getWindowId()))).booleanValue();
	}
	public void setEntryCleaFlg(boolean entryCleaFlg) {
		this.entryCleaFlg.put(new Integer(getWindowId()), String.valueOf(entryCleaFlg));
	}
	
	public boolean isErrFlg() {
		return Boolean.valueOf((String)errFlg.get(new Integer(getWindowId()))).booleanValue();
	}
	public void setErrFlg(boolean errFlg) {
		this.errFlg.put(new Integer(getWindowId()), String.valueOf(errFlg));
	}
    
    public String getNowEntryCount() {
        return (String)nowEntryCount.get(new Integer(getWindowId()));
    }
    public void setNowEntryCount(String nowEntryCount) {
        this.nowEntryCount.put(new Integer(getWindowId()), nowEntryCount);
    }
    
    public String getStaffBottonNo() {
        return (String)staffBottonNo.get(new Integer(getWindowId()));
    }
    public void setStaffBottonNo(String staffBottonNo) {
        this.staffBottonNo.put(new Integer(getWindowId()), staffBottonNo);
    }
}