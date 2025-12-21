package jp.co.isid.mos.bird.bizsupport.contractstatement.entity;

import java.math.BigDecimal;

public class UIKeisanshoList {

    public static final String TABLE = "BD01KEIR";

    public static final String companyCd_COLUMN = "COMPANY_CD";

    public static final String onerCd_COLUMN = "ONER_CD";

    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";

    public static final String urikakeCd_COLUMN = "URIKAKE_CD";

    public static final String urikakeName_COLUMN = "URIKAKE_NAME";

    public static final String keisanYm_COLUMN = "KEISAN_YM";

    public static final String keiCategory_COLUMN = "KEI_CATEGORY";

    public static final String keiCateName_COLUMN = "KEI_CATE_NAME";

    public static final String ctrlFlg1_COLUMN = "CTRL_FLG1";

    public static final String ctrlFlg2_COLUMN = "CTRL_FLG2";

    public static final String ctrlFlg3_COLUMN = "CTRL_FLG3";

    public static final String sortKey１_COLUMN = "SORT_KEY１";

    public static final String keiShu_COLUMN = "KEI_SHU";

    public static final String keiShuName_COLUMN = "KEI_SHU_NAME";

    public static final String saveDir_COLUMN = "SAVE_DIR";

    public static final String dispMonths_COLUMN = "DISP_MONTHS";

    public static final String sortKey2_COLUMN = "SORT_KEY2";

    public static final String hakkoDt_COLUMN = "HAKKO_DT";

    public static final String fileName_COLUMN = "FILE_NAME";

    /**
     * 会社コード
     */
    private String companyCd;

    /**
     * オーナーコード
     */
    private String onerCd;

    /**
     * オーナー名称
     */
    private String onerNameKj;

    /**
     * 売掛先コード
     */
    private String urikakeCd;

    /**
     * 売掛先名称
     */
    private String urikakeName;

    /**
     * 対象年月
     */
    private String keisanYm;

    /**
     * 計算書カテゴリ
     */
    private String keiCategory;

    /**
     * 計算書カテゴリ名称
     */
    private String keiCateName;

    /**
     * 制御フラグ１
     */
    private String ctrlFlg1;

    /**
     * 制御フラグ２
     */
    private String ctrlFlg2;

    /**
     * 制御フラグ３
     */
    private String ctrlFlg3;

    /**
     * 計算書カテゴリソートキー
     */
    private String sortKey１;

    /**
     * 計算書種別
     */
    private String keiShu;

    /**
     * 計算書種別名称
     */
    private String keiShuName;

    /**
     * 保存ディレクトリ
     */
    private String saveDir;

    /**
     * 表示月数
     */
    private BigDecimal dispMonths;

    /**
     * 計算書種別ソートキー
     */
    private String sortKey2;

    /**
     * 発行日
     */
    private String hakkoDt;

    /**
     * ファイル名
     */
    private String fileName;

    /**
     * スパンカウントオーナー
     */
    private int spanCntOner;

    /**
     * スパンカウント売掛先
     */
    private int spanCntUrikake;

