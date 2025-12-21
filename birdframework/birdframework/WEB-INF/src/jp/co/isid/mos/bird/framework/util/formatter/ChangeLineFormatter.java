/*
 * 作成日: 2006/02/24
 */
package jp.co.isid.mos.bird.framework.util.formatter;

/**
 * 改行フォーマッター <BR>
 * \r\n ⇔ ` 変換します。 ` ⇔ BR 変換します。
 * @author xytamura
 */
public class ChangeLineFormatter extends DefaultFormatter {

    //改行文字DB保存用
    public static final String ENTER_WORD = "`";

    //エンターキー
    public static final String ENTER_KEY = "\r\n";

    //BRタグ
    public static final String BR = "<BR>";

    //true:編集画面 false:確認、参照画面
    private boolean mode;

    /**
     * コンストラクタ
     */
    public ChangeLineFormatter() {
    }

    /**
     * コンストラクタ
     * @param mode true:編集画面 false:確認、参照画面
     */
    public ChangeLineFormatter(boolean mode) {
        this.mode = mode;
    }

    /**
     * フォーマット処理を行います。
     * @param target フォーマット対象文字
     * @param isForView true:表示用 false:値処理
     */
    public String format(final Object target, final boolean isForView) {
        if (target == null) {
            return null;
        }
        String msg = "";

        if (target instanceof String) {
            msg = (String) target;
        } else {
            msg.toString();
        }

        String addBrMsg = "";
        if (msg != null && !msg.equals("")) {
            int index = 0;
            boolean endFlg = true;

            while (endFlg) {
                index = msg.indexOf(getRepaceWord(isForView));
                if (index == -1) {
                    endFlg = false;
                } else {
                    String topMsg = msg.substring(0, index);
                    String bottomMsg = msg.substring(index
                            + getRepaceWord(isForView).length());
                    msg = topMsg + getChangeLineWord(isForView) + bottomMsg;
                }
            }
            addBrMsg = msg;
        }

        return addBrMsg;
    }

    /**
     * 置き換え文字を取得します。
     * @param isForView
     * @return
     */
    private String getRepaceWord(boolean isForView) {
        //表示用
        if (isForView) {
            return ENTER_WORD;

            //値処理用
        } else {
            if (isMode()) {
                return ENTER_KEY;
            }
            return BR;
        }
    }

    /**
     * 改行文字を取得します。
     * @param isForView
     * @return
     */
    private String getChangeLineWord(boolean isForView) {
        //表示用
        if (isForView) {
            if (isMode()) {
                return ENTER_KEY;

            } else {
                return BR;
            }
            //値処理用
        } else {
            return ENTER_WORD;
        }
    }

    /**
     * @return mode を戻します。
     */
    public boolean isMode() {
        return mode;
    }

    /**
     * @param mode mode を設定。
     */
    public void setMode(boolean mode) {
        this.mode = mode;
    }
}