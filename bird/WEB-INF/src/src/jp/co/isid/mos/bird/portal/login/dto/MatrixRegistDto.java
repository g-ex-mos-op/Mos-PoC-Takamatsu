package jp.co.isid.mos.bird.portal.login.dto;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;

/**
 * マトリクス認証DTO
 * @author xnkusama
 */
public class MatrixRegistDto implements CsvOutputDto {

    private String userId = "";
    private String keyA1 = "";
    private String keyA2 = "";
    private String keyA3 = "";
    private String keyA4 = "";
    private String keyA5 = "";
    private String keyB1 = "";
    private String keyB2 = "";
    private String keyB3 = "";
    private String keyB4 = "";
    private String keyB5 = "";
    private String keyC1 = "";
    private String keyC2 = "";
    private String keyC3 = "";
    private String keyC4 = "";
    private String keyC5 = "";
    private String keyD1 = "";
    private String keyD2 = "";
    private String keyD3 = "";
    private String keyD4 = "";
    private String keyD5 = "";
    private String keyE1 = "";
    private String keyE2 = "";
    private String keyE3 = "";
    private String keyE4 = "";
    private String keyE5 = "";
    private String pageKey = "";
    
    /**
     * @param A1を設定。
     */
    public void setKeyA1(String val) {
        keyA1 = val;
    }

    /**
     * @return A1を戻します。
     */
    public String getKeyA1() {
        return keyA1;
    }
    
    /**
     * @param A2を設定。
     */
    public void setKeyA2(String val) {
        keyA2 = val;
    }

    /**
     * @return A2を戻します。
     */
    public String getKeyA2() {
        return keyA2;
    }
    
    /**
     * @param A3を設定。
     */
    public void setKeyA3(String val) {
        keyA3 = val;
    }

    /**
     * @return A3を戻します。
     */
    public String getKeyA3() {
        return keyA3;
    }
    
    /**
     * @param A4を設定。
     */
    public void setKeyA4(String val) {
        keyA4 = val;
    }

    /**
     * @return A4を戻します。
     */
    public String getKeyA4() {
        return keyA4;
    }
    
    /**
     * @param A5を設定。
     */
    public void setKeyA5(String val) {
        keyA5 = val;
    }

    /**
     * @return A5を戻します。
     */
    public String getKeyA5() {
        return keyA5;
    }
    
    /**
     * @param B1を設定。
     */
    public void setKeyB1(String val) {
        keyB1 = val;
    }

    /**
     * @return B1を戻します。
     */
    public String getKeyB1() {
        return keyB1;
    }
    
    /**
     * @param B2を設定。
     */
    public void setKeyB2(String val) {
        keyB2 = val;
    }

    /**
     * @return B2を戻します。
     */
    public String getKeyB2() {
        return keyB2;
    }
    
    /**
     * @param B3を設定。
     */
    public void setKeyB3(String val) {
        keyB3 = val;
    }

    /**
     * @return B3を戻します。
     */
    public String getKeyB3() {
        return keyB3;
    }
    
    /**
     * @param B4を設定。
     */
    public void setKeyB4(String val) {
        keyB4 = val;
    }

    /**
     * @return B4を戻します。
     */
    public String getKeyB4() {
        return keyB4;
    }
    
    /**
     * @param B5を設定。
     */
    public void setKeyB5(String val) {
        keyB5 = val;
    }

    /**
     * @return B5を戻します。
     */
    public String getKeyB5() {
        return keyB5;
    }
    
    /**
     * @param C1を設定。
     */
    public void setKeyC1(String val) {
        keyC1 = val;
    }

    /**
     * @return C1を戻します。
     */
    public String getKeyC1() {
        return keyC1;
    }
    
    /**
     * @param C2を設定。
     */
    public void setKeyC2(String val) {
        keyC2 = val;
    }

    /**
     * @return C2を戻します。
     */
    public String getKeyC2() {
        return keyC2;
    }
    
    /**
     * @param C3を設定。
     */
    public void setKeyC3(String val) {
        keyC3 = val;
    }

    /**
     * @return C3を戻します。
     */
    public String getKeyC3() {
        return keyC3;
    }
    
    /**
     * @param C4を設定。
     */
    public void setKeyC4(String val) {
        keyC4 = val;
    }

    /**
     * @return C4を戻します。
     */
    public String getKeyC4() {
        return keyC4;
    }
    
    /**
     * @param C5を設定。
     */
    public void setKeyC5(String val) {
        keyC5 = val;
    }

    /**
     * @return C5を戻します。
     */
    public String getKeyC5() {
        return keyC5;
    }
    
