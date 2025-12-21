/*
 * 作成日: 2006/07/28
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.mlresultregist.action.MlResultRegistAbilityCheckConfirmAction;
import jp.co.isid.mos.bird.storemanage.mlresultregist.common.MlResultRegistCommon;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.MstAbilityCheckCategory;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.TrnAbilityCheckResult;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.TrnEntryStatus;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.GetAbilityCheckCategoryInfoLogic;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.GetAbilityCheckCategoryResultLogic;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.GetAbilityCheckMeisaiInfoLogic;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.GetAbilityCheckStaffListLogic;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.RegistAbilityCheckLogic;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * マスターライセンス結果登録 能力チェック編集画面アクション
 * @author xyuchida
 */
public class MlResultRegistAbilityCheckConfirmActionImpl implements
        MlResultRegistAbilityCheckConfirmAction {

    /** アクションID定義 */
    public static final String initialize_ACTION_ID = "BSM008A31";
    public static final String regist_ACTION_ID     = "BSM008A32";
    public static final String next_ACTION_ID       = "BSM008A33";
    public static final String back_ACTION_ID     = "BSM008A34";

    private RegistAbilityCheckLogic mlrrRegistAbilityCheckLogic;
    private GetAbilityCheckCategoryResultLogic mlrrGetAbilityCheckCategoryResultLogic;
    private GetAbilityCheckCategoryResultLogic mlrrGetAbilityCheckLastCategoryResultLogic;
    private GetAbilityCheckMeisaiInfoLogic mlrrGetAbilityCheckMeisaiInfoLogic;
    private GetAbilityCheckCategoryInfoLogic mlrrGetAbilityCheckCategoryInfoLogic;
    private GetAbilityCheckStaffListLogic mlrrGetAbilityCheckStaffListLogic;

    private MlResultRegistDto mlResultRegistDto;

    public RegistAbilityCheckLogic getMlrrRegistAbilityCheckLogic() {
        return mlrrRegistAbilityCheckLogic;
    }
    public void setMlrrRegistAbilityCheckLogic(
            RegistAbilityCheckLogic mlrrRegistAbilityCheckLogic) {
        this.mlrrRegistAbilityCheckLogic = mlrrRegistAbilityCheckLogic;
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
    public GetAbilityCheckCategoryInfoLogic getMlrrGetAbilityCheckCategoryInfoLogic() {
        return mlrrGetAbilityCheckCategoryInfoLogic;
    }
    public void setMlrrGetAbilityCheckCategoryInfoLogic(
            GetAbilityCheckCategoryInfoLogic mlrrGetAbilityCheckCategoryInfoLogic) {
        this.mlrrGetAbilityCheckCategoryInfoLogic = mlrrGetAbilityCheckCategoryInfoLogic;
    }
    public GetAbilityCheckStaffListLogic getMlrrGetAbilityCheckStaffListLogic() {
        return mlrrGetAbilityCheckStaffListLogic;
    }
    public void setMlrrGetAbilityCheckStaffListLogic(
            GetAbilityCheckStaffListLogic mlrrGetAbilityCheckStaffListLogic) {
        this.mlrrGetAbilityCheckStaffListLogic = mlrrGetAbilityCheckStaffListLogic;
    }
    public MlResultRegistDto getMlResultRegistDto() {
        return mlResultRegistDto;
    }
    public void setMlResultRegistDto(MlResultRegistDto mlResultRegistDto) {
        this.mlResultRegistDto = mlResultRegistDto;
    }

    /**
     * 初期処理
     * @return 画面遷移情報
     */
    public String initialize() {
        return null;
    }

    /**
     * 登録
     * @return 画面遷移情報
     */
    public String regist() {

        // 能力チェック結果登録
        getMlrrRegistAbilityCheckLogic().execute(getMlResultRegistDto());

        // エントリースタッフ一覧更新
        updateEntryStaffList();

        // 一覧画面へ遷移
        return MlResultRegistCommon.VIEW_ID_ABILITY_LIST;
    }

    /**
     * 次の受験者を登録
     * @return 画面遷移情報
     */
    public String next() {

        // 能力チェック結果登録
        getMlrrRegistAbilityCheckLogic().execute(getMlResultRegistDto());

        // 次の受験者のインデックス取得
        int nextStaffIndex = getNextStaffIndex(getMlResultRegistDto().getSelectedAbilityCheckIndex());
        // 選択したスタッフIDをDTOへセット
        TrnEntryStatus trnEntryStatus = (TrnEntryStatus) getMlResultRegistDto().getListAbilitiyStaffList().get(nextStaffIndex);
        getMlResultRegistDto().setSelectAbilityCheckStaffId(trnEntryStatus.getStaffId());
        getMlResultRegistDto().setSelectAbilityCechkStaffEntryStatus(trnEntryStatus);
        getMlResultRegistDto().setSelectedAbilityCheckIndex(nextStaffIndex);

        // 能力チェックカテゴリ結果登録情報の取得
        getMlResultRegistDto().setTrnAbilityCheckCategoryResult(
                getMlrrGetAbilityCheckCategoryResultLogic().execute(getMlResultRegistDto()));
        // 表示用Entityにコピー
        TrnAbilityCheckResult viewEntity = new TrnAbilityCheckResult();
        try {
            PropertyUtils.copyProperties(viewEntity, getMlResultRegistDto().getTrnAbilityCheckCategoryResult());
        } catch (Exception e) {
        }
        getMlResultRegistDto().setTrnAbilityCheckViewCategoryResult(viewEntity);
        // カテゴリ前回結果情報の取得
        getMlResultRegistDto().setTrnAbilityCheckLastCategoryResult(
                getMlrrGetAbilityCheckLastCategoryResultLogic().execute(getMlResultRegistDto()));
        // 能力チェック明細結果登録情報の取得
        List listMeisai = getMlrrGetAbilityCheckMeisaiInfoLogic().execute(getMlResultRegistDto());
        getMlResultRegistDto().setAbilityCheckDetailResultList(listMeisai);
        // 能力チェックカテゴリ情報の取得
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
        getMlResultRegistDto().setAbilityCheckCategoryInfoList(categoryList);

        // エントリースタッフ一覧更新
        updateEntryStaffList();

        // 編集画面へ遷移
        return MlResultRegistCommon.VIEW_ID_ABILITY_EDIT;
    }

    /**
     * 戻る
     * @return 画面遷移情報
     */
    public String back() {
        // 編集画面へ遷移
        return MlResultRegistCommon.VIEW_ID_ABILITY_EDIT;
    }

    /**
     * 次の受験者インデックス取得
     * @param selectedIndex 現在選択受験者インデックス
     * @return 次の受験者インデックス
     */
    private int getNextStaffIndex(int selectedIndex) {
        int nextStaffIndex = selectedIndex;
        List staffList = getMlResultRegistDto().getListAbilitiyStaffList();
        if (staffList != null) {
            for (int i = 0; i < staffList.size(); i++) {
                // インデックス算出
                nextStaffIndex = (nextStaffIndex + 1) % staffList.size();
                // 免除以外のみ編集対象とする
                if (!((TrnEntryStatus) staffList.get(nextStaffIndex)).getSub1Result()
                        .equals(MlResultRegistCommon.RESULT_EXEMPT)) {
                    break;
                }
            }
        }
        return nextStaffIndex;
    }

    /**
     * エントリースタッフ一覧更新
     */
    private void updateEntryStaffList() {
        // 対象者一覧取得
        List listEntryStaff = getMlrrGetAbilityCheckStaffListLogic().execute(getMlResultRegistDto());
        getMlResultRegistDto().setListAbilitiyStaffList(listEntryStaff);
        int abilityIndex = -1;
        for (int i = getMlResultRegistDto().getSelectedAbilityCheckIndex(), n = listEntryStaff.size(); i < n; i++) {
            TrnEntryStatus trnEntryStatus = (TrnEntryStatus) listEntryStaff.get(i);
            if (!trnEntryStatus.getSub1Result().equals(MlResultRegistCommon.RESULT_EXEMPT)) {
                abilityIndex = i;
                break;
            }
        }
        getMlResultRegistDto().setSelectedAbilityCheckIndex(abilityIndex);
    }
}
