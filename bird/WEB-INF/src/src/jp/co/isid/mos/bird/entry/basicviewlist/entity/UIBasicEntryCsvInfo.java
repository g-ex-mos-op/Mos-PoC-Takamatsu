package jp.co.isid.mos.bird.entry.basicviewlist.entity;

public class UIBasicEntryCsvInfo {

    public static final String TABLE = "BT22ENKN";
    
    public static final String entryCd_COLUMN = "ENTRY_CD";//エントリーコード

    public static final String entryYear_COLUMN = "ENTRY_YEAR";//エントリー年
    
    public static final String entryKai_COLUMN = "ENTRY_KAI";//エントリー回
    
    public static final String onerCd_COLUMN = "ONER_CD";//オーナーコード
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";//オーナー名称
    
    public static final String sibuCd_COLUMN = "SIBU_CD";//支部コード
    
    public static final String sibuName_COLUMN = "SIBU_NAME";//支部名称

//---20070116 マスタライセンスPh4対応 ---start---
    public static final String sibuTorikomiCd_COLUMN = "TORIKOMI_CD"; //支部取込コード
    
    public static final String sibuTorikomiName_COLUMN = "TORIKOMI_NAME";//支部取込コードの名称
//---20070116 マスタライセンスPh4対応 --- end ---

    
    public static final String miseCd_COLUMN = "MISE_CD";//店コード
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";//店名称
    
    public static final String sekininshaName_COLUMN = "SEKININSHA_NAME";//申込責任者名称
    
    public static final String sekininshaJob_COLUMN = "SEKININSHA_JOB";//申込責任者職位
    
    public static final String staffSiKj_COLUMN = "STAFF_SI_KJ";//スタッフ氏(漢字)
    
    public static final String staffNaKj_COLUMN = "STAFF_NA_KJ";//スタッフ名(漢字)
    
    public static final String staffSiKna_COLUMN = "STAFF_SI_KNA";//スタッフ氏(フリガナ)
    
    public static final String staffNaKna_COLUMN = "STAFF_NA_KNA";//スタッフ名(フリガナ)
    
    public static final String sex_COLUMN = "SEX";//性別
    
    public static final String birthday_COLUMN = "BIRTHDAY";//生年月日
    
    public static final String job_COLUMN = "JOB";//職位
    
    public static final String kekkaSakiName_COLUMN = "KEKKA_SAKI_NAME";//結果報告先名称
    
    public static final String kekkaSakiZip_COLUMN = "KEKKA_SAKI_ZIP";//結果報告先郵便番号
    
    public static final String kekkaSakiAddress1_COLUMN = "KEKKA_SAKI_ADDRESS1";//結果報告先住所1
    
    public static final String kekkaSakiAddress2_COLUMN = "KEKKA_SAKI_ADDRESS2";//結果報告先住所2
    
    public static final String kekkaSakiAddress3_COLUMN = "KEKKA_SAKI_ADDRESS3";//結果報告先住所3
    
    public static final String empExpYear_COLUMN = "EMP_EXP_YEAR";//店舗経験 社員歴年数
    
    public static final String empExpMonth_COLUMN = "EMP_EXP_MONTH";//店舗経験 社員歴月数
    
    public static final String paExpYear_COLUMN = "PA_EXP_YEAR";//店舗経験 パート・アルバイト歴年数
    
    public static final String paExpMonth_COLUMN = "PA_EXP_MONTH";//店舗経験 パート・アルバイト歴月数
    
    public static final String guideKbn_COLUMN = "GUIDE_KBN";//受講案内送付先区分
    
    public static final String guideName_COLUMN = "GUIDE_NAME";//受講案内送付先名称
    
    public static final String guideZip_COLUMN = "GUIDE_ZIP";//受講案内送付先郵便番号
    
    public static final String guideAdrs1_COLUMN = "GUIDE_ADRS1";//受講案内送付先住所1
    
    public static final String guideAdrs2_COLUMN = "GUIDE_ADRS2";//受講案内送付先住所2
    
    public static final String guideAdrs3_COLUMN = "GUIDE_ADRS3";//受講案内送付先住所3
    
    public static final String otherFlg1_COLUMN = "OTHER_FLG1";//臨店実習コース
    
    public static final String otherFlg2_COLUMN = "OTHER_FLG2";//宿泊先タイプ
    
    public static final String otherFlg3_COLUMN = "OTHER_FLG3";//面接
    
    public static final String bossName_COLUMN = "BOSS_NAME";//上司名称
    
    public static final String bossGroup_COLUMN = "BOSS_GROUP";//上司所属
    
