/**
 * 2008/06/20
 */
package jp.co.isid.mos.bird.storemanage.claimref.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.entity.CodCompany;
import jp.co.isid.mos.bird.common.entity.CodHyojiTaisho;
import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.storemanage.claimref.code.HyojiNaiyo;
import jp.co.isid.mos.bird.storemanage.claimref.entity.UIVoiceInfo;

/**
 * 個店管理：『お客様の声』
 * リクエストDTO
 * 
 * @author xkinu
 *
 */
public class RequestDto {
	/**
	 * 自画面SessionDTO
	 */
	private SessionDto selfSessionDto;
	/**
	 * リクエスト対象VIEWID
	 */
	private String viewId;
	/**
	 * リクエスト対象ブラウザのウィンドウID
	 */
	private boolean newWindowFlg;
	/**
	 * リクエスト対象ブラウザのウィンドウID
	 */
	private int windowId;
    /**
     * 対象年月プルダウンリスト情報保持リスト
     */
    private List listTaishoNengetu = new ArrayList(0);
    /**
     * 対象条件プルダウンリスト
     */
    private List listTaishoJoken= new ArrayList(0);
    /**
     * 表示対象プルダウンリスト
     */
    private List listsHyojiTaisho= new ArrayList(0);
    private List listBlock = new ArrayList(0);
    /**
     * 検索結果リスト
     */
    private List listSearchData = new ArrayList(0);
    /**
     * 会社コード
     */ 
    private String companyCd;
    /**
     * 表示内容
     */
    private String hyojiNaiyo;
    /**
     * 中分類コード
     */
    private String bnrM;
    /**
     * 対象年月
     */ 
    private String taishoNengetu;
    /**
     * 対象条件
     */ 
    private String taishoJoken;
    /**
     * 表示対象
     */ 
    private String hyojiTaisho;
    private String blockCd;
    /**
     * 条件項目設定完了フラグ
     */
    private boolean conditionDataComp=false;
    
    /**
     * 個店ポータル画面遷移時用
     * 店舗コード
     */
    private String miseCd;
    /**
     * 詳細画面遷移時用
     * 管理番号
     */
    private String kanriNo;
    
