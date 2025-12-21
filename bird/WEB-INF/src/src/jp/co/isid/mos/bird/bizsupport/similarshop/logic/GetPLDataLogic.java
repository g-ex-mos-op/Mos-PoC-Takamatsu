/*
 * 作成日: 2006/4/12
 */
package jp.co.isid.mos.bird.bizsupport.similarshop.logic;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.bizsupport.similarshop.dto.RuijitenReferenceDto;

/**
 * 類似店情報の取得ロジックインターフェース
 * @author Nakajima
 */
public interface GetPLDataLogic {

    public void execute(RuijitenReferenceDto ruijitenReferenceDto, String miseCd, String sysdate, String area, String tenpoKeitai, String ritti, String uriageSitei, BigDecimal uriageMin, BigDecimal uriageMax, String openDtMin, String openDtMax, String userId, String userTypeCd, boolean limitFlg);
}