    public static final String bossJobType_COLUMN = "BOSS_JOB_TYPE";//上司職位
    
    public static final String bossComment_COLUMN = "BOSS_COMMENT";//上司コメント
    
    public static final String lastUser_COLUMN = "LAST_USER";//更新者ID
    
    public static final String lastUserName_COLUMN = "LAST_USER_NAME";//更新者名称
    
    public static final String keiyakuEnd_COLUMN = "KEIYAKU_END";//オーナー契約終了日
 

    
    /**
     * エントリーコード
     */
    private String entryCd;
    
    /**
     * エントリー年
     */
    private String entryYear;
    
    /**
     * エントリー回
     */
    private String entryKai;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * オーナー名称
     */
    private String onerNameKj;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部名称
     */
    private String sibuName;

//---20070116 マスタライセンスPh4対応 ---start---
    /**
     * 支部取込コード
     */
    private String sibuTorikomiCd;
    
    /**
     * 支部取込名称
     */
    private String sibuTorikomiName;
//---20070116 マスタライセンスPh4対応 --- end ---

    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称
     */
    private String miseNameKj;
    
    /**
     * 申込責任者名称
     */
    private String sekininshaName;
    
    /**
     * 申込責任者職位
     */
    private String sekininshaJob;
    
    /**
     * スタッフ氏(漢字)
     */
    private String staffSiKj;
    
    /**
     * スタッフ名(漢字)
     */
    private String staffNaKj;
    
    /**
     * スタッフ氏(フリガナ)
     */
    private String staffSiKna;
    
    /**
     * スタッフ名(フリガナ)
     */
    private String staffNaKna;
    
    /**
     * 性別
     */
    private String sex;
    
    /**
     * 生年月日
     */
    private String birthday;
    
    /**
     * 職位
     */
    private String job;
    
    /**
     * 結果報告先名称
     */
    private String kekkaSakiName;
    
    /**
     * 結果報告先郵便番号
     */
    private String kekkaSakiZip;
    
    /**
     * 結果報告先住所1
     */
    private String kekkaSakiAddress1;
    
    /**
     * 結果報告先住所2
     */
    private String kekkaSakiAddress2;
    
    /**
     * 結果報告先住所3
     */
    private String kekkaSakiAddress3;
    
    /**
     * 店舗経験 社員歴年数
     */
    private String empExpYear;
    
    /**
     * 店舗経験 社員歴月数
     */
    private String empExpMonth;
    
    /**
     * 店舗経験 パート・アルバイト歴年数
     */
    private String paExpYear;
    
    /**
     * 店舗経験 パート・アルバイト歴月数
     */
    private String paExpMonth;
    
    /**
     * 受講案内送付先区分
     */
    private String guideKbn;
    
    /**
     * 受講案内送付先名称
     */
    private String guideName;
    
    /**
     * 受講案内送付先郵便番号
     */
    private String guideZip;
    
    /**
     * 受講案内送付先住所1
     */
    private String guideAdrs1;
    
    /**
     * 受講案内送付先住所2
     */
    private String guideAdrs2;
    
    /**
     * 受講案内送付先住所3
     */
    private String guideAdrs3;

    /**
     * 臨店実習コース
     */
    private String otherFlg1;
    
    /**
     * 宿泊先タイプ
     */
    private String otherFlg2;
    
    /**
     * 面接
     */
    private String otherFlg3;
    
    /**
     * 上司名称
     */
    private String bossName;
    
    /**
     * 上司所属
     */
    private String bossGroup;
    
    /**
     * 上司職位
     */
    private String bossJobType;
    
    /**
     * 上司コメント
     */
    private String bossComment;
    
    /**
     * 更新者ID
     */
    private String lastUser;
    
    /**
     * 更新者名称
     */
    private String lastUserName;
    
    /**
     * オーナー契約終了日
     */
    private String keiyakuEnd;
    
 

    
    
    /**
     * エントリーコードを取得します。
     * @return エントリーコード
     */
    public String getEntryCd() {
        return entryCd;
    }
    /**
     * エントリーコードを設定します。
     * @param entryCd エントリーコード
     */
    public void setEntryCd(String entryCd) {
        this.entryCd = entryCd;
    }
    
    /**
     * エントリー年を取得します。
     * @return エントリー年
     */
    public String getEntryYear() {
        return entryYear;
    }
    /**
     * エントリー年を設定します。
     * @param entryYear エントリー年
     */
    public void setEntryYear(String entryYear) {
        this.entryYear = entryYear;
    }
    
