/*
 * 作成日: 2005/09/06
 * 
 */
package jp.co.isid.mos.bird.framework.exception;

/**
 * 「制約条件に違反している」場合に使用するException
 * @author xytamura
 */
public class ConstraintsViolationException extends ApplicationException {

    private static final String WRN_MSG_ID = "W0605";

    private static final String ERR_MSG_ID = "E0605";

    /**
     * コンストラクタ
     * @param entity メッセージのパラメータ
     */
    public ConstraintsViolationException(final String entity) {
        this(entity, "", false);
    }

    /**
     * コンストラクタ
     * @param entity メッセージのパラメータ
     * @param detail 詳細情報
     */
    public ConstraintsViolationException(final String entity,
            final String detail) {
        this(entity, detail, false);
    }

    /**
     * コンストラクタ
     * @param entity メッセージのパラメータ
     * @param detail 詳細情報
     * @param warningFlg ワーニングの場合はtrue、エラーの場合はfalse
     */
    public ConstraintsViolationException(final String entity,
            final String detail, boolean warningFlg) {
        super(new String[] { entity, detail }, warningFlg);
    }

    /**
     * コンストラクタ
     * @param entity メッセージのパラメータ
     * @param errHtmlElementName HTMLパラメータ名称
     * @param errHtmlTabName HTMLタブ名称
     */
    public ConstraintsViolationException(final String entity,
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
    public ConstraintsViolationException(final String entity,
            final String errHtmlElementName, final int errHtmlElementIndex) {
        this(entity);
        setHtmlElementName(errHtmlElementName);
        setHtmlElementIndex(errHtmlElementIndex);
    }

    /**
     * @see jp.co.isid.mos.bird.framework.exception.ApplicationException#getMessageId()
     */
    public String getMessageId() {
        if (isWarning()) {
            return WRN_MSG_ID;
        }
        return ERR_MSG_ID;
    }

}