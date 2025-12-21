/*
 * ì¬“ú: 2006/06/07
 *
 */
package jp.co.isid.mos.bird.storemanage.staffregist.logic;

import jp.co.isid.mos.bird.storemanage.staffregist.entity.MstStaff;

/**
 * @author xyuchida
 */
public interface CreateStaffInfoLogic {

    public MstStaff execute(String companyCd, String onerCd);
}
