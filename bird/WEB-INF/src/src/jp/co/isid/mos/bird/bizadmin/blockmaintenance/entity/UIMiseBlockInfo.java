package jp.co.isid.mos.bird.bizadmin.blockmaintenance.entity;

import java.sql.Timestamp;

import jp.co.isid.mos.bird.bizadmin.blockmaintenance.common.BlockMaintenanceUtil;

/**
 * 店舗ブロック設定情報エンティティークラス
 * 
 * @author xkinu
 *
 */
public class UIMiseBlockInfo {
    
    public static final String TABLE = "BC13MBLK";
    
    /** 排他処理用のタイムスタンプ */
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";

    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String companyName_COLUMN = "COMPANY_NAME";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
    public static final String blockCd_COLUMN = "BLOCK_CD";
    
    public static final String blockName_COLUMN = "BLOCK_NAME";
    
    public static final String changeFlg_COLUMN = "CHANGE_FLG";
    
    public static final String moveBlockCd_COLUMN = "MOVE_BLOCK_CD";
    
    public static final String moveBlockName_COLUMN = "MOVE_BLOCK_NAME";
    
    public static final String selectFlg_COLUMN = "SELECT_FLG";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    public static final String closeMj_COLUMN = "CLOSE_MJ";
    
    public static final String SELECTED = "1";
    public static final String NOT_CHANGE = "1";
    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * 会社名称
     */
    private String companyName;
    
    /**
     * 店舗コード
     */
    private String miseCd;
    
    /**
     * 店舗名称
     */
    private String miseNameKj;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部
     */
    private String sibuName;
    
    /**
     * ブロックコード
     */
    private String blockCd;
    
    /**
     * ブロック名称
     */
    private String blockName;
    
    /**
     * 画面変更フラグ
     */
    private String changeFlg;
    
    /**
     * 新ブロックコード
     */
    private String moveBlockCd;
    
    /**
     * 新ブロック名称
     */
    private String moveBlockName;
    
    /**
     * 新ブロック画面変更フラグ
     */
    private String moveBlockChangeFlg;
    
    /**
     * 選択フラグ
     */
    private String selectFlg;
    
    /**
     * 登録ユーザー
     */
    private String firstUser;
    
    /**
     * 登録プログラム
     */
    private String firstPgm;
    
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp firstTmsp;
    
    /**
     * 修正ユーザー
     */
    private String lastUser;
    
    /**
     * 修正プログラム
     */
    private String lastPgm;
    
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp lastTmsp;
    /**
     * クローズ文字列
     * 
     */
    private String closeMj;
    /**
     * 表示・非表示判断フラグ
     */
    private boolean dispFlg = true;
    /**
     * 会社コードを取得します。
     * @return 会社コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 会社コードを設定します。
     * @param companyCd 会社コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * 会社名称を取得します。
     * @return 会社名称
     */
    public String getCompanyName() {
        return companyName;
    }
    /**
     * 会社名称を設定します。
     * @param companyName 会社名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    /**
     * 店舗コードを取得します。
     * @return 店舗コード
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * 店舗コードを設定します。
     * @param miseCd 店舗コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    
    /**
     * 店舗名称を取得します。
     * @return 店舗名称
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 店舗名称を設定します。
     * @param miseNameKj 店舗名称
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * 支部コードを取得します。
     * @return 支部コード
     */
    public String getSibuCd() {
        return sibuCd;
    }
    /**
     * 支部コードを設定します。
     * @param sibuCd 支部コード
     */
    public void setSibuCd(String sibuCd) {
        this.sibuCd = sibuCd;
    }
    
