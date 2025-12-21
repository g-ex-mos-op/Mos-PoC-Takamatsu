/*
 * 作成日: 2006/07/28
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.logic.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import jp.co.isid.mos.bird.storemanage.mlresultregist.common.MlResultRegistCommon;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.MstAbilityCheckCategory;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.TrnAbilityCheckDetailResult;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.JudgeAbilityCheckLogic;

/**
 * 能力チェック合否判定ロジック
 * 
 * @author xyuchida
 */
public class JudgeAbilityCheckLogicImpl implements JudgeAbilityCheckLogic {

    /** ロジックID */    
    public static final String LOGIC_ID = "BSM008L12";

    /**
     * 能力チェック合否判定
     * 
     * @param mlResultRegistDto
     */
    public void execute(MlResultRegistDto mlResultRegistDto) {

        Set resultSet = new HashSet();
        // カテゴリ単位で判定
        for (Iterator iti = mlResultRegistDto.getAbilityCheckCategoryInfoList().iterator(); iti.hasNext();) {
            MstAbilityCheckCategory category = (MstAbilityCheckCategory) iti.next();
            String result = null;
            // 免除判定
            if (!category.getCategoryResult().equals(MlResultRegistCommon.RESULT_EXEMPT)) {
                int passCount = 0;
                for (Iterator itj = mlResultRegistDto.getAbilityCheckDetailResultList().iterator(); itj.hasNext();) {
                    TrnAbilityCheckDetailResult detailResult = (TrnAbilityCheckDetailResult) itj.next();
                    // カテゴリ内の合格項目数をカウント
                    if (category.getCategoryCd().equals(detailResult.getCategoryCd())
                            && detailResult.getKoumokuResult().equals(MlResultRegistCommon.RESULT_PASS)) {
                        passCount++;
                    }
                }
                if (passCount >= category.getPassNum()) {
                    // 合格
                    result = MlResultRegistCommon.RESULT_PASS;
                } else {
                    // 不合格
                    result = MlResultRegistCommon.RESULT_FAILURE;
                }

            } else {
                // 免除
                result = MlResultRegistCommon.RESULT_EXEMPT;
            }
            // 結果設定
            mlResultRegistDto.getTrnAbilityCheckCategoryResult().setSub1ChkResult(category.getCategoryCd(), result);
            // 総合結果判定用Setに追加
            resultSet.add(result);
        }

        // 能力チェック総合結果判定
        String abilityCheckResult = null;
        // 全てのカテゴリが免除の場合、免除
        if (resultSet.size() == 1 && resultSet.contains(MlResultRegistCommon.RESULT_EXEMPT)) {
            abilityCheckResult = MlResultRegistCommon.RESULT_EXEMPT;
        } else {
            // 合格、免除をSetから削除
            resultSet.removeAll(Arrays.asList(new Object[] {MlResultRegistCommon.RESULT_PASS, MlResultRegistCommon.RESULT_EXEMPT}));
            // 合格、免除以外の結果が無い場合、合格
            if (resultSet.size() == 0) {
                abilityCheckResult = MlResultRegistCommon.RESULT_PASS;

            // 合格、免除以外の結果がある場合、不合格
            } else {
                abilityCheckResult = MlResultRegistCommon.RESULT_FAILURE;
            }
        }
        mlResultRegistDto.setAbilityCheckResult(abilityCheckResult);
    }
}
