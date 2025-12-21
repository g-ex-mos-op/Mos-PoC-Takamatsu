/*
 * ì¬“ú: 2007/02/21
 *
 */
package jp.co.isid.mos.bird.common.logic;

import java.util.List;

/**
 * @author xlee
 *
 */
public interface GetSibuHoyuTenpoLogic {

    public abstract List execute(String companyCd, String userId, boolean limit);
}
