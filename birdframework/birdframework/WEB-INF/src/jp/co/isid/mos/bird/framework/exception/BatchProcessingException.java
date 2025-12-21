/*
 * 作成日: 2005/09/06
 * 
 */
package jp.co.isid.mos.bird.framework.exception;

/**
 * 「バッチ処理との同一業務の競合」場合に使用するException
 * @author xytamura
 */
public class BatchProcessingException extends ApplicationException {

    private static final String ERR_MSG_ID = "E0401";

    /**
     * コンストラクタ
     */
    public BatchProcessingException() {
        this("");
    }

    /**
     * コンストラクタ
     * @param detail 詳細情報
     */
    public BatchProcessingException(final String detail) {
        super(new String[] { detail }, false);
    }

    /**
     * コンストラクタ
     * @param errHtmlElementName HTMLパラメータ名称
     * @param errHtmlTabName HTMLタブ名称
     */
    public BatchProcessingException(final String errHtmlElementName,
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
    public BatchProcessingException(final String entity,
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