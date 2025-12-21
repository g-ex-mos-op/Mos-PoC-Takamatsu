/*
 * 作成日: 2006/3/6
 */
package jp.co.isid.mos.bird.entry.hanyoviewlist.dto;

import java.math.BigDecimal;
import java.util.List;

import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;

/**
 * ベーシック研修申込状況確認Dto
 * @author Nakajima
 */
public class HanyoViewListDto implements DownloadDto, CsvOutputDto {

    // 変数宣言 /////////////////////////////////
    
    /* エントリーコード */
    private String entryCd;
    /* エントリー年 */
    private String entryYear;
    /* エントリー回 */
    private String entryKai;
    /* エントリータイトル */
    private String entryTitle;
    /* 開催場所 */
    private String entryPlace;
    /* 開催名 */
    private String entryNameRyaku;
    /* 研修(出張/更新)開催日FROM */
    private String fromDt;
    /* 研修(出張/更新)開催日TO */
    private String toDt;
    /* 本部：登録開始日 */
    private String honbuInputDtFrom;
    /* 本部：登録終了日 */
    private String honbuInputDtTo;
    /* オーナー：登録開始日 */
    private String onerInputDtFrom;
    /* オーナー：登録終了日 */
    private String onerInputDtTo;
    /* 定員 */
    private String numberLimit;
    /* 申込人数 */
    private String entryNum;
    /* ユーザータイプコード */
    private String userTypeCd;
    /* システム日付 */
    private String sysDate;
    /* システム日付の翌日日付 */
    private String sysNextDate;
    /* SV制限フラグ */
    private boolean limitFlg;
    /* ユーザーID */
    private String userId;
    /* 選択ラジオボタンIndex */
    private String indexFlg;
    /* 対象研修区分(コンボボックス) */
    private String kenshuKbn;
    
    
    /* 研修(出張/更新)一覧データ */
    private List hanyoListData;
    
    
    
    /**
     * エントリーコード取得
     * @return entryCd を戻します。
     */
    public String getEntryCd() {
        return entryCd;
    }
    /**
     * エントリーコード設定
     * @param String entryCd を設定。
     */
    public void setEntryCd(String entryCd) {
        this.entryCd = entryCd;
    }
    
    /**
     * エントリー年取得
     * @return entryYear を戻します。
     */
    public String getEntryYear() {
        return entryYear;
    }
    /**
     * エントリー年設定
     * @param String entryYear を設定。
     */
    public void setEntryYear(String entryYear) {
        this.entryYear = entryYear;
    }
    
    /**
     * エントリー回取得
     * @return entryKai を戻します。
     */
    public String getEntryKai() {
        return entryKai;
    }
    /**
     * エントリー回設定
     * @param String entryKai を設定。
     */
    public void setEntryKai(String entryKai) {
        this.entryKai = entryKai;
    }
    
    /**
     * エントリータイトル取得
     * @return entryKai を戻します。
     */
    public String getEntryTitle() {
        return entryTitle;
    }
    /**
     * エントリータイトル設定
     * @param String entryTitle を設定。
     */
    public void setEntryTitle(String entryTitle) {
        this.entryTitle = entryTitle;
    }
    
    /**
     * 開催場所取得
     * @return entryPlace を戻します。
     */
    public String getEntryPlace() {
        return entryPlace;
    }
    /**
     * 開催場所設定
     * @param String entryPlace を設定。
     */
    public void setEntryPlace(String entryPlace) {
        this.entryPlace = entryPlace;
    }
    
    /**
     * 開催名取得
     * @return entryNameRyaku を戻します。
     */
    public String getEntryNameRyaku() {
        return entryNameRyaku;
    }
    /**
     * 開催名設定
     * @param String entryNameRyaku を設定。
     */
    public void setEntryNameRyaku(String entryNameRyaku) {
        this.entryNameRyaku = entryNameRyaku;
    }
    
    /**
     * 研修(出張/更新)開催日取得
     * @return fromDt を戻します。
     */
    public String getFromDt() {
        return fromDt;
    }
    /**
     * 研修(出張/更新)開催日設定
     * @param String fromDt を設定。
     */
    public void setFromDt(String fromDt) {
        this.fromDt = fromDt;
    }
    
    /**
     * 研修(出張/更新)終了日取得
     * @return toDt を戻します。
     */
    public String getToDt() {
        return toDt;
    }
    /**
     * 研修(出張/更新)終了日設定
     * @param String toDt を設定。
     */
    public void setToDt(String toDt) {
        this.toDt = toDt;
    }
    
    /**
     * 本部：登録開始日取得
     * @return honbuInputDtFrom を戻します。
     */
    public String getHonbuInputDtFrom() {
        return honbuInputDtFrom;
    }
    /**
     * 本部：登録開始日設定
     * @param String honbuInputDtFrom を設定。
     */
    public void setHonbuInputDtFrom(String honbuInputDtFrom) {
        this.honbuInputDtFrom = honbuInputDtFrom;
    }
    
    /**
     * 本部：登録終了日取得
     * @return honbuInputDtTo を戻します。
     */
    public String getHonbuInputDtTo() {
        return honbuInputDtTo;
    }
    /**
     * 本部：登録終了日設定
     * @param String honbuInputDtTo を設定。
     */
    public void setHonbuInputDtTo(String honbuInputDtTo) {
        this.honbuInputDtTo = honbuInputDtTo;
    }
    
