/*
 * 作成日: 2006/3/10
 */
package jp.co.isid.mos.bird.storeinfo.onerref.logic;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.onerref.dto.OwnerReferenceDto;

/**
 * オーナー照会リンク情報の取得ロジックインターフェース
 * @author itamoto
 */
public interface OnerLinkJohoLogic {

    public List execute(OwnerReferenceDto ownerReferenceDto);
}
