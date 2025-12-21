/*
 * 作成日: 2006/09/13
 *
 */
package jp.co.isid.mos.bird.framework.exception;

/**
 * 汎用例外
 * 
 * @author xyuchida
 */
public class GenericMessageException extends ApplicationException {

    /**
     * エラーメッセージID
     */
    private static final String ERR_MSG_ID = "E0204";

    /**
     * ワーニングメッセージID
     */
    private static final String WRN_MSG_ID = "W0204";

    /**
     * コンストラクタ
     * 
     * @param message メッセージパラメータ
     */
    public GenericMessageException(String message) {
        this(new String[] {message});
    }

    /**
     * コンストラクタ
     * 
     * @param messageArgs メッセージパラメータ
     */
    public GenericMessageException(String[] messageArgs) {
        super(messageArgs);
    }

    /**
     * コンストラクタ
     * 
     * @param message メッセージパラメータ
     * @param errHtmlElementName HTMLエレメント名称
     */
    public GenericMessageException(String message, String errHtmlElementName) {
        this(message);
        setHtmlElementName(errHtmlElementName);
    }

    /**
     * コンストラクタ
     * 
     * @param message メッセージパラメータ
     * @param errHtmlElementName HTMLエレメント名称
     * @param errHtmlTabName HTMLタブ名称
     */
    public GenericMessageException(String message, String errHtmlElementName, String errHtmlTabName) {
        this(message);
        setHtmlElementName(errHtmlElementName);
        setHtmlTabName(errHtmlTabName);
    }

    /**
     * コンストラクタ
     * 
     * @param message メッセージパラメータ
     * @param errHtmlElementName HTMLエレメント名称
     * @param errHtmlElementIndex HTMLパラメータインデックス
     */
    public GenericMessageException(String message, String errHtmlElementName, int errHtmlElementIndex) {
        this(message);
        setHtmlElementName(errHtmlElementName);
        setHtmlElementIndex(errHtmlElementIndex);
    }

    /**
     * メッセージID取得
     * 
     * @return メッセージID
     */
    public String getMessageId() {
        return (isWarning()) ? WRN_MSG_ID : ERR_MSG_ID;
    }
}
