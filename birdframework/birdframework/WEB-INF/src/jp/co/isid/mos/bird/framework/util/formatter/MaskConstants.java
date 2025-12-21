/*
 * MaskConstants.java
 * 
 * Created by xytamura on 2005/10/27
 * Copy from 2ndGenesis
 */
package jp.co.isid.mos.bird.framework.util.formatter;

/**
 * Maskフォーマット処理を行うインタフェース。
 * @author ISID MOS PROJECT
 * @since  2003.07.22
 */
public interface MaskConstants {
    /**
     * 
     * @param MC_NUMBER MC_NUMBER
     */
    char MC_NUMBER = '#';

    /**
     * 
     * @param MC_UPPER MC_UPPER
     */
    char MC_UPPER = 'U';

    /**
     * 
     * @param MC_LOWER MC_LOWER
     */
    char MC_LOWER = 'L';

    /**
     * 
     * @param MC_NUMBER_OR_LETTER MC_NUMBER_OR_LETTER 
     */
    char MC_NUMBER_OR_LETTER = 'A';

    /**
     * 
     * @param MC_LETTER MC_LETTER
     */
    char MC_LETTER = '?';

    /**
     * 
     * @param MC_ALL MC_NUMBER
     */
    char MC_ALL = '*';
}