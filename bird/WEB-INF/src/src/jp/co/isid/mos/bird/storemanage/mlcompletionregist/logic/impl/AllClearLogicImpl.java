/**
 * 
 */
package jp.co.isid.mos.bird.storemanage.mlcompletionregist.logic.impl;

import jp.co.isid.mos.bird.framework.exception.MissingConfigException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.dao.TrnMlLicenseeDao;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.dto.MlCompletionregistDto;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.entity.TrnMlLicensee;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.logic.AllClearLogic;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.util.MlCompletionregistUtil;

/**
 * ライセンス保持者管理更新研修情報全クリアロジック
 * 
 * @author xkinu
 *
 */
public class AllClearLogicImpl implements AllClearLogic {
    /** ロジックID */
    public static final String LOGIC_ID = MlCompletionregistUtil.SCREEN_ID+"L07";
	
	/** DAO【ライセンス保持者管理情報】*/
	private TrnMlLicenseeDao trnMlLicenseeDao;
	
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.storemanage.mlcompletionregist.logic.AllClearLogic#execute(jp.co.isid.mos.bird.storemanage.mlcompletionregist.dto.MlCompletionregistDto)
	 */
	public int execute(MlCompletionregistDto dto) {
		//事前条件処理
		validate(dto);
		//DAO【研修情報取得】.ライセンス保持者管理(BT26UPJK)情報の更新研修情報全クリア更新を実行します。
		//パラメータEntityを生成
		TrnMlLicensee entity = new TrnMlLicensee();
        entity.setRenew1EntYear("");
        entity.setRenew1EntKai("");
        entity.setRenew1Course("");
        entity.setRenew1CourseName("");
        entity.setRenew1Date("");
        entity.setRenew1Status("");
        entity.setRenew2EntYear("");
        entity.setRenew2EntKai("");
        entity.setRenew2Course("");
        entity.setRenew2CourseName("");
        entity.setRenew2Date("");
        entity.setRenew2Status("");
        entity.setRenew3EntYear("");
        entity.setRenew3EntKai("");
        entity.setRenew3Course("");
        entity.setRenew3CourseName("");
        entity.setRenew3Date("");
        entity.setRenew3Status("");
        entity.setRenew4EntYear("");
        entity.setRenew4EntKai("");
        entity.setRenew4Course("");
        entity.setRenew4CourseName("");
        entity.setRenew4Date("");
        entity.setRenew4Status("");
        entity.setRenew5EntYear("");
        entity.setRenew5EntKai("");
        entity.setRenew5Course("");
        entity.setRenew5CourseName("");
        entity.setRenew5Date("");
        entity.setRenew5Status("");
        entity.setLastTmsp(DateManager.getCurrentTimestamp());
        entity.setLastUser(dto.getUserId());
        entity.setLastPgm(MlCompletionregistUtil.SCREEN_ID);
        
		// TODO 自動生成されたメソッド・スタブ
		return getTrnMlLicenseeDao().updateAllClear(entity);
	}
    /**
     * 事前条件処理
     */
    private void validate(MlCompletionregistDto dto) {
        // 画面DTO
        if (dto == null) {
            throw new MissingDataException("マスタライセンス研修修了登録 画面DTO");
        }
        // ユーザーID
        String userId = dto.getUserId();
        if (MlCompletionregistUtil.isNull(userId)) {
            throw new MissingConfigException("ユーザーID");
        }
    }
	/**
	 * @return trnMlLicenseeDao を戻します。
	 */
	public TrnMlLicenseeDao getTrnMlLicenseeDao() {
		return trnMlLicenseeDao;
	}
	/**
	 * @param trnMlLicenseeDao 設定する trnMlLicenseeDao。
	 */
	public void setTrnMlLicenseeDao(TrnMlLicenseeDao trnMlLicenseeDao) {
		this.trnMlLicenseeDao = trnMlLicenseeDao;
	}

}
