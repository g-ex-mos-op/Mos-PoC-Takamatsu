package jp.co.isid.mos.bird.storemanage.posniporef.entity;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.framework.util.Calculator;


public class PosNipoInfo {
    
    public static final String TABLE = "BT63SNIP";
    public static final String companyCd_COLUMN    = "COMPANY_CD";
    public static final String miseCd_COLUMN       = "MISE_CD";
    public static final String miseName_COLUMN     = "MISE_NAME";
    public static final String eigyoDt_COLUMN      = "EIGYO_DT";
    public static final String uriage_COLUMN       = "URIAGE";
    public static final String siireShoku_COLUMN   = "SIIRE_SHOKU";
    public static final String siireYasai_COLUMN   = "SIIRE_YASAI";
    public static final String siireHouzai_COLUMN  = "SIIRE_HOUZAI";
    public static final String siireOther_COLUMN   = "SIIRE_OTHER";
    public static final String siireShoumou_COLUMN = "SIIRE_SHOUMOU";
    public static final String jituShoku_COLUMN    = "JITU_SHOKU";
    public static final String jituYasai_COLUMN    = "JITU_YASAI";
    public static final String jituHouzai_COLUMN   = "JITU_HOUZAI";
    public static final String jituOther_COLUMN    = "JITU_OTHER";
    public static final String stdShoku_COLUMN     = "STD_SHOKU";
    public static final String stdYasai_COLUMN     = "STD_YASAI";
    public static final String stdHouzai_COLUMN    = "STD_HOUZAI";
    public static final String stdOther_COLUMN     = "STD_OTHER";
    public static final String paSal_COLUMN        = "PA_SAL";
    public static final String paWh_COLUMN         = "PA_WH";
    public static final String shainSal_COLUMN     = "SHAIN_SAL";
    public static final String shainWh_COLUMN      = "SHAIN_WH";

    /** 合計行であるか否か */
    private boolean isSumRow = false;
    /** 作業用 */
    private BigDecimal tmp = new BigDecimal(0);

    //--------------------------
    // テーブル対応項目
    //--------------------------
    /** 会社コード */
    private String companyCd;
    /** 店コード */
    private String miseCd;
    /** 店舗名称 */
    private String miseName;
    /** 営業日 */
    private String eigyoDt;
    /** 売上高計 */
    private BigDecimal uriage       = new BigDecimal(0);
    /** 食材仕入高 */
    private BigDecimal siireShoku   = new BigDecimal(0);
    /** 野菜仕入高 */
    private BigDecimal siireYasai   = new BigDecimal(0);
    /** 包材仕入高 */
    private BigDecimal siireHouzai  = new BigDecimal(0);
    /** その他仕入高 */
    private BigDecimal siireOther   = new BigDecimal(0);
    /** 消耗品仕入高 */
    private BigDecimal siireShoumou = new BigDecimal(0);
    /** 実際原価：食材 */
    private BigDecimal jituShoku    = new BigDecimal(0);
    /** 実際原価：野菜 */
    private BigDecimal jituYasai    = new BigDecimal(0);
    /** 実際原価：包材 */
    private BigDecimal jituHouzai   = new BigDecimal(0);
    /** 実際原価：その他 */
    private BigDecimal jituOther    = new BigDecimal(0);
    /** 標準原価：食材 */
    private BigDecimal stdShoku     = new BigDecimal(0);
    /** 標準原価：野菜 */
    private BigDecimal stdYasai     = new BigDecimal(0);
    /** 標準原価：包材 */
    private BigDecimal stdHouzai    = new BigDecimal(0);
    /** 標準原価：その他 */
    private BigDecimal stdOther     = new BigDecimal(0);
    /** Ｐ／Ａ人件費計 */
    private BigDecimal paSal        = new BigDecimal(0);
    /** Ｐ／Ａ総労働時間 */
    private BigDecimal paWh         = new BigDecimal(0);
    /** 社員人件費計 */
    private BigDecimal shainSal     = new BigDecimal(0);
    /** 社員総労働時間 */
    private BigDecimal shainWh      = new BigDecimal(0);

