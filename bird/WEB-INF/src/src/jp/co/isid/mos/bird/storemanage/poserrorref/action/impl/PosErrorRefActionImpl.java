/*
 * 作成日:2007/02/08
 */
package jp.co.isid.mos.bird.storemanage.poserrorref.action.impl;

import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.common.entity.MstUserCompany;
import jp.co.isid.mos.bird.common.logic.GetUserCompanyLogic;
import jp.co.isid.mos.bird.framework.action.CsvDownloadAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.UIUserMise;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.storemanage.poserrorref.action.PosErrorRefAction;
import jp.co.isid.mos.bird.storemanage.poserrorref.dto.PosErrorRefDto;
import jp.co.isid.mos.bird.storemanage.poserrorref.dto.PosErrorRefReqDto;
import jp.co.isid.mos.bird.storemanage.poserrorref.logic.GetPosErrorInfoLogic;
import jp.co.isid.mos.bird.storemanage.poserrorref.logic.GetShuDtListLogic;

/**
 * POS集信エラー店舗一覧 アクション
 * @author xkonishi
 *
 */
public class PosErrorRefActionImpl implements PosErrorRefAction {

    /**
     * アクションID定義
     */
    public static final String initialize_ACTION_ID = "BSM015A01";

    public static final String execute_ACTION_ID = "BSM015A02";
    
    public static final String csvDownload_ACTION_ID = "BSM015A03";
    
    /**
     * ユーザータイプコード
     */

    public static final String USER_TYPE_ONER = "02";

    public static final String USER_TYPE_MISE = "03";
    
    /**
     * 集信日取得期間
     */
    public static final int SHUDT_KIKAN = 60;
    
    /**
     * 検索済み区分
     */
    public static final int REFERENCE_FINISHED = 1;
    
    /**
     * POS集信エラー店舗一覧Dto(セッション用)
     */
    private PosErrorRefDto posErrorRefDto;

    /**
     * POS集信エラー店舗一覧Dto(リクエスト用)
     */
    private PosErrorRefReqDto posErrorRefReqDto;

    /**
     * プルダウンメニューDto
     */
    private PullDownMenuDto pullDownMenuDto;
    
    /**
     * CSVダウンロードアクション
     */
    private CsvDownloadAction posErrorRefCsvDownloadAction;
    
    /**
     * 集信日取得ロジック
     */
    private GetShuDtListLogic getShuDtListLogic;
    
    /**
     * 集信エラー店舗情報取得ロジック
     */
    private GetPosErrorInfoLogic getPosErrorInfoLogic;
    
    /**
     * 会社リスト取得ロジック
     */
    private GetUserCompanyLogic getUserCompanyLogic;
    
    /**
     * ユーザー情報
     */
    private BirdUserInfo birdUserInfo;
    
    /**
     * 日付情報
     */
    private BirdDateInfo birdDateInfo;
    
    
    /**
     * 初期処理
     */
    public String initialize() {
        // メニュー画面から遷移してきた場合
        if (pullDownMenuDto.isClearFlg()) {
            // クリアフラグ初期化
            pullDownMenuDto.setClearFlg(false);
            // ウインドウID生成
            int windowId = posErrorRefDto.createWindowId();
            // リクエスト用DTOにウインドウIDをセット
            posErrorRefReqDto.setWindowId(windowId);
            // ボタン名フラグ初期化
            posErrorRefReqDto.setBtnNameFlg(false);
            posErrorRefDto.setBtnNameFlg(windowId, false);
            
            // ユーザータイプコード取得
            String userType = birdUserInfo.getMstUser().getUserTypeCd();
            posErrorRefDto.setUserTypeCd(userType);
            
            // ユーザーID取得
            String userId = birdUserInfo.getMstUser().getUser_id();
            // 会社リストを取得し、DTOにセット
            posErrorRefDto.setCompanyList(getUserCompanyLogic.execute(userId, null));
            // システム日付を取得し、DTOにセット
            String sysDate = birdDateInfo.getSysDate();
            posErrorRefDto.setSysDate(sysDate);
            // 集信日リストを取得しDTOにセット
            posErrorRefDto.setShuDtList(getShuDtListLogic.execute(sysDate, SHUDT_KIKAN));
            posErrorRefReqDto.setShushinErrorInfo(null);
            
        }
        // ウインドウID取得
        int windowId = posErrorRefReqDto.getWindowId();
        // ボタン名フラグセット
        posErrorRefReqDto.setBtnNameFlg(posErrorRefDto.isBtnNameFlg(windowId));
        
        // データの再表示が必要な場合
        if (posErrorRefReqDto.getReferenceFlag() != REFERENCE_FINISHED) {
            
            // 検索データが存在する場合
            if (posErrorRefDto.isExistSearchData(windowId)) {
                // リクエスト用DTOに集信エラー店舗情報をセット
                posErrorRefReqDto.setShushinErrorInfo(posErrorRefDto.getShushinErrorInfo(windowId));
                // ボタン名フラグセット
                posErrorRefReqDto.setBtnNameFlg(posErrorRefDto.isBtnNameFlg(windowId));
                // 集信日・会社名称セット
                posErrorRefReqDto.setDispShuDt(posErrorRefDto.getShuDt(windowId));
                posErrorRefReqDto.setCompanyName(posErrorRefDto.getCompanyName(windowId));
                
            // 検索データが存在しない場合
            } else {
                // 前回検索した条件を取得
                String refShuDt = posErrorRefDto.getShuDt(windowId);
                String refCompanyCd = posErrorRefDto.getCompanyCd(windowId);
                // リクエストDTOにユーザータイプコードをセット
                posErrorRefReqDto.setUserTypeCd(posErrorRefDto.getUserTypeCd());

                // 再度検索処理を実行
                List posInfo = getPosErrorInfoLogic.execute(refCompanyCd,
                        posErrorRefReqDto.getMiseCd(), posErrorRefReqDto.getOnerCd(),
                        posErrorRefReqDto.getUserTypeCd(), refShuDt);
                
                if (posInfo == null || posInfo.size() == 0) {
                    // 集信エラー店舗情報クリア
                    posErrorRefReqDto.setShushinErrorInfo(null);
                    posErrorRefDto.setShushinErrorInfo(windowId, null);
                    // 会社名、集信日クリア
                    posErrorRefDto.setCompanyName(windowId, null);
                    posErrorRefDto.setShuDt(windowId, null);
                    posErrorRefReqDto.setDispShuDt(null);
                    
                } else {
                    // リクエスト用DTOに集信エラー店舗情報をセット
                    posErrorRefReqDto.setShushinErrorInfo(posInfo);
                    // セッション用DTOに集信エラー店舗情報をセット
                    posErrorRefDto.setShushinErrorInfo(windowId, posInfo);
                    // 表示用集信日セット
                    posErrorRefReqDto.setDispShuDt(refShuDt);
                    // 会社名セット
                    posErrorRefReqDto.setCompanyName(posErrorRefDto.getCompanyName(windowId));
                }
            }
        }
        return null;
    }

