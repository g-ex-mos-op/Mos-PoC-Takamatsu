package jp.co.isid.mos.bird.bizsupport.similarshop.entity;

import java.math.BigDecimal;

public class UIRuijiPLTenpoInfo {
    
    public static final String TABLE = "BT17PLDT";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
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
    
    public static final String elec_COLUMN = "ELEC";
    
    public static final String gas_COLUMN = "GAS";
    
    public static final String water_COLUMN = "WATER";
    
    public static final String sonota_COLUMN = "SONOTA";
    
    public static final String genzairyoKei_COLUMN = "GENZAIRYO_KEI";
    
    public static final String genzairyoShire_COLUMN = "GENZAIRYO_SHIRE";
    
    public static final String genzairyoZaiko_COLUMN = "GENZAIRYO_ZAIKO";
    
    public static final String yasaiKei_COLUMN = "YASAI_KEI";
    
    public static final String yasaiShire_COLUMN = "YASAI_SHIRE";
    
    public static final String yasaiZaiko_COLUMN = "YASAI_ZAIKO";
    
    public static final String houzaiKei_COLUMN = "HOUZAI_KEI";
    
    public static final String houzaiShire_COLUMN = "HOUZAI_SHIRE";
    
    public static final String houzaiZaiko_COLUMN = "HOUZAI_ZAIKO";
    
    public static final String buppanKei_COLUMN = "BUPPAN_KEI";
    
    public static final String buppanShire_COLUMN = "BUPPAN_SHIRE";
    
    public static final String buppanZaiko_COLUMN = "BUPPAN_ZAIKO";
    
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
    
    public static final String bonusKei_COLUMN = "BONUS_KEI";
    
    public static final String retireKei_COLUMN = "RETIRE_KEI";
    
    public static final String kashiireUp_COLUMN = "KASHIIRE_UP";
    
    public static final String kashiireDown_COLUMN = "KASHIIRE_DOWN";
    
    public static final String kashiireZandaka_COLUMN = "KASHIIRE_ZANDAKA";
    
    public static final String kappuUp_COLUMN = "KAPPU_UP";
    
    public static final String kappuDown_COLUMN = "KAPPU_DOWN";
    
    public static final String kappuZandaka_COLUMN = "KAPPU_ZANDAKA";
    
    public static final String leaseUp_COLUMN = "LEASE_UP";
    
    public static final String leaseDown_COLUMN = "LEASE_DOWN";
    
    public static final String leaseZandaka_COLUMN = "LEASE_ZANDAKA";
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称
     */
    private String miseNameKj;
    
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
     * ボーナス(計)
     */
    private BigDecimal bonusKei;
    