    //--------------------------
    // タブ(仕入タブ)対応項目
    //--------------------------
    /** 仕入：食包材合計(金額) */
    public BigDecimal getSiireKingakuShokuHouzaiSum(){
        tmp = new BigDecimal(0);
        tmp = tmp.add(siireShoku);
        tmp = tmp.add(siireYasai);
        tmp = tmp.add(siireHouzai);
        return tmp;
    }
    /** 仕入：金額合計 */
    public BigDecimal getSiireKingakuSum(){
        tmp = new BigDecimal(0);
        tmp = tmp.add(siireShoku);
        tmp = tmp.add(siireYasai);
        tmp = tmp.add(siireHouzai);
        tmp = tmp.add(siireOther);
        return tmp;
    }
    /** 仕入：率 */
    public BigDecimal getSiireRitu(){
        tmp = getSiireKingakuSum();
        tmp = Calculator.percentage(tmp, uriage, 2);
        return tmp;
    }

    //--------------------------
    // タブ(実際原価タブ)対応項目
    //--------------------------
    /** 実際原価：金額：食包材合計 */
    public BigDecimal getJituKingakuShokuHouzaiSum(){
        tmp = new BigDecimal(0);
        tmp = tmp.add(jituShoku);
        tmp = tmp.add(jituYasai);
        tmp = tmp.add(jituHouzai);
        return tmp;
    }
    /** 実際原価：金額合計 */
    public BigDecimal getJituKingakuSum(){
        tmp = new BigDecimal(0);
        tmp = tmp.add(jituShoku);
        tmp = tmp.add(jituYasai);
        tmp = tmp.add(jituHouzai);
        tmp = tmp.add(jituOther);
        return tmp;
    }
    /** 実際原価：原価率：食材 */
    public BigDecimal getJituGenkarituShoku(){
        tmp = Calculator.percentage(jituShoku, uriage, 2);
        return tmp;
    }
    /** 実際原価：原価率：野菜 */
    public BigDecimal getJituGenkarituYasai(){
        tmp = Calculator.percentage(jituYasai, uriage, 2);
        return tmp;
    }
    /** 実際原価：原価率：包材 */
    public BigDecimal getJituGenkarituHouzai(){
        tmp = Calculator.percentage(jituHouzai, uriage, 2);
        return tmp;
    }
    /** 実際原価：原価率：食包材合計 */
    public BigDecimal getJituGenkarituShokuHouzaiSum(){
        tmp = getJituKingakuShokuHouzaiSum();
        tmp = Calculator.percentage(tmp, uriage, 2);
        return tmp;
    }
    /** 実際原価：原価率：物販系 */
    public BigDecimal getJituGenkarituOther(){
        tmp = Calculator.percentage(jituOther, uriage, 2);
        return tmp;
    }
    /** 実際原価：原価率合計 */
    public BigDecimal getJituGenkarituSum(){
        tmp = getJituKingakuSum();
        tmp = Calculator.percentage(tmp, uriage, 2);
        return tmp;
    }
    
    //--------------------------
    // タブ(標準原価タブ)対応項目
    //--------------------------
    /** 標準原価：金額：食包材合計 */
    public BigDecimal getStdKingakuShokuHouzaiSum(){
        tmp = new BigDecimal(0);
        tmp = tmp.add(stdShoku);
        tmp = tmp.add(stdYasai);
        tmp = tmp.add(stdHouzai);
        return tmp;
    }
    /** 標準原価：金額合計 */
    public BigDecimal getStdKingakuSum(){
        tmp = new BigDecimal(0);
        tmp = tmp.add(stdShoku);
        tmp = tmp.add(stdYasai);
        tmp = tmp.add(stdHouzai);
        tmp = tmp.add(stdOther);
        return tmp;
    }
    /** 標準原価：原価率：食材 */
    public BigDecimal getStdGenkarituShoku(){
        tmp = Calculator.percentage(stdShoku, uriage, 2);
        return tmp;
    }
    /** 標準原価：原価率：野菜 */
    public BigDecimal getStdGenkarituYasai(){
        tmp = Calculator.percentage(stdYasai, uriage, 2);
        return tmp;
    }
    /** 標準原価：原価率：包材 */
    public BigDecimal getStdGenkarituHouzai(){
        tmp = Calculator.percentage(stdHouzai, uriage, 2);
        return tmp;
    }
    /** 標準原価：原価率：食包材合計 */
    public BigDecimal getStdGenkarituShokuHouzaiSum(){
        tmp = getStdKingakuShokuHouzaiSum();
        tmp = Calculator.percentage(tmp, uriage, 2);
        return tmp;
    }
    /** 標準原価：原価率：物販計 */
    public BigDecimal getStdGenkarituOther(){
        tmp = Calculator.percentage(stdOther, uriage, 2);
        return tmp;
    }
    /** 標準原価：原価率合計 */
    public BigDecimal getStdGenkarituSum(){
        tmp = getStdKingakuSum();
        tmp = Calculator.percentage(tmp, uriage, 2);
        return tmp;
    }
    
