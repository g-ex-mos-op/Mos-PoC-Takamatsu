package jp.co.isid.mos.bird.storeinfo.miselumpextract.entity;

public class MstInsentive {
    
    public static final String TABLE = "BM26INSR";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String closeDt_COLUMN = "CLOSE_DT";
    
    public static final String insentive1_COLUMN = "INSENTIVE_1";
    
    public static final String insentiveName1_COLUMN = "INSENTIVE_NAME_1";
    
    public static final String insentive1Sta_COLUMN = "INSENTIVE_1_STA";
    
    public static final String insentive1End_COLUMN = "INSENTIVE_1_END";
    
    public static final String insentive1Note_COLUMN = "INSENTIVE_1_NOTE";
    
    public static final String insentive2_COLUMN = "INSENTIVE_2";
    
    public static final String insentiveName2_COLUMN = "INSENTIVE_NAME_2";
    
    public static final String insentive2Sta_COLUMN = "INSENTIVE_2_STA";
    
    public static final String insentive2End_COLUMN = "INSENTIVE_2_END";
    
    public static final String insentive2Note_COLUMN = "INSENTIVE_2_NOTE";
    
    public static final String insentive3_COLUMN = "INSENTIVE_3";
    
    public static final String insentiveName3_COLUMN = "INSENTIVE_NAME_3";
    
    public static final String insentive3Sta_COLUMN = "INSENTIVE_3_STA";
    
    public static final String insentive3End_COLUMN = "INSENTIVE_3_END";
    
    public static final String insentive3Note_COLUMN = "INSENTIVE_3_NOTE";
    
    public static final String insentive4_COLUMN = "INSENTIVE_4";
    
    public static final String insentiveName4_COLUMN = "INSENTIVE_NAME_4";
    
    public static final String insentive4Sta_COLUMN = "INSENTIVE_4_STA";
    
    public static final String insentive4End_COLUMN = "INSENTIVE_4_END";
    
    public static final String insentive4Note_COLUMN = "INSENTIVE_4_NOTE";
    
    public static final String insentive5_COLUMN = "INSENTIVE_5";
    
    public static final String insentiveName5_COLUMN = "INSENTIVE_NAME_5";
    
    public static final String insentive5Sta_COLUMN = "INSENTIVE_5_STA";
    
    public static final String insentive5End_COLUMN = "INSENTIVE_5_END";
    
    public static final String insentive5Note_COLUMN = "INSENTIVE_5_NOTE";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
    public static final String blockCd_COLUMN = "BLOCK_CD";
    
    public static final String blockName_COLUMN = "BLOCK_NAME";
    
    /**
     * 管理会社コード企業コード
     */
    private String companyCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称（漢字）
     */
    private String miseNameKj;
    
    /**
     * 店クローズ日
     */
    private String closeDt;
    
    /**
     * インセンティブCD1
     */
    private String insentive1;
    
    /**
     * インセンティブ名称1
     */
    private String insentiveName1;
    
    /**
     * インセンティブ開始日1
     */
    private String insentive1Sta;
    
    /**
     * インセンティブ終了日1
     */
    private String insentive1End;
    
    /**
     * インセンティブ備考1
     */
    private String insentive1Note;
    
    /**
     * インセンティブCD2
     */
    private String insentive2;
    
    /**
     * インセンティブ名称2
     */
    private String insentiveName2;
    
    /**
     * インセンティブ開始日2
     */
    private String insentive2Sta;
    
    /**
     * インセンティブ終了日2
     */
    private String insentive2End;
    
    /**
     * インセンティブ備考2
     */
    private String insentive2Note;
    
    /**
     * インセンティブCD3
     */
    private String insentive3;
    
    /**
     * インセンティブ名称3
     */
    private String insentiveName3;
    
    /**
     * インセンティブ開始日3
     */
    private String insentive3Sta;
    
    /**
     * インセンティブ終了日3
     */
    private String insentive3End;
    
    /**
     * インセンティブ備考3
     */
    private String insentive3Note;
    
