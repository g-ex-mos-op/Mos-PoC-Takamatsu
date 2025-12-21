package jp.co.isid.mos.bird.bizreport.common.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * 業務管理 売上推移表・検索条件の共通Dto
 * 『売上推移表』画面と『宅配売上推移表』画面の検索条件のみを保持し、
 *  互いの画面への遷移時にここで保持した内容を受け渡すためのクラス。
 *  
 *  複数ページに対応するため、各条件はMap形式で保持し、【WindowId】をキーとして保持する。
 * 
 * @author xwatanabe
 */
public class SuiiRefCommonCondDto {

    /** 分類種別定数 */
    // 月次
    public static final String CLASSKIND_MONTHLY = "0";
    // 日次
    public static final String CLASSKIND_DAILY = "1";

    /** ウィンドウIDカウンター */
    private int count = 0;
    /** ウィンドウID */
    private Integer windowId = new Integer(0);

    //----------------------------
    // 各検索条件値
    //----------------------------
    /** 画面ID */
    private Map screenId = new HashMap();
    /** 会社コード */
    private Map companyCd = new HashMap();
    /** 店舗種別 */
    private Map tenpoShubetu = new HashMap();
    /** 対象期間 */
    private Map taishoKikan = new HashMap();
    /** 期間指定 */
    private Map kikanSitei = new HashMap();
    /** 対象条件 */
    private Map taishoJoken = new HashMap();
    /** 表示対象 */
    private Map hyojiTaisho = new HashMap();
    /** ブロックコード */
    private Map blockCd = new HashMap();
    /** 前年データ種別 */
    private Map zenDataShubetu = new HashMap();

    //----------------------------
    // 画面遷移情報
    //----------------------------
    /** 画面遷移フラグ = true : 画面遷移、= false : 通常*/
    private boolean actionFlg;
    /** 分類種別 = 0 : 月次、= 1 : 日次*/
    private int classKind;

    /**
     * ウィンドウIDのカウンターを１つ増やし、その値を返却する
     * @return 最大ウィンドウIDをインクリメントした値
     */
    public int nextWindowId() {
        return ++count;
    }

    /**
     * 初期クリア処理
     *
     */
    public void clear() {
        setScreenId(null);
        setCompanyCd(null);
        setTenpoShubetu(null);
        setTaishoKikan(null);
        setKikanSitei(null);
        setTaishoJoken(null);
        setHyojiTaisho(null);
        setBlockCd(null);
        setZenDataShubetu(null);
    }

    ///////////////以下、セッター・ゲッター///////////////////////

    /**
     * ウィンドウIDを取得
     * @return ウィンドウID
     */
    public int getWindowId() {
        return windowId.intValue();
    }
    /**
     * ウィンドウIDのセット
     * @param ウィンドウID
     */
    public void setWindowId(int num) {
        this.windowId = new Integer(num);
    }
    /**
     * 画面ID取得処理
     */
    public String getScreenId(){
        return (String) screenId.get(windowId);
    }
    /**
     * 画面ID設定処理
     * @param companyCd
     */
    public void setScreenId(String companyCd){
        this.screenId.put(windowId, companyCd);
    }
   
    /**
     * 会社コードを取得します。
     */
    public String getCompanyCd(){
        return (String) companyCd.get(windowId);
    }
    /**
     * 会社コードを設定します。
     */
    public void setCompanyCd(String companyCd){
        this.companyCd.put(windowId, companyCd);
    }

    public String getBlockCd() {
        return (String) blockCd.get(windowId);
    }

    public void setBlockCd(String blockCd) {
        this.blockCd.put(windowId, blockCd);
    }

    public String getHyojiTaisho() {
        return (String) hyojiTaisho.get(windowId);
    }

    public void setHyojiTaisho(String hyojiTaisho) {
        this.hyojiTaisho.put(windowId, hyojiTaisho);
    }

    public String getKikanSitei() {
        return (String) kikanSitei.get(windowId);
    }

    public void setKikanSitei(String kikanSitei) {
        this.kikanSitei.put(windowId, kikanSitei);
    }

    public String getTaishoJoken() {
        return (String) taishoJoken.get(windowId);
    }

    public void setTaishoJoken(String taishoJoken) {
        this.taishoJoken.put(windowId, taishoJoken);
    }

    public String getTaishoKikan() {
        return (String) taishoKikan.get(windowId);
    }

    public void setTaishoKikan(String taishoKikan) {
        this.taishoKikan.put(windowId, taishoKikan);
    }

    public String getTenpoShubetu() {
        return (String) tenpoShubetu.get(windowId);
    }

    public void setTenpoShubetu(String tenpoShubetu) {
        this.tenpoShubetu.put(windowId, tenpoShubetu);
    }

    public String getZenDataShubetu() {
        return (String) zenDataShubetu.get(windowId);
    }

    public void setZenDataShubetu(String zenDataShubetu) {
        this.zenDataShubetu.put(windowId, zenDataShubetu);
    }

    public boolean isActionFlg() {
        return actionFlg;
    }

    public void setActionFlg(boolean actionFlg) {
        this.actionFlg = actionFlg;
    }

    public int getClassKind() {
        return classKind;
    }

    public void setClassKind(int classKind) {
        this.classKind = classKind;
    }
}