    /**
     * @param D1を設定。
     */
    public void setKeyD1(String val) {
        keyD1 = val;
    }

    /**
     * @return D1を戻します。
     */
    public String getKeyD1() {
        return keyD1;
    }
    
    /**
     * @param D2を設定。
     */
    public void setKeyD2(String val) {
        keyD2 = val;
    }

    /**
     * @return D2を戻します。
     */
    public String getKeyD2() {
        return keyD2;
    }
    
    /**
     * @param D3を設定。
     */
    public void setKeyD3(String val) {
        keyD3 = val;
    }

    /**
     * @return D3を戻します。
     */
    public String getKeyD3() {
        return keyD3;
    }
    
    /**
     * @param D4を設定。
     */
    public void setKeyD4(String val) {
        keyD4 = val;
    }

    /**
     * @return D4を戻します。
     */
    public String getKeyD4() {
        return keyD4;
    }
    
    /**
     * @param D5を設定。
     */
    public void setKeyD5(String val) {
        keyD5 = val;
    }

    /**
     * @return D5を戻します。
     */
    public String getKeyD5() {
        return keyD5;
    }
    
    /**
     * @param E1を設定。
     */
    public void setKeyE1(String val) {
        keyE1 = val;
    }

    /**
     * @return E1を戻します。
     */
    public String getKeyE1() {
        return keyE1;
    }
    
    /**
     * @param E2を設定。
     */
    public void setKeyE2(String val) {
        keyE2 = val;
    }

    /**
     * @return E2を戻します。
     */
    public String getKeyE2() {
        return keyE2;
    }
    
    /**
     * @param E3を設定。
     */
    public void setKeyE3(String val) {
        keyE3 = val;
    }

    /**
     * @return E3を戻します。
     */
    public String getKeyE3() {
        return keyE3;
    }
    
    /**
     * @param E4を設定。
     */
    public void setKeyE4(String val) {
        keyE4 = val;
    }

    /**
     * @return E4を戻します。
     */
    public String getKeyE4() {
        return keyE4;
    }
    
    /**
     * @param E5を設定。
     */
    public void setKeyE5(String val) {
        keyE5 = val;
    }

    /**
     * @return A5を戻します。
     */
    public String getKeyE5() {
        return keyE5;
    }

    /**
     * @param userId userId を設定。
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return userId を戻します。
     */
    public String getUserId() {
        return userId;
    }
    
    public List getInputList() {
        List row = new ArrayList();
        // １行目
        List col = new ArrayList();
        col.add(getKeyA1());
        col.add(getKeyA2());
        col.add(getKeyA3());
        col.add(getKeyA4());
        col.add(getKeyA5());
        row.add(col);
        
        // ２行目
        col = new ArrayList();
        col.add(getKeyB1());
        col.add(getKeyB2());
        col.add(getKeyB3());
        col.add(getKeyB4());
        col.add(getKeyB5());
        row.add(col);
        
        // ２行目
        col = new ArrayList();
        col.add(getKeyC1());
        col.add(getKeyC2());
        col.add(getKeyC3());
        col.add(getKeyC4());
        col.add(getKeyC5());
        row.add(col);

        // ２行目
        col = new ArrayList();
        col.add(getKeyD1());
        col.add(getKeyD2());
        col.add(getKeyD3());
        col.add(getKeyD4());
        col.add(getKeyD5());
        row.add(col);

        // ２行目
        col = new ArrayList();
        col.add(getKeyE1());
        col.add(getKeyE2());
        col.add(getKeyE3());
        col.add(getKeyE4());
        col.add(getKeyE5());
        row.add(col);
        
        return row;
    }

    /**
     * @param pageKey pageKey を設定。
     */
    public void setPageKey(String pageKey) {
        this.pageKey = pageKey;
    }

    /**
     * @return pageKey を戻します。
     */
    public String getPageKey() {
        return pageKey;
    }
    
    public void clear() {
        setKeyA1("");
        setKeyA2("");
        setKeyA3("");
        setKeyA4("");
        setKeyA5("");
        setKeyB1("");
        setKeyB2("");
        setKeyB3("");
        setKeyB4("");
        setKeyB5("");
        setKeyC1("");
        setKeyC2("");
        setKeyC3("");
        setKeyC4("");
        setKeyC5("");
        setKeyD1("");
        setKeyD2("");
        setKeyD3("");
        setKeyD4("");
        setKeyD5("");
        setKeyE1("");
        setKeyE2("");
        setKeyE3("");
        setKeyE4("");
        setKeyE5("");
    }
}
