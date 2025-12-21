/*
 * 作成日: 2006/3/31
 */
package jp.co.isid.mos.bird.bizsupport.plregist.dto;

import java.sql.Timestamp;

import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.PlDataErrorInfo;
import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLInfo;

/**
 * P/LデータDto String型版（画面用）
 * @author itamoto
 */
public class TrnPLInfoDto {
	
	/* 画面表示スタイルシート指定 */
	public static final String CLASSNAME_NORMAL = "normal_field";
    public static final String CLASSNAME_ERROR = "error_field";
    public static final String CLASSNAME_WARNING = "warning_field";
    
	/* エラー表記指定 */
    public static final String ERROR_NOTATION_NUM_REQUIRED = "未";
    public static final String ERROR_NOTATION_NUM_INVALID = "×";
    public static final String ERROR_NOTATION_NUM_LIMIT = "！";
    public static final String ERROR_NOTATION_NUM_SALES = "！";
    public static final String ERROR_NOTATION_INVALID = "×";
    public static final String ERROR_NOTATION_NUM_ADJUST = "×";
    public static final String ERROR_NOTATION_NUM_LOAN = "！";
    /* P/Lデータエンティティ */
    private TrnPLInfo trnPLInfo;
    
    /**
     * 初期入力状態フラグ
     * true:初期入力状態
     */
    private boolean defaultFlg = false;
    
    /**
     * 内訳売上高計コピー許可フラグ
     * true:コピーOK
     */
    private boolean uriUchiwakeCopeFlg = false;
    
    
    /**
     * 内訳売上原価計コピー許可フラグ
     * true:コピーOK
     */
    private boolean uriagegenkaUchiwakeCopeFlg = false;

    /**
     * 内訳水道光熱費計コピー許可フラグ
     * true:コピーOK
     */
    private boolean suikohiUchiwakeCopeFlg = false;
    
    /**
     * 内訳給料手当計コピー許可フラグ
     * true:コピーOK
     */
    private boolean salaryUchiwakeCopeFlg = false;
    
    
    /**
     * PLの種類
     */
    private String plType;
    
    /**
     * 年月
     */
    private String plYm;
    
    /**
     * 企業コード
     */
    private String companyCd;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 売上高
     */
    private String uriagedaka;
    
    /**
     * 売上原価
     */
    private String uriagegenka;
    
    /**
     * 売上総利益
     */
    private String uriageSoRieki;
    
    /**
     * 給料手当
     */
    private String salary;
    
    /**
     * 家賃地代
     */
    private String yachin;
    
    /**
     * 水道光熱費
     */
    private String suikouHi;
    
    /**
     * ロイヤルティ
     */
    private String royalty;
    
    /**
     * 支払手数料
     */
    private String tesuryo;
    
    /**
     * 広告費
     */
    private String koukoku;
    
    /**
     * 消耗品費
     */
    private String shoumou;
    
    /**
     * 法定福利費
     */
    private String houteiFukuri;
    
    /**
     * 福利厚生費
     */
    private String fukuriKousei;
    
    /**
     * 交際費
     */
    private String kousai;
    
    /**
     * 旅費交通費
     */
    private String ryohi;
    
    /**
     * 通信費
     */
    private String tusin;
    
    /**
     * 賃借リース料
     */
    private String lease;
    
    /**
     * 車両費
     */
    private String sharyo;
    
    /**
     * 租税公課
     */
    private String sozei;
    
    /**
     * 保険料
     */
    private String hoken;
    
    /**
     * 運賃
     */
    private String unchin;
    
    /**
     * 修繕費
     */
    private String shuzen;
    
    /**
     * 予備欄
     */
    private String yobi;
    
    /**
     * 雑費
     */
    private String zappi;
    
    /**
     * 経費小計
     */
    private String keihiShokei;
    
    /**
     * 償却前利益
     */
    private String shokyakuRieki;
    
    /**
     * 減価償却費
     */
    private String genkaShokyaku;
    
    /**
     * 営業外収益
     */
    private String eigaiShueki;
    
    /**
     * 営業外費用
     */
    private String eigaiHiyo;
    
    /**
     * 本社費配賦
     */
    private String honshahiHai;
    
    /**
     * 当月利益
     */
    private String rieki;
    
    /**
     * 売上
     */
    private String uriage;
    
    /**
     * 物販売上
     */
    private String buppan;
    
    /**
     * 売上高内訳(計)
     */
    private String uriUchiwake;
    
    /**
     * 電気代
     */
    private String elec;
    
    /**
     * ガス代
     */
    private String gas;
    
    /**
     * 水道代
     */
    private String water;
    
    /**
     * その他
     */
    private String other;
    
    /**
     * 水道光熱費の内訳(計)
     */
    private String suikouUchiwake;
    
    /**
     * 原材料(差引売上原価)
     */
    private String genzairyoKei;
    
    /**
     * 原材料(当月仕入)
     */
    private String genzairyoShire;
    
    /**
     * 原材料(当月在庫)
     */
    private String genzairyoZaiko;
    
    /**
     * 野菜(差引売上原価)
     */
    private String yasaiKei;
    
    /**
     * 野菜(当月仕入)
     */
    private String yasaiShire;
    
    /**
     * 野菜(当月在庫)
     */
    private String yasaiZaiko;
    
    /**
     * 包材(差引売上原価)
     */
    private String houzaiKei;
    
    /**
     * 包材(当月仕入)
     */
    private String houzaiShire;
    
    /**
     * 包材(当月在庫)
     */
    private String houzaiZaiko;
    
    /**
     * 物販(差引売上原価)
     */
    private String buppanKei;
    
    /**
     * 物販(当月仕入)
     */
    private String buppanShire;
    
    /**
     * 物販(当月在庫)
     */
    private String buppanZaiko;
    
    /**
     * 当月仕入(計)
     */
    private String touSiireKei;
    
    /**
     * 当月在庫(計)
     */
    private String touZaikoKei;
    
    /**
     * 差引売上原価(計)
     */
    private String sashihikiKei;
    
    /**
     * 役員報酬(給料)
     */
    private String yakuinSalary;
    
    /**
     * 役員報酬(賞与)
     */
    private String yakuinBonus;
    
    /**
     * 役員報酬(退職金)
     */
    private String yakuinRetire;
    
    /**
     * 役員報酬(計)
     */
    private String yakuinKei;
    
    /**
     * 給料手当(給料)
     */
    private String salarySalary;
    
    /**
     * 給料手当(賞与)
     */
    private String salaryBonus;
    
    /**
     * 給料手当(退職金)
     */
    private String salaryRetire;
    
    /**
     * 給料手当(計)
     */
    private String salaryKei;
    
    /**
     * 雑給(給料)
     */
    private String zakkyuSalary;
    
    /**
     * 雑給(賞与)
     */
    private String zakkyuBonus;
    
    /**
     * 雑給(退職金)
     */
    private String zakkyuRetire;
    
    /**
     * 雑給(計)
     */
    private String zakkyuKei;
    
    /**
     * 給料(計)
     */
    private String kyuryoKei;
    
    /**
     * 賞与(計)
     */
    private String bonusKei;
    
    /**
     * 退職金(計)
     */
    private String retireKei;
    
    /**
     * 給料手当内訳(計)
     */
    private String salaryUtiKei;
    
    /**
     * 借入金(当月増加)
     */
    private String kashiireUp;
    
    /**
     * 借入金(当月減少)
     */
    private String kashiireDown;
    
    /**
     * 借入金(当月残高)
     */
    private String kashiireZandaka;
    
    /**
     * 割賦未払金(当月増加)
     */
    private String kappuUp;
    
    /**
     * 割賦未払金(当月減少)
     */
    private String kappuDown;
    
    /**
     * 割賦未払金(当月残高)
     */
    private String kappuZandaka;
    
    /**
     * リース未払金(当月増加)
     */
    private String leaseUp;
    
    /**
     * リース未払金(当月減少)
     */
    private String leaseDown;
    
    /**
     * リース未払金(当月残高)
     */
    private String leaseZandaka;
    
    /**
     * 当月増加(計)
     */
    private String touZoukaKei;
    
    /**
     * 当月減少(計)
     */
    private String touGenshoKei;
    
    /**
     * 当月残高(計)
     */
    private String touZandakaKei;
    
    /**
     * メモ・通信
     */
    private String memo;
    
    /**
     * 作成者
     */
    private String author;
    
    /**
     * 作成年月日
     */
    private String authDt;
    
    /**
     * 作成者電話番号
     */
    private String authPhoneNum;
    
    /**
     * 作成者会計事務所等
     */
    private String authOther;
    
    /**
     * 更新日
     */
    private String lastDt;
    
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
     * 店名称
     */
    private String miseNameKj;
    
    /**
     * 初期入力状態フラグの設定
     * @return
     */
    public boolean isDefaultFlg() {
        return defaultFlg;
    }
    /**
     * 初期入力状態フラグの設定
     * @param defaultFlg
     */
    public void setDefaultFlg(boolean defaultFlg) {
        this.defaultFlg = defaultFlg;
    }

    /**
     * 内訳売上高コピー許可フラグ
     * @return
     */
    public boolean isUriUchiwakeCopeFlg() {
        return uriUchiwakeCopeFlg;
    }
    /**
     * 内訳売上高コピー許可フラグ
     * @param uriUchiwakeCopeFlg
     */
    public void setUriUchiwakeCopeFlg(boolean uriUchiwakeCopeFlg) {
        this.uriUchiwakeCopeFlg = uriUchiwakeCopeFlg;
    }
    
    /**
     * 内訳売上原価計コピー許可フラグ
     * @return
     */
    public boolean isUriagegenkaUchiwakeCopeFlg() {
        return uriagegenkaUchiwakeCopeFlg;
    }
    /**
     *内訳売上原価計コピー許可フラグ
     * @param uriagegenkaUchiwakeCopeFlg
     */
    public void setUriagegenkaUchiwakeCopeFlg(boolean uriagegenkaUchiwakeCopeFlg) {
        this.uriagegenkaUchiwakeCopeFlg = uriagegenkaUchiwakeCopeFlg;
    }
    
    /**
     * 内訳水道光熱費計コピー許可フラグ
     * @return
     */
    public boolean isSuikohiUchiwakeCopeFlg() {
        return suikohiUchiwakeCopeFlg;
    }
    /**
     * 内訳水道光熱費計コピー許可フラグ
     * @param suikohiUchiwakeCopeFlg
     */
    public void setSuikohiUchiwakeCopeFlg(boolean suikohiUchiwakeCopeFlg) {
        this.suikohiUchiwakeCopeFlg = suikohiUchiwakeCopeFlg;
    }
    
