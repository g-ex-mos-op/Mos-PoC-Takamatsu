package jp.co.isid.mos.bird.portal.login.action.impl;

import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.portal.login.action.MatrixRegistAction;
import jp.co.isid.mos.bird.portal.login.dto.MatrixRegistDto;
import jp.co.isid.mos.bird.portal.login.logic.impl.UpdateMatrixInfoLogicImpl;


/**
 * @author xnkusama
 *
 */
public class MatrixRegistActionImpl implements MatrixRegistAction {

    /* アクションID */
    public static final String init_ACTION_ID           = "BPO000A08";
    public static final String regist_ACTION_ID         = "BPO000A09";
    public static final String backFromRegist_ACTION_ID = "BPO000A10";
    // DTO
    private MatrixRegistDto _matrixRegistDto;
    /* LOGIC */
    private UpdateMatrixInfoLogicImpl updateMatrixInfoLogicImpl;
    
    /**
     * BIRDユーザー情報
     */
    private BirdUserInfo birdUserInfo;
    
    /**
     * DTO設定処理
     * @param matrixRegistDto
     */
    public void setMatrixRegistDto(final MatrixRegistDto matrixRegistDto) {
        this._matrixRegistDto = matrixRegistDto;
    }
    /**
     * DTO取得処理
     * @return
     */
    private MatrixRegistDto getMatrixRegistDto() {
        return this._matrixRegistDto;
    }

    /**
     * BIRDユーザー情報を設定します
     * @param birdUserInfo birdUserInfo を設定。
     */
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

    /**
     * BIRDユーザー情報を取得します
     * @return birdUserInfo を戻します。
     */
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }
    
    /**
     * マトリクス認証登録画面 戻るアクション
     * @return
     */
    public String backFromRegist() {
        return getMatrixRegistDto().getPageKey();
    }

    /**
     * 初期処理
     */
    public String init() {
        getMatrixRegistDto().clear();
        return null;
    }
    /**
     * マトリクス認証登録画面 実行アクション
     * @return
     * @exception ApplicationException
     */
    public String regist() throws ApplicationException {
        MatrixRegistDto dto = getMatrixRegistDto();
        dto.setUserId(getBirdUserInfo().getUserID());
        getUpdateMatrixInfoLogicImpl().updateMatrixInfo(dto);
        
        // マトリクス認証情報 取得
        return "BPO000V02";
    }
    
    /**
     * スキップ
     */
    public String skip() {
        return "BPO001V01";
    }
    public UpdateMatrixInfoLogicImpl getUpdateMatrixInfoLogicImpl() {
        return updateMatrixInfoLogicImpl;
    }
    public void setUpdateMatrixInfoLogicImpl(
            UpdateMatrixInfoLogicImpl updateMatrixInfoLogicImpl) {
        this.updateMatrixInfoLogicImpl = updateMatrixInfoLogicImpl;
    }

}