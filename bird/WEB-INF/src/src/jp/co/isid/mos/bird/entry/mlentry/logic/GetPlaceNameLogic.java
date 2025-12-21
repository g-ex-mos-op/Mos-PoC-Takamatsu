package jp.co.isid.mos.bird.entry.mlentry.logic;


/**
 * 受験地名称取得ロジック
 * @author Aspac
 */
public interface GetPlaceNameLogic {

    public String execute(String entryCd, String entryYear, String entryKai, String entryPlaceCd);
    
}
