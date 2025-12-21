/*
 * 作成日: 2005/09/06
 * 
 */
package jp.co.isid.mos.bird.framework.exception;

/**
 * 「対象のデータが存在しない」場合に使用するException
 * @author xytamura
 *
 */
public class NoResultException extends ApplicationException {

    private static final String ERR_MSG_ID="E0102";
    
    /**
     * コンストラクタ 
     */
    public NoResultException(){
        this("");
    }

    /**
     * コンストラクタ
     * @param detail 詳細情報
     */
    public NoResultException( final String detail){
        super(new String[]{detail}, false);
    }

    /**
     * コンストラクタ
     * @param errHtmlElementName HTMLパラメータ名称
     * @param errHtmlTabName HTMLタブ名称
     */
    public NoResultException(
            final String errHtmlElementName, final String errHtmlTabName) {
        this("");
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
    public NoResultException(final String entity,
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
