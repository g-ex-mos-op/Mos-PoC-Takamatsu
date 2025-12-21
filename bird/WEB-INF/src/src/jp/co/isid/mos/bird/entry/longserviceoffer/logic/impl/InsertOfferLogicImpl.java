/*
 * 作成日: 2006/12/26
 */
package jp.co.isid.mos.bird.entry.longserviceoffer.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.entry.longserviceoffer.common.LongserviceOfferConstants;
import jp.co.isid.mos.bird.entry.longserviceoffer.dto.LongserviceOfferDto;
import jp.co.isid.mos.bird.entry.longserviceoffer.entity.UIOfferEntry;
import jp.co.isid.mos.bird.entry.longserviceoffer.logic.InsertOfferLogic;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * 入力欄追加処理
 * @author narita
 */
public class InsertOfferLogicImpl implements InsertOfferLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN018L03";

	/**
     * 入力欄の作成を行う
     * @param longserviceOfferDto
     */
    public UIOfferEntry getNewEntryData(LongserviceOfferDto dto) {
    	
    	NumericFormatter numFormatter = new NumericFormatter();
    	
    	// ＤＴＯから最大ソート番号を取得し、カウントアップ
    	int maxSeqNo = Integer.parseInt(dto.getMaxSeqNo()) + 1;
    	// 文字タイプへの変換 #0
    	String strMaxNo = numFormatter.format(String.valueOf(maxSeqNo),
    			LongserviceOfferConstants.SEQ_FORMAT);
    	
    	// 入力欄の新規作成
    	UIOfferEntry uIOfferEntry = new UIOfferEntry();
    	
    	// デフォルト値のセット
    	uIOfferEntry.setEntryCd(dto.getEntryCd());
    	uIOfferEntry.setEntryYear(dto.getEntryYear());
    	uIOfferEntry.setEntryKai(dto.getEntryKai());
    	uIOfferEntry.setCompanyCd(dto.getCompanyCd());
    	uIOfferEntry.setOnerCd(dto.getOnerCd());
    	uIOfferEntry.setSeqNo(strMaxNo);
    	//uIOfferEntry.setTblMode(LongserviceOfferConstants.TBL_MODE_OFF);
    	
    	return uIOfferEntry;
    }
    
	/**
     * 入力欄の追加処理を行う
     * @param longserviceOfferDto
     */
    public LongserviceOfferDto execute(LongserviceOfferDto dto) {
    	    	   	    	
    	// 入力欄追加処理を行う。
    	UIOfferEntry uIOfferEntry = this.getNewEntryData(dto);

    	// 入力欄が30個の場合は追加処理を行わない。（30個の場合はボタンが押せないが安全のため）
    	if(Integer.parseInt(uIOfferEntry.getSeqNo()) <= LongserviceOfferConstants.SEQ_MAX ){

	    	if(Integer.parseInt(uIOfferEntry.getSeqNo()) >= LongserviceOfferConstants.SEQ_MAX ){
	    		
	    		// 入力欄が30個になったら追加ボタンをロックする
	    		dto.setInsertBtnFlg(false);
	    		//throw new NoInputException("入力欄追加ボタンは３０個以内", "btnExecute", null);
	    	}
	    	// DTOに最大ソート番号をセット
	    	dto.setMaxSeqNo(uIOfferEntry.getSeqNo());
	    	// 入力欄を追加する
	    	List uIOfferEntryList = dto.getUIOfferEntryList();
	    	uIOfferEntryList.add(uIOfferEntry);
    	}
    	
    	return dto;
    }
}