/*
 * 作成日: 2006/3/6
 */
package jp.co.isid.mos.bird.bizsupport.similarshop.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.GraphOutputDto;

import jp.co.isid.mos.bird.bizsupport.similarshop.entity.UIRuijiPLTenpoInfo;
import jp.co.isid.mos.bird.bizsupport.similarshop.entity.UIOnerTenpoInfo;
import jp.co.isid.mos.bird.bizsupport.similarshop.entity.UIUserOnerCdInfo;

/**
 * 類似店照会Dto
 * @author Nakajima
 */
public class RuijitenReferenceDto implements GraphOutputDto {


    private String _graphUrl;
    
    /**
     * @param _graphUrl _graphUrl を設定。
     */
    public void setGraphUrl(String graphUrl) {
        this._graphUrl = graphUrl;
    }
    /**
     * @return _graphUrl を戻します。
     */
    public String getGraphUrl() {
        return _graphUrl;
    }
	
	
	
	/* ログイン情報 */
    private BirdUserInfo birdUserInfo;

    // 条件部 /////////////////////////////////
	/* 店コード */
    private Map miseCd = new HashMap();
    /* 会社コード */
	private String companyCd;
	/* 会社＋店コード */
	private String companyMiseCd;
    /* エリア */
    private Map area = new HashMap();
    /* 店舗形態 */
    private Map tenpoKeitai = new HashMap();
    /* 立地 */
    private Map ritti = new HashMap();
    /* 売上FROM */
    private Map uriageFrom = new HashMap();
    /* 売上TO */
    private Map uriageTo = new HashMap();
    /* オープン日FROM */
    private Map openDtFrom = new HashMap();
    /* オープン日TO */
    private Map openDtTo = new HashMap();
    /* ユーザータイプコード */
    private String userTypeCd;
    /* 売上高 */
    private String uriagedaka;
    /* オープン日 */
    private String openDt;
    /* クリアーフラグ */
    private boolean clearFlg;
    /* 対象PL年月 */
    private String prevPlYm;
    /* SV制限フラグ */
    private boolean limitFlg;
    /* ユーザーID */
    private String userId;
    /* ウィンドウID */
    private int windowId;
    /* MAX値ウィンドウID */
    private int maxWindowId;
    /* 店コード */
    private Map tempMiseCd = new HashMap();
    /* エリア */
    private Map tempArea = new HashMap();
    /* 店舗形態 */
    private Map tempTenpoKeitai = new HashMap();
    /* 立地 */
    private Map tempRitti = new HashMap();
    /* 売上FROM */
    private Map tempUriageFrom = new HashMap();
    /* 売上TO */
    private Map tempUriageTo = new HashMap();
    /* オープン日FROM */
    private Map tempOpenDtFrom = new HashMap();
    /* オープン日TO */
    private Map tempOpenDtTo = new HashMap();
    
    // 共通フォーム呼出フラグ
    private Map callFormFlag = new HashMap();
    
    
	/* オーナー保有店舗一覧情報 */
    private List mstOnerHoyuMiseList;
    /* 指定店舗PLデータ一覧情報 */
    private UIRuijiPLTenpoInfo misePLData;
    /* 類似店舗PLデータ一覧情報 */
    private UIRuijiPLTenpoInfo miseRuijiPLData;
    /* 店舗データ */
    private UIOnerTenpoInfo tenpoData;
    /* ユーザーオーナーコードデータ */
    private UIUserOnerCdInfo userOnerCdData;
    
	/**
	 * オーナー保有店舗一覧の設定
	 * @return mstOnerHoyuMiseList を戻します。
	 */
	public List getMstOnerHoyuMiseList() {
		return mstOnerHoyuMiseList;
	}
	/**
	 * オーナー保有店舗一覧の設定
	 * @param mstOnerHoyuMiseList mstOnerHoyuMiseList を設定。
	 */
	public void setMstOnerHoyuMiseList(List mstOnerHoyuMiseList) {
		this.mstOnerHoyuMiseList = mstOnerHoyuMiseList;
	}
    
