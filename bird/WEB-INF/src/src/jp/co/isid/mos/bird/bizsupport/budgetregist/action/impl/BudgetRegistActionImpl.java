package jp.co.isid.mos.bird.bizsupport.budgetregist.action.impl;

import java.io.IOException;
import java.util.List;

import jp.co.isid.mos.bird.framework.action.CsvOutputAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.CannotAccessException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;

import jp.co.isid.mos.bird.bizsupport.budgetregist.action.BudgetRegistAction;
import jp.co.isid.mos.bird.bizsupport.budgetregist.dto.BudgetRegistDto;
import jp.co.isid.mos.bird.bizsupport.budgetregist.logic.BudgetRegistCheckLogic;
import jp.co.isid.mos.bird.bizsupport.budgetregist.logic.BudgetRegistClearLogic;
import jp.co.isid.mos.bird.bizsupport.budgetregist.logic.BudgetRegistLogic;
import jp.co.isid.mos.bird.bizsupport.budgetregist.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizsupport.common.entity.CodCompany;
import jp.co.isid.mos.bird.bizsupport.common.logic.GetUserCompanyLogic;



/**
 * 予算登録Action
 * 
 * @author Aspac
 */
public class BudgetRegistActionImpl implements BudgetRegistAction {

    /** アクションID定義 */
    public static final String initialize_ACTION_ID = "BBS022A01";


    /**
     * テンプレートCSVダウンロードAction
     */
    private CsvOutputAction budgetRegistTemplateCsvOutputAction;
    
    /**
     * 登録ファイルCSVエラー情報ダウンロードAction
     */
    private CsvOutputAction budgetRegistCsvOutputAction;
    
    
    
    /**
     * 予算更新DTO
     */
    private BudgetRegistDto budgetRegistDto;

    /**
     * メニューDTO
     */
    private PullDownMenuDto pullDownMenuDto;
    
    
    
    
    /**
     * 予算CSVチェックロジック
     */
    private BudgetRegistCheckLogic budgetRegistCheckLogic;
    
    /**
     * 予算更新ロジック
     */
    private BudgetRegistLogic budgetRegistLogic;

    /**
     * 予算クリアロジック
     */
    private BudgetRegistClearLogic budgetRegistClearLogic;
    
    
    /**
     * ユーザ関連情報
     */
    private BirdUserInfo birdUserInfo;
    
    /**
     * 日付情報
     */
    private BirdDateInfo birdDateInfo;
    
    /**
     * 初期画面表示ロジック
     */
    private ConditionLogic conditionLogic;
    
    /**
     * 会社情報取得ロジック
     */
    private GetUserCompanyLogic getUserCompanyLogic;
    
    
    
    

