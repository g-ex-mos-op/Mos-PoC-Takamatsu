package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 売上金管理月報明細照会・条件部DTOクラス
 * 
 * @author xogawa
 */
public class MeisaiRequestJokenDto extends MeisaiRequestDto {

	/**
	 * 会社情報リスト
	 */
	private List companyList;

	/**
	 * 対象店舗リスト
	 */
	private List taishoTenpoList;

	
    /**
     * エラー格納クラス
     */
    private ApplicationException appEx;
	
    /**
     * 検索フラグ
     */
    private boolean searchFlg;
    

    /**
	 * 会社情報リストを取得する
	 * @return companyList 会社情報リスト
	 */
	public List getCompanyList() {
		return companyList;
	}

	/**
	 * 会社情報リストを設定する
	 * @param companyList 会社情報リスト
	 */
	public void setCompanyList(List companyList) {
		this.companyList = companyList;
	}

    /**
     * 会社情報リストの要素数を取得する
     * @return int 会社情報リストの要素数
     */
    public int getCompanyListSize() {
        return companyList == null ? 0 :companyList.size();
    }

	/**
	 * 対象店舗リストを取得する
	 * @return taishoTenpoList 対象店舗リスト
	 */
	public List getTaishoTenpoList() {
		return taishoTenpoList;
	}

	/**
	 * 対象店舗リストを設定する
	 * @param taishoTenpoList 対象店舗リスト
	 */
	public void setTaishoTenpoList(List taishoTenpoList) {
		this.taishoTenpoList = taishoTenpoList;
	}

	/**
	 * エラー格納クラスを取得する
	 * @return appEx エラー格納クラス
	 */
	public ApplicationException getAppEx() {
		return appEx;
	}

	/**
	 * エラー格納クラスを設定する
	 * @param appEx エラー格納クラス
	 */
	public void setAppEx(ApplicationException appEx) {
		this.appEx = appEx;
	}	

    /**
     * 検索フラグを戻します。
     * @return searchFlg 検索フラグ
     */
    public boolean getSearchFlg() {
        return searchFlg;
    }

    /**
     * 検索フラグを設定します。
     * @param searchFlg 検索フラグ
     */
    public void setSearchFlg(boolean searchFlg) {
        this.searchFlg = searchFlg;
    }
}