/*
 * 作成日: 2005/09/06
 * 
 */
package jp.co.isid.mos.bird.framework.exception;

/**
 * 「不可能な操作を行おうとした」場合に使用するException
 * @author xytamura
 */
public class InvalidRelationException extends ApplicationException {

    private static final String WRN_MSG_ID = "W0603";

    private static final String ERR_MSG_ID = "E0603";

    /**
     * コンストラクタ
     * @param entity1 １番目のメッセージのパラメータ
     * @param entity2 ２番目のメッセージのパラメータ
     */
    public InvalidRelationException(final String entity1, final String entity2) {
        this(entity1, entity2, "", false);
    }

    /**
     * コンストラクタ
     * @param entity1 １番目のメッセージのパラメータ
     * @param entity2 ２番目のメッセージのパラメータ
     * @param detail 詳細情報
     */
    public InvalidRelationException(final String entity1, final String entity2,
            final String detail) {
        this(entity1, entity2, detail, false);
    }

    /**
     * コンストラクタ
     * @param entity1 １番目のメッセージのパラメータ
     * @param entity2 ２番目のメッセージのパラメータ
     * @param detail 詳細情報
     * @param warningFlg ワーニングの場合はtrue、エラーの場合はfalse
     */
    public InvalidRelationException(final String entity1, final String entity2,
            final String detail, boolean warningFlg) {
        super(new String[] { entity1, entity2, detail }, warningFlg);
    }

    /**
     * コンストラクタ
     * @param entity1 １番目のメッセージのパラメータ
     * @param entity2 ２番目のメッセージのパラメータ
     * @param errHtmlElementName HTMLパラメータ名称
     * @param errHtmlTabName HTMLタブ名称
     */
    public InvalidRelationException(final String entity1, final String entity2,
            final String errHtmlElementName, final String errHtmlTabName) {
        this(entity1, entity2);
        setHtmlElementName(errHtmlElementName);
        setHtmlTabName(errHtmlTabName);
    }
    /**
     * コンストラクタ
     * 
     * @param entity1 １番目のメッセージのパラメータ
     * @param entity2 ２番目のメッセージのパラメータ
     * @param errHtmlElementName HTMLパラメータ名称
     * @param errHtmlElementIndex HTMLパラメータインデックス
     */
    public InvalidRelationException(
    		final String entity1, final String entity2
        ,   final String errHtmlElementName, final int errHtmlElementIndex) {
        this(entity1, entity2);
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