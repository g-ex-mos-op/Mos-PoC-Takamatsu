/*
 * 作成日: 2005/09/06
 * 
 */
package jp.co.isid.mos.bird.framework.exception;

/**
 * 「アクセス権限がない」場合に使用するException
 * @author xytamura
 */
public class CannotAccessException extends ApplicationException {

    private static final String ERR_MSG_ID = "E0402";

    /**
     * コンストラクタ
     */
    public CannotAccessException() {
        this("");
    }

    /**
     * コンストラクタ
     * @param detail 詳細情報
     */
    public CannotAccessException(final String detail) {
        super(new String[] { detail }, false);
    }

    /**
     * コンストラクタ
     * @param errHtmlElementName HTMLパラメータ名称
     * @param errHtmlTabName HTMLタブ名称
     */
    public CannotAccessException(final String errHtmlElementName,
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
    public CannotAccessException(final String entity,
            final String errHtmlElementName, final int errHtmlElementIndex) {
        this(entity);
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