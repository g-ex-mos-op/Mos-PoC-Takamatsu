package jp.co.isid.mos.bird.bizsupport.pointmstregist.action.impl;

import jp.co.isid.mos.bird.bizsupport.common.entity.UISeidoMst;
import jp.co.isid.mos.bird.bizsupport.common.logic.GetSeidoPointInfoLogic;
import jp.co.isid.mos.bird.bizsupport.pointmstregist.action.PointMstRegistRefAction;
import jp.co.isid.mos.bird.bizsupport.pointmstregist.common.PointMstRegistConstants;
import jp.co.isid.mos.bird.bizsupport.pointmstregist.dto.PointDataDto;
import jp.co.isid.mos.bird.bizsupport.pointmstregist.dto.SeidoDataDto;
import jp.co.isid.mos.bird.bizsupport.pointmstregist.logic.GetPointInfoLogic;
import jp.co.isid.mos.bird.bizsupport.pointmstregist.logic.GetToukyuInfoLogic;
import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;

/**
 * ポイントマスタメンテナンス
 * @author yushuncheng
 *
 */
public class PointMstRegistRefActionImpl implements CommonAction, PointMstRegistRefAction {

	/* アクションID */
    public static final String initialize_ACTION_ID = "BBS036V01";
    public static final String add_ACTION_ID = "BBS036V02";
    public static final String edit_ACTION_ID = "BBS036V02";

    // DTO
    private SeidoDataDto seidoDataDto;
    private PullDownMenuDto pullDownMenuDto;
    private PointDataDto pointDataDto;

	// Logic
    private GetSeidoPointInfoLogic getSeidoPointInfoLogic;
    private GetToukyuInfoLogic getToukyuInfoLogic;
    private GetPointInfoLogic getPointInfoLogic;
    private BirdUserInfo birdUserInfo;

	/**
     * 初期処理
     *
     *  @return 画面遷移情報
     */
	@Override
	public String initialize() {

		// 画面初期表示処理
        if (getPullDownMenuDto().isClearFlg()) {

        	//----------------------
            // 株式報酬制度一覧取得
            //----------------------
        	getSeidoDataDto().setSeidoList(getGetSeidoPointInfoLogic().execute());

            //検索条件のデフォルトセット
            getSeidoDataDto().setCondTarget(0);
        	getPullDownMenuDto().setClearFlg(false);
        }

		return null;
	}

	/**
     * 新規処理
     *
     * @return 編集画面
     */
	@Override
    public String insert()  {

		PointDataDto pageDto = new PointDataDto();
		pageDto.setSeidoInfo(new UISeidoMst());
		setPointDataDto(pageDto);

		//----------------------
        // 会社等級一覧取得
        //----------------------
		getPointDataDto().setNendoPointList(getGetToukyuInfoLogic().execute());

		// 「新規」モードの設定
		getPointDataDto().setEditMode(1);
		return PointMstRegistConstants.VIEW_ID_EDIT;
    }

    /**
     * 編集処理
     * @return 編集画面
     */
    public String edit() {

    	// 選択された株式報酬制度情報取得
    	getGetPointInfoLogic().execute(getSeidoDataDto(), getPointDataDto());

		// 「編集」モードの設定
    	getPointDataDto().setEditMode(2);
		return PointMstRegistConstants.VIEW_ID_EDIT;
	}

    /**
     * 計画削除処理
     * @return  初期画面
     */
	public String delete() {
		String tourokuNo = getSeidoDataDto().getSeidoList().get(getSeidoDataDto().getCondTarget()).getTourokuNo();
		getGetPointInfoLogic().deleteDate(tourokuNo, getBirdUserInfo());
		getPullDownMenuDto().setClearFlg(true);
		return null;
	}

    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }

    public SeidoDataDto getSeidoDataDto() {
		return seidoDataDto;
	}

	public void setSeidoDataDto(SeidoDataDto seidoDataDto) {
		this.seidoDataDto = seidoDataDto;
	}

    public PointDataDto getPointDataDto() {
		return pointDataDto;
	}

	public void setPointDataDto(PointDataDto pointDataDto) {
		this.pointDataDto = pointDataDto;
	}

	public GetSeidoPointInfoLogic getGetSeidoPointInfoLogic() {
		return getSeidoPointInfoLogic;
	}

	public void setGetSeidoPointInfoLogic(GetSeidoPointInfoLogic getSeidoPointInfoLogic) {
		this.getSeidoPointInfoLogic = getSeidoPointInfoLogic;
	}

	public GetToukyuInfoLogic getGetToukyuInfoLogic() {
		return getToukyuInfoLogic;
	}

	public void setGetToukyuInfoLogic(GetToukyuInfoLogic getToukyuInfoLogic) {
		this.getToukyuInfoLogic = getToukyuInfoLogic;
	}

	public GetPointInfoLogic getGetPointInfoLogic() {
		return getPointInfoLogic;
	}

	public void setGetPointInfoLogic(GetPointInfoLogic getPointInfoLogic) {
		this.getPointInfoLogic = getPointInfoLogic;
	}

	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}

	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}
}
