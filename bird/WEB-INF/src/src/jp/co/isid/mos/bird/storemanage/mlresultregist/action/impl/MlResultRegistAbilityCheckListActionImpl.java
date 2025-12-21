package jp.co.isid.mos.bird.storemanage.mlresultregist.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.mlresultregist.action.MlResultRegistAbilityCheckListAction;
import jp.co.isid.mos.bird.storemanage.mlresultregist.common.MlResultRegistCommon;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.MstAbilityCheckCategory;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.TrnAbilityCheckResult;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.TrnEntryStatus;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.GetAbilityCheckCategoryInfoLogic;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.GetAbilityCheckCategoryResultLogic;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.GetAbilityCheckMeisaiInfoLogic;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * マスターライセンス結果登録 能力チェック対象者選択画面アクション
 * @author kusama
 */
public class MlResultRegistAbilityCheckListActionImpl implements MlResultRegistAbilityCheckListAction {

    /** アクションID定義 */
    public static final String initialize_ACTION_ID = "BSM008A11";
    public static final String regist_ACTION_ID     = "BSM008A12";
    public static final String back_ACTION_ID       = "BSM008A13";

    /* LOGIC */
    private GetAbilityCheckCategoryInfoLogic mlrrGetAbilityCheckCategoryInfoLogic;
    private GetAbilityCheckCategoryResultLogic mlrrGetAbilityCheckCategoryResultLogic;
    private GetAbilityCheckCategoryResultLogic mlrrGetAbilityCheckLastCategoryResultLogic;
    private GetAbilityCheckMeisaiInfoLogic mlrrGetAbilityCheckMeisaiInfoLogic;

    /* DTO */
    private MlResultRegistDto mlResultRegistDto;

    /* 再エントリーフラグ：再エントリー */
    private final String ENTRY_FLG_RE = "1";
    
    /**
     * 初期処理
     */
    public String initialize() {
        return null;
    }
    
    /**
     * 戻る
     */
    public String back() {
        // 条件画面へ遷移
        return MlResultRegistCommon.VIEW_ID_CONDITION;
    }
    
    /**
     * 実行
     */
    public String regist() {

        //---------------------------------
        // 選択したスタッフ情報を取得
        //---------------------------------
        List staffList    = getMlResultRegistDto().getListAbilitiyStaffList();
        int selectedIndex = getMlResultRegistDto().getSelectedAbilityCheckIndex();
        
        //取得
        TrnEntryStatus trnEntryStatus = (TrnEntryStatus)staffList.get(selectedIndex);

        //DTOにセット
        getMlResultRegistDto().setSelectAbilityCheckStaffId(trnEntryStatus.getStaffId());
        getMlResultRegistDto().setSelectAbilityCechkStaffEntryStatus(trnEntryStatus);

        //------------------------------------------------
        // 能力チェックカテゴリ結果登録情報の取得(今回分)
        //------------------------------------------------
        getMlResultRegistDto().setTrnAbilityCheckCategoryResult(
                getMlrrGetAbilityCheckCategoryResultLogic().execute(getMlResultRegistDto()));

        //表示用Entityにコピー
        TrnAbilityCheckResult viewEntity = new TrnAbilityCheckResult();
        try {
            PropertyUtils.copyProperties(viewEntity, getMlResultRegistDto().getTrnAbilityCheckCategoryResult());
        } catch (Exception e) {
        }
        getMlResultRegistDto().setTrnAbilityCheckViewCategoryResult(viewEntity);

        //------------------------------------
        // カテゴリ前回結果情報の取得(前回分)
        //------------------------------------
        //前回分
        TrnAbilityCheckResult zenkai = new TrnAbilityCheckResult();

        //初回エントリーor再エントリーか
        String reentryFlg = viewEntity.getReentryFlg();
        
        //再エントリーの時のみ(初回時は空白で表示)
        if(reentryFlg != null && reentryFlg.equals(ENTRY_FLG_RE)){
            zenkai = getMlrrGetAbilityCheckLastCategoryResultLogic().execute(getMlResultRegistDto());
        }

        //前回分をDTOにセット
        getMlResultRegistDto().setTrnAbilityCheckLastCategoryResult(zenkai);

        // 2007/01/09 変更
        //getMlResultRegistDto().setTrnAbilityCheckLastCategoryResult(getMlrrGetAbilityCheckLastCategoryResultLogic().execute(getMlResultRegistDto()));

        //-----------------------------------
        // 能力チェック明細結果登録情報の取得
        //-----------------------------------
        List listMeisai = getMlrrGetAbilityCheckMeisaiInfoLogic().execute(getMlResultRegistDto());
        getMlResultRegistDto().setAbilityCheckDetailResultList(listMeisai);

        //-----------------------------------
        // 能力チェックカテゴリ情報の取得
        //-----------------------------------
        List categoryList = getMlrrGetAbilityCheckCategoryInfoLogic().execute();
        boolean indexSetFlag = false;
        for (int i = 0, n = categoryList.size(); i < n; i++) {
            MstAbilityCheckCategory categoryEntity = (MstAbilityCheckCategory)categoryList.get(i);
            String categoryResult = viewEntity.getSub1ChkResult(categoryEntity.getCategoryCd());
            // カテゴリ結果を設定
            categoryEntity.setCategoryResult(categoryResult);
            if (!indexSetFlag && categoryResult != null && !categoryResult.equals(MlResultRegistCommon.RESULT_EXEMPT)) {
                getMlResultRegistDto().setSelectedTabIndex(i);
                indexSetFlag = true;
            }
        }
        //DTOにセット
        getMlResultRegistDto().setAbilityCheckCategoryInfoList(categoryList);

        
        // 編集画面へ遷移
        return MlResultRegistCommon.VIEW_ID_ABILITY_EDIT;
    }

