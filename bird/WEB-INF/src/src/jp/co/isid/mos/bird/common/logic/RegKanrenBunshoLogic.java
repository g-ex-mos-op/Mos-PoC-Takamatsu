/*
 * ì¬“ú: 2006/02/07
 *
 */
package jp.co.isid.mos.bird.common.logic;

import java.util.List;

/**
 * @author xytamura
 *
 */
public interface RegKanrenBunshoLogic {
    /**
     * ŠÖ˜A•¶‘‚Ì“o˜^
     * @param infoShu î•ñí•Ê
     * @param regDate “o˜^“ú
     * @param seq ƒV[ƒPƒ“ƒX”Ô†
     * @param listUIDocSearch ŠÖ˜A•¶‘î•ñ
     * @param userId ƒ†[ƒU‚h‚c
     * @param programId ƒvƒƒOƒ‰ƒ€‚h‚c
     */
    public abstract void execute(String infoShu, String regDate, String seq,
            List listKanrenBunshoInfo, String userId, String programId);
}