/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.common.camp.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizreport.common.camp.code.MenuTotaledKbn;
import jp.co.isid.mos.bird.bizreport.common.camp.code.ShukeiKbn;
import jp.co.isid.mos.bird.bizreport.common.camp.code.TaishoJoken;
import jp.co.isid.mos.bird.bizreport.common.camp.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.camp.code.ZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.common.camp.entity.MstCampDate;
import jp.co.isid.mos.bird.bizreport.common.camp.entity.UILists;
import jp.co.isid.mos.bird.bizreport.common.camp.util.CommonUtil;
import jp.co.isid.mos.bird.bizreport.common.code.UserType;
import jp.co.isid.mos.bird.bizreport.common.entity.CodHyojiTaisho;
import jp.co.isid.mos.bird.bizreport.common.entity.CodKikanSitei;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * キャンペーン照会画面セッション用DTO
 * 
 * @author xkinu
 *
 */
public abstract class SessionDto {
    /* 現行セッションKey */
    private String nowSessionKey;
    /* セッションKey保持Map */
    private Map sessionKey = new HashMap();
    
    /**
     * ログインユーザー情報
     */
    private BirdUserInfo birdUserInfo;
    /**
     * BIRD日付情報
     */
    private BirdDateInfo birdDateInfo;
    
    /**
     * 会社リスト
     */
    private List listCompany = new ArrayList();
    /**
     * 年度リスト
     */
    private Map listNendo = new HashMap();
    /**
     * プルダウン用キャンペーンリスト
     */
    private Map listCamp = new HashMap();
    /**
     * メニュープルダウンリスト
     */ 
    private Map listMenu= new HashMap();
    /**
     * 最大ウィンドウID
     */
    private int maxWindowId = 0;
    /**
     * 最大データ保持件数
     */
    private int maxSize=5;
    /**
     * 指定オーナーコード
     */ 
    private String targetOnerCd;
    /**
     * 年度
     */ 
    private Map nendo= new HashMap();
    /**
     * 会社コード
     */ 
    private Map companyCd= new HashMap();
    /**
     * キャンペーン識別番号
     */ 
    private Map campId= new HashMap();
    /**
     * メニュー集計区分
     */ 
    private Map menuTotaledKbn= new HashMap();
    /**
     * 店舗種別
     */ 
    private Map tenpoShubetu= new HashMap();
    /**
     * 対象条件
     */ 
    private Map taishoJoken= new HashMap();
    /**
     * 表示対象プルダウンリスト
     * 
     * 対象店舗プルダウン用にも使用します。
     */ 
    private Map listHyojiTaisho= new HashMap();
    /**
     * 表示対象
     */ 
    private Map hyojiTaisho = new HashMap();

    /**
     * 前年データ種別
     */ 
    private Map zennenDataShubetu= new HashMap();
    /**
     * 検索結果
     */
    private Map listSearchData = new LinkedHashMap();
    /**
     * 検索済みフラグ
     */
    private Map searchedFlg = new HashMap();
    
    /**
     * ウィンドウIDを生成します。
     */
    public int createWindowId() {
        int newWindowId = getMaxWindowId() + 1;
        setMaxWindowId(newWindowId);
        return newWindowId;
    }
    
    /**
     * ウィンドウIDを更新します。
     */
    public int updateWindowid() {
       return createWindowId();
    }

	/**
	 * @return birdDateInfo を戻します。
	 */
	public BirdDateInfo getBirdDateInfo() {
		return birdDateInfo;
	}

