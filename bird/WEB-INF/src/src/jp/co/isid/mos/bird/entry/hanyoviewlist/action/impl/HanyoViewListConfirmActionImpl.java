package jp.co.isid.mos.bird.entry.hanyoviewlist.action.impl;

import java.io.IOException;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.entry.hanyoviewlist.action.HanyoViewListConfirmAction;
import jp.co.isid.mos.bird.entry.hanyoviewlist.dto.HanyoViewListDto;
import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.action.CsvOutputAction;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

public class HanyoViewListConfirmActionImpl implements CommonAction, HanyoViewListConfirmAction {

    /* アクションID */
    public static String initialize_ACTION_ID        = "BEN006A01";
    public static String execute_ACTION_ID           = "BEN006A02";
    public static String callMiseInfo_ACTION_ID      = "BEN006A03";
    public static String callMiseInfoResult_ACTION_ID = "BEN006A04";
    
    /* ビューID */
    private static final String VIEWID_CONDITION    = "BEN006V01"; //条件画面
    
    /* ACTION */
    private CsvOutputAction hanyoViewListCsvOutputAction;
    
    /* LOGIC */

    
    
    /* DTO */
    // 研修(出張/更新)申込状況確認Dto //
    private HanyoViewListDto hanyoViewListDto;
    // ベーシック研修申込状況確認CommonDto //
    private HanyoViewListDto hanyoViewListCommonDto;
   

    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    public BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }

	////////////////////
	/*     ACTION     */
	////////////////////

	
	/////////////////
	/*     DTO     */
	/////////////////
	/**
	 * 研修(出張/更新)申込状況確認dtoの設定
	 * @return hanyoViewListDto を戻します。
	 */
	public HanyoViewListDto getHanyoViewListDto() {
		return hanyoViewListDto;
	}
	/**
	 * 研修(出張/更新)申込状況確認dtoの設定
	 * @param hanyoViewListDto を設定。
	 */
	public void setHanyoViewListDto(HanyoViewListDto hanyoViewListDto) {
		this.hanyoViewListDto = hanyoViewListDto;
	}
    
    /**
     * 研修申込状況確認CommonDtoの設定
     * @return hanyoViewListCommonDto を戻します。
     */
    public HanyoViewListDto getHanyoViewListCommonDto() {
        return hanyoViewListCommonDto;
    }
    /**
     * 研修申込状況確認CommonDtoの設定
     * @param hanyoViewListCommonDto を設定。
     */
    public void setHanyoViewListCommonDto(HanyoViewListDto hanyoViewListCommonDto) {
        this.hanyoViewListCommonDto = hanyoViewListCommonDto;
    }
	
	
	///////////////////
	/*     LOGIC     */
	///////////////////



        
	/**
     * 研修(出張/更新)申込状況確認 照会画面 初期表示処理
     * @return 画面遷移情報
     */
    public String initialize() {
        
        return null;
    }

    
    /**
     * CSVダウンロード
     * @return
     */
    public String downloadCsv() {

        // CSVダウンロード
        try {
            getHanyoViewListCsvOutputAction().downloadCsv();
        } catch (IOException e) {
            throw new FtlSystemException("CSVダウンロード");
        }
        return null;
    }
    
    
    /**
     * 戻る遷移(条件画面へ)
     * @return 画面遷移情報
     */
    public String cancel() {
        // 対象研修コンボボックス設定
        if(hanyoViewListCommonDto.getKenshuKbn().equals("ALL")){
            hanyoViewListCommonDto.setEntryCd("ZZ");
        }
        
        return VIEWID_CONDITION;
    }
    

    public CsvOutputAction getHanyoViewListCsvOutputAction() {
        return hanyoViewListCsvOutputAction;
    }
    public void setHanyoViewListCsvOutputAction(
            CsvOutputAction hanyoViewListCsvOutputAction) {
        this.hanyoViewListCsvOutputAction = hanyoViewListCsvOutputAction;
    }
    
    
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }
    
    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    public BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    
}