    /**
     * 退職金(計)
     */
    private BigDecimal retireKei;
    
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
     * @param other その他
     */
    public void setSonota(BigDecimal sonota) {
        this.sonota = sonota;
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
     * ボーナス(計)を取得します。
     * @return ボーナス(計)
     */
    public BigDecimal getBonusKei() {
        return bonusKei;
    }
    /**
     * ボーナス(計)を設定します。
     * @param bonusKei ボーナス(計)
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
     * @param retireKei (計)
     */
    public void setRetireKei(BigDecimal retireKei) {
        this.retireKei = retireKei;
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
    
    
    //////////////////////////////////////////////////////////////////
    // TODO マイナス値の赤字表示
    //////////////////////////////////////////////////////////////////

    /* 画面表示スタイルシート指定 */
	public static final String CLASSNAME_NORMAL = "normal_field";
    public static final String CLASSNAME_MINUS  = "minus_field";

    /**
     * 売上高(画面表示用style)
     */
    private String uriagedakaClass;
    
    /**
     * 売上原価(画面表示用style)
     */
    private String uriagegenkaClass;
    
    /**
     * 売上総利益(画面表示用style)
     */
    private String uriageSoRiekiClass;
    
    /**
     * 給料手当(画面表示用style)
     */
    private String salaryClass;
    
    /**
     * 家賃地代(画面表示用style)
     */
    private String yachinClass;
    
    /**
     * 水道光熱費(画面表示用style)
     */
    private String suikouHiClass;
    
    /**
     * ロイヤルティ(画面表示用style)
     */
    private String royaltyClass;
    
    /**
     * 支払手数料(画面表示用style)
     */
    private String tesuryoClass;
    
    /**
     * 広告費(画面表示用style)
     */
    private String koukokuClass;
    
    /**
     * 消耗品費(画面表示用style)
     */
    private String shoumouClass;
    
    /**
     * 法定福利費(画面表示用style)
     */
    private String houteiFukuriClass;
    
    /**
     * 福利厚生費(画面表示用style)
     */
    private String fukuriKouseiClass;
    
    /**
     * 交際費(画面表示用style)
     */
    private String kousaiClass;
    
    /**
     * 旅費交通費(画面表示用style)
     */
    private String ryohiClass;
    
    /**
     * 通信費(画面表示用style)
     */
    private String tusinClass;
    
    /**
     * 賃借リース料(画面表示用style)
     */
    private String leaseClass;
    
    /**
     * 車両費(画面表示用style)
     */
    private String sharyoClass;
    
    /**
     * 租税公課(画面表示用style)
     */
    private String sozeiClass;
    
    /**
     * 保険料(画面表示用style)
     */
    private String hokenClass;
    
    /**
     * 運賃(画面表示用style)
     */
    private String unchinClass;
    
    /**
     * 修繕費(画面表示用style)
     */
    private String shuzenClass;
    
    /**
     * 予備欄(画面表示用style)
     */
    private String yobiClass;
    
    /**
     * 雑費(画面表示用style)
     */
    private String zappiClass;
    
    /**
     * 経費小計(画面表示用style)
     */
    private String keihiShokeiClass;
    
    /**
     * 償却前利益(画面表示用style)
     */
    private String shokyakuRiekiClass;
    
    /**
     * 減価償却費(画面表示用style)
     */
    private String genkaShokyakuClass;
    
    /**
     * 営業外収益(画面表示用style)
     */
    private String eigaiShuekiClass;
    
    /**
     * 営業外費用(画面表示用style)
     */
    private String eigaiHiyoClass;
    
    /**
     * 本社費配賦(画面表示用style)
     */
    private String honshahiHaiClass;
    
    /**
     * 当月利益(画面表示用style)
     */
    private String riekiClass;
    
    /**
     * 売上(画面表示用style)
     */
    private String uriageClass;
    
    /**
     * 物販売上(画面表示用style)
     */
    private String buppanClass;
    
    /**
     * 電気代(画面表示用style)
     */
    private String elecClass;
    
    /**
     * ガス代(画面表示用style)
     */
    private String gasClass;
    
    /**
     * 水道代(画面表示用style)
     */
    private String waterClass;
    
    /**
     * その他(画面表示用style)
     */
    private String sonotaClass;
    
    /**
     * 原材料(差引売上原価)(画面表示用style)
     */
    private String genzairyoKeiClass;
    
    /**
     * 原材料(当月仕入)(画面表示用style)
     */
    private String genzairyoShireClass;
    
    /**
     * 原材料(当月在庫)(画面表示用style)
     */
    private String genzairyoZaikoClass;
    
    /**
     * 野菜(差引売上原価)(画面表示用style)
     */
    private String yasaiKeiClass;
    
    /**
     * 野菜(当月仕入)(画面表示用style)
     */
    private String yasaiShireClass;
    
    /**
     * 野菜(当月在庫)(画面表示用style)
     */
    private String yasaiZaikoClass;
    
    /**
     * 包材(差引売上原価)(画面表示用style)
     */
    private String houzaiKeiClass;
    
    /**
     * 包材(当月仕入)(画面表示用style)
     */
    private String houzaiShireClass;
    
    /**
     * 包材(当月在庫)(画面表示用style)
     */
    private String houzaiZaikoClass;
    
    /**
     * 物販(差引売上原価)(画面表示用style)
     */
    private String buppanKeiClass;
    
    /**
     * 物販(当月仕入)(画面表示用style)
     */
    private String buppanShireClass;
    
    /**
     * 物販(当月在庫)(画面表示用style)
     */
    private String buppanZaikoClass;
    
    /**
     * 役員報酬(給料)(画面表示用style)
     */
    private String yakuinSalaryClass;
    
    /**
     * 役員報酬(賞与)(画面表示用style)
     */
    private String yakuinBonusClass;
    
    /**
     * 役員報酬(退職金)(画面表示用style)
     */
    private String yakuinRetireClass;
    
    /**
     * 役員報酬(計)(画面表示用style)
     */
    private String yakuinKeiClass;
    
    /**
     * 給料手当(給料)(画面表示用style)
     */
    private String salarySalaryClass;
    
    /**
     * 給料手当(賞与)(画面表示用style)
     */
    private String salaryBonusClass;
    
    /**
     * 給料手当(退職金)(画面表示用style)
     */
    private String salaryRetireClass;
    
    /**
     * 給料手当(計)(画面表示用style)
     */
    private String salaryKeiClass;
    
    /**
     * 雑給(給料)(画面表示用style)
     */
    private String zakkyuSalaryClass;
    
    /**
     * 雑給(賞与)(画面表示用style)
     */
    private String zakkyuBonusClass;
    
    /**
     * 雑給(退職金)(画面表示用style)
     */
    private String zakkyuRetireClass;
    
    /**
     * 雑給(計)(画面表示用style)
     */
    private String zakkyuKeiClass;
    
    /**
     * ボーナス(計)(画面表示用style)
     */
    private String bonusKeiClass;
    
    /**
     * 退職金(計)(画面表示用style)
     */
    private String retireKeiClass;
    
    /**
     * 借入金(当月増加)(画面表示用style)
     */
    private String kashiireUpClass;
    
    /**
     * 借入金(当月減少)(画面表示用style)
     */
    private String kashiireDownClass;
    
    /**
     * 借入金(当月残高)(画面表示用style)
     */
    private String kashiireZandakaClass;
    
    /**
     * 割賦未払金(当月増加)(画面表示用style)
     */
    private String kappuUpClass;
    
    /**
     * 割賦未払金(当月減少)(画面表示用style)
     */
    private String kappuDownClass;
    
    /**
     * 割賦未払金(当月残高)(画面表示用style)
     */
    private String kappuZandakaClass;
    
    /**
     * リース未払金(当月増加)(画面表示用style)
     */
    private String leaseUpClass;
    
    /**
     * リース未払金(当月減少)(画面表示用style)
     */
    private String leaseDownClass;
    
    /**
     * リース未払金(当月残高)(画面表示用style)
     */
    private String leaseZandakaClass;

    
	/**
	 * @return bonusKeiClass を戻します。
	 */
	public String getBonusKeiClass() {
		return returnStyle(bonusKei);
	}
	/**
	 * @return buppanClass を戻します。
	 */
	public String getBuppanClass() {
		return returnStyle(buppan);
	}
	/**
	 * @return buppanKeiClass を戻します。
	 */
	public String getBuppanKeiClass() {
		return returnStyle(buppanKei);
	}
	/**
	 * @return buppanShireClass を戻します。
	 */
	public String getBuppanShireClass() {
		return returnStyle(buppanShire);
	}
	/**
	 * @return buppanZaikoClass を戻します。
	 */
	public String getBuppanZaikoClass() {
		return returnStyle(buppanZaiko);
	}
	/**
	 * @return eigaiHiyoClass を戻します。
	 */
	public String getEigaiHiyoClass() {
		return returnStyle(eigaiHiyo);
	}
	/**
	 * @return eigaiShuekiClass を戻します。
	 */
	public String getEigaiShuekiClass() {
		return returnStyle(eigaiShueki);
	}
	/**
	 * @return elecClass を戻します。
	 */
	public String getElecClass() {
		return returnStyle(elec);
	}
	/**
	 * @return fukuriKouseiClass を戻します。
	 */
	public String getFukuriKouseiClass() {
		return returnStyle(fukuriKousei);
	}
	/**
	 * @return gasClass を戻します。
	 */
	public String getGasClass() {
		return returnStyle(gas);
	}
	/**
	 * @return genkaShokyakuClass を戻します。
	 */
	public String getGenkaShokyakuClass() {
		return returnStyle(genkaShokyaku);
	}
	/**
	 * @return genzairyoKeiClass を戻します。
	 */
	public String getGenzairyoKeiClass() {
		return returnStyle(genzairyoKei);
	}
	/**
	 * @return genzairyoShireClass を戻します。
	 */
	public String getGenzairyoShireClass() {
		return returnStyle(genzairyoShire);
	}
	/**
	 * @return genzairyoZaikoClass を戻します。
	 */
	public String getGenzairyoZaikoClass() {
		return returnStyle(genzairyoZaiko);
	}
	/**
	 * @return hokenClass を戻します。
	 */
	public String getHokenClass() {
		return returnStyle(hoken);
	}
	/**
	 * @return honshahiHaiClass を戻します。
	 */
	public String getHonshahiHaiClass() {
		return returnStyle(honshahiHai);
	}
	/**
	 * @return houteiFukuriClass を戻します。
	 */
	public String getHouteiFukuriClass() {
		return returnStyle(houteiFukuri);
	}
	/**
	 * @return houzaiKeiClass を戻します。
	 */
	public String getHouzaiKeiClass() {
		return returnStyle(houzaiKei);
	}
	/**
	 * @return houzaiShireClass を戻します。
	 */
	public String getHouzaiShireClass() {
		return returnStyle(houzaiShire);
	}
	/**
	 * @return houzaiZaikoClass を戻します。
	 */
	public String getHouzaiZaikoClass() {
		return returnStyle(houzaiZaiko);
	}
	/**
	 * @return kappuDownClass を戻します。
	 */
	public String getKappuDownClass() {
		return returnStyle(kappuDown);
	}
	/**
	 * @return kappuUpClass を戻します。
	 */
	public String getKappuUpClass() {
		return returnStyle(kappuUp);
	}
	/**
	 * @return kappuZandakaClass を戻します。
	 */
	public String getKappuZandakaClass() {
		return returnStyle(kappuZandaka);
	}
	/**
	 * @return kashiireDownClass を戻します。
	 */
	public String getKashiireDownClass() {
		return returnStyle(kashiireDown);
	}
	/**
	 * @return kashiireUpClass を戻します。
	 */
	public String getKashiireUpClass() {
		return returnStyle(kashiireUp);
	}
	/**
	 * @return kashiireZandakaClass を戻します。
	 */
	public String getKashiireZandakaClass() {
		return returnStyle(kashiireZandaka);
	}
	/**
	 * @return keihiShokeiClass を戻します。
	 */
	public String getKeihiShokeiClass() {
		return returnStyle(keihiShokei);
	}
	/**
	 * @return koukokuClass を戻します。
	 */
	public String getKoukokuClass() {
		return returnStyle(koukoku);
	}
	/**
	 * @return kousaiClass を戻します。
	 */
	public String getKousaiClass() {
		return returnStyle(kousai);
	}
	/**
	 * @return leaseClass を戻します。
	 */
	public String getLeaseClass() {
		return returnStyle(lease);
	}
	/**
	 * @return leaseDownClass を戻します。
	 */
	public String getLeaseDownClass() {
		return returnStyle(leaseDown);
	}
	/**
	 * @return leaseUpClass を戻します。
	 */
	public String getLeaseUpClass() {
		return returnStyle(leaseUp);
	}
	/**
	 * @return leaseZandakaClass を戻します。
	 */
	public String getLeaseZandakaClass() {
		return returnStyle(leaseZandaka);
	}
	/**
	 * @return retireKeiClass を戻します。
	 */
	public String getRetireKeiClass() {
		return returnStyle(retireKei);
	}
	/**
	 * @return riekiClass を戻します。
	 */
	public String getRiekiClass() {
		return returnStyle(rieki);
	}
	/**
	 * @return royaltyClass を戻します。
	 */
	public String getRoyaltyClass() {
		return returnStyle(royalty);
	}
	/**
	 * @return ryohiClass を戻します。
	 */
	public String getRyohiClass() {
		return returnStyle(ryohi);
	}
	/**
	 * @return salaryBonusClass を戻します。
	 */
	public String getSalaryBonusClass() {
		return returnStyle(salaryBonus);
	}
	/**
	 * @return salaryClass を戻します。
	 */
	public String getSalaryClass() {
		return returnStyle(salary);
	}
	/**
	 * @return salaryKeiClass を戻します。
	 */
	public String getSalaryKeiClass() {
		return returnStyle(salaryKei);
	}
	/**
	 * @return salaryRetireClass を戻します。
	 */
	public String getSalaryRetireClass() {
		return returnStyle(salaryRetire);
	}
	/**
	 * @return salarySalaryClass を戻します。
	 */
	public String getSalarySalaryClass() {
		return returnStyle(salarySalary);
	}
	/**
	 * @return sharyoClass を戻します。
	 */
	public String getSharyoClass() {
		return returnStyle(sharyo);
	}
	/**
	 * @return shokyakuRiekiClass を戻します。
	 */
	public String getShokyakuRiekiClass() {
		return returnStyle(shokyakuRieki);
	}
	/**
	 * @return shoumouClass を戻します。
	 */
	public String getShoumouClass() {
		return returnStyle(shoumou);
	}
	/**
	 * @return shuzenClass を戻します。
	 */
	public String getShuzenClass() {
		return returnStyle(shuzen);
	}
	/**
	 * @return sonotaClass を戻します。
	 */
	public String getSonotaClass() {
		return returnStyle(sonota);
	}
	/**
	 * @return sozeiClass を戻します。
	 */
	public String getSozeiClass() {
		return returnStyle(sozei);
	}
	/**
	 * @return suikouHiClass を戻します。
	 */
	public String getSuikouHiClass() {
		return returnStyle(suikouHi);
	}
	/**
	 * @return tesuryoClass を戻します。
	 */
	public String getTesuryoClass() {
		return returnStyle(tesuryo);
	}
	/**
	 * @return tusinClass を戻します。
	 */
	public String getTusinClass() {
		return returnStyle(tusin);
	}
	/**
	 * @return unchinClass を戻します。
	 */
	public String getUnchinClass() {
		return returnStyle(unchin);
	}
	/**
	 * @return uriageClass を戻します。
	 */
	public String getUriageClass() {
		return returnStyle(uriage);
	}
	/**
	 * @return uriagedakaClass を戻します。
	 */
	public String getUriagedakaClass() {
		return returnStyle(uriagedaka);
	}
	/**
	 * @return uriagegenkaClass を戻します。
	 */
	public String getUriagegenkaClass() {
		return returnStyle(uriagegenka);
	}
	/**
	 * @return uriageSoRiekiClass を戻します。
	 */
	public String getUriageSoRiekiClass() {
		return returnStyle(uriageSoRieki);
	}
	/**
	 * @return waterClass を戻します。
	 */
	public String getWaterClass() {
		return returnStyle(water);
	}
	/**
	 * @return yachinClass を戻します。
	 */
	public String getYachinClass() {
		return returnStyle(yachin);
	}
	/**
	 * @return yakuinBonusClass を戻します。
	 */
	public String getYakuinBonusClass() {
		return returnStyle(yakuinBonus);
	}
	/**
	 * @return yakuinKeiClass を戻します。
	 */
	public String getYakuinKeiClass() {
		return returnStyle(yakuinKei);
	}
	/**
	 * @return yakuinRetireClass を戻します。
	 */
	public String getYakuinRetireClass() {
		return returnStyle(yakuinRetire);
	}
	/**
	 * @return yakuinSalaryClass を戻します。
	 */
	public String getYakuinSalaryClass() {
		return returnStyle(yakuinSalary);
	}
	/**
	 * @return yasaiKeiClass を戻します。
	 */
	public String getYasaiKeiClass() {
		return returnStyle(yasaiKei);
	}
	/**
	 * @return yasaiShireClass を戻します。
	 */
	public String getYasaiShireClass() {
		return returnStyle(yasaiShire);
	}
	/**
	 * @return yasaiZaikoClass を戻します。
	 */
	public String getYasaiZaikoClass() {
		return returnStyle(yasaiZaiko);
	}
	/**
	 * @return yobiClass を戻します。
	 */
	public String getYobiClass() {
		return returnStyle(yobi);
	}
	/**
	 * @return zakkyuBonusClass を戻します。
	 */
	public String getZakkyuBonusClass() {
		return returnStyle(zakkyuBonus);
	}
	/**
	 * @return zakkyuKeiClass を戻します。
	 */
	public String getZakkyuKeiClass() {
		return returnStyle(zakkyuKei);
	}
	/**
	 * @return zakkyuRetireClass を戻します。
	 */
	public String getZakkyuRetireClass() {
		return returnStyle(zakkyuRetire);
	}
	/**
	 * @return zakkyuSalaryClass を戻します。
	 */
	public String getZakkyuSalaryClass() {
		return returnStyle(zakkyuSalary);
	}
	/**
	 * @return zappiClass を戻します。
	 */
	public String getZappiClass() {
		return returnStyle(zappi);
	}

	/**
	 * スタイルを判定する（値がマイナス値以外は正常）
	 * @param val
	 * @return
	 */
	private String returnStyle(BigDecimal val) {
		boolean isPlus = true;
		if (val != null && (val.compareTo(new BigDecimal("0")) < 0)) {
			isPlus = false;
		}
		return (isPlus) ? CLASSNAME_NORMAL : CLASSNAME_MINUS;
	}
}
