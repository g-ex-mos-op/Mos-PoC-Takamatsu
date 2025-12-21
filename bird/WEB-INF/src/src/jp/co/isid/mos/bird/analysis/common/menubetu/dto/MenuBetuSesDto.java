/**
 * 
 */
package jp.co.isid.mos.bird.analysis.common.menubetu.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.entity.CodBlock;
import jp.co.isid.mos.bird.common.entity.CodHyojiTaisho;
import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * メニュー別リクエスト用DTO
 * 
 * 作成日:2008/09/22
 * @author xkinu
 *
 */
public abstract class MenuBetuSesDto {
    
    private static final String BLOCK_ALL = "全ブロック"; 
    
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
     * 対象条件プルダウンリスト
     * 
     */ 
    private List listTaishoJoken= new ArrayList(0);
    /**
     * 対象条件プルダウンリスト
     * 
     */ 
    private List listTaishoKikan= new ArrayList(0);
    /**
     * ブロックリスト
     */
    private List blockList = new ArrayList(0);
    /**
     * 対象期間指定プルダウンリスト
     * 
     */ 
    private List listKikanShitei= new ArrayList(0);
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
     * 対象条件
     */ 
    private Map taishoJoken= new HashMap();
    /**
     * 表示対象
     */ 
    private Map hyojiTaisho = new HashMap();
    /**
     * ブロックコード
     */
    private Map blockCd = new HashMap();
    /**
     * 対象期間
     */ 
    private Map taishoKikan= new HashMap();
    /**
     * 期間指定
     */ 
    private Map kikanSitei = new HashMap();
    /**
     * 期間期間Ｔｏ
     */ 
    private Map kikanSiteiDay = new HashMap();
    /**
     * 検索結果
     */
    private Map listSearchData = new LinkedHashMap();
    /**
     * 共通画面遷移時のDTO保持オブジェクト
     */
    private Map holdReqDto = new HashMap();
    /**
	 * @return holdReqDto を戻します。
	 */
	public MenuBetuReqDto getHoldReqDto(int windowId) {
		return (MenuBetuReqDto)holdReqDto.get(new Integer(windowId));
	}

	/**
	 * @param holdReqDto を クラス変数holdReqDtoへ設定します。
	 */
	public void setHoldReqDto(int windowId, MenuBetuReqDto holdReqDto) {
		this.holdReqDto.put(new Integer(windowId), holdReqDto);
	}

	/**
	 * @return birdDateInfo を戻します。
	 */
	public BirdDateInfo getBirdDateInfo() {
		return birdDateInfo;
	}

