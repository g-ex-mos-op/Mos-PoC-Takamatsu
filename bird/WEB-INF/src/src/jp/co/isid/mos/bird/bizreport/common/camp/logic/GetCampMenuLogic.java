/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.common.camp.logic;

import java.util.List;

/**
 * ƒƒjƒ…[î•ñæ“¾
 * 
 * @author xkinu
 *
 */
public interface GetCampMenuLogic {
    /**
     * ˆ—Às
     * 
     * @param campId
     * @param menuTotaledKbn
     * @return
     */
     public List execute(String campId, String menuTotaledKbn);
}
