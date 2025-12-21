package jp.co.isid.mos.bird.bill.demanddeposithistory.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;

/**
 * ご請求ご入金履歴
 * @author xwatanabe
 */
public class DemandDepositHistoryDto implements CsvOutputDto {

    /** ユーザータイプ */
    private String userType;
    /** 初期処理フラグ */
    private boolean initFlg = true;
    /** 会社コード */
    private String companyCd;
    /** オーナーコード */
    private String onerCd;

    /** 請求入金情報[最新タブ]表示用リスト */
    private List saisinTabList;
    /** 請求入金情報[最新タブ]表示用リストのサイズ */
    private int saisinTabListSize;
    /** 請求入金情報[履歴タブ]表示用リスト */
    private List rirekiTabList;
    /** 請求入金情報[履歴タブ]表示用リストのサイズ */
    private int rirekiTabListSize;

    /** 内訳画面請求書履歴リスト */
    private List utiwakeBillList;
// add 2023/02/07 USI範  begin
    /** 税率10%、税率別の内訳リスト */
    private List tenPercentList;
    /** 税率8%軽減税、税率別の内訳リスト */
    private List gennZeiList;
    /** 税率経過措置、税率別の内訳リスト */
    private List keikaSotiList;
    /** 税率非課税、税率別の内訳リスト */
    private List hiKazeiList;
// add 2023/02/07 USI範  end
    /** 店別請求履歴情報リスト */
    private List miseSeikyuList;
    /** 店別請求履歴情報リストのサイズ */
    private int miseSeikyuListSize;
    /** オーナー商品分類別請求履歴情報リスト */
    private List onerSeikyuBnruiList;
    /** 店商品分類別請求履歴情報リスト */
    private List miseSeikyuBnruiList;
    /** 店商品分類別請求履歴情報リストのサイズ */
    private int miseSeikyuBnruiListSize;

    /** 日付情報 */
    private BirdDateInfo birdDateInfo;


    /**
     * 情報のクリア
     */
    public void clear() {

        setUserType(null);                //ユーザタイプ
        setInitFlg(true);                 //初期処理フラグ
        setCompanyCd(null);               //会社コード
        setOnerCd(null);                  //オーナーコード
        setSaisinTabList( null );         //請求入金情報[最新タブ]表示用リスト
        setSaisinTabListSize( 0 );         //請求入金情報[最新タブ]表示用リストのサイズ
        setRirekiTabList( null );         //請求入金情報[履歴タブ]表示用リスト
        setRirekiTabListSize( 0 );         //請求入金情報[履歴タブ]表示用リストのサイズ

        setUtiwakeBillList( null );       //内訳画面請求書履歴リスト
// add 2022/11/16 USI範  begin
        setTenPercentList( null );         //税率10%、税率別の内訳リスト
        setGennZeiList( null );         	//税率8%軽減税、税率別の内訳リスト
        setKeikaSotiList( null );         	//税率経過措置、税率別の内訳リスト
        setHiKazeiList( null );         	//税率非課税、税率別の内訳リスト
// add 2022/11/16 USI範  end
        setMiseSeikyuList( null );        //店別請求履歴情報リスト
        setOnerSeikyuBnruiList( null );   //オーナー商品分類別請求履歴情報リスト
        setMiseSeikyuBnruiList( null );   //店商品分類別請求履歴情報リスト

        setMiseSeikyuBnruiListSize( 0 );   //店商品分類別請求履歴情報リストのサイズ
    }

    /////////////////////////////////ここより下、ゲッター・セッター//////////////////////////////////

    /**
     * ユーザタイプの取得
     * @return userTypeCd を戻します。
     */
    public String getUserType() {
        return userType;
    }
    /**
     * ユーザタイプのセット
     * @param userTypeCd userTypeCdを設定。
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * 初期処理フラグを取得
     * initFlg(true･･･初期状態/false･･･初期状態でない)を戻します。
     * @return true･･･初期状態/false･･･初期状態でない
     */
    public boolean isInitFlg() {
        return initFlg;
    }
    /**
     * 初期処理フラグのセット
     * @param initFlg initFlgを設定。
     */
    public void setInitFlg(boolean initFlg) {
        this.initFlg = initFlg;
    }

    /**
     * 会社コードを取得
     * @return 会社コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 会社コードのセット
     * @param 会社コード
     */
    public void setCompanyCd(String compCd) {
        this.companyCd = compCd;
    }

