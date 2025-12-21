/*
 * 作成日: 2006/3/14
 */
package jp.co.isid.mos.bird.bizsupport.plregist.action.impl;

import java.util.Iterator;

import jp.co.isid.mos.bird.bizsupport.lumptakein.dto.LumpTakeInDto;
import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLInfo;
import jp.co.isid.mos.bird.bizsupport.plregist.action.PlRegistEditAction;
import jp.co.isid.mos.bird.bizsupport.common.dto.PlRegistDto;
import jp.co.isid.mos.bird.bizsupport.common.dto.PlRegistStateDto;
import jp.co.isid.mos.bird.bizsupport.plregist.dto.SessionKeyDto;
import jp.co.isid.mos.bird.bizsupport.plregist.entity.MSTUserMiseInfo;
import jp.co.isid.mos.bird.bizsupport.plregist.entity.TrnZenPLInfo;
import jp.co.isid.mos.bird.bizsupport.plregist.logic.RenewPLDataLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

import org.seasar.framework.log.Logger;

/**
 * P/Lデータ入力　確認画面アクションクラス
 * @author itamoto
 */
public class PlRegistConfirmActionImpl implements PlRegistEditAction {

    private static Logger logger_ = Logger.getLogger(PlRegistEditActionImpl.class);

    /* アクションID */
    public static String initialize_ACTION_ID        = "BBS001A08";
    public static String changeTab_ACTION_ID         = "BBS001A09";
    public static String execute_ACTION_ID           = "BBS001A10";
    public static String cancel_ACTION_ID            = "BBS001A11";
    
    /* session key 画面保持パラメータDTO */
    private SessionKeyDto plRegistSessionKeyDto;

    /* P/Lデータ入力用Dto */
    private PlRegistDto plRegistDto;
    /* CSV一括取込DTO */
    private LumpTakeInDto lumpTakeInDto;

    /* TrnPLInfo */
    private TrnPLInfo trnPLInfo;
    /* TrnZenPLInfo */
    private TrnZenPLInfo trnZenPLInfo;
    
    /* ログイン情報 */
    private BirdUserInfo birdUserInfo;
    /* システム日付情報 */
    private BirdDateInfo birdDateInfo;
    
    /* PLデータ更新ロジック */
    private RenewPLDataLogic renewPLDataLogic;
    
    /* 登録状況確認用Dto */
    private PlRegistStateDto plRegistStateDto;
    

	public PlRegistStateDto getPlRegistStateDto() {
        return plRegistStateDto;
    }
    public void setPlRegistStateDto(PlRegistStateDto plRegistStateDto) {
        this.plRegistStateDto = plRegistStateDto;
    }
    /**
	 * session key 画面保持パラメータDTOの設定
	 * @return plRegistSessionKeyDto を戻します。
	 */
	public SessionKeyDto getPlRegistSessionKeyDto() {
		return plRegistSessionKeyDto;
	}
	/**
	 * session key 画面保持パラメータDTOの設定
	 * @param plRegistSessionKeyDto plRegistSessionKeyDto を設定。
	 */
	public void setPlRegistSessionKeyDto(SessionKeyDto plRegistSessionKeyDto) {
		this.plRegistSessionKeyDto = plRegistSessionKeyDto;
	}

    /**
	 * P/Lデータ入力用Dtoの設定
	 * @return plRegistDto を戻します。
	 */
	public PlRegistDto getPlRegistDto() {
		return plRegistDto;
	}
	/**
	 * P/Lデータ入力用Dtoの設定
	 * @param plRegistDto plRegistDto を設定。
	 */
	public void setPlRegistDto(PlRegistDto plRegistDto) {
		this.plRegistDto = plRegistDto;
	}

	/**
	 * CSV一括取込DTOの設定
	 * @return lumpTakeInDto を戻します。
	 */
	public LumpTakeInDto getLumpTakeInDto() {
		return lumpTakeInDto;
	}
	/**
	 * CSV一括取込DTOの設定
	 * @param lumpTakeInDto lumpTakeInDto を設定。
	 */
	public void setLumpTakeInDto(LumpTakeInDto lumpTakeInDto) {
		this.lumpTakeInDto = lumpTakeInDto;
	}

	/**
	 * P/Lデータの設定
	 * @return trnPLInfo を戻します。
	 */
	public TrnPLInfo getTrnPLInfo() {
		return trnPLInfo;
	}
	/**
	 * P/Lデータの設定
	 * @param trnPLInfo trnPLInfo を設定。
	 */
	public void setTrnPLInfo(TrnPLInfo trnPLInfo) {
		this.trnPLInfo = trnPLInfo;
	}
	/**
	 * 前月P/Lデータの設定
	 * @return trnZenPLInfo を戻します。
	 */
	public TrnZenPLInfo getTrnZenPLInfo() {
		return trnZenPLInfo;
	}
	/**
	 * 前月P/Lデータの設定
	 * @param trnZenPLInfo trnZenPLInfo を設定。
	 */
	public void setTrnZenPLInfo(TrnZenPLInfo trnZenPLInfo) {
		this.trnZenPLInfo = trnZenPLInfo;
	}

