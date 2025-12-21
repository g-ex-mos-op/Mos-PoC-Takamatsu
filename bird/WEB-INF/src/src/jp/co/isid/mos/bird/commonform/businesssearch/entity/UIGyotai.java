package jp.co.isid.mos.bird.commonform.businesssearch.entity;

public class UIGyotai {
    
    public static final String TABLE = "BC09GTAI";
    
    public static final String gyotaiKbn_COLUMN = "GYOTAI_KBN";
    
    public static final String gyotaiName_COLUMN = "GYOTAI_KBN_NAME";
    
    /**
     * 業態区分
     */
    private String gyotaiKbn;
    
    /**
     * 業態区分名称
     */
    private String gyotaiName;
    
    /**
     * 選択状態
     */
    private boolean checkedGyotai;

    /**
     * 業態区分を取得します。
     * @return 業態区分
     */
    public String getGyotaiKbn() {
        return gyotaiKbn;
    }
    /**
     * 業態区分を設定します。
     * @param gyotaiKbn 業態区分
     */
    public void setGyotaiKbn(String gyotaiKbn) {
        this.gyotaiKbn = gyotaiKbn;
    }
    
    /**
     * 業態区分名称を取得します。
     * @return 業態区分名称
     */
    public String getGyotaiName() {
        return gyotaiName;
    }
    /**
     * 業態区分名称を設定します。
     * @param gyotaiName 業態区分名称
     */
    public void setGyotaiName(String gyotaiName) {
        this.gyotaiName = gyotaiName;
    }
    
    /**
     * 選択状態を取得します。
     * @return 選択状態
     */
    public boolean isCheckedGyotai() {
        return checkedGyotai;
    }
    /**
     * 選択状態を設定します。
     * @param checkedGyotai 選択状態
     */
    public void setCheckedGyotai(boolean checkedGyotai) {
        this.checkedGyotai = checkedGyotai;
    }
}
