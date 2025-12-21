/*
 * 作成日: 2005/09/06
 * 
 */
package jp.co.isid.mos.bird.framework.exception;

/**
 * 「２重ログイン」場合に使用するException
 * @author xytamura
 */
public class DuplicateLoginException extends ApplicationException {

    private static final String ERR_MSG_ID = "E0412";

    /**
     * コンストラクタ
     */
    public DuplicateLoginException() {
        super(new String[] { "" }, false);
    }

    /**
     * コンストラクタ
     * @param errHtmlElementName HTMLパラメータ名称
     * @param errHtmlTabName HTMLタブ名称
     */
    public DuplicateLoginException(final String errHtmlElementName,
            final String errHtmlTabName) {
        this();
        setHtmlElementName(errHtmlElementName);
        setHtmlTabName(errHtmlTabName);
    }
    /**
     * コンストラクタ
     * 
     * @param entity メッセージのパラメータ
     * @param errHtmlElementName HTMLパラメータ名称
     * @param errHtmlElementIndex HTMLパラメータインデックス
     */
    public DuplicateLoginException(
    		final String errHtmlElementName
		,   final int errHtmlElementIndex) 
    {
        this();
        setHtmlElementName(errHtmlElementName);
        setHtmlElementIndex(errHtmlElementIndex);
    }


    /**
     * @see jp.co.isid.mos.bird.framework.exception.ApplicationException#getMessageId()
     */
    public String getMessageId() {
        return ERR_MSG_ID;
    }

}