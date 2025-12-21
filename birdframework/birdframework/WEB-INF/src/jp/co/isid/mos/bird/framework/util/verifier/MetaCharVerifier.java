/*
 * MetaCharVerifier.java
 * Created by Noh
 *
 */
package jp.co.isid.mos.bird.framework.util.verifier;

/**
 * SQLメタ文字が含まれているかチェックを行う
 * 取り急ぎ、枠だけ
 */
public class MetaCharVerifier extends DefaultVerifier {

	private String regex = "%％";
    /**
     * 新しいバリデータを生成します。
     * ・nullを許可
     * ・英字不可
     * ・桁数制限なし
     */
    public MetaCharVerifier() {
    }
    /**
     *@param name string
     *@param value string
     *@return msgf 
     */
    public String[] getMsg(final String name, final String value) {
        String[] msg = new String[2];
        msg[0] = name;
        msg[1] = "(" + name + "=" + value + ")";
        return msg;
    }

    /**
     * 整数値としての妥当性チェックを行います。 
     * @param  text       対象となる文字列 
     * @return true：成功 / false：失敗
     */
    public boolean validate(final String text) {
    	//%,％をテェックする
    	//テェックすることは増えるかも知らない
    	
    	String stl = text.trim();
    	//入力したテキストテェック
    	if((stl == null) || (stl.equals(""))){
    		return false;
    	}
        if(stl.matches(".*["+ regex +"].*")){
        	_isNullable= false;
        }else{
        	_isNullable= true;
        }

        return _isNullable;
    }

    /**
     * 許可するデータタイプを文字列で返します。
     * @return String
     */
    public String getValidDescription() {
    	String reDescription = "";
    	if(_isNullable){
    		
    	}else{
    		reDescription = "入力したデータの中で["+regex+"]が入っています。";
    	}
        return reDescription;
    }
}
