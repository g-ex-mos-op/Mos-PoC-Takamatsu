package jp.co.isid.mos.bird.bizreport.posreportref.entity;

import java.math.BigDecimal;

/**
 * POS速報メニュー別エンティティ
 * 
 * 更新日:2010/11/26 xkinu BT77MRALテーブルへCAMP_FLGの項目追加対応
 *                         このCAMP_FLGでキャンペーン対象メニューか否かの判断を行えます。
 * @author xkinu
 *
 */
public class TrnPosReportRefMenu {
    
    public static final String TABLE = "BT77MRAL";
    
    public static final String compnayCd_COLUMN = "COMPNAY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String eigyoDt_COLUMN = "EIGYO_DT";
    
    public static final String dataNum_COLUMN = "DATA_NUM";
    
    public static final String menuCd_COLUMN = "MENU_CD";
    
    public static final String shuSysDt_COLUMN = "SHU_SYS_DT";
    
    public static final String shuSysTime_COLUMN = "SHU_SYS_TIME";
    
    public static final String kazuEat_COLUMN = "KAZU_EAT";
    
    public static final String kazuTake_COLUMN = "KAZU_TAKE";
    
    public static final String kazuTel_COLUMN = "KAZU_TEL";
    
    public static final String kazuDrive_COLUMN = "KAZU_DRIVE";
    
    public static final String kazuOther_COLUMN = "KAZU_OTHER";
    
    public static final String menuTanka_COLUMN = "MENU_TANKA";
    
    public static final String menuNameKj_COLUMN = "MENU_NAME_KJ";
    /** ヴァーチャルカラム：キャンペーンフラグ */
    public static final String campFlg_COLUMN = "CAMP_FLG";
    /** ヴァーチャルカラム：売上個数 */
    public static final String uriageCount_COLUMN = "URIAGE_COUNT";
    /** ヴァーチャルカラム：売上金額 */
    public static final String uriageKingaku_COLUMN = "URIAGE";
    
    /**
     * 企業コード
     */
    private String compnayCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 営業日
     */
    private String eigyoDt;
    
    /**
     * 回数
     */
    private String dataNum;
    
    /**
     * メニューコード
     */
    private String menuCd;
    
    /**
     * 集計日付
     */
    private String shuSysDt;
    
    /**
     * 集計時刻
     */
    private String shuSysTime;
    
    /**
     * EAT_IN個数
     */
    private String kazuEat;
    
    /**
     * TAKE_OUT個数
     */
    private String kazuTake;
    
    /**
     * TEL_ORDER個数
     */
    private String kazuTel;
    
    /**
     * DRIVE_TH個数
     */
    private String kazuDrive;
    
    /**
     * その他の個数
     */
    private String kazuOther;
    
    /**
     * メニュー単価
     */
    private String menuTanka;
    
    /**
     * 売上金額
     */
    private String uriageKingaku;
    
    /**
     * 売上個数
     */
    private String uriageCount;

    /**
     * メニュー名称
     */
    private String menuNameKj;
    /**
     * キャンペーンフラグ
     * 作成日:2010/11/26
     */
    private String campFlg;
    /**
     * 金額構成比
     * 作成日:2010/11/26
     */
    private BigDecimal kingakuKouseiHi;
    
    /**
     * 企業コードを取得します。
     * @return 企業コード
     */
    public String getCompnayCd() {
        return compnayCd;
    }
    /**
     * 企業コードを設定します。
     * @param compnayCd 企業コード
     */
    public void setCompnayCd(String compnayCd) {
        this.compnayCd = compnayCd;
    }
    
    /**
     * 店コードを取得します。
     * @return 店コード
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * 店コードを設定します。
     * @param miseCd 店コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
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
     * 回数を取得します。
     * @return 回数
     */
    public String getDataNum() {
        return dataNum;
    }
    /**
     * 回数を設定します。
     * @param dataNum 回数
     */
    public void setDataNum(String dataNum) {
        this.dataNum = dataNum;
    }
    
    /**
     * メニューコードを取得します。
     * @return メニューコード
     */
    public String getMenuCd() {
        return menuCd;
    }
    /**
     * メニューコードを設定します。
     * @param menuCd メニューコード
     */
    public void setMenuCd(String menuCd) {
        this.menuCd = menuCd;
    }
    
    /**
     * 集計日付を取得します。
     * @return 集計日付
     */
    public String getShuSysDt() {
        return shuSysDt;
    }
    /**
     * 集計日付を設定します。
     * @param shuSysDt 集計日付
     */
    public void setShuSysDt(String shuSysDt) {
        this.shuSysDt = shuSysDt;
    }
    