    /**
     * オーナー：登録開始日取得
     * @return onerInputDtFrom を戻します。
     */
    public String getOnerInputDtFrom() {
        return onerInputDtFrom;
    }
    /**
     * オーナー：登録開始日設定
     * @param String onerInputDtFrom を設定。
     */
    public void setOnerInputDtFrom(String onerInputDtFrom) {
        this.onerInputDtFrom = onerInputDtFrom;
    }
    
    /**
     * オーナー：登録終了日取得
     * @return onerInputDtTo を戻します。
     */
    public String getOnerInputDtTo() {
        return onerInputDtTo;
    }
    /**
     * オーナー：登録終了日設定
     * @param String onerInputDtTo を設定。
     */
    public void setOnerInputDtTo(String onerInputDtTo) {
        this.onerInputDtTo = onerInputDtTo;
    }
    
    /**
     * 定員取得
     * @return numberLimit を戻します。
     */
    public String getNumberLimit() {
        return numberLimit;
    }
    /**
     * 定員設定
     * @param String numberLimit を設定。
     */
    public void setNumberLimit(String numberLimit) {
        this.numberLimit = numberLimit;
    }
    
    /**
     * 総申込人数取得
     * @return entryNum を戻します。
     */
    public String getEntryNum() {
        return entryNum;
    }
    /**
     * 総申込人数設定
     * @param String entryNum を設定。
     */
    public void setEntryNum(String entryNum) {
        this.entryNum = entryNum;
    }
        
    /**
     * システム日付の設定
     * @return String を戻します。
     */
    public String getSysDate() {
        return sysDate;
    }
    /**
     * システム日付の設定
     * @param String sysDate を設定。
     */
    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }
    
    /**
     * システム日付の翌日日付の設定
     * @return String を戻します。
     */
    public String getSysNextDate() {
        return sysNextDate;
    }
    /**
     * システム日付の翌日日付の設定
     * @param String sysNextDate を設定。
     */
    public void setSysNextDate(String sysNextDate) {
        this.sysNextDate = sysNextDate;
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
     * 選択ラジオボタンIndexの設定
     * @return String を戻します。
     */
    public String getIndexFlg() {
        return indexFlg;
    }
    /**
     * 選択ラジオボタンIndexの設定
     * @param String indexFlg を設定。
     */
    public void setIndexFlg(String indexFlg) {
        this.indexFlg = indexFlg;
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
     * 対象研修区分(コンボボックス)の設定
     * @return kenshuKbn を戻します。
     */
    public String getKenshuKbn() {
        return kenshuKbn;
    }
    /**
     * 対象研修区分(コンボボックス)の設定
     * @param String kenshuKbn を設定。
     */
    public void setKenshuKbn(String kenshuKbn) {
        this.kenshuKbn = kenshuKbn;
    }
    
    

    
    
    //////////////////
    //     LIST     //
    //////////////////
    
    /**
     * 研修(出張/更新)一覧データの取得
     * @return hanyoListData を戻します。
     */
    public List getHanyoListData() {
        return hanyoListData;
    }
    /**
     * 研修(出張/更新)一覧データの設定
     * @param UIHanyoListDataInfo hanyoListData を設定。
     */
    public void setHanyoListData(List hanyoListData) {
        this.hanyoListData = hanyoListData;
    }
    
    /**
     * 研修(出張/更新)一覧データの件数を返す
     * @return int を戻します。
     */
    public int getHanyoListDataSize() {
        int i = 0;
        if(getHanyoListData() != null){
            i = getHanyoListData().size();
        }
        return i;
    }
    
    
    
    
    
    
    
    
    
////////////////////////////////////////////////////////////////////////////////////////////    
    
    
    
    // 変数宣言 /////////////////////////////////
    


    
    
    /* ログイン情報 */
    private BirdUserInfo birdUserInfo;

    /* ベーシック研修一覧データ */
    private List hanyoEntryList;
    /* ベーシック研修一覧CSVデータ */
    private List hanyoEntryCsvList;
    
    

    

    
    /**
     * ベーシック研修エントリー者一覧データの取得
     * @return hanyoEntryList を戻します。
     */
    public List getHanyoEntryList() {
        return hanyoEntryList;
    }
    /**
     * ベーシック研修エントリー者一覧データの設定
     * @param UIHanyoEntryInfo hanyoEntryList を設定。
     */
    public void setHanyoEntryList(List hanyoEntryList) {
        this.hanyoEntryList = hanyoEntryList;
    }
    
    /**
     * ベーシック研修エントリー者一覧CSVデータの取得
     * @return hanyoEntryCsvList を戻します。
     */
    public List getHanyoEntryCsvList() {
        return hanyoEntryCsvList;
    }
    /**
     * ベーシック研修エントリー者一覧CSVデータの設定
     * @param UIHanyoEntryCsvInfo hanyoEntryCsvList を設定。
     */
    public void setHanyoEntryCsvList(List hanyoEntryCsvList) {
        this.hanyoEntryCsvList = hanyoEntryCsvList;
    }
    
    
    
    
    
    
    

}
