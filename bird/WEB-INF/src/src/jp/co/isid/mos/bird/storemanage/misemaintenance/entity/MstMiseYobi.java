package jp.co.isid.mos.bird.storemanage.misemaintenance.entity;

import java.sql.Timestamp;

/**
 * 店舗拡張マスタ(MstMiseYobi)
 * 
 * 
 * 更新日:2011/07/08 xkinu ガス&エアコン種別項目追加対応
 *
 */
public class MstMiseYobi {
    
    public static final String TABLE = "BM23MIYO";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
//    public static final String emergenceTel_COLUMN = "EMERGENCE_TEL";
//    
//    public static final String emergenceName_COLUMN = "EMERGENCE_NAME";
    
    public static final String yobiFree1_COLUMN = "YOBI_FREE_1";
    
    public static final String yobiFree2_COLUMN = "YOBI_FREE_2";
    
    public static final String yobiFree3_COLUMN = "YOBI_FREE_3";
    
    public static final String yobiFree4_COLUMN = "YOBI_FREE_4";
    
    public static final String yobiFree5_COLUMN = "YOBI_FREE_5";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
// 20060517 追加 start ------------------------------------------------------ 
    public static final String firstUser_COLUMN      = "FIRST_USER";
    
    public static final String firstPgm_COLUMN       = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN      = "FIRST_TMSP";
    
    public static final String emergenceTel1_COLUMN  = "EMERGENCE_TEL1";
    
    public static final String emergenceTel2_COLUMN  = "EMERGENCE_TEL2";
    
    public static final String emergenceTel3_COLUMN  = "EMERGENCE_TEL3";
    
    public static final String emergenceName1_COLUMN = "EMERGENCE_NAME1";
    
    public static final String emergenceName2_COLUMN = "EMERGENCE_NAME2";
    
    public static final String emergenceName3_COLUMN = "EMERGENCE_NAME3";
// 20060517 追加 end --------------------------------------------------------    
// 20110708 追加 start ------------------------------------------------------ 
    /** ガス種別(予備区分１を使用) */
    public static final String yobiKbn1_COLUMN      = "YOBI_KBN_1";
    /** エアコン種別(予備区分２を使用) */
    public static final String yobiKbn2_COLUMN      = "YOBI_KBN_2";
    /** ガス種別名称(BC32GASS.GAS_SHU_NAME) */
    public static final String gasShuName_COLUMN    = "GAS_SHU_NAME";
    /** エアコン種別名称(BC33AIRS.AIR_CON_SHU_NAME) */
    public static final String airConShuName_COLUMN = "AIR_CON_SHU_NAME";
// 20110708 追加 end --------------------------------------------------------    

    
    /**
     * 管理会社コード企業コード
     */
    private String companyCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
//    /**
//     * 緊急連絡先電話番号
//     */
//    private String emergenceTel;
//    
//    /**
//     * 緊急連絡先氏名
//     */
//    private String emergenceName;
    
    /**
     * 予備フリー項目1
     */
    private String yobiFree1;
    
    /**
     * 予備フリー項目2
     */
    private String yobiFree2;
    
    /**
     * 予備フリー項目3
     */
    private String yobiFree3;
    
    /**
     * 予備フリー項目4
     */
    private String yobiFree4;
    
    /**
     * 予備フリー項目5
     */
    private String yobiFree5;
    
    /**
     * 更新ユーザー
     */
    private String lastUser;
    
    /**
     * 更新プログラム
     */
    private String lastPgm;
    
    /**
     * 更新タイムスタンプ
     */
    private Timestamp lastTmsp;
    
// 20060517 追加 start --------------- 
    
    /**
     * 登録ユーザー
     */
    private String firstUser;
    
    /**
     * 登録プログラム
     */
    private String firstPgm;
    
    /**
     * 登録タイムスタンプ
     */
    private Timestamp firstTmsp; 
    
    /**
     * 緊急連絡先電話番号1
     */
    private String emergenceTel1;
    
    /**
     * 緊急連絡先電話番号2
     */
    private String emergenceTel2;
    
    /**
     * 緊急連絡先電話番号3
     */
    private String emergenceTel3;
    
    /**
     * 緊急連絡先氏名1
     */
    private String emergenceName1;
    
    /**
     * 緊急連絡先氏名2
     */
    private String emergenceName2;
    
