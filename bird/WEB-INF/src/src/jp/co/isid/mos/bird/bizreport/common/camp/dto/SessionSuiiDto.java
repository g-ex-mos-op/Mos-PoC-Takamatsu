package jp.co.isid.mos.bird.bizreport.common.camp.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizreport.common.camp.entity.MstCampDate;
import jp.co.isid.mos.bird.bizreport.common.camp.entity.UILists;
import jp.co.isid.mos.bird.bizreport.common.camp.util.CommonUtil;

/**
 * 推移表用SessionDTO
 * 
 * @author xkinu
 *
 */
public class SessionSuiiDto extends SessionDto {
    /**
     * 照会画面タイプ取得処理
     * 
     * 日報系
     * @return
     */
    public int getScreeanType() {
    	return CommonUtil.TYPE_SUII;
    }
    /**
     * メニューコード
     */ 
    private Map menuCd= new HashMap();
    /**
     * ブロックコードプルダウンリスト
     */ 
    private List listBlock= new ArrayList();
    /**
     * ブロックコード
     */ 
    private Map blockCd = new HashMap();
    /**
     * 店舗選択画面呼び出し時リクエストDTO保持Map
     */ 
    private Map miseCallJoken = new HashMap();

	/**
	 * メニューコード取得処理
	 * @param windowId
	 * @return menuCd を戻します。
	 */
	public String getMenuCd(int windowId) {
		return (String)menuCd.get(new Integer(windowId));
	}

	/**
	 * メニューコード設定処理
	 * 
	 * @param windowId
	 * @param menuMenuCd 設定する menuMenuCd。
	 */
	public void setMenuCd(int windowId, String menuCd) {
		this.menuCd.put(new Integer(windowId), menuCd);
	}

	/**
	 * ブロックコードプルダウンリスト取得処理
	 * @return listBlock を戻します。
	 */
	public List getListBlock() {
		return listBlock;
	}

	/**
	 * ブロックコードプルダウンリスト設定処理
	 * @param listBlock 設定する listBlock。
	 */
	public void setListBlock(List listBlock) {
		this.listBlock = listBlock;
	}
	/**
	 * ブロックコード取得処理
	 * @param windowId
	 * @return blockCd を戻します。
	 */
	public String getBlockCd(int windowId) {
		return (String)blockCd.get(new Integer(windowId));
	}

	/**
	 * ブロックコード設定処理
	 * 
	 * @param windowId
	 * @param BlockCd 設定する BlockCd。
	 */
	public void setBlockCd(int windowId, String blockCd) {
		this.blockCd.put(new Integer(windowId), blockCd);
	}

	/**
	 * 指定RequestDTOへ検索済み条件値格納します。
	 */
	public void getSearchedDataDto(int windowId, RequestSuiiDto searchedDataDto) {
		searchedDataDto.setWindowId(windowId);
		searchedDataDto.setCompanyCd(getCompanyCd(windowId));
		searchedDataDto.setNendo(getNendo(windowId));
		/* プルダウン情報を設定します。*/
		searchedDataDto.setPullDownData(this);
		searchedDataDto.setCampId(getCampId(windowId));
		searchedDataDto.setMenuTotaledKbn(getMenuTotaledKbn(windowId));
		searchedDataDto.setMenuCd(getMenuCd(windowId));
		searchedDataDto.setTenpoShubetu(getTenpoShubetu(windowId));
		searchedDataDto.setTaishoJoken(getTaishoJoken(windowId));
        searchedDataDto.setBlockCd(getBlockCd(windowId));
		searchedDataDto.setHyojiTaisho(getHyojiTaisho(windowId));
		searchedDataDto.setZennenDataShubetu(getZennenDataShubetu(windowId));
		searchedDataDto.setSelfSessionDto(this);
		searchedDataDto.setListSearchData(getListSearchData(windowId));
	}
	/**
	 * 検索済み条件値設定処理
	 * @param dto
	 */
	public void setSearchedData(RequestSuiiDto dto) {
		int windowId = dto.getWindowId();
		setCompanyCd(windowId, dto.getCompanyCd());
		setNendo(windowId, dto.getNendo());
		setCampId(windowId, dto.getCampId());
		setMenuTotaledKbn(windowId, dto.getMenuTotaledKbn());
		setMenuCd(windowId, dto.getMenuCd());
		setTenpoShubetu(windowId, dto.getTenpoShubetu());
		setTaishoJoken(windowId, dto.getTaishoJoken());
		setHyojiTaisho(windowId, dto.getHyojiTaisho());
		setBlockCd(windowId, dto.getBlockCd());
		setZennenDataShubetu(windowId, dto.getZennenDataShubetu());
	}
	/**
	 * 検索結果該当データなし対象情報を全てNullを設定します。
	 *  
	 * @param windowId
	 */
	public void setNoResultSearchedData(int windowId) {
		setCompanyCd(windowId, null);
		setNendo(windowId, null);
		setCampId(windowId, null);
		setMenuTotaledKbn(windowId, null);
		setTenpoShubetu(windowId, null);
		setTaishoJoken(windowId, null);
		setHyojiTaisho(windowId, null);
		setZennenDataShubetu(windowId, null);

		setMenuCd(windowId, null);
		setBlockCd(windowId, null);
		//検索データ設定
		removeListSearchData(windowId);
	}

