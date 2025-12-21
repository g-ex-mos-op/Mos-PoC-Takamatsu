package jp.co.isid.mos.bird.common.logic;

import java.util.List;

public interface GetKanrenSakiBunshoInfoLogic {

    /**
     * 自分を関連先として登録している文書の取得します。
     * @param infoShu 情報種別
     * @param regDate 登録日
     * @param seq シーケンス番号
     * @return 関連文書情報
     */
    public abstract List execute(String infoShu, String regDate, String seq);

}