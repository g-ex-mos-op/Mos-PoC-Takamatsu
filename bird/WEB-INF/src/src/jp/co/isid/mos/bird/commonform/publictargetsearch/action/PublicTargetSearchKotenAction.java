/*
 * 作成日: 2006/01/30
 *
 */
package jp.co.isid.mos.bird.commonform.publictargetsearch.action;

/**
 * @author xytamura
 *
 */
public interface PublicTargetSearchKotenAction {
    /**
     * 戻る
     * @return
     */
    public abstract String cancel();

    /**
     * 決定
     * @return
     */
    public abstract String select();

    /**
     * ファイル読込
     * @return
     */
    public abstract String fileSelect();

    /**
     * クリア
     * @return
     */
    public abstract String clear();
}