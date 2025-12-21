package jp.co.isid.mos.bird.bizsupport.common.logic;


import java.util.List;



/**
 * 業務支援ユーザー所属会社ロジック インターフェース
 * 
 * @author inazawa
 *
 */
public interface GetUserCompanyLogic {
    public List execute(String userId);
}
