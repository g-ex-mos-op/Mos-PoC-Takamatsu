/*
 * ì¬“ú: 2006/01/30
 *
 */
package jp.co.isid.mos.bird.commonform.publictargetsearch.action;

/**
 * @author xytamura
 *
 */
public interface PublicTargetSearchAction {
    /**
     * ‰Šúˆ—
     */
    public abstract String initialize();

    /**
     * ‰ïĞ‚ª‘I‘ğ‚³‚ê‚½‚Ìˆ—
     * @return
     */
    public abstract String selectCompany();

    /**
     * –ß‚é
     * @return
     */
    public abstract String cancel();

    /**
     * ƒNƒŠƒA
     * @return
     */
    public abstract String clear();

    /**
     * Œˆ’è
     * @return
     */
    public abstract String select();

    /**
     * ŒÂ•Êİ’è
     * @return
     */
    public abstract String kogetuSet();
    
    /**
     * Ø‘Ö
     */
    public abstract String kirikae();
}