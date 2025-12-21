/*
 * 作成日: 2006/4/12
 */
package jp.co.isid.mos.bird.bizsupport.similarshop.logic;

import java.util.List;

/**
 * オーナー保有店舗の検索ロジックインターフェース
 * @author Nakajima
 */
public interface GetOnerTenpoInfoLogic {

    public List execute(String sysdate, String onerCd, String nenGetu);
}
