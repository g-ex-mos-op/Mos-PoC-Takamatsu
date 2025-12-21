/*
 * 作成日: 2006/04/19
 */
package jp.co.isid.mos.bird.storemanage.mlholderlist.dao;

import java.util.List;
import jp.co.isid.mos.bird.storemanage.mlholderlist.entity.UIMLHolder;
import jp.co.isid.mos.bird.storemanage.mlholderlist.dto.MlHolderListDto;

/**
 * マスタライセンス保持者一覧
 * @author xylee
 */
public interface UIMLHolderDao {

    public static final Class BEAN = UIMLHolder.class;

    /**
     * マスタライセンス保持者の検索
     * @param MlHolderListDto
     * @return List
     */
    public List selectMLHoder(MlHolderListDto dto);
   
}