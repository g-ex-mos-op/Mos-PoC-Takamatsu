package jp.co.isid.mos.bird.bizsupport.plinfoview.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jp.co.isid.mos.bird.framework.util.Converter;

public class UIPLDataInfo {
    
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
    
    public static final String rowCount_COLUMN = "ROW_COUNT";
    
    public static final int ROW_TYPE_NORMAL = 0;
    public static final int ROW_TYPE_12SUM = 1;
    public static final int ROW_TYPE_12AVG = 2;
    
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
    private BigDecimal uriagedaka = new BigDecimal("0");
    
    /**
     * 前月売上高
     */
    private BigDecimal uriagedakaZen = new BigDecimal("0");
    
    /**
     * 売上原価
     */
    private BigDecimal uriagegenka = new BigDecimal("0");
    
    /**
     * 売上総利益
     */
    private BigDecimal uriageSoRieki = new BigDecimal("0");
    
    /**
     * 給料手当
     */
    private BigDecimal salary = new BigDecimal("0");
    
    /**
     * 家賃地代
     */
    private BigDecimal yachin = new BigDecimal("0");
    
    /**
     * 水道光熱費
     */
    private BigDecimal suikouHi = new BigDecimal("0");
    
    /**
     * ロイヤルティ
     */
    private BigDecimal royalty = new BigDecimal("0");
    
    /**
     * 支払手数料
     */
    private BigDecimal tesuryo = new BigDecimal("0");
    
    /**
     * 広告費
     */
    private BigDecimal koukoku = new BigDecimal("0");
    
    /**
     * 消耗品費
     */
    private BigDecimal shoumou = new BigDecimal("0");
    
    /**
     * 法定福利費
     */
    private BigDecimal houteiFukuri = new BigDecimal("0");
    
    /**
     * 福利厚生費
     */
    private BigDecimal fukuriKousei = new BigDecimal("0");
    
    /**
     * 交際費
     */
    private BigDecimal kousai = new BigDecimal("0");
    
    /**
     * 旅費交通費
     */
    private BigDecimal ryohi = new BigDecimal("0");
    
    /**
     * 通信費
     */
    private BigDecimal tusin = new BigDecimal("0");
    
    /**
     * 賃借リース料
     */
    private BigDecimal lease = new BigDecimal("0");
    
    /**
     * 車両費
     */
    private BigDecimal sharyo = new BigDecimal("0");
    
    /**
     * 租税公課
     */
    private BigDecimal sozei = new BigDecimal("0");
    
    /**
     * 保険料
     */
    private BigDecimal hoken = new BigDecimal("0");
    
    /**
     * 運賃
     */
    private BigDecimal unchin = new BigDecimal("0");
    
    /**
     * 修繕費
     */
    private BigDecimal shuzen = new BigDecimal("0");
    
    /**
     * 予備欄
     */
    private BigDecimal yobi = new BigDecimal("0");
    
    /**
     * 雑費
     */
    private BigDecimal zappi = new BigDecimal("0");
    
    /**
     * 経費小計
     */
    private BigDecimal keihiShokei = new BigDecimal("0");
    
    /**
     * 償却前利益
     */
    private BigDecimal shokyakuRieki = new BigDecimal("0");
    
    /**
     * 減価償却費
     */
    private BigDecimal genkaShokyaku = new BigDecimal("0");
    
    /**
     * 営業外収益
     */
    private BigDecimal eigaiShueki = new BigDecimal("0");
    
    /**
     * 営業外費用
     */
    private BigDecimal eigaiHiyo = new BigDecimal("0");
    
    /**
     * 本社費配賦
     */
    private BigDecimal honshahiHai = new BigDecimal("0");
    
    /**
     * 当月利益
     */
    private BigDecimal rieki = new BigDecimal("0");
    
    /**
     * 売上
     */
    private BigDecimal uriage = new BigDecimal("0");
    
    /**
     * 物販売上
     */
    private BigDecimal buppan = new BigDecimal("0");
    
    /**
     * 売上高内訳(計)
     */
    private BigDecimal uriUchiwake = new BigDecimal("0");
    
    /**
     * 電気代
     */
    private BigDecimal elec = new BigDecimal("0");
    