    /**
     * テンプレートCSVダウンロードActionを取得します。
     * @return CSVダウンロードAction
     */
    public CsvOutputAction getBudgetRegistTemplateCsvOutputAction() {
        return budgetRegistTemplateCsvOutputAction;
    }
    /**
     * テンプレートCSVダウンロードActionを設定します。
     * @param budgetRegistTemplateCsvOutputAction
     */
    public void setBudgetRegistTemplateCsvOutputAction(
            CsvOutputAction budgetRegistTemplateCsvOutputAction) {
        this.budgetRegistTemplateCsvOutputAction = budgetRegistTemplateCsvOutputAction;
    }
    
    
    
    
    /**
     * 登録ファイルCSVエラー情報ダウンロードActionを取得します。
     * @return CSVダウンロードAction
     */
    public CsvOutputAction getBudgetRegistCsvOutputAction() {
        return budgetRegistCsvOutputAction;
    }
    /**
     * 登録ファイルCSVエラー情報ダウンロードActionを設定します。
     * @param budgetRegistCsvOutputAction
     */
    public void setBudgetRegistCsvOutputAction(
            CsvOutputAction budgetRegistCsvOutputAction) {
        this.budgetRegistCsvOutputAction = budgetRegistCsvOutputAction;
    }

    
    
    
    /**
     * 予算DTOを取得します。
     * @return 予算DTO
     */
    public BudgetRegistDto getBudgetRegistDto() {
        return budgetRegistDto;
    }
    /**
     * 予算DTOを設定します。
     * @param budgetRegistDto 予算DTO
     */
    public void setBudgetRegistDto(BudgetRegistDto budgetRegistDto) {
        this.budgetRegistDto = budgetRegistDto;
    }

    
    /**
     * メニューDTOを取得します。
     * @return メニューDTO
     */
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }
    /**
     * メニューDTOを設定します。
     * @param pullDownMenuDto メニューDTO
     */
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }
    
    
    
    
    /**
     * 予算登録ロジックを取得します。
     * @return
     */
    public BudgetRegistLogic getBudgetRegistLogic() {
        return budgetRegistLogic;
    }
    /**
     * 予算登録ロジックを設定します。
     * @param budgetRegistLogic
     */
    public void setBudgetRegistLogic(BudgetRegistLogic budgetRegistLogic) {
        this.budgetRegistLogic = budgetRegistLogic;
    }
    
    
    
    /**
     * 予算CSV取込エラーチェックロジックを取得します。
     * @return 
     */
    public BudgetRegistCheckLogic getBudgetRegistCheckLogic() {
        return budgetRegistCheckLogic;
    }
    /**
     * 予算CSV取込エラーチェックロジックを設定します。
     * @param 
     */
    public void setBudgetRegistCheckLogic(BudgetRegistCheckLogic budgetRegistCheckLogic) {
        this.budgetRegistCheckLogic = budgetRegistCheckLogic;
    }
    
    
    
    
    /**
     * 予算クリアロジックを取得します。
     * @return
     */
    public BudgetRegistClearLogic getBudgetRegistClearLogic() {
        return budgetRegistClearLogic;
    }
    /**
     * 予算クリアロジックを設定します。
     * @param budgetRegistClearLogic
     */
    public void setBudgetRegistClearLogic(
            BudgetRegistClearLogic budgetRegistClearLogic) {
        this.budgetRegistClearLogic = budgetRegistClearLogic;
    }
    
    
    
    
    /**
     * ユーザ関連情報を取得します。
     * @return ユーザ関連情報
     */
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }
    /**
     * ユーザ関連情報を設定します。
     * @param birdUserInfo ユーザ関連情報
     */
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }



    /**
     * 画面初期表示データ取得ロジックを取得する。
     * @return
     */
    public ConditionLogic getConditionLogic() {
        return conditionLogic;
    }
    /**
     * 画面初期表示データロジックを設定する。
     * @param conditionLogic
     */
    public void setConditionLogic(ConditionLogic conditionLogic) {
        this.conditionLogic = conditionLogic;
    }
    
    
    /**
     * 会社情報取得ロジックを取得する
     * @return
     */
    public GetUserCompanyLogic getGetUserCompanyLogic() {
        return getUserCompanyLogic;
    }
    /**
     * 会社情報取得ロジックを設定する
     * @param getUserCompanyLogic
     */
    public void setGetUserCompanyLogic(GetUserCompanyLogic getUserCompanyLogic) {
        this.getUserCompanyLogic = getUserCompanyLogic;
    }
    
    
    
    /**
     * 初期処理
     * ※画面初期表示に必要な情報を取得します。
     * 
     * @return 画面遷移情報
     */
    public String initialize() {

        
        getBudgetRegistDto().doClear();
        
        // 初期処理
        if (getPullDownMenuDto().isClearFlg()) {
            
            getBudgetRegistDto().setUserId(birdUserInfo.getUserID());
        
            // ユーザー、日付情報セット
            getBudgetRegistDto().setBirdUserInfo(getBirdUserInfo());
            getBudgetRegistDto().setBirdDateInfo(getBirdDateInfo());
            
            // ユーザタイプ判定
            String userTypeCd = birdUserInfo.getMstUser().getUserTypeCd();
            
            if (userTypeCd.equals("01")) {
                
                //会社情報を取得する
                List listCmp = getGetUserCompanyLogic().execute(birdUserInfo.getUserID());
                getBudgetRegistDto().setListCompany(listCmp);
                
                if (listCmp.size() > 0){
                
                    //会社コードの初期値取得・設定
                    CodCompany codCompany = (CodCompany) listCmp.get(0);
                    budgetRegistDto.setCondCompanyCd(codCompany.getCompanyCd());               
                }
                
                //予算更新の検索条件を設定する
                getConditionLogic().execute(getBudgetRegistDto());
                
                //下期予算クリア 開始年月をセット
                String simokiClearFrom = "";
                try {
                    simokiClearFrom = DateManager.getPrevMonth(getBudgetRegistDto().getSysdate().substring(0, 6), 1);
                }
                catch (Exception ex) {
                    throw new FtlSystemException("日付", "", ex);
                }
                getBudgetRegistDto().setSimokiClearFromMonth(simokiClearFrom);
            } else {
                throw new CannotAccessException();
                
            }
            
            pullDownMenuDto.setClearFlg(false);
        }

        return null;
    }
    
    
    /**
     * 実行処理
     * ※実行ボタンのアクションを振り分けます。
     * 
     * @return
     */
    public String execute(){
        
        //Dto初期化
        getBudgetRegistDto().doClear();
        
        BudgetRegistDto BRDto = getBudgetRegistDto();
        
        // テンプレートダウンロード
        if(BRDto.getExecuteMode() == 0){
            downloadTempCsv();
        }
        
        // ファイル登録
        else if(BRDto.getExecuteMode() == 1){
            doBudgetRegist();
        }
        
        // 下期予算クリア
        else if(BRDto.getExecuteMode() == 2){
            doBudgetRegistClear();
        }

        return null;
    }
    
    
    /**
     * テンプレートCSVダウンロード
     * ※登録CSVファイルのテンプレートをダウンロードします。
     * 
     * @return
     */
    public String downloadTempCsv() {
        
        // CSVダウンロード
        try {
            getBudgetRegistTemplateCsvOutputAction().downloadCsv();
        } catch (IOException e) {
            throw new FtlSystemException("CSVダウンロード");
        }
        return null;
    }
    
    
    /**
     * エラー情報CSVダウンロード
     * ※登録CSVファイルのエラー情報をダウンロードします。
     * 
     * @return
     */
    public String downloadErrInfoCsv() {
        
        // CSVダウンロード
        try {
            getBudgetRegistCsvOutputAction().downloadCsv();
        } catch (IOException e) {
            throw new FtlSystemException("CSVダウンロード");
        }
        return null;
    }
    

    /**
     * 予算更新処理
     * ※予算更新処理を行います。
     * ※予算取込CSVにエラーがあった場合には、エラー情報CSVをダウンロードします。
     * 
     * @return 画面遷移情報
     */
    public String doBudgetRegist() {
        
        // 予算CSV読込
        getBudgetRegistCheckLogic().execute(getBudgetRegistDto());
        
        if(getBudgetRegistDto().isErrorFlg()){
            // エラー情報CSVダウンロード
            downloadErrInfoCsv();
        }
        else {
            // 予算登録処理
            getBudgetRegistLogic().execute(getBudgetRegistDto());
        }
        
        return initialize_ACTION_ID;
    }

    
    /**
     * 予算更新処理(クリア)
     * ※９月のみ実行可能
     * ※予算情報をクリアします。
     */
    public String doBudgetRegistClear() {
        // 予算クリア処理
        getBudgetRegistClearLogic().execute(getBudgetRegistDto());
    
        return initialize_ACTION_ID;
    }
    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }
    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }


}
