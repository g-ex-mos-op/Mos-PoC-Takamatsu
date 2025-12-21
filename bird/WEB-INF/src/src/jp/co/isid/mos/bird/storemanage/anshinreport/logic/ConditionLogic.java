package jp.co.isid.mos.bird.storemanage.anshinreport.logic;

import java.util.Map;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.storemanage.anshinreport.dto.AnshinReportDto;


/**
 * あんしん点検結果報告
 * 条件画面情報生成ロジック インターフェース
 * 
 * @author kawashima
 *
 */
public interface ConditionLogic {
    public Map execute(AnshinReportDto dto, BirdUserInfo userInfo, BirdDateInfo dateInfo) throws Exception;


}
