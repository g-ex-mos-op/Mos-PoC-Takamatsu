package jp.co.isid.mos.bird.bizreport.common.camp.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.campniporef.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.common.camp.code.RankKind;
import jp.co.isid.mos.bird.bizreport.common.camp.code.ShukeiKbn;
import jp.co.isid.mos.bird.bizreport.common.camp.entity.MstCampDate;
import jp.co.isid.mos.bird.bizreport.common.camp.entity.UILists;
import jp.co.isid.mos.bird.bizreport.common.camp.util.CommonUtil;
import jp.co.isid.mos.bird.bizreport.common.entity.CodKikanSitei;
import jp.co.isid.mos.bird.common.code.UserType;

/**
 * 日報用SessionDTO
 * 
 * @author xkinu
 *
 */
public class SessionNipoDto extends SessionDto {
    /**
     * 照会画面タイプ取得処理
     * 
     * 日報系
     * @return
     */
    public int getScreeanType() {
    	return CommonUtil.TYPE_NIPO;
    }
    /**
     * 集計区分
     */ 
    private Map shukeiKbn= new HashMap();
    /**
     * 対象日(開始日)
     */ 
    private Map taishoDt= new HashMap();
    /**
     * 対象終了日
     */ 
    private Map taishoDtTo= new HashMap();
    /**
     * 順位
     */ 
    private Map rankKind= new HashMap();
    /**
     * 対象期間
     */ 
    private Map taishoKikan= new HashMap();

	/**
	 * 集計区分プルダウンリスト
	 * @return sahukeiKbn を戻します。
	 */
	public List getListShukeiKbn() {
        if (UserType.HONBU.equals(getUserTypeCd())) {
            return ShukeiKbn.getPullDownList();
        }
        else {
            String arrayCode[] = new String[]{ShukeiKbn.IN_RC, ShukeiKbn.OUT_RC};
            return ShukeiKbn.getPullDownList(arrayCode);
        }
	}
	/**
	 * 順位プルダウンリスト
	 * @return sahukeiKbn を戻します。
	 */
	public List getListRankKind() {
		return RankKind.getPullDownList();
	}
	/**
	 * 指定会社コードの全キャンペーン対象日プルダウンリスト取得処理
	 * 
	 * @param companyCd　指定会社コード
	 * @return
	 */
	public List getListsTaishoDt(String companyCd) {
		List listNend = getListNendo(companyCd);
		if(listNend != null && getListsCamp(companyCd) != null) {
			List lists = new ArrayList(0);
			for (int n=0; n<listNend.size(); n++) {
				CodKikanSitei sNendo = (CodKikanSitei)listNend.get(n);
				String nendo = sNendo.getCode();
				List listCamp = getListCamp(companyCd, nendo);
				if(listCamp != null) {
					for(int i=0; i<listCamp.size(); i++) {
						MstCampDate mstCampDate = (MstCampDate)listCamp.get(i);
						UILists eLists = new UILists();
						String code = mstCampDate.getCampId();
						eLists.setKey(code);
						eLists.setKeyName(mstCampDate.getCampTitle());
						eLists.setListData(CommonUtil.creatListDay(getBirdDateInfo().getAppDate()
								, mstCampDate.getCampTo(), mstCampDate.getCampFrom()));
						lists.add(eLists);
					}
				}
			}
			return lists;
		}
		return null;
	}
	/**
	 * 指定会社コード＆年度の全キャンペーン対象日プルダウンリスト取得処理
	 * 
	 * @param companyCd　指定会社コード
	 * @param nendo　　　指定年度
	 * @return
	 */
	public List getListsTaishoDt(String companyCd, String nendo) {
		List listCamp = getListCamp(companyCd, nendo);
		if(listCamp != null) {
			List lists = new ArrayList(0);
			for(int i=0; i<listCamp.size(); i++) {
				MstCampDate mstCampDate = (MstCampDate)listCamp.get(i);
				UILists eLists = new UILists();
				String code = mstCampDate.getCampId();
				eLists.setKey(code);
				eLists.setKeyName(mstCampDate.getCampTitle());
				eLists.setListData(CommonUtil.creatListDay(getBirdDateInfo().getAppDate()
						, mstCampDate.getCampTo(), mstCampDate.getCampFrom()));
				lists.add(eLists);
			}
			return lists;
		}
		return null;
	}
	/**
	 * 集計区分取得処理
	 * @param windowId
	 * @return shukeiKbn を戻します。
	 */
	public String getShukeiKbn(int windowId) {
		return (String)shukeiKbn.get(new Integer(windowId));
	}

	/**
	 * 集計区分設定処理
	 * 
	 * @param windowId
	 * @param shukeiKbn 設定する shukeiKbn。
	 */
	public void setShukeiKbn(int windowId, String shukeiKbn) {
		this.shukeiKbn.put(new Integer(windowId), shukeiKbn);
	}
	/**
	 * 対象日(開始日)取得処理
	 * @param windowId
	 * @return taishoDt を戻します。
	 */
	public String getTaishoDt(int windowId) {
		return (String)taishoDt.get(new Integer(windowId));
	}