    /**
     * 内訳給料手当計コピー許可フラグ
     * @return
     */
    public boolean isSalaryUchiwakeCopeFlg() {
        return salaryUchiwakeCopeFlg;
    }
    /**
     * 内訳給料手当計コピー許可フラグ
     * @param salaryUchiwakeCopeFlg
     */
    public void setSalaryUchiwakeCopeFlg(boolean salaryUchiwakeCopeFlg) {
        this.salaryUchiwakeCopeFlg = salaryUchiwakeCopeFlg;
    }
    
    
	/**
	 * P/Lデータエンティティの設定
	 * @return trnPLInfo を戻します。
	 */
	public TrnPLInfo getTrnPLInfo() {
		return trnPLInfo;
	}
	/**
	 * P/Lデータエンティティの設定
	 * @param trnPLInfo trnPLInfo を設定。
	 */
	public void setTrnPLInfo(TrnPLInfo trnPLInfo) {
		this.trnPLInfo = trnPLInfo;
	}

    /**
     * PLの種類を取得します。
     * @return PLの種類
     */
    public String getPlType() {
        return plType;
    }
    /**
     * PLの種類を設定します。
     * @param plType PLの種類
     */
    public void setPlType(String plType) {
        this.plType = plType;
    }
    
    /**
     * 年月を取得します。
     * @return 年月
     */
    public String getPlYm() {
        return plYm;
    }
    /**
     * 年月を設定します。
     * @param plYm 年月
     */
    public void setPlYm(String plYm) {
        this.plYm = plYm;
    }
    
    /**
     * 企業コードを取得します。
     * @return 企業コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 企業コードを設定します。
     * @param companyCd 企業コード
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
     * 売上高を取得します。
     * @return 売上高
     */
    public String getUriagedaka() {
        return uriagedaka;
    }
    /**
     * 売上高を設定します。
     * @param uriagedaka 売上高
     */
    public void setUriagedaka(String uriagedaka) {
        this.uriagedaka = uriagedaka;
    }
    
    /**
     * 売上原価を取得します。
     * @return 売上原価
     */
    public String getUriagegenka() {
        return uriagegenka;
    }
    /**
     * 売上原価を設定します。
     * @param uriagegenka 売上原価
     */
    public void setUriagegenka(String uriagegenka) {
        this.uriagegenka = uriagegenka;
    }
    
    /**
     * 売上総利益を取得します。
     * @return 売上総利益
     */
    public String getUriageSoRieki() {
        return uriageSoRieki;
    }
    /**
     * 売上総利益を設定します。
     * @param uriageSoRieki 売上総利益
     */
    public void setUriageSoRieki(String uriageSoRieki) {
        this.uriageSoRieki = uriageSoRieki;
    }
    
    /**
     * 給料手当を取得します。
     * @return 給料手当
     */
    public String getSalary() {
        return salary;
    }
    /**
     * 給料手当を設定します。
     * @param salary 給料手当
     */
    public void setSalary(String salary) {
        this.salary = salary;
    }
    
    /**
     * 家賃地代を取得します。
     * @return 家賃地代
     */
    public String getYachin() {
        return yachin;
    }
    /**
     * 家賃地代を設定します。
     * @param yachin 家賃地代
     */
    public void setYachin(String yachin) {
        this.yachin = yachin;
    }
    
    /**
     * 水道光熱費を取得します。
     * @return 水道光熱費
     */
    public String getSuikouHi() {
        return suikouHi;
    }
    /**
     * 水道光熱費を設定します。
     * @param suikouHi 水道光熱費
     */
    public void setSuikouHi(String suikouHi) {
        this.suikouHi = suikouHi;
    }
    
    /**
     * ロイヤルティを取得します。
     * @return ロイヤルティ
     */
    public String getRoyalty() {
        return royalty;
    }
    /**
     * ロイヤルティを設定します。
     * @param royalty ロイヤルティ
     */
    public void setRoyalty(String royalty) {
        this.royalty = royalty;
    }
    
    /**
     * 支払手数料を取得します。
     * @return 支払手数料
     */
    public String getTesuryo() {
        return tesuryo;
    }
    /**
     * 支払手数料を設定します。
     * @param tesuryo 支払手数料
     */
    public void setTesuryo(String tesuryo) {
        this.tesuryo = tesuryo;
    }
    
    /**
     * 広告費を取得します。
     * @return 広告費
     */
    public String getKoukoku() {
        return koukoku;
    }
    /**
     * 広告費を設定します。
     * @param koukoku 広告費
     */
    public void setKoukoku(String koukoku) {
        this.koukoku = koukoku;
    }
    
    /**
     * 消耗品費を取得します。
     * @return 消耗品費
     */
    public String getShoumou() {
        return shoumou;
    }
    /**
     * 消耗品費を設定します。
     * @param shoumou 消耗品費
     */
    public void setShoumou(String shoumou) {
        this.shoumou = shoumou;
    }
    
    /**
     * 法定福利費を取得します。
     * @return 法定福利費
     */
    public String getHouteiFukuri() {
        return houteiFukuri;
    }
    /**
     * 法定福利費を設定します。
     * @param houteiFukuri 法定福利費
     */
    public void setHouteiFukuri(String houteiFukuri) {
        this.houteiFukuri = houteiFukuri;
    }
    
    /**
     * 福利厚生費を取得します。
     * @return 福利厚生費
     */
    public String getFukuriKousei() {
        return fukuriKousei;
    }
    /**
     * 福利厚生費を設定します。
     * @param fukuriKousei 福利厚生費
     */
    public void setFukuriKousei(String fukuriKousei) {
        this.fukuriKousei = fukuriKousei;
    }
    
    /**
     * 交際費を取得します。
     * @return 交際費
     */
    public String getKousai() {
        return kousai;
    }
    /**
     * 交際費を設定します。
     * @param kousai 交際費
     */
    public void setKousai(String kousai) {
        this.kousai = kousai;
    }
    
    /**
     * 旅費交通費を取得します。
     * @return 旅費交通費
     */
    public String getRyohi() {
        return ryohi;
    }
    /**
     * 旅費交通費を設定します。
     * @param ryohi 旅費交通費
     */
    public void setRyohi(String ryohi) {
        this.ryohi = ryohi;
    }
    
    /**
     * 通信費を取得します。
     * @return 通信費
     */
    public String getTusin() {
        return tusin;
    }
    /**
     * 通信費を設定します。
     * @param tusin 通信費
     */
    public void setTusin(String tusin) {
        this.tusin = tusin;
    }
    
    /**
     * 賃借リース料を取得します。
     * @return 賃借リース料
     */
    public String getLease() {
        return lease;
    }
    /**
     * 賃借リース料を設定します。
     * @param lease 賃借リース料
     */
    public void setLease(String lease) {
        this.lease = lease;
    }
    
    /**
     * 車両費を取得します。
     * @return 車両費
     */
    public String getSharyo() {
        return sharyo;
    }
    /**
     * 車両費を設定します。
     * @param sharyo 車両費
     */
    public void setSharyo(String sharyo) {
        this.sharyo = sharyo;
    }
    
    /**
     * 租税公課を取得します。
     * @return 租税公課
     */
    public String getSozei() {
        return sozei;
    }
    /**
     * 租税公課を設定します。
     * @param sozei 租税公課
     */
    public void setSozei(String sozei) {
        this.sozei = sozei;
    }
    
    /**
     * 保険料を取得します。
     * @return 保険料
     */
    public String getHoken() {
        return hoken;
    }
    /**
     * 保険料を設定します。
     * @param hoken 保険料
     */
    public void setHoken(String hoken) {
        this.hoken = hoken;
    }
    
    /**
     * 運賃を取得します。
     * @return 運賃
     */
    public String getUnchin() {
        return unchin;
    }
    /**
     * 運賃を設定します。
     * @param unchin 運賃
     */
    public void setUnchin(String unchin) {
        this.unchin = unchin;
    }
    
    /**
     * 修繕費を取得します。
     * @return 修繕費
     */
    public String getShuzen() {
        return shuzen;
    }
    /**
     * 修繕費を設定します。
     * @param shuzen 修繕費
     */
    public void setShuzen(String shuzen) {
        this.shuzen = shuzen;
    }
    
    /**
     * 予備欄を取得します。
     * @return 予備欄
     */
    public String getYobi() {
        return yobi;
    }
    /**
     * 予備欄を設定します。
     * @param yobi 予備欄
     */
    public void setYobi(String yobi) {
        this.yobi = yobi;
    }
    
    /**
     * 雑費を取得します。
     * @return 雑費
     */
    public String getZappi() {
        return zappi;
    }
    /**
     * 雑費を設定します。
     * @param zappi 雑費
     */
    public void setZappi(String zappi) {
        this.zappi = zappi;
    }
    
    /**
     * 経費小計を取得します。
     * @return 経費小計
     */
    public String getKeihiShokei() {
        return keihiShokei;
    }
    /**
     * 経費小計を設定します。
     * @param keihiShokei 経費小計
     */
    public void setKeihiShokei(String keihiShokei) {
        this.keihiShokei = keihiShokei;
    }
    
    /**
     * 償却前利益を取得します。
     * @return 償却前利益
     */
    public String getShokyakuRieki() {
        return shokyakuRieki;
    }
    /**
     * 償却前利益を設定します。
     * @param shokyakuRieki 償却前利益
     */
    public void setShokyakuRieki(String shokyakuRieki) {
        this.shokyakuRieki = shokyakuRieki;
    }
    
    /**
     * 減価償却費を取得します。
     * @return 減価償却費
     */
    public String getGenkaShokyaku() {
        return genkaShokyaku;
    }
    /**
     * 減価償却費を設定します。
     * @param genkaShokyaku 減価償却費
     */
    public void setGenkaShokyaku(String genkaShokyaku) {
        this.genkaShokyaku = genkaShokyaku;
    }
    
    /**
     * 営業外収益を取得します。
     * @return 営業外収益
     */
    public String getEigaiShueki() {
        return eigaiShueki;
    }
    /**
     * 営業外収益を設定します。
     * @param eigaiShueki 営業外収益
     */
    public void setEigaiShueki(String eigaiShueki) {
        this.eigaiShueki = eigaiShueki;
    }
    
    /**
     * 営業外費用を取得します。
     * @return 営業外費用
     */
    public String getEigaiHiyo() {
        return eigaiHiyo;
    }
    /**
     * 営業外費用を設定します。
     * @param eigaiHiyo 営業外費用
     */
    public void setEigaiHiyo(String eigaiHiyo) {
        this.eigaiHiyo = eigaiHiyo;
    }
    
