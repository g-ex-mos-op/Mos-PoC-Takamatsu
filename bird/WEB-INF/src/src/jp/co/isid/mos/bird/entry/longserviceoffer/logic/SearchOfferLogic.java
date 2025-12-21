/*
 * 作成日: 2006/4/19
 */
package jp.co.isid.mos.bird.entry.longserviceoffer.logic;

import java.util.List;
import jp.co.isid.mos.bird.entry.longserviceoffer.entity.UIOfferMst;
import jp.co.isid.mos.bird.entry.longserviceoffer.entity.UIOfferOner;

import jp.co.isid.mos.bird.entry.longserviceoffer.dto.LongserviceOfferDto;
import jp.co.isid.mos.bird.entry.nationalentry.dto.NationalEntryDto;

/**
 * エントリーマスタ管理の検索ロジックインターフェース
 * @author itamoto
 */
public interface SearchOfferLogic {

    /**
     * エントリーマスタ管理・エントリー日付管理の検索を行う
     * @param longserviceOfferDto
     * @return 永年勤続責任者情報
     */
    public UIOfferMst executeMst(LongserviceOfferDto longserviceOfferDto,NationalEntryDto nDto,int initFlg);
    
    /**
     * エントリーマスタ管理・エントリー日付管理の検索を行う
     * @param longserviceOfferDto
     * @return 永年勤続申請者情報リスト
     */
    public List executeInfo(LongserviceOfferDto longserviceOfferDto);
    
    /**
     * オーナー情報の検索を行う
     * @param companyCd
     * @param onerCd
     * @return オーナー情報
     */
    public UIOfferOner executeOner(String companyCd, String onerCd);
    
    /**
     * 最大ソート番号を取得する
     * @param  longserviceOfferDto
     * @return 永年勤続責任者情報
     */
    public String getMaxSeqNo(LongserviceOfferDto dto);
    
    /**
     * データカウントを取得する
     * @param  longserviceOfferDto
     * @return 最大ソート番号
     */
    public int getEntryCount(LongserviceOfferDto dto);
}