    /**
     * エントリー回を取得します。
     * @return エントリー回
     */
    public String getEntryKai() {
        return entryKai;
    }
    /**
     * エントリー回を設定します。
     * @param entryKai エントリー回
     */
    public void setEntryKai(String entryKai) {
        this.entryKai = entryKai;
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
     * 支部名称を取得します。
     * @return 支部名称
     */
    public String getSibuName() {
        return sibuName;
    }
    /**
     * 支部名称を設定します。
     * @param sibuName 支部名称
     */
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
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
     * 申込責任者名称を取得します。
     * @return 申込責任者名称
     */
    public String getSekininshaName() {
        return sekininshaName;
    }
    /**
     * 申込責任者名称を設定します。
     * @param sekininshaName 申込責任者名称
     */
    public void setSekininshaName(String sekininshaName) {
        this.sekininshaName = sekininshaName;
    }
    
    /**
     * 申込責任者職位を取得します。
     * @return 申込責任者職位
     */
    public String getSekininshaJob() {
        return sekininshaJob;
    }
    /**
     * 申込責任者職位を設定します。
     * @param sekininshaJob 申込責任者職位
     */
    public void setSekininshaJob(String sekininshaJob) {
        this.sekininshaJob = sekininshaJob;
    }
    
    /**
     * スタッフ氏(漢字)を取得します。
     * @return スタッフ氏(漢字)
     */
    public String getStaffSiKj() {
        return staffSiKj;
    }
    /**
     * スタッフ氏(漢字)を設定します。
     * @param staffSiKj スタッフ氏(漢字)
     */
    public void setStaffSiKj(String staffSiKj) {
        this.staffSiKj = staffSiKj;
    }
    
    /**
     * スタッフ名(漢字)を取得します。
     * @return スタッフ名(漢字)
     */
    public String getStaffNaKj() {
        return staffNaKj;
    }
    /**
     * スタッフ名(漢字)を設定します。
     * @param staffNaKj スタッフ名(漢字)
     */
    public void setStaffNaKj(String staffNaKj) {
        this.staffNaKj = staffNaKj;
    }
    
    /**
     * スタッフ氏(フリガナ)を取得します。
     * @return スタッフ氏(フリガナ)
     */
    public String getStaffSiKna() {
        return staffSiKna;
    }
    /**
     * スタッフ氏(フリガナ)を設定します。
     * @param staffSiKna スタッフ氏(フリガナ)
     */
    public void setStaffSiKna(String staffSiKna) {
        this.staffSiKna = staffSiKna;
    }
  
    /**
     * スタッフ名(フリガナ)を取得します。
     * @return スタッフ名(フリガナ)
     */
    public String getStaffNaKna() {
        return staffNaKna;
    }
    /**
     * スタッフ名(フリガナ)を設定します。
     * @param staffNaKna スタッフ名(フリガナ)
     */
    public void setStaffNaKna(String staffNaKna) {
        this.staffNaKna = staffNaKna;
    }
    
    /**
     * 性別を取得します。
     * @return 性別
     */
    public String getSex() {
        return sex;
    }
    /**
     * 性別を設定します。
     * @param sex 性別
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 生年月日を取得します。
     * @return 生年月日
     */
    public String getBirthday() {
        return birthday;
    }
    /**
     * 生年月日を設定します。
     * @param birthday 生年月日
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * 職位を取得します。
     * @return 職位
     */
    public String getJob() {
        return job;
    }
    /**
     * 職位を設定します。
     * @param job 職位
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * 結果報告先名称を取得します。
     * @return 結果報告先名称
     */
    public String getKekkaSakiName() {
        return kekkaSakiName;
    }
    /**
     * 結果報告先名称を設定します。
     * @param kekkaSakiName 結果報告先名称
     */
    public void setKekkaSakiName(String kekkaSakiName) {
        this.kekkaSakiName = kekkaSakiName;
    }

    /**
     * 結果報告先郵便番号を取得します。
     * @return 結果報告先郵便番号
     */
    public String getKekkaSakiZip() {
        return kekkaSakiZip;
    }
    /**
     * 結果報告先郵便番号を設定します。
     * @param kekkaSakiZip 結果報告先郵便番号
     */
    public void setKekkaSakiZip(String kekkaSakiZip) {
        this.kekkaSakiZip = kekkaSakiZip;
    }

    /**
     * 結果報告先住所1を取得します。
     * @return 結果報告先住所
     */
    public String getKekkaSakiAddress1() {
        return kekkaSakiAddress1;
    }
    /**
     * 結果報告先住所1を設定します。
     * @param kekkaSakiAddress1 結果報告先住所1
     */
    public void setKekkaSakiAddress1(String kekkaSakiAddress1) {
        this.kekkaSakiAddress1 = kekkaSakiAddress1;
    }

    /**
     * 結果報告先住所2を取得します。
     * @return 結果報告先住所2
     */
    public String getKekkaSakiAddress2() {
        return kekkaSakiAddress2;
    }
    /**
     * 結果報告先住所2を設定します。
     * @param kekkaSakiAddress2 結果報告先住所2
     */
    public void setKekkaSakiAddress2(String kekkaSakiAddress2) {
        this.kekkaSakiAddress2 = kekkaSakiAddress2;
    }
    
    /**
     * 結果報告先住所3を取得します。
     * @return 結果報告先住所3
     */
    public String getKekkaSakiAddress3() {
        return kekkaSakiAddress3;
    }
    /**
     * 結果報告先住所3を設定します。
     * @param kekkaSakiAddress3 結果報告先住所3
     */
    public void setKekkaSakiAddress3(String kekkaSakiAddress3) {
        this.kekkaSakiAddress3 = kekkaSakiAddress3;
    }
    
    /**
     * 店舗経験 社員歴年数を取得します。
     * @return 店舗経験 社員歴年数
     */
    public String getEmpExpYear() {
        return empExpYear;
    }
    /**
     * 店舗経験 社員歴年数を設定します。
     * @param empExpYear 店舗経験 社員歴年数
     */
    public void setEmpExpYear(String empExpYear) {
        this.empExpYear = empExpYear;
    }
    
    /**
     * 店舗経験 社員歴月数を取得します。
     * @return 店舗経験 社員歴月数
     */
    public String getEmpExpMonth() {
        return empExpMonth;
    }
    /**
     * 店舗経験 社員歴月数を設定します。
     * @param empExpMonth 店舗経験 社員歴月数
     */
    public void setEmpExpMonth(String empExpMonth) {
        this.empExpMonth = empExpMonth;
    }
    
    /**
     * 店舗経験 パート・アルバイト歴年数を取得します。
     * @return 店舗経験 パート・アルバイト歴年数
     */
    public String getPaExpYear() {
        return paExpYear;
    }
    /**
     * 店舗経験 パート・アルバイト歴年数を設定します。
     * @param paExpYear 店舗経験 パート・アルバイト歴年数
     */
    public void setPaExpYear(String paExpYear) {
        this.paExpYear = paExpYear;
    }

    /**
     * 店舗経験 パート・アルバイト歴月数を取得します。
     * @return 店舗経験 パート・アルバイト歴月数
     */
    public String getPaExpMonth() {
        return paExpMonth;
    }
    /**
     * 店舗経験 パート・アルバイト歴月数を設定します。
     * @param paExpMonth 店舗経験 パート・アルバイト歴月数
     */
    public void setPaExpMonth(String paExpMonth) {
        this.paExpMonth = paExpMonth;
    }

    /**
     * 受講案内送付先区分を取得します。
     * @return 受講案内送付先区分
     */
    public String getGuideKbn() {
        return guideKbn;
    }
    /**
     * 受講案内送付先区分を設定します。
     * @param guideKbn 受講案内送付先区分
     */
    public void setGuideKbn(String guideKbn) {
        this.guideKbn = guideKbn;
    }
    
    /**
     * 受講案内送付先名称を取得します。
     * @return 受講案内送付先名称
     */
    public String getGuideName() {
        return guideName;
    }
    /**
     * 受講案内送付先名称を設定します。
     * @param guideName 受講案内送付先名称
     */
    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }
    
