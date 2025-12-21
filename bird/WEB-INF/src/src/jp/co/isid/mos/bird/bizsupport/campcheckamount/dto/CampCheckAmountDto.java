package jp.co.isid.mos.bird.bizsupport.campcheckamount.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizsupport.campcheckamount.common.CampCheckAmountConstants;

/**
 * キャンペーン設定数量確認DTO
 * 
 * @author xlee
 */
public class CampCheckAmountDto{

	/**
     * ユーザータイプ
     */
    private String userTypeCd;
    
    /**
     * システム日付
     */
    private String sysDate;
    
    /**
     *　ボタン名
     */
    private String buttonNm;
    
    /**
     * 対象キャンペーン
     */
    private String taishoCmpNo;
    
    /**
     * 対象条件
     */
    private String taishoCond;

    /**
     * 表示対象：店コード
     */   
    private String taishoMiseCd;
    
    /**
     * 表示対象：オーナーコード
     */   
    private String taishoOnerCd;
    
    /**
     * 表示対象：支部コード
     */   
    private String taishoSibuCd;
    
    /**
     * 表示対象：ブロックコード
     */   
    private String taishoBlockCd;
    
    /**
     * 対象キャンペーンリスト
     */    
    private List taishoCmpList;
    
    /**
     * 表示対象：支部リスト
     */    
    private List taishoSibuList;
	
    /**
     * 表示対象：ブロックリスト
     */    
    private List taishoBlockList;
    
    /**
     * 実行フラグ
     */    
    private String execFlg;
    
    /**
     * タブ区分
     */    
    private String tabKbn;
    
    /**
     * タブ表示区分
     */    
    private String tabViewFlg;
    
    /**
     * 結果リスト
     */    
    private List resultListTab1;
    
    /**
     * 結果リスト
     */    
    private List resultListTab2;
    
    /**
     * 結果リストサイズ
     */   
    private BigDecimal resultListSz;
    
    /**
     *　有償金額合計
     */   
    private BigDecimal yuShoAmtSum;
    
    /**
     * オーナーコード
     */
    private List onerCdList;
    
    /**
     * 実行メソッドを呼ぶ区分
     */
    private String callExecKbn;
    
    /**
     * 初期化する
     */
	public void clear(){
		setUserTypeCd(null);
		setSysDate(null);
		setButtonNm(CampCheckAmountConstants.BUTTON_NM_INIT);
		setTaishoCmpNo(null);
		setTaishoCond(null);
		setTaishoMiseCd(null);
		setTaishoOnerCd(null);
		setTaishoSibuCd(null);		
		setTaishoBlockCd(null);
		setTaishoCmpList(null);
		setTaishoSibuList(null);
		setTaishoBlockList(null);
		setExecFlg(null);
		setTabKbn(null);
		setTabViewFlg(null);
		setResultListTab1(null);
		setResultListTab2(null);
		setResultListSz(new BigDecimal(0));
		setYuShoAmtSum(new BigDecimal(0));
		setOnerCdList(null);
		setCallExecKbn(null);
	}
	
    /**
     * ユーザータイプを取得します。
     * @return ユーザータイプ
     */
    public String getUserTypeCd() {
        return userTypeCd;
    }

    /**
     * ユーザータイプをを設定します。
     * @param userTypeCd ユーザータイプ
     */
    public void setUserTypeCd(String userTypeCd) {
        this.userTypeCd = userTypeCd;
    }
    
    /**
     * システム日付を取得します。
     * @return システム日付
     */
    public String getSysDate() {
        return sysDate;
    }

