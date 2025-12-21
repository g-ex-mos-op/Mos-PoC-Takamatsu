/*
 * 作成日: 2005/09/06
 * 
 */
package jp.co.isid.mos.bird.framework.exception;

/**
 * 「障害で処理が続行できない」場合に使用するException
 * @author xytamura
 */
public class FtlSystemException extends ApplicationException {

    private static final String ERR_MSG_ID = "F0101";

    /**
     * コンストラクタ
     * @param entity メッセージのパラメータ
     */
    public FtlSystemException(final String entity) {
        this(entity, "");
    }

    /**
     * コンストラクタ
     * @param entity メッセージのパラメータ
     * @param detail 詳細情報
     */
    public FtlSystemException(final String entity, final String detail) {
        super(new String[] { entity, detail }, false);
    }

    /**
     * コンストラクタ
     * @param entity メッセージのパラメータ
     * @param detail 詳細情報
     * @param originalException 発生したException
     */
    public FtlSystemException(final String entity, final String detail,
            Exception originalException) {
        super(new String[] { entity, detail }, false, originalException);
    }

    /**
     * コンストラクタ
     * @param entity メッセージのパラメータ
     * @param detail 詳細情報
     * @param originalException 発生したException
     * @param errHtmlElementName HTMLパラメータ名称
     * @param errHtmlTabName HTMLタブ名称
     */
    public FtlSystemException(final String entity, final String detail,
            Exception originalException, final String errHtmlElementName,
            final String errHtmlTabName) {
        this(entity, detail, originalException);
        setHtmlElementName(errHtmlElementName);
        setHtmlTabName(errHtmlTabName);
    }
    /**
     * コンストラクタ
     * 
     * @param entity メッセージのパラメータ
     * @param detail 詳細情報
     * @param originalException 発生したException
     * @param errHtmlElementName HTMLパラメータ名称
     * @param errHtmlElementIndex HTMLパラメータインデックス
     */
    public FtlSystemException(
    		final String entity, final String detail
            , Exception originalException
    		, final String errHtmlElementName, final int errHtmlElementIndex) 
    {
        this(entity, detail, originalException);
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