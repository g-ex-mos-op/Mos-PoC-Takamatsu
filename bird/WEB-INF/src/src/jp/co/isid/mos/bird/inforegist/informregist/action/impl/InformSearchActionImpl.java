/*
 * 作成日: 2006/2/8
 */
package jp.co.isid.mos.bird.inforegist.informregist.action.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.common.logic.GetKanrenBunshoInfoLogic;
import jp.co.isid.mos.bird.common.logic.GetKoukaiTaishoLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.inforegist.informregist.action.InformSearchAction;
import jp.co.isid.mos.bird.inforegist.informregist.dto.InformRegistDto;
import jp.co.isid.mos.bird.inforegist.informregist.entity.UINews;
import jp.co.isid.mos.bird.inforegist.informregist.logic.GetNewsLogic;

import org.apache.commons.beanutils.PropertyUtils;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * インフォメーション登録初期画面アクションクラス
 * @author itamoto
 * 
 * 更新日：2011/04/15 ASAPC 「お知らせ」から「インフォメーション」へ画面名称変更対応
 */
public class InformSearchActionImpl implements InformSearchAction {

    /* アクションID */
    public static final String initialize_ACTION_ID = "BIF004A01";
    public static final String regist_ACTION_ID     = "BIF004A02";
    public static final String search_ACTION_ID     = "BIF004A03";
    public static final String edit_ACTION_ID       = "BIF004A04";
    public static final String delete_ACTION_ID     = "BIF004A05";

    /* 年月出力月数 */
    private static int NENGETU_DISPLAY_MONTH = 12;

    /* informRegistDto */
    private InformRegistDto informRegistDto;
    /* pullDownMenuDto */
    private PullDownMenuDto pullDownMenuDto;
    /* publicTargetDto */
    private PublicTargetDto publicTargetDto;

	/* インフォメーション情報 */
    private UINews uiNews;

    /* GetNewsLogic */
    private GetNewsLogic getNewsLogic;
    /* 公開対象取得ロジック */
    private GetKoukaiTaishoLogic getKoukaiTaishoLogic;
    /* 関連文書取得ロジック */
    private GetKanrenBunshoInfoLogic getKanrenBunshoInfoLogic;

    /* ログイン情報 */
    private BirdUserInfo birdUserInfo;
    /* 日付情報 */
    private BirdDateInfo birdDateInfo;

    /* インフォメーション選択選択index */
    private int index;
    
    /**
     * インフォメーション検索用Dtoの設定
     * @return informRegistDto を戻します。
     */
    public InformRegistDto getInformRegistDto() {
        return informRegistDto;
    }
    /**
     * インフォメーション検索用Dtoの設定
     * @param informRegistDto informRegistDto を設定。
     */
    public void setInformRegistDto(InformRegistDto informRegistDto) {
        this.informRegistDto = informRegistDto;
    }