    /**
     * 処理用
     */
    private int workCnt1;

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
     * オーナーコードを取得します。
     * @return オーナーコード
     */
    public String getOnerCd() {
        return onerCd;
    }
    /**
     * オーナーコードを設定します。
     * @param onerCd オーナーコード
     */
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }

    /**
     * オーナー名称を取得します。
     * @return オーナー名称
     */
    public String getOnerNameKj() {
        return onerNameKj;
    }
    /**
     * オーナー名称を設定します。
     * @param onerNameKj オーナー名称
     */
    public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj = onerNameKj;
    }

    /**
     * 売掛先コードを取得します。
     * @return 売掛先コード
     */
    public String getUrikakeCd() {
        return urikakeCd;
    }
    /**
     * 売掛先コードを設定します。
     * @param urikakeCd 売掛先コード
     */
    public void setUrikakeCd(String urikakeCd) {
        this.urikakeCd = urikakeCd;
    }

    /**
     * 売掛先名称を取得します。
     * @return 売掛先名称
     */
    public String getUrikakeName() {
        return urikakeName;
    }
    /**
     * 売掛先名称を設定します。
     * @param urikakeName 売掛先名称
     */
    public void setUrikakeName(String urikakeName) {
        this.urikakeName = urikakeName;
    }

    /**
     * 対象年月を取得します。
     * @return 対象年月
     */
    public String getKeisanYm() {
        return keisanYm;
    }
    /**
     * 対象年月を設定します。
     * @param keisanYm 対象年月
     */
    public void setKeisanYm(String keisanYm) {
        this.keisanYm = keisanYm;
    }

    /**
     * 計算書カテゴリを取得します。
     * @return 計算書カテゴリ
     */
    public String getKeiCategory() {
        return keiCategory;
    }
    /**
     * 計算書カテゴリを設定します。
     * @param keiCategory 計算書カテゴリ
     */
    public void setKeiCategory(String keiCategory) {
        this.keiCategory = keiCategory;
    }

    /**
     * 計算書カテゴリ名称を取得します。
     * @return 計算書カテゴリ名称
     */
    public String getKeiCateName() {
        return keiCateName;
    }
    /**
     * 計算書カテゴリ名称を設定します。
     * @param keiCateName 計算書カテゴリ名称
     */
    public void setKeiCateName(String keiCateName) {
        this.keiCateName = keiCateName;
    }

    /**
     * 制御フラグ１を取得します。
     * @return 制御フラグ１
     */
    public String getCtrlFlg1() {
        return ctrlFlg1;
    }
    /**
     * 制御フラグ１を設定します。
     * @param ctrlFlg1 制御フラグ１
     */
    public void setCtrlFlg1(String ctrlFlg1) {
        this.ctrlFlg1 = ctrlFlg1;
    }

    /**
     * 制御フラグ２を取得します。
     * @return 制御フラグ２
     */
    public String getCtrlFlg2() {
        return ctrlFlg2;
    }
    /**
     * 制御フラグ２を設定します。
     * @param ctrlFlg2 制御フラグ２
     */
    public void setCtrlFlg2(String ctrlFlg2) {
        this.ctrlFlg2 = ctrlFlg2;
    }

    /**
     * 制御フラグ３を取得します。
     * @return 制御フラグ３
     */
    public String getCtrlFlg3() {
        return ctrlFlg3;
    }
    /**
     * 制御フラグ３を設定します。
     * @param ctrlFlg3 制御フラグ３
     */
    public void setCtrlFlg3(String ctrlFlg3) {
        this.ctrlFlg3 = ctrlFlg3;
    }

    /**
     * 計算書カテゴリソートキーを取得します。
     * @return 計算書カテゴリソートキー
     */
    public String getSortKey１() {
        return sortKey１;
    }
    /**
     * 計算書カテゴリソートキーを設定します。
     * @param sortKey１ 計算書カテゴリソートキー
     */
    public void setSortKey１(String sortKey１) {
        this.sortKey１ = sortKey１;
    }

    /**
     * 計算書種別を取得します。
     * @return 計算書種別
     */
    public String getKeiShu() {
        return keiShu;
    }
    /**
     * 計算書種別を設定します。
     * @param keiShu 計算書種別
     */
    public void setKeiShu(String keiShu) {
        this.keiShu = keiShu;
    }

    /**
     * 計算書種別名称を取得します。
     * @return 計算書種別名称
     */
    public String getKeiShuName() {
    	return (keiShuName == null) ? "" : keiShuName.replaceAll("[　*| *]*$", "");
    }
    /**
     * 計算書種別名称を設定します。
     * @param keiShuName 計算書種別名称
     */
    public void setKeiShuName(String keiShuName) {
        this.keiShuName = keiShuName;
    }

    /**
     * 保存ディレクトリを取得します。
     * @return 保存ディレクトリ
     */
    public String getSaveDir() {
        return saveDir;
    }
    /**
     * 保存ディレクトリを設定します。
     * @param saveDir 保存ディレクトリ
     */
    public void setSaveDir(String saveDir) {
        this.saveDir = saveDir;
    }

    /**
     * 表示月数を取得します。
     * @return 表示月数
     */
    public BigDecimal getDispMonths() {
        return dispMonths;
    }
    /**
     * 表示月数を設定します。
     * @param dispMonths 表示月数
     */
    public void setDispMonths(BigDecimal dispMonths) {
        this.dispMonths = dispMonths;
    }

    /**
     * 計算書種別ソートキーを取得します。
     * @return 計算書種別ソートキー
     */
    public String getSortKey2() {
        return sortKey2;
    }
    /**
     * 計算書種別ソートキーを設定します。
     * @param sortKey2 計算書種別ソートキー
     */
    public void setSortKey2(String sortKey2) {
        this.sortKey2 = sortKey2;
    }

    /**
     * 発行日を取得します。
     * @return 発行日
     */
    public String getHakkoDt() {
        return hakkoDt;
    }
    /**
     * 発行日を設定します。
     * @param hakkoDt 発行日
     */
    public void setHakkoDt(String hakkoDt) {
        this.hakkoDt = hakkoDt;
    }

    /**
     * ファイル名を取得します。
     * @return ファイル名
     */
    public String getFileName() {
        return fileName;
    }
    /**
     * ファイル名を設定します。
     * @param fileName ファイル名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public int getSpanCntOner() {
        return spanCntOner;
    }
    public void setSpanCntOner(int spanCnt) {
        this.spanCntOner = spanCnt;
    }
    public int getWorkCnt1() {
        return workCnt1;
    }
    public void setWorkCnt1(int workCnt1) {
        this.workCnt1 = workCnt1;
    }
    public int getSpanCntUrikake() {
        return spanCntUrikake;
    }
    public void setSpanCntUrikake(int spanCntUrikake) {
        this.spanCntUrikake = spanCntUrikake;
    }

}
