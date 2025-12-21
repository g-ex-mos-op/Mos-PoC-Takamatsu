package jp.co.isid.mos.bird.bizreport.posreportregist.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.posreportregist.action.PosReportRegistConfirmAction;
import jp.co.isid.mos.bird.bizreport.posreportregist.common.PosReportRegistConstants;
import jp.co.isid.mos.bird.bizreport.posreportregist.dto.PosReportRegistDto;
import jp.co.isid.mos.bird.bizreport.posreportregist.logic.RegistLogic;
import jp.co.isid.mos.bird.bizreport.posreportregist.logic.impl.RegistPosReportLogicImpl;
import jp.co.isid.mos.bird.entry.ownerchange.common.OwnerChangeConstants;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;


/**
 * POS速報設定登録（確認画面）
 * @author Aspac
 *
 * 更新日:2010/11/09 ASPAC Ｐ６対応仕様変更対応
 *        新規テーブルBR82RTSM(店舗別リアルタイム集信期間マスタ)を使用し、
 *        全店対象期間設定機能を設けます。
 */
public class PosReportRegistConfirmActionImpl implements PosReportRegistConfirmAction {
    
    /*アクションID:初期化処理*/
    public static final String initialize_ACTION_ID  = "BBR007A11";
    /*アクションID:戻る処理*/
    public static final String back_ACTION_ID        = "BBR007A12";
    /*アクションID:登録処理*/
    public static final String regist_ACTION_ID      = "BBR007A13";

    /** BIRD日付情報 */
    private BirdDateInfo birdDateInfo;
    /** BIRDユーザー情報 */
    private BirdUserInfo birdUserInfo;
    /**
     * LOGIC【登録】
     */
    private RegistLogic posReportRegistLogic;
    /**
     * POS速報設定登録ロジック
     */
    private RegistPosReportLogicImpl registPosReportLogic;
    
    /**
     * POS速報設定DTO
     */
    private PosReportRegistDto posReportRegistDto;

    /**
     * セッションキー作成クラス
     */
    private MakeSessionKey makeSessionKey = new MakeSessionKey();


    /**
     * 初期化処理
     */
    public String initialize() {
        
		// 自画面へ遷移
		return null;
	}
    
    
    /**
     * 戻るボタン処理
     * 
     * 
     */
    public String back() {
        
        // セッションキーチェック
        if(!isValidSessionKey()) {
            return OwnerChangeConstants.VIEW_ID_OPERATION_ERR;            
        }
        return PosReportRegistConstants.VIEWID_REGIST;
    }
    
    
    /**
     * 登録
     */
    public String regist() {
        
        // セッションキーチェック
        if(!isValidSessionKey()) {
            return OwnerChangeConstants.VIEW_ID_OPERATION_ERR;            
        }
        
        // POS速報設定リスト取得
        List listRegist = getPosReportRegistDto().getListRegistData();
        
        // POS速報設定登録ロジック
        getRegistPosReportLogic().execute(listRegist, getBirdUserInfo().getUserID());
        // LOGIC【登録】
        getPosReportRegistLogic().execute(getBirdUserInfo(), getPosReportRegistDto());
        
        // 登録完了フラグ
        getPosReportRegistDto().setRegistFlg(true);
        
        
        return PosReportRegistConstants.VIEWID_REGIST;
    }


    /**
     * セッションキーチェック
     * @return セッションキーチェック結果
     */
    private boolean isValidSessionKey(){
         boolean result = makeSessionKey.isValidSessionKey(
                 posReportRegistDto.getNowSessionKey(), posReportRegistDto.getSessionKey());
         
         return result;
    }
    
    public PosReportRegistDto getPosReportRegistDto() {
        return posReportRegistDto;
    }
    public void setPosReportRegistDto(PosReportRegistDto posReportRegistDto) {
        this.posReportRegistDto = posReportRegistDto;
    }
    
    public RegistPosReportLogicImpl getRegistPosReportLogic() {
        return registPosReportLogic;
    }
    public void setRegistPosReportLogic(RegistPosReportLogicImpl registPosReportLogic) {
        this.registPosReportLogic = registPosReportLogic;
    }
	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}


	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}


	public RegistLogic getPosReportRegistLogic() {
		return posReportRegistLogic;
	}


	public void setPosReportRegistLogic(RegistLogic posReportRegistLogic) {
		this.posReportRegistLogic = posReportRegistLogic;
	}


	public BirdDateInfo getBirdDateInfo() {
		return birdDateInfo;
	}


	public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
		this.birdDateInfo = birdDateInfo;
	}

    
    
}
