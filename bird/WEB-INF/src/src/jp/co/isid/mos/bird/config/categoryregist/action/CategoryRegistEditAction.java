/*
 * ì¬“ú: 2006/02/17
 *
 */
package jp.co.isid.mos.bird.config.categoryregist.action;

/**
 * @author xyuchida
 *
 */
public interface CategoryRegistEditAction {

    /**
     * ‰Šúˆ—
     * 
     * @return ‰æ–Ê‘JˆÚî•ñ
     */
    public String initialize();

    /**
     * Šm”F
     * 
     * @return ‰æ–Ê‘JˆÚî•ñ
     */
    public String confirm();

    /**
     * –ß‚é
     * 
     * @return ‰æ–Ê‘JˆÚî•ñ
     */
    public String cancel();

    /**
     * s’Ç‰Á
     * 
     * @return ‰æ–Ê‘JˆÚî•ñ
     */
    public String addLine();
}