    /**
     * 緊急連絡先氏名3
     */
    private String emergenceName3;
// 20060517 追加 end ------------------    
//  20110708 追加 start ------------------------------------------------------ 
    /** ガス種別(予備区分１を使用) */
    private String yobiKbn1;
    /** エアコン種別(予備区分２を使用) */
    private String yobiKbn2;
    /** ガス種別名称(BC32GASS.GAS_SHU_NAME) */
    private String gasShuName;
    /** エアコン種別名称(BC33AIRS.AIR_CON_SHU_NAME) */
    private String airConShuName;
// 20110708 追加 end --------------------------------------------------------    
    
    
    /**
     * 管理会社コード企業コードを取得します。
     * @return 管理会社コード企業コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 管理会社コード企業コードを設定します。
     * @param companyCd 管理会社コード企業コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * 店コードを取得します。
     * @return 店コード
     */
    public String getMiseCd() {
        return convString(miseCd);
    }
    /**
     * 店コードを設定します。
     * @param miseCd 店コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    
//    /**
//     * 緊急連絡先電話番号を取得します。
//     * @return 緊急連絡先電話番号
//     */
//    public String getEmergenceTel() {
//        return convString(emergenceTel);
//    }
//    /**
//     * 緊急連絡先電話番号を設定します。
//     * @param emergenceTel 緊急連絡先電話番号
//     */
//    public void setEmergenceTel(String emergenceTel) {
//        this.emergenceTel = emergenceTel;
//    }
//    
//    /**
//     * 緊急連絡先氏名を取得します。
//     * @return 緊急連絡先氏名
//     */
//    public String getEmergenceName() {
//        return convString(emergenceName);
//    }
//    /**
//     * 緊急連絡先氏名を設定します。
//     * @param emergenceName 緊急連絡先氏名
//     */
//    public void setEmergenceName(String emergenceName) {
//        this.emergenceName = emergenceName;
//    }
    
    /**
     * 予備フリー項目1を取得します。
     * @return 予備フリー項目1
     */
    public String getYobiFree1() {
        return convString(yobiFree1);
    }
    /**
     * 予備フリー項目1を設定します。
     * @param yobiFree1 予備フリー項目1
     */
    public void setYobiFree1(String yobiFree1) {
        this.yobiFree1 = yobiFree1;
    }
    
    /**
     * 予備フリー項目2を取得します。
     * @return 予備フリー項目2
     */
    public String getYobiFree2() {
        return convString(yobiFree2);
    }
    /**
     * 予備フリー項目2を設定します。
     * @param yobiFree2 予備フリー項目2
     */
    public void setYobiFree2(String yobiFree2) {
        this.yobiFree2 = yobiFree2;
    }
    
    /**
     * 予備フリー項目3を取得します。
     * @return 予備フリー項目3
     */
    public String getYobiFree3() {
        return convString(yobiFree3);
    }
    /**
     * 予備フリー項目3を設定します。
     * @param yobiFree3 予備フリー項目3
     */
    public void setYobiFree3(String yobiFree3) {
        this.yobiFree3 = yobiFree3;
    }
    
    /**
     * 予備フリー項目4を取得します。
     * @return 予備フリー項目4
     */
    public String getYobiFree4() {
        return convString(yobiFree4);
    }
    /**
     * 予備フリー項目4を設定します。
     * @param yobiFree4 予備フリー項目4
     */
    public void setYobiFree4(String yobiFree4) {
        this.yobiFree4 = yobiFree4;
    }
    
    /**
     * 予備フリー項目5を取得します。
     * @return 予備フリー項目5
     */
    public String getYobiFree5() {
        return convString(yobiFree5);
    }
    /**
     * 予備フリー項目5を設定します。
     * @param yobiFree5 予備フリー項目5
     */
    public void setYobiFree5(String yobiFree5) {
        this.yobiFree5 = yobiFree5;
    }
    
    /**
     * 更新ユーザーを取得します。
     * @return 更新ユーザー
     */
    public String getLastUser() {
        return convString(lastUser);
    }
    /**
     * 更新ユーザーを設定します。
     * @param lastUser 更新ユーザー
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 更新プログラムを取得します。
     * @return 更新プログラム
     */
    public String getLastPgm() {
        return convString(lastPgm);
    }
    /**
     * 更新プログラムを設定します。
     * @param lastPgm 更新プログラム
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    
    /**
     * 更新タイムスタンプを取得します。
     * @return 更新タイムスタンプ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 更新タイムスタンプを設定します。
     * @param lastTmsp 更新タイムスタンプ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
 
// 20060517 追加 start -------------------------------------
    /**
     * 登録ユーザーを取得します。
     * @return 登録ユーザー
     */
    public String getFirstUser() {
        return convString(firstUser);
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
        return convString(firstPgm);
    }
    /**
     * 登録プログラムを設定します。
     * @param firstPgm 登録プログラム
     */
    public void setFirstPgm(String firstPgm) {
        this.firstPgm = firstPgm;
    }
    
    /**
     * 登録タイムスタンプを取得します。
     * @return 登録タイムスタンプ
     */
    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * 登録タイムスタンプを設定します。
     * @param firstTmsp 登録タイムスタンプ
     */
    public void setFirstTmsp(Timestamp firstTmsp) {
        this.firstTmsp = firstTmsp;
    }
    
