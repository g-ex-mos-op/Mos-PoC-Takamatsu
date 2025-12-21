package jp.co.isid.mos.bird.bizreport.posreportref.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.posreportref.action.PosReportRefAction;
import jp.co.isid.mos.bird.bizreport.posreportref.dto.PosReportRefDto;
import jp.co.isid.mos.bird.bizreport.posreportref.dto.SessionDto;
import jp.co.isid.mos.bird.bizreport.posreportref.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizreport.posreportref.logic.LatestDateLogic;
import jp.co.isid.mos.bird.bizreport.posreportref.logic.PosReportRefMiseInfoLogic;
import jp.co.isid.mos.bird.bizreport.posreportref.logic.SearchMiseListLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.NoResultException;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * POS速報画面アクション
 * @author inazawa
 * 2007/02/07
 */
public class PosReportRefActionImpl implements PosReportRefAction{
    /*アクションID*/
    public static final String initialize_ACTION_ID = "BBR006A01";
    /*アクションID:実行／再検索*/
    public static final String search_ACTION_ID     = "BBR006A02";
    
    /*[プルダウンDTO]**/
    private PullDownMenuDto pullDownMenuDto;
    /*DTO[POS速報DTO]**/
    private PosReportRefDto posReportRefDto;
    /*LOGIC[最新日付取得]**/
    private LatestDateLogic latestDateLogic;
    /*LOGIC[店一覧情報取得]**/
    private PosReportRefMiseInfoLogic posReportRefMiseInfoLogic;
    /** DTO【セッション情報保持】*/
    private SessionDto posReportRefSessionDto;
    /** LOGIC【条件項目取得】*/
    private ConditionLogic posReportRefConditionLogic;
    /** LOGIC【店一覧検索】*/
    private SearchMiseListLogic posReportRefSearchMiseListLogic;

    /**
     * 初期処理
     */
    public String initialize() {
        if(getPullDownMenuDto().isClearFlg()){
            getPullDownMenuDto().setClearFlg(false);
            //４．ロジック【条件項目取得】を実行し、DTO【セッション情報】へ条件項目の設定処理を行います。
            getPosReportRefConditionLogic().execute(getBirdUserInfo()
            		, getPosReportRefSessionDto(), getPosReportRefDto());
        }
        getPosReportRefDto().setListHyojiTaisho(
        		getPosReportRefSessionDto().getListHyojiTaisho());
        return null;
    }
    /**
     * 実行／再検索処理
     * 作成日:2010/10/25
     * 
     * @return 初期画面(自画面)VIEW_ID
     */
    public String search() {
        //最新日付取得
        getPosReportRefDto().setLatestDate(getLatestDateLogic().execute(getPosReportRefDto()));
        //ロジック【ＰＯＳ速報（店舗一覧)取得】を実行し、List[[ＰＯＳ速報（推移)]]を取得します。
        List listMiseList = getPosReportRefSearchMiseListLogic().execute(getBirdUserInfo(), getPosReportRefDto());
        //５－２．処理５－１で取得したList[[ＰＯＳ速報（店舗一覧)]]を
        //        DTO【リクエスト情報】.List[[ＰＯＳ速報（店舗一覧)]]へ設定します。
        getPosReportRefDto().setListPosReportMiseInfo(listMiseList);
    	//検索結果の該当データが存在しない場合は下記の処理を行います。
    	if (getPosReportRefDto().getListPosReportMiseInfo().size()==0) {
    		//DTO[POS速報DTO].List[[ポス速報(店舗一覧)]]へnullを設定します。
    		getPosReportRefDto().setListPosReportMiseInfo(null);
    		//Exceptionを発生させます。
    		throw new NoResultException();
    	}
    	return null;
    }
    /**
     * pullDownMenuDtoを取得
     * @return pullDownMenuDto
     */
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }
    /**
     * pullDownMenuDtoを設定
     * @param pullDownMenuDto
     */
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }

    /**
     * posReportRefDtoを取得
     * @return posReportRefDto
     */
    public PosReportRefDto getPosReportRefDto() {
        return posReportRefDto;
    }
    /**
     * posReportRefDtoを設定
     * @param posReportRefDto
     */
    public void setPosReportRefDto(PosReportRefDto posReportRefDto) {
        this.posReportRefDto = posReportRefDto;
    }
    
    /**
     * コンテナーの取得
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }
    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }

    /**
     * latestDateLogicを取得
     * @return latestDateLogic
     */
    public LatestDateLogic getLatestDateLogic() {
        return latestDateLogic;
    }

    /**
     * latestDateLogicを設定
     * @param latestDateLogic
     */
    public void setLatestDateLogic(LatestDateLogic latestDateLogic) {
        this.latestDateLogic = latestDateLogic;
    }
    /**
     * posReportRefMiseInfoLogicを取得
     * @return posReportRefMiseInfoLogic
     */
    public PosReportRefMiseInfoLogic getPosReportRefMiseInfoLogic() {
        return posReportRefMiseInfoLogic;
    }
    /**
     * posReportRefMiseInfoLogicを設定
     * @param posReportRefMiseInfoLogic
     */
    public void setPosReportRefMiseInfoLogic(
            PosReportRefMiseInfoLogic posReportRefMiseInfoLogic) {
        this.posReportRefMiseInfoLogic = posReportRefMiseInfoLogic;
    }

    /**
     * LOGIC【条件項目取得】取得処理
     * 作成日:2010/10/25
     * 
     * @return　LOGIC【条件項目取得】
     */
	public ConditionLogic getPosReportRefConditionLogic() {
		return posReportRefConditionLogic;
	}
	/**
	 * LOGIC【条件項目取得】設定処理
     * 作成日:2010/10/25
	 * 
	 * @param posReportRefConditionLogic　LOGIC【条件項目取得】
	 */
	public void setPosReportRefConditionLogic(
			ConditionLogic posReportRefConditionLogic) {
		this.posReportRefConditionLogic = posReportRefConditionLogic;
	}
	/**
	 * DTO【セッション情報保持】取得処理
     * 作成日:2010/10/25
	 * 
	 * @return
	 */
	public SessionDto getPosReportRefSessionDto() {
		return posReportRefSessionDto;
	}
	/**
	 * DTO【セッション情報保持】設定処理
     * 作成日:2010/10/25
	 * 
	 * @param posReportRefSessionDto
	 */
	public void setPosReportRefSessionDto(SessionDto posReportRefSessionDto) {
		this.posReportRefSessionDto = posReportRefSessionDto;
	}
	public SearchMiseListLogic getPosReportRefSearchMiseListLogic() {
		return posReportRefSearchMiseListLogic;
	}
	public void setPosReportRefSearchMiseListLogic(
			SearchMiseListLogic posReportRefSearchMiseListLogic) {
		this.posReportRefSearchMiseListLogic = posReportRefSearchMiseListLogic;
	}
}
