package jp.co.isid.mos.bird.entry.basicentry.entity;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

public class UIEntryMst {

    public static final String TABLE = "BR17ENTL";
    
    public static final String entryCd_COLUMN = "ENTRY_CD";

    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    
    public static final String entryTitle_COLUMN = "ENTRY_TITLE";
    
    public static final String entryPlace_COLUMN = "ENTRY_PLACE";
    
    public static final String kaisaiDtFrom_COLUMN = "BASIC_DT_FROM";
    
    public static final String kaisaiDtTo_COLUMN = "BASIC_DT_TO";
    
    public static final String rintenDtFrom_COLUMN = "RINTEN_DT_FROM";
    
    public static final String rintenDtTo_COLUMN = "RINTEN_DT_TO";
    
    public static final String honbuInputDtFrom_COLUMN = "HONBU_INPUT_DT_FROM";
    
    public static final String honbuInputDtTo_COLUMN = "HONBU_INPUT_DT_TO";
    
    public static final String onerInputDtFrom_COLUMN = "ONER_INPUT_DT_FROM";
    
    public static final String onerInputDtTo_COLUMN = "ONER_INPUT_DT_TO";
    
    public static final String honbuOutputDtFrom_COLUMN = "HONBU_OUTPUT_DT_FROM";
    
    public static final String honbuOutputDtTo_COLUMN = "HONBU_OUTPUT_DT_TO";
    
    public static final String onerOutputDtFrom_COLUMN = "ONER_OUTPUT_DT_FROM";
    
    public static final String onerOutputDtTo_COLUMN = "ONER_OUTPUT_DT_TO";
    
    public static final String numberLimit_COLUMN = "NUMBER_LIMIT";
    
    public static final String placeLimit_COLUMN = "PLACE_LIMIT";
    
    public static final String note_COLUMN = "NOTE";
    
    public static final String somosikomiNum_COLUMN = "SOMOSIKOMI_NUM";
    
    public static final String mosikomiNum_COLUMN = "MOSIKOMI_NUM";
    
    public static final String onerSu_COLUMN = "ONERSU";
    
    /**
     * エントリーコード
     */
    private String entryCd;
    
    /**
     * エントリー年
     */
    private String entryYear;
    
    /**
     * エントリー回
     */
    private String entryKai;
    
    /**
     * エントリータイトル
     */
    private String entryTitle;
    
    /**
     * エントリー開催場所
     */
    private String entryPlace;
    
    /**
     * 開催日FROM
     */
    private String kaisaiDtFrom;
    
    /**
     * 開催日TO
     */
    private String kaisaiDtTo;
    
    /**
     * 臨店研修開催日FROM
     */
    private String rintenDtFrom;
    
    /**
     * 臨店研修開催日TO
     */
    private String rintenDtTo;
    
    /**
     * 本部登録開始日FROM
     */
    private String honbuInputDtFrom;
    
    /**
     * 本部登録終了日TO
     */
    private String honbuInputDtTo;
    
    /**
     * オーナー登録開始日FROM
     */
    private String onerInputDtFrom;
    
    /**
     * オーナー登録終了日TO
     */
    private String onerInputDtTo;
    
    /**
     * 本部表示開始日FROM
     */
    private String honbuOutputDtFrom;
    
    /**
     * 本部表示終了日TO
     */
    private String honbuOutputDtTo;
    
    /**
     * オーナー表示開始日FROM
     */
    private String onerOutputDtFrom;
    
    /**
     * オーナー表示終了日TO
     */
    private String onerOutputDtTo;
    
    /**
     * 申込定員
     */
    private String numberLimit;
    
    /**
     * 会場定員
     */
    private String placeLimit;
    
    /**
     * 備考
     */
    private String note;
    
    /**
     * 総申込人数
     */
    private String somosikomiNum;
    
    /**
     * 申込人数（オーナーユーザーのみ）
     */
    private String mosikomiNum;
    
    /**
     * オーナー数
     */
    private String onerSu;
    
    /**
     * 編集可能フラグ
     */
    private String editFlg;
    
    /**
     * 表示フラグ
     */
    private String displayFlg;
    
    /**
     * エントリーコードを取得します。
     * @return エントリーコード
     */
    public String getEntryCd() {
        return entryCd;
    }
    /**
     * エントリーコードを設定します。
     * @param entryCd エントリーコード
     */
    public void setEntryCd(String entryCd) {
        this.entryCd = entryCd;
    }
    
    /**
     * エントリー年を取得します。
     * @return エントリー年
     */
    public String getEntryYear() {
        return entryYear;
    }
    /**
     * エントリー年を設定します。
     * @param entryYear エントリー年
     */
    public void setEntryYear(String entryYear) {
        this.entryYear = entryYear;
    }
    