	/**
	 * @param birdDateInfo 設定する birdDateInfo。
	 */
	public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
		this.birdDateInfo = birdDateInfo;
	}

	/**
	 * @return birdUserInfo を戻します。
	 */
	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}

	/**
	 * @param birdUserInfo 設定する birdUserInfo。
	 */
	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}
	/**
	 * @return listCompany を戻します。
	 */
	public List getListCompany() {
		return listCompany;
	}

	/**
	 * @param listCompany 設定する listCompany。
	 */
	public void setListCompany(List listCompany) {
		this.listCompany = listCompany;
	}


	/**
	 * 会社コード取得処理
	 * @param windowId
	 * @return companyCd を戻します。
	 */
	public String getCompanyCd(int windowId) {
		return (String)companyCd.get(new Integer(windowId));
	}

	/**
	 * 会社コード設定処理
	 * 
	 * @param windowId
	 * @param companyCd 設定する companyCd。
	 */
	public void setCompanyCd(int windowId, String companyCd) {
		this.companyCd.put(new Integer(windowId), companyCd);
	}
	/**
	 * 年度取得処理
	 * @param windowId
	 * @return nendo を戻します。
	 */
	public String getNendo(int windowId) {
		return (String)nendo.get(new Integer(windowId));
	}

	/**
	 * 年度設定処理
	 * 
	 * @param windowId
	 * @param nendo 設定する nendo。
	 */
	public void setNendo(int windowId, String nendo) {
		this.nendo.put(new Integer(windowId), nendo);
	}


	/**
	 * キャンペーン識別番号取得処理
	 * 
	 * @param windowId
	 * @return campId を戻します。
	 */
	public String getCampId(int windowId) {
		return (String)campId.get(new Integer(windowId));
	}

	/**
	 * キャンペーン識別番号設定処理
	 * @param windowId
	 * @param campId
	 */
	public void setCampId(int windowId, String campId) {
		this.campId.put(new Integer(windowId), campId);
	}
	/**
	 * メニュー集計区分プルダウン取得処理
	 * 
	 * @return listMenuTotaled を戻します。
	 */
	public List getListMenuTotaled() {
		return MenuTotaledKbn.getPullDownList();
	}
	/**
	 * メニュープルダウンリスト取得処理
	 * 
	 * @param key キャンペーンID＋メニュー集計区分の文字列を結合させた文字列
	 * @return listMenu を戻します。
	 */
	public List getListMenu(String campId, String menuShukeiKbn) {
		if(this.listMenu.containsKey(campId)) {
			return (List)((Map)(this.listMenu.get(campId))).get(menuShukeiKbn);
		}
		return null;
	}

	/**
	 * メニュープルダウンリスト設定処理
	 * 
	 * @param list 設定する listMenu。
	 */
	public void setListMenu(String campId, String menuShukeiKbn, List list) {
		Map mapKbn = null;
		if(this.listMenu.containsKey(campId)) {
			mapKbn = (Map)(this.listMenu.get(campId));
		}
		else {
			mapKbn = new HashMap();
		}
		mapKbn.put(menuShukeiKbn, list);
		this.listMenu.put(campId, mapKbn);
	}
	/**
	 * 指定会社コード全キャンペーンメニュープルダウンリスト取得処理
	 * 
	 * @param companyCd　指定会社コード
	 * @return
	 */
	public List getListsMenu(String companyCd) {
		List listNend = getListNendo(companyCd);
		if(listNend != null && getListsCamp(companyCd) != null) {
			List lists = new ArrayList(0);
			for (int n=0; n<listNend.size(); n++) {
				CodKikanSitei sNendo = (CodKikanSitei)listNend.get(n);
				String nendo = sNendo.getCode();
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
			}
			return lists;
		}
		return null;
	}
	/**
	 * 指定会社コード＆年度全キャンペーンメニュープルダウンリスト取得処理
	 * 
	 * @param companyCd　指定会社コード
	 * @return
	 */
	public List getListsMenu(String companyCd, String nendo) {
		List listCamp = getListCamp(companyCd, nendo);
		if(listCamp != null) {
			List lists = new ArrayList(0);
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
			return lists;
		}
		return null;
	}
	/**
	 * @return listSearchData を戻します。
	 */
	public List getListSearchData(int windowId) {
		return (List)listSearchData.get(new Integer(windowId));
	}

	/**
	 * 検索結果設定処理
	 * 
	 * @param windowId
	 * @param listSearchData
	 */
	public void setListSearchData(int windowId, List listSearchData) {
        // 現在ウインドウIDのデータを保持している場合
        if (this.listSearchData.containsKey(new Integer(windowId))) {
            // 順番を入れ替える為、前回データを削除する
            this.listSearchData.remove(new Integer(windowId));
        // 最大数を超えた場合
        } else if (this.listSearchData.size() >= getMaxSize()) {
            // 最古データを削除
            this.listSearchData.remove(this.listSearchData.keySet().toArray()[0]);
        }       	
    	this.listSearchData.put(new Integer(windowId), listSearchData);
	}
	/**
	 * 検索結果削除処理
	 * 
	 * @param windowId
	 */
	public void removeListSearchData(int windowId) {
        // 現在ウインドウIDのデータを保持している場合
        if (isExistSearchedData(windowId)) {
            // 順番を入れ替える為、前回データを削除する
            this.listSearchData.remove(new Integer(windowId));
        }
	}
	/**
	 * 該当データ情報設定処理
	 * 
	 * 検索結果該当データなし対象情報を全てNullを設定します。
	 *  
	 * @param windowId
	 */
	public abstract void setNoResultSearchedData(int windowId);
	/**
	 * 表示対象プルダウンリスト取得処理
	 * @return listHyojiTaisho を戻します。
	 */
	public List getListHyojiTaisho(String campId, String taishoJoken) {
		if(this.listHyojiTaisho.containsKey(campId)) {
			return (List)((Map)(this.listHyojiTaisho.get(campId))).get(taishoJoken);
		}
		return null;
	}

	/**
	 * 表示対象プルダウンリスト設定処理
	 * @param list 設定する listHyojiTaisho。
	 */
	public void setListHyojiTaisho(String campId, String taishoJoken, List list) {
		Map mapHyojiTaisho = null;
		if(this.listHyojiTaisho.containsKey(campId)) {
			mapHyojiTaisho = (Map)(this.listHyojiTaisho.get(campId));
		}
		else {
			mapHyojiTaisho = new HashMap();
		}
		mapHyojiTaisho.put(taishoJoken, list);
		this.listHyojiTaisho.put(campId, mapHyojiTaisho);
	}
	/**
	 * 指定会社コード全キャンペーン表示対象プルダウンリスト取得処理
	 * 
	 * @param companyCd　指定会社コード
	 * @return
	 */
	public List getListsHyojiTaisho(String companyCd) {
		List listNend = getListNendo(companyCd);
		List listTaishoJoken = getListTaishoJoken(companyCd);
		if(listNend != null && getListsCamp(companyCd) != null) {
			List lists = new ArrayList(0);
			for (int n=0; n<listNend.size(); n++) {
				CodKikanSitei sNendo = (CodKikanSitei)listNend.get(n);
				String nendo = sNendo.getCode();
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
		List listCamp = getListCamp(companyCd, nendo);
		if(listCamp != null) {
			List lists = new ArrayList(0);
			for(int c=0; c<listCamp.size(); c++) {
				String campId = ((MstCampDate)listCamp.get(c)).getCampId();
				UILists campList = new UILists();
				campList.setKey(campId);
				campList.setKeyName(((MstCampDate)listCamp.get(c)).getCampTitle());
				List listLists = new ArrayList(0);
				for(int i=0; i<listTaishoJoken.size(); i++) {
					String code = (String)((SelectItem)listTaishoJoken.get(i)).getValue();
					UILists mLists = new UILists();
					mLists.setKey(code);
					mLists.setKeyName(((SelectItem)listTaishoJoken.get(i)).getLabel());
					mLists.setListData(getListHyojiTaisho(campId, code));
					listLists.add(mLists);
				}
				campList.setListData(listLists);
				lists.add(campList);
			}
			return lists;
		}
		return null;
	}
	/**
	 * 表示対象取得処理
	 * @param windowId
	 * @return hyojiTaisho を戻します。
	 */
	public String getHyojiTaisho(int windowId) {
		return (String)hyojiTaisho.get(new Integer(windowId));
	}
	/**
	 * 対象条件プルダウンリスト取得処理
	 * 
	 * @return listTaishoJoken を戻します。
	 */
	public List getListTaishoJoken(String companyCd) {
		if(CommonUtil.TYPE_NIPO == getScreeanType()) {
			if(UserType.TENPO.equals(getUserTypeCd())) {
				return TaishoJoken.getPullDownList(getUserTypeCd()
						, companyCd
						, getBirdUserInfo().isLimit());
			}
			return ShukeiKbn.getPullDownList();
		}
		return TaishoJoken.getPullDownList(getUserTypeCd()
				, companyCd
				, getBirdUserInfo().isLimit());
		
	}
	/**
	 * 表示対象名称
	 * @return hyojiTaishoName を戻します。
	 */
	public String getHyojiTaishoName(String campId, String taishoJoken, String hyojiTaisho) {
		List listCode = getListHyojiTaisho(campId, taishoJoken);
        if (hyojiTaisho == null) {
            return "";
        }
		if(listCode != null) {
			for(int i=0; i<listCode.size(); i++) {
				CodHyojiTaisho entity = (CodHyojiTaisho)listCode.get(i);
				if(entity.getHyojitaishoCd() != null && hyojiTaisho.trim().equals(entity.getHyojitaishoCd().trim())) {
					return entity.getHyojitaishoName();
				}
			}
		}
		return "";
	}
	/**
	 * 店舗種別プルダウンリスト取得処理
	 * 
	 * @return listTenpoShubetu を戻します。
	 */
	public List getListTenpoShubetu() {
		return TenpoShubetu.getPullDownList(getUserTypeCd());
	}
	/**
	 * 前年データ種別プルダウンリスト取得処理
	 * 
	 * @return listZennenDataShubetu を戻します。
	 */
	public List getListZennenDataShubetu(String tenpoShubetu) {
		return ZennenDataShubetu.getPullDownList(getUserTypeCd(), getScreeanType(), tenpoShubetu);
	}
	/**
	 * 店舗種別分の前年データ種別プルダウンリスト取得処理
	 * 
	 * @return listZennenDataShubetu を戻します。
	 */
	public List getListsZennenDataShubetu() {
		List listTenpoShubetu = getListTenpoShubetu();
		List lists = new ArrayList(0);
		for(int i=0; i<listTenpoShubetu.size(); i++) {
			UILists eLists = new UILists();
			String code = (String)((SelectItem)listTenpoShubetu.get(i)).getValue();
			eLists.setKey(code);
			eLists.setKeyName(((SelectItem)listTenpoShubetu.get(i)).getLabel());
			eLists.setListData(getListZennenDataShubetu(code));
			lists.add(eLists);
		}
		return lists;
	}

	/**
	 * 表示対象設定処理
	 * 
	 * @param windowId
	 * @param hyojiTaisho 設定する hyojiTaisho。
	 */
	public void setHyojiTaisho(int windowId, String hyojiTaisho) {
		this.hyojiTaisho.put(new Integer(windowId), hyojiTaisho);
	}

	/**
	 * @return maxSize を戻します。
	 */
	public int getMaxSize() {
		return maxSize;
	}

	/**
	 * @param maxSize 設定する maxSize。
	 */
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	/**
	 * @return maxWindowId を戻します。
	 */
	public int getMaxWindowId() {
		return maxWindowId;
	}

	/**
	 * @param maxWindowId 設定する maxWindowId。
	 */
	public void setMaxWindowId(int maxWindowId) {
		this.maxWindowId = maxWindowId;
	}

	/**
	 * @return nowSessionKey を戻します。
	 */
	public String getNowSessionKey() {
		return nowSessionKey;
	}

	/**
	 * @param nowSessionKey 設定する nowSessionKey。
	 */
	public void setNowSessionKey(String nowSessionKey) {
		this.nowSessionKey = nowSessionKey;
	}

	/**
	 * セッションKey保持MapからセッションKeyの取得
	 * @return sessionKey を戻します。
	 */
	public String getSessionKey(int windowId) {
		return (String)sessionKey.get(new Integer(windowId));
	}
    /**
     * セッションKey保持MapへセッションKeyの設定
     * @param sessionKey sessionKey を設定。
     */
    public void setSessionKey(int windowId, String sessionKey) {
        this.sessionKey.put(new Integer(windowId), sessionKey);
    }

	/**
	 * @return targetOnerCd を戻します。
	 */
	public String getTargetOnerCd() {
		return targetOnerCd;
	}

	/**
	 * @param targetOnerCd 設定する targetOnerCd。
	 */
	public void setTargetOnerCd(String targetOnerCd) {
		this.targetOnerCd = targetOnerCd;
	}

	/**
	 * @return tenpoShubetu を戻します。
	 */
	public String getTenpoShubetu(int windowId) {
		return (String)tenpoShubetu.get(new Integer(windowId));
	}

	/**
	 * @param tenpoShubetu 設定する tenpoShubetu。
	 */
	public void setTenpoShubetu(int windowId, String tenpoShubetu) {
		this.tenpoShubetu.put(new Integer(windowId), tenpoShubetu);
	}

	/**
	 * @return zennenDataShubetu を戻します。
	 */
	public String getZennenDataShubetu(int windowId) {
		return (String)zennenDataShubetu.get(new Integer(windowId));
	}

	/**
	 * @param zennenDataShubetu 設定する zennenDataShubetu。
	 */
	public void setZennenDataShubetu(int windowId, String zennenDataShubetu) {
		this.zennenDataShubetu.put(new Integer(windowId), zennenDataShubetu);
	}

	/**
	 * @param tenpoShubetu 設定する tenpoShubetu。
	 */
	public void setTenpoShubetu(Map tenpoShubetu) {
		this.tenpoShubetu = tenpoShubetu;
	}

	/**
	 * @param zennenDataShubetu 設定する zennenDataShubetu。
	 */
	public void setZennenDataShubetu(Map zennenDataShubetu) {
		this.zennenDataShubetu = zennenDataShubetu;
	}
	public String getUserTypeCd() {
		return getBirdUserInfo().getMstUser().getUserTypeCd();
	}

	/**
	 * キャンペーンプルダウンリスト取得処理
	 * @param key getNendo(windowId)+getCompanyCd(windowId)
	 * @return listCamp を戻します。
	 */
	public List getListsCamp(String comapnyCd) {
		if(this.listCamp.containsKey(comapnyCd)) {
			Map mapCompany = (Map)(this.listCamp.get(comapnyCd));
			List lists = new ArrayList(0);
			for(Iterator ite = mapCompany.keySet().iterator(); ite.hasNext();) {
				String nendoKey = (String)ite.next();
				UILists uiList = new UILists();
				uiList.setKey(nendoKey);
				uiList.setKeyName(nendoKey);
				uiList.setListData(getListCamp(comapnyCd, nendoKey));
				lists.add(uiList);
			}
			return lists;
		}
		return null;
	}
	/**
	 * キャンペーンプルダウンリスト取得処理
	 * @param key getNendo(windowId)+getCompanyCd(windowId)
	 * @return listCamp を戻します。
	 */
	public List getListCamp(String comapnyCd, String nendo) {
		if(this.listCamp.containsKey(comapnyCd)) {
			return (List)((Map)(this.listCamp.get(comapnyCd))).get(nendo);
		}
		return null;
	}

	/**
	 * キャンペーンプルダウンリスト設定処理
	 * @param key getNendo(windowId)+getCompanyCd(windowId)
	 * @param listCamp 設定する listCamp。
	 */
	public void setListCamp(String comapnyCd, String nendo, List listCamp) {
		Map mapCompany = null;
		if(this.listCamp.containsKey(comapnyCd)) {
			mapCompany = (Map)(this.listCamp.get(comapnyCd));
		}
		else {
			mapCompany = new HashMap();
		}
		mapCompany.put(nendo, listCamp);
		this.listCamp.put(comapnyCd, mapCompany);
	}

	/**
	 * メニュー集計区分取得処理
	 * @param windowId
	 * @return menuTotaledKbn を戻します。
	 */
	public String getMenuTotaledKbn(int windowId) {
		return (String)menuTotaledKbn.get(new Integer(windowId));
	}

	/**
	 * メニュー集計区分設定処理
	 * 
	 * @param windowId
	 * @param menuTotaledKbn 設定する menuTotaledKbn。
	 */
	public void setMenuTotaledKbn(int windowId, String menuTotaledKbn) {
		this.menuTotaledKbn.put(new Integer(windowId), menuTotaledKbn);
	}

	/**
	 * @return listNendo を戻します。
	 */
	public List getListNendo(String companyCd) {
		return (List)listNendo.get(companyCd);
	}

	/**
	 * @param listNendo 設定する listNendo。
	 */
	public void setListNendo(String companyCd, List listNendo) {
		this.listNendo.put(companyCd, listNendo);
	}
	/**
	 * リクエスト対象キャンペーン期間マスタエンティティ取得処理
	 * 
	 * @return mstCampDate を戻します。
	 */
	public MstCampDate getMstCampDate(int windowId) {
		List listCamp = getListCamp(getCompanyCd(windowId), getNendo(windowId));
		for(int i=0; i<listCamp.size(); i++) {
			MstCampDate entity = (MstCampDate)listCamp.get(i);
			if(getCampId(windowId).equals(entity.getCampId())) {
				return entity;
			}
		}
		return null;
	}
	/**
	 * 対象条件取得処理
	 * @param windowId
	 * @return taishoJoken を戻します。
	 */
	public String getTaishoJoken(int windowId) {
		return (String)taishoJoken.get(new Integer(windowId));
	}

	/**
	 * 対象条件設定処理
	 * 
	 * @param windowId
	 * @param taishoJoken 設定する taishoJoken。
	 */
	public void setTaishoJoken(int windowId, String taishoJoken) {
		this.taishoJoken.put(new Integer(windowId), taishoJoken);
	}
    /**
     * 検索データ存在判断処理
     * 
     * @param windowId
     * @return
     */
    public boolean isExistSearchedData(int windowId) {
   		return listSearchData.containsKey(new Integer(windowId));
    }
    /**
     * 検索実行判断処理
     * 
     * @param windowId
     * @return
     */
    public boolean isMustSearch(int windowId) {
    	if(isSearched(windowId) && getCampId(windowId) != null) {
    		return !isExistSearchedData(windowId);
    	}
    	return false;
    }
    /**
     * 検索済み判断処理
     * 
     * @param windowId
     * @return
     */
    public boolean isSearched(int windowId) {
    	return campId.containsKey(new Integer(windowId));
    }
    /**
     * 照会画面タイプ取得処理
     * 
     * 例）日報系or推移系
     * @return
     */
    public abstract int getScreeanType();

    /**
     * 検索済みフラグ
     * @return
     */
    public boolean isSearchedFlg(int windowId) {
        if (searchedFlg.containsKey(new Integer(windowId))) {
            if (Boolean.TRUE.equals((Boolean) searchedFlg.get(new Integer(windowId)))) {
                return true;
            }
        }
        return false;
    }
    /**
     * 検索済みフラグ設定処理
     * @param flg  true:検索済み
     */
    public void setSearchedFlg(int windowId, boolean flg) {
        searchedFlg.put(new Integer(windowId), new Boolean(flg));
    }
    /**
     * 全WindowIDの検索済みフラグクリア
     */
    public void clearSearchedFlg() {
        searchedFlg = new HashMap();
    }
}