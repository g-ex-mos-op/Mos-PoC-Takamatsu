/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.posreportref.logic;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.posreportref.dto.PosReportRefDto;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * 店一覧検索ロジックインターフェース
 * 
 * 作成日:2010/10/25
 * @author xkinu
 *
 */
public interface SearchMiseListLogic {
	   /**
	    * 店一覧POS件数取得
	    * @param posReportRefDto
	    * @return
	    */
	   public List execute(
			   BirdUserInfo birdUserInfo
			 , PosReportRefDto posReportRefDto);

}