    /**
     * 受講案内送付先郵便番号を取得します。
     * @return 受講案内送付先郵便番号
     */
    public String getGuideZip() {
        return guideZip;
    }
    /**
     * 受講案内送付先郵便番号を設定します。
     * @param guideZip 受講案内送付先郵便番号
     */
    public void setGuideZip(String guideZip) {
        this.guideZip = guideZip;
    }
    
    /**
     * 受講案内送付先住所1を取得します。
     * @return 受講案内送付先住所1
     */
    public String getGuideAdrs1() {
        return guideAdrs1;
    }
    /**
     * 受講案内送付先住所1を設定します。
     * @param guideAdrs1 受講案内送付先住所1
     */
    public void setGuideAdrs1(String guideAdrs1) {
        this.guideAdrs1 = guideAdrs1;
    }
    
    /**
     * 受講案内送付先住所2を取得します。
     * @return 受講案内送付先住所2
     */
    public String getGuideAdrs2() {
        return guideAdrs2;
    }
    /**
     * 受講案内送付先住所2を設定します。
     * @param guideAdrs2 受講案内送付先住所2
     */
    public void setGuideAdrs2(String guideAdrs2) {
        this.guideAdrs2 = guideAdrs2;
    }
    
    /**
     * 受講案内送付先住所3を取得します。
     * @return 受講案内送付先住所3
     */
    public String getGuideAdrs3() {
        return guideAdrs3;
    }
    /**
     * 受講案内送付先住所3を設定します。
     * @param guideAdrs3 受講案内送付先住所3
     */
    public void setGuideAdrs3(String guideAdrs3) {
        this.guideAdrs3 = guideAdrs3;
    }
    
