package jp.co.isid.mos.bird.bizsupport.common.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;

import org.apache.myfaces.custom.fileupload.UploadedFile;

/**
 * 登録状況確認用Dto
 * 
 * @author Aspac
 */
public class PlRegistStateDto implements CsvOutputDto {

    /**
     * 遷移元情報
     */
    private String navigationCase;
    

    /**
     * クローズ店舗を含むフラグ true:含む false:含まない
     */
    private boolean closeMiseFlg;
    
    
    /**
     * 店舗リンク表示フラグ
     * ※データ入力画面からの遷移時には店舗をリンク表示する。
     * ※内容確認画面からの遷移時には店舗をリンクにしない。
     */
    private boolean miseLinkFlg;
    
    /**
     * オーナーコード
     */
    private String ownerCd;

    /**
     * オーナー名
     */
    private String ownerName;

    /**
     * 対象年月
     */
    private String plYm;

    /**
     * 作成者
     */
    private String authorName;

    /**
     * 作成年月日
     */
    private String authDt;

    /**
     * 作成者電話番号
     */
    private String authPhoneNum;

    /**
     * 作成者会計事務所等
     */
    private String authOther;

    /**
     * 決算月
     */
    private String kessanDt;

    /**
     * 取込時オーナーコード
     */
    private String lastOwnerCd;

    /**
     * 取込時対象年月
     */
    private String lastPlYm;

    /**
     * 条件画面表示モード
     */
    private int selectViewMode;

    /**
     * P/Lデータリスト
     */
    private List plDataList;

    /**
     * P/Lデータエラーリスト
     */
    private List plDataErrorList;

    /**
     * 対象年月選択肢リスト
     */
    private List plYmList;


    /**
     * オーナーコードを取得します。
     * @return オーナーコード
     */
    public String getOwnerCd() {
        return ownerCd;
    }

    /**
     * オーナーコードを設定します。
     * @param ownerCd オーナーコード
     */
    public void setOwnerCd(String ownerCd) {
        this.ownerCd = ownerCd;
    }

    /**
     * オーナー名を取得します。
     * @return オーナー名
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * オーナー名を設定します。
     * @param ownerName オーナー名
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * 対象年月を取得します。
     * @return 対象年月
     */
    public String getPlYm() {
        return plYm;
    }

    /**
     * 対象年月を設定します。
     * @param plYm 対象年月
     */
    public void setPlYm(String plYm) {
        this.plYm = plYm;
    }

    /**
     * 作成者を取得します。
     * @return 作成者
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * 作成者を設定します。
     * @param authorName 作成者
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    /**
     * 作成年月日を取得します。
     * @return 作成年月日
     */
    public String getAuthDt() {
        return authDt;
    }

    /**
     * 作成年月日を設定します。
     * @param authDt 作成年月日
     */
    public void setAuthDt(String authDt) {
        this.authDt = authDt;
    }

    /**
     * 作成者電話番号を取得します。
     * @return 作成者電話番号
     */
    public String getAuthPhoneNum() {
        return authPhoneNum;
    }

    /**
     * 作成者電話番号を設定します。
     * @param authPhoneNum 作成者電話番号
     */
    public void setAuthPhoneNum(String authPhoneNum) {
        this.authPhoneNum = authPhoneNum;
    }

    /**
     * 作成者会計事務所等を取得します。
     * @return 作成者会計事務所等
     */
    public String getAuthOther() {
        return authOther;
    }

    /**
     * 作成者会計事務所等を設定します。
     * @param authOther 作成者会計事務所等
     */
    public void setAuthOther(String authOther) {
        this.authOther = authOther;
    }

    /**
     * 決算月を取得します。
     * @return 決算月
     */
    public String getKessanDt() {
        return kessanDt;
    }

    /**
     * 決算月を設定します。
     * @param kessanDt 決算月
     */
    public void setKessanDt(String kessanDt) {
        this.kessanDt = kessanDt;
    }

    /**
     * 取込時オーナーコードを取得します。
     * @return 取込時オーナーコード
     */
    public String getLastOwnerCd() {
        return lastOwnerCd;
    }

    /**
     * 取込時オーナーコードを設定します。
     * @param lastOwnerCd 取込時オーナーコード
     */
    public void setLastOwnerCd(String lastOwnerCd) {
        this.lastOwnerCd = lastOwnerCd;
    }

    /**
     * 取込時対象年月を取得します。
     * @return 取込時対象年月
     */
    public String getLastPlYm() {
        return lastPlYm;
    }

    /**
     * 取込時対象年月を設定します。
     * @param lastPlYm 取込時対象年月
     */
    public void setLastPlYm(String lastPlYm) {
        this.lastPlYm = lastPlYm;
    }

    /**
     * 条件画面表示モードを取得します。
     * @return 条件画面表示モード
     */
    public int getSelectViewMode() {
        return selectViewMode;
    }

    /**
     * 条件画面表示モードを設定します。
     * @param selectViewMode 条件画面表示モード
     */
    public void setSelectViewMode(int selectViewMode) {
        this.selectViewMode = selectViewMode;
    }

    /**
     * P/Lデータリストを取得します。
     * @return P/Lデータリスト
     */
    public List getPlDataList() {
        return plDataList;
    }

    /**
     * P/Lデータリストを設定します。
     * @param plDataList P/Lデータリスト
     */
    public void setPlDataList(List plDataList) {
        this.plDataList = plDataList;
    }

    /**
     * P/Lデータエラーリストを取得します。
     * @return P/Lデータエラーリスト
     */
    public List getPlDataErrorList() {
        return plDataErrorList;
    }

    /**
     * P/Lデータエラーリストを設定します。
     * @param plDataErrorList P/Lデータエラーリスト
     */
    public void setPlDataErrorList(List plDataErrorList) {
        this.plDataErrorList = plDataErrorList;
    }

    /**
     * 対象年月選択肢リストを取得します。
     * @return 対象年月選択肢リスト
     */
    public List getPlYmList() {
        return plYmList;
    }

    /**
     * 対象年月選択肢リストを設定します。
     * @param plYmList 対象年月選択肢リスト
     */
    public void setPlYmList(List plYmList) {
        this.plYmList = plYmList;
    }

    public boolean isEmptyPlDataList() {
        return (getPlDataList() == null || getPlDataList().isEmpty()); 
    }

    /**
     * 画面遷移元IDを設定します。
     * @return 画面遷移元ID
     */
    public String getNavigationCase() {
        return navigationCase;
    }

    /**
     * 画面遷移元IDを設定します。
     * @param navigationCase 画面遷移元ID
     */
    public void setNavigationCase(String navigationCase) {
        this.navigationCase = navigationCase;
    }

    /**
     * クローズ店舗を含むフラグを取得します。
     * @return closeMiseFlg クローズ店舗を含むフラグ true:含む　false:含まない
     */
    public boolean isCloseMiseFlg() {
        return closeMiseFlg;
    }

    /**
     * クローズ店舗を含むフラグを設定します。
     * @return
     */
    public void setCloseMiseFlg(boolean closeMiseFlg) {
        this.closeMiseFlg = closeMiseFlg;
    }

    /**
     * 店舗リンク表示フラグを取得します。
     * @return miseLinkFlg  true:リンク表示　false:ラベル表示
     */
    public boolean isMiseLinkFlg() {
        return miseLinkFlg;
    }

    /**
     * 店舗リンク表示フラグを設定します。 
     * @param miseLinkFlg
     */
    public void setMiseLinkFlg(boolean miseLinkFlg) {
        this.miseLinkFlg = miseLinkFlg;
    }


}
