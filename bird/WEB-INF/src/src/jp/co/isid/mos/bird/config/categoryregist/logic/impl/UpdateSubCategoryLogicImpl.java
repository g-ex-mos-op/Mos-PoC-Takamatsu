/*
 * 作成日: 2006/02/21
 *
 */
package jp.co.isid.mos.bird.config.categoryregist.logic.impl;

import java.sql.Timestamp;
import java.util.Iterator;

import jp.co.isid.mos.bird.config.categoryregist.dao.MstSubCategoryInfoDao;
import jp.co.isid.mos.bird.config.categoryregist.dao.TrnSubCateIdDao;
import jp.co.isid.mos.bird.config.categoryregist.dto.CategoryRegistDto;
import jp.co.isid.mos.bird.config.categoryregist.entity.MstSubCategoryInfo;
import jp.co.isid.mos.bird.config.categoryregist.entity.TrnSubCateId;
import jp.co.isid.mos.bird.config.categoryregist.logic.UpdateSubCategoryLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.Formatter;

/**
 * @author xyuchida
 *
 */
public class UpdateSubCategoryLogicImpl implements UpdateSubCategoryLogic {

    private static final String SCREENID_CATEGORY_REGIST = "BCF004";

    /**
     * 情報サブカテゴリDAO
     */
    private MstSubCategoryInfoDao mstSubCategoryInfoDao;

    /**
     * サブカテゴリID採番DAO
     */
    private TrnSubCateIdDao trnSubCateIdDao;

    /**
     * コードフォーマッター
     */
    private Formatter formatter;

    /**
     * 情報サブカテゴリDAOを取得します。
     * @return 情報サブカテゴリDAO
     */
    public MstSubCategoryInfoDao getMstSubCategoryInfoDao() {
        return mstSubCategoryInfoDao;
    }

    /**
     * 情報サブカテゴリDAOを設定します。
     * @param mstSubCategoryInfoDao 情報サブカテゴリDAO
     */
    public void setMstSubCategoryInfoDao(
            MstSubCategoryInfoDao mstSubCategoryInfoDao) {
        this.mstSubCategoryInfoDao = mstSubCategoryInfoDao;
    }

    /**
     * サブカテゴリID採番DAOを取得します。
     * @return サブカテゴリID採番DAO
     */
    public TrnSubCateIdDao getTrnSubCateIdDao() {
        return trnSubCateIdDao;
    }

    /**
     * サブカテゴリID採番DAOを設定します。
     * @param trnSubCateIdDao サブカテゴリID採番DAO
     */
    public void setTrnSubCateIdDao(TrnSubCateIdDao trnSubCateIdDao) {
        this.trnSubCateIdDao = trnSubCateIdDao;
    }

    /**
     * コードフォーマッターを取得します。
     * @return コードフォーマッター
     */
    public Formatter getFormatter() {
        return formatter;
    }

    /**
     * コードフォーマッターを設定します。
     * @param formatter コードフォーマッター
     */
    public void setFormatter(Formatter formatter) {
        this.formatter = formatter;
    }

    /**
     * 情報サブカテゴリ更新
     * 
     * @param userId 更新ユーザID
     * @param categoryRegistDto カテゴリ登録DTO
     */
    public void execute(String userId, CategoryRegistDto categoryRegistDto) {

        // サブカテゴリIDカウンタ取得
        int subCateIdInitValue = getSubCateIdCounter(
                categoryRegistDto.getInfoShu(), categoryRegistDto.getCateId());
        int subCateIdCounter = subCateIdInitValue;
        String subCateId = null;

        // 現在日付取得
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();

        for (Iterator it = categoryRegistDto.getEditList().iterator(); it.hasNext();) {

            MstSubCategoryInfo mstSubCategoryInfo = (MstSubCategoryInfo) it.next();

            if (mstSubCategoryInfo.isChkFlg()) {
                // delete
                getMstSubCategoryInfoDao().deleteSubCategory(mstSubCategoryInfo);

            }
            else {
            	// スペース除去
            	mstSubCategoryInfo.setNewCateName(mstSubCategoryInfo.getNewCateName().replaceAll("^[\\s　]+|[\\s　]+$", ""));
            	if (mstSubCategoryInfo.isInsertFlg()) {
	
	                // サブカテゴリID採番
	                subCateId = getFormatter().format(String.valueOf(++subCateIdCounter), false);
	
	                // プライマリキー設定
	                mstSubCategoryInfo.setInfoShu(categoryRegistDto.getInfoShu());
	                mstSubCategoryInfo.setCateId(categoryRegistDto.getCateId());
	                mstSubCategoryInfo.setSubCateId(subCateId);
	
	                // 共通情報設定
	                mstSubCategoryInfo.setFirstUser(userId);
	                mstSubCategoryInfo.setLastUser(userId);
	                mstSubCategoryInfo.setFirstTmsp(currentTimestamp);
	                mstSubCategoryInfo.setLastTmsp(currentTimestamp);
	                mstSubCategoryInfo.setFirstPgm(SCREENID_CATEGORY_REGIST);
	                mstSubCategoryInfo.setLastPgm(SCREENID_CATEGORY_REGIST);
	
	                // insert
	                getMstSubCategoryInfoDao().insertSubCategory(mstSubCategoryInfo);
	
	            } else {
	
	                // 共通情報設定
	                mstSubCategoryInfo.setLastUser(userId);
	                mstSubCategoryInfo.setLastPgm(SCREENID_CATEGORY_REGIST);
	
	                // update
	                getMstSubCategoryInfoDao().updateSubCategory(mstSubCategoryInfo);
	            }
            }
        }

        // サブカテゴリID採番を行ったか判定する
        if (subCateIdCounter > subCateIdInitValue) {
            // サブカテゴリID採番カウンタ更新
            updateSubCateIdCounter(userId, categoryRegistDto.getInfoShu(), categoryRegistDto.getCateId(), subCateId);
        }
    }

    protected int getSubCateIdCounter(String infoShu, String cateId) {

        // サブカテゴリIDカウンタ取得
        int subCateIdCounter = 0;
        String maxSubCateId = getTrnSubCateIdDao().getId(infoShu, cateId);
        if (maxSubCateId != null) {
            subCateIdCounter = Integer.parseInt(maxSubCateId);
        }

        return subCateIdCounter;
    }

    protected void updateSubCateIdCounter(
            String userId, String infoShu, String cateId, String subCateId) {

        TrnSubCateId trnSubCateId = new TrnSubCateId();

        // 最後に登録したサブカテゴリIDを設定
        trnSubCateId.setInfoShu(infoShu);
        trnSubCateId.setCateId(cateId);
        trnSubCateId.setId(subCateId);

        // 現在日付取得
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();

        // 共通情報設定
        trnSubCateId.setFirstUser(userId);
        trnSubCateId.setFirstPgm(SCREENID_CATEGORY_REGIST);
        trnSubCateId.setFirstTmsp(currentTimestamp);
        trnSubCateId.setLastUser(userId);
        trnSubCateId.setLastPgm(SCREENID_CATEGORY_REGIST);
        trnSubCateId.setLastTmsp(currentTimestamp);

        // delete
        getTrnSubCateIdDao().deleteId(infoShu, cateId);

        // insert
        getTrnSubCateIdDao().insertId(trnSubCateId);
    }
}