	/**
	 * 対象日(開始日)設定処理
	 * 
	 * @param windowId
	 * @param taishoDt 設定する taishoDt。
	 */
	public void setTaishoDt(int windowId, String taishoDt) {
		this.taishoDt.put(new Integer(windowId), taishoDt);
	}
	/**
	 * 対象終了日取得処理
	 * @param windowId
	 * @return taishoDtTo を戻します。
	 */
	public String getTaishoDtTo(int windowId) {
		return (String)taishoDtTo.get(new Integer(windowId));
	}

	/**
	 * 対象終了日設定処理
	 * 
	 * @param windowId
	 * @param taishoDtTo
	 */
	public void setTaishoDtTo(int windowId, String taishoDtTo) {
		this.taishoDtTo.put(new Integer(windowId), taishoDtTo);
	}
	/**
	 * 順位取得処理
	 * @param windowId
	 * @return rankKind を戻します。
	 */
	public String getRankKind(int windowId) {
		return (String)rankKind.get(new Integer(windowId));
	}

	/**
	 * 順位設定処理
	 * 
	 * @param windowId
	 * @param rankKind 設定する rankKind。
	 */
	public void setRankKind(int windowId, String rankKind) {
		this.rankKind.put(new Integer(windowId), rankKind);
	}
	/**
	 * 対象期間取得処理
	 * 
	 * 対象日&対象期間
	 * 対象日
	 * 対象期間
	 * @param windowId
	 * @return taishoKikan を戻します。
	 */
	public String getTaishoKikan(int windowId) {
		return (String)taishoKikan.get(new Integer(windowId));
	}

	/**
	 * 対象期間設定処理
	 * 
	 * @param windowId
	 * @param taishoDt 設定する taishoDt。
	 */
	public void setTaishoKikan(int windowId, String taishoDt) {
		this.taishoKikan.put(new Integer(windowId), taishoDt);
	}
	/**
	 * 指定RequestDTOへ検索済み条件値格納します。
	 */
	public void getSearchedDataDto(int windowId, RequestNipoDto searchedDataDto) {
		searchedDataDto.setWindowId(windowId);
		searchedDataDto.setCompanyCd(getCompanyCd(windowId));
		searchedDataDto.setNendo(getNendo(windowId));
		/* プルダウン情報を設定します。*/
		searchedDataDto.setPullDownData(this);
		searchedDataDto.setCampId(getCampId(windowId));
		searchedDataDto.setMenuTotaledKbn(getMenuTotaledKbn(windowId));
		searchedDataDto.setTenpoShubetu(getTenpoShubetu(windowId));
		searchedDataDto.setShukeiKbn(getShukeiKbn(windowId));
		searchedDataDto.setTaishoJoken(getTaishoJoken(windowId));
		searchedDataDto.setHyojiTaisho(getHyojiTaisho(windowId));
		searchedDataDto.setTaishoKikan(getTaishoKikan(windowId));
		searchedDataDto.setTaishoDt(getTaishoDt(windowId));
		searchedDataDto.setTaishoDtTo(getTaishoDtTo(windowId));
		searchedDataDto.setRankKind(getRankKind(windowId));
		searchedDataDto.setZennenDataShubetu(getZennenDataShubetu(windowId));
		searchedDataDto.setTaishoKikan(getTaishoKikan(windowId));
		//検索データ設定
		searchedDataDto.setListSearchData(getListSearchData(windowId));
		
	}
	/**
	 * 検索済み条件値設定処理
	 * @param dto
	 */
	public void setSearchedData(RequestNipoDto dto) {
		int windowId = dto.getWindowId();
		setCompanyCd(windowId, dto.getCompanyCd());
		setNendo(windowId, dto.getNendo());
		setCampId(windowId, dto.getCampId());
		setMenuTotaledKbn(windowId, dto.getMenuTotaledKbn());
		setTenpoShubetu(windowId, dto.getTenpoShubetu());
		setShukeiKbn(windowId, dto.getShukeiKbn());
		setTaishoJoken(windowId, dto.getTaishoJoken());
		setHyojiTaisho(windowId, dto.getHyojiTaisho());
		setTaishoKikan(windowId, dto.getTaishoKikan());
		setTaishoDt(windowId, dto.getTaishoDt());
		setTaishoDtTo(windowId, dto.getTaishoDtTo());
		setRankKind(windowId, dto.getRankKind());
		setZennenDataShubetu(windowId, dto.getZennenDataShubetu());
		setTaishoKikan(windowId, dto.getTaishoKikan());
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
		
		setShukeiKbn(windowId, null);
		setTaishoKikan(windowId, null);
		setTaishoDt(windowId, null);
		setTaishoDtTo(windowId, null);
		setRankKind(windowId, null);
		
		//検索データ設定
		removeListSearchData(windowId);
	}
	/**
	 * 対象期間プルダウンリスト用データ取得処理
	 * 
	 * 2008/10/15追加
	 * @return list 対象期間プルダウンリスト用データ
	 */
	public List getListTaishoKikan() {
		return TaishoKikan.getPullDownList();
	}
}
