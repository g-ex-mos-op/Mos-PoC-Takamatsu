/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.vansmastregist.entity;

import java.sql.Timestamp;

/**
 * 代表商品情報
 * @author narita
 */
public class UIVansShohinInfo {

    public static final String TABLE = "BM54BSET";

    /** 排他処理用のタイムスタンプ */
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";

    public static final String kanriMoto_COLUMN = "KANRI_MOTO";

    public static final String kanriMotoKj_COLUMN = "KANRI_MOTO_KJ";

    public static final String sokoCd_COLUMN = "SOKO_CD";

    public static final String sokoNameKj_COLUMN = "SOKO_NAME_KJ";

    public static final String shoCdDai_COLUMN = "SHO_CD_DAI";

    public static final String shoNisugata_COLUMN = "SHO_NISUGATA";

    public static final String shoNameKj_COLUMN = "SHO_NAME_KJ";

    public static final String firstUser_COLUMN = "FIRST_USER";

    public static final String firstPgm_COLUMN = "FIRST_PGM";

    public static final String firstTmsp_COLUMN = "FIRST_TMSP";

    public static final String lastUser_COLUMN = "LAST_USER";

    public static final String lastPgm_COLUMN = "LAST_PGM";

    public static final String lastTmsp_COLUMN = "LAST_TMSP";

    /**
     * 管理元コード
     */
    private String kanriMoto;

    /**
     * 管理元名称
     */
    private String kanriMotoKj;

    /**
     * 倉庫コード
     */
    private String sokoCd;

    /**
     * 倉庫名称
     */
    private String sokoNameKj;

    /**
     * 代表商品コード
     */
    private String shoCdDai;

    /**
     * 商品荷姿コード
     */
    private String shoNisugata;

    /**
     * 商品名称
     */
    private String shoNameKj;

    /**
     * 商品荷姿名称
     */
    private String shoNisugataKj;

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
     * 登録モード
     */
    private String dataMode;

    /**
     * 未設定フラグ
     */
    private boolean notingFlg;

    /**
     * 削除フラグ
     */
    private boolean deleteFlg;

    /**
     * 入力欄追加リンク有効フラグ
     */
    private boolean addLinkFlg;

    /**
     * 開始管理フラグ
     */
    private boolean firstKanriFlg;

    /**
     * 開始倉庫フラグ
     */
    private boolean firstSokoFlg;

    /**
     * 確認画面での非表示時フラグ
     */
    private boolean noDispFlg;

    /**
     * ３行追加時の倉庫情報表示フラグ
     */
    private boolean dispSokoInfoFlg;

    /**
     * 登録商品数
     */
    private int shohinCount;

    /**
     * 確認画面表示時の登録商品数
     */
    private int dispShohinCount;

    /**
     * 画面表示ＩＤ 商品コード
     */
    private String dispIdShoCdDai;

    /**
     * 画面表示ＩＤ 荷姿
     */
    private String dispIdNisugata;

    /**
     * 管理元コードを取得します。
     * @return 管理元コード
     */
    public String getSokoCd() {
        return sokoCd;
    }
    /**
     * 管理元コードを設定します。
     * @param sokoCd 管理元コード
     */
    public void setSokoCd(String sokoCd) {
        this.sokoCd = sokoCd;
    }

    /**
     * 倉庫名称を取得します。
     * @return 倉庫名称
     */
    public String getSokoNameKj() {
    	if(sokoNameKj == null)
    		return sokoNameKj;
        return sokoNameKj.replaceAll("[　*| *]*$", "");
    }
    /**
     * 倉庫名称を設定します。
     * @param sokoNameKj 倉庫名称
     */
    public void setSokoNameKj(String sokoNameKj) {
        this.sokoNameKj = sokoNameKj;
    }

    /**
     * 代表商品コードを取得します。
     * @return 代表商品コード
     */
    public String getShoCdDai() {
        return shoCdDai;
    }
    /**
     * 代表商品コードを設定します。
     * @param shoCdDai 代表商品コード
     */
    public void setShoCdDai(String shoCdDai) {
        this.shoCdDai = shoCdDai;
    }

