/*
 * 作成日: 2006/07/24
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.entity;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * CSVレコードエラー情報
 * 
 * @author xyuchida
 */
public class UICsvErrorInfo {

    public static final String ERRORCODE_FORMAT = "0001";
    public static final String ERRORCODE_REQUIRED = "0002";
    public static final String ERRORCODE_INVALID = "0003";
    public static final String ERRORCODE_LENGTH = "0004";
    public static final String ERRORCODE_EXISTS = "0005";
    public static final String ERRORCODE_NOTEXISTS = "0006";
    public static final String ERRORCODE_DUPLICATE = "0007";
    public static final String ERRORCODE_LICENSED = "0008";

    private static final Map ERROR_TITLE;
    private static final Map ERROR_MESSAGE;
    static {
        ERROR_TITLE = new HashMap();
        ERROR_MESSAGE = new HashMap();
        ERROR_TITLE.put(ERRORCODE_FORMAT, "フォーマットエラー");
        ERROR_MESSAGE.put(ERRORCODE_FORMAT, "ファイル形式が不正です。");
        ERROR_TITLE.put(ERRORCODE_REQUIRED, "登録エラー");
        ERROR_MESSAGE.put(ERRORCODE_REQUIRED, "入力された{0}が不正です。");
        ERROR_TITLE.put(ERRORCODE_INVALID, "登録エラー");
        ERROR_MESSAGE.put(ERRORCODE_INVALID, "入力された{0}が不正です。");
        ERROR_TITLE.put(ERRORCODE_LENGTH, "登録エラー");
        ERROR_MESSAGE.put(ERRORCODE_LENGTH, "入力された{0}が不正です。");
        ERROR_TITLE.put(ERRORCODE_EXISTS, "登録エラー");
        ERROR_MESSAGE.put(ERRORCODE_EXISTS, "入力された{0}は既に存在しています。");
        ERROR_TITLE.put(ERRORCODE_NOTEXISTS, "登録エラー");
        ERROR_MESSAGE.put(ERRORCODE_NOTEXISTS, "入力された{0}は存在しません。");
        ERROR_TITLE.put(ERRORCODE_DUPLICATE, "重複エラー");
        ERROR_MESSAGE.put(ERRORCODE_DUPLICATE, "{0}が重複しています。");
        ERROR_TITLE.put(ERRORCODE_LICENSED, "登録エラー");
        ERROR_MESSAGE.put(ERRORCODE_LICENSED, "既にライセンスが発行されています。");
    }

    private String errorCode;
    private Object[] params;
    private String inputData;

    /**
     * @return errorCode を戻します。
     */
    private String getErrorCode() {
        return errorCode;
    }
    /**
     * @param errorCode errorCode を設定。
     */
    private void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    /**
     * @return params を戻します。
     */
    private Object[] getParams() {
        return params;
    }
    /**
     * @param params params を設定。
     */
    private void setParams(Object[] params) {
        this.params = params;
    }
    /**
     * @return inputData を戻します。
     */
    public String getInputData() {
        return inputData;
    }
    /**
     * @param inputData inputData を設定。
     */
    public void setInputData(String inputData) {
        this.inputData = inputData;
    }


    /**
     * エラー情報設定
     * 
     * @param errorCode エラーコード
     */
    public void setError(String errorCode) {
        setError(errorCode, "");
    }

    /**
     * エラー情報設定
     * 
     * @param errorCode エラーコード
     * @param overwrite エラー情報上書きフラグ true:上書き false:上書きしない
     */
    public void setError(String errorCode, boolean overwrite) {
        if (overwrite || !isExistError()) {
            setError(errorCode, "");
        }
    }

    /**
     * エラー情報設定
     * 
     * @param errorCode エラーコード
     * @param params メッセージパラメータ
     */
    public void setError(String errorCode, String params) {
        setError(errorCode, new Object[] { params });
    }

    /**
     * エラー情報設定
     * 
     * @param errorCode エラーコード
     * @param params メッセージパラメータ
     * @param overwrite エラー情報上書きフラグ true:上書き false:上書きしない
     */
    public void setError(String errorCode, String params, boolean overwrite) {
        if (overwrite || !isExistError()) {
            setError(errorCode, params);
        }
    }

    /**
     * エラー情報設定
     * 
     * @param errorCode エラーコード
     * @param params メッセージパラメータ
     */
    public void setError(String errorCode, Object[] params) {
        if (isValidErrorCode(errorCode)) {
            setErrorCode(errorCode);
            setParams(params);
        }
    }

    /**
     * エラー情報設定
     * 
     * @param errorCode エラーコード
     * @param params メッセージパラメータ
     * @param overwrite エラー情報上書きフラグ true:上書き false:上書きしない
     */
    public void setError(String errorCode, Object[] params, boolean overwrite) {
        if (overwrite || !isExistError()) {
            setError(errorCode, params);
        }
    }

    /**
     * エラー有無判定
     * 
     * @return エラー有無
     */
    public boolean isExistError() {
        return isValidErrorCode(getErrorCode());
    }

    /**
     * エラータイトル取得
     * 
     * @return エラータイトル
     */
    public String getErrorTitle() {
        return (String) ERROR_TITLE.get(getErrorCode());
    }

    /**
     * エラーメッセージ取得
     * 
     * @return
     */
    public String getErrorMessage() {
        String errorMessage = (String) ERROR_MESSAGE.get(getErrorCode());
        // メッセージパラメータを適用してフォーマット
        return MessageFormat.format(errorMessage, getParams());
    }

    /**
     * エラーコード妥当性判定
     * 
     * @param errorCode エラーコード
     * @return エラーコード妥当性 true:正常 false:不正
     */
    private boolean isValidErrorCode(String errorCode) {
        return ERROR_TITLE.containsKey(errorCode);
    }
}
