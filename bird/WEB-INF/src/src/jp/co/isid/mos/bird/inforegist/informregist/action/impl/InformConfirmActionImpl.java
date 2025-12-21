/*
 * 作成日: 2006/2/8
 */
package jp.co.isid.mos.bird.inforegist.informregist.action.impl;

import jp.co.isid.mos.bird.common.code.InfoShu;
import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.commonform.publictargetsearch.dto.PublicTargetSearchDto;
import jp.co.isid.mos.bird.framework.action.FileUploadAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.inforegist.documentregist.logic.impl.UploadLogicImpl;
import jp.co.isid.mos.bird.inforegist.informregist.action.InformConfirmAction;
import jp.co.isid.mos.bird.inforegist.informregist.dto.InformRegistDto;
import jp.co.isid.mos.bird.inforegist.informregist.entity.UINews;
import jp.co.isid.mos.bird.inforegist.informregist.logic.RenewNewsLogic;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * お知らせ登録確認アクションクラス
 * @author itamoto
 * 
 * 更新日：2011/04/15 ASAPC 「お知らせ」から「インフォメーション」へ画面名称変更対応
 */
public class InformConfirmActionImpl implements InformConfirmAction {


    /* アクションID */
    public static String initialize_ACTION_ID        = "BIF004A06";
    public static String startPublicTarget_ACTION_ID = "BIF004A09";
    public static String regist_ACTION_ID            = "BIF004A07";
    public static String cancel_ACTION_ID            = "BIF004A08";
    
    /* informSearchDto */
    private InformRegistDto informRegistDto;
    /* 公開対象Dto */
    private PublicTargetDto publicTargetDto;
    private PublicTargetSearchDto publicTargetSearchDto;

    /* お知らせ情報 */
    private UINews uiNews;
    /* お知らせ情報の更新Logic */
    private RenewNewsLogic renewNewsLogic;
    // アップロードロジック
    private UploadLogicImpl uploadLogic;
    private FileUploadAction fileUploadAction;

    /* ログイン情報 */
    private BirdUserInfo birdUserInfo;
    /* システム日付情報 */
    private BirdDateInfo birdDateInfo;
    
    /**
     * お知らせ検索用Dtoの設定
     * @return informRegistDto を戻します。
     */
    public InformRegistDto getInformRegistDto() {
        return informRegistDto;
    }
    /**
     * お知らせ検索用Dtoの設定
     * @param informRegistDto informRegistDto を設定。
     */
    public void setInformRegistDto(InformRegistDto informRegistDto) {
        this.informRegistDto = informRegistDto;
    }

    /**
     * 公開対象DTOの設定
     * @return publicTargetDto を戻します。
     */
    public PublicTargetDto getPublicTargetDto() {
        return publicTargetDto;
    }
    /**
     * 公開対象DTOの設定
     * @param publicTargetDto publicTargetDto を設定。
     */
    public void setPublicTargetDto(PublicTargetDto publicTargetDto) {
        this.publicTargetDto = publicTargetDto;
    }
    /**
     * 公開対象選択DTOの設定
     * @return publicTargetSearchDto を戻します。
     */
    public PublicTargetSearchDto getPublicTargetSearchDto() {
        return publicTargetSearchDto;
    }
    /**
     * 公開対象選択DTOの設定
     * @param publicTargetSearchDto publicTargetSearchDto を設定。
     */
    public void setPublicTargetSearchDto(
            PublicTargetSearchDto publicTargetSearchDto) {
        this.publicTargetSearchDto = publicTargetSearchDto;
    }
    
	/**
	 * お知らせ情報更新ロジックの設定
	 * @return renewNewsLogic を戻します。
	 */
	public RenewNewsLogic getRenewNewsLogic() {
		return renewNewsLogic;
	}
	/**
	 * お知らせ情報更新ロジックの設定
	 * @param renewNewsLogic renewNewsLogic を設定。
	 */
	public void setRenewNewsLogic(RenewNewsLogic renewNewsLogic) {
		this.renewNewsLogic = renewNewsLogic;
	}
	
	/**
	 * お知らせ情報の設定
	 * @return uiNews を戻します。
	 */
	public UINews getUiNews() {
		return uiNews;
	}
	/**
	 * お知らせ情報の設定
	 * @param uiNews uiNews を設定。
	 */
	public void setUiNews(UINews uiNews) {
		this.uiNews = uiNews;
	}

