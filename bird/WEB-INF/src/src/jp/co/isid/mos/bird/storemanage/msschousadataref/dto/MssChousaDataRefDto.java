package jp.co.isid.mos.bird.storemanage.msschousadataref.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.storemanage.msschousadataref.entity.CodCompany;
import jp.co.isid.mos.bird.storemanage.msschousadataref.entity.MssFileInfo;
import jp.co.isid.mos.bird.storemanage.msschousadataref.entity.MstUserMise;
import jp.co.isid.mos.bird.storemanage.msschousadataref.entity.MstUserOner;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;


/**
 * 調査データ分析画面DTO
 * 作成日：2006/08/03
 * @author K.INAZAWA(ASPAC)
 */
public class MssChousaDataRefDto implements DownloadDto {
    /* VIEW_ID */
    public final String VIEW_ID				= "BSM011";
    public final String condition_VIEW_ID		= "BSM011V01";
    public final String result_VIEW_ID			= "BSM012V02";
    public final String onerSearch_VIEW_ID		= "BCO006V01";
    public final String miseSearch_VIEW_ID		= "BCO008V01";
    
    /* 条件画面表示モード 非表示:0、表示:1 */
    private int selectViewMode;

    /* 会社コード */
    private String companyCd;
    
    /* 会社名 */
    private String companyName;
    
    /*ユーザーID */
    private String userId;

    /* 本部コード */
    private String sitenCd;
    
    /* オーナーコード */
    private String onerCd;
    /* オーナー名称 */
    private String onerName;
    
    /* 店舗コード */
    private String miseCd;
    
    /* 選択Flage 0:店舗　1:オーナー　*/
    private int selectFlg; 
    
     /* 実施年度 */
    private String jissiNendo;
    
    /* 年度リスト */
    private List taishoNendo;

    /* 実施回 */
    private String kai;
    
    
    /* 	ユーザタイプ */
    private String usertypeCd;
    
    /* 結果リスト */
    private List resultList;
    
    /* ファイルリスト */
    private List fileList;

    /* 対象回数タブリスト */
    private List tabList;

    /* 店リスト */
    private List miseList;

    /* オーナーリスト */
    private List onerList;

    /* 分析名リスト */
    private List bunsekiList;

    /* システム日付 */
    private String sysDate;

    /* データ区分*/
    private String dataKbn;

    /* 店情報*/
    private MstUserMise mstUserMise;
    
    /* オーナー情報*/
    private MstUserOner mstUserOner;
    
    /* ファイル情報*/
    private MssFileInfo mssFileInfo;
    
    /*ダウンロード対象タブindex*/
    private int downloadIndex;
    /*ダウンロード対象タブ保有データリストindex*/
    private int listIndex;

    /*分析名称取得*/
    private String bunsekiName;

    /*再検索フラグ*/
    private String reExecuteFlg;
    /*会社リスト*/
    List companyList;
/**
	 * 条件画面表示モードの設定
	 * @return selectViewMode を戻します。
	 */
	public int getSelectViewMode() {
		return selectViewMode;
	}
	/**
	 * 条件画面表示モードの設定
	 * @param selectViewMode selectViewMode を設定。
	 */
	public void setSelectViewMode(int selectViewMode) {
		this.selectViewMode = selectViewMode;
	}
	
 	/**
	 * @return miseList を戻します。
	 */
	public List getMiseList() {
		return miseList;
	}
	/**
	 * @param miseList miseList を設定。
	 */
	public void setMiseList(List miseList) {
		this.miseList = miseList;
	}
	/**
	 * @return usertypeCd を戻します。
	 */
	public String getUsertypeCd() {
		return usertypeCd;
	}
	
