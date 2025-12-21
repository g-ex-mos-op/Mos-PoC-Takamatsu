package jp.co.isid.mos.bird.bill.detailbilldownload.dto;
import java.util.List;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;

/**
 * ご請求明細ダウンロード
 * @author xwatanabe
 */
public class DetailBillDownloadListDto implements CsvOutputDto {

    /** ユーザタイプ */
    private String userTypeCd;
    /** 会社コード */
    private String companayCd;
    /** オーナーコード */
    private String onerCd;
    /** 売掛先コード */
    private String urikakeCd;
    /** 売掛先リスト */
    private List urikakeList;
    /** 請求書一覧リスト */
    private List seikyuList;

    /** オーナーコード選択エリアの表示フラグ */
    private boolean onerCdAreaFlg;
    /** 請求先一覧選択エリアの表示フラグ */
    private boolean urikakeListAreaFlg;
    /** 請求書一覧選択エリアの表示フラグ */
    private boolean seikyuListAreaFlg;
    
    /** 並び順ラジオ */
    private String radioNarabi;
    /** 年月選択ラジオ */
    private int radioNengetu;
    /** 年月選択ラジオ最後のINDEX */
    private int lastIndex;
    
    /////////////////////////////////////////////////

    /**
     * ユーザタイプの取得
     * @return userTypeCd を戻します。
     */
    public String getUserTypeCd() {
        return userTypeCd;
    }
    /**
     * ユーザタイプのセット
     * @param userTypeCd userTypeCdを設定。
     */
    public void setUserTypeCd(String userTypeCd) {
        this.userTypeCd = userTypeCd;
    }

    /**
     * 会社コードを取得します。
     * @return 会社コード
     */
    public String getCompanayCd() {
        return companayCd;
    }
    /**
     * 会社コードを設定します。
     * @param companyInfo 会社コード
     */
    public void setCompanayCd(String companayCd) {
        this.companayCd = companayCd;
    }

    /**
     * オーナーコードを取得します。
     * @return オーナーコード
     */
    public String getOnerCd() {
        return onerCd;
    }
    /**
     * オーナーコードを設定します。
     * @param onerCd オーナーコード
     */
    public void setOnerCd(String onerCd) {
        
        this.onerCd = onerCd;
    }


    /**
     * 売掛先コードを取得します。
     * @return 売掛先コード
     */
    public String getUrikakeCd() {
        return urikakeCd;
    }
    /**
     * 売掛先コードを設定します。
     * @param urikakeCd 売掛先コード
     */
    public void setUrikakeCd(String urikakeCd) {
        
        this.urikakeCd = urikakeCd;
    }


    /**
     * 売掛先リストを取得します。
     * @return 売掛先リスト
     */
    public List getUrikakeList() {
        return urikakeList;
    }
    /**
     * 売掛先リストを設定します。
     * @param 売掛先リスト
     */
    public void setUrikakeList(List urikakeList) {
        this.urikakeList = urikakeList;
    }
    
    /**
     * 請求書一覧リストを取得します。
     * @return 請求書一覧リスト
     */
    public List getSeikyuList() {
        return seikyuList;
    }
    /**
     * 請求書一覧リストを設定します。
     * @param 請求書一覧リスト
     */
    public void setSeikyuList(List seikyuList) {
        this.seikyuList = seikyuList;
    }

    /**
     * オーナーコード選択エリア表示フラグを取得します。
     * @return オーナーコード選択エリア表示フラグ
     */
    public boolean getOnerCdAreaFlg() {
        return onerCdAreaFlg;
    }
    /**
     * オーナーコード選択エリア表示フラグを設定します。
     * @param オーナーコード選択エリア表示フラグ
     */
    public void setOnerCdAreaFlg(boolean onerCdAreaFlg) {
        this.onerCdAreaFlg = onerCdAreaFlg;
    }
    
    /**
     * 請求先一覧選択エリア表示フラグを取得します。
     * @return 請求先一覧選択エリア表示フラグ
     */
    public boolean getUrikakeListAreaFlg() {
        return urikakeListAreaFlg;
    }
    /**
     * 請求先一覧選択エリア表示フラグを設定します。
     * @param 請求先一覧選択エリア表示フラグ
     */
    public void setUrikakeListAreaFlg(boolean urikakeListAreaFlg) {
        this.urikakeListAreaFlg = urikakeListAreaFlg;
    }

    /**
     * 請求書一覧選択エリアの表示フラグを取得します。
     * @return 請求書一覧選択エリア表示フラグ
     */
    public boolean getSeikyuListAreaFlg() {
        return seikyuListAreaFlg;
    }
    /**
     * 請求書一覧選択エリアの表示フラグを設定します。
     * @param 請求書一覧選択エリア表示フラグ
     */
    public void setSeikyuListAreaFlg(boolean seikyuListAreaFlg) {
        this.seikyuListAreaFlg = seikyuListAreaFlg;
    }

    /**
     * 並び順ラジオを取得します。
     * @return 並び順ラジオ
     */
    public String getRadioNarabi() {
        return radioNarabi;
    }
    /**
     * 並び順ラジオを設定します。
     * @param 並び順ラジオ
     */
    public void setRadioNarabi(String radioNarabi) {
        this.radioNarabi = radioNarabi;
    }

    /**
     * 年月選択ラジオを取得します。
     * @return 年月選択ラジオ
     */
    public int getRadioNengetu() {
        return radioNengetu;
    }
    /**
     * 年月選択ラジオを設定します。
     * @param 年月選択ラジオ
     */
    public void setRadioNengetu(int radioNengetu) {
        this.radioNengetu = radioNengetu;
    }

    /**
     * 年月選択ラジオ最後のINDEXを取得します。
     * @return 年月選択ラジオ最後のINDEX
     */
    public int getLastIndex() {
        return lastIndex;
    }
    /**
     * 年月選択ラジオ最後のINDEXを設定します。
     * @param 年月選択ラジオ最後のINDEX
     */
    public void setLastIndex(int lastIndex) {
        this.lastIndex = lastIndex;
    }

    /**
     * 情報のクリア
     */
    public void clear() {
        setUserTypeCd( null );               //ユーザタイプ
        setCompanayCd( null );               //会社コード
        setOnerCd( null );                   //オーナーコード
        setUrikakeCd( null );                //売掛先コード
        setUrikakeList( null );              //売掛先リスト
        setSeikyuList( null );               //請求書一覧リスト
        setOnerCdAreaFlg( false );           //オーナーコード選択エリアの表示フラグ
        setUrikakeListAreaFlg( false );      //請求先一覧選択エリアの表示フラグ
        setSeikyuListAreaFlg( false );       //請求書一覧選択エリアの表示フラグ 
        setRadioNarabi( "0" );                //並び順ラジオ初期選択
        setRadioNengetu( 0 );                 //画年月選択ラジオ初期選択
        setLastIndex( 0 );                    //画年月選択ラジオ最後のINDEX
    }

}