    /**
     * オーナー保有店舗一覧の件数を取得
     * @return オーナー保有店舗一覧の件数
     */
    public int getMstOnerHoyuMiseListSize(){
        List list = getMstOnerHoyuMiseList();
        if(list == null){
            return 0;
        }
        return list.size();
    }
    
   
	/**
	 * 指定店舗のPLデータの設定
	 * @return misePLData を戻します。
	 */
	public UIRuijiPLTenpoInfo getMisePLData() {
		return misePLData;
	}
	/**
	 * 指定店舗のPLデータの設定
	 * @param UIRuijiPLTenpoInfo misePLData を設定。
	 */
	public void setMisePLData(UIRuijiPLTenpoInfo misePLData) {
		this.misePLData = misePLData;
	}
	
	/**
	 * 類似店舗のPLデータの設定
	 * @return miseRuijiPLData を戻します。
	 */
	public UIRuijiPLTenpoInfo getMiseRuijiPLData() {
		return miseRuijiPLData;
	}
	/**
	 * 類似店舗のPLデータの設定
	 * @param List miseRuijiPLData を設定。
	 */
	public void setMiseRuijiPLData(UIRuijiPLTenpoInfo miseRuijiPLData) {
		this.miseRuijiPLData = miseRuijiPLData;
	}
	
	/**
	 * 対象店舗データの設定
	 * @return tenpoData を戻します。
	 */
	public UIOnerTenpoInfo getTenpoData() {
		return tenpoData;
	}
	/**
	 * 対象店舗のPLデータの設定
	 * @param List tenpoData を設定。
	 */
	public void setTenpoData(UIOnerTenpoInfo tenpoData) {
		this.tenpoData = tenpoData;
	}
	
	/**
     * 店コード設定
     * @return miseCd を戻します。
     */
    public String getMiseCd() {
        return (String) miseCd.get(new Integer(getWindowId()));
    }
    /**
     * 店コード設定
     * @param miseCd miseCd を設定。
     */
    public void setMiseCd(String miseCd) {
        this.miseCd.put(new Integer(getWindowId()), miseCd);
    }
	
	/**
	 * エリアの設定
	 * @return String を戻します。
	 */
	public String getArea() {
		return (String) area.get(new Integer(getWindowId()));
	}
	/**
	 * エリアの設定
	 * @param String area を設定。
	 */
	public void setArea(String area) {
		this.area.put(new Integer(getWindowId()), area);
	}
    