    /**
     * 臨店実習コースを取得します。
     * @return 臨店実習コース
     */
    public String getOtherFlg1() {
        return otherFlg1;
    }
    /**
     * 臨店実習コースを設定します。
     * @param otherFlg1 臨店実習コース
     */
    public void setOtherFlg1(String otherFlg1) {
        this.otherFlg1 = otherFlg1;
    }

    /**
     * 宿泊先タイプを取得します。
     * @return 宿泊先タイプ
     */
    public String getOtherFlg2() {
        return otherFlg2;
    }
    /**
     * 宿泊先タイプを設定します。
     * @param otherFlg2 宿泊先タイプ
     */
    public void setOtherFlg2(String otherFlg2) {
        this.otherFlg2 = otherFlg2;
    }

    /**
     * 面接を取得します。
     * @return 面接
     */
    public String getOtherFlg3() {
        return otherFlg3;
    }
    /**
     * 面接を設定します。
     * @param otherFlg3 面接
     */
    public void setOtherFlg3(String otherFlg3) {
        this.otherFlg3 = otherFlg3;
    }

    /**
     * 上司名称を取得します。
     * @return 上司名称
     */
    public String getBossName() {
        return bossName;
    }
    /**
     * 上司名称を設定します。
     * @param bossName 上司名称
     */
    public void setBossName(String bossName) {
        this.bossName = bossName;
    }

    /**
     * 上司所属を取得します。
     * @return 上司所属
     */
    public String getBossGroup() {
        return bossGroup;
    }
    /**
     * 上司所属を設定します。
     * @param bossGroup 上司所属
     */
    public void setBossGroup(String bossGroup) {
        this.bossGroup = bossGroup;
    }

    /**
     * 上司職位を取得します。
     * @return 上司職位
     */
    public String getBossJobType() {
        return bossJobType;
    }
    /**
     * 上司職位を設定します。
     * @param bossJobType 上司職位
     */
    public void setBossJobType(String bossJobType) {
        this.bossJobType = bossJobType;
    }

    /**
     * 上司コメントを取得します。
     * @return 上司コメント
     */
    public String getBossComment() {
        return bossComment;
    }
    /**
     * 上司コメントを設定します。
     * @param bossComment 上司コメント
     */
    public void setBossComment(String bossComment) {
        this.bossComment = bossComment;
    }

    /**
     * 更新者IDを取得します。
     * @return 更新者ID
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * 更新者IDを設定します。
     * @param lastUser 更新者ID
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }

    /**
     * 更新者名称を取得します。
     * @return 更新者名称
     */
    public String getLastUserName() {
        return lastUserName;
    }
    /**
     * 更新者名称を設定します。
     * @param lastUserName 更新者名称
     */
    public void setLastUserName(String lastUserName) {
        this.lastUserName = lastUserName;
    }

    /**
     * オーナー契約終了日を取得します。
     * @return オーナー契約終了日
     */
    public String getKeiyakuEnd() {
        return keiyakuEnd;
    }
    /**
     * オーナー契約終了日を設定します。
     * @param keiyakuEnd オーナー契約終了日
     */
    public void setKeiyakuEnd(String keiyakuEnd) {
        this.keiyakuEnd = keiyakuEnd;
    }
    /**
     * @return sibuTorikomiCd を戻します。
     */
    public String getSibuTorikomiCd() {
        return sibuTorikomiCd;
    }
    /**
     * @param sibuTorikomiCd 設定する sibuTorikomiCd。
     */
    public void setSibuTorikomiCd(String sibuTorikomiCd) {
        this.sibuTorikomiCd = sibuTorikomiCd;
    }
    /**
     * @return sibuTorikomiName を戻します。
     */
    public String getSibuTorikomiName() {
        return sibuTorikomiName;
    }
    /**
     * @param sibuTorikomiName 設定する sibuTorikomiName。
     */
    public void setSibuTorikomiName(String sibuTorikomiName) {
        this.sibuTorikomiName = sibuTorikomiName;
    }

}
