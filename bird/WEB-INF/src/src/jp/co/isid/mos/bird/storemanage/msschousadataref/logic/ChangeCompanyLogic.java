package jp.co.isid.mos.bird.storemanage.msschousadataref.logic;

import jp.co.isid.mos.bird.storemanage.msschousadataref.dto.MssChousaDataRefDto;

/**
 * ミステリーショッパーズ 店舗別評価
 * 条件画面情報生成ロジック インターフェース
 * 
 * @author inazawa
 *
 */
public interface ChangeCompanyLogic {
    /** ロジックID定義 */
    public String execute(MssChousaDataRefDto dto) throws Exception;
}
