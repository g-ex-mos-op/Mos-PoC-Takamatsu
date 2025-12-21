/*
 * 作成日: 2006/02/21
 *
 */
package jp.co.isid.mos.bird.config.categoryregist.logic.impl;

import java.sql.Timestamp;
import java.util.Iterator;

import jp.co.isid.mos.bird.config.categoryregist.dao.MstCategoryInfoDao;
import jp.co.isid.mos.bird.config.categoryregist.dao.TrnCateIdDao;
import jp.co.isid.mos.bird.config.categoryregist.dto.CategoryRegistDto;
import jp.co.isid.mos.bird.config.categoryregist.entity.MstCategoryInfo;
import jp.co.isid.mos.bird.config.categoryregist.entity.TrnCateId;
import jp.co.isid.mos.bird.config.categoryregist.logic.UpdateCategoryLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.Formatter;

/**
 * @author xyuchida
 *
 */
public class UpdateCategoryLogicImpl implements UpdateCategoryLogic {

    private static final String SCREENID_CATEGORY_REGIST = "BCF004";

    /**
     * 情報カテゴリDAO
     */
    private MstCategoryInfoDao mstCategoryInfoDao;

    /**
     * カテゴリID採番DAO
     */
    private TrnCateIdDao trnCateIdDao;

    /**
     * コードフォーマッター
     */
    private Formatter formatter;

    /**
     * 情報カテゴリDAOを取得します。
     * @return 情報カテゴリDAO
     */
    public MstCategoryInfoDao getMstCategoryInfoDao() {
        return mstCategoryInfoDao;
    }

    /**
     * 情報カテゴリDAOを設定します。
     * @param mstCategoryInfoDao 情報カテゴリDAO
     */
    public void setMstCategoryInfoDao(MstCategoryInfoDao mstCategoryInfoDao) {
        this.mstCategoryInfoDao = mstCategoryInfoDao;
    }

    /**
     * カテゴリID採番DAOを取得します。
     * @return カテゴリID採番DAO
     */
    public TrnCateIdDao getTrnCateIdDao() {
        return trnCateIdDao;
    }

    /**
     * カテゴリID採番DAOを設定します。
     * @param trnCateIdDao カテゴリID採番DAO
     */
    public void setTrnCateIdDao(TrnCateIdDao trnCateIdDao) {
        this.trnCateIdDao = trnCateIdDao;
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
     * 情報カテゴリ更新
     * 
     * @param userId 更新ユーザID
     * @param categoryRegistDto カテゴリ登録DTO
     */
    public void execute(String userId, CategoryRegistDto categoryRegistDto) {

        // カテゴリIDカウンタ取得
        int cateIdInitValue = getCateIdCounter(categoryRegistDto.getInfoShu());
        int cateIdCounter = cateIdInitValue;
        String cateId = null;

        // 現在日付取得
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();

        for (Iterator it = categoryRegistDto.getEditList().iterator(); it.hasNext();) {

            MstCategoryInfo mstCategoryInfo = (MstCategoryInfo) it.next();

            // スペース除去
            String str = mstCategoryInfo.getNewCateName();
            if(str != null){
                mstCategoryInfo.setNewCateName(str.replaceAll("^[\\s　]+|[\\s　]+$", ""));
            }

            if (mstCategoryInfo.isChkFlg()) {

                // delete
                getMstCategoryInfoDao().deleteCategory(mstCategoryInfo);

            } else if (mstCategoryInfo.isInsertFlg()) {

                // カテゴリID採番
                cateId = getFormatter().format(String.valueOf(++cateIdCounter), false);

                // プライマリキー設定
                mstCategoryInfo.setInfoShu(categoryRegistDto.getInfoShu());
                mstCategoryInfo.setCateId(cateId);

                // 共通情報設定
                mstCategoryInfo.setFirstUser(userId);
                mstCategoryInfo.setFirstPgm(SCREENID_CATEGORY_REGIST);
                mstCategoryInfo.setFirstTmsp(currentTimestamp);
                mstCategoryInfo.setLastUser(userId);
                mstCategoryInfo.setLastPgm(SCREENID_CATEGORY_REGIST);
                mstCategoryInfo.setLastTmsp(currentTimestamp);

                // insert
                getMstCategoryInfoDao().insertCategory(mstCategoryInfo);

            } else {

                // 共通情報設定
                mstCategoryInfo.setLastUser(userId);
                mstCategoryInfo.setLastPgm(SCREENID_CATEGORY_REGIST);

                // update
                getMstCategoryInfoDao().updateCategory(mstCategoryInfo);
            }
        }

        // カテゴリID採番を行ったか判定する
        if (cateIdCounter > cateIdInitValue) {

            // カテゴリID採番カウンタ更新
            updateCateIdCounter(userId, categoryRegistDto.getInfoShu(), cateId);
        }
    }

    protected int getCateIdCounter(String infoShu) {

        // カテゴリIDカウンタ取得
        int cateIdCounter = 0;
        String maxCateId = getTrnCateIdDao().getId(infoShu);
        if (maxCateId != null) {
            cateIdCounter = Integer.parseInt(maxCateId);
        }

        return cateIdCounter;
    }

    protected void updateCateIdCounter(
            String userId, String infoShu, String cateId) {

        TrnCateId trnCateId = new TrnCateId();

        // 最後に登録したカテゴリIDを設定
        trnCateId.setInfoShu(infoShu);
        trnCateId.setId(cateId);

        // 現在日付取得
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();

        // 共通情報設定
        trnCateId.setFirstUser(userId);
        trnCateId.setFirstPgm(SCREENID_CATEGORY_REGIST);
        trnCateId.setFirstTmsp(currentTimestamp);
        trnCateId.setLastUser(userId);
        trnCateId.setLastPgm(SCREENID_CATEGORY_REGIST);
        trnCateId.setLastTmsp(currentTimestamp);

        // delete
        getTrnCateIdDao().deleteId(infoShu);

        // insert
        getTrnCateIdDao().insertId(trnCateId);
    }
}
