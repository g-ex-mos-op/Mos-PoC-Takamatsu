/*
 * 作成日: 2005/09/06
 * 
 */
package jp.co.isid.mos.bird.framework.exception;

/**
 * 「対象のデータが多すぎる」場合に使用するException
 * @author xytamura
 */
public class TooManyResultException extends ApplicationException {

    private static final String ERR_MSG_ID = "E0105";

    /**
     * コンストラクタ
     */
    public TooManyResultException() {
        this("");
    }

    /**
     * コンストラクタ
     * @param detail 詳細情報
     */
    public TooManyResultException(final String detail) {
        super(new String[] { detail }, false);
    }

    /**
     * コンストラクタ
     * @param entity メッセージのパラメータ
     * @param errHtmlElementName HTMLパラメータ名称
     * @param errHtmlTabName HTMLタブ名称
     */
    public TooManyResultException(final String errHtmlElementName,
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
    public TooManyResultException(
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