    /**
     * ガス代
     */
    private BigDecimal gas = new BigDecimal("0");
    
    /**
     * 水道代
     */
    private BigDecimal water = new BigDecimal("0");
    
    /**
     * その他
     */
    private BigDecimal sonota = new BigDecimal("0");
    
    /**
     * 水道光熱費の内訳(計)
     */
    private BigDecimal suikouUchiwake = new BigDecimal("0");
    
    /**
     * 原材料(前月在庫)
     */
    private BigDecimal genzairyoZenZaiko = new BigDecimal("0");
    
    /**
     * 原材料(差引売上原価)
     */
    private BigDecimal genzairyoKei = new BigDecimal("0");
    
    /**
     * 原材料(当月仕入)
     */
    private BigDecimal genzairyoShire = new BigDecimal("0");
    
    /**
     * 原材料(当月在庫)
     */
    private BigDecimal genzairyoZaiko = new BigDecimal("0");
    
    /**
     * 野菜(前月在庫)
     */
    private BigDecimal yasaiZenZaiko = new BigDecimal("0");
    
    /**
     * 野菜(差引売上原価)
     */
    private BigDecimal yasaiKei = new BigDecimal("0");
    
    /**
     * 野菜(当月仕入)
     */
    private BigDecimal yasaiShire = new BigDecimal("0");
    
    /**
     * 野菜(当月在庫)
     */
    private BigDecimal yasaiZaiko = new BigDecimal("0");
    
    /**
     * 包材(前月在庫)
     */
    private BigDecimal houzaiZenZaiko = new BigDecimal("0");
    
    /**
     * 包材(差引売上原価)
     */
    private BigDecimal houzaiKei = new BigDecimal("0");
    
    /**
     * 包材(当月仕入)
     */
    private BigDecimal houzaiShire = new BigDecimal("0");
    
    /**
     * 包材(当月在庫)
     */
    private BigDecimal houzaiZaiko = new BigDecimal("0");
    
    /**
     * 物販(前月在庫)
     */
    private BigDecimal buppanZenZaiko = new BigDecimal("0");
    
    /**
     * 物販(差引売上原価)
     */
    private BigDecimal buppanKei = new BigDecimal("0");
    
    /**
     * 物販(当月仕入)
     */
    private BigDecimal buppanShire = new BigDecimal("0");
    
    /**
     * 物販(当月在庫)
     */
    private BigDecimal buppanZaiko = new BigDecimal("0");
    
    /**
     * 前月在庫(計)
     */
    private BigDecimal zenZaikoKei = new BigDecimal("0");
    
    /**
     * 当月仕入(計)
     */
    private BigDecimal touSiireKei = new BigDecimal("0");
    
    /**
     * 当月在庫(計)
     */
    private BigDecimal touZaikoKei = new BigDecimal("0");
    
    /**
     * 差引売上原価(計)
     */
    private BigDecimal sashihikiKei = new BigDecimal("0");
    
    /**
     * 役員報酬(給料)
     */
    private BigDecimal yakuinSalary = new BigDecimal("0");
    
    /**
     * 役員報酬(賞与)
     */
    private BigDecimal yakuinBonus = new BigDecimal("0");
    
    /**
     * 役員報酬(退職金)
     */
    private BigDecimal yakuinRetire = new BigDecimal("0");
    
    /**
     * 役員報酬(計)
     */
    private BigDecimal yakuinKei = new BigDecimal("0");
    
    /**
     * 給料手当(給料)
     */
    private BigDecimal salarySalary = new BigDecimal("0");
    
    /**
     * 給料手当(賞与)
     */
    private BigDecimal salaryBonus = new BigDecimal("0");
    
    /**
     * 給料手当(退職金)
     */
    private BigDecimal salaryRetire = new BigDecimal("0");
    
    /**
     * 給料手当(計)
     */
    private BigDecimal salaryKei = new BigDecimal("0");
    
    /**
     * 雑給(給料)
     */
    private BigDecimal zakkyuSalary = new BigDecimal("0");
    
    /**
     * 雑給(賞与)
     */
    private BigDecimal zakkyuBonus = new BigDecimal("0");
    