    /**
     * 実行
     */
    public String execute() {
        // ウインドウID取得
        int windowId = posErrorRefReqDto.getWindowId();
        // 会社名取得
        String companyName = getCompanyName(posErrorRefReqDto.getSelectedCompanyCd());
        // リクエスト用DTO
        posErrorRefReqDto.setCompanyName(companyName);
        // セッション用DTO
        posErrorRefDto.setCompanyName(windowId, companyName);

        // セッション用DTOに会社コード、集信日をセット
        posErrorRefDto.setCompanyCd(windowId, posErrorRefReqDto.getSelectedCompanyCd());
        posErrorRefDto.setShuDt(windowId, posErrorRefReqDto.getShuDt());
        // 表示用集信日セット
        posErrorRefReqDto.setDispShuDt(posErrorRefReqDto.getShuDt());

        // ユーザータイプがオーナー("02")の場合
        if (birdUserInfo.getMstUser().getUserTypeCd().equals(USER_TYPE_ONER)) {
            // リクエスト用DTO
            posErrorRefReqDto.setOnerCd(getOnerCd());
            // セッション用DTO
            posErrorRefDto.setOnerCd(posErrorRefReqDto.getWindowId(), getOnerCd());
        // ユーザータイプが("03")店舗の場合
        } else if (birdUserInfo.getMstUser().getUserTypeCd().equals(USER_TYPE_MISE)) {
            // リクエスト用DTO
            posErrorRefReqDto.setMiseCd(getMiseCd());                    
            // セッション用DTO
            posErrorRefDto.setMiseCd(posErrorRefReqDto.getWindowId(), getMiseCd());
        }

        // 集信エラー店舗情報取得ロジックを実行
        List posInfo = getPosErrorInfoLogic.execute(posErrorRefReqDto.getSelectedCompanyCd(),
                posErrorRefReqDto.getMiseCd(), posErrorRefReqDto.getOnerCd(),
                birdUserInfo.getMstUser().getUserTypeCd(), posErrorRefReqDto.getShuDt());
     
        if (posInfo == null || posInfo.size() == 0) {
            // 集信エラー店舗情報クリア
            posErrorRefReqDto.setShushinErrorInfo(null);
            posErrorRefDto.setShushinErrorInfo(windowId, null);
            // 会社名、集信日クリア
            posErrorRefDto.setCompanyCd(windowId, null);
            posErrorRefDto.setShuDt(windowId, null);
            posErrorRefReqDto.setDispShuDt(null);
            
            throw new NotExistException("該当データ");
        } else {
            // 集信エラー店舗情報セット
            posErrorRefReqDto.setShushinErrorInfo(posInfo);
            posErrorRefDto.setShushinErrorInfo(windowId, posInfo);
            // ボタン名フラグセット
            posErrorRefReqDto.setBtnNameFlg(true);
            posErrorRefDto.setBtnNameFlg(windowId, true);
            // 検索済みフラグセット
            posErrorRefReqDto.setReferenceFlag(REFERENCE_FINISHED);
        }
        return null;
    }

