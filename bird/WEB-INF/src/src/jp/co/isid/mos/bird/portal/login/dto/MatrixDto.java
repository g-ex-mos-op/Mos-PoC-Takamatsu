package jp.co.isid.mos.bird.portal.login.dto;

import java.util.List;

/**
 * マトリクス認証DTO
 * @author xnkusama
 */
public class MatrixDto {

    private String _key1;
    private String _key2;
    private String _key3;
    private List _matrixList;
    private String matrixValue;
    private String viewValue;
    private boolean pswdUpdtChekFlg=false;
    private boolean matrixResetFlg = false;
    
    /**
     * @param _key1 _key1 を設定。
     */
    public void setKey1(String key1) {
        this._key1 = key1;
    }
    /**
     * @return _key1 を戻します。
     */
    public String getKey1() {
        return _key1;
    }
    /**
     * @param _key2 _key2 を設定。
     */
    public void setKey2(String key2) {
        this._key2 = key2;
    }
    /**
     * @return _key2 を戻します。
     */
    public String getKey2() {
        return _key2;
    }
    /**
     * @param _key3 _key3 を設定。
     */
    public void setKey3(String key3) {
        this._key3 = key3;
    }
    /**
     * @return _key3 を戻します。
     */
    public String getKey3() {
        return _key3;
    }
    /**
     * 
     * @param _matrixList _matrixList を設定。
     */
    public void setMatrixList(List matrixList) {
        this._matrixList = matrixList;
    }
    /**
     * @return _matrixList を戻します。
     */
    public List getMatrixList() {
        return _matrixList;
    }
    
    public void clear() {
    	setKey1("");
        setKey2("");
        setKey3("");
    }
    public String getMatrixValue() {
        return matrixValue;
    }
    public void setMatrixValue(String matrixValue) {
        this.matrixValue = matrixValue;
    }
    public String getViewValue() {
        return viewValue;
    }
    public void setViewValue(String viewValue) {
        this.viewValue = viewValue;
    }
	/**
	 * @return pswdUpdtChekFlg を戻します。
	 */
	public boolean isPswdUpdtChekFlg() {
		return pswdUpdtChekFlg;
	}
	/**
	 * @param pswdUpdtChekFlg を クラス変数pswdUpdtChekFlgへ設定します。
	 */
	public void setPswdUpdtChekFlg(boolean pswdUpdtChekFlg) {
		this.pswdUpdtChekFlg = pswdUpdtChekFlg;
	}
    public boolean isMatrixResetFlg() {
        return matrixResetFlg;
    }
    public void setMatrixResetFlg(boolean matrixResetFlg) {
        this.matrixResetFlg = matrixResetFlg;
    }
}
