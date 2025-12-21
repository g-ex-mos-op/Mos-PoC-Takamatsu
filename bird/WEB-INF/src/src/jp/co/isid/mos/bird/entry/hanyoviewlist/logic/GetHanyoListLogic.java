/*
 * 作成日: 2006/5/26
 */
package jp.co.isid.mos.bird.entry.hanyoviewlist.logic;

import java.util.List;

import jp.co.isid.mos.bird.entry.hanyoviewlist.dto.HanyoViewListDto;

/**
 * 研修(出張/更新)一覧取得ロジックインターフェース
 * @author Nakajima
 */
public interface GetHanyoListLogic {

    public List execute(HanyoViewListDto hanyoViewListCommondto);
    
}