    private UIVoiceInfo viewInfo;
	/**
	 * @return companyCd を戻します。
	 */
	public String getCompanyCd() {
		return companyCd;
	}
	/**
	 * @param companyCd 設定する companyCd。
	 */
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}
	/**
	 * 表示内容プルダウンリスト用データ取得処理
	 * @return
	 */
	public List getListHyojiNaiyo() {
		return HyojiNaiyo.getPullDownList();
	}
	/**
	 * @return hyojiTaisho を戻します。
	 */
	public String getHyojiTaisho() {
		return hyojiTaisho;
	}
	/**
	 * @param hyojiTaisho 設定する hyojiTaisho。
	 */
	public void setHyojiTaisho(String hyojiTaisho) {
		this.hyojiTaisho = hyojiTaisho;
	}
	/**
	 * @return listSearchData を戻します。
	 */
	public List getListSearchData() {
		return listSearchData;
	}
	/**
	 * @param listSearchData 設定する listSearchData。
	 */
	public void setListSearchData(List listSearchData) {
		this.listSearchData = listSearchData;
	}
	/**
	 * @return listsHyojiTaisho を戻します。
	 */
	public List getListsHyojiTaisho() {
		return listsHyojiTaisho;
	}
	/**
	 * @param listsHyojiTaisho 設定する listsHyojiTaisho。
	 */
	public void setListsHyojiTaisho(List listsHyojiTaisho) {
		this.listsHyojiTaisho = listsHyojiTaisho;
	}
	/**
	 * 表示対象名称
	 * @return hyojiTaishoName を戻します。
	 */
	public String getHyojiTaishoName() {
		for(int i=0; i<getListsHyojiTaisho().size(); i++) {
			CodHyojiTaisho entity = (CodHyojiTaisho)getListsHyojiTaisho().get(i);
			if(entity.getHyojitaishoCd() != null && hyojiTaisho.trim().equals(entity.getHyojitaishoCd().trim())) {
				return entity.getHyojitaishoName();
			}
		}
		return "";
	}
    /**
     * 検索データ存在判断処理
     * 
     * @param windowId
     * @return
     */
    public boolean isExistSearchedData() {
		return (getListSearchData() != null && getListSearchData().size()>0);
    }
    /**
     * 検索実行判断処理
     * 
     * @param windowId
     * @return
     */
    public boolean isMustSearch() {
		if(getSelfSessionDto() == null) {
			return false;
		}
		return getSelfSessionDto().isMustSearch(getWindowId());
    }
    /**
     * 対象店舗数取得処理
     * 
     * @param windowId
     * @return
     */
    public BigDecimal getTaishoTenpoCnt() {
    	List listSearchData = getListSearchData();
        if (listSearchData == null || listSearchData.isEmpty()) { 
            return new BigDecimal("0");
        }
   		return new BigDecimal(String.valueOf(listSearchData.size()));
    }
    /**
     * 検索済み判断処理
     * 
     * @param windowId
     * @return
     */
    public boolean isSearched() {
		if(getSelfSessionDto() == null) {
			return false;
		}
		return getSelfSessionDto().isSearched(getWindowId());
    }
	/**
	 * 対象条件プルダウンリスト用リスト取得処理
	 * 
	 * @return listTaishoJoken を戻します。
	 */
	public List getListTaishoJoken() {
		return listTaishoJoken;
	}
	/**
	 * 対象条件プルダウンリスト用リスト設定処理
	 * 
	 * @param listTaishoJoken 設定する listTaishoJoken。
	 */
	public void setListTaishoJoken(List listTaishoJoken) {
		this.listTaishoJoken = listTaishoJoken;
	}
	/**
	 * @return newWindowFlg を戻します。
	 */
	public boolean isNewWindowFlg() {
		return newWindowFlg;
	}
	/**
	 * @param newWindowFlg 設定する newWindowFlg。
	 */
	public void setNewWindowFlg(boolean newWindowFlg) {
		this.newWindowFlg = newWindowFlg;
	}
	/**
	 * @return selfSessionDto を戻します。
	 */
	public SessionDto getSelfSessionDto() {
		return selfSessionDto;
	}
	/**
	 * @param selfSessionDto 設定する selfSessionDto。
	 */
	public void setSelfSessionDto(SessionDto selfSessionDto) {
		this.selfSessionDto = selfSessionDto;
	}
	/**
	 * @return taishoJoken を戻します。
	 */
	public String getTaishoJoken() {
		return taishoJoken;
	}
	/**
	 * @param taishoJoken 設定する taishoJoken。
	 */
	public void setTaishoJoken(String taishoJoken) {
		this.taishoJoken = taishoJoken;
	}
	/**
	 * @return viewId を戻します。
	 */
	public String getViewId() {
		return viewId;
	}
	/**
	 * @param viewId 設定する viewId。
	 */
	public void setViewId(String viewId) {
		this.viewId = viewId;
	}
	/**
	 * @return windowId を戻します。
	 */
	public int getWindowId() {
		return windowId;
	}
	/**
	 * @param windowId 設定する windowId。
	 */
	public void setWindowId(int windowId) {
		this.windowId = windowId;
	}
	/**
	 * 
	 * 条件項目の表示プルダウンリストを設定します
	 * 
	 * 【事前条件】：(ウィンドウID・会社コード・年度)が設定されていること
	 */
	public void setPullDownData(SessionDto selfSessionDto) {
		//1．対象日プルダウンリストを設定します。
		setListTaishoNengetu(
				selfSessionDto.getListTaishoNengetu());
		setListTaishoJoken(
				selfSessionDto.getListTaishoJoken(getCompanyCd()));
		//2．表示対象(支部)プルダウンリストを設定します。
		setListsHyojiTaisho(
				selfSessionDto.getListsHyojiTaisho(getCompanyCd()));
		setListBlock(selfSessionDto.getListBlock());
		
		//6.条件項目設定完了フラグをtrueに設定します。
		setConditionDataComp(true);
	}
	/**
	 * 
	 * 条件初期値を設定します
	 */
	public void setInitialazeData(SessionDto selfSessionDto) {
        //1.デフォルト会社コードを設定します。
		CodCompany com = (CodCompany)selfSessionDto.getListCompany().get(0);
		setCompanyCd(com.getCompanyCd());
		
		//2.条件項目の表示プルダウンリストを設定します
		setPullDownData(selfSessionDto);
		
		//3．デフォルト対象条件を設定します。
		String taishoJoken = (String)((SelectItem)(getListTaishoJoken().get(0))).getValue();
		setTaishoJoken(taishoJoken);
		if(UserType.TENPO.equals(selfSessionDto.getUserTypeCd())) {
			UILists uiHyojiTaisho = (UILists)getListsHyojiTaisho().get(0);
			CodHyojiTaisho eHyojiTaisho = (CodHyojiTaisho)uiHyojiTaisho.getListData().get(0);
			setHyojiTaisho(eHyojiTaisho.getHyojitaishoCd());
		}
		//4.デフォルトブロックコードを空で設定します。
		setBlockCd("");
		
		//5．デフォルト対象年月
		setTaishoNengetu((String)((SelectItem)(getListTaishoNengetu().get(0))).getValue());
		
	}
	/**
	 * @return taishoNengetu を戻します。
	 */
	public String getTaishoNengetu() {
		return taishoNengetu;
	}
	/**
	 * @param taishoNengetu 設定する taishoNengetu。
	 */
	public void setTaishoNengetu(String taishoNengetu) {
		this.taishoNengetu = taishoNengetu;
	}
	/**
	 * 表示内容取得処理
	 * 
	 * @return hyojiNaiyo を戻します。
	 */
	public String getHyojiNaiyo() {
		return hyojiNaiyo;
	}
	/**
	 * 表示内容設定処理
	 * 
	 * @param hyojiNaiyo 設定する hyojiNaiyo。
	 */
	public void setHyojiNaiyo(String hyojiNaiyo) {
		this.hyojiNaiyo = hyojiNaiyo;
	}
	/**
	 * 表示内容名称取得処理
	 * 
	 * @return
	 */
	public String getHyojiNaiyoName() {
		return HyojiNaiyo.getName(getHyojiNaiyo());
	}
	/**
	 * @return bnrM を戻します。
	 */
	public String getBnrM() {
		return bnrM;
	}
	/**
	 * @param bnrM 設定する bnrM。
	 */
	public void setBnrM(String bnrM) {
		this.bnrM = bnrM;
	}
	/**
	 * @return conditionDataComp を戻します。
	 */
	public boolean isConditionDataComp() {
		return conditionDataComp;
	}
	/**
	 * @param conditionDataComp 設定する conditionDataComp。
	 */
	public void setConditionDataComp(boolean conditionDataComp) {
		this.conditionDataComp = conditionDataComp;
	}
	/**
	 * @return listTaishoNengetu を戻します。
	 */
	public List getListTaishoNengetu() {
		return listTaishoNengetu;
	}
	/**
	 * @param listTaishoNengetu 設定する listTaishoNengetu。
	 */
	public void setListTaishoNengetu(List listTaishoNengetu) {
		this.listTaishoNengetu = listTaishoNengetu;
	}
	/**
	 * 表示内容が『お褒め』判別処理
	 * 
	 * @return
	 */
	public boolean isOhome() {
		if(HyojiNaiyo.CODE_OHOME.equals(getHyojiNaiyo())) {
			return true;
		}
		return false;
	}
	/**
	 * @return listBlock を戻します。
	 */
	public List getListBlock() {
		return listBlock;
	}
	/**
	 * @param listBlock 設定する listBlock。
	 */
	public void setListBlock(List listBlock) {
		this.listBlock = listBlock;
	}
	/**
	 * @return blockCd を戻します。
	 */
	public String getBlockCd() {
		return blockCd;
	}
	/**
	 * @param blockCd 設定する blockCd。
	 */
	public void setBlockCd(String blockCd) {
		this.blockCd = blockCd;
	}
	/**
	 * @return kanriNo を戻します。
	 */
	public String getKanriNo() {
		return kanriNo;
	}
	/**
	 * @param kanriNo 設定する kanriNo。
	 */
	public void setKanriNo(String kanriNo) {
		this.kanriNo = kanriNo;
	}
	/**
	 * @return miseCd を戻します。
	 */
	public String getMiseCd() {
		return miseCd;
	}
	/**
	 * @param miseCd 設定する miseCd。
	 */
	public void setMiseCd(String miseCd) {
		this.miseCd = miseCd;
	}
	/**
	 * @return viewInfo を戻します。
	 */
	public UIVoiceInfo getViewInfo() {
		return viewInfo;
	}
	/**
	 * @param entity 設定する viewInfo。
	 */
	public void setViewInfo(UIVoiceInfo entity) {
		this.viewInfo = entity;
	}
}
