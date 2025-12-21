/*
 * 作成日: 2006/3/6
 */
package jp.co.isid.mos.bird.entry.basicviewlist.dto;

import java.math.BigDecimal;
import java.util.List;

import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;

/**
 * ベーシック研修申込状況確認Dto
 * @author Nakajima
 */
public class BasicViewListDto implements DownloadDto, CsvOutputDto {

    // 変数宣言 /////////////////////////////////
    
    /* エントリーコード */
    private String entryCd;
    /* エントリー年 */
    private String entryYear;
    /* エントリー回 */
    private String entryKai;
    /* エントリータイトル */
    private String entryTitle;
    /* ベーシック研修開催日FROM */
    private String basicDtFrom;
    /* ベーシック研修開催日TO */
    private String basicDtTo;
    /* 臨店実習開催日FROM */
    private String rintenDtFrom;
    /* 臨店実習開催日TO */
    private String rintenDtTo;
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
    /* 備考 */
    private String note;
    /* 申込人数 */
    private String entryNum;
    /* ユーザータイプコード */
    private String userTypeCd;
    /* システム日付 */
    private String sysDate;
    /* SV制限フラグ */
    private boolean limitFlg;
    /* ユーザーID */
    private String userId;
    /* 選択ラジオボタンIndex */
    private String indexFlg;

    
    
    /* ログイン情報 */
    private BirdUserInfo birdUserInfo;
    /* ベーシック研修一覧データ */
    private List basicListData;
    /* ベーシック研修一覧データ */
    private List basicEntryList;
    /* ベーシック研修一覧CSVデータ */
    private List basicEntryCsvList;
    
    
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
     * ベーシック研修開催日取得
     * @return basicDtFrom を戻します。
     */
    public String getBasicDtFrom() {
        return basicDtFrom;
    }
    /**
     * ベーシック研修開催日設定
     * @param String basicDtFrom を設定。
     */
    public void setBasicDtFrom(String basicDtFrom) {
        this.basicDtFrom = basicDtFrom;
    }
    
    /**
     * ベーシック研修終了日取得
     * @return basicDtTo を戻します。
     */
    public String getBasicDtTo() {
        return basicDtTo;
    }
    /**
     * ベーシック研修終了日設定
     * @param String basicDtTo を設定。
     */
    public void setBasicDtTo(String basicDtTo) {
        this.basicDtTo = basicDtTo;
    }
    
    /**
     * 臨店実習開催日取得
     * @return rintenDtFrom を戻します。
     */
    public String getRintenDtFrom() {
        return rintenDtFrom;
    }
    /**
     * 臨店実習開催日設定
     * @param String rintenDtFrom を設定。
     */
    public void setRintenDtFrom(String rintenDtFrom) {
        this.rintenDtFrom = rintenDtFrom;
    }
    
    /**
     * 臨店実習終了日取得
     * @return rintenDtTo を戻します。
     */
    public String getRintenDtTo() {
        return rintenDtTo;
    }
    /**
     * 臨店実習終了日設定
     * @param String rintenDtTo を設定。
     */
    public void setRintenDtTo(String rintenDtTo) {
        this.rintenDtTo = rintenDtTo;
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
     * 備考取得
     * @return note を戻します。
     */
    public String getNote() {
        return note;
    }
    /**
     * 備考設定
     * @param String note を設定。
     */
    public void setNote(String note) {
        this.note = note;
    }
    
    /**
     * 申込人数取得
     * @return entryNum を戻します。
     */
    public String getEntryNum() {
        return entryNum;
    }
    /**
     * 申込人数設定
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
     * ベーシック研修一覧データの取得
     * @return basicListData を戻します。
     */
    public List getBasicListData() {
        return basicListData;
    }
    /**
     * ベーシック研修一覧データの設定
     * @param UIBasicListDataInfo basicListData を設定。
     */
    public void setBasicListData(List basicListData) {
        this.basicListData = basicListData;
    }
    
    /**
     * ベーシック研修エントリー者一覧データの取得
     * @return basicEntryList を戻します。
     */
    public List getBasicEntryList() {
        return basicEntryList;
    }
    /**
     * ベーシック研修エントリー者一覧データの設定
     * @param UIBasicEntryInfo basicEntryList を設定。
     */
    public void setBasicEntryList(List basicEntryList) {
        this.basicEntryList = basicEntryList;
    }
    
    /**
     * ベーシック研修エントリー者一覧CSVデータの取得
     * @return basicEntryCsvList を戻します。
     */
    public List getBasicEntryCsvList() {
        return basicEntryCsvList;
    }
    /**
     * ベーシック研修エントリー者一覧CSVデータの設定
     * @param UIBasicEntryCsvInfo basicEntryCsvList を設定。
     */
    public void setBasicEntryCsvList(List basicEntryCsvList) {
        this.basicEntryCsvList = basicEntryCsvList;
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
    
}