	/**
	 * @param birdDateInfo を クラス変数birdDateInfoへ設定します。
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
	 * @param birdUserInfo を クラス変数birdUserInfoへ設定します。
	 */
	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}
	/**
	 * ログインユーザータイプコード取得処理
	 * 
	 * @return
	 */
	public String getUserTypeCd() {
		return getBirdUserInfo().getMstUser().getUserTypeCd();
	}
	/**
	 * @return listCompany を戻します。
	 */
	public List getListCompany() {
		return listCompany;
	}

	/**
	 * @param listCompany を クラス変数listCompanyへ設定します。
	 */
	public void setListCompany(List listCompany) {
		this.listCompany = listCompany;
	}

	/**
	 * @return maxSize を戻します。
	 */
	public int getMaxSize() {
		return maxSize;
	}

	/**
	 * @param maxSize を クラス変数maxSizeへ設定します。
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
	 * @param maxWindowId を クラス変数maxWindowIdへ設定します。
	 */
	public void setMaxWindowId(int maxWindowId) {
		this.maxWindowId = maxWindowId;
	}

	/**
     * ウィンドウIDを生成します。
     */
    public int createWindowId() {
        int newWindowId = getMaxWindowId() + 1;
        setMaxWindowId(newWindowId);
        return newWindowId;
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
	 * 表示対象取得処理
	 * @param windowId
	 * @return hyojiTaisho を戻します。
	 */
	public String getHyojiTaisho(int windowId) {
		return (String)hyojiTaisho.get(new Integer(windowId));
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
     * ブロックコード を戻します。
     * @return ブロックコード
     */
    public String getBlockCd(int windowId) {
        return (String)blockCd.get(new Integer(windowId));
    }

    /**
     * ブロックコード を設定します。
     * @param ブロックコード
     */
    public void setBlockCd(int windowId, String blockCd) {
        this.blockCd.put(new Integer(windowId), blockCd);
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
        if (isExistSearchedData(windowId)) {
            // 順番を入れ替える為、前回データを削除する
        	removeListSearchData(windowId);
        // 最大数を超えた場合
        } else if (this.listSearchData.size() >= getMaxSize()) {
            // 最古データを削除
            this.listSearchData.remove(this.listSearchData.keySet().toArray()[0]);
        }       	
    	this.listSearchData.put(new Integer(windowId), listSearchData);
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
    	if(isSearched(windowId)) {
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
    	return (companyCd.containsKey(new Integer(windowId)) && !CommonUtil.isNull(getCompanyCd(windowId)));
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
	 * 対象期間取得処理
	 * @return taishoKikan を戻します。
	 */
	public String getTaishoKikan(int windowId) {
		return (String)taishoKikan.get(new Integer(windowId));
	}

	/**
	 * 対象期間設定処理
	 * @param taishoKikan を クラス変数taishoKikanへ設定します。
	 */
	public void setTaishoKikan(int windowId, String taishoKikan) {
		this.taishoKikan.put(new Integer(windowId), taishoKikan);
	}
	/**
	 * 期間指定取得処理
	 * 
	 * @return kikanSitei を戻します。
	 */
	public String getKikanSitei(int windowId) {
		return (String)kikanSitei.get(new Integer(windowId));
	}

	/**
	 * 期間指定設定処理
	 * 
	 * @param kikanSitei を クラス変数kikanSiteiへ設定します。
	 */
	public void setKikanSitei(int windowId, String kikanSitei) {
		this.kikanSitei.put(new Integer(windowId), kikanSitei);
	}
    /**
     * @return kikanSiteiTo を戻します。
     */
    public String getKikanSiteiDay(int windowId) {
        return (String)kikanSiteiDay.get(new Integer(windowId));
    }

    /**
     * @param kikanSiteiTo を クラス変数kikanSiteiToへ設定します。
     */
    public void setKikanSiteiDay(int windowId, String kikanSitei) {
        this.kikanSiteiDay.put(new Integer(windowId), kikanSitei);
    }
	/**
	 * 表示対象取得処理
	 * @return listHyojiTaisho を戻します。
	 */
	public List getListHyojiTaisho(String taishoJoken) {
		if(getListTaishoJoken() != null) {
			for(int i=0; i<getListTaishoJoken().size(); i++) {
				UILists info = (UILists)getListTaishoJoken().get(i);
				if(info.getKey().equals(taishoJoken)) {
					return info.getListData();
				}
			}
		}
		return null;
	}
	/**
	 * 対象条件プルダウンリストデータ取得処理
	 * 
	 * @return listTaishoJoken を戻します。
	 */
	public List getListTaishoJoken() {
		return listTaishoJoken;
	}

	/**
	 * 対象条件プルダウンリストデータ設定処理
	 * @param list を クラス変数listTaishoJokenへ設定します。
	 */
	public void setListTaishoJoken(List list) {
		this.listTaishoJoken = list;
	}
    
    /**
     * ブロックリスト を戻します。
     * @return ブロックリスト
     */
    public List getBlockList() {
        return blockList;
    }

    /**
     * ブロックリストを設定します。
     * @param blockList ブロックリスト
     */
    public void setBlockList(List blockList) {
        this.blockList = blockList;
    }

	/**
	 * @return listTaishoKikan を戻します。
	 */
	public List getListTaishoKikan() {
		return listTaishoKikan;
	}

	/**
	 * @param listTaishoKikan を クラス変数listTaishoKikanへ設定します。
	 */
	public void setListTaishoKikan(List list) {
		this.listTaishoKikan = list;
	}

    /**
     * @return listTaishoKikan を戻します。
     */
    public List getListKikanShitei() {
        return listKikanShitei;
    }

    /**
     * @param listTaishoKikan を クラス変数listTaishoKikanへ設定します。
     */
    public void setListKikanShitei(List llistKikanShitei) {
        this.listKikanShitei = llistKikanShitei;
    }

	/**
	 * 
	 * @param listHyojiTaisho を クラス変数listHyojiTaishoへ設定します。
	 */
	public void setListHyojiTaisho(String taishoJoken, List listHyojiTaisho) {
		if(getListTaishoJoken() != null) {
			for(int i=0; i<getListTaishoJoken().size(); i++) {
				UILists info = (UILists)getListTaishoJoken().get(i);
				if(info.getKey().equals(taishoJoken)) {
					info.setListData(listHyojiTaisho);
					break;
				}
			}
		}
	}

    /**
	 * 表示対象取得処理
	 * @param windowId
	 * @return hyojiTaisho を戻します。
	 */
	public String getHyojiTaishoName(String taishoJoken, String hyojiTaisho) {
		for(int i=0; i<getListTaishoJoken().size(); i++) {
			UILists uiInfo = (UILists)getListTaishoJoken().get(i);
			if(uiInfo.getKey().equals(taishoJoken)) {
				List listHyojiTaisho = uiInfo.getListData();
				if(listHyojiTaisho ==null) {
					break;
				}
				for(int h=0; h<listHyojiTaisho.size(); h++) {
					CodHyojiTaisho entity = (CodHyojiTaisho)listHyojiTaisho.get(h);
					if(entity.getHyojitaishoCd().equals(hyojiTaisho)) {
						return entity.getHyojitaishoName();
					}
				}
			}
		}
		return "";
	}

    /**
     * ブロック名称 を戻します。
     * @return ブロック名称
     */
    public String getBlockName(String blockCd) {
        String blockName = null;
        
        if(getBlockList() == null) {
            blockName = null;
        } else if (blockCd == null || blockCd.equals("")) {
            // 空の場合は全ブロック
            blockName = BLOCK_ALL;
        } else {

            for (int i = 0; i < getBlockList().size(); i++) {
                CodBlock data = (CodBlock) getBlockList().get(i);
                if(data.getBlockCd().equals(blockCd)) {
                    blockName = data.getBlockName();
                    break;
                }
            }
        }
        
        return  blockName;
    }
    
	/**
	 * 表示対象取得処理
	 * @param windowId
	 * @return hyojiTaisho を戻します。
	 */
	public String getHyojiTaishoName(int windowId) {
		return getHyojiTaishoName(getTaishoJoken(windowId), getHyojiTaisho(windowId));
	}

}
