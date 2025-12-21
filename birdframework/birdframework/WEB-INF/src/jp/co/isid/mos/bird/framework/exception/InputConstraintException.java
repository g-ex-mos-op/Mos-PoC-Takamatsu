/*
 * 作成日: 2006/12/12
 * 
 */
package jp.co.isid.mos.bird.framework.exception;

/**
 * 「入力値の制約条件が満たされていない」場合に使用するException
 * @author 鄭
 */
public class InputConstraintException extends ApplicationException {

    private static final String ERR_MSG_ID = "E0508";

    /**
     * コンストラクタ
     * @param entity1 １番目のメッセージのパラメータ
     * @param entity2 ２番目のメッセージのパラメータ
     */
    public InputConstraintException(final String entity1, final String entity2) {
        this(entity1, entity2, "");
    }

    /**
     * コンストラクタ
     * @param entity1 １番目のメッセージのパラメータ
     * @param entity2 ２番目のメッセージのパラメータ
     * @param detail  詳細情報
     */
    public InputConstraintException(final String entity1, final String entity2,
            final String detail) {
        super(new String[] { entity1, entity2, detail });
    }

    /**
     * コンストラクタ
     * @param entity1 １番目のメッセージのパラメータ
     * @param entity2 ２番目のメッセージのパラメータ
     * @param errHtmlElementName HTMLパラメータ名称
     * @param errHtmlTabName     HTMLタブ名称
     */
    public InputConstraintException(final String entity1, final String entity2,
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
     * @param errHtmlElementName  HTMLパラメータ名称
     * @param errHtmlElementIndex HTMLパラメータインデックス
     */
    public InputConstraintException(final String entity1, final String entity2,
            final String errHtmlElementName, final int errHtmlElementIndex) {
        this(entity1, entity2);
        setHtmlElementName(errHtmlElementName);
        setHtmlElementIndex(errHtmlElementIndex);
    }

    /**
     * コンストラクタ
     * @param entity1 １番目のメッセージのパラメータ
     * @param entity2 ２番目のメッセージのパラメータ
     * @param detail  詳細情報
     * @param errHtmlElementName HTMLパラメータ名称
     * @param errHtmlTabName     HTMLタブ名称
     */
    public InputConstraintException(final String entity1, final String entity2,
            final String detail, final String errHtmlElementName, final String errHtmlTabName) {
        this(entity1, entity2, detail);
        setHtmlElementName(errHtmlElementName);
        setHtmlTabName(errHtmlTabName);
    }

    /**
     * コンストラクタ 
     * @param entity1 １番目のメッセージのパラメータ
     * @param entity2 ２番目のメッセージのパラメータ
     * @param detail  詳細情報
     * @param errHtmlElementName  HTMLパラメータ名称
     * @param errHtmlElementIndex HTMLパラメータインデックス
     */
    public InputConstraintException(final String entity1, final String entity2,
            final String detail, final String errHtmlElementName, final int errHtmlElementIndex) {
        this(entity1, entity2, detail);
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