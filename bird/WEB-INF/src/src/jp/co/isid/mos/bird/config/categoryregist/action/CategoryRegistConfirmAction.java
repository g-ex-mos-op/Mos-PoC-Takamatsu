/*
 * ì¬“ú: 2006/02/17
 *
 */
package jp.co.isid.mos.bird.config.categoryregist.action;

/**
 * @author xyuchida
 *
 */
public interface CategoryRegistConfirmAction {

    /**
     * ‰Šúˆ—
     * 
     * @return ‰æ–Ê‘JˆÚî•ñ
     */
    public String initialize();

    /**
     * –ß‚é
     * 
     * @return ‰æ–Ê‘JˆÚî•ñ
     */
    public String cancel();

    /**
     * “o˜^
     * 
     * @return ‰æ–Ê‘JˆÚî•ñ
     */
    public String regist();
}
