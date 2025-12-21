package jp.co.isid.mos.bird.analysis.kakouriage.entity;

import java.math.BigDecimal;

public class UIKakoUriage {
    
    public UIKakoUriage(String month) {
        this.month = month;
        this.rowHirituFlg = false;
    }
    public UIKakoUriage(String month, boolean hirituFlg) {
        this.month = month;
        this.rowHirituFlg = hirituFlg;
    }
    /**
     * 月
     */
    private String month;
    
    /**
     * 年度１
     */
    private BigDecimal nendo1 = new BigDecimal("0");
    
    /**
     * 年度２
     */
    private BigDecimal nendo2 = new BigDecimal("0");
    
    /**
     * 年度３
     */
    private BigDecimal nendo3 = new BigDecimal("0");
    
    /**
     * 年度４
     */
    private BigDecimal nendo4 = new BigDecimal("0");
    
    /**
     * 年度５
     */
    private BigDecimal nendo5 = new BigDecimal("0");
    
    /**
     * 年度６
     */
    private BigDecimal nendo6 = new BigDecimal("0");
    
    /**
     * 年度７
     */
    private BigDecimal nendo7 = new BigDecimal("0");
    
    /**
     * 年度１客数
     */
    private BigDecimal nendo1Kyakusu = new BigDecimal("0");
    
    /**
     * 年度２客数
     */
    private BigDecimal nendo2Kyakusu = new BigDecimal("0");
    
    /**
     * 年度３客数
     */
    private BigDecimal nendo3Kyakusu = new BigDecimal("0");
    
    /**
     * 年度４客数
     */
    private BigDecimal nendo4Kyakusu = new BigDecimal("0");
    
    /**
     * 年度５客数
     */
    private BigDecimal nendo5Kyakusu = new BigDecimal("0");
    
    /**
     * 年度６客数
     */
    private BigDecimal nendo6Kyakusu = new BigDecimal("0");
    
    /**
     * 年度７客数
     */
    private BigDecimal nendo7Kyakusu = new BigDecimal("0");
    
    /**
     * 比率行フラグ
     */
    private boolean rowHirituFlg = false;
    
    
    /**
     * 月を取得します。
     * @return 月
     */
    public String getMonth() {
        return month;
    }
    /**
     * 月を設定します。
     * @param month 月
     */
    public void setMonth(String month) {
        this.month = month;
    }
    
    /**
     * 年度１を取得します。
     * @return 年度１
     */
    public BigDecimal getNendo1() {
        return nendo1;
    }
    /**
     * 年度１を設定します。
     * @param nendo1 年度１
     */
    public void setNendo1(BigDecimal nendo1) {
        this.nendo1 = nendo1;
    }
    
    /**
     * 年度２を取得します。
     * @return 年度２
     */
    public BigDecimal getNendo2() {
        return nendo2;
    }
    /**
     * 年度２を設定します。
     * @param nendo2 年度２
     */
    public void setNendo2(BigDecimal nendo2) {
        this.nendo2 = nendo2;
    }
    
    /**
     * 年度３を取得します。
     * @return 年度３
     */
    public BigDecimal getNendo3() {
        return nendo3;
    }
    /**
     * 年度３を設定します。
     * @param nendo3 年度３
     */
    public void setNendo3(BigDecimal nendo3) {
        this.nendo3 = nendo3;
    }
    
    /**
     * 年度４を取得します。
     * @return 年度４
     */
    public BigDecimal getNendo4() {
        return nendo4;
    }
    /**
     * 年度４を設定します。
     * @param nendo4 年度４
     */
    public void setNendo4(BigDecimal nendo4) {
        this.nendo4 = nendo4;
    }
    
    /**
     * 年度５を取得します。
     * @return 年度５
     */
    public BigDecimal getNendo5() {
        return nendo5;
    }
    /**
     * 年度５を設定します。
     * @param nendo5 年度５
     */
    public void setNendo5(BigDecimal nendo5) {
        this.nendo5 = nendo5;
    }
    
    /**
     * 年度６を取得します。
     * @return 年度６
     */
    public BigDecimal getNendo6() {
        return nendo6;
    }
    /**
     * 年度６を設定します。
     * @param nendo6 年度６
     */
    public void setNendo6(BigDecimal nendo6) {
        this.nendo6 = nendo6;
    }
    
    /**
     * 年度７を取得します。
     * @return 年度７
     */
    public BigDecimal getNendo7() {
        return nendo7;
    }
    /**
     * 年度７を設定します。
     * @param nendo7 年度７
     */
    public void setNendo7(BigDecimal nendo7) {
        this.nendo7 = nendo7;
    }
    
    /**
     * 年度１客数を取得します。
     * @return 年度１客数
     */
    public BigDecimal getNendo1Kyakusu() {
        return nendo1Kyakusu;
    }
    /**
     * 年度１客数を設定します。
     * @param nendo1Kaykusu 年度１客数
     */
    public void setNendo1Kyakusu(BigDecimal nendo1Kaykusu) {
        this.nendo1Kyakusu = nendo1Kaykusu;
    }
    
    /**
     * 年度２客数を取得します。
     * @return 年度２客数
     */
    public BigDecimal getNendo2Kyakusu() {
        return nendo2Kyakusu;
    }
    /**
     * 年度２客数を設定します。
     * @param nendo2Kaykusu 年度２客数
     */
    public void setNendo2Kyakusu(BigDecimal nendo2Kaykusu) {
        this.nendo2Kyakusu = nendo2Kaykusu;
    }
    