    /**
     * 支部を取得します。
     * @return 支部
     */
    public String getSibuName() {
        return sibuName;
    }
    /**
     * 支部を設定します。
     * @param sibuName 支部
     */
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
    }
    
    /**
     * ブロックコードを取得します。
     * @return ブロックコード
     */
    public String getBlockCd() {
        return blockCd;
    }
    /**
     * ブロックコードを設定します。
     * @param blockCd ブロックコード
     */
    public void setBlockCd(String blockCd) {
        this.blockCd = blockCd;
    }
    
    /**
     * ブロック名称を取得します。
     * @return ブロック名称
     */
    public String getBlockName() {
        return blockName;
    }
    /**
     * ブロック名称を設定します。
     * @param blockName ブロック名称
     */
    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }
    
    /**
     * 画面変更フラグを取得します。
     * 
     * @return true 変更可、　false 変更不可
     */
    public boolean isChange() {
        return !NOT_CHANGE.equals(changeFlg);
    }
    /**
     * 画面変更フラグを取得します。
     * @return 画面変更フラグ
     */
    public String getChangeFlg() {
        return changeFlg;
    }
    /**
     * 画面変更フラグを設定します。
     * @param changeFlg 画面変更フラグ
     */
    public void setChangeFlg(String changeFlg) {
        this.changeFlg = changeFlg;
    }
    
    /**
     * 新ブロックコードを取得します。
     * @return 新ブロックコード
     */
    public String getMoveBlockCd() {
        return moveBlockCd;
    }
    /**
     * 新ブロックコードを設定します。
     * @param moveBlockCd 新ブロックコード
     */
    public void setMoveBlockCd(String moveBlockCd) {
        this.moveBlockCd = moveBlockCd;
    }
    
    /**
     * 新ブロック名称を取得します。
     * @return 新ブロック名称
     */
    public String getMoveBlockName() {
        return moveBlockName;
    }
    /**
     * 新ブロック名称を設定します。
     * @param moveBlockName 新ブロック名称
     */
    public void setMoveBlockName(String moveBlockName) {
        this.moveBlockName = moveBlockName;
    }
    
    /**
     * 選択フラグを取得します。
     * @return 選択フラグ
     */
    public boolean isSelect() {
        return SELECTED.equals(selectFlg);
    }
    /**
     * 選択フラグを取得します。
     * @return 選択フラグ
     */
    public void setSelect(boolean flg) {
    	if(flg) {
    		selectFlg = SELECTED;
    	}
    	else {
    		selectFlg = "0";
    	}
    }
    /**
     * 選択フラグを取得します。
     * @return 選択フラグ
     */
    public String getSelectFlg() {
        return selectFlg;
    }
    /**
     * 選択フラグを設定します。
     * @param selectFlg 選択フラグ
     */
    public void setSelectFlg(String selectFlg) {
        this.selectFlg = selectFlg;
    }
    
    /**
     * 登録ユーザーを取得します。
     * @return 登録ユーザー
     */
    public String getFirstUser() {
        return firstUser;
    }
    /**
     * 登録ユーザーを設定します。
     * @param firstUser 登録ユーザー
     */
    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
    }
    
    /**
     * 登録プログラムを取得します。
     * @return 登録プログラム
     */
    public String getFirstPgm() {
        return firstPgm;
    }
    /**
     * 登録プログラムを設定します。
     * @param firstPgm 登録プログラム
     */
    public void setFirstPgm(String firstPgm) {
        this.firstPgm = firstPgm;
    }
    
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟを取得します。
     * @return 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟを設定します。
     * @param firstTmsp 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public void setFirstTmsp(Timestamp firstTmsp) {
        this.firstTmsp = firstTmsp;
    }
    
    /**
     * 修正ユーザーを取得します。
     * @return 修正ユーザー
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * 修正ユーザーを設定します。
     * @param lastUser 修正ユーザー
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 修正プログラムを取得します。
     * @return 修正プログラム
     */
    public String getLastPgm() {
        return lastPgm;
    }
    /**
     * 修正プログラムを設定します。
     * @param lastPgm 修正プログラム
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟを取得します。
     * @return 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟを設定します。
     * @param lastTmsp 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
	/**
	 * @return closeMj を戻します。
	 */
	public String getCloseMj() {
		return closeMj;
	}
	/**
	 * @param closeMj 設定する closeMj。
	 */
	public void setCloseMj(String closeMj) {
		this.closeMj = closeMj;
	}
	/**
	 * 表示・非表示判断フラグ
	 * @return dispFlg を戻します。
	 */
	public boolean isDispFlg() {
		return dispFlg;
	}
	/**
	 * 表示・非表示判断フラグ
	 * @param dispFlg 設定する dispFlg。
	 */
	public void setDispFlg(boolean dispFlg) {
		this.dispFlg = dispFlg;
	}
	/**
	 * @return moveBlockChangeFlg を戻します。
	 */
	public String getMoveBlockChangeFlg() {
		return moveBlockChangeFlg;
	}
	/**
	 * @param moveBlockChangeFlg 設定する moveBlockChangeFlg。
	 */
	public void setMoveBlockChangeFlg(String moveBlockChangeFlg) {
		this.moveBlockChangeFlg = moveBlockChangeFlg;
	}
    /**
     * 新ブロック画面変更フラグを取得します。
     * 
     * @return true 変更可、　false 変更不可
     */
    public boolean isMoveBlockChange() {
        return !NOT_CHANGE.equals(moveBlockChangeFlg);
    }
    public boolean isMove() {
    	return !BlockMaintenanceUtil.isNull(getMoveBlockCd());
    }
}
