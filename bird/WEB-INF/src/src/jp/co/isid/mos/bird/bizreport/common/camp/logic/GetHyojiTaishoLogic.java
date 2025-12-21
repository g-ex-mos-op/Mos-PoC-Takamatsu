package jp.co.isid.mos.bird.bizreport.common.camp.logic;

import java.util.Map;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;


/**
 * 業務管理　表示対象項目ロジック インターフェース
 * @author xkinu
 *
 */
public interface GetHyojiTaishoLogic {
	public static final String RK_LIST = "list";
	public static final String RK_LIST_BLOCK = "listBlock";
	
    /**
     * 処理実行
     * 
     * @param dataInfo
     * @param userInfo
     * @param companyCd
     * @param campId
     * @param taishoJoken
     * @return
     */
     public Map execute(BirdDateInfo dataInfo, BirdUserInfo userInfo, String companyCd, String campId, String taishoJoken);
}
