package jp.co.isid.mos.bird.bizsupport.pllumpextract.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class UIPlDataInfo {
    
    public static final String TABLE = "BT17PLDT";
    
    public static final String plType_COLUMN = "PL_TYPE";
    
    public static final String plYm_COLUMN = "PL_YM";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String uriagedaka_COLUMN = "URIAGEDAKA";
    
    public static final String uriagegenka_COLUMN = "URIAGEGENKA";
    
    public static final String uriageSoRieki_COLUMN = "URIAGE_SO_RIEKI";
    
    public static final String salary_COLUMN = "SALARY";
    
    public static final String yachin_COLUMN = "YACHIN";
    
    public static final String suikouHi_COLUMN = "SUIKOU_HI";
    
    public static final String royalty_COLUMN = "ROYALTY";
    
    public static final String tesuryo_COLUMN = "TESURYO";
    
    public static final String koukoku_COLUMN = "KOUKOKU";
    
    public static final String shoumou_COLUMN = "SHOUMOU";
    
    public static final String houteiFukuri_COLUMN = "HOUTEI_FUKURI";
    
    public static final String fukuriKousei_COLUMN = "FUKURI_KOUSEI";
    
    public static final String kousai_COLUMN = "KOUSAI";
    
    public static final String ryohi_COLUMN = "RYOHI";
    
    public static final String tusin_COLUMN = "TUSIN";
    
    public static final String lease_COLUMN = "LEASE";
    
    public static final String sharyo_COLUMN = "SHARYO";
    
    public static final String sozei_COLUMN = "SOZEI";
    
    public static final String hoken_COLUMN = "HOKEN";
    
    public static final String unchin_COLUMN = "UNCHIN";
    
    public static final String shuzen_COLUMN = "SHUZEN";
    
    public static final String yobi_COLUMN = "YOBI";
    
    public static final String zappi_COLUMN = "ZAPPI";
    
    public static final String keihiShokei_COLUMN = "KEIHI_SHOKEI";
    
    public static final String shokyakuRieki_COLUMN = "SHOKYAKU_RIEKI";
    
    public static final String genkaShokyaku_COLUMN = "GENKA_SHOKYAKU";
    
    public static final String eigaiShueki_COLUMN = "EIGAI_SHUEKI";
    
    public static final String eigaiHiyo_COLUMN = "EIGAI_HIYO";
    
    public static final String honshahiHai_COLUMN = "HONSHAHI_HAI";
    
    public static final String rieki_COLUMN = "RIEKI";
    
    public static final String uriage_COLUMN = "URIAGE";
    
    public static final String buppan_COLUMN = "BUPPAN";
    
    public static final String uriUchiwake_COLUMN = "URI_UCHIWAKE";
    
    public static final String elec_COLUMN = "ELEC";
    
    public static final String gas_COLUMN = "GAS";
    
    public static final String water_COLUMN = "WATER";
    
    public static final String sonota_COLUMN = "SONOTA";
    
    public static final String suikouUchiwake_COLUMN = "SUIKOU_UCHIWAKE";
    
    public static final String genzairyoZenZaiko_COLUMN = "GENZAIRYO_ZEN_ZAIKO";
    
    public static final String genzairyoKei_COLUMN = "GENZAIRYO_KEI";
    
    public static final String genzairyoShire_COLUMN = "GENZAIRYO_SHIRE";
    
    public static final String genzairyoZaiko_COLUMN = "GENZAIRYO_ZAIKO";
    
    public static final String yasaiZenZaiko_COLUMN = "YASAI_ZEN_ZAIKO";
    
    public static final String yasaiKei_COLUMN = "YASAI_KEI";
    
    public static final String yasaiShire_COLUMN = "YASAI_SHIRE";
    
    public static final String yasaiZaiko_COLUMN = "YASAI_ZAIKO";
    
    public static final String houzaiZenZaiko_COLUMN = "HOUZAI_ZEN_ZAIKO";
    
    public static final String houzaiKei_COLUMN = "HOUZAI_KEI";
    
    public static final String houzaiShire_COLUMN = "HOUZAI_SHIRE";
    
    public static final String houzaiZaiko_COLUMN = "HOUZAI_ZAIKO";
    
    public static final String buppanZenZaiko_COLUMN = "BUPPAN_ZEN_ZAIKO";
    
    public static final String buppanKei_COLUMN = "BUPPAN_KEI";
    
    public static final String buppanShire_COLUMN = "BUPPAN_SHIRE";
    
    public static final String buppanZaiko_COLUMN = "BUPPAN_ZAIKO";
    
    public static final String zenZaikoKei_COLUMN = "ZEN_ZAIKO_KEI";
    
    public static final String touSiireKei_COLUMN = "TOU_SIIRE_KEI";
    
    public static final String touZaikoKei_COLUMN = "TOU_ZAIKO_KEI";
    
    public static final String sashihikiKei_COLUMN = "SASHIHIKI_KEI";
    
    public static final String yakuinSalary_COLUMN = "YAKUIN_SALARY";
    
    public static final String yakuinBonus_COLUMN = "YAKUIN_BONUS";
    
    public static final String yakuinRetire_COLUMN = "YAKUIN_RETIRE";
    
    public static final String yakuinKei_COLUMN = "YAKUIN_KEI";
    
    public static final String salarySalary_COLUMN = "SALARY_SALARY";
    
    public static final String salaryBonus_COLUMN = "SALARY_BONUS";
    
    public static final String salaryRetire_COLUMN = "SALARY_RETIRE";
    
    public static final String salaryKei_COLUMN = "SALARY_KEI";
    
    public static final String zakkyuSalary_COLUMN = "ZAKKYU_SALARY";
    
    public static final String zakkyuBonus_COLUMN = "ZAKKYU_BONUS";
    
    public static final String zakkyuRetire_COLUMN = "ZAKKYU_RETIRE";
    
    public static final String zakkyuKei_COLUMN = "ZAKKYU_KEI";
    
    public static final String kyuryoKei_COLUMN = "KYURYO_KEI";
    
    public static final String bonusKei_COLUMN = "BONUS_KEI";
    
    public static final String retireKei_COLUMN = "RETIRE_KEI";
    
    public static final String salaryUtiKei_COLUMN = "SALARY_UTI_KEI";
    
    public static final String kashiireZenZandaka_COLUMN = "KASHIIRE_ZEN_ZANDAKA";
    
    public static final String kashiireUp_COLUMN = "KASHIIRE_UP";
    
    public static final String kashiireDown_COLUMN = "KASHIIRE_DOWN";
    
    public static final String kashiireZandaka_COLUMN = "KASHIIRE_ZANDAKA";
    
    public static final String kappuZenZandaka_COLUMN = "KAPPU_ZEN_ZANDAKA";
    
    public static final String kappuUp_COLUMN = "KAPPU_UP";
    
    public static final String kappuDown_COLUMN = "KAPPU_DOWN";
    
    public static final String kappuZandaka_COLUMN = "KAPPU_ZANDAKA";
    
    public static final String leaseZenZandaka_COLUMN = "LEASE_ZEN_ZANDAKA";
    
    public static final String leaseUp_COLUMN = "LEASE_UP";
    
    public static final String leaseDown_COLUMN = "LEASE_DOWN";
    
    public static final String leaseZandaka_COLUMN = "LEASE_ZANDAKA";
    
    public static final String zenZandakaKei_COLUMN = "ZEN_ZANDAKA_KEI";
    
    public static final String touZoukaKei_COLUMN = "TOU_ZOUKA_KEI";
    
    public static final String touGenshoKei_COLUMN = "TOU_GENSHO_KEI";
    
    public static final String touZandakaKei_COLUMN = "TOU_ZANDAKA_KEI";
    
    public static final String memo_COLUMN = "MEMO";
    
    public static final String authorName_COLUMN = "AUTHOR_NAME";
    
    public static final String authDt_COLUMN = "AUTH_DT";
    
    public static final String authPhoneNum_COLUMN = "AUTH_PHONE_NUM";
    
    public static final String authOther_COLUMN = "AUTH_OTHER";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
        
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
    private BigDecimal uriagedaka;
    
    /**
     * 売上原価
     */
    private BigDecimal uriagegenka;
    
    /**
     * 売上総利益
     */
    private BigDecimal uriageSoRieki;
    
    /**
     * 給料手当
     */
    private BigDecimal salary;
    
    /**
     * 家賃地代
     */
    private BigDecimal yachin;
    
    /**
     * 水道光熱費
     */
    private BigDecimal suikouHi;
    
    /**
     * ロイヤルティ
     */
    private BigDecimal royalty;
    
    /**
     * 支払手数料
     */
    private BigDecimal tesuryo;
    
    /**
     * 広告費
     */
    private BigDecimal koukoku;
    
    /**
     * 消耗品費
     */
    private BigDecimal shoumou;
    
    /**
     * 法定福利費
     */
    private BigDecimal houteiFukuri;
    
    /**
     * 福利厚生費
     */
    private BigDecimal fukuriKousei;
    
    /**
     * 交際費
     */
    private BigDecimal kousai;
    
    /**
     * 旅費交通費
     */
    private BigDecimal ryohi;
    
    /**
     * 通信費
     */
    private BigDecimal tusin;
    
    /**
     * 賃借リース料
     */
    private BigDecimal lease;
    
    /**
     * 車両費
     */
    private BigDecimal sharyo;
    
    /**
     * 租税公課
     */
    private BigDecimal sozei;
    
    /**
     * 保険料
     */
    private BigDecimal hoken;
    
    /**
     * 運賃
     */
    private BigDecimal unchin;
    
    /**
     * 修繕費
     */
    private BigDecimal shuzen;
    
    /**
     * 予備欄
     */
    private BigDecimal yobi;
    
    /**
     * 雑費
     */
    private BigDecimal zappi;
    
    /**
     * 経費小計
     */
    private BigDecimal keihiShokei;
    
    /**
     * 償却前利益
     */
    private BigDecimal shokyakuRieki;
    
    /**
     * 減価償却費
     */
    private BigDecimal genkaShokyaku;
    
    /**
     * 営業外収益
     */
    private BigDecimal eigaiShueki;
    
    /**
     * 営業外費用
     */
    private BigDecimal eigaiHiyo;
    
    /**
     * 本社費配賦
     */
    private BigDecimal honshahiHai;
    
    /**
     * 当月利益
     */
    private BigDecimal rieki;
    
    /**
     * 売上
     */
    private BigDecimal uriage;
    
    /**
     * 物販売上
     */
    private BigDecimal buppan;
    
    /**
     * 売上高内訳(計)
     */
    private BigDecimal uriUchiwake;
    
    /**
     * 電気代
     */
    private BigDecimal elec;
    
    /**
     * ガス代
     */
    private BigDecimal gas;
    
    /**
     * 水道代
     */
    private BigDecimal water;
    
    /**
     * その他
     */
    private BigDecimal sonota;
    
    /**
     * 水道光熱費の内訳(計)
     */
    private BigDecimal suikouUchiwake;
    
    /**
     * 原材料(前月在庫)
     */
    private BigDecimal genzairyoZenZaiko;
    
    /**
     * 原材料(差引売上原価)
     */
    private BigDecimal genzairyoKei;
    
    /**
     * 原材料(当月仕入)
     */
    private BigDecimal genzairyoShire;
    
    /**
     * 原材料(当月在庫)
     */
    private BigDecimal genzairyoZaiko;
    
    /**
     * 野菜(前月在庫)
     */
    private BigDecimal yasaiZenZaiko;
    
    /**
     * 野菜(差引売上原価)
     */
    private BigDecimal yasaiKei;
    
    /**
     * 野菜(当月仕入)
     */
    private BigDecimal yasaiShire;
    
    /**
     * 野菜(当月在庫)
     */
    private BigDecimal yasaiZaiko;
    
    /**
     * 包材(前月在庫)
     */
    private BigDecimal houzaiZenZaiko;
    
    /**
     * 包材(差引売上原価)
     */
    private BigDecimal houzaiKei;
    
    /**
     * 包材(当月仕入)
     */
    private BigDecimal houzaiShire;
    
    /**
     * 包材(当月在庫)
     */
    private BigDecimal houzaiZaiko;
    
    /**
     * 物販(前月在庫)
     */
    private BigDecimal buppanZenZaiko;
    
    /**
     * 物販(差引売上原価)
     */
    private BigDecimal buppanKei;
    
    /**
     * 物販(当月仕入)
     */
    private BigDecimal buppanShire;
    
    /**
     * 物販(当月在庫)
     */
    private BigDecimal buppanZaiko;
    
    /**
     * 前月在庫(計)
     */
    private BigDecimal zenZaikoKei;
    
    /**
     * 当月仕入(計)
     */
    private BigDecimal touSiireKei;
    
    /**
     * 当月在庫(計)
     */
    private BigDecimal touZaikoKei;
    
    /**
     * 差引売上原価(計)
     */
    private BigDecimal sashihikiKei;
    
    /**
     * 役員報酬(給料)
     */
    private BigDecimal yakuinSalary;
    
    /**
     * 役員報酬(賞与)
     */
    private BigDecimal yakuinBonus;
    
    /**
     * 役員報酬(退職金)
     */
    private BigDecimal yakuinRetire;
    
    /**
     * 役員報酬(計)
     */
    private BigDecimal yakuinKei;
    
    /**
     * 給料手当(給料)
     */
    private BigDecimal salarySalary;
    
    /**
     * 給料手当(賞与)
     */
    private BigDecimal salaryBonus;
    
    /**
     * 給料手当(退職金)
     */
    private BigDecimal salaryRetire;
    
    /**
     * 給料手当(計)
     */
    private BigDecimal salaryKei;
    
    /**
     * 雑給(給料)
     */
    private BigDecimal zakkyuSalary;
    
    /**
     * 雑給(賞与)
     */
    private BigDecimal zakkyuBonus;
    
    /**
     * 雑給(退職金)
     */
    private BigDecimal zakkyuRetire;
    
    /**
     * 雑給(計)
     */
    private BigDecimal zakkyuKei;
    
    /**
     * 給料(計)
     */
    private BigDecimal kyuryoKei;
    
    /**
     * 賞与(計)
     */
    private BigDecimal bonusKei;
    
    /**
     * 退職金(計)
     */
    private BigDecimal retireKei;
    
    /**
     * 給料手当内訳(計)
     */
    private BigDecimal salaryUtiKei;
    
    /**
     * 借入金(前月残高)
     */
    private BigDecimal kashiireZenZandaka;
    
    /**
     * 借入金(当月増加)
     */
    private BigDecimal kashiireUp;
    
    /**
     * 借入金(当月減少)
     */
    private BigDecimal kashiireDown;
    
    /**
     * 借入金(当月残高)
     */
    private BigDecimal kashiireZandaka;
    
    /**
     * 割賦未払金(前月残高)
     */
    private BigDecimal kappuZenZandaka;
    
    /**
     * 割賦未払金(当月増加)
     */
    private BigDecimal kappuUp;
    
    /**
     * 割賦未払金(当月減少)
     */
    private BigDecimal kappuDown;
    
    /**
     * 割賦未払金(当月残高)
     */
    private BigDecimal kappuZandaka;
    
    /**
     * リース未払金(前月残高)
     */
    private BigDecimal leaseZenZandaka;
    
    /**
     * リース未払金(当月増加)
     */
    private BigDecimal leaseUp;
    
    /**
     * リース未払金(当月減少)
     */
    private BigDecimal leaseDown;
    
    /**
     * リース未払金(当月残高)
     */
    private BigDecimal leaseZandaka;
    
    /**
     * 前月残高(計)
     */
    private BigDecimal zenZandakaKei;
    
    /**
     * 当月増加(計)
     */
    private BigDecimal touZoukaKei;
    
    /**
     * 当月減少(計)
     */
    private BigDecimal touGenshoKei;
    
    /**
     * 当月残高(計)
     */
    private BigDecimal touZandakaKei;
    
    /**
     * メモ・通信
     */
    private BigDecimal memo;
    
    /**
     * 作成者
     */
    private BigDecimal authorName;
    
    /**
     * 作成年月日
     */
    private BigDecimal authDt;
    
    /**
     * 作成者電話番号
     */
    private BigDecimal authPhoneNum;
    
    /**
     * 作成者会計事務所等
     */
    private BigDecimal authOther;
    
    /**
     * 登録ユーザー
     */
    private BigDecimal firstUser;
    
    /**
     * 登録プログラム
     */
    private BigDecimal firstPgm;
    
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp firstTmsp;
    
    /**
     * 修正ユーザー
     */
    private BigDecimal lastUser;
    
    /**
     * 修正プログラム
     */
    private BigDecimal lastPgm;
    
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp lastTmsp;
        
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
    public BigDecimal getUriagedaka() {
        return uriagedaka;
    }
    /**
     * 売上高を設定します。
     * @param uriagedaka 売上高
     */
    public void setUriagedaka(BigDecimal uriagedaka) {
        this.uriagedaka = uriagedaka;
    }
    
    /**
     * 売上原価を取得します。
     * @return 売上原価
     */
    public BigDecimal getUriagegenka() {
        return uriagegenka;
    }
    /**
     * 売上原価を設定します。
     * @param uriagegenka 売上原価
     */
    public void setUriagegenka(BigDecimal uriagegenka) {
        this.uriagegenka = uriagegenka;
    }
    
    /**
     * 売上総利益を取得します。
     * @return 売上総利益
     */
    public BigDecimal getUriageSoRieki() {
        return uriageSoRieki;
    }
    /**
     * 売上総利益を設定します。
     * @param uriageSoRieki 売上総利益
     */
    public void setUriageSoRieki(BigDecimal uriageSoRieki) {
        this.uriageSoRieki = uriageSoRieki;
    }
    
    /**
     * 給料手当を取得します。
     * @return 給料手当
     */
    public BigDecimal getSalary() {
        return salary;
    }
    /**
     * 給料手当を設定します。
     * @param salary 給料手当
     */
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
    
    /**
     * 家賃地代を取得します。
     * @return 家賃地代
     */
    public BigDecimal getYachin() {
        return yachin;
    }
    /**
     * 家賃地代を設定します。
     * @param yachin 家賃地代
     */
    public void setYachin(BigDecimal yachin) {
        this.yachin = yachin;
    }
    
    /**
     * 水道光熱費を取得します。
     * @return 水道光熱費
     */
    public BigDecimal getSuikouHi() {
        return suikouHi;
    }
    /**
     * 水道光熱費を設定します。
     * @param suikouHi 水道光熱費
     */
    public void setSuikouHi(BigDecimal suikouHi) {
        this.suikouHi = suikouHi;
    }
    
    /**
     * ロイヤルティを取得します。
     * @return ロイヤルティ
     */
    public BigDecimal getRoyalty() {
        return royalty;
    }
    /**
     * ロイヤルティを設定します。
     * @param royalty ロイヤルティ
     */
    public void setRoyalty(BigDecimal royalty) {
        this.royalty = royalty;
    }
    
    /**
     * 支払手数料を取得します。
     * @return 支払手数料
     */
    public BigDecimal getTesuryo() {
        return tesuryo;
    }
    /**
     * 支払手数料を設定します。
     * @param tesuryo 支払手数料
     */
    public void setTesuryo(BigDecimal tesuryo) {
        this.tesuryo = tesuryo;
    }
    
    /**
     * 広告費を取得します。
     * @return 広告費
     */
    public BigDecimal getKoukoku() {
        return koukoku;
    }
    /**
     * 広告費を設定します。
     * @param koukoku 広告費
     */
    public void setKoukoku(BigDecimal koukoku) {
        this.koukoku = koukoku;
    }
    
    /**
     * 消耗品費を取得します。
     * @return 消耗品費
     */
    public BigDecimal getShoumou() {
        return shoumou;
    }
    /**
     * 消耗品費を設定します。
     * @param shoumou 消耗品費
     */
    public void setShoumou(BigDecimal shoumou) {
        this.shoumou = shoumou;
    }
    
    /**
     * 法定福利費を取得します。
     * @return 法定福利費
     */
    public BigDecimal getHouteiFukuri() {
        return houteiFukuri;
    }
    /**
     * 法定福利費を設定します。
     * @param houteiFukuri 法定福利費
     */
    public void setHouteiFukuri(BigDecimal houteiFukuri) {
        this.houteiFukuri = houteiFukuri;
    }
    
    /**
     * 福利厚生費を取得します。
     * @return 福利厚生費
     */
    public BigDecimal getFukuriKousei() {
        return fukuriKousei;
    }
    /**
     * 福利厚生費を設定します。
     * @param fukuriKousei 福利厚生費
     */
    public void setFukuriKousei(BigDecimal fukuriKousei) {
        this.fukuriKousei = fukuriKousei;
    }
    
    /**
     * 交際費を取得します。
     * @return 交際費
     */
    public BigDecimal getKousai() {
        return kousai;
    }
    /**
     * 交際費を設定します。
     * @param kousai 交際費
     */
    public void setKousai(BigDecimal kousai) {
        this.kousai = kousai;
    }
    
    /**
     * 旅費交通費を取得します。
     * @return 旅費交通費
     */
    public BigDecimal getRyohi() {
        return ryohi;
    }
    /**
     * 旅費交通費を設定します。
     * @param ryohi 旅費交通費
     */
    public void setRyohi(BigDecimal ryohi) {
        this.ryohi = ryohi;
    }
    
    /**
     * 通信費を取得します。
     * @return 通信費
     */
    public BigDecimal getTusin() {
        return tusin;
    }
    /**
     * 通信費を設定します。
     * @param tusin 通信費
     */
    public void setTusin(BigDecimal tusin) {
        this.tusin = tusin;
    }
    
    /**
     * 賃借リース料を取得します。
     * @return 賃借リース料
     */
    public BigDecimal getLease() {
        return lease;
    }
    /**
     * 賃借リース料を設定します。
     * @param lease 賃借リース料
     */
    public void setLease(BigDecimal lease) {
        this.lease = lease;
    }
    
    /**
     * 車両費を取得します。
     * @return 車両費
     */
    public BigDecimal getSharyo() {
        return sharyo;
    }
    /**
     * 車両費を設定します。
     * @param sharyo 車両費
     */
    public void setSharyo(BigDecimal sharyo) {
        this.sharyo = sharyo;
    }
    
    /**
     * 租税公課を取得します。
     * @return 租税公課
     */
    public BigDecimal getSozei() {
        return sozei;
    }
    /**
     * 租税公課を設定します。
     * @param sozei 租税公課
     */
    public void setSozei(BigDecimal sozei) {
        this.sozei = sozei;
    }
    
    /**
     * 保険料を取得します。
     * @return 保険料
     */
    public BigDecimal getHoken() {
        return hoken;
    }
    /**
     * 保険料を設定します。
     * @param hoken 保険料
     */
    public void setHoken(BigDecimal hoken) {
        this.hoken = hoken;
    }
    
    /**
     * 運賃を取得します。
     * @return 運賃
     */
    public BigDecimal getUnchin() {
        return unchin;
    }
    /**
     * 運賃を設定します。
     * @param unchin 運賃
     */
    public void setUnchin(BigDecimal unchin) {
        this.unchin = unchin;
    }
    
    /**
     * 修繕費を取得します。
     * @return 修繕費
     */
    public BigDecimal getShuzen() {
        return shuzen;
    }
    /**
     * 修繕費を設定します。
     * @param shuzen 修繕費
     */
    public void setShuzen(BigDecimal shuzen) {
        this.shuzen = shuzen;
    }
    
    /**
     * 予備欄を取得します。
     * @return 予備欄
     */
    public BigDecimal getYobi() {
        return yobi;
    }
    /**
     * 予備欄を設定します。
     * @param yobi 予備欄
     */
    public void setYobi(BigDecimal yobi) {
        this.yobi = yobi;
    }
    
    /**
     * 雑費を取得します。
     * @return 雑費
     */
    public BigDecimal getZappi() {
        return zappi;
    }
    /**
     * 雑費を設定します。
     * @param zappi 雑費
     */
    public void setZappi(BigDecimal zappi) {
        this.zappi = zappi;
    }
    
    /**
     * 経費小計を取得します。
     * @return 経費小計
     */
    public BigDecimal getKeihiShokei() {
        return keihiShokei;
    }
    /**
     * 経費小計を設定します。
     * @param keihiShokei 経費小計
     */
    public void setKeihiShokei(BigDecimal keihiShokei) {
        this.keihiShokei = keihiShokei;
    }
    
    /**
     * 償却前利益を取得します。
     * @return 償却前利益
     */
    public BigDecimal getShokyakuRieki() {
        return shokyakuRieki;
    }
    /**
     * 償却前利益を設定します。
     * @param shokyakuRieki 償却前利益
     */
    public void setShokyakuRieki(BigDecimal shokyakuRieki) {
        this.shokyakuRieki = shokyakuRieki;
    }
    
    /**
     * 減価償却費を取得します。
     * @return 減価償却費
     */
    public BigDecimal getGenkaShokyaku() {
        return genkaShokyaku;
    }
    /**
     * 減価償却費を設定します。
     * @param genkaShokyaku 減価償却費
     */
    public void setGenkaShokyaku(BigDecimal genkaShokyaku) {
        this.genkaShokyaku = genkaShokyaku;
    }
    
    /**
     * 営業外収益を取得します。
     * @return 営業外収益
     */
    public BigDecimal getEigaiShueki() {
        return eigaiShueki;
    }
    /**
     * 営業外収益を設定します。
     * @param eigaiShueki 営業外収益
     */
    public void setEigaiShueki(BigDecimal eigaiShueki) {
        this.eigaiShueki = eigaiShueki;
    }
    
    /**
     * 営業外費用を取得します。
     * @return 営業外費用
     */
    public BigDecimal getEigaiHiyo() {
        return eigaiHiyo;
    }
    /**
     * 営業外費用を設定します。
     * @param eigaiHiyo 営業外費用
     */
    public void setEigaiHiyo(BigDecimal eigaiHiyo) {
        this.eigaiHiyo = eigaiHiyo;
    }
    
    /**
     * 本社費配賦を取得します。
     * @return 本社費配賦
     */
    public BigDecimal getHonshahiHai() {
        return honshahiHai;
    }
    /**
     * 本社費配賦を設定します。
     * @param honshahiHai 本社費配賦
     */
    public void setHonshahiHai(BigDecimal honshahiHai) {
        this.honshahiHai = honshahiHai;
    }
    
    /**
     * 当月利益を取得します。
     * @return 当月利益
     */
    public BigDecimal getRieki() {
        return rieki;
    }
    /**
     * 当月利益を設定します。
     * @param rieki 当月利益
     */
    public void setRieki(BigDecimal rieki) {
        this.rieki = rieki;
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
     * 物販売上を取得します。
     * @return 物販売上
     */
    public BigDecimal getBuppan() {
        return buppan;
    }
    /**
     * 物販売上を設定します。
     * @param buppan 物販売上
     */
    public void setBuppan(BigDecimal buppan) {
        this.buppan = buppan;
    }
    
    /**
     * 売上高内訳(計)を取得します。
     * @return 売上高内訳(計)
     */
    public BigDecimal getUriUchiwake() {
        return uriUchiwake;
    }
    /**
     * 売上高内訳(計)を設定します。
     * @param uriUchiwake 売上高内訳(計)
     */
    public void setUriUchiwake(BigDecimal uriUchiwake) {
        this.uriUchiwake = uriUchiwake;
    }
    
    /**
     * 電気代を取得します。
     * @return 電気代
     */
    public BigDecimal getElec() {
        return elec;
    }
    /**
     * 電気代を設定します。
     * @param elec 電気代
     */
    public void setElec(BigDecimal elec) {
        this.elec = elec;
    }
    
    /**
     * ガス代を取得します。
     * @return ガス代
     */
    public BigDecimal getGas() {
        return gas;
    }
    /**
     * ガス代を設定します。
     * @param gas ガス代
     */
    public void setGas(BigDecimal gas) {
        this.gas = gas;
    }
    
    /**
     * 水道代を取得します。
     * @return 水道代
     */
    public BigDecimal getWater() {
        return water;
    }
    /**
     * 水道代を設定します。
     * @param water 水道代
     */
    public void setWater(BigDecimal water) {
        this.water = water;
    }
    
    /**
     * その他を取得します。
     * @return その他
     */
    public BigDecimal getSonota() {
        return sonota;
    }
    /**
     * その他を設定します。
     * @param sonota その他
     */
    public void setSonota(BigDecimal sonota) {
        this.sonota = sonota;
    }
    
    /**
     * 水道光熱費の内訳(計)を取得します。
     * @return 水道光熱費の内訳(計)
     */
    public BigDecimal getSuikouUchiwake() {
        return suikouUchiwake;
    }
    /**
     * 水道光熱費の内訳(計)を設定します。
     * @param suikouUchiwake 水道光熱費の内訳(計)
     */
    public void setSuikouUchiwake(BigDecimal suikouUchiwake) {
        this.suikouUchiwake = suikouUchiwake;
    }
    
    /**
     * 原材料(前月在庫)を取得します。
     * @return 原材料(前月在庫)
     */
    public BigDecimal getGenzairyoZenZaiko() {
        return genzairyoZenZaiko;
    }
    /**
     * 原材料(前月在庫)を設定します。
     * @param genzairyoZenZaiko 原材料(前月在庫)
     */
    public void setGenzairyoZenZaiko(BigDecimal genzairyoZenZaiko) {
        this.genzairyoZenZaiko = genzairyoZenZaiko;
    }
    
    /**
     * 原材料(差引売上原価)を取得します。
     * @return 原材料(差引売上原価)
     */
    public BigDecimal getGenzairyoKei() {
        return genzairyoKei;
    }
    /**
     * 原材料(差引売上原価)を設定します。
     * @param genzairyoKei 原材料(差引売上原価)
     */
    public void setGenzairyoKei(BigDecimal genzairyoKei) {
        this.genzairyoKei = genzairyoKei;
    }
    
    /**
     * 原材料(当月仕入)を取得します。
     * @return 原材料(当月仕入)
     */
    public BigDecimal getGenzairyoShire() {
        return genzairyoShire;
    }
    /**
     * 原材料(当月仕入)を設定します。
     * @param genzairyoShire 原材料(当月仕入)
     */
    public void setGenzairyoShire(BigDecimal genzairyoShire) {
        this.genzairyoShire = genzairyoShire;
    }
    
    /**
     * 原材料(当月在庫)を取得します。
     * @return 原材料(当月在庫)
     */
    public BigDecimal getGenzairyoZaiko() {
        return genzairyoZaiko;
    }
    /**
     * 原材料(当月在庫)を設定します。
     * @param genzairyoZaiko 原材料(当月在庫)
     */
    public void setGenzairyoZaiko(BigDecimal genzairyoZaiko) {
        this.genzairyoZaiko = genzairyoZaiko;
    }
    
    /**
     * 野菜(前月在庫)を取得します。
     * @return 野菜(前月在庫)
     */
    public BigDecimal getYasaiZenZaiko() {
        return yasaiZenZaiko;
    }
    /**
     * 野菜(前月在庫)を設定します。
     * @param yasaiZenZaiko 野菜(前月在庫)
     */
    public void setYasaiZenZaiko(BigDecimal yasaiZenZaiko) {
        this.yasaiZenZaiko = yasaiZenZaiko;
    }
    
    /**
     * 野菜(差引売上原価)を取得します。
     * @return 野菜(差引売上原価)
     */
    public BigDecimal getYasaiKei() {
        return yasaiKei;
    }
    /**
     * 野菜(差引売上原価)を設定します。
     * @param yasaiKei 野菜(差引売上原価)
     */
    public void setYasaiKei(BigDecimal yasaiKei) {
        this.yasaiKei = yasaiKei;
    }
    
    /**
     * 野菜(当月仕入)を取得します。
     * @return 野菜(当月仕入)
     */
    public BigDecimal getYasaiShire() {
        return yasaiShire;
    }
    /**
     * 野菜(当月仕入)を設定します。
     * @param yasaiShire 野菜(当月仕入)
     */
    public void setYasaiShire(BigDecimal yasaiShire) {
        this.yasaiShire = yasaiShire;
    }
    
    /**
     * 野菜(当月在庫)を取得します。
     * @return 野菜(当月在庫)
     */
    public BigDecimal getYasaiZaiko() {
        return yasaiZaiko;
    }
    /**
     * 野菜(当月在庫)を設定します。
     * @param yasaiZaiko 野菜(当月在庫)
     */
    public void setYasaiZaiko(BigDecimal yasaiZaiko) {
        this.yasaiZaiko = yasaiZaiko;
    }
    
    /**
     * 包材(前月在庫)を取得します。
     * @return 包材(前月在庫)
     */
    public BigDecimal getHouzaiZenZaiko() {
        return houzaiZenZaiko;
    }
    /**
     * 包材(前月在庫)を設定します。
     * @param houzaiZenZaiko 包材(前月在庫)
     */
    public void setHouzaiZenZaiko(BigDecimal houzaiZenZaiko) {
        this.houzaiZenZaiko = houzaiZenZaiko;
    }
    
    /**
     * 包材(差引売上原価)を取得します。
     * @return 包材(差引売上原価)
     */
    public BigDecimal getHouzaiKei() {
        return houzaiKei;
    }
    /**
     * 包材(差引売上原価)を設定します。
     * @param houzaiKei 包材(差引売上原価)
     */
    public void setHouzaiKei(BigDecimal houzaiKei) {
        this.houzaiKei = houzaiKei;
    }
    
    /**
     * 包材(当月仕入)を取得します。
     * @return 包材(当月仕入)
     */
    public BigDecimal getHouzaiShire() {
        return houzaiShire;
    }
    /**
     * 包材(当月仕入)を設定します。
     * @param houzaiShire 包材(当月仕入)
     */
    public void setHouzaiShire(BigDecimal houzaiShire) {
        this.houzaiShire = houzaiShire;
    }
    
    /**
     * 包材(当月在庫)を取得します。
     * @return 包材(当月在庫)
     */
    public BigDecimal getHouzaiZaiko() {
        return houzaiZaiko;
    }
    /**
     * 包材(当月在庫)を設定します。
     * @param houzaiZaiko 包材(当月在庫)
     */
    public void setHouzaiZaiko(BigDecimal houzaiZaiko) {
        this.houzaiZaiko = houzaiZaiko;
    }
    
    /**
     * 物販(前月在庫)を取得します。
     * @return 物販(前月在庫)
     */
    public BigDecimal getBuppanZenZaiko() {
        return buppanZenZaiko;
    }
    /**
     * 物販(前月在庫)を設定します。
     * @param buppanZenZaiko 物販(前月在庫)
     */
    public void setBuppanZenZaiko(BigDecimal buppanZenZaiko) {
        this.buppanZenZaiko = buppanZenZaiko;
    }
    
    /**
     * 物販(差引売上原価)を取得します。
     * @return 物販(差引売上原価)
     */
    public BigDecimal getBuppanKei() {
        return buppanKei;
    }
    /**
     * 物販(差引売上原価)を設定します。
     * @param buppanKei 物販(差引売上原価)
     */
    public void setBuppanKei(BigDecimal buppanKei) {
        this.buppanKei = buppanKei;
    }
    
    /**
     * 物販(当月仕入)を取得します。
     * @return 物販(当月仕入)
     */
    public BigDecimal getBuppanShire() {
        return buppanShire;
    }
    /**
     * 物販(当月仕入)を設定します。
     * @param buppanShire 物販(当月仕入)
     */
    public void setBuppanShire(BigDecimal buppanShire) {
        this.buppanShire = buppanShire;
    }
    
    /**
     * 物販(当月在庫)を取得します。
     * @return 物販(当月在庫)
     */
    public BigDecimal getBuppanZaiko() {
        return buppanZaiko;
    }
    /**
     * 物販(当月在庫)を設定します。
     * @param buppanZaiko 物販(当月在庫)
     */
    public void setBuppanZaiko(BigDecimal buppanZaiko) {
        this.buppanZaiko = buppanZaiko;
    }
    
    /**
     * 前月在庫(計)を取得します。
     * @return 前月在庫(計)
     */
    public BigDecimal getZenZaikoKei() {
        return zenZaikoKei;
    }
    /**
     * 前月在庫(計)を設定します。
     * @param zenZaikoKei 前月在庫(計)
     */
    public void setZenZaikoKei(BigDecimal zenZaikoKei) {
        this.zenZaikoKei = zenZaikoKei;
    }
    
    /**
     * 当月仕入(計)を取得します。
     * @return 当月仕入(計)
     */
    public BigDecimal getTouSiireKei() {
        return touSiireKei;
    }
    /**
     * 当月仕入(計)を設定します。
     * @param touSiireKei 当月仕入(計)
     */
    public void setTouSiireKei(BigDecimal touSiireKei) {
        this.touSiireKei = touSiireKei;
    }
    
    /**
     * 当月在庫(計)を取得します。
     * @return 当月在庫(計)
     */
    public BigDecimal getTouZaikoKei() {
        return touZaikoKei;
    }
    /**
     * 当月在庫(計)を設定します。
     * @param touZaikoKei 当月在庫(計)
     */
    public void setTouZaikoKei(BigDecimal touZaikoKei) {
        this.touZaikoKei = touZaikoKei;
    }
    
    /**
     * 差引売上原価(計)を取得します。
     * @return 差引売上原価(計)
     */
    public BigDecimal getSashihikiKei() {
        return sashihikiKei;
    }
    /**
     * 差引売上原価(計)を設定します。
     * @param sashihikiKei 差引売上原価(計)
     */
    public void setSashihikiKei(BigDecimal sashihikiKei) {
        this.sashihikiKei = sashihikiKei;
    }
    
    /**
     * 役員報酬(給料)を取得します。
     * @return 役員報酬(給料)
     */
    public BigDecimal getYakuinSalary() {
        return yakuinSalary;
    }
    /**
     * 役員報酬(給料)を設定します。
     * @param yakuinSalary 役員報酬(給料)
     */
    public void setYakuinSalary(BigDecimal yakuinSalary) {
        this.yakuinSalary = yakuinSalary;
    }
    
    /**
     * 役員報酬(賞与)を取得します。
     * @return 役員報酬(賞与)
     */
    public BigDecimal getYakuinBonus() {
        return yakuinBonus;
    }
    /**
     * 役員報酬(賞与)を設定します。
     * @param yakuinBonus 役員報酬(賞与)
     */
    public void setYakuinBonus(BigDecimal yakuinBonus) {
        this.yakuinBonus = yakuinBonus;
    }
    
    /**
     * 役員報酬(退職金)を取得します。
     * @return 役員報酬(退職金)
     */
    public BigDecimal getYakuinRetire() {
        return yakuinRetire;
    }
    /**
     * 役員報酬(退職金)を設定します。
     * @param yakuinRetire 役員報酬(退職金)
     */
    public void setYakuinRetire(BigDecimal yakuinRetire) {
        this.yakuinRetire = yakuinRetire;
    }
    
    /**
     * 役員報酬(計)を取得します。
     * @return 役員報酬(計)
     */
    public BigDecimal getYakuinKei() {
        return yakuinKei;
    }
    /**
     * 役員報酬(計)を設定します。
     * @param yakuinKei 役員報酬(計)
     */
    public void setYakuinKei(BigDecimal yakuinKei) {
        this.yakuinKei = yakuinKei;
    }
    
    /**
     * 給料手当(給料)を取得します。
     * @return 給料手当(給料)
     */
    public BigDecimal getSalarySalary() {
        return salarySalary;
    }
    /**
     * 給料手当(給料)を設定します。
     * @param salarySalary 給料手当(給料)
     */
    public void setSalarySalary(BigDecimal salarySalary) {
        this.salarySalary = salarySalary;
    }
    
    /**
     * 給料手当(賞与)を取得します。
     * @return 給料手当(賞与)
     */
    public BigDecimal getSalaryBonus() {
        return salaryBonus;
    }
    /**
     * 給料手当(賞与)を設定します。
     * @param salaryBonus 給料手当(賞与)
     */
    public void setSalaryBonus(BigDecimal salaryBonus) {
        this.salaryBonus = salaryBonus;
    }
    
    /**
     * 給料手当(退職金)を取得します。
     * @return 給料手当(退職金)
     */
    public BigDecimal getSalaryRetire() {
        return salaryRetire;
    }
    /**
     * 給料手当(退職金)を設定します。
     * @param salaryRetire 給料手当(退職金)
     */
    public void setSalaryRetire(BigDecimal salaryRetire) {
        this.salaryRetire = salaryRetire;
    }
    
    /**
     * 給料手当(計)を取得します。
     * @return 給料手当(計)
     */
    public BigDecimal getSalaryKei() {
        return salaryKei;
    }
    /**
     * 給料手当(計)を設定します。
     * @param salaryKei 給料手当(計)
     */
    public void setSalaryKei(BigDecimal salaryKei) {
        this.salaryKei = salaryKei;
    }
    
    /**
     * 雑給(給料)を取得します。
     * @return 雑給(給料)
     */
    public BigDecimal getZakkyuSalary() {
        return zakkyuSalary;
    }
    /**
     * 雑給(給料)を設定します。
     * @param zakkyuSalary 雑給(給料)
     */
    public void setZakkyuSalary(BigDecimal zakkyuSalary) {
        this.zakkyuSalary = zakkyuSalary;
    }
    
    /**
     * 雑給(賞与)を取得します。
     * @return 雑給(賞与)
     */
    public BigDecimal getZakkyuBonus() {
        return zakkyuBonus;
    }
    /**
     * 雑給(賞与)を設定します。
     * @param zakkyuBonus 雑給(賞与)
     */
    public void setZakkyuBonus(BigDecimal zakkyuBonus) {
        this.zakkyuBonus = zakkyuBonus;
    }
    
    /**
     * 雑給(退職金)を取得します。
     * @return 雑給(退職金)
     */
    public BigDecimal getZakkyuRetire() {
        return zakkyuRetire;
    }
    /**
     * 雑給(退職金)を設定します。
     * @param zakkyuRetire 雑給(退職金)
     */
    public void setZakkyuRetire(BigDecimal zakkyuRetire) {
        this.zakkyuRetire = zakkyuRetire;
    }
    
    /**
     * 雑給(計)を取得します。
     * @return 雑給(計)
     */
    public BigDecimal getZakkyuKei() {
        return zakkyuKei;
    }
    /**
     * 雑給(計)を設定します。
     * @param zakkyuKei 雑給(計)
     */
    public void setZakkyuKei(BigDecimal zakkyuKei) {
        this.zakkyuKei = zakkyuKei;
    }
    
    /**
     * 給料(計)を取得します。
     * @return 給料(計)
     */
    public BigDecimal getKyuryoKei() {
        return kyuryoKei;
    }
    /**
     * 給料(計)を設定します。
     * @param kyuryoKei 給料(計)
     */
    public void setKyuryoKei(BigDecimal kyuryoKei) {
        this.kyuryoKei = kyuryoKei;
    }
    
    /**
     * 賞与(計)を取得します。
     * @return 賞与(計)
     */
    public BigDecimal getBonusKei() {
        return bonusKei;
    }
    /**
     * 賞与(計)を設定します。
     * @param bonusKei 賞与(計)
     */
    public void setBonusKei(BigDecimal bonusKei) {
        this.bonusKei = bonusKei;
    }
    
    /**
     * 退職金(計)を取得します。
     * @return 退職金(計)
     */
    public BigDecimal getRetireKei() {
        return retireKei;
    }
    /**
     * 退職金(計)を設定します。
     * @param retireKei 退職金(計)
     */
    public void setRetireKei(BigDecimal retireKei) {
        this.retireKei = retireKei;
    }
    
    /**
     * 給料手当内訳(計)を取得します。
     * @return 給料手当内訳(計)
     */
    public BigDecimal getSalaryUtiKei() {
        return salaryUtiKei;
    }
    /**
     * 給料手当内訳(計)を設定します。
     * @param salaryUtiKei 給料手当内訳(計)
     */
    public void setSalaryUtiKei(BigDecimal salaryUtiKei) {
        this.salaryUtiKei = salaryUtiKei;
    }
    
    /**
     * 借入金(前月残高)を取得します。
     * @return 借入金(前月残高)
     */
    public BigDecimal getKashiireZenZandaka() {
        return kashiireZenZandaka;
    }
    /**
     * 借入金(前月残高)を設定します。
     * @param kashiireZenZandaka 借入金(前月残高)
     */
    public void setKashiireZenZandaka(BigDecimal kashiireZenZandaka) {
        this.kashiireZenZandaka = kashiireZenZandaka;
    }
    
    /**
     * 借入金(当月増加)を取得します。
     * @return 借入金(当月増加)
     */
    public BigDecimal getKashiireUp() {
        return kashiireUp;
    }
    /**
     * 借入金(当月増加)を設定します。
     * @param kashiireUp 借入金(当月増加)
     */
    public void setKashiireUp(BigDecimal kashiireUp) {
        this.kashiireUp = kashiireUp;
    }
    
    /**
     * 借入金(当月減少)を取得します。
     * @return 借入金(当月減少)
     */
    public BigDecimal getKashiireDown() {
        return kashiireDown;
    }
    /**
     * 借入金(当月減少)を設定します。
     * @param kashiireDown 借入金(当月減少)
     */
    public void setKashiireDown(BigDecimal kashiireDown) {
        this.kashiireDown = kashiireDown;
    }
    
    /**
     * 借入金(当月残高)を取得します。
     * @return 借入金(当月残高)
     */
    public BigDecimal getKashiireZandaka() {
        return kashiireZandaka;
    }
    /**
     * 借入金(当月残高)を設定します。
     * @param kashiireZandaka 借入金(当月残高)
     */
    public void setKashiireZandaka(BigDecimal kashiireZandaka) {
        this.kashiireZandaka = kashiireZandaka;
    }
    
    /**
     * 割賦未払金(前月残高)を取得します。
     * @return 割賦未払金(前月残高)
     */
    public BigDecimal getKappuZenZandaka() {
        return kappuZenZandaka;
    }
    /**
     * 割賦未払金(前月残高)を設定します。
     * @param kappuZenZandaka 割賦未払金(前月残高)
     */
    public void setKappuZenZandaka(BigDecimal kappuZenZandaka) {
        this.kappuZenZandaka = kappuZenZandaka;
    }
    
    /**
     * 割賦未払金(当月増加)を取得します。
     * @return 割賦未払金(当月増加)
     */
    public BigDecimal getKappuUp() {
        return kappuUp;
    }
    /**
     * 割賦未払金(当月増加)を設定します。
     * @param kappuUp 割賦未払金(当月増加)
     */
    public void setKappuUp(BigDecimal kappuUp) {
        this.kappuUp = kappuUp;
    }
    
    /**
     * 割賦未払金(当月減少)を取得します。
     * @return 割賦未払金(当月減少)
     */
    public BigDecimal getKappuDown() {
        return kappuDown;
    }
    /**
     * 割賦未払金(当月減少)を設定します。
     * @param kappuDown 割賦未払金(当月減少)
     */
    public void setKappuDown(BigDecimal kappuDown) {
        this.kappuDown = kappuDown;
    }
    
    /**
     * 割賦未払金(当月残高)を取得します。
     * @return 割賦未払金(当月残高)
     */
    public BigDecimal getKappuZandaka() {
        return kappuZandaka;
    }
    /**
     * 割賦未払金(当月残高)を設定します。
     * @param kappuZandaka 割賦未払金(当月残高)
     */
    public void setKappuZandaka(BigDecimal kappuZandaka) {
        this.kappuZandaka = kappuZandaka;
    }
    
    /**
     * リース未払金(前月残高)を取得します。
     * @return リース未払金(前月残高)
     */
    public BigDecimal getLeaseZenZandaka() {
        return leaseZenZandaka;
    }
    /**
     * リース未払金(前月残高)を設定します。
     * @param leaseZenZandaka リース未払金(前月残高)
     */
    public void setLeaseZenZandaka(BigDecimal leaseZenZandaka) {
        this.leaseZenZandaka = leaseZenZandaka;
    }
    
    /**
     * リース未払金(当月増加)を取得します。
     * @return リース未払金(当月増加)
     */
    public BigDecimal getLeaseUp() {
        return leaseUp;
    }
    /**
     * リース未払金(当月増加)を設定します。
     * @param leaseUp リース未払金(当月増加)
     */
    public void setLeaseUp(BigDecimal leaseUp) {
        this.leaseUp = leaseUp;
    }
    
    /**
     * リース未払金(当月減少)を取得します。
     * @return リース未払金(当月減少)
     */
    public BigDecimal getLeaseDown() {
        return leaseDown;
    }
    /**
     * リース未払金(当月減少)を設定します。
     * @param leaseDown リース未払金(当月減少)
     */
    public void setLeaseDown(BigDecimal leaseDown) {
        this.leaseDown = leaseDown;
    }
    
    /**
     * リース未払金(当月残高)を取得します。
     * @return リース未払金(当月残高)
     */
    public BigDecimal getLeaseZandaka() {
        return leaseZandaka;
    }
    /**
     * リース未払金(当月残高)を設定します。
     * @param leaseZandaka リース未払金(当月残高)
     */
    public void setLeaseZandaka(BigDecimal leaseZandaka) {
        this.leaseZandaka = leaseZandaka;
    }
    
    /**
     * 前月残高(計)を取得します。
     * @return 前月残高(計)
     */
    public BigDecimal getZenZandakaKei() {
        return zenZandakaKei;
    }
    /**
     * 前月残高(計)を設定します。
     * @param zenZandakaKei 前月残高(計)
     */
    public void setZenZandakaKei(BigDecimal zenZandakaKei) {
        this.zenZandakaKei = zenZandakaKei;
    }
    
    /**
     * 当月増加(計)を取得します。
     * @return 当月増加(計)
     */
    public BigDecimal getTouZoukaKei() {
        return touZoukaKei;
    }
    /**
     * 当月増加(計)を設定します。
     * @param touZoukaKei 当月増加(計)
     */
    public void setTouZoukaKei(BigDecimal touZoukaKei) {
        this.touZoukaKei = touZoukaKei;
    }
    
    /**
     * 当月減少(計)を取得します。
     * @return 当月減少(計)
     */
    public BigDecimal getTouGenshoKei() {
        return touGenshoKei;
    }
    /**
     * 当月減少(計)を設定します。
     * @param touGenshoKei 当月減少(計)
     */
    public void setTouGenshoKei(BigDecimal touGenshoKei) {
        this.touGenshoKei = touGenshoKei;
    }
    
    /**
     * 当月残高(計)を取得します。
     * @return 当月残高(計)
     */
    public BigDecimal getTouZandakaKei() {
        return touZandakaKei;
    }
    /**
     * 当月残高(計)を設定します。
     * @param touZandakaKei 当月残高(計)
     */
    public void setTouZandakaKei(BigDecimal touZandakaKei) {
        this.touZandakaKei = touZandakaKei;
    }
    
    /**
     * メモ・通信を取得します。
     * @return メモ・通信
     */
    public BigDecimal getMemo() {
        return memo;
    }
    /**
     * メモ・通信を設定します。
     * @param memo メモ・通信
     */
    public void setMemo(BigDecimal memo) {
        this.memo = memo;
    }
    
    /**
     * 作成者を取得します。
     * @return 作成者
     */
    public BigDecimal getAuthorName() {
        return authorName;
    }
    /**
     * 作成者を設定します。
     * @param authorName 作成者
     */
    public void setAuthorName(BigDecimal authorName) {
        this.authorName = authorName;
    }
    
    /**
     * 作成年月日を取得します。
     * @return 作成年月日
     */
    public BigDecimal getAuthDt() {
        return authDt;
    }
    /**
     * 作成年月日を設定します。
     * @param authDt 作成年月日
     */
    public void setAuthDt(BigDecimal authDt) {
        this.authDt = authDt;
    }
    
    /**
     * 作成者電話番号を取得します。
     * @return 作成者電話番号
     */
    public BigDecimal getAuthPhoneNum() {
        return authPhoneNum;
    }
    /**
     * 作成者電話番号を設定します。
     * @param authPhoneNum 作成者電話番号
     */
    public void setAuthPhoneNum(BigDecimal authPhoneNum) {
        this.authPhoneNum = authPhoneNum;
    }
    
    /**
     * 作成者会計事務所等を取得します。
     * @return 作成者会計事務所等
     */
    public BigDecimal getAuthOther() {
        return authOther;
    }
    /**
     * 作成者会計事務所等を設定します。
     * @param authOther 作成者会計事務所等
     */
    public void setAuthOther(BigDecimal authOther) {
        this.authOther = authOther;
    }
    
    /**
     * 登録ユーザーを取得します。
     * @return 登録ユーザー
     */
    public BigDecimal getFirstUser() {
        return firstUser;
    }
    /**
     * 登録ユーザーを設定します。
     * @param firstUser 登録ユーザー
     */
    public void setFirstUser(BigDecimal firstUser) {
        this.firstUser = firstUser;
    }
    
    /**
     * 登録プログラムを取得します。
     * @return 登録プログラム
     */
    public BigDecimal getFirstPgm() {
        return firstPgm;
    }
    /**
     * 登録プログラムを設定します。
     * @param firstPgm 登録プログラム
     */
    public void setFirstPgm(BigDecimal firstPgm) {
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
    public BigDecimal getLastUser() {
        return lastUser;
    }
    /**
     * 修正ユーザーを設定します。
     * @param lastUser 修正ユーザー
     */
    public void setLastUser(BigDecimal lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 修正プログラムを取得します。
     * @return 修正プログラム
     */
    public BigDecimal getLastPgm() {
        return lastPgm;
    }
    /**
     * 修正プログラムを設定します。
     * @param lastPgm 修正プログラム
     */
    public void setLastPgm(BigDecimal lastPgm) {
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
    
}