    /**
     * CSVダウンロード
     */
    public String csvDownload() {
        // アプリ日付取得
        posErrorRefReqDto.setAppDate(birdDateInfo.getAppDate());
        // システム日付取得
        posErrorRefReqDto.setSysDate(birdDateInfo.getSysDate());
        // リクエストDTOにユーザータイプコードをセット
        posErrorRefReqDto.setUserTypeCd(posErrorRefDto.getUserTypeCd());
        
        // ユーザータイプがオーナー("02")の場合
        if (birdUserInfo.getMstUser().getUserTypeCd().equals(USER_TYPE_ONER)) {
            // リクエスト用DTO
            posErrorRefReqDto.setOnerCd(getOnerCd());
            // セッション用DTO
            posErrorRefDto.setOnerCd(posErrorRefReqDto.getWindowId(), getOnerCd());
        // ユーザータイプが("03")店舗の場合
        } else if (birdUserInfo.getMstUser().getUserTypeCd().equals(USER_TYPE_MISE)) {
            // リクエスト用DTO
            posErrorRefReqDto.setMiseCd(getMiseCd());                    
            // セッション用DTO
            posErrorRefDto.setMiseCd(posErrorRefReqDto.getWindowId(), getMiseCd());
        }
        // 会社名取得
        String companyName= getCompanyName(posErrorRefReqDto.getSelectedCompanyCd());
        // リクエスト用DTO
        posErrorRefReqDto.setCompanyName(companyName);
        // CSVダウンロード
        posErrorRefCsvDownloadAction.download();
        
        return null;
    }
    
    /**
     * 会社名取得
     * @param  companyCd 　会社コード
     * 
     */
    private String getCompanyName(String companyCd) {
        List companyList = posErrorRefDto.getCompanyList();
        String companyName = null; 

        for (int i = 0; i < companyList.size(); i++) {
            MstUserCompany mstUserCompany = (MstUserCompany) companyList.get(i);
            
            if(mstUserCompany.getCompanyCd().equals(companyCd)){
                companyName = mstUserCompany.getCompanyName();
                break;
            }
        }
        return companyName;
    }


    /**
     * 店コード取得
     * @return
     */
    private String getMiseCd() {
        String miseCd = null;
        List miseList = birdUserInfo.getUserMise();
        if (miseList != null && miseList.size() > 0) {
            UIUserMise uIUserMise = (UIUserMise) miseList.get(0);
            miseCd = uIUserMise.getMiseCd();
        }
        return miseCd;
    }
    /**
     * オーナーコード取得
     * @return
     */
    private String getOnerCd() {
        List onerList = birdUserInfo.getUserOner();
        String onerCd = null;
        for (Iterator it = onerList.iterator(); it.hasNext();) {
            UIUserOner uIUserOner = (UIUserOner) it.next();
            if (uIUserOner.getCompanyCd().equals(posErrorRefReqDto.getSelectedCompanyCd())) {
                onerCd = uIUserOner.getOnerCd();
                break;
            }
        }
        return onerCd;
    }
    
    public GetPosErrorInfoLogic getGetPosErrorInfoLogic() {
        return getPosErrorInfoLogic;
    }

    public void setGetPosErrorInfoLogic(GetPosErrorInfoLogic getPosErrorInfoLogic) {
        this.getPosErrorInfoLogic = getPosErrorInfoLogic;
    }

    public GetShuDtListLogic getGetShuDtListLogic() {
        return getShuDtListLogic;
    }

    public void setGetShuDtListLogic(GetShuDtListLogic getShuDtListLogic) {
        this.getShuDtListLogic = getShuDtListLogic;
    }

    public CsvDownloadAction getPosErrorRefCsvDownloadAction() {
        return posErrorRefCsvDownloadAction;
    }

    public void setPosErrorRefCsvDownloadAction(
            CsvDownloadAction posErrorRefCsvDownloadAction) {
        this.posErrorRefCsvDownloadAction = posErrorRefCsvDownloadAction;
    }

    public PosErrorRefDto getPosErrorRefDto() {
        return posErrorRefDto;
    }

    public void setPosErrorRefDto(PosErrorRefDto posErrorRefDto) {
        this.posErrorRefDto = posErrorRefDto;
    }

    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }

    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }

    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

    public GetUserCompanyLogic getGetUserCompanyLogic() {
        return getUserCompanyLogic;
    }

    public void setGetUserCompanyLogic(GetUserCompanyLogic getUserCompanyLogic) {
        this.getUserCompanyLogic = getUserCompanyLogic;
    }

    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }

    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }

    public PosErrorRefReqDto getPosErrorRefReqDto() {
        return posErrorRefReqDto;
    }

    public void setPosErrorRefReqDto(PosErrorRefReqDto posErrorRefReqDto) {
        this.posErrorRefReqDto = posErrorRefReqDto;
    }
}