    /**
     * オーナーコードを取得
     * @return オーナーコード
     */
    public String getOnerCd() {
        return onerCd;
    }
    /**
     * オーナーコードのセット
     * @param オーナーコード
     */
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }

    /**
     * 請求入金情報[最新タブ]表示用リストを取得
     * @return 請求入金情報[最新タブ]表示用リスト
     */
    public List getSaisinTabList() {
        return saisinTabList;
    }
    /**
     * 請求入金情報[最新タブ]表示用リストのセット
     * @param  請求入金情報[最新タブ]表示用リスト
     */
    public void setSaisinTabList(List saisinTabList) {
        this.saisinTabList = saisinTabList;
    }
    /**
     * 請求入金情報[最新タブ]表示用リストサイズの取得
     * @return [最新タブ]表示用リストサイズ
     */
    public int getSaisinTabListSize() {
        return saisinTabListSize;
    }
    /**
     * 請求入金情報[最新タブ]表示用リストサイズのセット
     * @param [最新タブ]表示用リストサイズ
     */
    public void setSaisinTabListSize(int size) {
        this.saisinTabListSize = size;
    }


    /**
     * 請求入金情報[履歴タブ]表示用リストを取得
     * @return 請求入金情報[履歴タブ]表示用リスト
     */
    public List getRirekiTabList() {
        return rirekiTabList;
    }
    /**
     * 請求入金情報[履歴タブ]表示用リストのセット
     * @param  請求入金情報[履歴タブ]表示用リスト
     */
    public void setRirekiTabList(List rirekiTabList) {
        this.rirekiTabList = rirekiTabList;
    }
    /**
     * 請求入金情報[履歴タブ]表示用リストサイズの取得
     * @return [履歴タブ]表示用リストサイズ
     */
    public int getRirekiTabListSize() {
        return rirekiTabListSize;
    }
    /**
     * 請求入金情報[履歴タブ]表示用リストサイズのセット
     * @param [履歴タブ]表示用リストサイズ
     */
    public void setRirekiTabListSize(int size) {
        this.rirekiTabListSize = size;
    }


    /**
     * 内訳画面請求書履歴リストを取得
     * @return 内訳画面請求書履歴リスト
     */
    public List getUtiwakeBillList() {
        return utiwakeBillList;
    }
    /**
     * 内訳画面請求書履歴リストのセット
     * @param 内訳画面請求書履歴リスト
     */
    public void setUtiwakeBillList(List utiwakeBillList) {
        this.utiwakeBillList = utiwakeBillList;
    }

    /**
     * 店別請求履歴情報リストを取得
     * @return 店別請求履歴情報リスト
     */
    public List getMiseSeikyuList() {
        return miseSeikyuList;
    }
    /**
     * 店別請求履歴情報リストのセット
     * @param 店別請求履歴情報リスト
     */
    public void setMiseSeikyuList(List miseSeikyuList) {
        this.miseSeikyuList = miseSeikyuList;
    }

// add 2023/02/07 USI範  begin
    /**
     * 税率10%、税率別の内訳リストを取得
     * @return 税率10%、税率別の内訳リスト
     */
    public List getTenPercentList() {
        return tenPercentList;
    }
    /**
     * 税率10%、税率別の内訳リストのセット
     * @param 税率10%、税率別の内訳リスト
     */
    public void setTenPercentList(List tenPercentList) {
        this.tenPercentList = tenPercentList;
    }
    /**
     * 税率8%軽減税、税率別の内訳リストを取得
     * @return 税率8%軽減税、税率別の内訳リスト
     */
    public List getGennZeiList() {
        return gennZeiList;
    }
    /**
     * 税率8%軽減税、税率別の内訳リストのセット
     * @param 税率8%軽減税、税率別の内訳リスト
     */
    public void setGennZeiList(List gennZeiList) {
        this.gennZeiList = gennZeiList;
    }
    /**
     * 税率経過措置、税率別の内訳リストを取得
     * @return 税率経過措置、税率別の内訳リスト
     */
    public List getKeikaSotiList() {
        return keikaSotiList;
    }
    /**
     * 税率経過措置、税率別の内訳リストのセット
     * @param 税率経過措置、税率別の内訳リスト
     */
    public void setKeikaSotiList(List keikaSotiList) {
        this.keikaSotiList = keikaSotiList;
    }
    /**
     * 税率非課税、税率別の内訳リストを取得
     * @return 税率非課税、税率別の内訳リスト
     */
    public List getHiKazeiList() {
        return hiKazeiList;
    }
    /**
     * 税率非課税、税率別の内訳リストのセット
     * @param 税率非課税、税率別の内訳リスト
     */
    public void setHiKazeiList(List hiKazeiList) {
        this.hiKazeiList = hiKazeiList;
    }
// add 2023/02/07 USI範  end

    /**
     * 店別請求情報リストのサイズを取得
     * @return 店別請求情報リストのサイズ
     */
    public int getMiseSeikyuListSize() {
        return miseSeikyuListSize;
    }
    /**
     * 店別請求情報リストのサイズのセット
     * @param 店別請求情報リストのサイズ
     */
    public void setMiseSeikyuListSize(int size) {
        this.miseSeikyuListSize = size;
    }


    /**
     * オーナー商品分類別請求履歴リストを取得
     * @return オーナー商品分類別請求履歴リスト
     */
    public List getOnerSeikyuBnruiList() {
        return onerSeikyuBnruiList;
    }
    /**
     * オーナー商品分類別請求履歴リストのセット
     * @param オーナー商品分類別請求履歴リスト
     */
    public void setOnerSeikyuBnruiList(List onerSeikyuBnruiList) {
        this.onerSeikyuBnruiList = onerSeikyuBnruiList;
    }

    /**
     * 店商品分類別請求履歴リストを取得
     * @return 店商品分類別請求履歴リスト
     */
    public List getMiseSeikyuBnruiList() {
        return miseSeikyuBnruiList;
    }
    /**
     * 店商品分類別請求履歴リストのセット
     * @param 店商品分類別請求履歴リスト
     */
    public void setMiseSeikyuBnruiList(List miseSeikyuBnruiList) {
        this.miseSeikyuBnruiList = miseSeikyuBnruiList;
    }

    /**
     * 店商品分類別請求履歴リストのサイズを取得
     * @return 店商品分類別請求履歴リストのサイズ
     */
    public int getMiseSeikyuBnruiListSize() {
        return miseSeikyuBnruiListSize;
    }
    /**
     * 店商品分類別請求履歴リストのサイズのセット
     * @param 店商品分類別請求履歴リストのサイズ
     */
    public void setMiseSeikyuBnruiListSize(int size) {
        this.miseSeikyuBnruiListSize = size;
    }

    public BirdDateInfo getBirdDateInfo() {
		return birdDateInfo;
	}

	public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
		this.birdDateInfo = birdDateInfo;
	}
}