    /**
     * 初期処理判定Dtoの設定
     * @return pullDownMenuDto を戻します。
     */
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }
    /**
     * 初期処理判定Dtoの設定
     * @param pullDownMenuDto pullDownMenuDto を設定。
     */
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }
    
	/**
	 * 公開対象Dtoの設定
	 * @return publicTargetDto を戻します。
	 */
	public PublicTargetDto getPublicTargetDto() {
		return publicTargetDto;
	}
	/**
	 * 公開対象Dtoの設定
	 * @param publicTargetDto publicTargetDto を設定。
	 */
	public void setPublicTargetDto(PublicTargetDto publicTargetDto) {
		this.publicTargetDto = publicTargetDto;
	}

	/**
	 * インフォメーション情報の設定
	 * @return uiNews を戻します。
	 */
	public UINews getUiNews() {
		return uiNews;
	}
	/**
	 * インフォメーション情報の設定
	 * @param uiNews uiNews を設定。
	 */
	public void setUiNews(UINews uiNews) {
		this.uiNews = uiNews;
	}

	/**
	 * 既存のインフォメーション一覧取得ロジックの設定
	 * @return getNewsLogic を戻します。
	 */
	public GetNewsLogic getGetNewsLogic() {
		return getNewsLogic;
	}
	/**
	 * 既存のインフォメーション一覧取得ロジックの設定
	 * @param getNewsLogic getNewsLogic を設定。
	 */
	public void setGetNewsLogic(GetNewsLogic getNewsLogic) {
		this.getNewsLogic = getNewsLogic;
	}

	/**
     * 公開対象取得ロジックの設定
     * @return getKoukaiTaishoLogic を戻します。
     */
    public GetKoukaiTaishoLogic getGetKoukaiTaishoLogic() {
        return getKoukaiTaishoLogic;
    }
    /**
     * 公開対象取得ロジックの設定
     * @param getKoukaiTaishoLogic getKoukaiTaishoLogic を設定。
     */
    public void setGetKoukaiTaishoLogic(
            GetKoukaiTaishoLogic getKoukaiTaishoLogic) {
        this.getKoukaiTaishoLogic = getKoukaiTaishoLogic;
    }

    /**
     * 関連文書取得ロジックを取得します。
     * @return 関連文書取得ロジック
     */
    public GetKanrenBunshoInfoLogic getGetKanrenBunshoInfoLogic() {
        return getKanrenBunshoInfoLogic;
    }
    
    /**
     * 関連文書取得ロジックを設定します。
     * @param 関連文書取得ロジック
     */
    public void setGetKanrenBunshoInfoLogic(
            GetKanrenBunshoInfoLogic getKanrenBunshoInfoLogic) {
        this.getKanrenBunshoInfoLogic = getKanrenBunshoInfoLogic;
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
	 * インフォメーション選択indexの設定
	 * @return index を戻します。
	 */
	public int getIndex() {
		return index;
	}
	/**
	 * インフォメーション選択indexの設定
	 * @param index index を設定。
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
     * 初期処理
     * @return 画面遷移情報
     */
	public String initialize() {
        if (pullDownMenuDto.isClearFlg()) {

            S2Container container = SingletonS2ContainerFactory.getContainer();
            HttpServletRequest request = (HttpServletRequest) container.getComponent("request");
            BirdDateInfo birdDateInfo = (BirdDateInfo) request.getSession().getAttribute("birdDateInfo");

        	// インフォメーション情報＆Dto初期化
            uiNews = new UINews();
            informRegistDto = new InformRegistDto();

            // 対象年月取得
            DateFormatter formatter = new DateFormatter();
            String sysMonth = formatter.format(birdDateInfo.getSysDate(),
					DateFormatter.PATTERN_MONTH, DateFormatter.DATE_TYPE_YMD);
            List listMonth = new ArrayList();

            for (int i = 0; i < NENGETU_DISPLAY_MONTH; i++) {
                String month = "";
                try {
                    month = DateManager.getPrevMonth(sysMonth, i);
                }
                catch (Exception ex) {
                    throw new FtlSystemException("条件画面初期処理");
                }
                SelectItem item = new SelectItem(month, formatter.format(month,
						DateFormatter.PATTERN_MONTH_SLASH,
						DateFormatter.DATE_TYPE_YM)); 
                listMonth.add(item);
            }
            informRegistDto.setTargetYMList(listMonth);

            pullDownMenuDto.setClearFlg(false);
        }
        return null;
    }

    /**
     * 新規登録処理
     */
    public String regist(){
        // インフォメーション情報＆公開対象初期化
        uiNews = new UINews();
        publicTargetDto = new PublicTargetDto();
        publicTargetDto.setInfoShu(informRegistDto.INFO_SHU);
        getInformRegistDto().clear();
        getInformRegistDto().clearUploadFile();
        
// add start xkhata
        getInformRegistDto().setAttachName(null);
        getInformRegistDto().setUploadFileName(null);
// add end
        
        // 表示用　登録者名セット
    	uiNews.setUserNameKj(birdUserInfo.getMstUser().getUser_name());
        // 発効日セット
        uiNews.setPubDateFrom(getBirdDateInfo().getSysDate());
        // 登録モード新規登録セット
        informRegistDto.setMode(1);
        getInformRegistDto().setListKanrenBunsho(new ArrayList());
        return informRegistDto.edit_VIEW_ID;
    }

    /**
     * 検索処理
     */
    public String search() {
    	getInformRegistDto().setExistRegistData(false);
    	List listSearchData = getNewsLogic.execute(
    			getInformRegistDto().getTargetYM(), birdUserInfo.getUserID());
    	getInformRegistDto().setSearchResultList(listSearchData);
		// 検索結果0件時
		if (listSearchData.size() <= 0) {
			throw new NoResultException("");
		}
		for(int i=0; i<listSearchData.size(); i++) {
			UINews entity = (UINews)listSearchData.get(i);
			if("0".equals(entity.getSakujoFlg())) {
				getInformRegistDto().setExistRegistData(true);
				break;
			}
		}
		return null;
	}

    /**
	 * 編集処理
	 */
    public String edit(){
    	// 選択情報設定処理
    	setEditInfo();
        
//       add start xkhata
        getInformRegistDto().setAttachName(null);
        getInformRegistDto().setUploadFileName(null);
// add end

		// 登録モード編集セット
		informRegistDto.setMode(2);
        return informRegistDto.edit_VIEW_ID;
    }
    
    /**
     * 削除処理
     */
    public String delete(){
    	// 選択情報設定処理
    	setEditInfo();
    	// 登録モード削除セット
        informRegistDto.setMode(3);
        return informRegistDto.confirm_VIEW_ID;
    }
    
    /**
     * 照会処理
     */
    public String view(){
    	// 選択情報設定処理
    	setEditInfo();
    	// 登録モード照会セット
        informRegistDto.setMode(4);
        return informRegistDto.confirm_VIEW_ID;
    }
    
    /**
     * 選択情報設定処理
     */
    private void setEditInfo() {
        getInformRegistDto().clearUploadFile();
        // 選択情報設定
        if (getInformRegistDto().getSearchResultListSize() > 0) {
            //2006/04/11 戻るボタンが押下された時でも、編集された値が反映されてしまったのを修正
//            uiNews = (UINews) informRegistDto.getSearchResultList().get(
//					getIndex());
            uiNews = new UINews();
            UINews entity = (UINews) informRegistDto.getSearchResultList().get(
              getIndex());
            try{
                //値をコピー
                PropertyUtils.copyProperties(uiNews, entity);
                
            }catch(Exception ex){
                throw new FtlSystemException("インフォメーション登録", ex.toString(), ex);
            }
            
		} else {
			// 選択チェック
			throw new NotNullException("インフォメーション情報選択", "", true);
		}

    	// 【公開対象の取得】
    	setPublicTargetDto(getKoukaiTaishoLogic.execute(
				informRegistDto.INFO_SHU, uiNews.getRegDate(), uiNews.getSeq()));
        
        // 関連文書情報取得
        List listKanren = getGetKanrenBunshoInfoLogic().execute(informRegistDto.INFO_SHU,
                uiNews.getRegDate(), uiNews.getSeq());
        getInformRegistDto().setListKanrenBunsho(listKanren);

    }
}

