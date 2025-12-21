package jp.co.isid.mos.bird.entry.mlentry.logic;

import jp.co.isid.mos.bird.entry.mlentry.dto.MlEntryDto;

/**
 * マスターライセンス情報の検索ロジックインターフェース
 * @author Aspac
 */
public interface SearchOnerLogic {

    /**
     * マスターライセンスエントリー情報の検索を行う
     * @param hanyoRegistDto
     */
    public void execute(MlEntryDto mlEntryDto);   
    
    /**
     * オーナー情報を検索設定する。
     * @param MlEntryDto
     */
    public void getEntrySofuOnerList(MlEntryDto dto);
    
    /**
     * オーナー情報を検索設定する。
     * @param MlEntryDto
     */
    public void getEntryOnerList(MlEntryDto dto);
    
    /**
     * オーナー情報の有無をチェックする。
     */
    public void isEntryOnerInfo(MlEntryDto dto);
}