    /**
     * 本社費配賦を取得します。
     * @return 本社費配賦
     */
    public String getHonshahiHai() {
        return honshahiHai;
    }
    /**
     * 本社費配賦を設定します。
     * @param honshahiHai 本社費配賦
     */
    public void setHonshahiHai(String honshahiHai) {
        this.honshahiHai = honshahiHai;
    }
    
    /**
     * 当月利益を取得します。
     * @return 当月利益
     */
    public String getRieki() {
        return rieki;
    }
    /**
     * 当月利益を設定します。
     * @param rieki 当月利益
     */
    public void setRieki(String rieki) {
        this.rieki = rieki;
    }
    
    /**
     * 売上を取得します。
     * @return 売上
     */
    public String getUriage() {
        return uriage;
    }
    /**
     * 売上を設定します。
     * @param uriage 売上
     */
    public void setUriage(String uriage) {
        this.uriage = uriage;
    }
    
    /**
     * 物販売上を取得します。
     * @return 物販売上
     */
    public String getBuppan() {
        return buppan;
    }
    /**
     * 物販売上を設定します。
     * @param buppan 物販売上
     */
    public void setBuppan(String buppan) {
        this.buppan = buppan;
    }
    
    /**
     * 売上高内訳(計)を取得します。
     * @return 売上高内訳(計)
     */
    public String getUriUchiwake() {
        return uriUchiwake;
    }
    /**
     * 売上高内訳(計)を設定します。
     * @param uriUchiwake 売上高内訳(計)
     */
    public void setUriUchiwake(String uriUchiwake) {
        this.uriUchiwake = uriUchiwake;
    }
    
    /**
     * 電気代を取得します。
     * @return 電気代
     */
    public String getElec() {
        return elec;
    }
    /**
     * 電気代を設定します。
     * @param elec 電気代
     */
    public void setElec(String elec) {
        this.elec = elec;
    }
    
    /**
     * ガス代を取得します。
     * @return ガス代
     */
    public String getGas() {
        return gas;
    }
    /**
     * ガス代を設定します。
     * @param gas ガス代
     */
    public void setGas(String gas) {
        this.gas = gas;
    }
    
    /**
     * 水道代を取得します。
     * @return 水道代
     */
    public String getWater() {
        return water;
    }
    /**
     * 水道代を設定します。
     * @param water 水道代
     */
    public void setWater(String water) {
        this.water = water;
    }
    
    /**
     * その他を取得します。
     * @return その他
     */
    public String getOther() {
        return other;
    }
    /**
     * その他を設定します。
     * @param other その他
     */
    public void setOther(String other) {
        this.other = other;
    }
    
    /**
     * 水道光熱費の内訳(計)を取得します。
     * @return 水道光熱費の内訳(計)
     */
    public String getSuikouUchiwake() {
        return suikouUchiwake;
    }
    /**
     * 水道光熱費の内訳(計)を設定します。
     * @param suikouUchiwake 水道光熱費の内訳(計)
     */
    public void setSuikouUchiwake(String suikouUchiwake) {
        this.suikouUchiwake = suikouUchiwake;
    }
    
    /**
     * 原材料(差引売上原価)を取得します。
     * @return 原材料(差引売上原価)
     */
    public String getGenzairyoKei() {
        return genzairyoKei;
    }
    /**
     * 原材料(差引売上原価)を設定します。
     * @param genzairyoKei 原材料(差引売上原価)
     */
    public void setGenzairyoKei(String genzairyoKei) {
        this.genzairyoKei = genzairyoKei;
    }
    
    /**
     * 原材料(当月仕入)を取得します。
     * @return 原材料(当月仕入)
     */
    public String getGenzairyoShire() {
        return genzairyoShire;
    }
    /**
     * 原材料(当月仕入)を設定します。
     * @param genzairyoShire 原材料(当月仕入)
     */
    public void setGenzairyoShire(String genzairyoShire) {
        this.genzairyoShire = genzairyoShire;
    }
    
    /**
     * 原材料(当月在庫)を取得します。
     * @return 原材料(当月在庫)
     */
    public String getGenzairyoZaiko() {
        return genzairyoZaiko;
    }
    /**
     * 原材料(当月在庫)を設定します。
     * @param genzairyoZaiko 原材料(当月在庫)
     */
    public void setGenzairyoZaiko(String genzairyoZaiko) {
        this.genzairyoZaiko = genzairyoZaiko;
    }
    
    /**
     * 野菜(差引売上原価)を取得します。
     * @return 野菜(差引売上原価)
     */
    public String getYasaiKei() {
        return yasaiKei;
    }
    /**
     * 野菜(差引売上原価)を設定します。
     * @param yasaiKei 野菜(差引売上原価)
     */
    public void setYasaiKei(String yasaiKei) {
        this.yasaiKei = yasaiKei;
    }
    
    /**
     * 野菜(当月仕入)を取得します。
     * @return 野菜(当月仕入)
     */
    public String getYasaiShire() {
        return yasaiShire;
    }
    /**
     * 野菜(当月仕入)を設定します。
     * @param yasaiShire 野菜(当月仕入)
     */
    public void setYasaiShire(String yasaiShire) {
        this.yasaiShire = yasaiShire;
    }
    
    /**
     * 野菜(当月在庫)を取得します。
     * @return 野菜(当月在庫)
     */
    public String getYasaiZaiko() {
        return yasaiZaiko;
    }
    /**
     * 野菜(当月在庫)を設定します。
     * @param yasaiZaiko 野菜(当月在庫)
     */
    public void setYasaiZaiko(String yasaiZaiko) {
        this.yasaiZaiko = yasaiZaiko;
    }
    
    /**
     * 包材(差引売上原価)を取得します。
     * @return 包材(差引売上原価)
     */
    public String getHouzaiKei() {
        return houzaiKei;
    }
    /**
     * 包材(差引売上原価)を設定します。
     * @param houzaiKei 包材(差引売上原価)
     */
    public void setHouzaiKei(String houzaiKei) {
        this.houzaiKei = houzaiKei;
    }
    
    /**
     * 包材(当月仕入)を取得します。
     * @return 包材(当月仕入)
     */
    public String getHouzaiShire() {
        return houzaiShire;
    }
    /**
     * 包材(当月仕入)を設定します。
     * @param houzaiShire 包材(当月仕入)
     */
    public void setHouzaiShire(String houzaiShire) {
        this.houzaiShire = houzaiShire;
    }
    
    /**
     * 包材(当月在庫)を取得します。
     * @return 包材(当月在庫)
     */
    public String getHouzaiZaiko() {
        return houzaiZaiko;
    }
    /**
     * 包材(当月在庫)を設定します。
     * @param houzaiZaiko 包材(当月在庫)
     */
    public void setHouzaiZaiko(String houzaiZaiko) {
        this.houzaiZaiko = houzaiZaiko;
    }
    
    /**
     * 物販(差引売上原価)を取得します。
     * @return 物販(差引売上原価)
     */
    public String getBuppanKei() {
        return buppanKei;
    }
    /**
     * 物販(差引売上原価)を設定します。
     * @param buppanKei 物販(差引売上原価)
     */
    public void setBuppanKei(String buppanKei) {
        this.buppanKei = buppanKei;
    }
    
    /**
     * 物販(当月仕入)を取得します。
     * @return 物販(当月仕入)
     */
    public String getBuppanShire() {
        return buppanShire;
    }
    /**
     * 物販(当月仕入)を設定します。
     * @param buppanShire 物販(当月仕入)
     */
    public void setBuppanShire(String buppanShire) {
        this.buppanShire = buppanShire;
    }
    
    /**
     * 物販(当月在庫)を取得します。
     * @return 物販(当月在庫)
     */
    public String getBuppanZaiko() {
        return buppanZaiko;
    }
    /**
     * 物販(当月在庫)を設定します。
     * @param buppanZaiko 物販(当月在庫)
     */
    public void setBuppanZaiko(String buppanZaiko) {
        this.buppanZaiko = buppanZaiko;
    }
    
    /**
     * 当月仕入(計)を取得します。
     * @return 当月仕入(計)
     */
    public String getTouSiireKei() {
        return touSiireKei;
    }
    /**
     * 当月仕入(計)を設定します。
     * @param touSiireKei 当月仕入(計)
     */
    public void setTouSiireKei(String touSiireKei) {
        this.touSiireKei = touSiireKei;
    }
    
    /**
     * 当月在庫(計)を取得します。
     * @return 当月在庫(計)
     */
    public String getTouZaikoKei() {
        return touZaikoKei;
    }
    /**
     * 当月在庫(計)を設定します。
     * @param touZaikoKei 当月在庫(計)
     */
    public void setTouZaikoKei(String touZaikoKei) {
        this.touZaikoKei = touZaikoKei;
    }
    
    /**
     * 差引売上原価(計)を取得します。
     * @return 差引売上原価(計)
     */
    public String getSashihikiKei() {
        return sashihikiKei;
    }
    /**
     * 差引売上原価(計)を設定します。
     * @param sashihikiKei 差引売上原価(計)
     */
    public void setSashihikiKei(String sashihikiKei) {
        this.sashihikiKei = sashihikiKei;
    }
    
    /**
     * 役員報酬(給料)を取得します。
     * @return 役員報酬(給料)
     */
    public String getYakuinSalary() {
        return yakuinSalary;
    }
    /**
     * 役員報酬(給料)を設定します。
     * @param yakuinSalary 役員報酬(給料)
     */
    public void setYakuinSalary(String yakuinSalary) {
        this.yakuinSalary = yakuinSalary;
    }
    
    /**
     * 役員報酬(賞与)を取得します。
     * @return 役員報酬(賞与)
     */
    public String getYakuinBonus() {
        return yakuinBonus;
    }
    /**
     * 役員報酬(賞与)を設定します。
     * @param yakuinBonus 役員報酬(賞与)
     */
    public void setYakuinBonus(String yakuinBonus) {
        this.yakuinBonus = yakuinBonus;
    }
    
    /**
     * 役員報酬(退職金)を取得します。
     * @return 役員報酬(退職金)
     */
    public String getYakuinRetire() {
        return yakuinRetire;
    }
    /**
     * 役員報酬(退職金)を設定します。
     * @param yakuinRetire 役員報酬(退職金)
     */
    public void setYakuinRetire(String yakuinRetire) {
        this.yakuinRetire = yakuinRetire;
    }
    
    /**
     * 役員報酬(計)を取得します。
     * @return 役員報酬(計)
     */
    public String getYakuinKei() {
        return yakuinKei;
    }
    /**
     * 役員報酬(計)を設定します。
     * @param yakuinKei 役員報酬(計)
     */
    public void setYakuinKei(String yakuinKei) {
        this.yakuinKei = yakuinKei;
    }
    