    /**
     * 緊急連絡先電話番号1を取得します。
     * @return 緊急連絡先電話番号1
     */
    public String getEmergenceTel1() {
        return convString(emergenceTel1);
    }
    /**
     * 緊急連絡先電話番号1を設定します。
     * @param emergenceTel1 緊急連絡先電話番号1
     */
    public void setEmergenceTel1(String emergenceTel1) {
        this.emergenceTel1 = emergenceTel1;
    }
    
    /**
     * 緊急連絡先電話番号2を取得します。
     * @return 緊急連絡先電話番号2
     */
    public String getEmergenceTel2() {
        return convString(emergenceTel2);
    }
    /**
     * 緊急連絡先電話番号2を設定します。
     * @param emergenceTel2 緊急連絡先電話番号2
     */
    public void setEmergenceTel2(String emergenceTel2) {
        this.emergenceTel2 = emergenceTel2;
    }
    
    /**
     * 緊急連絡先電話番号3を取得します。
     * @return 緊急連絡先電話番号3
     */
    public String getEmergenceTel3() {
        return convString(emergenceTel3);
    }
    /**
     * 緊急連絡先電話番号3を設定します。
     * @param emergenceTel3 緊急連絡先電話番号3
     */
    public void setEmergenceTel3(String emergenceTel3) {
        this.emergenceTel3 = emergenceTel3;
    }
    
    /**
     * 緊急連絡先氏名1を取得します。
     * @return 緊急連絡先氏名1
     */
    public String getEmergenceName1() {
        return convString(emergenceName1);
    }
    /**
     * 緊急連絡先氏名1を設定します。
     * @param emergenceName1 緊急連絡先氏名1
     */
    public void setEmergenceName1(String emergenceName1) {
        this.emergenceName1 = emergenceName1;
    }
    
    /**
     * 緊急連絡先氏名2を取得します。
     * @return 緊急連絡先氏名2
     */
    public String getEmergenceName2() {
        return convString(emergenceName2);
    }
    /**
     * 緊急連絡先氏名2を設定します。
     * @param emergenceName2 緊急連絡先氏名2
     */
    public void setEmergenceName2(String emergenceName2) {
        this.emergenceName2 = emergenceName2;
    }
    
    /**
     * 緊急連絡先氏名3を取得します。
     * @return 緊急連絡先氏名3
     */
    public String getEmergenceName3() {
        return convString(emergenceName3);
    }
    /**
     * 緊急連絡先氏名3を設定します。
     * @param emergenceName3 緊急連絡先氏名3
     */
    public void setEmergenceName3(String emergenceName3) {
        this.emergenceName3 = emergenceName3;
    }

// 20060517 追加  end ----------------------------------------
    
    private String convString(String str) {
        String ret = str;
        if (str == null) {
            ret = "";
        }
        ret = ret.trim();
        return ret;
    }
	/**
	 * エアコン種別名称(BC33AIRS.AIR_CON_SHU_NAME)取得処理
	 * 
	 * @return クラス変数airConShuName を戻します。
	 */
	public String getAirConShuName() {
		return airConShuName;
	}
	/**
	 * エアコン種別名称(BC33AIRS.AIR_CON_SHU_NAME)設定処理
	 * 
	 * @param airConShuName を クラス変数airConShuNameへ設定します。
	 */
	public void setAirConShuName(String airConShuName) {
		this.airConShuName = airConShuName;
	}
	/**
	 * ガス種別名称(BC32GASS.GAS_SHU_NAME)取得処理
	 * 
	 * @return クラス変数gasShuName を戻します。
	 */
	public String getGasShuName() {
		return gasShuName;
	}
	/**
	 * ガス種別名称(BC32GASS.GAS_SHU_NAME)設定処理
	 * 
	 * @param gasShuName を クラス変数gasShuNameへ設定します。
	 */
	public void setGasShuName(String gasShuName) {
		this.gasShuName = gasShuName;
	}
	/**
	 * ガス種別(予備区分１を使用)
	 * 
	 * @return クラス変数yobiKbn1 を戻します。
	 */
	public String getYobiKbn1() {
		return yobiKbn1;
	}
	/**
	 * ガス種別(予備区分１を使用)
	 * @param yobiKbn1 を クラス変数yobiKbn1へ設定します。
	 */
	public void setYobiKbn1(String yobiKbn1) {
		this.yobiKbn1 = yobiKbn1;
	}
	/**
	 * エアコン種別(予備区分２を使用)
	 * 
	 * @return クラス変数yobiKbn2 を戻します。
	 */
	public String getYobiKbn2() {
		return yobiKbn2;
	}
	/**
	 * エアコン種別(予備区分２を使用)
	 * 
	 * @param yobiKbn2 を クラス変数yobiKbn2へ設定します。
	 */
	public void setYobiKbn2(String yobiKbn2) {
		this.yobiKbn2 = yobiKbn2;
	}
}
