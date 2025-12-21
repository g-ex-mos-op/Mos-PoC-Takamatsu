/**
 * 2008/06/20
 */
package jp.co.isid.mos.bird.storemanage.claimref.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.entity.CodHyojiTaisho;
import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.storemanage.claimref.util.VoiceRefUtil;

/**
 * 個店管理：『お客様の声』
 * セッションDTO
 * 
 * @author xkinu
 *
 */
public class SessionDto {
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
     * 最大ウィンドウID
     */
    private int maxWindowId = 0;
    /**
     * 最大データ保持件数
     */
    private int maxSize=5;
    /**
     * 会社コード
     */ 
    private Map companyCd= new HashMap();
    /**
     * 表示内容
     */ 
    private Map hyojiNaiyo= new HashMap();
    /**
     * 中分類コード
     */ 
    private Map bnrM= new HashMap();
    /**
     * 対象年月プルダウンリスト情報保持リスト
     */
    private List listTaishoNengetu = new ArrayList(0);
    /**
     * 対象年月
     */ 
    private Map taishoNengetu= new HashMap();
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
    private List listBlock = new ArrayList(0);
    /**
     * 表示対象
     */ 
    private Map hyojiTaisho = new HashMap();
    private Map blockCd = new HashMap();
    
    private Map requestDto = new HashMap();
    /**
     * 検索結果
     */
    private Map listSearchData = new LinkedHashMap();
    /**
     * 検索済みフラグ
     */
    private Map searchedFlg = new HashMap();
    /**
     * 検索結果詳細
     */
    private Map listInfoData = new LinkedHashMap();
    /**
     * 個店ポータル画面ロール制御フラグ
     */
    private boolean viewKotenPortal = false;
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
	 * @return maxSize を戻します。
	 */
	public int getMaxSize() {
		return maxSize;
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
	 * 表示対象プルダウンリスト取得処理
	 * @return listHyojiTaisho を戻します。
	 */
	public List getListHyojiTaisho(String companyCd, String taishoJoken) {
		if(this.listHyojiTaisho.containsKey(companyCd)) {
			return (List)((Map)(this.listHyojiTaisho.get(companyCd))).get(taishoJoken);
		}
		return null;
	}

	/**
	 * 表示対象プルダウンリスト設定処理
	 * @param list 設定する listHyojiTaisho。
	 */
	public void setListHyojiTaisho(String companyCd, String taishoJoken, List list) {
		Map mapHyojiTaisho = null;
		if(this.listHyojiTaisho.containsKey(companyCd)) {
			mapHyojiTaisho = (Map)(this.listHyojiTaisho.get(companyCd));
		}
		else {
			mapHyojiTaisho = new HashMap();
		}
		mapHyojiTaisho.put(taishoJoken, list);
		this.listHyojiTaisho.put(companyCd, mapHyojiTaisho);
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
     * 検索実行判断処理
     * 
     * @param windowId
     * @return
     */
    public boolean isMustSearch(int windowId) {
    	if(isSearched(windowId) && getCompanyCd(windowId) != null) {
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
    	return companyCd.containsKey(new Integer(windowId));
    }

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
	 * 表示内容取得処理
	 * @param windowId
	 * @return hyojiNaiyo を戻します。
	 */
	public String getHyojiNaiyo(int windowId) {
		return (String)hyojiNaiyo.get(new Integer(windowId));
	}

	/**
	 * 表示内容設定処理
	 * 
	 * @param windowId
	 * @param code 設定する hyojiNaiyo。
	 */
	public void setHyojiNaiyo(int windowId, String code) {
		this.hyojiNaiyo.put(new Integer(windowId), code);
	}
	/**
	 * 中分類コード取得処理
	 * @param windowId
	 * @return bnrM を戻します。
	 */
	public String getBnrM(int windowId) {
		return (String)bnrM.get(new Integer(windowId));
	}

	/**
	 * 中分類コード設定処理
	 * 
	 * @param windowId
	 * @param code 設定する bnrM。
	 */
	public void setBnrM(int windowId, String code) {
		this.bnrM.put(new Integer(windowId), code);
	}
	/**
	 * 対象年月取得処理
	 * 
	 * @param windowId
	 * @return taishoNengetu を戻します。
	 */
	public String getTaishoNengetu(int windowId) {
		return (String)taishoNengetu.get(new Integer(windowId));
	}

	/**
	 * 対象年月設定処理
	 * 
	 * @param windowId
	 * @param code 設定する taishoNengetu。
	 */
	public void setTaishoNengetu(int windowId, String code) {
		this.taishoNengetu.put(new Integer(windowId), code);
	}
	/**
	 * 対象条件プルダウンリスト取得処理
	 * 
	 * @return listTaishoJoken を戻します。
	 */
	public List getListTaishoJoken(String companyCd) {
		if(UserType.HONBU.equals(getUserTypeCd())) {
			return TaishoJoken.getPullDownList(getUserTypeCd()
					, companyCd
					, getBirdUserInfo().isLimit(), VoiceRefUtil.TAISHOJOKEN_CODES_HONBU);
		}
		return TaishoJoken.getPullDownList(getUserTypeCd()
				, companyCd
				, getBirdUserInfo().isLimit());
		
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
	 * 表示対象設定処理
	 * 
	 * @param windowId
	 * @param hyojiTaisho 設定する hyojiTaisho。
	 */
	public void setHyojiTaisho(int windowId, String hyojiTaisho) {
		this.hyojiTaisho.put(new Integer(windowId), hyojiTaisho);
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
	 * 表示対象名称
	 * @return hyojiTaishoName を戻します。
	 */
	public String getHyojiTaishoName(String companyCd, String taishoJoken, String hyojiTaisho) {
		List listCode = getListHyojiTaisho(companyCd, taishoJoken);
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
	 * 指定会社コード全表示対象プルダウンリスト取得処理
	 * 
	 * @param companyCd　指定会社コード
	 * @return
	 */
	public List getListsHyojiTaisho(String companyCd) {
		List listTaishoJoken = getListTaishoJoken(companyCd);
		List lists = new ArrayList(0);
		if(listTaishoJoken != null) {
			for(int i=0; i<listTaishoJoken.size(); i++) {
				String code = (String)((SelectItem)listTaishoJoken.get(i)).getValue();
				UILists mLists = new UILists();
				mLists.setKey(code);
				mLists.setKeyName(((SelectItem)listTaishoJoken.get(i)).getLabel());
				mLists.setListData(getListHyojiTaisho(companyCd, code));
				lists.add(mLists);
			}
		}
		return lists;
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
	 * @return listInfoData を戻します。
	 */
	public List getListInfoData(int windowId) {
		return (List)listInfoData.get(new Integer(windowId));
	}

	/**
	 * 検索結果設定処理
	 * 
	 * @param windowId
	 * @param listInfoData
	 */
	public void setListInfoData(int windowId, List listSearchData) {
        // 現在ウインドウIDのデータを保持している場合
        if (this.listInfoData.containsKey(new Integer(windowId))) {
            // 順番を入れ替える為、前回データを削除する
            this.listInfoData.remove(new Integer(windowId));
        // 最大数を超えた場合
        } else if (this.listInfoData.size() >= getMaxSize()) {
            // 最古データを削除
            this.listInfoData.remove(this.listInfoData.keySet().toArray()[0]);
        }       	
    	this.listInfoData.put(new Integer(windowId), listSearchData);
	}
	/** 
	 * ユーザータイプコード取得処理 
	 * 
	 * @return
	 */
	public String getUserTypeCd() {
		return getBirdUserInfo().getMstUser().getUserTypeCd();
	}
	/**
	 * 検索済み条件値設定処理
	 * @param dto
	 */
	public void setSearchedData(RequestDto dto) {
		int windowId = dto.getWindowId();
		setCompanyCd(windowId, dto.getCompanyCd());
		setHyojiNaiyo(windowId, dto.getHyojiNaiyo());
		setTaishoNengetu(windowId, dto.getTaishoNengetu());
		setTaishoJoken(windowId, dto.getTaishoJoken());
		setHyojiTaisho(windowId, dto.getHyojiTaisho());
		setBnrM(windowId, dto.getBnrM());
		setBlockCd(windowId, dto.getBlockCd());
	}
	/**
	 * 検索結果該当データなし対象情報を全てNullを設定します。
	 *  
	 * @param windowId
	 */
	public void setNoResultSearchedData(int windowId) {
		setCompanyCd(windowId, null);
		setHyojiNaiyo(windowId, null);
		setTaishoNengetu(windowId, null);
		setTaishoJoken(windowId, null);
		setHyojiTaisho(windowId, null);
		setBnrM(windowId, null);
		setBlockCd(windowId, null);
		//検索データ設定
		removeListSearchData(windowId);
	}
	/**
	 * 指定RequestDTOへ検索済み条件値格納します。
	 */
	public void getSearchedDataDto(int windowId, RequestDto searchedDataDto) {
		searchedDataDto.setWindowId(windowId);
		searchedDataDto.setCompanyCd(getCompanyCd(windowId));
		/* プルダウン情報を設定します。*/
		searchedDataDto.setPullDownData(this);
		searchedDataDto.setHyojiNaiyo(getHyojiNaiyo(windowId));
		searchedDataDto.setTaishoNengetu(getTaishoNengetu(windowId));
		searchedDataDto.setTaishoJoken(getTaishoJoken(windowId));
		searchedDataDto.setHyojiTaisho(getHyojiTaisho(windowId));
		searchedDataDto.setBnrM(getBnrM(windowId));
		searchedDataDto.setBlockCd(getBlockCd(windowId));
		//検索データ設定
		searchedDataDto.setListSearchData(getListSearchData(windowId));
		
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
	public void setListTaishoNengetu() {
		this.listTaishoNengetu = VoiceRefUtil.creatListNengetu(getBirdDateInfo().getSysDate(), 25);
	}
	/**
	 * 本部ユーザ判別処理
	 * 
	 * @return
	 */
	public boolean isHonbuUser() {
		if(UserType.HONBU.equals(getUserTypeCd())) {
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
	public String getBlockCd(int windowId) {
		return (String)(blockCd.get(new Integer(windowId)));
	}

	/**
	 * @param blockCd 設定する blockCd。
	 */
	public void setBlockCd(int windowId, String blockCd) {
		this.blockCd.put(new Integer(windowId), blockCd);
	}

	/**
	 * @return viewKotenPortal を戻します。
	 */
	public boolean isViewKotenPortal() {
		return viewKotenPortal;
	}

	/**
	 * @param viewKotenPortal 設定する viewKotenPortal。
	 */
	public void setViewKotenPortal(boolean viewKotenPortal) {
		this.viewKotenPortal = viewKotenPortal;
	}

	/**
	 * 
	 * @param windowId
	 * @return requestDto を戻します。
	 */
	public RequestDto getRequestDto(int windowId) {
		return (RequestDto)requestDto.get(new Integer(windowId));
	}

	/**
	 * @param requestDto 設定する requestDto。
	 */
	public void setRequestDto(int windowId, RequestDto requestDto) {
		this.requestDto.put(new Integer(windowId), requestDto);
	}

}
