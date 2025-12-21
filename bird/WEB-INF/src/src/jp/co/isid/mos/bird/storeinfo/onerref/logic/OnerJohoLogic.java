/*
 * 作成日: 2006/3/7
 */
package jp.co.isid.mos.bird.storeinfo.onerref.logic;

import jp.co.isid.mos.bird.storeinfo.onerref.dto.OwnerReferenceDto;
import jp.co.isid.mos.bird.storeinfo.onerref.entity.MstOner;

/**
 * オーナー情報の取得ロジックインターフェース
 * @author itamoto
 */
public interface OnerJohoLogic {

    public MstOner execute(OwnerReferenceDto ownerReferenceDto);
}
