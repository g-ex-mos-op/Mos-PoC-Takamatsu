package jp.co.isid.mos.bird.togouser.userregist.dao;

import jp.co.isid.mos.bird.togouser.userregist.entity.UIUserShozoku;

public interface UIUserShozokuDao {
    
    public static final Class BEAN = UIUserShozoku.class;
    
    /**
     * ƒ†[ƒUŒöŠJ‘ÎÛŠ‘®‹æ•ªŠÇ—‚ÌV‹K“o˜^(insertUserShozoku)
     * @param uiUserShozoku
     */
    public void insertUserShozoku(UIUserShozoku uiUserShozoku);

}
