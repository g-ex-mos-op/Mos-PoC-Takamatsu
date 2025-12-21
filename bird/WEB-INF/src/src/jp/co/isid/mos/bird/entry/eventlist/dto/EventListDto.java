package jp.co.isid.mos.bird.entry.eventlist.dto;

/**
 * @author xyuchida
 *
 */
public class EventListDto {

    /**
     * 初期処理フラグ定数
     */
    public static final int INITFLAG_OFF = 0;
    public static final int INITFLAG_ON = 1;
    //2007/01/23 全国大会からの永年勤続申請画面遷移
    public static final int INITFLAG_FROMNATIONAL = 2;

    /**
     * 遷移区分定数
     */
    public static final int RETURNKIND_INIT = 0;
    public static final int RETURNKIND_END = 1;
    public static final int RETURNKIND_BACK = 2;

    /**
     * 会社コード
     */
    private String companyCd;
    /**
     * オーナーコード
     */
    private String onerCd;
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
     * 申込期間区分
     *  = 0 : 申込終了以前
     *  = 1 : 申込終了後
     */
    private int entryTermKind;

    /**
     * 初期起動フラグ
     *  = 0 : 初期値
     *  = 1 : 各画面起動
     */
    private int initFlag;
    /**
     * 遷移区分
     *  = 0 : 初期値
     *  = 1 : 処理終了
     *  = 2 : 戻る
     */
    private int returnKind;
    /**
     * ウインドウID
     */
    private int windowId;

    public String getCompanyCd() {
        return companyCd;
    }
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    public String getOnerCd() {
        return onerCd;
    }
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }
    public String getEntryCd() {
        return entryCd;
    }
    public void setEntryCd(String entryCd) {
        this.entryCd = entryCd;
    }
    public String getEntryYear() {
        return entryYear;
    }
    public void setEntryYear(String entryYear) {
        this.entryYear = entryYear;
    }
    public String getEntryKai() {
        return entryKai;
    }
    public void setEntryKai(String entryKai) {
        this.entryKai = entryKai;
    }
    public int getEntryTermKind() {
        return entryTermKind;
    }
    public void setEntryTermKind(int entryTermKind) {
        this.entryTermKind = entryTermKind;
    }

    public int getInitFlag() {
        return initFlag;
    }
    public void setInitFlag(int initFlag) {
        this.initFlag = initFlag;
    }
    public int getReturnKind() {
        return returnKind;
    }
    public void setReturnKind(int returnKind) {
        this.returnKind = returnKind;
    }
    public int getWindowId() {
        return windowId;
    }
    public void setWindowId(int windowId) {
        this.windowId = windowId;
    }

    public void clear() {
        setCompanyCd(null);
        setOnerCd(null);
        setEntryCd(null);
        setEntryYear(null);
        setEntryKai(null);
        setEntryTermKind(0);
        setInitFlag(INITFLAG_OFF);
        setReturnKind(RETURNKIND_INIT);
        setWindowId(0);
    }
}