	/**
	 * @param miseCallJoken 設定する miseCallJoken。
	 */
	public void setMiseCallJoken(RequestSuiiDto reqDto) {
		this.miseCallJoken.put(new Integer(reqDto.getWindowId()), reqDto);
	}
	/**
	 * @param miseCallJoken 設定する miseCallJoken。
	 */
	public RequestSuiiDto getMiseCallJoken(int windowId) {
		Integer key = new Integer(windowId);
		return (RequestSuiiDto)miseCallJoken.get(key);
	}

    /**
     * アプリ日付取得
     */
    public String getAppDate() {
        if (getBirdDateInfo() != null) {
            return getBirdDateInfo().getAppDate();
        }
        return "";
    }

    /**
     * 指定会社コード全キャンペーンメニュープルダウンリスト取得処理
     * 
     * @param companyCd　指定会社コード
     * @return
     */
    public List getListsMenuNendo(String companyCd, String nendo) {
        if (nendo != nendo && getListsCamp(companyCd) != null) {
            List lists = new ArrayList(0);
            List listCamp = getListCamp(companyCd, nendo);
            if(listCamp != null) {
                List listMenuTotaled = getListMenuTotaled();
                for(int c=0; c<listCamp.size(); c++) {
                    String campId = ((MstCampDate)listCamp.get(c)).getCampId();
                    UILists campList = new UILists();
                    campList.setKey(campId);
                    campList.setKeyName(((MstCampDate)listCamp.get(c)).getCampTitle());
                    List listMenuLists = new ArrayList(0);
                    for(int i=0; i<listMenuTotaled.size(); i++) {
                        String code = (String)((SelectItem)listMenuTotaled.get(i)).getValue();
                        UILists mLists = new UILists();
                        mLists.setKey(code);
                        mLists.setKeyName(((SelectItem)listMenuTotaled.get(i)).getLabel());
                        mLists.setListData(getListMenu(campId, code));
                        listMenuLists.add(mLists);
                    }
                    campList.setListData(listMenuLists);
                    lists.add(campList);
                }
            }
            return lists;
        }
        return null;
    }
    /**
     * 指定会社コード全キャンペーン表示対象プルダウンリスト取得処理
     * 
     * @param companyCd　指定会社コード
     * @return
     */
    public List getListsHyojiTaisho(String companyCd, String nendo) {
        List listTaishoJoken = getListTaishoJoken(companyCd);
        if(nendo != null && getListsCamp(companyCd) != null) {
            List lists = new ArrayList(0);
            List listCamp = getListCamp(companyCd, nendo);
            if(listCamp != null) {
                for(int c=0; c<listCamp.size(); c++) {
                    String campId = ((MstCampDate)listCamp.get(c)).getCampId();
                    UILists campList = new UILists();
                    campList.setKey(campId);
                    campList.setKeyName(((MstCampDate)listCamp.get(c)).getCampTitle());
                    List listMenuLists = new ArrayList(0);
                    for(int i=0; i<listTaishoJoken.size(); i++) {
                        String code = (String)((SelectItem)listTaishoJoken.get(i)).getValue();
                        UILists mLists = new UILists();
                        mLists.setKey(code);
                        mLists.setKeyName(((SelectItem)listTaishoJoken.get(i)).getLabel());
                        mLists.setListData(getListHyojiTaisho(campId, code));
                        listMenuLists.add(mLists);
                    }
                    campList.setListData(listMenuLists);
                    lists.add(campList);
                }
            }
            return lists;
        }
        return null;
    }
}