	/**
	 * 店舗形態の設定
	 * @return String を戻します。
	 */
	public String getTenpoKeitai() {
		return (String) tenpoKeitai.get(new Integer(getWindowId()));
	}
	/**
	 * 店舗形態の設定
	 * @param String tenpoKeitai を設定。
	 */
	public void setTenpoKeitai(String tenpoKeitai) {
		this.tenpoKeitai.put(new Integer(getWindowId()), tenpoKeitai);
	}
    
	
	/**
	 * 会社コードの設定
	 * @return companayCd を戻します。
	 */
	public String getCompanyCd() {
		return companyCd;
	}
	/**
	 * 会社コードの設定
	 * @param companyCd companyCd を設定。
	 */
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}
	
	/**
	 * 会社＋店コードの設定
	 * @return companayMiseCd を戻します。
	 */
	public String getCompanyMiseCd() {
		return companyMiseCd;
	}
	/**
	 * 会社＋店コードの設定
	 * @param companyMiseCd companyMiseCd を設定。
	 */
	public void setCompanyMiseCd(String companyMiseCd) {
		this.companyMiseCd = companyMiseCd;
	}
	
	/**
	 * 立地の設定
	 * @return String を戻します。
	 */
	public String getRitti() {
		return (String) ritti.get(new Integer(getWindowId()));
	}
	/**
	 * 立地の設定
	 * @param String ritti を設定。
	 */
	public void setRitti(String ritti) {
		this.ritti.put(new Integer(getWindowId()), ritti);
	}
	
	/**
	 * 売上FROMの設定
	 * @return String を戻します。
	 */
	public String getUriageFrom() {
		return (String) uriageFrom.get(new Integer(getWindowId()));
	}
	/**
	 * 売上FROMの設定
	 * @param String uriageFrom を設定。
	 */
	public void setUriageFrom(String uriageFrom) {
		this.uriageFrom.put(new Integer(getWindowId()), uriageFrom);
	}
	
	/**
	 * 売上TOの設定
	 * @return String を戻します。
	 */
	public String getUriageTo() {
		return (String) uriageTo.get(new Integer(getWindowId()));
	}
	/**
	 * 売上TOの設定
	 * @param String uriageTo を設定。
	 */
	public void setUriageTo(String uriageTo) {
		this.uriageTo.put(new Integer(getWindowId()), uriageTo);
	}
	
	/**
	 * OPEN日FROMの設定
	 * @return String を戻します。
	 */
	public String getOpenDtFrom() {
		return (String) openDtFrom.get(new Integer(getWindowId()));
	}
	/**
	 * OPEN日FROMの設定
	 * @param String openDtFrom を設定。
	 */
	public void setOpenDtFrom(String openDtFrom) {
		this.openDtFrom.put(new Integer(getWindowId()), openDtFrom);
	}
	
	/**
	 * OPEN日TOの設定
	 * @return String を戻します。
	 */
	public String getOpenDtTo() {
		return (String) openDtTo.get(new Integer(getWindowId()));
	}
	/**
	 * OPEN日TOの設定
	 * @param String openDtTo を設定。
	 */
	public void setOpenDtTo(String openDtTo) {
		this.openDtTo.put(new Integer(getWindowId()), openDtTo);
	}
    
	/**
	 * ログインユーザータイプコード判定(オーナー、本部)の設定
	 * @return userTypeCd を戻します。
	 */
	public String getUserTypeCd() {
		return userTypeCd;
	}
	/**
	 * ログインユーザータイプコード判定(オーナー、本部)の設定
	 * @param String userTypeCd を設定。
	 */
	public void setUserTypeCd(String userTypeCd) {
		this.userTypeCd = userTypeCd;
	}
	
	/**
	 * 売上高の設定
	 * @return String を戻します。
	 */
	public String getUriagedaka() {
		return uriagedaka;
	}
	/**
	 * 売上高の設定
	 * @param String uriagedaka を設定。
	 */
	public void setUriagedaka(String uriagedaka) {
		this.uriagedaka = uriagedaka;
	}
	
	/**
	 * オープン日の設定
	 * @return String を戻します。
	 */
	public String getOpenDt() {
		return openDt;
	}
	/**
	 * オープン日の設定
	 * @param String openDt を設定。
	 */
	public void setOpenDt(String openDt) {
		this.openDt = openDt;
	}
	
	