    /**
     * 給料手当(給料)を取得します。
     * @return 給料手当(給料)
     */
    public String getSalarySalary() {
        return salarySalary;
    }
    /**
     * 給料手当(給料)を設定します。
     * @param salarySalary 給料手当(給料)
     */
    public void setSalarySalary(String salarySalary) {
        this.salarySalary = salarySalary;
    }
    
    /**
     * 給料手当(賞与)を取得します。
     * @return 給料手当(賞与)
     */
    public String getSalaryBonus() {
        return salaryBonus;
    }
    /**
     * 給料手当(賞与)を設定します。
     * @param salaryBonus 給料手当(賞与)
     */
    public void setSalaryBonus(String salaryBonus) {
        this.salaryBonus = salaryBonus;
    }
    
    /**
     * 給料手当(退職金)を取得します。
     * @return 給料手当(退職金)
     */
    public String getSalaryRetire() {
        return salaryRetire;
    }
    /**
     * 給料手当(退職金)を設定します。
     * @param salaryRetire 給料手当(退職金)
     */
    public void setSalaryRetire(String salaryRetire) {
        this.salaryRetire = salaryRetire;
    }
    
    /**
     * 給料手当(計)を取得します。
     * @return 給料手当(計)
     */
    public String getSalaryKei() {
        return salaryKei;
    }
    /**
     * 給料手当(計)を設定します。
     * @param salaryKei 給料手当(計)
     */
    public void setSalaryKei(String salaryKei) {
        this.salaryKei = salaryKei;
    }
    
    /**
     * 雑給(給料)を取得します。
     * @return 雑給(給料)
     */
    public String getZakkyuSalary() {
        return zakkyuSalary;
    }
    /**
     * 雑給(給料)を設定します。
     * @param zakkyuSalary 雑給(給料)
     */
    public void setZakkyuSalary(String zakkyuSalary) {
        this.zakkyuSalary = zakkyuSalary;
    }
    
    /**
     * 雑給(賞与)を取得します。
     * @return 雑給(賞与)
     */
    public String getZakkyuBonus() {
        return zakkyuBonus;
    }
    /**
     * 雑給(賞与)を設定します。
     * @param zakkyuBonus 雑給(賞与)
     */
    public void setZakkyuBonus(String zakkyuBonus) {
        this.zakkyuBonus = zakkyuBonus;
    }
    
    /**
     * 雑給(退職金)を取得します。
     * @return 雑給(退職金)
     */
    public String getZakkyuRetire() {
        return zakkyuRetire;
    }
    /**
     * 雑給(退職金)を設定します。
     * @param zakkyuRetire 雑給(退職金)
     */
    public void setZakkyuRetire(String zakkyuRetire) {
        this.zakkyuRetire = zakkyuRetire;
    }
    
    /**
     * 雑給(計)を取得します。
     * @return 雑給(計)
     */
    public String getZakkyuKei() {
        return zakkyuKei;
    }
    /**
     * 雑給(計)を設定します。
     * @param zakkyuKei 雑給(計)
     */
    public void setZakkyuKei(String zakkyuKei) {
        this.zakkyuKei = zakkyuKei;
    }
    
    /**
     * 給料(計)を取得します。
     * @return 給料(計)
     */
    public String getKyuryoKei() {
        return kyuryoKei;
    }
    /**
     * 給料(計)を設定します。
     * @param kyuryoKei 給料(計)
     */
    public void setKyuryoKei(String kyuryoKei) {
        this.kyuryoKei = kyuryoKei;
    }
    
    /**
     * 賞与(計)を取得します。
     * @return 賞与(計)
     */
    public String getBonusKei() {
        return bonusKei;
    }
    /**
     * 賞与(計)を設定します。
     * @param bonusKei 賞与(計)
     */
    public void setBonusKei(String bonusKei) {
        this.bonusKei = bonusKei;
    }
    
    /**
     * 退職金(計)を取得します。
     * @return 退職金(計)
     */
    public String getRetireKei() {
        return retireKei;
    }
    /**
     * 退職金(計)を設定します。
     * @param retireKei 退職金(計)
     */
    public void setRetireKei(String retireKei) {
        this.retireKei = retireKei;
    }
    
    /**
     * 給料手当内訳(計)を取得します。
     * @return 給料手当内訳(計)
     */
    public String getSalaryUtiKei() {
        return salaryUtiKei;
    }
    /**
     * 給料手当内訳(計)を設定します。
     * @param salaryUtiKei 給料手当内訳(計)
     */
    public void setSalaryUtiKei(String salaryUtiKei) {
        this.salaryUtiKei = salaryUtiKei;
    }
    
    /**
     * 借入金(当月増加)を取得します。
     * @return 借入金(当月増加)
     */
    public String getKashiireUp() {
        return kashiireUp;
    }
    /**
     * 借入金(当月増加)を設定します。
     * @param kashiireUp 借入金(当月増加)
     */
    public void setKashiireUp(String kashiireUp) {
        this.kashiireUp = kashiireUp;
    }
    
    /**
     * 借入金(当月減少)を取得します。
     * @return 借入金(当月減少)
     */
    public String getKashiireDown() {
        return kashiireDown;
    }
    /**
     * 借入金(当月減少)を設定します。
     * @param kashiireDown 借入金(当月減少)
     */
    public void setKashiireDown(String kashiireDown) {
        this.kashiireDown = kashiireDown;
    }
    
    /**
     * 借入金(当月残高)を取得します。
     * @return 借入金(当月残高)
     */
    public String getKashiireZandaka() {
        return kashiireZandaka;
    }
    /**
     * 借入金(当月残高)を設定します。
     * @param kashiireZandaka 借入金(当月残高)
     */
    public void setKashiireZandaka(String kashiireZandaka) {
        this.kashiireZandaka = kashiireZandaka;
    }
    
    /**
     * 割賦未払金(当月増加)を取得します。
     * @return 割賦未払金(当月増加)
     */
    public String getKappuUp() {
        return kappuUp;
    }
    /**
     * 割賦未払金(当月増加)を設定します。
     * @param kappuUp 割賦未払金(当月増加)
     */
    public void setKappuUp(String kappuUp) {
        this.kappuUp = kappuUp;
    }
    
    /**
     * 割賦未払金(当月減少)を取得します。
     * @return 割賦未払金(当月減少)
     */
    public String getKappuDown() {
        return kappuDown;
    }
    /**
     * 割賦未払金(当月減少)を設定します。
     * @param kappuDown 割賦未払金(当月減少)
     */
    public void setKappuDown(String kappuDown) {
        this.kappuDown = kappuDown;
    }
    
    /**
     * 割賦未払金(当月残高)を取得します。
     * @return 割賦未払金(当月残高)
     */
    public String getKappuZandaka() {
        return kappuZandaka;
    }
    /**
     * 割賦未払金(当月残高)を設定します。
     * @param kappuZandaka 割賦未払金(当月残高)
     */
    public void setKappuZandaka(String kappuZandaka) {
        this.kappuZandaka = kappuZandaka;
    }
    
    /**
     * リース未払金(当月増加)を取得します。
     * @return リース未払金(当月増加)
     */
    public String getLeaseUp() {
        return leaseUp;
    }
    /**
     * リース未払金(当月増加)を設定します。
     * @param leaseUp リース未払金(当月増加)
     */
    public void setLeaseUp(String leaseUp) {
        this.leaseUp = leaseUp;
    }
    
    /**
     * リース未払金(当月減少)を取得します。
     * @return リース未払金(当月減少)
     */
    public String getLeaseDown() {
        return leaseDown;
    }
    /**
     * リース未払金(当月減少)を設定します。
     * @param leaseDown リース未払金(当月減少)
     */
    public void setLeaseDown(String leaseDown) {
        this.leaseDown = leaseDown;
    }
    
    /**
     * リース未払金(当月残高)を取得します。
     * @return リース未払金(当月残高)
     */
    public String getLeaseZandaka() {
        return leaseZandaka;
    }
    /**
     * リース未払金(当月残高)を設定します。
     * @param leaseZandaka リース未払金(当月残高)
     */
    public void setLeaseZandaka(String leaseZandaka) {
        this.leaseZandaka = leaseZandaka;
    }
    
    /**
     * 当月増加(計)を取得します。
     * @return 当月増加(計)
     */
    public String getTouZoukaKei() {
        return touZoukaKei;
    }
    /**
     * 当月増加(計)を設定します。
     * @param touZoukaKei 当月増加(計)
     */
    public void setTouZoukaKei(String touZoukaKei) {
        this.touZoukaKei = touZoukaKei;
    }
    
    /**
     * 当月減少(計)を取得します。
     * @return 当月減少(計)
     */
    public String getTouGenshoKei() {
        return touGenshoKei;
    }
    /**
     * 当月減少(計)を設定します。
     * @param touGenshoKei 当月減少(計)
     */
    public void setTouGenshoKei(String touGenshoKei) {
        this.touGenshoKei = touGenshoKei;
    }
    
    /**
     * 当月残高(計)を取得します。
     * @return 当月残高(計)
     */
    public String getTouZandakaKei() {
        return touZandakaKei;
    }
    /**
     * 当月残高(計)を設定します。
     * @param touZandakaKei 当月残高(計)
     */
    public void setTouZandakaKei(String touZandakaKei) {
        this.touZandakaKei = touZandakaKei;
    }
    
    /**
     * メモ・通信を取得します。
     * @return メモ・通信
     */
    public String getMemo() {
        if(!isNull(memo)) return memo.trim();
        else return null;
    }
    /**
     * メモ・通信を設定します。
     * @param memo メモ・通信
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }
    
    /**
     * 作成者を取得します。
     * @return 作成者
     */
    public String getAuthor() {
        return author;
    }
    /**
     * 作成者を設定します。
     * @param author 作成者
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    
    /**
     * 作成年月日を取得します。
     * @return 作成年月日
     */
    public String getAuthDt() {
        return authDt;
    }
    /**
     * 作成年月日を設定します。
     * @param authDt 作成年月日
     */
    public void setAuthDt(String authDt) {
        this.authDt = authDt;
    }
    
    /**
     * 作成者電話番号を取得します。
     * @return 作成者電話番号
     */
    public String getAuthPhoneNum() {
        return authPhoneNum;
    }
    /**
     * 作成者電話番号を設定します。
     * @param authPhoneNum 作成者電話番号
     */
    public void setAuthPhoneNum(String authPhoneNum) {
        this.authPhoneNum = authPhoneNum;
    }
    
