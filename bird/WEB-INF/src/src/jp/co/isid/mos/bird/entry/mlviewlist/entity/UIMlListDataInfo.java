package jp.co.isid.mos.bird.entry.mlviewlist.entity;

public class UIMlListDataInfo {

    public static final String TABLE = "BR17ENTL";
    
    public static final String entryCd_COLUMN = "ENTRY_CD";

    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    
    public static final String entryTitle_COLUMN = "ENTRY_TITLE";
    
    public static final String entryPlace_COLUMN = "ENTRY_PLACE";
    
    public static final String entryNameRyaku_COLUMN = "ENTRY_NAME_RYAKU";
    
    public static final String fromDt_COLUMN = "FROM_DT";
    
    public static final String toDt_COLUMN = "TO_DT";
    
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
    
    public static final String entryNum_COLUMN = "ENTRY_NUM";
    
    public static final String maninKbn_COLUMN = "MANIN_KBN";
    
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
     * エントリー略称
     */
    private String entryNameRyaku;
    
    /**
     * 開催日FROM
     */
    private String fromDt;
    
    /**
     * 開催日TO
     */
    private String toDt;
    
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
     * 申込者数
     */
    private String entryNum;
    
    /**
     * 満員区分
     */
    private String maninKbn;
    
    /**
     * 受験番号採番可否（true：採番可） 
     */
    private boolean executableSaiban;
    
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
     * エントリー略称を取得します。
     * @return エントリー略称
     */
    public String getEntryNameRyaku() {
        return entryNameRyaku;
    }
    /**
     * エントリー略称を設定します。
     * @param entryNameRyaku エントリー略称
     */
    public void setEntryNameRyaku(String entryNameRyaku) {
        this.entryNameRyaku = entryNameRyaku;
    }
    
    /**
     * 研修開催日FROMを取得します。
     * @return 研修開催日FROM
     */
    public String getFromDt() {
        return fromDt;
    }
    /**
     * 研修開催日FROMを設定します。
     * @param fromDt 研修開催日FROM
     */
    public void setFromDt(String fromDt) {
        this.fromDt = fromDt;
    }
    
    /**
     * 研修開催日TOを取得します。
     * @return 研修開催日TO
     */
    public String getToDt() {
        return toDt;
    }
    /**
     * 研修開催日TOを設定します。
     * @param toDt 研修開催日TO
     */
    public void setToDt(String toDt) {
        this.toDt = toDt;
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
    public String getEntryNum() {
        return entryNum;
    }
    /**
     * 申込者数を設定します。
     * @param entryNum 申込者数
     */
    public void setEntryNum(String entryNum) {
        this.entryNum = entryNum;
    }
    
    /**
     * 満員区分を取得します。
     * @return 満員区分
     */
    public String getManinKbn() {
        return maninKbn;
    }
    /**
     * 満員区分を設定します。
     * @param maninKbn 満員区分
     */
    public void setManinKbn(String maninKbn) {
        this.maninKbn = maninKbn;
    }
    public boolean isExecutableSaiban() {
        return executableSaiban;
    }
    public void setExecutableSaiban(boolean executableSaiban) {
        this.executableSaiban = executableSaiban;
    }
    

    
}
