package jp.co.isid.mos.bird.entry.mlviewlist.logic;

import java.util.List;

import jp.co.isid.mos.bird.entry.mlviewlist.dto.MlViewListDto;

/**
 * マスターライセンス一覧取得ロジックインターフェース
 * @author Aspac
 */
public interface GetMlListLogic {

    public List execute(MlViewListDto mlViewListCommondto);
    
}