    /**
     * 作成者会計事務所等を取得します。
     * @return 作成者会計事務所等
     */
    public String getAuthOther() {
        return authOther;
    }
    /**
     * 作成者会計事務所等を設定します。
     * @param authOther 作成者会計事務所等
     */
    public void setAuthOther(String authOther) {
        this.authOther = authOther;
    }
    
    /**
     * 更新日を取得します。
     * @return 更新日
     */
    public String getLastDt() {
        return lastDt;
    }
    /**
     * 更新日を設定します。
     * @param lastDt 更新日
     */
    public void setLastDt(String lastDt) {
        this.lastDt = lastDt;
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
     * 店名称を取得します。
     * @return 店名称
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 店名称を設定します。
     * @param miseNameKj 店名称
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }

//    /**
//     * エラー情報を取得します。
//     * @return エラー情報
//     */
//    public PlDataErrorInfo getPlDataErrorInfo() {
//        return plDataErrorInfo;
//    }
//    /**
//     * エラー情報を設定します。
//     * @param plDataErrorInfo エラー情報
//     */
//    public void setPlDataErrorInfo(PlDataErrorInfo plDataErrorInfo) {
//        this.plDataErrorInfo = plDataErrorInfo;
//    }

    /**
     * エラー表記生成
     * @param colName
     * @return エラー表記名
     */ 
    private String createErrorNotation(String colName) {
        String result = null;
        PlDataErrorInfo errInfo = trnPLInfo.getPlDataErrorInfo();
        // 必須エラー
        if (errInfo.isErrorItem(colName, PlDataErrorInfo.ERRORCODE_NUM_REQUIRED)) {
            result = ERROR_NOTATION_NUM_REQUIRED;
        }
        // 妥当性エラー
        else if (errInfo.isErrorItem(colName, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) {
            result = ERROR_NOTATION_NUM_INVALID;
        }
        // 計算チェックエラー
        else if (errInfo.isErrorItem(colName, PlDataErrorInfo.ERRORCODE_NUM_ADJUST)) {
            result = ERROR_NOTATION_NUM_ADJUST;
        }
        // 上下限エラー
        else if (errInfo.isErrorItem(colName, PlDataErrorInfo.ERRORCODE_NUM_LIMIT)) {
            result = ERROR_NOTATION_NUM_LIMIT;
        }
        // POS売上チェック
        else if (errInfo.isErrorItem(colName, PlDataErrorInfo.ERRORCODE_NUM_SALES)) {
            result = ERROR_NOTATION_NUM_SALES;
        }
        // MEMO・通信欄妥当性エラー
        else if (errInfo.isErrorItem(colName, PlDataErrorInfo.ERRORCODE_INVALID)) {
            result = ERROR_NOTATION_INVALID;
        }
        // 借入金ワーニング
        else if (errInfo.isErrorItem(colName, PlDataErrorInfo.ERRORCODE_NUM_ADJUST_LOAN)) {
            result = ERROR_NOTATION_NUM_LOAN;
        }
        else {
            result = "";
        }
        return result;
    }

    // 画面エラー表記プロパティ ///////////////////////////////////////////////
    /**
     * 売上高エラー表記取得
     * @return 売上高エラー表記
     */
    public String getUriagedakaErrorNotation() {
        return createErrorNotation(TrnPLInfo.uriagedaka_COLUMN);
    }

    /**
     * 売上原価エラー表記取得
     * @return 売上原価エラー表記
     */
    public String getUriagegenkaErrorNotation() {
        return createErrorNotation(TrnPLInfo.uriagegenka_COLUMN);
    }

    /**
     * 売上総利益エラー表記取得
     * @return 売上総利益エラー表記
     */
    public String getUriageSoRiekiErrorNotation() {
        return createErrorNotation(TrnPLInfo.uriageSoRieki_COLUMN);
    }

    /**
     * 給料手当エラー表記取得
     */
    public String getSalaryErrorNotation() {
        return createErrorNotation(TrnPLInfo.salary_COLUMN);
    }
    
    /**
     * 家賃地代エラー表記取得
     */
    public String getYachinErrorNotation() {
        return createErrorNotation(TrnPLInfo.yachin_COLUMN);
    }
    
    /**
     * 水道光熱費エラー表記取得
     */
    public String getSuikouHiErrorNotation() {
        return createErrorNotation(TrnPLInfo.suikouHi_COLUMN);
    }
    
    /**
     * ロイヤルティエラー表記取得
     */
    public String getRoyaltyErrorNotation() {
        return createErrorNotation(TrnPLInfo.royalty_COLUMN);
    }
    
    /**
     * 支払手数料エラー表記取得
     */
    public String getTesuryoErrorNotation() {
        return createErrorNotation(TrnPLInfo.tesuryo_COLUMN);
    }
    
    /**
     * 広告費エラー表記取得
     */
    public String getKoukokuErrorNotation() {
        return createErrorNotation(TrnPLInfo.koukoku_COLUMN);
    }
    
    /**
     * 消耗品費エラー表記取得
     */
    public String getShoumouErrorNotation() {
        return createErrorNotation(TrnPLInfo.shoumou_COLUMN);
    }
    
    /**
     * 法定福利費エラー表記取得
     */
    public String getHouteiFukuriErrorNotation() {
        return createErrorNotation(TrnPLInfo.houteiFukuri_COLUMN);
    }
    
    /**
     * 福利厚生費エラー表記取得
     */
    public String getFukuriKouseiErrorNotation() {
        return createErrorNotation(TrnPLInfo.fukuriKousei_COLUMN);
    }
    
    /**
     * 交際費エラー表記取得
     */
    public String getKousaiErrorNotation() {
        return createErrorNotation(TrnPLInfo.kousai_COLUMN);
    }
    
    /**
     * 旅費交通費エラー表記取得
     */
    public String getRyohiErrorNotation() {
        return createErrorNotation(TrnPLInfo.ryohi_COLUMN);
    }
    
    /**
     * 通信費エラー表記取得
     */
    public String getTusinErrorNotation() {
        return createErrorNotation(TrnPLInfo.tusin_COLUMN);
    }
    
    /**
     * 賃借リース料エラー表記取得
     */
    public String getLeaseErrorNotation() {
        return createErrorNotation(TrnPLInfo.lease_COLUMN);
    }
    
    /**
     * 車両費エラー表記取得
     */
    public String getSharyoErrorNotation() {
        return createErrorNotation(TrnPLInfo.sharyo_COLUMN);
    }
    
    /**
     * 租税公課エラー表記取得
     */
    public String getSozeiErrorNotation() {
        return createErrorNotation(TrnPLInfo.sozei_COLUMN);
    }
    
    /**
     * 保険料エラー表記取得
     */
    public String getHokenErrorNotation() {
        return createErrorNotation(TrnPLInfo.hoken_COLUMN);
    }
    
    /**
     * 運賃エラー表記取得
     */
    public String getUnchinErrorNotation() {
        return createErrorNotation(TrnPLInfo.unchin_COLUMN);
    }
    
    /**
     * 修繕費エラー表記取得
     */
    public String getShuzenErrorNotation() {
        return createErrorNotation(TrnPLInfo.shuzen_COLUMN);
    }
    
    /**
     * 予備欄エラー表記取得
     */
    public String getYobiErrorNotation() {
        return createErrorNotation(TrnPLInfo.yobi_COLUMN);
    }
    
    /**
     * 雑費エラー表記取得
     */
    public String getZappiErrorNotation() {
        return createErrorNotation(TrnPLInfo.zappi_COLUMN);
    }
    
    /**
     * 経費小計エラー表記取得
     */
    public String getKeihiShokeiErrorNotation() {
        return createErrorNotation(TrnPLInfo.keihiShokei_COLUMN);
    }
    
    /**
     * 償却前利益エラー表記取得
     */
    public String getShokyakuRiekiErrorNotation() {
        return createErrorNotation(TrnPLInfo.shokyakuRieki_COLUMN);
    }
    
    /**
     * 減価償却費エラー表記取得
     */
    public String getGenkaShokyakuErrorNotation() {
        return createErrorNotation(TrnPLInfo.genkaShokyaku_COLUMN);
    }
    
    /**
     * 営業外収益エラー表記取得
     */
    public String getEigaiShuekiErrorNotation() {
        return createErrorNotation(TrnPLInfo.eigaiShueki_COLUMN);
    }
    
    /**
     * 営業外費用エラー表記取得
     */
    public String getEigaiHiyoErrorNotation() {
        return createErrorNotation(TrnPLInfo.eigaiHiyo_COLUMN);
    }
    
    /**
     * 本社費配賦エラー表記取得
     */
    public String getHonshahiHaiErrorNotation() {
        return createErrorNotation(TrnPLInfo.honshahiHai_COLUMN);
    }
    
    /**
     * 当月利益エラー表記取得
     */
    public String getRiekiErrorNotation() {
        return createErrorNotation(TrnPLInfo.rieki_COLUMN);
    }
    
    /**
     * 売上エラー表記取得
     */
    public String getUriageErrorNotation() {
        return createErrorNotation(TrnPLInfo.uriage_COLUMN);
    }
    
    /**
     * 物販売上エラー表記取得
     */
    public String getBuppanErrorNotation() {
        return createErrorNotation(TrnPLInfo.buppan_COLUMN);
    }
    
    /**
     * 売上高内訳(計)エラー表記取得
     */
    public String getUriUchiwakeErrorNotation() {
        return createErrorNotation(TrnPLInfo.uriUchiwake_COLUMN);
    }
    
    /**
     * 電気代エラー表記取得
     */
    public String getElecErrorNotation() {
        return createErrorNotation(TrnPLInfo.elec_COLUMN);
    }
    
    /**
     * ガス代エラー表記取得
     */
    public String getGasErrorNotation() {
        return createErrorNotation(TrnPLInfo.gas_COLUMN);
    }
    
    /**
     * 水道代エラー表記取得
     */
    public String getWaterErrorNotation() {
        return createErrorNotation(TrnPLInfo.water_COLUMN);
    }
    
    /**
     * その他エラー表記取得
     */
    public String getOtherErrorNotation() {
        return createErrorNotation(TrnPLInfo.sonota_COLUMN);
    }
    
    /**
     * 水道光熱費の内訳(計)エラー表記取得
     */
    public String getSuikouUchiwakeErrorNotation() {
        return createErrorNotation(TrnPLInfo.suikouUchiwake_COLUMN);
    }
    
    /**
     * 原材料(差引売上原価)エラー表記取得
     */
    public String getGenzairyoKeiErrorNotation() {
        return createErrorNotation(TrnPLInfo.genzairyoKei_COLUMN);
    }
    
    /**
     * 原材料(当月仕入)エラー表記取得
     */
    public String getGenzairyoShireErrorNotation() {
        return createErrorNotation(TrnPLInfo.genzairyoShire_COLUMN);
    }
    
    /**
     * 原材料(当月在庫)エラー表記取得
     */
    public String getGenzairyoZaikoErrorNotation() {
        return createErrorNotation(TrnPLInfo.genzairyoZaiko_COLUMN);
    }
    
    /**
     * 野菜(差引売上原価)エラー表記取得
     */
    public String getYasaiKeiErrorNotation() {
        return createErrorNotation(TrnPLInfo.yasaiKei_COLUMN);
    }
    
    /**
     * 野菜(当月仕入)エラー表記取得
     */
    public String getYasaiShireErrorNotation() {
        return createErrorNotation(TrnPLInfo.yasaiShire_COLUMN);
    }
    
    /**
     * 野菜(当月在庫)エラー表記取得
     */
    public String getYasaiZaikoErrorNotation() {
        return createErrorNotation(TrnPLInfo.yasaiZaiko_COLUMN);
    }
    
    /**
     * 包材(差引売上原価)エラー表記取得
     */
    public String getHouzaiKeiErrorNotation() {
        return createErrorNotation(TrnPLInfo.houzaiKei_COLUMN);
    }
    
    /**
     * 包材(当月仕入)エラー表記取得
     */
    public String getHouzaiShireErrorNotation() {
        return createErrorNotation(TrnPLInfo.houzaiShire_COLUMN);
    }
    
    /**
     * 包材(当月在庫)エラー表記取得
     */
    public String getHouzaiZaikoErrorNotation() {
        return createErrorNotation(TrnPLInfo.houzaiZaiko_COLUMN);
    }
    
    /**
     * 物販(差引売上原価)エラー表記取得
     */
    public String getBuppanKeiErrorNotation() {
        return createErrorNotation(TrnPLInfo.buppanKei_COLUMN);
    }
    
    /**
     * 物販(当月仕入)エラー表記取得
     */
    public String getBuppanShireErrorNotation() {
        return createErrorNotation(TrnPLInfo.buppanShire_COLUMN);
    }
    
    /**
     * 物販(当月在庫)エラー表記取得
     */
    public String getBuppanZaikoErrorNotation() {
        return createErrorNotation(TrnPLInfo.buppanZaiko_COLUMN);
    }
    
    /**
     * 当月仕入(計)エラー表記取得
     */
    public String getTouSiireKeiErrorNotation() {
        return createErrorNotation(TrnPLInfo.touSiireKei_COLUMN);
    }
    
    /**
     * 当月在庫(計)エラー表記取得
     */
    public String getTouZaikoKeiErrorNotation() {
        return createErrorNotation(TrnPLInfo.touZaikoKei_COLUMN);
    }
    
    /**
     * 差引売上原価(計)エラー表記取得
     */
    public String getSashihikiKeiErrorNotation() {
        return createErrorNotation(TrnPLInfo.sashihikiKei_COLUMN);
    }
    
    /**
     * 役員報酬(給料)エラー表記取得
     */
    public String getYakuinSalaryErrorNotation() {
        return createErrorNotation(TrnPLInfo.yakuinSalary_COLUMN);
    }
    
    /**
     * 役員報酬(賞与)エラー表記取得
     */
    public String getYakuinBonusErrorNotation() {
        return createErrorNotation(TrnPLInfo.yakuinBonus_COLUMN);
    }
    
    /**
     * 役員報酬(退職金)エラー表記取得
     */
    public String getYakuinRetireErrorNotation() {
        return createErrorNotation(TrnPLInfo.yakuinRetire_COLUMN);
    }
    
    /**
     * 役員報酬(計)エラー表記取得
     */
    public String getYakuinKeiErrorNotation() {
        return createErrorNotation(TrnPLInfo.yakuinKei_COLUMN);
    }
    
    /**
     * 給料手当(給料)エラー表記取得
     */
    public String getSalarySalaryErrorNotation() {
        return createErrorNotation(TrnPLInfo.salarySalary_COLUMN);
    }
    
    /**
     * 給料手当(賞与)エラー表記取得
     */
    public String getSalaryBonusErrorNotation() {
        return createErrorNotation(TrnPLInfo.salaryBonus_COLUMN);
    }
    
    /**
     * 給料手当(退職金)エラー表記取得
     */
    public String getSalaryRetireErrorNotation() {
        return createErrorNotation(TrnPLInfo.salaryRetire_COLUMN);
    }
    
    /**
     * 給料手当(計)エラー表記取得
     */
    public String getSalaryKeiErrorNotation() {
        return createErrorNotation(TrnPLInfo.salaryKei_COLUMN);
    }
    
    /**
     * 雑給(給料)エラー表記取得
     */
    public String getZakkyuSalaryErrorNotation() {
        return createErrorNotation(TrnPLInfo.zakkyuSalary_COLUMN);
    }
    
    /**
     * 雑給(賞与)エラー表記取得
     */
    public String getZakkyuBonusErrorNotation() {
        return createErrorNotation(TrnPLInfo.zakkyuBonus_COLUMN);
    }
    
    /**
     * 雑給(退職金)エラー表記取得
     */
    public String getZakkyuRetireErrorNotation() {
        return createErrorNotation(TrnPLInfo.zakkyuRetire_COLUMN);
    }
    
    /**
     * 雑給(計)エラー表記取得
     */
    public String getZakkyuKeiErrorNotation() {
        return createErrorNotation(TrnPLInfo.zakkyuKei_COLUMN);
    }
    
    /**
     * 給料(計)エラー表記取得
     */
    public String getKyuryoKeiErrorNotation() {
        return createErrorNotation(TrnPLInfo.kyuryoKei_COLUMN);
    }
    
    /**
     * 賞与(計)エラー表記取得
     */
    public String getBonusKeiErrorNotation() {
        return createErrorNotation(TrnPLInfo.bonusKei_COLUMN);
    }
    
    /**
     * 退職金(計)エラー表記取得
     */
    public String getRetireKeiErrorNotation() {
        return createErrorNotation(TrnPLInfo.retireKei_COLUMN);
    }
    
    /**
     * 給料手当内訳(計)エラー表記取得
     */
    public String getSalaryUtiKeiErrorNotation() {
        return createErrorNotation(TrnPLInfo.salaryUtiKei_COLUMN);
    }
    
    /**
     * 借入金(当月増加)エラー表記取得
     */
    public String getKashiireUpErrorNotation() {
        return createErrorNotation(TrnPLInfo.kashiireUp_COLUMN);
    }
    
    /**
     * 借入金(当月減少)エラー表記取得
     */
    public String getKashiireDownErrorNotation() {
        return createErrorNotation(TrnPLInfo.kashiireDown_COLUMN);
    }
    
    /**
     * 借入金(当月残高)エラー表記取得
     */
    public String getKashiireZandakaErrorNotation() {
        return createErrorNotation(TrnPLInfo.kashiireZandaka_COLUMN);
    }
    
    /**
     * 割賦未払金(当月増加)エラー表記取得
     */
    public String getKappuUpErrorNotation() {
        return createErrorNotation(TrnPLInfo.kappuUp_COLUMN);
    }
    
    /**
     * 割賦未払金(当月減少)エラー表記取得
     */
    public String getKappuDownErrorNotation() {
        return createErrorNotation(TrnPLInfo.kappuDown_COLUMN);
    }
    
    /**
     * 割賦未払金(当月残高)エラー表記取得
     */
    public String getKappuZandakaErrorNotation() {
        return createErrorNotation(TrnPLInfo.kappuZandaka_COLUMN);
    }
    
    /**
     * リース未払金(当月増加)エラー表記取得
     */
    public String getLeaseUpErrorNotation() {
        return createErrorNotation(TrnPLInfo.leaseUp_COLUMN);
    }
    
    /**
     * リース未払金(当月減少)エラー表記取得
     */
    public String getLeaseDownErrorNotation() {
        return createErrorNotation(TrnPLInfo.leaseDown_COLUMN);
    }
    
    /**
     * リース未払金(当月残高)エラー表記取得
     */
    public String getLeaseZandakaErrorNotation() {
        return createErrorNotation(TrnPLInfo.leaseZandaka_COLUMN);
    }
    
    /**
     * 当月増加(計)エラー表記取得
     */
    public String getTouZoukaKeiErrorNotation() {
        return createErrorNotation(TrnPLInfo.touZoukaKei_COLUMN);
    }
    
    /**
     * 当月減少(計)エラー表記取得
     */
    public String getTouGenshoKeiErrorNotation() {
        return createErrorNotation(TrnPLInfo.touGenshoKei_COLUMN);
    }
    
    /**
     * 当月残高(計)エラー表記取得
     */
    public String getTouZandakaKeiErrorNotation() {
        return createErrorNotation(TrnPLInfo.touZandakaKei_COLUMN);
    }
    
    /**
     * メモ・通信エラー表記取得
     */
    public String getMemoErrorNotation() {
        return createErrorNotation(TrnPLInfo.memo_COLUMN);
    }
    
    /**
     * class名生成
     * @param itemCode 項目名
     * @return class名
     */
    private String createClassName(String itemCode) {
        String className = null;
        if (trnPLInfo.getPlDataErrorInfo().isErrorItem(itemCode)) {
            className = CLASSNAME_ERROR;
        } else if (trnPLInfo.getPlDataErrorInfo().isWarningItem(itemCode)) {
            className = CLASSNAME_WARNING;
        } else {
            className = CLASSNAME_NORMAL;
        }
        return className;
    }

    // 画面表示スタイルプロパティ ///////////////////////////////////////////////
    /**
     * 売上高class名取得
     * @return 売上高class名
     */
    public String getUriagedakaClass() {
        return createClassName(TrnPLInfo.uriagedaka_COLUMN);
    }

    /**
     * 売上原価class名取得
     * @return 売上原価class名
     */
    public String getUriagegenkaClass() {
        return createClassName(TrnPLInfo.uriagegenka_COLUMN);
    }

    /**
     * 売上総利益class名取得
     * @return 売上総利益class名
     */
    public String getUriageSoRiekiClass() {
        return createClassName(TrnPLInfo.uriageSoRieki_COLUMN);
    }

    /**
     * 給料手当class名取得
     */
    public String getSalaryClass() {
        return createClassName(TrnPLInfo.salary_COLUMN);
    }
    
    /**
     * 家賃地代class名取得
     */
    public String getYachinClass() {
        return createClassName(TrnPLInfo.yachin_COLUMN);
    }
    
    /**
     * 水道光熱費class名取得
     */
    public String getSuikouHiClass() {
        return createClassName(TrnPLInfo.suikouHi_COLUMN);
    }
    
    /**
     * ロイヤルティclass名取得
     */
    public String getRoyaltyClass() {
        return createClassName(TrnPLInfo.royalty_COLUMN);
    }
    
    /**
     * 支払手数料class名取得
     */
    public String getTesuryoClass() {
        return createClassName(TrnPLInfo.tesuryo_COLUMN);
    }
    
    /**
     * 広告費class名取得
     */
    public String getKoukokuClass() {
        return createClassName(TrnPLInfo.koukoku_COLUMN);
    }
    
    /**
     * 消耗品費class名取得
     */
    public String getShoumouClass() {
        return createClassName(TrnPLInfo.shoumou_COLUMN);
    }
    
    /**
     * 法定福利費class名取得
     */
    public String getHouteiFukuriClass() {
        return createClassName(TrnPLInfo.houteiFukuri_COLUMN);
    }
    
    /**
     * 福利厚生費class名取得
     */
    public String getFukuriKouseiClass() {
        return createClassName(TrnPLInfo.fukuriKousei_COLUMN);
    }
    
    /**
     * 交際費class名取得
     */
    public String getKousaiClass() {
        return createClassName(TrnPLInfo.kousai_COLUMN);
    }
    
    /**
     * 旅費交通費class名取得
     */
    public String getRyohiClass() {
        return createClassName(TrnPLInfo.ryohi_COLUMN);
    }
    
    /**
     * 通信費class名取得
     */
    public String getTusinClass() {
        return createClassName(TrnPLInfo.tusin_COLUMN);
    }
    
    /**
     * 賃借リース料class名取得
     */
    public String getLeaseClass() {
        return createClassName(TrnPLInfo.lease_COLUMN);
    }
    
    /**
     * 車両費class名取得
     */
    public String getSharyoClass() {
        return createClassName(TrnPLInfo.sharyo_COLUMN);
    }
    
    /**
     * 租税公課class名取得
     */
    public String getSozeiClass() {
        return createClassName(TrnPLInfo.sozei_COLUMN);
    }
    
    /**
     * 保険料class名取得
     */
    public String getHokenClass() {
        return createClassName(TrnPLInfo.hoken_COLUMN);
    }
    
    /**
     * 運賃class名取得
     */
    public String getUnchinClass() {
        return createClassName(TrnPLInfo.unchin_COLUMN);
    }
    
    /**
     * 修繕費class名取得
     */
    public String getShuzenClass() {
        return createClassName(TrnPLInfo.shuzen_COLUMN);
    }
    
    /**
     * 予備欄class名取得
     */
    public String getYobiClass() {
        return createClassName(TrnPLInfo.yobi_COLUMN);
    }
    
    /**
     * 雑費class名取得
     */
    public String getZappiClass() {
        return createClassName(TrnPLInfo.zappi_COLUMN);
    }
    
    /**
     * 経費小計class名取得
     */
    public String getKeihiShokeiClass() {
        return createClassName(TrnPLInfo.keihiShokei_COLUMN);
    }
    
    /**
     * 償却前利益class名取得
     */
    public String getShokyakuRiekiClass() {
        return createClassName(TrnPLInfo.shokyakuRieki_COLUMN);
    }
    
    /**
     * 減価償却費class名取得
     */
    public String getGenkaShokyakuClass() {
        return createClassName(TrnPLInfo.genkaShokyaku_COLUMN);
    }
    
    /**
     * 営業外収益class名取得
     */
    public String getEigaiShuekiClass() {
        return createClassName(TrnPLInfo.eigaiShueki_COLUMN);
    }
    
    /**
     * 営業外費用class名取得
     */
    public String getEigaiHiyoClass() {
        return createClassName(TrnPLInfo.eigaiHiyo_COLUMN);
    }
    
    /**
     * 本社費配賦class名取得
     */
    public String getHonshahiHaiClass() {
        return createClassName(TrnPLInfo.honshahiHai_COLUMN);
    }
    
    /**
     * 当月利益class名取得
     */
    public String getRiekiClass() {
        return createClassName(TrnPLInfo.rieki_COLUMN);
    }
    
    /**
     * 売上class名取得
     */
    public String getUriageClass() {
        return createClassName(TrnPLInfo.uriage_COLUMN);
    }
    
    /**
     * 物販売上class名取得
     */
    public String getBuppanClass() {
        return createClassName(TrnPLInfo.buppan_COLUMN);
    }
    
    /**
     * 売上高内訳(計)class名取得
     */
    public String getUriUchiwakeClass() {
        return createClassName(TrnPLInfo.uriUchiwake_COLUMN);
    }
    
    /**
     * 電気代class名取得
     */
    public String getElecClass() {
        return createClassName(TrnPLInfo.elec_COLUMN);
    }
    
    /**
     * ガス代class名取得
     */
    public String getGasClass() {
        return createClassName(TrnPLInfo.gas_COLUMN);
    }
    
    /**
     * 水道代class名取得
     */
    public String getWaterClass() {
        return createClassName(TrnPLInfo.water_COLUMN);
    }
    
    /**
     * その他class名取得
     */
    public String getOtherClass() {
        return createClassName(TrnPLInfo.sonota_COLUMN);
    }
    
    /**
     * 水道光熱費の内訳(計)class名取得
     */
    public String getSuikouUchiwakeClass() {
        return createClassName(TrnPLInfo.suikouUchiwake_COLUMN);
    }
    
    /**
     * 原材料(差引売上原価)class名取得
     */
    public String getGenzairyoKeiClass() {
        return createClassName(TrnPLInfo.genzairyoKei_COLUMN);
    }
    
    /**
     * 原材料(当月仕入)class名取得
     */
    public String getGenzairyoShireClass() {
        return createClassName(TrnPLInfo.genzairyoShire_COLUMN);
    }
    
    /**
     * 原材料(当月在庫)class名取得
     */
    public String getGenzairyoZaikoClass() {
        return createClassName(TrnPLInfo.genzairyoZaiko_COLUMN);
    }
    
    /**
     * 野菜(差引売上原価)class名取得
     */
    public String getYasaiKeiClass() {
        return createClassName(TrnPLInfo.yasaiKei_COLUMN);
    }
    
    /**
     * 野菜(当月仕入)class名取得
     */
    public String getYasaiShireClass() {
        return createClassName(TrnPLInfo.yasaiShire_COLUMN);
    }
    
    /**
     * 野菜(当月在庫)class名取得
     */
    public String getYasaiZaikoClass() {
        return createClassName(TrnPLInfo.yasaiZaiko_COLUMN);
    }
    
    /**
     * 包材(差引売上原価)class名取得
     */
    public String getHouzaiKeiClass() {
        return createClassName(TrnPLInfo.houzaiKei_COLUMN);
    }
    
    /**
     * 包材(当月仕入)class名取得
     */
    public String getHouzaiShireClass() {
        return createClassName(TrnPLInfo.houzaiShire_COLUMN);
    }
    
    /**
     * 包材(当月在庫)class名取得
     */
    public String getHouzaiZaikoClass() {
        return createClassName(TrnPLInfo.houzaiZaiko_COLUMN);
    }
    
    /**
     * 物販(差引売上原価)class名取得
     */
    public String getBuppanKeiClass() {
        return createClassName(TrnPLInfo.buppanKei_COLUMN);
    }
    
    /**
     * 物販(当月仕入)class名取得
     */
    public String getBuppanShireClass() {
        return createClassName(TrnPLInfo.buppanShire_COLUMN);
    }
    
    /**
     * 物販(当月在庫)class名取得
     */
    public String getBuppanZaikoClass() {
        return createClassName(TrnPLInfo.buppanZaiko_COLUMN);
    }
    
    /**
     * 当月仕入(計)class名取得
     */
    public String getTouSiireKeiClass() {
        return createClassName(TrnPLInfo.touSiireKei_COLUMN);
    }
    
    /**
     * 当月在庫(計)class名取得
     */
    public String getTouZaikoKeiClass() {
        return createClassName(TrnPLInfo.touZaikoKei_COLUMN);
    }
    
    /**
     * 差引売上原価(計)class名取得
     */
    public String getSashihikiKeiClass() {
        return createClassName(TrnPLInfo.sashihikiKei_COLUMN);
    }
    
    /**
     * 役員報酬(給料)class名取得
     */
    public String getYakuinSalaryClass() {
        return createClassName(TrnPLInfo.yakuinSalary_COLUMN);
    }
    
    /**
     * 役員報酬(賞与)class名取得
     */
    public String getYakuinBonusClass() {
        return createClassName(TrnPLInfo.yakuinBonus_COLUMN);
    }
    
    /**
     * 役員報酬(退職金)class名取得
     */
    public String getYakuinRetireClass() {
        return createClassName(TrnPLInfo.yakuinRetire_COLUMN);
    }
    
    /**
     * 役員報酬(計)class名取得
     */
    public String getYakuinKeiClass() {
        return createClassName(TrnPLInfo.yakuinKei_COLUMN);
    }
    
    /**
     * 給料手当(給料)class名取得
     */
    public String getSalarySalaryClass() {
        return createClassName(TrnPLInfo.salarySalary_COLUMN);
    }
    
    /**
     * 給料手当(賞与)class名取得
     */
    public String getSalaryBonusClass() {
        return createClassName(TrnPLInfo.salaryBonus_COLUMN);
    }
    
    /**
     * 給料手当(退職金)class名取得
     */
    public String getSalaryRetireClass() {
        return createClassName(TrnPLInfo.salaryRetire_COLUMN);
    }
    
    /**
     * 給料手当(計)class名取得
     */
    public String getSalaryKeiClass() {
        return createClassName(TrnPLInfo.salaryKei_COLUMN);
    }
    
    /**
     * 雑給(給料)class名取得
     */
    public String getZakkyuSalaryClass() {
        return createClassName(TrnPLInfo.zakkyuSalary_COLUMN);
    }
    
    /**
     * 雑給(賞与)class名取得
     */
    public String getZakkyuBonusClass() {
        return createClassName(TrnPLInfo.zakkyuBonus_COLUMN);
    }
    
    /**
     * 雑給(退職金)class名取得
     */
    public String getZakkyuRetireClass() {
        return createClassName(TrnPLInfo.zakkyuRetire_COLUMN);
    }
    
    /**
     * 雑給(計)class名取得
     */
    public String getZakkyuKeiClass() {
        return createClassName(TrnPLInfo.zakkyuKei_COLUMN);
    }
    
    /**
     * 給料(計)class名取得
     */
    public String getKyuryoKeiClass() {
        return createClassName(TrnPLInfo.kyuryoKei_COLUMN);
    }
    
    /**
     * 賞与(計)class名取得
     */
    public String getBonusKeiClass() {
        return createClassName(TrnPLInfo.bonusKei_COLUMN);
    }
    
    /**
     * 退職金(計)class名取得
     */
    public String getRetireKeiClass() {
        return createClassName(TrnPLInfo.retireKei_COLUMN);
    }
    
    /**
     * 給料手当内訳(計)class名取得
     */
    public String getSalaryUtiKeiClass() {
        return createClassName(TrnPLInfo.salaryUtiKei_COLUMN);
    }
    
    /**
     * 借入金(当月増加)class名取得
     */
    public String getKashiireUpClass() {
        return createClassName(TrnPLInfo.kashiireUp_COLUMN);
    }
    
    /**
     * 借入金(当月減少)class名取得
     */
    public String getKashiireDownClass() {
        return createClassName(TrnPLInfo.kashiireDown_COLUMN);
    }
    
    /**
     * 借入金(当月残高)class名取得
     */
    public String getKashiireZandakaClass() {
        return createClassName(TrnPLInfo.kashiireZandaka_COLUMN);
    }
    
    /**
     * 割賦未払金(当月増加)class名取得
     */
    public String getKappuUpClass() {
        return createClassName(TrnPLInfo.kappuUp_COLUMN);
    }
    
    /**
     * 割賦未払金(当月減少)class名取得
     */
    public String getKappuDownClass() {
        return createClassName(TrnPLInfo.kappuDown_COLUMN);
    }
    
    /**
     * 割賦未払金(当月残高)class名取得
     */
    public String getKappuZandakaClass() {
        return createClassName(TrnPLInfo.kappuZandaka_COLUMN);
    }
    
    /**
     * リース未払金(当月増加)class名取得
     */
    public String getLeaseUpClass() {
        return createClassName(TrnPLInfo.leaseUp_COLUMN);
    }
    
    /**
     * リース未払金(当月減少)class名取得
     */
    public String getLeaseDownClass() {
        return createClassName(TrnPLInfo.leaseDown_COLUMN);
    }
    
    /**
     * リース未払金(当月残高)class名取得
     */
    public String getLeaseZandakaClass() {
        return createClassName(TrnPLInfo.leaseZandaka_COLUMN);
    }
    
    /**
     * 当月増加(計)class名取得
     */
    public String getTouZoukaKeiClass() {
        return createClassName(TrnPLInfo.touZoukaKei_COLUMN);
    }
    
    /**
     * 当月減少(計)class名取得
     */
    public String getTouGenshoKeiClass() {
        return createClassName(TrnPLInfo.touGenshoKei_COLUMN);
    }
    
    /**
     * 当月残高(計)class名取得
     */
    public String getTouZandakaKeiClass() {
        return createClassName(TrnPLInfo.touZandakaKei_COLUMN);
    }
    
    /**
     * メモ・通信class名取得
     */
    public String getMemoClass() {
        return createClassName(TrnPLInfo.memo_COLUMN);
    }

    /**
     * 未設定数値項目設定
     * @param value 設定値
     */
    public void fillBlankItem() {
    	fillBlankItem("0");
    }

    /**
     * 未設定数値項目設定
     * @param value 設定値
     */
    public void fillBlankItem(String value) {
        setUriagedaka((isNull(getUriagedaka())) ? value : getUriagedaka());
        setUriagegenka((isNull(getUriagegenka())) ? value : getUriagegenka());
        setUriageSoRieki((isNull(getUriageSoRieki())) ? value : getUriageSoRieki());
        setSalary((isNull(getSalary())) ? value : getSalary());
        setYachin((isNull(getYachin())) ? value : getYachin());
        setSuikouHi((isNull(getSuikouHi())) ? value : getSuikouHi());
        setRoyalty((isNull(getRoyalty())) ? value : getRoyalty());
        setTesuryo((isNull(getTesuryo())) ? value : getTesuryo());
        setKoukoku((isNull(getKoukoku())) ? value : getKoukoku());
        setShoumou((isNull(getShoumou())) ? value : getShoumou());
        setHouteiFukuri((isNull(getHouteiFukuri())) ? value : getHouteiFukuri());
        setFukuriKousei((isNull(getFukuriKousei())) ? value : getFukuriKousei());
        setKousai((isNull(getKousai())) ? value : getKousai());
        setRyohi((isNull(getRyohi())) ? value : getRyohi());
        setTusin((isNull(getTusin())) ? value : getTusin());
        setLease((isNull(getLease())) ? value : getLease());
        setSharyo((isNull(getSharyo())) ? value : getSharyo());
        setSozei((isNull(getSozei())) ? value : getSozei());
        setHoken((isNull(getHoken())) ? value : getHoken());
        setUnchin((isNull(getUnchin())) ? value : getUnchin());
        setShuzen((isNull(getShuzen())) ? value : getShuzen());
        setYobi((isNull(getYobi())) ? value : getYobi());
        setZappi((isNull(getZappi())) ? value : getZappi());
        setKeihiShokei((isNull(getKeihiShokei())) ? value : getKeihiShokei());
        setShokyakuRieki((isNull(getShokyakuRieki())) ? value : getShokyakuRieki());
        setGenkaShokyaku((isNull(getGenkaShokyaku())) ? value : getGenkaShokyaku());
        setEigaiShueki((isNull(getEigaiShueki())) ? value : getEigaiShueki());
        setEigaiHiyo((isNull(getEigaiHiyo())) ? value : getEigaiHiyo());
        setHonshahiHai((isNull(getHonshahiHai())) ? value : getHonshahiHai());
        setRieki((isNull(getRieki())) ? value : getRieki());
        setUriage((isNull(getUriage())) ? value : getUriage());
        setBuppan((isNull(getBuppan())) ? value : getBuppan());
        setUriUchiwake((isNull(getUriUchiwake())) ? value : getUriUchiwake());
        setElec((isNull(getElec())) ? value : getElec());
        setGas((isNull(getGas())) ? value : getGas());
        setWater((isNull(getWater())) ? value : getWater());
        setOther((isNull(getOther())) ? value : getOther());
        setSuikouUchiwake((isNull(getSuikouUchiwake())) ? value : getSuikouUchiwake());
        setGenzairyoKei((isNull(getGenzairyoKei())) ? value : getGenzairyoKei());
        setGenzairyoShire((isNull(getGenzairyoShire())) ? value : getGenzairyoShire());
        setGenzairyoZaiko((isNull(getGenzairyoZaiko())) ? value : getGenzairyoZaiko());
        setYasaiKei((isNull(getYasaiKei())) ? value : getYasaiKei());
        setYasaiShire((isNull(getYasaiShire())) ? value : getYasaiShire());
        setYasaiZaiko((isNull(getYasaiZaiko())) ? value : getYasaiZaiko());
        setHouzaiKei((isNull(getHouzaiKei())) ? value : getHouzaiKei());
        setHouzaiShire((isNull(getHouzaiShire())) ? value : getHouzaiShire());
        setHouzaiZaiko((isNull(getHouzaiZaiko())) ? value : getHouzaiZaiko());
        setBuppanKei((isNull(getBuppanKei())) ? value : getBuppanKei());
        setBuppanShire((isNull(getBuppanShire())) ? value : getBuppanShire());
        setBuppanZaiko((isNull(getBuppanZaiko())) ? value : getBuppanZaiko());
        setTouSiireKei((isNull(getTouSiireKei())) ? value : getTouSiireKei());
        setTouZaikoKei((isNull(getTouZaikoKei())) ? value : getTouZaikoKei());
        setSashihikiKei((isNull(getSashihikiKei())) ? value : getSashihikiKei());
        setYakuinSalary((isNull(getYakuinSalary())) ? value : getYakuinSalary());
        setYakuinBonus((isNull(getYakuinBonus())) ? value : getYakuinBonus());
        setYakuinRetire((isNull(getYakuinRetire())) ? value : getYakuinRetire());
        setYakuinKei((isNull(getYakuinKei())) ? value : getYakuinKei());
        setSalarySalary((isNull(getSalarySalary())) ? value : getSalarySalary());
        setSalaryBonus((isNull(getSalaryBonus())) ? value : getSalaryBonus());
        setSalaryRetire((isNull(getSalaryRetire())) ? value : getSalaryRetire());
        setSalaryKei((isNull(getSalaryKei())) ? value : getSalaryKei());
        setZakkyuSalary((isNull(getZakkyuSalary())) ? value : getZakkyuSalary());
        setZakkyuBonus((isNull(getZakkyuBonus())) ? value : getZakkyuBonus());
        setZakkyuRetire((isNull(getZakkyuRetire())) ? value : getZakkyuRetire());
        setZakkyuKei((isNull(getZakkyuKei())) ? value : getZakkyuKei());
        setKyuryoKei((isNull(getKyuryoKei())) ? value : getKyuryoKei());
        setBonusKei((isNull(getBonusKei())) ? value : getBonusKei());
        setRetireKei((isNull(getRetireKei())) ? value : getRetireKei());
        setSalaryUtiKei((isNull(getSalaryUtiKei())) ? value : getSalaryUtiKei());
        setKashiireUp((isNull(getKashiireUp())) ? value : getKashiireUp());
        setKashiireDown((isNull(getKashiireDown())) ? value : getKashiireDown());
        setKashiireZandaka((isNull(getKashiireZandaka())) ? value : getKashiireZandaka());
        setKappuUp((isNull(getKappuUp())) ? value : getKappuUp());
        setKappuDown((isNull(getKappuDown())) ? value : getKappuDown());
        setKappuZandaka((isNull(getKappuZandaka())) ? value : getKappuZandaka());
        setLeaseUp((isNull(getLeaseUp())) ? value : getLeaseUp());
        setLeaseDown((isNull(getLeaseDown())) ? value : getLeaseDown());
        setLeaseZandaka((isNull(getLeaseZandaka())) ? value : getLeaseZandaka());
        setTouZoukaKei((isNull(getTouZoukaKei())) ? value : getTouZoukaKei());
        setTouGenshoKei((isNull(getTouGenshoKei())) ? value : getTouGenshoKei());
        setTouZandakaKei((isNull(getTouZandakaKei())) ? value : getTouZandakaKei());
    }

    /**
	 * nullチェック
	 */
    private boolean isNull(String val) {
        if (val == null) {
            return true;
        }
        if ("".equals(val.trim())) {
            return true;
        }
        return false;
    }
}