//	/**
//	 * BIRDログイン情報の設定
//	 * @return birdUserInfo を戻します。
//	 */
//	public BirdUserInfo getBirdUserInfo() {
//		return birdUserInfo;
//	}
//	/**
//	 * BIRDログイン情報の設定
//	 * @param birdUserInfo birdUserInfo を設定。
//	 */
//	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
//		this.birdUserInfo = birdUserInfo;
//	}
	
	/**
	 * 賞与(指定店舗PLデータ)を返す
	 * @return BigDecimal を戻します。
	 */
	public BigDecimal getShouyo() {
		BigDecimal decBonusKei  = new BigDecimal(0);
		BigDecimal decRetireKei = new BigDecimal(0);
		if(misePLData != null ){
			decBonusKei  = misePLData.getBonusKei();
			decRetireKei = misePLData.getRetireKei();
		}
		decBonusKei = decBonusKei.add(decRetireKei);
		return decBonusKei;
	}
	
	/**
	 * 賞与(類似店PLデータ)を返す
	 * @return BigDecimal を戻します。
	 */
	public BigDecimal getShouyoRuijiten() {
		BigDecimal decBonusKei  = new BigDecimal(0);
		BigDecimal decRetireKei = new BigDecimal(0);
		if(miseRuijiPLData != null ){
			decBonusKei  = miseRuijiPLData.getBonusKei();
			decRetireKei = miseRuijiPLData.getRetireKei();
		}
		decBonusKei = decBonusKei.add(decRetireKei);
		return decBonusKei;
	}
	
	/**
	 * オーナーコードの設定
	 * @return userOnerCdData を戻します。
	 */
	public UIUserOnerCdInfo getUserOnerCdData() {
		return userOnerCdData;
	}
	/**
	 * オーナーコードの設定
	 * @param UIUserOnerCdInfo onerCd を設定。
	 */
	public void setUserOnerCdData(UIUserOnerCdInfo userOnerCdData) {
		this.userOnerCdData = userOnerCdData;
	}
    
    /**
     * クリアーフラグの設定
     * @return clearFlg を戻します。
     */
    public boolean getClearFlg() {
        return clearFlg;
    }
    /**
     * クリアーフラグの設定
     * @param boolean clearFlg を設定。
     */
    public void setClearFlg(boolean clearFlg) {
        this.clearFlg = clearFlg;
    }
    
    /**
     * 対象PL年月の設定
     * @return String を戻します。
     */
    public String getPrevPlYm() {
        return prevPlYm;
    }
    /**
     * 対象PL年月の設定
     * @param String prevPlYm を設定。
     */
    public void setPrevPlYm(String prevPlYm) {
        this.prevPlYm = prevPlYm;
    }
    
    /**
     * SV制限フラグの設定
     * @return boolean を戻します。
     */
    public boolean getLimitFlg() {
        return limitFlg;
    }
    /**
     * SV制限フラグの設定
     * @param boolean limitFlg を設定。
     */
    public void setLimitFlg(boolean limitFlg) {
        this.limitFlg = limitFlg;
    }
    
    /**
     * ユーザーIDの設定
     * @return String を戻します。
     */
    public String getUserId() {
        return userId;
    }
    /**
     * ユーザーIDの設定
     * @param String userId を設定。
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * ウィンドウIDの取得
     * @return int を戻します。
     */
    public int getWindowId() {
        return windowId;
    }
    /**
     * ウィンドウIDの設定
     * @param int windowId を設定。
     */
    public void setWindowId(int windowId) {
        this.windowId = windowId;
    }
    
    /**
     * MAX値ウィンドウIDの取得
     * @return int を戻します。
     */
    public int getMaxWindowId() {
        return maxWindowId;
    }
    /**
     * MAX値ウィンドウIDの設定
     * @param int maxWindowId を設定。
     */
    public void setMaxWindowId(int maxWindowId) {
        this.maxWindowId = maxWindowId;
    }
    
    /**
     * ウィンドウIDの生成
     * @return int を戻します。
     */
    public int createWindowId() {
        int newWindowId = getMaxWindowId() + 1;
        setMaxWindowId(newWindowId);
        return newWindowId;
    }
    
    /**
     * ウィンドウIDの更新
     */
    public void updateWindowid() {
        setWindowId(createWindowId());
    }

    
    /**
     * 店コード取得（退避用）
     * @return tempMiseCd を戻します。
     */
    public String getTempMiseCd() {
        return (String) tempMiseCd.get(new Integer(getWindowId()));
    }
    /**
     * 店コード設定（退避用）
     * @param String tempMiseCd を設定。
     */
    public void setTempMiseCd(String tempMiseCd) {
        this.tempMiseCd.put(new Integer(getWindowId()), tempMiseCd);
    }
    
    /**
     * エリアの取得（退避用）
     * @return String を戻します。
     */
    public String getTempArea() {
        return (String) tempArea.get(new Integer(getWindowId()));
    }
    /**
     * エリアの設定（退避用）
     * @param String tempArea を設定。
     */
    public void setTempArea(String tempArea) {
        this.tempArea.put(new Integer(getWindowId()), tempArea);
    }
    
    /**
     * 店舗形態の取得（退避用）
     * @return String を戻します。
     */
    public String getTempTenpoKeitai() {
        return (String) tempTenpoKeitai.get(new Integer(getWindowId()));
    }
    /**
     * 店舗形態の設定（退避用）
     * @param String tempTenpoKeitai を設定。
     */
    public void setTempTenpoKeitai(String tempTenpoKeitai) {
        this.tempTenpoKeitai.put(new Integer(getWindowId()), tempTenpoKeitai);
    }
    
    /**
     * 立地の取得（退避用）
     * @return String を戻します。
     */
    public String getTempRitti() {
        return (String) tempRitti.get(new Integer(getWindowId()));
    }
    /**
     * 立地の設定（退避用）
     * @param String tempRitti を設定。
     */
    public void setTempRitti(String tempRitti) {
        this.tempRitti.put(new Integer(getWindowId()), tempRitti);
    }
    
    /**
     * 売上FROMの取得（退避用）
     * @return String を戻します。
     */
    public String getTempUriageFrom() {
        return (String) tempUriageFrom.get(new Integer(getWindowId()));
    }
    /**
     * 売上FROMの設定（退避用）
     * @param String tempUriageFrom を設定。
     */
    public void setTempUriageFrom(String tempUriageFrom) {
        this.tempUriageFrom.put(new Integer(getWindowId()), tempUriageFrom);
    }
    
    /**
     * 売上TOの取得（退避用）
     * @return String を戻します。
     */
    public String getTempUriageTo() {
        return (String) tempUriageTo.get(new Integer(getWindowId()));
    }
    /**
     * 売上TOの設定（退避用）
     * @param String tempUriageTo を設定。
     */
    public void setTempUriageTo(String tempUriageTo) {
        this.tempUriageTo.put(new Integer(getWindowId()), tempUriageTo);
    }
    
    /**
     * OPEN日FROMの取得（退避用）
     * @return String を戻します。
     */
    public String getTempOpenDtFrom() {
        return (String) tempOpenDtFrom.get(new Integer(getWindowId()));
    }
    /**
     * OPEN日FROMの設定（退避用）
     * @param String tempOpenDtFrom を設定。
     */
    public void setTempOpenDtFrom(String tempOpenDtFrom) {
        this.tempOpenDtFrom.put(new Integer(getWindowId()), tempOpenDtFrom);
    }
    
    /**
     * OPEN日TOの取得（退避用）
     * @return String を戻します。
     */
    public String getTempOpenDtTo() {
        return (String) tempOpenDtTo.get(new Integer(getWindowId()));
    }
    /**
     * OPEN日TOの設定（退避用）
     * @param String tempOpenDtTo を設定。
     */
    public void setTempOpenDtTo(String tempOpenDtTo) {
        this.tempOpenDtTo.put(new Integer(getWindowId()), tempOpenDtTo);
    }
    
    /**
     * 店選択ボタンの処理後か判定
     * @param boolean を設定。
     */
    public boolean isCallFormFlag() {
        Boolean flag = (Boolean) callFormFlag.get(new Integer(getWindowId()));
        return (flag == null) ? false : flag.booleanValue();
    }
    /**
     * 店選択ボタンの処理後か判定
     * @param boolean を設定。
     */
    public void setCallFormFlag(boolean callFormFlag) {
        this.callFormFlag.put(new Integer(getWindowId()), Boolean.valueOf(callFormFlag));
    }

}
