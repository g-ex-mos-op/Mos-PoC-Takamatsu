package jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.entity;

import java.sql.Timestamp;

import jp.co.isid.mos.bird.common.util.CommonUtil;

/**
 * 入金明細PDF情報エンティティ
 * 
 * @auth xkinu
 * 作成日：2009/06/24
 */
public class TrnPayDetaileStatement {
    
    public static final String TABLE = "BD25MEIR";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String urikakeCd_COLUMN = "URIKAKE_CD";
    
    public static final String urikakeName_COLUMN = "URIKAKE_NAME";
    
    public static final String keisanYm_COLUMN = "KEISAN_YM";
    
    public static final String keiCategory_COLUMN = "KEI_CATEGORY";
    
    public static final String keiShu_COLUMN = "KEI_SHU";
    
    public static final String seq_COLUMN = "SEQ";
    
    public static final String hakkoDt_COLUMN = "HAKKO_DT";
    
    public static final String fileName_COLUMN = "FILE_NAME";
    
    public static final String saveDir_COLUMN = "SAVE_DIR";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    /**
     * 会社コード
     */
    private String companyCd =CommonUtil.COMPANY_CD_MOS;
    
    /**
     * 売掛先コード
     */
    private String urikakeCd;
    
    /**
     * 売掛先名称
     */
    private String urikakeName;
    
    /**
     * 対象年月日
     */
    private String keisanYm;
    
    /**
     * 計算書カテゴリ
     */
    private String keiCategory = "03";
    
    /**
     * 計算書種別
     */
    private String keiShu = "01";
    
    /**
     * 回数
     */
    private String seq;
    
    /**
     * 発行日
     */
    private String hakkoDt;
    
    /**
     * ファイル名称
     */
    private String fileName;
    
    /**
     * 保存ディレクトリー
     */
    private String saveDir;
    /**
     * 登録ユーザーID
     */
    private String firstUser;
    
    /**
     * 登録タイムスタンプ
     */
    private Timestamp firstTmsp;
    
    /**
     * 登録プログラム
     */
    private String firstPgm;
    
    /**
     * 更新ユーザーID
     */
    private String lastUser;
    
    /**
     * 更新タイムスタンプ
     */
    private Timestamp lastTmsp;
    
    /**
     * 更新プログラム
     */
    private String lastPgm;
    
    /**
     * 会社コード取得処理
     * @return 会社コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 会社コード設定処理
     * @param companyCd 会社コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * 売掛先コード取得処理
     * @return 売掛先コード
     */
    public String getUrikakeCd() {
        return urikakeCd;
    }
    /**
     * 売掛先コード設定処理
     * @param urikakeCd 売掛先コード
     */
    public void setUrikakeCd(String urikakeCd) {
        this.urikakeCd = urikakeCd;
    }
    
    /**
     * 売掛先名称取得処理
     * @return 売掛先名称
     */
    public String getUrikakeName() {
        return urikakeName;
    }
    /**
     * 売掛先名称設定処理
     * @param urikakeName 売掛先名称
     */
    public void setUrikakeName(String urikakeName) {
        this.urikakeName = urikakeName;
    }
    
    /**
     * 対象年月日取得処理
     * @return 対象年月日
     */
    public String getKeisanYm() {
        return keisanYm;
    }
    /**
     * 対象年月日設定処理
     * @param keisanYm 対象年月日
     */
    public void setKeisanYm(String keisanYm) {
        this.keisanYm = keisanYm;
    }
    
    /**
     * 計算書カテゴリ取得処理
     * @return 計算書カテゴリ
     */
    public String getKeiCategory() {
        return keiCategory;
    }
    /**
     * 計算書カテゴリ設定処理
     * @param keiCategory 計算書カテゴリ
     */
    public void setKeiCategory(String keiCategory) {
        this.keiCategory = keiCategory;
    }
    
    /**
     * 計算書種別取得処理
     * @return 計算書種別
     */
    public String getKeiShu() {
        return keiShu;
    }
    /**
     * 計算書種別設定処理
     * @param keiShu 計算書種別
     */
    public void setKeiShu(String keiShu) {
        this.keiShu = keiShu;
    }
    
    /**
     * 回数取得処理
     * @return 回数
     */
    public String getSeq() {
        return seq;
    }
    /**
     * 回数設定処理
     * @param seq 回数
     */
    public void setSeq(String seq) {
        this.seq = seq;
    }
    
    /**
     * 発行日取得処理
     * @return 発行日
     */
    public String getHakkoDt() {
        return hakkoDt;
    }
    /**
     * 発行日設定処理
     * @param hakkoDt 発行日
     */
    public void setHakkoDt(String hakkoDt) {
        this.hakkoDt = hakkoDt;
    }
    
    /**
     * ファイル名称取得処理
     * @return ファイル名称
     */
    public String getFileName() {
        return fileName;
    }
    /**
     * ファイル名称設定処理
     * @param fileName ファイル名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    /**
     * 登録ユーザーID取得処理
     * @return 登録ユーザーID
     */
    public String getFirstUser() {
        return firstUser;
    }
    /**
     * 登録ユーザーID設定処理
     * @param firstUser 登録ユーザーID
     */
    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
    }
    
    /**
     * 登録タイムスタンプ取得処理
     * @return 登録タイムスタンプ
     */
    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * 登録タイムスタンプ設定処理
     * @param firstTmsp 登録タイムスタンプ
     */
    public void setFirstTmsp(Timestamp firstTmsp) {
        this.firstTmsp = firstTmsp;
    }
    
    /**
     * 登録プログラム取得処理
     * @return 登録プログラム
     */
    public String getFirstPgm() {
        return firstPgm;
    }
    /**
     * 登録プログラム設定処理
     * @param firstPgm 登録プログラム
     */
    public void setFirstPgm(String firstPgm) {
        this.firstPgm = firstPgm;
    }
    
    /**
     * 更新ユーザーID取得処理
     * @return 更新ユーザーID
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * 更新ユーザーID設定処理
     * @param lastUser 更新ユーザーID
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 更新タイムスタンプ取得処理
     * @return 更新タイムスタンプ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 更新タイムスタンプ設定処理
     * @param lastTmsp 更新タイムスタンプ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    
    /**
     * 更新プログラム取得処理
     * @return 更新プログラム
     */
    public String getLastPgm() {
        return lastPgm;
    }
    /**
     * 更新プログラム設定処理
     * @param lastPgm 更新プログラム
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
	public String getSaveDir() {
		return saveDir;
	}
	public void setSaveDir(String saveDir) {
		this.saveDir = saveDir;
	}
    
}
