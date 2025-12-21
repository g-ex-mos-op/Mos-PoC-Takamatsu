package jp.co.isid.mos.bird.entry.basicentry.dao;

import jp.co.isid.mos.bird.entry.basicentry.entity.TrnEntryResult;

/**
 * Œ¤CŒ‹‰Êó‹µ‚ğæ“¾‚·‚é
 * @author kusama
 */
public interface TrnEntryResultDao {

    public static final Class BEAN = TrnEntryResult.class;
    public static final String getCount_ARGS = "entryCd, entryYear, entryKai, staffId";
    public static final String insertEntry_ARGS = "trnEntryResult";
    public static final String deleteTrnEntryResult_ARGS = "entryCd, entryYear, entryKai, staffId";

    /**
     * Œ¤CŒ‹‰Êó‹µ‚ÌV‹K“o˜^(insertEntry)
     * @param trnEntryResult
     */
    public int insertEntry(TrnEntryResult trnEntryResult);

    /**
     * Œ¤CŒ‹‰Êó‹µ‚ÌŒ”æ“¾(getEntry)
     * @param entryCd ƒGƒ“ƒgƒŠ[ƒR[ƒh
     * @param staffId ƒXƒ^ƒbƒtID
     * @return int ŒŸõŒ‹‰Ê
     */
    public int getCount(String entryCd, String entryYear, String entryKai, String staffId);

    /**
     * Œ¤CŒ‹‰Êó‹µ‚Ìíœ
     * @param entryCd
     * @param entryYear
     * @param entryKai
     * @param staffId
     * @return
     */
    public int deleteTrnEntryResult(String entryCd, String entryYear, String entryKai, String staffId);
}