    /**
     * インセンティブCD4
     */
    private String insentive4;
    
    /**
     * インセンティブ名称4
     */
    private String insentiveName4;
    
    /**
     * インセンティブ開始日4
     */
    private String insentive4Sta;
    
    /**
     * インセンティブ終了日4
     */
    private String insentive4End;
    
    /**
     * インセンティブ備考4
     */
    private String insentive4Note;
    
    /**
     * インセンティブCD5
     */
    private String insentive5;
    
    /**
     * インセンティブ名称5
     */
    private String insentiveName5;
    
    /**
     * インセンティブ開始日5
     */
    private String insentive5Sta;
    
    /**
     * インセンティブ終了日5
     */
    private String insentive5End;
    
    /**
     * インセンティブ備考5
     */
    private String insentive5Note;
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部名称
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
     * 管理会社コード企業コードを取得します。
     * @return 管理会社コード企業コード
     */
    public String getCompanyCd() {
        return convString(companyCd);
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
    
    /**
     * 店名称（漢字）を取得します。
     * @return 店名称（漢字）
     */
    public String getMiseNameKj() {
        return convString(miseNameKj);
    }
    /**
     * 店名称（漢字）を設定します。
     * @param miseNameKj 店名称（漢字）
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * 店クローズ日を取得します。
     * @return 店クローズ日
     */
    public String getCloseDt() {
        return convString(closeDt);
    }
    /**
     * 店クローズ日を設定します。
     * @param closeDt 店クローズ日
     */
    public void setCloseDt(String closeDt) {
        this.closeDt = closeDt;
    }
    
    /**
     * インセンティブCD1を取得します。
     * @return インセンティブCD1
     */
    public String getInsentive1() {
        return convString(insentive1);
    }
    /**
     * インセンティブCD1を設定します。
     * @param insentive1 インセンティブCD1
     */
    public void setInsentive1(String insentive1) {
        this.insentive1 = insentive1;
    }
    
    /**
     * インセンティブ名称1を取得します。
     * @return インセンティブ名称1
     */
    public String getInsentiveName1() {
        return convString(insentiveName1);
    }
    /**
     * インセンティブ名称1を設定します。
     * @param insentiveName1 インセンティブ名称1
     */
    public void setInsentiveName1(String insentiveName1) {
        this.insentiveName1 = insentiveName1;
    }
    
    /**
     * インセンティブ開始日1を取得します。
     * @return インセンティブ開始日1
     */
    public String getInsentive1Sta() {
        return convString(insentive1Sta);
    }
    /**
     * インセンティブ開始日1を設定します。
     * @param insentive1Sta インセンティブ開始日1
     */
    public void setInsentive1Sta(String insentive1Sta) {
        this.insentive1Sta = insentive1Sta;
    }
    
    /**
     * インセンティブ終了日1を取得します。
     * @return インセンティブ終了日1
     */
    public String getInsentive1End() {
        return convString(insentive1End);
    }
    /**
     * インセンティブ終了日1を設定します。
     * @param insentive1End インセンティブ終了日1
     */
    public void setInsentive1End(String insentive1End) {
        this.insentive1End = insentive1End;
    }
    
    /**
     * インセンティブ備考1を取得します。
     * @return インセンティブ備考1
     */
    public String getInsentive1Note() {
        return convString(insentive1Note);
    }
    /**
     * インセンティブ備考1を設定します。
     * @param insentive1Note インセンティブ備考1
     */
    public void setInsentive1Note(String insentive1Note) {
        this.insentive1Note = insentive1Note;
    }
    
    /**
     * インセンティブCD2を取得します。
     * @return インセンティブCD2
     */
    public String getInsentive2() {
        return convString(insentive2);
    }
    /**
     * インセンティブCD2を設定します。
     * @param insentive2 インセンティブCD2
     */
    public void setInsentive2(String insentive2) {
        this.insentive2 = insentive2;
    }
    
    /**
     * インセンティブ名称2を取得します。
     * @return インセンティブ名称2
     */
    public String getInsentiveName2() {
        return convString(insentiveName2);
    }
    /**
     * インセンティブ名称2を設定します。
     * @param insentiveName2 インセンティブ名称2
     */
    public void setInsentiveName2(String insentiveName2) {
        this.insentiveName2 = insentiveName2;
    }
    
    /**
     * インセンティブ開始日2を取得します。
     * @return インセンティブ開始日2
     */
    public String getInsentive2Sta() {
        return convString(insentive2Sta);
    }
    /**
     * インセンティブ開始日2を設定します。
     * @param insentive2Sta インセンティブ開始日2
     */
    public void setInsentive2Sta(String insentive2Sta) {
        this.insentive2Sta = insentive2Sta;
    }
    
    /**
     * インセンティブ終了日2を取得します。
     * @return インセンティブ終了日2
     */
    public String getInsentive2End() {
        return convString(insentive2End);
    }
    /**
     * インセンティブ終了日2を設定します。
     * @param insentive2End インセンティブ終了日2
     */
    public void setInsentive2End(String insentive2End) {
        this.insentive2End = insentive2End;
    }
    
    /**
     * インセンティブ備考2を取得します。
     * @return インセンティブ備考2
     */
    public String getInsentive2Note() {
        return convString(insentive2Note);
    }
    /**
     * インセンティブ備考2を設定します。
     * @param insentive2Note インセンティブ備考2
     */
    public void setInsentive2Note(String insentive2Note) {
        this.insentive2Note = insentive2Note;
    }
    
    /**
     * インセンティブCD3を取得します。
     * @return インセンティブCD3
     */
    public String getInsentive3() {
        return convString(insentive3);
    }
    /**
     * インセンティブCD3を設定します。
     * @param insentive3 インセンティブCD3
     */
    public void setInsentive3(String insentive3) {
        this.insentive3 = insentive3;
    }
    
    /**
     * インセンティブ名称3を取得します。
     * @return インセンティブ名称3
     */
    public String getInsentiveName3() {
        return convString(insentiveName3);
    }
    /**
     * インセンティブ名称3を設定します。
     * @param insentiveName3 インセンティブ名称3
     */
    public void setInsentiveName3(String insentiveName3) {
        this.insentiveName3 = insentiveName3;
    }
    
    /**
     * インセンティブ開始日3を取得します。
     * @return インセンティブ開始日3
     */
    public String getInsentive3Sta() {
        return convString(insentive3Sta);
    }
    /**
     * インセンティブ開始日3を設定します。
     * @param insentive3Sta インセンティブ開始日3
     */
    public void setInsentive3Sta(String insentive3Sta) {
        this.insentive3Sta = insentive3Sta;
    }
    
    /**
     * インセンティブ終了日3を取得します。
     * @return インセンティブ終了日3
     */
    public String getInsentive3End() {
        return convString(insentive3End);
    }
    /**
     * インセンティブ終了日3を設定します。
     * @param insentive3End インセンティブ終了日3
     */
    public void setInsentive3End(String insentive3End) {
        this.insentive3End = insentive3End;
    }
    
    /**
     * インセンティブ備考3を取得します。
     * @return インセンティブ備考3
     */
    public String getInsentive3Note() {
        return convString(insentive3Note);
    }
    /**
     * インセンティブ備考3を設定します。
     * @param insentive3Note インセンティブ備考3
     */
    public void setInsentive3Note(String insentive3Note) {
        this.insentive3Note = insentive3Note;
    }
    
    /**
     * インセンティブCD4を取得します。
     * @return インセンティブCD4
     */
    public String getInsentive4() {
        return convString(insentive4);
    }
    /**
     * インセンティブCD4を設定します。
     * @param insentive4 インセンティブCD4
     */
    public void setInsentive4(String insentive4) {
        this.insentive4 = insentive4;
    }
    
    /**
     * インセンティブ名称4を取得します。
     * @return インセンティブ名称4
     */
    public String getInsentiveName4() {
        return convString(insentiveName4);
    }
    /**
     * インセンティブ名称4を設定します。
     * @param insentiveName4 インセンティブ名称4
     */
    public void setInsentiveName4(String insentiveName4) {
        this.insentiveName4 = insentiveName4;
    }
    
    /**
     * インセンティブ開始日4を取得します。
     * @return インセンティブ開始日4
     */
    public String getInsentive4Sta() {
        return convString(insentive4Sta);
    }
    /**
     * インセンティブ開始日4を設定します。
     * @param insentive4Sta インセンティブ開始日4
     */
    public void setInsentive4Sta(String insentive4Sta) {
        this.insentive4Sta = insentive4Sta;
    }
    
    /**
     * インセンティブ終了日4を取得します。
     * @return インセンティブ終了日4
     */
    public String getInsentive4End() {
        return convString(insentive4End);
    }
    /**
     * インセンティブ終了日4を設定します。
     * @param insentive4End インセンティブ終了日4
     */
    public void setInsentive4End(String insentive4End) {
        this.insentive4End = insentive4End;
    }
    
    /**
     * インセンティブ備考4を取得します。
     * @return インセンティブ備考4
     */
    public String getInsentive4Note() {
        return convString(insentive4Note);
    }
    /**
     * インセンティブ備考4を設定します。
     * @param insentive4Note インセンティブ備考4
     */
    public void setInsentive4Note(String insentive4Note) {
        this.insentive4Note = insentive4Note;
    }
    
    /**
     * インセンティブCD5を取得します。
     * @return インセンティブCD5
     */
    public String getInsentive5() {
        return convString(insentive5);
    }
    /**
     * インセンティブCD5を設定します。
     * @param insentive5 インセンティブCD5
     */
    public void setInsentive5(String insentive5) {
        this.insentive5 = insentive5;
    }
    
    /**
     * インセンティブ名称5を取得します。
     * @return インセンティブ名称5
     */
    public String getInsentiveName5() {
        return convString(insentiveName5);
    }
    /**
     * インセンティブ名称5を設定します。
     * @param insentiveName5 インセンティブ名称5
     */
    public void setInsentiveName5(String insentiveName5) {
        this.insentiveName5 = insentiveName5;
    }
    
    /**
     * インセンティブ開始日5を取得します。
     * @return インセンティブ開始日5
     */
    public String getInsentive5Sta() {
        return convString(insentive5Sta);
    }
    /**
     * インセンティブ開始日5を設定します。
     * @param insentive5Sta インセンティブ開始日5
     */
    public void setInsentive5Sta(String insentive5Sta) {
        this.insentive5Sta = insentive5Sta;
    }
    
    /**
     * インセンティブ終了日5を取得します。
     * @return インセンティブ終了日5
     */
    public String getInsentive5End() {
        return convString(insentive5End);
    }
    /**
     * インセンティブ終了日5を設定します。
     * @param insentive5End インセンティブ終了日5
     */
    public void setInsentive5End(String insentive5End) {
        this.insentive5End = insentive5End;
    }
    
    /**
     * インセンティブ備考5を取得します。
     * @return インセンティブ備考5
     */
    public String getInsentive5Note() {
        return convString(insentive5Note);
    }
    /**
     * インセンティブ備考5を設定します。
     * @param insentive5Note インセンティブ備考5
     */
    public void setInsentive5Note(String insentive5Note) {
        this.insentive5Note = insentive5Note;
    }
    /**
     * 支部コードを取得します。
     * @return 支部コード
     */
    public String getSibuCd() {
        return convString(sibuCd);
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
        return convString(sibuName);
    }
    /**
     * 支部名称を設定します。
     * @param sibuName 支部名称
     */
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
    }
    
    /**
     * ブロックコードを取得します。
     * @return ブロックコード
     */
    public String getBlockCd() {
        return convString(blockCd);
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
        return convString(blockName);
    }
    /**
     * ブロック名称を設定します。
     * @param blockName ブロック名称
     */
    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }
    
    private String convString(String str) {
        String ret = str;
        if (str == null) {
            ret = "";
        }
        ret = ret.trim();
        return ret;
    }
}