    /**
     * エントリー回を取得します。
     * @return エントリー回
     */
    public String getEntryKai() {
        return entryKai;
    }
    /**
     * エントリー回を設定します。
     * @param entryKai エントリー回
     */
    public void setEntryKai(String entryKai) {
        this.entryKai = entryKai;
    }
    
    /**
     * エントリータイトルを取得します。
     * @return エントリータイトル
     */
    public String getEntryTitle() {
        return entryTitle;
    }
    /**
     * エントリータイトルを設定します。
     * @param entryTitle エントリータイトル
     */
    public void setEntryTitle(String entryTitle) {
        this.entryTitle = entryTitle;
    }
    
    /**
     * エントリー開催場所を取得します。
     * @return エントリー開催場所
     */
    public String getEntryPlace() {
        return entryPlace;
    }
    /**
     * エントリー開催場所を設定します。
     * @param entryPlace エントリー開催場所
     */
    public void setEntryPlace(String entryPlace) {
        this.entryPlace = entryPlace;
    }
    
    /**
     * ベーシック研修開催日FROMを取得します。
     * @return ベーシック研修開催日FROM
     */
    public String getKaisaiDtFrom() {
        return kaisaiDtFrom;
    }
    /**
     * ベーシック研修開催日FROMを設定します。
     * @param kaisaiDtFrom ベーシック研修開催日FROM
     */
    public void setKaisaiDtFrom(String kaisaiDtFrom) {
        this.kaisaiDtFrom = kaisaiDtFrom;
    }
    
    /**
     * ベーシック研修開催日TOを取得します。
     * @return ベーシック研修開催日TO
     */
    public String getKaisaiDtTo() {
        return kaisaiDtTo;
    }
    /**
     * ベーシック研修開催日TOを設定します。
     * @param kaisaiDtTo ベーシック研修開催日TO
     */
    public void setKaisaiDtTo(String kaisaiDtTo) {
        this.kaisaiDtTo = kaisaiDtTo;
    }
    
    /**
     * 臨店研修開催日FROMを取得します。
     * @return 臨店研修開催日FROM
     */
    public String getRintenDtFrom() {
        return rintenDtFrom;
    }
    /**
     * 臨店研修開催日FROMを設定します。
     * @param rintenDtFrom 臨店研修開催日FROM
     */
    public void setRintenDtFrom(String rintenDtFrom) {
        this.rintenDtFrom = rintenDtFrom;
    }
    
    /**
     * 臨店研修開催日TOを取得します。
     * @return 臨店研修開催日TO
     */
    public String getRintenDtTo() {
        return rintenDtTo;
    }
    /**
     * 臨店研修開催日TOを設定します。
     * @param rintenDtTo 臨店研修開催日TO
     */
    public void setRintenDtTo(String rintenDtTo) {
        this.rintenDtTo = rintenDtTo;
    }
    
    /**
     * 本部登録開始日FROMを取得します。
     * @return 本部登録開始日FROM
     */
    public String getHonbuInputDtFrom() {
        return honbuInputDtFrom;
    }
    /**
     * 本部登録開始日FROMを設定します。
     * @param honbuInputDtFrom 本部登録開始日FROM
     */
    public void setHonbuInputDtFrom(String honbuInputDtFrom) {
        this.honbuInputDtFrom = honbuInputDtFrom;
    }
    
    /**
     * 本部登録終了日TOを取得します。
     * @return 本部登録終了日TO
     */
    public String getHonbuInputDtTo() {
        return honbuInputDtTo;
    }
    /**
     * 本部登録終了日TOを設定します。
     * @param honbuInputDtTo 本部登録終了日TO
     */
    public void setHonbuInputDtTo(String honbuInputDtTo) {
        this.honbuInputDtTo = honbuInputDtTo;
    }
    
    /**
     * オーナー登録開始日FROMを取得します。
     * @return オーナー登録開始日FROM
     */
    public String getOnerInputDtFrom() {
        return onerInputDtFrom;
    }
    /**
     * オーナー登録開始日FROMを設定します。
     * @param onerInputDtFrom オーナー登録開始日FROM
     */
    public void setOnerInputDtFrom(String onerInputDtFrom) {
        this.onerInputDtFrom = onerInputDtFrom;
    }
    
    /**
     * オーナー登録終了日TOを取得します。
     * @return オーナー登録終了日TO
     */
    public String getOnerInputDtTo() {
        return onerInputDtTo;
    }
    /**
     * オーナー登録終了日TOを設定します。
     * @param onerInputDtTo オーナー登録終了日TO
     */
    public void setOnerInputDtTo(String onerInputDtTo) {
        this.onerInputDtTo = onerInputDtTo;
    }
    
