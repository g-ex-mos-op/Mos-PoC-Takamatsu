/*
 * 作成日: 2006/3/6
 */
package jp.co.isid.mos.bird.storeinfo.onerref.dto;

import java.util.List;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.storeinfo.onerref.entity.MstOner;

/**
 * オーナ情報照会Dto
 * @author itamoto
 */
public class OwnerReferenceDto{

	/* ログイン情報 */
    private BirdUserInfo birdUserInfo;

    // 条件部 /////////////////////////////////
    /* 会社リスト */
	private List companyList;
    /* 会社コード */
	private String companyCd;
	
	/* オーナコード */
    private String onerCd;
    /* オーナ名称 */
    private String onerNameKj;  
    /* オーナ名称（かな）*/
    private String onerNameKna;

    // 検索結果部 /////////////////////////////////
    /* オーナ検索結果情報 */
    private MstOner mstOner;
	/* オーナー代表者情報履歴情報 */
    private List trnOnerDaiList;
	/* オーナー保有店舗一覧情報 */
    private List mstOnerHoyuMiseList;
	/* 共栄会役員実績情報 */
    private List mstKyoeiList;

    // ナビエリア部 /////////////////////////////////
	/* オーナ照会リンク情報 */
    private List codOnerLinkJohoList;
    
    //検索結果として表示されているデータの検索条件
    private String searchedCondCompanyName;
    
    // ユーザタイプコード
    private String userTypeCd;
    
    //  条件画面：オーナーリスト
    private List listPullOner;
    private List listOnerMos;
    private List listOnerTomos;
    private List listOnerSikina;
    
    /**
	 * 会社リストの設定
	 * @return companyList を戻します。
	 */
	public List getCompanyList() {
		return companyList;
	}
	/**
	 * 会社リストの設定
	 * @param companyList companyList を設定。
	 */
	public void setCompanyList(List companyList) {
		this.companyList = companyList;
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
     * オーナコード設定
     * @return onerCd を戻します。
     */
    public String getOnerCd() {
        return onerCd;
    }
    /**
     * オーナコード設定
     * @param onerCd onerCd を設定。
     */
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }
    /**
     * オーナ名称設定
     * @return onerNameKj を戻します。
     */
    public String getOnerNameKj() {
        return onerNameKj;
    }
    /**
     * オーナ名称設定
     * @param onerNameKj onerNameKj を設定。
     */
    public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj = onerNameKj;
    }
    /**
     * オーナ名称（カナ）設定
     * @return onerNameKna を戻します。
     */
    public String getOnerNameKna() {
        return onerNameKna;
    }
    /**
     * オーナ名称（カナ）設定
     * @param onerNameKna onerNameKna を設定。
     */
    public void setOnerNameKna(String onerNameKna) {
        this.onerNameKna = onerNameKna;
    }

	/**
	 * オーナ検索結果情報の設定
	 * @return mstOner を戻します。
	 */
	public MstOner getMstOner() {
		return mstOner;
	}
	/**
	 * オーナ検索結果情報の設定
	 * @param mstOner mstOner を設定。
	 */
	public void setMstOner(MstOner mstOner) {
		this.mstOner = mstOner;
	}

	/**
	 * オーナー代表者情報履歴情報の設定
	 * @return trnOnerDaiList を戻します。
	 */
	public List getTrnOnerDaiList() {
		return trnOnerDaiList;
	}
	/**
	 * オーナー代表者情報履歴情報の設定
	 * @param trnOnerDaiList trnOnerDaiList を設定。
	 */
	public void setTrnOnerDaiList(List trnOnerDaiList) {
		this.trnOnerDaiList = trnOnerDaiList;
	}
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
	 * 共栄会役員実績情報の設定
	 * @return mstKyoeiList を戻します。
	 */
	public List getMstKyoeiList() {
		return mstKyoeiList;
	}
	/**
	 * 共栄会役員実績情報の設定
	 * @param mstKyoeiList mstKyoeiList を設定。
	 */
	public void setMstKyoeiList(List mstKyoeiList) {
		this.mstKyoeiList = mstKyoeiList;
	}
	
	/**
	 * BIRDログイン情報の設定
	 * @return birdUserInfo を戻します。
	 */
	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}
	/**
	 * BIRDログイン情報の設定
	 * @param birdUserInfo birdUserInfo を設定。
	 */
	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}
	/**
	 * オーナ照会リンク情報の設定
	 * @return codOnerLinkJohoList を戻します。
	 */
	public List getCodOnerLinkJohoList() {
		return codOnerLinkJohoList;
	}
	/**
	 * オーナ照会リンク情報の設定
	 * @param codOnerLinkJohoList codOnerLinkJohoList を設定。
	 */
	public void setCodOnerLinkJohoList(List codOnerLinkJohoList) {
		this.codOnerLinkJohoList = codOnerLinkJohoList;
	}
    