    /**
     * 集計時刻を取得します。
     * @return 集計時刻
     */
    public String getShuSysTime() {
        return shuSysTime;
    }
    /**
     * 集計時刻を設定します。
     * @param shuSysTime 集計時刻
     */
    public void setShuSysTime(String shuSysTime) {
        this.shuSysTime = shuSysTime;
    }
    
    /**
     * EAT_IN個数を取得します。
     * @return EAT_IN個数
     */
    public String getKazuEat() {
        return kazuEat;
    }
    /**
     * EAT_IN個数を設定します。
     * @param kazuEat EAT_IN個数
     */
    public void setKazuEat(String kazuEat) {
        this.kazuEat = kazuEat;
    }
    
    /**
     * TAKE_OUT個数を取得します。
     * @return TAKE_OUT個数
     */
    public String getKazuTake() {
        return kazuTake;
    }
    /**
     * TAKE_OUT個数を設定します。
     * @param kazuTake TAKE_OUT個数
     */
    public void setKazuTake(String kazuTake) {
        this.kazuTake = kazuTake;
    }
    
    /**
     * TEL_ORDER個数を取得します。
     * @return TEL_ORDER個数
     */
    public String getKazuTel() {
        return kazuTel;
    }
    /**
     * TEL_ORDER個数を設定します。
     * @param kazuTel TEL_ORDER個数
     */
    public void setKazuTel(String kazuTel) {
        this.kazuTel = kazuTel;
    }
    
    /**
     * DRIVE_TH個数を取得します。
     * @return DRIVE_TH個数
     */
    public String getKazuDrive() {
        return kazuDrive;
    }
    /**
     * DRIVE_TH個数を設定します。
     * @param kazuDrive DRIVE_TH個数
     */
    public void setKazuDrive(String kazuDrive) {
        this.kazuDrive = kazuDrive;
    }
    
    /**
     * その他の個数を取得します。
     * @return その他の個数
     */
    public String getKazuOther() {
        return kazuOther;
    }
    /**
     * その他の個数を設定します。
     * @param kazuOther その他の個数
     */
    public void setKazuOther(String kazuOther) {
        this.kazuOther = kazuOther;
    }
    
    /**
     * メニュー単価を取得します。
     * @return メニュー単価
     */
    public String getMenuTanka() {
        return menuTanka;
    }

    /**
     * メニュー単価を設定します。
     * @return メニュー単価
     */
    public void setMenuTanka(String menuTanka) {
        this.menuTanka = menuTanka;
    }

    /**
     * 売上金額を取得します。
     * @return 売上金額
     */
    public void setUriageKingaku(String uriage) {
        uriageKingaku = uriage;
    }
    /**
     * 売上金額を取得します。
     * @return 売上金額
     */
    public String getUriageKingaku() {
        return uriageKingaku;
    }
    /**
     * 売上個数を取得します。
     * @return 売上個数
     */
    public String getUriageCount() {
        return uriageCount;
    }
    /**
     * メニュー名称を取得します。
     * @return メニュー名称
     */
    public String getMenuNameKj() {
        return menuNameKj;
    }
    /**
     * メニュー名称を設定します。
     * @param menuNameKj メニュー名称
     */
    public void setMenuNameKj(String menuNameKj) {
        this.menuNameKj = menuNameKj;
    }
    /**
     * キャンペーンフラグ取得処理
     * 
     * キャンペーン対象期間内のメニューの場合は
     * ”1”の値が設定されています。
     * @return flg String キャンペーンフラグ
     */
	public String getCampFlg() {
		return campFlg;
	}
	/**
	 * キャンペーンフラグ設定処理
	 * @param campFlg
	 */
	public void setCampFlg(String campFlg) {
		this.campFlg = campFlg;
	}
	/**
	 * キャンペーンメニュー判断値取得処理
	 * キャンペーン対象期間内のメニューの場合はtrueを戻します。
	 * @return boolean
	 */
    public boolean isCampMenu() {
    	return "1".equals(getCampFlg());
    }
    public void setCampMenu(boolean is) {
    }
	public BigDecimal getKingakuKouseiHi() {
		return kingakuKouseiHi;
	}
	public void setKingakuKouseiHi(BigDecimal kingakuKouseiHi) {
		this.kingakuKouseiHi = kingakuKouseiHi;
	}
	/**
	 * @param uriageCount を クラス変数uriageCountへ設定します。
	 */
	public void setUriageCount(String uriageCount) {
		this.uriageCount = uriageCount;
	}
}
