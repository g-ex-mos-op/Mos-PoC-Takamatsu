/*
 * 作成日: 2005/09/06
 * 
 */
package jp.co.isid.mos.bird.framework.exception;

/**
 * 「必要な情報が外部で設定されていない」場合に使用するException
 * @author xytamura
 */
public class NotCompletedException extends ApplicationException {

    private static final String ERR_MSG_ID = "E0407";

    /**
     * コンストラクタ
     * @param entity メッセージのパラメータ
     */
    public NotCompletedException(final String entity) {
        this(entity, "");
    }

    /**
     * コンストラクタ
     * @param entity メッセージのパラメータ
     * @param detail 詳細情報
     */
    public NotCompletedException(final String entity, final String detail) {
        super(new String[] { entity, detail }, false);

    }

    /**
     * コンストラクタ
     * @param entity メッセージのパラメータ
     * @param errHtmlElementName HTMLパラメータ名称
     * @param errHtmlTabName HTMLタブ名称
     */
    public NotCompletedException(final String entity,
            final String errHtmlElementName, final String errHtmlTabName) {
        this(entity);
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
    public NotCompletedException(final String entity,
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