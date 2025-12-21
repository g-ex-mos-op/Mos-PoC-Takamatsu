/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.energyamount.action.impl;

import java.io.IOException;

import jp.co.isid.mos.bird.bizsupport.energyamount.dto.SuiiRequestDto;
import jp.co.isid.mos.bird.bizsupport.energyamount.dto.SuiiSessionDto;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * エネルギーデータ推移表ＣＳＶダウンロードアクション
 * 
 * 作成日:2010/05/06
 * @author xkinu
 *
 */
public class CsvActionImpl extends CsvOutput2ActionImpl {
    public static final String downloadCsv_ACTION_ID = "BBS032A10";
    
    private BirdUserInfo birdUserInfo;
    private BirdDateInfo birdDateInfo;
    /** 推移表ダウンロードセッション情報保持DTO */
    private SuiiSessionDto energyamountSuiiSesDto;
    /** 推移表ダウンロードリクエスト情報保持DTO */
    private SuiiRequestDto energyamountSuiiReqDto;

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl#downloadCsv()
	 */
	public void downloadCsv() throws IOException, ApplicationException {
		getEnergyamountSuiiReqDto().setBirdUserInfo(getBirdUserInfo());
		getEnergyamountSuiiReqDto().setBirdDateInfo(getBirdDateInfo());
		// TODO 自動生成されたメソッド・スタブ
		super.downloadCsv();
	}

	/**
	 * @return クラス変数birdDateInfo を戻します。
	 */
	public BirdDateInfo getBirdDateInfo() {
		return birdDateInfo;
	}

	/**
	 * @param birdDateInfo を クラス変数birdDateInfoへ設定します。
	 */
	public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
		this.birdDateInfo = birdDateInfo;
	}

	/**
	 * @return クラス変数birdUserInfo を戻します。
	 */
	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}

	/**
	 * @param birdUserInfo を クラス変数birdUserInfoへ設定します。
	 */
	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}

	/**
	 * @return クラス変数energyamountSuiiReqDto を戻します。
	 */
	public SuiiRequestDto getEnergyamountSuiiReqDto() {
		return energyamountSuiiReqDto;
	}

	/**
	 * @param energyamountSuiiReqDto を クラス変数energyamountSuiiReqDtoへ設定します。
	 */
	public void setEnergyamountSuiiReqDto(SuiiRequestDto energyamountSuiiReqDto) {
		this.energyamountSuiiReqDto = energyamountSuiiReqDto;
	}

	/**
	 * @return クラス変数energyamountSuiiSesDto を戻します。
	 */
	public SuiiSessionDto getEnergyamountSuiiSesDto() {
		return energyamountSuiiSesDto;
	}

	/**
	 * @param energyamountSuiiSesDto を クラス変数energyamountSuiiSesDtoへ設定します。
	 */
	public void setEnergyamountSuiiSesDto(SuiiSessionDto energyamountSuiiSesDto) {
		this.energyamountSuiiSesDto = energyamountSuiiSesDto;
	}
    
}