    /**
     * 本部表示開始日FROMを取得します。
     * @return 本部表示開始日FROM
     */
    public String getHonbuOutputDtFrom() {
        return honbuOutputDtFrom;
    }
    /**
     * 本部表示開始日FROMを設定します。
     * @param honbuOutputDtFrom 本部表示開始日FROM
     */
    public void setHonbuOutputDtFrom(String honbuOutputDtFrom) {
        this.honbuOutputDtFrom = honbuOutputDtFrom;
    }
    
    /**
     * 本部表示終了日TOを取得します。
     * @return 本部表示終了日TO
     */
    public String getHonbuOutputDtTo() {
        return honbuOutputDtTo;
    }
    /**
     * 本部表示終了日TOを設定します。
     * @param honbuOutputDtTo 本部表示終了日TO
     */
    public void setHonbuOutputDtTo(String honbuOutputDtTo) {
        this.honbuOutputDtTo = honbuOutputDtTo;
    }
    
    /**
     * オーナー表示開始日FROMを取得します。
     * @return オーナー表示開始日FROM
     */
    public String getOnerOutputDtFrom() {
        return onerOutputDtFrom;
    }
    /**
     * オーナー表示開始日FROMを設定します。
     * @param onerOutputDtFrom オーナー表示開始日FROM
     */
    public void setOnerOutputDtFrom(String onerOutputDtFrom) {
        this.onerOutputDtFrom = onerOutputDtFrom;
    }
    
    /**
     * オーナー表示終了日TOを取得します。
     * @return オーナー表示終了日TO
     */
    public String getOnerOutputDtTo() {
        return onerOutputDtTo;
    }
    /**
     * オーナー表示終了日TOを設定します。
     * @param onerOutputDtTo オーナー表示終了日TO
     */
    public void setOnerOutputDtTo(String onerOutputDtTo) {
        this.onerOutputDtTo = onerOutputDtTo;
    }
    
    /**
     * 申込定員を取得します。
     * @return 申込定員
     */
    public String getNumberLimit() {
        return numberLimit;
    }
    /**
     * 申込定員を設定します。
     * @param numberLimit 申込定員
     */
    public void setNumberLimit(String numberLimit) {
        this.numberLimit = numberLimit;
    }
    
    /**
     * 会場定員を取得します。
     * @return 会場定員
     */
    public String getPlaceLimit() {
        return placeLimit;
    }
    /**
     * 会場定員を設定します。
     * @param placeLimit 会場定員
     */
    public void setPlaceLimit(String placeLimit) {
        this.placeLimit = placeLimit;
    }
    
    /**
     * 備考を取得します。
     * @return 備考
     */
    public String getNote() {
        return note;
    }
    /**
     * 備考を設定します。
     * @param note 備考
     */
    public void setNote(String note) {
        this.note = note;
    }
    
    /**
     * 申込者数を取得します。
     * @return 申込者数
     */
    public String getSomosikomiNum() {
        return somosikomiNum;
    }
    /**
     * 申込者数を設定します。
     * @param entryNum 申込者数
     */
    public void setSomosikomiNum(String entryNum) {
        this.somosikomiNum = entryNum;
    }
    public String getMosikomiNum() {
        return mosikomiNum;
    }
    public void setMosikomiNum(String mosikomiNum) {
        this.mosikomiNum = mosikomiNum;
    }
    public String getOnerSu() {
        return onerSu;
    }
    public void setOnerSu(String onserSu) {
        this.onerSu = onserSu;
    }
    
    public String getRintenDtDisp() {
        DateFormatter formatter = new DateFormatter(DateFormatter.DATE_TYPE_YMD, "MM'/'dd'（'E'）'");
        return formatter.format(getRintenDtFrom(), true) + "～" + formatter.format(getRintenDtTo(), true);
    }
    public void setRintenDtDisp(String value) {
    }
    public String getDisplayFlg() {
        return displayFlg;
    }
    public void setDisplayFlg(String displayFlg) {
        this.displayFlg = displayFlg;
    }
    public String getEditFlg() {
        return editFlg;
    }
    public void setEditFlg(String editFlg) {
        this.editFlg = editFlg;
    }
    /**
     * 参加申込数定員オーバー
     * @return true 定員オーナー
     */
    public boolean isLimitOnverSanka() {
        if (getSomosikomiNum() == null) {
            return false;
        }
        BigDecimal bigSomosikomiNum = new BigDecimal(getSomosikomiNum());
        BigDecimal bigLimit = new BigDecimal(getNumberLimit());
        return bigLimit.compareTo(bigSomosikomiNum) <= 0;
    }
}