    /**
     * 年度３客数を取得します。
     * @return 年度３客数
     */
    public BigDecimal getNendo3Kyakusu() {
        return nendo3Kyakusu;
    }
    /**
     * 年度３客数を設定します。
     * @param nendo3Kaykusu 年度３客数
     */
    public void setNendo3Kyakusu(BigDecimal nendo3Kaykusu) {
        this.nendo3Kyakusu = nendo3Kaykusu;
    }
    
    /**
     * 年度４客数を取得します。
     * @return 年度４客数
     */
    public BigDecimal getNendo4Kyakusu() {
        return nendo4Kyakusu;
    }
    /**
     * 年度４客数を設定します。
     * @param nendo4Kaykusu 年度４客数
     */
    public void setNendo4Kyakusu(BigDecimal nendo4Kaykusu) {
        this.nendo4Kyakusu = nendo4Kaykusu;
    }
    
    /**
     * 年度５客数を取得します。
     * @return 年度５客数
     */
    public BigDecimal getNendo5Kyakusu() {
        return nendo5Kyakusu;
    }
    /**
     * 年度５客数を設定します。
     * @param nendo5Kaykusu 年度５客数
     */
    public void setNendo5Kyakusu(BigDecimal nendo5Kaykusu) {
        this.nendo5Kyakusu = nendo5Kaykusu;
    }
    
    /**
     * 年度６客数を取得します。
     * @return 年度６客数
     */
    public BigDecimal getNendo6Kyakusu() {
        return nendo6Kyakusu;
    }
    /**
     * 年度６客数を設定します。
     * @param nendo6Kaykusu 年度６客数
     */
    public void setNendo6Kyakusu(BigDecimal nendo6Kaykusu) {
        this.nendo6Kyakusu = nendo6Kaykusu;
    }
    
    /**
     * 年度７客数を取得します。
     * @return 年度７客数
     */
    public BigDecimal getNendo7Kyakusu() {
        return nendo7Kyakusu;
    }
    /**
     * 年度７客数を設定します。
     * @param nendo7Kaykusu 年度７客数
     */
    public void setNendo7Kyakusu(BigDecimal nendo7Kaykusu) {
        this.nendo7Kyakusu = nendo7Kaykusu;
    }
 
    public String getNendo2Cls() {
        if (this.rowHirituFlg) {
            return getNendo2().compareTo(new BigDecimal("100.00")) < 0 ? "body_hiritu_m" : "body_num_n";
        }
        return "body_num";
    }
    public String getNendo3Cls() {
        if (this.rowHirituFlg) {
            return getNendo3().compareTo(new BigDecimal("100.00")) < 0 ? "body_hiritu_m" : "body_num_n";
        }
        return "body_num";
    }
    public String getNendo4Cls() {
        if (this.rowHirituFlg) {
            return getNendo4().compareTo(new BigDecimal("100.00")) < 0 ? "body_hiritu_m" : "body_num_n";
        }
        return "body_num";
    }
    public String getNendo5Cls() {
        if (this.rowHirituFlg) {
            return getNendo5().compareTo(new BigDecimal("100.00")) < 0 ? "body_hiritu_m" : "body_num_n";
        }
        return "body_num";
    }
    public String getNendo6Cls() {
        if (this.rowHirituFlg) {
            return getNendo6().compareTo(new BigDecimal("100.00")) < 0 ? "body_hiritu_m" : "body_num_n";
        }
        return "body_num";
    }
    public String getNendo7Cls() {
        if (this.rowHirituFlg) {
            return getNendo7().compareTo(new BigDecimal("100.00")) < 0 ? "body_hiritu_m" : "body_num_n";
        }
        return "body_num";
    }
    public String getNendo2KyakusuCls() {
        if (this.rowHirituFlg) {
            return getNendo2Kyakusu().compareTo(new BigDecimal("100.00")) < 0 ? "body_hiritu_m" : "body_num_n";
        }
        return "body_num";
    }
    public String getNendo3KyakusuCls() {
        if (this.rowHirituFlg) {
            return getNendo3Kyakusu().compareTo(new BigDecimal("100.00")) < 0 ? "body_hiritu_m" : "body_num_n";
        }
        return "body_num";
    }
    public String getNendo4KyakusuCls() {
        if (this.rowHirituFlg) {
            return getNendo4Kyakusu().compareTo(new BigDecimal("100.00")) < 0 ? "body_hiritu_m" : "body_num_n";
        }
        return "body_num";
    }
    public String getNendo5KyakusuCls() {
        if (this.rowHirituFlg) {
            return getNendo5Kyakusu().compareTo(new BigDecimal("100.00")) < 0 ? "body_hiritu_m" : "body_num_n";
        }
        return "body_num";
    }
    public String getNendo6KyakusuCls() {
        if (this.rowHirituFlg) {
            return getNendo6Kyakusu().compareTo(new BigDecimal("100.00")) < 0 ? "body_hiritu_m" : "body_num_n";
        }
        return "body_num";
    }
    public String getNendo7KyakusuCls() {
        if (this.rowHirituFlg) {
            return getNendo7Kyakusu().compareTo(new BigDecimal("100.00")) < 0 ? "body_hiritu_m" : "body_num_n";
        }
        return "body_num";
    }

}