//20060525 追加 start -------------------------------------------------------------
    public String getSearchedCondCompanyName() {
        return searchedCondCompanyName;
    }
    public void setSearchedCondCompanyName(String searchedCondCompanyName) {
        this.searchedCondCompanyName = searchedCondCompanyName;
    }
//20060525 追加 end --------------------------------------------------------------
 
//20060607 追加 start -------------------------------------------------------------    
    /**
     * ユーザタイプコードの設定
     * @return userTypeCd を戻します。
     */
    public String getUserTypeCd() {
        return userTypeCd;
    }
    /**
     * ユーザタイプコードの設定
     * @param userTypeCd userTypeCd を設定。
     */
    public void setUserTypeCd(String userTypeCd) {
        this.userTypeCd = userTypeCd;
    }
    
    /**
     * 条件画面のオーナーコードプルダウン用、オーナーリストの設定
     * @return listPullOner を戻します。
     */
    public List getListPullOner() {
        return listPullOner;
    }
    /**
     * 条件画面の店コードプルダウン用、店リストの設定
     * @param listPullOner listPullOner を設定。
     */
    public void setListPullOner(List listPullOner) {
        this.listPullOner = listPullOner;
    }
    
    /**
     * 条件画面の店コードプルダウン用、店リスト(モス)の設定
     * @return userTypeCd を戻します。
     */
    public List getListOnerMos() {
        return listOnerMos;
    }
    /**
     * 条件画面の店コードプルダウン用、店リスト(モス)の設定
     * @param userTypeCd userTypeCd を設定。
     */
    public void setListOnerMos(List listPullMiseMos) {
        this.listOnerMos = listPullMiseMos;
    }
    
    /**
     * 条件画面の店コードプルダウン用、店リスト(トモス)の設定
     * @return userTypeCd を戻します。
     */
    public List getListOnerTomos() {
        return listOnerTomos;
    }
    /**
     * 条件画面の店コードプルダウン用、店リスト(トモス)の設定
     * @param userTypeCd userTypeCd を設定。
     */
    public void setListOnerTomos(List listPullMiseTomos) {
        this.listOnerTomos = listPullMiseTomos;
    }
    
    /**
     * 条件画面の店コードプルダウン用、店リスト(四季菜)の設定
     * @return userTypeCd を戻します。
     */
    public List getListOnerSikina() {
        return listOnerSikina;
    }
    /**
     * 条件画面の店コードプルダウン用、店リスト(四季菜)の設定
     * @param userTypeCd userTypeCd を設定。
     */
    public void setListOnerSikina(List listPullMiseSikina) {
        this.listOnerSikina = listPullMiseSikina;
    }
    
//20060607 追加 end --------------------------------------------------------------
    /**
     * 一号店存在チェック
     * 存在する場合はtrueを返します。
     */
    public boolean isExistsFirstMise() {
    	if(getMstOner() == null) {
    		return false;
    	}
    	return !CommonUtil.isNull(getMstOner().getMiseFirst());
    }
}