    /**
     * システム日付をを設定します。
     * @param sysDate システム日付
     */
    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }
    
    /**
     * 会社コードを取得します。
     * @return 会社コード
     */
    public String getCompanyCd() {
		//企業コード固定 (株式会社モスフードサービス)
		return CampCheckAmountConstants.COMPANY_CD;
    }
    
    /**
     * ボタン名を取得します。
     * @return ボタン名
     */
    public String getButtonNm() {
		return buttonNm;
    }

    /**
     * ボタン名を設定します。
     * @param condCompanyCd ボタン名
     */
    public void setButtonNm(String buttonNm) {
        this.buttonNm = buttonNm;
    }
    
    /**
     * 対象キャンペーンを取得します。
     * @return 対象キャンペーン
     */
    public String getTaishoCmpNo() {
		return taishoCmpNo;
    }

    /**
     * 対象キャンペーンを設定します。
     * @param taishoCmp 対象キャンペーン
     */
    public void setTaishoCmpNo(String taishoCmpNo) {
        this.taishoCmpNo = taishoCmpNo;
    }
    
    /**
     * 対象条件を取得します。
     * @return 対象条件
     */
    public String getTaishoCond() {
		return taishoCond;
    }

    /**
     * 対象条件を設定します。
     * @param taishoCond 対象条件
     */
    public void setTaishoCond(String taishoCond) {
        this.taishoCond = taishoCond;
    }
    
    /**
     * 表示対象：店コードを取得します。
     * @return 表示対象：店コード
     */
    public String getTaishoMiseCd() {
		return taishoMiseCd;
    }

    /**
     * 表示対象：店コードを設定します。
     * @param taishoMiseCd 表示対象：店コード
     */
    public void setTaishoMiseCd(String taishoMiseCd) {
        this.taishoMiseCd = taishoMiseCd;
    }
    
    /**
     * 表示対象：オーナーを取得します。
     * @return 表示対象：オーナー
     */
    public String getTaishoOnerCd() {
		return taishoOnerCd;
    }

    /**
     * 表示対象：オーナーを設定します。
     * @param taishoMiseCd 表示対象：オーナー
     */
    public void setTaishoOnerCd(String taishoOnerCd) {
        this.taishoOnerCd = taishoOnerCd;
    }
    
    /**
     * 表示対象：支部コードを取得します。
     * @return 表示対象：支部コード
     */
    public String getTaishoSibuCd() {
		return taishoSibuCd;
    }

    /**
     * 表示対象：支部コードを設定します。
     * @param taishoMiseCd 表示対象：支部コード
     */
    public void setTaishoSibuCd(String taishoSibuCd) {
        this.taishoSibuCd = taishoSibuCd;
    }
    
    /**
     * 表示対象：ブロックコードを取得します。
     * @return 表示対象：ブロックコード
     */
    public String getTaishoBlockCd() {
		return taishoBlockCd;
    }

    /**
     * 表示対象：ブロックコードを設定します。
     * @param taishoBlockCd 表示対象：ブロックコード
     */
    public void setTaishoBlockCd(String taishoBlockCd) {
        this.taishoBlockCd = taishoBlockCd;
    }
    
    /**
     * 対象キャンペーンリストを取得します。
     * @return 対象キャンペーンリスト
     */
    public List getTaishoCmpList() {
		return taishoCmpList;
    }

    /**
     * 対象キャンペーンリストを設定します。
     * @param taishoCmpList 対象キャンペーンリスト
     */
    public void setTaishoCmpList(List taishoCmpList) {
        this.taishoCmpList = taishoCmpList;
    }
    
    /**
     * 対象キャンペーンリストサイズを設定します。
     * @param int 対象キャンペーンリストサイズ
     */ 
	public int getTaishoCmpListSz(){
		if(getTaishoCmpList() == null){
			return 0;
		}else{
			return getTaishoCmpList().size();
		}
	}
    
    /**
     * 対象条件リストを取得します。
     * @return 対象条件
     */
    public List getTaishoCondList() {
        List taishoCondList = new ArrayList();
    	taishoCondList.add(
    			new SelectItem(
    					CampCheckAmountConstants.TAISHO_COND_TENPO, CampCheckAmountConstants.TAISHO_COND_TENPO_NM));
    	taishoCondList.add(
    			new SelectItem(
    					CampCheckAmountConstants.TAISHO_COND_OWNER, CampCheckAmountConstants.TAISHO_COND_OWNER_NM));
    	taishoCondList.add(
    			new SelectItem(
    					CampCheckAmountConstants.TAISHO_COND_SIBU, CampCheckAmountConstants.TAISHO_COND_SIBU_NM));
    	return taishoCondList;
    }
    
    /**
     * 表示対象：支部リストを取得します。
     * @return 表示対象：支部リスト
     */
    public List getTaishoSibuList() {
		return taishoSibuList;
    }

    /**
     * 表示対象：支部リストを設定します。
     * @param taishoCmpList 対象キャンペーンリスト
     */
    public void setTaishoSibuList(List taishoSibuList) {
        this.taishoSibuList = taishoSibuList;
    }
    
    /**
     * 表示対象：ブロックリストを取得します。
     * @return 表示対象：ブロックリスト
     */
    public List getTaishoBlockList() {
		return taishoBlockList;
    }

    /**
     * 表示対象：ブロックリストを設定します。
     * @param taishoCmpList  表示対象：ブロックリスト
     */
    public void setTaishoBlockList(List taishoBlockList) {
        this.taishoBlockList = taishoBlockList;
    }
    
    /**
     * 実行フラグを取得します。
     * @return 実行フラグ
     */
    public String getExecFlg() {
		return execFlg;
    }

    /**
     * 実行フラグを設定します。
     * @param execFlg  実行フラグ
     */
    public void setExecFlg(String execFlg) {
        this.execFlg = execFlg;
    }
    
    /**
     * タブ区分を取得します。
     * @return タブ区分
     */
    public String getTabKbn() {
		return tabKbn;
    }

    /**
     * タブ区分を設定します。
     * @param tabKbn  タブ区分
     */
    public void setTabKbn(String tabKbn) {
        this.tabKbn = tabKbn;
    }
    
    /**
     * タブ表示フラグを取得します。
     * @return 表示フラグ
     */
    public String getTabViewFlg() {
		return tabViewFlg;
    }

    /**
     * タブ表示フラグを設定します。
     * @param tabViewFlg  タブ表示フラグ
     */
    public void setTabViewFlg(String tabViewFlg) {
        this.tabViewFlg = tabViewFlg;
    }
    
    /**
     * 結果リストを取得します。
     * @return 結果リスト
     */
    public List getResultListTab1() {
		return resultListTab1;
    }

    /**
     * 結果リストを設定します。
     * @param resultListTab1  結果リスト
     */
    public void setResultListTab1(List resultListTab1) {
        this.resultListTab1 = resultListTab1;
    }
    
    /**
     * 結果リストサイズを取得します。
     * @return 結果リストサイズ
     */
    public BigDecimal getResultListSz() {
		return resultListSz;
    }
    
    /**
     * 結果リストサイズを設定します。
     * @param resultListSz  結果リストサイズ
     */
    public void setResultListSz(BigDecimal resultListSz) {
        this.resultListSz = resultListSz;
    }
    
    /**
     * 結果リストを取得します。
     * @return 結果リスト
     */
    public List getResultListTab2() {
		return resultListTab2;
    }
    
    /**
     * 結果リストを設定します。
     * @param resultListTab2  結果リスト
     */
    public void setResultListTab2(List resultListTab2) {
        this.resultListTab2 = resultListTab2;
    }

    /**
     * 有償金額合計を取得します。
     * @return 有償金額合計
     */
    public BigDecimal getYuShoAmtSum() {
		return yuShoAmtSum;
    }

    /**
     *　有償金額合計を設定します。
     * @param resultListSz  有償金額合計
     */
    public void setYuShoAmtSum(BigDecimal yuShoAmtSum) {
        this.yuShoAmtSum = yuShoAmtSum;
    }
    
    /**
     * オーナーコードを取得します。
     * @return オーナーコード
     */
    public List getOnerCdList() {
		return onerCdList;
    }

    /**
     * オーナーコードを設定します。
     * @param onerCd  オーナーコード
     */
    public void setOnerCdList(List onerCdList) {
        this.onerCdList = onerCdList;
    }
    
    /**
     * 実行メソッドを呼ぶ区分を取得します。
     * @return 実行メソッドを呼ぶ区分
     */
    public String getCallExecKbn() {
		return callExecKbn;
    }

    /**
     * 実行メソッドを呼ぶ区分を設定します。
     * @param callExecKbn  実行メソッドを呼ぶ区分
     */
    public void setCallExecKbn(String callExecKbn) {
        this.callExecKbn = callExecKbn;
    }
}
