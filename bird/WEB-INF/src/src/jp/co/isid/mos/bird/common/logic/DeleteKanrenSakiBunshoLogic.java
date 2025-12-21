package jp.co.isid.mos.bird.common.logic;

public interface DeleteKanrenSakiBunshoLogic {

    /**
     * 自分を関連文書として登録している情報を削除します。
     * @param infoShu 情報種別
     * @param regDate 登録日
     * @param seq シーケンス番号
     */
    public abstract void execute(String infoShu, String regDate, String seq);

}