    //--------------------------
    // タブ(人件費タブ)対応項目
    //--------------------------
    /** 人時売上高 */
    public BigDecimal getJinjiUriage(){
        tmp = new BigDecimal(0);
        tmp = tmp.add(paWh);
        tmp = tmp.add(shainWh);
        tmp = Calculator.divide(uriage, tmp, 0);
        return tmp;
    }
    /** P/A人件費率 */
    public BigDecimal getPaJinkenhiritu(){
        tmp = Calculator.percentage(paSal, uriage, 2);
        return tmp;
    }
    /** 社員人件費率 */
    public BigDecimal getShainJinkenhiritu(){
        tmp = Calculator.percentage(shainSal, uriage, 2);
        return tmp;
    }
    /** 人件費：人件費率 */
    public BigDecimal getJinkenhirituSum(){
        tmp = new BigDecimal(0);
        tmp = tmp.add(paSal);
        tmp = tmp.add(shainSal);
        tmp = Calculator.percentage(tmp, uriage, 2);
        return tmp;
    }
    /** 人件費：総労働時間 */
    public BigDecimal getJinkenhiWh(){
        tmp = new BigDecimal(0);
        tmp = tmp.add(paWh);
        tmp = tmp.add(shainWh);
        return tmp;
    }
    /** 人件費：合計金額 */
    public BigDecimal getJinkenhiKingakuSum(){
        tmp = new BigDecimal(0);
        tmp = tmp.add(paSal);
        tmp = tmp.add(shainSal);
        return tmp;
    }

    
    ////////////////////////////////////////////////////

    /**
     * 合計行か否かを取得します。
     */
    public boolean isSumRow() {
        return isSumRow;
    }
    /**
     * 合計行のタイプを設定します。
     * @param true:合計行である、false:合計行でない
     */
    public void setSumRow(boolean b) {
        this.isSumRow = b;
    }

    /**
     * 営業日を取得します。
     * @return 営業日
     */
    public String getEigyoDt() {
        return eigyoDt;
    }
    /**
     * 営業日を設定します。
     * @param eigyoDt 営業日
     */
    public void setEigyoDt(String eigyoDt) {
        this.eigyoDt = eigyoDt;
    }
   
    /**
     * 売上を取得します。
     * @return 売上
     */
    public BigDecimal getUriage() {
        return uriage;
    }
    /**
     * 売上を設定します。
     * @param uriage 売上
     */
    public void setUriage(BigDecimal uriage) {
        this.uriage = uriage;
    }
    