	/**
	 * 日付情報の設定
	 * @return birdDateInfo を戻します。
	 */
	public BirdDateInfo getBirdDateInfo() {
		return birdDateInfo;
	}
	/**
	 * 日付情報の設定
	 * @param birdDateInfo birdDateInfo を設定。
	 */
	public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
		this.birdDateInfo = birdDateInfo;
	}

	/**
	 * BIRDログイン情報の設定
	 * @return birdUserInfo を戻します。
	 */
	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}
	/**
	 * BIRDログイン情報の設定
	 * @param birdUserInfo birdUserInfo を設定。
	 */
	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}

	/**
	 * PLデータ更新ロジックの設定
	 * @return renewPLDataLogic を戻します。
	 */
	public RenewPLDataLogic getRenewPLDataLogic() {
		return renewPLDataLogic;
	}
	/**
	 * PLデータ更新ロジックの設定
	 * @param renewPLDataLogic renewPLDataLogic を設定。
	 */
	public void setRenewPLDataLogic(RenewPLDataLogic renewPLDataLogic) {
		this.renewPLDataLogic = renewPLDataLogic;
	}
	
	/**
     * 初期処理
     * @return 画面遷移情報
     */
	public String initialize() {

		// WARNINGメッセージセット
		plRegistDto.setWarningMsg(trnPLInfo);

        // 店舗選択プルダウンのデフォルトを、現在の店舗の次の店舗にする。
        // 本社P/Lの場合、店舗リストの最初を指定
        if (plRegistDto.getPlFlg() == 1) {
            for (Iterator it = plRegistDto.getStorePlList().iterator(); it.hasNext();) {
            	MSTUserMiseInfo mstUserMiseInfo = (MSTUserMiseInfo) it.next();
            	plRegistDto.setStorePlMiseCd(mstUserMiseInfo.getMiseCd());
            	break;
            }
        }
        // 店舗P/Lの場合、現在編集店舗の次の店舗を指定
        else if (plRegistDto.getPlFlg() == 0) {
        	boolean findFlg = false;
        	boolean setFlg = false;
            for (Iterator it = plRegistDto.getStorePlList().iterator(); it.hasNext();) {
            	MSTUserMiseInfo mstUserMiseInfo = (MSTUserMiseInfo) it.next();
            	if (findFlg) {
                	plRegistDto.setStorePlMiseCd(mstUserMiseInfo.getMiseCd());
                	setFlg = true;
                	break;
            	}
            	if (mstUserMiseInfo.getMiseCd().equals(trnPLInfo.getMiseCd())) {
            		findFlg = true;
            	}
            }
            if (setFlg == false) {
                for (Iterator it = plRegistDto.getStorePlList().iterator(); it.hasNext();) {
                	MSTUserMiseInfo mstUserMiseInfo = (MSTUserMiseInfo) it.next();
                	plRegistDto.setStorePlMiseCd(mstUserMiseInfo.getMiseCd());
                	break;
                }            	
            }
        }
        
        return null;
    }

    /**
     * タブ切り替え
     * @return 画面遷移情報
     */
    public String changeTab() {
    	// 【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
		if (!plRegistDto.isValidSessionKey(plRegistSessionKeyDto
				.getSessionKey(), plRegistDto.getSessionKey())) {
			return plRegistDto.operationErr_VIEW_ID;
		}
		return null;
	}

    /**
	 * 実行
	 * @return 画面遷移情報
	 */
    public String execute(){
    	// 【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
		if (!plRegistDto.isValidSessionKey(plRegistSessionKeyDto
				.getSessionKey(), plRegistDto.getSessionKey())) {
			return plRegistDto.operationErr_VIEW_ID;
		}
    	plRegistDto.setBirdUserInfo(birdUserInfo);
    	plRegistDto.setBirdDateInfo(birdDateInfo);

    	// 登録
    	renewPLDataLogic.execute(trnPLInfo, plRegistDto);
    	
    	// 保持情報クリア
		setTrnPLInfo(new TrnPLInfo());
		setTrnZenPLInfo(new TrnZenPLInfo());
		plRegistDto.clear();
        
        // 登録状況確認画面へ
        getPlRegistStateDto().setPlYm(getPlRegistDto().getTargetYM());
        getPlRegistStateDto().setOwnerCd(getPlRegistDto().getOnerCd());
        getPlRegistStateDto().setCloseMiseFlg(getPlRegistDto().isCloseMiseFlg());
        getPlRegistStateDto().setMiseLinkFlg(true);
        getPlRegistStateDto().setNavigationCase(plRegistDto.select_VIEW_ID);
        return plRegistDto.stateConfirm_VIEW_ID;
    }

    /**
	 * 登録して次の店舗の編集画面を表示
	 * @return 画面遷移情報
	 */
    public String next(){
    	// 【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
		if (!plRegistDto.isValidSessionKey(plRegistSessionKeyDto
				.getSessionKey(), plRegistDto.getSessionKey())) {
			return plRegistDto.operationErr_VIEW_ID;
		}

		plRegistDto.setBirdUserInfo(birdUserInfo);
    	plRegistDto.setBirdDateInfo(birdDateInfo);

    	// 登録
    	renewPLDataLogic.execute(trnPLInfo, plRegistDto);
    	
    	// 保持情報クリア
		setTrnPLInfo(new TrnPLInfo());
		setTrnZenPLInfo(new TrnZenPLInfo());
		plRegistDto.clear();
		
		// 編集画面初期処理フラグON
		plRegistDto.setInitFlg(true);
		// 店舗P/Lに設定
		plRegistDto.setPlFlg(0);
		
    	return plRegistDto.edit_VIEW_ID;
    }

    /**
	 * 戻る
	 * @return 画面遷移情報
	 */
    public String cancel(){
    	// 【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
		if (!plRegistDto.isValidSessionKey(plRegistSessionKeyDto
				.getSessionKey(), plRegistDto.getSessionKey())) {
			return plRegistDto.operationErr_VIEW_ID;
		}
    	return plRegistDto.edit_VIEW_ID;
    }
}

