/*
 * 作成日: 2005/09/06
 * 
 */
package jp.co.isid.mos.bird.framework.exception;

/**
 * 「対象エンティティが存在しない」場合に使用するException
 * @author xytamura
 *
 */
public class NoEntityException extends ApplicationException {

    private static final String WRN_MSG_ID="W0104";
    private static final String ERR_MSG_ID="E0104";
    
    /**
     * コンストラクタ
     * 
     * @param entity1 １番目のメッセージのパラメータ
     * @param entity2 ２番目のメッセージのパラメータ
     */
    public NoEntityException(final String entity1, final String entity2) {
        this(entity1, entity2, "", false);
    }

    /**
     * コンストラクタ
     * 
     * @param entity1 １番目のメッセージのパラメータ
     * @param entity2 ２番目のメッセージのパラメータ
     * @param detail 詳細情報
     */
    public NoEntityException(final String entity1, final String entity2,
            final String detail) {
        this(entity1, entity2, detail, false);
    }

    /**
     * コンストラクタ
     * 
     * @param entity1 １番目のメッセージのパラメータ
     * @param entity2 ２番目のメッセージのパラメータ
     * @param detail 詳細情報
     * @param warningFlg ワーニングの場合はtrue、エラーの場合はfalse
     */
    public NoEntityException(final String entity1, final String entity2,
            final String detail, boolean warningFlg) {
        super(new String[]{entity1, entity2, detail}, warningFlg);
    }

	/**
	 * @see jp.co.isid.mos.bird.framework.exception.ApplicationException#getMessageId()
	 */
	public String getMessageId() {
        if(isWarning()){
            return WRN_MSG_ID;
        }            
        return ERR_MSG_ID;
	}

}