    /**
     * @return companyCd を戻します。
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * @param companyCd 設定する companyCd。
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    /**
     * @return miseCd を戻します。
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * @param miseCd 設定する miseCd。
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    /**
     * @return miseName を戻します。
     */
    public String getMiseName() {
        return miseName;
    }
    /**
     * @param miseName 設定する miseName。
     */
    public void setMiseName(String miseName) {
        this.miseName = miseName;
    }
    /**
     * @return siireHouzai を戻します。
     */
    public BigDecimal getSiireHouzai() {
        return siireHouzai;
    }
    /**
     * @param siireHouzai 設定する siireHouzai。
     */
    public void setSiireHouzai(BigDecimal siireHouzai) {
        this.siireHouzai = siireHouzai;
    }
    /**
     * @return siireOther を戻します。
     */
    public BigDecimal getSiireOther() {
        return siireOther;
    }
    /**
     * @param siireOther 設定する siireOther。
     */
    public void setSiireOther(BigDecimal siireOther) {
        this.siireOther = siireOther;
    }
    /**
     * @return siireShoku を戻します。
     */
    public BigDecimal getSiireShoku() {
        return siireShoku;
    }
    /**
     * @param siireShoku 設定する siireShoku。
     */
    public void setSiireShoku(BigDecimal siireShoku) {
        this.siireShoku = siireShoku;
    }
    /**
     * @return siireShoumou を戻します。
     */
    public BigDecimal getSiireShoumou() {
        return siireShoumou;
    }
    /**
     * @param siireShoumou 設定する siireShoumou。
     */
    public void setSiireShoumou(BigDecimal siireShoumou) {
        this.siireShoumou = siireShoumou;
    }
    /**
     * @return siireYasai を戻します。
     */
    public BigDecimal getSiireYasai() {
        return siireYasai;
    }
    /**
     * @param siireYasai 設定する siireYasai。
     */
    public void setSiireYasai(BigDecimal siireYasai) {
        this.siireYasai = siireYasai;
    }
    /**
     * @return jituHouzai を戻します。
     */
    public BigDecimal getJituHouzai() {
        return jituHouzai;
    }
    /**
     * @param jituHouzai 設定する jituHouzai。
     */
    public void setJituHouzai(BigDecimal jituHouzai) {
        this.jituHouzai = jituHouzai;
    }
    /**
     * @return jituOther を戻します。
     */
    public BigDecimal getJituOther() {
        return jituOther;
    }
    /**
     * @param jituOther 設定する jituOther。
     */
    public void setJituOther(BigDecimal jituOther) {
        this.jituOther = jituOther;
    }
    /**
     * @return jituShoku を戻します。
     */
    public BigDecimal getJituShoku() {
        return jituShoku;
    }
    /**
     * @param jituShoku 設定する jituShoku。
     */
    public void setJituShoku(BigDecimal jituShoku) {
        this.jituShoku = jituShoku;
    }
    /**
     * @return jituYasai を戻します。
     */
    public BigDecimal getJituYasai() {
        return jituYasai;
    }
    /**
     * @param jituYasai 設定する jituYasai。
     */
    public void setJituYasai(BigDecimal jituYasai) {
        this.jituYasai = jituYasai;
    }
    /**
     * @return stdHouzai を戻します。
     */
    public BigDecimal getStdHouzai() {
        return stdHouzai;
    }
    /**
     * @param stdHouzai 設定する stdHouzai。
     */
    public void setStdHouzai(BigDecimal stdHouzai) {
        this.stdHouzai = stdHouzai;
    }
    /**
     * @return stdOther を戻します。
     */
    public BigDecimal getStdOther() {
        return stdOther;
    }
    /**
     * @param stdOther 設定する stdOther。
     */
    public void setStdOther(BigDecimal stdOther) {
        this.stdOther = stdOther;
    }
    /**
     * @return stdShoku を戻します。
     */
    public BigDecimal getStdShoku() {
        return stdShoku;
    }
    /**
     * @param stdShoku 設定する stdShoku。
     */
    public void setStdShoku(BigDecimal stdShoku) {
        this.stdShoku = stdShoku;
    }
    /**
     * @return stdYasai を戻します。
     */
    public BigDecimal getStdYasai() {
        return stdYasai;
    }
    /**
     * @param stdYasai 設定する stdYasai。
     */
    public void setStdYasai(BigDecimal stdYasai) {
        this.stdYasai = stdYasai;
    }
    /**
     * @return paSal を戻します。
     */
    public BigDecimal getPaSal() {
        return paSal;
    }
    /**
     * @param paSal 設定する paSal。
     */
    public void setPaSal(BigDecimal paSal) {
        this.paSal = paSal;
    }
    /**
     * @return paWh を戻します。
     */
    public BigDecimal getPaWh() {
        return paWh;
    }
    /**
     * @param paWh 設定する paWh。
     */
    public void setPaWh(BigDecimal paWh) {
        this.paWh = paWh;
    }
    /**
     * @return shainSal を戻します。
     */
    public BigDecimal getShainSal() {
        return shainSal;
    }
    /**
     * @param shainSal 設定する shainSal。
     */
    public void setShainSal(BigDecimal shainSal) {
        this.shainSal = shainSal;
    }
    /**
     * @return shainWh を戻します。
     */
    public BigDecimal getShainWh() {
        return shainWh;
    }
    /**
     * @param shainWh 設定する shainWh。
     */
    public void setShainWh(BigDecimal shainWh) {
        this.shainWh = shainWh;
    }
}
