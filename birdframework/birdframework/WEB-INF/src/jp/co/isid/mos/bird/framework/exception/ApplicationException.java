/*
 * 作成日: 2005/09/06
 *
 */
package jp.co.isid.mos.bird.framework.exception;

/**
 * @author xytamura
 */
public abstract class ApplicationException extends RuntimeException {

    /** ワーニングの場合はtrue、エラーの場合はfalse */
    private boolean warningFlg = false;
    /** メッセージ引数 */
    private String[] messageArgs = null;
//    /** メッセージ */
//    private String fullMessage = null;
    /** 元となったEeption */
    private Exception exception = null;
    /** 
     * エラーHTMLパラメーター名 
     * INPUT系のNAME項目の名称です。
     */
    private String errHtmlElementName = null;
    /** エラーHTMLパラメーターINDEX */
    private int errHtmlElementIndex = 0;
    
    private String errHtmlTabName = null;
    
    /**
     * コンストラクタ、レベルはエラー
     * @param messageArgs メッセージ引数
     */
    public ApplicationException(final String[] messageArgs) {
        this(messageArgs, false, null);
    }

    /**
     * コンストラクタ
     * @param messageArgs メッセージ引数
     * @param warningFlg ワーニングの場合はtrue、エラーの場合はfalse
     */
    public ApplicationException(final String[] messageArgs,
            final boolean warningFlg) {
        this(messageArgs, warningFlg, null);
    }

    /**
     * コンストラクタ
     * @param messageArgs メッセージ引数
     * @param warningFlg ワーニングの場合はtrue、エラーの場合はfalse
     * @param ex Exception
     */
    public ApplicationException(final String[] messageArgs,
            final boolean warningFlg, final Exception ex) {
    	this(messageArgs, warningFlg, ex, null);
    }
    /**
     * コンストラクタ
     * @param messageArgs メッセージ引数
     * @param warningFlg ワーニングの場合はtrue、エラーの場合はfalse
     * @param ex Exception
     * @param htmlElementName String エラーHTMLパラメーター名称
     */
    public ApplicationException(
    	  final String[] messageArgs
		, final boolean warningFlg
		, final Exception ex
		, final String htmlElementName
		) 
    {
        init(messageArgs, warningFlg, ex, htmlElementName, 0);
    }
    /**
     * コンストラクター
     * 
     * @param messageArgs メッセージ引数
     * @param warningFlg ワーニングの場合はtrue、エラーの場合はfalse
     * @param ex Exception
     * @param htmlElementName String エラーHTMLパラメーター名称
     * @param htmlElementIndex int エラーHTMLパラメーターインデックス
     */
    public ApplicationException(
    	  final String[] messageArgs
		, final boolean warningFlg
		, final Exception ex
		, final String htmlElementName
		, final int htmlElementIndex
		) 
    {
        init(messageArgs, warningFlg, ex, htmlElementName, htmlElementIndex);
    }

    /**
     * 初期処理
     * 
     * @param messageArgs メッセージ引数
     * @param isWarning ワーニングの場合はtrue、エラーの場合はfalse
     * @param ex
     * @param htmlElementName
     * @param htmlElementIndex
     */
    private void init(
    	  final String[] messageArgs
		, final boolean warningFlg
		, final Exception ex
		, final String htmlElementName
		, final int htmlElementIndex
		) 
    {
        this.messageArgs = messageArgs;
        this.warningFlg = warningFlg;
        this.exception = ex;
        this.errHtmlElementName = htmlElementName;
    }

//    /**
//     * メッセージを返します。
//     * 
//     * @return メッセージ
//     */
//    public String getFullMessage() {
//        return fullMessage;
//    }
//
//    /**
//     * 表示用メッセージ作成処理。
//     */
//    private void setDispMessage() {
//        fullMessage = MessageFactory.getMessage(getMessageId(), messageArgs);
//    }

    /**
     * メッセージＩＤを返します。
     * 
     * @return
     */
    public abstract String getMessageId();

    /**
     * ワーニングかどうかをあらわすフラグを返します。
     * 
     * @return ワーニングの場合はtrue、エラーの場合はfalse
     */
    public boolean isWarning() {
        return warningFlg;
    }

    /**
     * ワーニングかどうかをあらわすフラグをセットします。
     * 
     * @param isWarning ワーニングの場合はtrue、エラーの場合はfalseをセットします。
     */
    public void setWarning(final boolean isWarning) {
        this.warningFlg = isWarning;
    }

    /**
     * 元のExceptionを返します。
     * @return 元のException
     */
    public Exception getOriginalException(){
        return exception;
    }
    
    /**
     * メッセージ引数を取得します。
     * @return メッセージ引数 
     */
    public String[] getMessageArgs(){
        return messageArgs;   
    }
    
    /**
     * HTMLパラメータ名称取得処理
     * 
     * @return HTMLパラメータ名称
     */
    public final String getHtmlElementName() {
    	return errHtmlElementName;
    }
    
    /**
     * HTMLパラメータ名称取得処理
     * 
     * @return HTMLパラメータ名称
     */
    public void setHtmlElementName(final String errHtmlElementName) {
        this.errHtmlElementName = errHtmlElementName;
    }
    
    /**
     * HTMLタブ情報設定処理
     * 
     * @param htmlElementName String エラーHTMLタブ名称
     */
    public void setHtmlTabName(final String htmlTabName) {
        this.errHtmlTabName = htmlTabName;
    }
    
    /**
     * HTMLタブ情報設定処理
     * 
     * @param htmlElementName String エラーHTMLタブ名称
     */
    public String getHtmlTabName() {
        return this.errHtmlTabName;
    }
    
    
    /**
     * エラーHTMLパラメーターINDEX取得処理
     * 
     * @return errHtmlElementIndex を戻します。
     */
    public int getHtmlElementIndex() {
        return errHtmlElementIndex;
    }
    
    /**
     * エラーHTMLパラメーターINDEX設定処理
     * 
     * @param errSeq errSeq を設定。
     */
    public void setHtmlElementIndex(int index) {
        this.errHtmlElementIndex = index;
    }
}