	/**
	 * @return resultList を戻します。
	 */
	public List getResultList() {
		return resultList;
	}
	/**
	 * @param resultList resultList を設定。
	 */
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
	/**
	 * @param usertypeCd usertypeCd を設定。
	 */
	public void setUsertypeCd(String usertypeCd) {
		this.usertypeCd = usertypeCd;
	}
	/**
	 * @return sitenCd を戻します。
	 */
	public String getSitenCd() {
		return sitenCd;
	}
	/**
	 * @param sitenCd sitenCd を設定。
	 */
	public void setSitenCd(String sitenCd) {
		this.sitenCd = sitenCd;
	}
	/**
	 * @return miseCd を戻します。
	 */
	public String getMiseCd() {
		return miseCd;
	}
	/**
	 * @param miseCd miseCd を設定。
	 */
	public void setMiseCd(String miseCd) {
		this.miseCd = miseCd;
	}
	/**
	 * @return onerCd を戻します。
	 */
	public String getOnerCd() {
		return onerCd;
	}
	/**
	 * @param onerCd onerCd を設定。
	 */
	public void setOnerCd(String onerCd) {
		this.onerCd = onerCd;
	}
	/**
	 * @return selectFlg を戻します。
	 */
	public int getSelectFlg() {
		return selectFlg;
	}
	/**
	 * @param selectFlg selectFlg を設定。
	 */
	public void setSelectFlg(int selectFlg) {
		this.selectFlg = selectFlg;
	}
	/**
	 * @return companyCd を戻します。
	 */
	public String getCompanyCd() {
		return companyCd;
	}
	/**
	 * @param companyCd companyCd を設定。
	 */
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}
	/**
	 * @return condition_VIEW_ID を戻します。
	 */
	public String getCondition_VIEW_ID() {
		return condition_VIEW_ID;
	}
	/**
	 * @return entryKai を戻します。
	 */
	public String getKai() {
		return kai;
	}
	/**
	 * @param entryKai entryKai を設定。
	 */
	public void setKai(String kai) {
		this.kai = kai;
	}
	/**
	 * @return jissiNendo を戻します。
	 */
	public String getJissiNendo() {
		return jissiNendo;
	}
	/**
	 * @param jissiNendo jissiNendo を設定。
	 */
	public void setJissiNendo(String jissiNendo) {
		this.jissiNendo = jissiNendo;
	}
	/**
	 * @return entryYearList を戻します。
	 */
    /**
     * @return sysDate を戻します。
     */
    public String getSysDate() {
        return sysDate;
    }
    /**
     * @param String sysDate を設定。
     */
    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }
	
    /**
     * @return dataKbnを戻す
     */
    public String getDataKbn() {
        return dataKbn;
    }
    /**
     * @param dataKbnを設定
     */
    public void setDataKbn(String dataKbn) {
        this.dataKbn = dataKbn;
    }

    /**
     * オーナーリスト取得
     * @return onerList
     */
    public List getOnerList() {
        return onerList;
    }
    /**
     * オーナーリストをセット
     * @param onerList
     */
    public void setOnerList(List onerList) {
        this.onerList = onerList;
    }
    /**
     * fileListを取得
     * @return fileList
     */
    public List getFileList() {
        return fileList;
    }
    /**
     * fileListをセット
     * @param fileList
     */
    public void setFileList(List fileList) {
        this.fileList = fileList;
    }
    /**
     * mstUserMiseを取得
     * @return mstUserMise
     */
    public MstUserMise getMstUserMise() {
        return mstUserMise;
    }
    /**
     *  mstUserMiseをセット
     * @param mstUserMise
     */
    public void setMstUserMise(MstUserMise mstUserMise) {
        this.mstUserMise = mstUserMise;
    }
    /**
     * mstUserOnerを取得
     * @return mstUserOner
     */
    public MstUserOner getMstUserOner() {
        return mstUserOner;
    }
    /**
     * mstUserOnerをセット
     * @param mstUserOner
     */
    public void setMstUserOner(MstUserOner mstUserOner) {
        this.mstUserOner = mstUserOner;
    }
    /**
     * mssFileInfoを取得
     * @return mssFileInfo
     */
    public MssFileInfo getMssFileInfo() {
        return mssFileInfo;
    }
    /**
     * mssFileInfoをセット
     * @param mssFileInfo
     */
    public void setMssFileInfo(MssFileInfo mssFileInfo) {
        this.mssFileInfo = mssFileInfo;
    }
    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    public BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }
    /**
     * getS2Container処理
     * @return
     */
    public S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }
    /**
     * ダウンロードindex
     * @param downloadIndex downloadIndex を設定。
     */
    public void setDownloadIndex(int downloadIndex) {
        this.downloadIndex = downloadIndex;
    }

    /**
     * ダウンロードindex
     * @return downloadIndex を戻します。
     */
    public int getDownloadIndex() {
        return downloadIndex;
    }
    /**
     * ダウンロードindex
     * @param listIndex を設定。
     */
    public void setListIndex(int index) {
        this.listIndex = index;
    }

    /**
     * ダウンロードindex
     * @return listIndex を戻します。
     */
    public int getListIndex() {
        return listIndex;
    }
    /**
     * bunsekiListを取得
     * @return bunsekiList
     */
    public List getBunsekiList() {
        return bunsekiList;
    }
    /**
     * bunsekiListをセット
     * @param bunsekiList
     */
    public void setBunsekiList(List bunsekiList) {
        this.bunsekiList = bunsekiList;
    }
    /**
     * onerNameを取得
     * @return onerName
     */
    public String getOnerName() {
        return onerName;
    }
    /**
     * onerNameをセット
     * @param onerName
     */
    public void setOnerName(String onerName) {
        this.onerName = onerName;
    }
    /**
     * 分析名称取得
     * @return bunsekiName
     */
    public String getBunsekiName() {
        return  bunsekiName;
    }
    /**
     * 分析名称セット
     * @param bunsekiName
     */
    public void setBunsekiName(String bunsekiName) {
       this.bunsekiName = bunsekiName;
    }

    /**
     * クリア
     */
    public void clear(){
        setKai(null);
        setMiseCd(null);
        setOnerCd(null);
        setResultList(null);
        setJissiNendo(null);
        setOnerName(null);
        setBunsekiName(null);
        setReExecuteFlg(null);
        setCompanyList(null);
    }
    public void clearList(){
        setFileList(null);
    }
    public List getTaishoNendo() {
        return taishoNendo;
    }
    public void setTaishoNendo(List taishoNendo) {
        this.taishoNendo = taishoNendo;
    }
    public String getReExecuteFlg() {
        return reExecuteFlg;
    }
    public void setReExecuteFlg(String reExecuteFlg) {
        this.reExecuteFlg = reExecuteFlg;
    }
    /**
     * 会社コードリスト 取得処理
     * 
     * @return listCompany を戻します。
     */
    public List getCompanyList() {
        return companyList;
    }
    /**
     * 会社コードリスト 設定処理
     * 
     * @param companyList を設定。
     */
    public void setCompanyList(List companyList) {
        this.companyList = companyList;
    }

    public String getCompanyName(String cd) {
        if(getCompanyList() == null) {
            return null;
        }
        for (int i = 0; i < getCompanyList().size(); i++) {
            CodCompany data = (CodCompany) getCompanyList().get(i);
            if(data.getCompanyCd().equals(cd)) {
                return data.getCompanyName();
            }
        }
        return  ""; 
   }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    /**
     * タブリスト 取得処理
     * 
     * @return tabList を戻します。
     */
    public List getTabList() {
        return tabList;
    }
    /**
     * タブリスト 設定処理
     * 
     * @param tabList を設定。
     */
    public void setTabList(List tabList) {
        this.tabList = tabList;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

}