    /**
     * 雑給(退職金)
     */
    private BigDecimal zakkyuRetire = new BigDecimal("0");
    
    /**
     * 雑給(計)
     */
    private BigDecimal zakkyuKei = new BigDecimal("0");
    
    /**
     * 給料(計)
     */
    private BigDecimal kyuryoKei = new BigDecimal("0");
    
    /**
     * 賞与(計)
     */
    private BigDecimal bonusKei = new BigDecimal("0");
    
    /**
     * 退職金(計)
     */
    private BigDecimal retireKei = new BigDecimal("0");
    
    /**
     * 給料手当内訳(計)
     */
    private BigDecimal salaryUtiKei = new BigDecimal("0");
    
    /**
     * 借入金(前月残高)
     */
    private BigDecimal kashiireZenZandaka = new BigDecimal("0");
    
    /**
     * 借入金(当月増加)
     */
    private BigDecimal kashiireUp = new BigDecimal("0");
    
    /**
     * 借入金(当月減少)
     */
    private BigDecimal kashiireDown = new BigDecimal("0");
    
    /**
     * 借入金(当月残高)
     */
    private BigDecimal kashiireZandaka = new BigDecimal("0");
    
    /**
     * 割賦未払金(前月残高)
     */
    private BigDecimal kappuZenZandaka = new BigDecimal("0");
    
    /**
     * 割賦未払金(当月増加)
     */
    private BigDecimal kappuUp = new BigDecimal("0");
    
    /**
     * 割賦未払金(当月減少)
     */
    private BigDecimal kappuDown = new BigDecimal("0");
    
    /**
     * 割賦未払金(当月残高)
     */
    private BigDecimal kappuZandaka = new BigDecimal("0");
    
    /**
     * リース未払金(前月残高)
     */
    private BigDecimal leaseZenZandaka = new BigDecimal("0");
    
    /**
     * リース未払金(当月増加)
     */
    private BigDecimal leaseUp = new BigDecimal("0");
    
    /**
     * リース未払金(当月減少)
     */
    private BigDecimal leaseDown = new BigDecimal("0");
    
    /**
     * リース未払金(当月残高)
     */
    private BigDecimal leaseZandaka = new BigDecimal("0");
    
    /**
     * 前月残高(計)
     */
    private BigDecimal zenZandakaKei = new BigDecimal("0");
    
    /**
     * 当月増加(計)
     */
    private BigDecimal touZoukaKei = new BigDecimal("0");
    
    /**
     * 当月減少(計)
     */
    private BigDecimal touGenshoKei = new BigDecimal("0");
    
    /**
     * 当月残高(計)
     */
    private BigDecimal touZandakaKei = new BigDecimal("0");
    
    /**
     * メモ・通信
     */
    private String memo;
    
    /**
     * 作成者
     */
    private String authorName;
    
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
     * 行タイプ
     */
    private int rowType = 0;
    
