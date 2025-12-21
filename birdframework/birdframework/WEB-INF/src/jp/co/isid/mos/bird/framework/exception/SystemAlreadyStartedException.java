/*
 * 作成日: 2005/09/06
 * 
 */
package jp.co.isid.mos.bird.framework.exception;

/**
 * 「システムの２重起動」場合に使用するException
 * @author xytamura
 */
public class SystemAlreadyStartedException extends ApplicationException {

    private static final String ERR_MSG_ID = "E0411";

    /**
     * コンストラクタ
     */
    public SystemAlreadyStartedException() {
        super(new String[] { "" }, false);
    }

    /**
     * コンストラクタ
     * @param errHtmlElementName HTMLパラメータ名称
     * @param errHtmlTabName HTMLタブ名称
     */
    public SystemAlreadyStartedException(final String errHtmlElementName,
            final String errHtmlTabName) {
        this();
        setHtmlElementName(errHtmlElementName);
        setHtmlTabName(errHtmlTabName);
    }

    /**
     * コンストラクタ
     * 
     * @param errHtmlElementName HTMLパラメータ名称
     * @param errHtmlElementIndex HTMLパラメータインデックス
     */
    public SystemAlreadyStartedException(
    		final String errHtmlElementName, final int errHtmlElementIndex) {
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