    /**
     * 商品荷姿コードを取得します。
     * @return 商品荷姿コード
     */
    public String getShoNisugata() {
        return shoNisugata;
    }
    /**
     * 商品荷姿コードを設定します。
     * @param shoNisugata 商品荷姿コード
     */
    public void setShoNisugata(String shoNisugata) {
        this.shoNisugata = shoNisugata;
    }

    /**
     * 商品名称を取得します。
     * @return 商品名称
     */
    public String getShoNameKj() {
    	if(shoNameKj == null)
    		return shoNameKj;
    	return shoNameKj.replaceAll("[　*| *]*$", "");
    }
    /**
     * 商品名称を設定します。
     * @param shoNameKj 商品名称
     */
    public void setShoNameKj(String shoNameKj) {
        this.shoNameKj = shoNameKj;
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
	public String getDataMode() {
		return dataMode;
	}
	public void setDataMode(String dataMode) {
		this.dataMode = dataMode;
	}
	public String getKanriMoto() {
		return kanriMoto;
	}
	public void setKanriMoto(String kanriMoto) {
		this.kanriMoto = kanriMoto;
	}
	public String getKanriMotoKj() {
		return kanriMotoKj;
	}
	public void setKanriMotoKj(String kanriMotoKj) {
		this.kanriMotoKj = kanriMotoKj;
	}
	public boolean isAddLinkFlg() {
		return addLinkFlg;
	}
	public void setAddLinkFlg(boolean addLinkFlg) {
		this.addLinkFlg = addLinkFlg;
	}
	public int getShohinCount() {
		return shohinCount;
	}
	public void setShohinCount(int shohinCount) {
		this.shohinCount = shohinCount;
	}
	public boolean isFirstKanriFlg() {
		return firstKanriFlg;
	}
	public void setFirstKanriFlg(boolean firstKanriFlg) {
		this.firstKanriFlg = firstKanriFlg;
	}
	public boolean isFirstSokoFlg() {
		return firstSokoFlg;
	}
	public void setFirstSokoFlg(boolean firstSokoFlg) {
		this.firstSokoFlg = firstSokoFlg;
	}
	public boolean isDeleteFlg() {
		return deleteFlg;
	}
	public void setDeleteFlg(boolean deleteFlg) {
		this.deleteFlg = deleteFlg;
	}
	public String getShoNisugataKj() {
		return shoNisugataKj;
	}
	public void setShoNisugataKj(String shoNisugataKj) {
		this.shoNisugataKj = shoNisugataKj;
	}
	public boolean isNotingFlg() {
		return notingFlg;
	}
	public void setNotingFlg(boolean notingFlg) {
		this.notingFlg = notingFlg;
	}

	public String getDispIdShoCdDai() {
		return dispIdShoCdDai;
		//return VansmastRegistConstants.FOCUS_OSHO_CD_DAI + kanriMoto;
	}
	public void setDispIdShoCdDai(String dispIdShoCdDai) {
		this.dispIdShoCdDai = dispIdShoCdDai;
	}
	public String getDispIdNisugata() {
		return dispIdNisugata;
		//return VansmastRegistConstants.FOCUS_SHO_NISUGATA + kanriMoto;
	}
	public void setDispIdNisugata(String dispIdNisugata) {
		this.dispIdNisugata = dispIdNisugata;
	}
	public int getDispShohinCount() {
		return dispShohinCount;
	}
	public void setDispShohinCount(int dispShohinCount) {
		this.dispShohinCount = dispShohinCount;
	}
	public boolean isNoDispFlg() {
		return noDispFlg;
	}
	public void setNoDispFlg(boolean noDispFlg) {
		this.noDispFlg = noDispFlg;
	}
	public boolean isDispSokoInfoFlg() {
		return dispSokoInfoFlg;
	}
	public void setDispSokoInfoFlg(boolean dispSokoInfoFlg) {
		this.dispSokoInfoFlg = dispSokoInfoFlg;
	}
}