    /**
     * 月ごとの結果件数
     */
    private int rowCount = 0;
    
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
        String val = Converter.decToString(uriagedaka);
        this.uriagedaka = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(uriagegenka);
        this.uriagegenka = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(uriageSoRieki);
        this.uriageSoRieki = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(salary);
        this.salary = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(yachin);
        this.yachin = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(suikouHi);
        this.suikouHi = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(royalty);
        this.royalty = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(tesuryo);
        this.tesuryo = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(koukoku);
        this.koukoku = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(shoumou);
        this.shoumou = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(houteiFukuri);
        this.houteiFukuri = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(fukuriKousei);
        this.fukuriKousei = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(kousai);
        this.kousai = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(ryohi);
        this.ryohi = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(tusin);
        this.tusin = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(lease);
        this.lease = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(sharyo);
        this.sharyo = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(sozei);
        this.sozei = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(hoken);
        this.hoken = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(unchin);
        this.unchin = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(shuzen);
        this.shuzen = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(yobi);
        this.yobi = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(zappi);
        this.zappi = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(keihiShokei);
        this.keihiShokei = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(shokyakuRieki);
        this.shokyakuRieki = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(genkaShokyaku);
        this.genkaShokyaku = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(eigaiShueki);
        this.eigaiShueki = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(eigaiHiyo);
        this.eigaiHiyo = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(honshahiHai);
        this.honshahiHai = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(rieki);
        this.rieki = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(uriage);
        this.uriage = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(buppan);
        this.buppan = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(uriUchiwake);
        this.uriUchiwake = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(elec);
        this.elec = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(gas);
        this.gas = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(water);
        this.water = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
     * @param other その他
     */
    public void setSonota(BigDecimal sonota) {
        String val = Converter.decToString(sonota);
        this.sonota = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(suikouUchiwake);
        this.suikouUchiwake = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(genzairyoZenZaiko);
        this.genzairyoZenZaiko = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(genzairyoKei);
        this.genzairyoKei = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(genzairyoShire);
        this.genzairyoShire = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(genzairyoZaiko);
        this.genzairyoZaiko = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(yasaiZenZaiko);
        this.yasaiZenZaiko = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(yasaiKei);
        this.yasaiKei = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(yasaiShire);
        this.yasaiShire = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(yasaiZaiko);
        this.yasaiZaiko = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(houzaiZenZaiko);
        this.houzaiZenZaiko = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(houzaiKei);
        this.houzaiKei = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(houzaiShire);
        this.houzaiShire = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(houzaiZaiko);
        this.houzaiZaiko = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(buppanZenZaiko);
        this.buppanZenZaiko = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(buppanKei);
        this.buppanKei = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(buppanShire);
        this.buppanShire = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(buppanZaiko);
        this.buppanZaiko = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(zenZaikoKei);
        this.zenZaikoKei = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(touSiireKei);
        this.touSiireKei = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(touZaikoKei);
        this.touZaikoKei = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(sashihikiKei);
        this.sashihikiKei = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(yakuinSalary);
        this.yakuinSalary = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(yakuinBonus);
        this.yakuinBonus = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(yakuinRetire);
        this.yakuinRetire = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(yakuinKei);
        this.yakuinKei = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(salarySalary);
        this.salarySalary = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(salaryBonus);
        this.salaryBonus = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(salaryRetire);
        this.salaryRetire = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(salaryKei);
        this.salaryKei = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(zakkyuSalary);
        this.zakkyuSalary = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(zakkyuBonus);
        this.zakkyuBonus = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(zakkyuRetire);
        this.zakkyuRetire = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(zakkyuKei);
        this.zakkyuKei = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(kyuryoKei);
        this.kyuryoKei = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(bonusKei);
        this.bonusKei = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(retireKei);
        this.retireKei = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(salaryUtiKei);
        this.salaryUtiKei = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(kashiireZenZandaka);
        this.kashiireZenZandaka = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(kashiireUp);
        this.kashiireUp = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(kashiireDown);
        this.kashiireDown = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(kashiireZandaka);
        this.kashiireZandaka = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(kappuZenZandaka);
        this.kappuZenZandaka = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(kappuUp);
        this.kappuUp = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(kappuDown);
        this.kappuDown = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(kappuZandaka);
        this.kappuZandaka = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(leaseZenZandaka);
        this.leaseZenZandaka = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(leaseUp);
        this.leaseUp = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(leaseDown);
        this.leaseDown = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(leaseZandaka);
        this.leaseZandaka = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(zenZandakaKei);
        this.zenZandakaKei = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(touZoukaKei);
        this.touZoukaKei = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(touGenshoKei);
        this.touGenshoKei = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
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
        String val = Converter.decToString(touZandakaKei);
        this.touZandakaKei = (val.equals("") ? new BigDecimal("0") : new BigDecimal(removeComma(val)));
   }
    
