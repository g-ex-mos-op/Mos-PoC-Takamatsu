/*
 * 作成日: 2006/08/23
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.logic.impl;

import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.verifier.DefaultJapaneseVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.JinmeiVerifier;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.CheckAbilityCheckLogic;

/**
 * 能力チェック入力値チェックロジック
 * 
 * @author xyuchida
 */
public class CheckAbilityCheckLogicImpl implements CheckAbilityCheckLogic {

    /** ロジックID */    
    public static final String LOGIC_ID = "BSM008L23";

    public void execute(MlResultRegistDto mlResultRegistDto) {

        // アセッサー
        String assesser = mlResultRegistDto.getTrnAbilityCheckCategoryResult().getAssesser();
        if (assesser == null || assesser.trim().length() == 0) {
            throw new NotNullException("アセッサー", "assesser", null);
        }
// アセッサー 入力制限をユーザー登録に合わせる
//  ・レングス 20Byte->40byte(DBは80Byteだが入力は40Byte）
//  ・入力可能文字 人名verifierを使用
//        ZenkakuVerifier zenkakuVerifier = new ZenkakuVerifier();
//        if (!zenkakuVerifier.validate(assesser)) {
//            throw new InvalidInputException("アセッサー", "assesser", null);
//        }
//        if (assesser.trim().getBytes().length > 20) {
//            throw new NotRelevantException("アセッサー", "全角１０文字以内", "assesser", null);
//        }
        DefaultJapaneseVerifier jinmeiVerifier = new JinmeiVerifier();
        if (!jinmeiVerifier.validate(assesser.trim())) {
            throw new InvalidInputException("アセッサー", "assesser", null);
        }
        // 文字列長チェック
        if (assesser.trim().getBytes().length > 40) {
            throw new InvalidInputException("アセッサー", "assesser", null);
        }
    }
}