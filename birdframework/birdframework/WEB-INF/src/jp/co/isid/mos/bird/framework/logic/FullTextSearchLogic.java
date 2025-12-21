package jp.co.isid.mos.bird.framework.logic;

import jp.co.isid.mos.bird.framework.dto.FullTextSearchDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;

/**
 * FullTextSearchロジック Interface
 * @author xnkusama
 */
public interface FullTextSearchLogic {
    /**
     * FullTextSearch検索
     * @param fullTextSearchDto
     * @throws FtlSystemException
     */
    public void search(FullTextSearchDto fullTextSearchDto) throws FtlSystemException;
    
    /**
     * FullTextSearch検索結果とDB検索結果のマッチングを行う
     * @param dto  FullTextSearch検索結果
     * @param dbData DB検索結果（FileAccessInfoEntityを格納したList）
     * @return List マッチングの結果リスト
     */
    public void matchingData(FullTextSearchDto dto) throws FtlSystemException;
}