    /**
     * メモ・通信を取得します。
     * @return メモ・通信
     */
    public String getMemo() {
        return memo;
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
        return authorName;
    }
    /**
     * 作成者を設定します。
     * @param author 作成者
     */
    public void setAuthor(String authorName) {
        this.authorName = authorName;
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
     * 当月利益
     *  ＝ 償却前利益 - 減価償却費 - 営業外費用 - 共通費配賦 + 営業外利益
     * @return
     */
    public BigDecimal getTougetuRieki() {
        BigDecimal val = new BigDecimal("0");
        
        val = getShokyakuRieki().subtract(getGenkaShokyaku());
        val = val.subtract(getEigaiHiyo());
        val = val.subtract(getHonshahiHai());
        val = val.add(getEigaiShueki());
        return val;
    }
    
    /**
     * キャッシュフロー
     *  ＝ 当月利益 + 減価償却費
     * @return
     */
    public BigDecimal getCashflow() {
        //return getTougetuRieki().add(getGenkaShokyaku());
        return getRieki().add(getGenkaShokyaku());
    }
    
    /**
     * 差引余剰金
     *  ＝ キャッシュフロー - 借入返済 - 割賦支払
     * @return
     */
    public BigDecimal getSashihikiYojyo() {
        BigDecimal val = new BigDecimal("0");
        val = getCashflow().subtract(getKashiireDown());
        val = val.subtract(getKappuDown());
        return val;
    }
    
    /**
     * 賞与
     *  ＝ 賞与（計） + 退職金（計）
     */
    public BigDecimal getSalBonus() {
       return getBonusKei().add(getRetireKei());
    }
    
    /**
     * ロイヤルティ・広告費
     *  ＝ ロイヤルティ + 広告費
     * @return
     */
    public BigDecimal getRoyaltyKoukoku() {
        return getRoyalty().add(getKoukoku());
    }
    /**
     * 行タイプの取得
     * @return
     */
	public int getRowType() {
		return rowType;
	}
	public void setRowType(int rowType) {
		this.rowType = rowType;
	}
    /**
     * 前月売上高
     * @return
     */
	public BigDecimal getUriagedakaZen() {
		return uriagedakaZen;
	}
	public void setUriagedakaZen(BigDecimal uriagedakaZen) {
		this.uriagedakaZen = uriagedakaZen;
	}

    /**
     * その他合計
     *  = 支払手数料＋法定福利費＋福利厚生費＋交際費＋旅費交通費＋通信費
     *  ＋賃借リース費＋車両費＋租税公課＋保険料＋運賃＋修繕費＋雑費
     */
    public BigDecimal getSonotaGokei() {
        BigDecimal bigSonota = new BigDecimal("0");
        bigSonota = bigSonota.add(getTesuryo());
        bigSonota = bigSonota.add(getHouteiFukuri());
        bigSonota = bigSonota.add(getFukuriKousei());
        bigSonota = bigSonota.add(getKousai());
        bigSonota = bigSonota.add(getRyohi());
        bigSonota = bigSonota.add(getTusin());
        bigSonota = bigSonota.add(getLease());
        bigSonota = bigSonota.add(getSharyo());
        bigSonota = bigSonota.add(getSozei());
        bigSonota = bigSonota.add(getHoken());
        bigSonota = bigSonota.add(getUnchin());
        bigSonota = bigSonota.add(getShuzen());
        bigSonota = bigSonota.add(getZappi());
        return bigSonota;
    }
    
    /**
     * 減価償却等
     *  = 減価償却費＋営業外費用＋共通日配賦－営業外収益
     * @return
     */
    public BigDecimal getGenkaSyokyakuNado() {
        BigDecimal bigGenkaSyokakuNado = new BigDecimal("0");
        bigGenkaSyokakuNado = bigGenkaSyokakuNado.add(getGenkaShokyaku());
        bigGenkaSyokakuNado = bigGenkaSyokakuNado.add(getEigaiHiyo());
        bigGenkaSyokakuNado = bigGenkaSyokakuNado.add(getHonshahiHai());
        bigGenkaSyokakuNado = bigGenkaSyokakuNado.subtract(getEigaiShueki());
        return bigGenkaSyokakuNado;
    }
    /**
     * カンマ（,）を取り除く
     * @return java.lang.String
     * @param source java.lang.String
     */
    private static String removeComma(String source) {
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<source.length();i++) {
            if ( source.charAt(i) != ',' ) {
                sb.append(source.charAt(i));
            }
        }
        return sb.toString();
    }
    /**
     * PL年月ごとの検索結果件数
     * @return
     */
    public int getRowCount() {
        return rowCount;
    }
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }
}