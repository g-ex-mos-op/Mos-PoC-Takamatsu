/**
 *
 */
package jp.co.isid.mos.bird.bizsupport.pointmstregist.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.common.logic.GetSeidoPointInfoLogic;
import jp.co.isid.mos.bird.bizsupport.pointmstregist.action.PointMstRegistEditAction;
import jp.co.isid.mos.bird.bizsupport.pointmstregist.common.PointMstRegistConstants;
import jp.co.isid.mos.bird.bizsupport.pointmstregist.dto.PointDataDto;
import jp.co.isid.mos.bird.bizsupport.pointmstregist.logic.GetPointInfoLogic;
import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;

/**
 * ポイントマスタメンテナンス
 * @author yushuncheng
 *
 */
public class PointMstRegistEditActionImpl implements CommonAction, PointMstRegistEditAction {

	private final static String viewId = "BBS036";

    // DTO
    private PointDataDto pointDataDto;

    private PullDownMenuDto pullDownMenuDto;

	// Logic
    private GetPointInfoLogic getPointInfoLogic;

    private GetSeidoPointInfoLogic getSeidoPointInfoLogic;

    private BirdUserInfo birdUserInfo;

	/**
     * 編集画面
     *
     * @return 初期処理
     */
	public String initialize() {

		return null;
	}

    /**
     * 対象年度追加
     * @return 対象年度追加処理
     */
    public String addNendo() {

		getGetPointInfoLogic().addNendo(getPointDataDto());

    	return null;
    }

    /**
     * 登録
     * @return 登録
     */
	public String insert() {
		int editMode = getPointDataDto().getEditMode();
		//事前条件処理を実行する。
		if(getPointDataDto().checkInputData()) {
			if(editMode==1){
				// 「新規」モードの場合
				getGetPointInfoLogic().insertDate(getPointDataDto(), getBirdUserInfo());
			}else{
				// 「編集」モードの場合
				getGetPointInfoLogic().updateDate(getPointDataDto(), getBirdUserInfo());
			}
			getPullDownMenuDto().setClearFlg(true);
			return PointMstRegistConstants.SCREEN_ID;
		}
		return null;
	}

    /**
     * 年度削除
     * @return 年度削除処理
     */
    public String deleteNendo() {

    	List listNendo = getPointDataDto().getNendoPointList();
    	String selectNo = getPointDataDto().getDeleteNo();
    	int deleteNo = -1;
    	// 年度indexの有効ｚチェック
    	if(selectNo != null && !selectNo.trim().isEmpty()) {
    		try{
    			deleteNo = new Integer(selectNo);
    		}catch(Exception e) {}
    	}
    	// 年度削除
    	if(deleteNo >= 0) {
    		listNendo.remove(deleteNo);
        	getPointDataDto().setNendoPointList(listNendo);
    	}
    	// 年度削除indexの初期化
    	getPointDataDto().setDeleteNo("");

    	return null;
    }

    /**
     * 戻る
     * @return 画面遷移情報
     */
    public String back() {
        // 条件画面へ遷移
        return PointMstRegistConstants.SCREEN_ID;
    }


    public PointDataDto getPointDataDto() {
		return pointDataDto;
	}

	public void setPointDataDto(PointDataDto pointDataDto) {
		this.pointDataDto = pointDataDto;
	}

	public GetPointInfoLogic getGetPointInfoLogic() {
		return getPointInfoLogic;
	}

	public void setGetPointInfoLogic(GetPointInfoLogic getPointInfoLogic) {
		this.getPointInfoLogic = getPointInfoLogic;
	}

	public GetSeidoPointInfoLogic getGetSeidoPointInfoLogic() {
		return getSeidoPointInfoLogic;
	}

	public void setGetSeidoPointInfoLogic(GetSeidoPointInfoLogic getSeidoPointInfoLogic) {
		this.getSeidoPointInfoLogic = getSeidoPointInfoLogic;
	}

	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}

	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}

	public PullDownMenuDto getPullDownMenuDto() {
		return pullDownMenuDto;
	}

	public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
		this.pullDownMenuDto = pullDownMenuDto;
	}

}
