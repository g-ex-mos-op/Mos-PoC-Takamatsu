/*
 * 作成日: 2005/09/06
 * 
 */
package jp.co.isid.mos.bird.framework.exception;

/**
 * 「ユーザ同士で同一業務キーの競合」場合に使用するException
 * @author xytamura
 */
public class SomeoneUsingException extends ApplicationException {

    private static final String ERR_MSG_ID = "E0410";

    /**
     * コンストラクタ
     * @param entity1 １番目のメッセージのパラメータ
     * @param entity2 ２番目のメッセージのパラメータ
     */
    public SomeoneUsingException(final String entity1, final String entity2) {
        this(entity1, entity2, "");
    }

    /**
     * コンストラクタ
     * @param entity1 １番目のメッセージのパラメータ
     * @param entity2 ２番目のメッセージのパラメータ
     * @param detail 詳細情報
     */
    public SomeoneUsingException(final String entity1, final String entity2,
            final String detail) {
        super(new String[] { entity1, entity2, detail }, false);
    }

    /**
     * コンストラクタ
     * @param entity1 １番目のメッセージのパラメータ
     * @param entity2 ２番目のメッセージのパラメータ
     * @param errHtmlElementName HTMLパラメータ名称
     * @param errHtmlTabName HTMLタブ名称
     */
    public SomeoneUsingException(final String entity1, final String entity2,
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
    public SomeoneUsingException(
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
        return ERR_MSG_ID;
    }

}