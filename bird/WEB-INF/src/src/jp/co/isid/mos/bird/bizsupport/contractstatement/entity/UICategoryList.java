package jp.co.isid.mos.bird.bizsupport.contractstatement.entity;

import java.util.List;

public class UICategoryList {
    
    /**
     * 計算書データ
     */
    private List listData;
    
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
     * 計算書カテゴリ名称
     */
    private String keiCateName;
    
    /**
     * テーブル幅
     */
    private int tableWidth;
    
    public String getCtrlFlg1() {
        return ctrlFlg1;
    }

    public void setCtrlFlg1(String ctrlFlg1) {
        this.ctrlFlg1 = ctrlFlg1;
    }

    public String getCtrlFlg2() {
        return ctrlFlg2;
    }

    public void setCtrlFlg2(String ctrlFlg2) {
        this.ctrlFlg2 = ctrlFlg2;
    }

    public String getCtrlFlg3() {
        return ctrlFlg3;
    }

    public void setCtrlFlg3(String ctrlFlg3) {
        this.ctrlFlg3 = ctrlFlg3;
    }

    public List getListData() {
        return listData;
    }

    public void setListData(List listData) {
        this.listData = listData;
    }

    public String getKeiCateName() {
        return keiCateName;
    }

    public void setKeiCateName(String keiCateName) {
        this.keiCateName = keiCateName;
    }

    public int getTableWidth() {
        return tableWidth;
    }

    public void setTableWidth(int tableWidth) {
        this.tableWidth = tableWidth;
    }
    
}