	/**
	 * ログイン情報の設定
	 * @return birdUserInfo を戻します。
	 */
	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}
	/**
	 * ログイン情報の設定
	 * @param birdUserInfo birdUserInfo を設定。
	 */
	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
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
     * 初期処理
     * @return 画面遷移情報
     * 更新日：2011/04/15 ASAPC 「お知らせ」から「インフォメーション」へ画面名称変更対応
     */
	public String initialize() {
		try {
            PropertyUtils.copyProperties(publicTargetSearchDto, publicTargetDto);
        } catch (Exception e) {
            throw new FtlSystemException(InfoShu.OSIRASE_NAME + "登録");
        }
        return null;
    }

    /**
     * 公開対象の表示処理
     * 更新日：2011/04/15 ASAPC 「お知らせ」から「インフォメーション」へ画面名称変更対応
     */
    public String startPublicTarget(){
        // 公開対象選択のパラメータ設定(参照モード)
        publicTargetSearchDto.setReferenceFlg(true);
        publicTargetSearchDto.setNavigationCase(informRegistDto.confirm_VIEW_ID);
		publicTargetSearchDto.setInitFlag(true);

        try {
            PropertyUtils.copyProperties(publicTargetSearchDto, publicTargetDto);
        } catch (Exception e) {
            throw new FtlSystemException(InfoShu.OSIRASE_NAME + "登録");
        }
        // 公開対象選択へ遷移
        return informRegistDto.publicTarget_VIEW_ID;
    }

    /**
     * 新規登録処理
     */
    public String regist(){
    	// 登録
    	setRegistValue(uiNews);
    	renewNewsLogic.execute(uiNews, informRegistDto, publicTargetDto);
        //ファイルアップロード
        upload();
        
		// 情報クリア
    	informRegistDto.clear();
    	uiNews = new UINews();
		return informRegistDto.search_VIEW_ID;
    }

    /**
     * 登録情報設定処理
     * @param entity
     */
    private void setRegistValue(UINews entity) {
        MstUser userInfo = getBirdUserInfo().getMstUser();
        if (informRegistDto.getMode() == 1) {
        	entity.setRegDate(birdDateInfo.getSysDate());
            entity.setPubUser(userInfo.getUser_id());
            entity.setUserNameKj(userInfo.getUser_name());
            entity.setFirstUser(userInfo.getUser_id());
        }
        entity.setPubOrg(userInfo.getBumonCd());
        entity.setPubOrgName(userInfo.getBumonName());
        entity.setLastUser(userInfo.getUser_id());
    }

    /**
	 * 取消処理
	 */
    public String cancel() {
        return (informRegistDto.getMode() < 3) ? informRegistDto.edit_VIEW_ID
                : informRegistDto.search_VIEW_ID;
    }
    
    /**
     * アップロード
     */
    public String upload() {
        
//        getFileUploadAction().setUploadDto(getInformRegistDto());
//        getFileUploadAction().setUploadLogic(getUploadLogic());
        for (int i = 2; i <= 4; i++) {
            getInformRegistDto().setUploadEntity(getUiNews());
            getInformRegistDto().setUploadIndex(i);
            switch (i) {
                case 2:
                    getInformRegistDto().setTempFileName(getInformRegistDto().getUploadTempFileNameTenpu1());
                    getInformRegistDto().setUploadFileName(getInformRegistDto().getUploadFileNameTenpu1());
                    getInformRegistDto().setUploadIndex(2);
                    break;
                case 3:
                    getInformRegistDto().setTempFileName(getInformRegistDto().getUploadTempFileNameTenpu2());
                    getInformRegistDto().setUploadFileName(getInformRegistDto().getUploadFileNameTenpu2());
                    getInformRegistDto().setUploadIndex(3);
                    break;
                case 4:
                    getInformRegistDto().setTempFileName(getInformRegistDto().getUploadTempFileNameTenpu3());
                    getInformRegistDto().setUploadFileName(getInformRegistDto().getUploadFileNameTenpu3());
                    getInformRegistDto().setUploadIndex(4);
                    break;
            }
            
            if (getInformRegistDto().getTempFileName() != null &&
                    getInformRegistDto().getTempFileName().trim().length() != 0) {
                getFileUploadAction().upload();
            }
        }
        return null;
    }
    
    /**
     * @return uploadLogic を戻します。
     */
    public UploadLogicImpl getUploadLogic() {
        return uploadLogic;
    }
    /**
     * @param uploadLogic uploadLogic を設定。
     */
    public void setUploadLogic(UploadLogicImpl uploadLogic) {
        this.uploadLogic = uploadLogic;
    }
    /**
     * fileUploadAction を取得します。
     * @return fileUploadAction 
     */
    public FileUploadAction getFileUploadAction() {
        return fileUploadAction;
    }
    /**
     * fileUploadActionを設定します。
     * @param fileUploadAction
     */
    public void setFileUploadAction(FileUploadAction fileUploadAction) {
        this.fileUploadAction = fileUploadAction;
    }

    
    
}