    public MlResultRegistDto getMlResultRegistDto() {
        return mlResultRegistDto;
    }

    public void setMlResultRegistDto(MlResultRegistDto mlResultRegistDto) {
        this.mlResultRegistDto = mlResultRegistDto;
    }

    public GetAbilityCheckCategoryInfoLogic getMlrrGetAbilityCheckCategoryInfoLogic() {
        return mlrrGetAbilityCheckCategoryInfoLogic;
    }

    public void setMlrrGetAbilityCheckCategoryInfoLogic(
            GetAbilityCheckCategoryInfoLogic mlrrGetAbilityCheckCategoryInfoLogic) {
        this.mlrrGetAbilityCheckCategoryInfoLogic = mlrrGetAbilityCheckCategoryInfoLogic;
    }

    public GetAbilityCheckCategoryResultLogic getMlrrGetAbilityCheckCategoryResultLogic() {
        return mlrrGetAbilityCheckCategoryResultLogic;
    }

    public void setMlrrGetAbilityCheckCategoryResultLogic(
            GetAbilityCheckCategoryResultLogic mlrrGetAbilityCheckCategoryResultLogic) {
        this.mlrrGetAbilityCheckCategoryResultLogic = mlrrGetAbilityCheckCategoryResultLogic;
    }

    public GetAbilityCheckCategoryResultLogic getMlrrGetAbilityCheckLastCategoryResultLogic() {
        return mlrrGetAbilityCheckLastCategoryResultLogic;
    }

    public void setMlrrGetAbilityCheckLastCategoryResultLogic(
            GetAbilityCheckCategoryResultLogic mlrrGetAbilityCheckLastCategoryResultLogic) {
        this.mlrrGetAbilityCheckLastCategoryResultLogic = mlrrGetAbilityCheckLastCategoryResultLogic;
    }

    public GetAbilityCheckMeisaiInfoLogic getMlrrGetAbilityCheckMeisaiInfoLogic() {
        return mlrrGetAbilityCheckMeisaiInfoLogic;
    }

    public void setMlrrGetAbilityCheckMeisaiInfoLogic(
            GetAbilityCheckMeisaiInfoLogic mlrrGetAbilityCheckMeisaiInfoLogic) {
        this.mlrrGetAbilityCheckMeisaiInfoLogic = mlrrGetAbilityCheckMeisaiInfoLogic;
    }
}
